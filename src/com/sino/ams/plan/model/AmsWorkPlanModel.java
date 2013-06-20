package com.sino.ams.plan.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.plan.dto.AmsWorkPlanDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsWorkPlanModel</p>
 * <p>Description:�����Զ�����SQL��������AmsWorkPlanModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class AmsWorkPlanModel extends BaseSQLProducer {

    private AmsWorkPlanDTO amsWorkPlan = null;
    private SfUserDTO SfUser = null;

    /**
     * ���ܣ������ƻ����� AMS_WORK_PLAN ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsWorkPlanDTO ���β���������
     */
    public AmsWorkPlanModel(SfUserDTO userAccount, AmsWorkPlanDTO dtoParameter) {
        super(userAccount, dtoParameter);
        SfUser = userAccount;
        this.amsWorkPlan = (AmsWorkPlanDTO) dtoParameter;
    }

    /**
     * ���ܣ�����Զ����ɹ����ƻ����� AMS_WORK_PLAN���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = null;
        try {
            sqlStr = "INSERT INTO AMS_WORK_PLAN\n" +
                    "  (PLAN_ID,\n" +
                    "   PLAN_NAME,\n" +
                    "   PLAN_DESC,\n" +
                    "   EXECUTE_USER,\n" +
                    "   PLAN_STATUS,\n" +
                    "   CREATION_DATE,\n" +
                    "   CREATED_BY,\n" +
                    "   EXECUTE_TIME)\n" +
                    "VALUES\n" +
                    "  ( NEWID(), ?, ?, ?, ?, GETDATE(), ?, ?)";

            sqlArgs.add(amsWorkPlan.getPlanName());
            sqlArgs.add(amsWorkPlan.getPlanDesc());
            sqlArgs.add(amsWorkPlan.getExecuteUser());
            amsWorkPlan.setPlanStatus("1");
            sqlArgs.add(amsWorkPlan.getPlanStatus());
            sqlArgs.add(SfUser.getUserId());
           sqlArgs.add(amsWorkPlan.getExecuteTime());
       }
        catch (CalendarException e) {
            e.printLog();
            throw new SQLModelException(e);
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ����ƻ����� AMS_WORK_PLAN���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() throws SQLModelException{
        SQLModel sqlModel = new SQLModel();
        try {
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE AMS_WORK_PLAN\n" +
                "   SET PLAN_NAME        = ?,\n" +
                "       PLAN_DESC        = ?,\n" +
                "       EXECUTE_USER     = ?,\n" +
                "       LAST_UPDATE_DATE = GETDATE(),\n" +
                "       EXECUTE_TIME = ?,\n" +
                "       LAST_UPDATE_BY   = ?\n" +
                " WHERE PLAN_ID = ?";

        sqlArgs.add(amsWorkPlan.getPlanName());
        sqlArgs.add(amsWorkPlan.getPlanDesc());
        sqlArgs.add(amsWorkPlan.getExecuteUser());
        sqlArgs.add(amsWorkPlan.getExecuteTime());
        sqlArgs.add(SfUser.getUserId());
        sqlArgs.add(amsWorkPlan.getPlanId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
             } catch (CalendarException e) {
            e.printLog();
            throw new SQLModelException(e);
        }
        return sqlModel;
    }

    public SQLModel getDataRepealModel(String planId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE AMS_WORK_PLAN SET PLAN_STATUS = 2 WHERE PLAN_ID = ?";
        sqlArgs.add(planId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
   public SQLModel updateStatus(String planId){
       SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE AMS_WORK_PLAN SET PLAN_STATUS = 6 WHERE PLAN_ID = ?";
        sqlArgs.add(planId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
   }
    /**
     * ���ܣ�����Զ����ɹ����ƻ����� AMS_WORK_PLAN����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_WORK_PLAN"
                + " WHERE"
                + " PLAN_ID = ?";
        sqlArgs.add(amsWorkPlan.getPlanId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ����ƻ����� AMS_WORK_PLAN������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " AWP.PLAN_ID,"
                + " AWP.PLAN_NAME,"
                + " AWP.PLAN_DESC,"
                + " AWP.EXECUTE_TIME,"
                + " SU.USER_ID EXECUTE_USER,"
                + " SU.USERNAME EXECUTE_USER_NAME,"
                + " AWP.PLAN_STATUS"
                + " FROM"
                + " AMS_WORK_PLAN AWP,"
                + " SF_USER SU"
                + " WHERE"
                + " SU.USER_ID = AWP.EXECUTE_USER"
                + " AND AWP.PLAN_ID = ?";
        sqlArgs.add(amsWorkPlan.getPlanId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ����ƻ����� AMS_WORK_PLAN����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " PLAN_ID,"
                + " PLAN_NAME,"
                + " PLAN_DESC,"
                + " EXECUTE_USER,"
                + " PLAN_STATUS,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_WORK_PLAN"
                + " WHERE"
                + "( " + SyBaseSQLUtil.isNull() + "  OR PLAN_ID LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR PLAN_NAME LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR PLAN_DESC LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR EXECUTE_USER LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR PLAN_STATUS LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
        sqlArgs.add(amsWorkPlan.getPlanId());
        sqlArgs.add(amsWorkPlan.getPlanId());
        sqlArgs.add(amsWorkPlan.getPlanName());
        sqlArgs.add(amsWorkPlan.getPlanName());
        sqlArgs.add(amsWorkPlan.getPlanDesc());
        sqlArgs.add(amsWorkPlan.getPlanDesc());
        sqlArgs.add(amsWorkPlan.getExecuteUser());
        sqlArgs.add(amsWorkPlan.getExecuteUser());
        sqlArgs.add(amsWorkPlan.getPlanStatus());
        sqlArgs.add(amsWorkPlan.getPlanStatus());
        sqlArgs.add(amsWorkPlan.getCreationDate());
        sqlArgs.add(amsWorkPlan.getCreationDate());
        sqlArgs.add(amsWorkPlan.getCreatedBy());
        sqlArgs.add(amsWorkPlan.getCreatedBy());
        sqlArgs.add(amsWorkPlan.getLastUpdateDate());
        sqlArgs.add(amsWorkPlan.getLastUpdateDate());
        sqlArgs.add(amsWorkPlan.getLastUpdateBy());
        sqlArgs.add(amsWorkPlan.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� executeUser �����ѯ����SQL��
     * ����Զ��������ݹ����ƻ����� AMS_WORK_PLAN��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param executeUser String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByExecuteUserModel(int executeUser) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " PLAN_ID,"
                + " PLAN_NAME,"
                + " PLAN_DESC,"
                + " PLAN_STATUS,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_WORK_PLAN"
                + " WHERE"
                + " EXECUTE_USER = ?";
        sqlArgs.add(executeUser);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� createdBy �����ѯ����SQL��
     * ����Զ��������ݹ����ƻ����� AMS_WORK_PLAN��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param createdBy String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByCreatedByModel(int createdBy) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " PLAN_ID,"
                + " PLAN_NAME,"
                + " PLAN_DESC,"
                + " EXECUTE_USER,"
                + " PLAN_STATUS,"
                + " CREATION_DATE,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_WORK_PLAN"
                + " WHERE"
                + " CREATED_BY = ?";
        sqlArgs.add(createdBy);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� lastUpdateBy �����ѯ����SQL��
     * ����Զ��������ݹ����ƻ����� AMS_WORK_PLAN��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param lastUpdateBy String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByLastUpdateByModel(int lastUpdateBy) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " PLAN_ID,"
                + " PLAN_NAME,"
                + " PLAN_DESC,"
                + " EXECUTE_USER,"
                + " PLAN_STATUS,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE"
                + " FROM"
                + " AMS_WORK_PLAN"
                + " WHERE"
                + " LAST_UPDATE_BY = ?";
        sqlArgs.add(lastUpdateBy);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        if (foreignKey.equals("executeUser")) {
            sqlModel = getDataByExecuteUserModel(amsWorkPlan.getExecuteUser());
        } else if (foreignKey.equals("createdBy")) {
            sqlModel = getDataByCreatedByModel(amsWorkPlan.getCreatedBy());
        } else if (foreignKey.equals("lastUpdateBy")) {
            sqlModel = getDataByLastUpdateByModel(amsWorkPlan.getLastUpdateBy());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ����ƻ����� AMS_WORK_PLANҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AWP.PLAN_ID,\n" +
                "       AWP.PLAN_NAME,\n" +
                "       EFV.VALUE PLAN_STATUS,\n" +
                "       AWP.CREATION_DATE,\n" +
                "       SU.USERNAME EXECUTE_USER,\n" +
                "       SU1.USERNAME CREATED_BY," +
                "       AWP.EXECUTE_TIME, " +
                "       AWP.PLAN_STATUS STATUSID\n" +
                "  FROM AMS_WORK_PLAN AWP, SF_USER SU, SF_USER SU1, ETS_FLEX_VALUES EFV\n" +
                " WHERE SU.USER_ID = AWP.EXECUTE_USER\n" +
                "   AND SU1.USER_ID = AWP.CREATED_BY\n" +
                "   AND AWP.PLAN_NAME LIKE dbo.NVL(?, AWP.PLAN_NAME)\n" +
                "   AND AWP.PLAN_STATUS = dbo.NVL(?, AWP.PLAN_STATUS)\n" +
                "   AND AWP.EXECUTE_USER = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, AWP.EXECUTE_USER)))\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR AWP.CREATION_DATE >= TO_DATE(?, 'YYYY-MM-DD'))\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR AWP.CREATION_DATE < TO_DATE(?, 'YYYY-MM-DD')+1)\n" +
                "   AND EFV.FLEX_VALUE_SET_ID = 18\n" +
                "   AND EFV.CODE = AWP.PLAN_STATUS\n"+
                "   ORDER BY AWP.EXECUTE_TIME DESC";

        sqlArgs.add(amsWorkPlan.getPlanName());
        sqlArgs.add(amsWorkPlan.getPlanStatus());
        sqlArgs.add(amsWorkPlan.getExecuteUser());
        sqlArgs.add(amsWorkPlan.getFromDate());
        sqlArgs.add(amsWorkPlan.getFromDate());
        sqlArgs.add(amsWorkPlan.getToDate());
        sqlArgs.add(amsWorkPlan.getToDate());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}