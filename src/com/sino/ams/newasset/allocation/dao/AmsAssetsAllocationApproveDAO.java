package com.sino.ams.newasset.allocation.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.newasset.allocation.model.AmsAssetsAllocationApproveModel;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.dao.AmsAssetsChkLogDAO;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AssetsOrderConfirmDAO;
import com.sino.ams.newasset.dao.AssetsReceiveDAO;
import com.sino.ams.newasset.dto.AmsAssetsChkLogDTO;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-3-31
 * Time: 12:54:41
 * To change this template use File | Settings | File Templates.
 */
public class AmsAssetsAllocationApproveDAO extends AMSProcedureBaseDAO {

    public AmsAssetsAllocationApproveDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        super.sqlProducer = new AmsAssetsAllocationApproveModel((SfUserDTO) userAccount, dto);
    }

    public boolean approveOrder(DTOSet orderLines) {
        boolean operateResult = true;
        boolean autoCommit = true;
        boolean needMsg = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            boolean needConfirm = dto.getSf_task_attribute1().equals("CONFIRM");
            if (dto.getTransferType().equals("BTW_COMP") && dto.getSf_task_attribute4().equals("CANCEL") && dto.getSf_end().equals("1")) {
                dto.setTransStatus(AssetsDictConstant.CANCELED);
                setDTOParameter(dto);
                AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
                SQLModel sqlModel = modelProducer.getOrderApproveModel();
                DBOperator.updateRecord(sqlModel, conn);
                sqlModel = modelProducer.updateLineStatusModel(AssetsDictConstant.CANCELED); //�����ʲ�����������״̬Ϊ�ѳ���
                DBOperator.updateRecord(sqlModel, conn);
                sqlModel = modelProducer.deleteReserveModel(); //ɾ�����ﱣ����
                DBOperator.updateRecord(sqlModel, conn);
            } else {
                if (dto.getSf_end().equals("1")) {
                    dto.setTransStatus(AssetsDictConstant.APPROVED);
                } else {
                    dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
                }
                setDTOParameter(dto);
                AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
                SQLModel sqlModel = modelProducer.getOrderApproveModel();
                DBOperator.updateRecord(sqlModel, conn);
                if (dto.getSf_end().equals("1")) {
                    sqlModel = modelProducer.getLineStatusUpdateModel(); //�����ʲ�����������״̬Ϊ������
                    DBOperator.updateRecord(sqlModel, conn);
                }
                if (dto.getSf_task_attribute1().equals("DEPT")) {//���ż�������벿���ʲ�����Ա���ܷ���
                    AssetsReceiveDAO receiveDAO = new AssetsReceiveDAO(userAccount, dto, conn);
                    operateResult = receiveDAO.assignTransAssets(orderLines);
                }
                if (dto.getTransferType().equals("BTW_COMP")) {
                    AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(userAccount, null, conn);
                    lineDAO.updateTransLine(orderLines);//���µ���ص�ͽ�����
                    lineDAO.uodateAccount(orderLines);
                }
                //TODO ������������Ҫ�ѵ���������Ŀ����Ϣת�����뷽�ʲ���һ����ע2��
                if (dto.getSf_end().equals("1")) {
                    sqlModel = modelProducer.updateAssetsRemark2(); //������������Ҫ�ѵ���������Ŀ����Ϣת�����뷽�ʲ���һ����ע2��
                    DBOperator.updateRecord(sqlModel, conn);
                }
            }
            if(needConfirm){
                confirmAssets();
            }
            operateResult = processProcedure(true);
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                } else {
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
                if (needMsg) {
                    processMessage(operateResult);
                }
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }

    private void confirmAssets() throws DataHandleException {
        AssetsOrderConfirmDAO orderConfirmDAO = new AssetsOrderConfirmDAO(userAccount, dtoParameter, conn);
        orderConfirmDAO.confirmOrderAssets();
    }

    protected void prepareProcedureData(){
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        dto.setPrimaryKey(dto.getTransId());
        dto.setOrderNo(dto.getTransNo());
        if (dto.getTransferType().equals(AssetsDictConstant.TRANS_INN_DEPT)) {
            dto.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_INN_DEPT);
        } else if (dto.getTransferType().equals(AssetsDictConstant.TRANS_BTW_DEPT)) {
            dto.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_BTW_DEPT);
        } else {
            dto.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_BTW_COMP);
        }
    }

    public void rejectOrder() {
        boolean operateResult = rejectProcedure();
        if(operateResult){
            prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
        } else {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
            message.setIsError(true);
        }
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        message.addParameterValue(dto.getTransTypeValue());
        message.addParameterValue(dto.getTransNo());
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
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ���¼�豸����һ�ν�����������籨�ϣ��������Ӿ�����Ҫ����
     * ��Ҫͬ����MIS��ʱ����ø÷�����
     *
     * @param orderType String
     * @param isExist   String
     * @throws DataHandleException
     */
    private void recordChkLog(String orderType, String isExist) throws DataHandleException {
        try {
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            AmsAssetsTransLineDTO line = new AmsAssetsTransLineDTO();
            line.setTransId(dto.getTransId());
            AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(userAccount, line, conn);
            lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
            DTOSet dtos = (DTOSet) lineDAO.getDataByForeignKey("transId");
            if (dtos != null && !dtos.isEmpty()) {
                int lineCount = dtos.getSize();
                String orderUrl = "";
                AmsAssetsChkLogDTO chkLogDTO = null;
                AmsAssetsChkLogDAO chkLogDAO = new AmsAssetsChkLogDAO(userAccount, null, conn);
                for (int i = 0; i < lineCount; i++) {
                    line = (AmsAssetsTransLineDTO) dtos.getDTO(i);
                    chkLogDTO = new AmsAssetsChkLogDTO();
                    chkLogDTO.setBarcode(line.getBarcode());
                    chkLogDTO.setLastChkNo(line.getTransNo());
                    chkLogDTO.setHeaderId(line.getTransId());
                    chkLogDTO.setResponsibilityUser(line.getResponsibilityUser());
                    chkLogDTO.setResponsibilityDept(line.getDeptCode());
                    chkLogDTO.setAddressId(line.getAddressId());
                    chkLogDTO.setOrganizationId(userAccount.getOrganizationId());
                    chkLogDTO.setCreatedBy(userAccount.getUserId());
                    chkLogDTO.setOrderType(orderType);
                    chkLogDTO.setIsExist(isExist);
                    orderUrl = AssetsURLList.ASSETS_TRANS_SERVLET;
                    orderUrl += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    orderUrl += "&transId=" + line.getTransId();
                    chkLogDTO.setOrderDtlUrl(orderUrl);
                    chkLogDAO.setDTOParameter(chkLogDTO);
                    chkLogDAO.saveCheckLogData();
                }
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
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
//        data.setCurrRoleName(getCurrRoleName());
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
            AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getCurrApproveRoleModel();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                currRoleName = simp.getFirstRow().getStrValue("ROLENAME");
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
            AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
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
    private void updateTransLineRemark(String[] barcodes) throws DataHandleException {
        AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
        String aggBarcodes = initBarcodes(barcodes);
        SQLModel sqlModel = modelProducer.updateTransLineRemark();
        DBOperator.updateRecord(sqlModel, conn);
    }

    //��ʼ����ȡ��BARCODE
    private String initBarcodes(String[] barcodes) {
        String aggBarcodes = "(";
        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];
            aggBarcodes += "'" + barcode + "', ";
        }
        aggBarcodes += "'aa')";
        return aggBarcodes;
    }

    /**
     * ���ܣ����⴦�������ѡ���ʲ�REMARK
     */
    private void deleteTransLineRemark(String[] barcodes) throws DataHandleException {
        AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];
            SQLModel sqlModel = modelProducer.deleteTransLineRemark(barcode);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    //�ж��Ƿ����
    public boolean isFinanceGroup(int fromGroup) throws QueryException {
        AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsFinanceGroupModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    //�ж��Ƿ�ʵ�ﲿ��
    public boolean isSpecialGroup(int fromGroup) throws QueryException {
        AmsAssetsAllocationApproveModel modelProducer = (AmsAssetsAllocationApproveModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsSpecialGroupModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }
}
