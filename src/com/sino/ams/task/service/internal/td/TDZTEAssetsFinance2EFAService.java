package com.sino.ams.task.service.internal.td;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.ams.task.service.AbstractTaskService;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ���ʲ��������ݴ�ZTE��ת�Ƶ�ETS_FA_ASSETS_TD��</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDZTEAssetsFinance2EFAService extends AbstractTaskService {


    /**
     * <p>����˵������ODI���뵽ZTE����ʲ��������ݸ��µ�EFA_TD�� </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ���ݸ��³���ʱ�׳����ݴ����쳣
     */
    public void updateTDEFAFinanceFromZTE() throws DataHandleException {
        Connection conn = null;
        CallableStatement cStmt = null;
        try {
            String sqlStr = "{call dbo.AUTO_SYN_TRANS_TD_ASSETINFO(?)}";
            conn = DBManager.getDBConnection();
            cStmt = conn.prepareCall(sqlStr);
            cStmt.registerOutParameter(1, Types.INTEGER);
            cStmt.execute();
            int a = cStmt.getInt(1);
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
