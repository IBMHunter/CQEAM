package com.sino.sinoflow.template;

import java.sql.Connection;

import com.sino.base.data.RowSet;
import com.sino.sinoflow.flowinterface.IAPPTrays;

/**
 * <p>Title: CaseRoutinet</p>
 * <p>Description:���̱��溯��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */

public class Tray_Template implements IAPPTrays {

    public RowSet getInTray(String loginName, String URL,  String keyword, String subject,String createby, Connection conn) {
        return null;
    }

    public RowSet getPendingTray(String loginName, String URL, String keyword, String subject, String createby, Connection conn){
        return null;
    }

    public RowSet getOutTray(String loginName, String URL, String keyword, String subject, String createby, Connection conn) {
        return null;    
    }
}