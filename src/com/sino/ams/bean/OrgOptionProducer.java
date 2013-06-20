package com.sino.ams.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.DatabaseForWeb;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * User: zhoujs
 * Date: 2009-3-27 15:29:45
 * Function:��˾��ص�������
 */
@SuppressWarnings("unchecked")
public class OrgOptionProducer extends OptionProducer {
    public OrgOptionProducer(SfUserDTO userAccount, Connection conn) {
        super(userAccount, conn);
    }

    /**
     * ��˾����������
     * @param selectValue String
     * @return String
     * @throws QueryException ��ѯ�쳣
     */
    public String getCompanyOption(String selectValue) throws QueryException {
        return getCompanyOption(selectValue, "");
    }

    /**
     * @param selectValue
     * @param range       'Y'-TD;'N'-��TD;''-ALL
     * @return
     * @throws QueryException
     */
    public String getCompanyOption(String selectValue, String range) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        boolean addBlank = false;
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT COMPANY_CODE, COMPANY_CODE || STR_REPLACE(COMPANY, 'OU_', ' ') COMPANY\n" +
                        "  FROM ETS_OU_CITY_MAP \n" +
                        "   WHERE ENABLED='Y'";
        if (StrUtil.isNotEmpty(range)) {
            sqlStr += "   \n  AND IS_TD=?";
            sqlArgs.add(range);
        }
        if (userAccount.isProvinceUser() || userAccount.isSysAdmin()) {
            addBlank = true;
        } else {
            sqlStr += " \n  AND ORGANIZATION_ID=?";
            sqlArgs.add(userAccount.getOrganizationId());
        }
        sqlStr += " \nORDER BY COMPANY_CODE";

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectValue, addBlank);

    }

    /**
     * �ʲ��˲�������
     * @param selectValue String
     * @return String
     * @throws QueryException ��ѯ�쳣
     */
    public String getBookTypeCodeOption(String selectValue) throws QueryException {
        return getBookTypeCodeOption(selectValue, "");
    }

    /**
     * �ʲ��˲�������
     * @param selectValue
     * @param range       'Y'-TD;'N'-��TD;''-ALL
     * @return
     * @throws QueryException
     */
    public String getBookTypeCodeOption(String selectValue, String range) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        boolean addBlank = false;
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT BOOK_TYPE_CODE, COMPANY_CODE || STR_REPLACE(COMPANY, 'OU_', ' ') COMPANY\n" +
                "  FROM ETS_OU_CITY_MAP\n" +
                "   WHERE ENABLED='Y'\n ";
        if (StrUtil.isNotEmpty(range)) {
            sqlStr += " AND IS_TD=?";
            sqlArgs.add(range);
        }
        if (userAccount.isProvinceUser() || userAccount.isSysAdmin()) {
            addBlank = true;
        } else {
            sqlStr += " AND ORGANIZATION_ID=?";
            sqlArgs.add(userAccount.getOrganizationId());
        }
        sqlStr += " \nORDER BY COMPANY_CODE";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectValue, addBlank);
    }

    /**
     * ��˾ID������
     * @param selectValue
     * @return
     * @throws QueryException
     */
    public String getOrgnizationOption(String selectValue) throws QueryException {
        return getOrgnizationOption(selectValue);
    }

    /**
     * ��˾ID������
     * @param selectValue
     * @param range       'Y'-TD;'N'-��TD;''-ALL
     * @return
     * @throws QueryException
     */
    public String getOrgnizationOption(int selectValue, String range) throws QueryException {
        boolean addBlank = false;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT ORGANIZATION_ID,COMPANY_CODE || STR_REPLACE(COMPANY, 'OU_', ' ')  COMPANY\n" +
                "  FROM ETS_OU_CITY_MAP\n" +
                "   WHERE ENABLED='Y'\n ";
        if (StrUtil.isNotEmpty(range)) {
            sqlStr += " AND IS_TD=?";
            sqlArgs.add(range);
        }
//        if (userAccount.isProvinceUser() || userAccount.isSysAdmin()) {
        if (userAccount.isProvinceUser() && userAccount.isSysAdmin()) {
            addBlank = true;
        } else {
            sqlStr += " AND ORGANIZATION_ID=?";
            sqlArgs.add(userAccount.getOrganizationId());
        }
        sqlStr += " \nORDER BY COMPANY_CODE";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectValue), addBlank);
    }

    public String getAllOrgnizationOption(String selectValue) throws QueryException {
        boolean addBlank = false;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT ORGANIZATION_ID,COMPANY\n" +
                "  FROM ETS_OU_CITY_MAP\n" +
                "   WHERE ENABLED='Y'\n" +
                "  ORDER BY COMPANY_CODE";

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectValue, addBlank);
    }


    /**
     * ��ȡ�ʲ�ͬ��Ȩ�޹�˾������,�����û�Ա����Ž��й���
     * @param selectValue ��ѡ��˾
     * @param range       ��˾���ͣ�Y-TD��˾��N-���޹�˾
     * @return
     * @throws QueryException
     */
    public String getSynOrgnizationOption(int selectValue, String range) throws QueryException {
        boolean addBlank = false;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EOCM.ORGANIZATION_ID,\n" +
                        "       EOCM.COMPANY_CODE || STR_REPLACE(EOCM.COMPANY, 'OU_', ' ')\n" +
                        "  FROM ETS_MISFA_TRANSACTION_RESP EMTR, ETS_OU_CITY_MAP EOCM\n" +
                        " WHERE EOCM.ENABLED = 'Y'\n" +
                        "   AND EOCM.COMPANY_CODE = EMTR.COMPANY_CODE\n" +
                        "   AND EMTR.EMPLOYEE_NUMBER = ?\n";

        sqlArgs.add(userAccount.getEmployeeNumber());
        if (StrUtil.isNotEmpty(range)) {
            sqlStr += "   AND EOCM.IS_TD = ?\n";
            sqlArgs.add(range);
        }
        addBlank = true;
        sqlStr += " ORDER BY EOCM.COMPANY_CODE";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectValue), addBlank);
    }
    
    //Ĭ�ϱ�ѡ
    public String getSynOrgnizationOption2(int selectValue, String range) throws QueryException {
        boolean addBlank = false;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EOCM.ORGANIZATION_ID,\n" +
                        "       EOCM.COMPANY_CODE || STR_REPLACE(EOCM.COMPANY, 'OU_', ' ')\n" +
                        "  FROM ETS_MISFA_TRANSACTION_RESP EMTR, ETS_OU_CITY_MAP EOCM\n" +
                        " WHERE EOCM.ENABLED = 'Y'\n" +
                        "   AND EOCM.COMPANY_CODE = EMTR.COMPANY_CODE\n" +
                        "   AND EMTR.EMPLOYEE_NUMBER = ?\n";

        sqlArgs.add(userAccount.getEmployeeNumber());
        if (StrUtil.isNotEmpty(range)) {
            sqlStr += "   AND EOCM.IS_TD = ?\n";
            sqlArgs.add(range);
        }
        sqlStr += " ORDER BY EOCM.COMPANY_CODE";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(StrUtil.nullToString(selectValue), addBlank);
    }

    /**
	 * ��ȡ����OU��֯�����б��(SOAͬ��)
	 * @param selectedValue String
	 * @param addBlank      �Ƿ���롰��ѡ��";
	 * @return String
	 */
	public String getSynAllOrganization(String selectedValue, boolean addBlank) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

			String sqlStr =" SELECT EOCM.ORGANIZATION_ID,\n" +
				           "       EOCM.COMPANY_CODE || STR_REPLACE(EOCM.COMPANY, 'OU_', ' ')\n" +
				           "  FROM ETS_MISFA_TRANSACTION_RESP EMTR, ETS_OU_CITY_MAP EOCM\n" +
				           " WHERE EOCM.ENABLED = 'Y'\n" +
				           "   AND EOCM.COMPANY_CODE = EMTR.COMPANY_CODE\n" +
				           "   AND EMTR.EMPLOYEE_NUMBER = ?\n";
			sqlArgs.add(userAccount.getEmployeeNumber());
		if (!(userAccount.isProvinceUser() && userAccount.isSysAdmin())) {
			sqlStr += " AND EOCM.ORGANIZATION_ID = ?";
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

    
    
    

}
