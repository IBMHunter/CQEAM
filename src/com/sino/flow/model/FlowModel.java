package com.sino.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.UploadException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.flow.constant.FlowConstant;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-19
 * Time: 11:48:10
 */
public class FlowModel {
    private HttpServletRequest req;
    private String applyId; //Ӧ��ID
    private String applyNumber = "";//Ӧ�ñ��
    private RequestParser parser;
    private String procId; //����ID
    private int userId; //��ǰ������û�ID
    private String actId;
    private String currTaskId = ""; //��ǰ�ڵ�ID
    private String procName = "";

    public FlowModel(HttpServletRequest req, String applyId, String applyNumber, int userId) throws UploadException {
        this.req = req;
        this.applyId = applyId;
        this.applyNumber = applyNumber;
        this.parser = new RequestParser();
        parser.transData(req);
        this.procId = parser.getParameter("procId");
        this.currTaskId = parser.getParameter("currTaskId");
        this.userId = userId;
    }

    public String getCurrTaskId() {
        return currTaskId;
    }

    public void setCurrTaskId(String currTaskId) {
        this.currTaskId = currTaskId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    /**
     * ��ACT���в����¼
     *
     * @throws UploadException
     */
    public SQLModel getAddActModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr =
                "INSERT INTO SF_ACT\n" +
                        "  (ACTID,\n" +
                        "   PROC_ID,\n" +
                        "   PROC_NAME,\n" +
                        "   CUR_TASK_ID,\n" +
                        "   CUR_USERID,\n" +
                        "   CUR_STATUS,\n" +
                        "   CUR_TASK_POSITION_ID,\n" +
                        "   FROM_DT,\n" +
                        "   FROM_TASK_ID,\n" +
                        "   FROM_USER_ID,\n" +
                        "   COMPLETE_DT,\n" +
                        "   COMPLETE_STATUS,\n" +
                        "   COMPLETE_USER,\n" +
                        "   NEXT_TASK_ID,\n" +
                        "   NEXT_USER_ID,\n" +
                        "   CREATION_DATE,\n" +
                        "   CREATED_BY,\n" +
                        "   HANDLE_USER,\n" +
                        "   DEPT_ID,\n" +
                        "   APP_ID,\n" +
                        "   APPLY_NUMBER,\n" +
                        "   AGENT_USER_ID)\n" +
                        "VALUES\n" +
                        "  (?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   SF_FLOW_PKG.GET_POSITION_ID_BY_USER_ID(?),\n" +
                        "   GETDATE(),\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   GETDATE(),\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   GETDATE(),\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?)";

        List sqlArgs = new ArrayList();
        sqlArgs.add(actId);
        sqlArgs.add(procId);
        sqlArgs.add(procName);
        sqlArgs.add(currTaskId);
        sqlArgs.add(userId);//��ǰ�û���
        sqlArgs.add(FlowConstant.PROC_STATUS_NORMAL);
        sqlArgs.add(userId);//ͨ��userIdȡְλ
        sqlArgs.add("-1");   //fromtaskid
        sqlArgs.add("-1");  //fromuserid
        sqlArgs.add(FlowConstant.TASK_STATUS_FINISHED);
        sqlArgs.add(userId);//complete user
        sqlArgs.add(parser.getParameter("nextTaskId"));
        //sqlArgs.add(parser.getParameter("nextUserId"));
        sqlArgs.add("");//��һ���û�����ʱ��û��������ȷ��
        sqlArgs.add(userId);//created by
        sqlArgs.add("");//handle user
        sqlArgs.add(parser.getParameter("currDeptId"));
        sqlArgs.add(applyId);
        sqlArgs.add(applyNumber);
        sqlArgs.add("");//agent userId
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ��һ�α���1
     *
     * @return
     * @throws UploadException
     */
    public SQLModel getSfActLogCreateModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr =
                "INSERT INTO SF_ACT_LOG\n" +
                        "  (LOG_ID,\n" +
                        "   ACTID,\n" +
                        "   PROC_ID,\n" +
                        "   PROC_NAME,\n" +
                        "   CUR_TASK_ID,\n" +
                        "   CUR_USERID,\n" +
                        "   CUR_STATUS,\n" +
                        "   CUR_TASK_POSITION_ID,\n" +
                        "   FROM_DT,\n" +
                        "   FROM_TASK_ID,\n" +
                        "   FROM_USER_ID,\n" +
                        "   COMPLETE_DT,\n" +
                        "   COMPLETE_STATUS,\n" +
                        "   COMPLETE_USER,\n" +
                        "   NEXT_TASK_ID,\n" +
                        "   NEXT_USER_ID,\n" +
                        "   CREATION_DATE,\n" +
                        "   CREATED_BY,\n" +
                        "   DEPT_ID,\n" +
                        "   APP_ID,\n" +
                        "   APPLY_NUMBER,\n" +
                        "   AGENT_USER_ID,\n" +
                        "   FLOW_CODE)\n" +
                        "  (SELECT  NEWID() ,\n" +
                        "          SA.ACTID,\n" +
                        "          SA.PROC_ID,\n" +
                        "          SA.PROC_NAME,\n" +
                        "          SA.CUR_TASK_ID,\n" +
                        "          SA.CUR_USERID,\n" +
                        "          SA.CUR_STATUS,\n" +
                        "          SA.CUR_TASK_POSITION_ID,\n" +
                        "          SA.FROM_DT,\n" +
                        "          SA.FROM_TASK_ID,\n" +
                        "          SA.FROM_USER_ID,\n" +
                        "          SA.COMPLETE_DT,\n" +
                        "          SA.COMPLETE_STATUS,\n" +
                        "          SA.COMPLETE_USER,\n" +
                        "          SA.NEXT_TASK_ID,\n" +
                        "          SA.NEXT_USER_ID,\n" +
                        "          SA.CREATION_DATE,\n" +
                        "          SA.CREATED_BY,\n" +
                        "          SA.DEPT_ID,\n" +
                        "          SA.APP_ID,\n" +
                        "          SA.APPLY_NUMBER,\n" +
                        "          SA.AGENT_USER_ID,\n" +
                        "          ?\n" +
                        "     FROM SF_ACT SA\n" +
                        "    WHERE SA.ACTID = ?)";

        List sqlArgs = new ArrayList();
        sqlArgs.add(FlowConstant.FLOW_CODE_NEXT);
        sqlArgs.add(actId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * �����ӵ����̣���һ�θ���ACT
     *
     * @return
     * @throws UploadException
     */
    public SQLModel getSfActUpdateModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE"
                + " SF_ACT SA"
                + " SET"
                + " SA.CUR_TASK_ID = SA.NEXT_TASK_ID,"
                + " SA.CUR_USERID =NULL,"  //��ʱûȷ������Ϊ��һ���ڵ�����ж��������
                + " SA.CUR_TASK_POSITION_ID =NULL,"   //
                + " SA.CUR_APPLMSG = ?,"
                + " SA.FROM_DT = GETDATE(),"
                + " SA.FROM_TASK_ID = ?,"
                + " SA.FROM_USER_ID = ?,"
                + " SA.COMPLETE_DT = NULL,"
                + " SA.COMPLETE_USER = NULL,"
                + " SA.COMPLETE_STATUS = ?,"
                + " SA.NEXT_TASK_ID = NULL,"
                + " SA.NEXT_USER_ID = NULL"
                + " WHERE"
                + " SA.ACTID = ?";
        List sqlArgs = new ArrayList();
        sqlModel.setSqlStr(sqlStr);

        // sqlArgs.add(parser.getParameter("nextPositionId"));
        sqlArgs.add(parser.getParameter("flowDesc"));//��һ������ʾ
        sqlArgs.add(currTaskId);
        sqlArgs.add(userId);
        sqlArgs.add(FlowConstant.TASK_STATUS_NOT_FINISHED);
        sqlArgs.add(actId);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //�����������.userId,��һ����session�еģ�session���п�����ί����
    public SQLModel getAddApproveContentModle(String actId, String curUserId, String approveContent) throws UploadException {
        SQLModel sm = new SQLModel();
        String sql =
                "INSERT INTO SF_APPROVE_CONTENT\n" +
                        "  (APPROVE_ID,\n" +
                        "   ACTID,\n" +
                        "   APPLY_ID,\n" +
                        "   APPROVE_PERSON_ID,\n" +
                        "   APPROVE_TIME,\n" +
                        "   APPROVE_CONTENT,\n" +
                        "   TASK_ID,\n" +
                        "   APPROVE_DEPT_ID,\n" +
                        "   APPROVE_POSITION_ID,\n" +
                        "   PROC_ID,\n" +
                        "   AGENT_USER_ID\n" +
                        "  )\n" +
                        "VALUES\n" +
                        "  ( NEWID() , ?, ?, ?, GETDATE(), ?, ?, ?, SF_FLOW_PKG.GET_POSITION_ID_BY_USER_ID(?), ?,?)";
        ArrayList al = new ArrayList();
        al.add(actId);
        al.add(applyId);
        al.add(curUserId);
        al.add(approveContent);
        al.add(currTaskId);
        al.add(parser.getParameter("currDeptId"));
        al.add(curUserId);//ȡְλID
        al.add(procId);
        String agentUserId = "";
        if (!StrUtil.nullToString(userId).equals(curUserId)) { //���session���û������������е�ǰ�����˵��û������Ǵ���
            agentUserId = StrUtil.nullToString(userId);
        }
        al.add(agentUserId);
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;

    }

    /**
     * ���ܣ���ɵ�ǰ�ڵ���ת��Ϣ��
     *
     * @return SQLModel
     * @throws UploadException
     */
    public SQLModel getCompleteSfActModel() throws UploadException {
        String taskProp = parser.getParameter("taskProp");
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE SF_ACT SA\n" +
                "   SET SA.CUR_STATUS      = ?,\n" +
                "       SA.COMPLETE_DT     = GETDATE(),\n" +
                "       SA.COMPLETE_STATUS = ?,\n" +
                "       SA.CUR_USERID      = SF_FLOW_PKG.GET_CUR_USER_ID(?)," +
                "       SA.COMPLETE_USER   = SF_FLOW_PKG.GET_CUR_USER_ID(?),\n" +
                "       SA.CUR_TASK_POSITION_ID =\n" +
                "       SF_FLOW_PKG.GET_POSITION_ID_BY_USER_ID(SF_FLOW_PKG.GET_CUR_USER_ID(?))," +
                "       SA.NEXT_TASK_ID    = ?,\n" +
                "       SA.NEXT_USER_ID    = ?,\n" +
                "       SA.DEPT_ID         = ?,\n" +
                "       SA.AGENT_USER_ID   = SF_FLOW_PKG.GET_CUR_USER_AGENT(?)\n" +
                " WHERE SA.ACTID = ?";


        List sqlArgs = new ArrayList();
        String proStatus = "";
//        if (taskProp.equals(FlowConstant.TASK_PROP_END)) {
//            proStatus = FlowConstant.PROC_STATUS_COMPLETE;
//        } else {
        proStatus = FlowConstant.PROC_STATUS_NORMAL;
//        }
        sqlArgs.add(proStatus);
        sqlArgs.add(FlowConstant.TASK_STATUS_FINISHED);
        sqlArgs.add(parser.getParameter("actId"));//��ǰ�����ˣ���Ϊ��ǰ�������п����ж������ʱ��������ȷ��
        sqlArgs.add(parser.getParameter("actId"));//��ǰ�����
        sqlArgs.add(parser.getParameter("actId"));//��ǰ����˵�ְλ
        sqlArgs.add(parser.getParameter("nextTaskId"));
        sqlArgs.add("");//sqlArgs.add(parser.getParameter("nextUserId")); ��һ�ڵ�İ����ˣ�ֻ������һ�β���������ȷ��
        sqlArgs.add(parser.getParameter("currDeptId"));
        sqlArgs.add(parser.getParameter("actId")); //��ǰ�����˵Ĵ�����
        sqlArgs.add(parser.getParameter("actId"));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����ݵ�ǰ�ڵ���ת��Ϣ��
     *
     * @return SQLModel
     */
    public SQLModel getCreateSfActLogModel(String flowCode) throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "INSERT INTO SF_ACT_LOG\n" +
                "  (LOG_ID,\n" +
                "   ACTID,\n" +
                "   PROC_ID,\n" +
                "   PROC_NAME,\n" +
                "   CUR_TASK_ID,\n" +
                "   CUR_USERID,\n" +
                "   CUR_STATUS,\n" +
                "   CUR_TASK_POSITION_ID,\n" +
                "   FROM_DT,\n" +
                "   FROM_TASK_ID,\n" +
                "   FROM_USER_ID,\n" +
                "   COMPLETE_DT,\n" +
                "   COMPLETE_STATUS,\n" +
                "   COMPLETE_USER,\n" +
                "   NEXT_TASK_ID,\n" +
                "   NEXT_USER_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   DEPT_ID,\n" +
                "   APP_ID,\n" +
                "   APPLY_NUMBER,\n" +
                "   AGENT_USER_ID,\n" +
                "   FLOW_CODE)\n" +
                "  (SELECT  NEWID() ,\n" +
                "          SA.ACTID,\n" +
                "          SA.PROC_ID,\n" +
                "          SA.PROC_NAME,\n" +
                "          SA.CUR_TASK_ID,\n" +
                "          SA.CUR_USERID,\n" +
                "          SA.CUR_STATUS,\n" +
                "          SA.CUR_TASK_POSITION_ID,\n" +
                "          SA.FROM_DT,\n" +
                "          SA.FROM_TASK_ID,\n" +
                "          SA.FROM_USER_ID,\n" +
                "          SA.COMPLETE_DT,\n" +
                "          SA.COMPLETE_STATUS,\n" +
                "          SA.COMPLETE_USER,\n" +
                "          SA.NEXT_TASK_ID,\n" +
                "          SA.NEXT_USER_ID,\n" +
                "          SA.CREATION_DATE,\n" +
                "          SA.CREATED_BY,\n" +
                "          SA.DEPT_ID,\n" +
                "          SA.APP_ID,\n" +
                "          SA.APPLY_NUMBER,\n" +
                "          SA.AGENT_USER_ID,\n" +
                "          ?\n" +
                "     FROM SF_ACT SA\n" +
                "    WHERE SA.ACTID = ?)";

        List sqlArgs = new ArrayList();
        sqlArgs.add(flowCode);
        sqlArgs.add(parser.getParameter("actId"));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����µ�ǰ�ڵ���Ϣ��ʹ������ת����һ�ڵ㡣
     * ����ͨ��������
     *
     * @return SQLModel
     * @throws UploadException
     */
    public SQLModel getUpdateSfActForPassModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr =
                "UPDATE SF_ACT SA\n" +
                        "   SET SA.CUR_TASK_ID          = SA.NEXT_TASK_ID,\n" +
                        "       SA.CUR_USERID           = NULL,\n" +
                        "       SA.CUR_TASK_POSITION_ID = NULL,\n" +
                        "       SA.CUR_STATUS           = ?,\n" +
                        "       SA.FROM_DT              = GETDATE(),\n" +
                        "       SA.FROM_TASK_ID         = SA.CUR_TASK_ID,\n" +
                        "       SA.FROM_USER_ID         = SA.CUR_USERID,\n" +
                        "       SA.COMPLETE_DT          = NULL,\n" +
                        "       SA.COMPLETE_USER        = NULL,\n" +
                        "       SA.COMPLETE_STATUS      = ?,\n" +
                        "       SA.NEXT_TASK_ID         = NULL,\n" +
                        "       SA.NEXT_USER_ID         = NULL\n" +
                        " WHERE SA.ACTID = ?";

        List sqlArgs = new ArrayList();
        sqlArgs.add(FlowConstant.PROC_STATUS_NORMAL);
        sqlArgs.add(FlowConstant.TASK_STATUS_NOT_FINISHED);
        sqlArgs.add(parser.getParameter("actId"));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����µ�ǰ�ڵ���Ϣ��ʹ���̻ص���һ�ڵ㡣
     * �˻���һ�ڵ�������Ҫ�޸ġ�
     *
     * @return SQLModel
     * @throws UploadException
     */
    public SQLModel getUpdateSfActForRejectModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr =
                "UPDATE SF_ACT SA\n" +
                        "   SET SA.CUR_TASK_ID          = ?,\n" +
                        "       SA.CUR_USERID           = ?,\n" +
                        "       SA.AGENT_USER_ID        = ?,\n" +
                        "       SA.CUR_TASK_POSITION_ID = ?,\n" +
                        "       SA.CUR_STATUS           = ?,\n" +
                        "       SA.FROM_DT              = GETDATE(),\n" +
                        "       SA.FROM_TASK_ID         = SA.CUR_TASK_ID,\n" +
                        "       SA.FROM_USER_ID         = SA.CUR_USERID,\n" +
                        "       SA.COMPLETE_DT          = NULL,\n" +
                        "       SA.COMPLETE_USER        = NULL,\n" +
                        "       SA.COMPLETE_STATUS      = ?,\n" +
                        "       SA.NEXT_TASK_ID         = NULL,\n" +
                        "       SA.NEXT_USER_ID         = NULL\n" +
                        " WHERE SA.ACTID = ?";

        List sqlArgs = new ArrayList();
        sqlArgs.add(parser.getParameter("prevTaskId"));
        sqlArgs.add(parser.getParameter("prevUserId"));
        sqlArgs.add("");
        sqlArgs.add(parser.getParameter("prevPositionId"));//����ڵ���
        sqlArgs.add(FlowConstant.PROC_STATUS_NORMAL);
        sqlArgs.add(FlowConstant.TASK_STATUS_NOT_FINISHED);
        sqlArgs.add(parser.getParameter("actId"));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ɾ��SF_ACT�����Ӧ��¼.
     *
     * @return SQLModel
     * @throws UploadException
     */
    public SQLModel getDeleteSfActModel(String actId) throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "DELETE FROM"
                + " SF_ACT SA"
                + " WHERE"
                + " SA.ACTID = ?";
        List sqlArgs = new ArrayList();
        if (actId.equals("")) {
            actId = parser.getParameter("actId");
        }
        sqlArgs.add(actId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ȡ���̵ĵ�һ�����
     */
    public SQLModel getFirstTaskIdModel() {
        String sq =
                "SELECT STD.TASK_ID\n" +
                        "  FROM SF_TASK_DEFINE STD\n" +
                        " WHERE STD.TASK_PROP = 1\n" +
                        "   AND STD.PROC_ID = ?";
        ArrayList al = new ArrayList();
        al.add(procId);
        SQLModel sm = new SQLModel();
        sm.setArgs(al);
        sm.setSqlStr(sq);
        return sm;
    }

    public SQLModel getProcNameByIdModel() {
        String sql =
                "SELECT SPD.PROC_NAME FROM SF_PROCEDURE_DEF SPD WHERE SPD.PROC_ID = ?";
        ArrayList al = new ArrayList();
        al.add(procId);
        SQLModel sm = new SQLModel();
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;

    }

    public SQLModel completeActLogNextUserIdModel(String actId) {
        String sql =
                "UPDATE SF_ACT_LOG SAL\n" +
                        "   SET SAL.NEXT_USER_ID = SF_FLOW_PKG.GET_CUR_USER_ID(?)\n" +
                        " WHERE SAL.ACTID = ?\n" +
                        "   AND SAL.LOG_ID =\n" +
                        "       (SELECT MAX(SAL2.LOG_ID) FROM SF_ACT_LOG SAL2 WHERE SAL2.ACTID = ?)";
        ArrayList al = new ArrayList();
        al.add(actId);
        al.add(actId);
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    public SQLModel deleteActInfoModel(String actId) {
        String sql = "DELETE FROM SF_ACT_INFO SAI WHERE SAI.ACT_ID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //update SF_ACT for reject to begin
    public SQLModel updateAct42Begin() throws UploadException {
        String sql =
                "UPDATE SF_ACT SA\n" +
                        "   SET SA.CUR_TASK_ID          = SF_FLOW_PKG.GET_BEGIN_TASK(?),\n" +
                        "       SA.CUR_USERID           = SA.CREATED_BY,\n" +
                        "       SA.AGENT_USER_ID        = '', --�ڵ�ǰ�����˰������ˣ�����\n" +
                        "       SA.CUR_TASK_POSITION_ID = '', --�ڵ�ǰ�����˰������ˣ�����\n" +
                        "       SA.CUR_STATUS           = ?,\n" +
                        "       SA.FROM_DT              = GETDATE(),\n" +
                        "       SA.FROM_TASK_ID         = SA.CUR_TASK_ID,\n" +
                        "       SA.FROM_USER_ID         = SA.CUR_USERID,\n" +
                        "       SA.COMPLETE_DT          = NULL,\n" +
                        "       SA.COMPLETE_USER        = NULL,\n" +
                        "       SA.COMPLETE_STATUS      = ?,\n" +
                        "       SA.NEXT_TASK_ID         = NULL,\n" +
                        "       SA.NEXT_USER_ID         = NULL\n" +
                        " WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(procId);//ͨ������IDȡ�ʼ�Ľڵ�
        al.add(FlowConstant.PROC_STATUS_NORMAL);
        al.add(FlowConstant.TASK_STATUS_NOT_FINISHED);
        al.add(parser.getParameter("actId"));
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ȡĳһ�����̼�¼�Ĵ����ˣ���Ҫ�����˵�������
    public SQLModel getCreatedByModel(String actId) {
        SQLModel sm = new SQLModel();
        String sql =
                "SELECT SA.CREATED_BY FROM SF_ACT SA WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //��ĳ�����̼�¼ֱ��ת�Ƶ������ڵ㣬���ACT
    public SQLModel completeAct2EndModel(String actId, String procId) {
        String sql =
                "UPDATE SF_ACT SA\n" +
                        "   SET SA.CUR_STATUS           = ?,\n" +
                        "       SA.COMPLETE_DT          = GETDATE(),\n" +
                        "       SA.COMPLETE_STATUS      = ?,\n" +
                        "       SA.CUR_USERID           = SF_FLOW_PKG.GET_CUR_USER_ID(?),\n" +
                        "       SA.COMPLETE_USER        = SF_FLOW_PKG.GET_CUR_USER_ID(?),\n" +
                        "       SA.CUR_TASK_POSITION_ID = SF_FLOW_PKG.GET_POSITION_ID_BY_USER_ID(SF_FLOW_PKG.GET_CUR_USER_ID(?)),\n" +
                        "       SA.NEXT_TASK_ID         = SF_FLOW_PKG.GET_END_TASK(?),\n" +
                        "       SA.NEXT_USER_ID         = NULL,\n" +
                        "       SA.DEPT_ID              = SF_FLOW_PKG.GET_DEPT_ID_BY_USER_ID(SF_FLOW_PKG.GET_CUR_USER_ID(?)),\n" +
                        "       SA.AGENT_USER_ID        = SF_FLOW_PKG.GET_CUR_USER_AGENT(?)\n" +
                        " WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(FlowConstant.PROC_STATUS_NORMAL);
        al.add(FlowConstant.TASK_STATUS_NOT_FINISHED);
        al.add(actId);   //��ǰ������
        al.add(actId);//�����
        al.add(actId); //��ǰ�˵�ְλ
        al.add(procId); //ȡ�����ڵ�
        al.add(actId); //ȡ����
        al.add(actId); //ȡ ������
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    //ֱ�����������ڵ�ʱ���õ��ı���SQL
    public SQLModel getAct2Log4flow2EndModel(String flowCode, String actId) throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "INSERT INTO SF_ACT_LOG\n" +
                "  (LOG_ID,\n" +
                "   ACTID,\n" +
                "   PROC_ID,\n" +
                "   PROC_NAME,\n" +
                "   CUR_TASK_ID,\n" +
                "   CUR_USERID,\n" +
                "   CUR_STATUS,\n" +
                "   CUR_TASK_POSITION_ID,\n" +
                "   FROM_DT,\n" +
                "   FROM_TASK_ID,\n" +
                "   FROM_USER_ID,\n" +
                "   COMPLETE_DT,\n" +
                "   COMPLETE_STATUS,\n" +
                "   COMPLETE_USER,\n" +
                "   NEXT_TASK_ID,\n" +
                "   NEXT_USER_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   DEPT_ID,\n" +
                "   APP_ID,\n" +
                "   APPLY_NUMBER,\n" +
                "   AGENT_USER_ID,\n" +
                "   FLOW_CODE)\n" +
                "  (SELECT  NEWID() ,\n" +
                "          SA.ACTID,\n" +
                "          SA.PROC_ID,\n" +
                "          SA.PROC_NAME,\n" +
                "          SA.CUR_TASK_ID,\n" +
                "          SA.CUR_USERID,\n" +
                "          SA.CUR_STATUS,\n" +
                "          SA.CUR_TASK_POSITION_ID,\n" +
                "          SA.FROM_DT,\n" +
                "          SA.FROM_TASK_ID,\n" +
                "          SA.FROM_USER_ID,\n" +
                "          SA.COMPLETE_DT,\n" +
                "          SA.COMPLETE_STATUS,\n" +
                "          SA.COMPLETE_USER,\n" +
                "          SA.NEXT_TASK_ID,\n" +
                "          SA.NEXT_USER_ID,\n" +
                "          SA.CREATION_DATE,\n" +
                "          SA.CREATED_BY,\n" +
                "          SA.DEPT_ID,\n" +
                "          SA.APP_ID,\n" +
                "          SA.APPLY_NUMBER,\n" +
                "          SA.AGENT_USER_ID,\n" +
                "          ?\n" +
                "     FROM SF_ACT SA\n" +
                "    WHERE SA.ACTID = ?)";

        List sqlArgs = new ArrayList();
        sqlArgs.add(flowCode);
        sqlArgs.add(actId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel addAprroveContent4Flow2End(String actId, String applyId, String userId, String approveContent, String currTaskId, String procId) {
        String sql =
                "\n" +
                        "INSERT INTO SF_APPROVE_CONTENT\n" +
                        "  (APPROVE_ID,\n" +
                        "   ACTID,\n" +
                        "   APPLY_ID,\n" +
                        "   APPROVE_PERSON_ID,\n" +
                        "   APPROVE_TIME,\n" +
                        "   APPROVE_CONTENT,\n" +
                        "   TASK_ID,\n" +
                        "   APPROVE_DEPT_ID,\n" +
                        "   APPROVE_POSITION_ID,\n" +
                        "   PROC_ID)\n" +
                        "VALUES\n" +
                        "  ( NEWID() ,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   GETDATE(),\n" +
                        "   ?,\n" +
                        "   ?,\n" +
                        "   SF_FLOW_PKG.GET_DEPT_ID_BY_USER_ID(?),\n" +
                        "   SF_FLOW_PKG.GET_POSITION_ID_BY_USER_ID(?),\n" +
                        "   ?)";
        ArrayList al = new ArrayList();
        al.add(actId);
        al.add(applyId);
        al.add(userId);
        al.add(approveContent);
        al.add(currTaskId);
        al.add(userId);
        al.add(userId);
        al.add(procId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }

    public SQLModel getCompleteSfAct4RejectBeginModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE SF_ACT SA\n" +
                "   SET SA.CUR_STATUS      = ?,\n" +
                "       SA.COMPLETE_DT     = GETDATE(),\n" +
                "       SA.COMPLETE_STATUS = ?,\n" +
                "       SA.CUR_USERID      = SF_FLOW_PKG.GET_CUR_USER_ID(?)," +
                "       SA.COMPLETE_USER   = SF_FLOW_PKG.GET_CUR_USER_ID(?),\n" +
                "       SA.CUR_TASK_POSITION_ID =\n" +
                "       SF_FLOW_PKG.GET_POSITION_ID_BY_USER_ID(SF_FLOW_PKG.GET_CUR_USER_ID(?))," +
                "       SA.NEXT_TASK_ID    = SF_FLOW_PKG.GET_BEGIN_TASK(?),\n" +
                "       SA.NEXT_USER_ID    = ?,\n" +
                "       SA.DEPT_ID         = SF_FLOW_PKG.GET_DEPT_ID_BY_USER_ID(SF_FLOW_PKG.GET_CUR_USER_ID(?)),\n" +
                "       SA.AGENT_USER_ID   = SF_FLOW_PKG.GET_CUR_USER_AGENT(?)\n" +
                " WHERE SA.ACTID = ?";


        List sqlArgs = new ArrayList();
        String proStatus = "";
        proStatus = FlowConstant.PROC_STATUS_NORMAL;
        sqlArgs.add(proStatus);
        sqlArgs.add(FlowConstant.TASK_STATUS_FINISHED);
        sqlArgs.add(parser.getParameter("actId"));//��ǰ�����ˣ���Ϊ��ǰ�������п����ж������ʱ��������ȷ��
        sqlArgs.add(parser.getParameter("actId"));//��ǰ�����
        sqlArgs.add(parser.getParameter("actId"));//��ǰ����˵�ְλ
        sqlArgs.add(procId);
        sqlArgs.add("");//sqlArgs.add(parser.getParameter("nextUserId")); ��һ�ڵ�İ����ˣ�ֻ������һ�β���������ȷ��
        sqlArgs.add(parser.getParameter("actId"));
        sqlArgs.add(parser.getParameter("actId")); //��ǰ�����˵Ĵ�����
        sqlArgs.add(parser.getParameter("actId"));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //ĳӦ���Ƿ��Ѿ��߹�����
    public SQLModel isAddedModel() {
        String sql = "SELECT *\n" +
                "  FROM SF_ACT SA\n" +
                " WHERE SA.PROC_ID = ?\n" +
                "   AND SA.APP_ID = ?";
        ArrayList al=new ArrayList();
        al.add(procId);
        al.add(applyId);
        SQLModel sm=new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }
    public SQLModel isAddedOpinion(){
        String sql="SELECT *\n" +
                "  FROM SF_APPROVE_CONTENT SAC\n" +
                " WHERE SAC.PROC_ID = ?\n" +
                "   AND SAC.APPLY_ID = ?" ;
        ArrayList al=new ArrayList();
        al.add(procId);
        al.add(applyId);
        SQLModel sm=new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        return sm;
    }
}
