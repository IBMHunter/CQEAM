package com.sino.td.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.ArrUtil;
import com.sino.td.newasset.dto.TdAssetsTransLineDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class TdAssetsConfirmModel extends AMSSQLProducer {

	public TdAssetsConfirmModel(SfUserDTO userAccount, TdAssetsTransLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}


	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getAssetsConfirmModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " TD_ASSETS_TRANS_LINE AATL"
						+ " SET"
						+ " AATL.CONFIRM_DATE = GETDATE() ,"
						+ " AATL.RESPONSIBILITY_DEPT = dbo.NVL(?, AATL.RESPONSIBILITY_DEPT),"
						+ " AATL.LINE_STATUS = ?,"
						+ " AATL.CONFIRMED_BY = ?"
						+ " WHERE"
						+ " AATL.TRANS_ID = ?"
						+ " AND AATL.BARCODE = ?"
						+ " AND AATL.RESPONSIBILITY_USER = ?"
						+ " AND AATL.CONFIRM_DATE  " + SyBaseSQLUtil.isNullNoParam() + " ";
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getLineStatus());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransId());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getResponsibilityUser());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ������豸�����ETS_ITEM_INFO��Ϣ
	 * ע�⣺ֻ��ȷ�ϲ����ڵ������ʲ���Ҫ�ò���
	 * @return SQLModel
	 */
	public SQLModel getItemUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String sqlStr = "UPDATE "
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.RESPONSIBILITY_USER = ?,"
						+ " EII.RESPONSIBILITY_DEPT = ?,"
						+ " EII.ADDRESS_ID = ?,"
						+ " EII.ORGANIZATION_ID = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE() ,"
						+ " EII.LAST_UPDATE_BY = ?"
						+ " WHERE"
						+ " EII.BARCODE = ?";
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getAddressId());
		sqlArgs.add(dto.getToOrganizationId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getBarcode());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ������豸��������ETS_SYSITEM_DISTRIBUTE��Ϣ
	 * ע�⣺ֻ��ȷ�ϲ����ڵ������ʲ���Ҫ�ò���
	 * @return SQLModel
	 */
	public SQLModel getTmpDistributeModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String sqlStr = "INSERT INTO"
						+ " ETS_SYSITEM_DISTRIBUTE("
						+ " SYSTEM_ID,"
						+ " ITEM_CODE,"
						+ " ORGANIZATION_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " IS_TMP)("
						+ " SELECT"
						+ "  NEWID() ,"
						+ " TMP_V.ITEM_CODE,"
						+ " ?,"
						+ " GETDATE() ,"
						+ " ?,"
						+ " ?"
						+ " FROM("
						+ " SELECT DISTINCT"
						+ " EII.ITEM_CODE"
						+ " FROM"
						+ " ETS_ITEM_INFO           EII,"
						+ " TD_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " EII.BARCODE = AATL.BARCODE"
						+ " AND (EII.DISABLE_DATE  " + SyBaseSQLUtil.isNullNoParam() + "  OR EII.DISABLE_DATE > GETDATE() )"
						+ " AND (EII.ITEM_STATUS  " + SyBaseSQLUtil.isNullNoParam() + "  OR EII.ITEM_STATUS = ?)"
						+ " AND AATL.TRANS_ID = ?"
						+ " AND AATL.BARCODE = ?"
						+ " AND NOT EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " ETS_SYSITEM_DISTRIBUTE ESD"
						+ " WHERE"
						+ " ESD.ITEM_CODE = EII.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)) TMP_V)";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(AssetsDictConstant.STATUS_YES);
		sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
		sqlArgs.add(dto.getTransId());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�����ڵ�����ȷ��SQL��
	 * @param transIds List
	 * @return SQLModel
	 */
	public SQLModel getOrdersConfirmModel(List<String> transIds) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String[] tmpIdArr = new String[transIds.size()];
		tmpIdArr = transIds.toArray(tmpIdArr);
		String tmpIds = ArrUtil.arrToSqlStr(tmpIdArr);
		String sqlStr = "UPDATE"
						+ " TD_ASSETS_TRANS_HEADER AATH"
						+ " SET"
						+ " AATH.TRANS_STATUS     = ?,"
						+ " AATH.TRANS_DATE       = GETDATE() ,"
						+ " AATH.LAST_UPDATE_DATE = GETDATE() ,"
						+ " AATH.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " AATH.TRANS_ID IN (" + tmpIds + ")"
						+ " AND EXISTS("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " TD_TRANS_CONFIRM_V ATCV"
						+ " WHERE"
						+ " AATH.TRANS_ID = ATCV.TRANS_ID"
						+ " AND ATCV.NOT_CONFIRMED_NUMBER = 0)";
		sqlArgs.add(AssetsDictConstant.ORDER_STS_CONFIRMD);
		sqlArgs.add(userAccount.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�����ʲ�ɾ��SQL��
	 * @param transIds ������ID
	 * @return SQLModel
	 */
	public SQLModel getDeleteReservedAssetsModel(List<String> transIds) {
		SQLModel sqlModel = new SQLModel();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String[] tmpIdArr = new String[transIds.size()];
		tmpIdArr = transIds.toArray(tmpIdArr);
		String tmpIds = ArrUtil.arrToSqlStr(tmpIdArr);
		String sqlStr = "DELETE FROM"
						+ " AMS_ASSETS_RESERVED AAR"
						+ " WHERE"
						+ " AAR.TRANS_ID IN (" + tmpIds + ")"
						+ " AND EXISTS("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " TD_ASSETS_TRANS_LINE AATL"
						+ " WHERE"
						+ "  " + SyBaseSQLUtil.isNotNull("AATL.CONFIRM_DATE") + " "
						+ " AND AAR.TRANS_ID = AATL.TRANS_ID"
						+ " AND AAR.BARCODE = AATL.BARCODE"
						+ " AND AATL.RESPONSIBILITY_USER = ?)";
		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getResponsibilityUser()); //����Ϊ����Ա���Դ���ȷ�ϣ���˲����õ�¼�������Ϣ������
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ص��ѯSQL
	 * @return SQLModel
	 */
	public SQLModel getAddressQueryModel() {
		SQLModel sqlModel = new SQLModel();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String addressNo = dto.getAssignedToLocation() + "." + "0000.0000";
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " ADDRESS_ID"
						+ " FROM"
						+ " AMS_OBJECT_ADDRESS AOA"
						+ " WHERE"
						+ " AOA.ADDRESS_NO = ?";
		sqlArgs.add(addressNo);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ص�(Address)����SQL
	 * @return SQLModel
	 */
	public SQLModel getAddressCreateModel() {
		SQLModel sqlModel = new SQLModel();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO AMS_OBJECT_ADDRESS("
						+ " ADDRESS_ID,"
						+ " OBJECT_NO,"
						+ " BOX_NO,"
						+ " NET_UNIT,"
						+ " ORGANIZATION_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " REMARK,"
						+ " ADDRESS_NO"
						+ " ) VALUES ("
						+ " ?, ?, '0000', '0000', ?, GETDATE() , ?, ?, ?)";
		String remark = "�ʲ�ȷ���Զ�����AMS_OBJECT_ADDRESS��¼";
		String addressNo = dto.getAssignedToLocation() + "." + "0000.0000";
		sqlArgs.add(dto.getAddressId());
		sqlArgs.add(dto.getAssignedToLocation());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(remark);
		sqlArgs.add(addressNo);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}



	/**
	 * ���ܣ���ȡ�ɱ�ǩ����SQL(���ڹ�˾���ʲ�����)
	 * @return SQLModel
	 */
	public SQLModel getDiscardOldBarcodeModel(){
		SQLModel sqlModel = new SQLModel();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS = ?,"
						+ " EII.REMARK = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE() ,"
						+ " EII.LAST_UPDATE_BY = ?"
						+ " WHERE"
						+ " EII.BARCODE = ?";
		sqlArgs.add(AssetsDictConstant.ITEM_STATUS_DISCARDED);
		sqlArgs.add(AssetsDictConstant.TRANS_OUT_REMARK);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�±�ǩ����SQL(���ڹ�˾���ʲ�����)
	 * @return SQLModel
	 */
	public SQLModel getCreateNewBarcodeModel(){
		SQLModel sqlModel = new SQLModel();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO"
						+ " ETS_ITEM_INFO("
						+ " SYSTEMID,"
						+ " BARCODE,"
						+ " ITEM_CODE,"
						+ " ITEM_QTY,"
						+ " PROJECTID,"
						+ " ITEM_STATUS,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_DEPT,"
						+ " ADDRESS_ID,"
						+ " ORGANIZATION_ID,"
						+ " REMARK,"
						+ " START_DATE,"
						+ " FINANCE_PROP,"
						+ " LAST_LOC_CHG_DATE,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY) ("
						+ " SELECT"
						+ "  NEWID() ,"
						+ " AATL.NEW_BARCODE,"
						+ " EII.ITEM_CODE,"
						+ " EII.ITEM_QTY,"
						+ " EII.PROJECTID,"
						+ " ?,"
						+ " AATL.RESPONSIBILITY_USER,"
						+ " AATL.RESPONSIBILITY_DEPT,"
						+ " AOA.ADDRESS_ID,"
						+ " AATH.TO_ORGANIZATION_ID,"
						+ " ?,"
						+ " EII.START_DATE,"
						+ " ?,"
						+ " GETDATE() ,"
						+ " GETDATE() ,"
						+ " ?,"
						+ " GETDATE() ,"
						+ " ?"
						+ " FROM"
						+ " TD_ASSETS_TRANS_HEADER AATH,"
						+ " TD_ASSETS_TRANS_LINE   AATL,"
						+ " AMS_OBJECT_ADDRESS      AOA,"
						+ " ETS_ITEM_INFO           EII"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATL.ASSIGNED_TO_LOCATION = AOA.OBJECT_NO"
						+ " AND AATH.TRANS_ID = ?"
						+ " AND AATL.BARCODE = ?)";

		sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
		sqlArgs.add(AssetsDictConstant.TRANS_IN_REMARK);
		sqlArgs.add(AssetsDictConstant.FIN_PROP_UNKNOW);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransId());
		sqlArgs.add(dto.getBarcode());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
