package com.sino.ams.newasset.constant;

import com.sino.ams.constant.AMSActionConstant;
import com.sino.base.constant.web.WebActionConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface AssetsActionConstant extends WebActionConstant,
		AMSActionConstant {
	String ASSIGN_ACTION = "ASSIGN_ACTION"; //�������
	String CONFIRM_ACTION = "CONFIRM_ACTION"; //ȷ�ϲ���
	String ARCHIVE_ACTION = "ARCHIVE_ACTION"; //�鵵����
	String SAVE_MTL_PRIVI = "SAVE_MTL_PRIVI"; //�����ʲ�רҵȨ��(����רҵ����Ա)
	String DEL_MTL_PRIVI = "DEL_MTL_PRIVI"; ; //ɾ���ʲ�רҵȨ��(����רҵ����Ա)
	String EPT_DTL_ACTION = "EPT_DTL_ACTION";//������ϸ��Ϣ
	String GET_TARGET_OU = "GET_TARGET_OU";//��ȡ�ʲ������еĽ��չ�˾
    String IMP_LOCATION_CODE_ACTION = "IMP_LOCATION_CODE_ACTION"; //�����̵�ص�����Ϣ
    String BACK_ACTION = "BACK_ACTION"; //�̵㹤���˻ز���
    String PRINT_BARCODE_ACTION = "PRINT_BARCODE_ACTION"; //��ӡ
}
