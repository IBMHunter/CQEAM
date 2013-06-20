package com.sino.traskmoting.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.traskmoting.dto.SfActInfoDTO;

/**
 * <p>
 * Title: SfActInfoModel
 * </p>
 * <p>
 * Description:�����Զ�����SQL��������SfActInfoModel�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 */

public class SfActInfoModel extends BaseSQLProducer {

	private SfUserBaseDTO sfUserBase = null;

	/**
	 * ���ܣ���ת���̣��ڰ���ת��Ϣ SF_ACT_INFO ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            SfActInfoDTO ���β���������
	 */
	public SfActInfoModel(SfUserBaseDTO userAccount, SfActInfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUserBase = userAccount;
	}

	/**
	 * ���ܣ�����Զ�������ת���̣��ڰ���ת��Ϣ SF_ACT_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(HttpServletRequest req) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sctId = req.getParameter("apiId");
		String sqlStr = "SELECT " + " SFACT_TASK_NAME ," + " GET_REALNAMES(SFACT_PICK_USER) SFACT_PICK_USER  ,"
				+ " SFACT_SIGN_DATE , " + " SFACT_COMPLETE_DATE " + " FROM"
				+ " SF_ACT_INFO" + " WHERE" + " SFACT_ACT_ID = ? AND SFACT_TASK_NAME <> 'SPLIT'";
		sqlArgs.add(sctId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������ת���̣��ڰ���ת��Ϣ SF_ACT_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @param req
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel(HttpServletRequest req)
			throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		String projName = req.getParameter("projectName");
		String procName = req.getParameter("procedureName");
		String sfactApplColumn1 = req.getParameter("sfactSortColumn1");
		String sfactApplColumn2 = req.getParameter("sfactSortColumn2");
		String sfactApplColumn3 = req.getParameter("sfactSortColumn3");
		List sqlArgs = new ArrayList();
		try {
			String sqlStr = "SELECT " + " SFACT_ACT_ID , "
					+ " SFACT_TASK_GROUP," + " SFACT_TASK_ROLE,"
					+ " GET_REALNAMES(SFACT_PICK_USER) SFACT_PICK_USER ," + " SFACT_SIGN_DATE,"
					+ " SFACT_APPL_COLUMN_1," + " SFACT_APPL_COLUMN_2,"
					+ " SFACT_APPL_COLUMN_3" + " FROM" + " SF_ACT_INFO"
					+ " WHERE" + " ( " + SyBaseSQLUtil.isNull() + "  OR SFACT_PROJ_NAME LIKE ?)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SFACT_PROC_NAME LIKE ?)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SFACT_APPL_COLUMN_1 LIKE ?)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SFACT_APPL_COLUMN_2 LIKE ?)"
					+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SFACT_APPL_COLUMN_3 LIKE ?)"
                    + " AND SFACT_TASK_NAME <> 'SPLIT'"
                    + " AND SFACT_TASK_NAME <> 'JOIN'"
                    + " order by  SFACT_SIGN_DATE DESC";

			sqlArgs.add(projName);
			sqlArgs.add(projName);
			sqlArgs.add(procName);
			sqlArgs.add(procName);
			sqlArgs.add(sfactApplColumn1);
			sqlArgs.add(sfactApplColumn1);
			sqlArgs.add(sfactApplColumn2);
			sqlArgs.add(sfactApplColumn2);
			sqlArgs.add(sfactApplColumn3);
			sqlArgs.add(sfactApplColumn3);
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		} catch (Exception ex) {

			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ��ȡ��ϸ����
	 * 
	 */
	public SQLModel getPageQueryDetleByID(HttpServletRequest req) {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sfappid = req.getParameter("apiId");
		try {
			String sqlStr = " SELECT " + " SFACT_HANDLER ,"
					+ " SFACT_SIGN_DATE ," + " SFACT_TASK_NAME , "
					+ " SFACT_COMPLETE_DATE " + " FROM" + " SF_ACT_INFO"
					+ " Where" + " SFACT_ACT_ID = ? AND SFACT_TASK_NAME <> 'SPLIT'"
                    + " AND SFACT_TASK_NAME <> 'JOIN'";
            sqlArgs.add(sfappid);
			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlModel;
	}

	/**
	 * 
	 * ���ܣ������û���ȡ�����Ϣ
	 * 
	 * */
	public SQLModel getTraskUserMotingModel() {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		try {
            String sqlStr = "SELECT SAI.SFACT_ACT_ID,"
                    + " SAI.SFACT_TASK_NAME,"
                    + " SAI.SFACT_APPL_COLUMN_1,"
                    + " SAI.SFACT_APPL_COLUMN_2,"
                    + " SAI.SFACT_SIGN_DATE,"
                    + " SAI.SFACT_SIGN_DUE_DATE,"
                    + " SAI.SFACT_DELIVERY_PRIORITY,"
                    + " GET_REALNAMES(SU.LOGIN_NAME) SFACT_PICK_USER,"
                    + " (CASE SAI.SFACT_SIGN_STATUS"
                    + "  WHEN 1 THEN '�ڰ�'"
                    + "  ELSE '�ռ�'"
                    + "  END) AS SFACT_SIGN_STATUS"
                    + " FROM SF_ACT_INFO SAI,"
                    + "      SF_USER     SU"
                    + " WHERE (SAI.SFACT_TASK_NAME <> 'SPLIT')"
                    + " AND (SAI.SFACT_TASK_NAME <> 'JOIN')"
                    + " AND (SAI.SFACT_PICK_USER <> 'SYSTEM')"
                    + " AND ((SAI.SFACT_SIGN_STATUS = '1' AND SFACT_PICK_USER = SU.LOGIN_NAME) OR"
                    + " ((SAI.SFACT_SIGN_STATUS " + SyBaseSQLUtil.isNullNoParam() + "  OR SAI.SFACT_SIGN_STATUS = '0') AND"
                    + "  GROUP_IN_LIST(SAI.SFACT_TASK_USERS, SU.LOGIN_NAME) <> '0'))"
                    + " ORDER  BY SU.LOGIN_NAME,"
                    + "           SAI.SFACT_SIGN_STATUS";
            sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlModel;
	}

	/**
	 * ���ܣ����ݽ�ɫ��ȡ�����Ϣ
	 * 
	 * */
	public SQLModel getTraskRoleMotingModel() {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		try {
			String sqlStr = "SELECT " + " SFACT_ACT_ID ,"
					+ " SFACT_TASK_ROLE  ," 
					+ " SFACT_TASK_NAME ,"
					+ " SFACT_SIGN_DATE ,"
					+ " SFACT_SIGN_DUE_DATE ," 
//					+ " SFACT_COMPLETE_DATE ,"
					+ " SFACT_FROM_DATE ,"
					+ " SFACT_SIGN_STATUS ,"
					+ " SFACT_DELIVERY_PRIORITY ,"
					+ " SFACT_APPL_COLUMN_1"
					+ " FROM " + " SF_ACT_INFO "
                    + " WHERE SFACT_TASK_NAME <> 'SPLIT'"
                    + " AND SFACT_TASK_NAME <> 'JOIN'"
                    + " AND SFACT_PICK_USER <> 'SYSTEM'"
                    + " ORDER BY "
					+ " SFACT_TASK_ROLE ,SFACT_ACT_ID";
			sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlModel;
	}
	/**
	 * ���ܣ����ݳ�ʱ��ɫ��ȡ�����Ϣ
	 * 
	 * */
	public SQLModel getTraskOtRoleMotingModel() {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		try {
			String sqlStr = "SELECT " 
							+ " SFACT_ACT_ID ,"
							+ " SFACT_TASK_ROLE  ,"
							+ " SFACT_TASK_NAME ,"
							+ " SFACT_SIGN_DATE ,"
							+ " SFACT_SIGN_DUE_DATE ," 
//							+ " SFACT_COMPLETE_DATE ,"
							+ " SFACT_FROM_DATE ,"
							+ " SFACT_SIGN_STATUS ,"
							+ " SFACT_DELIVERY_PRIORITY ,"
							+ " SFACT_APPL_COLUMN_1"
							+ " FROM " 
							+ " SF_ACT_INFO " 
							+ " WHERE "
							+ " ((SFACT_SIGN_STATUS = 1 AND SFACT_SIGN_DUE_DATE < GETDATE()) OR (SFACT_SIGN_STATUS <> 1 AND SFACT_COMPLETE_DATE > SFACT_SIGN_DUE_DATE))  "
							+ " AND SFACT_PICK_USER <> 'SYSTEM'"
				            + " AND SFACT_TASK_NAME <> 'SPLIT'"
				            + " AND SFACT_TASK_NAME <> 'JOIN'"
				            + " ORDER BY "
							+ " SFACT_TASK_ROLE ";
	     sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlModel;
	}

	/**
	 * ���ܣ������������ƻ�ȡ�����Ϣ
	 * 
	 * */
	public SQLModel getTraskNameMotingModel() {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		try {
			String sqlStr = "SELECT " 
							+ " SFACT_ACT_ID ,"
                            + " SFACT_PROC_NAME,"
                            + " SFACT_TASK_NAME ,"
							+ " SFACT_TASK_ROLE ,"
							+ " SFACT_APPL_COLUMN_1 ," 
							+ " SFACT_SIGN_STATUS ,"
							+ " SFACT_DELIVERY_PRIORITY ,"
							+ "  GET_REALNAMES(SFACT_PICK_USER) SFACT_PICK_USER  , "
							+ " SFACT_SIGN_DATE ," 
							+ " SFACT_SIGN_DUE_DATE"
                            + " FROM "
							+ " SF_ACT_INFO "
		                    + " WHERE SFACT_TASK_NAME <> 'SPLIT'"
		                    + " AND SFACT_TASK_NAME <> 'JOIN'"
                            + " AND SFACT_PICK_USER <> 'SYSTEM'"
		                    + " ORDER BY "
		                    + " SFACT_TASK_NAME ,"
							+ " SFACT_ACT_ID";
			sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sqlModel;
	}
	/**
	 * ���ܣ����ݳ�ʱ�������ƻ�ȡ�����Ϣ
	 * 
	 * */
	public SQLModel getTraskOtNameMotingModel() {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		try {
			String sqlStr = "SELECT " 
				    + " SFACT_ACT_ID ,"
                    + " SFACT_PROC_NAME,"
                    + " SFACT_TASK_NAME ,"
					+ " SFACT_TASK_ROLE ,"
					+ " SFACT_APPL_COLUMN_1 ," 
					+ " SFACT_SIGN_STATUS ,"
					+ " SFACT_DELIVERY_PRIORITY ,"
					+ " GET_REALNAMES(SFACT_PICK_USER) SFACT_PICK_USER , "
					+ " SFACT_SIGN_DATE ," 
					+ " SFACT_SIGN_DUE_DATE"
                    + " FROM "
					+ " SF_ACT_INFO "
					+ " WHERE  "
					+ " ((SFACT_SIGN_STATUS = 1 AND SFACT_SIGN_DUE_DATE < GETDATE()) OR (SFACT_SIGN_STATUS <> 1 AND SFACT_COMPLETE_DATE > SFACT_SIGN_DUE_DATE))  "
					+ " AND SFACT_PICK_USER <> 'SYSTEM'"
                    + " AND SFACT_TASK_NAME <> 'SPLIT'"
                    + " AND SFACT_TASK_NAME <> 'JOIN'"
                    + " ORDER BY " + " SFACT_TASK_NAME ";
			sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sqlModel;
	}

	/**
	 * ��ȡ��ϸ��Ϣ
	 * 
	 * */
	public SQLModel getPrimaryKeyMointingModel(HttpServletRequest req) {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sctId = req.getParameter("actId");
		try {
            String sqlStr = "SELECT SAP.SFACT_DELIVERY_PRIORITY,"
                    + " (CASE SAP.SFACT_SIGN_STATUS"
                    + "  WHEN 1 THEN GET_REALNAMES(SAP.SFACT_PICK_USER)"
                    + "  ELSE GET_REALNAMES(SAP.SFACT_TASK_USERS)"
                    + "  END) AS SFACT_PICK_USER,"
                    + " SAP.SFACT_TASK_NAME,"
                    + " SAP.SFACT_SIGN_STATUS,"
                    + " SAP.SFACT_COMPLETE_STATUS,"
                    + " SAP.SFACT_SIGN_DATE,"
                    + " SAP.SFACT_SIGN_DUE_DATE,"
                    + " SAP.SFACT_COMPLETE_DATE,"
                    + " SAP.SFACT_ACT_ID"
                    + " FROM SF_ACT_INFO   SAI,"
                    + "      SF_ACT_PROCESS_V SAP"
                    + " WHERE  SAI.SFACT_ACT_ID = ?"
                    + "  AND SAI.SFACT_CASE_ID = SAP.SFACT_CASE_ID"
                    + "  AND ((SAP.SFACT_PICK_USER " + SyBaseSQLUtil.isNullNoParam() + "  And SAP.SFACT_PICK_STATUS = 0) Or SAP.SFACT_PICK_USER <> 'SYSTEM')"
                    + "  AND SAP.SFACT_TASK_NAME <> 'JOIN'"
                    + "  AND SAP.SFACT_TASK_NAME <> 'SPLIT'"
                    + " ORDER  BY SFACT_COMPLETE_STATUS Desc, SFACT_COMPLETE_DATE";
            sqlArgs.add(sctId);
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlModel;
	}
	/**
	 * 
	 * ���ܣ����ݳ�ʱ�û���ȡ�����Ϣ
	 * 
	 * */
	public SQLModel getTraskOtUserMotingModel() {
		// TODO Auto-generated method stub
		SQLModel sqlModel = new SQLModel();
		try {
			String sqlStr = "SELECT "+
			         "  SFACT_ACT_ID, " +
			         "  SFACT_TASK_NAME, "+
			         "  SFACT_APPL_COLUMN_1, "+
		             "  SFACT_APPL_COLUMN_2, "+
		             "  SFACT_SIGN_DATE, "+
		             "  SFACT_SIGN_DUE_DATE, "+
		             "  GET_REALNAMES(SFACT_PICK_USER) SFACT_PICK_USER, "+
		             "  SFACT_SIGN_USER, "+
		             "  SFACT_DELIVERY_PRIORITY, "+
	                 "  (CASE SFACT_SIGN_STATUS"+ 
	                 "  WHEN 1 THEN '�ڰ�'"+
	                 "  ELSE '�ռ�'"+ 
	                 "  END) AS SFACT_SIGN_STATUS"+ 
			         " FROM SF_ACT_INFO "+
			         "  WHERE ((SFACT_SIGN_STATUS = 1 AND SFACT_SIGN_DUE_DATE < GETDATE()) OR (SFACT_SIGN_STATUS <> 1 AND SFACT_COMPLETE_DATE > SFACT_SIGN_DUE_DATE))" +
			         "   AND SFACT_PICK_USER <> 'SYSTEM' "+
	                 " AND SFACT_TASK_NAME <> 'SPLIT'" +
	                 " AND SFACT_TASK_NAME <> 'JOIN'" +
	                 "  ORDER BY SFACT_PICK_USER ";
			sqlModel.setSqlStr(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlModel;
	}

    public SQLModel getTraskEnoteMotingModel() {
        // TODO Auto-generated method stub
        SQLModel sqlModel = new SQLModel();
        try {
            String sqlStr = "SELECT SAI.SFACT_ACT_ID,\n" +
                    "       SAI.SFACT_TASK_NAME,\n" +
                    "       SAI.SFACT_APPL_COLUMN_1,\n" +
                    "       SAI.SFACT_APPL_COLUMN_2,\n" +
                    "       SAI.SFACT_SIGN_DUE_DATE,\n" +
                    "       SAI.SFACT_DELIVERY_PRIORITY,\n" +
                    "       (CASE TEMP_SE.READ WHEN '1' THEN '����'" +
                    "         ELSE 'δ��' END) AS SFACT_SIGN_STATUS,\n" +
                    "       TEMP_SE.FROM_DATE SFACT_SIGN_DATE,\n" +
                    "       GET_REALNAMES(SU.LOGIN_NAME) SFACT_PICK_USER\n" +
//                    "       (CASE SAI.SFACT_SIGN_STATUS\n" +
//                    "         WHEN 1 THEN\n" +
//                    "          '�ڰ�'\n" +
//                    "         ELSE\n" +
//                    "          '�ռ�'\n" +
//                    "       END) AS SFACT_SIGN_STATUS\n" +
                    "FROM   SF_ACT_INFO SAI,\n" +
                    "       SF_USER SU,\n" +
                    "       (SELECT /*+ INDEX_COMBINE(SE, T) */\n" +
                    "         SE.ACT_ID,\n" +
                    "         SE.TO_USER_ID,\n" +
                    "         SE.FROM_USER_ID,\n" +
                    "         SE.READ,\n" +
                    "         SE.FROM_DATE\n" +
                    "        FROM   SF_ENOTE SE,\n" +
                    "               (SELECT /*+ INDEX_COMBINE(SE) */\n" +
                    "                 SE.ACT_ID,\n" +
                    "                 MAX(SE.FROM_DATE) FROM_DATE\n" +
                    "                FROM   SF_ENOTE SE\n" +
                    "                GROUP  BY SE.ACT_ID) T\n" +
                    "        WHERE  SE.ACT_ID = T.ACT_ID\n" +
                    "               AND SE.FROM_DATE = T.FROM_DATE) TEMP_SE\n" +
                    "WHERE  SAI.SFACT_ACT_ID = TEMP_SE.ACT_ID\n" +
                    "       AND TEMP_SE.TO_USER_ID = SU.USER_ID" +
                    " ORDER BY SU.LOGIN_NAME, SAI.SFACT_SIGN_STATUS";

            sqlModel.setSqlStr(sqlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlModel;
    }
}