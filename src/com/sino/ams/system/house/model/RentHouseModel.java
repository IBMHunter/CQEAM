package com.sino.ams.system.house.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.dto.EtsFaAssetsDTO;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.house.dto.AmsHouseInfoDTO;
import com.sino.ams.system.house.dto.AmsHouseUsesDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-7-14
 * Time: 18:25:40
 * Function:���޷�������ά��.
 */
public class RentHouseModel extends AMSSQLProducer {

    /**
     * ���ܣ����������ʲ�(EAM) AMS_LAND_INFO ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     */
    public RentHouseModel(SfUserDTO userAccount, AmsHouseInfoDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException
     *          ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//		try {
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO houseInfoDTO = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_HOUSE_INFO("
                + " BARCODE,"
                + " HOUSE_ADDRESS ,"
                + " HOUSE_AREA ,"
                + " HOUSE_CERTIFICATE_NO ,"
                + " HOUSE_USAGE ,"
                + " BUSINESS_AREA ,"
                + " OFFICE_AREA ,"
                + " HOUSE_STATUS ,"
                + " IS_RENT ,"
                + " AREA_UNIT ,"
                + " OFFICE_USAGE,"
                + " OFFICE_TYPE,"
                + " LAND_CERTFICATE_NO,"
                + " OCCUPY_AREA ,"
                + " REMARK ,"
                + " LAND_TYPE"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, 'Y', ?, ?, ?, ?, ?, ?, ?)";

        sqlArgs.add(houseInfoDTO.getBarcode());
        sqlArgs.add(houseInfoDTO.getHouseAddress());
        sqlArgs.add(houseInfoDTO.getHouseArea());
        sqlArgs.add(houseInfoDTO.getHouseCertificateNo());
        sqlArgs.add(houseInfoDTO.getHouseUsage());
        sqlArgs.add(houseInfoDTO.getBusinessArea());
        sqlArgs.add(houseInfoDTO.getOfficeArea());
        sqlArgs.add(houseInfoDTO.getHouseStatus());
        sqlArgs.add(houseInfoDTO.getAreaUnit());
        sqlArgs.add(houseInfoDTO.getOfficeUsage());
        sqlArgs.add(houseInfoDTO.getOfficeType());
        sqlArgs.add(houseInfoDTO.getLandCertficateNo());
        sqlArgs.add(houseInfoDTO.getOccupyArea());
        sqlArgs.add(houseInfoDTO.getHremark());
        sqlArgs.add(houseInfoDTO.getLandType());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������޷���(EAM) AMS_HOUSE_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//		try {
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsHouseInfo = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " AMS_HOUSE_INFO"
                + " SET"
                + " HOUSE_ADDRESS = ?,"
                + " FLOOR_NUMBER = ?,"
                + " HOUSE_NO = ?,"
                + " HOUSE_AREA = ?,"
                + " HOUSE_CERTIFICATE_NO = ?,"
                + " HOUSE_USAGE = ?,"
                + " BUSINESS_AREA = ?,"
                + " PRODUCE_HOSUSE_AREA = ?,"
                + " PRODUCE_BASE_AREA = ?,"
                + " OFFICE_AREA = ?,"
                + " HOUSE_STATUS = ?,"
//                + " IS_RENT = ?,"
                + " AREA_UNIT = ?,"
                + " OFFICE_USAGE= ?,"
                + " OFFICE_TYPE= ?,"
                + " LAND_CERTFICATE_NO=?,"
                + " OCCUPY_AREA =?,"
                + " REMARK =?,"
                + " LAND_TYPE=?"          //��������
                + " WHERE"
                + " BARCODE = ?";

        sqlArgs.add(amsHouseInfo.getHouseAddress());
        sqlArgs.add(amsHouseInfo.getFloorNumber());
        sqlArgs.add(amsHouseInfo.getHouseNo());
        sqlArgs.add(amsHouseInfo.getHouseArea());
        sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
        sqlArgs.add(amsHouseInfo.getHouseUsage());
        sqlArgs.add(amsHouseInfo.getBusinessArea());
        sqlArgs.add(amsHouseInfo.getProduceHosuseArea());
        sqlArgs.add(amsHouseInfo.getProduceBaseArea());
        sqlArgs.add(amsHouseInfo.getOfficeArea());
        sqlArgs.add(amsHouseInfo.getHouseStatus());
//        sqlArgs.add(amsHouseInfo.getIsRent());
        sqlArgs.add(amsHouseInfo.getAreaUnit());

        sqlArgs.add(amsHouseInfo.getOfficeUsage());
        sqlArgs.add(amsHouseInfo.getOfficeType());
        sqlArgs.add(amsHouseInfo.getLandCertficateNo());
        sqlArgs.add(amsHouseInfo.getOccupyArea());
        sqlArgs.add(amsHouseInfo.getHremark());
        sqlArgs.add(amsHouseInfo.getLandType());

        sqlArgs.add(amsHouseInfo.getBarcode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsLandInfo = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_LAND_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(amsLandInfo.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {          //��ϸ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsLandInfo = (AmsHouseInfoDTO) dtoParameter;
//        String sqlStr = " SELECT " +
//                " EII.ITEM_CODE,\n" +
//                " EII.SYSTEMID SYSTEM_ID,\n" +
//                " EII.BARCODE,\n" +
//                " ESI.ITEM_NAME,\n" +
//                " ESI.ITEM_SPEC,\n" +
//                " AHI.BARCODE,\n" +
//                " AHI.HOUSE_ADDRESS,\n" +
//                " AHI.FLOOR_NUMBER,\n" +
//                " AHI.HOUSE_NO,\n" +
//                " AHI.HOUSE_AREA,\n" +
//                " AHI.AREA_UNIT,\n" +
//                " AHI.HOUSE_CERTIFICATE_NO,\n" +
//                " AHI.HOUSE_USAGE,\n" +
//                " AHI.BUSINESS_AREA,\n" +
//                " AHI.PRODUCE_HOSUSE_AREA,\n" +
//                " AHI.PRODUCE_BASE_AREA,\n" +
//                " AHI.OFFICE_AREA,\n" +
//                " AHI.HOUSE_STATUS,\n" +
//                " AHI.OFFICE_USAGE,\n" +
//                " AHI.OFFICE_TYPE,\n" +
//                " AHI.REMARK HREMARK,\n" +
//                " AHI.LAND_CERTFICATE_NO,\n" +
//                " AHI.OCCUPY_AREA,\n" +
//                " AHI.LAND_TYPE,\n" +
//                " AHR.RENT_ID,\n" +
//                " AHR.RENT_START_DATE,\n" +
//                " AHR.RENT_END_DATE,\n" +
//                " AHR.RENT_UNIT,\n" +
//                " AHR.CONTACT_PERSON,\n" +
//                " AHR.CONTACT_PHONE,\n" +
//                " AHR.RENT_FEE,\n" +
//                " AHR.ENABLED,\n" +
//                " AHR.CURRENCY_UNIT,\n" +
//                " AHR.CREATION_DATE,\n" +
//                " AHR.CREATED_BY,\n" +
//                " AHR.LAST_UPDATE_DATE,\n" +
//                " AHR.LAST_UPDATE_BY" +
//                " FROM " +
//                " ETS_ITEM_INFO   EII,\n" +
//                " AMS_HOUSE_INFO  AHI,\n" +
//                " ETS_SYSTEM_ITEM ESI,\n" +
//                " AMS_HOUSE_RENT  AHR\n" +
//                " WHERE " +
//                " EII.BARCODE = AHI.BARCODE\n" +
//                " AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
//                " AND AHI.BARCODE *= AHR.BARCODE"
//              + " AND EII.BARCODE = ?";

        String sqlStr ="SELECT A.*\n" +
                "  FROM (SELECT EII.ITEM_CODE,\n" +
                "               EII.SYSTEMID SYSTEM_ID,\n" +
                "               EII.BARCODE,\n" +
                "               ESI.ITEM_NAME,\n" +
                "               ESI.ITEM_SPEC,\n" +
                "               AHI.HOUSE_ADDRESS,\n" +
                "               AHI.FLOOR_NUMBER,\n" +
                "               AHI.HOUSE_NO,\n" +
                "               AHI.HOUSE_AREA,\n" +
                "               AHI.AREA_UNIT,\n" +
                "               AHI.HOUSE_CERTIFICATE_NO,\n" +
                "               AHI.HOUSE_USAGE,\n" +
                "               AHI.BUSINESS_AREA,\n" +
                "               AHI.PRODUCE_HOSUSE_AREA,\n" +
                "               AHI.PRODUCE_BASE_AREA,\n" +
                "               AHI.OFFICE_AREA,\n" +
                "               AHI.HOUSE_STATUS,\n" +
                "               AHI.OFFICE_USAGE,\n" +
                "               AHI.OFFICE_TYPE,\n" +
                "               AHI.REMARK HREMARK,\n" +
                "               AHI.LAND_CERTFICATE_NO,\n" +
                "               AHI.OCCUPY_AREA,\n" +
                "               AHI.LAND_TYPE,\n" +
                "               AHR.RENT_ID,\n" +
                "               AHR.RENT_START_DATE,\n" +
                "               AHR.RENT_END_DATE,\n" +
                "               AHR.RENT_UNIT,\n" +
                "               AHR.CONTACT_PERSON,\n" +
                "               AHR.CONTACT_PHONE,\n" +
                "               AHR.RENT_FEE,\n" +
                "               AHR.ENABLED,\n" +
                "               AHR.CONTRACT_NAME,\n" +
                "               AHR.CURRENCY_UNIT,\n" +
                "               AHR.CREATION_DATE,\n" +
                "               AHR.CREATED_BY,\n" +
                "               AHR.LAST_UPDATE_DATE,\n" +
                "               AHR.LAST_UPDATE_BY\n" +
                "          FROM ETS_ITEM_INFO   EII,\n" +
                "               AMS_HOUSE_INFO  AHI,\n" +
                "               ETS_SYSTEM_ITEM ESI,\n" +
                "               AMS_HOUSE_RENT  AHR\n" +
                "         WHERE EII.BARCODE = AHI.BARCODE\n" +
                "           AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                "           AND AHR.BARCODE = AHI.BARCODE\n" +
                "           AND EII.BARCODE = ?\n" +
                "         ORDER BY AHR.CREATION_DATE DESC) A\n" +
                " WHERE ROWNUM < 2";
        sqlArgs.add(amsLandInfo.getBarcode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFO����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsHouseInfoDTO amsLandInfo = (AmsHouseInfoDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " BARCODE,"
                    + " RENT_PERSON,"
                    + " RENT_DATE,"
                    + " AREA_UNIT,"
                    + " RENTAL,"
                    + " MONEY_UNIT,"
                    + " PAY_TYPE,"
                    + " END_DATE,"
                    + " LAND_AREA,"
                    + " RENT_USAGE,"
                    + " REMARK"
                    + " FROM"
                    + " AMS_LAND_INFO"
                    + " WHERE"
                    + " ( " + SyBaseSQLUtil.isNull() + "  OR BARCODE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR RENT_PERSON LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR RENT_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR AREA_UNIT LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR RENTAL LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR MONEY_UNIT LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR PAY_TYPE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR END_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAND_AREA LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR RENT_USAGE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)";
            sqlArgs.add(amsLandInfo.getBarcode());
            sqlArgs.add(amsLandInfo.getBarcode());
            sqlArgs.add(amsLandInfo.getRentPerson());
            sqlArgs.add(amsLandInfo.getRentPerson());
            sqlArgs.add(amsLandInfo.getRentDate());
            sqlArgs.add(amsLandInfo.getRentDate());
            sqlArgs.add(amsLandInfo.getAreaUnit());
            sqlArgs.add(amsLandInfo.getAreaUnit());
            sqlArgs.add(amsLandInfo.getRental());
            sqlArgs.add(amsLandInfo.getRental());
            sqlArgs.add(amsLandInfo.getMoneyUnit());
            sqlArgs.add(amsLandInfo.getMoneyUnit());
            sqlArgs.add(amsLandInfo.getPayType());
            sqlArgs.add(amsLandInfo.getPayType());
            sqlArgs.add(amsLandInfo.getEndDate());
            sqlArgs.add(amsLandInfo.getEndDate());
            sqlArgs.add(amsLandInfo.getRentUsage());
            sqlArgs.add(amsLandInfo.getRentUsage());

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
     * ����Զ������������������ʲ�(EAM) AMS_LAND_INFO��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeModel(String barcode) {     //��ϸ��sql ?????
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT \n"
                + " EII.SYSTEMID,\n"
                + " ESI.ITEM_NAME,\n"
                + " ESI.ITEM_SPEC, \n"
                + " ALI.BARCODE,\n"
                + " ALI.RENT_PERSON,\n"
                + " ALI.RENT_DATE,\n"
                + " ALI.AREA_UNIT,\n"
                + " ALI.RENTAL,\n"
                + " ALI.MONEY_UNIT,\n"
                + " ALI.PAY_TYPE,\n"
                + " ALI.END_DATE,\n"
                + " ALI.LAND_AREA,\n"
                + " ALI.RENT_USAGE,\n"
                + " ALI.REMARK\n"
                + " FROM "
                + " ETS_ITEM_INFO EII, "
                + " AMS_LAND_INFO ALI,\n"
                + " ETS_SYSTEM_ITEM ESI\n"
                + " WHERE "
                + " EII.BARCODE = ALI.BARCODE"
                + " AND ESI.ITEM_CODE = EII.ITEM_CODE"
                + " AND BARCODE = ?";
        sqlArgs.add(barcode);
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
        AmsHouseInfoDTO amsLandInfo = (AmsHouseInfoDTO) dtoParameter;
        if (foreignKey.equals("barcode")) {
            sqlModel = getDataByBarcodeModel(amsLandInfo.getBarcode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcode ��������ɾ��SQL��
     * ����Զ������������������ʲ�(EAM) AMS_LAND_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param barcode String
     * @return SQLModel ��������ɾ����SQLModel
     */
    private SQLModel getDeleteByBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " RENT_PERSON,"
                + " RENT_DATE,"
                + " AREA_UNIT,"
                + " RENTAL,"
                + " MONEY_UNIT,"
                + " PAY_TYPE,"
                + " END_DATE,"
                + " LAND_AREA,"
                + " RENT_USAGE,"
                + " REMARK"
                + " FROM"
                + " AMS_LAND_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcode);

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
        AmsHouseInfoDTO amsLandInfo = (AmsHouseInfoDTO) dtoParameter;
        if (foreignKey.equals("barcode")) {
            sqlModel = getDeleteByBarcodeModel(amsLandInfo.getBarcode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {   //��ѯʹ�õ�sql
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT " +
                " EFA.ASSET_ID,\n" +
                " EFA.FA_CATEGORY1,\n" +
                " EFA.FA_CATEGORY2,\n" +
                " EFA.ASSETS_DESCRIPTION,\n" +
                " EFA.FA_CATEGORY_CODE,\n" +
                " EFA.UNIT_OF_MEASURE,\n" +
                " EFA.LIFE_IN_YEARS,\n" +
                " EFA.MODEL_NUMBER,\n" +
                " EFA.TAG_NUMBER,\n" +
                " EFA.CURRENT_UNITS,\n" +
                " EFA.ASSETS_LOCATION,\n" +
                " EFA.PROJECT_NAME,\n" +
                " EFA.DATE_PLACED_IN_SERVICE,\n" +
                " EFA.IS_RETIREMENTS,\n" +
                " EFA.ASSET_NUMBER,\n" +
                " EFA.COST,\n" +
                " EFA.DEPRN_COST,\n" +
                " EFA.ASSETS_STATUS,\n" +
                " EFA.BOOK_TYPE_CODE,\n" +
                " EFA.ASSIGNED_TO_NAME,\n" +
                " EFA.ASSIGNED_TO_NUMBER,\n" +
                " EFA.SEGMENT1,\n" +
                " EFA.SEGMENT2,\n" +
                " EFA.TRANS_TO_MIS,\n" +
                " EFA.TRANS_TO_MIS_DESC,\n" +
                " EFA.TRANS_TO_MIS_TAG,\n" +
                " EFA.TRANS_TO_MIS_LOC,\n" +
                " EFA.CODE_COMBINATION_ID,\n" +
                " EFA.COMPANY_CODE,\n" +
                " EFA.PROJECT_ID PROJECTID,\n" +
                " EFA.ORGANIZATION_ID,\n" +
                " EFA.SCRAP_VALUE,\n" +
                " EFA.DEPRECIATION_ACCOUNT,\n" +
                " EFA.ASSETS_LOCATION_CODE,\n" +
                " EFA.SEGMENT3,\n" +
                " EFA.SEGMENT2_TEMP\n" +
                " FROM " +
                " ETS_FA_ASSETS EFA\n" +
                " WHERE " +
                " (EFA.SEGMENT2 LIKE '09-%' OR EFA.SEGMENT2 LIKE '07-71%')\n" + //�ƶ�����Ŀ¼09��������07-71������
                " AND EFA.ASSETS_STATUS = 0" + //�ʲ�״̬
                " AND (EFA.IS_RETIREMENTS =0 OR EFA.IS_RETIREMENTS =2)" + //�Ƿ񱨷�
                " AND NOT EXISTS\n" +
//						" (SELECT 1 FROM ETS_ITEM_INFO EII WHERE EII.BARCODE = EFA.TAG_NUMBER)";
                " (SELECT 1 FROM AMS_HOUSE_INFO EII WHERE EII.BARCODE = EFA.TAG_NUMBER)";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDispositionModel() throws SQLModelException {   //��ѯʹ�õ�sql
        SQLModel sqlModel = new SQLModel();
      try {
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsHouseInfo = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = " SELECT \n" +
                        " EII.ITEM_CODE,\n" +
                        " ESI.ITEM_NAME,\n" +
                        " ESI.ITEM_SPEC,\n" +
                        " EII.SYSTEMID,\n" +
                        " EII.ADDRESS_ID,\n" +
                        " AHI.BARCODE,\n" +
                        " AHI.HOUSE_CERTIFICATE_NO,\n" +
                        " dbo.APP_GET_COUNTY_NAME(EII.ADDRESS_ID) COUNTY_NAME,\n" +
                        " AHI.HOUSE_ADDRESS,\n" +
                        " CASE WHEN AHI.IS_RENT='Y' THEN '��' ELSE '��' END IS_RENT ,\n"+
                        " AHI.FLOOR_NUMBER,\n" +
                        " AHI.HOUSE_NO,\n" +
                        " AHI.HOUSE_AREA,\n" +
                        " AHI.HOUSE_USAGE,\n" +
                        " AHI.BUSINESS_AREA,\n" +
                        " AHI.PRODUCE_HOSUSE_AREA,\n" +
                        " AHI.PRODUCE_BASE_AREA,\n" +
                        " AHI.OFFICE_AREA,\n" +
                        " AHI.HOUSE_STATUS,\n" +
                        " AHI.OCCUPY_AREA,\n" +
                        " AHI.OFFICE_USAGE,\n" +
                        " AHI.OFFICE_TYPE,\n" +
                        " EII.ATTRIBUTE2,\n" +
                        " AHI.LAND_TYPE,\n" +
                        " AHI.LAND_CERTFICATE_NO,\n" +
                        " AHR.RENT_ID,\n" +
                        " AHR.RENT_START_DATE,\n" +
                        " AHR.RENT_END_DATE,\n" +
//                        " AHR.CONTRACT_FILE,\n" +
//                        " AHR.CONTRACT_NAME,\n" +
                        " AHR.RENT_FEE,\n" +
                        " AHR.RENT_UNIT,\n" +
                        " AHR.CONTACT_PERSON,\n" +
                        " AHR.CONTACT_PHONE,\n" +
                        " AHR.CONTRACT_NAME,\n" +
                        " AHR.ENABLED,\n" +
                        " AHR.CURRENCY_UNIT,\n" +
                        " AHR.CREATION_DATE,\n" +
                        " AHR.CREATED_BY,\n" +
                        " AHR.LAST_UPDATE_DATE,\n" +
                        " AHR.LAST_UPDATE_BY\n" +
                        " FROM " +
                        " ETS_ITEM_INFO   EII,\n" +
                        " AMS_HOUSE_INFO  AHI,\n" +
                        " ETS_SYSTEM_ITEM ESI,\n" +
                        " AMS_HOUSE_RENT  AHR\n" +
                        " WHERE " +
                        " EII.BARCODE = AHI.BARCODE\n" +
                        " AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                        " AND AHI.BARCODE *= AHR.BARCODE\n" +
                        " AND AHI.IS_RENT = 'Y'\n" +    //���޵ķ���������Ϣ
                        " AND AHR.ENABLED = 'Y' \n"+   //������Ч
                        " AND (AHI.BARCODE LIKE dbo.NVL(?, AHI.BARCODE))\n" +
                        " AND ESI.ITEM_NAME LIKE dbo.NVL(?, ESI.ITEM_NAME)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.OFFICE_USAGE LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.OFFICE_TYPE LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.ATTRIBUTE2 LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.HOUSE_CERTIFICATE_NO = ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.LAND_CERTFICATE_NO = ?)" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHR.RENT_UNIT LIKE ?)"+
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHR.RENT_END_DATE > = ?)" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHR.RENT_END_DATE < = ?)";

        if (!StrUtil.isEmpty(amsHouseInfo.getTemp())) { //�Ƿ��Ѵ���
            sqlArgs.add(amsHouseInfo.getBarcode());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getOfficeUsage());
            sqlArgs.add(amsHouseInfo.getOfficeUsage());
            sqlArgs.add(amsHouseInfo.getOfficeType());
            sqlArgs.add(amsHouseInfo.getOfficeType());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getRentUnit());
            sqlArgs.add(amsHouseInfo.getRentUnit());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getToDate());
            sqlArgs.add(amsHouseInfo.getToDate());
        } else {
            sqlArgs.add(amsHouseInfo.getBarcode());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getRentUnit());
            sqlArgs.add(amsHouseInfo.getRentUnit());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getToDate());
            sqlArgs.add(amsHouseInfo.getToDate());
        }

        if(amsHouseInfo.getProvince().equals("N")){
          sqlStr += "AND  EII.ORGANIZATION_ID = ?";
          sqlArgs.add(userAccount.getOrganizationId());
        }
//		   if (amsHouseInfo.getCertificate().equals("Y")) {
//			sqlStr += " AND  " + SyBaseSQLUtil.isNotNull("TODO") + " ";
//			} else if (amsHouseInfo.getCertificate().equals("N")) {
//			sqlStr += " AND AHI.HOUSE_CERTIFICATE_NO  " + SyBaseSQLUtil.isNullNoParam() + " ";
//			}
//		   if(amsHouseInfo.getCertificate().equals("Y")){
//			 sqlStr += " AND ( " + SyBaseSQLUtil.isNull() + "  OR  AHI.HOUSE_CERTIFICATE_NO = ?)";
//			 sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
//			 sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
//		   }
//		  if (amsHouseInfo.getHasLandNo().equals("Y")) {
//			sqlStr += " AND  " + SyBaseSQLUtil.isNotNull("TODO") + " ";
//			} else if (amsHouseInfo.getCertificate().equals("N")) {
//			sqlStr += " AND AHI.OCCUPY_AREA  " + SyBaseSQLUtil.isNullNoParam() + " ";
//			}
//		   if(amsHouseInfo.getHasLandNo().equals("Y")){
//			 sqlStr += " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.LAND_CERTFICATE_NO = ?)";
//			 sqlArgs.add(amsHouseInfo.getLandCertficateNo());
//			 sqlArgs.add(amsHouseInfo.getLandCertficateNo());
//		   }


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
       } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }


  /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getRentHistoryModel() throws SQLModelException {   //��ѯ������ʷ��Ϣʹ�õ�sql
        SQLModel sqlModel = new SQLModel();
      try {
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsHouseInfo = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = " SELECT \n" +
                        " EII.ITEM_CODE,\n" +
                        " ESI.ITEM_NAME,\n" +
                        " ESI.ITEM_SPEC,\n" +
                        " EII.SYSTEMID,\n" +
                        " EII.ADDRESS_ID,\n" +
                        " AHI.BARCODE,\n" +
                        " AHI.HOUSE_CERTIFICATE_NO,\n" +
                        " dbo.APP_GET_COUNTY_NAME(EII.ADDRESS_ID) COUNTY_NAME,\n" +
                        " AHI.HOUSE_ADDRESS,\n" +
                        " CASE WHEN AHI.IS_RENT='Y' THEN '��' ELSE '��' END AS IS_RENT,"+
                        " AHI.FLOOR_NUMBER,\n" +
                        " AHI.HOUSE_NO,\n" +
                        " AHI.HOUSE_AREA,\n" +
                        " AHI.HOUSE_USAGE,\n" +
                        " AHI.BUSINESS_AREA,\n" +
                        " AHI.PRODUCE_HOSUSE_AREA,\n" +
                        " AHI.PRODUCE_BASE_AREA,\n" +
                        " AHI.OFFICE_AREA,\n" +
                        " AHI.HOUSE_STATUS,\n" +
                        " AHI.OCCUPY_AREA,\n" +
                        " AHI.OFFICE_USAGE,\n" +
                        " AHI.OFFICE_TYPE,\n" +
                        " EII.ATTRIBUTE2,\n" +
                        " AHI.LAND_TYPE,\n" +
                        " AHI.LAND_CERTFICATE_NO,\n" +
                        " AHR.RENT_ID,\n" +
                        " AHR.RENT_START_DATE,\n" +
                        " AHR.RENT_END_DATE,\n" +
//                        " AHR.CONTRACT_FILE,\n" +
//                        " AHR.CONTRACT_NAME,\n" +
                        " AHR.RENT_FEE,\n" +
                        " AHR.RENT_UNIT,\n" +
                        " AHR.CONTACT_PERSON,\n" +
                        " AHR.CONTRACT_NAME,\n" +
                        " AHR.CONTACT_PHONE,\n" +
                        " AHR.ENABLED,\n" +
                        " AHR.CURRENCY_UNIT,\n" +
                        " AHR.CREATION_DATE,\n" +
                        " AHR.CREATED_BY,\n" +
                        " AHR.LAST_UPDATE_DATE,\n" +
                        " AHR.LAST_UPDATE_BY\n" +
                        " FROM " +
                        " ETS_ITEM_INFO   EII,\n" +
                        " AMS_HOUSE_INFO  AHI,\n" +
                        " ETS_SYSTEM_ITEM ESI,\n" +
                        " AMS_HOUSE_RENT  AHR\n" +
                        " WHERE " +
                        " EII.BARCODE = AHI.BARCODE\n" +
                        " AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                        " AND AHI.BARCODE *= AHR.BARCODE\n" +
                        " AND AHI.IS_RENT = 'Y'\n" +    //���޵ķ���������Ϣ
//                        " AND AHR.ENABLED = 'Y' \n"+   //������Ч
                        " AND (AHI.BARCODE LIKE dbo.NVL(?, AHI.BARCODE))\n" +
                        " AND ESI.ITEM_NAME LIKE dbo.NVL(?, ESI.ITEM_NAME)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.OFFICE_USAGE LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.OFFICE_TYPE LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.ATTRIBUTE2 LIKE ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.HOUSE_CERTIFICATE_NO = ?)\n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.LAND_CERTFICATE_NO = ?)" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHR.RENT_END_DATE > = ?)" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHR.RENT_END_DATE < = ?)" ;

        if (!StrUtil.isEmpty(amsHouseInfo.getTemp())) { //�Ƿ��Ѵ���
            sqlArgs.add(amsHouseInfo.getBarcode());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getOfficeUsage());
            sqlArgs.add(amsHouseInfo.getOfficeUsage());
            sqlArgs.add(amsHouseInfo.getOfficeType());
            sqlArgs.add(amsHouseInfo.getOfficeType());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getToDate());
            sqlArgs.add(amsHouseInfo.getToDate());
        } else {
            sqlArgs.add(amsHouseInfo.getBarcode());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getFromDate());
            sqlArgs.add(amsHouseInfo.getToDate());
            sqlArgs.add(amsHouseInfo.getToDate());
        }
          sqlStr += "AND  EII.ORGANIZATION_ID = ?";
          sqlArgs.add(userAccount.getOrganizationId());

          sqlModel.setSqlStr(sqlStr);
          sqlModel.setArgs(sqlArgs);
       } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }

        return sqlModel;
    }


    /**
     * ���ܣ���ѯ�������ص���Ϣ��
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getHouseLandModel() throws SQLModelException {   //��ѯ����������Ϣʹ�õ�sql
        SQLModel sqlModel = new SQLModel();
//      try {
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsHouseInfo = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = "SELECT " +
                " EII.ITEM_CODE,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " EII.SYSTEMID,\n" +
                " EII.ADDRESS_ID,\n" +
                " AHI.BARCODE,\n" +
                " AHI.HOUSE_CERTIFICATE_NO,\n" +
//                    " dbo.APP_GET_COUNTY_NAME(EII.ADDRESS_ID) COUNTY_NAME,\n"+
                " AHI.HOUSE_ADDRESS,\n" +
                " CASE WHEN AHI.IS_RENT='Y' THEN '��' ELSE '��' END AS IS_RENT,"+
                " ARI.RENT_PERSON,\n" +
                " ARI.RENT_DATE,\n" +
                " AHI.FLOOR_NUMBER,\n" +
                " AHI.HOUSE_NO,\n" +
                " AHI.HOUSE_AREA,\n" +
                " AHI.HOUSE_USAGE,\n" +
                " AHI.BUSINESS_AREA,\n" +
                " AHI.PRODUCE_HOSUSE_AREA,\n" +
                " AHI.PRODUCE_BASE_AREA,\n" +
                " AHI.OFFICE_AREA,\n" +
                " AHI.HOUSE_STATUS," +
                " AHI.OCCUPY_AREA," +
                " dbo.APP_GET_FLEX_VALUE(AHI.AREA_UNIT, ?) AREA_UNIT,\n" +
                " ARI.RENTAL,\n" +
                " ARI.MONEY_UNIT,\n" +
                " dbo.APP_GET_FLEX_VALUE(ARI.PAY_TYPE, ?) PAY_TYPE,\n" +
                " ARI.END_DATE,\n" +
                " AHI.OFFICE_USAGE,\n" +
                " AHI.OFFICE_TYPE," +
                " EII.ATTRIBUTE2" +
                " FROM " +
                " ETS_ITEM_INFO EII,\n" +
                " AMS_HOUSE_INFO AHI,\n " +
                " ETS_SYSTEM_ITEM ESI,\n" +
                " AMS_RENT_INFO ARI\n" +
                " WHERE " +
                " EII.BARCODE = AHI.BARCODE\n" +
                " AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                " AND AHI.BARCODE *= ARI.BARCODE"
                + " AND EII.ATTRIBUTE2 = '�Ѵ���'"
                + " AND ( AHI.BARCODE LIKE dbo.NVL(?,AHI.BARCODE))"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) "
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR OFFICE_USAGE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR OFFICE_TYPE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.ATTRIBUTE2 LIKE ?)";
//                    +  "AND ( " + SyBaseSQLUtil.isNull() + "  OR )";
//                    + " AND (  " + SyBaseSQLUtil.isNull() + "  OR  AHI.HOUSE_STATUS = ?)";

        if (!StrUtil.isEmpty(amsHouseInfo.getTemp())) {
            sqlArgs.add(DictConstant.AREA_UNIT);
            sqlArgs.add(DictConstant.PAY_TYPE);
            sqlArgs.add(amsHouseInfo.getBarcode());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getOfficeUsage());
            sqlArgs.add(amsHouseInfo.getOfficeUsage());
            sqlArgs.add(amsHouseInfo.getOfficeType());
            sqlArgs.add(amsHouseInfo.getOfficeType());
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getTemp());
        } else {
            sqlArgs.add(DictConstant.AREA_UNIT);
            sqlArgs.add(DictConstant.PAY_TYPE);
            sqlArgs.add(amsHouseInfo.getBarcode());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemName());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add(amsHouseInfo.getItemSpec());
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add("");
            sqlArgs.add(amsHouseInfo.getTemp());
            sqlArgs.add(amsHouseInfo.getTemp());
        }
        if (amsHouseInfo.getCertificate().equals("Y")) {
            sqlStr += " AND  " + SyBaseSQLUtil.isNotNull("TODO") + " ";
        } else if (amsHouseInfo.getCertificate().equals("N")) {
            sqlStr += " AND AHI.HOUSE_CERTIFICATE_NO  " + SyBaseSQLUtil.isNullNoParam() + " ";
        }
        if (amsHouseInfo.getCertificate().equals("Y")) {
            sqlStr += " AND ( " + SyBaseSQLUtil.isNull() + "  OR  AHI.HOUSE_CERTIFICATE_NO = ?)";
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
            sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
        }
        if (amsHouseInfo.getHasLandNo().equals("Y")) {
            sqlStr += " AND  " + SyBaseSQLUtil.isNotNull("AHI.OCCUPY_AREA") + " ";
        } else if (amsHouseInfo.getCertificate().equals("N")) {
            sqlStr += " AND AHI.OCCUPY_AREA  " + SyBaseSQLUtil.isNullNoParam() + " ";
        }
        if (amsHouseInfo.getHasLandNo().equals("Y")) {
            sqlStr += " AND ( " + SyBaseSQLUtil.isNull() + "  OR AHI.LAND_CERTFICATE_NO = ?)";
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
            sqlArgs.add(amsHouseInfo.getLandCertficateNo());
        }


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
        String strSql = "SELECT "
                + " EII.*"
                + " FROM"
                + " ETS_ITEM_INFO  EII\n"
                + " WHERE"
                + " EII.BARCODE = ?";
        strArg.add(barcode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }


    /**
     * ���ܣ��ж����������Ƿ��ڵ�ǰ�������ڡ�
     * @param barcode String
     * @return SQLModel
     */
    public SQLModel isRentModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql=
                " SELECT A.*\n" +
                "  FROM " +
                "   (SELECT " +
                "     EII.ITEM_CODE,\n" +
                "     EII.SYSTEMID SYSTEM_ID,\n" +
                "     EII.BARCODE,\n" +
                "     ESI.ITEM_NAME,\n" +
                "     ESI.ITEM_SPEC,\n" +
                "     AHI.HOUSE_ADDRESS,\n" +
                "     AHI.FLOOR_NUMBER,\n" +
                "     AHI.HOUSE_NO,\n" +
                "     AHI.HOUSE_AREA,\n" +
                "     AHI.AREA_UNIT,\n" +
                "     AHI.HOUSE_CERTIFICATE_NO,\n" +
                "     AHI.HOUSE_USAGE,\n" +
                "     AHI.BUSINESS_AREA,\n" +
                "     AHI.PRODUCE_HOSUSE_AREA,\n" +
                "     AHI.PRODUCE_BASE_AREA,\n" +
                "     AHI.OFFICE_AREA,\n" +
                "     AHI.HOUSE_STATUS,\n" +
                "     AHI.OFFICE_USAGE,\n" +
                "     AHI.OFFICE_TYPE,\n" +
                "     AHI.REMARK HREMARK,\n" +
                "     AHI.LAND_CERTFICATE_NO,\n" +
                "     AHI.OCCUPY_AREA,\n" +
                "     AHI.LAND_TYPE,\n" +
                "     AHR.RENT_ID,\n" +
                "     AHR.RENT_START_DATE,\n" +
                "     AHR.RENT_END_DATE,\n" +
                "     AHR.RENT_UNIT,\n" +
                "     AHR.CONTACT_PERSON,\n" +
                "     AHR.CONTACT_PHONE,\n" +
                "     AHR.RENT_FEE,\n" +
                "     AHR.ENABLED,\n" +
                "     AHR.CURRENCY_UNIT,\n" +
                "     AHR.CREATION_DATE,\n" +
                "     AHR.CREATED_BY,\n" +
                "     AHR.LAST_UPDATE_DATE,\n" +
                "     AHR.LAST_UPDATE_BY\n" +
                "     FROM " +
                "     ETS_ITEM_INFO   EII,\n" +
                "     AMS_HOUSE_INFO  AHI,\n" +
                "     ETS_SYSTEM_ITEM ESI,\n" +
                "     AMS_HOUSE_RENT  AHR\n" +
                "     WHERE " +
                "     EII.BARCODE = AHI.BARCODE\n" +
                "     AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                "     AND AHR.BARCODE = AHI.BARCODE\n" +
                "     AND EII.BARCODE = ?\n" +
                "     AND GETDATE()<AHR.RENT_END_DATE " +
                "     ORDER BY AHR.CREATION_DATE DESC) A\n" +
                "  WHERE " +
                "  ROWNUM < 2";

        strArg.add(barcode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }

       /**
     * ���ܣ���ȡ�жϵ�ǰ�û��Ƿ���Ȩ��ִ�����ݱ༭������BARCODE �Ĵ�����
     *
     * @param barcode String
     * @return SQLModel
     */
    public SQLModel getHouseModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql = "SELECT "
                + " 1"
                + " FROM"
                + " AMS_HOUSE_INFO  AHI\n"
                + " WHERE"
                + " AHI.BARCODE = ?";
        strArg.add(barcode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }

    /**
     * ����:���뵽��ams_rent_info
     *
     * @return
     * @throws SQLModelException
     */
    public SQLModel doCreatRentData(AmsHouseInfoDTO houseRentDTO) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "INSERT INTO" +
                    " AMS_HOUSE_RENT\n" +
                    " (RENT_ID,\n" +
                    "  BARCODE,\n" +
                    "  RENT_START_DATE,\n" +
                    "  RENT_END_DATE,\n" +
                    "  RENT_UNIT,\n" +
                    "  CONTACT_PERSON,\n" +
                    "  CONTACT_PHONE,\n" +
                    "  RENT_FEE,\n" +
                    "  CREATION_DATE,\n" +
                    "  CREATED_BY," +
                    "  CONTRACT_NAME)\n" +
                    "  VALUES\n" +
                    "  ( NEWID(), ?, ?, ?, ?, ?, ?, ?, GETDATE(), ? ,?)";

            sqlArgs.add(houseRentDTO.getBarcode());
            sqlArgs.add(houseRentDTO.getRentStartDate());
            sqlArgs.add(houseRentDTO.getRentEndDate());
            sqlArgs.add(houseRentDTO.getRentUnit());
            sqlArgs.add(houseRentDTO.getContactPerson());
            sqlArgs.add(houseRentDTO.getContactPhone());
            sqlArgs.add(houseRentDTO.getRentFee());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(houseRentDTO.getContractName());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }


    public SQLModel getItInfoModel(AmsHouseInfoDTO amsHouseInfo) {
            SQLModel sqlModel = new SQLModel();
            List sqlArgs = new ArrayList();
            String sqlStr ="UPDATE "
                        + " ETS_ITEM_INFO"
                        + " SET"
//                        + " ATTRIBUTE1 = ?,"
//                        + " ITEM_QTY = 1,"
//                        + " ITEM_CODE = ?,"
                        + " LAST_UPDATE_DATE = GETDATE(),"
                        + " LAST_UPDATE_BY = ?"
                        + " WHERE"
                        + " BARCODE = ?";
//            sqlArgs.add(DictConstant.RENT);
            sqlArgs.add(userAccount.getOrganizationId());
            sqlArgs.add(amsHouseInfo.getBarcode());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);

            return sqlModel;
        }

    /**
     * ����:���뵽��ams_rent_info
     *
     * @return
     * @throws SQLModelException
     */
    public SQLModel doUpdteRentData(AmsHouseInfoDTO houseRentDTO) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "UPDATE" +
                    "  AMS_HOUSE_RENT AHR\n" +
                    "  SET" +
                    "  AHR.BARCODE=?,\n" +
                    "  AHR.RENT_START_DATE=?,\n" +
                    "  AHR.RENT_END_DATE=?,\n" +
                    "  AHR.RENT_UNIT=?,\n" +
                    "  AHR.CONTACT_PERSON=?,\n" +
                    "  AHR.CONTACT_PHONE=?,\n" +
                    "  AHR.RENT_FEE=?,\n" +
                    "  AHR.LAST_UPDATE_DATE=GETDATE(),\n" +
                    "  AHR.LAST_UPDATE_BY=?,\n" +
                    "  AHR.CONTRACT_NAME=?\n" +
                    "  WHERE\n" +
                    "  AHR.RENT_ID=?";

            sqlArgs.add(houseRentDTO.getBarcode());
            sqlArgs.add(houseRentDTO.getRentStartDate());
            sqlArgs.add(houseRentDTO.getRentEndDate());
            sqlArgs.add(houseRentDTO.getRentUnit());
            sqlArgs.add(houseRentDTO.getContactPerson());
            sqlArgs.add(houseRentDTO.getContactPhone());
            sqlArgs.add(houseRentDTO.getRentFee());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(houseRentDTO.getContractName());
            sqlArgs.add(houseRentDTO.getRentId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

   /**
     * ����:�޸ı�ams_rent_info  ��ΪʧЧ
     * @return
     * @throws SQLModelException
     */
    public SQLModel doUpdteDisData(AmsHouseInfoDTO houseRentDTO) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "UPDATE" +
                    "  AMS_HOUSE_RENT AHR\n" +
                    "  SET" +
                    "  AHR.ENABLED='N',\n" +
                    "  AHR.LAST_UPDATE_DATE=GETDATE(),\n" +
                    "  AHR.LAST_UPDATE_BY=?\n" +
                    "  WHERE\n" +
                    "  AHR.RENT_ID=?";

            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(houseRentDTO.getRentId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
//        } catch (CalendarException ex) {
//            ex.printLog();
//            throw new SQLModelException(ex);
//        }
        return sqlModel;
    }

    /**
     * ���ܣ�ɾ��ams_rent_info������
     *
     * @return
     */
    public SQLModel doDeleteRentData() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsLandInfoDTO = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = "DELETE "
                + " FROM"
                + " AMS_RENT_INFO"
                + " WHERE"
                + " RENT_ID = ?";
        sqlArgs.add(amsLandInfoDTO.getRentId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ������µķ��������ʲ���
     *
     * @return SQLModel
     */
    public SQLModel getItemCreateModel(EtsItemInfoDTO etsItemInfo) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//        try {
            List sqlArgs = new ArrayList();
            String sqlStr = "INSERT INTO "
			+ " ETS_ITEM_INFO("
			+ " SYSTEMID,"
			+ " BARCODE,"
			+ " ITEM_CODE,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
            + " ITEM_STATUS,"
            + " ORGANIZATION_ID,"
            + " ADDRESS_ID"
//            + " ATTRIBUTE1"
            + ") VALUES ("
			+ "  NEWID() , ?, ?, GETDATE(), ?, '����',? ,? )";

		sqlArgs.add(etsItemInfo.getBarcode());
		sqlArgs.add(etsItemInfo.getItemCode());
		sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(etsItemInfo.getAddressId());
//        sqlArgs.add(DictConstant.RENT);
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
//        } catch (CalendarException ex) {
//            ex.printLog();
//            throw new SQLModelException(ex);
//        }
        return sqlModel;
    }

    /**
     * ���ܣ�������Ŀ���̡�
     *
     * @return SQLModel
     */
    public SQLModel getProjectCreateModel(EtsItemInfoDTO itemDTO, EtsFaAssetsDTO faDTO) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO "
                + " ETS_PA_PROJECTS_ALL("
                + " PROJECT_ID,"
                + " NAME,"
                + " SEGMENT1,"
                + " PROJECT_TYPE,"
                + " PROJECT_STATUS_CODE,"
                + " START_DATE,"
                + " COMPLETION_DATE,"
                + " ENABLED_FLAG,"
                + " SOURCE,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " MIS_PROJECT_ID"
                + ") VALUES ("
                + "  NEWID(), ?, ?, ?, 'APPROVED', ?, ?, 'Y', 'AMS', GETDATE(), ?,?)";

        sqlArgs.add(faDTO.getProjectName());
//        sqlArgs.add(etsPaProjectsAll.getSegment1());
//        sqlArgs.add(etsPaProjectsAll.getProjectType());
//        sqlArgs.add(etsPaProjectsAll.getStartDate());
//        sqlArgs.add(etsPaProjectsAll.getCompletionDate());
        sqlArgs.add(userAccount.getUserId());
//        sqlArgs.add(etsPaProjectsAll.getMisProjectId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ������µķ��������ʲ�����ϸ��Ϣ��
     *
     * @return SQLModel
     */
    public SQLModel insertHouse(AmsHouseInfoDTO amsHouseInfo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO "
                + " AMS_HOUSE_INFO("
                + " BARCODE,"
//							+ " RENT_PERSON,"
//							+ " RENT_DATE,"
                + " HOUSE_ADDRESS,"
                + " FLOOR_NUMBER,"
                + " HOUSE_NO,"
                + " HOUSE_AREA,"
                + " AREA_UNIT,"
                + " REMARK,"
//							+ " RENTAL,"
//							+ " MONEY_UNIT,"
//							+ " PAY_TYPE,"
//                            + " END_DATE,"
                + " HOUSE_CERTIFICATE_NO,"
                + " HOUSE_USAGE,"
                + " BUSINESS_AREA,"
                + " PRODUCE_HOSUSE_AREA,"
                + " PRODUCE_BASE_AREA,"
                + " OFFICE_AREA,"
                + " HOUSE_STATUS,"
                + " IS_RENT"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        sqlArgs.add(amsHouseInfo.getBarcode());
//			sqlArgs.add(amsHouseInfo.getRentPerson());
//			sqlArgs.add(amsHouseInfo.getRentDate());
        sqlArgs.add(amsHouseInfo.getHouseAddress());
        sqlArgs.add(amsHouseInfo.getFloorNumber());
        sqlArgs.add(amsHouseInfo.getHouseNo());
        sqlArgs.add(amsHouseInfo.getHouseArea());
        sqlArgs.add(amsHouseInfo.getAreaUnit());
        sqlArgs.add(amsHouseInfo.getHremark());
//			sqlArgs.add(amsHouseInfo.getRental());
//			sqlArgs.add(amsHouseInfo.getMoneyUnit());
//			sqlArgs.add(amsHouseInfo.getPayType());
//			sqlArgs.add(amsHouseInfo.getEndDate());
        sqlArgs.add(amsHouseInfo.getHouseCertificateNo());
        sqlArgs.add(amsHouseInfo.getHouseUsage());
        sqlArgs.add(amsHouseInfo.getBusinessArea());
        sqlArgs.add(amsHouseInfo.getProduceHosuseArea());
        sqlArgs.add(amsHouseInfo.getProduceBaseArea());
        sqlArgs.add(amsHouseInfo.getOfficeArea());
        sqlArgs.add(amsHouseInfo.getHouseStatus());
//        System.out.println("amsHouseInfo.getHouseStatus()="+amsHouseInfo.getHouseStatus());
        sqlArgs.add(amsHouseInfo.getIsRent());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����misԱ����Ϣ��
     *
     * @return SQLModel
     */
    public SQLModel getMISemployee(String employeeNumber) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT" +
                "  AME.EMPLOYEE_ID, " +
                "  AME.DEPT_CODE\n" +
                "  FROM " +
                "  AMS_MIS_EMPLOYEE AME\n" +
                "  WHERE AME.EMPLOYEE_NUMBER = ?";
        sqlArgs.add(employeeNumber);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����addressId��
     *
     * @return SQLModel
     */
    public SQLModel getLocId(String locCode, String orgId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT " +
                " AOA.ADDRESS_ID\n" +
                " FROM " +
                " ETS_OBJECT EO, " +
                " AMS_OBJECT_ADDRESS AOA\n" +
                " WHERE " +
                " AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                " AND EO.WORKORDER_OBJECT_CODE = ?\n" +
                " AND EO.ORGANIZATION_ID = ?";
        sqlArgs.add(locCode);
        sqlArgs.add(orgId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ����projectId��
     *
     * @return SQLModel
     */
    public SQLModel getProId(String misProjId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT EPPA.PROJECT_ID  FROM ETS_PA_PROJECTS_ALL EPPA WHERE EPPA.MIS_PROJECT_ID = ?";
        sqlArgs.add(misProjId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ��ж�barcode��
     *
     * @param barcode
     * @return SQLModel
     */
    public SQLModel hasBarcode(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM ETS_ITEM_INFO EII WHERE EII.BARCODE = ?";
        sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��ж�projectId�Ĵ����ԡ�
     *
     * @return SQLModel
     */
    public SQLModel hasProjId(String projectId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT * FROM ETS_PA_PROJECTS_ALL EPPA WHERE EPPA.MIS_PROJECT_ID = ?";
        sqlArgs.add(projectId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ��ı�ets_item_info������Ϊ�Ѵ��á�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getEiiData(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//        EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
        String sqlStr = "UPDATE "
                + " ETS_ITEM_INFO"
                + " SET"
//            + " BARCODE = ?,"
//            + " ITEM_QTY = 1,"
//            + " ITEM_CODE = ?,"
//            + " ADDRESS_ID=?,"
                + " ATTRIBUTE2 ='�Ѵ���',"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��ж�ams_house_info����;�������Ƿ�Ϊ�ա�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel isTempDate(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                " FROM " +
                " AMS_HOUSE_INFO AHI\n" +
                " WHERE " +
                " AHI.OFFICE_USAG " + SyBaseSQLUtil.isNull() + " \n" +
                " AND AHI.OFFICE_TYP " + SyBaseSQLUtil.isNull() + " \n" +
                " AND AHI.BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����뵽�� ams_house_user
     *
     * @param dto
     * @return
     */
    public SQLModel insertUsesInfo(AmsHouseUsesDTO dto) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO AMS_HOUSE_USES\n" +
                "  (USES_ID,\n" +
                "   BARCODE,\n" +
                "   USAGE,\n" +
                "   AREA,\n" +
                "   REMARK)\n" +
                "VALUES\n" +
                "  ( NEWID() ,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?)";
        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(dto.getUsage());
        sqlArgs.add(dto.getArea());
        sqlArgs.add(dto.getRemark());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel getUsesInfo() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsHouseInfoDTO = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = "SELECT AHU.* FROM AMS_HOUSE_USES AHU WHERE AHU.BARCODE = ?";
        sqlArgs.add(amsHouseInfoDTO.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ����countyCode��
     *
     * @return SQLModel
     */
    public SQLModel getCount(String costCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT \n" +
                " EC.COUNTY_CODE\n" +
                " FROM   \n" +
                " ETS_COUNTY         EC,\n" +
                " AMS_MIS_COST_MATCH AMCM\n" +
                " WHERE\n" +
                " EC.COUNTY_CODE_MIS = AMCM.COUNTY_CODE_MIS\n" +
                " AND AMCM.COST_CODE = ?";
        sqlArgs.add(costCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����ams_house_info�ı�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel updateNull(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//        EtsItemInfoDTO etsItemInfo = (EtsItemInfoDTO)dtoParameter;
        String sqlStr = "UPDATE "
                + " AMS_HOUSE_INFO"
                + " SET"
                + " FLOOR_NUMBER = '',"
                + " HOUSE_NO = '',"
                + " HOUSE_AREA = '',"
                + " HOUSE_CERTIFICATE_NO = '',"
                + " HOUSE_USAGE = '',"
                + " BUSINESS_AREA = '',"
                + " PRODUCE_HOSUSE_AREA = '',"
                + " PRODUCE_BASE_AREA = '',"
                + " OFFICE_AREA = '',"
                + " HOUSE_STATUS = '',"
                + " IS_RENT = '',"
                + " AREA_UNIT = '',"
                + " OFFICE_USAGE= '',"
                + " OFFICE_TYPE= '',"
                + " LAND_CERTFICATE_NO='',"
                + " OCCUPY_AREA ='',"
                + " REMARK =''"
                + " WHERE"
                + " BARCODE = ?";

        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

//       /**
//     * ���ܣ�����Զ������ʲ��ص��(EAM) ETS_OBJECT���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
//     * @return SQLModel �������ݲ�����SQLModel
//     */
//    public SQLModel getAOACreateModel(){
//        SQLModel sqlModel = new SQLModel();
//        List sqlArgs = new ArrayList();
//        EtsObjectDTO etsObject = (EtsObjectDTO)dtoParameter;
//        String sqlStr = "INSERT INTO "
//            + " AMS_OBJECT_ADDRESS("
//            + " ADDRESS_ID,"
//            + " OBJECT_NO,"
//            + " ORGANIZATION_ID"
//            + ") VALUES ("
//            + "  " + SyBaseSQLUtil.getNewID( "AMS_OBJECT_ADDRESS_S" ) + " , ?, ?)";
//
//        sqlArgs.add(etsObject.getWorkorderObjectNo());
//        sqlArgs.add(sfUser.getOrganizationId());
//
//        sqlModel.setSqlStr(sqlStr);
//        sqlModel.setArgs(sqlArgs);
//        return sqlModel;
//    }


     /**
     * ���ܣ�����Զ��������������ʲ�(EAM) AMS_LAND_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getRentInfoModel() {          //������Ϣ��ϸ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHouseInfoDTO amsLandInfo = (AmsHouseInfoDTO) dtoParameter;
        String sqlStr = " SELECT " +
                " EII.ITEM_CODE,\n" +
                " EII.SYSTEMID SYSTEM_ID,\n" +
                " EII.BARCODE,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " AHI.BARCODE,\n" +
                " AHI.HOUSE_ADDRESS,\n" +
                " AHI.FLOOR_NUMBER,\n" +
                " AHI.HOUSE_NO,\n" +
                " AHI.HOUSE_AREA,\n" +
                " AHI.AREA_UNIT,\n" +
                " AHI.HOUSE_CERTIFICATE_NO,\n" +
                " AHI.HOUSE_USAGE,\n" +
                " AHI.BUSINESS_AREA,\n" +
                " AHI.PRODUCE_HOSUSE_AREA,\n" +
                " AHI.PRODUCE_BASE_AREA,\n" +
                " AHI.OFFICE_AREA,\n" +
                " AHI.HOUSE_STATUS,\n" +
                " AHI.OFFICE_USAGE,\n" +
                " AHI.OFFICE_TYPE,\n" +
                " AHI.REMARK HREMARK,\n" +
                " AHI.LAND_CERTFICATE_NO,\n" +
                " AHI.OCCUPY_AREA,\n" +
                " AHI.LAND_TYPE,\n" +
                " AHR.RENT_ID,\n" +
                " AHR.RENT_START_DATE,\n" +
                " AHR.RENT_END_DATE,\n" +
                " AHR.RENT_UNIT,\n" +
                " AHR.CONTACT_PERSON,\n" +
                " AHR.CONTACT_PHONE,\n" +
                " AHR.RENT_FEE,\n" +
                " AHR.ENABLED,\n" +
                " AHR.CURRENCY_UNIT,\n" +
                " AHR.CREATION_DATE,\n" +
                " AHR.CREATED_BY,\n" +
                " AHR.LAST_UPDATE_DATE,\n" +
                " AHR.LAST_UPDATE_BY" +
                " FROM " +
                " ETS_ITEM_INFO   EII,\n" +
                " AMS_HOUSE_INFO  AHI,\n" +
                " ETS_SYSTEM_ITEM ESI,\n" +
                " AMS_HOUSE_RENT  AHR\n" +
                " WHERE " +
                " EII.BARCODE = AHI.BARCODE\n" +
                " AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                " AND AHR.BARCODE = AHI.BARCODE"
              + " AND EII.BARCODE = ?"+
                " AND AHR.RENT_ID = ?";


        sqlArgs.add(amsLandInfo.getBarcode());
        sqlArgs.add(amsLandInfo.getRentId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ��ж��û���Ӧ�Ľ�ɫ��
     * @return SQLModel
     */
    public SQLModel isProvince() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT 1\n" +
                        " FROM \n" +
                        " SF_USER SU, \n" +
                        " SF_USER_RIGHT SUR, \n" +
                        " SF_ROLE SR\n" +
                        " WHERE \n" +
                        " SUR.USER_ID = SU.USER_ID\n" +
                        " AND SUR.ROLE_ID = SR.ROLE_ID\n" +
                        " AND SU.USER_ID = ?\n" +
                        " AND SR.ROLE_NAME = 'ʡ���������ʲ�����Ա'\n";

        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
