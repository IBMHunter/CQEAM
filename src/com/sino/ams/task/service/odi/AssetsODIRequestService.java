package com.sino.ams.task.service.odi;

import com.sino.ams.task.service.AbstractTaskService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.SrvDAO;
import com.sino.soa.service.TDSrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��ODI�����ȡ���й�˾��TD��˾MISϵͳ�ʲ�ͷ������Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsODIRequestService extends AbstractTaskService {

    /**
     * <p>����˵������ȡ�����й�˾��TD��˾MISϵͳ�ʲ�ͷ������Ϣ(ODI)��</p>
     *
     * @throws DataHandleException ��ȡ����ʧ��ʱ�׳����쳣��
     */
    public void requestODI2ProcessAssets() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            SrvDAO srvDAO = new SrvDAO();
            srvDAO.synAssetHeaderInfo(conn, taskExecutor);
            srvDAO.synAssetDistribute(conn, taskExecutor);

            TDSrvDAO tdSrvDAO = new TDSrvDAO();
            tdSrvDAO.synAssetHeaderInfo(conn, taskExecutor);
            tdSrvDAO.synAssetDistribute(conn, taskExecutor);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
