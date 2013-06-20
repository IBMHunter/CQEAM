package com.sino.ams.task.scheduler.soa.td.read;

import com.sino.ams.task.service.soa.td.read.TDVendorReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡTDϵͳ�ɹ���Ӧ����Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDVendorReadTask {

    /**
     * <p>����˵����ͨ��SOA�����ȡTDϵͳ�ɹ���Ӧ����Ϣ��EAMϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readTDVendors() throws DataHandleException {
        TDVendorReadService service = new TDVendorReadService();
        service.readTDVendors();
    }
}
