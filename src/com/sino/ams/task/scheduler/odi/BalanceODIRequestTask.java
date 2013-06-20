package com.sino.ams.task.scheduler.odi;

import com.sino.ams.task.service.odi.BalanceODIRequestService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��ODI�����ȡ���й�˾��TD��˾MISϵͳ������Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class BalanceODIRequestTask {

    /**
     * <p>����˵����ͨ��ODI�����ȡ���й�˾��TD��˾MISϵͳ������Ϣ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �����������ʱ�׳����ݴ����쳣
     */
    public void requestODI2ProcessBalance() throws DataHandleException {
        BalanceODIRequestService service = new BalanceODIRequestService();
        service.requestODI2ProcessBalance();
    }
}
