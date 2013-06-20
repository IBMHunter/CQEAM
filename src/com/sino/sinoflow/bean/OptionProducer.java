package com.sino.sinoflow.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.DatabaseForWeb;
import com.sino.sinoflow.constant.WebAttrConstant;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: �����ƶ�IESϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OptionProducer {
    private Connection conn = null;
    private SfUserBaseDTO userAccount = null;

    public OptionProducer(SfUserBaseDTO userAccount, Connection conn) {
        this.userAccount = userAccount;
        this.conn = conn;
    }

    /**
     * ���ܣ���ȡ���н�ɫ���ɵ������б�
     * @param resId ָ����Դ���
     * @return String
     * @throws QueryException
     */
    public String getAllRoleOption(String resId) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " SR.ROLE_NAME,"
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
                    + " SR.ROLE_Name = SRP.ROLE_Name"
                    + " AND SRP.SYSTEM_ID = ?"
                    + ")";
            sqlArgs.add(resId);
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml();
    }

    /**
     * ���ܣ���ȡ�ܷ���ָ����Դ�Ľ�ɫ���ɵ������б�
     * @param resourceId String
     * @return String
     * @throws QueryException
     */
    public String getViewRoleOption(String resourceId) throws QueryException {
    	 SQLModel sqlModel = new SQLModel();
         List sqlArgs = new ArrayList();
         
 	      String sqlStr = "SELECT"
 	      + " SRP.ROLE_NAME,"
 	      + " SRP.ROLE_NAME"
 	      + " FROM"
 	      + " SF_RES_PRIVS SRP,"
 	      + " SF_RES_DEFINE SRD"
 	      + " WHERE"
 	      + " SRP.SYSTEM_ID = SRD.SYSTEM_ID"
 	      + " AND SRD.RES_ID = ?";
         	
         sqlModel.setSqlStr(sqlStr);
         sqlArgs.add(resourceId);
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
        return getAllGroup(selectedGroup, "", false, true);
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

    public String getAllGroup(String selectedGroup, String organizationId, boolean onlySelf, boolean addBlank) throws
            QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT" +
                " GROUP_ID," +
                " GROUPNAME " +
                " FROM SF_GROUP " +
                " WHERE ((IS_DESIGNER % 2) = 0 OR IS_DESIGNER = '')";
        if (onlySelf) {
            sqlStr += " AND GROUP_ID=?";
            sqlArgs.add(selectedGroup);
        } else {
            if (StrUtil.nullToString(organizationId).equals("")) {
                if (!userAccount.isSysAdmin()) {
                    sqlStr += " AND ORGANIZATION=?";
                    sqlArgs.add(userAccount.getOrganizationId());
                }
            } else {
                sqlStr += " AND ORG_ID=?";
                sqlArgs.add(organizationId);
            }
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedGroup, addBlank);
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
     * ѡ��ָ��������û�
     * @param groupId      String
     * @param selectedUser �Ƿ�ѡ��ָ���û�
     * @return String
     * @throws QueryException
     */
    public String getUsersOfGroup(String groupId, String selectedUser) throws QueryException {
        return getUsersOfGroup(groupId, selectedUser, true);
    }

    public String getUsersOfGroup(String groupName, String selectedUser, boolean addBlank) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT DISTINCT SU.USER_ID, SU.LOGIN_NAME" +
                "  FROM SF_USER_AUTHORITY SUA, SF_USER SU" +
                " WHERE SU.USER_ID = SUA.USER_ID" +
                "   AND SUA.GROUP_NAME = ?" +
                "   AND SU.DISABLE_DATE = ''" +
                " ORDER BY LOGIN_NAME";

        sqlArgs.add(groupName);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedUser, addBlank);
    }

    public String getMenuValue(String selectedMenu) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT SRD.RES_ID, SRD.RES_NAME" +
                        "  FROM SF_RES_DEFINE SRD" +
                        " WHERE SRD.RES_PAR_ID = ''" +
                        "   AND EXISTS (SELECT NULL" +
                        "          FROM SF_RES_PRIVS SRP, SF_USER_AUTHORITY SUA" +
                        "         WHERE SRP.ROLE_NAME = SUA.ROLE_NAME" +
                        "           AND SRP.SYSTEM_ID = SRD.SYSTEM_ID" +
                        "           AND SUA.USER_ID = ?)" +
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
        String sqlStr = "SELECT SRD.SYSTEM_ID,SRD.RES_NAME" +
                "  FROM SF_RES_DEFINE SRD" +
                " WHERE SRD.RES_PAR_ID IS NOT NULL" +
                "   AND SRD.RES_URL IS NOT NULL" +
                "   AND EXISTS (SELECT NULL" +
                "          FROM SF_RES_PRIVS SRP, SF_USER_AUTHORITY SUA" +
                "         WHERE SRP.ROLE_NAME = SUA.ROLE_NAME" +
                "           AND SRP.SYSTEM_ID = SRD.SYSTEM_ID" +
                "           AND SUA.USER_ID = ?)" +
                "   AND SRD.RES_PAR_ID LIKE ? + '.%'" +
                " ORDER BY SRD.RES_ID, SRD.RES_PAR_ID";
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(resParId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedMenu, false);
    }

    /**
     * ������������б�
     * @param selectedInv String
     * @return String
     * @throws QueryException
     */
    public String getFlowGroupOption(String selectedInv) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SG.GROUP_ID, SG.GROUPNAME" +
                "  FROM SF_GROUP SG" +
                " WHERE SG.IS_DESIGNER = 1";
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
        String sqlStr = " SELECT 'N' CODE , '��Ч' VALUE  " +
        "UNION" +
        "SELECT 'Y' CODE, 'ʧЧ' VALUE ";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedInv, false);
    }

    /**
     * ���ܣ�������������б�
     * @param orgId       String
     * @param selectedInv String
     * @return String
     * @throws QueryException
     */
    public String getGroupOption(String orgId, String selectedInv) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SG.GROUP_ID, SG.GROUPNAME" +
                "  FROM SF_GROUP SG" +
                " WHERE SG.IS_DESIGNER = '0'" +
                " AND SG.ENABLED = 'Y'" +
                " AND SG.ORG_ID = ?";
        sqlArgs.add(orgId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedInv, true);
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
                + " SR.ROLE_NAME,"
                + " SR.ROLE_NAME"
                + " FROM"
                + " SF_ROLE SR";
        sqlModel.setSqlStr(sqlStr);
        DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
        return webFieldProducer.getOptionHtml(selectedValue, true);
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
                + " SG.GROUPNAME"
                + " FROM"
                + " SF_GROUP SG"
                + " WHERE"
                + " ((SG.IS_DESIGNER % 2) = 0 OR SG.IS_DESIGNER = '')"
                + " AND SG.ENABLED = 'Y'"
                + " AND SG.ORGANIZATION = ?";
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
        return optProducer.getOptionHtml(selectedValue, true);
    }
}
