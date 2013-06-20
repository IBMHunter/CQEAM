package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.mis.ZTEAssetsFinance2EFAService;
import com.sino.ams.task.service.internal.td.TDZTEAssetsFinance2EFAService;
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
public class ZTEAssetsFinance2EAMTask {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ��ʲ��������ݸ��µ�EFA(��TD)��</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �ʲ���������ת���г���ʱ�׳��ʲ��������ݴ����쳣
     */
    public void updateFAFinanceFromZTE() throws DataHandleException {
        ZTEAssetsFinance2EFAService zteService = new ZTEAssetsFinance2EFAService();
        zteService.updateEFAFinanceFromZTE();

        TDZTEAssetsFinance2EFAService tdService = new TDZTEAssetsFinance2EFAService();
        tdService.updateTDEFAFinanceFromZTE();
    }
}
