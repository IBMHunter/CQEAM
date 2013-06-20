package com.sino.ams.task.service.internal.mis;

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
 * <p>����: ����ת�Ƶ�EFA_TD����ʲ�����ת�Ƶ�EAM��ر�</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class EFAAssetsData2EAMService extends AbstractTaskService {


    /**
     * <p>����˵������ת�Ƶ�EFA_TD����ʲ�����ת�Ƶ�EAM��ر�</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ����ת�Ƴ���ʱ�׳����ݴ����쳣
     */
    public void readEFAAssetsData2EAM() throws DataHandleException {
        Connection conn = null;
        CallableStatement cStmt = null;
        try {
            String sqlStr = "{call dbo.SYN_AUTO_FA_TO_EII(?)}";
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
