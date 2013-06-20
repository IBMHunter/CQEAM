package com.sino.ams.task.scheduler.sms;

import com.sino.base.exception.DataHandleException;
import com.sino.config.SinoConfig;
import com.sino.hn.constant.HNConstant;
import com.sino.hn.sms.service.HnSendMsg;
import com.sino.sms.service.EamMsgSend;
import com.sino.sms.service.SMSMsgCreate;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ��ʱ����EAMϵͳ���ֻ�����</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class EAMShortMessageTask {
    private static final String provinceCode = SinoConfig.getProvinceCode();

    /**
     * <p>����˵���������ֻ����� </p>
     * <p>����˵������Ҫ����������� </p>
     * <li>�����ֻ����ţ�</li>
     * <li>�����ֻ����ţ�</li>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �����������ʱ�׳����ݴ����쳣
     */
    public void sendShortMessage() throws DataHandleException {
        SMSMsgCreate sc = new SMSMsgCreate();
        sc.AutoCreateMsg(); // �ռ�
        if (provinceCode.equals(HNConstant.PROVINCE_CODE)) {
            HnSendMsg es = new HnSendMsg();
            es.sendMsg(); // ����
        } else {
            EamMsgSend es = new EamMsgSend();
            es.sendMsg();
        }
    }
}
