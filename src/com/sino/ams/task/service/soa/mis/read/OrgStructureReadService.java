package com.sino.ams.task.service.soa.mis.read;

import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.SrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA�����ȡMISϵͳ��֯�ṹ(����)��Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class OrgStructureReadService extends AbstractTaskSOAService {


    /**
     * <p>����˵������MISϵͳ��ȡ��֯�ṹ(����)��Ϣ��EAMϵͳ</p>
     *
     * @throws DataHandleException ��ȡ������Ϣ����ʱ�����ݴ����쳣
     */
    public void readOrgStructure() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            SrvDAO srvDAO = new SrvDAO();
            srvDAO.synOrgstructure(conn, taskExecutor);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
