package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.mis.ZTEAssetsData2EFAService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ��ODI���뵽ZTE����ʲ�����ת�Ƶ�EAMϵͳEFA��</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ZTEAssetsData2EAMTask {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ�����ת�Ƶ�EAMϵͳEFA��</p>
     *
     * @throws DataHandleException ����ת���г���ʱ�׳����ݴ����쳣
     */
    public void readZTEAssetsData2EAM() throws DataHandleException {
        ZTEAssetsData2EFAService zteService = new ZTEAssetsData2EFAService();
        zteService.readZTEAssetsData2EFA();
    }

}
