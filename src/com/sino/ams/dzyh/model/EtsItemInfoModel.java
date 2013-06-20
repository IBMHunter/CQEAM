package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EtsItemInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EtsItemInfoModel</p>
 * <p>Description:�����Զ�����SQL��������EtsItemInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EtsItemInfoModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoDTO ���β���������
	 */
	public EtsItemInfoModel(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " ETS_ITEM_INFO("
				+ " SYSTEMID,"
				+ " FA_BARCODE,"
				+ " VENDOR_BARCODE,"
				+ " ITEM_QTY,"
				+ " DISABLE_DATE,"
				+ " REMARK,"
				+ " START_DATE,"
				+ " ITEM_CODE,"
				+ " PROJECTID,"
				+ " ITEM_STATUS,"
				+ " ATTRIBUTE1,"
				+ " ATTRIBUTE2,"
				+ " SENDTOMIS_FLAG,"
				+ " MIS_ITEMNAME,"
				+ " MIS_ITEMSPEC,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY,"
				+ " ASSET_ID,"
				+ " ADDRESS_ID,"
				+ " FINANCE_PROP,"
				+ " ATTRIBUTE3,"
				+ " PARENT_BARCODE,"
				+ " LAST_LOC_CHG_DATE,"
				+ " ORGANIZATION_ID,"
				+ " BARCODE,"
				+ " IS_PARENT,"
				+ " RESPONSIBILITY_USER,"
				+ " RESPONSIBILITY_DEPT,"
				+ " MAINTAIN_USER,"
				+ " MAINTAIN_DEPT,"
				+ " IS_TMP,"
				+ " PRICE"
				+ ") VALUES ("
				+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
			sqlArgs.add(dto.getFaBarcode());
			sqlArgs.add(dto.getVendorBarcode());
			sqlArgs.add(dto.getItemQty());
			sqlArgs.add(dto.getDisableDate());
			sqlArgs.add(dto.getRemark());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getItemCode());
			sqlArgs.add(dto.getProjectid());
			sqlArgs.add(dto.getItemStatus());
			sqlArgs.add(dto.getAttribute1());
			sqlArgs.add(dto.getAttribute2());
			sqlArgs.add(dto.getSendtomisFlag());
			sqlArgs.add(dto.getMisItemname());
			sqlArgs.add(dto.getMisItemspec());
			sqlArgs.add(dto.getCreationDate());
			sqlArgs.add(dto.getCreatedBy());
			sqlArgs.add(dto.getLastUpdateDate());
			sqlArgs.add(dto.getLastUpdateBy());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getAddressId());
			sqlArgs.add(dto.getFinanceProp());
			sqlArgs.add(dto.getAttribute3());
			sqlArgs.add(dto.getParentBarcode());
			sqlArgs.add(dto.getLastLocChgDate());
			sqlArgs.add(dto.getOrganizationId());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getIsParent());
			sqlArgs.add(dto.getResponsibilityUser());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getMaintainUser());
			sqlArgs.add(dto.getMaintainDept());
			sqlArgs.add(dto.getIsTmp());
			sqlArgs.add(dto.getPrice());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
			String sqlStr = "UPDATE ETS_ITEM_INFO"
				+ " SET"
				+ " FA_BARCODE = ?,"
				+ " VENDOR_BARCODE = ?,"
				+ " ITEM_QTY = ?,"
				+ " DISABLE_DATE = ?,"
				+ " REMARK = ?,"
				+ " START_DATE = ?,"
				+ " ITEM_CODE = ?,"
				+ " PROJECTID = ?,"
				+ " ITEM_STATUS = ?,"
				+ " ATTRIBUTE1 = ?,"
				+ " ATTRIBUTE2 = ?,"
				+ " SENDTOMIS_FLAG = ?,"
				+ " MIS_ITEMNAME = ?,"
				+ " MIS_ITEMSPEC = ?,"
				+ " CREATION_DATE = ?,"
				+ " CREATED_BY = ?,"
				+ " LAST_UPDATE_DATE = ?,"
				+ " LAST_UPDATE_BY = ?,"
				+ " ASSET_ID = ?,"
				+ " ADDRESS_ID = ?,"
				+ " FINANCE_PROP = ?,"
				+ " ATTRIBUTE3 = ?,"
				+ " PARENT_BARCODE = ?,"
				+ " LAST_LOC_CHG_DATE = ?,"
				+ " ORGANIZATION_ID = ?,"
				+ " BARCODE = ?,"
				+ " IS_PARENT = ?,"
				+ " RESPONSIBILITY_USER = ?,"
				+ " RESPONSIBILITY_DEPT = ?,"
				+ " MAINTAIN_USER = ?,"
				+ " MAINTAIN_DEPT = ?,"
				+ " IS_TMP = ?,"
				+ " PRICE = ?"
				+ " WHERE"
				+ " SYSTEMID = ?";
		
			sqlArgs.add(dto.getFaBarcode());
			sqlArgs.add(dto.getVendorBarcode());
			sqlArgs.add(dto.getItemQty());
			sqlArgs.add(dto.getDisableDate());
			sqlArgs.add(dto.getRemark());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getItemCode());
			sqlArgs.add(dto.getProjectid());
			sqlArgs.add(dto.getItemStatus());
			sqlArgs.add(dto.getAttribute1());
			sqlArgs.add(dto.getAttribute2());
			sqlArgs.add(dto.getSendtomisFlag());
			sqlArgs.add(dto.getMisItemname());
			sqlArgs.add(dto.getMisItemspec());
			sqlArgs.add(dto.getCreationDate());
			sqlArgs.add(dto.getCreatedBy());
			sqlArgs.add(dto.getLastUpdateDate());
			sqlArgs.add(dto.getLastUpdateBy());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getAddressId());
			sqlArgs.add(dto.getFinanceProp());
			sqlArgs.add(dto.getAttribute3());
			sqlArgs.add(dto.getParentBarcode());
			sqlArgs.add(dto.getLastLocChgDate());
			sqlArgs.add(dto.getOrganizationId());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getIsParent());
			sqlArgs.add(dto.getResponsibilityUser());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getMaintainUser());
			sqlArgs.add(dto.getMaintainDept());
			sqlArgs.add(dto.getIsTmp());
			sqlArgs.add(dto.getPrice());
			sqlArgs.add(dto.getSystemid());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " ETS_ITEM_INFO"
				+ " WHERE"
				+ " SYSTEMID = ?";
			sqlArgs.add(dto.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EII.SYSTEMID,"
			+ " ESI.ITEM_CATEGORY2 EII_ITEM_CATEGORY2,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME EII_ITEM_NAME,"
			+ " ESI.ITEM_SPEC EII_ITEM_SPEC,"
			+ " EII.ITEM_QTY,"
			+ " EII.PRICE,"
			+ " EII.RESPONSIBILITY_DEPT,"
			+ " AMD.DEPT_NAME EII_DEPT_NAME,"
			+ " EII.RESPONSIBILITY_USER,"
			+ " AME.USER_NAME EII_USER_NAME,"
			+ " EII.ADDRESS_ID,"
			+ " EO.WORKORDER_OBJECT_NAME EII_WORKORDER_OBJECT_NAME,"
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
			+ " AMS_MIS_EMPLOYEE AME"
			+ " WHERE"
			+ " ESI.ITEM_CODE=EII.ITEM_CODE"
			+ " AND EII.RESPONSIBILITY_DEPT=AMD.DEPT_CODE"
			+ " AND EII.ADDRESS_ID=AOA.ADDRESS_ID"
			+ " AND AOA.BOX_NO='0000'"
			+ " AND AOA.NET_UNIT='0000'"
			+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
			+ " AND EII.RESPONSIBILITY_USER=AME.EMPLOYEE_ID"
			+ " AND SYSTEMID = ?";
		sqlArgs.add(dto.getSystemid());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� addressId �����ѯ����SQL��
	 * ����Զ��������ݱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param addressId String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByAddressIdModel(int addressId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " SYSTEMID,"
			+ " FA_BARCODE,"
			+ " VENDOR_BARCODE,"
			+ " ITEM_QTY,"
			+ " DISABLE_DATE,"
			+ " REMARK,"
			+ " START_DATE,"
			+ " ITEM_CODE,"
			+ " PROJECTID,"
			+ " ITEM_STATUS,"
			+ " ATTRIBUTE1,"
			+ " ATTRIBUTE2,"
			+ " SENDTOMIS_FLAG,"
			+ " MIS_ITEMNAME,"
			+ " MIS_ITEMSPEC,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " ASSET_ID,"
			+ " FINANCE_PROP,"
			+ " ATTRIBUTE3,"
			+ " PARENT_BARCODE,"
			+ " LAST_LOC_CHG_DATE,"
			+ " ORGANIZATION_ID,"
			+ " BARCODE,"
			+ " IS_PARENT,"
			+ " RESPONSIBILITY_USER,"
			+ " RESPONSIBILITY_DEPT,"
			+ " MAINTAIN_USER,"
			+ " MAINTAIN_DEPT,"
			+ " IS_TMP,"
			+ " PRICE"
			+ " FROM"
			+ " ETS_ITEM_INFO"
			+ " WHERE"
			+ " ADDRESS_ID = ?";
		sqlArgs.add(addressId);
		
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
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		if(foreignKey.equals("addressId")){
			sqlModel = getDataByAddressIdModel(dto.getAddressId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� addressId ��������ɾ��SQL��
	 * ����Զ��������ݱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param addressId String 
	 * @return SQLModel ��������ɾ����SQLModel
	 */

	private SQLModel getDeleteByAddressIdModel(int addressId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
			+ " SYSTEMID,"
			+ " FA_BARCODE,"
			+ " VENDOR_BARCODE,"
			+ " ITEM_QTY,"
			+ " DISABLE_DATE,"
			+ " REMARK,"
			+ " START_DATE,"
			+ " ITEM_CODE,"
			+ " PROJECTID,"
			+ " ITEM_STATUS,"
			+ " ATTRIBUTE1,"
			+ " ATTRIBUTE2,"
			+ " SENDTOMIS_FLAG,"
			+ " MIS_ITEMNAME,"
			+ " MIS_ITEMSPEC,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " ASSET_ID,"
			+ " FINANCE_PROP,"
			+ " ATTRIBUTE3,"
			+ " PARENT_BARCODE,"
			+ " LAST_LOC_CHG_DATE,"
			+ " ORGANIZATION_ID,"
			+ " BARCODE,"
			+ " IS_PARENT,"
			+ " RESPONSIBILITY_USER,"
			+ " RESPONSIBILITY_DEPT,"
			+ " MAINTAIN_USER,"
			+ " MAINTAIN_DEPT,"
			+ " IS_TMP,"
			+ " PRICE"
			+ " FROM"
			+ " ETS_ITEM_INFO"
			+ " WHERE"
			+ " ADDRESS_ID = ?";
		sqlArgs.add(addressId);
		
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
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		if(foreignKey.equals("addressId")){
			sqlModel = getDeleteByAddressIdModel(dto.getAddressId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ���ֵ�׺�Ʒ����Ϣ��ѯ(EAM) ETS_ITEM_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " EII.SYSTEMID,"
				+ " ESI.ITEM_CATEGORY2 EII_ITEM_CATEGORY2,"
				+ " EII.BARCODE,"
				+ " ESI.ITEM_NAME EII_ITEM_NAME,"
				+ " ESI.ITEM_SPEC EII_ITEM_SPEC,"
				+ " EII.ITEM_QTY,"
				+ " EII.PRICE,"
				+ " EII.RESPONSIBILITY_DEPT,"
				+ " AMD.DEPT_NAME EII_DEPT_NAME,"
				+ " EII.RESPONSIBILITY_USER,"
				+ " AME.USER_NAME EII_USER_NAME,"
				+ " EII.ADDRESS_ID,"
				+ " EO.WORKORDER_OBJECT_NAME EII_WORKORDER_OBJECT_NAME,"
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
				+ " AMS_MIS_EMPLOYEE AME"
				+ " WHERE"
				+ " ESI.ITEM_CODE=EII.ITEM_CODE"
				+ " AND EII.FINANCE_PROP='DZYH'"
				+ " AND EII.RESPONSIBILITY_DEPT=AMD.DEPT_CODE"
				+ " AND EII.ADDRESS_ID=AOA.ADDRESS_ID"
				+ " AND AOA.BOX_NO='0000'"
				+ " AND AOA.NET_UNIT='0000'"
				+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
				+ " AND EII.RESPONSIBILITY_USER=AME.EMPLOYEE_ID"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_CATEGORY2 LIKE ?)"
				+ " AND EII.BARCODE LIKE dbo.NVL(? ,EII.BARCODE)"
				+ " AND ESI.ITEM_NAME LIKE dbo.NVL(? ,ESI.ITEM_NAME)"
				+ " AND ESI.ITEM_SPEC LIKE dbo.NVL(? ,ESI.ITEM_SPEC)"
				+ " AND EII.RESPONSIBILITY_DEPT LIKE dbo.NVL(? ,EII.RESPONSIBILITY_DEPT)"
				+ " AND EII.ADDRESS_ID LIKE dbo.NVL(? ,EII.ADDRESS_ID)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.MAINTAIN_USER LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.ATTRIBUTE3 LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.LAST_LOC_CHG_DATE LIKE ?)";
			
			sqlArgs.add(dto.getEiiItemCategory2());
			sqlArgs.add(dto.getEiiItemCategory2());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getEiiItemName());
			sqlArgs.add(dto.getEiiItemSpec());
			sqlArgs.add(dto.getEiiDeptName());
			sqlArgs.add(dto.getEiiWorkorderObjectName());
			sqlArgs.add(dto.getMaintainUser());
			sqlArgs.add(dto.getMaintainUser());
			sqlArgs.add(dto.getAttribute3());
			sqlArgs.add(dto.getAttribute3());
			sqlArgs.add(dto.getLastLocChgDate());
			sqlArgs.add(dto.getLastLocChgDate());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}