package com.sino.sinoflow.constant;

import com.sino.base.constant.web.WebActionConstant;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 */
public interface URLDefineList {

    //==================================�����ҳ��===========================================//
    String LOGIN_PAGE = "/login.jsp"; //��½ҳ��
    String COMMON_MSG_PAGE = "/publicMessage.jsp";//��Ϣ��ʾҳ�棬����ҳ�����include����ʽ������ҳ�档
    String FIRST_LOGIN_PAGE = "/firstLogin.jsp"; //��MIS�û��״ε�¼�޸ĳ�ʼ����
    String PORTAL_SERVLET = "/servlet/com.sino.ams.log.servlet.PortalLoginServlet"; //portal��֤Servlet
    String LOGIN_FRM_SERVLET = "/servlet/com.sino.ams.log.servlet.LoginFrmServlet";
    String HOME_PAGE = "/mainPage.jsp"; //�����ҳ��
    String HOME_PAGE_TRAY = "/mainPage.jsp?home=1"; //�����ҳ��--��ҳΪ��������
    String TIME_OUT_PAGE = "/timeOutPage.jsp"; //�Ự����ҳ��
    String NO_PRIVI_PAGE = "/noPriviPage.jsp"; //��Ȩ��ҳ��
    String ERROR_PAGE = "/errorPages/errorPage.jsp"; //����ҳ��
    String TOP_PAGE = "/topPage.jsp"; //�����˵�ҳ��
    String MENU_PAGE = "/resourceMenu.jsp"; //���˵���ҳ��
    String SUCCESS_PAGE = "/public/successPage.jsp"; //�����ɹ�����ҳ��
    String TEMP_PAGE = "/temPage.jsp"; //��ʱҳ�棬���ڶ��忪����Ա�Ĺ�������
    String WINDOW_CLOSE_PAGE = "/windowClose.jsp"; //ת��ر�ҳ�棬�ɴ�����ֵ retValue
    String WINDOW_SAVE_PROBLEM_PAGE = "/flow/saveError.jsp"; //���������ʾ
    String INDEX_PAGE = "/home.jsp";//��ҳ��Ϣ
    String GET_MENU_PAGE = "/getMenuPage.jsp";//������Ŀ
    //==================================�����ҳ��===========================================//

    //==================================��Ŀά����ҳ��===========================================//
    String RES_FRM_PAGE = "/system/resource/resourceFrm.jsp";//�ƹ�����
    String RES_QRY_PAGE = "/system/resource/resourceQuery.jsp";//�ƹ�����
    String RES_TREE_PAGE = "/system/resource/resourceTree.jsp";//�ƹ�����
    String RES_DTL_PAGE = "/system/resource/resourceDetail.jsp";//�ƹ�����
    String RES_QRY_SERVLET = "/servlet/com.sino.sinoflow.framework.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.QUERY_ACTION;
    String RES_NEW_SERVLET = "/servlet/com.sino.sinoflow.framework.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.NEW_ACTION;
    String RES_DTL_SERVLET = "/servlet/com.sino.sinoflow.framework.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.DETAIL_ACTION;
    String RES_PRIVI_FRM = "/system/resource/resPriviFrm.jsp"; //�ƹ�����
    String RES_PRIVI_TREE = "/system/resource/resPriviTree.jsp"; //�ƹ�����
    String RES_PRIVI_QUERY = "/system/resource/resPriviQuery.jsp";//�ƹ�����
    String PRIVI_QUERY_SERVLET = "/servlet/com.sino.sinoflow.framework.resource.servlet.SfResPrivsServlet?act=" + WebActionConstant.QUERY_ACTION;
    String WORK_PLAN_QUERY = "/plan/workPlanQuery.jsp";   //�����ƻ�ά������ѯ��ҳ��
    String WORK_PLAN_DETAIL = "/plan/workPlanDetail.jsp"; //�����ƻ���ϸҳ��
    String WORK_PLAN_SEARCH = "/plan/workPlanSearch.jsp";//�����ƻ���ѯҳ��
    String WORK_PLAN_PIGEONHOLE = "/plan/workPlanPigeonhole.jsp";//�����ƻ��鵵ҳ��
    String WORK_PLAN_NEW = "/plan/workPlanNew.jsp";//���������ƻ�ҳ��
    String WORK_PLAN_S_DETAI = "/plan/workPlanSDetail.jsp";//�����ƻ���ϸҳ�棨��ѯ��
    String WORK_PLAN_QUERY_SERVLET = "/servlet/com.sino.ams.plan.servlet.AmsWorkPlanServlet?act=" + WebActionConstant.DETAIL_ACTION;

    //==================================��Ŀά����ҳ��===========================================//

    //==============================================�û���Ϣά����ҳ��============================
    String AMSSINO_DEPT_QUERY_PAGE = "/system/group/amsSinoDeptQuery.jsp";
    String DEPT_GROUP_QUERY_PAGE = "/system/group/deptGroupQuery.jsp";
    String GROUP_QUERY_PAGE = "/system/group/groupQuery.jsp";
    String GROUP_SERVLET_PAGE = "/servlet/com.sino.sinoflow.user.servlet.SfGroupServlet?act=" + WebActionConstant.QUERY_ACTION;
    String GROUP_DETAIL_PAGE = "/system/group/groupDetail.jsp";
    String ROLE_QUERY_PAGE = "/system/role/roleQuery.jsp";
    String ROLE_DETAIL_PAGE = "/system/role/roleDetail.jsp";
    String ROLE_QUERY_SERVLET = "/servlet/com.sino.sinoflow.user.servlet.SfRoleServlet?act=" + WebActionConstant.QUERY_ACTION; //�޸�
    String USER_LIST_PAGE = "/system/user/userList.jsp";
    String APP_USER_LIST_PAGE = "/system/user/appUserList.jsp";
    String USER_INFO_DETAIL_PAGE = "/system/user/userInfoDetail.jsp";
    String USER_INFO_LIST_PAGE = "/system/user/userInfoList.jsp";
    String USER_LIST_SERVLET = "/servlet/com.sino.sinoflow.user.servlet.SfUserServlet?act=" + WebActionConstant.QUERY_ACTION;
    String USER_DETAIL_PAGE = "/system/user/userDetail.jsp";
    String APP_USER_DETAIL_PAGE = "/system/user/appUserDetail.jsp";
    String CHOOSE_USER_PAGE = "/workorder/util/chooseUser.jsp";
    String CHANGE_USER_PAGE ="/system/user/changeUserPassword.jsp";  //�޸��û�����ҳ��
    String CHANGE_USERPWD_SERVLET = "/servlet/com.sino.sinoflow.user.servlet.ChangeUserPasswordServlet";  //�޸��û�����

    //==============================================�û���Ϣά����ҳ��============================
    String EMPLOYEE_LIST_PAGE = "/system/employee/employeeList.jsp";  //�쵼��Ϣ¼��ҳ�棬��ҳ�Ĳ�ѯҳ��
    String EMPLOYEE_DETAIL_PAGE = "/system/employee/employeeDetail.jsp";
    String EMPLOYEE_DETAIL_SERVLET = "/servlet/com.sino.sinoflow.employee.servlet.XfEmployeesServlet?act=&modelFlag='Y'";
    
	//=================================���̶���==============================================
    String PROJECT_QUERY_PAGE = "/system/project/projectQuery.jsp";//���̲�ѯҳ��
    String PROJECT_SERVLET = "/servlet/com.sino.sinoflow.user.servlet/SfProjectServlet?act=";//����Servlet
    
    String ADMIN_AUTHORITY_SERVLET = "/servlet/com.sino.sinoflow.servlet.SfAdminAuthorityServlet?act=";//���̹���ԱServlet
    String ADMIN_AUTHORITY_QUERY_PAGE = "/system/adminAuthority/adminAuthorityQuery.jsp";//���̹����ѯҳ��
    String ADMIN_AUTHORITY_DETAIL_PAGE = "/system/adminAuthority/adminAuthorityDetail.jsp";//���̹���Ա����ҳ��
	
	String APPLICATION_DETAIL_PAGE = "/flowTask/applicationDetail.jsp"; //Ӧ�ö���ҳ��
	String APPLICATION_QUERY_PAGE = "/flowTask/applicationQuery.jsp"; //Ӧ�ò�ѯҳ��
	String APPLICATION_SERVLET = "/servlet/com.sino.sinoflow.servlet.SfApplicationServlet?act=";//Ӧ��Servlet
	
	String API_QUERY_PAGE = "/flowTask/apiQuery.jsp";//�ӿڳ����ѯ 
	String API_DETAIL_PAGE = "/flowTask/apiDetail.jsp";//�����ӿ�ҳ��
	String API_SERVLET = "/servlet/com.sino.sinoflow.servlet.SfApiServlet?act=";//�ӿ�Servlet
	
	String VALIDATION_QUERY_PAGE = "/flowTask/validationQuery.jsp"; //�Ϸ��Լ�� ��ѯҳ
	String VALIDATION_DETAIL_PAGE = "/flowTask/validationDetail.jsp"; //�����Ϸ��Լ��ҳ��
	String VALIDATION_SERVLET = "/servlet/com.sino.sinoflow.servlet.SfValidationServlet?act=";//�Ϸ���Servlet";
	
	String WORK_SCHEDULE_QUERY_PAGE = "/flowTask/workScheduleQuery.jsp";//����ʱ����ѯҳ��
	String WORK_SCHEDULE_DETAIL_PAGE = "/flowTask/workScheduleDetail.jsp";//����ʱ�����ҳ��
	String WORK_SCHEDULE_SERLVET = "/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet?act=";//����ʱ�䶨��Servlet
	
	String WORK_HOUR_QUARY = "/flowTask/workHourQuery.jsp";//����ʱ���ѯҳ��
	String WORK_HOUR_DETAIL = "/flowTask/workHourDetail.jsp";//����ʱ�䶨��ҳ��
	String WORK_HOUR_SERVLET = "/servlet/com.sino.sinoflow.servlet.SfWorkHourServlet?act=";//����ʱ��servlet
	
	String HOLIDAYS_DETAIL = "/flowTask/holidaysDetail.jsp";//�ڼ��ն���ҳ��
	String HOLIDAYS_QUARY = "/flowTask/holidaysQuery.jsp";//�ڼ��ղ�ѯҳ��
	String HOLIDAYS_SERVLET = "/servlet/com.sino.sinoflow.servlet.SfHolidaysServlet?act=";//�ڼ���servlet
	
	String AUTO_VALUE_QUERY_PAGE = "/flowTask/autoValueQuery.jsp";//�Զ���ֵ��ѯҳ��
	String AUTO_VALUE_DETAIL_PAGE = "/flowTask/autoValueDetail.jsp";//�Զ���ֵ����ҳ��
	String AUTO_VALUE_SERLVET = "/servlet/com.sino.sinoflow.servlet.SfAutoValueServlet?act=";//�Զ���ֵServlet
	
    String DELEGATION_QUERY_PAGE = "/flowTask/delegationQuery.jsp";//ί�ж����ѯҳ��
    String DELEGATION_QUERY_PAGE2 = "/flowTask/delegationQuery2.jsp";//ί�ж����ѯҳ��
    String DELEGATION_DETAIL_PAGE ="/flowTask/delegationDetail.jsp";//ί�ж���ҳ��
    String DELEGATION_DETAIL_PAGE2 ="/flowTask/delegationDetail2.jsp";//ί�ж���ҳ��
    String DELEGATION_SERLVET = "/servlet/com.sino.sinoflow.servlet.SfDelegationServlet?act=";//ί�ж���Servlet
    String DELEGATION_SERLVET2 = "/servlet/com.sino.sinoflow.servlet.SfDelegationServlet2?act=";//ί�ж���Servlet
	//=================================���̶���==============================================
	String TRASK_QUERY_PAGE = "/traskmoting/traskQueryPage.jsp"; //����ҳ��
	String TRASK_DETAIL_PAGE = "/traskmoting/traskDetailPage.jsp";//��ϸҳ��
	String TRASK_MOTING_QUERY_PAGE ="/traskmoting/traskMiotingQueryPage.jsp";
	String TRASK_USER_MONITING_PAGE ="/traskmoting/traskUserMonitoring.jsp"; //�����û����
	String TRASK_ROLE_MONITING_PAGE = "/traskmoting/traskRoleMonitoring.jsp"; //���ݽ�ɫ
	String TRASK_NAME_MONITING_PAGE = "/traskmoting/traskNameMonitoring.jsp"; //���ݳ�������
	String TRASK_CHU_BAN_PAGE = null;//���ݴ߰�
	String TRASK_MOINTING_DETAIL_PAGE ="/traskmoting/traskMointingDetailPage.jsp";//�����ϸҳ��
	String TRASK_OT_USER_MONITING_PAGE = "/traskmoting/traskOtUserMonitoring.jsp";
	String TRASK_OT_ROLE_MONITING_PAGE = "/traskmoting/traskOtRoleMonitoring.jsp";
	String TRASK_OT_NAME_MONITING_PAGE = "/traskmoting/traskOtNameMonitoring.jsp";
    String TRASK_ENOTE_MONITING_PAGE = "/traskmoting/traskEnoteMonitoring.jsp";

    String NOTIFICATION_TRAY_PAGE = "/flow/NotificationTray.jsp";//�߰����б�ҳ��
	String SFENOTE_DETAIL_PAGE = "/flow/NotificationDetail.jsp";//�߰�����ϸ��ʾҳ��
	String CHECK_BOX_TRAY_PAGE = "/flow/checkBoxTray.jsp";//������б�ҳ��
    String MONITOR_TRAY_PAGE = "/flow/monitorTray.jsp";

    String SFCOPY_DETAIL_PAGE = "/flow/copyDetail.jsp"; //������ϸ��ʾҳ��
}

