package com.sino.ams.system.dict.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.dict.dto.EtsFlexValuesDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.StrUtil;


/**
 * <p>Title: EtsFlexAnalyseValuesModel</p>
 * <p>Description:�����Զ�����SQL��������EtsFlexAnalyseValuesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author kouzh
 * @version 1.0
 */


public class EtsFlexAnalyseValuesModel extends AMSSQLProducer {

/**
	 * ���ܣ��ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsFlexValuesDTO ���β���������
	 */
	public EtsFlexAnalyseValuesModel(SfUserDTO userAccount, EtsFlexValuesDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " ETS_FLEX_ANALYSE_VALUES("
						+ " FLEX_VALUE_ID,"
						+ " FLEX_VALUE_SET_ID,"
						+ " CODE,"
						+ " VALUE,"
						+ " DESCRIPTION,"
						+ " ENABLED,"
						+ " ATTRIBUTE1,"
						+ " ATTRIBUTE2,"
						+ " IS_INNER,"
						+ " CREATION_DATE,"
						+ " CREATED_BY," 
						+ " FILE_VERSION,"
						+ " ATTRIBUTE3,"
						+ " ATTRIBUTE4,"
						+ " ATTRIBUTE5,"
						+ " ATTRIBUTE6"
						+ ") VALUES ("
						+ " MEWID() , ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?,?,?,?,?,?)";

		sqlArgs.add(dto.getFlexValueSetId());
		sqlArgs.add(dto.getCode());
		sqlArgs.add(dto.getValue());
		sqlArgs.add(dto.getDescription());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getAttribute1());
		sqlArgs.add(dto.getAttribute2());
		sqlArgs.add(dto.getIsInner());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getFileVersion());
		sqlArgs.add(dto.getAttribute3());
		sqlArgs.add(dto.getAttribute4());
		sqlArgs.add(dto.getAttribute5());
		sqlArgs.add(dto.getAttribute6());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_FLEX_ANALYSE_VALUES"
						+ " SET"
						+ " FLEX_VALUE_SET_ID = ?,"
						+ " CODE = ?,"
						+ " VALUE = ?,"
						+ " DESCRIPTION = ?,"
						+ " ENABLED = ?,"
						+ " ATTRIBUTE1 = ?,"
						+ " ATTRIBUTE2 = ?,"
						+ " IS_INNER = ?,"
						+ " LAST_UPDATE_DATE = GETDATE(),"
						+ " LAST_UPDATE_BY = ?,"
						+ " FILE_VERSION = ?,"
						+ " ATTRIBUTE3 = ?,"
						+ " ATTRIBUTE4 = ?,"
						+ " ATTRIBUTE5 = ?,"
						+ " ATTRIBUTE6 = ?"
						+ " WHERE"
						+ " FLEX_VALUE_ID = ?";

		sqlArgs.add(dto.getFlexValueSetId());
		sqlArgs.add(dto.getCode());
		sqlArgs.add(dto.getValue());
		sqlArgs.add(dto.getDescription());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getAttribute1());
		sqlArgs.add(dto.getAttribute2());
		sqlArgs.add(dto.getIsInner());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getFileVersion());
		sqlArgs.add(dto.getAttribute3());
		sqlArgs.add(dto.getAttribute4());
		sqlArgs.add(dto.getAttribute5());
		sqlArgs.add(dto.getAttribute6());
		sqlArgs.add(dto.getFlexValueId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "DELETE FROM"
						+ " ETS_FLEX_ANALYSE_VALUES"
						+ " WHERE"
						+ " FLEX_VALUE_ID = ?";
		sqlArgs.add(dto.getFlexValueId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " EFV.FLEX_VALUE_ID,"
						+ " EFV.FLEX_VALUE_SET_ID,"
						+ " EFVS.NAME FLEX_VALUE_SET_NAME,"
						+ " EFV.CODE,"
						+ " EFV.VALUE,"
						+ " EFV.DESCRIPTION,"
						+ " EFV.ENABLED,"
						+ " EFV.ATTRIBUTE1,"
						+ " EFV.ATTRIBUTE2,"
						+ " EFV.IS_INNER,"
						+ " EFV.CREATION_DATE,"
						+ " EFV.CREATED_BY,"
						+ " EFV.LAST_UPDATE_DATE,"
						+ " EFV.LAST_UPDATE_BY,"
						+ " EFV.FILE_VERSION,"
						+ " EFV.ATTRIBUTE3,"
						+ " EFV.ATTRIBUTE4,"
						+ " EFV.ATTRIBUTE5,"
						+ " EFV.ATTRIBUTE6"
						+ " FROM"
						+ " ETS_FLEX_ANALYSE_VALUES EFV,"
						+ " ETS_FLEX_ANALYSE_VALUE_SET EFVS"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFV.FLEX_VALUE_ID = ? AND EFV.FILE_VERSION =(SELECT DISTINCT (T.FILE_VERSION) FROM "
				+ " AMS_FILE_VERSION T WHERE T.START_DATE <= current_date AND current_date <= T.END_DATE)";
		sqlArgs.add(dto.getFlexValueId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� flexValueSetId �����ѯ����SQL��
	 * ����Զ����������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param flexValueSetId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByFlexValueSetIdModel(String flexValueSetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " FLEX_VALUE_ID,"
						+ " CODE,"
						+ " VALUE,"
						+ " DESCRIPTION,"
						+ " ENABLED,"
						+ " ATTRIBUTE1,"
						+ " ATTRIBUTE2,"
						+ " IS_INNER,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY,"
						+ " FILE_VERSION,"
						+ " ATTRIBUTE3,"
						+ " ATTRIBUTE4,"
						+ " ATTRIBUTE5,"
						+ " ATTRIBUTE6"
						+ " FROM"
						+ " ETS_FLEX_ANALYSE_VALUES EFV"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = ?  AND EFV.FILE_VERSION =(SELECT DISTINCT (T.FILE_VERSION) FROM "
				+ " AMS_FILE_VERSION T WHERE T.START_DATE <= current_date AND current_date <= T.END_DATE)";
		sqlArgs.add(flexValueSetId);

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
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		if (foreignKey.equals("flexValueSetId")) {
			sqlModel = getDataByFlexValueSetIdModel(StrUtil.nullToString(dto.getFlexValueSetId()));
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� flexValueSetId ��������ɾ��SQL��
	 * ����Զ����������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUES ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param flexValueSetId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDeleteByFlexValueSetIdModel(String flexValueSetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " ETS_FLEX_ANALYSE_VALUES"
						+ " WHERE"
						+ " FLEX_VALUE_SET_ID = ?";
		sqlArgs.add(flexValueSetId);

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
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		if (foreignKey.equals("flexValueSetId")) {
			sqlModel = getDeleteByFlexValueSetIdModel(StrUtil.nullToString(dto.getFlexValueSetId()));
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " EFV.FLEX_VALUE_ID,"
						+ " EFV.FLEX_VALUE_SET_ID,"
						+ " EFV.CODE,"
						+ " EFV.VALUE,"
						+ " EFV.DESCRIPTION,"
						+ " CASE WHEN ENABLED='Y' THEN '��' ELSE '��' END ENABLED,"
						+ " EFVS.CODE DICT_TYPE_CODE,"
						+ " EFVS.NAME DICT_TYPE_NAME,"
						+ " CASE WHEN IS_INNER='Y' THEN '��' ELSE '��' END IS_INNER ,"
						+ " CASE WHEN MAINTAIN_FLAG='Y' THEN '��' ELSE '��' END MAINTAIN_FLAG,"
						+ " EFV.FILE_VERSION,"
						+ " EFV.ATTRIBUTE1,"
						+ " EFV.ATTRIBUTE2,"
						+ " EFV.ATTRIBUTE3,"
						+ " EFV.ATTRIBUTE4,"
						+ " EFV.ATTRIBUTE5,"
						+ " EFV.ATTRIBUTE6"
						+ " FROM"
						+ " ETS_FLEX_ANALYSE_VALUES EFV,"
						+ " ETS_FLEX_ANALYSE_VALUE_SET EFVS"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFV.FLEX_VALUE_SET_ID = dbo.NVL(?, EFV.FLEX_VALUE_SET_ID)"
						+ " AND EFV.CODE LIKE dbo.NVL(?, EFV.CODE)"
						+ " AND EFV.VALUE LIKE dbo.NVL(?, EFV.VALUE)"
						+ " AND EFV.ENABLED = dbo.NVL(?, EFV.ENABLED)"
						+ " AND EFV.IS_INNER = dbo.NVL(?, EFV.IS_INNER)"
		                + " AND EFV.FLEX_VALUE_ID = dbo.NVL(?, EFV.FLEX_VALUE_ID)  AND EFV.FILE_VERSION =(SELECT TOP 1 (T.FILE_VERSION) FROM "
				+ " AMS_FILE_VERSION T WHERE T.START_DATE <= current_date AND current_date <= T.END_DATE)";
		sqlArgs.add(dto.getFlexValueSetId());
		sqlArgs.add(dto.getCode());
		sqlArgs.add(dto.getValue());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getIsInner());
		sqlArgs.add(dto.getFlexValueId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
