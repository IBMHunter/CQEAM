package com.sino.ams.newasset.urgenttrans.constant;

/**
 * 
 * @ϵͳ����: �ʲ�����ϵͳ
 * @��������: ��������
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Aug 1, 2011
 */
public interface UrgentAppConstant {
	String PROC_NAME = "urgentTransApp";
	String TRANS_TYPE = "ASS-TRS";
	String TRANS_TYPE_NAME = "���������"; //�ʲ�����������
	
	String ATT3_FILL_DATA = "FILL_DATA";	 //���� 
	String ATT3_ASS_APPROVE = "ASS_APPROVE"; //�����ʲ�����Ա����
	String ATT3_ASS_APPROVED = "ASS_APPROVED";	 //���ž������ 
	String ATT3_SUPER_ASS_APPROVE = "SUPER_ASS_APPROVE";//�ϼ����ܲ����ʲ�����Ա���
	
	String FLOW_CODE_END = "END";
	
	//PDA����
	String PDA_CREATE_TYPE_NAME = "��������"; //�ʲ�����������
	int DOWNLOAD_TYPE_OUT = 1;
	int DOWNLOAD_TYPE_IN = 2;
	String OUT_ORDER_NAME = "��������";
	String IN_ORDER_NAME = "��������";
	
	String STATUS_TRANS_OUT = "TRANS_OUT"; //����
	String STATUS_TRANS_IN = "TRANS_IN";   //����
	String STATUS_ARCHIVED = "ARCHIEVED";   //�鵵
	
	/**
	 *  ��ʷ�������ֵ
	 */
	String ORDER_DTL_URL = "";
	String ORDER_CATEGORY = "3";
}
