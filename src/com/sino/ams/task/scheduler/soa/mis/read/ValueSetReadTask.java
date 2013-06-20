package com.sino.ams.task.scheduler.soa.mis.read;

import com.sino.ams.task.service.soa.mis.read.ValueSetReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ���ֵ����Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ValueSetReadTask {

    /**
     * <p>����˵����ͨ��SOA�����ȡMISϵͳ���ֵ����Ϣ��EAMϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readValueSet() throws DataHandleException {
        ValueSetReadService service = new ValueSetReadService();
        service.readValueSets();
    }
}
