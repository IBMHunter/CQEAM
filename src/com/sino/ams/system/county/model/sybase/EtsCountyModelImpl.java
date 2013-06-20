package com.sino.ams.system.county.model.sybase;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.county.dto.EtsCountyDTO;
import com.sino.ams.system.county.model.EtsCountyModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsCountyModel</p>
 * <p>Description:�����Զ�����SQL��������EtsCountyModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author zz_jlc
 * @version 1.0
 */


public class EtsCountyModelImpl extends BaseSQLProducer implements EtsCountyModel {

    private SfUserDTO sfUser = null;


    /**
     * ���ܣ����ر�(EAM) ETS_COUNTY ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����


     @param dtoParameter EtsCountyDTO ���β���������
     */
    public EtsCountyModelImpl(SfUserDTO userAccount, EtsCountyDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ��������ر�(EAM) ETS_COUNTY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
//    public SQLModel getDataCreateModel() throws SQLModelException {
//        SQLModel sqlModel = new SQLModel();
//        List sqlArgs = new ArrayList();
//        EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
//        String sqlStr = "INSERT INTO "
//                + " ETS_COUNTY("
//                + " COUNTY_CODE,"
//                + " COUNTY_NAME,"
//                + " COMPANY_CODE,"
//                + " ENABLED,"
//                + " COUNTY_CODE_MIS,"
//                + " COUNTY_CODE_COA_CC,"
//                + " CREATION_DATE,"
//                + " CREATED_BY,"
//                + " LAST_UPDATE_DATE,"
//                + " LAST_UPDATE_BY"
//                + ") VALUES ("
//                + " ETS_COUNTY_S.NEXTVAL, ?, ?, 'Y', ?, ?, GETDATE(), ?, GETDATE(), ?)";
//
//        sqlArgs.add(etsCounty.getCountyName());
//        sqlArgs.add(etsCounty.getCompanyCode());
////            sqlArgs.add(etsCounty.getEnabled());
//        sqlArgs.add(etsCounty.getCountyCodeMis());
//        sqlArgs.add(etsCounty.getCountyCodeCoaCc());
////            sqlArgs.add(etsCounty.getCreationDate());
//        sqlArgs.add(sfUser.getUserId());
////            sqlArgs.add(etsCounty.getLastUpdateDate());
//        sqlArgs.add(sfUser.getUserId());
//
//        sqlModel.setSqlStr(sqlStr);
//        sqlModel.setArgs(sqlArgs);
//        return sqlModel;
//    }
 
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " ETS_COUNTY("
                + " COUNTY_CODE,"
                + " COUNTY_NAME,"
                + " COMPANY_CODE,"
                + " ENABLED,"
                + " COUNTY_CODE_MIS,"
                + " COUNTY_CODE_COA_CC,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + ") VALUES ("
                + " ?, ?, ?, 'Y', ?, ?, GETDATE(), ?, GETDATE(), ?)";
        sqlArgs.add(etsCounty.getCountyCodeMis());
        sqlArgs.add(etsCounty.getCountyName());
        sqlArgs.add(etsCounty.getCompanyCode());
//            sqlArgs.add(etsCounty.getEnabled());
        sqlArgs.add(etsCounty.getCountyCodeMis());
        sqlArgs.add(etsCounty.getCountyCodeCoaCc());
//            sqlArgs.add(etsCounty.getCreationDate());
        sqlArgs.add(sfUser.getUserId());
//            sqlArgs.add(etsCounty.getLastUpdateDate());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ر�(EAM) ETS_COUNTY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_COUNTY"
                + " SET"
                + " COUNTY_NAME = ?,"
//                + " COMPANY_CODE = ?,"
//                + " ENABLED = ?,"
                + " COUNTY_CODE_MIS = ?,"
                + " COUNTY_CODE_COA_CC = ?,"
                //                    + " CREATION_DATE = ?,"
                //                    + " CREATED_BY = ?,"
                + " LAST_UPDATE_DATE = GETDATE() ,"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " COUNTY_CODE = ?";

        sqlArgs.add(etsCounty.getCountyName());
//        sqlArgs.add(etsCounty.getCompanyCode());
//        sqlArgs.add(etsCounty.getEnabled());
        sqlArgs.add(etsCounty.getCountyCodeMis());
        sqlArgs.add(etsCounty.getCountyCodeCoaCc());
//            sqlArgs.add(etsCounty.getCreationDate());
//            sqlArgs.add(etsCounty.getCreatedBy());
//            sqlArgs.add(etsCounty.getLastUpdateDate());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsCounty.getCountyCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ر�(EAM) ETS_COUNTY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " ETS_COUNTY"
                + " WHERE"
                + " COUNTY_CODE = ?";
        sqlArgs.add(etsCounty.getCountyCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ر�(EAM) ETS_COUNTY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " COUNTY_CODE,"
                + " COUNTY_NAME,"
                + " COMPANY_CODE,"
                + " ENABLED,"
                + " COUNTY_CODE_MIS,"
                + " COUNTY_CODE_COA_CC,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " ETS_COUNTY"
                + " WHERE"
                + " COUNTY_CODE = ?";
        sqlArgs.add(etsCounty.getCountyCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ر�(EAM) ETS_COUNTY����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " COUNTY_CODE,"
                    + " COUNTY_NAME,"
                    + " COMPANY_CODE,"
                    + " ENABLED,"
                    + " COUNTY_CODE_MIS,"
                    + " COUNTY_CODE_COA_CC,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_BY"
                    + " FROM"
                    + " ETS_COUNTY"
                    + " WHERE"
                    + " ( " + SyBaseSQLUtil.isNull() + "  OR COUNTY_CODE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COUNTY_NAME LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COMPANY_CODE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ENABLED LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COUNTY_CODE_MIS LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COUNTY_CODE_COA_CC LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
            sqlArgs.add(etsCounty.getCountyCode());
            sqlArgs.add(etsCounty.getCountyCode());
            sqlArgs.add(etsCounty.getCountyName());
            sqlArgs.add(etsCounty.getCountyName());
            sqlArgs.add(etsCounty.getCompanyCode());
            sqlArgs.add(etsCounty.getCompanyCode());
            sqlArgs.add(etsCounty.getEnabled());
            sqlArgs.add(etsCounty.getEnabled());
            sqlArgs.add(etsCounty.getCountyCodeMis());
            sqlArgs.add(etsCounty.getCountyCodeMis());
            sqlArgs.add(etsCounty.getCountyCodeCoaCc());
            sqlArgs.add(etsCounty.getCountyCodeCoaCc());
            sqlArgs.add(etsCounty.getCreationDate());
            sqlArgs.add(etsCounty.getCreationDate());
            sqlArgs.add(etsCounty.getCreatedBy());
            sqlArgs.add(etsCounty.getCreatedBy());
            sqlArgs.add(etsCounty.getLastUpdateDate());
            sqlArgs.add(etsCounty.getLastUpdateDate());
            sqlArgs.add(etsCounty.getLastUpdateBy());
            sqlArgs.add(etsCounty.getLastUpdateBy());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ر�(EAM) ETS_COUNTYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsCountyDTO etsCounty = (EtsCountyDTO) dtoParameter;
        
        StringBuffer str = new StringBuffer();
          
        str.append(" SELECT dbo.APP_GET_ORGNIZATION_NAME(EOCM.ORGANIZATION_ID) ORGNIZATION_NAME,\n");
		str.append("    \t\t\t EC.COUNTY_CODE_MIS,\n");
		str.append("    \t\t\t EC.COUNTY_CODE_COA_CC,\n");
		str.append("    \t\t\t EC.COUNTY_NAME,\n");
		str.append("    \t\t\t EC.COUNTY_CODE,\n");
		str.append("    \t\t\t EC.LAST_UPDATE_DATE\n");
		str.append("    \tFROM ETS_COUNTY EC,\n");
		str.append("    \t\t\t ETS_OU_CITY_MAP EOCM\n");
		str.append("     WHERE EC.COMPANY_CODE = EOCM.COMPANY_CODE ");
		str.append("  AND (" + SyBaseSQLUtil.nullIntParam() + " OR EOCM.ORGANIZATION_ID = ?)");
		str.append("  AND (" + SyBaseSQLUtil.nullStringParam() + " OR EC.COUNTY_CODE = ?)");
		str.append("  AND (" + SyBaseSQLUtil.nullStringParam() + " OR EC.COUNTY_NAME = ?)");
		str.append("  AND (" + SyBaseSQLUtil.nullStringParam() + " OR EC.COUNTY_CODE_MIS = ?)");
		str.append("  AND (" + SyBaseSQLUtil.nullStringParam() + " OR EC.COUNTY_CODE_COA_CC LIKE ?)");
                 /* + " AND ( " + SyBaseSQLUtil.isNull() + "  OR EC.LAST_UPDATE_DATE LIKE ?)" */ ;
//        sqlArgs.add( etsCounty.getOrganizationId() );
//        sqlArgs.add( etsCounty.getOrganizationId() );
//        sqlArgs.add(etsCounty.getCountyCode());
//        sqlArgs.add(etsCounty.getCountyCode());
        SyBaseSQLUtil.nullIntParamArgs(sqlArgs, etsCounty.getOrganizationId());
		SyBaseSQLUtil.nullStringParamArgs(sqlArgs, etsCounty.getCountyCode());

		SyBaseSQLUtil.nullStringParamArgs(sqlArgs, etsCounty.getCountyName() );
//		sqlArgs.add(etsCounty.getCountyName());
//		sqlArgs.add(etsCounty.getCountyName());
//		sqlArgs.add(etsCounty.getCountyName());
        
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, etsCounty.getCountyCodeMis() );
//        sqlArgs.add(etsCounty.getCountyCodeMis());
//        sqlArgs.add(etsCounty.getCountyCodeMis());
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, etsCounty.getCountyCodeCoaCc() );
//        sqlArgs.add(etsCounty.getCountyCodeCoaCc());
//        sqlArgs.add(etsCounty.getCountyCodeCoaCc());
//        sqlArgs.add(etsCounty.getCountyCodeCoaCc());
//            sqlArgs.add(etsCounty.getLastUpdateDate());
//            sqlArgs.add(etsCounty.getLastUpdateDate());

        sqlModel.setSqlStr( str.toString() );
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}
    
    private Integer strToInt(String str){
    	if( StrUtil.isEmpty( str ) ){
    		return null;
    	}else{
    		return Integer.parseInt( str );
    	}
    }

}