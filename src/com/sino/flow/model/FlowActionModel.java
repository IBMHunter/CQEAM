package com.sino.flow.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.db.sql.model.SQLModel;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-11-9
 * Time: 17:35:05
 */
public class FlowActionModel {
    //ȡ�û��ĵ绰����
    public SQLModel getPhoneModel(int userId, String sendType) {
        String sql = "SELECT SUP.MSG_CELL_PHONE\n" +
                "  FROM SF_USER_PHONE SUP\n" +
                " WHERE SUP.USER_ID = ?\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR SUP.SEND_TYPE = ?)";
        ArrayList al = new ArrayList();
        al.add(userId);
        al.add(sendType);
        al.add(sendType);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ȡ�û��Ĵ�����
    public SQLModel getAgent(String userId, String procId) {
        String sql = "SELECT SFA.AGENT_USER_ID\n" +
                "  FROM SF_FLOW_AGENT SFA\n" +
                " WHERE SFA.USER_ID = ?\n" +
                "   AND (GETDATE() BETWEEN SFA.ACTIVE_START_DATE AND SFA.ACTIVE_END_DATE)\n" +
                "   AND SFA.DISABLE_DATE " + SyBaseSQLUtil.isNullNoParam() + " \n" +
                "   AND (SFA.PROC_ID " + SyBaseSQLUtil.isNullNoParam() + "  OR SFA.PROC_ID = ?)";
        ArrayList al = new ArrayList();
        al.add(userId);
        al.add(procId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ȡ���̼�¼�ĵ�ǰ��Ϣ
    public SQLModel getSfAct(String actId) {
        String sql = "SELECT * FROM SF_ACT SA WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ȡ���̵Ŀ�ʼ�ڵ㣬��������
    public SQLModel getBeginTaskUser(String actId) {
        String sql = " SELECT SF_FLOW_PKG.GET_BEGIN_TASK(SA.PROC_ID) BEGIN_TASK, SA.CREATED_BY\n" +
                "      INTO L_FIRST_TASK, L_FIRST_USER\n" +
                "      FROM SF_ACT SA\n" +
                "     WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //�����ύʱ��ȷ���Ƿ�Ϊ�ظ��ύ������ס������¼
    public SQLModel lockAct(String actId, String sessionUserId) {
        String sql = " UPDATE SF_ACT SA SET SA.LOCKED_BY = ?, SA.LOCKED_DATE = GETDATE()\n" +
                " WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(sessionUserId);
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;
    }

    //�����ظ��ύ�����ɺ������
    public SQLModel clearLock(String actId) {
        String sql = " UPDATE SF_ACT SA SET SA.LOCKED_BY = NULL, SA.LOCKED_DATE =NULL\n" +
                " WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    /**
     * ȡ��һ������ĸ����������������1����Ӧ�ü����������͡�
     *
     */
    public SQLModel getNextTaskCount(String curTaskId) {
        String sql =
                "SELECT COUNT(*) TOTAL\n" +
                        "  FROM SF_FLOW_DEFINE SFD\n" +
                        " WHERE SFD.FROM_TASK_ID =?\n" +
                        "   AND SFD.DISABLED = 'N' ";
        ArrayList al = new ArrayList();
        al.add(curTaskId);
        SQLModel sm = new SQLModel();
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;
    }

    //ȡtaskInfo by id
    public SQLModel getTaskInfoByTaskId(String taskId) {
        String sql = "SELECT * FROM SF_TASK_DEFINE STD WHERE STD.TASK_ID = ?";
        ArrayList al = new ArrayList();
        al.add(taskId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //����Ӧ�÷���Ťת��¼
    public SQLModel getSfActByApply(String applyId, String appTableName) {
        String sql = "SELECT SA.*\n" +
                "  FROM SF_ACT SA, SF_PROCEDURE_DEF SPD\n" +
                " WHERE SA.PROC_ID = SPD.PROC_ID\n" +
                "   AND SPD.APP_TABLE_NAME = ?\n" +
                "   AND SA.APP_ID = ?";
        ArrayList al = new ArrayList();
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //����Ӧ�ò���Ӧ�õ�ǰ���ڵĽڵ���Ϣ
    public SQLModel getCurTaskInfoByApp(String applyId, String appTableName) {
        String sql = "SELECT STD.*\n" +
                "  FROM SF_ACT SA, SF_PROCEDURE_DEF SPD, SF_TASK_DEFINE STD\n" +
                " WHERE SA.PROC_ID = SPD.PROC_ID\n" +
                "   AND SA.CUR_TASK_ID = STD.TASK_ID\n" +
                "   AND SPD.APP_TABLE_NAME = ?\n" +
                "   AND SA.APP_ID = ?";
        ArrayList al = new ArrayList();
        al.add(appTableName);
        al.add(applyId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ĳӦ���Ƿ��Ѿ��߹�����
    public SQLModel isAddedModel(String procId, String applyId) {
        String sql = "SELECT *\n" +
                "  FROM SF_ACT SA\n" +
                " WHERE SA.PROC_ID = ?\n" +
                "   AND SA.APP_ID = ?";
        ArrayList al = new ArrayList();
        al.add(procId);
        al.add(applyId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ͨ��������ȡ������Ϣ
    public SQLModel getProcByName(String procName) {
        String sql = "SELECT * FROM SF_PROCEDURE_DEF SPD WHERE SPD.PROC_NAME = ?";
        ArrayList al = new ArrayList();
        al.add(procName);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ȡĳһ���̵ĵ�һ���ڵ�
    public SQLModel getFirstTask(String procId) {   //2009.4.1�޸ģ�su��
        String sql = "SELECT *\n" +
                "  FROM SF_TASK_DEFINE STD\n" +
                " WHERE STD.PROC_ID = ?\n" +
                "   AND STD.TASK_PROP = 1 AND STD.DISABLED = 'N'";
        ArrayList al = new ArrayList();
        al.add(procId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //�ж�ĳ���ڵ��Ƿ�Ϊ��һ���ڵ�
    public SQLModel getTaskProp(String taskId) {
        String sql = "SELECT STD.TASK_PROP FROM SF_TASK_DEFINE STD WHERE STD.TASK_ID = ?";
        ArrayList al = new ArrayList();
        al.add(taskId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    /**
     * ������������ȡ����Ϣ
     * @param fromTaskId
     * @param toTaskId
     * @return
     */
    public SQLModel getFlowDefine(String fromTaskId,String toTaskId){
        SQLModel sqlModel=new SQLModel();
        List sqlArgs=new ArrayList();
        String sqlStr = "SELECT *\n" +
                "  FROM SF_FLOW_DEFINE SFD\n" +
                " WHERE SFD.FROM_TASK_ID = ?\n" +
                "   AND SFD.TO_TASK_ID = ?\n" +
                "   AND SFD.DISABLED = 'N'";
        sqlArgs.add(fromTaskId);
        sqlArgs.add(toTaskId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

  
}
