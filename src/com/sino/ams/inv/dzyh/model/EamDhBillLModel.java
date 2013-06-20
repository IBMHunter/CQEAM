package com.sino.ams.inv.dzyh.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.inv.dzyh.dto.EamDhBillLDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;

/**
 * <p>
 * Title: EamDhBillLModel
 * </p>
 * <p>
 * Description:�����Զ�����SQL��������EamDhBillLModel�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ��ʿ��
 * @version 1.0
 */
public class EamDhBillLModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ֵ�׺�Ʒ����(EAM) EAM_DH_BILL_L ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            EamDhBillLDTO ���β���������
	 */
	public EamDhBillLModel(SfUserDTO userAccount, EamDhBillLDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ���ֵ�׺�Ʒ����-̨�˳����ѯ���ܡ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "SELECT" 
				+ " EII.SYSTEMID," 
				+ " EII.ORGANIZATION_ID,"
				+ " EDCV.CATALOG_VALUE_ID," 
				+ " ESI.ITEM_CATEGORY,"
				+ " ESI.ITEM_CATEGORY2,"
				+ " EII.BARCODE," 
				+ " ESI.ITEM_NAME," 
				+ " ESI.ITEM_SPEC,"
				+ " EII.ITEM_QTY," 
				+ " EII.ITEM_CODE," 
				+ " EII.PRICE,"
				+ " EII.RESPONSIBILITY_DEPT," 
				+ " AMD.DEPT_CODE,"
				+ " AMD.DEPT_NAME," 
				+ " AMD.COMPANY_CODE,"
				+ " EII.RESPONSIBILITY_USER," 
				+ " AME.EMPLOYEE_ID,"
				+ " AME.EMPLOYEE_NUMBER," 
				+ " AME.USER_NAME,"
				+ " EII.ADDRESS_ID," 
				+ " EO.WORKORDER_OBJECT_NAME,"
				+ " EO.WORKORDER_OBJECT_CODE,"
				+ " EO.WORKORDER_OBJECT_NO,"
				+ " EII.MAINTAIN_USER,"
				+ " EII.LAST_LOC_CHG_DATE,"
				+ " EII.ATTRIBUTE3,"
				+ " EII.REMARK"
				+ " FROM"
				+ " ETS_SYSTEM_ITEM ESI,"
				+ " ETS_ITEM_INFO EII,"
				+ " AMS_MIS_DEPT AMD,"
				+ " ETS_OBJECT EO,"
				+ " AMS_OBJECT_ADDRESS AOA,"
				+ " EAM_DH_CATALOG_VALUES EDCV,"
				+ " AMS_MIS_EMPLOYEE AME"
				+ " WHERE"
				+ " ESI.ITEM_CODE=EII.ITEM_CODE"
				+ " AND EII.RESPONSIBILITY_DEPT=AMD.DEPT_CODE"
				+ " AND EII.ADDRESS_ID=AOA.ADDRESS_ID"
				+ " AND AOA.BOX_NO='0000'"
				+ " AND AOA.NET_UNIT='0000'"
				+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
				+ " AND EII.RESPONSIBILITY_USER=AME.EMPLOYEE_ID"
				+ " AND ESI.ITEM_CATEGORY2=EDCV.CATALOG_CODE"
				// + " AND EO.OBJECT_CATEGORY NOT IN(71,72,73,74,75)"
				//+ " AND EO.OBJECT_CATEGORY = 99"
				+ " AND EO.BUSINESS_CATEGORY = 'INV_BIZ_DZYH'"
				+ " AND EII.FINANCE_PROP = 'DZYH'"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EO.WORKORDER_OBJECT_NO LIKE ?)"

				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EDCV.CATALOG_VALUE_ID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)"
				+ " AND EII.ORGANIZATION_ID = ?";

		sqlArgs.add(dto.getWorkorderObjectNo());
		sqlArgs.add(dto.getWorkorderObjectNo());
		
		sqlArgs.add(dto.getCatalogValueId());
		sqlArgs.add(dto.getCatalogValueId());

		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemName());

		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��̵��¼
	 * ETS_ITEM_INFO���ݸ���SQLModel������EAM_DH_BILL_L���е�MAINTAIN_USER�ֶΡ�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "UPDATE EAM_DH_BILL_L" + " SET"
				+ " MAINTAIN_USER = ?" + " WHERE"
				+ " BILL_LINE_ID = ?";

		sqlArgs.add(dto.getMaintainUser());
		sqlArgs.add(dto.getBillLineId());
		// sqlArgs.add(userAccount.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����ETS_ITEM_INFO���е�RESPONSIBILITY_DEPT�ֶ�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateDeptModel(String barcode, String newDept) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		// EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_ITEM_INFO" + " SET"
				+ " RESPONSIBILITY_DEPT = ?" + " WHERE"
				+ " BARCODE = ?";

		sqlArgs.add(newDept);
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����ETS_ITEM_INFO���е�RESPONSIBILITY_USER�ֶ�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateUserModel(String barcode,
			String responsibilityUser) {

		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_ITEM_INFO" + " SET"
				+ " RESPONSIBILITY_USER = ?" + " WHERE"
				+ " BARCODE = ?";

		sqlArgs.add(responsibilityUser);
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����ETS_ITEM_INFO���е�WORKORDER_OBJECT_NO�ֶ�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateAddressIdModel(String barcode, String addressId) {

		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_ITEM_INFO" + " SET"
				+ " ADDRESS_ID = ?" + " WHERE" + " BARCODE = ?";

		sqlArgs.add(addressId);
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����ETS_ITEM_INFO���е�LAST_LOC_CHG_DATE�ֶΣ�����ֶ����������ͣ���Ҫע�⣬�������Ĳ�����String���͵�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws CalendarException
	 */
	public SQLModel getDataUpdateLastLocChgDateModel(String barcode,
			String lastLocChgDate) throws CalendarException {

		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_ITEM_INFO" + " SET"
				+ " LAST_LOC_CHG_DATE = ?" + " WHERE" + " BARCODE = ?";

		dto.setLastLocChgDate(lastLocChgDate);
		sqlArgs.add(dto.getLastLocChgDate());
		// sqlArgs.add(lastLocChgDate);
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;

	}

	/**
	 * ���ܣ�����ETS_ITEM_INFO���е�MAINTAIN_USER�ֶΣ�����ֶ����������ͣ���Ҫע�⣬�������Ĳ�����String���͵�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateMaintainUserModel(String barcode,
			String maintainUser) {

		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillLDTO dto = (EamDhBillLDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_ITEM_INFO " + " SET"
				+ " MAINTAIN_USER = ?" + " WHERE" + " BARCODE = ?";

		sqlArgs.add(maintainUser);
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	public int createInvBillHeaderOut(Connection conn, String transNo,
			String objCode, String transType, String itemType, int userid)
			throws SQLException {
		int nRet = -1;
		int retValue = 0;
		String sqlStr = "";
		sqlStr = "BEGIN ? := EAM_INV_OPERATE.CREATE_BILL_HEADER(?,?,?,?,?,?,?); END;";

		CallableStatement cStmt = null;
		try {
			cStmt = conn.prepareCall(sqlStr);
			cStmt.registerOutParameter(1, Types.NUMERIC);
			cStmt.setString(2, transNo);
			cStmt.setString(3, objCode);
			cStmt.setString(4, transType);
			cStmt.setString(5, itemType);
			cStmt.setInt(6, userid);
			cStmt.registerOutParameter(7, Types.VARCHAR);
			cStmt.registerOutParameter(8, Types.NUMERIC);

			Logger.logInfo(sqlStr);
			Logger.logInfo("[2]" + transNo + "[3]" + objCode + "[4]"
					+ transType + "[5]" + itemType + "[6]"
					+ String.valueOf(userid));
			cStmt.execute();

			retValue = cStmt.getInt(1);
			nRet = cStmt.getInt(8);

			if (retValue == 0)
				Logger.logError(cStmt.getString(7));
			
			
		} finally {
			DBManager.closeDBStatement(cStmt);
		}

		return nRet;
	}

	public int createInvBillLineOut(Connection conn, int bill_id, String barcode, String itemCode, String itemCategory,
	// String transType,String itemType,
			String itemCategory2, String deptCode, String employeeId, String maintainUser, String workorderObjectNo) 
	throws SQLException {
		
		String sqlStr = "";
		sqlStr = "BEGIN ? := EAM_INV_OPERATE.DEAL_WITH_BILL_LINE_OUT(?,?,?,?,?,?,?,?,?,?,?,?); END;";
		
		CallableStatement cst = null;
		
		int success = 0;
		
		try {
			cst = conn.prepareCall(sqlStr);

			cst.registerOutParameter(1, Types.INTEGER);
        	cst.setInt(2, bill_id);
        	cst.setString(3, barcode);
        	cst.setInt(4, StrUtil.strToInt(itemCode));
        	cst.setString(5, itemCategory);
        	cst.setString(6, itemCategory2);
        	
        	cst.setInt(7, StrUtil.strToInt(deptCode));
        	cst.setInt(8, StrUtil.strToInt(employeeId));
        	cst.setInt(9, userAccount.getUserId());
        	cst.setString(10, maintainUser);
        	
        	cst.setInt(11, StrUtil.strToInt(workorderObjectNo));
        	
        	cst.setInt(12, userAccount.getOrganizationId());
        	
        	cst.registerOutParameter(13, Types.VARCHAR);
        	
        	Logger.logInfo(sqlStr);
        	cst.execute();
			
			success = cst.getInt(1);
			
			if (success == 0)
				Logger.logError(cst.getString(13));
			
			
		}  finally {
			DBManager.closeDBStatement(cst);
		}

		return success;
	}
}
