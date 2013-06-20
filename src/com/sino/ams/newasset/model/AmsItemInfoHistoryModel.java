package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: AmsItemInfoHistoryModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemInfoHistoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsItemInfoHistoryModel extends AMSSQLProducer {

	/**
	 * ���ܣ��豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemInfoHistoryDTO ���β���������
	 */
	public AmsItemInfoHistoryModel(SfUserDTO userAccount,
								   AmsItemInfoHistoryDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemInfoHistoryDTO dto = (AmsItemInfoHistoryDTO) dtoParameter;
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
						+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?)";

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

       String sqlStr= "SELECT AIIH.BARCODE,\n" +
        "       AIIH.ITEM_CATEGORY_NAME,\n" +
        "       AIIH.ITEM_NAME,\n" +
        "       AIIH.ITEM_SPEC,\n" +
        "       AIIH.RESPONSIBILITY_USER_NAME,\n" +
        "       AIIH.RESPONSIBILITY_DEPT_NAME,\n" +
        "       AIIH.WORKORDER_OBJECT_NAME,\n" +
        "       AIIH.WORKORDER_OBJECT_CODE ADDRESS_NO,\n" +
        "       AIIH.CREATION_DATE,\n" +
        "       AIIH.ORDER_NO,\n" +
        "       AIIH.ORDER_DTL_URL\n" +
        "  FROM AMS_ITEM_INFO_HISTORY AIIH, SF_USER SU\n" +
        " WHERE AIIH.CREATED_BY = SU.USER_ID\n" +
        "   AND AIIH.BARCODE = ?\n" +
        " ORDER BY AIIH.CREATION_DATE DESC";

		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        AmsItemInfoHistoryDTO dto = (AmsItemInfoHistoryDTO) dtoParameter;
        String sqlStr = "SELECT"
                + " EII.BARCODE,"
                + " ESI.ITEM_CODE,"
                + " ESI.ITEM_CATEGORY,"
                + " dbo.APP_GET_FLEX_VALUE(ESI.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY_NAME,"
                + " ESI.ITEM_NAME,"
                + " ESI.ITEM_SPEC,"
                + " ESI.YEARS,"
                + " ESI.ITEM_UNIT,"
                + " EII.START_DATE,"
                + " EO.WORKORDER_OBJECT_CODE,"
                + " EO.WORKORDER_OBJECT_NAME,"
                + " EO.WORKORDER_OBJECT_LOCATION,"
                + " EC.COUNTY_CODE,"
                + " EC.COUNTY_NAME,"
                + " AME.USER_NAME RESPONSIBILITY_USER_NAME,"
                + " AME.EMPLOYEE_NUMBER,"
                + " AMD.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
                + " EPPA.NAME PROJECT_NAME,"
                + " EPPA.SEGMENT1 PROJECT_NUMBER,"
                + " EMPV.VENDOR_NAME,"
                + " EMPV.SEGMENT1 VENDOR_NUMBER,"
                + " EII.FINANCE_PROP,"
                + " dbo.APP_GET_FLEX_VALUE(EII.FINANCE_PROP, 'FINANCE_PROP') FINANCE_PROP_VALUE,"
                + " EII.RESPONSIBILITY_USER,"
                + " EII.RESPONSIBILITY_DEPT,"
                + " EII.MAINTAIN_USER,"
                + " EII.MAINTAIN_DEPT,"
                + " EII.ADDRESS_ID,"
                + " EOCM.COMPANY_CODE,"
                + " EOCM.COMPANY"
                + " FROM"
                + " ETS_ITEM_INFO          EII,"
                + " ETS_SYSTEM_ITEM        ESI,"
                + " AMS_OBJECT_ADDRESS     AOA,"
                + " ETS_OBJECT             EO,"
                + " ETS_COUNTY             EC,"
                + " ETS_OU_CITY_MAP        EOCM,"
                + " AMS_MIS_DEPT           AMD,"
                + " AMS_MIS_EMPLOYEE       AME,"
                + " ETS_PA_PROJECTS_ALL    EPPA,"
                + " ETS_MIS_PO_VENDORS     EMPV"
                + " WHERE"
                + " EII.ITEM_CODE = ESI.ITEM_CODE"
                + " AND ESI.VENDOR_ID *= EMPV.VENDOR_ID"
                + " AND EII.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                + " AND EII.ADDRESS_ID = AOA.ADDRESS_ID"
                + " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO"
                + " AND EO.COUNTY_CODE *= EC.COUNTY_CODE"
                + " AND EII.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID"
                + " AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE"
                + " AND EII.PROJECTID *= EPPA.PROJECT_ID"
                + " AND EII.BARCODE = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(dto.getBarcode());        
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
		AmsItemInfoHistoryDTO dto = (AmsItemInfoHistoryDTO) dtoParameter;
		if (foreignKey.equals("barcode")) {
			sqlModel = getDataByBarcodeModel(dto.getBarcode());
		}
		return sqlModel;
	}
}
