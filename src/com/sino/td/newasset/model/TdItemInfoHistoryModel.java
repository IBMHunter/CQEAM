package com.sino.td.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.td.newasset.dto.TdItemInfoHistoryDTO;

/**
 * <p>Title: AmsItemInfoHistoryModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemInfoHistoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class TdItemInfoHistoryModel extends AMSSQLProducer {

	/**
	 * ���ܣ��豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter TdItemInfoHistoryDTO ���β���������
	 */
	public TdItemInfoHistoryModel(SfUserDTO userAccount,
								   TdItemInfoHistoryDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TdItemInfoHistoryDTO dto = (TdItemInfoHistoryDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_ITEM_INFO_HISTORY("
						+ " HISTORY_ID,"
						+ " BARCODE,"
						+ " ADDRESS_ID,"
						+ " ITEM_CODE,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_DEPT,"
						+ " ORDER_NO,"
						+ " ORDER_CATEGORY,"
						+ " ORDER_DTL_URL,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " REMARK"
						+ ") VALUES ("
						+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, GETDATE() , ?, ?)";

		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getAddressId());
		sqlArgs.add(dto.getItemCode());
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getOrderNo());
		sqlArgs.add(dto.getOrderCategory());
		sqlArgs.add(dto.getOrderDtlUrl());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getRemark());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcode �����ѯ����SQL��
	 * ����Զ����������豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param barcode String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataByBarcodeModel(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AIIH.BARCODE,"
						+ " dbo.NVL(TMP_1.ITEM_CATEGORY_NAME, 'δ�䶯') ITEM_CATEGORY_NAME,"
						+ " dbo.NVL(TMP_1.ITEM_NAME, 'δ�䶯') ITEM_NAME,"
						+ " dbo.NVL(TMP_1.ITEM_SPEC, 'δ�䶯') ITEM_SPEC,"
						+ " dbo.NVL(AME.USER_NAME, 'δ�䶯') RESPONSIBILITY_USER_NAME,"
						+ " dbo.NVL(AMD.DEPT_NAME, 'δ�䶯') RESPONSIBILITY_DEPT_NAME,"
						+ " dbo.NVL(EO.WORKORDER_OBJECT_NAME, 'δ�䶯') WORKORDER_OBJECT_NAME,"
						+ " dbo.NVL(EO.WORKORDER_OBJECT_CODE, 'δ�䶯') ADDRESS_NO,"
//						+ " dbo.NVL(AOA.ADDRESS_NO, 'δ�䶯') ADDRESS_NO,"
						+ " AIIH.CREATION_DATE,"
						+ " AIIH.ORDER_NO,"
						+ " AIIH.ORDER_DTL_URL"
						+ " FROM"
						+ " AMS_ITEM_INFO_HISTORY AIIH,"
						+ " (SELECT"
						+ " ESI.ITEM_CODE,"
						+ " EFV.VALUE ITEM_CATEGORY_NAME,"
						+ " ESI.ITEM_NAME,"
						+ " ESI.ITEM_SPEC"
						+ " FROM"
						+ " ETS_SYSTEM_ITEM    ESI,"
						+ " ETS_FLEX_VALUES    EFV,"
						+ " ETS_FLEX_VALUE_SET EFVS"
						+ " WHERE"
						+ " ESI.ITEM_CATEGORY = EFV.CODE"
						+ " AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFVS.CODE = 'ITEM_TYPE') TMP_1,"
						+ " AMS_OBJECT_ADDRESS    AOA,"
						+ " ETS_OBJECT            EO,"
						+ " AMS_MIS_EMPLOYEE      AME,"
						+ " AMS_MIS_DEPT          AMD"
						+ " WHERE"
						+ " AIIH.ITEM_CODE *= TMP_1.ITEM_CODE"
						+ " AND AIIH.ADDRESS_ID *= AOA.ADDRESS_ID"
						+ " AND AOA.OBJECT_NO *= EO.WORKORDER_OBJECT_NO"
						+ " AND AIIH.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID"
						+ " AND AIIH.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE"
						+ " AND AIIH.BARCODE = ?"
						+ " ORDER BY"
						+ " AIIH.CREATION_DATE DESC";
		sqlArgs.add(barcode);

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
		TdItemInfoHistoryDTO dto = (TdItemInfoHistoryDTO) dtoParameter;
		if (foreignKey.equals("barcode")) {
			sqlModel = getDataByBarcodeModel(dto.getBarcode());
		}
		return sqlModel;
	}
}
