package com.sino.ams.task.scheduler.soa.td.write;

import com.sino.ams.task.service.soa.td.write.TDLocationCombinationWriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ������ϵص�ı䶯(������)��TDϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDLocationCombinationWriteTask {

    /**
     * <p>����˵����EAMϵͳͬ��������ϵص�ı䶯(������)��TDϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeTDLocationChanges() throws DataHandleException {
        TDLocationCombinationWriteService service = new TDLocationCombinationWriteService();
        service.writeTDLocationChanges();
    }
}
