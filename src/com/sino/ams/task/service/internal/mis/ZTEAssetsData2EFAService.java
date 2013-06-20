package com.sino.ams.task.service.internal.mis;

import com.sino.ams.task.service.AbstractTaskService;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;

import java.sql.*;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: �����ݴ�ZTE��ת�Ƶ�ETS_FA_ASSETS��</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class ZTEAssetsData2EFAService extends AbstractTaskService {


    /**
     * <p>����˵������ODI���뵽ZTE�������ת�Ƶ�EFA��</p>
     *
     * @throws DataHandleException ����ת�Ƴ���ʱ�׳����ݴ����쳣
     */
    public void readZTEAssetsData2EFA() throws DataHandleException {
        Connection conn = null;
        CallableStatement cStmt = null;
        try {
            String sqlStr = "{call dbo.AUTO_SYN_FA_ASSETS (?)}";
            conn = DBManager.getDBConnection();
            cStmt = conn.prepareCall(sqlStr);
            cStmt.registerOutParameter(1, Types.INTEGER);
            cStmt.execute();
            int a = cStmt.getInt(1);
//            System.out.println(a);
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                DBManager.closeDBStatement(cStmt);
            DBManager.closeDBConnection(conn);
            } catch (Throwable ex) {
                Logger.logError(ex);
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws DataHandleException {
        ZTEAssetsData2EFAService service = new ZTEAssetsData2EFAService();
        service.readZTEAssetsData2EFA();
    }
}
