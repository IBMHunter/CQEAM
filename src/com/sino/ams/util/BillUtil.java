package com.sino.ams.util;

import java.sql.Connection;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2008-2-18
 */
public class BillUtil {

    public static void updateStatus(String tableName, String pkName,String pkValue,String statusColumnName, String status, Connection conn) throws DataHandleException {
        String sqlStr = " UPDATE " + tableName + " SET " +statusColumnName+ "='" + status + "' WHERE " + pkName + "=" + pkValue ;
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sqlStr);
        DBOperator.updateRecord(sm, conn);
    }
}
