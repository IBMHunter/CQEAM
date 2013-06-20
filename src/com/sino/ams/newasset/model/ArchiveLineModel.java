package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ArchiveLineModel extends AMSSQLProducer {
    private AmsAssetsCheckLineDTO dto = null;
    private AmsAssetsCheckHeaderDTO headerDTO = null;

    public ArchiveLineModel(SfUserDTO userAccount,
                            AmsAssetsCheckLineDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ����Ǹ���Ĳ������÷���
     * @param dtoParameter DTO
     */
    public void setDTOParameter(DTO dtoParameter) {
        super.setDTOParameter(dtoParameter);
        this.dto = (AmsAssetsCheckLineDTO) dtoParameter;
    }

    /**
     * ���ܣ������̵�ĵ���
     * @param headerDTO AmsAssetsCheckHeaderDTO
     */
    public void setOrderHeader(AmsAssetsCheckHeaderDTO headerDTO) {
        this.headerDTO = headerDTO;
    }

    /**
     * ���ܣ������̵㵥�ʲ������ݸ���SQL
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getChkLineArchiveModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String archiveRemark = dto.getArchiveRemark();
        if (archiveRemark.equals(AssetsDictConstant.ADOPT_SCAN_RESULT)) {
            dto.setArchiveStatus(dto.getScanStatus());
        } else if (archiveRemark.equals(AssetsDictConstant.ADOPT_SYST_STATUS)) {
            dto.setArchiveStatus(dto.getSystemStatus());
        }
        String sqlStr = "UPDATE "
                + " AMS_ASSETS_CHECK_LINE"
                + " SET"
                + " ARCHIVE_STATUS = ?,"
                + " ARCHIVE_REMARK = ?"
                + " WHERE"
                + " HEADER_ID = ?"
                + " AND BARCODE = ?";
        sqlArgs.add(dto.getArchiveStatus());
        sqlArgs.add(dto.getArchiveRemark());
        sqlArgs.add(dto.getHeaderId());
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ������ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        dto.setRemark("PDAɨ���ϴ������ʲ�");
        String sqlStr = "INSERT INTO "
                + " AMS_ASSETS_CHECK_LINE("
                + " HEADER_ID,"
                + " BARCODE,"
                + " SYSTEM_STATUS,"
                + " SCAN_STATUS,"
                + " REMARK"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?)";

        sqlArgs.add(dto.getHeaderId());
        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(dto.getSystemStatus());
        sqlArgs.add(dto.getScanStatus());
        sqlArgs.add(dto.getRemark());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������̵㵥�ʲ������ݸ���SQL
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getItem2TmpInvModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE "
                + " ETS_ITEM_INFO"
                + " SET"
                + " ADDRESS_ID = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
//						+ " ITEM_STATUS = ?"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(userAccount.getTmpAddressId());
//		sqlArgs.add(AssetsDictConstant.ITEM_STATUS_ON_WAY);//�ݲ�������;״̬�������豸�������;��
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ������̵㵥�ʲ������ݸ���SQL
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getOrderLineArchiveModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "UPDATE "
                    + " AMS_ASSETS_CHECK_LINE"
                    + " SET"
                    + " ARCH_ITEM_CODE = ?,"
                    + " ARCH_RESPONSIBILITY_USER = ?,"
                    + " ARCH_RESPONSIBILITY_DEPT = ?,"
                    + " ARCHIVE_STATUS = ?,"
                    + " ARCHIVE_REMARK = ?,"
                    + " ARCH_TO_TEMP_INV = ?,"
                    + " ARCH_START_DATE = ?,"
                    + " ARCH_MAINTAIN_USER = ?,"
                    + " MANUFACTURER_ID = ?,"
                    + " IS_SHARE = ?,"
                    + " CONTENT_CODE = ?,"
                    + " CONTENT_NAME = ?,"
                    + " POWER = ?"
                    + " WHERE"
                    + " HEADER_ID = ?"
                    + " AND BARCODE = ?";
            sqlArgs.add(dto.getArchItemCode());
            sqlArgs.add(dto.getArchResponsibilityUser());
            sqlArgs.add(dto.getArchResponsibilityDept());
            sqlArgs.add(dto.getArchiveStatus());
            sqlArgs.add(dto.getArchiveRemark());
            sqlArgs.add(dto.getArchToTempInv());
            sqlArgs.add(dto.getArchStartDate());
            sqlArgs.add(dto.getArchMaintainUser());
            sqlArgs.add(dto.getManufacturerId());
            sqlArgs.add(dto.getShare());
            sqlArgs.add(dto.getContentCode());
            sqlArgs.add(dto.getContentName());
            sqlArgs.add(dto.getPower());
            sqlArgs.add(dto.getHeaderId());
            sqlArgs.add(dto.getBarcode());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ������̵㵥�ʲ������ݸ���SQL��
     * ������ԭ�ص����и��豸�����
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getItemPropUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "UPDATE "
                    + " ETS_ITEM_INFO"
                    + " SET"
                    + " ADDRESS_ID = ?,"
                    + " ITEM_CODE = ?,"
                    + " ITEM_STATUS = ?,"
                    + " RESPONSIBILITY_USER = ?,"
                    + " RESPONSIBILITY_DEPT = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " START_DATE = ?,"
                    + " LAST_UPDATE_BY = ?,"
                    + " MAINTAIN_USER = ?,"
                    + " MANUFACTURER_ID = ?,"
                    + " IS_SHARE = ?,"
                    + " CONTENT_CODE = ?,"
                    + " CONTENT_NAME = ?,"
                    + " POWER = ?,"
                    + " LNE_ID=?,"
                    + " CEX_ID=?,"
                    + " OPE_ID=?,"
                    + " NLE_ID=?,"
                    + " CONSTRUCT_STATUS=?";

            String orderType = headerDTO.getOrderType();
            sqlArgs.add(dto.getAddressId());
            sqlArgs.add(dto.getScanItemCode());
            sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
            sqlArgs.add(dto.getScanResponsibilityUser());
            sqlArgs.add(dto.getScanResponsibilityDept());
            sqlArgs.add(dto.getScanStartDate()); //��������
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getScanMaintainUser());
            sqlArgs.add(dto.getManufacturerId());
            sqlArgs.add(dto.getShare());
            sqlArgs.add(dto.getContentCode());
            sqlArgs.add(dto.getContentName());
            sqlArgs.add(dto.getPower());
            sqlArgs.add(dto.getLneId());
            sqlArgs.add(dto.getCexId());
            sqlArgs.add(dto.getOpeId());
            sqlArgs.add(dto.getNleId());
            sqlArgs.add(dto.getConstructStatus());
            if (orderType.equals(AssetsDictConstant.RNT_CHK)) {
                sqlStr += ", ATTRIBUTE1 = ?";
                sqlArgs.add(AssetsDictConstant.RENT);
            }
            sqlStr += " WHERE BARCODE = ?";
            sqlArgs.add(dto.getBarcode());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ������̵㵥�ʲ������ݵص����SQL��
     * ������ԭ�ص���û�и��豸�����
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getItemAddressUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "UPDATE "
                    + " ETS_ITEM_INFO"
                    + " SET"
                    + " ITEM_CODE = ?,"
                    + " ITEM_STATUS = ?,"
                    + " RESPONSIBILITY_USER = ?,"
                    + " RESPONSIBILITY_DEPT = ?,"
                    + " START_DATE = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " LAST_UPDATE_BY = ?,"
                    + " ADDRESS_ID = ?,"
                    + " MAINTAIN_USER = ?,"
                    + " MANUFACTURER_ID = ?,"
                    + " IS_SHARE = ?,"
                    + " CONTENT_CODE = ?,"
                    + " CONTENT_NAME = ?,"
                    + " POWER = ?,"
                    + " LNE_ID=?,"
                    + " CEX_ID=?,"
                    + " OPE_ID=?,"
                    + " NLE_ID=?,"
                    + " CONSTRUCT_STATUS=?";
            sqlArgs.add(dto.getScanItemCode());
            sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
            sqlArgs.add(dto.getScanResponsibilityUser());
            sqlArgs.add(dto.getScanResponsibilityDept());
            sqlArgs.add(dto.getScanStartDate()); //��������
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getAddressId());
            sqlArgs.add(dto.getScanMaintainUser());
            sqlArgs.add(dto.getManufacturerId());
            sqlArgs.add(dto.getShare());
            sqlArgs.add(dto.getContentCode());
            sqlArgs.add(dto.getContentName());
            sqlArgs.add(dto.getPower());
            sqlArgs.add(dto.getLneId());
            sqlArgs.add(dto.getCexId());
            sqlArgs.add(dto.getOpeId());
            sqlArgs.add(dto.getNleId());
            sqlArgs.add(dto.getConstructStatus());
            String orderType = headerDTO.getOrderType();
            if (orderType.equals(AssetsDictConstant.RNT_CHK)) {
                sqlStr += ", ATTRIBUTE1 = ?";
                sqlArgs.add(AssetsDictConstant.RENT);
            }
            sqlStr += " WHERE BARCODE = ?";
            sqlArgs.add(dto.getBarcode());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ������̵㵥�ʲ������ݸ���SQL��
     * ������ԭ�ص����и��豸�����
     * @return SQLModel
     */
    public SQLModel getHasItemInDBModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " SYSTEMID"
                + " FROM"
                + " ETS_ITEM_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ������̵㵥�ʲ������ݸ���SQL��
     * ������ԭ�ص����и��豸�����
     * @return SQLModel
     */
    public SQLModel getExistItemCatgoryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " ESI.ITEM_CODE"
                + " FROM"
                + " ETS_SYSTEM_ITEM ESI"
                + " WHERE"
                + " ESI.ITEM_CATEGORY = ?"
                + " AND ESI.ITEM_NAME = ?"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC = ?)";
        sqlArgs.add(dto.getScanItemCategory());
        sqlArgs.add(dto.getScanItemName());
        sqlArgs.add(dto.getScanItemSpec());
        sqlArgs.add(dto.getScanItemSpec());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��жϸ��豸�����Ƿ��Ѿ��������OU
     * @return SQLModel
     */
    public SQLModel getHasItemDistributeModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " ESD.SYSTEM_ID"
                + " FROM"
                + " ETS_SYSITEM_DISTRIBUTE ESD"
                + " WHERE"
                + " ESD.ITEM_CODE = ?"
                + " AND ESD.ORGANIZATION_ID = ?";
        sqlArgs.add(dto.getScanItemCode());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������µ��豸����
     * @return SQLModel
     */
    public SQLModel getSystemItemCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO "
                + " ETS_SYSTEM_ITEM("
                + " ITEM_CODE,"
                + " ITEM_NAME,"
                + " ITEM_SPEC,"
                + " ITEM_CATEGORY,"
                + " MEMO,"
                + " IS_TMP_CODE,"
                + " CREATED_BY,"
                + " MASTER_ORGANIZATION_ID"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?, ?, ?, ?)";
        sqlArgs.add(dto.getScanItemCode());
        sqlArgs.add(dto.getScanItemName());
        sqlArgs.add(dto.getScanItemSpec());
        sqlArgs.add(dto.getScanItemCategory());
        sqlArgs.add(dto.getArchiveRemark());
        sqlArgs.add(AssetsDictConstant.STATUS_YES);
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(servletConfig.getProvinceOrgId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�µ��豸�������SQL
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getItemDistributeModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "INSERT INTO "
                    + " ETS_SYSITEM_DISTRIBUTE("
                    + " SYSTEM_ID,"
                    + " ITEM_CODE,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " IS_TMP"
                    + ") VALUES ("
                    + "  NEWID() , ?, ?, ?, ?, ?)";
            sqlArgs.add(dto.getScanItemCode());
            sqlArgs.add(userAccount.getOrganizationId());
            sqlArgs.add(dto.getCreationDate());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(AssetsDictConstant.STATUS_YES);
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�µ��豸���������¼SQL
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getItemDisApplyModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "INSERT INTO "
                    + " AMS_APPLY_SYSTEM_ITEM("
                    + " ITEM_CODE,"
                    + " APPLY_OU,"
                    + " CREATION_DATE,"
                    + " CREATED_BY"
                    + ") VALUES (?, ?, ?, ?)";
            sqlArgs.add(dto.getScanItemCode());
            sqlArgs.add(userAccount.getOrganizationId());
            sqlArgs.add(dto.getCreationDate());
            sqlArgs.add(userAccount.getUserId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ������µ��豸
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getItemCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "INSERT INTO ETS_ITEM_INFO ("
                    + " SYSTEMID,"
                    + " BARCODE,"
                    + " ITEM_CODE,"
                    + " ADDRESS_ID,"
                    + " RESPONSIBILITY_USER,"
                    + " RESPONSIBILITY_DEPT,"
                    + " MAINTAIN_USER,"
                    + " ORGANIZATION_ID,"
                    + " REMARK,"
                    + " START_DATE,"
                    + " CREATED_BY,"
                    + " MANUFACTURER_ID,"
                    + " IS_SHARE,"
                    + " CONTENT_CODE,"
                    + " CONTENT_NAME,"
                    + " POWER,"
                    + " LNE_ID,"
                    + " CEX_ID,"
                    + " OPE_ID,"
                    + " NLE_ID,"
                    + " FINANCE_PROP,"
                    + " CONSTRUCT_STATUS";
            String valueStr =
                    ") VALUES ( NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            String orderType = headerDTO.getOrderType();
            sqlArgs.add(dto.getBarcode());
            sqlArgs.add(dto.getScanItemCode());
            sqlArgs.add(dto.getAddressId());
            sqlArgs.add(dto.getScanResponsibilityUser());
            sqlArgs.add(dto.getScanResponsibilityDept());
            sqlArgs.add(dto.getScanMaintainUser());
            sqlArgs.add(userAccount.getOrganizationId());
            sqlArgs.add(dto.getRemark());
            sqlArgs.add(dto.getScanStartDate());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getManufacturerId());
            sqlArgs.add(dto.getShare());
            sqlArgs.add(dto.getContentCode());
            sqlArgs.add(dto.getContentName());
            sqlArgs.add(dto.getPower());
            sqlArgs.add(dto.getLneId());
            sqlArgs.add(dto.getCexId());
            sqlArgs.add(dto.getOpeId());
            sqlArgs.add(dto.getNleId());
            sqlArgs.add(dto.getFinanceProp());
            sqlArgs.add(dto.getConstructStatus());
            if (!orderType.equals(AssetsDictConstant.RNT_CHK)) {
                valueStr += ")";
            } else {
                sqlStr += ", ATTRIBUTE1";
                valueStr += ", ?)";
                sqlArgs.add(AssetsDictConstant.RENT);
            }
            sqlStr += valueStr;
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ����촴�������Ǳ��豸SQL
     * @return SQLModel
     */
    public SQLModel getInsItemCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO AMS_INSTRUMENT_INFO("
                + " BARCODE,"
                + " ITEM_CODE,"
                + " CREATION_DATE,"
                + " CREATED_BY"
                + " ) VALUES(?, ?, GETDATE(), ?)";
        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(dto.getArchItemCode());
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����촴�������豸SQL
     * @return SQLModel
     */
    public SQLModel getRentItemCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO AMS_RENT_INFO("
                + " RENT_ID,"
                + " BARCODE,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " REMARK"
                +
                " ) VALUES( NEWID() , ?, GETDATE(), ?, ?)";
        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getRemark());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
