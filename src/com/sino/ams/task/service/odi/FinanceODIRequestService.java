package com.sino.ams.task.service.odi;

import com.sino.ams.task.service.AbstractTaskService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.SrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��ODI�����ȡMISϵͳ�ʲ�������Ϣ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class FinanceODIRequestService extends AbstractTaskService {

    /**
     * <p>����˵������ȡMISϵͳ�ʲ�������Ϣ  </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��ȡ����ʧ��ʱ�׳����쳣��
     */
    public void requestODI2ProcessFinance() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            String maxPeriodName = getMaxPeriodName(conn);
            SrvDAO srvDAO = new SrvDAO();
            srvDAO.synAssetInfo(conn, taskExecutor, maxPeriodName);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
