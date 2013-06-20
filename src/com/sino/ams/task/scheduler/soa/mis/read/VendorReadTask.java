package com.sino.ams.task.scheduler.soa.mis.read;

import com.sino.ams.task.service.soa.mis.read.VendorReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ�ɹ���Ӧ����Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class VendorReadTask {

    /**
     * <p>����˵����ͨ��SOA�����ȡMISϵͳ�ɹ���Ӧ����Ϣ��EAMϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readVendors() throws DataHandleException {
        VendorReadService service = new VendorReadService();
        service.readVendors();
    }
}
