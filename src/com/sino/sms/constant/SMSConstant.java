package com.sino.sms.constant;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: �����ƶ�Ӧ���ɹ�ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface SMSConstant {
    String MSG_ENABLED_YES = "Y";
    String MSG_ENABLED_NO = "N";
    String NEED_RESEND_YES = "Y";
    String NEED_RESEND_NO = "N";
    String MSG_PROCESSED_YES = "Y";
    String MSG_PROCESSED_NO = "N";
    String MSG_CATEGORY_FLOW = "1";//�����ö���Ϣ����
    String MSG_CATEGORY_NO_FLOW = "161";//���ܵ�ͨ�����������ɲɹ�����������֪ͨ�ɹ�Ա���깺Ա����Ӧ��

    //====================ɽ��
//    String uri = "http://211.142.25.74:10086/alert/services/HuilinAlertService";
    String uri = "http://10.204.4.38:8080/alert/services/HuilinAlertService";
    String appId = "101014";

    //=================���ŷ���
    String INBOX = "INBOX";//�ռ���
    String ORDER_DIS = "ORDER_DIS";//�����·�
    String ORDER_ACHIEVE = "ORDER_ACHIEVE";//�����鵵
    String SPARE_ALLOT = "SPARE_ALLOT";//��������֪ͨ
    String SPARE_RECIEVE = "SPARE_RECIEVE";//��������֪ͨ
    String ASSET_DIS = "ASSET_DIS";//�ʲ��̵��·�֪ͨ
    String CHECK_ORDER_ACHIEVE = "CHECK_ORDER_ACHIEVE";//�ʲ��̵㹤���鵵
    String RENT_MIND = "RENT_MIND";//�����ʲ���ʱ����
    String ASSET_CONFIRM = "ASSET_CONFIRM";//�ʲ�����ȷ��

    String INBOX_ID = "1";//�ռ���
    String ORDER_DIS_ID = "2";//�����·�
    String ORDER_ACHIEVE_ID = "3";//�����鵵
    String SPARE_ALLOT_ID = "4";//��������֪ͨ
    String SPARE_RECIEVE_ID = "5";//��������֪ͨ
    String ASSET_DIS_ID = "6";//�ʲ��̵��·�֪ͨ
    String CHECK_ORDER_ACHIEVE_ID = "9";//�ʲ��̵㹤���鵵
    String RENT_MIND_ID = "7";//�����ʲ���ʱ����
    String ASSET_CONFIRM_ID = "8";//�ʲ�����ȷ��

}
