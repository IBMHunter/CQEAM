package com.sino.ams.task.scheduler.odi;

import com.sino.ams.task.service.odi.mis.RetireODIRequestService;
import com.sino.ams.task.service.odi.td.TDRetireODIRequestService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��ODI�����ȡMISϵͳ��TDϵͳ�����ʲ���Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class RetirementODIRequestTask {

    /**
     * <p>����˵������ȡMISϵͳ��TDϵͳ�����ʲ���Ϣ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �����������ʱ�׳����ݴ����쳣
     */
    public void requestODI2ProcessRetire() throws DataHandleException {
        RetireODIRequestService service = new RetireODIRequestService();
        service.requestODI2ProcessRetire();

        TDRetireODIRequestService tdService = new TDRetireODIRequestService();
        tdService.requestODI2ProcessTDRetire();
    }
}
