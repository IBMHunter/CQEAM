package com.sino.sinoflow.constant;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public interface WebAttrConstant {

    String TRUE_VALUE = "Y";
    String FALSE_VALUE = "N";

    String ROOT_RESOURCE = "ROOT_RESOURCE";//�û���Ȩ���ʵĸ��˵���Դ
    String MENU_RESOURCE = "MENU_RESOURCE";//�û���Ȩ���ʵ���߲˵���Դ
    String FIRST_RESOURCE = "FIRST_RESOURCE";//�û���Ȩ���ʵĵ�һ�������˵�
    String MENU_OPTION = "MENU_OPTION";   //����Ŀ
    String SMALL_MENU_OPTION = "SMALL_MENU_OPTION";//����Ŀ
    String TRANS_TYPE="TRANS_TYPE";  //��������
    String RES_DATA = "RES_DATA";
    String RESOURCE_OPTION = "RESOURCE_OPTION";//�˵�ѡ��
    String ALL_ROLE_OPTION = "ALL_ROLE_OPTION";//���еĽ�ɫ

    String OTHER_ROLE_OPTION = "OTHER_ROLE_OPTION";//����δѡ��ɫ
    
    String ALL_GROUP_OPTION = "ALL_GROUP_OPTION";//���е����
    String VIEW_ROLE_OPTION = "VIEW_ROLE_OPTION";//��ѡ��ɫ
    String VIEW_GROUP_OPTION = "VIEW_GROUP_OPTION";//��ѡ���
    String MENU_TREE = "MENU_TREE";//��Ŀ���������β˵�
    String RES_PRIV_DTO = "RES_PRIV_DTO";//��ĿȨ������

    String ENABLED_OPTION = "ENABLED_OPTION";//��Ч������ѡ��
    String GROUP_OPTION = "ALL_GROUP_OPTION";//���ѡ��
	String ROLE_OPTION = "ROLE_OPTION";//��ɫ������
    String FLOW_GROUP_OPTION = "FLOW_GROUP_OPTION";  //�������
    String GROUP_CATEGORY = "GROUP_CATEGORY";   //���רҵ
    String GROUP_DTOSET = "GROUP_DTOSET";//���DTOSET
    String TASK_FLOW_ROWSET = "TASK_FLOW_ROWSET"; //����-���� ROWSET
    String MULTI_TASK_FLOW_ROWSET = "MULTI_TASK_FLOW_ROWSET"; //����-���� ROWSET
    String REVIEW_USER_ROWSET = "REVIEW_USER_ROWSET"; //��ǩ��Ա����� ROWSET
    String REVIEW_STATUS_ROWSET = "REVIEW_STATUS_ROWSET"; //��ǩ��Ա����� ROWSET
    String CYCLE_USER_ROWSET = "CYCLE_USER_ROWSET"; //��ǩ��Ա����� ROWSET
    String CYCLE_USERS_INFO = "CYCLE_USERS_INFO";       //��ǩ��Ա״̬��Ϣ
    String CYCLE_TYPE_INFO = "CYCLE_TYPE_INFO";
    String CYCLE_ALLUSERS_INFO = "CYCLE_ALLUSERS_INFO";
    String CYCLE_SELECTEDUSERS_INFO = "CYCLE_SELECTEDUSERS_INFO";
    String DEPT_GROUP_ATTR = "DEPT_GROUP_ATTR"; //��������Ӧ��Ϣ
    String GROUP_ATTR = "GROUP_ATTR";//�����Ϣ
    String ROLE_ATTR = "ROLE_ATTR";//��ɫ��Ϣ
    String USER_ATTR = "USER_ATTR";//�û���Ϣ
    String ORG_GROUP_ATTR = "";//��֯�������Ϣ--�û�ά����
    String ALL_ROLE_ATTR = "ALL_ROLE_ATTR";//���н�ɫ��Ϣ--�û�ά����

    String OPERATOR_ATTR = "OPERATOR_ATTR";
    
    String BACKMANAGER_ATTR="BACKMANAGER_ATTR";//�ŷ��ս����������Ϣ

    //---------------------------------------------------------------------------
    String PROCEDURE_ATTR = "PROCEDURE_ATTR";//������Ϣ
    String PROCEDURE_TEMP_FILE = "PROCEDURE_TEMP_FILE";

    //-----------------------------�ֵ�ά������-----------------------------------//
    String FIRST_DICT = "FIRST_DICT";//
    String TREE_HTML = "TREE_HTML";
    String TREE_JS = "TREE_JS";
    String DICT_TYPE_DATA = "DICT_TYPE_DATA";
    String DICT_DATA = "DICT_DATA";
    String IS_INNER_RADIO = "IS_INNER_RADIO";//�Ƿ����õ�ѡ��ť
    String ENABLED_RADIO = "ENABLED_RADIO";//��Ч�Ե�ѡ��ť
    String MAINTAIN_RADIO = "MAINTAIN_RADIO";//��ά���Ե�ѡ��ť
    String DICT_PARENT_OPT = "DICT_PARENT_OPT";//�ֵ����������

    //-----------------------------------------ͬ������--------------------------------------------//
    String SYSCHRONIZE_DTO = "SYSCHRONIZE_DTO";
	String MESSAGE_DATA = "MESSAGE_DATA";

	String EFFECTIVE_Y = "Y";
	String EFFECTIVE_N = "N";
	String EFFECTIVE_YES = "��";
	String EFFECTIVE_NO = "��";
	String[] EFFECTIVE_CAP = {EFFECTIVE_YES, EFFECTIVE_NO};
	String[] EFFECTIVE_VAL = {EFFECTIVE_Y, EFFECTIVE_N};

	String RESEND_Y = "Y";
	String RESEND_N = "N";
	String RESEND_YES = "��";
	String RESEND_NO = "��";
	String[] RESEND_CAP = {RESEND_YES, RESEND_NO};
	String[] RESEND_VAL = {RESEND_Y, RESEND_N};

	String COLLECT_Y = "Y";
	String COLLECT_N = "N";
	String COLLECT_YES = "��";
	String COLLECT_NO = "��";
	String[] COLLECT_CAP = {COLLECT_YES, COLLECT_NO};
	String[] COLLECT_VAL = {COLLECT_Y, COLLECT_N};

    //---------------------------------------����ʹ�� DTO-----------------------------------------//
    String SF_APPLICATION_DTO = "SF_APPLICATION";   // Ӧ�ö�����Ϣ
    String SF_ACT_INFO_DTO = "SF_ACT_INFO";          // ��ת��Ϣ
    String SF_CASE_INFO_DTO = "SF_CASE_INFO"; 

    String SF_ACTIDS_INFO = "SF_ACTIDS_INFO";

    String SF_ACT_PAUSE_DTO = "SF_ACT_PAUSE";

    //	-----------------------------------------����ά��--------------------------------------------//
	String PROJECT_OPTION_STR = "PROJECT_OPTION_STR";//���� OPTION HTML�� �����
	String GROUP_OPTION_STR = "GROUP_OPTION_STR";//����OPTION HTML�� �����

	String PROJECT_OPTION_STR_ALL = "PROJECT_OPTION_STR_ALL"; //�����Ƿ�ѡ��,�����б��ѡ���Ƿ���ѡ��״̬
    String PROCEDURE_OPTION_STR_SELECT = "PROCEDURE_OPTION_STR_SELECT";
    String GROUP_OPTION_STR_SELECT = "GROUP_OPTION_STR_SELECT"; //����Ƿ�ѡ��,�����б��Ƿ���ѡ��״̬
	String ROLE_OPTION_STR_SELECT = "ROLE_OPTION_STR_SELECT";//��ɫ�Ƿ�ѡ�У������б��Ƿ���ѡ��״̬

	String PROJECT_GROUP_OPTION_STR = "PROJECT_GROUP_OPTION_STR"; //��ѡ��������Ӧ�����ʵ�ֲ˵�����
	String PROJECT_ROLE_OPTION_STR = "PROJECT_GROUP_OPTION_STR"; //��ѡ��������Ӧ�Ľ�ɫ��ʵ�ֲ˵�����
	String USER_OPTION_STR = "USER_OPTION_STR"; //�û������б�
	String WORKSCHEDULE_ATTR = "WORKSCHEDULE_ATTR"; 
	String APP_ATTR = "APP_ATTR";
	String PROJECT_PROCEDURE_OPTION = "PROJECT_PROCEDURE_OPTION"; 
	String DELEGATION_ATT = "DELEGATION_ATT";
	String API_ATTR = "API_ATTR";
    String API_OPTION_STR = "API_OPTION_STR";
    String VALIDATION_ATTR = "VALIDATION_ATTR";
	String AUTOVALUE_ATTR = "AUTOVALUE_ATTR";
	String ADMINAUTHORITY_ATTR = "ADMINAUTHORITY_ATTR";
	String PROCEDURE_TASK_OPTION_STR = "PROCEDURE_TASK_OPTION_STR";//��Ӧ�����µ�����
	String HOLIDAYS_OPTION_STR = "HOLIDAYS_OPTION_STR";//�ڼ��������б�
	String WORKHOUR_OPTION_STR = "WORKHOUR_OPTION_STR";//����ʱ��

    String APP_DATAID = "app_dataID";
    String SF_APPDATAID = "sf_appDataID";
    String SINOFLOW_WEB_OBJECT = "sf_object";
    String SINOFLOW_STATUS = "SINOFLOW_STAUTS";
    String SINOFLOW_NEW_CASE = "SINOFLOW_NEW_CASE";

    String SINOFLOW_CURSTATUS = "SINOFLOW_CURSTATUS";

    //���̹�����
    String SF_KEYWORD = "SF_KEYWORD";
    String SF_SUBJECT = "SF_SUBJECT";
    String SF_RANGE = "SF_RANGE";
    String SF_OTHERS = "SF_OTHERS";
    String FROM_DATE = "FROM_DATE";
    String TO_DATE = "TO_DATE";
    String SORT_TYPE = "SORT_TYPE";
    String SORT_TYPE_STR = "SORT_TYPE_STR";
    String TYPE = "TYPE";
    String SF_CREATEBY = "SF_CREATEBY";
    String SF_INTRAY_TOP_LIST = "SF_INTRAY_TOP_LIST";
    String SF_PENDINGTRAY_TOP_LIST = "SF_PENDINGTRAY_TOP_LIST";
    String SF_OUTTRAY_TOP_LIST = "SF_OUTTRAY_TOP_LIST";
    String SF_INTRAY_BOTTOM_LIST = "SF_INTRAY_BOTTOM_LIST";
    String SF_PENDINGTRAY_BOTTOM_LIST = "SF_PENDINGTRAY_BOTTOM_LIST";
    String SF_OUTTRAY_BOTTOM_LIST = "SF_OUTTRAY_BOTTOM_LIST";

    String SF_ACT_COPY_INFO = "SF_ACT_COPY_INFO";
    String SF_NOTICESTRAY_LIST = "SF_NOTICESTRAY_LIST";
}

