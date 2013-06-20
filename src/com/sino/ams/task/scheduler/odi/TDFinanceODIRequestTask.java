package com.sino.ams.task.scheduler.odi;

import com.sino.ams.task.service.odi.TDFinanceODIRequestService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��ODI�����ȡTDϵͳ�ʲ�������Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDFinanceODIRequestTask {

    /**
     * <p>����˵������ȡTDϵͳ�ʲ�������Ϣ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �����������ʱ�׳����ݴ����쳣
     */
    public void requestODI2ProcessTDFinance() throws DataHandleException {
        TDFinanceODIRequestService service = new TDFinanceODIRequestService();
        service.requestODI2ProcessTDFinance();
    }
}
