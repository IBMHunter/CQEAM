package com.sino.td.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsChkLogDTO;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
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
import com.sino.base.util.StrUtil;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.td.commom.TdURLDefineList;
import com.sino.td.newasset.dto.TdAssetsTransHeaderDTO;
import com.sino.td.newasset.dto.TdAssetsTransLineDTO;
import com.sino.td.newasset.model.TdOrderApproveModel;

/**
 * <p>Title: AmsAssetsTransHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class TdOrderApproveDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter TdAssetsTransHeaderDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public TdOrderApproveDAO(SfUserDTO userAccount, TdAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO)dtoParameter;
        super.sqlProducer = new TdOrderApproveModel((SfUserDTO) userAccount, dto);
    }

    /**
     * ���ܣ��������ݣ��������������ϵ������õ�
     * @param flowDTO FlowDTO
     * @param orderLines ���ĵ������е��۾ɷ����˻�(2008-12-01 17:37)
     * @return boolean
     */
    public boolean approveOrder(FlowDTO flowDTO, DTOSet orderLines, String[] barcodes) {
        boolean operateResult = false;
        boolean autoCommit = true;
        boolean needMsg = true;
        String flowCode = "";
        try {
            if (canApprove()) {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO)dtoParameter;
                flowCode = dto.getFlowCode();
                flowDTO.setProcName(dto.getProcdureName());
                flowDTO.setActivity(flowCode);
                flowDTO.setApplyId(dto.getTransId());
                flowDTO.setSessionUserId(userAccount.getUserId());
                flowDTO.setSessionUserName(userAccount.getUsername());
                flowDTO.setApplyNo(dto.getTransNo());
                FlowAction flowProcessor = new FlowAction(conn, flowDTO);
                boolean flow2End = flowProcessor.isFlowToEnd();
                dto.setFlow2End(flow2End);
                setDTOParameter(dto);
                if (flowCode.equals(FlowConstant.FLOW_CODE_NEXT)) {
                    if (flow2End) {
                    	if(dto.getTransType().equals(AssetsDictConstant.ASS_DIS) || dto.getTransType().equals(AssetsDictConstant.ASS_SUB) || dto.getTransType().equals(AssetsDictConstant.ASS_SELL) || dto.getTransType().equals(AssetsDictConstant.ASS_RENT) || dto.getTransType().equals(AssetsDictConstant.ASS_DONA)){
                    		dto.setTransStatus(AssetsDictConstant.COMPLETED);
                    	} else {
                    		dto.setTransStatus(AssetsDictConstant.APPROVED);
                    	}                        
                    } else {
                        dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
                    }
                    flowProcessor.flow();
                } else {
                    dto.setTransStatus(AssetsDictConstant.REJECTED);
                    flowProcessor.reject2Begin();
                }
                setDTOParameter(dto);
                TdOrderApproveModel modelProducer = (TdOrderApproveModel)sqlProducer;
                SQLModel sqlModel = modelProducer.getOrderApproveModel();
                DBOperator.updateRecord(sqlModel, conn);

                String provinceCode = servletConfig.getProvinceCode();
                if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_JIN) && flowCode.equals(FlowConstant.FLOW_CODE_NEXT)){//��ɽ����Ҫ�ù��ܡ�
                    if(dto.getTransferType().equals("BTW_COMP")){
                        TdAssetsTransLineDAO lineDAO = new TdAssetsTransLineDAO(userAccount, null, conn);
                        lineDAO.uodateAccount(orderLines);

                    }
                }
                if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SHAN) && flowCode.equals(FlowConstant.FLOW_CODE_NEXT)){//��������
                    if(dto.getTransferType().equals("BTW_COMP")){
                        TdAssetsTransLineDAO lineDAO = new TdAssetsTransLineDAO(userAccount, null, conn);
//						lineDAO.uodateAccount(orderLines);
                        lineDAO.updateTransLine(orderLines);//���µ���ص�ͽ�����
                    }
                }
                //���������ʲ�����ʡ��˾רҵ�ʲ�����Ա����ʱ����
//                if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS) &&
//                      provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SHAN) &&
//                      userAccount.getOrganizationId().equals("82") && dto.getServletConfig().getMtlAssetsMgr().equals("רҵ�ʲ�����Ա")) {
                if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS) && dto.getAttribute4().equals("MTL_ASSETS")) {
                    if (barcodes != null && barcodes.length != 0) {
                       updateTransLineRemark(barcodes);
                    }
                }
                //���������ʲ�����ʡ��˾�ʲ��������ʱ����,�ڶ����Ĳ�������
//                if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS) &&
//                      provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SHAN) &&
//                      userAccount.getOrganizationId().equals("82") && dto.getServletConfig().getProvAssetsMgr().equals("ȫʡ�ʲ�����Ա")) {
                if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS) &&  dto.getAttribute4().equals("PROV_ASSETS")) {
                    //���⴦�������ѡ���ʲ�REMARK
                    if (barcodes != null && barcodes.length != 0) {
                        deleteTransLineRemark(barcodes);
                        updateTransLineRemark(barcodes);
                    }
                }
                if (flow2End && flowCode.equals(FlowConstant.FLOW_CODE_NEXT)) {
                    sqlModel = modelProducer.getLineStatusUpdateModel(); //�����ʲ�����������״̬Ϊ������
                    DBOperator.updateRecord(sqlModel, conn);
                    if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)) { //����
                        sqlModel = modelProducer.getAssetsDiscardModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                        if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SX)){//ɽ��ʡ����Ҫͬ��
                            recordChkLog(AssetsDictConstant.ASS_DIS, AssetsDictConstant.STATUS_NO);
                        }
                    } else if (dto.getTransType().equals(AssetsDictConstant.ASS_CLR)) { //����
                        sqlModel = modelProducer.getAssetsClearModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                    } else if (dto.getTransType().equals(AssetsDictConstant.ASS_FREE)) { //����
                        sqlModel = modelProducer.getAssetsFreeModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                    } else if (dto.getTransType().equals(AssetsDictConstant.ASS_SUB)) { //��ֵ
                        sqlModel = modelProducer.getAssetsSubModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                    } else if (dto.getTransType().equals(AssetsDictConstant.ASS_SELL)){
                    	sqlModel = modelProducer.getAssetsSellModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                    } else if (dto.getTransType().equals(AssetsDictConstant.ASS_RENT)){
                    	sqlModel = modelProducer.getAssetsRentModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                    } else if (dto.getTransType().equals(AssetsDictConstant.ASS_DONA)){
                    	sqlModel = modelProducer.getAssetsDonaModel();
                        DBOperator.updateRecord(sqlModel, conn);
                        deleteReserveAssets();
                    }
                }
                operateResult = true;
            } else {
                prodMessage(AssetsMessageKeys.APPROVE_INVALID);
                message.setIsError(!operateResult);
                needMsg = false;
            }
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (ContainerException ex) {
            ex.printLog();
        } catch (QueryException ex) {
            ex.printLog();
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                } else {
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
                if (needMsg) {
                    processMessage(operateResult, flowCode);
                }
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }


    /**
     * ���ܣ����쵥��������Ϣ��ʾ
     * @param operateResult boolean
     * @param flowCode String
     */
    private void processMessage(boolean operateResult, String flowCode) {
        TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO)dtoParameter;
        if (flowCode.equals(FlowConstant.FLOW_CODE_NEXT)) {
            if (operateResult) {
                prodMessage(AssetsMessageKeys.PASS_ORDER_SUCCESS);
            } else {
                prodMessage(AssetsMessageKeys.PASS_ORDER_FAILURE);
            }
        } else {
            if (operateResult) {
                prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
            } else {
                prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
            }
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
     * @throws DataHandleException
     */
    private void deleteReserveAssets() throws DataHandleException {
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO)dtoParameter;
        reserveDTO.setTransId(dto.getTransId());
        TdAssetsReservedDAO reserveDAO = new TdAssetsReservedDAO(userAccount,reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ��жϸõ����Ƿ��������
     * @return boolean
     * @throws QueryException
     */
    private boolean canApprove() throws QueryException {
//		OrderApproveModel modelProducer = (OrderApproveModel)sqlProducer;
//		SQLModel sqlModel = modelProducer.getCanApproveModel();
//		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
//		simp.executeQuery();
//		return simp.hasResult();
        return true;
    }

    /**
     * ���ܣ���¼�豸����һ�ν�����������籨�ϣ��������Ӿ�����Ҫ����
     * ��Ҫͬ����MIS��ʱ����ø÷�����
     * @param orderType String
     * @param isExist String
     * @throws DataHandleException
     */
    private void recordChkLog(String orderType, String isExist) throws DataHandleException {
        try {
            TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO) dtoParameter;
            TdAssetsTransLineDTO line = new TdAssetsTransLineDTO();
            line.setTransId(dto.getTransId());
            TdAssetsTransLineDAO lineDAO = new TdAssetsTransLineDAO(userAccount, line, conn);
            lineDAO.setDTOClassName(TdAssetsTransLineDTO.class.getName());
            DTOSet dtos = (DTOSet) lineDAO.getDataByForeignKey("transId");
            if (dtos != null && !dtos.isEmpty()) {
                int lineCount = dtos.getSize();
                String orderUrl = "";
                AmsAssetsChkLogDTO chkLogDTO = null;
                TdAssetsChkLogDAO chkLogDAO = new TdAssetsChkLogDAO(userAccount, null, conn);
                for(int i = 0; i < lineCount; i++){
                    line = (TdAssetsTransLineDTO)dtos.getDTO(i);
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
                    orderUrl = TdURLDefineList.ASSETS_TRANS_SERVLET_TD;
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
     * @return Object
     * @throws QueryException
     */
    public Object getDataByPrimaryKey() throws QueryException {
        Object primaryKeyData = super.getDataByPrimaryKey();
        TdAssetsTransHeaderDTO data = (TdAssetsTransHeaderDTO) primaryKeyData;
        data.setCurrRoleName(getCurrRoleName());
        data.setServletConfig(servletConfig);
        primaryKeyData = data;
        return primaryKeyData;
    }

    /**
     * ���ܣ���ȡ��ǰ��������ɫ����
     * @return String
     * @throws QueryException
     */
    public String getCurrRoleName() throws QueryException {
        String currRoleName = "";
        try {
        	TdOrderApproveModel modelProducer = (TdOrderApproveModel) sqlProducer;
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
     * @return String
     * @throws QueryException
     */
    public String getAccessSheet() throws QueryException {
        String accessSheet = "";
        try {
        	TdOrderApproveModel modelProducer = (TdOrderApproveModel) sqlProducer;
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
    	TdOrderApproveModel modelProducer = (TdOrderApproveModel) sqlProducer;
        String aggBarcodes = initBarcodes(barcodes);
//        SQLModel sqlModel = modelProducer.updateTransLineRemark(aggBarcodes);
        SQLModel sqlModel = modelProducer.updateTransLineRemark();
        DBOperator.updateRecord(sqlModel, conn);
    }
    //��ʼ����ȡ��BARCODE
    private String initBarcodes (String[] barcodes) {
        String aggBarcodes = "(";
        for (int i = 0; i < barcodes.length; i++) {
             String barcode = barcodes[i];
             aggBarcodes += "'" + barcode + "', ";
        }
        aggBarcodes += "'aa')";
//        int cc = aggBarcodes.lastIndexOf(",");
//        aggBarcodes = aggBarcodes.substring(0,cc)+")";
        return aggBarcodes;
    }

    /**
     * ���ܣ����⴦�������ѡ���ʲ�REMARK
     */
    private void deleteTransLineRemark(String[] barcodes) throws DataHandleException {
    	TdOrderApproveModel modelProducer = (TdOrderApproveModel) sqlProducer;
        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];
            SQLModel sqlModel = modelProducer.deleteTransLineRemark(barcode);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }
}
