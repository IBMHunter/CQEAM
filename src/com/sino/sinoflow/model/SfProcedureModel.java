package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfProcedureDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfProcedureModel</p>
 * <p>Description:�����Զ�����SQL��������SfProcedureModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfProcedureModel extends BaseSQLProducer {

//	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��������� SF_PROCEDURE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfProcedureDTO ���β���������
	 */
	public SfProcedureModel(SfUserBaseDTO userAccount, SfProcedureDTO dtoParameter) {
		super(userAccount, dtoParameter);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROCEDURE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " SF_PROCEDURE("
				+ " PROJECT_ID,"
				+ " PROCEDURE_NAME,"
				+ " TRAY_TYPE,"
				+ " DURATION,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " DESCRIPTION,"
				+ " MEMO,"
                + " MAIN_PROCEDURE,"
                + " DEFAULT_URL"
				+ ") VALUES ("
				+ " ?, ?, ?, ?, ?, CONVERT(DATETIME, ?),"
                + " ?, CONVERT(DATETIME, ?), ?, ?, ?, ?)";
		
			sqlArgs.add(sfProcedure.getProjectId());
			sqlArgs.add(sfProcedure.getProcedureName());
			sqlArgs.add(sfProcedure.getTrayType());
			sqlArgs.add(sfProcedure.getDuration());
			sqlArgs.add(sfProcedure.getCreatedBy());
			sqlArgs.add(sfProcedure.getCreationDate());
			sqlArgs.add(sfProcedure.getLastUpdatedBy());
			sqlArgs.add(sfProcedure.getLastUpdateDate());
			sqlArgs.add(sfProcedure.getDescription());
			sqlArgs.add(sfProcedure.getMemo());
			sqlArgs.add(sfProcedure.getMainProcedure());
            sqlArgs.add(sfProcedure.getDefaultUrl());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROCEDURE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
			String sqlStr = "UPDATE SF_PROCEDURE"
				+ " SET"
				+ " PROJECT_ID = ?,"
				+ " PROCEDURE_NAME = ?,"
				+ " TRAY_TYPE = ?,"
				+ " DURATION = ?,"
				+ " CREATED_BY = ?,"
				+ " CREATION_DATE = CONVERT(DATETIME, ?),"
				+ " LAST_UPDATED_BY = ?,"
				+ " LAST_UPDATE_DATE = CONVERT(DATETIME, ?),"
				+ " DESCRIPTION = ?,"
				+ " MEMO = ?,"
				+ " MAIN_PROCEDURE = ?,"
                + " DEFAULT_URL = ?"
                + " WHERE"
				+ " PROCEDURE_ID = ?";
		
			sqlArgs.add(sfProcedure.getProjectId());
			sqlArgs.add(sfProcedure.getProcedureName());
			sqlArgs.add(sfProcedure.getTrayType());
			sqlArgs.add(sfProcedure.getDuration());
			sqlArgs.add(sfProcedure.getCreatedBy());
			sqlArgs.add(sfProcedure.getCreationDate());
			sqlArgs.add(sfProcedure.getLastUpdatedBy());
			sqlArgs.add(sfProcedure.getLastUpdateDate());
			sqlArgs.add(sfProcedure.getDescription());
			sqlArgs.add(sfProcedure.getMemo());
			sqlArgs.add(sfProcedure.getMainProcedure());
            sqlArgs.add(sfProcedure.getDefaultUrl());
            sqlArgs.add(sfProcedure.getProcedureId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROCEDURE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_PROCEDURE"
				+ " WHERE"
				+ " PROCEDURE_ID = ?";
			sqlArgs.add(sfProcedure.getProcedureId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROCEDURE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PROJECT_ID,"
			+ " PROCEDURE_ID,"
			+ " PROCEDURE_NAME,"
			+ " TRAY_TYPE,"
			+ " DURATION,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " DESCRIPTION,"
			+ " MEMO,"
			+ " MAIN_PROCEDURE,"
            + " DEFAULT_URL"
            + " FROM"
			+ " SF_PROCEDURE"
			+ " WHERE"
			+ " PROCEDURE_ID = ?";
		sqlArgs.add(sfProcedure.getProcedureId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROCEDURE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " PROJECT_ID,"
				+ " PROCEDURE_ID,"
				+ " PROCEDURE_NAME,"
				+ " TRAY_TYPE,"
				+ " DURATION,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " DESCRIPTION,"
				+ " MEMO,"
				+ " MAIN_PROCEDURE,"
                + " DEFAULT_URL"
                + " FROM"
				+ " SF_PROCEDURE"
				+ " WHERE"
				+ " (? <= 0 OR PROJECT_ID = ?)"
				+ " AND (? <= 0 OR PROCEDURE_ID = ?)"
				+ " AND (? = '' OR PROCEDURE_NAME LIKE ?)"
				+ " AND (? <= 0 OR TRAY_TYPE = ?)"
				+ " AND (? <= 0 OR DURATION = ?)"
				+ " AND (? = '' OR CREATED_BY LIKE ?)"
				+ " AND (? = '' OR CREATION_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR LAST_UPDATED_BY LIKE ?)"
				+ " AND (? = '' OR LAST_UPDATE_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR DESCRIPTION LIKE ?)"
				+ " AND (? = '' OR MEMO LIKE ?)"
				+ " AND (? <= 0 OR MAIN_PROCEDURE = ?)"
                + " AND (? = '' OR DEFAULT_URL LIKE ?)";
            sqlArgs.add(sfProcedure.getProjectId());
			sqlArgs.add(sfProcedure.getProjectId());
			sqlArgs.add(sfProcedure.getProcedureId());
			sqlArgs.add(sfProcedure.getProcedureId());
			sqlArgs.add(sfProcedure.getProcedureName());
			sqlArgs.add(sfProcedure.getProcedureName());
			sqlArgs.add(sfProcedure.getTrayType());
			sqlArgs.add(sfProcedure.getTrayType());
			sqlArgs.add(sfProcedure.getDuration());
			sqlArgs.add(sfProcedure.getDuration());
			sqlArgs.add(sfProcedure.getCreatedBy());
			sqlArgs.add(sfProcedure.getCreatedBy());
			sqlArgs.add(sfProcedure.getCreationDate());
			sqlArgs.add(sfProcedure.getCreationDate());
			sqlArgs.add(sfProcedure.getLastUpdatedBy());
			sqlArgs.add(sfProcedure.getLastUpdatedBy());
			sqlArgs.add(sfProcedure.getLastUpdateDate());
			sqlArgs.add(sfProcedure.getLastUpdateDate());
			sqlArgs.add(sfProcedure.getDescription());
			sqlArgs.add(sfProcedure.getDescription());
			sqlArgs.add(sfProcedure.getMemo());
			sqlArgs.add(sfProcedure.getMemo());
			sqlArgs.add(sfProcedure.getMainProcedure());
			sqlArgs.add(sfProcedure.getMainProcedure());
            sqlArgs.add(sfProcedure.getDefaultUrl());
            sqlArgs.add(sfProcedure.getDefaultUrl());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� projectId �����ѯ����SQL��
	 * ����Զ��������ݹ������� SF_PROCEDURE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param projectId String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByProjectIdModel(int projectId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " PROCEDURE_ID,"
			+ " PROCEDURE_NAME,"
			+ " TRAY_TYPE,"
			+ " DURATION,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " DESCRIPTION,"
			+ " MEMO,"
			+ " MAIN_PROCEDURE,"
            + " DEFAULT_URL"
            + " FROM"
			+ " SF_PROCEDURE"
			+ " WHERE"
			+ " PROJECT_ID = ?";
		sqlArgs.add(projectId);
		
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
		SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
		if(foreignKey.equals("projectId")){
			sqlModel = getDataByProjectIdModel(sfProcedure.getProjectId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� projectId ��������ɾ��SQL��
	 * ����Զ��������ݹ������� SF_PROCEDURE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param projectId String 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByProjectIdModel(int projectId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
/*
            + " PROCEDURE_ID,"
			+ " PROCEDURE_NAME,"
			+ " TRAY_TYPE,"
			+ " DURATION,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " DESCRIPTION,"
			+ " MEMO,"
			+ " MAIN_PROCEDURE,"
            + " DEFAULT_URL"
*/
            + " FROM"
			+ " SF_PROCEDURE"
			+ " WHERE"
			+ " PROJECT_ID = ?";
		sqlArgs.add(projectId);
		
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
		SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
		if(foreignKey.equals("projectId")){
			sqlModel = getDeleteByProjectIdModel(sfProcedure.getProjectId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROCEDUREҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();		
			List sqlArgs = new ArrayList();
			SfProcedureDTO sfProcedure = (SfProcedureDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " PROJECT_ID,"
				+ " CONVERT(INT,PROCEDURE_ID) PROCEDURE_ID,"
				+ " PROCEDURE_NAME,"
				+ " TRAY_TYPE,"
				+ " DURATION,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " DESCRIPTION,"
				+ " MEMO,"
				+ " MAIN_PROCEDURE,"
                + " DEFAULT_RUL"
                + " FROM"
				+ " SF_PROCEDURE"
				+ " WHERE"
				+ " (? <= 0 OR PROJECT_ID = ?)"
				+ " AND (? <= 0 OR PROCEDURE_ID = ?)"
				+ " AND (? = '' OR PROCEDURE_NAME LIKE ?)"
				+ " AND (? <= 0 OR TRAY_TYPE = ?)"
				+ " AND (? <= 0 OR DURATION = ?)"
				+ " AND (? = '' OR CREATED_BY LIKE ?)"
				+ " AND (? = '' OR CREATION_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR LAST_UPDATED_BY LIKE ?)"
				+ " AND (? = '' OR LAST_UPDATE_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR DESCRIPTION LIKE ?)"
				+ " AND (? = '' OR MEMO LIKE ?)"
				+ " AND (? <= 0 OR MAIN_PROCEDURE = ?)"
                + " AND (? = '' OR DEFAULT_URL LIKE ?)";
            sqlArgs.add(sfProcedure.getProjectId());
			sqlArgs.add(sfProcedure.getProjectId());
			sqlArgs.add(sfProcedure.getProcedureId());
			sqlArgs.add(sfProcedure.getProcedureId());
			sqlArgs.add(sfProcedure.getProcedureName());
			sqlArgs.add(sfProcedure.getProcedureName());
			sqlArgs.add(sfProcedure.getTrayType());
			sqlArgs.add(sfProcedure.getTrayType());
			sqlArgs.add(sfProcedure.getDuration());
			sqlArgs.add(sfProcedure.getDuration());
			sqlArgs.add(sfProcedure.getCreatedBy());
			sqlArgs.add(sfProcedure.getCreatedBy());
			sqlArgs.add(sfProcedure.getCreationDate());
			sqlArgs.add(sfProcedure.getCreationDate());
			sqlArgs.add(sfProcedure.getLastUpdatedBy());
			sqlArgs.add(sfProcedure.getLastUpdatedBy());
			sqlArgs.add(sfProcedure.getLastUpdateDate());
			sqlArgs.add(sfProcedure.getLastUpdateDate());
			sqlArgs.add(sfProcedure.getDescription());
			sqlArgs.add(sfProcedure.getDescription());
			sqlArgs.add(sfProcedure.getMemo());
			sqlArgs.add(sfProcedure.getMemo());
			sqlArgs.add(sfProcedure.getMainProcedure());
			sqlArgs.add(sfProcedure.getMainProcedure());
            sqlArgs.add(sfProcedure.getDefaultUrl());
            sqlArgs.add(sfProcedure.getDefaultUrl());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}



	/**
	 * ���ܣ����ض�Ӧ�����µĹ���
     * @param projectId project ID
	 */
	public SQLModel getProjectProcedureModel(int projectId) {
	    SQLModel sqlModel = new SQLModel();
	    List sqlArgs = new ArrayList();
	    String sqlStr = "SELECT "
	    				+ " PROCEDURE_ID,"
	    				+ " PROCEDURE_NAME"
	    				+ " FROM SF_PROCEDURE"
	    				+ " WHERE "
	    				+ " PROJECT_ID = ?"
	    				+ " ORDER BY PROCEDURE_NAME";
	    sqlArgs.add(projectId);
	    sqlModel.setSqlStr(sqlStr);
	    sqlModel.setArgs(sqlArgs);        
	    return sqlModel;
	}

    public SQLModel getProjectProcedureModel(String projectId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                        + " PROCEDURE_ID,"
                        + " PROCEDURE_NAME"
                        + " FROM SF_PROCEDURE"
                        + " WHERE "
                        + " PROJECT_ID = ?"
                        + " ORDER BY PROCEDURE_NAME";
        sqlArgs.add(StrUtil.strToInt(projectId));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
	 * ���ܣ����ض�Ӧ�����µĹ���
     * @param projectId project ID
	 */
	public SQLModel getProjectProcedureModel2(int projectId) {
	    SQLModel sqlModel = new SQLModel();
	    List sqlArgs = new ArrayList();
	    String sqlStr = "SELECT "
	    				+ " PROCEDURE_NAME,"
	    				+ " PROCEDURE_NAME,"
	    				+ " PROCEDURE_NAME OBY"
	    				+ " FROM SF_PROCEDURE"
	    				+ " WHERE "
	    				+ " PROJECT_ID = ?"
	    				+ " ORDER BY OBY";
	    sqlArgs.add(projectId);
	    sqlModel.setSqlStr(sqlStr);
	    sqlModel.setArgs(sqlArgs);        
	    return sqlModel;
	}

    public SQLModel getDescModel(int procId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                        + " SPJ.DESCRIPTION PROJECT_DESC,"
                        + " SP.DESCRIPTION PROCEDURE_DESC"
                        + " FROM SF_PROCEDURE SP, SF_PROJECT SPJ"
                        + " WHERE "
                        + " SP.PROCEDURE_ID = ?"
                        + " AND SPJ.PROJECT_ID = SP.PROJECT_ID";
        sqlArgs.add(procId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��õ�����ύ�Ĺ���
     * @param procName ������
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getLattestProcedureModel(String procName) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
        String sqlStr = "Select SP.*"
                + " From SF_PROCEDURE SP,"
                + " (SELECT PROCEDURE_NAME, Max(PROCEDURE_ID) PROCEDURE_ID "
                + " FROM SF_PROCEDURE  GROUP BY PROCEDURE_NAME) T"
                + " Where SP.PROCEDURE_ID = T.PROCEDURE_ID"
                + " AND (? = '' OR SP.PROCEDURE_NAME LIKE ?)";
        sqlArgs.add(procName);
        sqlArgs.add(procName);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel clearOldProjectsProcs() {
        SQLModel sqlModel = new SQLModel();

        String sqlStr = "DELETE SF_PROCEDURE"
                + " WHERE  PROCEDURE_ID NOT IN"
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
}