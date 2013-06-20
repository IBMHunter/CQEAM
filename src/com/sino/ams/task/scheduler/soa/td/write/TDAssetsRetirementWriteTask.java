package com.sino.ams.task.scheduler.soa.td.write;

import com.sino.ams.task.service.soa.td.write.TDAssetsRetirementWriteService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA����ͬ��EAMϵͳ�����ʲ���TDϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDAssetsRetirementWriteTask {

    /**
     * <p>����˵����EAMϵͳͬ�������ʲ���TDϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void writeTDAssetsRetirement() throws DataHandleException {
        TDAssetsRetirementWriteService service = new TDAssetsRetirementWriteService();
        service.writeTDAssetsRetirement();
    }
}
