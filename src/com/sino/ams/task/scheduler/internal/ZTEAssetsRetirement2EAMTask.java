package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.mis.ZTEAssetsRetirement2EFAService;
import com.sino.ams.task.service.internal.td.TDZTEAssetsRetirement2EFAService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ���ʲ��������ݴ�ZTE����µ�ETS_FA_ASSETS(��TD)��</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ZTEAssetsRetirement2EAMTask {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ��������ݸ��µ�EFA(��TD)��</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �ʲ���������ת���г���ʱ�׳����ݴ����쳣
     */
    public void updateEAMRetirementFromZTE() throws DataHandleException {
        ZTEAssetsRetirement2EFAService zteService = new ZTEAssetsRetirement2EFAService();
        zteService.updateEAMRetirementFromZTE();

        TDZTEAssetsRetirement2EFAService tdService = new TDZTEAssetsRetirement2EFAService();
        tdService.updateTDEAMRetirementFromZTE();
    }
}
