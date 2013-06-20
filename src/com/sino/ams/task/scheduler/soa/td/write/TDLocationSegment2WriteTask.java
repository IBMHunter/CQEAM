package com.sino.ams.task.scheduler.soa.td.write;

import com.sino.ams.task.service.soa.td.write.TDLocationSegment2WriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ����ص�ı䶯(������)��TDϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDLocationSegment2WriteTask {

    /**
     * <p>����˵����ͨ��SOA����ͬ��EAMϵͳ����ص�ı䶯(������)��TDϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeTDLocationSegment2() throws DataHandleException {
        TDLocationSegment2WriteService service = new TDLocationSegment2WriteService();
        service.writeTDLocationSegment2();
    }
}
