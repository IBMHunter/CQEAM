package com.sino.ams.task.scheduler.soa;

import com.sino.ams.task.service.soa.mis.read.AssetPeriodReadService;
import com.sino.ams.task.service.soa.td.read.TDAssetPeriodReadService;
import com.sino.base.exception.DataHandleException;


/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ��TDϵͳ���ʲ�����ڼ���Ϣ��EAMϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AccountPeriodReadTask {

    /**
     * <p>����˵����ͨ��SOA�����ȡMISϵͳ��TDϵͳ���ʲ�����ڼ���Ϣ��EAMϵͳ</p>
     *
     * @throws DataHandleException ��ȡMISϵͳ��TDϵͳ���ʲ�����ڼ���Ϣ��EAMϵͳ����ʱ�׳����ݴ����쳣
     */
    public void readAccountPeriod() throws DataHandleException {
        AssetPeriodReadService periodReadService = new AssetPeriodReadService();
        periodReadService.readAssetPeriod();

        TDAssetPeriodReadService tdPeriodReadService = new TDAssetPeriodReadService();
        tdPeriodReadService.readTDAssetPeriod();
    }
}
