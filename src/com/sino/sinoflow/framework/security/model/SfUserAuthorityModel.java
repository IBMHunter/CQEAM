package com.sino.sinoflow.framework.security.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.framework.security.dto.SfUserAuthorityDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfUserAuthorityModel</p>
 * <p>Description:�����Զ�����SQL��������SfUserAuthorityModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfUserAuthorityModel extends BaseSQLProducer {

//	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfUserAuthorityDTO ���β���������
	 */
	public SfUserAuthorityModel(SfUserBaseDTO userAccount, SfUserAuthorityDTO dtoParameter) {
		super(userAccount, dtoParameter);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " SF_USER_AUTHORITY("
			+ " USER_ID,"
			+ " DEPARTMENT,"
			+ " POSITION,"
			+ " GROUP_NAME,"
			+ " ROLE_NAME"
			+ ") VALUES ("
			+ " ?, ?, ?, ?, ?)";

		sqlArgs.add(sfUserAuthority.getUserId());
		sqlArgs.add(sfUserAuthority.getDepartment());
		sqlArgs.add(sfUserAuthority.getPosition());
		sqlArgs.add(sfUserAuthority.getGroupName());
		sqlArgs.add(sfUserAuthority.getRoleName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
		String sqlStr = "UPDATE SF_USER_AUTHORITY"
			+ " SET"
			+ " USER_ID = ?,"
			+ " DEPARTMENT = ?,"
			+ " POSITION = ?,"
			+ " GROUP_NAME = ?,"
			+ " ROLE_NAME = ?"
			+ " WHERE"
			+ " AUTHORITY_ID = ?";

		sqlArgs.add(sfUserAuthority.getUserId());
		sqlArgs.add(sfUserAuthority.getDepartment());
		sqlArgs.add(sfUserAuthority.getPosition());
		sqlArgs.add(sfUserAuthority.getGroupName());
		sqlArgs.add(sfUserAuthority.getRoleName());
		sqlArgs.add(sfUserAuthority.getAuthorityId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_USER_AUTHORITY"
				+ " WHERE"
				+ " AUTHORITY_ID = ?";
			sqlArgs.add(sfUserAuthority.getAuthorityId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " AUTHORITY_ID,"
			+ " USER_ID,"
			+ " DEPARTMENT,"
			+ " POSITION,"
			+ " GROUP_NAME,"
			+ " ROLE_NAME"
			+ " FROM"
			+ " SF_USER_AUTHORITY"
			+ " WHERE"
			+ " AUTHORITY_ID = ?";
		sqlArgs.add(sfUserAuthority.getAuthorityId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " AUTHORITY_ID,"
			+ " USER_ID,"
			+ " DEPARTMENT,"
			+ " POSITION,"
			+ " GROUP_NAME,"
			+ " ROLE_NAME"
			+ " FROM"
			+ " SF_USER_AUTHORITY"
			+ " WHERE"
			+ " (? <= 0 OR AUTHORITY_ID = ?)"
			+ " AND (? <= 0 OR USER_ID = ?)"
			+ " AND (? = '' OR DEPARTMENT LIKE ?)"
			+ " AND (? = '' OR POSITION LIKE ?)"
			+ " AND (? = '' OR GROUP_NAME LIKE ?)"
			+ " AND (? = '' OR ROLE_NAME LIKE ?)";
		sqlArgs.add(sfUserAuthority.getAuthorityId());
		sqlArgs.add(sfUserAuthority.getAuthorityId());
		sqlArgs.add(sfUserAuthority.getUserId());
		sqlArgs.add(sfUserAuthority.getUserId());
		sqlArgs.add(sfUserAuthority.getDepartment());
		sqlArgs.add(sfUserAuthority.getDepartment());
		sqlArgs.add(sfUserAuthority.getPosition());
		sqlArgs.add(sfUserAuthority.getPosition());
		sqlArgs.add(sfUserAuthority.getGroupName());
		sqlArgs.add(sfUserAuthority.getGroupName());
		sqlArgs.add(sfUserAuthority.getRoleName());
		sqlArgs.add(sfUserAuthority.getRoleName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� userId �����ѯ����SQL��
	 * ����Զ����������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param userId AdvancedNumber
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataByUserIdModel(int userId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AUTHORITY_ID,"
						+ " DEPARTMENT,"
						+ " POSITION,"
						+ " GROUP_NAME,"
						+ " ROLE_NAME"
						+ " FROM"
						+ " SF_USER_AUTHORITY"
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
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
		if(foreignKey.equals("userId")){
			sqlModel = getDataByUserIdModel(sfUserAuthority.getUserId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� userId ��������ɾ��SQL��
	 * ����Զ����������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param userId AdvancedNumber
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByUserIdModel(int userId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
/*
            + " AUTHORITY_ID,"
			+ " DEPARTMENT,"
			+ " POSITION,"
			+ " GROUP_NAME,"
			+ " ROLE_NAME"
*/
			+ " FROM"
			+ " SF_USER_AUTHORITY"
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
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
		if(foreignKey.equals("userId")){
			sqlModel = getDeleteByUserIdModel(sfUserAuthority.getUserId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������Ñ�ְλȨ����Ϣ SF_USER_AUTHORITYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserAuthorityDTO sfUserAuthority = (SfUserAuthorityDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " AUTHORITY_ID,"
			+ " USER_ID,"
			+ " DEPARTMENT,"
			+ " POSITION,"
			+ " GROUP_NAME,"
			+ " ROLE_NAME"
			+ " FROM"
			+ " SF_USER_AUTHORITY"
			+ " WHERE"
			+ " (? <= 0 OR AUTHORITY_ID = ?)"
			+ " AND (? <= 0 OR USER_ID = ?)"
			+ " AND (? = '' OR DEPARTMENT LIKE ?)"
			+ " AND (? = '' OR POSITION LIKE ?)"
			+ " AND (? = '' OR GROUP_NAME LIKE ?)"
			+ " AND (? = '' OR ROLE_NAME LIKE ?)";
		sqlArgs.add(sfUserAuthority.getAuthorityId());
		sqlArgs.add(sfUserAuthority.getAuthorityId());
		sqlArgs.add(sfUserAuthority.getUserId());
		sqlArgs.add(sfUserAuthority.getUserId());
		sqlArgs.add(sfUserAuthority.getDepartment());
		sqlArgs.add(sfUserAuthority.getDepartment());
		sqlArgs.add(sfUserAuthority.getPosition());
		sqlArgs.add(sfUserAuthority.getPosition());
		sqlArgs.add(sfUserAuthority.getGroupName());
		sqlArgs.add(sfUserAuthority.getGroupName());
		sqlArgs.add(sfUserAuthority.getRoleName());
		sqlArgs.add(sfUserAuthority.getRoleName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    public SQLModel getGroupModel(String loginName, String role) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SUA.GROUP_NAME, SU.USERNAME "
            + " FROM"
            + " SF_USER_AUTHORITY SUA, SF_USER SU"
            + " WHERE"
            + " UPPER(SU.LOGIN_NAME) = UPPER(?)"
            + " AND SU.USER_ID = SUA.USER_ID"
            + " AND SUA.ROLE_NAME = ?";
        sqlArgs.add(loginName);
        sqlArgs.add(role);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
