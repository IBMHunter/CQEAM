package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EamDhPriviDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamDhPriviModel</p>
 * <p>Description:�����Զ�����SQL��������EamDhPriviModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamDhPriviModel extends AMSSQLProducer {


	/**
	 * ���ܣ�Ȩ�޶����(EAM) EAM_DH_PRIVI ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhPriviDTO ���β���������
	 */
	public EamDhPriviModel(SfUserDTO userAccount, EamDhPriviDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ�����Ȩ�޶����(EAM) EAM_DH_PRIVI���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhPriviDTO dto = (EamDhPriviDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " EAM_DH_PRIVI("
			+ " PRIVI_ID,"
			+ " USER_ID,"
			+ " DEPT_CODE,"
			+ " ORG_ID,"
			+ " ENABLED,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " DEFAULTFLAG"
			+ ") VALUES ("
			+ "  NEWID() , ?, ?, ?, ?, ?, GETDATE(), ?)";
	
		sqlArgs.add(dto.getUserId());
		sqlArgs.add(dto.getDeptCode());
		sqlArgs.add(dto.getOrgId());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getDefaultflag());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����Ȩ�޶����(EAM) EAM_DH_PRIVI���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamDhPriviDTO dto = (EamDhPriviDTO)dtoParameter;
			String sqlStr = "UPDATE EAM_DH_PRIVI"
				+ " SET"
				+ " USER_ID = ?,"
				+ " DEPT_CODE = ?,"
				+ " ORG_ID = ?,"
				+ " ENABLED = ?,"
				+ " CREATED_BY = ?,"
				+ " CREATION_DATE = ?,"
				+ " LAST_UPDATE_BY = ?,"
				+ " LAST_UPDATE_DATE = ?,"
				+ " DEFAULTFLAG = ?"
				+ " WHERE"
				+ " PRIVI_ID = ?";
		
			sqlArgs.add(dto.getUserId());
			sqlArgs.add(dto.getDeptCode());
			sqlArgs.add(dto.getOrgId());
			sqlArgs.add(dto.getEnabled());
			sqlArgs.add(dto.getCreatedBy());
			sqlArgs.add(dto.getCreationDate());
			sqlArgs.add(dto.getLastUpdateBy());
			sqlArgs.add(dto.getLastUpdateDate());
			sqlArgs.add(dto.getDefaultflag());
			sqlArgs.add(dto.getPriviId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����Ȩ�޶����(EAM) EAM_DH_PRIVI����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhPriviDTO dto = (EamDhPriviDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " EAM_DH_PRIVI"
				+ " WHERE"
				+ " PRIVI_ID = ?";
			sqlArgs.add(dto.getPriviId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����Ȩ�޶����(EAM) EAM_DH_PRIVI������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhPriviDTO dto = (EamDhPriviDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " PRIVI_ID,"
			+ " USER_ID,"
			+ " DEPT_CODE,"
			+ " ORG_ID,"
			+ " ENABLED,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " DEFAULTFLAG"
			+ " FROM"
			+ " EAM_DH_PRIVI"
			+ " WHERE"
			+ " PRIVI_ID = ?";
		sqlArgs.add(dto.getPriviId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����Ȩ�޶����(EAM) EAM_DH_PRIVIҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhPriviDTO dto = (EamDhPriviDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EDP.PRIVI_ID,"
			+ " AMS_PUB_PKG.GET_ORGNIZATION_NAME(EDP.ORG_ID) COMPANY_NAME,"
			+ " AMS_PUB_PKG.GET_DEPT_NAME(EDP.DEPT_CODE) DEPT_NAME,"
			+ " AMS_PUB_PKG.GET_USER_NAME(EDP.USER_ID) USER_NAME,"
			+ " CASE WHEN EDP.ENABLED='1' THEN '��' ELSE '��' END  ENABLED, "
			+ " EDP.CREATION_DATE,"
			+ " DEFAULTFLAG"
			+ " FROM"
			+ " EAM_DH_PRIVI EDP"
			+ " WHERE"
			+ " EDP.DEPT_CODE = dbo.NVL(?, EDP.DEPT_CODE)"
			+ " AND EDP.USER_ID = ISNULL(?, EDP.USER_ID)"
			+ " AND EDP.ORG_ID = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EDP.ORG_ID)))"
			+ " ORDER BY EDP.CREATION_DATE DESC";
		sqlArgs.add(dto.getDeptCode());
		sqlArgs.add(dto.getUserId());
		sqlArgs.add(dto.getOrgId());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}
	public SQLModel getCheckDefaultflagModel(String defaultflag)
	{
		SQLModel sqlModel = new SQLModel();
		List strArg = new ArrayList();
		String strSql = "SELECT 1 FROM EAM_DH_PRIVI EDP WHERE EDP.DEFAULTFLAG = ?";
		strArg.add(defaultflag);
		
		sqlModel.setSqlStr(strSql);
		sqlModel.setArgs(strArg);
		return sqlModel;
	}
}