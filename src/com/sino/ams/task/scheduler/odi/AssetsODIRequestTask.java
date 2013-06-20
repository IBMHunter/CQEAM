package com.sino.ams.task.scheduler.odi;

import com.sino.ams.task.service.odi.AssetsODIRequestService;
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
public class AssetsODIRequestTask {

    /**
     * <p>����˵������SOA����ODI����ͨ��ODI����ʽ�����й�˾��TD��˾MISϵͳ�������ݲ���EAMϵͳ��ZTE��</p>
     * 1���ʲ�ͷ������Ϣ��
     * 2���ʲ���������Ϣ��
     *
     * @throws DataHandleException �����������ʱ�׳����ݴ����쳣
     */
    public void requestODI2ProcessAssets() throws DataHandleException {
        AssetsODIRequestService service = new AssetsODIRequestService();
        service.requestODI2ProcessAssets();
    }
}
