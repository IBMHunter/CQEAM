package com.sino.ams.task.scheduler.oa;


import com.sino.hn.todo.service.EamToOaService;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ��������������</p>
 * <p>����: ����EAMϵͳ������Ϣ��OAϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class Eam2OATask {

    /**
     * <p>����˵��������EAMϵͳ������Ϣ��OAϵͳ</p>
     */
    public void processHNOATasks() {
        EamToOaService service = new EamToOaService();
        service.sendOatodo();
        service.sendOatodoDele();
    }
}
