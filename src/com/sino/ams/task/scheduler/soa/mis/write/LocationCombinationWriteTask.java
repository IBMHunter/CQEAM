package com.sino.ams.task.scheduler.soa.mis.write;

import com.sino.ams.task.service.soa.mis.write.LocationCombinationWriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ������ϵص�ı䶯(������)��MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class LocationCombinationWriteTask {

    /**
     * <p>����˵����EAMϵͳͬ��������ϵص�ı䶯(������)��MISϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeLocationChanges() throws DataHandleException {
        LocationCombinationWriteService service = new LocationCombinationWriteService();
        service.writeLocationChanges();
    }
}
