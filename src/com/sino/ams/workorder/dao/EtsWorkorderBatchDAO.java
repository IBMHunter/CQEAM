package com.sino.ams.workorder.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderBatchDTO;
import com.sino.ams.workorder.model.EtsWorkorderBatchModel;
import com.sino.ams.workorder.model.OrderExtendModel;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.bean.FlowCommonUtil;
import com.sino.sinoflow.flowinterface.AppFlowBaseDTO;


/**
 * <p>Title: EtsWorkorderBatchDAO</p>
 * <p>Description:�����Զ����ɷ������EtsWorkorderBatchDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class EtsWorkorderBatchDAO extends BaseDAO {

    private EtsWorkorderBatchModel etsWorkorderBatchModel = null;
    private SfUserDTO sfUser = null;
    protected FlowCommonUtil flowCommonUtil = null;
    private String hId = "";

    /**
     * ���ܣ���������(EAM) ETS_WORKORDER_BATCH ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsWorkorderBatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsWorkorderBatchDAO(SfUserDTO userAccount, EtsWorkorderBatchDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
        initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsWorkorderBatchDTO dtoPara = (EtsWorkorderBatchDTO) dtoParameter;
        super.sqlProducer = new EtsWorkorderBatchModel((SfUserDTO) userAccount, dtoPara);
        etsWorkorderBatchModel = (EtsWorkorderBatchModel) sqlProducer;
    }

    /**
     * ���ܣ����빤������(EAM)��ETS_WORKORDER_BATCH�����ݡ�
     * @return boolean
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("��������(EAM)");
    }

    /**
     * ���ܣ����¹�������(EAM)��ETS_WORKORDER_BATCH�����ݡ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("��������(EAM)");
    }

    /**
     * ���ܣ�ɾ����������(EAM)��ETS_WORKORDER_BATCH�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("��������(EAM)");
    }

    /**
     * ���湤������Ϣ
     * @return boolean
     */
    public boolean saveData() throws DataHandleException {
        boolean operateResult = true;
        boolean isNew = true;
        if (isNew) {
            createData();
        } else {
            updateData();
        }

        return operateResult;
    }

    /**
     * ɾ����ʱ���иù������¹�����copy��ʽ������ʱ��
     * @param workorderBatchNo
     * @return
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    public boolean copyToTmpData(String workorderBatchNo) throws DataHandleException {
        boolean operatorResult = true;
        SQLModel sqlModel = new SQLModel();
        List sqlModList = new ArrayList();
        OrderExtendModel orderExtend = new OrderExtendModel();

        //step1:ɾ������������ʱ������Ϣ��������ʱ��
        sqlModList.add(orderExtend.getDeleteSchemeDataModel(workorderBatchNo, true));
        sqlModList.add(orderExtend.getDeleteTmpDataModel(workorderBatchNo));
        //step2��������ʽ����Ϣ����ʱ��
        sqlModList.add(orderExtend.getCopyToTmpDataModel(workorderBatchNo));
        sqlModList.add(orderExtend.getCopySchemToTempModel(workorderBatchNo));

        operatorResult = DBOperator.updateBatchRecords(sqlModList, conn);
        return operatorResult;
    }

    public EtsWorkorderBatchDTO getBatchByNo() throws QueryException {
        EtsWorkorderBatchDTO workorderBatchDTO = (EtsWorkorderBatchDTO) dtoParameter;
        SQLModel sqlModel = etsWorkorderBatchModel.getDataByBatchNo(workorderBatchDTO.getWorkorderBatch());
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.setDTOClassName(EtsWorkorderBatchDTO.class.getName());
        simpleQuery.executeQuery();
        return (EtsWorkorderBatchDTO) simpleQuery.getFirstDTO();
    }

    public boolean processFlow(boolean isSubmit, String Sf_appFieldValue, EtsWorkorderBatchDTO headerdto) {
        EtsWorkorderBatchDTO batchDTO = (EtsWorkorderBatchDTO) dtoParameter;
        hId = batchDTO.getSystemid();
        AppFlowBaseDTO headerDTO = new AppFlowBaseDTO() {
            public String getPrimaryKey() {
                return hId;
            }

            public void setPrimaryKey(String primaryKey) {
            }
        };

        headerDTO.setApp_dataID(batchDTO.getSystemid());
        headerDTO.setPrimaryKey(batchDTO.getSystemid());
        headerDTO.setOrderNo(batchDTO.getWorkorderBatch());
        headerDTO.setOrderName("Ѳ������");
        headerDTO.setSf_appFieldValue(Sf_appFieldValue);

//        fieldValue = orderNum;
//        h//eaderDTO.setSf_appFieldValue(fieldValue);
        flowCommonUtil = new FlowCommonUtil(headerDTO, conn);
        return flowCommonUtil.processProcedure(isSubmit);
    }

    public boolean cancleFlow(String proName, String Sf_appFieldValue, String batchId) throws SQLException, DataHandleException, QueryException, SQLModelException, ContainerException, ParseException {
        EtsWorkorderBatchDTO batchDTO = (EtsWorkorderBatchDTO) dtoParameter;
        hId = batchDTO.getSystemid();
        boolean aa = false;
        AppFlowBaseDTO headerDTO = new AppFlowBaseDTO() {
            public String getPrimaryKey() {
                return hId;
            }

            public void setPrimaryKey(String primaryKey) {
            }
        };

        headerDTO.setApp_dataID(batchId);
        headerDTO.setPrimaryKey(batchId);
        headerDTO.setSfAppName(proName);
        headerDTO.setSf_appFieldValue(Sf_appFieldValue);

        flowCommonUtil = new FlowCommonUtil(headerDTO, conn);
        try {
            aa = flowCommonUtil.processCancel();
        } catch (Exception e) {
            Logger.logError(e);
        }
        return aa;
    }

    public boolean flowBack(String proName, String Sf_appFieldValue, String batchId) throws SQLException, DataHandleException, QueryException, SQLModelException, ContainerException, ParseException {
        EtsWorkorderBatchDTO batchDTO = (EtsWorkorderBatchDTO) dtoParameter;
        hId = batchDTO.getSystemid();
        boolean aa = false;
        AppFlowBaseDTO headerDTO = new AppFlowBaseDTO() {
            public String getPrimaryKey() {
                return hId;
            }

            public void setPrimaryKey(String primaryKey) {
            }
        };

        headerDTO.setApp_dataID(batchId);
        headerDTO.setPrimaryKey(batchId);
        headerDTO.setSfAppName(proName);
        headerDTO.setSf_appFieldValue(Sf_appFieldValue);

        flowCommonUtil = new FlowCommonUtil(headerDTO, conn);
        try {
            aa = flowCommonUtil.reject();
        } catch (Exception e) {
            Logger.logError(e);
        }
        return aa;
    }
}