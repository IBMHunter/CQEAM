package com.sino.ams.system.house.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsItemInfoModel</p>
 * <p>Description:�����Զ�����SQL��������EtsItemInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsItemInfoModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoDTO ���β���������
	 */
	public EtsItemInfoModel(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " ETS_ITEM_INFO("
			+ " SYSTEMID,"
			+ " BARCODE,"
			+ " ITEM_CODE,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
            + " ITEM_STATUS,"
            + " ORGANIZATION_ID,"
            + " ADDRESS_ID,"
            + " ATTRIBUTE1"
            + ") VALUES ("
			+ "  NEWID(), ?, ?, GETDATE(), ?, '����',? ,? ,?)";
		
		sqlArgs.add(etsItemInfo.getBarcode());
		sqlArgs.add(etsItemInfo.getItemCode());
		sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(etsItemInfo.getAddressId());
        sqlArgs.add(DictConstant.RENT);

        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "UPDATE "
            + " ETS_ITEM_INFO"
			+ " SET"
			+ " BARCODE = ?,"
			+ " ITEM_QTY = 1,"
			+ " ITEM_CODE = ?,"
            + " ADDRESS_ID=?,"
            + " LAST_UPDATE_DATE = GETDATE(),"
			+ " LAST_UPDATE_BY = ?"
			+ " WHERE"
			+ " SYSTEMID = ?";
		
		sqlArgs.add(etsItemInfo.getBarcode());
		sqlArgs.add(etsItemInfo.getItemCode());
        sqlArgs.add(etsItemInfo.getAddressId());
        sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(etsItemInfo.getSystemid());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
			+ " ETS_ITEM_INFO"
			+ " WHERE"
			+ " SYSTEMID = ?";
		sqlArgs.add(etsItemInfo.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEMID,"
			+ " BARCODE,"
			+ " FA_BARCODE,"
			+ " VENDOR_BARCODE,"
			+ " ITEM_QTY,"
			+ " DISABLE_DATE,"
			+ " REMARK,"
			+ " START_DATE,"
			+ " ITEM_CODE,"
			+ " YEARS,"
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
			+ " ORGANIZATION_ID,"
			+ " FINANCE_PROP"
			+ " FROM"
			+ " ETS_ITEM_INFO"
			+ " WHERE"
			+ " SYSTEMID = ?";
		sqlArgs.add(etsItemInfo.getSystemid());
		
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
	private SQLModel getDataByAddressIdModel(String addressId){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();                    //������ϸ
		String sqlStr = "SELECT "
			+ " SYSTEMID,"
			+ " BARCODE,"
			+ " FA_BARCODE,"
			+ " VENDOR_BARCODE,"
			+ " ITEM_QTY,"
			+ " DISABLE_DATE,"
			+ " REMARK,"
			+ " START_DATE,"
			+ " ITEM_CODE,"
			+ " YEARS,"
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
			+ " ORGANIZATION_ID,"
			+ " FINANCE_PROP"
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
	public SQLModel getDataByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
		if(foreignKey.equals("addressId")){
			sqlModel = getDataByAddressIdModel(etsItemInfo.getAddressId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� addressId ��������ɾ��SQL��
	 * ����Զ��������ݱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param addressId String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDeleteByAddressIdModel(String addressId){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
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
	public SQLModel getDeleteByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
		if(foreignKey.equals("addressId")){
			sqlModel = getDeleteByAddressIdModel(etsItemInfo.getAddressId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
        List sqlArgs = null;
        String sqlStr = null;

            sqlArgs = new ArrayList();
            EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
            sqlStr = "SELECT "
                + " SYSTEMID,"
                + " BARCODE,"
                + " FA_BARCODE,"
                + " VENDOR_BARCODE,"
                + " ITEM_QTY,"
                + " DISABLE_DATE,"
                + " REMARK,"
                + " START_DATE,"
                + " ITEM_CODE,"
                + " YEARS,"
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
                + " ORGANIZATION_ID,"
                + " FINANCE_PROP"
                + " FROM"
                + " ETS_ITEM_INFO"
                + " WHERE"
                + " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR BARCODE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR FA_BARCODE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR VENDOR_BARCODE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ITEM_QTY LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR DISABLE_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR START_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ITEM_CODE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR YEARS LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR PROJECTID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ITEM_STATUS LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ATTRIBUTE1 LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ATTRIBUTE2 LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR SENDTOMIS_FLAG LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR MIS_ITEMNAME LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR MIS_ITEMSPEC LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ASSET_ID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ADDRESS_ID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)";
            sqlArgs.add(etsItemInfo.getSystemid());
            sqlArgs.add(etsItemInfo.getSystemid());
            sqlArgs.add(etsItemInfo.getBarcode());
            sqlArgs.add(etsItemInfo.getBarcode());
            sqlArgs.add(etsItemInfo.getFaBarcode());
            sqlArgs.add(etsItemInfo.getFaBarcode());
            sqlArgs.add(etsItemInfo.getVendorBarcode());
            sqlArgs.add(etsItemInfo.getVendorBarcode());
            sqlArgs.add(etsItemInfo.getItemQty());
            sqlArgs.add(etsItemInfo.getItemQty());
            sqlArgs.add(etsItemInfo.getDisableDate());
            sqlArgs.add(etsItemInfo.getDisableDate());
            sqlArgs.add(etsItemInfo.getRemark());
            sqlArgs.add(etsItemInfo.getRemark());
//            sqlArgs.add(etsItemInfo.getStartDate());
//            sqlArgs.add(etsItemInfo.getStartDate());
            sqlArgs.add(etsItemInfo.getItemCode());
            sqlArgs.add(etsItemInfo.getItemCode());
            sqlArgs.add(etsItemInfo.getYears());
            sqlArgs.add(etsItemInfo.getYears());
            sqlArgs.add(etsItemInfo.getProjectid());
            sqlArgs.add(etsItemInfo.getProjectid());
            sqlArgs.add(etsItemInfo.getItemStatus());
            sqlArgs.add(etsItemInfo.getItemStatus());
            sqlArgs.add(etsItemInfo.getAttribute1());
            sqlArgs.add(etsItemInfo.getAttribute1());
            sqlArgs.add(etsItemInfo.getAttribute2());
            sqlArgs.add(etsItemInfo.getAttribute2());
            sqlArgs.add(etsItemInfo.getSendtomisFlag());
            sqlArgs.add(etsItemInfo.getSendtomisFlag());
            sqlArgs.add(etsItemInfo.getMisItemname());
            sqlArgs.add(etsItemInfo.getMisItemname());
            sqlArgs.add(etsItemInfo.getMisItemspec());
            sqlArgs.add(etsItemInfo.getMisItemspec());
//            sqlArgs.add(etsItemInfo.getCreationDate());
//            sqlArgs.add(etsItemInfo.getCreationDate());
            sqlArgs.add(etsItemInfo.getCreatedBy());
            sqlArgs.add(etsItemInfo.getCreatedBy());
//            sqlArgs.add(etsItemInfo.getLastUpdateDate());
//            sqlArgs.add(etsItemInfo.getLastUpdateDate());
            sqlArgs.add(etsItemInfo.getLastUpdateBy());
            sqlArgs.add(etsItemInfo.getLastUpdateBy());
            sqlArgs.add(etsItemInfo.getAssetId());
            sqlArgs.add(etsItemInfo.getAssetId());
            sqlArgs.add(etsItemInfo.getAddressId());
            sqlArgs.add(etsItemInfo.getAddressId());
            sqlArgs.add(etsItemInfo.getOrganizationId());
            sqlArgs.add(etsItemInfo.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
       
		return sqlModel;
	}

}