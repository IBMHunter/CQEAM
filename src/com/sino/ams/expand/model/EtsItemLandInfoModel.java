package com.sino.ams.expand.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.expand.dto.EtsItemLandInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: EtsItemLandInfoModel</p>
 * <p>Description:�����Զ�����SQL��������EtsItemLandInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����
 * @version 1.0
 */


public class EtsItemLandInfoModel extends AMSSQLProducer {

    /**
     * ���ܣ����������ʲ�(AMS) AMS_LAND_INFO ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemLandInfoDTO ���β���������
     */
    public EtsItemLandInfoModel(SfUserDTO userAccount, EtsItemLandInfoDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(AMS) AMS_LAND_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {          //��ϸ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
        String sqlStr = "SELECT EII.ITEM_CODE,\n" +
                " EII.SYSTEMID SYSTEM_ID,\n" +
                " EII.BARCODE,\n" +
                " EII.ADDRESS_ID,\n" +
                " ESI.ITEM_NAME,\n" +
                " ESI.ITEM_SPEC,\n" +
                " ARI.RENT_PERSON,\n" +
                " ARI.RENT_DATE,\n" +
                " ALI.BARCODE,\n" +
                " ALI.AREA_UNIT,\n" +
                " ALI.LAND_AREA,\n" +
                " ALI.REMARK,\n" +
                " ALI.LAND_CERTFICATE_NO,\n" +
                " ALI.LAND_ADDRESS,\n" +
                " dbo.NVL(ALI.IS_RENT,'N') IS_RENT,\n" +
                " ARI.RENT_ID,\n" +
                " ARI.RENTAL,\n" +
                " ARI.MONEY_UNIT,\n" +
                " ARI.PAY_TYPE,\n" +
                " ARI.END_DATE\n" +
                " FROM " +
                " ETS_ITEM_INFO   EII,\n" +
                " AMS_LAND_INFO   ALI,\n" +
                " ETS_SYSTEM_ITEM ESI,\n" +
                " AMS_RENT_INFO   ARI\n" +
                //" WHERE EII.BARCODE = ALI.BARCODE(+)\n" +
                " WHERE EII.BARCODE *= ALI.BARCODE\n" +
                " AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                //" AND ARI.BARCODE(+) = ALI.BARCODE\n" +
                " AND ARI.BARCODE =* ALI.BARCODE\n" +
                " AND EII.BARCODE = ?";
        sqlArgs.add(dto.getBarcode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������������ʲ�(AMS) AMS_LAND_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {   //��ѯʹ�õ�sql
        SQLModel sqlModel = new SQLModel();
             try {
        List sqlArgs = new ArrayList();
        EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
        String sqlStr = "SELECT" 
        	+ " EII.SYSTEMID,\n" 
        	+ " EII.ADDRESS_ID,\n" 
        	+ " EII.BARCODE,\n" 
        	+ " ALI.LAND_AREA,\n" 
        	+ " dbo.APP_GET_FLEX_VALUE(ALI.AREA_UNIT, ?) AREA_UNIT,\n" 
        	+ " ALI.LAND_CERTFICATE_NO,\n" 
        	+ " dbo.APP_GET_COUNTY_NAME(EII.ADDRESS_ID) COUNTY_NAME,\n" 
        	+ " (CASE ALI.IS_RENT WHEN 'Y' THEN '��' ELSE '��' END) IS_RENT,\n" 
        	+ " ARI.RENT_PERSON,\n" 
        	+ " ARI.RENT_DATE,\n" 
        	+ " ALI.REMARK,\n" 
        	+ " ALI.LAND_ADDRESS,\n" 
            + " ARI.RENTAL,\n" 
            + " ARI.MONEY_UNIT,\n" 
            + " dbo.APP_GET_FLEX_VALUE(ARI.PAY_TYPE, ?) PAY_TYPE,\n" 
            + " ARI.END_DATE\n" 
            + " FROM \n" 
            + " ETS_ITEM_INFO 		EII,\n" 
            + " AMS_LAND_INFO 		ALI,\n" 
            + " AMS_RENT_INFO 		ARI,\n" 
            + " ETS_SYSTEM_ITEM 	ESI\n" 
            + " WHERE" 
            + " ESI.ITEM_CODE = EII.ITEM_CODE\n" 
            + " AND EII.BARCODE = ALI.BARCODE\n" 
            + " AND ALI.BARCODE = ARI.BARCODE\n" 
            + " AND ESI.ITEM_CATEGORY = 'LAND'\n" 
            //+ " AND (EII.BARCODE(+) LIKE NVL(?,EII.BARCODE(+)))\n"
            + " AND (EII.BARCODE LIKE dbo.NVL(?,EII.BARCODE))\n" 
            + " AND EII.ORGANIZATION_ID =? \n" 
            + " AND (CONVERT(VARCHAR, ?) = '' OR ARI.RENT_DATE >= ?)" 
            + " AND (CONVERT(VARCHAR, ?) = '' OR ARI.RENT_DATE <= ?)" 
            + " AND (? = '' OR ALI.IS_RENT = ?)";

        sqlArgs.add(DictConstant.LAND_AREA_UNIT);
        sqlArgs.add(DictConstant.PAY_TYPE);
        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(dto.getFromDate());
        sqlArgs.add(dto.getFromDate());
        sqlArgs.add(dto.getToDate());
        sqlArgs.add(dto.getToDate());
        sqlArgs.add(dto.getIsRent());
        sqlArgs.add(dto.getIsRent());
        
        if (dto.getHasCertficate().equals("Y")) {
            sqlStr += " AND LTRIM(ALI.LAND_CERTFICATE_NO) IS NOT NULL";
        } else if (dto.getHasCertficate().equals("N")) {
            sqlStr += " AND LTRIM(ALI.LAND_CERTFICATE_NO) IS NULL";
        }
        /*if (dto.getIsRent().equals("Y")) {
            sqlStr += " AND EII.ATTRIBUTE1='RENT'";
        } else if (dto.getIsRent().equals("N")) {
            sqlStr += " AND EII.ATTRIBUTE1 IS NULL";
        }*/
        
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
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

    public SQLModel doDeleteRentData() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
        String sqlStr = "DELETE "
                + " FROM"
                + " AMS_RENT_INFO"
                + " WHERE"
                + " RENT_ID = ?";
        sqlArgs.add(dto.getRentId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel doCreatRentData() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
            String sqlStr = "INSERT INTO" +
                    "   AMS_RENT_INFO\n" +
                    "   (RENT_ID,\n" +
                    "   BARCODE,\n" +
                    "   RENT_PERSON,\n" +
                    "   RENT_DATE,\n" +
                    "   RENTAL,\n" +
                    "   MONEY_UNIT,\n" +
                    "   PAY_TYPE,\n" +
                    "   END_DATE,\n" +
                    "   RENT_USAGE,\n" +
                    "   CREATION_DATE,\n" +
                    "   CREATED_BY)\n" +
                    "	VALUES\n" +
                    "  (NEWID(), ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";

            sqlArgs.add(dto.getBarcode());
            sqlArgs.add(dto.getRentPerson());
            sqlArgs.add(dto.getRentDate());
            sqlArgs.add(dto.getRental());
            sqlArgs.add(dto.getMoneyUnit());
            sqlArgs.add(dto.getPayType());
            sqlArgs.add(dto.getEndDate());
            sqlArgs.add(dto.getRentUsage());
            sqlArgs.add(userAccount.getUserId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    public SQLModel doUpdateRentData() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
            String sqlStr = "UPDATE" +
                    "   AMS_RENT_INFO\n" +
                    "   SET" +
                    "   BARCODE = ?,\n" +
                    "   RENT_PERSON = ?,\n" +
                    "   RENT_DATE = ?,\n" +
                    "   RENTAL = ?,\n" +
                    "   MONEY_UNIT = ?,\n" +
                    "   PAY_TYPE = ?,\n" +
                    "   END_DATE = ?,\n" +
                    "   RENT_USAGE = ?,\n" +
                    "   LAST_UPDATE_DATE = GETDATE(),\n" +
                    "   LAST_UPDATE_BY = ?\n" +
                    "	WHERE\n" +
                    "   RENT_ID = ?";

            sqlArgs.add(dto.getBarcode());
            sqlArgs.add(dto.getRentPerson());
            sqlArgs.add(dto.getRentDate());
            sqlArgs.add(dto.getRental());
            sqlArgs.add(dto.getMoneyUnit());
            sqlArgs.add(dto.getPayType());
            sqlArgs.add(dto.getEndDate());
            sqlArgs.add(dto.getRentUsage());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getRentId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }
    /**
     * ���ܣ�����Զ��������������ʲ�(AMS) AMS_LAND_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getCreateLandModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//		try {
        List sqlArgs = new ArrayList();
        EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_LAND_INFO("
                + " BARCODE,"
                + " AREA_UNIT,"
                + " LAND_AREA,"
                + " REMARK,"
                + " LAND_CERTFICATE_NO,"
                + " LAND_ADDRESS,"
                + " IS_RENT"
                + ") VALUES ("
                + " ?, ?, CONVERT(DECIMAL(18,2), ?), ?, ?, ?, ?)";

        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(dto.getAreaUnit());
        sqlArgs.add(dto.getLandArea());
        sqlArgs.add(dto.getRemark());
        sqlArgs.add(dto.getLandCertficateNo());
        sqlArgs.add(dto.getLandAddress());
        sqlArgs.add(dto.getIsRent());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
        return sqlModel;
    }
    
    public SQLModel getUpdateLandModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//		try {
        List sqlArgs = new ArrayList();
        EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " AMS_LAND_INFO SET"
                + " AREA_UNIT = ?,"
                + " LAND_AREA = CONVERT(DECIMAL(18,2), ?),"
                + " REMARK = ?,"
                + " LAND_CERTFICATE_NO = ?,"
                + " LAND_ADDRESS = ?,"
                + " IS_RENT = ?"
                + " WHERE"
                + " BARCODE = ?";

        sqlArgs.add(dto.getAreaUnit());
        sqlArgs.add(dto.getLandArea());
        sqlArgs.add(dto.getRemark());
        sqlArgs.add(dto.getLandCertficateNo());
        sqlArgs.add(dto.getLandAddress());
        sqlArgs.add(dto.getIsRent());
        sqlArgs.add(dto.getBarcode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
        return sqlModel;
    }
    /**
     * ���ܣ�����Զ��������������ʲ�(AMS) AMS_LAND_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDeleteLandModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsItemLandInfoDTO dto = (EtsItemLandInfoDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_LAND_INFO"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}