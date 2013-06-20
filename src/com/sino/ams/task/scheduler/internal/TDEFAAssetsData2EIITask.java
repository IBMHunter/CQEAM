package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.td.TDEFAAssetsData2EAMService;
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
public class TDEFAAssetsData2EIITask {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ�����ת�Ƶ�EAMϵͳTD���Ӧ�ñ�</p>
     * <p>����˵������Ҫ���������������</p>
     * <li>��EFA_TD�ʲ�����ת�Ƶ�EII�ȱ�</li>
     * <li>д������ƥ���</li>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ����ת���г���ʱ�׳����ݴ����쳣
     */
    public void readTDEFAAssetsData2EII() throws DataHandleException {
        TDEFAAssetsData2EAMService faService = new TDEFAAssetsData2EAMService();
        faService.readTDFAAssets2EAM();
    }

}
