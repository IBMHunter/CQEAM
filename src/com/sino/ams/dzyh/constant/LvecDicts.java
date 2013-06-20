package com.sino.ams.dzyh.constant;

import com.sino.ams.newasset.constant.AssetsDictConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface LvecDicts extends AssetsDictConstant{
	String TASK_STATUS = "TASK_STATUS"; //����״̬
	String CHECK_TYPE = "CHECK_TYPE";//�������
	String CHKORDER_STATUS = "CHKORDER_STATUS";

	/*==========================����״̬========================*/
	String TSK_STATUS1_NEW = "ADD_NEW";
	String TSK_STATUS1_SAVE_TEMP = "SAVE_TEMP";
	String TSK_STATUS1_CANCELED = "CANCELED";
	String TSK_STATUS1_OPENING = "OPENING";
	String TSK_STATUS1_TIMOUT = "TIMED_OUT";

	String TSK_STATUS2_NEW = "����";
	String TSK_STATUS2_SAVE_TEMP = "�ݴ�";
	String TSK_STATUS2_CANCELED = "��ȡ��";
	String TSK_STATUS2_OPENING = "������";
	String TSK_STATUS2_CLOSED = "�ѹ���";

	String CHECK_BATCH = "CHK-BAT";//

	/*==========================�������===============================*/
	String CHK_TYPE1_DZYH = "DZYH";//DZYH
	String CHK_TYPE1_YQYB = "YQYB";//�Ǳ��̵�
	String[] TASK_TYPE1_LIST = {CHK_TYPE1_DZYH, CHK_TYPE1_YQYB};

	String CHK_TYPE2_DZYH = "�ͺ��̵�";//��ֵ�׺��̵�
	String CHK_TYPE2_YQYB = "�Ǳ��̵�";//�����Ǳ��̵�
	String[] TASK_TYPE2_LIST = {CHK_TYPE2_DZYH, CHK_TYPE2_YQYB};

	/*==========================�������===============================*/
	String ORD_TYPE1_DZYH = "DZYH-CHK";//�ͺ��̵�
	String ORD_TYPE1_YQYB = "YQYB-CHK";//�Ǳ��̵�
	String ORD_TYPE1_DHBS = "DHBS-CHK";//�ͺĲ�ɨ
	String[] ORD_TYPE1_LIST = {ORD_TYPE1_DZYH, ORD_TYPE1_YQYB, ORD_TYPE1_DHBS};

	String ORD_TYPE2_DZYH = "�ͺ��̵�";//��ֵ�׺��̵�
	String ORD_TYPE2_YQYB = "�Ǳ��̵�";//�����Ǳ��̵�
	String ORD_TYPE2_DHBS = "�ͺĲ�ɨ";//�ͺĲ�ɨ
	String[] ORD_TYPE2_LIST = {ORD_TYPE2_DZYH, ORD_TYPE2_YQYB, ORD_TYPE2_DHBS};

	/*==========================����״̬========================*/

	String ORDER_STS1_NEW = "ADD_NEW";
	String ORDER_STS1_SAVE_TEMP = "SAVE_TEMP";
	String ORDER_STS1_CANCELED = "CANCELED";
	String ORDER_STS1_DISTRIBUTED = "DISTRIBUTED";
	String ORDER_STS1_DOWNLOADED = "DOWNLOADED";
	String ORDER_STS1_UPLOADED = "UPLOADED";
	String ORDER_STS1_ARCHIEVED = "ARCHIEVED";

	String ORDER_STS2_NEW = "����";
	String ORDER_STS2_SAVE_TEMP = "�ݴ�";
	String ORDER_STS2_CANCELED = "��ȡ��";
	String ORDER_STS��_DISTRIBUTED = "���·�";
	String ORDER_STS��_DOWNLOADED = "������";
	String ORDER_STS2_UPLOADED = "������";
	String ORDER_STS��_ARCHIEVED = "�ѹ鵵";

	/*==========================��鹤��״̬========================*/

	String SCAN_REMARK = "PDA����������";

	String LOCATION_CATEGORY_DZYH = "99";
	String LOCATION_CATEGORY_YQYB = "98";

	String CHECK_TOOLS = "CHECK_TOOLS";
	String CHECK_TOOLS1_PDA = "0";//PDAȷ��
	String CHECK_TOOLS1_WEB = "1";//WEBȷ��

	String CHECK_TOOLS2_PDA = "PDAȷ��";//PDAȷ��
	String CHECK_TOOLS2_WEB = "WEBȷ��";//WEBȷ��
	String ASORD_LINE_ARCH_REMARK = "�ͺĲ�ɨ�����Զ��鵵";
	String ASORD_ITMCODE_ARCH_REMARK = "�ͺĲ�ɨ���������豸����";
	String FIN_PROP_DZYH = "DZYH";//��ֵ�׺ģ�
	String ORDER_CATEGORY_4 = "4";//�����豸��ʷ��ĵ�������
	String INSTRU_ARCH_REMARK = "�����Ǳ��̵㹤��WEBȷ�Ϸ�ʽ����";
	String LOG_CHG_TYPE = "TYPE_CHECK";

	String YB_BR_STATUS1_ADD = "YB_BR_STATUS_ADD";//����
	String YB_BR_STATUS1_WAPPROVE = "YB_BR_STATUS_WAPPRO";//����
	String YB_BR_STATUS1_CANCEL = "YB_BR_STATUS_CANCEL";//��ȡ��
	String YB_BR_STATUS1_APPROVE = "YB_BR_STATUS_APPROVE";//����
	String YB_BR_STATUS1_NAPPRO = "YB_BR_STATUS_NAPPRO";//����δͨ��
	String YB_BR_STATUS1_BORROW = "YB_BR_STATUS_BORROW";//���
	String YB_BR_STATUS1_CLOSE = "YB_BR_STATUS_CLOSE";//�ر�
	String YB_BR_STATUS1_RETURN = "YB_BR_STATUS_RETURN";//����

	String YB_BR_STATUS2_ADD = "����";//����
	String YB_BR_STATUS2_WAPPROVE = "����";//����
	String YB_BR_STATUS2_CANCEL = "��ȡ��";//��ȡ��
	String YB_BR_STATUS2_APPROVE = "����";//����
	String YB_BR_STATUS2_NAPPRO = "����δͨ��";//����δͨ��
	String YB_BR_STATUS2_BORROW = "���";//���
	String YB_BR_STATUS2_CLOSE = "�ر�";//�ر�
	String YB_BR_STATUS2_RETURN = "����";//����

	String CATEGORY_YQYB = "YQYB";//�����Ǳ�
	String CATEGORY_DZYH = "DZYH";//��ֵ�׺�

	String ROLE_INSTR_APP_NM = "�Ǳ�������";
}
