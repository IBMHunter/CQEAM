package com.sino.sinoflow.todo.constant;

/**
 * 
 * @ϵͳ����: 
 * @��������: OA_TODO
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 30, 2011
 */
public interface HNOAConstant {
	
	
	//д��OA��һЩ����
	String OA_TODO_SYSID = "1009";			//ϵͳID������Ϊ1��BPMΪ2��ͳһ������䣩����ͬ����Ϊ1004���ʲ�ϵͳΪ1009
	String OA_TODO_SOURCE_ID = "PR";	//Ӧ����Դ,ʡ��Ӧ��Ϊ��PR
	//���촦������ȼ���
	String OA_TODO_PRI_LEVEL_ONE = "1"; 		//��ͨ
	String OA_TODO_PRI_LEVEL_TWO = "2";		//����
	String OA_TODO_PRI_LEVEL_THREE = "3";	//�ؼ�
	String OA_TODO_PRI_LEVEL_FOUR = "4";
	String OA_TODO_PRI_DEFAULT = OA_TODO_PRI_LEVEL_ONE;
	
	String OA_TODO_DOC_TYPE = "�ʲ�����ϵͳ";
	
	//�������Ѱ졢ע��
	String OA_TODO_TYPE_OPEN = "1";	//OPEN
	String OA_TODO_TYPE_CLOSE = "2";  	//CLOSE
	String OA_TODO_TYPE_CANCEL = "6"; //CANCEL
	
	String M_CONTENT_TYPE = "text/html; charset=GBK";
	
	//��־״̬(��ʼ/����)
	String TODO_LOG_TYPE_START = "START";
	String TODO_LOG_TYPE_END = "END";
	
	
	String RESULT_CODE_SUCCESS = "1";
	String RESULT_CODE_FAILURE = "-1";
	
	String WS_CON_TIMEOUT = "30000";
	String WS_REV_TIMEOUT = "30000";
	
	long OA_TODO_THREAD_SLEEP_TIME = 60000;
}
