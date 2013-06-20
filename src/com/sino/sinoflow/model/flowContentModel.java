package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfActInfoDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfActLogModel</p>
 * <p>Description:�����Զ�����SQL��������sfActLogModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class flowContentModel extends BaseSQLProducer {

//	private SfUserBaseDTO sfUser = null;
    private SfActInfoDTO sfActLog = null;

    /**
	 * ���ܣ���ת���̣��ڰ���ת��Ϣ SF_ACT_LOG ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfActLogDTO ���β���������
	 */
    public flowContentModel(SfUserBaseDTO userAccount, SfActInfoDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfActLog = dtoParameter;
//        sfUser = userAccount;
    }

    public SQLModel getSaveStatusModelByDataID(String appName, String appDataId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT"
                + " CASE SAI.SFACT_PICK_STATUS WHEN 0 THEN GET_REALNAMES(SAI.SFACT_TASK_USERS) ELSE GET_REALNAMES(SAI.SFACT_PICK_USER) END SFACT_COMPLETE_USER,"
                + " CASE SAI.SFACT_FROM_DATE WHEN NULL THEN SAI.SFACT_CREATE_DT ELSE SAI.SFACT_FROM_DATE END SFACT_FROM_DATE,"
                + " SAI.SFACT_COMPLETE_DATE,"
                + " SAI.SFACT_APPL_COLUMN_4,"
                + " SAI.SFACT_TASK_NAME"
                + " FROM"
                + " SF_ACT_ALL_V SAI, SF_APPLICATION SA"
                + " WHERE"
                + " SA.APP_NAME = ?"
                + " AND SA.APP_ID = SAI.SFACT_APPDEF_ID"
                + " AND SAI.SFACT_APPL_ID = ?"
                + " AND (SAI.SFACT_PICK_USER  " + SyBaseSQLUtil.isNullNoParam() + "  OR SAI.SFACT_PICK_USER <> 'SYSTEM')"
                + " ORDER BY SFACT_FROM_DATE, SFACT_COMPLETE_DATE";

        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(appName);
        sqlArgs.add(appDataId);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getSaveStatusModel(String caseId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " CASE SAI.SFACT_PICK_STATUS WHEN 0 THEN GET_REALNAMES(SAI.SFACT_TASK_USERS) ELSE GET_REALNAMES(SAI.SFACT_PICK_USER) END SFACT_COMPLETE_USER,"
                + " CASE SAI.SFACT_FROM_DATE WHEN NULL THEN SAI.SFACT_CREATE_DT ELSE SAI.SFACT_FROM_DATE END SFACT_FROM_DATE,"
                + " SAI.SFACT_COMPLETE_DATE,"
                + " SAI.SFACT_APPL_COLUMN_4,"
                + " SAI.SFACT_TASK_NAME"
                + " FROM"
                + " SF_ACT_ALL_V SAI"
                + " WHERE"
                + " SAI.SFACT_CASE_ID = ?"
                + " AND (SAI.SFACT_PICK_USER  " + SyBaseSQLUtil.isNullNoParam() + "  OR SAI.SFACT_PICK_USER <> 'SYSTEM')"
                + " ORDER BY SFACT_FROM_DATE";

        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(caseId);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}