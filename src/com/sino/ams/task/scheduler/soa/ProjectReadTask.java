package com.sino.ams.task.scheduler.soa;

import com.sino.ams.task.service.soa.mis.read.ProjectReadService;
import com.sino.ams.task.service.soa.td.read.TDProjectReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ��Ŀ��Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ProjectReadTask {


    /**
     * <p>����˵������MISϵͳ��ȡ��Ŀ��Ϣ��EAMϵͳ��</p>
     *
     * @throws DataHandleException ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readProjects() throws DataHandleException {
        ProjectReadService service = new ProjectReadService();
        service.readProjects();

        TDProjectReadService tdProjectReadService = new TDProjectReadService();
        tdProjectReadService.readTDProjects();

    }
}
