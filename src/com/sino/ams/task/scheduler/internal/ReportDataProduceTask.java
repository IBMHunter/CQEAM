package com.sino.ams.task.scheduler.internal;

import com.sino.ams.task.service.internal.ReportDataProduceService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ���������ĺ�̨���񣬸�����������������ô洢���̵ķ���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ReportDataProduceTask {

    /**
     * <p>����˵�����Ӹ����Ӧ�ñ����ɱ������ݵ��������ģ�ͱ�(��TD)��</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          �ʲ���������ת���г���ʱ�׳����ݴ����쳣
     */
    public void produceReportData() throws DataHandleException {
        ReportDataProduceService zteService = new ReportDataProduceService();
        zteService.produceReportData();
    }
}
