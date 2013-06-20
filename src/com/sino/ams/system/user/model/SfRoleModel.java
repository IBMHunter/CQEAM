package com.sino.ams.system.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfRoleDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: SfRoleModel</p>
 * <p>Description:�����Զ�����SQL��������SfRoleModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfRoleModel extends BaseSQLProducer {

	private SfRoleDTO sfRole = null;
    private SfUserDTO sfUser = null;

    /**
	 * ���ܣ�SF_ROLE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfRoleDTO ���β���������
	 */
	public SfRoleModel(SfUserDTO userAccount, SfRoleDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfRole = (SfRoleDTO)dtoParameter;
        sfUser=userAccount;
    }
	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO "
			+ " SF_ROLE("
			+ " ROLE_ID,"
			+ " ROLENAME,"
			+ " DESCRIPTION,"
			+ " SORTNO,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ ") VALUES ("
			+ "  NEWID() , ?, ?, ?, ?, GETDATE(), ?, ?, ?)";
		
		sqlArgs.add(sfRole.getRolename());
		sqlArgs.add(sfRole.getDescription());
		sqlArgs.add(sfRole.getSortno());
		sqlArgs.add(sfRole.getIsInner());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfRole.getLastUpdateDate());
		sqlArgs.add(sfRole.getLastUpdateBy());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE SF_ROLE"
			+ " SET"
			+ " ROLENAME = ?,"
			+ " DESCRIPTION = ?,"
			+ " SORTNO = ?,"
			+ " IS_INNER = ?,"
			+ " LAST_UPDATE_DATE = GETDATE(),"
			+ " LAST_UPDATE_BY = ?"
			+ " WHERE"
			+ " ROLE_ID = ?";
		
		sqlArgs.add(sfRole.getRolename());
		sqlArgs.add(sfRole.getDescription());
		sqlArgs.add(sfRole.getSortno());
		sqlArgs.add(sfRole.getIsInner());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfRole.getRoleId());
		
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
	 */
	public SQLModel getPrimaryKeyDataModel(){
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
	 */
	public SQLModel getDataMuxModel(){
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
	}

	/**
	 * ���ܣ�������������ֶ� createdBy �����ѯ����SQL��
	 * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param createdBy String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByCreatedByModel(int createdBy){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " ROLE_ID,"
			+ " ROLENAME,"
			+ " DESCRIPTION,"
			+ " SORTNO,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
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
	 */
	private SQLModel getDataByLastUpdateByModel(int lastUpdateBy){
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
			+ " LAST_UPDATE_DATE"
			+ " FROM"
			+ " SF_ROLE"
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
	 */
	public SQLModel getPageQueryModel(){
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
			+ " WHERE 1=1"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ROLE_ID LIKE  ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ROLENAME LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR DESCRIPTION LIKE  ?)";

        sqlArgs.add(sfRole.getRoleId());
        sqlArgs.add(sfRole.getRoleId());
		sqlArgs.add(sfRole.getRolename());
		sqlArgs.add(sfRole.getRolename());
		sqlArgs.add(sfRole.getDescription());
		sqlArgs.add(sfRole.getDescription());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}