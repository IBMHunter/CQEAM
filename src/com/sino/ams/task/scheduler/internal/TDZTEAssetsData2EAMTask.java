package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.td.TDZTEAssetsData2EFAService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ��ODI���뵽ZTE����ʲ�����ת�Ƶ�EAMϵͳEFA_TD��</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDZTEAssetsData2EAMTask {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ�����ת�Ƶ�EAMϵͳEFA_TD��</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ����ת���г���ʱ�׳����ݴ����쳣
     */
    public void readTDZTEAssetsData2EAM() throws DataHandleException {
        TDZTEAssetsData2EFAService zteService = new TDZTEAssetsData2EFAService();
        zteService.readTDZTEAssets2FA();
    }

}
