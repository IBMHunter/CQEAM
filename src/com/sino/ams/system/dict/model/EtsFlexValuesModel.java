package com.sino.ams.system.dict.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.dict.dto.EtsFlexValuesDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: EtsFlexValuesModel</p>
 * <p>Description:�����Զ�����SQL��������EtsFlexValuesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EtsFlexValuesModel extends AMSSQLProducer {

/**
	 * ���ܣ��ֵ�����(AMS) ETS_FLEX_VALUES ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsFlexValuesDTO ���β���������
	 */
	public EtsFlexValuesModel(SfUserDTO userAccount, EtsFlexValuesDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_VALUES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr =
						"INSERT INTO "
						+ " ETS_FLEX_VALUES("
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
						+ " CREATED_BY"
						+ ") VALUES ( NEWID()  , ?, ?, ?, ?, ?, ?, ?, ?,  " +  SyBaseSQLUtil.getCurDate() + "  , ?)";

		sqlArgs.add(dto.getFlexValueSetId());
		sqlArgs.add(dto.getCode());
		sqlArgs.add(dto.getValue());
		sqlArgs.add(dto.getDescription());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getAttribute1());
		sqlArgs.add(dto.getAttribute2());
		sqlArgs.add(dto.getIsInner());
		sqlArgs.add(userAccount.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_VALUES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_FLEX_VALUES"
						+ " SET"
						+ " FLEX_VALUE_SET_ID = ?,"
						+ " CODE = ?,"
						+ " VALUE = ?,"
						+ " DESCRIPTION = ?,"
						+ " ENABLED = ?,"
						+ " ATTRIBUTE1 = ?,"
						+ " ATTRIBUTE2 = ?,"
						+ " IS_INNER = ?,"
						+ " LAST_UPDATE_DATE = " +  SyBaseSQLUtil.getCurDate() + ","
						+ " LAST_UPDATE_BY = ?"
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
		sqlArgs.add(dto.getFlexValueId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_VALUES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValuesDTO dto = (EtsFlexValuesDTO) dtoParameter;
		String sqlStr = "DELETE FROM"
						+ " ETS_FLEX_VALUES"
						+ " WHERE"
						+ " FLEX_VALUE_ID = ?";
		sqlArgs.add(dto.getFlexValueId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_VALUES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
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
						+ " EFV.LAST_UPDATE_BY"
						+ " FROM"
						+ " ETS_FLEX_VALUES EFV,"
						+ " ETS_FLEX_VALUE_SET EFVS"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFV.FLEX_VALUE_ID = ?";
		sqlArgs.add(dto.getFlexValueId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� flexValueSetId �����ѯ����SQL��
	 * ����Զ����������ֵ�����(AMS) ETS_FLEX_VALUES��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
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
						+ " LAST_UPDATE_BY"
						+ " FROM"
						+ " ETS_FLEX_VALUES"
						+ " WHERE"
						+ " FLEX_VALUE_SET_ID = ?";
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
			sqlModel = getDataByFlexValueSetIdModel(dto.getFlexValueSetId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� flexValueSetId ��������ɾ��SQL��
	 * ����Զ����������ֵ�����(AMS) ETS_FLEX_VALUES ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param flexValueSetId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDeleteByFlexValueSetIdModel(String flexValueSetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " ETS_FLEX_VALUES"
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
			sqlModel = getDeleteByFlexValueSetIdModel(dto.getFlexValueSetId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_VALUESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
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
						+ " CASE EFV.ENABLED WHEN 'Y' THEN '��' ELSE '��' END ENABLED,"
						+ " EFVS.CODE DICT_TYPE_CODE,"
						+ " EFVS.NAME DICT_TYPE_NAME,"
						+ " CASE EFVS.IS_INNER WHEN 'Y' THEN '��' ELSE '��' END IS_INNER,"
						+ " CASE EFVS.MAINTAIN_FLAG WHEN 'Y' THEN '��' ELSE '��' END MAINTAIN_FLAG"
						+ " FROM"
						+ " ETS_FLEX_VALUES EFV,"
						+ " ETS_FLEX_VALUE_SET EFVS"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFV.FLEX_VALUE_SET_ID = dbo.NVL(?, EFV.FLEX_VALUE_SET_ID)"
						+ " AND (? = '' OR EFV.FLEX_VALUE_ID = ?)"
						+ " AND (? = '' OR EFV.CODE LIKE ?)"
						+ " AND (? = '' OR EFV.VALUE LIKE ?)"
						+ " AND EFV.ENABLED = dbo.NVL(?, EFV.ENABLED)"
						+ " AND EFV.IS_INNER = dbo.NVL(?, EFV.IS_INNER)";
		sqlArgs.add(dto.getFlexValueSetId());
		sqlArgs.add(dto.getFlexValueId());
		sqlArgs.add(dto.getFlexValueId());
		sqlArgs.add(dto.getCode());
		sqlArgs.add(dto.getCode());
		sqlArgs.add(dto.getValue());
		sqlArgs.add(dto.getValue());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getIsInner());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
