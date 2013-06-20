package com.sino.sinoflow.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfProjectDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfProjectModel</p>
 * <p>Description:�����Զ�����SQL��������SfProjectModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfProjectModel extends BaseSQLProducer {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��������� SF_PROJECT ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfProjectDTO ���β���������
	 */
	public SfProjectModel(SfUserBaseDTO userAccount, SfProjectDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROJECT���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		
			List sqlArgs = new ArrayList();
			SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " SF_PROJECT("
				+ " PROJECT_NAME,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " ENABLED,"
				+ " EFFECTIVE_DATE,"
				+ " VERSION,"
				+ " DESCRIPTION,"
                + " FILENAME"
                + ") VALUES ("
				+ " ?, ?, CONVERT(DATETIME, ?), ?, " +
                    "CONVERT(DATETIME, ?), ?, CONVERT(DATETIME, ?), ?, ?, ?)";
		
			sqlArgs.add(sfProject.getProjectName());
			sqlArgs.add(sfProject.getCreatedBy());
			sqlArgs.add(sfProject.getCreationDate());
			sqlArgs.add(sfProject.getLastUpdatedBy());
			sqlArgs.add(sfProject.getLastUpdateDate());
			sqlArgs.add(sfProject.getEnabled());
			sqlArgs.add(sfProject.getEffectiveDate());
			sqlArgs.add(sfProject.getVersion());
			sqlArgs.add(sfProject.getDescription());
            sqlArgs.add(sfProject.getFilename());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
	
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROJECT���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		
			List sqlArgs = new ArrayList();
			SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
			String sqlStr = "UPDATE SF_PROJECT"
				+ " SET"
				+ " PROJECT_NAME = ?,"
				+ " CREATED_BY = ?,"
				+ " CREATION_DATE = CONVERT(DATETIME, ?),"
				+ " LAST_UPDATED_BY = ?,"
				+ " LAST_UPDATE_DATE = CONVERT(DATETIME, ?),"
				+ " ENABLED = ?,"
				+ " EFFECTIVE_DATE = CONVERT(DATETIME, ?),"
				+ " VERSION = ?,"
				+ " DESCRIPTION = ?,"
                + " FILENAME = ?"
                + " WHERE"
				+ " PROJECT_ID = ?";
		
			sqlArgs.add(sfProject.getProjectName());
			sqlArgs.add(sfProject.getCreatedBy());
			sqlArgs.add(sfProject.getCreationDate());
			sqlArgs.add(sfProject.getLastUpdatedBy());
			sqlArgs.add(sfProject.getLastUpdateDate());
			sqlArgs.add(sfProject.getEnabled());
			sqlArgs.add(sfProject.getEffectiveDate());
			sqlArgs.add(sfProject.getVersion());
			sqlArgs.add(sfProject.getDescription());
            sqlArgs.add(sfProject.getFilename());
            sqlArgs.add(sfProject.getProjectId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
	
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROJECT����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_PROJECT"
				+ " WHERE"
				+ " PROJECT_ID = ?";
			sqlArgs.add(sfProject.getProjectId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROJECT������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PROJECT_ID,"
			+ " PROJECT_NAME,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " ENABLED,"
			+ " EFFECTIVE_DATE,"
			+ " VERSION,"
			+ " DESCRIPTION,"
            + " FILENAME"
            + " FROM"
			+ " SF_PROJECT"
			+ " WHERE"
			+ " PROJECT_ID = ?";
		sqlArgs.add(sfProject.getProjectId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROJECT����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		
			List sqlArgs = new ArrayList();
			SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " PROJECT_ID,"
				+ " PROJECT_NAME,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " ENABLED,"
				+ " EFFECTIVE_DATE,"
				+ " VERSION,"
				+ " DESCRIPTION,"
                + " FILENAME"
                + " FROM"
				+ " SF_PROJECT"
				+ " WHERE"
				+ " (? <= 0 OR PROJECT_ID = ?)"
				+ " AND (? = '' OR PROJECT_NAME LIKE ?)"
				+ " AND (? = '' OR CREATED_BY LIKE ?)"
				+ " AND (? = '' OR CREATION_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR LAST_UPDATED_BY LIKE ?)"
				+ " AND (? = '' OR LAST_UPDATE_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR ENABLED LIKE ?)"
				+ " AND (? = '' OR EFFECTIVE_DATE LIKE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR VERSION LIKE ?)"
				+ " AND (? = '' OR DESCRIPTION LIKE ?)"
                + " AND (? = '' OR FILENAME LIKE ?)";
            sqlArgs.add(sfProject.getProjectId());
			sqlArgs.add(sfProject.getProjectId());
			sqlArgs.add(sfProject.getProjectName());
			sqlArgs.add(sfProject.getProjectName());
			sqlArgs.add(sfProject.getCreatedBy());
			sqlArgs.add(sfProject.getCreatedBy());
			sqlArgs.add(sfProject.getCreationDate());
			sqlArgs.add(sfProject.getCreationDate());
			sqlArgs.add(sfProject.getLastUpdatedBy());
			sqlArgs.add(sfProject.getLastUpdatedBy());
			sqlArgs.add(sfProject.getLastUpdateDate());
			sqlArgs.add(sfProject.getLastUpdateDate());
			sqlArgs.add(sfProject.getEnabled());
			sqlArgs.add(sfProject.getEnabled());
			sqlArgs.add(sfProject.getEffectiveDate());
			sqlArgs.add(sfProject.getEffectiveDate());
			sqlArgs.add(sfProject.getVersion());
			sqlArgs.add(sfProject.getVersion());
			sqlArgs.add(sfProject.getDescription());
			sqlArgs.add(sfProject.getDescription());
            sqlArgs.add(sfProject.getFilename());
            sqlArgs.add(sfProject.getFilename());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
	
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� SF_PROJECTҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " CONVERT(INT,PROJECT_ID) PROJECT_ID,"
				+ " PROJECT_NAME,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " ENABLED,"
				+ " EFFECTIVE_DATE,"
				+ " VERSION,"
				+ " DESCRIPTION,"
                + " FILENAME"
                + " FROM"
				+ " SF_PROJECT"
				+ " WHERE"
				+ " (? <= 0 OR PROJECT_ID = ?)"
				+ " AND (? = '' OR PROJECT_NAME LIKE ?)"
				+ " AND (? = '' OR CREATED_BY LIKE ?)"
				+ " AND (? = '' OR CREATION_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR LAST_UPDATED_BY LIKE ?)"
				+ " AND (? = '' OR LAST_UPDATE_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR ENABLED LIKE ?)"
				+ " AND (? = '' OR EFFECTIVE_DATE = CONVERT(DATETIME, ?))"
				+ " AND (? = '' OR VERSION LIKE ?)"
				+ " AND (? = '' OR DESCRIPTION LIKE ?)"
                + " AND (? = '' OR FILENAME LIKE ?)"
                + " ORDER BY PROJECT_NAME,LAST_UPDATE_DATE";
			sqlArgs.add(sfProject.getProjectId());
			sqlArgs.add(sfProject.getProjectId());
			sqlArgs.add(sfProject.getProjectName());
			sqlArgs.add(sfProject.getProjectName());
			sqlArgs.add(sfProject.getCreatedBy());
			sqlArgs.add(sfProject.getCreatedBy());
			sqlArgs.add(sfProject.getCreationDate());
			sqlArgs.add(sfProject.getCreationDate());
			sqlArgs.add(sfProject.getLastUpdatedBy());
			sqlArgs.add(sfProject.getLastUpdatedBy());
			sqlArgs.add(sfProject.getLastUpdateDate());
			sqlArgs.add(sfProject.getLastUpdateDate());
			sqlArgs.add(sfProject.getEnabled());
			sqlArgs.add(sfProject.getEnabled());
			sqlArgs.add(sfProject.getEffectiveDate());
			sqlArgs.add(sfProject.getEffectiveDate());
			sqlArgs.add(sfProject.getVersion());
			sqlArgs.add(sfProject.getVersion());
			sqlArgs.add(sfProject.getDescription());
			sqlArgs.add(sfProject.getDescription());
            sqlArgs.add(sfProject.getFilename());
            sqlArgs.add(sfProject.getFilename());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
	
		return sqlModel;
	}

	/**
	 * ���ܣ��õ�������Ч�Ĺ���
     * @param projName ������
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getNewestProjectModel(String projName) throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		
		List sqlArgs = new ArrayList();
//		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SP.PROJECT_ID,"
			+ " SP.PROJECT_NAME,"
			+ " SP.CREATED_BY,"
			+ " SP.CREATION_DATE,"
			+ " SP.LAST_UPDATED_BY,"
			+ " SP.LAST_UPDATE_DATE,"
			+ " SP.ENABLED,"
			+ " SP.EFFECTIVE_DATE,"
			+ " SP.VERSION,"
			+ " SP.DESCRIPTION,"
            + " SP.FILENAME"
            + " FROM"
			+ " SF_PROJECT SP, SF_PROJECT_V SPV"
			+ " WHERE"
			+ " SP.PROJECT_ID = SPV.PROJECT_ID"
			+ " AND (? = '' OR SPV.PROJECT_NAME = ?)";
		sqlArgs.add(projName);
		sqlArgs.add(projName);
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

    /**
     * ���ܣ��õ�����ύ�Ĺ���
     * @param projName ������
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getLattestProjectModel(String projName) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
//		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
        String sqlStr = "SELECT SP.*"
                + " FROM SF_PROJECT SP"
                + " WHERE EXISTS (SELECT NULL FROM"
                + " (SELECT PROJECT_NAME, MAX(PROJECT_ID) PROJECT_ID "
                + " FROM SF_PROJECT  GROUP BY PROJECT_NAME) T"
                + " WHERE SP.PROJECT_ID = T.PROJECT_ID"
                + " AND (? = '' OR SP.PROJECT_NAME LIKE ?))";
        sqlArgs.add(projName);
        sqlArgs.add(projName);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel getProjectIdModel(int projId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
//		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
        String sqlStr = "SELECT SP.*"
                + " FROM SF_PROJECT SP"
                + " WHERE SP.PROJECT_ID = ?";
        
        sqlArgs.add(projId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ��õ� procId �Ĺ���
     * @param procId ���� ID
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getProjectByProcIdModel(int procId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
//		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
        String sqlStr = "SELECT SPJ.*"
                + " FROM SF_PROJECT SPJ, SF_PROCEDURE SP"
                + " WHERE SP.PROCEDURE_ID = ?"
                + " AND SP.PROJECT_ID = SPJ.PROJECT_ID";

        sqlArgs.add(procId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ��õ��û�����ȡ����������ύ�Ĺ���
     * @param user �û���
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getLattestProjectByUserModel(String user) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
//		SfProjectDTO sfProject = (SfProjectDTO)dtoParameter;
        String sqlStr = "SELECT SP.*"
                + " FROM SF_PROJECT SP"
                + " WHERE EXISTS(SELECT NULL FROM"
                + " SF_ADMIN_AUTHORITY SAU, SF_USER SU, SF_FLOWFILE_STORE SFS,"
                + " (SELECT PROJECT_NAME, MAX(PROJECT_ID) PROJECT_ID "
                + " FROM SF_PROJECT  GROUP BY PROJECT_NAME) T"
                + " WHERE UPPER(SU.LOGIN_NAME) = UPPER(?)"
                + " AND SAU.USER_ID = SU.USER_ID"
                + " AND SAU.PROJECT_NAME = T.PROJECT_NAME"
                + " AND SP.PROJECT_ID = T.PROJECT_ID"
                + " AND SFS.PROJECT_ID = SP.PROJECT_ID)";

        sqlArgs.add(user);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
	 * ���ܣ��õ����������б�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getOptionProjectModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT DISTINCT"
						+ " SAA.PROJECT_NAME,"
						+ " SAA.PROJECT_NAME"
						+ " FROM"
						+ " SF_ADMIN_AUTHORITY SAA";

		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
	
	/**
	 * ���ܣ��õ����������б�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getOptionProjectModelId() {
		SQLModel sqlModel = new SQLModel();
	
		String sqlStr = "SELECT "
						+ " PROJECT_ID,"
						+ " PROJECT_NAME"
						+ " FROM"
						+ " SF_PROJECT_V"
						+ " ORDER BY PROJECT_ID";

		sqlModel.setSqlStr(sqlStr);
		
		return sqlModel;
	}
}