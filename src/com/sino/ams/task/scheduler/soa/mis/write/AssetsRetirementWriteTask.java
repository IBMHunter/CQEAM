package com.sino.ams.task.scheduler.soa.mis.write;

import com.sino.ams.task.service.soa.mis.write.AssetsRetirementWriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ�����ʲ���MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsRetirementWriteTask {

    /**
     * <p>����˵����EAMϵͳͬ�������ʲ���MISϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeAssetsRetirement() throws DataHandleException {
        AssetsRetirementWriteService service = new AssetsRetirementWriteService();
        service.writeAssetsRetirement();
    }
}
