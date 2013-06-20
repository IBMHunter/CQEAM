package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.ArrUtil;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: AdminConfirmModel</p>
 * <p>Description:�����Զ�����SQL��������AdminConfirmModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 */

public class AATConfirmYNModel extends AMSSQLProducer {

	private String confirmYN;


	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ������ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransLineDTO ���β���������
	 */
	public AATConfirmYNModel(SfUserDTO userAccount, AmsAssetsTransLineDTO dtoParameter, String confirmYN) {
		super(userAccount, dtoParameter);
		this.confirmYN = confirmYN;
	}

	/**
	 * ���ܣ������ȡ���˴�ȷ���ʲ���SQL
	 * @throws SQLModelException
	 * @return SQLModel
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			String deptCode = "('";
			DTOSet depts = userAccount.getPriviDeptCodes();
			if (depts != null && depts.getSize() > 0) {
				AmsMisDeptDTO dept = null;
				int deptCount = depts.getSize();
				for (int i = 0; i < deptCount; i++) {
					dept = (AmsMisDeptDTO) depts.getDTO(i);
					deptCode += dept.getDeptCode() + "', '";
				}
			}
			deptCode += "')";
			AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
			String sqlStr = "SELECT"
							+ " AATH.TRANS_ID,"
							+ " AATH.TRANS_NO,"
							+ " AATH.TRANSFER_TYPE,"
							+ " EFA.ASSET_NUMBER,"
							+ " EFA.ASSETS_DESCRIPTION,"
							+ " EFA.MODEL_NUMBER,"
							+ " ISNULL(EFA.CURRENT_UNITS, 1) CURRENT_UNITS,"
							+ " EFA.COST,"
							+ " EFA.DEPRN_COST,"
							+ " EFA.DATE_PLACED_IN_SERVICE,"
							+ " AATL.BARCODE,"
							+ " dbo.NVL(AATL.NEW_BARCODE, AATL.BARCODE) NEW_BARCODE,"
							+ " EOO.WORKORDER_OBJECT_NO OLD_LOCATION,"
							+ " EOO.WORKORDER_OBJECT_NAME OLD_LOCATION_NAME,"
							+ " AMEO.EMPLOYEE_ID OLD_RESPONSIBILITY_USER,"
							+ " AMEO.USER_NAME OLD_RESPONSIBILITY_USER_NAME,"
							+ " AMDO.DEPT_CODE OLD_RESPONSIBILITY_DEPT,"
							+ " AMDO.DEPT_NAME OLD_RESPONSIBILITY_DEPT_NAME,"
							+ " EON.WORKORDER_OBJECT_NO ASSIGNED_TO_LOCATION,"
							+ " EON.WORKORDER_OBJECT_NAME ASSIGNED_TO_LOCATION_NAME,"
							+ " AMEN.EMPLOYEE_ID RESPONSIBILITY_USER,"
							+ " AMEN.USER_NAME RESPONSIBILITY_USER_NAME,"
							+ " AMDN.DEPT_CODE RESPONSIBILITY_DEPT,"
							+ " AMDN.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
							+ " AOAO.ADDRESS_ID OLD_ADDRESS_ID,"
							+ " AOAN.ADDRESS_ID,"
							+ " AATH.FROM_ORGANIZATION_ID,"
							+ " AATH.TO_ORGANIZATION_ID,"
							+ " AATL.REMARK," ;
			
							if ("Y".equals(confirmYN)) { //��ȷ��
								sqlStr=sqlStr
								+ " '��ȷ��' CONFIRM_YN " ;
							} else {
								sqlStr=sqlStr
								+ " 'δȷ��' CONFIRM_YN " ;
							}
							sqlStr=sqlStr
							+ " FROM"
							+ " ETS_ITEM_INFO           EII,";
							
							if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
								
								sqlStr=sqlStr+ " ETS_ITEM_MATCH_TD          EIM, ETS_FA_ASSETS_TD        EFA,";
							} else {
								sqlStr=sqlStr+ " ETS_ITEM_MATCH          EIM, ETS_FA_ASSETS           EFA,";
							}
							sqlStr=sqlStr
							+ " AMS_ASSETS_TRANS_HEADER AATH,"
							+ " AMS_MIS_EMPLOYEE        AMEO,"
							+ " AMS_MIS_DEPT            AMDO,"
							+ " ETS_OBJECT              EOO,"
							+ " AMS_OBJECT_ADDRESS      AOAO,"
							+ " AMS_ASSETS_TRANS_LINE   AATL,"
							+ " AMS_MIS_EMPLOYEE        AMEN,"
							+ " AMS_MIS_DEPT            AMDN,"
							+ " ETS_OBJECT              EON,"
							+ " AMS_OBJECT_ADDRESS      AOAN"
							+ " WHERE"
							+ " EFA.ASSET_ID = EIM.ASSET_ID"
							+ " AND EIM.SYSTEMID = EII.SYSTEMID"
							+ " AND EII.BARCODE = AATL.BARCODE"
							+ " AND AATL.OLD_LOCATION *= EOO.WORKORDER_OBJECT_NO"
							+ " AND EOO.WORKORDER_OBJECT_NO *= AOAO.OBJECT_NO"
							+ " AND AATL.OLD_RESPONSIBILITY_USER *= AMEO.EMPLOYEE_ID"
							+ " AND AATL.OLD_RESPONSIBILITY_DEPT *= AMDO.DEPT_CODE"
							+ " AND AATL.ASSIGNED_TO_LOCATION = EON.WORKORDER_OBJECT_NO"
							+ " AND EON.WORKORDER_OBJECT_NO = AOAN.OBJECT_NO"
							+ " AND AATL.RESPONSIBILITY_USER = AMEN.EMPLOYEE_ID"
							+ " AND AATL.RESPONSIBILITY_DEPT = AMDN.DEPT_CODE"
							+ " AND AATL.TRANS_ID = AATH.TRANS_ID" ;
							
							if ("Y".equals(confirmYN)) { //��ȷ��
								sqlStr=sqlStr
								+ " AND AATH.TRANS_STATUS = 'CONFIRMD' \n" ;
							} else {
								sqlStr=sqlStr
								+ " AND ((AATL.LINE_STATUS = ? AND AATH.TRANSFER_TYPE = ?) OR (AATL.LINE_STATUS = ? OR LTRIM(AATL.LINE_STATUS) IS NULL AND AATH.TRANSFER_TYPE <> ?)) \n" 
								//+ " AND AATL.CONFIRM_DATE = NULL" ;
								+ "AND AATH.TRANS_STATUS <>  'CONFIRMD' \n";
							}

							sqlStr=sqlStr
							//+ " AND AATL.RESPONSIBILITY_DEPT IN " + deptCode
                            + " AND ( " + SyBaseSQLUtil.isNull() + " OR EFA.ASSETS_DESCRIPTION LIKE ?)"
                            + " AND ( " + SyBaseSQLUtil.isNull() + " OR AATH.TRANS_NO LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + " OR AATH.APPROVED_DATE >= ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + " OR AATH.APPROVED_DATE <= ?)"
							+ " ORDER BY AATH.TRANS_NO DESC";

			if ("Y".equals(confirmYN)) { //��ȷ��
				
			} else {
				sqlArgs.add(AssetsDictConstant.APPROVED);
				sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);
				sqlArgs.add(AssetsDictConstant.ORDER_STS_ASSIGNED);
				sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);
			}
			/*sqlArgs.add(AssetsDictConstant.APPROVED);
			sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);
			sqlArgs.add(AssetsDictConstant.ORDER_STS_ASSIGNED);
			sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);*/
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getSQLEndDate());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}


	/**
	 * ���ܣ�����Զ����ɹ̶��ʲ���ǰ��Ϣ(EAM) AMS_FA_ASSETS����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param checkedAssets ѡ�е��ʲ�
	 * @return SQLModel �����ʲ�������SQL
	 * @throws SQLModelException
	 */
	public SQLModel getExpCheckedAssetsModel(String[] checkedAssets) throws SQLModelException {
		SQLModel sqlModel = getPageQueryModel();
		String barcodes = ArrUtil.arrToSqlStr(checkedAssets);
		String sqlStr = sqlModel.getSqlStr();
		sqlStr = "SELECT * FROM (" + sqlStr +
				 ") TMP_V WHERE TMP_V.BARCODE IN (" + barcodes + ")";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
	
	/**
	 * ���ܣ������ȡ���˴�ȷ���ʲ���SQL
	 * @throws SQLModelException
	 * @return SQLModel
	 */
	public SQLModel getPageQueryModel2(String confirmYN) throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			String deptCode = "('";
			DTOSet depts = userAccount.getPriviDeptCodes();
			if (depts != null && depts.getSize() > 0) {
				AmsMisDeptDTO dept = null;
				int deptCount = depts.getSize();
				for (int i = 0; i < deptCount; i++) {
					dept = (AmsMisDeptDTO) depts.getDTO(i);
					deptCode += dept.getDeptCode() + "', '";
				}
			}
			deptCode += "')";
			AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
			String sqlStr = "SELECT"
							+ " AATH.TRANS_ID,"
							+ " AATH.TRANS_NO,"
							+ " AATH.TRANSFER_TYPE,"
							+ " EFA.ASSET_NUMBER,"
							+ " EFA.ASSETS_DESCRIPTION,"
							+ " EFA.MODEL_NUMBER,"
							+ " ISNULL(EFA.CURRENT_UNITS, 1) CURRENT_UNITS,"
							+ " EFA.COST,"
							+ " EFA.DEPRN_COST,"
							+ " EFA.DATE_PLACED_IN_SERVICE,"
							+ " AATL.BARCODE,"
							+ " dbo.NVL(AATL.NEW_BARCODE, AATL.BARCODE) NEW_BARCODE,"
							+ " EOO.WORKORDER_OBJECT_NO OLD_LOCATION,"
							+ " EOO.WORKORDER_OBJECT_NAME OLD_LOCATION_NAME,"
							+ " AMEO.EMPLOYEE_ID OLD_RESPONSIBILITY_USER,"
							+ " AMEO.USER_NAME OLD_RESPONSIBILITY_USER_NAME,"
							+ " AMDO.DEPT_CODE OLD_RESPONSIBILITY_DEPT,"
							+ " AMDO.DEPT_NAME OLD_RESPONSIBILITY_DEPT_NAME,"
							+ " EON.WORKORDER_OBJECT_NO ASSIGNED_TO_LOCATION,"
							+ " EON.WORKORDER_OBJECT_NAME ASSIGNED_TO_LOCATION_NAME,"
							+ " AMEN.EMPLOYEE_ID RESPONSIBILITY_USER,"
							+ " AMEN.USER_NAME RESPONSIBILITY_USER_NAME,"
							+ " AMDN.DEPT_CODE RESPONSIBILITY_DEPT,"
							+ " AMDN.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
							+ " AOAO.ADDRESS_ID OLD_ADDRESS_ID,"
							+ " AOAN.ADDRESS_ID,"
							+ " AATH.FROM_ORGANIZATION_ID,"
							+ " AATH.TO_ORGANIZATION_ID,"
							+ " AATL.REMARK," ;
			
							if ("Y".equals(confirmYN)) { //��ȷ��
								sqlStr=sqlStr
								+ " '��ȷ��' CONFIRM_YN " ;
							} else {
								sqlStr=sqlStr
								+ " 'δȷ��' CONFIRM_YN " ;
							}
							sqlStr=sqlStr
							+ " FROM"
							+ " ETS_ITEM_INFO           EII,";
							
							if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
								
								sqlStr=sqlStr+ " ETS_ITEM_MATCH_TD          EIM, ETS_FA_ASSETS_TD        EFA,";
							} else {
								sqlStr=sqlStr+ " ETS_ITEM_MATCH          EIM, ETS_FA_ASSETS           EFA,";
							}
							sqlStr=sqlStr
							+ " AMS_ASSETS_TRANS_HEADER AATH,"
							+ " AMS_MIS_EMPLOYEE        AMEO,"
							+ " AMS_MIS_DEPT            AMDO,"
							+ " ETS_OBJECT              EOO,"
							+ " AMS_OBJECT_ADDRESS      AOAO,"
							+ " AMS_ASSETS_TRANS_LINE   AATL,"
							+ " AMS_MIS_EMPLOYEE        AMEN,"
							+ " AMS_MIS_DEPT            AMDN,"
							+ " ETS_OBJECT              EON,"
							+ " AMS_OBJECT_ADDRESS      AOAN"
							+ " WHERE"
							+ " EFA.ASSET_ID = EIM.ASSET_ID"
							+ " AND EIM.SYSTEMID = EII.SYSTEMID"
							+ " AND EII.BARCODE = AATL.BARCODE"
							+ " AND AATL.OLD_LOCATION *= EOO.WORKORDER_OBJECT_NO"
							+ " AND EOO.WORKORDER_OBJECT_NO *= AOAO.OBJECT_NO"
							+ " AND AATL.OLD_RESPONSIBILITY_USER *= AMEO.EMPLOYEE_ID"
							+ " AND AATL.OLD_RESPONSIBILITY_DEPT *= AMDO.DEPT_CODE"
							+ " AND AATL.ASSIGNED_TO_LOCATION = EON.WORKORDER_OBJECT_NO"
							+ " AND EON.WORKORDER_OBJECT_NO = AOAN.OBJECT_NO"
							+ " AND AATL.RESPONSIBILITY_USER = AMEN.EMPLOYEE_ID"
							+ " AND AATL.RESPONSIBILITY_DEPT = AMDN.DEPT_CODE"
							+ " AND AATL.TRANS_ID = AATH.TRANS_ID" ;
							
							if ("Y".equals(confirmYN)) { //��ȷ��
								sqlStr=sqlStr
								+ " AND AATH.TRANS_STATUS = 'CONFIRMD' \n" ;
							} else {
								sqlStr=sqlStr
								+ " AND ((AATL.LINE_STATUS = ? AND AATH.TRANSFER_TYPE = ?) OR (AATL.LINE_STATUS = ? OR LTRIM(AATL.LINE_STATUS) IS NULL AND AATH.TRANSFER_TYPE <> ?)) \n" 
								//+ " AND AATL.CONFIRM_DATE = NULL" ;
								+ "AND AATH.TRANS_STATUS <>  'CONFIRMD' \n";
							}

							sqlStr=sqlStr
							//+ " AND AATL.RESPONSIBILITY_DEPT IN " + deptCode
                            + " AND ( " + SyBaseSQLUtil.isNull() + " OR EFA.ASSETS_DESCRIPTION LIKE ?)"
                            + " AND ( " + SyBaseSQLUtil.isNull() + " OR AATH.TRANS_NO LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + " OR AATH.APPROVED_DATE >= ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + " OR AATH.APPROVED_DATE <= ?)"
							+ " ORDER BY AATH.TRANS_NO DESC";

			if ("Y".equals(confirmYN)) { //��ȷ��
				
			} else {
				sqlArgs.add(AssetsDictConstant.APPROVED);
				sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);
				sqlArgs.add(AssetsDictConstant.ORDER_STS_ASSIGNED);
				sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);
			}
			/*sqlArgs.add(AssetsDictConstant.APPROVED);
			sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);
			sqlArgs.add(AssetsDictConstant.ORDER_STS_ASSIGNED);
			sqlArgs.add(AssetsDictConstant.TRANS_INN_DEPT);*/
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getSQLEndDate());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}
	
}