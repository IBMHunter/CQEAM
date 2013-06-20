package com.sino.ams.task.scheduler.soa.mis.read;

import com.sino.ams.task.service.soa.mis.read.EmployeeReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳԱ����Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class EmployeeReadTask {

    /**
     * <p>����˵������MISϵͳͬ��Ա����Ϣ��EAMϵͳ��</p>
     * <p>����˵����������������Ϣ��</p>
     * <li>Ա��������Ϣ��</li>
     * <li>Ա��������Ϣ��</li>
     *
     * @throws DataHandleException ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readEmployees() throws DataHandleException {
        EmployeeReadService service = new EmployeeReadService();
        service.readEmployees();
    }

}
