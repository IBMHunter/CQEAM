package com.sino.ams.others.cabel.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.others.cabel.dto.AmsCabelInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsCabelInfoModel</p>
 * <p>Description:�����Զ�����SQL��������AmsCabelInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsCabelInfoModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��������豸��չ��Ϣ(EAM) AMS_CABEL_INFO ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsCabelInfoDTO ���β���������
     */
    public AmsCabelInfoModel(SfUserDTO userAccount, AmsCabelInfoDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ������������豸��չ��Ϣ(EAM) AMS_CABEL_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
                    + " AMS_CABEL_INFO("
                    + " BARCODE,"
                    + " FROM_ADDRESS,"
                    + " TO_ADDRESS,"
                    + " SPREAD_TYPE,"
                    + " CABEL_USAGE,"
                    + " FROM_TUDE,"
                    + " TO_TUDE,"
                    + " CABEL_DEPTH,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_BY"
                    + ") VALUES ("
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            sqlArgs.add(amsCabelInfo.getBarcode());
            sqlArgs.add(amsCabelInfo.getFromAddress());
            sqlArgs.add(amsCabelInfo.getToAddress());
            sqlArgs.add(amsCabelInfo.getSpreadType());
            sqlArgs.add(amsCabelInfo.getCabelUsage());
            sqlArgs.add(amsCabelInfo.getFromTude());
            sqlArgs.add(amsCabelInfo.getToTude());
            sqlArgs.add(amsCabelInfo.getCabelDepth());
            sqlArgs.add(amsCabelInfo.getCreationDate());
            sqlArgs.add(amsCabelInfo.getCreatedBy());
            sqlArgs.add(amsCabelInfo.getLastUpdateDate());
            sqlArgs.add(amsCabelInfo.getLastUpdateBy());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������������豸��չ��Ϣ(EAM) AMS_CABEL_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
            String sqlStr = "UPDATE AMS_CABEL_INFO"
                    + " SET"
                    + " FROM_ADDRESS = ?,"
                    + " TO_ADDRESS = ?,"
                    + " SPREAD_TYPE = ?,"
                    + " CABEL_USAGE = ?,"
                    + " FROM_TUDE = ?,"
                    + " TO_TUDE = ?,"
                    + " CABEL_DEPTH = ?,"
                    + " CREATION_DATE = ?,"
                    + " CREATED_BY = ?,"
                    + " LAST_UPDATE_DATE = ?,"
                    + " LAST_UPDATE_BY = ?"
                    + " WHERE"
                    + " BARCODE = ?";

            sqlArgs.add(amsCabelInfo.getFromAddress());
            sqlArgs.add(amsCabelInfo.getToAddress());
            sqlArgs.add(amsCabelInfo.getSpreadType());
            sqlArgs.add(amsCabelInfo.getCabelUsage());
            sqlArgs.add(amsCabelInfo.getFromTude());
            sqlArgs.add(amsCabelInfo.getToTude());
            sqlArgs.add(amsCabelInfo.getCabelDepth());
            sqlArgs.add(amsCabelInfo.getCreationDate());
            sqlArgs.add(amsCabelInfo.getCreatedBy());
            sqlArgs.add(amsCabelInfo.getLastUpdateDate());
            sqlArgs.add(amsCabelInfo.getLastUpdateBy());
            sqlArgs.add(amsCabelInfo.getBarcode());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������������豸��չ��Ϣ(EAM) AMS_CABEL_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = "SELECT EII.BARCODE, /*����*/" +
                " ESI.ITEM_CODE, /*Unshow�豸����*/" +
                " ESI.ITEM_CATEGORY, /*�豸����*/" +
                " ESI.ITEM_NAME, /*�豸����*/" +
                " ESI.ITEM_SPEC, /*�豸�ͺ�*/" +
                " ACI.FROM_ADDRESS, /*��ʼ��ַ*/" +
                " ACI.TO_ADDRESS, /*Ŀ�ĵ�ַ*/" +
                " ACI.SPREAD_TYPE, /*���跽ʽ*/" +
                " ACI.CABEL_USAGE, /*������;*/" +
                " ACI.FROM_TUDE, /*ʼ�ص㾭γ��*/" +
                " ACI.TO_TUDE, /*ֹ�ص㾭γ��*/" +
                " ACI.CABEL_DEPTH, /*����*/" +
                " EII.ORGANIZATION_ID /*��֯*/" +
                "FROM   ETS_ITEM_INFO   EII," +
                " ETS_SYSTEM_ITEM ESI," +
                " AMS_CABEL_INFO  ACI " +
                "WHERE  ESI.ITEM_CATEGORY = 'CABEL' AND " +
                " ESI.ITEM_CODE = EII.ITEM_CODE AND " +
                " EII.BARCODE *= ACI.BARCODE AND " +
//                " EII.ORGANIZATION_ID = ? AND " +
                " EII.BARCODE = ? ";
//        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������������豸��չ��Ϣ(EAM) AMS_CABEL_INFO����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " BARCODE,"
                    + " FROM_ADDRESS,"
                    + " TO_ADDRESS,"
                    + " SPREAD_TYPE,"
                    + " CABEL_USAGE,"
                    + " FROM_TUDE,"
                    + " TO_TUDE,"
                    + " CABEL_DEPTH,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_BY"
                    + " FROM"
                    + " AMS_CABEL_INFO"
                    + " WHERE"
                    + " ( " + SyBaseSQLUtil.isNull() + "  OR BARCODE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR FROM_ADDRESS LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_ADDRESS LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR SPREAD_TYPE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CABEL_USAGE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR FROM_TUDE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_TUDE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CABEL_DEPTH LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
            sqlArgs.add(amsCabelInfo.getBarcode());
            sqlArgs.add(amsCabelInfo.getBarcode());
            sqlArgs.add(amsCabelInfo.getFromAddress());
            sqlArgs.add(amsCabelInfo.getFromAddress());
            sqlArgs.add(amsCabelInfo.getToAddress());
            sqlArgs.add(amsCabelInfo.getToAddress());
            sqlArgs.add(amsCabelInfo.getSpreadType());
            sqlArgs.add(amsCabelInfo.getSpreadType());
            sqlArgs.add(amsCabelInfo.getCabelUsage());
            sqlArgs.add(amsCabelInfo.getCabelUsage());
            sqlArgs.add(amsCabelInfo.getFromTude());
            sqlArgs.add(amsCabelInfo.getFromTude());
            sqlArgs.add(amsCabelInfo.getToTude());
            sqlArgs.add(amsCabelInfo.getToTude());
            sqlArgs.add(amsCabelInfo.getCabelDepth());
            sqlArgs.add(amsCabelInfo.getCabelDepth());
            sqlArgs.add(amsCabelInfo.getCreationDate());
            sqlArgs.add(amsCabelInfo.getCreationDate());
            sqlArgs.add(amsCabelInfo.getCreatedBy());
            sqlArgs.add(amsCabelInfo.getCreatedBy());
            sqlArgs.add(amsCabelInfo.getLastUpdateDate());
            sqlArgs.add(amsCabelInfo.getLastUpdateDate());
            sqlArgs.add(amsCabelInfo.getLastUpdateBy());
            sqlArgs.add(amsCabelInfo.getLastUpdateBy());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcodeNo �����ѯ����SQL��
     * ����Զ����������������豸��չ��Ϣ(EAM) AMS_CABEL_INFO��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param barcodeNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeNoModel(String barcodeNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " FROM_ADDRESS,"
                + " TO_ADDRESS,"
                + " SPREAD_TYPE,"
                + " CABEL_USAGE,"
                + " FROM_TUDE,"
                + " TO_TUDE,"
                + " CABEL_DEPTH,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_CABEL_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcodeNo);

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
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        if (foreignKey.equals("barcodeNo")) {
            sqlModel = getDataByBarcodeNoModel(amsCabelInfo.getBarcode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������������豸��չ��Ϣ(EAM) AMS_CABEL_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = " SELECT * " +
                "FROM   (SELECT EII.BARCODE, /*����*/ " +
                "        ESI.ITEM_CATEGORY, /*�豸����*/ " +
                "        ESI.ITEM_NAME, /*�豸����*/ " +
                "        ESI.ITEM_SPEC, /*�豸�ͺ�*/ " +
                "        ACI.FROM_ADDRESS, /*��ʼ��ַ*/ " +
                "        ACI.TO_ADDRESS, /*Ŀ�ĵ�ַ*/ " +
                "        ACI.SPREAD_TYPE, /*���跽ʽ*/ " +
                "        ACI.CABEL_USAGE, /*������;*/ " +
                "        ST.NAME SPREAD_TYPE_NAME, /*���跽ʽ*/ " +
                "        CU.NAME CABEL_USAGE_NAME, /*������;*/ " +
                "        ACI.CABEL_DEPTH /*����*/ " +
                "    FROM   ETS_ITEM_INFO EII, " +
                "        ETS_SYSTEM_ITEM ESI, " +
                "        AMS_CABEL_INFO ACI, " +
                "        (SELECT EFV.CODE  VALUE, " +
                "            EFV.VALUE NAME " +
                "        FROM   ETS_FLEX_VALUES    EFV, " +
                "            ETS_FLEX_VALUE_SET EFVS " +
                "        WHERE  EFVS.CODE = 'CABEL_USAGE' AND " +
                "            EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID) CU, " +
                "        (SELECT EFV.CODE  VALUE, " +
                "            EFV.VALUE NAME " +
                "        FROM   ETS_FLEX_VALUES    EFV, " +
                "            ETS_FLEX_VALUE_SET EFVS " +
                "        WHERE  EFVS.CODE = 'SPREAD_TYPE' AND " +
                "            EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID) ST " +
                "    WHERE  ESI.ITEM_CATEGORY = 'CABEL' AND " +
                "        ESI.ITEM_CODE = EII.ITEM_CODE AND " +
                "        EII.ORGANIZATION_ID =?  AND " +
                "        EII.BARCODE *= ACI.BARCODE AND " +
                "        ACI.SPREAD_TYPE *= ST.VALUE AND " +
                "        ACI.CABEL_USAGE *= CU.VALUE  ) T " +
                "WHERE ( " + SyBaseSQLUtil.isNull() + "  OR T.BARCODE LIKE ?) AND" +
                "      ( " + SyBaseSQLUtil.isNull() + "  OR T.SPREAD_TYPE = ?) AND " +
                "      ( " + SyBaseSQLUtil.isNull() + "  OR T.CABEL_USAGE = ?) " +
                " ORDER BY T.CABEL_USAGE,T.SPREAD_TYPE";

        sqlArgs.add(sfUser.getOrganizationId());

        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlArgs.add(amsCabelInfo.getSpreadType());
        sqlArgs.add(amsCabelInfo.getSpreadType());
        sqlArgs.add(amsCabelInfo.getCabelUsage());
        sqlArgs.add(amsCabelInfo.getCabelUsage());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //-------------------------------------------------------------------------------------------

    /**
     * ���ܣ���� AMS_CABEL_INFO���� BARCODE��Ϣ�Ƿ����
     */
    public SQLModel getCheckInACIModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1 FROM AMS_CABEL_INFO ACI WHERE ACI.BARCODE = ?";
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���� ETS_SYSTEM_ITEM ���и��豸������Ϣ�Ƿ����
     */
    public SQLModel getCheckInESIModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1 FROM ETS_SYSTEM_ITEM ESI WHERE ESI.ITEM_NAME = ? AND ESI.ITEM_SPEC=? AND ESI.ITEM_CATEGORY  = ? ";
        sqlArgs.add(amsCabelInfo.getItemName());
        sqlArgs.add(amsCabelInfo.getItemSpec());
        sqlArgs.add(amsCabelInfo.getItemCategory());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���� ETS_SYSITEM_DISTRIBUTE �����Ƿ񽫸��豸���ͷ����������
     */
    public SQLModel getCheckInESDModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1 FROM ETS_SYSITEM_DISTRIBUTE ESD WHERE ESD.ITEM_CODE = ? AND ESD.ORGANIZATION_ID  = ? ";
        sqlArgs.add(amsCabelInfo.getItemCode());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���� ETS_ITEM_INFO �����Ƿ���ڸ��豸
     */
    public SQLModel getCheckInEIIModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1 FROM ETS_ITEM_INFO EII WHERE EII.BARCODE = ?";
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���� ETS_ITEM_INFO ���м�¼�豸�����Ƿ��������豸����һ��
     */
    public SQLModel getCheckSameTypeInEIIModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = "SELECT 1 FROM ETS_ITEM_INFO EII,ETS_SYSTEM_ITEM ESI " +
                " WHERE  EII.ITEM_CODE = ESI.ITEM_CODE " +
                "AND  EII.BARCODE = ? AND ESI.ITEM_CODE = ? ";
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlArgs.add(amsCabelInfo.getItemCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //-------------------------------------------------

    /**
     * ���ܣ� ����һ��������Ϣ�� AMS_APPLY_SYSTEM_ITEM ��
     */
    public SQLModel getCreateInAASIModel() {
        SQLModel sqlModel = new SQLModel();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO AMS_APPLY_SYSTEM_ITEM " +
                "(ITEM_CODE , APPLY_OU,CREATION_DATE ,CREATED_BY )VALUES(?,?,GETDATE(),?)";
        sqlArgs.add(amsCabelInfo.getItemCode());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ� ����һ��������Ϣ�� ETS_SYSITEM_DISTRIBUTE ��
     */
    public SQLModel getCreateInESDModel() {
        SQLModel sqlModel = new SQLModel();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO ETS_SYSITEM_DISTRIBUTE  " +
                "(SYSTEM_ID, ITEM_CODE, ORGANIZATION_ID, CREATION_DATE, CREATED_BY, IS_TMP)  " +
                "VALUES (NEWID() ,?,?,GETDATE(),?,'Y')";
        sqlArgs.add(amsCabelInfo.getItemCode());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ� ����һ���豸������Ϣ��  ETS_SYSTEM_ITEM ��
     */
    public SQLModel getCreateInESIModel() {
        SQLModel sqlModel = new SQLModel();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO ETS_SYSTEM_ITEM\n " +
                "(ITEM_CODE,ITEM_NAME,ITEM_SPEC,ITEM_CATEGORY," +
                "MEMO ,ITEM_UNIT ,CREATED_BY," +
                "MASTER_ORGANIZATION_ID,BARCODE_NULLABLE,IS_TMP_CODE)VALUES\n " +
                "( NEWID() ,?,?,?," +
                "?,?,?," +
                "?,'Y','Y')";
        sqlArgs.add(amsCabelInfo.getItemName());
        sqlArgs.add(amsCabelInfo.getItemSpec());
        sqlArgs.add(amsCabelInfo.getItemCategory());
        sqlArgs.add("AmsCabelInfoModel�Զ�����");
        sqlArgs.add("��");
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add("82"); //MASTER_ORGANIZATION_ID
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ� ����һ���豸��Ϣ��   ETS_ITEM_INFO ��
     */

    public SQLModel getCreateInEIIModel() {
        SQLModel sqlModel = new SQLModel();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO ETS_ITEM_INFO " +
                " (SYSTEMID,BARCODE," +
                " ITEM_CODE,CREATION_DATE," +
                " CREATED_BY,ORGANIZATION_ID) " +
                " VALUES " +
                "( NEWID() ,? ,? ,GETDATE(),? ,?)";
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlArgs.add(amsCabelInfo.getItemCode());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ� ����һ���豸��Ϣ��   ETS_ITEM_INFO ��
     */
    public SQLModel getUpdateInEIIModel() {
        SQLModel sqlModel = new SQLModel();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE ETS_ITEM_INFO  EII  " +
                " SET EII.ITEM_CODE = ? " +
                " WHERE EII.BARCODE = ? ";
        sqlArgs.add(amsCabelInfo.getItemCode());
        sqlArgs.add(amsCabelInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getItemCodeInESIModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsCabelInfoDTO amsCabelInfo = (AmsCabelInfoDTO) dtoParameter;
        String sqlStr = " SELECT ESI.ITEM_CODE " +
                " FROM ETS_SYSTEM_ITEM ESI " +
                " WHERE ESI.ITEM_NAME = ? AND ESI.ITEM_SPEC= ? AND ESI.ITEM_CATEGORY = ? ";
        sqlArgs.add(amsCabelInfo.getItemName());
        sqlArgs.add(amsCabelInfo.getItemSpec());
        sqlArgs.add(amsCabelInfo.getItemCategory());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}