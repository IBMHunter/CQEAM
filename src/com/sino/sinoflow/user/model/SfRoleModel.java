package com.sino.sinoflow.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.StrUtil;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfRoleDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfRoleModel</p>
 * <p>Description:�����Զ�����SQL��������SfRoleModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 *
 * �޸��ˣ��׼�
 * �޸����ڣ�2008.8.21
 */


public class SfRoleModel extends BaseSQLProducer {

	private SfRoleDTO sfRole = null;

	/**
	 * ���ܣ�SF_ROLE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfRoleDTO ���β���������
	 */
	public SfRoleModel(SfUserBaseDTO userAccount, SfRoleDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfRole = (SfRoleDTO)dtoParameter;
	}

	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 *
	 * �޸�
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO "
			+ " SF_ROLE("
			+ " PROJECT_NAME,"
			+ " ROLE_NAME,"
			+ " ROLE_DESC,"
			+ " ENABLED"
			+ ") VALUES ("
			+ " ?,?, ?,?)";

		sqlArgs.add(sfRole.getProjectName());
		sqlArgs.add(sfRole.getRoleName());
		sqlArgs.add(sfRole.getRoleDesc());
		sqlArgs.add(sfRole.getEnabled());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 *
	 * �޸�
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE SF_ROLE"
			+ " SET"
			+ " ROLE_NAME = ?,"
			+ " ROLE_DESC = ?,"
			+ " ENABLED = ?"
			+ " WHERE"
			+ " ROLE_ID = ?";

		sqlArgs.add(sfRole.getRoleName());
		sqlArgs.add(sfRole.getRoleDesc());
		sqlArgs.add(sfRole.getEnabled());
		sqlArgs.add(sfRole.getRoleId());
//
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
			+ " SF_ROLE"
			+ " WHERE"
			+ " ROLE_ID = ?";
		sqlArgs.add(sfRole.getRoleId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 *
	 * �޸�
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " ROLE_ID,"
			+ " ROLE_NAME,"
			+ " ROLE_DESC,"
			+ " PROJECT_NAME"
			+ " FROM"
			+ " SF_ROLE "
			+ " WHERE"
			+ " ROLE_ID = ?";
		sqlArgs.add(sfRole.getRoleId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɶ���������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 *
	 * �޸�
	 */
	/*public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " ROLE_ID,"
			+ " ROLENAME,"
			+ " DESCRIPTION,"
			+ " SORTNO,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " SF_ROLE"
			+ " WHERE"
			+ " ROLE_ID = ?"
			+ " ROLENAME = ?"
			+ " DESCRIPTION = ?"
			+ " SORTNO = ?"
			+ " IS_INNER = ?"
			+ " CREATION_DATE = ?"
			+ " CREATED_BY = ?"
			+ " LAST_UPDATE_DATE = ?"
			+ " LAST_UPDATE_BY = ?";
		sqlArgs.add(sfRole.getRoleId());
		sqlArgs.add(sfRole.getRolename());
		sqlArgs.add(sfRole.getDescription());
		sqlArgs.add(sfRole.getSortno());
		sqlArgs.add(sfRole.getIsInner());
		sqlArgs.add(sfRole.getCreationDate());
		sqlArgs.add(sfRole.getCreatedBy());
		sqlArgs.add(sfRole.getLastUpdateDate());
		sqlArgs.add(sfRole.getLastUpdateBy());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}*/

	/**
	 * ���ܣ�������������ֶ� createdBy �����ѯ����SQL��
	 * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param createdBy String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 *
	 * �޸�
	 */
	private SQLModel getDataByCreatedByModel(int createdBy){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " ROLE_ID,"
			+ " ROLE_NAME,"
			+ " ROLE_DESC,"
			+ " PROJECT_NAME"
			+ " FROM"
			+ " SF_ROLE"
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
	 *
	 * �޸�
	 */
	private SQLModel getDataByLastUpdateByModel(int lastUpdateBy){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT "
			+ " ROLE_ID,"
			+ " ROLE_NAME,"
			+ " ROLE_DESC,"
			+ " PROJECT_NAME"
			+ " FROM"
			+ " SF_ROLE";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		if(foreignKey.equals("createdBy")){
			sqlModel = getDataByCreatedByModel(sfRole.getCreatedBy());
		} else if(foreignKey.equals("lastUpdateBy")){
			sqlModel = getDataByLastUpdateByModel(sfRole.getLastUpdateBy());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 *
	 * �޸�
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		String sqlStr = "SELECT "
			+ " CONVERT(INT,ROLE_ID) ROLE_ID,"
			+ " ROLE_NAME,"
			+ " ROLE_DESC,"
			+ " PROJECT_NAME"
			+ " FROM"
			+ " SF_ROLE"
			+ " WHERE (? = '' OR ROLE_DESC LIKE  ?)"
			+ " AND (? = '' OR ROLE_NAME LIKE ?)"
			+ " AND (? = '' OR ENABLED LIKE ?)";
		sqlArgs.add(sfRole.getRoleDesc());
		sqlArgs.add(sfRole.getRoleDesc());
		sqlArgs.add(sfRole.getRoleName());
		sqlArgs.add(sfRole.getRoleName());
		sqlArgs.add(sfRole.getEnabled());
		sqlArgs.add(sfRole.getEnabled());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ����ڷ��ؽ�ɫ�������б�
	 * @param pName String ��������
	 * @return SQLModel
	 */
	public SQLModel getRoleOptionModel(String pName){
		return getRoleOptionModel(pName, "");
	}


	/**
	 * ���ܣ����ڷ��ؽ�ɫ�������б�
	 * @param pName String ��������
	 * @param sysAdminRole String ϵͳ����Ա��ɫ����
	 * @return SQLModel
	 */
	public SQLModel getRoleOptionModel(String pName, String sysAdminRole){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		String sqlStr = "SELECT "
			+ " ROLE_ID,"
			+ " ROLE_NAME"
			+ " FROM"
			+ " SF_ROLE"
			+ " WHERE "
			+ " PROJECT_NAME = ?"
			+ " AND ENABLED = 'Y'";
		sqlArgs.add(pName);
		if(!((SfUserBaseDTO)userAccount).isSysAdmin() && !StrUtil.isEmpty(sysAdminRole)){
			sqlStr += " AND ROLE_NAME <> ?";
			sqlArgs.add(sysAdminRole);
		}
		sqlStr += " ORDER BY ROLE_NAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����ڷ��ؽ�ɫ�������б�
	 * @param pName String
	 * @return SQLModel
	 */
	public SQLModel getRoleOptionModel2(String pName){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		String sqlStr = "SELECT "
			+ " ROLE_NAME,"
			+ " ROLE_NAME"
			+ " FROM"
			+ " SF_ROLE"
			+ " WHERE "
			+ " PROJECT_NAME = ?"
			+ " AND ENABLED = 'Y'"
			+ " ORDER BY ROLE_NAME";

		sqlArgs.add(pName);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ѯ�����û�д���
	 * @param proj String ��������
	 * @param role ��Ӧ���
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel checkRoleExistModel(String proj, String role) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
			String sqlStr = "SELECT "
					+ " *"
					+ " FROM"
					+ " SF_ROLE"
					+ " WHERE"
					+ " PROJECT_NAME = ?"
					+ " AND ROLE_NAME = ?";

		sqlArgs.add(proj);
		sqlArgs.add(role);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
