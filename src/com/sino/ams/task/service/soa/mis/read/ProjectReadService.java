package com.sino.ams.task.service.soa.mis.read;

import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.SrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ��Ŀ��Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ProjectReadService extends AbstractTaskSOAService {

    /**
     * <p>����˵������MISϵͳ��ȡ��Ŀ��Ϣ��EAMϵͳ��</p>
     *
     * @throws DataHandleException ͬ�����ݳ���ʱ�׳����ݴ����쳣
     */
    public void readProjects() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            SrvDAO srvDAO = new SrvDAO();
            srvDAO.synProjectInfo(conn, taskExecutor);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
