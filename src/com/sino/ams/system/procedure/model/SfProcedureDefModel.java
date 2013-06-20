package com.sino.ams.system.procedure.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.procedure.dto.SfProcedureDefDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: SfProcedureDefModel</p>
 * <p>Description:�����Զ�����SQL��������SfProcedureDefModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class SfProcedureDefModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ת���̶��� SF_PROCEDURE_DEF ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfProcedureDefDTO ���β���������
	 */
	public SfProcedureDefModel(SfUserDTO userAccount, SfProcedureDefDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ�������ת���̶��� SF_PROCEDURE_DEF���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDefDTO sfProcedureDef = (SfProcedureDefDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " SF_PROCEDURE_DEF("
			+ " PROC_ID,"
			+ " PROC_NAME,"
			+ " DESCRIPTION,"
			+ " APP_TABLE_NAME,"
			+ " APP_PK_NAME,"
			+ " ORGANIZATION_ID,"
			+ " DISABLED,"
			+ " APPROVE_PATH,"
			+ " EDIT_PATH,"
			+ " QUERY_PATH,"
			+ " CANCEL_PATH"
			+ ") VALUES ("
			+ "  SF_PROCEDURE_DEF , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		sqlArgs.add(sfProcedureDef.getProcName());
		sqlArgs.add(sfProcedureDef.getDescription());
		sqlArgs.add(sfProcedureDef.getAppTableName());
		sqlArgs.add(sfProcedureDef.getAppPkName());
		sqlArgs.add(sfProcedureDef.getOrganizationId());
		sqlArgs.add(sfProcedureDef.getDisabled());
		sqlArgs.add(sfProcedureDef.getApprovePath());
		sqlArgs.add(sfProcedureDef.getEditPath());
		sqlArgs.add(sfProcedureDef.getQueryPath());
		sqlArgs.add(sfProcedureDef.getCancelPath());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������ת���̶��� SF_PROCEDURE_DEF���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDefDTO sfProcedureDef = (SfProcedureDefDTO)dtoParameter;
		String sqlStr = "UPDATE SF_PROCEDURE_DEF"
			+ " SET"
			+ " PROC_NAME = ?,"
			+ " DESCRIPTION = ?,"
			+ " APP_TABLE_NAME = ?,"
			+ " APP_PK_NAME = ?,"
			+ " ORGANIZATION_ID = ?,"
			+ " DISABLED = ?,"
			+ " APPROVE_PATH = ?,"
			+ " EDIT_PATH = ?,"
			+ " QUERY_PATH = ?,"
			+ " CANCEL_PATH = ?"
			+ " WHERE"
			+ " PROC_ID = ?";
		
		sqlArgs.add(sfProcedureDef.getProcName());
		sqlArgs.add(sfProcedureDef.getDescription());
		sqlArgs.add(sfProcedureDef.getAppTableName());
		sqlArgs.add(sfProcedureDef.getAppPkName());
		sqlArgs.add(sfProcedureDef.getOrganizationId());
		sqlArgs.add(sfProcedureDef.getDisabled());
		sqlArgs.add(sfProcedureDef.getApprovePath());
		sqlArgs.add(sfProcedureDef.getEditPath());
		sqlArgs.add(sfProcedureDef.getQueryPath());
		sqlArgs.add(sfProcedureDef.getCancelPath());
		sqlArgs.add(sfProcedureDef.getProcId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������ת���̶��� SF_PROCEDURE_DEF����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDefDTO sfProcedureDef = (SfProcedureDefDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
			+ " SF_PROCEDURE_DEF"
			+ " WHERE"
			+ " PROC_ID = ?";
		sqlArgs.add(sfProcedureDef.getProcId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������ת���̶��� SF_PROCEDURE_DEF������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDefDTO sfProcedureDef = (SfProcedureDefDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PROC_ID,"
			+ " PROC_NAME,"
			+ " DESCRIPTION,"
			+ " APP_TABLE_NAME,"
			+ " APP_PK_NAME,"
			+ " ORGANIZATION_ID,"
			+ " DISABLED,"
			+ " APPROVE_PATH,"
			+ " EDIT_PATH,"
			+ " QUERY_PATH,"
			+ " CANCEL_PATH"
			+ " FROM"
			+ " SF_PROCEDURE_DEF"
			+ " WHERE"
			+ " PROC_ID = ?";
		sqlArgs.add(sfProcedureDef.getProcId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������ת���̶��� SF_PROCEDURE_DEF����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDefDTO sfProcedureDef = (SfProcedureDefDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PROC_ID,"
			+ " PROC_NAME,"
			+ " DESCRIPTION,"
			+ " APP_TABLE_NAME,"
			+ " APP_PK_NAME,"
			+ " ORGANIZATION_ID,"
			+ " DISABLED,"
			+ " APPROVE_PATH,"
			+ " EDIT_PATH,"
			+ " QUERY_PATH,"
			+ " CANCEL_PATH"
			+ " FROM"
			+ " SF_PROCEDURE_DEF"
			+ " WHERE"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR PROC_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR PROC_NAME LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR DESCRIPTION LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR APP_TABLE_NAME LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR APP_PK_NAME LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR DISABLED LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR APPROVE_PATH LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR EDIT_PATH LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR QUERY_PATH LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CANCEL_PATH LIKE ?)";
		sqlArgs.add(sfProcedureDef.getProcId());
		sqlArgs.add(sfProcedureDef.getProcId());
		sqlArgs.add(sfProcedureDef.getProcName());
		sqlArgs.add(sfProcedureDef.getProcName());
		sqlArgs.add(sfProcedureDef.getDescription());
		sqlArgs.add(sfProcedureDef.getDescription());
		sqlArgs.add(sfProcedureDef.getAppTableName());
		sqlArgs.add(sfProcedureDef.getAppTableName());
		sqlArgs.add(sfProcedureDef.getAppPkName());
		sqlArgs.add(sfProcedureDef.getAppPkName());
		sqlArgs.add(sfProcedureDef.getOrganizationId());
		sqlArgs.add(sfProcedureDef.getOrganizationId());
		sqlArgs.add(sfProcedureDef.getDisabled());
		sqlArgs.add(sfProcedureDef.getDisabled());
		sqlArgs.add(sfProcedureDef.getApprovePath());
		sqlArgs.add(sfProcedureDef.getApprovePath());
		sqlArgs.add(sfProcedureDef.getEditPath());
		sqlArgs.add(sfProcedureDef.getEditPath());
		sqlArgs.add(sfProcedureDef.getQueryPath());
		sqlArgs.add(sfProcedureDef.getQueryPath());
		sqlArgs.add(sfProcedureDef.getCancelPath());
		sqlArgs.add(sfProcedureDef.getCancelPath());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������ת���̶��� SF_PROCEDURE_DEFҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfProcedureDefDTO sfProcedureDef = (SfProcedureDefDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PROC_ID,"
			+ " PROC_NAME,"
			+ " DESCRIPTION,"
			+ " APP_TABLE_NAME,"
			+ " APP_PK_NAME,"
			+ " ORGANIZATION_ID,"
			+ " DISABLED,"
			+ " APPROVE_PATH,"
			+ " EDIT_PATH,"
			+ " QUERY_PATH,"
			+ " CANCEL_PATH"
			+ " FROM"
			+ " SF_PROCEDURE_DEF"
			+ " WHERE"
			+ " ( " + SyBaseSQLUtil.isNull() + "  OR PROC_ID LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR PROC_NAME LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR DESCRIPTION LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR APP_TABLE_NAME LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR APP_PK_NAME LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR DISABLED LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR APPROVE_PATH LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EDIT_PATH LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR QUERY_PATH LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CANCEL_PATH LIKE ?)"
			+ " ORDER BY PROC_NAME";
		sqlArgs.add(sfProcedureDef.getProcId());
		sqlArgs.add(sfProcedureDef.getProcId());
		sqlArgs.add(sfProcedureDef.getProcName());
		sqlArgs.add(sfProcedureDef.getProcName());
		sqlArgs.add(sfProcedureDef.getDescription());
		sqlArgs.add(sfProcedureDef.getDescription());
		sqlArgs.add(sfProcedureDef.getAppTableName());
		sqlArgs.add(sfProcedureDef.getAppTableName());
		sqlArgs.add(sfProcedureDef.getAppPkName());
		sqlArgs.add(sfProcedureDef.getAppPkName());
		sqlArgs.add(sfProcedureDef.getOrganizationId());
		sqlArgs.add(sfProcedureDef.getOrganizationId());
		sqlArgs.add(sfProcedureDef.getDisabled());
		sqlArgs.add(sfProcedureDef.getDisabled());
		sqlArgs.add(sfProcedureDef.getApprovePath());
		sqlArgs.add(sfProcedureDef.getApprovePath());
		sqlArgs.add(sfProcedureDef.getEditPath());
		sqlArgs.add(sfProcedureDef.getEditPath());
		sqlArgs.add(sfProcedureDef.getQueryPath());
		sqlArgs.add(sfProcedureDef.getQueryPath());
		sqlArgs.add(sfProcedureDef.getCancelPath());
		sqlArgs.add(sfProcedureDef.getCancelPath());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}