package com.sino.ams.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.dto.*;
import com.sino.ams.newasset.model.OrderApproveModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>
 * Title: AmsAssetsTransHeaderDAO
 * </p>
 * <p>
 * Description:�����Զ����ɷ������AmsAssetsTransHeaderDAO�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class OrderApproveDAO extends AMSProcedureBaseDAO {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public OrderApproveDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        super.sqlProducer = new OrderApproveModel((SfUserDTO) userAccount, dto);
    }

    /**
     * ���ܣ��������ݣ��������������ϵ������õ�
     *
     * @param orderLines ���ĵ������е��۾ɷ����˻�(2008-12-01 17:37)
     * @return boolean
     */
    public boolean approveOrder(DTOSet orderLines, String[] barcodes) {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            boolean flow2End = dto.isFlowOver();
            if (flow2End) {
                if (dto.getTransType().equals(
                        AssetsDictConstant.ASS_DIS)
                        || dto.getTransType().equals(
                        AssetsDictConstant.ASS_SUB)
                        || dto.getTransType().equals(
                        AssetsDictConstant.ASS_SELL)
                        || dto.getTransType().equals(
                        AssetsDictConstant.ASS_RENT)
                        || dto.getTransType().equals(
                        AssetsDictConstant.ASS_DONA)) {
                    dto.setTransStatus(AssetsDictConstant.COMPLETED);
                } else {
                    dto.setTransStatus(AssetsDictConstant.APPROVED);
                }
            } else {
                dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
            }
            setDTOParameter(dto);
            OrderApproveModel modelProducer = (OrderApproveModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getOrderApproveModel();
            DBOperator.updateRecord(sqlModel, conn);

            String provinceCode = servletConfig.getProvinceCode();
            if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_JIN)) {// ��ɽ����Ҫ�ù��ܡ�
                if (dto.getTransferType().equals("BTW_COMP")) {
                    AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, null, conn);
                    lineDAO.uodateAccount(orderLines);

                }
            }
            if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SHAN)) {// ��������
                if (dto.getTransferType().equals("BTW_COMP")) {
                    AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, null, conn);
                    // lineDAO.uodateAccount(orderLines);
                    lineDAO.updateTransLine(orderLines);// ���µ���ص�ͽ�����
                }
            }
            if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)
                    && dto.getAttribute4().equals("MTL_ASSETS")) {
                if (barcodes != null && barcodes.length != 0) {
                    updateTransLineRemark(barcodes);
                }
            }
            if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)
                    && dto.getAttribute4().equals("PROV_ASSETS")) {
                // ���⴦�������ѡ���ʲ�REMARK
                if (barcodes != null && barcodes.length != 0) {
                    deleteTransLineRemark(barcodes);
                    updateTransLineRemark(barcodes);
                }
            }
            if (flow2End) {
                sqlModel = modelProducer.getLineStatusUpdateModel(); // �����ʲ�����������״̬Ϊ������
                DBOperator.updateRecord(sqlModel, conn);
                if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)) { // ����
                    sqlModel = modelProducer.getAssetsDiscardModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                    if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SX)) {// ɽ��ʡ����Ҫͬ��
                        recordChkLog(AssetsDictConstant.ASS_DIS, AssetsDictConstant.STATUS_NO);
                    }
                } else if (dto.getTransType().equals(AssetsDictConstant.ASS_CLR)) { // ����
                    sqlModel = modelProducer.getAssetsClearModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                } else if (dto.getTransType().equals(AssetsDictConstant.ASS_FREE)) { // ����
                    sqlModel = modelProducer.getAssetsFreeModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                } else if (dto.getTransType().equals(AssetsDictConstant.ASS_SUB)) { // ��ֵ
                    sqlModel = modelProducer.getAssetsSubModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                } else if (dto.getTransType().equals(AssetsDictConstant.ASS_SELL)) {
                    sqlModel = modelProducer.getAssetsSellModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                } else if (dto.getTransType().equals(AssetsDictConstant.ASS_RENT)) {
                    sqlModel = modelProducer.getAssetsRentModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                } else if (dto.getTransType().equals(AssetsDictConstant.ASS_DONA)) {
                    sqlModel = modelProducer.getAssetsDonaModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    deleteReserveAssets();
                }
            }
            operateResult = processProcedure(true);
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                } else {
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }

    protected void prepareProcedureData(){
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        dto.setPrimaryKey(dto.getTransId());
        dto.setOrderNo(dto.getTransNo());
        dto.setOrderName(dto.getTransTypeValue());//��ȷ��...
    }

    public boolean newApproveOrder() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
            if (dto.isFlowOver()) {
                dto.setTransStatus(AssetsDictConstant.COMPLETED);
            }
            setDTOParameter(dto);
            OrderApproveModel modelProducer = (OrderApproveModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getOrderApproveModel();
            DBOperator.updateRecord(sqlModel, conn);

            if (dto.isFlowOver()) {
                if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)) { // ����
                    String sfAttribute1 = dto.getSf_task_attribute1();
                    if(!sfAttribute1.equals("CANCEL")){
                        sqlModel = modelProducer.getLineStatusUpdateModel(); // �����ʲ�����������״̬Ϊ������
                        DBOperator.updateRecord(sqlModel, conn);
                        sqlModel = modelProducer.getAssetsDiscardModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        recordChkLog(AssetsDictConstant.ASS_DIS, AssetsDictConstant.STATUS_NO);
                    }
                    deleteReserveAssets();
                }
            }
            operateResult = processProcedure(true);
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                } else {
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
                processMessage(operateResult);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ����쵥��������Ϣ��ʾ
     *
     * @param operateResult boolean
     */
    private void processMessage(boolean operateResult) {
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        if (operateResult) {
            prodMessage(AssetsMessageKeys.PASS_ORDER_SUCCESS);
        } else {
            prodMessage(AssetsMessageKeys.PASS_ORDER_FAILURE);
        }
        String orderType = dto.getTransTypeValue();
        if (orderType.indexOf("��") > -1) {
            orderType = orderType.substring(0, orderType.length() - 1);
        }
        message.addParameterValue(orderType);
        message.addParameterValue(dto.getTransNo());
        message.setIsError(!operateResult);
    }

    /**
     * ���ܣ�ɾ�������ݱ������ʲ��������ڱ��Ϻʹ��õ���
     *
     * @throws DataHandleException
     */
    private void deleteReserveAssets() throws DataHandleException {
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        reserveDTO.setTransId(dto.getTransId());
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,
                reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }


    /**
     * ���ܣ���¼�豸����һ�ν�����������籨�ϣ��������Ӿ�����Ҫ���� ��Ҫͬ����MIS��ʱ����ø÷�����
     *
     * @param orderType String
     * @param isExist   String
     * @throws DataHandleException
     */
    private void recordChkLog(String orderType, String isExist) throws DataHandleException {
        try {
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            AmsAssetsTransLineDTO line = new AmsAssetsTransLineDTO();
            line.setTransNo(dto.getTransNo());
            line.setTransId(dto.getTransId());
            AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, line, conn);
            lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
            DTOSet dtos = (DTOSet) lineDAO.getDataByForeignKey("transId");
            if (dtos != null && !dtos.isEmpty()) {
                int lineCount = dtos.getSize();
                AmsAssetsChkLogDTO chkLogDTO = getChkLogData(orderType, isExist);
                AmsAssetsChkLogDAO chkLogDAO = new AmsAssetsChkLogDAO(userAccount, chkLogDTO, conn);

                AmsItemInfoHistoryDTO historyDTO = getHistoryData();
                AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount, historyDTO, conn);

                for (int i = 0; i < lineCount; i++) {
                    line = (AmsAssetsTransLineDTO) dtos.getDTO(i);

                    processChkLogData(chkLogDTO, line);
                    chkLogDAO.saveCheckLogData();

                    //�豸�����ʷ
                    historyDTO.setBarcode(line.getBarcode());
                    historyDAO.recordHistory();
                }
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
    }

    private AmsAssetsChkLogDTO getChkLogData(String orderType, String isExist){
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsChkLogDTO chkLogDTO = new AmsAssetsChkLogDTO();
        chkLogDTO.setOrderType(orderType);
        chkLogDTO.setIsExist(isExist);

        String orderURL = AssetsURLList.ASSETS_TRANS_SERVLET;
        orderURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
        orderURL += "&transId=" + dto.getTransId();
        chkLogDTO.setOrderDtlUrl(orderURL);
        return chkLogDTO;
    }

    private AmsItemInfoHistoryDTO getHistoryData(){
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        String orderURL = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsDisTransHeaderServlet";
        orderURL += "?act=DETAIL_ACTION";
        orderURL += "&transId=" + dto.getTransId();
        AmsItemInfoHistoryDTO itemDTO = new AmsItemInfoHistoryDTO();
        itemDTO.setOrderNo(dto.getTransNo());
        itemDTO.setOrderCategory("3");
        itemDTO.setCreatedBy(userAccount.getUserId());
        itemDTO.setOrderDtlUrl(orderURL);
        return itemDTO;
    }

    private void processChkLogData(AmsAssetsChkLogDTO chkLogDTO, AmsAssetsTransLineDTO line){
        chkLogDTO.setBarcode(line.getBarcode());
        chkLogDTO.setLastChkNo(line.getTransNo());
        chkLogDTO.setHeaderId(line.getTransId());
        chkLogDTO.setResponsibilityUser(line.getResponsibilityUser());
        chkLogDTO.setResponsibilityDept(line.getDeptCode());
        chkLogDTO.setAddressId(line.getAddressId());
        chkLogDTO.setOrganizationId(userAccount.getOrganizationId());
        chkLogDTO.setCreatedBy(userAccount.getUserId());
    }

    /**
     * ���ܣ�����������ȡ�ʲ��������ݣ������䵱ǰ������ɫ
     *
     * @return Object
     * @throws QueryException
     */
    public Object getDataByPrimaryKey() throws QueryException {
        Object primaryKeyData = super.getDataByPrimaryKey();
        AmsAssetsTransHeaderDTO data = (AmsAssetsTransHeaderDTO) primaryKeyData;
        data.setCurrRoleName(getCurrRoleName());
        data.setServletConfig(servletConfig);
        primaryKeyData = data;
        return primaryKeyData;
    }

    /**
     * ���ܣ���ȡ��ǰ��������ɫ����
     *
     * @return String
     * @throws QueryException
     */
    public String getCurrRoleName() throws QueryException {
        String currRoleName = "";
        try {
            OrderApproveModel modelProducer = (OrderApproveModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getCurrApproveRoleModel();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                currRoleName = simp.getFirstRow().getStrValue("ROLE_NAME");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return currRoleName;
    }

    /**
     * ���ܣ���ȡ��������
     *
     * @return String
     * @throws QueryException
     */
    public String getAccessSheet() throws QueryException {
        String accessSheet = "";
        try {
            OrderApproveModel modelProducer = (OrderApproveModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getAccessSheet();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                accessSheet = simp.getFirstRow().getStrValue("ACCESS_SHEET");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return accessSheet;
    }

    /**
     * ���ܣ���ȡ�����²��ܱ��ϵ��ʲ����������б�REMARKΪ���������ϡ���
     */
    private void updateTransLineRemark(String[] barcodes)
            throws DataHandleException {
        OrderApproveModel modelProducer = (OrderApproveModel) sqlProducer;
        String aggBarcodes = initBarcodes(barcodes);
        // SQLModel sqlModel = modelProducer.updateTransLineRemark(aggBarcodes);
        SQLModel sqlModel = modelProducer.updateTransLineRemark();
        DBOperator.updateRecord(sqlModel, conn);
    }

    // ��ʼ����ȡ��BARCODE
    private String initBarcodes(String[] barcodes) {
        String aggBarcodes = "(";
        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];
            aggBarcodes += "'" + barcode + "', ";
        }
        aggBarcodes += "'aa')";
        // int cc = aggBarcodes.lastIndexOf(",");
        // aggBarcodes = aggBarcodes.substring(0,cc)+")";
        return aggBarcodes;
    }

    /**
     * ���ܣ����⴦�������ѡ���ʲ�REMARK
     */
    private void deleteTransLineRemark(String[] barcodes)
            throws DataHandleException {
        OrderApproveModel modelProducer = (OrderApproveModel) sqlProducer;
        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];
            SQLModel sqlModel = modelProducer.deleteTransLineRemark(barcode);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    public void rejectOrder() {
        boolean operateResult = rejectProcedure();
        if (operateResult) {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
        } else {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
            message.setIsError(true);
        }
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        message.addParameterValue(dto.getTransTypeValue());
        message.addParameterValue(dto.getTransNo());
    }
}
