package com.sino.flow.constant;

/**
 * <p>Title: SinoCPS</p>
 * <p>Description: �����ƶ����к���ϵͳ�������õ����ֵ䶨��</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public interface FlowConstant {
	String TASK_BEGIN_MARK = "0"; //�����е�һ���ڵ����ʼ�ڵ��ʾ
	String PROC_STATUS_NORMAL = "0"; //���̵�ǰ״̬��0��ʾ����
	String PROC_STATUS_COMPLETE = "2"; //���̵�ǰ״̬��2��ʾ����
	String PROC_STATUS_PAUSE = "3"; //���̵�ǰ״̬��3��ʾ��ͣ
	String TASK_FINISH_MARK = "9999"; //���̽���
	String TASK_STATUS_FINISHED = "1"; //��ǰ�ڵ�����״̬��1��ʾ���
	String TASK_STATUS_NOT_FINISHED = "0"; //��ǰ�ڵ�����״̬ ��0��ʾδ���
	String TASK_PROP_START = "1"; //�ڵ����ԣ�1��ʾ��ʼ�ڵ�
	String TASK_PROP_MEDIATE = "2"; //�ڵ����ԣ�2��ʾ�м�ڵ�
	String TASK_PROP_END = "3"; //�ڵ����ԣ�3��ʾ��ֹ�ڵ�
	String FLOW_CODE_NEXT = "9"; //ͬ�⣬����ת����һ��
	String FLOW_CODE_PREV = "10"; //��ͬ�⣬����ת����һ��
	String ACTIVITY_NEW = "0"; //���̴���
	String ACTIVITY_CANCEL = "1"; //���̳���
	String ACTIVITY_AGAIN = "2"; //���̻���ٴ��ύ����
    final String CANCEL_APPLY = "CANCEL_APPLY";
	String APPLY_STATUS_PASS = "PASS"; //����flow�������ݵĲ�����
	String APPLY_STATUS_NOT_PASS = "NOTPASS"; //����flow�������ݵĲ�����
	String APPLY_STATUS_CANCEL = "CANCEL"; //����flow�������ݵĲ�����
	// String XX="22";

	//**�������ͣ����ݸ���Ӧ������**//
	String PROC_TYPE_CEN = "���к�������";

	//***�����������ڷ�����Ĭ��Ϊ1*//
	String FLOW_TYPE_DEFALUT = "1"; //

	//**�ڵ����ͣ�1��ְλ���ˣ�2 ��userId����***//
	String TASK_TYPE_POSITION = "1";
	String TASK_TYPE_USER = "2";

	//���ŷ��ͷ�ʽ
	String SEND_TYPE_ONCE = "1"; //ʵʱ����
	String SEND_TYPE_GATHER = "2"; //���ܷ���

	//**���ĸ�ϵͳ��������***//
	String MSG_AT_SYSTEM = "���к���ϵͳ�У����µĴ��쵥�ݣ�"; //��������ʱ������ͷ��Ϣ����Ϊÿ��ϵͳ����һ�������д�����
	String FLOW_FILE_PATH = "flow";

	String APPROVE_CONTENT_NEW = "��д����";
	String APPROVE_CONTENT_AGREE = "ͬ��";
	String APPROVE_CONTENT_REJECT = "��ͬ��";
	String APPROVE_CONTENT_END = "ͬ�⣬��������";
	String APPROVE_CONTENT_SUBMIT = "�ύ����";
	String APPROVE_CONTENT_RESUBMIT = "�ٴ��ύ����";
}
