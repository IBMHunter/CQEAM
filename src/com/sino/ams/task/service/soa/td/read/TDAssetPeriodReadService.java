package com.sino.ams.task.service.soa.td.read;

import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.TDSrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA�����ȡTDϵͳ�ʲ�����ڼ���Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDAssetPeriodReadService extends AbstractTaskSOAService {


    /**
     * <p>����˵������TDϵͳ��ȡ�ʲ�����ڼ䵽EAMϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��ȡ�ʲ�����ڼ䵽EAMϵͳ����ʱ�����ݴ����쳣
     */
    public void readTDAssetPeriod() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            TDSrvDAO srvDAO = new TDSrvDAO();
            srvDAO.synPeriodStatus(conn, taskExecutor);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
