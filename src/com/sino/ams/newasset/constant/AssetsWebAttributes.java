package com.sino.ams.newasset.constant;

import com.sino.ams.constant.WebAttrConstant;


/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface AssetsWebAttributes extends WebAttrConstant {
	String ORDER_AUTO_PROD = "���ʱ�Զ�����";
	String PROC_ARG_SCRIPT = "PROC_ARG_SCRIPT"; //��������Ҫ�Ĳ����ű�

	String DEPT_OPTIONS = "DEPT_OPTIONS";
	
	String USER_OPTIONS = "USER_OPTIONS";
	
	String GROUP_OPTIONS = "GROUP_OPTIONS";
    String ITEM_STATUS_OPTIONS = "ITEM_STATUS_OPTIONS";//�豸״̬
    String SHARE_STATUS_OPTIONS = "SHARE_STATUS_OPTIONS";//�豸����״̬
    String ITEM_UNIT_OPTIONS = "ITEM_UNIT_OPTIONS";//������λ
    String ITEM_DISCARD_OPTIONS = "ITEM_DISCARD_OPTIONS";//��������
    String DEAL_TYPE_OPTIONS = "";//��������

    String ORDER_HEAD_DATA = "ORDER_HEAD_DATA";
    String ZERO_TURN_DATA= "ZERO_TURN_DATA";//�㹺�ʲ�
	String ORDER_LINE_DATA = "ORDER_LINE_DATA";
	String PRIVI_DEPT_CODES = "PRIVI_DEPT_CODES";
	String IS_COMPANY_MANAGER = "IS_COMPANY_MANAGER";
	String IS_PROVINCE_MANAGER = "IS_PROVINCE_MANAGER";

    String ADDRESS_HEAD_DATA = "ADDRESS_HEAD_DATA";     //�ص���Ϣ
	
	String ASSETS_TREE_CONFIRM = "ASSETS_TREE_CONFIRM"; //���˴�ȷ���ʲ�
	String ASSETS_TREE_TRANSFER = "ASSETS_TREE_TRANSFER"; //�����ʲ�
	String ASSETS_TREE_DISCARD = "ASSETS_TREE_DISCARD"; //�����ʲ�
	String ASSETS_TREE_CLEAR = "ASSETS_TREE_CLEAR"; //�����ʲ�
	String ASSETS_TREE_PERSON = "ASSETS_TREE_PERSON"; //�����ʲ�
	String ASSETS_TREE_DEPART = "ASSETS_TREE_DEPART"; //�����ʲ�
	String ASSETS_TREE_COMPAN = "ASSETS_TREE_COMPAN"; //��˾�ʲ�
	String ASSETS_TREE_PROVIN = "ASSETS_TREE_PROVIN"; //ȫʡ�ʲ�
	String LOCATION_TREE_QUERY = "LOCATION_TREE_QUERY"; //�ص��ѯ
	String ASSETS_TREE_COMM_QUERY = "ASSETS_TREE_COMM_QUERY"; //�ʲ��ۺϲ�ѯ
	String ASSETS_TREE_CUST_QUERY = "ASSETS_TREE_CUST_QUERY"; //�ʲ��Զ����ѯ

	String RENT_ASSETS_TREE_PERSON = "RENT_ASSETS_TREE_PERSON"; //���������ʲ�
	String RENT_ASSETS_TREE_DEPART = "RENT_ASSETS_TREE_DEPART"; //���������ʲ�
	String RENT_ASSETS_TREE_COMPAN = "RENT_ASSETS_TREE_COMPAN"; //��˾�����ʲ�
	String RENT_ASSETS_TREE_PROVIN = "RENT_ASSETS_TREE_PROVIN"; //ȫʡ�����ʲ�

	String ASSETS_PERSON = "�����ʲ�"; //�����ʲ�
	String ASSETS_DEPART = "�����ʲ�"; //�����ʲ�
	String ASSETS_COMPAN = "��˾�ʲ�"; //��˾�ʲ�
	String ASSETS_PROVIN = "ȫʡ�ʲ�"; //ȫʡ�ʲ�
	String ASSETS_CONFIRM = "��ȷ���ʲ�"; //���˴�ȷ���ʲ�
	String ASSETS_TRANSFER = "�����ʲ�"; //�����ʲ�
	String ASSETS_DISCARD = "�����ʲ�";
	String ASSETS_CLEAR = "�����ʲ�";
	String LOCATION_QUERY = "�ص��ѯ";
	String ASSETS_COMM_QUERY = "�ۺϲ�ѯ";
	String ASSETS_CUST_QUERY = "�Զ����ѯ";

	String RENT_ASSETS_PERSON = "���������ʲ�"; //���������ʲ�
	String RENT_ASSETS_DEPART = "���������ʲ�"; //���������ʲ�
	String RENT_ASSETS_COMPAN = "��˾�����ʲ�"; //��˾�����ʲ�
	String RENT_ASSETS_PROVIN = "ȫʡ�����ʲ�"; //ȫʡ�����ʲ�

	String[] ASSETS_TREE_TYPES = {ASSETS_TREE_PERSON, ASSETS_TREE_DEPART,
								 ASSETS_TREE_COMPAN, ASSETS_TREE_PROVIN,
								 ASSETS_TREE_CONFIRM,
								 ASSETS_TREE_TRANSFER, /*ASSETS_TREE_DISCARD, ASSETS_TREE_CLEAR,*/
								 LOCATION_TREE_QUERY, ASSETS_TREE_CUST_QUERY,
								 ASSETS_TREE_COMM_QUERY};
	String[] CAPTIONS_TREE_TYPES = {ASSETS_PERSON, ASSETS_DEPART, ASSETS_COMPAN,
								   ASSETS_PROVIN, ASSETS_CONFIRM,
								   ASSETS_TRANSFER,
								   /*ASSETS_DISCARD, ASSETS_CLEAR, */
								   LOCATION_QUERY, ASSETS_CUST_QUERY,
								   ASSETS_COMM_QUERY};

	String[] RENT_ASSETS_TYPES = {RENT_ASSETS_TREE_PERSON, RENT_ASSETS_TREE_DEPART,
								 RENT_ASSETS_TREE_COMPAN, RENT_ASSETS_TREE_PROVIN,
								 /*ASSETS_TREE_CONFIRM, ASSETS_TREE_TRANSFER, */
								 /*ASSETS_TREE_DISCARD, ASSETS_TREE_CLEAR,*/
								 /*LOCATION_TREE_QUERY, ASSETS_TREE_CUST_QUERY, ASSETS_TREE_COMM_QUERY*/};
	String[] RENT_CAPTIONS_TYPES = {RENT_ASSETS_PERSON, RENT_ASSETS_DEPART, RENT_ASSETS_COMPAN,
								   RENT_ASSETS_PROVIN, /*ASSETS_CONFIRM,
											  ASSETS_TRANSFER,*/
								   /*ASSETS_DISCARD, ASSETS_CLEAR, */
								   /*LOCATION_QUERY, ASSETS_CUST_QUERY,
											  ASSETS_COMM_QUERY*/};

	String ASSETS_TREE = "ASSETS_TREE"; //�ʲ�̨����
	String ASSETS_DATA = "ASSETS_DATA"; //�ʲ�������Ϣ
	String PRIVI_DATA = "PRIVI_DATA"; //�ʲ�Ȩ������
	String PRIVI_ROLE = "PRIVI_ROLE"; //�ʲ�Ȩ������
	String TREE_CATEGORY = "TREE_CATEGORY"; //�ʲ�����չʾ���
	String TRANSFER_TYPE = "TRANSFER_TYPE"; //���ݵ����������չʾ�����ʲ�
	String PRIVI_RADIO = "PRIVI_RADIO"; //�ʲ�Ȩ������
	String ASSETS_RADIO = "ASSETS_RADIO"; //�ʲ���ť
	String PRIVI_DEPT_TREE = "PRIVI_DEPT_TREE"; //�������νṹ
	String ALL_USER_OPTION = "ALL_USER_OPTION"; //��ѡ�û��б�
	String EXIST_USER_OPTION = "EXIST_USER_OPTION"; //��ѡ�û��б�
	String EXPORT_SELECTED_ASSETS = "EXPORT_SELECTED_ASSETS"; //����ѡ����ʲ�
	String EXPORT_QUERY_ASSETS = "EXPORT_QUERY_ASSETS"; //������ѯ���ʲ�
	String ASSIGN_PERSONS = "ASSIGN_PERSONS"; //MISԱ�������б�
	String ASSIGN_USERS = "ASSIGN_USERS"; //SinoEAMϵͳ�û������б�
	String ASSIGN_LOCATIONS = "ASSIGN_LOCATIONS"; //�ص������б�
	String ASSIGN_DEPTS = "ASSIGN_DEPTS"; //���������б�

	String CHECK_BATCH_DATA = "CHECK_BATCH_DATA"; //�̵�������
	String CHECK_HEADER_DATAS = "CHECK_HEADER_DATA"; //�̵����е��̵㹤������
	String CHECK_HEADER_DATA = "CHECK_HEADER_DATA"; //�̵㹤��ͷ����
	String CHECK_LINE_DATAS = "CHECK_LINE_DATAS"; //�̵㹤��������
	String ORDER_STATUS_OPT = "ORDER_STATUS_OPT"; //�̵㹤��״̬������

	String TRANSFER_TYPE_OPTION = "TRANSFER_TYPE_OPTION"; //����������������
	String DEPRE_ACCOUNT_OPTION = "DEPRE_ACCOUNT_OPTION"; //�۾��˻�������
	String FA_CATEGORY_OPTION = "FA_CATEGORY_OPTION"; //�ʲ����������
	String ALL_QRY_FIELDS = "ALL_QRY_FIELDS"; //��ѡ��ѯ�ֶ�
	String CHK_QRY_FIELDS = "CHK_QRY_FIELDS"; //��ѡ��ѯ�ֶ�
	String ALL_DIS_FIELDS = "ALL_DIS_FIELDS"; //��ѡ��ʾ�ֶ�
	String CHK_DIS_FIELDS = "CHK_DIS_FIELDS"; //��ѡ��ʾ�ֶ�

//�����ۺϲ�ѯ
	String CUST_QUERY_PARA = "CUST_QUERY_PARA"; //��ѯ�ֶι���Ĳ�������
	String COMM_QUERY_HEAD = "COMM_QUERY_HEAD"; //��ʾ�ֶι���������б�ͷ
	String HEADER_DIV_TOP = "HEADER_DIV_TOP"; //��ͷ����붥������
	String DATA_DIV_TOP = "DATA_DIV_TOP"; //���ݲ���붥������
	String TABLE_WIDTH = "TABLE_WIDTH"; //���ݱ����
	String TD_WIDTH = "TD_WIDTH"; //���ݱ���п��
	String DATA_DIV_HEIGHT = "DATA_DIV_HEIGHT"; //���ݲ�߶�
	String ASSETS_DYNAMIC_URL = "ASSETS_DYNAMIC_URL";
	String ASS_PROP = "ASS_PROP";

	String COMM_QUERY_PARA = "COMM_QUERY_PARA"; //��ѯ�ֶι���Ĳ�������--����������ʹ��

//�����ۺϲ�ѯ
	String BARCODE_HISTORY_DATA = "BARCODE_HISTORY_DATA";
	String RENT_HISTORY_DATA = "RENT_HISTORY_DATA";			//�����ʲ������ʷ
	String LOCATION_DATA = "LOCATION_DATA"; //�ʲ��ص�����
	String LOCATION_ASSETS_DATA = "LOCATION_ASSETS_DATA"; //�ص��µ��ʲ�����

	String PRINT_TRANS_OUT = "PRINT_TRANS_OUT"; //�������ݴ�ӡ(���ڵ�����)
	String PRINT_TRANS_IN = "PRINT_TRANS_IN"; //���뵥�ݴ�ӡ(���ڽ��շ�)

	String PRINT_TRANS_OUT_CAP = "������ӡ"; //�������ݴ�ӡ(���ڵ�����)
	String PRINT_TRANS_IN_CAP = "�����ӡ"; //���뵥�ݴ�ӡ(���ڽ��շ�)

	String[] PRINT_TRANS_VALUE = {PRINT_TRANS_OUT, PRINT_TRANS_IN};
	String[] PRINT_TRANS_CAP = {PRINT_TRANS_OUT_CAP, PRINT_TRANS_IN_CAP};
	String PRINT_RADIO = "PRINT_RADIO"; //�������ݴ�ӡ��ѡ��ť

	String MTL_PRIVI_Y = "MTL_PRIVI_Y"; //�Ѹ�Ȩ��
	String MTL_PRIVI_N = "MTL_PRIVI_N"; //����Ȩ��

	String MTL_PRIVI_YES = "�Ѹ�Ȩ��"; //�Ѹ�Ȩ��
	String MTL_PRIVI_NO = "����Ȩ��"; //����Ȩ��

	String[] MTL_PRIVI_VAL = {MTL_PRIVI_Y, MTL_PRIVI_N};
	String[] MTL_PRIVI_CAP = {MTL_PRIVI_YES, MTL_PRIVI_NO};
	String MTL_PRIVI_RADIO = "MTL_PRIVI_RADIO";


	String ASSIGN_COST_CENTER = "ASSIGN_COST_CENTER"; //���䲿��
	String ASSIGN_RESPONSIBLE_USER = "ASSIGN_RESPONSIBLE_USER"; //����������
	String ASSIGN_MAINTAIN_USER = "ASSIGN_MAINTAIN_USER"; //����ʹ����

	String ASS_COST_CENTER = "���䲿��"; //����ɱ�����
	String ASS_RESPONSIBLE_USER = "����������"; //����������
	String ASS_MAINTAIN_USER = "����ʹ����"; //����ʹ����

	String[] ASSIGN_VAL = {ASSIGN_MAINTAIN_USER, ASSIGN_RESPONSIBLE_USER,
						  ASSIGN_COST_CENTER};
	String[] ASSIGN_CAP = {ASS_MAINTAIN_USER, ASS_RESPONSIBLE_USER,
						  ASS_COST_CENTER};
	String ASSIGN_RADIO = "ASSIGN_RADIO";
	String BOOK_TYPE_OPTION = "BOOK_TYPE_OPTION";
	String RCV_ORDER_HEAD = "RCV_ORDER_HEAD"; //���յ�ͷ
	String RCV_ORDER_LINE = "RCV_ORDER_LINE"; //���յ���
	String RCV_APP_CONTENT = "RCV_APP_CONTENT";
	String APPROVE_CONTENT = "APPROVE_CONTENT";
	String BARCODE_LOGS = "BARCODE_LOGS";//��ǩ��ά����־

	String REPORT_DATA = "REPORT_DATA";//��������
    String IS_GROUP_PID = "IS_GROUP_PID";//������
    String FINCE_PROP_MAPS = "FINCE_PROP_MAPS";//ʵ��̨�˲�ѯͳ���ʲ���������
    String TRANS_TYPE_OPTION = "TRANS_TYPE_OPTION";//�������������˵������ڸ��˹�����ѯ
    String MANAGE_INDICATORS_OPTION = "MANAGE_INDICATORS_OPTION";//����ָ���౨������
    String IS_FINANCE_GROUP = "IS_FINANCE_GROUP";//�Ƿ����ڲ���
    String IS_SPECIAL_GROUP = "IS_SPECIAL_GROUP";//�Ƿ�����ʵ�ﲿ��
    
    String TD_ASSETS_TYPE ="TD";

    String ITEM_TREE = "ITEM_TREE";

    String EXPORT_TIP_MSG = "<div id=\"$$$exportTipMsg$$$\" style=\"position:absolute; bottom:45%; left:5; z-index:10; visibility:hidden\">\n"
						  + "\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
						  + "\t\t<tr>\n"
						  + "\t\t\t<td width=\"30%\"></td>\n"
						  + "\t\t\t<td bgcolor=\"#ff9900\">\n"
						  + "\t\t\t\t<table width=\"100%\" height=\"60\" border=\"0\" cellspacing=\"2\" cellpadding=\"0\">\n"
						  + "\t\t\t\t\t<tr>\n"
						  + "\t\t\t\t\t\t<td bgcolor=\"#eeeeee\" align=\"center\">���ڵ������ݣ����Ժ�......<img\n" +
            "                                src=\"/images/wait.gif\" alt=\"\"></td>\n"
						  + "\t\t\t\t\t</tr>\n"
						  + "\t\t\t\t</table>\n"
						  + "\t\t\t</td>\n"
						  + "\t\t\t<td width=\"30%\"></td>\n"
						  + "\t\t</tr>\n"
						  + "\t</table>\n"
						  + "</div>";//��ѯʱʹ�õ���ʾ��Ϣ

}
