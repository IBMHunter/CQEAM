package com.sino.ams.newasset.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsCheckBatchDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.model.CheckApproveModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsAssetsTransHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class CheckApproveDAO extends AMSProcedureBaseDAO {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCheckBatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public CheckApproveDAO(SfUserDTO userAccount, AmsAssetsCheckBatchDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
        super.sqlProducer = new CheckApproveModel((SfUserDTO) userAccount, dto);
    }

    /**
     * ���ܣ��������ݣ��������������ϵ������õ�
     *
     * @return boolean
     */
    public boolean approveOrder() {
        boolean operateResult = false;
        boolean autoCommit = true;
        boolean needMsg = true;
        String flowCode = "";
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
            flowCode = dto.getFlowCode();
            if (dto.isFlowOver()) {
                dto.setBatchStatus(AssetsDictConstant.APPROVED);
            } else {
                dto.setBatchStatus(AssetsDictConstant.IN_PROCESS);
            }
            setDTOParameter(dto);
            CheckApproveModel modelProducer = (CheckApproveModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getBatchApproveModel(); //����������
            DBOperator.updateRecord(sqlModel, conn);

            sqlModel = modelProducer.getHeadersApproveModel(); //��������
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = processProcedure(true);
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
                if (needMsg) {
                    processMessage(operateResult, flowCode);
                }
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }

    public boolean newApproveOrder() {
        boolean operateResult = false;
        boolean autoCommit = true;
        boolean needMsg = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
            dto.setBatchStatus(AssetsDictConstant.IN_PROCESS);
            if (dto.isFlowOver()) {
                dto.setBatchStatus(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
            }
            setDTOParameter(dto);
            CheckApproveModel modelProducer = (CheckApproveModel) sqlProducer;

            SQLModel sqlModel = modelProducer.getBatchApproveModel(); //����������
            DBOperator.updateRecord(sqlModel, conn);

            sqlModel = modelProducer.getHeadersApproveModel(); //��������
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = processProcedure(true);
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
                if (needMsg) {
                    processMessage(operateResult, FlowConstant.FLOW_CODE_NEXT);
                }
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }

    public void rejectOrder() {
        boolean operateResult = rejectProcedure();
        if (operateResult) {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
        } else {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
            message.setIsError(true);
        }
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
        message.addParameterValue("�̵㹤��������");
        message.addParameterValue(dto.getBatchNo());
    }


    /**
     * ���ܣ����쵥��������Ϣ��ʾ
     *
     * @param operateResult boolean
     * @param flowCode      String
     */
    private void processMessage(boolean operateResult, String flowCode) {
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
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
        message.addParameterValue("�̵�");
        message.addParameterValue(dto.getBatchNo());
        message.setIsError(!operateResult);
    }

    /**
     * ���ܣ������������ݡ����Ǹ��෽����
      */
    protected void prepareProcedureData(){
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
        dto.setPrimaryKey(dto.getBatchId());
        dto.setOrderNo(dto.getBatchNo());
        dto.setOrderName(dto.getOrderTypeName());
    }
}
