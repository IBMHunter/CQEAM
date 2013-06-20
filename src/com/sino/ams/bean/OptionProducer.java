package com.sino.ams.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.util.ConvertUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.DatabaseForWeb;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.user.dao.EtsOuCityMapDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.config.SinoConfig;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: �����ƶ�IESϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class OptionProducer {
	protected Connection conn = null;
	protected SfUserDTO userAccount = null;
	
	public OptionProducer(SfUserDTO userAccount, Connection conn) {
		this.userAccount = userAccount;
		this.conn = conn;
	}

	/**
	 * ���ܣ���ȡ���н�ɫ���ɵ������б�
	 * @param resId ָ����Դ���
	 * @return String
	 * @throws QueryException
	 */
	@SuppressWarnings("unchecked")
	public String getAllRoleOption(int resId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " SR.ROLE_ID,"
				+ " SR.ROLE_NAME"
				+ " FROM"
				+ " SF_ROLE SR";
		if (!StrUtil.isEmpty(resId)) {
			sqlStr += " WHERE"
					+ " NOT EXISTS("
					+ " SELECT"
					+ " NULL"
					+ " FROM"
					+ " SF_RES_PRIVS SRP"
					+ " WHERE"
					+ " SR.ROLE_NAME = SRP.ROLE_NAME"
					+ " AND SRP.SYSTEM_ID = ?"
					+ ")";
			sqlArgs.add(resId);
		}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}
	
   public String getViewAllOrganization(int selectedValue, boolean addBlank) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr ="SELECT EOCM.ORGANIZATION_ID, EOCM.COMPANY\n" +
                "  FROM ETS_OU_CITY_MAP EOCM\n" +
                " WHERE NOT EXISTS (SELECT 'A'\n" +
                "          FROM AMS_SYN_RIGHT ASR\n" +
                "         WHERE ASR.ORGANIZATION_ID = EOCM.ORGANIZATION_ID\n" +
                "           AND ASR.USER_ID = ?)";

        sqlArgs.add(selectedValue);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedValue), false);

    }
   
   public String getViewOrganization(int selectedValue, boolean addBlank) throws QueryException {
       SQLModel sqlModel = new SQLModel();
       List sqlArgs = new ArrayList();
       String sqlStr ="SELECT ASR.ORGANIZATION_ID, EOCM.COMPANY\n" +
               "  FROM ETS_OU_CITY_MAP EOCM, AMS_SYN_RIGHT ASR\n" +
               " WHERE EOCM.ORGANIZATION_ID = ASR.ORGANIZATION_ID\n" +
               "   AND ASR.USER_ID = ?";
       
       sqlArgs.add(selectedValue);
       sqlModel.setSqlStr(sqlStr);
       sqlModel.setArgs(sqlArgs);
       DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
       return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedValue), false);

   }

	 /**
     * ���ܣ����ɱ�������������
     * @param selectedValue String
	 * @return String
	 * @throws QueryException
     */
    @SuppressWarnings("unchecked")
	public String getReportOption(String selectedValue) throws QueryException{
        SQLModel sqlModel=new SQLModel();
        List sqlArgs=new ArrayList();
        String sqlStr="select srd.RPT_ID, srd.RPT_NAME from SINO_RPT_DEFINE srd";
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
    }
    
    /**
     * ���ܣ�����ģ��汾������
     * @param selectedValue String
     * @return String
     * @throws QueryException
     */
    public String getNetVersionOption(String selectedValue, boolean addBlank) throws QueryException{
    	SQLModel sqlModel=new SQLModel();
    	List sqlArgs=new ArrayList();
    	String sqlStr="SELECT DISTINCT FILE_VERSION,FILE_VERSION FROM AMS_FILE_VERSION AFV ORDER BY AFV.FILE_VERSION";
    	sqlModel.setArgs(sqlArgs);
    	sqlModel.setSqlStr(sqlStr);
    	DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
    	return optProducer.getOptionHtml(selectedValue, addBlank);
    }
    
    /**
     * ���ܣ����ɲ�Ʒ����������
     * @param selectedValue String
     * @return String
     * @throws QueryException
     */
    public String getProductNameOption(String selectedValue, boolean addBlank) throws QueryException{
    	SQLModel sqlModel=new SQLModel();
    	List sqlArgs=new ArrayList();
    	String sqlStr="SELECT  DISTINCT T1.PR_CODE ,T2.VALUE ,T2.FLEX_VALUE_ID  " +
    			"FROM  " +
    			"AMS_OUT_PR_OPEX_1 T1, " +
    			"ETS_FLEX_ANALYSE_VALUES T2 " +
    			"WHERE " +
    			"T1.BUZI_CODE = 'PR-B1-0178' " +
    			"AND T2.FLEX_VALUE_SET_ID " +
    			"IN " +
    			"(SELECT T.FLEX_VALUE_SET_ID " +
    			"FROM " +
    			"ETS_FLEX_ANALYSE_VALUE_SET T " +
    			"WHERE " +
    			"T.CODE = 'PRODUCT')" +
    			"AND T1.PR_CODE = T2.CODE " +
    			" ORDER BY T2.FLEX_VALUE_ID";
    	sqlModel.setArgs(sqlArgs);
    	sqlModel.setSqlStr(sqlStr);
    	DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
    	return optProducer.getOptionHtml(selectedValue, addBlank);
    }
    
    /**
     * ���ܣ����ɲ�ƷԪ������������
     * @param selectedValue String
     * @return String
     * @throws QueryException
     */
    public String getProductElementNameOption(String selectedValue, boolean addBlank) throws QueryException{
    	SQLModel sqlModel=new SQLModel();
    	List sqlArgs=new ArrayList();
    	String sqlStr="SELECT DISTINCT T1.PE_CODE,T2.VALUE ,T2.FLEX_VALUE_ID " +
    			"FROM " +
    			"AMS_OUT_PE_OPEX_1 T1, " +
    			"ETS_FLEX_ANALYSE_VALUES T2 " +
    			"WHERE " +
    			"T1.BUZI_CODE IN( SELECT T3.CODE " +
    			"FROM " +
    			"ETS_FLEX_ANALYSE_VALUES T3 " +
    			"WHERE " +
    			"T3.ATTRIBUTE2 = '������λ�ɱ�' " +
    			"AND T3.FILE_VERSION = 1 " +
    			"AND T3.FLEX_VALUE_SET_ID IN" +
    			"(SELECT T.FLEX_VALUE_SET_ID " +
    			"FROM " +
    			"ETS_FLEX_ANALYSE_VALUE_SET T " +
    			"WHERE " +
    			"T.CODE = 'AMS_OUT_PE_OPEX_1'))" +
    			"AND T2.FLEX_VALUE_SET_ID IN" +
    			"(SELECT T.FLEX_VALUE_SET_ID " +
    			"FROM " +
    			"ETS_FLEX_ANALYSE_VALUE_SET T " +
    			"WHERE " +
    			"T.CODE = 'PE') " +
    			"AND T1.PE_CODE = T2.CODE  " +
    			"ORDER BY T2.FLEX_VALUE_ID";
    	sqlModel.setArgs(sqlArgs);
    	sqlModel.setSqlStr(sqlStr);
    	DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
    	return optProducer.getOptionHtml(selectedValue, addBlank);
    }
    
    /**
     * ���ܣ�����ģ��汾������
     * @param selectedValue String
     * @return String
     * @throws QueryException
     */
    public String getNetNameOption(String selectedValue, boolean addBlank) throws QueryException{
    	SQLModel sqlModel=new SQLModel();
    	List sqlArgs=new ArrayList();
    	String sqlStr="SELECT DISTINCT  T1.LNE_CODE ,T2.VALUE " +
    			"FROM " +
    			"AMS_LNE_COST_DATA T1, " +
    			"ETS_FLEX_ANALYSE_VALUES T2 " +
    			"WHERE " +
    			"T1.cost_category = 'LC-V0001'"+ 
    			"AND T2.FLEX_VALUE_SET_ID IN " +
    			"(SELECT T.FLEX_VALUE_SET_ID \n" +
    			"FROM \n" +
    			"ETS_FLEX_ANALYSE_VALUE_SET T " +
    			"WHERE " +
    			"T.CODE = 'LNE') " +
    			"AND T1.LNE_CODE = T2.CODE";
    	sqlModel.setArgs(sqlArgs);
    	sqlModel.setSqlStr(sqlStr);
    	DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
    	return optProducer.getOptionHtml(selectedValue, addBlank);
    }

	/**
	 * ���ܣ���ȡ�ܷ���ָ����Դ�Ľ�ɫ���ɵ������б�
	 * @param resourceId String
	 * @return String
	 * @throws QueryException
	 */
	public String getViewRoleOption(int resourceId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " SR.ROLE_ID,"
				+ " SR.ROLE_NAME"
				+ " FROM"
				+ " SF_ROLE SR,"
				+ " SF_RES_PRIVS SRP,"
				+ " SF_RES_DEFINE SRD"
				+ " WHERE"
				+ " SR.ROLE_NAME = SRP.ROLE_NAME"
				+ " AND SRP.SYSTEM_ID = SRD.SYSTEM_ID"
				+ " AND SRD.RES_ID = ?";
		sqlModel.setSqlStr(sqlStr);
		sqlArgs.add(resourceId);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ���ȡ����OU��֯�����б��
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getAllOrganization(int selectedValue) throws QueryException {
		return getAllOrganization(selectedValue, false);
	}

	/**
	 * ��ȡ����OU��֯�����б��
	 * @param selectedValue String
	 * @param addBlank      �Ƿ���롰��ѡ��";
	 * @return String
	 * @throws QueryException
	 */
	public String getAllOrganization(int selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ORGANIZATION_ID,"
				+ " COMPANY,"
				+ "	COMPANY_CODE"
				+ " FROM"
				+ " ETS_OU_CITY_MAP";

        if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
            sqlStr += " WHERE ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), false);
        } else if (userAccount.isDptAssetsManager() && userAccount.isProvinceUser() && !userAccount.isProvAssetsManager()) {
            sqlStr += " WHERE ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), false);
        } else {
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), addBlank);
        }
	}
	/**
	 * ��ȡ����OU��֯�����б��,�ǡ�ȫʡ�ʲ�����Ա���Ŀ���ѡȫ����OU���ǡ�ȫʡ�ʲ�����Ա����ֻ����ѡ�Լ���OU 
	 * @param selectedValue String
	 * @param addBlank      �Ƿ���롰��ѡ��";
	 * @return String
	 * @throws QueryException
	 */
	public String getAllOrganizationByPAM(int selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ORGANIZATION_ID,"
				+ " COMPANY,"
				+ "	COMPANY_CODE"
				+ " FROM"
				+ " ETS_OU_CITY_MAP";

		if (userAccount.isProvAssetsManager()) {
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml( ConvertUtil.int2String( selectedValue ) , addBlank);
		}else{
            sqlStr += " WHERE ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml( ConvertUtil.int2String( selectedValue ) , false);
        } 
	}
    public String getNotTdOrganization(int selectedValue, boolean addBlank) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " ORGANIZATION_ID,"
                + " COMPANY,"
                + "	COMPANY_CODE"
                + " FROM"
                + " ETS_OU_CITY_MAP"
                + " WHERE dbo.CHARINDEX_4('TD', COMPANY, 1, 1) < 0";

        if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
            sqlStr += " AND ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), false);
        } else if (userAccount.isDptAssetsManager() && userAccount.isProvinceUser() && !userAccount.isProvAssetsManager()) {
            sqlStr += " AND ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), false);
        } else {
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), addBlank);
        }
    }

    public String getIsTdOrganization(int selectedValue, boolean addBlank) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " ORGANIZATION_ID,"
                + " COMPANY,"
                + "	COMPANY_CODE"
                + " FROM"
                + " ETS_OU_CITY_MAP"
                + " WHERE dbo.CHARINDEX_4('TD', COMPANY, 1, 1) > 0";

        if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
            sqlStr += " AND ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), false);
        } else if (userAccount.isDptAssetsManager() && userAccount.isProvinceUser() && !userAccount.isProvAssetsManager()) {
            sqlStr += " AND ORGANIZATION_ID = ?";
            sqlArgs.add(userAccount.getOrganizationId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), false);
        } else {
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
            return webFieldProducer.getOptionHtml(ConvertUtil.int2String( selectedValue ), addBlank);
        }
    }
    
    public String getOrganization(int selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " ORGANIZATION_ID,"
						+ " COMPANY,"
                        + " COMPANY_CODE"
                        + " FROM"
						+ " ETS_OU_CITY_MAP " ;
						if (!userAccount.isProvinceUser()) {
							sqlStr += " WHERE CONVERT(VARCHAR ,ORGANIZATION_ID) = CONVERT(VARCHAR ,?)";
							sqlArgs.add(selectedValue);
						}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedValue), false);
	}

    public String getTdOrganization(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " ORGANIZATION_ID,"
						+ " COMPANY,"
                        + " COMPANY_CODE"
                        + " FROM"
						+ " ETS_OU_CITY_MAP "
						+ " WHERE IS_TD = 'Y' ";
        if(!userAccount.isSysAdmin()){
            sqlStr += "  AND ORGANIZATION_ID = ?";
            sqlArgs.add(selectedValue);
        }
        sqlStr += " ORDER BY ORGANIZATION_ID";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, false);
	}

    /**
     * Function:                ��ȡTD���TD��˾��OU
     * @param selectedValue     ѡ�е�OU
     * @param isTd              �Ƿ�TD��'Y'  OR 'N'��
     * @return                  String����
     * @throws QueryException
     */
    public String getIsOrNotTdOrganization(int selectedValue, String isTd, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " ORGANIZATION_ID,"
						+ " COMPANY,"
                        + " COMPANY_CODE"
                        + " FROM"
						+ " ETS_OU_CITY_MAP "
						+ " WHERE IS_TD = dbo.NVL(?, IS_TD) ";
        sqlArgs.add(isTd);
        if(!userAccount.isSysAdmin()){
            sqlStr += "  AND ORGANIZATION_ID = ?";
            sqlArgs.add(selectedValue);
        }
        sqlStr += " ORDER BY ORGANIZATION_ID";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedValue), addBlank);
	}

    public String getAllCompanyCode(String selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " COMPANY_CODE,"
				+ " COMPANY"
				+ " FROM"
				+ " ETS_OU_CITY_MAP";

		if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
			sqlStr += " WHERE COMPANY_CODE = ?";
			sqlArgs.add(userAccount.getCompanyCode());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(selectedValue, false);
		} else {
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(selectedValue, addBlank);
		}
	}

    /**
	 * ѡȡָ����˾��Ϣ
	 * @param selectedValue ѡ�е�ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getCompanyOpt(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " COMPANY_CODE,"
						+ " COMPANY"
						+ " FROM"
						+ " ETS_OU_CITY_MAP "
						+ " WHERE COMPANY_CODE = ?";
		sqlArgs.add(selectedValue);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, false);
	}

    /**
	 * ��ȡ����OU��֯�����б��,�޽�ɫ����
	 * @param selectedValue String
	 * @param addBlank      �Ƿ���롰��ѡ��";
	 * @return String
	 * @throws QueryException
	 */
	public String getAllOU(int selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ORGANIZATION_ID,"
				+ " COMPANY"
				+ " FROM"
				+ " ETS_OU_CITY_MAP";
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedValue), addBlank);
	}

	/**
	 * ѡȡָ��OU������
	 * @param selectedValue ѡ�е�ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getOrganizationOpt(int selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EOCM.ORGANIZATION_ID," +
							" EOCM.COMPANY,\n" +
							" EOCM.COMPANY_CODE\n"		+
							"  FROM ETS_OU_CITY_MAP EOCM\n" +
							" WHERE EOCM.ENABLED = 'Y'\n" +
							"   AND EOCM.ORGANIZATION_ID = ?";

		sqlArgs.add(selectedValue);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);

		return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedValue), false);
	}

	/**
	 * ���ܣ���ȡ�����ʲ��˲�������
	 * @param selectedValue String
	 * @param addBlank      boolean
	 * @return String
	 * @throws QueryException
	 */
	public String getAllBookTypeName(String selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EOCM.BOOK_TYPE_CODE,\n" +
				"       EOCM.BOOK_TYPE_CODE || '--' || EOCM.BOOK_TYPE_NAME BOOK_TYPE_NAME\n" +
				"  FROM ETS_OU_CITY_MAP EOCM";

		if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
			sqlStr += " WHERE EOCM.ORGANIZATION_ID = ?";
			sqlArgs.add(userAccount.getOrganizationId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(selectedValue, false);
		} else {
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(selectedValue, addBlank);
		}
	}


	public String getUseField(String selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT AFCV.FA_CAT_CODE_1,\n" +
				"       AFCV.FA_CAT_NAME_1\n" +
				"FROM   AMS_FA_CATEGORY_V AFCV\n" +
				"GROUP  BY AFCV.FA_CAT_CODE_1,\n" +
				"          AFCV.FA_CAT_NAME_1";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, addBlank);

	}

	/**
	 * ���ܣ���ȡ����OU��֯�����б��
	 * ����������֯����
	 * @param itemCode String
	 * @return String
	 * @throws QueryException
	 */
	public String getItemLeftOrgs(String itemCode) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "";
		if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
			sqlStr = "SELECT" +
					" EOCM.ORGANIZATION_ID, " +
					" EOCM.COMPANY\n" +
					" FROM " +
					" ETS_OU_CITY_MAP EOCM\n" +
					" WHERE EOCM.ORGANIZATION_ID = " + userAccount.getOrganizationId();
		} else {
			sqlStr = "SELECT"
					+ " ORGANIZATION_ID,"
					+ " COMPANY"
					+ " FROM"
					+ " ETS_OU_CITY_MAP EOCM"
					+ " WHERE NOT EXISTS ("
					+ " SELECT"
					+ " NULL"
					+ " FROM"
					+ " ETS_SYSITEM_DISTRIBUTE ESD"
					+ " WHERE"
					+ " EOCM.ORGANIZATION_ID = ESD.ORGANIZATION_ID"
					+ " AND ESD.ITEM_CODE = ?)";
			sqlArgs.add(itemCode);
		}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ���ȡ����OU��֯�����б��
	 * ����������֯����
	 * @param itemCode String
	 * @return String
	 * @throws QueryException
	 */
	public String getItemExistOrgs(String itemCode) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ORGANIZATION_ID,"
				+ " COMPANY"
				+ " FROM"
				+ " ETS_OU_CITY_MAP EOCM"
				+ " WHERE EXISTS ("
				+ " SELECT"
				+ " NULL"
				+ " FROM"
				+ " ETS_SYSITEM_DISTRIBUTE ESD"
				+ " WHERE"
				+ " EOCM.ORGANIZATION_ID = ESD.ORGANIZATION_ID"
				+ " AND ESD.ITEM_CODE = ?)";
		sqlArgs.add(itemCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ���ȡ�������������
	 * @param selectedGroup String
	 * @return String
	 * @throws QueryException
	 */
	public String getAllGroup(String selectedGroup) throws QueryException {
		return getAllGroup(selectedGroup, 0, false, true);
	}

	/**
	 * ���ܣ���ȡָ�������ѡ�е����������
	 * @param selectedGroup  String
	 * @param organizationId String
	 * @param onlySelf       boolean
	 * @param addBlank       boolean
	 * @return String
	 * @throws QueryException
	 */
	public String getAllGroup(String selectedGroup, int organizationId, boolean onlySelf, boolean addBlank) throws
			QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SG.GROUP_ID,\n" +
                "       SG.GROUP_NAME\n" +
                "FROM   SF_GROUP SG\n" +
                "WHERE  EXISTS (SELECT NULL\n" +
                "        FROM   SINO_GROUP_MATCH SGM,\n" +
                "               SINO_MIS_DEPT    SMD\n" +
                "        WHERE  SG.GROUP_ID = SGM.GROUP_ID\n" +
                "               AND SGM.DEPT_ID = SMD.DEPT_ID)";
		if (onlySelf) {
			sqlStr += " AND SG.GROUP_ID=?";
			sqlArgs.add(StrUtil.strToInt(selectedGroup));
		} else {
			if (StrUtil.nullToString(organizationId).equals("")) {
				if (!userAccount.isSysAdmin()) {
					sqlStr += " AND SG.ORGANIZATION_ID=?";
					sqlArgs.add(userAccount.getOrganizationId());
				}
			} else {
				sqlStr += " AND SG.ORGANIZATION_ID=?";
				sqlArgs.add(userAccount.getOrganizationId());
			}
		}
        sqlStr += " ORDER BY GROUP_NAME";
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedGroup, addBlank);
	}

	/**
	 * ���ܣ���ȡָ�������ѡ�е����������
	 * @param selectedGroup  String
	 * @param organizationId String
	 * @param onlySelf       boolean
	 * @param addBlank       boolean
	 * @return String
	 * @throws QueryException
	 */
	public String getAllGroup2(int selectedGroup, int organizationId, boolean onlySelf, boolean addBlank) throws
			QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT" +
				" GROUP_ID," +
				" GROUP_NAME \n" +
				" FROM SF_GROUP \n" +
				"WHERE ENABLED='Y' AND (MOD(IS_DESIGNER, 2) = 0 OR IS_DESIGNE IS NULL OR IS_DESIGNE='')";
		if (onlySelf) {
			sqlStr += " AND GROUP_ID=?";
			sqlArgs.add(selectedGroup);
		} else {
			if (StrUtil.nullToString(organizationId).equals("")) {
				if (!userAccount.isSysAdmin()) {
					sqlStr += " AND ORGANIZATION_ID=?";
					sqlArgs.add(userAccount.getOrganizationId());
				}
			} else {
				sqlStr += " AND ORGANIZATION_ID=?";
				sqlArgs.add(organizationId);
			}
		}
        sqlStr += " ORDER BY GROUP_NAME";
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml( ConvertUtil.int2String( selectedGroup ), addBlank);
	}

	/**
	 * ���ܣ���ȡָ�������ѡ�е����������
	 * @param selectedGroup  String
	 * @param organizationId String
	 * @param onlySelf       boolean
	 * @param addBlank       boolean
	 * @return String
	 * @throws QueryException
	 */
	public String getAllGroup3(String selectedGroup, int organizationId, boolean onlySelf, boolean addBlank) throws
			QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT" +
				" GROUP_ID," +
				" GROUP_NAME \n" +
				" FROM SF_GROUP \n" +
				"WHERE ENABLED='Y' AND (MOD(IS_DESIGNER, 2) = 0 OR IS_DESIGNE IS NULL OR IS_DESIGNE='')";
		if (onlySelf) {
			sqlStr += " AND GROUP_ID=?";
			sqlArgs.add(selectedGroup);
		} else {
			if (StrUtil.nullToString(organizationId).equals("")) {
				if (!userAccount.isSysAdmin()) {
					sqlStr += " AND ORGANIZATION_ID=?";
					sqlArgs.add(userAccount.getOrganizationId());
				}
			} else {
				sqlStr += " AND ORGANIZATION_ID=?";
				sqlArgs.add(organizationId);
			}
		}
        sqlStr += " ORDER BY GROUP_NAME";
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml( selectedGroup , addBlank);
	}
	
	/**
	 * ���ܣ������Ƿ�������
	 * @param selectedValue String
	 * @return String
	 */
	public String getBooleanOption(String selectedValue) {
		StringBuffer strOpt = new StringBuffer();
		if (selectedValue == null) {
			selectedValue = "";
		}
		strOpt.append("<option value=\"\">--��ѡ��--</option>");
		strOpt.append("<option value=\"");
		strOpt.append(WebAttrConstant.TRUE_VALUE);
		strOpt.append("\"");
		if (selectedValue.equals(WebAttrConstant.TRUE_VALUE)) {
			strOpt.append(" selected");
		}
		strOpt.append(">��</option>");
		strOpt.append("<option value=\"");
		strOpt.append(WebAttrConstant.FALSE_VALUE);
		strOpt.append("\"");
		if (selectedValue.equals(WebAttrConstant.FALSE_VALUE)) {
			strOpt.append(" selected");
		}
		strOpt.append(">��</option>");
		return strOpt.toString();
	}

	/**
	 * ����:��ȡ���������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDictParentOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
				+ " EFVS.FLEX_VALUE_SET_ID,"
				+ " EFVS.CODE||'('||EFVS.NAME||')'"
				+ " FROM"
				+ " ETS_FLEX_VALUE_SET EFVS";
		sqlModel.setSqlStr(sqlStr);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ����:��ȡ�ʲ��������������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getAnalyseDictParentOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
				+ " EFAVS.FLEX_VALUE_SET_ID,"
				+ " EFAVS.CODE||'('||EFAVS.NAME||')'"
				+ " FROM"
				+ " ETS_FLEX_ANALYSE_VALUE_SET EFAVS";
		sqlModel.setSqlStr(sqlStr);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ������ֵ�dictCode�������б�
	 * @param dictCode      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDictOption(String dictCode, String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EFV.CODE,\n" +
                "       EFV.VALUE\n" +
                "  FROM ETS_FLEX_VALUES    EFV,\n" +
                "       ETS_FLEX_VALUE_SET EFVS\n" +
                " WHERE EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                "       AND EFV.ENABLED = 'Y'\n" +
                "       AND EFVS.CODE = ?";
        sqlArgs.add(dictCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

    /**
	 * ���ܣ������ֶ�¼��KPIָ�����Ƶ������б�
	 * @param selectedValue      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getKpiReportTypeOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT AKSD.KPI_CODE CODE, AKSD.KPI_NAME VALUE\n" +
                        "  FROM AMS_KPI_STAT_DEFINE AKSD\n" +
                        " WHERE AKSD.IS_ENABLE = 'Y'\n" +
                        "   AND AKSD.KPI_TYPE = '1'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ������ֵ�itemUnit�������б�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getItemUnitOption( String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EFV.VALUE,"
				+ " EFV.VALUE"
				+ " FROM"
				+ " ETS_FLEX_VALUES    EFV,"
				+ " ETS_FLEX_VALUE_SET EFVS"
				+ " WHERE"
				+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
				+ " AND EFV.ENABLED = 'Y'"
				+ " AND EFVS.CODE = ?";
		sqlArgs.add(DictConstant.UNIT_OF_MEASURE);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	public String getCatgOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EFV.CODE, EFV.VALUE\n" +
				"  FROM ETS_FLEX_VALUES EFV\n" +
				" WHERE EFV.FLEX_VALUE_SET_ID = 1\n" +
				"   AND EFV.CODE != 'HOUSE'\n" +
				"   AND EFV.CODE != 'LAND'\n" +
				"   AND EFV.CODE != 'INSTRUMENT'\n" +
				"   AND EFV.CODE != 'INTANGIBLE'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ������ֵ�dictCode�������б�
	 * @param dictCode      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException �˷������ص���������û�С�---ѡ��---����
	 */
	public String getDictOption2(String dictCode, String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EFV.CODE,"
				+ " EFV.VALUE"
				+ " FROM"
				+ " ETS_FLEX_VALUES    EFV,"
				+ " ETS_FLEX_VALUE_SET EFVS"
				+ " WHERE"
				+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
				+ " AND EFV.ENABLED = 'Y'"
				+ " AND EFVS.CODE = ?";
		sqlArgs.add(dictCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, false);
	}

	/**
	 * ���ܣ������ֵ�dictCode�������б�,��ͬ����ȡ����.
	 * @param dictCode      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @param add           boolean
	 * @return String
	 * @throws QueryException
	 */
	public String getDictOption3(String dictCode, String selectedValue, boolean add) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EFV.CODE,"
				+ " EFV.DESCRIPTION"
				+ " FROM"
				+ " ETS_FLEX_VALUES    EFV,"
				+ " ETS_FLEX_VALUE_SET EFVS"
				+ " WHERE"
				+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
				+ " AND EFV.ENABLED = 'Y'"
				+ " AND EFVS.CODE = ?";
		sqlArgs.add(dictCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, add);
	}

	/**
	 * ���ܣ�����רҵ����ѡ��
	 * @param objectCategory String
	 * @param organizationId String
	 * @return String
	 * @throws QueryException
	 */
	public String getLeftCategoryOption(String objectCategory, int organizationId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT " +
				" EFV.CODE, EFV.VALUE\n" +
				" FROM " +
				" ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
				" WHERE" +
				" EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
				" AND EFVS.CODE = ?\n" +
				" AND NOT EXISTS (SELECT 1\n" +
				" FROM ETS_OBJECT_CATEGORY EOC\n" +
				" WHERE " +
				" CONVERT( VARCHAR , EOC.SEARCH_CATEGORY ) = EFV.CODE\n" +
				" AND EOC.OBJECT_CATEGORY = ?\n" +
				" AND EOC.ORGANIZATION_ID = ?)";
		sqlArgs.add(DictConstant.OBJECT_CATEGORY);
		sqlArgs.add(objectCategory);
		sqlArgs.add(organizationId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ�����רҵ����ѡ��
	 * @param objectCategory String
	 * @param organizationId String
	 * @return String
	 * @throws QueryException
	 */
	public String getRightCategoryOption(String objectCategory, int organizationId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT " +
				" EFV.CODE, EFV.VALUE\n" +
				" FROM " +
				" ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
				" WHERE" +
				" EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
				" AND EFVS.CODE = ?\n" +
				" AND EXISTS (SELECT 1\n" +
				" FROM ETS_OBJECT_CATEGORY EOC\n" +
				" WHERE " +
				" CONVERT( VARCHAR , EOC.SEARCH_CATEGORY ) = EFV.CODE\n" +
				" AND EOC.OBJECT_CATEGORY = ?\n" +
				" AND EOC.ORGANIZATION_ID = ?)";
		sqlArgs.add(DictConstant.OBJECT_CATEGORY);
		sqlArgs.add(objectCategory);
		sqlArgs.add(organizationId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ����:��ȡ���������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getCountyOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EC.COUNTY_CODE_MIS COUNTY_CODE,"
				+ " EC.COUNTY_NAME"
				+ " FROM"
				+ " ETS_COUNTY EC"
				+ " WHERE"
				+ " EC.COMPANY_CODE = ?";
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue , true);
	}
	
	/**
	 * ����:��ȡ�ɱ����������б��ֵ
	 * @param orgId OU��֯ID
	 * @param selectedValue ѡ����ֵ
	 * @return String �ɱ����������б��
	 * @throws QueryException  ��ѯ�쳣
	 */
	public String getObjectCostCenterOption(int orgId,String selectedValue)
			throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
			"SELECT DISTINCT SUBSTRING(EO.WORKORDER_OBJECT_NAME,\n" +
			"                          1,\n" +
			"                          CASE CHARINDEX('.', EO.WORKORDER_OBJECT_NAME)\n" +
			"                            WHEN 0 THEN\n" +
			"                             LEN(EO.WORKORDER_OBJECT_NAME)\n" +
			"                            ELSE\n" +
			"                             CHARINDEX('.', EO.WORKORDER_OBJECT_NAME) - 1\n" +
			"                          END) COST_CENTER_CODE,SUBSTRING(EO.WORKORDER_OBJECT_NAME,1,CASE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME) WHEN 0 THEN LEN(EO.WORKORDER_OBJECT_NAME) ELSE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME)-1 END) COST_CENTER_NAME\n" +
			"  FROM ETS_OBJECT EO " +
			"  WHERE EO.ORGANIZATION_ID = ? AND SUBSTRING(EO.WORKORDER_OBJECT_NAME,1,CASE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME) WHEN 0 THEN LEN(EO.WORKORDER_OBJECT_NAME) ELSE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME)-1 END) <> ''" +
			"  AND (? = '' OR SUBSTRING(EO.WORKORDER_OBJECT_NAME,1,CASE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME) WHEN 0 THEN LEN(EO.WORKORDER_OBJECT_NAME) ELSE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME)-1 END) = dbo.NVL(?,SUBSTRING(EO.WORKORDER_OBJECT_NAME,1,CASE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME) WHEN 0 THEN LEN(EO.WORKORDER_OBJECT_NAME) ELSE CHARINDEX('.',EO.WORKORDER_OBJECT_NAME)-1 END)))";
		sqlArgs.add(orgId);
		sqlArgs.add(selectedValue);
		sqlArgs.add(selectedValue);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 *
	 */
	public String getCityOption(String selectedValue)throws QueryException{
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AC.COUNTY_CODE,"
				+ " AC.COUNTY_NAME"
				+ " FROM"
				+ " AMS_COUNTY AC"
				+ " WHERE"
				+ " AC.PARENT_CODE IS NULL";

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(selectedValue, true);
//		}
	}
	/**
	 * ����:��ȡָ��OU�����������б��
	 * @param organizationId ou��֯ID
	 * @return String
	 * @throws QueryException
	 */
	public String getOuCountyOption(int organizationId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EC.COUNTY_CODE,"
				+ " EC.COUNTY_NAME"
				+ " FROM"
				+ " ETS_COUNTY      EC,"
				+ " ETS_OU_CITY_MAP EOCM"
				+ " WHERE"
				+ " EC.COMPANY_CODE = EOCM.COMPANY_CODE"
				+ " AND EC.ENABLED = 'Y'"
				+ " AND EOCM.ORGANIZATION_ID = ?";
		sqlArgs.add(organizationId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", true);
	}
	
	public String getCity2Option(String selectedValue)throws QueryException{
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AC.COUNTY_NAME,"
				+ " AC.COUNTY_NAME"
				+ " FROM"
				+ " AMS_COUNTY AC"
				+ " WHERE"
				+ " AC.PARENT_CODE IS NULL";

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(selectedValue, true);
	}
	/**
     * ��ȡָ��OU�����������б��
     * @return
     * @throws QueryException
     */
	public String getOuCounty2Options(String parentName,String countyCode) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
			+ " AC.COUNTY_NAME,"
			+ " AC.COUNTY_NAME"
			+ " FROM"
			+ " AMS_COUNTY      AC "
			+ " WHERE"
			+ " AC.PARENT_CODE = (SELECT COUNTY_CODE FROM AMS_COUNTY WHERE COUNTY_NAME=? AND COMPANY_CODE=?)";
        sqlArgs.add(parentName);
        sqlArgs.add(userAccount.getCompanyCode());
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(countyCode, true);
	}
	

    /**
     * ��ȡָ��OU�����������б��
     * @param parentCode
     * @return
     * @throws QueryException
     */
	public String getOuCountyOptions(String parentCode,String countyCode) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
			+ " AC.COUNTY_CODE,"
			+ " AC.COUNTY_NAME"
			+ " FROM"
			+ " AMS_COUNTY      AC "
			+ " WHERE"
			+ " AC.PARENT_CODE = ?";
//		if("".equals(parentCode)) {
//			sqlArgs.add(null);
//		} else {
//		}
        sqlArgs.add(parentCode);
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(countyCode, true);
	}
	/**
	 * ����ָ��ou�µ������б�
	 * @param selectedValue ѡ����ֵ
	 * @param ou ouId
	 * @return String
	 * @throws QueryException
	 */
	public String getCountyOptionGIS(String selectedValue,String ou) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EC.COUNTY_CODE, EC.COUNTY_NAME\n" +
				"  FROM ETS_COUNTY EC, ETS_OU_CITY_MAP EOCM\n" +
				" WHERE EC.COMPANY_CODE = EOCM.COMPANY_CODE\n" +
				"   AND EOCM.ORGANIZATION_ID = ?";

		sqlArgs.add(ou);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}


	/**
	 * ����:��ȡMIS���������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getCountyOptionMIS(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " SUBSTRING(T.ASSETS_LOCATION_CODE,1, CHARINDEX(T.ASSETS_LOCATION_CODE, '.') - 1) COUNTY_CODE_MIS,"
				+ " SUBSTRING(T.ASSETS_LOCATION, 1, CHARINDEX(T.ASSETS_LOCATION, '.') - 1) COUNTY_NAME";
				if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
					sqlStr=sqlStr+ " FROM ETS_FA_ASSETS_LOCATION_TD T";
				} else {
					sqlStr=sqlStr+ " FROM ETS_FA_ASSETS_LOCATION T";
				}
				sqlStr=sqlStr+ " WHERE"
				+ " T.ORG_ID = ?"
				+ " GROUP BY"
				+ " SUBSTRING(T.ASSETS_LOCATION, 1, CHARINDEX(T.ASSETS_LOCATION, '.') - 1),"
				+ " SUBSTRING(T.ASSETS_LOCATION_CODE,1, "
				+ " CHARINDEX(T.ASSETS_LOCATION_CODE, '.') - 1)";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ����:��ȡ�ض���֯�µ����������б��
	 * @param companyCode   String
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getGivenCountyOption(int companyCode, String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
				"     SELECT EC.COUNTY_CODE VALUE,EC.COUNTY_NAME NAME FROM ETS_COUNTY EC ,ETS_OU_CITY_MAP    EM\n" +
						"   WHERE EC.COMPANY_CODE = EM.COMPANY_CODE\n" +
						"   AND  EM.ORGANIZATION_ID = ?";
		sqlArgs.add(companyCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ����:��ȡ��ǰ�û�������֯�µĴ�ά��˾�����б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getMainCorpOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AMC.COMPANY_ID,"
				+ " AMC.NAME  "
				+ " FROM"
				+ " AMS_MAINTAIN_COMPANY AMC"
				+ " WHERE"
				+ " AMC.ORGANIZATION_ID = ? ";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ����:��ȡ������Ŀ�����б�
	 * @param selectedProject ѡ����Ŀ
	 * @return String
	 * @throws QueryException
	 */
	public String getProjectOption(String selectedProject) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT PROJECT_ID, NAME" +
				"  FROM ETS_PA_PROJECTS_ALL EPPA" +
				" WHERE EPPA.ENABLED_FLAG = 'Y'";

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedProject, true);
	}

	/**
	 * ѡ��ָ��������û�
	 * @param groupId      String
	 * @param selectedUser �Ƿ�ѡ��ָ���û�
	 * @return String
	 * @throws QueryException
	 */
	public String getUsersOfGroup(int groupId, String selectedUser) throws QueryException {
		return getUsersOfGroup(groupId, selectedUser, true);
	}

	public String getUsersOfGroup(int groupId, String selectedUser, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT DISTINCT SU.USER_ID, SU.USERNAME\n" +
				"  FROM SF_USER_RIGHT SUR, SF_USER SU\n" +
				" WHERE SU.USER_ID = SUR.USER_ID\n" +
				" AND SU.ENABLED = 'Y'\n"+
				"   AND SUR.GROUP_ID = ?\n" +
				"   AND (SU.DISABLE_DATE = '' OR SU.DISABLE_DATE IS NULL OR SU.DISABLE_DATE > GETDATE())\n" +
				" ORDER BY USERNAME";

		sqlArgs.add(groupId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedUser, addBlank);
	}

	/**
	 * ȡ����¹鵵��
	 * @param groupId      String
	 * @param selectedUser String
	 * @param addBlank     boolean
	 * @return String
	 * @throws QueryException
	 */
	public String getArcUsersOfGroup(String groupId, String selectedUser, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT DISTINCT SU.USER_ID, SU.USERNAME\n" +
				"  FROM SF_USER SU, SF_USER_RIGHT SUR, SF_ROLE SR\n" +
				" WHERE SUR.USER_ID = SU.USER_ID\n" +
				"   AND SU.ENABLED = 'Y'\n"+
				"   AND SUR.ROLE_ID = SR.ROLE_ID\n" +
				"   AND (SU.DISABLE_DATE = '' OR SU.DISABLE_DATE IS NULL OR SU.DISABLE_DATE > GETDATE())\n" +
				"   AND SUR.GROUP_ID = ?\n" +
				"   AND SR.ROLE_NAME = ?\n" +
				" ORDER BY SU.USERNAME";


		sqlArgs.add(StrUtil.strToInt(groupId));
		sqlArgs.add("�����鵵��");

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedUser, addBlank);
	}


	/**
	 * ���ܣ�����ֿ�������
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getInvOption(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AIV.INV_CODE,"
				+ " AIV.INV_NAME"
				+ " FROM"
				+ " AMS_INV_V AIV"
				+ " WHERE"
				+ " AIV.ORGANIZATION_ID = ?";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedInv), true);
	}

	/**
	 * ���ܣ�����ֿ�������
	 * @param orgId       OU��֯ID
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getInvOption2(String orgId, String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AIV.INV_CODE,"
				+ " AIV.INV_NAME"
				+ " FROM"
				+ " AMS_INV_V AIV"
				+ " WHERE"
				+ " AIV.ORGANIZATION_ID = ?";
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

	/**
	 * ���ܣ�����ֿ�����������
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getWarehouseTypeOption(String  selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EFV.CODE  VALUE,\n" +
                "       EFV.VALUE NAME\n" +
                "  FROM ETS_FLEX_VALUE_SET EFVS,\n" +
                "       ETS_FLEX_VALUES    EFV\n" +
                " WHERE EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID\n" +
                "   AND EFVS.CODE = 'INV_TYPE'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedInv), true);
	}

	/*
	   * ��������������
	   */
	public String getYearOption(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = 
			"SELECT DATEPART(YY,GETDATE()) VALUE,DATEPART(YY,GETDATE()) NAME\n" +
			"UNION\n" + 
			"SELECT DATEPART(YY,DATEADD(YY,-1,GETDATE())) VALUE,DATEPART(YY,DATEADD(YY,-1,GETDATE())) NAME\n" + 
			"UNION\n" + 
			"SELECT DATEPART(YY,DATEADD(YY,-2,GETDATE())) VALUE,DATEPART(YY,DATEADD(YY,-2,GETDATE())) NAME\n" + 
			"UNION\n" + 
			"SELECT DATEPART(YY,DATEADD(YY,-3,GETDATE())) VALUE,DATEPART(YY,DATEADD(YY,-3,GETDATE())) NAME\n" + 
			"UNION\n" + 
			"SELECT DATEPART(YY,DATEADD(YY,-4,GETDATE())) VALUE,DATEPART(YY,DATEADD(YY,-4,GETDATE())) NAME";

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, false);
	}

    public String getYear2Option(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT  YEAR(GETDATE()) VALUE, YEAR(GETDATE()) NAME  UNION \n" +
		" SELECT YEAR(GETDATE())-1 VALUE,YEAR(GETDATE())-1 NAME  UNION \n" +
		" SELECT YEAR(GETDATE())-2 VALUE,YEAR(GETDATE())-4 NAME  UNION \n" +
		" SELECT YEAR(GETDATE())-3 VALUE,YEAR(GETDATE())-3 NAME  UNION \n" +
		" SELECT YEAR(GETDATE())-4 VALUE, YEAR(GETDATE())-4 NAME  \n" +
		" ORDER BY VALUE DESC";

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

    /*
	   * ���12����
	   */
	public String getMonthOption(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
				"  SELECT '1'  VALUE ,'01'  NAME UNION \n" +
						"  SELECT '2'  VALUE ,'02'  NAME UNION \n" +
						"  SELECT '3'  VALUE ,'03'  NAME UNION \n" +
						"  SELECT '4'  VALUE ,'04'  NAME UNION \n" +
						"  SELECT '5'  VALUE ,'05'  NAME UNION \n" +
						"  SELECT '6'  VALUE ,'06'  NAME UNION \n" +
						"  SELECT '7'  VALUE ,'07'  NAME UNION \n" +
						"  SELECT '8'  VALUE ,'08'  NAME UNION \n" +
						"  SELECT '9'  VALUE ,'09'  NAME UNION \n" +
						"  SELECT '10' VALUE  ,'10' NAME UNION \n" +
						"  SELECT '11' VALUE  ,'11' NAME UNION \n" +
						"  SELECT '12' VALUE  ,'12' NAME ORDER BY NAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, false);
	}

    public String getMonth2Option(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
				"  SELECT '1'  VALUE ,'01'  NAME  UNION \n" +
						"  SELECT '2'  VALUE ,'02'  NAME  UNION \n" +
						"  SELECT '3'  VALUE ,'03'  NAME  UNION \n" +
						"  SELECT '4'  VALUE ,'04'  NAME  UNION \n" +
						"  SELECT '5'  VALUE ,'05'  NAME  UNION \n" +
						"  SELECT '6'  VALUE ,'06'  NAME  UNION \n" +
						"  SELECT '7'  VALUE ,'07'  NAME  UNION \n" +
						"  SELECT '8'  VALUE ,'08'  NAME  UNION \n" +
						"  SELECT '9'  VALUE ,'09'  NAME  UNION \n" +
						"  SELECT '10' VALUE  ,'10' NAME  UNION \n" +
						"  SELECT '11' VALUE  ,'11' NAME  UNION \n" +
						"  SELECT '12' VALUE  ,'12' NAME   " +
						"  ORDER BY NAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

    public String getMenuValue(String selectedMenu) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
				"SELECT SRD.RES_ID, SRD.RES_NAME\n" +
						"  FROM SF_RES_DEFINE SRD\n" +
						" WHERE (SRD.RES_PAR_ID IS NULL OR SRD.RES_PAR_ID = '')  \n" +
						"   AND EXISTS (SELECT NULL\n" +
						"          FROM SF_RES_PRIVS SRP, SF_USER_RIGHT SUR\n" +
						"         WHERE SRP.ROLE_NAME = SUR.ROLE_NAME\n" +
						"           AND SRP.SYSTEM_ID = SRD.SYSTEM_ID\n" +
						"           AND SUR.USER_ID = ?)\n" +
						" ORDER BY SRD.RES_ID, SRD.RES_PAR_ID";
		sqlArgs.add(userAccount.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", true);
	}

	public String getSmallMenuValue(String selectedMenu, String resParId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT SRD.SYSTEM_ID,SRD.RES_NAME\n" +
				"  FROM SF_RES_DEFINE SRD\n" +
				" WHERE  " + SyBaseSQLUtil.isNotNull("SRD.RES_PAR_ID") + "  \n" +
				"   AND  " + SyBaseSQLUtil.isNotNull("SRD.RES_URL") + " \n" +
				"   AND EXISTS (SELECT NULL\n" +
				"          FROM SF_RES_PRIVS SRP, SF_USER_RIGHT SUR\n" +
				"         WHERE SRP.ROLE_NAME = SUR.ROLE_NAME\n" +
				"           AND SRP.SYSTEM_ID = SRD.SYSTEM_ID\n" +
				"           AND SUR.USER_ID = ?)\n" +
				"   AND ( " + SyBaseSQLUtil.isNull() + "  OR SRD.RES_PAR_ID LIKE ? || '.%')\n" +
				" ORDER BY SRD.RES_ID, SRD.RES_PAR_ID";
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(resParId);
		sqlArgs.add(resParId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedMenu, false);
	}

	/**
	 * ����רҵ�б�
	 * @param dictCode String
	 * @return String
	 * @throws QueryException
	 */
	public String getCategoryOption(String dictCode) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EFV.CODE, EFV.DESCRIPTION\n" +
				"  FROM ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
				" WHERE EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
				"   AND EFVS.CODE = ?";
		sqlArgs.add(dictCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", false);
	}

	/**
	 * ������������б�
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getFlowGroupOption(int selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT SG.GROUP_ID, SG.GROUP_NAME\n" +
				"  FROM SF_GROUP SG\n" +
				" WHERE SG.IS_DESIGNER = 1";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectedInv), true);
	}

	/**
	 * �������רҵ�б�
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getGroupCategory(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EFV.CODE,EFV.VALUE\n" +
				"  FROM ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
				" WHERE EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID\n" +
				"   AND EFVS.CODE = 'GROUP_CATEGORY'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

	/**
	 * ���ܣ��г���ʧЧ
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getDisabled(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT 'N' CODE , '��Ч' VALUE  \n" +
				"UNION\n" +
				"SELECT 'Y' CODE, 'ʧЧ' VALUE ";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, false);
	}

	/**
	 * @return GridQuery;
	 *         �г�δƥ��AMS�ص�
	 * @throws com.sino.base.exception.QueryException
	 *
	 */
	public String getAMSLocation() throws QueryException {
		String orgId = String.valueOf(userAccount.getOrganizationId());
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "	SELECT " +
				" EO.WORKORDER_OBJECT_NO ETSNO, " +
				" EO.WORKORDER_OBJECT_LOCATION ETSLOCATION " +
				" FROM  " +
				" ETS_OBJECT EO " +
				//<70���ǲֿ�ص�
				" WHERE  (EO.OBJECT_CATEGORY <70 OR EO.OBJECT_CATEGORY = 80) " +
				"	AND  EO.IS_TEMP_ADDR = 0 " +
				"   AND (EO.DISABLE_DATE IS NULL OR EO.DISABLE_DATE ='')  " +
				"	AND  NOT EXISTS " +
				"        (SELECT 1 FROM ETS_MIS_LOCATION_MATCH EMLM " +
				"			WHERE EMLM.WORKORDER_OBJECT_NO=EO.WORKORDER_OBJECT_NO " +
				"			AND EMLM.ORGANIZATION_ID=" + orgId + ")" +
				" AND  EO.ORGANIZATION_ID=" + orgId +
				" ORDER BY EO.WORKORDER_OBJECT_LOCATION  ";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * �г�δƥ��MIS�ص�
	 * @return GridQuery
	 * @throws com.sino.base.exception.QueryException
	 *
	 */
	public String getMISLocation() throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT " +
				" EFA.ASSETS_LOCATION MISLOCATION " +
				" FROM ";
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			sqlStr=sqlStr+ " ETS_FA_ASSETS_LOCATION_TD EFA";
		} else {
			sqlStr=sqlStr+ " ETS_FA_ASSETS_LOCATION EFA ,";
		}	
		sqlStr=sqlStr+
				" ETS_OU_CITY_MAP EOCM " +
				" WHERE" +
				" SUBSTR(EFA.BOOK_TYPE_CODE,-4,4)=EOCM.COMPANY_CODE " +
				" AND EOCM.ORGANIZATION_ID= ?" +
				" AND  NOT EXISTS " +
				"  	(SELECT 1 FROM ETS_MIS_LOCATION_MATCH EMLM " +
				"       WHERE EMLM.ASSETS_LOCATION=EFA.ASSETS_LOCATION " +
				"       AND EMLM.ORGANIZATION_ID= ?" +
				" 	)" +
				"	GROUP BY EFA.ASSETS_LOCATION ";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ������ļ���Ϣ�������б�
	 * �˷������ص���������û�С�---ѡ��---����
	 * @param barCode String
	 * @return String
	 * @throws QueryException
	 */
	public String getAttachFile(String barCode) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AIF.FILE_DESC || '$' || AIF.FILE_PATH, AIF.FILE_DESC\n"
				+ " FROM"
				+ " AMS_ITEM_FILES AIF\n"
				+ " WHERE "
				+ " AIF.BARCODE = ?";
		sqlArgs.add(barCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", false);
	}

	/**
	 * ���ܣ����ɱ�ǩ�����������Ϣ�������б�
	 * @return String
	 * @throws QueryException �˷������ص���������û�С�---ѡ��---����
	 */
	public String getPrintGroup() throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT DISTINCT SG.GROUP_ID,\n" +
				" SG.GROUP_NAME\n" +
				" FROM " +
				" SF_GROUP SG, " +
				" SF_USER_RIGHT SUR, " +
				" SF_USER SU, " +
				" SF_ROLE SR\n" +
				" WHERE " +
				" SU.USER_ID = SUR.USER_ID\n" +
				" AND SUR.GROUP_ID = SG.GROUP_ID\n" +
				" AND SUR.ROLE_ID = SR.ROLE_ID\n" +
				" AND SU.USER_ID = \n" + userAccount.getUserId() +
				" AND SR.ROLE_NAME = '��ǩ������'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", false);
	}

	/**
	 * ���ܣ������ض���ɫ�����Ϣ�������б�
	 * @param role ��ɫ
	 * @return String
	 * @throws QueryException �˷������ص���������û�С�---ѡ��---����
	 */
	public String getBJGroup(String role) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT DISTINCT SG.GROUP_ID,\n" +
				" SG.GROUP_NAME\n" +
				" FROM " +
				" SF_GROUP SG, " +
				" SF_USER_RIGHT SUR, " +
				" SF_USER SU, " +
				" SF_ROLE SR\n" +
				" WHERE " +
				" SU.USER_ID = SUR.USER_ID\n" +
				" AND SUR.GROUP_ID = SG.GROUP_ID\n" +
				" AND SUR.ROLE_ID = SR.ROLE_ID\n" +
				" AND SU.USER_ID = \n" + userAccount.getUserId() +
				" AND SR.ROLE_NAME = " + "'" + role + "'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", false);
	}

	/**
	 * ���ܣ������ض���ɫ�����Ϣ�������б�
	 * @return String
	 * @throws QueryException �˷������ص���������û�С�---ѡ��---����
	 */
	public String getBJGroup2() throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT DISTINCT SG.GROUP_ID,\n" +
				" SG.GROUP_NAME\n" +
				" FROM " +
				" SF_GROUP SG, " +
				" SF_USER_RIGHT SUR, " +
				" SF_USER SU, " +
				" SF_ROLE SR\n" +
				" WHERE " +
				" SU.USER_ID = SUR.USER_ID\n" +
				" AND SUR.GROUP_ID = SG.GROUP_ID\n" +
				" AND SUR.ROLE_ID = SR.ROLE_ID\n" +
				" AND SU.USER_ID = \n" + userAccount.getUserId() +
				" AND SR.ROLE_NAME = '��Ʒ��������Ա'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml("", false);
	}

	/**
	 * ���ܣ����ɵ��������Ǳ����Ա�������б�
	 * �˷������ص���������û�С�---ѡ��---����
	 * @param selectValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getInstrument(String selectValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT DISTINCT SUR.USER_ID,\n" +
				" AMS_PUB_PKG.GET_COMPANY_NAME(SUR.USER_ID) COMPANY_NAME\n" +
//                        " AMS_PUB_PKG.GET_USER_NAME(SUR.USER_ID)\n" +
				" FROM " +
				" SF_USER_RIGHT SUR, " +
				" SF_ROLE SR\n" +
				" WHERE " +
				" SUR.ROLE_ID = SR.ROLE_ID\n" +
				" AND SR.ROLE_NAME = '�����Ǳ����Ա'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectValue, false);
	}

	/**
	 * ���ܣ�������������б�
	 * @param orgId       String
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getGroupOption(int orgId, String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT SG.GROUP_ID, SG.GROUP_NAME\n" +
				"  FROM SF_GROUP SG\n" +
				" WHERE SG.IS_DESIGNER = '0'" +
				" AND SG.ENABLED = 'Y'" +
				" AND SG.ORGANIZATION_ID = ?";
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

	/**
	 * ���ܣ�����רҵ����ѡ��
	 * @param groupId        String
	 * @param organizationId String
	 * @return String
	 * @throws QueryException
	 */
	public String getLeftGroupOption(String groupId, String organizationId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT " +
				" EFV.CODE, EFV.VALUE\n" +
				" FROM ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
				" WHERE EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
				" AND EFVS.CODE = ?\n" +
				" AND NOT EXISTS\n" +
				"(SELECT 1\n" +
				"         FROM ETS_OBJECT_CAT_GROUP EOCG\n" +
				"        WHERE CONVERT( VARCHAR, EOCG.OBJECT_CATEGORY ) = EFV.CODE\n" +
				"          AND CONVERT( VARCHAR , EOCG.GROUP_ID ) =  ? )";
		sqlArgs.add(DictConstant.OBJECT_CATEGORY);
		sqlArgs.add(groupId);
//        sqlArgs.add(userAccount.getOrganizationId());
//        sqlArgs.add(organizationId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ�����רҵ����ѡ��
	 * @param groupId        ;
	 * @param organizationId ;
	 * @return String
	 * @throws QueryException
	 */
	public String getRightGroupOption(String groupId, String organizationId) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT " +
				" EFV.CODE, EFV.VALUE\n" +
				" FROM ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS\n" +
				" WHERE EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
				" AND EFVS.CODE = ?\n" +
				" AND EXISTS\n" +
				"(SELECT 1\n" +
				"         FROM ETS_OBJECT_CAT_GROUP EOCG\n" +
				"        WHERE CONVERT( VARCHAR, EOCG.OBJECT_CATEGORY )= EFV.CODE\n" +
				"          AND CONVERT( VARCHAR, EOCG.GROUP_ID ) = ?)";
		sqlArgs.add(DictConstant.OBJECT_CATEGORY);
		sqlArgs.add(groupId);
//        sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ�ȡ���������б�
	 * @return String
	 * @throws QueryException
	 */
	public String getSpareVendorOption(String selectedValue) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT ASV.VENDOR_ID,\n" +
                "       ASV.VENDOR_NAME\n" +
                "  FROM AMS_SPARE_VENDORS ASV";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }

	public String getFromMonthOption(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
				"SELECT '200801' VALUE, '200801' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200802' VALUE, '200802' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200803' VALUE, '200803' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200804' VALUE, '200804' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200805' VALUE, '200805' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200806' VALUE, '200806' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200807' VALUE, '200807' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200808' VALUE, '200808' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200809' VALUE, '200809' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200810' VALUE, '200810' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200811' VALUE, '200811' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200812' VALUE, '200812' NAME  \n" +
						"UNION\n" +
						"SELECT '200901' VALUE, '200901' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200902' VALUE, '200902' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200903' VALUE, '200903' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200904' VALUE, '200904' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200905' VALUE, '200905' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200906' VALUE, '200906' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200907' VALUE, '200907' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200908' VALUE, '200908' NAME\n" +
						"  \n" +
						"union\n" +
						"SELECT '200909' VALUE, '200909' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200910' VALUE, '200910' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200911' VALUE, '200911' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '200912' VALUE, '200912' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201001' VALUE, '201001' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201002' VALUE, '201002' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201003' VALUE, '201003' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201004' VALUE, '201004' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201005' VALUE, '201005' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201006' VALUE, '201006' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201007' VALUE, '201007' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201008' VALUE, '201008' NAME  \n" +
						"UNION\n" +
						"SELECT '201009' VALUE, '201009' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201010' VALUE, '201010' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201011' VALUE, '201011' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201012' VALUE, '201012' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201101' VALUE, '201101' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201102' VALUE, '201102' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201103' VALUE, '201103' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201104' VALUE, '201104' NAME\n" +
						"  \n" +
						"  union\n" +
						"SELECT '201105' VALUE, '201105' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201106' VALUE, '201106' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201107' VALUE, '201107' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201108' VALUE, '201108' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201109' VALUE, '201109' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201110' VALUE, '201110' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201111' VALUE, '201111' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201112' VALUE, '201112' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201201' VALUE, '201201' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201202' VALUE, '201202' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201203' VALUE, '201203' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201204' VALUE, '201204' NAME  \n" +
						"UNION\n" +
						"SELECT '201205' VALUE, '201205' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201206' VALUE, '201206' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201207' VALUE, '201207' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201208' VALUE, '201208' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201209' VALUE, '201209' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201210' VALUE, '201210' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201211' VALUE, '201211' NAME\n" +
						"  \n" +
						"UNION\n" +
						"SELECT '201212' VALUE, '201212' NAME\n" +
						"  \n" +
						"\n" +
						"ORDER BY NAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

	/**
	 * ���ܣ�ȡʡ��˾�Ĵ��޿�����޿��б�
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getSpareOption(String selectedInv) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EO.WORKORDER_OBJECT_NO, EO.WORKORDER_OBJECT_LOCATION WORKORDER_OBJECT_NAME\n" +
                        "  FROM ETS_OBJECT EO\n" +
                        " WHERE (EO.OBJECT_CATEGORY = '72' OR EO.OBJECT_CATEGORY = '73')\n" +
                        "   AND EO.ORGANIZATION_ID = 82";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedInv, true);
    }

    /**
	 * ���ܣ�ȡ�������Ͽ��б�
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getSpareDisableOption(String selectedInv) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EO.WORKORDER_OBJECT_NO, \n" +
                "       EO.WORKORDER_OBJECT_LOCATION WORKORDER_OBJECT_NAME\n" +
                "  FROM ETS_OBJECT EO\n" +
                " WHERE EO.OBJECT_CATEGORY = '76'";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedInv, true);
    }

    /**
	 * ���ܣ�ȡ�������ϻ��չ�˾���б�
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getSpareDisableCompanyOption(String selectedInv) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT ASBC.COMPANY_ID, ASBC.COMPANY_NAME FROM AMS_SPARE_BF_COMPANY ASBC WHERE ASBC.ENABLE = 'Y' ORDER BY ASBC.COMPANY_NAME";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedInv, true);
    }

	/**
	 * ���ܣ�ȡ��Ӧ�������б�
	 * @param selectedVendor String
	 * @return String
	 * @throws QueryException
	 */
	public String getVendorOption(String selectedVendor) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT EMPV.VENDOR_ID, EMPV.VENDOR_NAME FROM ETS_MIS_PO_VENDORS EMPV";

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedVendor, true);
	}

	/**
	 * ���ܣ�ȡʡ��˾��ou������
	 * @param selectedInv String
	 * @return String
	 * @throws QueryException
	 */
	public String getPouOption(String selectedInv) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT" +
				" EOCM.ORGANIZATION_ID, " +
				" EOCM.COMPANY\n" +
				" FROM " +
				" ETS_OU_CITY_MAP EOCM\n" +
				" WHERE EOCM.ORGANIZATION_ID = " + userAccount.getOrganizationId();
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedInv, true);
	}

	/**
	 * ����:��ȡMIS���������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getCountyMIS(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EC.COUNTY_CODE_COA_CC,"
				+ " EC.COUNTY_NAME"
				+ " FROM"
				+ " ETS_COUNTY EC"
				+ " WHERE"
				+ " EC.COMPANY_CODE = ?";
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ���ȡ��ɫ����ѡ��
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getRoleOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
				+ " SR.ROLE_ID,"
				+ " SR.ROLE_NAME"
				+ " FROM"
				+ " SF_ROLE SR"
                + " ORDER BY SR.ROLE_NAME";
		sqlModel.setSqlStr(sqlStr);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ����:��ȡ��ǰ�û�������֯�µĴ�ά��˾�����б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getMainCompanyOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AMC.COMPANY_ID,"
				+ " AMC.NAME"
				+ " FROM"
				+ " AMS_MAINTAIN_COMPANY AMC"
				+ " WHERE"
				+ " AMC.ORGANIZATION_ID = ?";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ������ά��˾�Ѿ�ȷ�ϵ����εص��ѡ������
	 * @return String
	 * @throws QueryException
	 */
	public String getConfirmedLocOpt() throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EO.WORKORDER_OBJECT_NO,"
				+ " EO.WORKORDER_OBJECT_LOCATION"
				+ " FROM"
				+ " ETS_OBJECT                  EO,"
				+ " AMS_MAINTAIN_RESPONSIBILITY AMR"
				+ " WHERE"
				+ " EO.WORKORDER_OBJECT_NO = AMR.OBJECT_NO"
				+ " AND AMR.COMPANY_ID = ?";
		sqlModel.setSqlStr(sqlStr);
		sqlArgs.add(userAccount.getMaintainCompany());
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml();
	}

	/**
	 * ���ܣ����첿��(���)������(���ڰ�����ͳ���̵�ص���)
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getGroupOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " SG.GROUP_ID,"
				+ " SG.GROUP_NAME"
				+ " FROM"
				+ " SF_GROUP SG"
				+ " WHERE"
				+ " (MOD(IS_DESIGNER, 2) = 0 OR IS_DESIGNE IS NULL OR IS_DESIGNE='')"
				+ " AND SG.ENABLED = 'Y'"
				+ " AND SG.ORGANIZATION_ID = ?";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ����첿��������(���ڰ�����ͳ���̵㡢Ѳ���豸��)
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getDeptOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AMD.DEPT_CODE,"
				+ " AMD.DEPT_NAME"
				+ " FROM"
				+ " AMS_MIS_DEPT AMD"
				+ " WHERE"
				+ " AMD.COMPANY_CODE = ?";
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}
	
	/**
	 * ���ܣ�ͨ��OU�鵽��OU�µ����в���
	 * @param selectedValue String 	ѡ����
	 * @param companyCode			��˾���
	 * @return String
	 * @throws QueryException
	 */
	public String getDeptOption1(String companyCode ,String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AMD.DEPT_CODE,"
				+ " AMD.DEPT_NAME"
				+ " FROM"
				+ " AMS_MIS_DEPT AMD"
				+ " WHERE"
				+ " AMD.COMPANY_CODE = ?";
		sqlArgs.add(companyCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}
	
	public String getDeptOptionByOrgId(int organizationId ,String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AMD.DEPT_CODE,"
				+ " AMD.DEPT_NAME"
				+ " FROM"
				+ " ETS_OU_CITY_MAP EOCM, AMS_MIS_DEPT AMD"
				+ " WHERE"
				+ " AMD.COMPANY_CODE = EOCM.COMPANY_CODE " 
				+ " AND EOCM.ORGANIZATION_ID = ?";
		sqlArgs.add(organizationId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}
	
    	public String getMisDeptOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT SUBSTR(EFA.ASSETS_LOCATION_CODE,\n" +
                "              1,\n" +
                "              INSTR(EFA.ASSETS_LOCATION_CODE, '.') - 1) DEPT_CODE,\n" +
                "       SUBSTR(EFA.ASSETS_LOCATION,\n" +
                "              0,\n" +
                "              INSTR(EFA.ASSETS_LOCATION, '.', 1, 1) - 1) DEPT_NAME\n" +
                "  FROM ETS_FA_ASSETS EFA\n" +
                " WHERE EFA.COMPANY_CODE = ?\n" +
                " GROUP BY SUBSTR(EFA.ASSETS_LOCATION_CODE,\n" +
                "                 1,\n" +
                "                 INSTR(EFA.ASSETS_LOCATION_CODE, '.') - 1),\n" +
                "          SUBSTR(EFA.ASSETS_LOCATION,\n" +
                "                 0,\n" +
                "                 INSTR(EFA.ASSETS_LOCATION, '.', 1, 1) - 1)";
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}

    /**
	 * ���ܣ���ȡ�����������������
	 * @param selectedValue String
	 * @return String
	 */
	public String getGroupFlowOption(String selectedValue) {
		StringBuffer optHtml = new StringBuffer();
		optHtml.append("<option value=\"\">--��ѡ��--</option>");
		String[] selectValues = DictConstant.GRP_FLOW_VALUE;
		String[] selectCaptis = DictConstant.GRP_FLOW_CAPTI;
		for (int i = 0; i < selectValues.length; i++) {
			optHtml.append("<option value=\"");
			optHtml.append(selectValues[i]);
			if (selectValues[i].equals(selectedValue)) {
				optHtml.append("\" selected>");
			} else {
				optHtml.append("\">");
			}
			optHtml.append(selectCaptis[i]);
			optHtml.append("</option>");
		}
		return optHtml.toString();
	}


	/**
	 * ���ܣ���ȡ�����������������
	 * @param selectedValue String
	 * @return String
	 */
	public String getEnableOption(String selectedValue) {
		StringBuffer optHtml = new StringBuffer();
		optHtml.append("<option value=\"\">--��ѡ��--</option>");
		String[] selectValues = DictConstant.BOOLEAN_VALUE;
		String[] selectCaptis = DictConstant.BOOLEAN_CAPTI;
		for (int i = 0; i < selectValues.length; i++) {
			optHtml.append("<option value=\"");
			optHtml.append(selectValues[i]);
			if (selectValues[i].equals(selectedValue)) {
				optHtml.append("\" selected>");
			} else {
				optHtml.append("\">");
			}
			optHtml.append(selectCaptis[i]);
			optHtml.append("</option>");
		}
		return optHtml.toString();
	}

	/**
	 * ���ܣ���ȡ�������
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getFlowGrpOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
				+ " SG.GROUP_ID,"
				+ " SG.GROUP_NAME"
				+ " FROM"
				+ " SF_GROUP SG"
				+ " WHERE";
		sqlModel.setSqlStr(sqlStr);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}

    public String getResDeptOption(String organizationId, String selectedValue) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT"
                        + " AMD.DEPT_CODE,"
                        + " AMD.DEPT_NAME"
                        + " FROM"
                        + " AMS_MIS_DEPT AMD,"
                        + " ETS_OU_CITY_MAP EM"
                        + " WHERE"
                        + " AMD.COMPANY_CODE=EM.COMPANY_CODE"
                        + " AND EM.ORGANIZATION_ID = ?";
        sqlArgs.add( ConvertUtil.String2Int( organizationId ) );
//        sqlArgs.add(  organizationId   );
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }


    /**
     * Function :               ��ȡ���еĵص�רҵ����������Ϣ
     * @param selectedValue     Ĭ��ѡ�е�ֵ
     * @return                  String
     * @throws QueryException
     */
    public String getAllLocCategoryDesc(String selectedValue) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
/*                "SELECT AOCM.FA_LOC_CODE, \n" +
                "       AOCM.LOC_CATEGORY_DESC\n" +
                "  FROM AMS_OBJECT_CATEGORY_MAP AOCM";*/
        	
        	" SELECT EFV.ATTRIBUTE2, EFV.VALUE \n" +
        	"   FROM ETS_FLEX_VALUE_SET EFVS, ETS_FLEX_VALUES EFV \n" + 
        	"  WHERE EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID \n" + 
        	"    AND EFVS.CODE = 'OBJECT_CATEGORY' \n" ;
        	
        	
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }
    
    public String getOdAssetsStatusOption(String selectedValue) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " EFV.CODE,"
                + " EFV.VALUE"
                + " FROM"
                + " ETS_FLEX_VALUE_SET EFVS,"
                + " ETS_FLEX_VALUES    EFV"
                + " WHERE"
                + " EFVS.FLEX_VALUE_SET_ID = EFV.FLEX_VALUE_SET_ID"
                + " AND EFVS.CODE = ?"
                + " AND EFV.ATTRIBUTE2 = 'ASSETS_STATUS'"
                + " AND EFV.CODE = 'DISCARDED'";
        sqlArgs.add(AssetsDictConstant.ITEM_STATUS);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }

	/**
	 * ���ܣ���ǰ�û��������β���
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getRespDeptOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = 
			    /*  "SELECT"
				+ " AMD.DEPT_CODE,"
				+ " AMD.DEPT_NAME"
				+ " FROM"
				+ " AMS_MIS_DEPT AMD"
				+ " WHERE"
				+ " AMD.COMPANY_CODE = ?";*/
			/*"SELECT T.DEPT_CODE, AMD.DEPT_NAME, CONVERT( INT , T.USER_ID) USER_ID, T.PRIVI_ID \n" +
			"  FROM AMS_ASSETS_PRIVI T, AMS_MIS_DEPT AMD \n" +
			" WHERE T.USER_ID = ? \n" +
			"   AND AMD.DEPT_CODE = T.DEPT_CODE \n" +
			"   AND T.DEPT_CODE IS NOT NULL \n" +
			"   ORDER BY AMD.DEPT_NAME \n" ;*/
		
			"SELECT AMD.DEPT_CODE, AMD.DEPT_NAME, SU.USER_ID \n" +
			"  FROM SF_USER SU, AMS_MIS_EMPLOYEE AME, AMS_MIS_DEPT AMD \n" +
			" WHERE SU.EMPLOYEE_NUMBER = AME.EMPLOYEE_NUMBER \n" +
			"   AND AMD.DEPT_CODE = AME.DEPT_CODE \n" +
			"   AND SU.USER_ID = ? \n" ;

		sqlArgs.add(userAccount.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
		return optProducer.getOptionHtml(selectedValue, true);
	}
	
    /**
     * ���ܣ���ȡ�ص��������������򣬺����ƶ��á�
     * @param organizationId ��˾OU��֯ID
     * @param selectedValue  Ĭ��ѡ�е�ֵ
     * @return String
     * @throws QueryException
     */
    public String getAreaOptions(int organizationId, String selectedValue) throws QueryException {
    	EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(userAccount, null, conn);
    	String flexValueSetName = "";
    	String isTd = "";
    	if(etsOuCityMapDAO.isTdOrganization(organizationId) || userAccount.getIsTt().equals("Y")){
    		flexValueSetName = SinoConfig.getLoc1SetNameTD();
    		isTd = "Y";
    	} else {
    		flexValueSetName = SinoConfig.getLoc1SetNameMis();
    		isTd = "N";
    	}
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        String provinceCode = SinoConfig.getProvinceCode();
        if(provinceCode.equals(DictConstant.PROVINCE_CODE_JIN) || userAccount.getIsTt().equals("Y") ){   //ɽ�����⴦�������˵�һ��
            sqlStr = "SELECT FFV.FLEX_VALUE, FFV.DESCRIPTION\n" +
        	"  FROM M_FND_FLEX_VALUE_SETS FFVS,\n" + 
        	"       M_FND_FLEX_VALUES     FFV,\n" + 
        	"       ETS_OU_CITY_MAP       EOCM\n" + 
        	" WHERE FFVS.FLEX_VALUE_SET_ID = FFV.FLEX_VALUE_SET_ID\n" + 
        	"   AND FFVS.FLEX_VALUE_SET_NAME = ?\n" + 
        	"   AND FFV.ENABLED_FLAG = 'Y'\n" + 
        	"   AND EOCM.ORGANIZATION_ID = ?\n" + 
        	"   AND FFVS.SOURCE =\n" + 
        	"       (CASE EOCM.IS_TD WHEN 'Y' THEN 'TDMIS' ELSE 'MIS' END)";
        } else {
            sqlStr = "SELECT FFV.FLEX_VALUE,\n" +
            "       FFV.DESCRIPTION\n" +
            "FROM   M_FND_FLEX_VALUE_SETS FFVS,\n" +
            "       M_FND_FLEX_VALUES     FFV,\n" +
            "       ETS_OU_CITY_MAP       EOCM\n" +
            "WHERE  FFVS.FLEX_VALUE_SET_ID = FFV.FLEX_VALUE_SET_ID\n" +
            "       AND FFVS.FLEX_VALUE_SET_NAME = ?\n" +
            "       AND FFV.ENABLED_FLAG = 'Y'\n" +
            "       AND FFV.COMPANY_CODE = EOCM.COMPANY_CODE\n" +    //������ά���õص��һ�κ͹�˾�Ĺ�ϵ
            "       AND EOCM.ORGANIZATION_ID = ?\n" +
            "       AND EOCM.IS_TD = '" + isTd + "'\n";
        }
        sqlArgs.add(flexValueSetName);
        sqlArgs.add(organizationId);
        
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }
    
      public String getNMAreaOptions(int organizationId, String selectedValue) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT FFV.FLEX_VALUE,\n" +
                "       FFV.DESCRIPTION\n" +
                "FROM   M_FND_FLEX_VALUE_SETS FFVS,\n" +
                "       M_FND_FLEX_VALUES     FFV,\n" +
                "       ETS_OU_CITY_MAP       EOCM\n" +
                "WHERE  FFVS.FLEX_VALUE_SET_ID = FFV.FLEX_VALUE_SET_ID\n" +
                "       AND FFVS.FLEX_VALUE_SET_NAME = 'NMMC_FA_LOC_1'\n" +
                "       AND FFV.ENABLED_FLAG = 'Y'\n" +
                "        AND SUBSTRING(FFV.FLEX_VALUE, 1, 2) = SUBSTRING(EOCM.COMPANY_CODE,3,4)\n" +
                "       AND EOCM.ORGANIZATION_ID = ?\n" +
                "       AND EOCM.IS_TD = 'N'\n" +
                "UNION ALL\n" +
                "SELECT FFV.FLEX_VALUE,\n" +
                "       FFV.DESCRIPTION\n" +
                "FROM   M_FND_FLEX_VALUE_SETS FFVS,\n" +
                "       M_FND_FLEX_VALUES     FFV,\n" +
                "       ETS_OU_CITY_MAP       EOCM\n" +
                "WHERE  FFVS.FLEX_VALUE_SET_ID = FFV.FLEX_VALUE_SET_ID\n" +
                "       AND FFVS.FLEX_VALUE_SET_NAME = 'CMCC_FA_LOC_1'\n" +
                "       AND FFV.ENABLED_FLAG = 'Y'\n" +
                "        AND SUBSTRING(FFV.FLEX_VALUE, 1, 2) = SUBSTRING(EOCM.COMPANY_CODE,3,4)\n" +
                "       AND EOCM.ORGANIZATION_ID = ?\n" +
                "       AND EOCM.IS_TD = 'Y'";

        sqlArgs.add(organizationId);
        sqlArgs.add(organizationId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }
    public String getSpareFromObjectOption(String selectedValue) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EO.WORKORDER_OBJECT_NO,\n" +
                "       EO.WORKORDER_OBJECT_NAME\n" +
                "  FROM ETS_OBJECT EO\n" +
                " WHERE EO.OBJECT_CATEGORY = '72'\n" +
                "       AND\n" +
                "       (EO.WORKORDER_OBJECT_NAME LIKE '%����%' AND EO.ORGANIZATION_ID = 82 OR\n" +
                "       (EO.WORKORDER_OBJECT_NAME NOT LIKE '%����%' AND EO.ORGANIZATION_ID = 82))";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
    }

    public String getFlexValueSetOption(String selectValue, String range) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT MFFVS.FLEX_VALUE_SET_NAME, MFFVS.FLEX_VALUE_SET_NAME FROM M_FND_FLEX_VALUE_SETS MFFVS WHERE 1=1 \n";
        if (StrUtil.isNotEmpty(range)) {
            sqlStr += " AND SOURCE=?";
            sqlArgs.add(range);
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectValue, true);
    }

	/**
	 * ���ܣ���ȡ����ִ����(Ѳ�칤���Զ�����)
	 * @param selectedUser ѡ���û�
	 * @return String ����ִ����������
	 * @throws QueryException
	 */
	public String getWorkorderImplementUserOptions(String selectedUser) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT SU.USER_ID,\n" +
                "       SU.USERNAME\n" +
                "FROM   SF_USER SU\n" +
                "WHERE  SU.ORGANIZATION_ID = ?\n" +
                "       AND EXISTS (SELECT NULL\n" +
                "        FROM   SF_USER_AUTHORITY SUA\n" +
                "        WHERE  SU.USER_ID = SUA.USER_ID\n" +
                "               AND SUA.ROLE_NAME = '����ִ����')";

		sqlArgs.add(userAccount.getOrganizationId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedUser, true);
    }

    /**
	 * ���ܣ���ȡ�����鵵��(Ѳ�칤���Զ�����)
	 * @param selectedUser ѡ���û�
	 * @return String �����鵵��������
	 * @throws QueryException
	 */
	public String getWorkorderCheckoverUserOptions(String selectedUser) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT SU.USER_ID,\n" +
                "       SU.USERNAME\n" +
                "FROM   SF_USER SU\n" +
                "WHERE  SU.ORGANIZATION_ID = ?\n" +
                "       AND EXISTS (SELECT NULL\n" +
                "        FROM   SF_USER_AUTHORITY SUA\n" +
                "        WHERE  SU.USER_ID = SUA.USER_ID\n" +
                "               AND SUA.ROLE_NAME = '�����鵵��')";

		sqlArgs.add(userAccount.getOrganizationId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedUser, true);
    }
	
	 /**
	   * ���ܣ������������Ƶ������б�
	   *
	   * @param selectedValue String ѡ����ֵ
	   * @return String
	   * @throws QueryException
	   */
	  public String getAreaName(String selectedValue) throws QueryException {
	      SQLModel sqlModel = new SQLModel();
	      List sqlArgs = new ArrayList();
	      String sqlStr = "SELECT  ECA.AREA_ID ,ECA.AREA_NAME "+
	      " FROM EAM_CITY_AREA ECA, ETS_OU_CITY_MAP EOCM "+
	      " WHERE ECA.CITY_ID = EOCM.ORGANIZATION_ID " ;
//	      if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
	          sqlStr += " AND  EOCM.ORGANIZATION_ID = ?";
	          sqlArgs.add(userAccount.getOrganizationId());
	          sqlModel.setSqlStr(sqlStr);
	          sqlModel.setArgs(sqlArgs);
	          DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
	          return webFieldProducer.getOptionHtml(selectedValue, false);
//	      } else {
//	          sqlModel.setSqlStr(sqlStr);
//	          sqlModel.setArgs(sqlArgs);
//	          DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
//	          return webFieldProducer.getOptionHtml(selectedValue, true);
//	      }
	//
	      
	      
//	      sqlStr += " and  EOCM.ORGANIZATION_ID = ?";
//	      sqlArgs.add(userAccount.getOrganizationId()) ;
//	      sqlModel.setSqlStr(sqlStr);
//	      sqlModel.setArgs(sqlArgs);
//	      DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
//	      return webFieldProducer.getOptionHtml(selectedValue, true);
	  }
	  
		 /**
	    * ���ܣ����ɵ������Ƶ������б�
	    *
	    * @param selectedValue String ѡ����ֵ
	    * @return String
	    * @throws QueryException
	    */
	   public String getCityName(String selectedValue ,boolean addBlank) throws QueryException {
	       SQLModel sqlModel = new SQLModel();
	       List sqlArgs = new ArrayList();
	       String sqlStr = "SELECT EOCM.ORGANIZATION_ID , ECA.CITY_NAME  "+
	       	" FROM EAM_CITY_AREA ECA, ETS_OU_CITY_MAP EOCM "+
	       	" WHERE ECA.CITY_ID = EOCM.ORGANIZATION_ID ";
//	       if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
	           sqlStr += " and  EOCM.ORGANIZATION_ID = ?";
	           sqlStr+= " GROUP BY ECA.CITY_NAME ,EOCM.ORGANIZATION_ID ";
	           sqlArgs.add(userAccount.getOrganizationId());
	           sqlModel.setSqlStr(sqlStr);
	           sqlModel.setArgs(sqlArgs);
	           DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
	           return webFieldProducer.getOptionHtml(selectedValue, false);
	      /* } else {
	       	sqlStr+= " GROUP BY EOCM.CITY_NAME ,EOCM.ORGANIZATION_ID ";
	           sqlModel.setSqlStr(sqlStr);
	           sqlModel.setArgs(sqlArgs);
	           DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
	           return webFieldProducer.getOptionHtml(selectedValue, addBlank);
	       }*/
	   }

    /**
	 * ����:ɽ��������ȡ���������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getSpareCountyOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EC.COUNTY_CODE,"
				+ " EC.COUNTY_NAME"
				+ " FROM"
				+ " ETS_COUNTY EC"
				+ " WHERE"
				+ " EC.COMPANY_CODE = ?";
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue , true);
	}
	/**
	 * ��ȡ����OU��֯�����б��
	 * @param selectedValue String
	 * @param addBlank      �Ƿ���롰��ѡ��";
	 * @return String
	 * @throws QueryException
	 */
	public String getAllOrganizationCode(String selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ "	COMPANY_CODE,"
				+ " COMPANY"
				+ " FROM"
				+ " ETS_OU_CITY_MAP";
		SinoConfig c=new SinoConfig() {};
		if(c.getProvinceCode()=="41"){
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml(( selectedValue ), addBlank);
		}else{
			if (!(userAccount.isProvinceUser() && userAccount.isSysAdmin())) {
				sqlStr += " WHERE ORGANIZATION_ID = ?";
				sqlArgs.add(userAccount.getOrganizationId());
				sqlModel.setSqlStr(sqlStr);
				sqlModel.setArgs(sqlArgs);
				DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
				return webFieldProducer.getOptionHtml(selectedValue, false);
			} else {
			        	sqlModel.setSqlStr(sqlStr);
						sqlModel.setArgs(sqlArgs);
						DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
						return webFieldProducer.getOptionHtml(( selectedValue ), addBlank);
					}
			}		
		}
	
	/**
	 * Ӧ������ר��
	 * ��ȡ����OU��֯�����б��
	 * @param selectedValue String
	 * @param addBlank      �Ƿ���롰��ѡ��";
	 * @throws QueryException
	 */
	public String getAllOrganizationYj(int selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ORGANIZATION_ID,"
				+ " COMPANY,"
				+ "	COMPANY_CODE"
				+ " FROM"
				+ " ETS_OU_CITY_MAP";
		if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
			sqlStr += " WHERE ORGANIZATION_ID = ?";
			sqlArgs.add(userAccount.getOrganizationId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml( ConvertUtil.int2String( selectedValue ), false);
		}  else {
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
			return webFieldProducer.getOptionHtml( ConvertUtil.int2String( selectedValue ) , addBlank);
		}
	}
	
	
}
