package com.sino.ams.system.resource.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.resource.dto.SfResPrivsDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>Title: SfResPrivsModel</p>
 * <p>Description:�����Զ�����SQL��������SfResPrivsModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfResPrivsModel extends BaseSQLProducer {

	private SfResPrivsDTO sfResPrivs = null;
	private SfUserDTO sfUser = null;
/**
	 * ���ܣ�SF_RES_PRIVS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfResPrivsDTO ���β���������
	 */
	public SfResPrivsModel(SfUserDTO userAccount, SfResPrivsDTO dtoParameter) {
		super(userAccount, dtoParameter);
		this.sfResPrivs = (SfResPrivsDTO) dtoParameter;
		this.sfUser = userAccount;
	}

/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO "
+ " SF_RES_PRIVS("
						+ " ROLE_NAME,"
						+ " SYSTEM_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY"
						+ ") VALUES ("
						+ " ?, ?, ?, ?, ?, ?)";

		sqlArgs.add(sfResPrivs.getResId());
		sqlArgs.add(sfResPrivs.getCreationDate());
		sqlArgs.add(sfResPrivs.getCreatedBy());
		sqlArgs.add(sfResPrivs.getLastUpdateDate());
		sqlArgs.add(sfResPrivs.getLastUpdateBy());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE SF_RES_PRIVS"
						+ " SET"
						+ " GROUP_NAME = ?"
						+ " SYSTEM_ID = ?"
						+ " CREATION_DATE = ?"
						+ " CREATED_BY = ?"
						+ " LAST_UPDATE_DATE = ?"
						+ " LAST_UPDATE_BY = ?"
						+ " WHERE"
						+ " ROLE_NAME = ?";

		sqlArgs.add(sfResPrivs.getSystemId());
		sqlArgs.add(sfResPrivs.getCreationDate());
		sqlArgs.add(sfResPrivs.getCreatedBy());
		sqlArgs.add(sfResPrivs.getLastUpdateDate());
		sqlArgs.add(sfResPrivs.getLastUpdateBy());
		sqlArgs.add(sfResPrivs.getRoleName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " SF_RES_PRIVS"
						+ " WHERE"
						+ " ROLE_ID = ?";
		sqlArgs.add(sfResPrivs.getRoleId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " GROUP_NAME,"
						+ " SYSTEM_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY,"
						+ " FROM"
						+ " SF_RES_PRIVS + "
						+ " WHERE"
						+ " ROLE_NAME = ?";
		sqlArgs.add(sfResPrivs.getRoleName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�������������ֶ� createdBy �����ѯ����SQL��
	 * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param createdBy String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByCreatedByModel(int createdBy) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " GROUP_NAME,"
						+ " ROLE_NAME,"
						+ " SYSTEM_ID,"
						+ " CREATION_DATE,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY"
						+ " FROM"
						+ " SF_RES_PRIVS"
						+ " WHERE"
						+ " CREATED_BY = ?";
		sqlArgs.add(createdBy);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�������������ֶ� lastUpdateBy �����ѯ����SQL��
	 * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param lastUpdateBy String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByLastUpdateByModel(int lastUpdateBy) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " FROM"
						+ " SF_RES_PRIVS"
						+ " WHERE"
						+ " LAST_UPDATE_BY = ?";
		sqlArgs.add(lastUpdateBy);

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
		if (foreignKey.equals("groupId")) {
//			sqlModel = getDataByGroupIdModel(sfResPrivs.getGroupId());
		} else if (foreignKey.equals("createdBy")) {
			sqlModel = getDataByCreatedByModel(sfResPrivs.getCreatedBy());
		} else if (foreignKey.equals("lastUpdateBy")) {
			sqlModel = getDataByLastUpdateByModel(sfResPrivs.getLastUpdateBy());
		}
		return sqlModel;
	}

/**
	 * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�SQL��
	 * @return SQLModel
	 */
	public SQLModel getResourceOptionModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " SRD.RES_ID,"
						+ " SRD.RES_NAME"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " ORDER  BY"
						+ " SRD.RES_ID,"
						+ " SRD.RES_PAR_ID";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�����URL��Դ����SQL����Ŀ������
	 * @return SQLModel
	 */
	public SQLModel getResourceTreeModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = " SELECT"
						+ " SRD.SYSTEM_ID,"
						+ " SRD.RES_ID,"
						+ " SRD.RES_PAR_ID,"
+ " SRD.RES_NAME,"
						+ " SRD.RES_ID RES_URL,"
						+ " SRD.SORT_NO,"
+ " 'N' IS_POPUP,"
+ " SRD.POPSCRIPT"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " SRD.ENABLED = 'Y'"
						+ " AND SRD.VISIBLE = 'Y'"
						+ " ORDER BY"
						+ " SRD.RES_ID,"
						+ " SRD.RES_PAR_ID";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��һ��URL��ԴSQL����ĿȨ�޹�����
	 * @return SQLModel
	 */
	public SQLModel getFirstResourceModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = " SELECT"
						+ " SRD.*"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " SRD.ENABLED = 'Y'"
						+ " AND SRD.VISIBLE = 'Y'"
						+ " ORDER BY"
						+ " SRD.RES_ID,"
						+ " SRD.RES_PAR_ID";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��һ��URL��ԴSQL����ĿȨ�޹�����
	 * @return SQLModel
	 */
	public SQLModel getResourceByIdModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT"
						+ " SRD.*"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " SRD.RES_ID = ?";
		sqlArgs.add(sfResPrivs.getResId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��ѯ����Դ��SQL��
	 * @param systemId String
	 * @return SQLModel
	 */
	public SQLModel getParentResModel(int systemId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " CHARINDEX(("
						+ " SELECT"
						+ " SRD2.RES_ID"
						+ " FROM"
						+ " SF_RES_DEFINE SRD2"
						+ " WHERE SRD2.SYSTEM_ID = ?),"
						+ " SRD.RES_ID||'.') = 1";
		sqlArgs.add(systemId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡȨ��ɾ��SQL
	 * @param sfResPriv SfResPrivsDTO
	 * @return SQLModel
	 */
	public SQLModel getPriviDeleteModel(SfResPrivsDTO sfResPriv) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " SF_RES_PRIVS"
						+ " WHERE"
						+ " RES_ID = ?";
		sqlArgs.add(sfResPriv.getResId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡȨ�������Ƿ���ڵ�SQL
	 * @param sfResPriv SfResPrivsDTO
	 * @return List
	 */
	public SQLModel getPriviExistModel(SfResPrivsDTO sfResPriv) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT"
						+ " *"
						+ " FROM"
						+ " SF_RES_PRIVS"
						+ " WHERE"
						+ " ROLE_ID = ?"
						+ " AND RES_ID = ?";
		sqlArgs.add(sfResPriv.getRoleId());
		sqlArgs.add(sfResPriv.getResId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡȨ�޲���SQL
	 * @param sfResPriv SfResPrivsDTO
	 * @return List
	 */
	public SQLModel getPriviCreateModel(SfResPrivsDTO sfResPriv) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " INSERT INTO"
						+ " SF_RES_PRIVS("
						+ " ROLE_ID,"
						+ " RES_ID,"
						+ " CREATED_BY"
						+ " ) VALUES (?, ?, ?)";
		sqlArgs.add(sfResPriv.getRoleId());
		sqlArgs.add(sfResPriv.getResId());
		sqlArgs.add(sfUser.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
