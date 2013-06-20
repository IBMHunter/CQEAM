package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfFlowDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfFlowModel</p>
 * <p>Description:�����Զ�����SQL��������SfFlowModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfFlowModel extends BaseSQLProducer {

//	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ������� SF_FLOW ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfFlowDTO ���β���������
	 */
	public SfFlowModel(SfUserBaseDTO userAccount, SfFlowDTO dtoParameter) {
		super(userAccount, dtoParameter);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����������� SF_FLOW���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " SF_FLOW("
			+ " PROCEDURE_ID,"
			+ " FLOW_DESC,"
			+ " FROM_TASK_ID,"
			+ " TO_TASK_ID,"
			+ " FLOW_CODE,"
            + " FLOW_PROP,"
            + " FLOW_TYPE,"
            + " HINT"
			+ ") VALUES ("
			+ " ?, ?, ?, ?, ?, ?, ?, ?)";
		
		sqlArgs.add(sfFlow.getProcedureId());
		sqlArgs.add(sfFlow.getFlowDesc());
		sqlArgs.add(sfFlow.getFromTaskId());
		sqlArgs.add(sfFlow.getToTaskId());
        sqlArgs.add(sfFlow.getFlowCode());
        sqlArgs.add(sfFlow.getFlowProp());
		sqlArgs.add(sfFlow.getFlowType());
		sqlArgs.add(sfFlow.getHint());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����������� SF_FLOW���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
		String sqlStr = "UPDATE SF_FLOW"
			+ " SET"
			+ " PROCEDURE_ID = ?,"
			+ " FLOW_DESC = ?,"
			+ " FROM_TASK_ID = ?,"
			+ " TO_TASK_ID = ?,"
			+ " FLOW_CODE = ?,"
            + " FLOW_PROP = ?,"
            + " FLOW_TYPE = ?,"
			+ " HINT = ?"
			+ " WHERE"
			+ " FLOW_ID = ?";
		
		sqlArgs.add(sfFlow.getProcedureId());
		sqlArgs.add(sfFlow.getFlowDesc());
		sqlArgs.add(sfFlow.getFromTaskId());
		sqlArgs.add(sfFlow.getToTaskId());
		sqlArgs.add(sfFlow.getFlowCode());
        sqlArgs.add(sfFlow.getFlowProp());
        sqlArgs.add(sfFlow.getFlowType());
		sqlArgs.add(sfFlow.getHint());
		sqlArgs.add(sfFlow.getFlowId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����������� SF_FLOW����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_FLOW"
				+ " WHERE"
				+ " FLOW_ID = ?";
			sqlArgs.add(sfFlow.getFlowId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����������� SF_FLOW������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PROCEDURE_ID,"
			+ " FLOW_ID,"
			+ " FLOW_DESC,"
			+ " FROM_TASK_ID,"
			+ " TO_TASK_ID,"
			+ " FLOW_CODE,"
            + " FLOW_PROP,"
            + " FLOW_TYPE,"
			+ " HINT"
			+ " FROM"
			+ " SF_FLOW"
			+ " WHERE"
			+ " FLOW_ID = ?";
		sqlArgs.add(sfFlow.getFlowId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����������� SF_FLOW����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " PROCEDURE_ID,"
			+ " FLOW_ID,"
			+ " FLOW_DESC,"
			+ " FROM_TASK_ID,"
			+ " TO_TASK_ID,"
			+ " FLOW_CODE,"
            + " FLOW_PROP,"
            + " FLOW_TYPE,"
			+ " HINT"
			+ " FROM"
			+ " SF_FLOW"
			+ " WHERE"
			+ " (? <= 0 OR PROCEDURE_ID = ?)"
			+ " AND (? <= 0 OR FLOW_ID = ?)"
			+ " AND (? = '' OR FLOW_DESC LIKE ?)"
			+ " AND (? <= 0 OR FROM_TASK_ID = ?)"
			+ " AND (? <= 0 OR TO_TASK_ID = ?)"
			+ " AND (? = '' OR FLOW_CODE LIKE ?)"
            + " AND (? <= 0 OR FLOW_PROP = ?)"
            + " AND (? <= 0 OR FLOW_TYPE = ?)"
			+ " AND (? = '' OR HINT LIKE ?)";
		sqlArgs.add(sfFlow.getProcedureId());
		sqlArgs.add(sfFlow.getProcedureId());
		sqlArgs.add(sfFlow.getFlowId());
		sqlArgs.add(sfFlow.getFlowId());
		sqlArgs.add(sfFlow.getFlowDesc());
		sqlArgs.add(sfFlow.getFlowDesc());
		sqlArgs.add(sfFlow.getFromTaskId());
		sqlArgs.add(sfFlow.getFromTaskId());
		sqlArgs.add(sfFlow.getToTaskId());
		sqlArgs.add(sfFlow.getToTaskId());
		sqlArgs.add(sfFlow.getFlowCode());
		sqlArgs.add(sfFlow.getFlowCode());
        sqlArgs.add(sfFlow.getFlowProp());
        sqlArgs.add(sfFlow.getFlowProp());
        sqlArgs.add(sfFlow.getFlowType());
		sqlArgs.add(sfFlow.getFlowType());
		sqlArgs.add(sfFlow.getHint());
		sqlArgs.add(sfFlow.getHint());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    public SQLModel getStartFlow() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
            String sqlStr = "SELECT "
            + " SF.PROCEDURE_ID,"
            + " SF.FLOW_ID,"
            + " SF.FLOW_DESC,"
            + " SF.FROM_TASK_ID,"
            + " SF.TO_TASK_ID,"
            + " SF.FLOW_CODE,"
            + " SF.FLOW_PROP,"
            + " SF.FLOW_TYPE,"
            + " SF.HINT"
            + " FROM"
            + " SF_FLOW SF, SF_TASK ST"
            + " WHERE"
            + " (? <= 0 OR SF.PROCEDURE_ID = ?)"
            + " AND (? <= 0 OR SF.FLOW_ID = ?)"
            + " AND (? = '' OR SF.FLOW_DESC LIKE ?)"
//            + " AND (? <= 0 OR FROM_TASK_ID = ?)"
            + " AND ST.TASK_NAME = 'START'"
            + " AND SF.FROM_TASK_ID = ST.TASK_ID"
            + " AND (? <= 0 OR SF.TO_TASK_ID = ?)"
            + " AND (? = '' OR SF.FLOW_CODE LIKE ?)"
            + " AND (? <= 0 OR SF.FLOW_PROP = ?)"
            + " AND (? <= 0 OR SF.FLOW_TYPE = ?)"
            + " AND (? = '' OR SF.HINT LIKE ?)";
        sqlArgs.add(sfFlow.getProcedureId());
        sqlArgs.add(sfFlow.getProcedureId());
        sqlArgs.add(sfFlow.getFlowId());
        sqlArgs.add(sfFlow.getFlowId());
        sqlArgs.add(sfFlow.getFlowDesc());
        sqlArgs.add(sfFlow.getFlowDesc());
        sqlArgs.add(sfFlow.getToTaskId());
        sqlArgs.add(sfFlow.getToTaskId());
        sqlArgs.add(sfFlow.getFlowCode());
        sqlArgs.add(sfFlow.getFlowCode());
        sqlArgs.add(sfFlow.getFlowProp());
        sqlArgs.add(sfFlow.getFlowProp());
        sqlArgs.add(sfFlow.getFlowType());
        sqlArgs.add(sfFlow.getFlowType());
        sqlArgs.add(sfFlow.getHint());
        sqlArgs.add(sfFlow.getHint());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
	 * ���ܣ�������������ֶ� procedureId �����ѯ����SQL��
	 * ����Զ��������������� SF_FLOW��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param procedureId String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByProcedureIdModel(int procedureId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " FLOW_ID,"
			+ " FLOW_DESC,"
			+ " FROM_TASK_ID,"
			+ " TO_TASK_ID,"
			+ " FLOW_CODE,"
            + " FLOW_PROP,"
            + " FLOW_TYPE,"
			+ " HINT"
			+ " FROM"
			+ " SF_FLOW"
			+ " WHERE"
			+ " PROCEDURE_ID = ?";
		sqlArgs.add(procedureId);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
		if(foreignKey.equals("procedureId")){
			sqlModel = getDataByProcedureIdModel(sfFlow.getProcedureId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� procedureId ��������ɾ��SQL��
	 * ����Զ��������������� SF_FLOW����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param procedureId String 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByProcedureIdModel(int procedureId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
/*
            + " FLOW_ID,"
			+ " FLOW_DESC,"
			+ " FROM_TASK_ID,"
			+ " TO_TASK_ID,"
			+ " FLOW_CODE,"
            + " FLOW_PROP,"
            + " FLOW_TYPE,"
			+ " HINT"
*/
			+ " FROM"
			+ " SF_FLOW"
			+ " WHERE"
			+ " PROCEDURE_ID = ?";
		sqlArgs.add(procedureId);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
		if(foreignKey.equals("procedureId")){
			sqlModel = getDeleteByProcedureIdModel(sfFlow.getProcedureId());
		}
		return sqlModel;
	}

    /**
     * ����: �ҳ��� from ���� ID �� to ���� ID ֮��
     * @param from ���� ID
     * @param to  ���� ID
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel isConnectModel(int procId, int from, int to) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
            String sqlStr = "SELECT FLOW_ID"
            + " FROM"
            + " SF_FLOW"
            + " WHERE"
            + " PROCEDURE_ID = ?"
            + " AND FROM_TASK_ID = ?"
            + " AND TO_TASK_ID = ?";

        sqlArgs.add(procId);
        sqlArgs.add(from);
        sqlArgs.add(to);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel checkJoinConnectedModel(int procId, int from, int to) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
/*
            String sqlStr =  "SELECT NULL"
                    + " FROM (SELECT TO_TASK_ID"
                    + " FROM (SELECT * FROM (SELECT * FROM   SF_FLOW SF WHERE  EXISTS"
                    + " (SELECT NULL FROM SF_TASK ST WHERE  ST.PROCEDURE_ID = ?"
                    + " AND ST.TASK_NAME = 'JOIN'"
                    + " AND (ST.TASK_ID = SF.FROM_TASK_ID OR ST.TASK_ID = SF.TO_TASK_ID))) SF,"
                    + " SF_TASK ST WHERE SF.PROCEDURE_ID = ST.PROCEDURE_ID"
                    + " AND SF.FROM_TASK_ID = ST.TASK_ID"
                    + " AND (ST.TASK_NAME = 'JOIN' OR ST.TASK_ID = ?)) T2"
                    + " CONNECT BY PRIOR T2.TO_TASK_ID = T2.FROM_TASK_ID"
                    + " START  WITH T2.FROM_TASK_ID = ?) SF"
                    + " WHERE SF.TO_TASK_ID = ?";

        sqlArgs.add(procId);
        sqlArgs.add(from);
        sqlArgs.add(from);
        sqlArgs.add(to);

*/
        String sqlStr = " SELECT A.TASK_NAME"
                + " FROM (SELECT ST.TASK_ID FROM SF_FLOW SF, SF_TASK_ST WHERE"
                + " SF.PROCEDURE_ID = ? AND SF.FROM_TASK_ID = ?"
                + " AND SF.TO_TASK_ID = ST.TASK_ID AND ST.TASK_NAME = 'JOIN') A,"
                + " (SELECT ST.TASK_ID FROM SF_FLOW SF, SF_TASK ST WHERE"
                + " SF.PROCEDURE_ID = ? AND SF.TO_TASK_ID = ?"
                + " AND SF.FROM_TASK_ID = ST.TASK_ID AND ST.TASK_NAME = 'JOIN') B"
                + " WHERE A.TASK_ID = B.TASK_ID";

        sqlArgs.add(procId);
        sqlArgs.add(from);
        sqlArgs.add(procId);
        sqlArgs.add(to);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel clearOldProjectsFlows() {
        SQLModel sqlModel = new SQLModel();

        String sqlStr = "DELETE SF_FLOW"
                + " WHERE PROCEDURE_ID NOT IN"
                + " (SELECT DISTINCT SP.PROCEDURE_ID"
                + " FROM SF_PROCEDURE SP"
                + " WHERE  EXISTS (SELECT NULL"
                + " FROM SF_ACT_INFO SAI"
                + " WHERE  SAI.SFACT_PROC_ID = SP.PROCEDURE_ID)"
                + " OR EXISTS (SELECT NULL"
                + " FROM SF_PROJECT_V SFV"
                + " WHERE  SFV.PROJECT_ID = SP.PROJECT_ID))";

        sqlModel.setSqlStr(sqlStr);

        return sqlModel;
    }

    public SQLModel getNextFlowsInfoModel(int taskId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
            String sqlStr = "SELECT "
            + " SF.PROCEDURE_ID,"
            + " SF.FLOW_ID,"
            + " SF.FLOW_DESC,"
            + " SF.FROM_TASK_ID,"
            + " SF.TO_TASK_ID,"
            + " SF.FLOW_CODE,"
            + " SF.FLOW_PROP,"
            + " SF.FLOW_TYPE,"
            + " SF.HINT"
            + " FROM"
            + " SF_FLOW SF"
            + " WHERE"
            + " SF.FROM_TASK_ID = ?"
            + " ORDER BY SF.FLOW_CODE";

        sqlArgs.add(taskId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getPreviousTaskModel(int procId, int taskId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfFlowDTO sfFlow = (SfFlowDTO)dtoParameter;
            String sqlStr = "SELECT "
            + " ST.*"
            + " FROM"
            + " SF_FLOW SF, SF_TASK ST"
            + " WHERE"
            + " SF.PROCEDURE_ID = ?"
            + " AND SF.TO_TASK_ID = ?"
            + " AND ST.TASK_ID = SF.FROM_TASK_ID";

        sqlArgs.add(procId);
        sqlArgs.add(taskId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}