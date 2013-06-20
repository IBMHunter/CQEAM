package com.sino.ams.spare.version.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.sql.BaseSQLProducer;
import com.sino.ams.spare.version.dto.AmsItemVersionDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemVersionModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemVersionModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsItemVersionModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ������汾�䶯�� AMS_ITEM_VERSION ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemVersionDTO ���β���������
     */
    public AmsItemVersionModel(SfUserDTO userAccount, AmsItemVersionDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ����ɱ����汾�䶯�� AMS_ITEM_VERSION���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
                    + " AMS_ITEM_VERSION("
                    + " BARCODE,"
                    + " ITEM_CODE,"
                    + " VENDOR_BARCODE,"
                    + " VERSION_NO,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_BY,"
                    + " TRANS_ID,"
                    + " REMARK,"
                    + " VERSION_ID"
                    + ") VALUES ("
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, AMS_ITEM_VERSION_S.NEXTVAL)";

            sqlArgs.add(amsItemVersion.getBarcode());
            sqlArgs.add(amsItemVersion.getItemCode());
            sqlArgs.add(amsItemVersion.getVendorBarcode());
            sqlArgs.add(amsItemVersion.getVersionNo());
            sqlArgs.add(amsItemVersion.getOrganizationId());
            sqlArgs.add(amsItemVersion.getCreationDate());
            sqlArgs.add(amsItemVersion.getCreatedBy());
            sqlArgs.add(amsItemVersion.getLastUpdateDate());
            sqlArgs.add(amsItemVersion.getLastUpdateBy());
            sqlArgs.add(amsItemVersion.getTransId());
            sqlArgs.add(amsItemVersion.getRemark());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ����汾�䶯�� AMS_ITEM_VERSION���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
            String sqlStr = "UPDATE AMS_ITEM_VERSION"
                    + " SET"
                    + " BARCODE = ?,"
                    + " ITEM_CODE = ?,"
                    + " VENDOR_BARCODE = ?,"
                    + " VERSION_NO = ?,"
                    + " ORGANIZATION_ID = ?,"
                    + " CREATION_DATE = ?,"
                    + " CREATED_BY = ?,"
                    + " LAST_UPDATE_DATE = ?,"
                    + " LAST_UPDATE_BY = ?,"
                    + " TRANS_ID = ?,"
                    + " REMARK = ?,"
                    + " WHERE"
                    + " VERSION_ID = ?";

            sqlArgs.add(amsItemVersion.getBarcode());
            sqlArgs.add(amsItemVersion.getItemCode());
            sqlArgs.add(amsItemVersion.getVendorBarcode());
            sqlArgs.add(amsItemVersion.getVersionNo());
            sqlArgs.add(amsItemVersion.getOrganizationId());
            sqlArgs.add(amsItemVersion.getCreationDate());
            sqlArgs.add(amsItemVersion.getCreatedBy());
            sqlArgs.add(amsItemVersion.getLastUpdateDate());
            sqlArgs.add(amsItemVersion.getLastUpdateBy());
            sqlArgs.add(amsItemVersion.getTransId());
            sqlArgs.add(amsItemVersion.getRemark());
            sqlArgs.add(amsItemVersion.getVersionId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ����汾�䶯�� AMS_ITEM_VERSION����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_ITEM_VERSION"
                + " WHERE"
                + " VERSION_ID = ?";
        sqlArgs.add(amsItemVersion.getVersionId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ����汾�䶯�� AMS_ITEM_VERSION������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " BARCODE,"
                + " ITEM_CODE,"
                + " VENDOR_BARCODE,"
                + " VERSION_NO,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " TRANS_ID,"
                + " REMARK,"
                + " VERSION_ID"
                + " FROM"
                + " AMS_ITEM_VERSION"
                + " WHERE"
                + " VERSION_ID = ?";
        sqlArgs.add(amsItemVersion.getVersionId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ����汾�䶯�� AMS_ITEM_VERSION����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " BARCODE,"
                    + " ITEM_CODE,"
                    + " VENDOR_BARCODE,"
                    + " VERSION_NO,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_BY,"
                    + " TRANS_ID,"
                    + " REMARK,"
                    + " VERSION_ID"
                    + " FROM"
                    + " AMS_ITEM_VERSION"
                    + " WHERE"
                    + " (? IS NULL OR BARCODE LIKE ?)"
                    + " AND (? IS NULL OR ITEM_CODE LIKE ?)"
                    + " AND (? IS NULL OR VENDOR_BARCODE LIKE ?)"
                    + " AND (? IS NULL OR VERSION_NO LIKE ?)"
                    + " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATED_BY LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_BY LIKE ?)"
                    + " AND (? IS NULL OR TRANS_ID LIKE ?)"
                    + " AND (? IS NULL OR REMARK LIKE ?)"
                    + " AND (? IS NULL OR VERSION_ID LIKE ?)";
            sqlArgs.add(amsItemVersion.getBarcode());
            sqlArgs.add(amsItemVersion.getBarcode());
            sqlArgs.add(amsItemVersion.getItemCode());
            sqlArgs.add(amsItemVersion.getItemCode());
            sqlArgs.add(amsItemVersion.getVendorBarcode());
            sqlArgs.add(amsItemVersion.getVendorBarcode());
            sqlArgs.add(amsItemVersion.getVersionNo());
            sqlArgs.add(amsItemVersion.getVersionNo());
            sqlArgs.add(amsItemVersion.getOrganizationId());
            sqlArgs.add(amsItemVersion.getOrganizationId());
            sqlArgs.add(amsItemVersion.getCreationDate());
            sqlArgs.add(amsItemVersion.getCreationDate());
            sqlArgs.add(amsItemVersion.getCreatedBy());
            sqlArgs.add(amsItemVersion.getCreatedBy());
            sqlArgs.add(amsItemVersion.getLastUpdateDate());
            sqlArgs.add(amsItemVersion.getLastUpdateDate());
            sqlArgs.add(amsItemVersion.getLastUpdateBy());
            sqlArgs.add(amsItemVersion.getLastUpdateBy());
            sqlArgs.add(amsItemVersion.getTransId());
            sqlArgs.add(amsItemVersion.getTransId());
            sqlArgs.add(amsItemVersion.getRemark());
            sqlArgs.add(amsItemVersion.getRemark());
            sqlArgs.add(amsItemVersion.getVersionId());
            sqlArgs.add(amsItemVersion.getVersionId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcode �����ѯ����SQL��
     * ����Զ��������ݱ����汾�䶯�� AMS_ITEM_VERSION��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " ITEM_CODE,"
                + " VENDOR_BARCODE,"
                + " VERSION_NO,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " TRANS_ID,"
                + " REMARK,"
                + " VERSION_ID"
                + " FROM"
                + " AMS_ITEM_VERSION"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� itemCode �����ѯ����SQL��
     * ����Զ��������ݱ����汾�䶯�� AMS_ITEM_VERSION��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param itemCode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByItemCodeModel(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " BARCODE,"
                + " VENDOR_BARCODE,"
                + " VERSION_NO,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " TRANS_ID,"
                + " REMARK,"
                + " VERSION_ID"
                + " FROM"
                + " AMS_ITEM_VERSION"
                + " WHERE"
                + " ITEM_CODE = ?";
        sqlArgs.add(itemCode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     *
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
        if (foreignKey.equals("barcode")) {
            sqlModel = getDataByBarcodeModel(amsItemVersion.getBarcode());
        } else if (foreignKey.equals("itemCode")) {
            sqlModel = getDataByItemCodeModel(amsItemVersion.getItemCode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcode ��������ɾ��SQL��
     * ����Զ��������ݱ����汾�䶯�� AMS_ITEM_VERSION����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param barcode String
     * @return SQLModel ��������ɾ����SQLModel
     */
    private SQLModel getDeleteByBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " ITEM_CODE,"
                + " VENDOR_BARCODE,"
                + " VERSION_NO,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " TRANS_ID,"
                + " REMARK,"
                + " VERSION_ID"
                + " FROM"
                + " AMS_ITEM_VERSION"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� itemCode ��������ɾ��SQL��
     * ����Զ��������ݱ����汾�䶯�� AMS_ITEM_VERSION����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param itemCode String
     * @return SQLModel ��������ɾ����SQLModel
     */
    private SQLModel getDeleteByItemCodeModel(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " BARCODE,"
                + " VENDOR_BARCODE,"
                + " VERSION_NO,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " TRANS_ID,"
                + " REMARK,"
                + " VERSION_ID"
                + " FROM"
                + " AMS_ITEM_VERSION"
                + " WHERE"
                + " ITEM_CODE = ?";
        sqlArgs.add(itemCode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���������ֶ�ɾ������
     *
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
        if (foreignKey.equals("barcode")) {
            sqlModel = getDeleteByBarcodeModel(amsItemVersion.getBarcode());
        } else if (foreignKey.equals("itemCode")) {
            sqlModel = getDeleteByItemCodeModel(amsItemVersion.getItemCode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ����汾�䶯�� AMS_ITEM_VERSIONҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " BARCODE,"
                    + " ITEM_CODE,"
                    + " VENDOR_BARCODE,"
                    + " VERSION_NO,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_BY,"
                    + " TRANS_ID,"
                    + " REMARK,"
                    + " VERSION_ID"
                    + " FROM"
                    + " AMS_ITEM_VERSION"
                    + " WHERE"
                    + " (? IS NULL OR BARCODE LIKE ?)"
                    + " AND (? IS NULL OR ITEM_CODE LIKE ?)"
                    + " AND (? IS NULL OR VENDOR_BARCODE LIKE ?)"
                    + " AND (? IS NULL OR VERSION_NO LIKE ?)"
                    + " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATED_BY LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_BY LIKE ?)"
                    + " AND (? IS NULL OR TRANS_ID LIKE ?)"
                    + " AND (? IS NULL OR REMARK LIKE ?)"
                    + " AND (? IS NULL OR VERSION_ID LIKE ?)";
            sqlArgs.add(amsItemVersion.getBarcode());
            sqlArgs.add(amsItemVersion.getBarcode());
            sqlArgs.add(amsItemVersion.getItemCode());
            sqlArgs.add(amsItemVersion.getItemCode());
            sqlArgs.add(amsItemVersion.getVendorBarcode());
            sqlArgs.add(amsItemVersion.getVendorBarcode());
            sqlArgs.add(amsItemVersion.getVersionNo());
            sqlArgs.add(amsItemVersion.getVersionNo());
            sqlArgs.add(amsItemVersion.getOrganizationId());
            sqlArgs.add(amsItemVersion.getOrganizationId());
            sqlArgs.add(amsItemVersion.getCreationDate());
            sqlArgs.add(amsItemVersion.getCreationDate());
            sqlArgs.add(amsItemVersion.getCreatedBy());
            sqlArgs.add(amsItemVersion.getCreatedBy());
            sqlArgs.add(amsItemVersion.getLastUpdateDate());
            sqlArgs.add(amsItemVersion.getLastUpdateDate());
            sqlArgs.add(amsItemVersion.getLastUpdateBy());
            sqlArgs.add(amsItemVersion.getLastUpdateBy());
            sqlArgs.add(amsItemVersion.getTransId());
            sqlArgs.add(amsItemVersion.getTransId());
            sqlArgs.add(amsItemVersion.getRemark());
            sqlArgs.add(amsItemVersion.getRemark());
            sqlArgs.add(amsItemVersion.getVersionId());
            sqlArgs.add(amsItemVersion.getVersionId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    //---------------------------------------------------------------------

    /**
     * ���ܣ���ѯ�Ƿ����ĳ�豸����
     * @return String ����Ϊ1���ظ�����1��������Ϊ0.
     */
    public SQLModel getHasItemModel(){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
        String sqlStr = "SELECT 1                     \n" +
                " FROM   ETS_SYSTEM_ITEM ESI         \n" +
                " WHERE  ESI.ITEM_NAME = ? AND       \n" +
                "       ESI.ITEM_SPEC = ? AND        \n" +
                "       ESI.ITEM_CATEGORY = ?   AND  \n" +
                "       ROWNUM < 2";
        sqlArgs.add(amsItemVersion.getItemCode());
        sqlArgs.add(amsItemVersion.getItemSpec());
        sqlArgs.add(amsItemVersion.getItemCategory());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

	/**
	 * ���ܣ�����ĳ�豸����
	 * @return SQLModel
	 */
	public SQLModel getCreateSysItemModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemVersionDTO amsItemVersion = (AmsItemVersionDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSTEM_ITEM \n" +
                "       (ITEM_CODE,ITEM_NAME,             /*�豸���룬�豸����*/\n" +
                "        ITEM_SPEC,ITEM_CATEGORY,         /*�豸�ͺ�,�豸���*/\n" +
                "        ENABLED,MEMO,                    /*�Ƿ���Ч��ע��*/\n" +
                "        MASTER_ORGANIZATION_ID)          /*����֯����*/\n" +
                " VALUES " +
                "        ( ETS_SYSTEM_ITEM_S.NEXTVAL,?,?,?,'Y',?,86)";
        sqlArgs.add(amsItemVersion.getItemName());
        sqlArgs.add(amsItemVersion.getItemSpec());
        sqlArgs.add(amsItemVersion.getItemCategory());
        sqlArgs.add(amsItemVersion.getRemark());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
		return sqlModel;
    }
}
