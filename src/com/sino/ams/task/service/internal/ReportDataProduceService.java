package com.sino.ams.task.service.internal;

import com.sino.ams.task.dao.internal.AccountPeriodSearchDAO;
import com.sino.ams.task.service.AbstractTaskService;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ���������ĺ�̨���񣬸÷�������������洢����</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ReportDataProduceService extends AbstractTaskService {


    /**
     * <p>����˵�����Ӹ����Ӧ�ñ����ɱ������ݵ��������ģ�ͱ�</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ����ת�Ƴ���ʱ�׳����ݴ����쳣
     */
    public void produceReportData() throws DataHandleException {
        Connection conn = null;
        CallableStatement cStmt = null;
        try {
            conn = DBManager.getDBConnection();
            initTaskExecutor(conn);
            AccountPeriodSearchDAO accountPeriodDAO = new AccountPeriodSearchDAO(taskExecutor, conn);
            String accountPeriod = accountPeriodDAO.getMaxAccountPeriod();
            if (accountPeriod.length() > 0) {
                String sqlStr = "{call dbo.APP_EAM_FA_ANALYSIS_DATA(?,?)}";
                cStmt = conn.prepareCall(sqlStr);
                cStmt.setString(1, accountPeriod);
                cStmt.registerOutParameter(2, Types.INTEGER);
                cStmt.execute();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            DBManager.closeDBStatement(cStmt);
            DBManager.closeDBConnection(conn);
        }
    }
}
