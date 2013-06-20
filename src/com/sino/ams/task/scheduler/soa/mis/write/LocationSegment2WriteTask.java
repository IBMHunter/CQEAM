package com.sino.ams.task.scheduler.soa.mis.write;

import com.sino.ams.task.service.soa.mis.write.LocationSegment2WriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ����ص�ı䶯(������)��MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class LocationSegment2WriteTask {

    /**
     * <p>����˵����ͨ��SOA����ͬ��EAMϵͳ����ص�ı䶯(������)��MISϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeLocationSegment2() throws DataHandleException {
        LocationSegment2WriteService service = new LocationSegment2WriteService();
        service.writeLocationSegment2();
    }
}
