package com.sino.ams.task.service.soa.td.read;

import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.TDSrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA�����ȡTDϵͳ�ɹ���Ӧ����Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDVendorReadService extends AbstractTaskSOAService {


    /**
     * <p>����˵������TDϵͳ��ȡ�ɹ���Ӧ�̵�EAMϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��ȡ�ɹ���Ӧ�̵�EAMϵͳ����ʱ�����ݴ����쳣
     */
    public void readTDVendors() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            TDSrvDAO srvDAO = new TDSrvDAO();
            srvDAO.synVendorInfo(conn, taskExecutor);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
