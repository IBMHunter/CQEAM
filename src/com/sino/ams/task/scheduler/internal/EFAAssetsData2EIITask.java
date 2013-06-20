package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.mis.EFAAssetsData2EAMService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: �����ݴ�ZTE��ת�Ƶ�ETS_FA_ASSETS��</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class EFAAssetsData2EIITask {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ�����ת�Ƶ�EAMϵͳMIS���Ӧ�ñ�</p>
     * <p>����˵������Ҫ���������������</p>
     * <li>��EFA�ʲ�����ת�Ƶ�EII�ȱ�</li>
     * <li>д������ƥ���</li>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ����ת���г���ʱ�׳����ݴ����쳣
     */
    public void readEFAAssetsData2EII() throws DataHandleException {
        EFAAssetsData2EAMService faService = new EFAAssetsData2EAMService();
        faService.readEFAAssetsData2EAM();
    }
}
