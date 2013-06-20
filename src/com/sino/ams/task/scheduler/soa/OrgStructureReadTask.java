package com.sino.ams.task.scheduler.soa;

import com.sino.ams.task.service.soa.mis.read.OrgStructureReadService;
import com.sino.ams.task.service.soa.td.read.TDOrgStructureReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ��֯�ṹ(����)��Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class OrgStructureReadTask {

    /**
     * <p>����˵������MISϵͳ��ȡ��֯�ṹ(����)��Ϣ��EAMϵͳ</p>
     *
     * @throws DataHandleException ��ȡ������Ϣ����ʱ�����ݴ����쳣
     */
    public void readOrgStructure() throws DataHandleException {
        OrgStructureReadService service = new OrgStructureReadService();
        service.readOrgStructure();

        TDOrgStructureReadService tdService = new TDOrgStructureReadService();
        tdService.readTDOrgStructure();
    }
}
