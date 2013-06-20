package com.sino.ams.task.scheduler.soa.mis.read;

import com.sino.ams.task.service.soa.mis.read.AssetsLocationReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ�ʲ��ص���Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsLocationReadTask {

    /**
     * <p>����˵������MISϵͳͬ���ʲ��ص���Ϣ��EAMϵͳ��</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readAssetsLocations() throws DataHandleException {
        AssetsLocationReadService service = new AssetsLocationReadService();
        service.readAssetsLocations();
    }
}
