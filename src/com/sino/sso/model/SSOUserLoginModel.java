package com.sino.sso.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * User: zhoujs
 * Date: 2008-7-24
 * Time: 9:21:22
 * Function:�����¼��֤
 * ������ֻ����UID��OA��¼�û�������֤
 * ɽ��������Ա�������֤
 */
public class SSOUserLoginModel {
     public SQLModel getSSOUserLoginModel(SfUserDTO userAccount) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " SU.USER_ID,"
                + " SU.LOGIN_NAME,"
                + " SU.PASSWORD,"
                + " SU.USERNAME,"
                + " SU.WORKNO,"
                + " SU.OFFICETEL,"
                + " SU.FAX,"
                + " SU.MOVETEL,"
                + " SU.DISABLE_DATE,"
                + " SU.EMAIL,"
                + " SU.SORTNO,"
                + " SU.PASSWORD_DATE,"
                + " SU.ORGANIZATION_ID,"
                + " SU.IS_INNER,"
                + " SU.CREATION_DATE,"
                + " SU.CREATED_BY,"
                + " SU.LAST_UPDATE_DATE,"
                + " SU.LAST_UPDATE_BY,"
                + " SU.EMPLOYEE_NUMBER,"
                + " SU.IS_MAINTAIN_USER,"
                + " AMC.COMPANY_ID MAINTAIN_COMPANY,"
                + " AMC.NAME MAINTAIN_COMPANY_NAME,"
                + " AME.EMPLOYEE_ID,"
                + " AME.EMPLOYEE_NUMBER,"
                + " AMD.DEPT_CODE,"
                + " AMD.DEPT_NAME,"
                + " EOCM.COMPANY,"
                + " EOCM.COMPANY_CODE,"
                + " EOCM.BOOK_TYPE_CODE,"
                + " EOCM.BOOK_TYPE_NAME"
                + " FROM"
                + " SF_USER              SU,"
                + " AMS_MIS_EMPLOYEE     AME,"
                + " AMS_MIS_DEPT         AMD,"
                + " ETS_OU_CITY_MAP      EOCM,"
                + " AMS_MAINTAIN_COMPANY AMC"
                + " WHERE"
                + " SU.EMPLOYEE_NUMBER *= AME.EMPLOYEE_NUMBER"
                + " AND AME.DEPT_CODE *= AMD.DEPT_CODE"
                + " AND SU.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                + " AND SU.MAINTAIN_COMPANY *= AMC.COMPANY_ID"
                + " AND dbo.NVL(SU.END_DATE, GETDATE() ) >= GETDATE() "
                + " AND EOCM.ENABLED = 'Y'"
                + " AND UPPER(SU.OA_NAME) = ?";

        sqlArgs.add(userAccount.getOaName());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

     public SQLModel getSSOUserLoginByEmployeeNumberModel(SfUserDTO userAccount) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                        + " SU.USER_ID,"
                        + " SU.LOGIN_NAME,"
                        + " SU.PASSWORD,"
                        + " SU.USERNAME,"
                        + " SU.WORKNO,"
                        + " SU.OFFICETEL,"
                        + " SU.FAX,"
                        + " SU.MOVETEL,"
                        + " SU.DISABLE_DATE,"
                        + " SU.EMAIL,"
                        + " SU.SORTNO,"
                        + " SU.PASSWORD_DATE,"
                        + " SU.ORGANIZATION_ID,"
                        + " SU.IS_INNER,"
                        + " SU.CREATION_DATE,"
                        + " SU.CREATED_BY,"
                        + " SU.LAST_UPDATE_DATE,"
                        + " SU.LAST_UPDATE_BY,"
                        + " SU.EMPLOYEE_NUMBER,"
                        + " SU.IS_MAINTAIN_USER,"
                        + " AMC.COMPANY_ID MAINTAIN_COMPANY,"
                        + " AMC.NAME MAINTAIN_COMPANY_NAME,"
                        + " AME.EMPLOYEE_ID,"
                        + " AME.EMPLOYEE_NUMBER,"
                        + " AMD.DEPT_CODE,"
                        + " AMD.DEPT_NAME,"
                        + " EOCM.COMPANY,"
                        + " EOCM.COMPANY_CODE,"
                        + " EOCM.BOOK_TYPE_CODE,"
                        + " EOCM.BOOK_TYPE_NAME"
                        + " FROM"
                        + " SF_USER              SU,"
                        + " AMS_MIS_EMPLOYEE     AME,"
                        + " AMS_MIS_DEPT         AMD,"
                        + " ETS_OU_CITY_MAP      EOCM,"
                        + " AMS_MAINTAIN_COMPANY AMC"
                        + " WHERE"
                        + " SU.EMPLOYEE_NUMBER = AME.EMPLOYEE_NUMBER"
                        + " AND AME.DEPT_CODE *= AMD.DEPT_CODE"
                        + " AND SU.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                        + " AND SU.MAINTAIN_COMPANY *= AMC.COMPANY_ID"
                        + " AND dbo.NVL(SU.END_DATE, GETDATE() ) >= GETDATE() "
                        + " AND EOCM.ENABLED = 'Y'"
                        + " AND AME.EMPLOYEE_NUMBER = ?";

        sqlArgs.add(userAccount.getEmployeeNumber());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

        /**
     * ��ȡϵͳ�ص���ϸ����
     * @return SQLModel
     */
    public SQLModel getAddrConfigDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " CONFIG_ID,"
                + " CONFIG_TYPE,"
                + " CHECKED_FLAG "
                + " FROM"
                + " AMS_ADDRESS_CONFIG ";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ��ȡ�û�������˾��;�⼰��ص�
     * @param organizationId String
     * @return  SQLModel
     */
    public SQLModel getTmpInvAddressModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " AOA.ADDRESS_ID,"
                + " EO.WORKORDER_OBJECT_NO"
                + " FROM"
                + " AMS_OBJECT_ADDRESS AOA,"
                + " ETS_OBJECT         EO"
                + " WHERE  AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO"
                + " AND AOA.BOX_NO = '0000'"
                + " AND AOA.NET_UNIT = '0000'"
                + " AND EO.ORGANIZATION_ID = ?"
                + " AND EO.OBJECT_CATEGORY = ?";
        sqlArgs.add(organizationId);
        sqlArgs.add(DictConstant.INV_ON_WAY);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
