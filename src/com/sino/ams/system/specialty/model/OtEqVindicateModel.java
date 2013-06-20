package com.sino.ams.system.specialty.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.specialty.dto.OtherDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-12-20
 * Time: 11:52:42
 * To change this template use File | Settings | File Templates.
 */
public class OtEqVindicateModel extends AMSSQLProducer {
    private SfUserDTO sfUser = null;

    /**
     * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemInfoDTO ���β���������
     */
    public OtEqVindicateModel(SfUserDTO userAccount, OtherDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    public SQLModel selectItemInfo() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO amsInstrumentInfo = (OtherDTO) dtoParameter;
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_SYSTEM_ITEM ESI\n" +
                " WHERE ESI.ITEM_NAME =?\n" +
                "   AND ESI.ITEM_SPEC =?\n" +
                "   AND ESI.ITEM_CATEGORY =?";
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemCategory());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;

    }

    public SQLModel insertIntoItem() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO amsInstrumentInfo = (OtherDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSTEM_ITEM\n" +
                "  (ITEM_CODE,\n" +
                "   ITEM_NAME,\n" +
                "   ITEM_SPEC,\n" +
                "   ITEM_CATEGORY,\n" +
                "   ITEM_UNIT,\n" +
                "   VENDOR_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   IS_TMP_CODE)\n" +
                "VALUES\n" +
                "  (?, ?, ?, ?, '��', ?, GETDATE(), ?, 'Y')";
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemCategory());
        sqlArgs.add(amsInstrumentInfo.getVendorId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel selectDis(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_SYSITEM_DISTRIBUTE ESD\n" +
                " WHERE ESD.ITEM_CODE = ?\n" +
                "   AND ESD.ORGANIZATION_ID = ?";
        sqlArgs.add(itemCode);
        sqlArgs.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel insertIntoDis(String itemcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO amsInstrumentInfo = (OtherDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSITEM_DISTRIBUTE\n" +
                "  (SYSTEM_ID,\n" +
                "   ITEM_CODE,\n" +
                "   ORGANIZATION_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   IS_TMP)\n" +
                "VALUES\n" +
                "  ( NEWID() , ?, ?, GETDATE(), ?, 'Y')";
        sqlArgs.add(itemcode);
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel insertIntoD() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO amsInstrumentInfo = (OtherDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSITEM_DISTRIBUTE\n" +
                "  (SYSTEM_ID,\n" +
                "   ITEM_CODE,\n" +
                "   ORGANIZATION_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   IS_TMP)\n" +
                "VALUES\n" +
                "  ( NEWID() , ?, ?, GETDATE(), ?, 'Y')";
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel updateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_SYSTEM_ITEM  SET  VENDOR_ID=?  \n" +
                " WHERE ITEM_NAME =?\n" +
                "   AND ITEM_SPEC =?\n" +
                "   AND ITEM_CATEGORY = ?";

        sqlArgs.add(etsItemInfo.getVendorId());
        sqlArgs.add(etsItemInfo.getItemName());
        sqlArgs.add(etsItemInfo.getItemSpec());
        sqlArgs.add(etsItemInfo.getItemCategory());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
            String sqlStr = "INSERT INTO ETS_ITEM_INFO\n" +
                    "  (SYSTEMID,\n" +
                    "   BARCODE,  \n" +
                    "   ITEM_QTY,\n" +
                    "   REMARK,\n" +
                    "   START_DATE,\n" +
                    "   PROJECTID,\n" +
                    "   CREATION_DATE,\n" +
                    "   CREATED_BY,\n" +
                    "   ADDRESS_ID,\n" +
                    "   MANUFACTURER_ID,\n" +
                    "   IS_SHARE,\n" +
                    "   CONTENT_CODE,\n" +
                    "   CONTENT_NAME,\n" +
                    "   ORGANIZATION_ID," +
                    "   ITEM_CODE)\n" +
                    "VALUES\n" +
                    "  ( NEWID() ,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   GETDATE(),\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?)";
            sqlArgs.add(etsItemInfo.getBarcode1());
            sqlArgs.add(etsItemInfo.getItemQty());
            sqlArgs.add(etsItemInfo.getRemark());
            sqlArgs.add(etsItemInfo.getStartDate());
            sqlArgs.add(etsItemInfo.getPrjId());
            sqlArgs.add(sfUser.getUserId());
            sqlArgs.add(etsItemInfo.getAddressId());
            sqlArgs.add(etsItemInfo.getManufacturerId());
            sqlArgs.add(etsItemInfo.getShare());
            sqlArgs.add(etsItemInfo.getContentCode());
            sqlArgs.add(etsItemInfo.getContentName());
            sqlArgs.add(etsItemInfo.getCompany());
//            sqlArgs.add(sfUser.getOrganizationId());
            sqlArgs.add(etsItemInfo.getItemCode());


            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException e) {
            e.printLog();
            throw new SQLModelException(e);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_ITEM_INFO\n" +
                "   SET BARCODE       = ?,\n" +
                "       ITEM_QTY         = ?,\n" +
                "       REMARK           = ?,\n" +
                "       PROJECTID        = ?,\n" +
                "       LAST_UPDATE_DATE = GETDATE(),\n" +
                "       LAST_UPDATE_BY   = ?,\n" +
                "       ADDRESS_ID       = ?,\n" +
                "       MANUFACTURER_ID  = ?,\n" +
                "       IS_SHARE         = ?,\n" +
                "       CONTENT_CODE     = ?,\n" +
                "       CONTENT_NAME     = ?,\n" +
                "       ITEM_CODE        = ?" +
//                "       ORGANIZATION_ID   =?\n" +
                " WHERE SYSTEMID = ?";

        sqlArgs.add(etsItemInfo.getBarcode1());
        sqlArgs.add(etsItemInfo.getItemQty());
        sqlArgs.add(etsItemInfo.getRemark());
        sqlArgs.add(etsItemInfo.getPrjId());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsItemInfo.getAddressId());
        sqlArgs.add(etsItemInfo.getManufacturerId());
        sqlArgs.add(etsItemInfo.getShare());
        sqlArgs.add(etsItemInfo.getContentCode());
        sqlArgs.add(etsItemInfo.getContentName());
        sqlArgs.add(etsItemInfo.getItemCode());
//        sqlArgs.add(etsItemInfo.getOrganizationId());
//        sqlArgs.add(etsItemInfo.getCompany());
        sqlArgs.add(etsItemInfo.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
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
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "SELECT EII.SYSTEMID,\n" +
                "       EII.BARCODE BARCODE1,\n" +
                "       EII.DISABLE_DATE,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       EII.ITEM_QTY,\n" +
                "       ESI.YEARS,\n" +
                "       EII.REMARK," +
                "       EII.START_DATE," +
                "       EPPA.NAME PRJ_NAME,\n" +
                "       EII.PROJECTID PRJ_ID,\n" +
                "       ESI.MASTER_ORGANIZATION_ID,\n" +
                "       ESI.ITEM_CATEGORY,\n" +
                "       EII.VENDOR_BARCODE,\n" +
                "       EMPV.VENDOR_NAME,\n" +
                "       EII.ITEM_CODE,\n" +
                "       EII.ADDRESS_ID,\n" +
                "       EII.MANUFACTURER_ID,\n" +
                "       AM.MANUFACTURER_NAME,\n" +
                "       EII.CONTENT_CODE,\n" +
                "       EII.CONTENT_NAME,\n" +
                "       EII.IS_SHARE,\n" +
//                "       '�������'  ITEM_CATEGORY_DESC,\n" +
                "       EO.WORKORDER_OBJECT_NAME\n" +
                "  FROM ETS_ITEM_INFO       EII,\n" +
                "       ETS_SYSTEM_ITEM     ESI,\n" +
                "       ETS_PA_PROJECTS_ALL EPPA,\n" +
                "       ETS_MIS_PO_VENDORS  EMPV,\n" +
                "       AMS_OBJECT_ADDRESS  AOA,\n" +
                "       AMS_MANUFACTURER  AM,\n" +
                "       ETS_OBJECT          EO\n" +
                " WHERE ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                "   AND EII.PROJECTID *= EPPA.PROJECT_ID\n" +
                "   AND ESI.VENDOR_ID *= EMPV.VENDOR_ID\n" +
                "   AND AOA.OBJECT_NO *= EO.WORKORDER_OBJECT_NO\n" +
                "   AND EII.ADDRESS_ID *= AOA.ADDRESS_ID " +
                "   AND EII.MANUFACTURER_ID *= AM.MANUFACTURER_ID " +
                "   AND EII.SYSTEMID = ?";
        sqlArgs.add(etsItemInfo.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "SELECT EII.SYSTEMID,\n" +
                "       EII.BARCODE BARCODE1,\n" +
                "       EFV.VALUE ITEM_CATE_GORY_DESC,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       EII.ITEM_QTY,\n" +
                "       EMPV.VENDOR_NAME,\n" +
                "       EII.DISABLE_DATE,\n" +
                "       EPPA.NAME,\n" +
                "       ESI.MASTER_ORGANIZATION_ID,\n" +
                "       ESI.ITEM_CATEGORY,\n" +
                "       EII.VENDOR_BARCODE,\n" +
                "       EO.WORKORDER_OBJECT_CODE,\n" +
                "       EO.WORKORDER_OBJECT_NAME" +
                "  FROM ETS_ITEM_INFO       EII,\n" +
                "       ETS_SYSTEM_ITEM     ESI,\n" +
                "       ETS_PA_PROJECTS_ALL EPPA,\n" +
                "       ETS_MIS_PO_VENDORS  EMPV,\n" +
                "       ETS_FLEX_VALUES     EFV,\n" +
                "       ETS_FLEX_VALUE_SET  EFVS,\n" +
                "       AMS_OBJECT_ADDRESS  AOA,\n" +
                "       ETS_OBJECT          EO" +
                " WHERE EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                "   AND EII.PROJECTID *= EPPA.PROJECT_ID\n" +
                "   AND ESI.VENDOR_ID *= EMPV.VENDOR_ID\n" +
                "   AND EFVS.CODE = 'ITEM_TYPE'\n" +
                "   AND EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID\n" +
                "   AND EFV.CODE = ESI.ITEM_CATEGORY\n" +
                "   AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND AOA.ADDRESS_ID = EII.ADDRESS_ID \n" +
                "   AND ESI.ITEM_CATEGORY = ?\n" +
                " AND    ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)\n" +
                " AND    ( " + SyBaseSQLUtil.isNull() + "  OR EII.BARCODE LIKE ?)\n" +
                " AND    ( " + SyBaseSQLUtil.isNull() + "  OR ESI.VENDOR_ID LIKE ?)\n" +
                "  AND ( " + SyBaseSQLUtil.isNull() + "  OR AMS_PUB_PKG.GET_WORKORDER_OBJECT_NAME(EII.ADDRESS_ID) LIKE ?)\n";
        ;
        sqlArgs.add(etsItemInfo.getItemCategory());
        sqlArgs.add(etsItemInfo.getItemName());
        sqlArgs.add(etsItemInfo.getItemName());
        sqlArgs.add(etsItemInfo.getBarcode1());
        sqlArgs.add(etsItemInfo.getBarcode1());
        sqlArgs.add(etsItemInfo.getVendorId());
        sqlArgs.add(etsItemInfo.getVendorId());
        sqlArgs.add(etsItemInfo.getWorkorderObjectName());
        sqlArgs.add(etsItemInfo.getWorkorderObjectName());

        if (etsItemInfo.getDisable().equals("��")) {
            sqlStr += "AND  " + SyBaseSQLUtil.isNotNull("EII.DISABLE_DATE") + " \n";
        } else if (etsItemInfo.getDisable().equals("��")) {
            sqlStr += "AND EII.DISABLE_DATE  " + SyBaseSQLUtil.isNullNoParam() + " \n";
        }
        if ((!sfUser.isProvinceUser()) && (!sfUser.isSysAdmin())) {
            sqlStr += "AND EII.ORGANIZATION_ID = ?\n";
            sqlArgs.add(sfUser.getOrganizationId());
        }
        sqlStr += "ORDER  BY EII.ADDRESS_ID,EII.ITEM_CODE";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getItemCategoryModel(String itemCategory) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EFV.VALUE ITEM_CATEGORY_DESC\n" +
                "  FROM ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
                " WHERE EFV.FLEX_VALUE_SET_ID = 1\n" +
                "   AND EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID\n" +
                "   AND EFVS.CODE = 'ITEM_TYPE'\n" +
                "   AND EFV.CODE = ?";

        sqlArgs.add(itemCategory);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getCode() {
        SQLModel sqlModel = new SQLModel();
        List list = new ArrayList();
        String str = "SELECT EOC.COMPANY_CODE\n" +
                "  FROM ETS_OU_CITY_MAP EOC\n" +
                " WHERE EOC.ORGANIZATION_ID = ?";
        list.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(str);
        sqlModel.setArgs(list);
        return sqlModel;
    }

    public SQLModel getEnableModel(String systemId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE"
                + " ETS_ITEM_INFO"
                + " SET"
                + " DISABLE_DATE = NULL"
                + " WHERE"
                + " SYSTEMID =?";
        sqlArgs.add(systemId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getDisableModel(String systemId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE"
                + " ETS_ITEM_INFO"
                + " SET"
                + " DISABLE_DATE = GETDATE()"
                + " WHERE"
                + " SYSTEMID =  ? ";
        sqlArgs.add(systemId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel selectItem() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_SYSTEM_ITEM ESI\n" +
                " WHERE ESI.ITEM_NAME =?\n" +
                "   AND ESI.ITEM_SPEC =?\n" +
                "   AND ESI.ITEM_CATEGORY =?";
        sqlArgs.add(etsItemInfo.getItemName());
        sqlArgs.add(etsItemInfo.getItemSpec());
        sqlArgs.add(etsItemInfo.getItemCategory());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;

    }

    public SQLModel selectNO() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "SELECT 1  FROM ETS_ITEM_INFO  EII WHERE EII.BARCODE=?";
        sqlArgs.add(etsItemInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;

    }

    public SQLModel insertDataBase(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSTEM_ITEM\n" +
                "  (ITEM_CODE, ITEM_NAME, ITEM_SPEC, ITEM_CATEGORY, VENDOR_ID, CREATED_BY,IS_TMP_CODE)\n" +
                "VALUES\n" +
                "  (?,?,?,?,?,?,'Y')";
        sqlArgs.add(itemCode);
        sqlArgs.add(etsItemInfo.getItemName());
        sqlArgs.add(etsItemInfo.getItemSpec());
        sqlArgs.add(etsItemInfo.getItemCategory());
        sqlArgs.add(etsItemInfo.getVendorId());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO���ݲ���SQLModel��
     *
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO etsItemInfo = (OtherDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " ETS_ITEM_INFO("
                + " SYSTEMID,"
                + " BARCODE,"
                + " ITEM_CODE,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " ITEM_STATUS,"
                + " ORGANIZATION_ID,"
                + " ATTRIBUTE1"
                + ") VALUES ("
                + "  NEWID(), ?, ?, GETDATE(), ?, '����',? ,?)";

        sqlArgs.add(etsItemInfo.getBarcode());
        sqlArgs.add(etsItemInfo.getItemCode());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(DictConstant.RENT);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    public SQLModel insertIntoApp(String itemcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        OtherDTO amsInstrumentInfo = (OtherDTO) dtoParameter;
        String sqlStr = "INSERT INTO AMS_APPLY_SYSTEM_ITEM\n" +
                " ( ITEM_CODE,\n" +
                "   APPLY_OU," +
                "   CREATION_DATE,\n" +
                "   CREATED_BY)\n" +
                "VALUES\n" +
                "  (?, ?, GETDATE(), ?)";
        sqlArgs.add(itemcode);
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�жϵ�ǰ�û��Ƿ���Ȩ��ִ�����ݱ༭������BARCODE �Ĵ�����
     *
     * @param barcode String
     * @return SQLModel
     */
    public SQLModel getVerifyBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql =
                " SELECT"
                        + " 1 "
                        + " FROM "
                        + " ETS_ITEM_INFO AII "
                        + " WHERE AII.BARCODE = ?";
        strArg.add(barcode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }
}
