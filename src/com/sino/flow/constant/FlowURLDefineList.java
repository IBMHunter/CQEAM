package com.sino.flow.constant;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-18
 * Time: 13:55:24
 */
public interface FlowURLDefineList {
    String NEXT_TASK_PAGE = "/flow/nextTask.jsp";
    String NEXT_POSITION_USER_PAGE = "/flow/nextPositionUser.jsp";
    String USER_FIND_SERVLET = "/servlet/com.sino.flow.servlet.ApproveUserFindServlet";
    String ERROR_PAGE = "/flow/errorPage.jsp";
    String TASK_FIND_SERVLET = "/servlet/com.sino.flow.servlet.TaskFindServlet";
    String INBOX_PAGE="/flow/inbox.jsp";
    String INBOX_SERVLET="/servlet/com.sino.flow.servlet.InboxServlet";
    String APPROVE_CONTENT_PAGE="/flow/approveContent.jsp";//�������ҳ��
    String ALERT_AREA_PAGE="/flow/alertArea.jsp";//������ҳ��
    String OUT_BOX_PAGE="/flow/outbox.jsp";//������ҳ��.
    String PERSONAL_PAGE="/flow/personal.jsp";//���˵��ݲ�ѯ
    String CANCEL_PAGE="/flow/cancel.jsp";//��������ȡ��,
    String DEPT_POSITION_PAGE="/flow/deptPosition.jsp";//ѡ�Լ���ǰ�Ĳ���ְλ
    String USER_AGENCY_MANAGE_PAGE="/flow/userAgencyManage.jsp";//������
    String USER_AGENCY_SERVLET="/servlet/com.sino.flow.servlet.UserAgencyServlet" ;
    String USER_AGENCY_DETAIL_PAGE="/flow/userAgencyDetail.jsp";
}
