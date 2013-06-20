package com.sino.ams.task.scheduler.soa;

import com.sino.ams.task.service.soa.mis.write.AssetsAllocationWriteService;
import com.sino.ams.task.service.soa.td.write.TDAssetsAllocationWriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ��˾���ʲ������ı䶯��MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsAllocationWriteTask {

    /**
     * <p>����˵����EAMϵͳͬ����˾���ʲ������ı䶯��MISϵͳ </p>
     * <p>����˵���� </p>
     * <li>ͬ�������ڵ���</li>
     * <li>ͬ�����ż����</li>
     * <li>ͬ���������ܵ���</li>
     * <li>�����䶯��ͬ�������񲻸����ɽ����ֹ�����</li>
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeAssetsAllocations() throws DataHandleException {
        AssetsAllocationWriteService service = new AssetsAllocationWriteService();
        service.writeAssetsAllocations();

        TDAssetsAllocationWriteService tdService = new TDAssetsAllocationWriteService();
        tdService.writeTDAssetsAllocations();
    }
}
