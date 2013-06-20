package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.ArrUtil;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>
 * Title: AmsFaAssetsModel
 * </p>
 * <p>
 * Description:�����Զ�����SQL��������AmsFaAssetsModel�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ����ʤ
 * @version 1.0
 */

public class EtsFaAssetsModel extends AMSSQLProducer {

	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            AmsAssetsAddressVDTO ���β���������
	 */
	public EtsFaAssetsModel(SfUserDTO userAccount,
			AmsAssetsAddressVDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɹ̶��ʲ���ǰ��Ϣ(EAM) AMS_FA_ASSETS����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @param checkedAssets
	 *            ѡ�е��ʲ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getExpCheckedAssetsModel(String[] checkedAssets) {
		SQLModel sqlModel = getPageQueryModel();
		String barcodes = ArrUtil.arrToSqlStr(checkedAssets);
		String sqlStr = sqlModel.getSqlStr();
		sqlStr = "SELECT * FROM (" + sqlStr
				+ ") TMP_V WHERE TMP_V.BARCODE IN (" + barcodes + ")";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * �����ʲ��Ƿ�����TD�ʲ�
	 * 
	 * @return
	 */
	public SQLModel getIsTDModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EOCM.IS_TD\n"
				+ "  FROM ETS_OU_CITY_MAP EOCM, ETS_ITEM_INFO EII\n"
				+ " WHERE EII.ORGANIZATION_ID = EOCM.ORGANIZATION_ID\n"
				+ "   AND EII.BARCODE = ?";

		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ����ݱ�ǩ�Ż�ȡ�豸��ϸ��ϢSQL
	 * 
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPrimaryKeyDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		StringBuilder sql = new StringBuilder();
		if (dto.getIsTD().equals("Y")) {
//			sqlStr = "SELECT" + " AAAV.*" + " FROM"
//					+ " TD_ASSETS_ADDRESS_V AAAV" + " WHERE"
//					+ " AAAV.BARCODE = ?";
			sql.append( " SELECT \n " );
			sql.append( " AAAV.*, \n " );
			sql.append( " (CASE dbo.IS_IMPORTANT_ASSETS( AAAV.CONTENT_CODE )  WHEN 'Y' THEN '��' WHEN 'N' THEN '��' ELSE '' END ) IS_IMPORTANT_ASSETS \n " );
//			sql.append( " dbo.IS_IMPORTANT_ASSETS( AAAV.CONTENT_CODE ) IS_IMPORTANT_ASSETS \n " );
			sql.append( " FROM \n " );
			sql.append( " TD_ASSETS_ADDRESS_V_DETAIL AAAV \n " );
			sql.append( " WHERE \n " );
			sql.append( " AAAV.BARCODE = ? \n " );
		} else {
			sql.append( " SELECT \n " );
			sql.append( " AAAV.* , \n " );
			sql.append( " (CASE dbo.IS_IMPORTANT_ASSETS( AAAV.CONTENT_CODE )  WHEN 'Y' THEN '��' WHEN 'N' THEN '��' ELSE '' END ) IS_IMPORTANT_ASSETS \n " );
			sql.append( " FROM \n " );
			sql.append( " AMS_ASSETS_ADDRESS_V_DETAIL AAAV \n " );
			sql.append( " WHERE \n " );
			sql.append( " AAAV.BARCODE = ? \n " );
			
//			sqlStr = "SELECT" + " AAAV.*" + " FROM"
//					+ " AMS_ASSETS_ADDRESS_V AAAV" + " WHERE"
//					+ " AAAV.BARCODE = ?";
		}
		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr( sql.toString() );
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ̶��ʲ���ǰ��Ϣ(EAM) AMS_FA_ASSETSҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = null;
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		String treeCategory = dto.getTreeCategory();
		if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PERSON)) { // �����ʲ�
			sqlModel = getPersonalAssetsModel();
		} else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_DEPART)) { // �����ʲ�
			sqlModel = getDeptAssetsModel();
		} else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_COMPAN)) { // ��˾�ʲ�
			sqlModel = getCompanyAssetsModel();
		} else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PROVIN)) { // ȫʡ�ʲ�
			sqlModel = getProvinceAssetsModel();
		} else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) { // ��ȷ���ʲ�
			sqlModel = getConfirmAssetsModel();
		} else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)) { // �����ʲ�
			sqlModel = getTransferAssetsModel();
		}
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ���˴�ȷ���ʲ���SQL
	 * 
	 * @return SQLModel
	 */
	private SQLModel getConfirmAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT"
					+ " AATL.BARCODE,"
					+ " dbo.NVL(AATL.NEW_BARCODE, AATL.BARCODE) NEW_BARCODE,"
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
					+ " AATL.REMARK"
					+ " FROM"
					+ " ETS_ITEM_INFO           EII,"
					+ " ETS_ITEM_MATCH_TD       EIM,"
					+ " ETS_FA_ASSETS_TD        EFA,"
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
					+ " AND AATL.TRANS_ID = AATH.TRANS_ID"
					+ " AND (AATL.LINE_STATUS = ? OR AATL.LINE_STATUS = ?) "
                    + " AND AATH.TRANS_TYPE = ?"
					+ " AND AATL.RESPONSIBILITY_USER = ?"
					+ " AND AATL.CONFIRM_DATE = NULL "
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AATH.FROM_DEPT = dbo.NVL(?, AATH.FROM_DEPT))"

					+ " AND EFA.FA_CATEGORY1 = dbo.NVL(?, EFA.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.FA_CATEGORY2 = dbo.NVL(?, EFA.FA_CATEGORY2))"

					+ " AND EFA.ASSETS_DESCRIPTION LIKE dbo.NVL(?, EFA.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.MODEL_NUMBER LIKE dbo.NVL(?, EFA.MODEL_NUMBER))"
					+ " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)"
					+ " AND EFA.ASSET_NUMBER LIKE dbo.NVL(?, EFA.ASSET_NUMBER)"
					+ " AND EXISTS(" + " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAR.TRANS_ID = AATL.TRANS_ID"
					+ " AND AAR.BARCODE = AATL.BARCODE)";
			sqlArgs.add(AssetsDictConstant.APPROVED);
			sqlArgs.add(AssetsDictConstant.ASSIGNED);
			sqlArgs.add(AssetsDictConstant.ASS_RED);
			sqlArgs.add(userAccount.getEmployeeId());
			sqlArgs.add(dto.getDeptCode());
			sqlArgs.add(dto.getDeptCode());

			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());

			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getAssetNumber());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT"
					+ " AATL.BARCODE,"
					+ " dbo.NVL(AATL.NEW_BARCODE, AATL.BARCODE) NEW_BARCODE,"
					//+ " AATL.NEW_BARCODE,"
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
					+ " AATL.REMARK"
					+ " FROM"
					+ " ETS_ITEM_INFO           EII,"
					+ " ETS_ITEM_MATCH          EIM,"
					+ " ETS_FA_ASSETS           EFA,"
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
					//+ " AND EII.BARCODE = AATL.BARCODE"
					+ " AND (EII.BARCODE = AATL.NEW_BARCODE OR EII.BARCODE = AATL.BARCODE)"
					+ " AND AATL.OLD_LOCATION *= EOO.WORKORDER_OBJECT_NO"
					+ " AND EOO.WORKORDER_OBJECT_NO *= AOAO.OBJECT_NO"
					+ " AND AATL.OLD_RESPONSIBILITY_USER *= AMEO.EMPLOYEE_ID"
					+ " AND AATL.OLD_RESPONSIBILITY_DEPT *= AMDO.DEPT_CODE"
					+ " AND AATL.ASSIGNED_TO_LOCATION = EON.WORKORDER_OBJECT_NO"
					+ " AND EON.WORKORDER_OBJECT_NO = AOAN.OBJECT_NO"
					+ " AND AATL.RESPONSIBILITY_USER = AMEN.EMPLOYEE_ID"
					+ " AND AATL.RESPONSIBILITY_DEPT = AMDN.DEPT_CODE"
					+ " AND AATL.TRANS_ID = AATH.TRANS_ID"
					+ " AND (AATL.LINE_STATUS = ? OR AATL.LINE_STATUS = ?) "
                    + " AND AATH.TRANS_TYPE = ?"
					+ " AND AATL.RESPONSIBILITY_USER = ?"
					+ " AND AATL.CONFIRM_DATE = NULL"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AATH.FROM_DEPT = dbo.NVL(?, AATH.FROM_DEPT))"

					+ " AND EFA.FA_CATEGORY1 = dbo.NVL(?, EFA.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.FA_CATEGORY2 = dbo.NVL(?, EFA.FA_CATEGORY2))"

					+ " AND EFA.ASSETS_DESCRIPTION LIKE dbo.NVL(?, EFA.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.MODEL_NUMBER LIKE dbo.NVL(?, EFA.MODEL_NUMBER))"
					//+ " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)"
					
					+ " AND (AATL.NEW_BARCODE LIKE dbo.NVL(?, AATL.NEW_BARCODE) OR EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE))"
					+ " AND EFA.ASSET_NUMBER LIKE dbo.NVL(?, EFA.ASSET_NUMBER)"
					+ " AND EXISTS(" 
					+ " 	SELECT" 
					+ " 	NULL" 
					+ " 	FROM"
					+ " 	AMS_ASSETS_RESERVED AAR" 
					+ " 	WHERE"
					+ " 	AAR.TRANS_ID = AATL.TRANS_ID"
					+ " 	AND AAR.BARCODE = AATL.BARCODE)";
			sqlArgs.add(AssetsDictConstant.APPROVED);
			sqlArgs.add(AssetsDictConstant.ASSIGNED);
			sqlArgs.add(AssetsDictConstant.ASS_RED);
			sqlArgs.add(userAccount.getEmployeeId());
			sqlArgs.add(dto.getDeptCode());
			sqlArgs.add(dto.getDeptCode());

			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());

			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getAssetNumber());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ�����ʲ���SQL
	 * 
	 * @return SQLModel
	 */
	private SQLModel getTransferAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT"
					+ " AATL.BARCODE,"
					+ " AATL.BARCODE NEW_BARCODE,"
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
					+ " EOO.WORKORDER_OBJECT_NO OLD_LOCATION,"
					+ " EOO.WORKORDER_OBJECT_NAME OLD_LOCATION_NAME,"
					+ " AOAO.ADDRESS_ID OLD_ADDRESS_ID,"
					+ " AMDO.DEPT_NAME OLD_RESPONSIBILITY_DEPT_NAME,"
					+ " AATL.OLD_RESPONSIBILITY_DEPT,"
					+ " AMEO.USER_NAME OLD_RESPONSIBILITY_USER_NAME,"
					+ " AATL.OLD_RESPONSIBILITY_USER,"
					+ " EON.WORKORDER_OBJECT_NO ASSIGNED_TO_LOCATION,"
					+ " EON.WORKORDER_OBJECT_NAME ASSIGNED_TO_LOCATION_NAME,"
					+ " AOAN.ADDRESS_ID ADDRESS_ID,"
					+ " AMDN.DEPT_CODE RESPONSIBILITY_DEPT,"
					+ " AMDN.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
					+ " AMEN.EMPLOYEE_ID RESPONSIBILITY_USER,"
					+ " AMEN.USER_NAME RESPONSIBILITY_USER_NAME,"
					+ " AATH.FROM_ORGANIZATION_ID,"
					+ " AATH.TO_ORGANIZATION_ID,"
					+ " AATL.REMARK"
					+ " FROM"
					+ " ETS_FA_ASSETS_TD           EFA,"
					+ " ETS_ITEM_MATCH_TD          EIM,"
					+ " ETS_ITEM_INFO           EII,"
					+ " AMS_ASSETS_TRANS_LINE   AATL,"
					+ " AMS_ASSETS_TRANS_HEADER AATH,"
					+ " AMS_MIS_EMPLOYEE        AMEO,"
					+ " AMS_MIS_DEPT            AMDO,"
					+ " ETS_OBJECT              EOO,"
					+ " AMS_OBJECT_ADDRESS      AOAO,"
					+ " AMS_MIS_EMPLOYEE        AMEN,"
					+ " AMS_MIS_DEPT            AMDN,"
					+ " ETS_OBJECT              EON,"
					+ " AMS_OBJECT_ADDRESS      AOAN"
					+ " WHERE"
					+ " EFA.ASSET_ID = EIM.ASSET_ID"
					+ " AND EIM.SYSTEMID = EII.SYSTEMID"
					+ " AND EII.BARCODE = AATL.BARCODE"
					+ " AND AATL.OLD_LOCATION = EOO.WORKORDER_OBJECT_NO"
					+ " AND EOO.WORKORDER_OBJECT_NO = AOAO.OBJECT_NO"
					+ " AND AATL.OLD_RESPONSIBILITY_USER = AMEO.EMPLOYEE_ID"
					+ " AND AATL.OLD_RESPONSIBILITY_DEPT = AMDO.DEPT_CODE"
					+ " AND AATL.RESPONSIBILITY_DEPT = AMDN.DEPT_CODE"
					+ " AND AATL.RESPONSIBILITY_USER = AMEN.EMPLOYEE_ID"
					+ " AND AATL.ASSIGNED_TO_LOCATION = EON.WORKORDER_OBJECT_NO"
					+ " AND EON.WORKORDER_OBJECT_NO = AOAN.ADDRESS_ID"
					+ " AND AATL.TRANS_ID = AATH.TRANS_ID"
					+ " AND AATH.TRANS_TYPE = ?"
					+ " AND (AATH.CREATED_BY = ? OR AATL.OLD_RESPONSIBILITY_USER = ?)"
					+ " AND AATH.FROM_ORGANIZATION_ID = ?"
					+ " AND EFA.FA_CATEGORY1 LIKE dbo.NVL(?, EFA.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.FA_CATEGORY2 LIKE dbo.NVL(?, EFA.FA_CATEGORY2))"
					+ " AND EFA.ASSETS_DESCRIPTION LIKE dbo.NVL(?, EFA.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.MODEL_NUMBER LIKE dbo.NVL(?, EFA.MODEL_NUMBER))"
					+ " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)"
					+ " AND EXISTS (" + " SELECT" + " NULL" + " FROM"
					+ " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
					+ " ESD.ITEM_CODE = EII.ITEM_CODE"
					+ " AND ESD.ORGANIZATION_ID = 87)" + " AND EXISTS ("
					+ " SELECT" + " NULL" + " FROM   AMS_ASSETS_RESERVED AAR"
					+ " WHERE  AAR.TRANS_ID = AATL.TRANS_ID"
					+ " AND AAR.BARCODE = AATL.BARCODE)";
//					+ " ORDER  BY AATH.TRANS_NO DESC";
			sqlArgs.add(AssetsDictConstant.ASS_RED);
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(userAccount.getEmployeeId());
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getBarcode());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT"
					+ " AATL.BARCODE,"
					+ " AATL.BARCODE NEW_BARCODE,"
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
					+ " EOO.WORKORDER_OBJECT_NO OLD_LOCATION,"
					+ " EOO.WORKORDER_OBJECT_NAME OLD_LOCATION_NAME,"
					+ " AOAO.ADDRESS_ID OLD_ADDRESS_ID,"
					+ " AMDO.DEPT_NAME OLD_RESPONSIBILITY_DEPT_NAME,"
					+ " AATL.OLD_RESPONSIBILITY_DEPT,"
					+ " AMEO.USER_NAME OLD_RESPONSIBILITY_USER_NAME,"
					+ " AATL.OLD_RESPONSIBILITY_USER,"
					+ " EON.WORKORDER_OBJECT_NO ASSIGNED_TO_LOCATION,"
					+ " EON.WORKORDER_OBJECT_NAME ASSIGNED_TO_LOCATION_NAME,"
					+ " AOAN.ADDRESS_ID ADDRESS_ID,"
					+ " AMDN.DEPT_CODE RESPONSIBILITY_DEPT,"
					+ " AMDN.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
					+ " AMEN.EMPLOYEE_ID RESPONSIBILITY_USER,"
					+ " AMEN.USER_NAME RESPONSIBILITY_USER_NAME,"
					+ " AATH.FROM_ORGANIZATION_ID,"
					+ " AATH.TO_ORGANIZATION_ID,"
					+ " AATL.REMARK"
					+ " FROM"
					+ " ETS_FA_ASSETS           EFA,"
					+ " ETS_ITEM_MATCH          EIM,"
					+ " ETS_ITEM_INFO           EII,"
					+ " AMS_ASSETS_TRANS_LINE   AATL,"
					+ " AMS_ASSETS_TRANS_HEADER AATH,"
					+ " AMS_MIS_EMPLOYEE        AMEO,"
					+ " AMS_MIS_DEPT            AMDO,"
					+ " ETS_OBJECT              EOO,"
					+ " AMS_OBJECT_ADDRESS      AOAO,"
					+ " AMS_MIS_EMPLOYEE        AMEN,"
					+ " AMS_MIS_DEPT            AMDN,"
					+ " ETS_OBJECT              EON,"
					+ " AMS_OBJECT_ADDRESS      AOAN"
					+ " WHERE"
					+ " EFA.ASSET_ID = EIM.ASSET_ID"
					+ " AND EIM.SYSTEMID = EII.SYSTEMID"
					+ " AND EII.BARCODE = AATL.BARCODE"
					+ " AND AATL.OLD_LOCATION = EOO.WORKORDER_OBJECT_NO"
					+ " AND EOO.WORKORDER_OBJECT_NO = AOAO.OBJECT_NO"
					+ " AND AATL.OLD_RESPONSIBILITY_USER = AMEO.EMPLOYEE_ID"
					+ " AND AATL.OLD_RESPONSIBILITY_DEPT = AMDO.DEPT_CODE"
					+ " AND AATL.RESPONSIBILITY_DEPT = AMDN.DEPT_CODE"
					+ " AND AATL.RESPONSIBILITY_USER = AMEN.EMPLOYEE_ID"
					+ " AND AATL.ASSIGNED_TO_LOCATION = EON.WORKORDER_OBJECT_NO"
					+ " AND EON.WORKORDER_OBJECT_NO = AOAN.ADDRESS_ID"
					+ " AND AATL.TRANS_ID = AATH.TRANS_ID"
					+ " AND AATH.TRANS_TYPE = ?"
					+ " AND (AATH.CREATED_BY = ? OR AATL.OLD_RESPONSIBILITY_USER = ?)"
					+ " AND AATH.FROM_ORGANIZATION_ID = ?"
					+ " AND EFA.FA_CATEGORY1 LIKE dbo.NVL(?, EFA.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.FA_CATEGORY2 LIKE dbo.NVL(?, EFA.FA_CATEGORY2))"
					+ " AND EFA.ASSETS_DESCRIPTION LIKE dbo.NVL(?, EFA.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.MODEL_NUMBER LIKE dbo.NVL(?, EFA.MODEL_NUMBER))"
					+ " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)"
					+ " AND EXISTS (" + " SELECT" + " NULL" + " FROM"
					+ " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
					+ " ESD.ITEM_CODE = EII.ITEM_CODE"
					+ " AND ESD.ORGANIZATION_ID = 87)" + " AND EXISTS ("
					+ " SELECT" + " NULL" + " FROM   AMS_ASSETS_RESERVED AAR"
					+ " WHERE  AAR.TRANS_ID = AATL.TRANS_ID"
					+ " AND AAR.BARCODE = AATL.BARCODE)";
//					+ " ORDER  BY AATH.CREATION_DATE DESC";
			sqlArgs.add(AssetsDictConstant.ASS_RED);
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(userAccount.getEmployeeId());
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getBarcode());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ�����ʲ���SQL
	 * 
	 * @return SQLModel
	 */
	private SQLModel getPersonalAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT "
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " TD_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.FA_CATEGORY1 = dbo.NVL(?, FA_CATEGORY1)"
					+ " AND AAAV.FA_CATEGORY2 = dbo.NVL(?, FA_CATEGORY2)"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND AAAV.RESPONSIBILITY_USER = ?"
					+ " AND (AAAV.ITEM_STATUS IS NULL OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND AAAV.ASSET_ID IS NOT NULL  AND NOT EXISTS(" // " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " " + "
					+ " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(userAccount.getEmployeeId());
			sqlArgs.add(dto.getItemStatus());
			
			if (!userAccount.isProvinceUser()) {
				sqlStr = sqlStr + " AND EXISTS(" + " SELECT" + " NULL"
						+ " FROM" + " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
						+ " ESD.ITEM_CODE = AAAV.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT "
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " AMS_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.FA_CATEGORY1 = dbo.NVL(?, FA_CATEGORY1)"
					+ " AND AAAV.FA_CATEGORY2 = dbo.NVL(?, FA_CATEGORY2)"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND AAAV.RESPONSIBILITY_USER = ?"
					+ " AND (AAAV.ITEM_STATUS IS NULL OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND AAAV.ASSET_ID IS NOT NULL AND NOT EXISTS(" // " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " " + "
					+ " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(userAccount.getEmployeeId());
			sqlArgs.add(dto.getItemStatus());
			if (!userAccount.isProvinceUser()) {
				sqlStr = sqlStr + " AND EXISTS(" + " SELECT" + " NULL"
						+ " FROM" + " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
						+ " ESD.ITEM_CODE = AAAV.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ�����ʲ���SQL
	 * 
	 * @return SQLModel
	 */
	private SQLModel getDeptAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT "
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " TD_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.DEPT_NAME = ?"
					+ " AND AAAV.FA_CATEGORY1 LIKE dbo.NVL (?, AAAV.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.FA_CATEGORY2 LIKE dbo.NVL (?, AAAV.FA_CATEGORY2))"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND (AAAV.ITEM_STATUS = NULL  OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND AAAV.ASSET_ID IS NOT NULL AND NOT EXISTS(" //" + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " " + " 
					+ " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemStatus());
			if (!userAccount.isProvinceUser()) {
				sqlStr = sqlStr + " AND EXISTS(" + " SELECT" + " NULL"
						+ " FROM" + " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
						+ " ESD.ITEM_CODE = AAAV.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT "
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " AMS_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.DEPT_NAME = ?"
					+ " AND AAAV.FA_CATEGORY1 LIKE dbo.NVL (?, AAAV.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.FA_CATEGORY2 LIKE dbo.NVL (?, AAAV.FA_CATEGORY2))"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND (AAAV.ITEM_STATUS " + SyBaseSQLUtil.isNullNoParam() + "  OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND AAAV.ASSET_ID IS NOT NULL AND NOT EXISTS(" // " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " " + "
					+ " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemStatus());
			if (!userAccount.isProvinceUser()) {
				sqlStr = sqlStr + " AND EXISTS(" + " SELECT" + " NULL"
						+ " FROM" + " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
						+ " ESD.ITEM_CODE = AAAV.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��˾�ʲ���ѯSQL
	 * 
	 * @return SQLModel
	 */
	private SQLModel getCompanyAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT"
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " TD_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.COMPANY = ?"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.DEPT_NAME = ?)"
					+ " AND AAAV.FA_CATEGORY1 LIKE dbo.NVL (?, AAAV.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.FA_CATEGORY2 LIKE dbo.NVL (?, AAAV.FA_CATEGORY2))"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND AAAV.ASSET_ID IS NOT NULL " // + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " "
					+ " AND (AAAV.ITEM_STATUS = NULL OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND NOT EXISTS(" + " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getCompanyName());
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getDeptName());

			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemStatus());

			if (!userAccount.isProvinceUser()) {
				sqlStr = sqlStr + " AND EXISTS(" + " SELECT" + " NULL"
						+ " FROM" + " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
						+ " ESD.ITEM_CODE = AAAV.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT"
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " AMS_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.COMPANY = ?"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.DEPT_NAME = ?)"
					+ " AND AAAV.FA_CATEGORY1 LIKE dbo.NVL (?, AAAV.FA_CATEGORY1)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.FA_CATEGORY2 LIKE dbo.NVL (?, AAAV.FA_CATEGORY2))"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND AAAV.ASSET_ID IS NOT NULL " //+ SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " "
					+ " AND (AAAV.ITEM_STATUS " + SyBaseSQLUtil.isNullNoParam() + "  OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND NOT EXISTS(" + " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getCompanyName());
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getDeptName());

			sqlArgs.add(dto.getFaCategory1());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getFaCategory2());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemStatus());

			if (!userAccount.isProvinceUser()) {
				sqlStr = sqlStr + " AND EXISTS(" + " SELECT" + " NULL"
						+ " FROM" + " ETS_SYSITEM_DISTRIBUTE ESD" + " WHERE"
						+ " ESD.ITEM_CODE = AAAV.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡȫʡ�ʲ���ѯSQL
	 * 
	 * @return SQLModel
	 */
	private SQLModel getProvinceAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT "
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " TD_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.COMPANY = dbo.NVL(?, AAAV.COMPANY)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.DEPT_NAME = ?)"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND (AAAV.ITEM_STATUS " + SyBaseSQLUtil.isNullNoParam() + "  OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND AAAV.ASSET_ID IS NOT NULL " //+ SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " " 
					+ " AND NOT EXISTS("
					+ " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getCompanyName());
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemStatus());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT "
					+ " AAAV.BARCODE,"
					+ " AAAV.ASSET_NUMBER,"
					+ " AAAV.FA_CATEGORY1,"
					+ " AAAV.FA_CATEGORY2,"
					+ " AAAV.ASSETS_DESCRIPTION,"
					+ " AAAV.MODEL_NUMBER,"
					+ " AAAV.UNIT_OF_MEASURE,"
					+ " AAAV.CURRENT_UNITS,"
					+ " dbo.APP_GET_FLEX_VALUE(AAAV.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS, "
					+ " AAAV.COST,"
					+ " AAAV.ASSETS_CREATE_DATE,"
					+ " AAAV.DATE_PLACED_IN_SERVICE,"
					+ " AAAV.LIFE_IN_YEARS,"
					+ " AAAV.DEPRECIATION,"
					+ " AAAV.DEPRN_COST,"
					+ " AAAV.SCRAP_VALUE,"
					+ " AAAV.DEPRECIATION_ACCOUNT,"
					+ " AAAV.DEPRECIATION_ACCOUNT_NAME,"
					+ " AAAV.BOOK_TYPE_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NO,"
					+ " AAAV.WORKORDER_OBJECT_CODE,"
					+ " AAAV.WORKORDER_OBJECT_NAME,"
					+ " AAAV.PROJECT_NAME,"
					+ " AAAV.RESPONSIBILITY_USER,"
					+ " AAAV.RESPONSIBILITY_USER_NAME,"
					+ " AAAV.EMPLOYEE_NUMBER,"
					+ " AAAV.DEPT_CODE,"
					+ " AAAV.DEPT_NAME,"
					+ " AAAV.MAINTAIN_USER_NAME,"
					+ " AAAV.FA_CATEGORY_CODE"
					+ " FROM"
					+ " AMS_ASSETS_ADDRESS_V AAAV"
					+ " WHERE"
					+ " AAAV.COMPANY = dbo.NVL(?, AAAV.COMPANY)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.DEPT_NAME = ?)"
					+ " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
					+ " AND AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
					+ " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
					+ " AND (AAAV.ITEM_STATUS " + SyBaseSQLUtil.isNullNoParam() + "  OR AAAV.ITEM_STATUS = dbo.NVL(?, AAAV.ITEM_STATUS))"
					+ " AND AAAV.ASSET_ID IS NOT NULL " //+ SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " "
					+ " AND NOT EXISTS("
					+ " SELECT" + " NULL" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAAV.BARCODE = AAR.BARCODE)";
			sqlArgs.add(dto.getCompanyName());
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getDeptName());
			sqlArgs.add(dto.getAssetsDescription());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getModelNumber());
			sqlArgs.add(dto.getAssetNumber());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemStatus());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}
}
