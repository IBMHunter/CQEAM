package com.sino.flow.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.query.WebPageView;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.ReqAttributeList;
import com.sino.flow.dto.FlowDTO;
import com.sino.flow.model.FlowTraceModel;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-24
 * Time: 17:22:54
 */
public class FlowTraceDAO {
    private HttpServletRequest req;
    private Connection conn;

    public FlowTraceDAO(HttpServletRequest req, Connection conn) {
        this.req = req;
        this.conn = conn;
    }

    /**
     * ȡ��ǰ�û��ж��ٴ�������,��ʾ�ھ�������
     * @param currUserId
     */
    public void getInboxTotal(String currUserId) throws QueryException, ContainerException {
        String total = "0";
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getInboxCountModel(currUserId), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            total = (String) row.getValue("TOTAL");
        }
        req.setAttribute(ReqAttributeList.ALERT_AREA_TOTAL, total);
    }

    /**
     * ȡ��ǰ�û��ķ���������
     * @param currUserId
     * @throws QueryException
     * @throws ContainerException
     */
    public void getOutboxTotal(String currUserId) throws QueryException, ContainerException {
        String total = "0";
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getOutBoxCountModel(currUserId), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            total = (String) row.getValue("TOTAL");
        }
        req.setAttribute(ReqAttributeList.OUT_BOX_TOTAL, total);
    }

    /**
     * ȡ������������
     */
    public void getPersonalTotal(String currUserId) throws QueryException, ContainerException {
        String total = "0";
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getPersonalModel(currUserId), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            total = (String) row.getValue("TOTAL");
        }
        req.setAttribute(ReqAttributeList.PERSONAL_TOTAL, total);
    }

    /**
     * ȡ������������
     * @throws QueryException
     * @throws ContainerException
     */
    public void getAllTotal() throws QueryException, ContainerException {
        String total = "0";
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getAllModel(), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            total = (String) row.getValue("TOTAL");
        }
        req.setAttribute(ReqAttributeList.ALL_TOTAL, total);
    }

    /**
     * �����ռ���
     * @param userId
     * @throws QueryException
     */
    public void getInbox(String userId) throws QueryException {
        String applyNumber = StrUtil.nullToString(req.getParameter("applyNumber"));
        String procName = StrUtil.nullToString(req.getParameter("procName"));
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getInboxModel(userId, applyNumber, procName), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.INBOX_DATA, rs);
    }

    /**
     * �ڰ���
     * ȡ�ڰ�������
     * @param userId
     */
    public void getHandle(String userId) throws QueryException {
        String applyNumber = StrUtil.nullToString(req.getParameter("applyNumber"));
        String procName = StrUtil.nullToString(req.getParameter("procName"));
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getHandelModel(userId, applyNumber, procName), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.HANDLE_DATA, rs);
    }

    /**
     * �ռ���+�ڰ���
     * @param userId
     * @throws QueryException
     */
    public RowSet getMyWork(String userId, String actId) throws QueryException {
        String applyNumber = StrUtil.nullToString(req.getParameter("applyNumber"));
        String procName = StrUtil.nullToString(req.getParameter("procName"));
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getMyWorkModel(userId, applyNumber, procName, actId), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * ������
     * @param userId
     * @throws QueryException
     */
    public RowSet getMyWorked(String userId) throws QueryException {
        String fromDate = StrUtil.nullToString(req.getParameter("fromDate"));
        String toDate = StrUtil.nullToString(req.getParameter("toDate"));
        String applyNumber = StrUtil.nullToString(req.getParameter("applyNumber"));
        String procName = StrUtil.nullToString(req.getParameter("procName"));
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getMyWorkedModel(userId, fromDate, toDate, applyNumber, procName), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        return sq.getSearchResult();
    }


    /**
     * ��Ҫ�鵵��
     * @param userId
     * @throws QueryException
     */
    public void getArchieve(String userId) throws QueryException {
        String reportNumber = StrUtil.nullToString(req.getParameter("reportNumber"));
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getArchieveModel(userId, reportNumber), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.INBOX_DATA, rs);
    }

    /**
     * ȡ����ת��������
     * @param userId
     * @throws QueryException
     */
    public void getOutbox(String userId) throws QueryException {
        String fromDate = StrUtil.nullToString(req.getParameter("fromDate"));
        String toDate = StrUtil.nullToString(req.getParameter("toDate"));
        String applyNumber = StrUtil.nullToString(req.getParameter("applyNumber"));
        String procName = StrUtil.nullToString(req.getParameter("procName"));
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getOutboxModel(userId, fromDate, toDate, applyNumber, procName), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.OUTBOX_DATA, rs);
    }

    /**
     * ȡ���˴���������
     * @param userId
     * @throws QueryException
     */
    public void getPersonal(String userId, String fromDate, String toDate, String expenseType, String headerStatus, String reportNumber) throws QueryException {
//        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getPersonalDetailModel(userId,fromDate,toDate), conn);
//        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
//        sq.executeQuery();
//        RowSet rs = sq.getSearchResult();
//        req.setAttribute(ReqAttributeList.PERSONAL_DATA, rs);
        WebPageView view = new WebPageView(req, conn);
        view.setCalPattern(CalendarConstant.LINE_PATTERN);
        view.produceWebData(FlowTraceModel.getPersonalDetailModel(userId, fromDate, toDate, expenseType, headerStatus, reportNumber));
    }

    /**
     * ȡ��ǰ�û�����ȡ��������
     * @param userId
     * @throws QueryException
     */
    public void getCancel(String userId) throws QueryException {
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getCancelModel(userId), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.CANCEL_DATA, rs);
    }

    /**
     * ȡ����ȡ�����������
     * @param userId ��ǰ�û�
     */
    public void getCancelTotal(String userId) throws QueryException, ContainerException {
        String total = "0";
        SimpleQuery sq = new SimpleQuery(FlowTraceModel.getCancelTotalModel(userId), conn);
        sq.setCalPattern(CalendarConstant.LINE_PATTERN);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            total = (String) row.getValue("TOTAL");
        }
        req.setAttribute(ReqAttributeList.CANCEL_TOTAL, total);
    }

    /**
     * ǩ��
     */
    public void signApply(String userId) throws SQLException, DataHandleException, QueryException, ContainerException {
        String[] actId = req.getParameterValues("actId");
        String[] applyNum = req.getParameterValues("applyNum");
        String[] isSign = req.getParameterValues("isSign");
        String retStr = "";
        if (actId != null && actId.length > 0) {
            SignApplyDAO dao = new SignApplyDAO();
            for (int i = 0; i < actId.length; i++) {
                if (isSign[i].equals("Y")) {
                    String flag = dao.sign(actId[i], userId, conn);
                    //���flagΪ0����ʾ�Ѿ�����ǩ�գ������û���Ϣ
                    if (flag.equals("0")) {
                        retStr += applyNum[i] + ";";
                    }
                }
            }
            if (!retStr.equals("")) {
                retStr = "ǩ�ճɹ������������Ѿ�������ǩ�գ�" + retStr;
            } else {
                retStr = "ǩ�ճɹ���";
            }
        }
        req.setAttribute("SIGN_MSG", retStr);
    }

    public void getBack(String userId) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String actId = req.getParameter("actId");
            FlowAction fa = new FlowAction(conn);
            FlowDTO dto = new FlowDTO();
            dto.setActId(actId);
            dto.setSessionUserId(StrUtil.strToInt(userId));
            dto.setApproveContent("ȡ��");
            fa.setDto(dto);
            fa.getBack();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
