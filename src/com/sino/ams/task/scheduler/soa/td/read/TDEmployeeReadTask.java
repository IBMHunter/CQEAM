package com.sino.ams.task.scheduler.soa.td.read;

import com.sino.ams.task.service.soa.td.read.TDEmployeeReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡTDϵͳԱ����Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDEmployeeReadTask {

    /**
     * <p>����˵������TDϵͳͬ��Ա����Ϣ��EAMϵͳ��</p>
     * <p>����˵����������������Ϣ��</p>
     * <li>Ա��������Ϣ��</li>
     * <li>Ա��������Ϣ��</li>
     *
     * @throws DataHandleException ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */

    public void readTDEmployees() throws DataHandleException {
        TDEmployeeReadService service = new TDEmployeeReadService();
        service.readTDEmployee();
    }
}
