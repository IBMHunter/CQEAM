package com.sino.ams.system.trust.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.trust.dto.AmsMaintainResponsibilityDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsMaintainResponsibilityModel</p>
 * <p>Description:�����Զ�����SQL��������AmsMaintainResponsibilityModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsMaintainResponsibilityModel extends AMSSQLProducer {

    /**
     * ���ܣ���ά���� AMS_MAINTAIN_RESPONSIBILITY ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsMaintainResponsibilityDTO ���β���������
     */
    public AmsMaintainResponsibilityModel(SfUserDTO userAccount, AmsMaintainResponsibilityDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ����ɴ�ά���� AMS_MAINTAIN_RESPONSIBILITY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_MAINTAIN_RESPONSIBILITY("
                + " SYSTEM_ID,"
                + " COMPANY_ID,"
                + " OBJECT_NO,"
                + " ORGANIZATION_ID,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY"
                + ") VALUES ("
                + "  ? , ?, ?, ?, ?, GETDATE(), ?)";
        sqlArgs.add(dto.getSystemId());
        sqlArgs.add(dto.getCompanyId()+"");
        sqlArgs.add(dto.getObjectNo()+"");
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(dto.getRemark());
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά���� AMS_MAINTAIN_RESPONSIBILITY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_MAINTAIN_RESPONSIBILITY"
                + " SET"
                + " COMPANY_ID = ?,"
                + " OBJECT_NO = ?,"
                + " ORGANIZATION_ID = ?,"
                + " REMARK = ?,"
//			+ " CREATION_DATE = GETDATE(),"
//			+ " CREATED_BY = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " SYSTEM_ID = ?";

        sqlArgs.add(dto.getCompanyId());
        sqlArgs.add(dto.getObjectNo());
        sqlArgs.add(dto.getOrganizationId());
        sqlArgs.add(dto.getRemark());
//		sqlArgs.add(dto.getCreationDate());
//		sqlArgs.add(dto.getCreatedBy());
//		sqlArgs.add(dto.getLastUpdateDate());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getSystemId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά���� AMS_MAINTAIN_RESPONSIBILITY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "DELETE"
                + " FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY "
                + " WHERE"
                + " OBJECT_NO = ? ";
        sqlArgs.add(dto.getObjectNo()+"");
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά���� AMS_MAINTAIN_RESPONSIBILITY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEM_ID,"
                + " COMPANY_ID,"
                + " OBJECT_NO,"
                + " ORGANIZATION_ID,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " SYSTEM_ID = ?";
        sqlArgs.add(dto.getSystemId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� companyId �����ѯ����SQL��
     * ����Զ��������ݴ�ά���� AMS_MAINTAIN_RESPONSIBILITY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param companyId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByCompanyIdModel(String companyId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " SYSTEM_ID,"
                + " OBJECT_NO,"
                + " ORGANIZATION_ID,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " COMPANY_ID = ?";
        sqlArgs.add(companyId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� objectNo �����ѯ����SQL��
     * ����Զ��������ݴ�ά���� AMS_MAINTAIN_RESPONSIBILITY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param objectNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByObjectNoModel(int objectNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " SYSTEM_ID,"
                + " COMPANY_ID,"
                + " ORGANIZATION_ID,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " OBJECT_NO = ?";
        sqlArgs.add(objectNo);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� organizationId �����ѯ����SQL��
     * ����Զ��������ݴ�ά���� AMS_MAINTAIN_RESPONSIBILITY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param organizationId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByOrganizationIdModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " SYSTEM_ID,"
                + " COMPANY_ID,"
                + " OBJECT_NO,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " ORGANIZATION_ID = ?";
        sqlArgs.add(organizationId);

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
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        if (foreignKey.equals("companyId")) {
            sqlModel = getDataByCompanyIdModel(dto.getCompanyId());
        } else if (foreignKey.equals("objectNo")) {
            sqlModel = getDataByObjectNoModel(dto.getObjectNo());
        } else if (foreignKey.equals("organizationId")) {
            sqlModel = getDataByOrganizationIdModel(dto.getOrganizationId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� companyId ��������ɾ��SQL��
     * ����Զ��������ݴ�ά���� AMS_MAINTAIN_RESPONSIBILITY ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param companyId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByCompanyIdModel(String companyId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " COMPANY_ID = ?";
        sqlArgs.add(companyId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� objectNo ��������ɾ��SQL��
     * ����Զ��������ݴ�ά���� AMS_MAINTAIN_RESPONSIBILITY ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param objectNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByObjectNoModel(int objectNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " OBJECT_NO = ?";
        sqlArgs.add(objectNo);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� organizationId ��������ɾ��SQL��
     * ����Զ��������ݴ�ά���� AMS_MAINTAIN_RESPONSIBILITY ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param organizationId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByOrganizationIdModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY"
                + " WHERE"
                + " ORGANIZATION_ID = ?";
        sqlArgs.add(organizationId);

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
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        if (foreignKey.equals("companyId")) {
            sqlModel = getDeleteByCompanyIdModel(dto.getCompanyId());
        } else if (foreignKey.equals("objectNo")) {
            sqlModel = getDeleteByObjectNoModel(dto.getObjectNo());
        } else if (foreignKey.equals("organizationId")) {
            sqlModel = getDeleteByOrganizationIdModel(dto.getOrganizationId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά���� AMS_MAINTAIN_RESPONSIBILITYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "   SELECT " +
//                        "       AC.SYSTEM_ID SYSTEM_ID,\n"+
                "      	CONVERT( VARCHAR,EO.WORKORDER_OBJECT_NO ) ||'--------'|| CONVERT( VARCHAR , AC.COMPANY_ID )  SUB_CHECK , " +
                "       EO.WORKORDER_OBJECT_NO OBJECT_NO,\n" +
                "       AC.COMPANY_ID COMPANY_ID, \n" +
                "       EO.WORKORDER_OBJECT_CODE OBJECT_CODE ,\n" +
                "       EO.WORKORDER_OBJECT_NAME OBJECT_NAME ,\n" +
                "       EC.COUNTY_NAME COUNTY_NAME,\n" +
                "       dbo.NVL(AC.COMPANY_NAME,'δ����') COMPANY_NAME \n" +
                "          FROM  ETS_OBJECT EO, \n" +
                "                ETS_OU_CITY_MAP    EOCM,\n"+
                "               ( SELECT " +
//                        "                     AMS.SYSTEM_ID,\n" +
                "                     AMS.COMPANY_ID,\n" +
                "                     AMS.OBJECT_NO ,\n" +
                "                     AMC.NAME COMPANY_NAME \n" +
                "                     FROM  AMS_MAINTAIN_RESPONSIBILITY AMS, \n" +
                "                           AMS_MAINTAIN_COMPANY        AMC \n" +
                "                           WHERE  AMS.COMPANY_ID = AMC.COMPANY_ID  AND \n" +
                "                               (" + SyBaseSQLUtil.nullStringParam() + " OR ?='"+SyBaseSQLUtil.NULL_INT_VALUE+"' OR    AMC.COMPANY_ID = ?)" +
                "               ) AC ,\n" +
                "               ETS_COUNTY EC \n" +
                "       WHERE  AC.OBJECT_NO = EO.WORKORDER_OBJECT_NO AND \n" +
                "              EO.COUNTY_CODE*= EC.COUNTY_CODE  \n" +
                "         AND EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID \n "    +
                "         AND EOCM.COMPANY_CODE*=EC.COMPANY_CODE \n "  +
//                "              AND    EO.WORKORDER_OBJECT_CODE NOT LIKE '%DL%'\n" +
//                "              AND    EO.WORKORDER_OBJECT_CODE NOT LIKE '%GL%'\n" +
//                "              AND    EO.WORKORDER_OBJECT_CODE NOT LIKE '%GD%'" +
                "         AND  EO.ORGANIZATION_ID = ?  AND \n" +
                "       (  (" + SyBaseSQLUtil.nullStringParam() + " OR    EO.WORKORDER_OBJECT_CODE LIKE ?) OR \n" +
                "           (" + SyBaseSQLUtil.nullStringParam() + " OR   EO.WORKORDER_OBJECT_NAME LIKE ?) )AND \n" +
                "           (" + SyBaseSQLUtil.nullStringParam() + " OR     EO.COUNTY_CODE = ?) AND \n" +
                "            ( ( EO.OBJECT_CATEGORY < ?)  OR \n" +
                "                  ( EO.OBJECT_CATEGORY >= ?))" +
                "       AND ( " + SyBaseSQLUtil.isNull() + "  OR ?='"+SyBaseSQLUtil.NULL_INT_VALUE+"' OR AC.COMPANY_ID = ?) ";
        if (StrUtil.nullToString(dto.getIsall()).equals("1")) {
            sqlStr += "AND (AC.COMPANY_NAME IS NULL OR AC.COMPANY_NAME='')";
        } else if (StrUtil.nullToString(dto.getIsall()).equals("0")) {
            sqlStr += " AND  " + SyBaseSQLUtil.isNotNull("AC.COMPANY_NAME") + " ";
        }
        sqlStr += "  ORDER BY AC.COMPANY_ID";

//        sqlArgs.add(dto.getCompanyId2());
//        sqlArgs.add(dto.getCompanyId2());
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getCompanyId2()+"" );
        sqlArgs.add(dto.getCompanyId2()+"");
        sqlArgs.add(userAccount.getOrganizationId());
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getWorkorderObjectCode() );
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getWorkorderObjectName() );
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getCountyCode() );
        
//        sqlArgs.add(dto.getWorkorderObjectName());
//        sqlArgs.add(dto.getWorkorderObjectName());
//        sqlArgs.add(dto.getWorkorderObjectName());
//        sqlArgs.add(dto.getWorkorderObjectName());
//        sqlArgs.add(dto.getCountyCode());
//        sqlArgs.add(dto.getCountyCode());
        
        sqlArgs.add(WebAttrConstant.INV_CATEGORY+"");
        sqlArgs.add(WebAttrConstant.INV_CATEGORY_MAX+"");
        sqlArgs.add(dto.getCompanyId2()+"");
        sqlArgs.add(dto.getCompanyId2()+"");
        sqlArgs.add(dto.getCompanyId2()+"");

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ά��˾��δȷ�ϵ����εص��SQL
     *
     * @return SQLModel
     */
    public SQLModel getToConfirmLocModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "SELECT"
                + " EO.WORKORDER_OBJECT_NO,"
                + " EO.WORKORDER_OBJECT_LOCATION"
                + " FROM"
                + " ETS_OBJECT EO"
                + " WHERE"
                + " EO.ORGANIZATION_ID = ?"
                + " AND (EO.WORKORDER_OBJECT_CODE LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_CODE)"
                + " OR EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)"
                + " OR EO.WORKORDER_OBJECT_LOCATION LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_LOCATION))"
                + " AND NOT EXISTS ("
                + " SELECT"
                + " NULL"
                + " FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY AMR"
                + " WHERE"
                + " EO.WORKORDER_OBJECT_NO = AMR.OBJECT_NO)";
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    /**
     * ���ܣ������ά��˾��δȷ�ϵ����εص��SQL
     *
     * @return SQLModel
     */
    public SQLModel getLDeleteByCompanyIdModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMaintainResponsibilityDTO dto = (AmsMaintainResponsibilityDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_MAINTAIN_RESPONSIBILITY AMR"
                + " WHERE"
                + " AMR.COMPANY_ID = ?";
        sqlArgs.add(userAccount.getMaintainCompany());
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }
}
