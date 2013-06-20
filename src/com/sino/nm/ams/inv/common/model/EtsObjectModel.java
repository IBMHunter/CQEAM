package com.sino.nm.ams.inv.common.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsObjectModel</p>
 * <p>Description:�ֿ�ص�����</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yushibo
 * @version 1.0
 */
public class EtsObjectModel extends AMSSQLProducer {

	/**
     * ���ܣ��ʲ��ص��(AMS) ETS_OBJECT ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectDTO ���β���������
     */
	public EtsObjectModel(SfUserDTO userAccount, EtsObjectDTO dtoParameter) {
		super(userAccount, dtoParameter);
		// TODO Auto-generated constructor stub
	}

	/**
     * ���ܣ�����Զ������ʲ��ص��(AMS) ETS_OBJECT���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel(String etsObjectS, String countyCodeMis) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " ETS_OBJECT("
                + " WORKORDER_OBJECT_NO,"
                + " WORKORDER_OBJECT_CODE,"
                + " WORKORDER_OBJECT_NAME,"
                + " WORKORDER_OBJECT_LOCATION,"
                + " ORGANIZATION_ID,"
                + " LOCATION_CODE,"
                + " AREA_TYPE,"
                + " COUNTY_CODE,"
                + " REMARK,"
                + " OBJECT_CATEGORY,"
                + " BUSINESS_CATEGORY,"
                + " CREATION_DATE,"
//                + " CITY_ID ,"
//                + " AREA_ID ,"
                + " CREATED_BY"
                + ") VALUES ("
//                + " ETS_OBJECT_S.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(),?)";
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(),?)";

        sqlArgs.add(etsObjectS);
    	if (etsObject.getBusinessCategory().equals("INV_BIZ_ASSETS")) {
			sqlArgs.add("INIZC" + etsObjectS);
		} else if (etsObject.getBusinessCategory().equals("INV_BIZ_DZYH")) {
			sqlArgs.add("INIDH" + etsObjectS);
		} else if (etsObject.getBusinessCategory().equals("INV_BIZ_INSTRU")) {
			sqlArgs.add("INIYB" + etsObjectS);
		} else if (etsObject.getBusinessCategory().equals("INV_BIZ_PROJECT")) {
			sqlArgs.add("INIGC" + etsObjectS);
		} else if (etsObject.getBusinessCategory().equals("INV_BIZ_SPARE")) {
			sqlArgs.add("INIBJ" + etsObjectS);
		} 
        sqlArgs.add(etsObject.getWorkorderObjectName());
        sqlArgs.add(etsObject.getWorkorderObjectLocation());
        sqlArgs.add(userAccount.getOrganizationId());
//        sqlArgs.add(countyCodeMis + "." + "EAM" + etsObjectS + "." + "000");
        sqlArgs.add(etsObject.getCost_code()+"."+"CK"+etsObject.getWorkorderObjectNo()+"."+"000");
        sqlArgs.add(etsObject.getAreaType());
        sqlArgs.add(etsObject.getCountyCode());
        sqlArgs.add(etsObject.getRemark());
        sqlArgs.add(etsObject.getObjectCategory());
        sqlArgs.add(etsObject.getBusinessCategory());
//        sqlArgs.add(etsObject.getCityId());
//        sqlArgs.add(etsObject.getAreaId());
        sqlArgs.add(userAccount.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ��ص��(AMS) ETS_OBJECT���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateAddressModel(String etsObjectS) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO "
                + " AMS_OBJECT_ADDRESS("
                + " ADDRESS_ID,"
                + " OBJECT_NO,"
                + " BOX_NO,"
                + " NET_UNIT,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " REMARK,"
                + " ADDRESS_NO"
                + ") VALUES ("
//                + " ETS_OBJECT_S.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(),?)";
                + " NEWID(), ?, ?, ?, ?, GETDATE(), ?, ?, ?)";

        sqlArgs.add(etsObjectS);
        sqlArgs.add("0000");
        sqlArgs.add("0000");
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(0);
        sqlArgs.add("EAM�ڳ�����");
        sqlArgs.add(etsObjectS + "." + "0000" + "." + "0000");

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ�����Զ������ʲ��ص��(AMS) ETS_OBJECT���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_OBJECT"
                + " SET"
                + " WORKORDER_OBJECT_CODE = ?,"
                + " WORKORDER_OBJECT_NAME = ?,"
                + " WORKORDER_OBJECT_LOCATION = ?,"
                + " ORGANIZATION_ID = ?,"
                + " AREA_TYPE = ?,"
                + " COUNTY_CODE = ?,"
//                + " DISABLE_DATE = ?,"
                + " REMARK = ?,"
                + " OBJECT_CATEGORY = ?,"
                + " BUSINESS_CATEGORY = ?,"
                + " ISALL = ?,"
                + " IS_TEMP_ADDR = ?,"
//                + " CREATION_DATE = ?,"
//                + " CREATED_BY = ?,"
//                + " LAST_UPDATE_DATE = ?,"
                + " LAST_UPDATE_BY = ?,"
                + " PROJECT_ID = ?"
//                + " CITY_ID = ?,"
//                + " AREA_ID = ?"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";

        sqlArgs.add(etsObject.getWorkorderObjectCode());
        sqlArgs.add(etsObject.getWorkorderObjectName());
        sqlArgs.add(etsObject.getWorkorderObjectLocation());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(etsObject.getAreaType());
        sqlArgs.add(etsObject.getCountyCode());
//        sqlArgs.add(etsObject.getDisableDate());
        sqlArgs.add(etsObject.getRemark());
        sqlArgs.add(etsObject.getObjectCategory());
        sqlArgs.add(etsObject.getBusinessCategory());
        sqlArgs.add(etsObject.getIsall());
        sqlArgs.add(etsObject.getIsTempAddr());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(etsObject.getProjectId());
//        sqlArgs.add(etsObject.getCityId());
//        sqlArgs.add(etsObject.getAreaId());
        sqlArgs.add(etsObject.getWorkorderObjectNo());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ�����Զ������ʲ��ص��(AMS) ETS_OBJECT���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel(String countyCodeMis) {
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_OBJECT"
                + " SET"
//                + " WORKORDER_OBJECT_CODE = ?,"
                + " WORKORDER_OBJECT_NAME = ?,"
                + " WORKORDER_OBJECT_LOCATION = ?,"
                + " ORGANIZATION_ID = ?,"
                + " AREA_TYPE = ?,"
                + " COUNTY_CODE = ?,"
//                + " DISABLE_DATE = ?,"
                + " REMARK = ?,"
                + " OBJECT_CATEGORY = ?,"
                + " BUSINESS_CATEGORY = ?,"
                + " ISALL = ?,"
                + " IS_TEMP_ADDR = ?,"
//                + " CREATION_DATE = ?,"
//                + " CREATED_BY = ?,"
//                + " LAST_UPDATE_DATE = ?,"
                + " LAST_UPDATE_BY = ?,"
                + " LOCATION_CODE = ?,"
                + " PROJECT_ID = ?"
//                + " CITY_ID = ?,"
//                + " AREA_ID = ? "
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";

//        sqlArgs.add(etsObject.getWorkorderObjectCode());
        sqlArgs.add(etsObject.getWorkorderObjectName());
        sqlArgs.add(etsObject.getWorkorderObjectLocation());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(etsObject.getAreaType());
        sqlArgs.add(etsObject.getCountyCode());
//        sqlArgs.add(etsObject.getDisableDate());
        sqlArgs.add(etsObject.getRemark());
        sqlArgs.add(etsObject.getObjectCategory());
        sqlArgs.add(etsObject.getBusinessCategory());
        sqlArgs.add(etsObject.getIsall());
        sqlArgs.add(etsObject.getIsTempAddr());
        sqlArgs.add(userAccount.getUserId());
//        sqlArgs.add(countyCodeMis + "." + "EAM" + etsObject.getWorkorderObjectNo() + "." + "000");
        sqlArgs.add(etsObject.getCost_code()+"."+"CK"+etsObject.getWorkorderObjectNo()+"."+"000");
        sqlArgs.add(etsObject.getProjectId());
//        sqlArgs.add(etsObject.getCityId());
//        sqlArgs.add(etsObject.getAreaId());
        sqlArgs.add(etsObject.getWorkorderObjectNo());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ��ֿ��(AMS) ETS_OBJECT����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    /*public SQLModel getDataDeleteModel(){
         SQLModel sqlModel = new SQLModel();
         List sqlArgs = new ArrayList();
         EtsObjectDTO etsObject = (EtsObjectDTO)dtoParameter;
         String sqlStr = "DELETE FROM"
             + " ETS_OBJECT"
             + " WHERE"
             + " WORKORDER_OBJECT_NO = ?";
         sqlArgs.add(etsObject.getWorkorderObjectNo());
         sqlModel.setSqlStr(sqlStr);
         sqlModel.setArgs(sqlArgs);
         return sqlModel;
     }*/

    /**
     * ���ܣ�����Զ������ʲ��ֿ��(AMS) ETS_OBJECT������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " WORKORDER_OBJECT_NO,"
                + " WORKORDER_OBJECT_CODE,"
                + " WORKORDER_OBJECT_NAME,"
                + " WORKORDER_OBJECT_LOCATION,"
                + " ORGANIZATION_ID,"
                + " COUNTY_CODE,"
                + " DISABLE_DATE,"
                + " REMARK,"
                + " OBJECT_CATEGORY,"
                + " BUSINESS_CATEGORY,"
                + " ISALL,"
                + " IS_TEMP_ADDR,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " PROJECT_ID"
//                + " CITY_ID,"
//                + " AREA_ID"
                + " FROM"
                + " ETS_OBJECT"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(etsObject.getWorkorderObjectNo());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ������ʲ��ص��(AMS) ETS_OBJECTҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        
        String sqlStr = "";
        	   sqlStr = "SELECT" +
        	   			" EO.WORKORDER_OBJECT_NO," +
        	   			" EO.WORKORDER_OBJECT_CODE," +
        	   			" EO.WORKORDER_OBJECT_NAME," +
        	   			" EO.WORKORDER_OBJECT_LOCATION," +
        	   			" EC.COUNTY_NAME," +
        	   			" EFV.VALUE," +
        	   			" EO.DISABLE_DATE," +
        	   			" EO.ORGANIZATION_ID," +
        	   			" EO.OBJECT_CATEGORY," +
        	   			" EO.LOCATION_CODE," +
        	   			" dbo.APP_GET_FLEX_VALUE(EO.OBJECT_CATEGORY, 'INV_TYPE') INV_CATEGORY_NAME," +
//        	   			" AMS_PUB_PKG.GET_FLEX_VALUE(EO.BUSINESS_CATEGORY, 'INV_BIZ_CATEGORY') BIZ_CATEGORY_NAME," +
        	   			" dbo.APP_GET_FLEX_VALUE(EO.AREA_TYPE, 'ADDR_AREA_TYPE') AREA_TYPE," +
        	   			" EO.BUSINESS_CATEGORY " +
//        	   			" EO.CITY_ID ," +  SYBASE���޴��ֶ�
//        	   			" EO.AREA_ID " +   SYBASE���޴��ֶ�
        	   			" FROM" +
        	   			" ETS_OBJECT EO," + 
        	   			" ETS_COUNTY EC," + 
        	   			" ETS_FLEX_VALUES EFV," + 
        	   			" ETS_FLEX_VALUE_SET EFVS" +
        	   			" WHERE" +
        	   			" EO.COUNTY_CODE *= EC.COUNTY_CODE" +
        	   			" AND EO.WORKORDER_OBJECT_CODE LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_CODE)" +
        	   			" AND EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)" +
        	   			" AND EO.WORKORDER_OBJECT_LOCATION LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_LOCATION)" +
        	   			" AND (EO.ORGANIZATION_ID = ? OR -1 = ?)" +
        	   			" AND ("+ SyBaseSQLUtil.nullStringParam() +" OR EO.COUNTY_CODE LIKE ?)" +
        	   			" AND EO.OBJECT_CATEGORY LIKE dbo.NVL(?, EO.OBJECT_CATEGORY)" +
        	   			" AND EO.BUSINESS_CATEGORY LIKE dbo.NVL(?, EO.BUSINESS_CATEGORY)" +
        	   			" AND EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID" +
        	   			" AND EFV.CODE = EO.BUSINESS_CATEGORY" +
        	   			" AND EFVS.CODE = 'INV_BIZ_CATEGORY'" +
        	   			" AND CONVERT(INT,EO.OBJECT_CATEGORY,1) > ?" +
        	   			" AND CONVERT(INT,EO.OBJECT_CATEGORY,1) < ?";
        
        sqlArgs.add(etsObject.getWorkorderObjectCode());
        
        sqlArgs.add(etsObject.getWorkorderObjectName());
        
        sqlArgs.add(etsObject.getWorkorderObjectLocation());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(userAccount.getOrganizationId());
        
        sqlArgs.add(etsObject.getCountyCode());
        sqlArgs.add(etsObject.getCountyCode());
        sqlArgs.add(etsObject.getCountyCode());
        
        sqlArgs.add(etsObject.getObjectCategory());
        
        sqlArgs.add(etsObject.getBusinessCategory());
        
        sqlArgs.add(Integer.parseInt(WebAttrConstant.INV_CATEGORY));
        sqlArgs.add(Integer.parseInt(DictConstant.NETADDR_OTHERS));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ִ������ʧЧ������
     * @param workorderObjectCodes �ֿ�ID
     * @return SQLModel ����������SQLModel
     */
    public SQLModel getDisabledModel(String[] workorderObjectCodes) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//		EtsObjectDTO etsObject = (EtsObjectDTO)dtoParameter;
        String orderno = ArrUtil.arrToStr(workorderObjectCodes, 0, workorderObjectCodes.length - 1, "'", ",");
        String sqlStr = "UPDATE "
                + " ETS_OBJECT"
                + " SET"
                + " DISABLE_DATE = GETDATE()"
                + " WHERE"
                + " WORKORDER_OBJECT_CODE IN (" + orderno + ")";
//        sqlArgs.add(orderno);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�ִ��������Ч������
     * @param workorderObjectCodes �ֿ�ID
     * @return SQLModel ����������SQLModel
     */
    public SQLModel getEfficientModel(String[] workorderObjectCodes) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//		EtsObjectDTO etsObject = (EtsObjectDTO)dtoParameter;
        String orderno = ArrUtil.arrToStr(workorderObjectCodes, 0, workorderObjectCodes.length - 1, "'", ",");
        String sqlStr = "UPDATE "
                + " ETS_OBJECT"
                + " SET"
                + " DISABLE_DATE = NULL"
                + " WHERE"
                + " WORKORDER_OBJECT_CODE IN (" + orderno + ")";
//        sqlArgs.add(orderno);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ִ��ʧЧ������
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getInureModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " ETS_OBJECT"
                + " SET"
                + " DISABLE_DATE = null"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(etsObject.getWorkorderObjectNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ִ��ʧЧ������
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " ETS_OBJECT"
                + " SET"
                + " DISABLE_DATE = GETDATE()"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(etsObject.getWorkorderObjectNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����ͬ��֯���Ƿ�����ͬ���Ͳֿ�
     * @param objCategory �ֿ�����
     * @return SQLModel
     */
    public SQLModel getTypeObjHasBeenModel(String objCategory) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT  EO.WORKORDER_OBJECT_CODE " +
                " FROM ETS_OBJECT EO " +
                " WHERE   EO.OBJECT_CATEGORY= ? " +
                " AND EO.ORGANIZATION_ID = ?" +
                " AND ROWNUM < 2 ";
        sqlArgs.add(objCategory);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����WORKORDER_OBJECT_CODE�Ƿ��ظ�
     * @param objCode �ֿ����
     * @return SQLModel
     */
    public SQLModel getCodeHasBeenModel(String objCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT 1 FROM ETS_OBJECT EO " +
                " WHERE EO.WORKORDER_OBJECT_CODE = ?  ";
        sqlArgs.add(objCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ�������ҳ����һ����˾ֻ������һ�������Ǳ�������
     * @return SQLModel
     */
    public SQLModel getFieldsHasBeenModel(String objectCategory, String businessCategory) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT 1 FROM ETS_OBJECT EO " +
                " WHERE EO.OBJECT_CATEGORY=? AND EO.BUSINESS_CATEGORY=? AND EO.ORGANIZATION_ID=?  ";
        sqlArgs.add(objectCategory);
        sqlArgs.add(businessCategory);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ����޸�ҳ����һ����˾ֻ������һ�������Ǳ�������
     * @return SQLModel
     */
    public SQLModel getFieldsHasBeenModel(String workorderObjectCode, String objectCategory, String businessCategory) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT 1 FROM ETS_OBJECT EO " +
                " WHERE EO.OBJECT_CATEGORY=? AND EO.BUSINESS_CATEGORY=? AND EO.ORGANIZATION_ID=?  " + 
                " AND EO.WORKORDER_OBJECT_CODE <> (SELECT EO1.WORKORDER_OBJECT_CODE FROM ETS_OBJECT EO1 WHERE EO1.WORKORDER_OBJECT_CODE = ?)" ;
        sqlArgs.add(objectCategory);
        sqlArgs.add(businessCategory);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(workorderObjectCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ��ص��(AMS_OBJECT_ADDRESS)�в�������
     * @return
     */
    public SQLModel getAddressCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
        String sqlStr = "INSERT INTO AMS_OBJECT_ADDRESS\n" +
                "  (ADDRESS_ID,\n" +
                "   OBJECT_NO,\n" +
                "   BOX_NO,\n" +
                "   NET_UNIT,\n" +
                "   ORGANIZATION_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   REMARK,\n" +
                "   ADDRESS_NO)\n" +
                "  ( SELECT \n" +
                "   NEWID() ADDRESS_ID,\n" +
                "   EO.WORKORDER_OBJECT_NO OBJECT_NO,\n" +
                "   '0000' BOX_NO,\n" +
                "   '0000' NET_UNIT,\n" +
                "   EO.ORGANIZATION_ID ORGANIZATION_ID,\n" +
                "   EO.CREATION_DATE CREATION_DATE,\n" +
                "   EO.CREATED_BY CREATED_BY,\n" +
                "   EO.REMARK REMARK,\n" +
                "   EO.WORKORDER_OBJECT_NO||'.0000.0000' ADDRESS_NO\n" +
                "   FROM ETS_OBJECT EO WHERE rownum<2 AND EO.WORKORDER_OBJECT_CODE = ?\n" +
                "   )";
        sqlArgs.add(etsObject.getWorkorderObjectCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ĳ�ֿ��Ƿ����豸
     * @param workorderObjectCode �ֿ����
     * @return SQLModel
     */
    public SQLModel getItemsByObjectModel(String workorderObjectCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                "  FROM AMS_SPARE_INFO ASI, ETS_OBJECT EO\n" +
                " WHERE ASI.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND (ASI.QUANTITY > 0 OR ASI.DISREPAIR_QUANTITY > 0)\n" +
                "   AND EO.WORKORDER_OBJECT_CODE =  ?\n";
        sqlArgs.add(workorderObjectCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    public SQLModel getAreaNameModel(int areaId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
        String sqlStr = "SELECT ECA.AREA_NAME AREA_NAME FROM EAM_CITY_AREA ECA WHERE ECA.AREA_ID =?";
        sqlArgs.add(areaId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
    
    public SQLModel getCityNameModel(int cityId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
        String sqlStr = "SELECT ECA.CITY_NAME CITY_NAME FROM ETS_OU_CITY_MAP EOCM,EAM_CITY_AREA ECA " +
        		"WHERE ECA.CITY_ID = EOCM.ORGANIZATION_ID " +
        		"AND EOCM.ORGANIZATION_ID =?";
        sqlArgs.add(cityId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
    
    /**
	 * ���ܣ�����countyCode��ȡcountyCodeMis��ֵ
	 * @return SQLModel
	 */
	public SQLModel getCountyCodeMis(String countyCode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        
        String strSql ="SELECT EC.COUNTY_CODE_MIS\n" +
                "  FROM ETS_COUNTY   EC\n" +
                " WHERE EC.COUNTY_CODE = ?";
        
        strArg.add(countyCode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }
	   public SQLModel getNameModel(String code) {
	        SQLModel sqlModel = new SQLModel();
	        List sqlArgs = new ArrayList();
	        EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
	        String sqlStr = "SELECT EC.COUNTY_NAME FROM ETS_COUNTY EC WHERE EC.COUNTY_CODE = ?";

	        sqlArgs.add(code);
	        sqlModel.setSqlStr(sqlStr);
	        sqlModel.setArgs(sqlArgs);
			return sqlModel;
		}
}
