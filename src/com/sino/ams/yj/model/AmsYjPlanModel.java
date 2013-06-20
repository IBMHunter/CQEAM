package com.sino.ams.yj.model;

import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.yj.dto.AmsYjPlanDTO;

/**
 * <p>Title: AmsYjPlanModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjPlanModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ��Ԥ����ϵά��
 */

public class AmsYjPlanModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjPlanDTO ���β���������
     */
    public AmsYjPlanModel(SfUserDTO userAccount, AmsYjPlanDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjPlanDTO amsYjPlan = (AmsYjPlanDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
                    + " AMS_YJ_PLAN("
                    + " PLAN_ID,"
                    + " PLAN_LEVEL,"
                    + " PRO_CITY,"
                    + " PLAN_NAME,"
                    + " PLAN_NO,"
                    + " PLAN_TYPE,"
                    + " PRINT_DATE,"
                    + " KNOW_POST,"
                    + " LEADER_POST,"
                    + " IS_DRILL,"
                    + " REMARK,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " QUANTITY"
                    + ") VALUES ("
                    + " CONVERT(FLOAT,?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?)";

            sqlArgs.add(amsYjPlan.getPlanId());
            sqlArgs.add(amsYjPlan.getPlanLevel());
            sqlArgs.add(amsYjPlan.getProCity());
            sqlArgs.add(amsYjPlan.getPlanName());
            sqlArgs.add(amsYjPlan.getPlanNo());
            sqlArgs.add(amsYjPlan.getPlanType());
            sqlArgs.add(amsYjPlan.getPrintDate());
            sqlArgs.add(amsYjPlan.getKnowPost());
            sqlArgs.add(amsYjPlan.getLeaderPost());
            sqlArgs.add(amsYjPlan.getIsDrill());
            sqlArgs.add(amsYjPlan.getRemark());
            sqlArgs.add(amsYjPlan.getOrganizationId());
            sqlArgs.add(sfUser.getUserId());
            sqlArgs.add(amsYjPlan.getQuantity());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjPlanDTO amsYjPlan = (AmsYjPlanDTO) dtoParameter;
            String sqlStr = "UPDATE AMS_YJ_PLAN"
                    + " SET"
                    + " PLAN_LEVEL = ?,"
                    + " PRO_CITY = ?,"
                    + " PLAN_NAME = ?,"
                    + " PLAN_NO = ?,"
                    + " PLAN_TYPE = ?,"
                    + " PRINT_DATE = ?,"
                    + " KNOW_POST = ?,"
                    + " LEADER_POST = ?,"
                    + " IS_DRILL = ?,"
                    + " REMARK = ?,"
                    + " ORGANIZATION_ID = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " LAST_UPDATE_USER = ?,"
                    + " QUANTITY = ?"
                    + " WHERE"
                    + " PLAN_ID = CONVERT(FLOAT,?)";

            sqlArgs.add(amsYjPlan.getPlanLevel());
            sqlArgs.add(amsYjPlan.getProCity());
            sqlArgs.add(amsYjPlan.getPlanName());
            sqlArgs.add(amsYjPlan.getPlanNo());
            sqlArgs.add(amsYjPlan.getPlanType());
            sqlArgs.add(amsYjPlan.getPrintDate());
            sqlArgs.add(amsYjPlan.getKnowPost());
            sqlArgs.add(amsYjPlan.getLeaderPost());
            sqlArgs.add(amsYjPlan.getIsDrill());
            sqlArgs.add(amsYjPlan.getRemark());
            sqlArgs.add(amsYjPlan.getOrganizationId());
            sqlArgs.add(sfUser.getUserId());
            sqlArgs.add(amsYjPlan.getQuantity());
            sqlArgs.add(amsYjPlan.getPlanId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjPlanDTO amsYjPlan = (AmsYjPlanDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_YJ_PLAN"
                + " WHERE"
                + " PLAN_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjPlan.getPlanId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjPlanDTO amsYjPlan = (AmsYjPlanDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " PLAN_ID,"
                + " PLAN_LEVEL,"
                + " PRO_CITY,"
                + " PLAN_NAME,"
                + " PLAN_NO,"
                + " PLAN_TYPE,"
                + " PRINT_DATE,"
                + " KNOW_POST,"
                + " LEADER_POST,"
                + " IS_DRILL,"
                + " REMARK,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER,"
                + " QUANTITY"
                + " FROM"
                + " AMS_YJ_PLAN"
                + " WHERE"
                + " PLAN_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjPlan.getPlanId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjPlanDTO amsYjPlan = (AmsYjPlanDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " PLAN_ID,"
                    + " PLAN_LEVEL,"
                    + " PRO_CITY,"
                    + " PLAN_NAME,"
                    + " PLAN_NO,"
                    + " PLAN_TYPE,"
                    + " PRINT_DATE,"
                    + " KNOW_POST,"
                    + " LEADER_POST,"
                    + " IS_DRILL,"
                    + " REMARK,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_USER,"
                    + " QUANTITY"
                    + " FROM"
                    + " AMS_YJ_PLAN"
                    + " WHERE"
                   // + " (? IS NULL OR PLAN_ID LIKE ?)"
//                    + "  ("+ SyBaseSQLUtil.isNull() +" OR PLAN_LEVEL LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PRO_CITY LIKE ?)"
                    + "  ("+ SyBaseSQLUtil.isNull() +" OR PLAN_NAME LIKE ?)"   //
                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PLAN_NO LIKE ?)"    //
                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PLAN_TYPE LIKE ?)"  //
//                    + " AND (? IS NULL OR PRINT_DATE LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR KNOW_POST LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR LEADER_POST LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR IS_DRILL LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR REMARK LIKE ?)"
                    + " AND (? =-1 OR ORGANIZATION_ID =?)" ;    //
//                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR CREATE_USER LIKE ?)"
//                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR LAST_UPDATE_USER LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR QUANTITY LIKE ?)";
           // sqlArgs.add(amsYjPlan.getPlanId());
           // sqlArgs.add(amsYjPlan.getPlanId());
//            sqlArgs.add(amsYjPlan.getPlanLevel());
//            sqlArgs.add(amsYjPlan.getPlanLevel());
//            sqlArgs.add(amsYjPlan.getProCity());
//            sqlArgs.add(amsYjPlan.getProCity());
            sqlArgs.add(amsYjPlan.getPlanName());
            sqlArgs.add(amsYjPlan.getPlanName());
            sqlArgs.add(amsYjPlan.getPlanNo());
            sqlArgs.add(amsYjPlan.getPlanNo());
            sqlArgs.add(amsYjPlan.getPlanType());
            sqlArgs.add(amsYjPlan.getPlanType());
//            sqlArgs.add(amsYjPlan.getPrintDate());
//            sqlArgs.add(amsYjPlan.getPrintDate());
//            sqlArgs.add(amsYjPlan.getKnowPost());
//            sqlArgs.add(amsYjPlan.getKnowPost());
//            sqlArgs.add(amsYjPlan.getLeaderPost());
//            sqlArgs.add(amsYjPlan.getLeaderPost());
//            sqlArgs.add(amsYjPlan.getIsDrill());
//            sqlArgs.add(amsYjPlan.getIsDrill());
//            sqlArgs.add(amsYjPlan.getRemark());
//            sqlArgs.add(amsYjPlan.getRemark());
            sqlArgs.add(amsYjPlan.getOrganizationId());
            sqlArgs.add(amsYjPlan.getOrganizationId());
//            sqlArgs.add(amsYjPlan.getCreationDate());
//            sqlArgs.add(amsYjPlan.getCreationDate());
//            sqlArgs.add(amsYjPlan.getCreateUser());
//            sqlArgs.add(amsYjPlan.getCreateUser());
//            sqlArgs.add(amsYjPlan.getLastUpdateDate());
//            sqlArgs.add(amsYjPlan.getLastUpdateDate());
//            sqlArgs.add(amsYjPlan.getLastUpdateUser());
//            sqlArgs.add(amsYjPlan.getLastUpdateUser());
//            sqlArgs.add(amsYjPlan.getQuantity());
//            sqlArgs.add(amsYjPlan.getQuantity());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��Ԥ����ϵ�� AMS_YJ_PLANҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjPlanDTO amsYjPlan = (AmsYjPlanDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " dbo.APP_GET_ORGNIZATION_NAME(ORGANIZATION_ID) ORGANIZATION_NAME,"
                    + " PLAN_ID,"
                    + " PLAN_NAME,"
                    + " PLAN_LEVEL,"
                    + " PRO_CITY,"
                    + " PLAN_NO,"
                    + " PLAN_TYPE,"
                    + " PRINT_DATE,"
                    + " KNOW_POST,"
                    + " QUANTITY,"
                    + " LEADER_POST,"
                    + " IS_DRILL,"
                    + " REMARK,"
                    + " CREATION_DATE,"
                    + " dbo.APP_GET_USER_NAME(CREATE_USER) CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " dbo.APP_GET_USER_NAME(LAST_UPDATE_USER) LAST_UPDATE_USER"
                    + " FROM"
                    + " AMS_YJ_PLAN"
                    + " WHERE"
                   // + " (? IS NULL OR PLAN_ID LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PLAN_LEVEL LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PRO_CITY LIKE ?)"
                    + "  ("+ SyBaseSQLUtil.isNull() +" OR PLAN_NAME LIKE ?)"
                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PLAN_NO LIKE ?)"
                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR PLAN_TYPE LIKE ?)"
                    + " AND  PRINT_DATE >= dbo.NVL(?, PRINT_DATE)"
                    + " AND  PRINT_DATE <= dbo.NVL(?, PRINT_DATE)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR KNOW_POST LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR LEADER_POST LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR IS_DRILL LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR REMARK LIKE ?)"
                    + " AND (? =-1  OR ORGANIZATION_ID =?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR CREATION_DATE LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR CREATE_USER LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR LAST_UPDATE_DATE LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR LAST_UPDATE_USER LIKE ?)"
//                    + " AND ("+ SyBaseSQLUtil.isNull() +" OR QUANTITY LIKE ?)"
                    + " ORDER BY \n" +                    
                    "ORGANIZATION_ID,\n" +
                    "PRINT_DATE";
//            sqlArgs.add(amsYjPlan.getPlanId());
//            sqlArgs.add(amsYjPlan.getPlanId());
//            sqlArgs.add(amsYjPlan.getPlanLevel());
//            sqlArgs.add(amsYjPlan.getPlanLevel());
//            sqlArgs.add(amsYjPlan.getProCity());
//            sqlArgs.add(amsYjPlan.getProCity());
            sqlArgs.add(amsYjPlan.getPlanName());
            sqlArgs.add(amsYjPlan.getPlanName());
            sqlArgs.add(amsYjPlan.getPlanNo());
            sqlArgs.add(amsYjPlan.getPlanNo());
            sqlArgs.add(amsYjPlan.getPlanType());
            sqlArgs.add(amsYjPlan.getPlanType());
            sqlArgs.add(amsYjPlan.getStartDate());
            sqlArgs.add(amsYjPlan.getEndDate());
//            sqlArgs.add(amsYjPlan.getKnowPost());
//            sqlArgs.add(amsYjPlan.getKnowPost());
//            sqlArgs.add(amsYjPlan.getLeaderPost());
//            sqlArgs.add(amsYjPlan.getLeaderPost());
//            sqlArgs.add(amsYjPlan.getIsDrill());
//            sqlArgs.add(amsYjPlan.getIsDrill());
//            sqlArgs.add(amsYjPlan.getRemark());
//            sqlArgs.add(amsYjPlan.getRemark());
            sqlArgs.add(amsYjPlan.getOrganizationId());
            sqlArgs.add(amsYjPlan.getOrganizationId());
//            sqlArgs.add(amsYjPlan.getCreationDate());
//            sqlArgs.add(amsYjPlan.getCreationDate());
//            sqlArgs.add(amsYjPlan.getCreateUser());
//            sqlArgs.add(amsYjPlan.getCreateUser());
//            sqlArgs.add(amsYjPlan.getLastUpdateDate());
//            sqlArgs.add(amsYjPlan.getLastUpdateDate());
//            sqlArgs.add(amsYjPlan.getLastUpdateUser());
//            sqlArgs.add(amsYjPlan.getLastUpdateUser());
//            sqlArgs.add(amsYjPlan.getQuantity());
//            sqlArgs.add(amsYjPlan.getQuantity());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (Exception ex) {
			ex.getStackTrace();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}
        public SQLModel getPlanStat() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT dbo.APP_GET_ORGNIZATION_NAME(AYP.ORGANIZATION_ID) ORGANIZATION_NAME,\n" +
                        " (SELECT COUNT(1)\n" +
                        "FROM   AMS_YJ_PLAN AYP1\n" +
                        "WHERE  AYP1.ORGANIZATION_ID = AYP.ORGANIZATION_ID\n" +
                        " AND    AYP1.PLAN_LEVEL = '����Ԥ��') ZT,\n" +
                        "  (SELECT COUNT(1)\n" +
                        " FROM   AMS_YJ_PLAN AYP2\n" +
                        " WHERE  AYP2.ORGANIZATION_ID = AYP.ORGANIZATION_ID\n" +
                        " AND    AYP2.PLAN_LEVEL = 'ר��Ԥ��') ZX,\n" +
                        "  (SELECT COUNT(1)\n" +
                        " FROM   AMS_YJ_PLAN AYP3\n" +
                        " WHERE  AYP3.ORGANIZATION_ID = AYP.ORGANIZATION_ID\n" +
                        " AND    AYP3.PLAN_LEVEL = 'Ӧ������') YJ,\n" +
                        "  0\n" +
                        "FROM   AMS_YJ_PLAN AYP\n" +
                        "WHERE  (? =-1 OR AYP.ORGANIZATION_ID = ?)\n" +
                        "GROUP  BY AYP.ORGANIZATION_ID\n" +
                        "\n" +
                        "UNION ALL\n" +
                        "SELECT '�ϼ�' ORGANIZATION_NAME,\n" +
                        "  dbo.YEP_GET_ORG_PLANS_ZT(?),\n" +
                        "  dbo.YEP_GET_ORG_PLANS_ZX(?),\n" +
                        "  dbo.YEP_GET_ORG_PLANS_YJ(?),\n" +
                        "  COUNT(1)\n" +
                        "FROM   AMS_YJ_PLAN AYP\n" +
                        "WHERE  (? =-1 OR AYP.ORGANIZATION_ID = ?)";


        AmsYjPlanDTO planDTO=(AmsYjPlanDTO)dtoParameter;
        sqlArgs.add(planDTO.getOrganizationId());
        sqlArgs.add(planDTO.getOrganizationId());
        sqlArgs.add(planDTO.getOrganizationId());
        sqlArgs.add(planDTO.getOrganizationId());
        sqlArgs.add(planDTO.getOrganizationId());
        sqlArgs.add(planDTO.getOrganizationId());
        sqlArgs.add(planDTO.getOrganizationId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
}