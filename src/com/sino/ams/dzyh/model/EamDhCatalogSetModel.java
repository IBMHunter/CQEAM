package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EamDhCatalogSetDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamDhCatalogSetModel</p>
 * <p>Description:�����Զ�����SQL��������EamDhCatalogSetModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamDhCatalogSetModel extends AMSSQLProducer {


	/**
	 * ���ܣ���ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SET ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCatalogSetDTO ���β���������
	 */
	public EamDhCatalogSetModel(SfUserDTO userAccount, EamDhCatalogSetDTO dtoParameter) {
		super(userAccount, dtoParameter);

	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SET���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamDhCatalogSetDTO dto = (EamDhCatalogSetDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " EAM_DH_CATALOG_SET("
				+ " CATLOG_SET_ID,"
				+ " SET_CODE,"
				+ " SET_NAME,"
				+ " ENABLED,"
				+ " END_DATE,"
				+ " CREATED_BY,"
				+ " CREATION_DATE"
				+ ") VALUES ("
				+ "  NEWID() , ?, ?, ?, ?, ?, GETDATE())";
			sqlArgs.add(dto.getSetCode());
			sqlArgs.add(dto.getSetName());
			sqlArgs.add(dto.getEnabled());
			sqlArgs.add(dto.getEndDate());
			sqlArgs.add(userAccount.getUserId());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SET���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamDhCatalogSetDTO dto = (EamDhCatalogSetDTO)dtoParameter;
			String sqlStr = "UPDATE EAM_DH_CATALOG_SET"
				+ " SET"
				+ " SET_CODE = ?,"
				+ " SET_NAME = ?,"
				+ " ENABLED = ?,"
				+ " END_DATE = ?,"
				+ " LAST_UPDATE_BY = ?,"
				+ " LAST_UPDATE_DATE = GETDATE()"
				+ " WHERE"
				+ " CATLOG_SET_ID = ?";

			sqlArgs.add(dto.getSetCode());
			sqlArgs.add(dto.getSetName());
			sqlArgs.add(dto.getEnabled());
			sqlArgs.add(dto.getEndDate());
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getCatlogSetId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SET����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogSetDTO dto = (EamDhCatalogSetDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " EAM_DH_CATALOG_SET"
				+ " WHERE"
				+ " CATLOG_SET_ID = ?";
			sqlArgs.add(dto.getCatlogSetId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SET������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogSetDTO dto = (EamDhCatalogSetDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " CATLOG_SET_ID,"
			+ " SET_CODE,"
			+ " SET_NAME,"
			+ " ENABLED,"
			+ " END_DATE,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_DH_CATALOG_SET"
			+ " WHERE"
			+ " CATLOG_SET_ID = ?";
		sqlArgs.add(dto.getCatlogSetId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SETҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogSetDTO dto = (EamDhCatalogSetDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " CATLOG_SET_ID,"
			+ " SET_CODE,"
			+ " SET_NAME,"
			+" CASE WHEN ENABLED='1' THEN '��' ELSE '��' END AS ENABLED ,"
			+ " END_DATE,"
			+ " AMS_PUB_PKG.GET_USER_NAME(CREATED_BY) CREATED_BY,"
			+ " CREATION_DATE,"
			+ " AMS_PUB_PKG.GET_USER_NAME(LAST_UPDATE_BY) LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_DH_CATALOG_SET"
			+ " WHERE"
			+ " ( " + SyBaseSQLUtil.isNull() + "  OR SET_CODE LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SET_NAME LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ENABLED LIKE ?)"
			+ " ORDER BY CREATION_DATE DESC";
		sqlArgs.add(dto.getSetCode());
		sqlArgs.add(dto.getSetCode());
		sqlArgs.add(dto.getSetName());
		sqlArgs.add(dto.getSetName());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getEnabled());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}
