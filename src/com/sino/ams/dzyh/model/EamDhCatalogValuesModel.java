package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EamDhCatalogValuesDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamDhCatalogValuesModel</p>
 * <p>Description:�����Զ�����SQL��������EamDhCatalogValuesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EamDhCatalogValuesModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCatalogValuesDTO ���β���������
	 */
	public EamDhCatalogValuesModel(SfUserDTO userAccount, EamDhCatalogValuesDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogValuesDTO dto = (EamDhCatalogValuesDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " EAM_DH_CATALOG_VALUES("
			+ " CATALOG_VALUE_ID,"
			+ " CATALOG_CODE,"
			+ " CATALOG_SET_ID,"
			+ " CATALOG_NAME,"
			+ " UNIT,"
			+ " DESCRIPTION,"
			+ " BARCODE_FLAG,"
			+ " COMMON_FLAG,"
			+ " ENABLED,"
			+ " CREATED_BY,"
			+ " CREATION_DATE"
			+ ") VALUES ("
			+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
	
		sqlArgs.add(dto.getCatalogCode());
		sqlArgs.add(dto.getCatalogSetId());
		sqlArgs.add(dto.getCatalogName());
		sqlArgs.add(dto.getUnit());
		sqlArgs.add(dto.getDescription());
		sqlArgs.add(dto.getBarcodeFlag());
		sqlArgs.add(dto.getCommonFlag());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(userAccount.getUserId());
			
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogValuesDTO dto = (EamDhCatalogValuesDTO)dtoParameter;
		String sqlStr = "UPDATE EAM_DH_CATALOG_VALUES"
			+ " SET"
			+ " CATALOG_CODE = ?,"
			+ " CATALOG_SET_ID = ?,"
			+ " CATALOG_NAME = ?,"
			+ " UNIT = ?,"
			+ " DESCRIPTION = ?,"
			+ " BARCODE_FLAG = ?,"
			+ " COMMON_FLAG = ?,"
			+ " ENABLED = ?,"
			+ " LAST_UPDATE_BY = ?,"
			+ " LAST_UPDATE_DATE = GETDATE()"
			+ " WHERE"
			+ " CATALOG_VALUE_ID = ?";
	
		sqlArgs.add(dto.getCatalogCode());
		sqlArgs.add(dto.getCatalogSetId());
		sqlArgs.add(dto.getCatalogName());
		sqlArgs.add(dto.getUnit());
		sqlArgs.add(dto.getDescription());
		sqlArgs.add(dto.getBarcodeFlag());
		sqlArgs.add(dto.getCommonFlag());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getCatalogValueId());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogValuesDTO dto = (EamDhCatalogValuesDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " EAM_DH_CATALOG_VALUES"
				+ " WHERE"
				+ " CATALOG_VALUE_ID = ?";
			sqlArgs.add(dto.getCatalogValueId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogValuesDTO dto = (EamDhCatalogValuesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " CATALOG_VALUE_ID,"
			+ " CATALOG_CODE,"
			+ " CATALOG_SET_ID,"
			+ " CATALOG_NAME,"
			+ " UNIT,"
			+ " DESCRIPTION,"
			+ " BARCODE_FLAG,"
			+ " COMMON_FLAG,"
			+ " ENABLED,"
			+ " AMS_PUB_PKG.GET_USER_NAME(CREATED_BY) CREATED_BY,"
			+ " CREATION_DATE,"
			+ " AMS_PUB_PKG.GET_USER_NAME(LAST_UPDATE_BY) LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_DH_CATALOG_VALUES"
			+ " WHERE"
			+ " CATALOG_VALUE_ID = ?";
		sqlArgs.add(dto.getCatalogValueId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� catalogSetId �����ѯ����SQL��
	 * ����Զ��������ݵ�ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param catalogSetId AdvancedNumber 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataByCatalogSetIdModel(int catalogSetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " CATALOG_VALUE_ID,"
			+ " CATALOG_CODE,"
			+ " CATALOG_NAME,"
			+ " UNIT,"
			+ " DESCRIPTION,"
			+ " BARCODE_FLAG,"
			+ " COMMON_FLAG,"
			+ " ENABLED,"
			+ " AMS_PUB_PKG.GET_USER_NAME(CREATED_BY) CREATED_BY,"
			+ " CRAATION_DATE,"
			+ " AMS_PUB_PKG.GET_USER_NAME(LAST_UPDATE_BY) LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_DH_CATALOG_VALUES"
			+ " WHERE"
			+ " CATALOG_SET_ID = ?";
		sqlArgs.add(catalogSetId);
		
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
		EamDhCatalogValuesDTO dto = (EamDhCatalogValuesDTO)dtoParameter;
		if(foreignKey.equals("catalogSetId")){
			sqlModel = getDataByCatalogSetIdModel(dto.getCatalogSetId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCatalogValuesDTO dto = (EamDhCatalogValuesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EDCV.CATALOG_VALUE_ID,"
			+ " EDCV.CATALOG_CODE,"
			+ " EDCV.CATALOG_SET_ID,"
			+ " EDCS.SET_CODE,"
			+ " EDCS.SET_NAME,"
			+ " EDCV.CATALOG_NAME,"
			+ " EDCV.UNIT,"
			+ " EFV.VALUE UNIT_VALUE,"
			+ " EDCV.DESCRIPTION,"
			+ " CASE WHEN EDCV.BARCODE_FLAG='1' THEN '�������' ELSE '���������' END BARCODE_FLAG,"
			+ " CASE WHEN EDCV.COMMON_FLAG='1' THEN '����' ELSE '�ǳ���' END COMMON_FLAG,"
			+ " CASE WHEN EDCV.ENABLED='1' THEN '��' ELSE '��' END ENABLED,"
			+ " AMS_PUB_PKG.GET_USER_NAME(EDCV.CREATED_BY) CREATED_BY,"
			+ " EDCV.CREATION_DATE,"
			+ " AMS_PUB_PKG.GET_USER_NAME(EDCV.LAST_UPDATE_BY) LAST_UPDATE_BY,"
			+ " EDCV.LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_DH_CATALOG_VALUES EDCV,"
			+ " EAM_DH_CATALOG_SET EDCS,"
			+ " ETS_FLEX_VALUES EFV,"
			+ " ETS_FLEX_VALUE_SET EFVS"
			+ " WHERE"
			+ " EDCV.CATALOG_SET_ID=EDCS.CATLOG_SET_ID"
			+ " AND EDCV.UNIT=EFV.FLEX_VALUE_ID"
			+ " AND EFV.FLEX_VALUE_SET_ID=EFVS.FLEX_VALUE_SET_ID"
			+ " AND EDCV.CATALOG_SET_ID = dbo.NVL(? ,EDCV.CATALOG_SET_ID)"
			+ " AND EDCV.CATALOG_CODE LIKE dbo.NVL(? ,EDCV.CATALOG_CODE)"
			+ " AND EDCV.CATALOG_NAME LIKE dbo.NVL(? ,EDCV.CATALOG_NAME)"
			+ " AND EDCV.BARCODE_FLAG = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EDCV.BARCODE_FLAG)))"
			+ " AND EDCV.COMMON_FLAG = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EDCV.COMMON_FLAG)))"
			+ " ORDER BY CREATION_DATE DESC";
		sqlArgs.add(dto.getCatalogSetId());
		sqlArgs.add(dto.getCatalogCode());
		sqlArgs.add(dto.getCatalogName());
		sqlArgs.add(dto.getBarcodeFlag());
		sqlArgs.add(dto.getCommonFlag());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}