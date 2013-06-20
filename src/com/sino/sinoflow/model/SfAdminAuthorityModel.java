package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfAdminAuthorityDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * <p>Title: SfAdminAuthorityModel</p>
 * <p>Description:�����Զ�����SQL��������SfAdminAuthorityModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */
 

public class SfAdminAuthorityModel extends BaseSQLProducer {

//	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�����ԱȨ����Ϣ SF_ADMIN_AUTHORITY ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfAdminAuthorityDTO ���β���������
	 */
	public SfAdminAuthorityModel(SfUserBaseDTO userAccount, SfAdminAuthorityDTO dtoParameter) {
		super(userAccount, dtoParameter);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " SF_ADMIN_AUTHORITY("
			+ " USER_ID,"
			+ " PROJECT_NAME,"
			+ " GROUP_NAME"
			+ ") VALUES ("
			+ " ?, ?, ?)";
		
		sqlArgs.add(sfAdminAuthority.getUserId());
		sqlArgs.add(sfAdminAuthority.getProjectName());
		sqlArgs.add(sfAdminAuthority.getGroupName());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
		String sqlStr = "UPDATE SF_ADMIN_AUTHORITY"
			+ " SET"
			+ " USER_ID = ?,"
			+ " PROJECT_NAME = ?,"
			+ " GROUP_NAME = ?"
			+ " WHERE"
			+ " ADMIN_ID = ?";
		
		sqlArgs.add(sfAdminAuthority.getUserId());
		sqlArgs.add(sfAdminAuthority.getProjectName());
		sqlArgs.add(sfAdminAuthority.getGroupName());
		sqlArgs.add(sfAdminAuthority.getAdminId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_ADMIN_AUTHORITY"
				+ " WHERE"
				+ " ADMIN_ID = ?";
			sqlArgs.add(sfAdminAuthority.getAdminId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SAA.ADMIN_ID,"
			+ " SAA.USER_ID,"
			+ " SAA.PROJECT_NAME,"
			+ " SAA.GROUP_NAME,"
			+ " SU.LOGIN_NAME LOGIN_NAME"
			+ " FROM"
			+ " SF_ADMIN_AUTHORITY SAA,"
			+ " SF_USER SU"
			+ " WHERE"
			+ " SU.USER_ID = SAA.USER_ID"
			+ " AND ADMIN_ID = ?";
		sqlArgs.add(sfAdminAuthority.getAdminId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " ADMIN_ID,"
			+ " USER_ID,"
			+ " PROJECT_NAME,"
			+ " GROUP_NAME"
			+ " FROM"
			+ " SF_ADMIN_AUTHORITY"
			+ " WHERE"
			+ " (? <= 0 OR ADMIN_ID = ?)"
			+ " AND (? <= 0 OR USER_ID = ?)"
			+ " AND (? = '' OR PROJECT_NAME LIKE ?)"
			+ " AND (? = '' OR GROUP_NAME LIKE ?)";
		sqlArgs.add(sfAdminAuthority.getAdminId());
		sqlArgs.add(sfAdminAuthority.getAdminId());
		sqlArgs.add(sfAdminAuthority.getUserId());
		sqlArgs.add(sfAdminAuthority.getUserId());
		sqlArgs.add(sfAdminAuthority.getProjectName());
		sqlArgs.add(sfAdminAuthority.getProjectName());
		sqlArgs.add(sfAdminAuthority.getGroupName());
		sqlArgs.add(sfAdminAuthority.getGroupName());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� userId �����ѯ����SQL��
	 * ����Զ��������ݹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param userId String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByUserIdModel(int userId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " ADMIN_ID,"
			+ " PROJECT_NAME,"
			+ " GROUP_NAME"
			+ " FROM"
			+ " SF_ADMIN_AUTHORITY"
			+ " WHERE"
			+ " USER_ID = ?";
		sqlArgs.add(userId);
		
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
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
		if(foreignKey.equals("userId")){
			sqlModel = getDataByUserIdModel(sfAdminAuthority.getUserId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� userId ��������ɾ��SQL��
	 * ����Զ��������ݹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param userId String 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByUserIdModel(int userId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
/*
            + " ADMIN_ID,"
			+ " PROJECT_NAME,"
			+ " GROUP_NAME"
*/
			+ " FROM"
			+ " SF_ADMIN_AUTHORITY"
			+ " WHERE"
			+ " USER_ID = ?";
		sqlArgs.add(userId);
		
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
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
		if(foreignKey.equals("userId")){
			sqlModel = getDeleteByUserIdModel(sfAdminAuthority.getUserId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " CONVERT(INT,SA.ADMIN_ID) ADMIN_ID,"
			+ " SA.USER_ID,"
			+ " SA.PROJECT_NAME,"
			+ " SA.GROUP_NAME,"
			+ " SU.LOGIN_NAME LOGIN_NAME"
			+ " FROM"
			+ " SF_ADMIN_AUTHORITY SA,SF_USER SU"
			+ " WHERE"
			+ " (? <= 0 OR ADMIN_ID = ?)"
			+ " AND (? <= 0 OR SA.USER_ID = ?)"
			+ " AND (? = '' OR SA.PROJECT_NAME LIKE ?)"
			+ " AND (? = '' OR SA.GROUP_NAME LIKE ?)"
			+ " AND SA.USER_ID = SU.USER_ID"
			+ " ORDER BY SA.USER_ID";
		sqlArgs.add(sfAdminAuthority.getAdminId());
		sqlArgs.add(sfAdminAuthority.getAdminId());
		sqlArgs.add(sfAdminAuthority.getUserId());
		sqlArgs.add(sfAdminAuthority.getUserId());
		sqlArgs.add(sfAdminAuthority.getProjectName());
		sqlArgs.add(sfAdminAuthority.getProjectName());
		sqlArgs.add(sfAdminAuthority.getGroupName());
		sqlArgs.add(sfAdminAuthority.getGroupName());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
     * ���ܣ�ɾ��
     * @param ids IDs
     * @return SQLModel 
     */
    public SQLModel del(String[] ids){
	    String str = "";
		for (int i = 0; i < ids.length; i++) {
			str += ids[i]+",";
		}
		str = str.substring(0,str.length()-1);
    	SQLModel sqlModel = new SQLModel();
        String sqlStr = "DELETE" 
        		+ " FROM "
        		+ " SF_ADMIN_AUTHORITY"
        		+ " WHERE"
        		+ " ADMIN_ID IN("+str+")";
        sqlModel.setSqlStr(sqlStr);        
        return sqlModel;
    }

    public SQLModel add(String loginName) {
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfAdminAuthorityDTO sfAdminAuthority = (SfAdminAuthorityDTO)dtoParameter;
        String sqlStr = "INSERT INTO SF_ADMIN_AUTHORITY("
			+ " USER_ID,"
			+ " PROJECT_NAME,"
			+ " GROUP_NAME"
			+ ") SELECT SU.USER_ID, ?, ?"
            + " FROM SF_USER SU WHERE UPPER(SU.LOGIN_NAME) = UPPER(?)";        
        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(sfAdminAuthority.getProjectName());
        sqlArgs.add(sfAdminAuthority.getGroupName());
        sqlArgs.add(loginName);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� username, projName ����û���û��ȡ������Ȩ�ޡ�
     * ����Զ��������ݹ���ԱȨ����Ϣ SF_ADMIN_AUTHORITY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param username String
     * @param projName ��������
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel checkAuthorityModel(String username, String projName) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
            + " SAU.PROJECT_NAME,"
            + " SAU.ADMIN_ID,"
            + " SAU.USER_ID,"
            + " SAU.GROUP_NAME"
            + " FROM"
            + " SF_ADMIN_AUTHORITY SAU"
            + " WHERE EXISTS (SELECT NULL FROM SF_USER SU"
            + " WHERE"
            + " UPPER(SU.LOGIN_NAME) = UPPER(?)"
            + " AND SAU.USER_ID = SU.USER_ID"
            + " AND (? = '' OR SAU.PROJECT_NAME = '*' OR SAU.PROJECT_NAME = ?))";
        
        sqlArgs.add(username);
        sqlArgs.add(projName);
        sqlArgs.add(projName);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}