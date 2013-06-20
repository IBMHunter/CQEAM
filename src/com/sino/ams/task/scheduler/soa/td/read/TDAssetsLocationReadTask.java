package com.sino.ams.task.scheduler.soa.td.read;

import com.sino.ams.task.service.soa.td.read.TDAssetsLocationReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡTDϵͳ�ʲ��ص���Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDAssetsLocationReadTask {

    /**
     * <p>����˵������TDϵͳͬ���ʲ��ص���Ϣ��EAMϵͳ��</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readTDAssetsLocations() throws DataHandleException {
        TDAssetsLocationReadService service = new TDAssetsLocationReadService();
        service.readTDAssetsLocations();
    }
}
