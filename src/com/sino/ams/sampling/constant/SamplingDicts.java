package com.sino.ams.sampling.constant;

import com.sino.ams.newasset.constant.AssetsDictConstant;


/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface SamplingDicts extends AssetsDictConstant{
	String SAMPLING_TYPE = "SAMPLING_TYPE";//������
	String TASK_STATUS = "TASK_STATUS";//����״̬

	/*==========================����״̬========================*/
	String TSK_STATUS1_NEW = "ADD_NEW";
	String TSK_STATUS1_SAVE_TEMP = "SAVE_TEMP";
	String TSK_STATUS1_CANCELED = "CANCELED";
	String TSK_STATUS1_OPENING = "OPENING";
	String TSK_STATUS1_CLOSED = "CLOSED";

	String TSK_STATUS2_NEW = "����";
	String TSK_STATUS2_SAVE_TEMP = "�ݴ�";
	String TSK_STATUS2_CANCELED = "��ȡ��";
	String TSK_STATUS2_OPENING = "������";
	String TSK_STATUS2_CLOSED = "�ѹر�";

	/*==========================����״̬========================*/

	/*==========================�������========================*/
	String TSK_TYPE1_NEW = "ASSETS-COUNT";
	String TSK_TYPE1_SAVE_TEMP = "ASSETS-LOCATION";
	String TSK_TYPE1_CANCELED = "BTS-COUNT";

	String TSK_TYPE2_NEW = "���ʲ�����";
	String TSK_TYPE2_SAVE_TEMP = "���ʲ��ص�";
	String TSK_TYPE2_CANCELED = "����վ����";

	/*==========================�������========================*/

	String TASK_NO_MARK = "SAMP-TSK";//���������
	String BATH_NO_MARK = "SAMP-BAT";//��������
	String ORDR_NO_MARK = "ASS-SAM";//��鹤�����
	String ASS_SAM_PDA = "���";//PDA����ʾ������
	String SAM_ORDER_STATUS = "SAM_ORDER_STATUS";//��鹤��״̬

	/*==========================��鹤��״̬========================*/

	String ORDER_STS1_NEW = "ADD_NEW";
	String ORDER_STS1_SAVE_TEMP = "SAVE_TEMP";
	String ORDER_STS1_CANCELED = "CANCELED";
	String ORDER_STS1_DISTRIBUTED = "DISTRIBUTED";
	String ORDER_STS1_DOWNLOADED = "DOWNLOADED";
	String ORDER_STS1_ARCHIEVED = "ARCHIEVED";

	String ORDER_STS2_NEW = "����";
	String ORDER_STS2_SAVE_TEMP = "�ݴ�";
	String ORDER_STS2_CANCELED = "��ȡ��";
	String ORDER_STS��_DISTRIBUTED = "���·�";
	String ORDER_STS��_DOWNLOADED = "������";
	String ORDER_STS��_ARCHIEVED = "�ѹ鵵";

	/*==========================��鹤��״̬========================*/

	String SCAN_REMARK = "PDA����������";

	String BATCH_REMARK_PROV = "ʡ��˾����������";
	String BATCH_REMARK_SELF = "����˾����������";
}
