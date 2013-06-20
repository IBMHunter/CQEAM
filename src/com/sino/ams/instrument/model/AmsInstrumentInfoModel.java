package com.sino.ams.instrument.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.instrument.dto.AmsInstrumentInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: AmsInstrumentInfoModel</p>
 * <p>Description:�����Զ�����SQL��������AmsInstrumentInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class AmsInstrumentInfoModel extends AMSSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ������Ǳ����(EAM) AMS_INSTRUMENT_INFO ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentInfoDTO ���β���������
     */
    public AmsInstrumentInfoModel(SfUserDTO userAccount, AmsInstrumentInfoDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }


    /**
     * ���ܣ�����Զ����������Ǳ����(EAM) AMS_INSTRUMENT_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "INSERT INTO AMS_INSTRUMENT_INFO\n" +
                "  (BARCODE,\n" +
                "   ITEM_CODE,\n" +
                "   INSTRU_USAGE,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY)\n" +
                "VALUES \n" +
                "  ( ?, ?, ?, GETDATE(),?)";

        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel insertDistrbute(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO ETS_SYSITEM_DISTRIBUTE\n" +
                "  (SYSTEM_ID, ITEM_CODE, ORGANIZATION_ID, IS_TMP)\n" +
                "VALUES\n" +
                "  ( NEWID() , ?, ?, 'Y')";
        sqlArgs.add(itemCode);
        sqlArgs.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel insertDataBase(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSTEM_ITEM\n" +
                "  (ITEM_CODE, ITEM_NAME, ITEM_SPEC, ITEM_CATEGORY, VENDOR_ID, CREATED_BY,IS_TMP_CODE)\n" +
                "VALUES\n" +
                "  (?,?,?,'INSTRUMENT',?,?,'Y')";
        sqlArgs.add(itemCode);
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getVendorId());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel selectAddressId() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AOA.ADDRESS_ID, EO.WORKORDER_OBJECT_NAME\n" +
                "  FROM ETS_OBJECT EO, AMS_OBJECT_ADDRESS AOA\n" +
                " WHERE EO.OBJECT_CATEGORY = '77'\n" +
                "   AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND EO.ORGANIZATION_ID = ?";
        sqlArgs.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel insertDataNo(String itemCode, String addressId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_ITEM_INFO\n" +
                "  (BARCODE,\n" +
                "   SYSTEMID,\n" +
                "   ITEM_QTY,\n" +
                "   ITEM_CODE,\n" +
                "   CREATED_BY,\n" +
                "   ORGANIZATION_ID," +
//                "   ADDRESS_ID," +
                "   ATTRIBUTE3," +     //������;
                "   RESPONSIBILITY_USER," +
                "   RESPONSIBILITY_DEPT," +
                "   ADDRESS_ID, " +
                "   ATTRIBUTE2," +//����
                "   MAINTAIN_DEPT," + //ʹ�ò���
                "   MAINTAIN_USER," +
                "   ITEM_STATUS," + //�����Ǳ�״̬
                "   REMARK" + //�����Ǳ�����
                ")\n" +
                "   VALUES\n" +
                "  (?, NEWID() ,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getItemQty());
        sqlArgs.add(itemCode);
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(sfUser.getOrganizationId());
//        sqlArgs.add(addressId);
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());    //�����Ǿ���;
//        sqlArgs.add(amsInstrumentInfo.getResponsibilityUser());
        sqlArgs.add(amsInstrumentInfo.getUserId());
//        sqlArgs.add(sfUser.getUserId());
//        sqlArgs.add(amsInstrumentInfo.getResponsibilityDept());
        sqlArgs.add(amsInstrumentInfo.getDeptId());

        sqlArgs.add(amsInstrumentInfo.getAddressId());
        sqlArgs.add(amsInstrumentInfo.getUnitPrice());
        sqlArgs.add(amsInstrumentInfo.getMaintainDept());
        sqlArgs.add(amsInstrumentInfo.getMaintainUser());
        sqlArgs.add(amsInstrumentInfo.getItemStatus());
        sqlArgs.add(amsInstrumentInfo.getRemark());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel selectNO() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1  FROM ETS_ITEM_INFO  EII WHERE EII.BARCODE=?";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel selectInfo() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1  FROM AMS_INSTRUMENT_INFO   AII WHERE AII.BARCODE=?";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;

    }

    public SQLModel selectItemInfo() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_SYSTEM_ITEM ESI\n" +
                " WHERE ESI.ITEM_NAME =?\n" +
                "   AND ESI.ITEM_SPEC =?\n" +
                "   AND ESI.ITEM_CATEGORY = 'INSTRUMENT'";
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;

    }

    public SQLModel update() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_SYSTEM_ITEM  SET  VENDOR_ID=?,ITEM_NAME =?,ITEM_SPEC =?  \n" +
                " WHERE ITEM_CODE=?";
        sqlArgs.add(amsInstrumentInfo.getVendorId());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel updateItem() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_SYSTEM_ITEM  SET  VENDOR_ID=?  \n" +
                " WHERE ITEM_NAME =?\n" +
                "   AND ITEM_SPEC =?\n" +
                "   AND ITEM_CATEGORY = 'INSTRUMENT'";
        sqlArgs.add(amsInstrumentInfo.getVendorId());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����������Ǳ����(EAM) AMS_INSTRUMENT_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel updateIfo() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_INSTRUMENT_INFO"
                + " SET"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?," +
                "   INSTRU_USAGE=? "
                + " WHERE"
                + " BARCODE = ?";

        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());
        sqlArgs.add(amsInstrumentInfo.getBarcode1());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_INSTRUMENT_INFO"
                + " SET"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?," +
                "   INSTRU_USAGE=? "
                + " WHERE"
                + " BARCODE = ?";

        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());
        sqlArgs.add(amsInstrumentInfo.getBarcode1());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����������Ǳ����(EAM) AMS_INSTRUMENT_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_INSTRUMENT_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����������Ǳ����(EAM) AMS_INSTRUMENT_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {       // ����ϸ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "SELECT " +
                "  EII.SYSTEMID,\n" +
                "  ESI.ITEM_NAME,\n" +
                "  EII.ITEM_QTY,\n" +
                "  EII.BARCODE,\n" +
                "  EII.ITEM_STATUS,\n" +
                "  EII.ATTRIBUTE3 INSTRU_USAGE,\n" +
                "  ESI.ITEM_CATEGORY,\n" +
                "  EII.MAINTAIN_USER  USER_ID2, \n" +      //--��ǰʹ����
                "  EII.RESPONSIBILITY_USER USER_ID,\n" +      //--������
                "  AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
//                "  AMS_PUB_PKG.GET_USER_NAME(EII.MAINTAIN_USER) MAINTAIN_USER,\n" +
                " EII.MAINTAIN_USER MAINTAIN_USER," +
                "  EII.RESPONSIBILITY_DEPT DEPT_ID,\n" +
                "       AMS_PUB_PKG.GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) RESPONSIBILITY_DEPT,\n" +
                "       EII.MAINTAIN_DEPT ,\n" +
                "       AMS_PUB_PKG.GET_DEPT_NAME(EII.MAINTAIN_DEPT) MAINTAIN_DEPT_NAME,\n" +
                "       EII.ADDRESS_ID,\n" +
                "       AMS_PUB_PKG.GET_WORKORDER_OBJECT_NAME(EII.ADDRESS_ID) ADDRESSLOC,\n" +
                "  ESI.ITEM_CODE," +
                "  EII.ATTRIBUTE2 UNIT_PRICE,\n" +
                "  EMPV.VENDOR_NAME,\n" +
                "  EMPV.VENDOR_ID,\n" +
                "  ESI.ITEM_SPEC,\n" +
                "  EII.REMARK\n" +
                " FROM" +
                " ETS_ITEM_INFO EII, " +
                " ETS_SYSTEM_ITEM ESI, " +
                " ETS_MIS_PO_VENDORS EMPV\n" +
                " WHERE " +
                " ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                " AND ESI.VENDOR_ID *= EMPV.VENDOR_ID\n" +
                " AND EII.BARCODE = ?";
        sqlArgs.add(amsInstrumentInfo.getBarcode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����������Ǳ����(EAM) AMS_INSTRUMENT_INFO����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " BARCODE,"
                + " ITEM_CODE,"
                + " INSTRU_USAGE,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " CURR_KEEP_USER"
                + " FROM"
                + " AMS_INSTRUMENT_INFO"
                + " WHERE"
                + "( " + SyBaseSQLUtil.isNull() + "  OR BARCODE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR ITEM_CODE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR INSTRU_USAGE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CURR_KEEP_USER LIKE ?)";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());
        sqlArgs.add(amsInstrumentInfo.getCreationDate());
        sqlArgs.add(amsInstrumentInfo.getCreationDate());
        sqlArgs.add(amsInstrumentInfo.getCreatedBy());
        sqlArgs.add(amsInstrumentInfo.getCreatedBy());
        sqlArgs.add(amsInstrumentInfo.getLastUpdateDate());
        sqlArgs.add(amsInstrumentInfo.getLastUpdateDate());
        sqlArgs.add(amsInstrumentInfo.getLastUpdateBy());
        sqlArgs.add(amsInstrumentInfo.getLastUpdateBy());
        sqlArgs.add(amsInstrumentInfo.getCurrKeepUser());
        sqlArgs.add(amsInstrumentInfo.getCurrKeepUser());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcodeNo �����ѯ����SQL��
     * ����Զ��������������Ǳ����(EAM) AMS_INSTRUMENT_INFO��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param barcodeNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeNoModel(String barcodeNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " ITEM_CODE,"
                + " INSTRU_USAGE,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " CURR_KEEP_USER"
                + " FROM"
                + " AMS_INSTRUMENT_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcodeNo);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� itemCode �����ѯ����SQL��
     * ����Զ��������������Ǳ����(EAM) AMS_INSTRUMENT_INFO��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param itemCode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByItemCodeModel(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " BARCODE,"
                + " INSTRU_USAGE,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " CURR_KEEP_USER"
                + " FROM"
                + " AMS_INSTRUMENT_INFO"
                + " WHERE"
                + " ITEM_CODE = ?";
        sqlArgs.add(itemCode);

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
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        if (foreignKey.equals("barcodeNo")) {
            sqlModel = getDataByBarcodeNoModel(amsInstrumentInfo.getBarcode());
        } else if (foreignKey.equals("itemCode")) {
            sqlModel = getDataByItemCodeModel(amsInstrumentInfo.getItemCode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcodeNo ��������ɾ��SQL��
     * ����Զ��������������Ǳ����(EAM) AMS_INSTRUMENT_INFO ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param barcodeNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByBarcodeNoModel(String barcodeNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_INSTRUMENT_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcodeNo);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� itemCode ��������ɾ��SQL��
     * ����Զ��������������Ǳ����(EAM) AMS_INSTRUMENT_INFO ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param itemCode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByItemCodeModel(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_INSTRUMENT_INFO"
                + " WHERE"
                + " ITEM_CODE = ?";
        sqlArgs.add(itemCode);

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
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        if (foreignKey.equals("barcodeNo")) {
            sqlModel = getDeleteByBarcodeNoModel(amsInstrumentInfo.getBarcode());
        } else if (foreignKey.equals("itemCode")) {
            sqlModel = getDeleteByItemCodeModel(amsInstrumentInfo.getItemCode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ������Ǳ�ά����ѯ���ܡ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {     //�����Ǳ�ά����ѯsql
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
//        String sqlStr = "SELECT " +
//                        " EII.BARCODE,\n" +
//                        " EII.SYSTEMID,\n"+
////                        " EII.MAINTAIN_USER, \n" +      //--��ǰʹ����
//                        " '�����Ǳ�' ITEM_CATEGORY,\n"+
//                        " ESI.ITEM_NAME,\n" +
//                        " ESI.ITEM_SPEC,\n" +
//                        " EII.CREATED_BY,\n" +
//                        " EII.CREATION_DATE,\n" +
//                        " EII.RESPONSIBILITY_DEPT,\n" +
//                        " EII.MAINTAIN_USER MAINTAIN_USER,"+
////                        " AMS_PUB_PKG.GET_USER_NAME(EII.MAINTAIN_USER) MAINTAIN_USER,\n" +
//                        " AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
//                        " AMS_PUB_PKG.GET_USER_NAME(EII.CREATED_BY) CREATED_NAME,\n" +
//                        " AMS_PUB_PKG.GET_VENDOR_NAME(ESI.VENDOR_ID) VENDOR_NAME\n" +
//                        " FROM" +
//                        " ETS_ITEM_INFO EII, " +
//                        " ETS_SYSTEM_ITEM ESI\n" +
//                        " WHERE " +
//                        " ESI.ITEM_CODE = EII.ITEM_CODE\n" +
//                        " AND ESI.ITEM_CATEGORY = 'INSTRUMENT'"+
//                        " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)\n" +
//                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)" ;
//                        " AND EII.ORGANIZATION_ID = "+sfUser.getOrganizationId()
        String sqlStr = " SELECT" +
                " EII.ORGANIZATION_ID,\n" +
                " AMS_PUB_PKG.GET_ORGNIZATION_NAME(EII.ORGANIZATION_ID) ORGNIZATION_NAME,\n" +
                " EII.BARCODE,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " AMS_PUB_PKG.GET_VENDOR_NAME(ESI.VENDOR_ID) VENDOR_NAME,\n" +
                " AMS_PUB_PKG.GET_DEPT_NAME(EII.MAINTAIN_DEPT) MAINTAIN_DEPT_NAME,\n" +
                " EII.ITEM_QTY,\n" +
                " EII.ATTRIBUTE3,\n" +      //��;
                " AMS_PUB_PKG.GET_WORKORDER_OBJECT_NAME(EII.ADDRESS_ID) OBJECT_NAME,\n" +
                " EII.MAINTAIN_USER,\n" +  //ʹ����Ա
                " EII.REMARK," +
                //                  EII.ITEM_STATUS,\n" +
                "  CASE WHEN EII.ITEM_STATUS='NORMAL' THEN '����' WHEN EII.ITEM_STATUS='BORROW' THEN '����' WHEN EII.ITEM_STATUS='SEND_REPAIR' THEN '����' WHEN EII.ITEM_STATUS='DISCARDED' THEN '����'  END  ITEM_STATUS"+
                ",\n" +
                " EII.SYSTEMID,\n" +
                " '�����Ǳ�' ITEM_CATEGORY,\n" +
                " EII.MAINTAIN_DEPT,\n" +
                " EII.ADDRESS_ID,\n" +
                //                  AMS_PUB_PKG.GET_USER_NAME( EII.MAINTAIN_USER) MAINTAIN_NAME,\n" +  //ʹ����Ա
                " AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
                " AMS_PUB_PKG.GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) RESPONSIBILITY_DEPT_NAME,\n" +
                " EII.RESPONSIBILITY_DEPT,\n" +
                " EII.ATTRIBUTE2,\n" +        //����
                " EII.RESPONSIBILITY_USER,\n" +
                " EII.CREATED_BY,\n" +
                " EII.CREATION_DATE,\n" +
                //                  EII.RESPONSIBILITY_DEPT,\n" +
                //                  EII.MAINTAIN_USER MAINTAIN_USER,\n" +
                //                  AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
                " AMS_PUB_PKG.GET_USER_NAME(EII.CREATED_BY) CREATED_NAME,\n" +
                " TO_NUMBER(EII.ATTRIBUTE1) * EII.ITEM_QTY TOTAL_PR" +
                " FROM " +
                " ETS_ITEM_INFO EII, " +
                " ETS_SYSTEM_ITEM ESI\n" +
                " WHERE " +
                " ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                " AND ESI.ITEM_CATEGORY = 'INSTRUMENT'\n" +
                " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)\n" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.ORGANIZATION_ID = ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR AMS_PUB_PKG.GET_VENDOR_NAME(ESI.VENDOR_ID) LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ITEM_STATUS LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.MAINTAIN_USER LIKE ?)";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getOrganizationId());
        sqlArgs.add(amsInstrumentInfo.getOrganizationId());
        sqlArgs.add(amsInstrumentInfo.getVendorName());
        sqlArgs.add(amsInstrumentInfo.getVendorName());
        sqlArgs.add(amsInstrumentInfo.getResponsibilityName());
        sqlArgs.add(amsInstrumentInfo.getResponsibilityName());
        sqlArgs.add(amsInstrumentInfo.getItemStatus());
        sqlArgs.add(amsInstrumentInfo.getItemStatus());
        sqlArgs.add(amsInstrumentInfo.getMaintainUser());
        sqlArgs.add(amsInstrumentInfo.getMaintainUser());
//         if((!sfUser.isProvinceUser()) && (!sfUser.isSysAdmin())){
//            sqlStr += "AND EII.ORGANIZATION_ID = ?";
//            sqlArgs.add(sfUser.getOrganizationId());
//        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ����������Ǳ����(EAM) AMS_INSTRUMENT_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getUpusageModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " ETS_ITEM_INFO"
                + " SET"
//			+ " BARCODE = ?,"
                + " ITEM_QTY = ?,"
                + " ITEM_CODE = ?,"
//            + " ADDRESS_ID=?,"
                + " ATTRIBUTE3= ?,"       //�����Ǳ���;
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?,"
                + " ITEM_STATUS = ?,"
                + " RESPONSIBILITY_USER = ?,"
                + " RESPONSIBILITY_DEPT = ?,"
//			+ " MAINTAIN_USER = ?"
                + " ATTRIBUTE2= ?,"      //�����Ǳ���
                + " ADDRESS_ID= ?,"
                + " MAINTAIN_DEPT=?,"
                + " MAINTAIN_USER=?,"
                + " REMARK=?"
                + " WHERE"
                + " BARCODE = ?";

        sqlArgs.add(amsInstrumentInfo.getItemQty());
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(amsInstrumentInfo.getInstruUsage());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsInstrumentInfo.getItemStatus());
        sqlArgs.add(amsInstrumentInfo.getUserId());
//       sqlArgs.add(amsInstrumentInfo.getResponsibilityDept());
        sqlArgs.add(amsInstrumentInfo.getDeptId());
//       sqlArgs.add(amsInstrumentInfo.getMaintainUser());
//       sqlArgs.add(amsInstrumentInfo.getUserId2());
        sqlArgs.add(amsInstrumentInfo.getUnitPrice());
        sqlArgs.add(amsInstrumentInfo.getAddressId());
        sqlArgs.add(amsInstrumentInfo.getMaintainDept());
        sqlArgs.add(amsInstrumentInfo.getMaintainUser());


        sqlArgs.add(amsInstrumentInfo.getRemark());


        sqlArgs.add(amsInstrumentInfo.getBarcode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������Ǳ����ҳ��Ĳ�ѯModel��
     * @return SQLModel
     */
    public SQLModel getSQueryModel() {
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT " +
                " EII.BARCODE,\n" +
                " EII.MAINTAIN_USER,\n" +
                " ESI.ITEM_CODE,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " ESI.VENDOR_ID,\n" +
                " EII.RESPONSIBILITY_USER,\n" +
                " EII.MAINTAIN_USER MAINTAIN_NAME,\n" +
                " AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
                " AMS_PUB_PKG.GET_VENDOR_NAME(ESI.VENDOR_ID) VENDOR_NAME,\n" +
                " EII.ORGANIZATION_ID,\n" +
                " AMS_PUB_PKG.GET_BORROW_DATE(EII.BARCODE) BORROW_DATE,\n" +
                " TRUNC(GETDATE() - AMS_PUB_PKG.GET_BORROW_DATE(EII.BARCODE)) DAYS\n" +
                " FROM " +
                " ETS_ITEM_INFO EII, ETS_SYSTEM_ITEM ESI\n" +
                " WHERE " +
                " EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                " AND ESI.ITEM_CATEGORY = 'INSTRUMENT'\n" +
                " AND  " + SyBaseSQLUtil.isNotNull("EII.MAINTAIN_USER")  +
                //                 "     AND EII.ITEM_STATUS = 'NORMAL' "+
                //                 "     AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_NUMBER(GETDATE() - AMS_PUB_PKG.GET_BORROW_DATE(EII.BARCODE)) < =?)"+
                " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)\n" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.MAINTAIN_USER LIKE ?)";
//                        " AND EII.ORGANIZATION_ID = "+sfUser.getOrganizationId()
//        sqlArgs.add(amsInstrumentInfo.getBorrowNum());
//        sqlArgs.add(amsInstrumentInfo.getBorrowNum());
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getBorrowName());
        sqlArgs.add(amsInstrumentInfo.getBorrowName());
        if (amsInstrumentInfo.getBorrowNum().equals("Y")) {
            sqlStr += " AND AMS_PUB_PKG.GET_PRE_RETURN_DATE(EII.BARCODE)>GETDATE()";
        }
        if ((!sfUser.isProvinceUser()) && (!sfUser.isSysAdmin())) {
            sqlStr += " AND EII.ORGANIZATION_ID = ?";
            sqlArgs.add(sfUser.getOrganizationId());
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
//        sqlArgs.add(sfUser.getOrganizationId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������Ǳ�䶯��ʷ
     * @return SQLModel
     */
    public SQLModel getHQueryModel() {
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT" +
                " EII.BARCODE,\n" +
                " EII.MAINTAIN_USER,\n" +
                " AITH.BORROW_USER,\n" +
                " AMS_PUB_PKG.GET_USER_NAME(AITH.BORROW_USER) BORROW_NAME,\n" +
                " ESI.ITEM_CODE,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " EII.ORGANIZATION_ID,\n" +
                //                 " EII.ITEM_STATUS,\n" +
                " AMS_PUB_PKG.GET_FLEX_VALUE(EII.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS," +
                " AITH.RETURN_USER,\n" +
                " AITH.REPAIRE_USER,\n" +
                " AMS_PUB_PKG.GET_USER_NAME(AITH.RETURN_USER) RETURN_NAME,\n" +
                //                 " AMS_PUB_PKG.GET_USER_NAME(AITH.REPAIRE_USER) RETURN_NAME,\n" +
                " AMS_PUB_PKG.GET_STATUS_NAME(AITH.TRANS_STATUS) TRANS_STATUS_NAME,\n" +
                " AITH.TRANS_NO,\n" +
                " AITH.TRANS_STATUS,\n" +
                " AITH.BORROW_DATE,\n" +
                " AITH.RETURN_DATE,\n" +
                " AITH.REPAIRE_DATE," +
                " AITH.TRANS_TYPE" +
                " FROM " +
                " ETS_ITEM_INFO      EII,\n" +
                " AMS_INSTRU_TRANS_L AITL,\n" +
                " AMS_INSTRU_TRANS_H AITH,\n" +
                " ETS_SYSTEM_ITEM    ESI\n" +
                " WHERE " +
                " EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                " AND EII.BARCODE = AITL.BARCODE\n" +
                " AND AITH.TRANS_ID = AITL.TRANS_ID\n" +
                " AND ESI.ITEM_CATEGORY = 'INSTRUMENT'\n" +
                " AND AITH.TRANS_STATUS = 'COMPLETED'\n" +
                //                 " AND AITH.TRANS_TYPE = 'INS-RET' --�黹\n" +
                //                 " AND EII.ITEM_STATUS = 'NORMAL' -- ����\n" +
                " AND EII.MAINTAIN_USER  " + SyBaseSQLUtil.isNullNoParam() + " \n" +
                " AND EII.BARCODE = ?";
        sqlArgs.add(amsInstrumentInfo.getBarcode1());
//        sqlArgs.add(amsInstrumentInfo.getItemName());
//        sqlArgs.add(amsInstrumentInfo.getItemName());
//        sqlArgs.add(amsInstrumentInfo.getBorrowName());
//        sqlArgs.add(amsInstrumentInfo.getBorrowName());
        if ((!sfUser.isProvinceUser()) && (!sfUser.isSysAdmin())) {
            sqlStr += "AND EII.ORGANIZATION_ID = ?";
            sqlArgs.add(sfUser.getOrganizationId());
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ������Ǳ������˲�ѯ���ܡ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getQueryRespModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        String sqlStr = "SELECT" +
                " EII.ORGANIZATION_ID,\n" +
                " AMS_PUB_PKG.GET_ORGNIZATION_NAME(EII.ORGANIZATION_ID) ORGNIZATION_NAME,\n" +
                " EII.BARCODE,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " AMS_PUB_PKG.GET_VENDOR_NAME(ESI.VENDOR_ID) VENDOR_NAME,\n" +
                " AMS_PUB_PKG.GET_DEPT_NAME(EII.MAINTAIN_DEPT) MAINTAIN_DEPT_NAME,\n" +
                " EII.ITEM_QTY,\n" +
                " EII.ATTRIBUTE3,\n" +      //��;
                " AMS_PUB_PKG.GET_WORKORDER_OBJECT_NAME(EII.ADDRESS_ID) OBJECT_NAME,\n" +
                " EII.MAINTAIN_USER,\n" +  //ʹ����Ա
                " EII.REMARK," +
                "    CASE WHEN EII.ITEM_STATUS='NORMAL' THEN '����' WHEN EII.ITEM_STATUS='BORROW' THEN '����' WHEN EII.ITEM_STATUS='SEND_REPAIR' THEN '����' WHEN EII.ITEM_STATUS='DISCARDED' THEN '����'  END  ITEM_STATUS,"+
                " EII.SYSTEMID,\n" +
                " '�����Ǳ�' ITEM_CATEGORY,\n" +
                " EII.MAINTAIN_DEPT,\n" +
                " EII.ADDRESS_ID,\n" +
                " AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
                " AMS_PUB_PKG.GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) RESPONSIBILITY_DEPT_NAME,\n" +
                " EII.RESPONSIBILITY_DEPT,\n" +
                " EII.ATTRIBUTE2,\n" +        //����
                " EII.RESPONSIBILITY_USER,\n" +
                " EII.CREATED_BY,\n" +
                " EII.CREATION_DATE,\n" +
                " AMS_PUB_PKG.GET_USER_NAME(EII.CREATED_BY) CREATED_NAME,\n" +
                " TO_NUMBER(EII.ATTRIBUTE2) * EII.ITEM_QTY TOTAL_PR" +
                " FROM " +
                " ETS_ITEM_INFO EII, ETS_SYSTEM_ITEM ESI\n" +
                " WHERE " +
                " ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                " AND ESI.ITEM_CATEGORY = 'INSTRUMENT'\n" +
                " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)\n" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.ORGANIZATION_ID = ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR AMS_PUB_PKG.GET_VENDOR_NAME(ESI.VENDOR_ID) LIKE ?)" +
//                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) LIKE ?)"+
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR ITEM_STATUS LIKE ?)" +
                " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.MAINTAIN_USER LIKE ?)" +
                " AND EII.RESPONSIBILITY_USER =?";
        sqlArgs.add(amsInstrumentInfo.getBarcode());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getOrganizationId());
        sqlArgs.add(amsInstrumentInfo.getOrganizationId());
        sqlArgs.add(amsInstrumentInfo.getVendorName());
        sqlArgs.add(amsInstrumentInfo.getVendorName());
//        sqlArgs.add(amsInstrumentInfo.getResponsibilityName());
//        sqlArgs.add(amsInstrumentInfo.getResponsibilityName());
        sqlArgs.add(amsInstrumentInfo.getItemStatus());
        sqlArgs.add(amsInstrumentInfo.getItemStatus());
        sqlArgs.add(amsInstrumentInfo.getMaintainUser());
        sqlArgs.add(amsInstrumentInfo.getMaintainUser());
        sqlArgs.add(userAccount.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}