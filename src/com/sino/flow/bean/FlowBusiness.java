package com.sino.flow.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.UploadException;
import com.sino.base.util.StrUtil;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.exception.FlowException;
import com.sino.flow.model.FlowModel;
import com.sino.sms.constant.SMSConstant;
import com.sino.sms.dto.SfMsgDefineDTO;
import com.sino.sms.service.MessageProcessService;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-19
 * Time: 11:40:56
 * @version 1.2
 * @deprecated
 */
public class FlowBusiness {
    private Connection conn;
    private HttpServletRequest req;
    private FlowModel flowModel;
    private String applyId = "";
    private String applyNumber = "";
    private String userId = "";
    private String userName = "";
    private String currTaskId = "";

    /**
     * @param conn        ����
     * @param req
     * @param applyId     Ӧ��ID
     * @param applyNumber Ӧ�ñ��
     * @param i      ��ǰ�û���ID
     */
    public FlowBusiness(Connection conn, HttpServletRequest req, String applyId, String applyNumber, String userId, String userName) throws FlowException {
        this.conn = conn;
        this.req = req;
        this.applyId = applyId;
        this.applyNumber = applyNumber;
        this.userId = userId;
        this.userName = userName;
        this.currTaskId = StrUtil.nullToString(req.getParameter("currTaskId"));
        try {
            this.flowModel = new FlowModel(req, applyId, applyNumber, StrUtil.strToInt(userId));
        } catch (UploadException e) {
            throw new FlowException(e);
        }
    }


    //isSendMsg,�Ƿ��Ͷ���֪ͨ��һ�����ˡ�
    public void flow2Next(String approveContent, boolean isSendMsg) throws FlowException {
        try {
            String actId = req.getParameter("actId");
            if (actId == null || actId.equals("")) {//�����ǵ�һ���ύ����
                add2Flow(isSendMsg);
            } else {
                //step1���������̼�¼
                passApply(actId, approveContent, isSendMsg);
            }
        } catch (DataHandleException e) {
            throw new FlowException(e);
        } catch (UploadException e) {
            throw new FlowException(e);
        } catch (QueryException e) {
            throw new FlowException(e);
        } catch (ContainerException e) {
            throw new FlowException(e);
        }
    }


    //�˵���һ���ڵ�
    public void reject2Begin(String approveContent, boolean isSendMsg) throws FlowException {
        String actId = req.getParameter("actId");
        //step1:�����������
        if (approveContent.equals("")) {
            approveContent = "��ͬ��";
        }
        try {
            if (!lockAct(actId)) {
                throw new DataHandleException("�������Ѿ�����������ˢ��&lt;���쵥��&gt;��");
            }
            //���ˣ��϶����ǵ�һ���ڵ㡣
            //step1:�����������
            addApproveContent(actId, approveContent, false);
            //step2:���sf_act
            completeSfAct4reject2Begin();
            //step3:���actLog�е���һ��������
            completeActLogNextUserId(actId);
            //step4:��ACT��¼���ݵ�LOG��
            act2log(FlowConstant.FLOW_CODE_PREV);
            //step5:����ACT
            updateAct42Begin();
            //step6:��ɵ�ǰ�ڵ�Ķ���
            String cellPhone = getCellphoneByUserId(userId);
            if (!cellPhone.equals("")) {
                MessageProcessService msgService = new MessageProcessService();
                msgService.finishPhoneMessage(conn, actId, cellPhone);
            }
            HashMap hm = getAgentUsers(getCreatedBy(actId));
            //step7: save actInfo
            saveActInfo(actId, hm, "N");
            //step8: save msg
            if (isSendMsg) {
                saveMsg(hm, actId);
            }
        } catch (DataHandleException e) {
            throw new FlowException(e);
        } catch (UploadException e) {
            throw new FlowException(e);
        } catch (QueryException e) {
            throw new FlowException(e);
        } catch (ContainerException e) {
            throw new FlowException(e);
        }
    }

    //�����˻�
    public void reject(String approveContent, boolean isSendMsg) throws FlowException {
        String actId = req.getParameter("actId");
        //step1:�����������
        if (approveContent.equals("")) {
            approveContent = "��ͬ��";
        }
        try {
            if (!lockAct(actId)) {
                throw new DataHandleException("�������Ѿ�����������ˢ��&lt;���쵥��&gt;��");
            }
            addApproveContent(actId, approveContent, false);
            //step2����sf_act���Ϊ���״̬
            completeSfAct();
            //step2:��Ϊ������һ���������п����Ƕ���ˣ���������һ���ڵ��ʱ����ȷ����ֻ�д�ʱ����ȷ����
            completeActLogNextUserId(actId);
            //step3:��act��¼���浽��־��
            act2log(FlowConstant.FLOW_CODE_PREV);
            //step4:����ACT��
            String prevUserId = req.getParameter("prevUserId");//�˵�ǰһ�������ˣ�ǰһ��������ֻ��һ������Ϊ��ͳһ������ת����HashMap��ʽ
            HashMap hm = getAgentUsers(prevUserId);
            updateActForReject();
            //step5:���actInfo��
            saveActInfo(actId, hm, "N");
            //step6:��ɵ�ǰ�ڵ�Ķ��š�
            String cellPhone = getCellphoneByUserId(userId);
            if (!cellPhone.equals("")) {
                MessageProcessService msgService = new MessageProcessService();
                msgService.finishPhoneMessage(conn, actId, cellPhone);
            }
            if (isSendMsg) {//�����Ҫ�����ţ������š�
                saveMsg(hm, actId);
            }
        } catch (DataHandleException e) {
            throw new FlowException(e);
        } catch (UploadException e) {
            throw new FlowException(e);
        } catch (QueryException e) {
            throw new FlowException(e);
        } catch (ContainerException e) {
            throw new FlowException(e);
        }
    }

    /**
     * ����ȡ��ʱ���ã�
     * @throws FlowException
     */
    public void cancelFlow() throws FlowException {
        try {
            String actId = StrUtil.nullToString(req.getParameter("actId"));
            if (actId.equals("")) {
                return;
            }
            if (!lockAct(actId)) {
                throw new DataHandleException("�������Ѿ�����������ˢ��&lt;���쵥��&gt;��");
            }
            addApproveContent(actId, "��������", false);
            //step1:��ɵ�ǰ�ڵ���Ϣ
            completeSfAct();
            //step2:�����һ��
            completeActLogNextUserId(actId);
            //step2: ������־��
            act2log(FlowConstant.FLOW_CODE_NEXT);
            //step3:ɾ��act��
            deleteActInfo(actId);
            deleteAct("");
            //step4:��ɶ���
            String cellPhone = getCellphoneByUserId(userId);
            if (!cellPhone.equals("")) {
                MessageProcessService msgService = new MessageProcessService();
                msgService.finishPhoneMessage(conn, actId, cellPhone);
            }
        } catch (DataHandleException e) {
            throw new FlowException(e);
        } catch (UploadException e) {
            throw new FlowException(e);
        } catch (QueryException e) {
            throw new FlowException(e);
        } catch (ContainerException e) {
            throw new FlowException(e);
        }
    }

    //�����ݴ�
    public void saveTemp() throws FlowException {
        String actId = req.getParameter("actId");
        try {
            if (actId == null || actId.equals("")) {//���actIdΪ�գ������ǵ�һ�������̣�
                //���Ƿ��ظ��ύ
                if (isAdded()) {
                    throw new FlowException("�������Ѿ��ύ�������ظ��ύ��");
                }
                //ȡactId
                actId = getActId();
                //ȡ��ǰ�ڵ㣨����һ���ڵ㣩
                currTaskId = getFirstTaskId();
                //ȡ������
                String procName = getProcNameById();
                //���ڵ�ֻ���ݴ棬�Ծ��ڱ��ڵ㣬��˴˴�����ȡ��һ�����ˣ����ǵ�ǰ������
                //String nextUserId = req.getParameter("nextUserId");
                HashMap agentUser = new HashMap();
                agentUser.put(userId, "");
                flowModel.setProcName(procName);
                flowModel.setCurrTaskId(currTaskId);
                flowModel.setActId(actId);
                createSfAct();
                //createSfActLog();
                //updateSfAct();
                addApproveContent(actId, "��������", true);
                saveActInfo(actId, agentUser, "Y");
            } else {
                //�м�Ľڵ㣬�ݴ治�ô���ʲô
            }
        } catch (SQLException e) {
            throw new FlowException(e);
        } catch (DataHandleException e) {
            throw new FlowException(e);
        } catch (UploadException e) {
            throw new FlowException(e);
        } catch (QueryException e) {
            throw new FlowException(e);
        } catch (ContainerException e) {
            throw new FlowException(e);
        }
    }

    /**
     * �����ύ����ʱ����Ӧ�üӵ�������
     * @param isSendMsg �Ƿ����֪ͨ
     * @throws FlowException
     */
    private void add2Flow(boolean isSendMsg) throws FlowException {
        try {
            if (isAdded()) {
                throw new FlowException("�������Ѿ��ύ�������ظ��ύ��");
            }
            //ȡactId
            String actId = getActId();
            //ȡ��ǰ�ڵ㣨����һ���ڵ㣩
            currTaskId = getFirstTaskId();
            //ȡ������
            String procName = getProcNameById();
            String nextUserId = req.getParameter("nextUserId");

            //ȡ��һ���˵Ĵ����ˣ�����ޣ����ء����������һ�����˵Ķ������
            HashMap agentUser = getAgentUsers(nextUserId);
            flowModel.setProcName(procName);
            flowModel.setCurrTaskId(currTaskId);
            flowModel.setActId(actId);
            createSfAct();
            createSfActLog();
            updateSfAct();
            addApproveContent(actId, "��������", true);
            saveActInfo(actId, agentUser, "N");
            if (isSendMsg) {//�����Ҫ�����š�����֪ͨ
                saveMsg(agentUser, actId);
            }
        } catch (SQLException e) {
            throw new FlowException(e);
        } catch (DataHandleException e) {
            throw new FlowException(e);
        } catch (UploadException e) {
            throw new FlowException(e);
        } catch (QueryException e) {
            throw new FlowException(e);
        } catch (ContainerException e) {
            throw new FlowException(e);
        }
    }

    /**
     * @param msgContent    ����Ϣ����
     * @param userId        �û�ID
     * @param actId         ���̼�¼ID
     * @param msgCategoryId ����Ϣ����
     * @throws DataHandleException
     * @throws QueryException
     * @throws ContainerException
     */
    private void saveMsg(String msgContent, String userId, String actId, String msgCategoryId) throws DataHandleException, QueryException, ContainerException {
        MessageProcessService msgService = new MessageProcessService();
        SfMsgDefineDTO msg = new SfMsgDefineDTO();
        msg.setMsgCategoryId(msgCategoryId);
        msg.setMsgContent(msgContent);
        msg.setActId(actId);
        String cellPhone = getCellphoneByUserId(userId);
        msg.addCellPhone(cellPhone);
        msgService.saveMessage(conn, msg);
    }

    /**
     * ͨ������
     */
    private void passApply(String actId, String approveContent, boolean isSendMsg) throws DataHandleException, UploadException, QueryException, ContainerException {
        String taskProp = req.getParameter("taskProp");  //������ԣ���ʼ���1�������ڵ�Ϊ2
        String nextUserId = req.getParameter("nextUserId");
        String nextTaskId = req.getParameter("nextTaskId");
        HashMap agentUser = null;
        //step0:lock sf_act
        if (!lockAct(actId)) {
            throw new DataHandleException("�������Ѿ�����������ˢ��&lt;���쵥��&gt;��");
        }
        //step1:���������������ݿ�
        boolean isFirstTask = false;
        if (approveContent.equals("")) {
            if (taskProp.equals(FlowConstant.TASK_PROP_START)) {
                approveContent = "�ٴ��ύ����";
                isFirstTask = true;
            } else {
                approveContent = "ͬ��";
            }
        }
        addApproveContent(actId, approveContent, isFirstTask);
        //step2: ��sf_act���м�¼״̬��Ϊ���,
        completeSfAct();
        //step2:��Ϊ������һ���������п����Ƕ���ˣ���������һ���ڵ��ʱ����ȷ����ֻ�д�ʱ����ȷ����
        completeActLogNextUserId(actId);
        //step3:����¼ת����־��
        act2log(FlowConstant.FLOW_CODE_NEXT);
        //step4:  ����sf_act
        if (isEndTask(nextTaskId)) {
            //��������һ����㣬ɾ��ACT��¼
            deleteActInfo(actId);
            deleteAct(actId);
        } else {
            //i�������һ���ڵ㣬����ACT��¼
            //ȡ��һ���˵Ĵ����ˣ�����ޣ����ء�����
            agentUser = getAgentUsers(nextUserId);
            updateActForPass();
            saveActInfo(actId, agentUser, "N");
        }
        //step5:ȡ����ǰ�˵Ķ���֪ͨ ���������ǰ�������Ƕ���˰�����ʱҲӦ��ֻ��һ�ˣ���Ϊ��ǩ�յ�ʱ���Ѿ�ȡ���˱��˵Ķ���֪ͨ��
        String cellPhone = getCellphoneByUserId(userId);
        if (!cellPhone.equals("")) {
            MessageProcessService msgService = new MessageProcessService();
            msgService.finishPhoneMessage(conn, actId, cellPhone);
        }
        //step6:����������һ���ˣ�����������֪ͨ��һ������
        if (isSendMsg) {//�����Ҫ�����ţ������š�
            if (agentUser != null) { //�������Ѿ�û���ˣ���Щֵ���ǿա�
                saveMsg(agentUser, actId);
            }
        }
    }

    //����ֱ��������������̲������ˣ�һ��Ӧ�ò���Ҫ����
    public void flow2End(String actId, String procId, String approveContent, String currTaskId) throws DataHandleException, UploadException, QueryException, ContainerException {
        if (!lockAct(actId)) {
            throw new DataHandleException("�������Ѿ�����������ˢ��&lt;���쵥��&gt;��");
        }
        //step1 add approve opinion
        addApproveContent4Flow2End(actId, approveContent, currTaskId, procId);
        //step2 complete sf_act
        updateAct4flow2End(actId, procId);
        //step3 complete sf_act_log's next_user_id
        completeActLogNextUserId(actId);
        //step4: backup sf_act table
        act2Log4flow2End(actId);
        //step5: delete sf_act_info table
        deleteActInfo(actId);
        //step6: delte sf_act table's record
        deleteAct(actId);
        //step7:finish the msg
        String cellPhone = getCellphoneByUserId(userId);
        if (!cellPhone.equals("")) {
            MessageProcessService msgService = new MessageProcessService();
            msgService.finishPhoneMessage(conn, actId, cellPhone);
        }
    }

    //update sf_act table for method flow to end.
    private void updateAct4flow2End(String actId, String procId) throws DataHandleException {
        DBOperator.updateRecord(flowModel.completeAct2EndModel(actId, procId), conn);
    }

    private void act2Log4flow2End(String actId) throws UploadException, DataHandleException {
        DBOperator.updateRecord(flowModel.getAct2Log4flow2EndModel(FlowConstant.FLOW_CODE_NEXT, actId), conn);
    }

    private void updateActForReject() throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getUpdateSfActForRejectModel(), conn);
    }

    private void updateActForPass() throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getUpdateSfActForPassModel(), conn);
    }

    //update sf_act for reject to begin
    private void updateAct42Begin() throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.updateAct42Begin(), conn);
    }

    /**
     * ����������
     */
    private void addApproveContent(String actId, String approveContent, boolean isFirstTask) throws UploadException, DataHandleException, QueryException, ContainerException {
        if (isFirstTask) {
            if (approveContent == null || approveContent.equals("")) {
                approveContent = "�ٴ��ύ����";
            }
            DBOperator.updateRecord(flowModel.getAddApproveContentModle(actId, userId, approveContent), conn);
        } else {
            //ȡ��ǰ�����ˣ�����session��ȡ����Ϊsession���п����ǵ�ǰ�����˵�ί����
            String currUser = getCurrUser(actId);

            DBOperator.updateRecord(flowModel.getAddApproveContentModle(actId, currUser, approveContent), conn);
        }
    }

    public void addApproveContent4Flow2End(String actId, String approveContent, String currTaskId, String procId) throws QueryException, ContainerException, DataHandleException {
        String currUser = getCurrUser(actId);
        DBOperator.updateRecord(flowModel.addAprroveContent4Flow2End(actId, applyId, currUser, approveContent, currTaskId, procId), conn);
    }

    /**
     * ����SF_ACT��
     */
    private void completeSfAct() throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getCompleteSfActModel(), conn);
    }

    /**
     * ��SF_ACT��¼ת����־��   ���ݼ�¼
     */
    private void act2log(String flowCode) throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getCreateSfActLogModel(flowCode), conn);
    }

    private void deleteAct(String actId) throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getDeleteSfActModel(actId), conn);
    }


    /**
     * �ڵ�ǰ��ת��Ϣ�в����¼
     * ��һ���ύʱ��
     * @throws UploadException
     * @throws DataHandleException
     */
    private void createSfAct() throws UploadException, DataHandleException {
        DBOperator.updateRecord(flowModel.getAddActModel(), conn);
    }

    /**
     * ��ת��Ϣ��־���в����¼
     * ��һ�β���־�����
     * @throws UploadException
     * @throws DataHandleException
     */
    private void createSfActLog() throws UploadException, DataHandleException {
        DBOperator.updateRecord(flowModel.getSfActLogCreateModel(), conn);
    }

    /**
     * ������ת��Ϣ��
     * @throws DataHandleException
     * @throws UploadException
     */
    private void updateSfAct() throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getSfActUpdateModel(), conn);
    }

    /**
     * �����ӵ����̴����к���ȡ��ACTID
     * @return
     * @throws SQLException
     */
    private String getActId() throws SQLException {
        String actId = "";
        SeqProducer sp = new SeqProducer(conn);
        actId = StrUtil.nullToString(sp.getStrNextSeq("SF_ACT_S"));
        return actId;
    }

    /**
     * �����ӵ�����ҳ��û��currTaskId,
     * ���������ͺ�OUȡ�������̵ĵ�һ��TASKID
     */
    private String getFirstTaskId() throws QueryException, ContainerException {
        String currTaskId = "";
        SimpleQuery sq = new SimpleQuery(flowModel.getFirstTaskIdModel(), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            currTaskId = (String) row.getValue("TASK_ID");
        }
        return currTaskId;
    }

    private String getProcNameById() throws QueryException, ContainerException {
        String procName = "";
        SimpleQuery sq = new SimpleQuery(flowModel.getProcNameByIdModel(), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            procName = (String) row.getValue("PROC_NAME");
        }
        return procName;
    }

    /**
     * ��˾�ڲ���Ա������userIdȡ�绰
     * @param userId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    private String getCellphoneByUserId(String userId) throws QueryException, ContainerException {
        String cellphone = "";
        String sql =
                "SELECT IUP.MSG_CELL_PHONE FROM SF_USER_PHONE IUP WHERE IUP.USER_ID = ? AND  IUP.SEND_TYPE = '1'";
        SQLModel sm = new SQLModel();
        ArrayList al = new ArrayList();
        al.add(userId);
        sm.setSqlStr(sql);
        sm.setArgs(al);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            cellphone = (String) row.getValue("MSG_CELL_PHONE");
        }
        return cellphone;
    }

    /**
     * ��Ӧ����ϵ�ˣ�����userId��vendowSiteIdȡCellPhone
     * @param userId
     * @param vendorSiteId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    private String getCellphoneByUserId(String userId, String vendorSiteId) throws QueryException, ContainerException {
        String cellphone = "";
        String sql =
                "SELECT IUP.MSG_CELL_PHONE FROM IES_USER_PHONE IUP WHERE IUP.USER_ID = ? AND IUP.VENDOR_SITE_ID=?";
        SQLModel sm = new SQLModel();
        ArrayList al = new ArrayList();
        al.add(userId);
        al.add(vendorSiteId);
        sm.setSqlStr(sql);
        sm.setArgs(al);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            cellphone = (String) row.getValue("MSG_CELL_PHONE");
        }
        return cellphone;
    }

    /**
     * ͨ��employeeIdȡ�绰����
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    private String getCellphoneByEmployeeId(String employeeId) throws QueryException, ContainerException {
        String cellphone = "";
        String sql =
                "SELECT IUP.MSG_CELL_PHONE\n" +
                        "  FROM IES_USER_PHONE IUP\n" +
                        " WHERE IUP.USER_ID = (SELECT IUV.USER_ID\n" +
                        "                        FROM IES_USER_V IUV\n" +
                        "                       WHERE IUV.EMPLOYEE_ID = ?\n" +
                        "                         AND ROWNUM < 2)\n" +
                        "   AND IUP.VENDOR_SITE_ID " + SyBaseSQLUtil.isNullNoParam() + " ";

        SQLModel sm = new SQLModel();
        ArrayList al = new ArrayList();
        al.add(employeeId);
        sm.setSqlStr(sql);
        sm.setArgs(al);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            cellphone = (String) row.getValue("MSG_CELL_PHONE");
        }
        return cellphone;
    }

    private String getApplyNumberByActId(String actId) throws QueryException, ContainerException {
        String applyNumber = "";
        String sql =
                "SELECT SA.APPLY_NUMBER FROM SF_ACT SA WHERE SA.ACTID = ?";
        SQLModel sm = new SQLModel();
        ArrayList al = new ArrayList();
        al.add(actId);
        sm.setSqlStr(sql);
        sm.setArgs(al);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            applyNumber = (String) row.getValue("APPLY_NUMBER");
        }
        return applyNumber;
    }

    //userId,����ֺŷָ��ġ�
    //���ص�hashMap��userId-agentUserId(key-value)
    private HashMap getAgentUsers(String userId) throws QueryException, ContainerException {
        HashMap hm = new HashMap();
        int inndex = userId.lastIndexOf(";");
        if (inndex == -1) {
            String agent = getAgentUserId(userId);
            String[] strArr2 = StrUtil.splitStr(agent, ";");
            if (strArr2.length == 2) {
                hm.put(strArr2[0], strArr2[1]);
            } else {
                hm.put(strArr2[0], "");
            }
        } else {
            String[] strArr = StrUtil.splitStr(userId, ";");
            for (int i = 0; i < strArr.length; i++) {
                String agent = getAgentUserId(strArr[i]);
                String[] strArr2 = StrUtil.splitStr(agent, ";");
                if (strArr2.length == 2) {
                    hm.put(strArr2[0], strArr2[1]);
                } else {
                    hm.put(strArr2[0], "");
                }
            }
        }
        return hm;
    }

    //ȡuserId��Ӧ�Ĵ�����
    private String getAgentUserId(String userId) throws QueryException, ContainerException {
        String agentUserId = "";
        if (userId != null && !userId.equals("")) {
            String sql =
                    "SELECT SFA.USER_ID, SFA.AGENT_USER_ID\n" +
                            "  FROM SF_FLOW_AGENT SFA\n" +
                            " WHERE SFA.USER_ID = ?\n" +
                            "   AND (GETDATE() BETWEEN SFA.ACTIVE_START_DATE AND SFA.ACTIVE_END_DATE)\n" +
                            "   AND SFA.DISABLE_DATE " + SyBaseSQLUtil.isNullNoParam() + " ";

            SQLModel sm = new SQLModel();
            ArrayList al = new ArrayList();
            al.add(userId);
            sm.setArgs(al);
            sm.setSqlStr(sql);
            SimpleQuery sq = new SimpleQuery(sm, conn);
            sq.executeQuery();
            RowSet rs = sq.getSearchResult();
            if (rs != null && !rs.isEmpty()) {
                Row row = rs.getRow(0);
                agentUserId = (String) row.getValue("AGENT_USER_ID");
            }
        }
        return userId + ";" + agentUserId;
    }

    //ͨ��ActIdȡ��ǰ�����ˣ��õ���ֵUserId&&userName
    private String getCurrUser(String actId) throws QueryException, ContainerException {
        String currUser = "";
        String sql =
                "SELECT SAI.USER_ID\n" +
                        "  FROM SF_ACT_INFO SAI\n" +
                        " WHERE SAI.ACT_ID = ?\n" +
                        "   AND (SAI.USER_ID = ? OR SAI.AGENT_USER_ID = ?)";
        SQLModel sm = new SQLModel();
        ArrayList al = new ArrayList();
        al.add(actId);
        al.add(userId);
        al.add(userId);
        sm.setArgs(al);
        sm.setSqlStr(sql);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            currUser = (String) row.getValue("USER_ID");
        } else {
            //���û�ҵ�������ϵͳ�����⣬�������Ѿ��ύ��һ��
            throw new QueryException("�������Ѿ�����������ˢ��&lt;���쵥��&gt;��");
        }
        return currUser;
    }

    /**
     * ������Ϣ��actinfo����
     * @param actId
     * @param hm
     */
    private void saveActInfo(String actId, HashMap hm, String signFlag) throws DataHandleException {
        String sql = "DELETE FROM SF_ACT_INFO SAI WHERE SAI.ACT_ID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        DBOperator.updateRecord(sm, conn);
        if (hm == null) {
            return;
        }
        Iterator iter = hm.keySet().iterator();
        while (iter.hasNext()) {
            String userId = (String) iter.next();
            String agentUserId = (String) hm.get(userId);
            String insertSql = "INSERT INTO SF_ACT_INFO (ACT_ID, USER_ID, AGENT_USER_ID,SIGN_FLAG) VALUES (?, ?, ?,?)";
            ArrayList al2 = new ArrayList();
            al2.add(actId);
            al2.add(userId);
            al2.add(agentUserId);
            al2.add(signFlag);
            SQLModel sm2 = new SQLModel();
            sm2.setSqlStr(insertSql);
            sm2.setArgs(al2);
            DBOperator.updateRecord(sm2, conn);
        }
    }

    private boolean isFirstTask(String taskId) throws QueryException, ContainerException {
        boolean isFirst = false;
        String sql = "SELECT STD.TASK_PROP FROM SF_TASK_DEFINE STD WHERE STD.TASK_ID = ?";
        ArrayList al = new ArrayList();
        al.add(taskId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            String taskProp = (String) row.getValue("TASK_PROP");
            if (taskProp.equals(FlowConstant.TASK_PROP_START)) {
                isFirst = true;
            }
        }
        return isFirst;
    }

    private boolean isEndTask(String nextTaskId) throws QueryException, ContainerException {
        boolean isEndTask = false;
        String sql =
                "SELECT STD.TASK_NAME\n" +
                        "  FROM SF_TASK_DEFINE STD\n" +
                        " WHERE STD.TASK_ID = ?";

        ArrayList al = new ArrayList();
        al.add(nextTaskId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            String taskProp = (String) row.getValue("TASK_NAME");
            if (taskProp.equals("����")) {
                isEndTask = true;
            }
        }
        return isEndTask;
    }

    private void completeActLogNextUserId(String actId) throws DataHandleException {
        DBOperator.updateRecord(flowModel.completeActLogNextUserIdModel(actId), conn);
    }

    private void deleteActInfo(String actId) throws DataHandleException {
        DBOperator.updateRecord(flowModel.deleteActInfoModel(actId), conn);
    }

    //hm��userId-agentUserId��ʽ
    private void saveMsg(HashMap hm, String actId) throws DataHandleException, QueryException, ContainerException {
        String msgContent = FlowConstant.MSG_AT_SYSTEM + applyNumber + "��\nת���ˣ�" + userName;
        //��������˲�Ϊ�գ��������Ѵ�����
        Iterator iter = hm.keySet().iterator();
        while (iter.hasNext()) {
            String userId = (String) iter.next();
            String annouceUserId = userId;
            String agentUserId = (String) hm.get(userId);
            if (agentUserId != null && !agentUserId.equals("")) {
                annouceUserId = agentUserId;//����֪ͨ������
            }
            saveMsg(msgContent, annouceUserId, actId, SMSConstant.MSG_CATEGORY_FLOW);
        }
    }

    private String getCreatedBy(String actId) throws QueryException, ContainerException {
        String createdby = "";
        SimpleQuery sq = new SimpleQuery(flowModel.getCreatedByModel(actId), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            createdby = (String) row.getValue("CREATED_BY");
        }
        return createdby;
    }

    //����SF_ACT for �˵�������
    private void completeSfAct4reject2Begin() throws DataHandleException, UploadException {
        DBOperator.updateRecord(flowModel.getCompleteSfAct4RejectBeginModel(), conn);
    }

    private boolean lockAct(String actId) throws DataHandleException, QueryException, ContainerException {
        boolean success = false;
        //step1 :������������ظ��ύ����һ�ε��޸ı���ȵ���һ����ɺ�,һ����ɺ�Ϳ���select����currTaskId,
        //����ĸ�����Ҫ��Ϊ�˵ȴ���һ�ε�commit;
        String sql = " UPDATE SF_ACT SA SET SA.LOCKED_BY = ?, SA.LOCKED_DATE = GETDATE()\n" +
                " WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(userId);
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        DBOperator.updateRecord(sm, conn);
        String currTaskId = StrUtil.nullToString(req.getParameter("currTaskId"));
        String tableTaskId = "";
        String querysql = "  SELECT SA.CUR_TASK_ID FROM SF_ACT SA WHERE SA.ACTID = ?";
        ArrayList queryAl = new ArrayList();
        queryAl.add(actId);
        SQLModel querySM = new SQLModel();
        querySM.setSqlStr(querysql);
        querySM.setArgs(queryAl);
        SimpleQuery sq = new SimpleQuery(querySM, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            tableTaskId = (String) row.getValue("CUR_TASK_ID");
        }
        //��ҳ���е�currTaskId�����ݿ��е�currTaskIdһ��ʱ������true;
        //��ʱ�����ظ��ύ
        if (tableTaskId.equals(currTaskId)) {
            clearLock(actId);
            success = true;
        } else {
            //������ظ��ύ ������ô˷�����ʱ��Ҫ�ع�����
        }
        return success;
    }

    private void clearLock(String actId) throws DataHandleException {
        String sql = " UPDATE SF_ACT SA SET SA.LOCKED_BY = NULL, SA.LOCKED_DATE =NULL\n" +
                " WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        DBOperator.updateRecord(sm, conn);
    }

    //�ύ�����̣��鿴�Ƿ��ظ��ύ���������ݿ����Ƿ��м�¼������У��ͱ����Ѿ����ӵ�������
    public boolean isAdded() throws QueryException {
        boolean isAdded = false;
        SimpleQuery sq = new SimpleQuery(flowModel.isAddedModel(), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            isAdded = true;
        }
        return isAdded;
    }
    //��һ���ڵ㴦��ʱ���ж����ύ���뻹���ٴ��ύ����
    //  public boolean isAddOpinion(){

    //}
}
