package com.sino.flow.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.flow.dto.SfActDTO;
import com.sino.flow.dto.SfPositionDTO;
import com.sino.flow.dto.SfProcedureDefDTO;
import com.sino.flow.dto.SfTaskDefineDTO;
import com.sino.flow.model.FlowActionModel;
import com.sino.sms.dto.SfMsgDefineDTO;
import com.sino.sms.service.MessageProcessService;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-11-9
 * Time: 14:47:17
 * FlowBusiness���2.0��,
 * ���಻��request,��������request����ҳ����Ҳ������û��request���ֻ������
 */
public class FlowAction {
    private Connection conn;
    private FlowDTO dto;
    private FlowActionModel model;

    //�������������ת���ã�����Ҫ����FlowDTO���Ե��ô˹���
    public FlowAction(Connection conn) {
        this.conn = conn;
        this.model = new FlowActionModel();
    }

    //�����������ת��������ô˹�����
    public FlowAction(Connection conn, FlowDTO dto) throws SQLException {
        this.conn = conn;
        this.dto = dto;
        String retValue = dto.validate();
        if (!retValue.equals("")) {
            throw new SQLException("����Ĳ���������  ***" + retValue + "***");
        }
        this.model = new FlowActionModel();
    }

    public FlowDTO getDto() {
        return dto;
    }

    public void setDto(FlowDTO dto) {
        this.dto = dto;
    }

    /**
     * @param req             HttpServletRequest
     * @param applyId         Ӧ��ID
     * @param applyNo         Ӧ�ñ��
     * @param activity        ����Ϊ9������Ϊ10
     * @param sessionUserId   ��ǰsession���û�ID
     * @param sessionUserName ��ǰsession���û���
     * @param approveContent  �������
     * @return ���ز���DTO
     */
    public static FlowDTO getDTOFromReq(HttpServletRequest req,
                                        String applyId, String applyNo,
                                        String activity, String sessionUserId,
                                        String sessionUserName, String approveContent) {
        FlowDTO flowDTO = new FlowDTO();
        flowDTO.setApplyId(applyId);
        flowDTO.setApplyNo(applyNo);
        flowDTO.setActId(StrUtil.nullToString(req.getParameter("actId")));
        flowDTO.setActivity(activity);
        flowDTO.setSessionCurTaskId(StrUtil.nullToString(req.getParameter("currTaskId")));
        flowDTO.setSessionUserId(StrUtil.strToInt(sessionUserId));
        flowDTO.setSessionUserName(sessionUserName);
        flowDTO.setToTaskId(StrUtil.nullToString(req.getParameter("nextTaskId")));
        flowDTO.setToUserIds(StrUtil.nullToString(req.getParameter("nextUserId")));
        flowDTO.setProcId(StrUtil.nullToString(req.getParameter("procId")));
        flowDTO.setPrevTaskId(StrUtil.nullToString(req.getParameter("prevTaskId")));
        flowDTO.setPrevUserId(StrUtil.nullToString(req.getParameter("prevUserId")));
        flowDTO.setCurTaskId(StrUtil.nullToString(req.getParameter("currTaskId")));
        flowDTO.setSectionRight(StrUtil.nullToString(req.getParameter("sectionRight")));
        flowDTO.setProcName(StrUtil.nullToString(req.getParameter("procName")));
        return flowDTO;
    }

    public static FlowDTO getDTOFromReq(HttpServletRequest req) {
        FlowDTO flowDTO = new FlowDTO();
        flowDTO.setActId(StrUtil.nullToString(req.getParameter("actId")));
        flowDTO.setSessionCurTaskId(StrUtil.nullToString(req.getParameter("currTaskId")));
        flowDTO.setToTaskId(StrUtil.nullToString(req.getParameter("nextTaskId")));
        flowDTO.setToUserIds(StrUtil.nullToString(req.getParameter("nextUserId")));
        flowDTO.setProcId(StrUtil.nullToString(req.getParameter("procId")));
        flowDTO.setPrevTaskId(StrUtil.nullToString(req.getParameter("prevTaskId")));
        flowDTO.setPrevUserId(StrUtil.nullToString(req.getParameter("prevUserId")));
        flowDTO.setCurTaskId(StrUtil.nullToString(req.getParameter("currTaskId")));
        flowDTO.setApproveContent(StrUtil.nullToString(req.getParameter("approveContent")));
        flowDTO.setSectionRight(StrUtil.nullToString(req.getParameter("sectionRight")));
        flowDTO.setProcName(StrUtil.nullToString(req.getParameter("procName")));
        return flowDTO;
    }

    public void setDTOFromReq(HttpServletRequest req,
                              String applyId, String applyNo,
                              String activity, String sessionUserId,
                              String sessionUserName, String approveContent) {
        FlowDTO flowDTO = getDTOFromReq(req, applyId, applyNo, activity, sessionUserId, sessionUserName, approveContent);
        this.setDto(flowDTO);
    }

    /**
     * �����������̵��õ������������ܴ���ת���ĸ��ڵ㶼���Ե��ã�
     * ������һ���ڵ㡢������һ���ڵ㡢�������Ͷ����Ե���
     * ���������̶���
     */
    public void flow() throws SQLException, QueryException, DataHandleException {
        String actId = dto.getActId();
        /*
            ���û�����̼�¼ID���ȵ���add2Flow,��add2Flow���ж��Ƿ����ӹ�
            �м�ڵ㲻���Բ����ռ����д򿪣������ж��Ƿ��ظ��ύ�߼�����
            ��Ϊ�����ռ���򿪣�ȡ����session�е�ǰ��taskId
        */
        if (actId == null || actId.equals("")) {
            actId = add2Flow(dto.getProcName());
            dto.setActId(actId);
        }
        if (!lockAct(dto.getActId())) {
            throw new SQLException("�������Ѿ�����!");
        }
        CallableStatement cStmt = null;
        try {
            String sql = "{call SF_FLOW_MAIN_PKG.FLOW(?,?,?,?,?,?,?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.setString(1, dto.getApplyId());
            cStmt.setString(2, dto.getApplyNo());
            cStmt.setString(3, dto.getActId());
            cStmt.setString(4, dto.getActivity());
            cStmt.setInt(5, dto.getSessionUserId());
            cStmt.setString(6, dto.getApproveContent());
            cStmt.setString(7, dto.getToTaskId());
            cStmt.setString(8, dto.getToUserIds());
//            cStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            cStmt.execute();
            String errorMsg = cStmt.getString(9);
            if (errorMsg != null && !errorMsg.equals("")) {
                throw new SQLException(errorMsg);
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    /**
     * @param isSendMsg �Ƿ��Ͷ���
     */
    public void flow(boolean isSendMsg) throws SQLException, QueryException, DataHandleException {
        if (isSendMsg) {
            stopMessage();
            sendMessage(dto.getToUserIds());
        }
        flow();
    }

    /**
     * �������ǽ������˻ص���һ���ڵ�
     * �˻ص�ʱ��Ҳ����ֱ�ӵ���flow()�������˷���ֻ�Ƿ�װ��flow��
     * ��ȥ���û�����Ŀ�Ľڵ��Ŀ���˵Ĳ���
     * ����
     */
    public void reject() throws SQLException {
        if (!lockAct(dto.getActId())) {
            throw new SQLException("�������Ѿ�����!");
        }
        CallableStatement cStmt = null;
        try {
            String sql = "{call SF_FLOW_MAIN_PKG.REJECT(?,?,?,?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.setString(1, dto.getApplyId());
            cStmt.setString(2, dto.getApplyNo());
            cStmt.setString(3, dto.getActId());
            cStmt.setInt(4, dto.getSessionUserId());
            cStmt.setString(5, dto.getApproveContent());
//            cStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            cStmt.execute();
            String errorMsg = cStmt.getString(6);
            if (errorMsg != null && !errorMsg.equals("")) {
                throw new SQLException(errorMsg);
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    public void reject(boolean isSendMsg) throws SQLException {
        if (isSendMsg) {
            stopMessage();
            String prevUserId = dto.getPrevUserId();
            if (prevUserId == null || prevUserId.equals("")) {
                setPreTaskUser();
            }
            sendMessage(dto.getPrevUserId());
        }
        reject();
    }

    /**
     * �������ǽ������˻ص���ʼ�ڵ�
     * �˻ص�ʱ��Ҳ����ֱ�ӵ���flow()�������˷���ֻ�Ƿ�װ��flow��
     * ��ȥ���û�����Ŀ�Ľڵ��Ŀ���˵Ĳ���
     */
    public void reject2Begin() throws SQLException {
        if (!lockAct(dto.getActId())) {
            throw new SQLException("�������Ѿ�����!");
        }
        CallableStatement cStmt = null;
        try {
            String sql = "{call SF_FLOW_MAIN_PKG.REJECT_TO_BEGIN(?,?,?,?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.setString(1, dto.getApplyId());
            cStmt.setString(2, dto.getApplyNo());
            cStmt.setString(3, dto.getActId());
            cStmt.setInt(4, dto.getSessionUserId());
            cStmt.setString(5, dto.getApproveContent());
//            cStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            cStmt.execute();
            String errorMsg = cStmt.getString(6);
            if (errorMsg != null && !errorMsg.equals("")) {
                throw new SQLException(errorMsg);
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    public void reject2Begin(boolean isSendMsg) throws SQLException {
        if (isSendMsg) {
            stopMessage();
            String beginUserId = dto.getBeginUserId();
            if (beginUserId == null || beginUserId.equals("")) {
                setBeginTaskUser();
                beginUserId = dto.getBeginUserId();
            }
            sendMessage(beginUserId);
        }
        reject2Begin();
    }


    //��Ӧ���ύ�����̣�����Ťת,����Ѿ����ӣ������κδ���
    public String add2Flow(String procName) throws SQLException, QueryException, DataHandleException {
        CallableStatement cStmt = null;
        if (dto.getProcId() == null || dto.getProcId().equals("")) {
            SfProcedureDefDTO procDTO = getProcByName(procName);
            dto.setProcId(procDTO.getProcId());
        }
        String actId = isAdded(dto.getApplyId(), dto.getProcId());

        //��һ���ڵ�Ľڵ���Ϣ
        SfTaskDefineDTO taskDTO = getFirstTask(dto.getProcId());
        if (actId != null && !actId.equals("")) {
            /*
                �����ҳ�����ݴ�󣬲��������ռ�����ڰ����д򿪣�ҳ����û�е�ǰ��TaskId
                ����Ϊ�˿��Ƿ��ظ��ύ������Ƚϣ�
                �˴����Խ�Session�еĵ�ǰ�ڵ���Ϊ��һ���ڵ�,��Ϊ�˷���
                ֻ�е�һ���ڵ���ڲ����ռ��䣨�ڰ��䣩�򿪵��������˿��Խ���һ���ڵ���
                Ϊsession�еĽڵ�
            */
            dto.setSessionCurTaskId(taskDTO.getTaskId());
        } else {
            SeqProducer sp = new SeqProducer(conn);
//            actId = sp.getStrNextSeq("SF_ACT_S");
            dto.setActId(actId);
            dto.setCurTaskId(taskDTO.getTaskId());
            dto.setSessionCurTaskId(taskDTO.getTaskId());
            try {
                String sql = "{call SF_FLOW_MAIN_PKG.ADD_TO_FLOW(?,?,?,?,?,?,?,?)}";
                cStmt = conn.prepareCall(sql);
                cStmt.setString(1, actId);  //actId;
                cStmt.setString(2, dto.getProcId());
                cStmt.setString(3, procName);
                cStmt.setString(4, taskDTO.getTaskId());
                cStmt.setInt(5, dto.getSessionUserId());
                cStmt.setString(6, dto.getApplyId());
                cStmt.setString(7, dto.getApplyNo());
//                cStmt.registerOutParameter(8, OracleTypes.VARCHAR);
                cStmt.execute();
                String errorMsg = cStmt.getString(8);
                if (errorMsg != null && !errorMsg.equals("")) {
                    throw new SQLException(errorMsg);
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
        return actId;
    }

    /**
     * ȡ��һ������ڵ㣬�����Ƕ��
     *
     * @param curTaskId:��ǰ�ڵ�Id
     * @param flowCode:        ���������  ������Ϊ��
     * @param procId:          ����Id;
     * @return SfTaskDefineDTO��ļ���
     */
    public ArrayList getNextTasks(String curTaskId, String flowCode, String procId) throws SQLException {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList al = new ArrayList();
        try {
            String sql = "{call SF_FLOW_MAIN_PKG.GET_NEXT_TASKS(?,?,?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.setString(1, curTaskId);
            cStmt.setString(2, flowCode);
            cStmt.setString(3, procId);
//            cStmt.registerOutParameter(4, OracleTypes.CURSOR);
//            cStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            cStmt.execute();
            String errorMsg = cStmt.getString(5);
            if (errorMsg != null && !errorMsg.equals("")) {
                throw new SQLException(errorMsg);
            }
//            rs = ((OracleCallableStatement) cStmt).getCursor(4);
            while (rs.next()) {
                SfTaskDefineDTO task = new SfTaskDefineDTO();
                task.setTaskId(rs.getString("TASK_ID"));
                task.setTaskName(rs.getString("TASK_NAME"));
                task.setTaskMsg(rs.getString("TASK_MSG"));
                task.setTaskProp(rs.getString("TASK_PROP"));
                task.setProcId(rs.getString("PROC_ID"));
                task.setHiddenRight(rs.getString("HIDDEN_RIGHT"));
                task.setSectionRight(rs.getString("SECTION_RIGHT"));
                task.setOrganizationId(rs.getInt("ORGANIZATION_ID"));
                task.setFlowDesc(rs.getString("FLOW_DESC"));
                task.setFlowCode(rs.getString("FLOW_CODE"));
                al.add(task);
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
            DBManager.closeDBResultSet(rs);
        }
        return al;
    }

    /**
     * @param nextTaskId
     * @param nextDeptId ����������-1����ʱ����Ϊ��
     * @return SfPositionDTO ����
     * @throws SQLException
     */
    public ArrayList getNextUsers(String nextTaskId, String nextDeptId) throws SQLException {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList al = new ArrayList();
        try {
            String sql = "{call SF_FLOW_MAIN_PKG.GET_NEXT_USERS(?,?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.setString(1, nextTaskId);
            cStmt.setString(2, nextDeptId);
//            cStmt.registerOutParameter(3, OracleTypes.CURSOR);
//            cStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cStmt.execute();
            String errorMsg = cStmt.getString(4);
            if (errorMsg != null && !errorMsg.equals("")) {
                throw new SQLException(errorMsg);
            }
//            rs = ((OracleCallableStatement) cStmt).getCursor(3);
            while (rs.next()) {
                SfPositionDTO user = new SfPositionDTO();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setPositionId(rs.getString("POSITION_ID"));
                al.add(user);
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
            DBManager.closeDBResultSet(rs);
        }
        return al;
    }

    /**
     * ȡ�¸�����ڵ�ĸ���
     *
     * @return �¸��ڵ�ĸ���
     */
    public int getNextTaskCount() throws SQLException {
        String curTaskId = dto.getCurTaskId();
        if (curTaskId == null || curTaskId.equals("")) {
            setSfAct(dto.getActId());
        }
        SQLModel sm = model.getNextTaskCount(dto.getCurTaskId());
        String count = singleQuery(sm, "TOTAL");
        return Integer.parseInt(count);
    }

    public SfTaskDefineDTO getTaskInfoByTaskId(String taskId) throws QueryException, ContainerException {
        SfTaskDefineDTO dto = new SfTaskDefineDTO();
        SQLModel sm = model.getTaskInfoByTaskId(taskId);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            dto.setTaskId((String) row.getValue("TASK_ID"));
            dto.setProcId((String) row.getValue("PROC_ID"));
            dto.setTaskName((String) row.getValue("TASK_NAME"));
            dto.setTaskMsg((String) row.getValue("TASK_MSG"));
            dto.setSectionRight((String) row.getValue("SECTION_RIGHT"));
            dto.setHiddenRight((String) row.getValue("HIDDEN_RIGHT"));
            dto.setDeptId((String) row.getValue("DEPT_ID"));
            dto.setTaskProp((String) row.getValue("TASK_PROP"));
            dto.setAttribute1((String) row.getValue("ATTRIBUTE1"));
            dto.setAttribute2((String) row.getValue("ATTRIBUTE2"));
            dto.setAttribute3((String) row.getValue("ATTRIBUTE3"));
            dto.setAttribute4((String) row.getValue("ATTRIBUTE4"));
            dto.setAttribute5((String) row.getValue("ATTRIBUTE5"));
        }
        return dto;
    }

    //����Ӧ�÷������̼�¼
    public SfActDTO getSfActByAppId(String applyId, String appTableName) throws QueryException {
        SimpleQuery sq = new SimpleQuery(model.getSfActByApply(applyId, appTableName), conn);
        sq.setDTOClassName(SfActDTO.class.getName());
        sq.executeQuery();
        return (SfActDTO) sq.getFirstDTO();
    }

    //����Ӧ��ID����Ӧ�õ�ǰ�����ڵĽڵ�����
    public SfTaskDefineDTO getCurTaskInfoByApp(String applyId, String appTableName) throws QueryException {
        SimpleQuery sq = new SimpleQuery(model.getCurTaskInfoByApp(applyId, appTableName), conn);
        sq.setDTOClassName(SfTaskDefineDTO.class.getName());
        sq.executeQuery();
        return (SfTaskDefineDTO) sq.getFirstDTO();
    }

    /**
     * �ж���һ���ڵ��Ƿ����������
     *
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    public boolean isFlowToEnd() throws QueryException, ContainerException {
        String nextTaskId = dto.getToTaskId();
        SfTaskDefineDTO sfTaskDTO = getTaskInfoByTaskId(nextTaskId);
        return sfTaskDTO.getTaskName().equals("����");
    }

    //ֹ֪ͣͨ��ǰ������
    private void stopMessage() throws SQLException {
        try {
            String phone = getPhoneByUserId(StrUtil.nullToString(dto.getSessionUserId()), FlowConstant.SEND_TYPE_ONCE);
            if (!phone.equals("")) {
                MessageProcessService msgService = new MessageProcessService();
                msgService.finishPhoneMessage(conn, dto.getActId(), phone);
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * ֪ͨ��һ������
     *
     * @param toUserIds �˴����봫������
     *                  ��Ϊ������˻صģ�֪ͨ�Ĵ����ˣ��������������һ��
     */
    private void sendMessage(String toUserIds) throws SQLException {
        String msgContent = FlowConstant.MSG_AT_SYSTEM + dto.getApplyNo() + "��\nת���ˣ�" + dto.getSessionUserName();
        //��������˲�Ϊ�գ��������Ѵ�����
        String[] toUserIdArr = StrUtil.splitStr(toUserIds, ";");
        String procId = dto.getProcId();
        if (procId == null || procId.equals("")) {
            setSfAct(dto.getActId());
            procId = dto.getProcId();
        }
        try {
            if (toUserIdArr != null && toUserIdArr.length > 0) {
                for (int i = 0; i < toUserIdArr.length; i++) {
                    String agent = getAgentUserId(toUserIdArr[i], procId);
                    String announce = toUserIdArr[i];
                    if (agent != null && !agent.equals("")) {
                        announce = agent;
                    }
                    String phone = getPhoneByUserId(announce, FlowConstant.SEND_TYPE_ONCE);
                    saveMsg(msgContent, phone, "1", dto.getActId());
                }
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    //ȡ�绰����
    private String getPhoneByUserId(String userId, String sendType) throws SQLException {
        return singleQuery(model.getPhoneModel(StrUtil.strToInt(userId), sendType), "MSG_CELL_PHONE");
    }

    private String getAgentUserId(String userId, String procId) throws SQLException {
        return singleQuery(model.getAgent(userId, procId), "AGENT_USER_ID");
    }

    /**
     * ͨ��actIdȡ���������Ϣ��������ã�������������תǰ������ת�����
     * ����Ѿ���ת��ȡ�ľ����¸�һ�ڵ����Ϣ
     *
     * @param actId
     * @throws SQLException
     */

    private void setSfAct(String actId) throws SQLException {
        try {
            SQLModel sm = model.getSfAct(actId);
            SimpleQuery sq = new SimpleQuery(sm, conn);
            sq.executeQuery();
            RowSet rs = sq.getSearchResult();
            if (rs != null && rs.getSize() > 0) {
                Row row = rs.getRow(0);
                dto.setProcId(StrUtil.nullToString(row.getValue("PROC_ID")));
                dto.setCurTaskId(StrUtil.nullToString(row.getValue("CUR_TASK_ID")));
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    //ȡ��ֵ
    private String singleQuery(SQLModel sm, String name) throws SQLException {
        String value = "";
        try {
            SimpleQuery sq = new SimpleQuery(sm, conn);
            sq.executeQuery();
            RowSet rs = sq.getSearchResult();
            if (rs != null && rs.getSize() > 0) {
                Row row = rs.getRow(0);
                value = (String) row.getValue(name);
            }
        } catch (Exception e) {
            Logger.logError(sm);
            throw new SQLException(e.getMessage());
        }
        return value;
    }

    //��ȡ������
    private void saveMsg(String content, String phone, String msgCategory, String actId) throws DataHandleException {
        MessageProcessService msgService = new MessageProcessService();
        SfMsgDefineDTO msg = new SfMsgDefineDTO();
        msg.setMsgCategoryId(msgCategory);
        //�绰�������ѭ�����棬���򣬼���ѭ������Ϣ�ĵ绰����ȫ������ArrayList���档��Ϣ�������Ϊ
        //��һ������֪ͨ����绰
        msg.addCellPhone(phone);
        int length = content.length() / 65 + 1;
        String[] contentArray = new String[length];
        for (int i = 0; i < length; i++) {
            if ((i + 1) * 65 > content.length()) {
                contentArray[i] = content.substring(i * 65, content.length());
            } else {
                contentArray[i] = content.substring(i * 65, (i + 1) * 65);
            }
            if (i == length - 1) {
                contentArray[i] += "[��]";
            } else {
                contentArray[i] += "[" + (i + 1) + "]";
            }
            msg.setMsgContent(contentArray[i]);
            msg.setActId(actId);
            msgService.saveMessage(conn, msg);

        }
    }

    private void setPreTaskUser() throws SQLException {
        CallableStatement cStmt = null;
        try {
            String sql = "{? = call SF_FLOW_PKG.GET_FROM_TASK_USER(?,?)}";
            cStmt = conn.prepareCall(sql);
            String curTaskId = dto.getCurTaskId();
            if (curTaskId == null || curTaskId.equals("")) {
                setSfAct(dto.getActId());
            }
//            cStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            cStmt.setString(2, dto.getCurTaskId());
            cStmt.setString(3, dto.getActId());
            cStmt.execute();
            String taskUser = cStmt.getString(1);
            String[] tmpArr = StrUtil.splitStr(taskUser, "$$");
            dto.setPrevTaskId(tmpArr[0]);
            dto.setPrevUserId(tmpArr[1]);
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    private void setBeginTaskUser() throws SQLException {
        try {
            SQLModel sm = model.getBeginTaskUser(dto.getActId());
            SimpleQuery sq = new SimpleQuery(sm, conn);
            sq.executeQuery();
            RowSet rs = sq.getSearchResult();
            if (rs != null && rs.getSize() > 0) {
                Row row = rs.getRow(0);
                dto.setBeginTaskId(StrUtil.nullToString(row.getValue("BEGIN_TASK")));
                dto.setBeginUserId(StrUtil.nullToString(row.getValue("CREATED_BY")));
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

    }

    private boolean lockAct(String actId) throws SQLException {
        boolean success = false;
        try {
            //step1 :������������ظ��ύ����һ�ε��޸ı���ȵ���һ����ɺ�,һ����ɺ�Ϳ���select����currTaskId,
            //����ĸ�����Ҫ��Ϊ�˵ȴ���һ�ε�commit;

            SQLModel sm = model.lockAct(actId, StrUtil.nullToString(dto.getSessionUserId()));
            DBOperator.updateRecord(sm, conn);
            String currTaskId = StrUtil.nullToString(dto.getSessionCurTaskId());
            String tableTaskId = dto.getCurTaskId();
            if (tableTaskId == null || tableTaskId.equals("")) {
                setSfAct(actId);
                tableTaskId = dto.getCurTaskId();
            }
            //��ҳ���е�currTaskId�����ݿ��е�currTaskIdһ��ʱ������true;
            //��ʱ�����ظ��ύ
            if (tableTaskId.equals(currTaskId)) {
                clearLock(actId);
                success = true;
            } else {
                //������ظ��ύ ������ô˷�����ʱ��Ҫ�ع�����
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        return success;
    }

    private void clearLock(String actId) throws DataHandleException {
        DBOperator.updateRecord(model.clearLock(actId), conn);
    }

    /*
    *�Ƿ��Ѿ����ӹ�
    *modified by wwb 2007-11-21
    *�޸����ݣ�����������boolean�͸ĳ�String
    */
    private String isAdded(String applyId, String procId) throws SQLException {
        return singleQuery(model.isAddedModel(procId, applyId), "ACTID");
    }

    //ͨ��������ȡ������Ϣ
    private SfProcedureDefDTO getProcByName(String procName) throws QueryException {
        SfProcedureDefDTO dto = null;
        SimpleQuery sq = new SimpleQuery(model.getProcByName(procName), conn);
        sq.setDTOClassName(SfProcedureDefDTO.class.getName());
        sq.executeQuery();
        return (SfProcedureDefDTO) sq.getFirstDTO();
    }

    //ȡ��һ���ӵ���Ϣ
    private SfTaskDefineDTO getFirstTask(String procId) throws QueryException {
        SimpleQuery sq = new SimpleQuery(model.getFirstTask(procId), conn);
        sq.setDTOClassName(SfTaskDefineDTO.class.getName());
        sq.executeQuery();
        return (SfTaskDefineDTO) sq.getFirstDTO();
    }

    //�ж��Ƿ�Ϊ��һ���ڵ�
    public boolean isFirstTask(String taskId) throws SQLException {
        return (getTaskProp(taskId)).equals(FlowConstant.TASK_PROP_START);
    }

    //ȡ�ڵ�����
    private String getTaskProp(String taskId) throws SQLException {
        return singleQuery(model.getTaskProp(taskId), "TASK_PROP");
    }

    /**
     * ���ܣ������ݴ�ĵ���ʱʹ�ã�ϵͳ��ɾ���ڰ�������
     *
     * @throws SQLException
     */
    public void cancel() throws SQLException {
        System.out.println("flowDTO = " + dto);
        if (dto == null) {
            throw new SQLException("û����������DTO����");
        }
        String procedureName = dto.getProcName();
        String applyId = dto.getApplyId();
        if (StrUtil.isEmpty(procedureName)) {
            throw new SQLException("û��������������");
        }
        if (StrUtil.isEmpty(applyId)) {
            throw new SQLException("û������Ӧ��Ψһ��ʶID");
        }
        CallableStatement stmt = null;
        try {
            String sqlStr = "{call SF_FLOW_MAIN_PKG.CANCEL_FLOW(?, ?, ?)}";
            stmt = conn.prepareCall(sqlStr);
            stmt.setString(1, applyId);
            stmt.setString(2, procedureName);
//            stmt.registerOutParameter(3, OracleTypes.VARCHAR);
            stmt.execute();
            String errorMsg = stmt.getString(3);
            if (!StrUtil.isEmpty(errorMsg)) {
                throw new SQLException(errorMsg);
            }
        } finally {
            DBManager.closeDBStatement(stmt);
        }
    }

    /**
     * �˷�������������ȡ�ع���
     * ���ô˷�����
     * FlowAction fa=new FlowAction(conn);
     * FlowDTO dto=new  FlowDTO();
     * dto.setActId(actId); �������̼�¼ID
     * dto.setSessionUserId(userId);���õ�ǰ�û�
     * dto.setApproveContent()�����������Ĭ��Ϊ��ȡ�ء�
     * fa.setDto(dto);
     * fa.getBack();
     */
    public void getBack() throws SQLException {
        CallableStatement cStmt = null;
        try {
            String sql = "{call SF_FLOW_MAIN_PKG.GET_BACK(?,?)}";
//            String sql = "{call SF_FLOW_MAIN_PKG.GET_BACK(?,?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.setString(1, dto.getActId());
//            cStmt.setString(2, dto.getSessionUserId());
//            cStmt.setString(3, dto.getApproveContent());
//            cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
            cStmt.execute();
            String errorMsg = cStmt.getString(2);
            if (errorMsg != null && !errorMsg.equals("")) {
                throw new SQLException(errorMsg);
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    /**
     * ȡ��ת���̵Ŀ����룺flow_code
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    public String getFlowCode() throws QueryException, ContainerException {
        return getFlowCode(dto.getCurTaskId(),dto.getToTaskId());
    }

    /**
     * ȡ�����ڵ�����̵�����Ϣ
     * @param curTaskId
     * @param nextTaskId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    public String getFlowCode(String curTaskId,String nextTaskId) throws QueryException, ContainerException {
        String flowCode="";
        if(curTaskId.equals("")){
            curTaskId=getFirstTask(dto.getProcId()).getTaskId();
        }
        SQLModel sqlModel=new SQLModel();
        sqlModel=model.getFlowDefine(curTaskId,nextTaskId);
        SimpleQuery simpleQuery=new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
            flowCode=simpleQuery.getFirstRow().getStrValue("FLOW_CODE");
        }
        return  flowCode;
    }
}