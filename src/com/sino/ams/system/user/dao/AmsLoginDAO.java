package com.sino.ams.system.user.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.model.AmsLoginModel;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAMS</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007 - 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2008-7-24
 */
public class AmsLoginDAO extends AMSBaseDAO {
    SfUserDTO dto = null;

    public AmsLoginDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        dto = (SfUserDTO) dtoParameter;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new AmsLoginModel(userAccount, dtoParameter);
    }

    public boolean isPasswordExpired() throws QueryException, ContainerException {
        boolean expired = true;
        SimpleQuery sq = new SimpleQuery(((AmsLoginModel) sqlProducer).getCheckPswdDateModel(), conn);
        sq.executeQuery();
//        String days = sq.getFirstRow().getValue(0).toString();
//        if (Integer.parseInt(days) > 0) {
//            expired = true;
//        }
        return expired;
    }

    /**
     * ȡ������
     * @return success
     * @throws SQLException
     */
    public boolean getPassword() throws SQLException {
        boolean success = false;
        CallableStatement cStmt = null;
        String sqlStr = "begin ?:= AMS_EMAIL_JOB.createUserNewPwd(?,?); end;";
        try {
            cStmt = conn.prepareCall(sqlStr);
            cStmt.registerOutParameter(1, Types.NUMERIC);
            cStmt.setString(2, dto.getLoginName());
            cStmt.registerOutParameter(3, Types.VARCHAR);
            cStmt.execute();
            int rs = cStmt.getInt(1);
            if (rs == 0) {
                message.setMessageValue(cStmt.getString(3));
            } else
                message.setMessageValue("�������������ɲ���������������,����5���Ӻ�����ʼ������������¼");
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
        return success;
    }

    /**
     * ��ȡ������¼�������
     * @return int
     * @throws QueryException
     * @throws ContainerException
     */
    public int getLoginErrCount() throws QueryException, ContainerException {
        SimpleQuery sq = new SimpleQuery(((AmsLoginModel) sqlProducer).getLoginErrCountModel(), conn);
        sq.executeQuery();
        String days = sq.getFirstRow().getValue(0).toString();
        return Integer.parseInt(days);
    }
     public boolean getLoginDisable() throws QueryException, ContainerException {
        SimpleQuery sq = new SimpleQuery(((AmsLoginModel) sqlProducer).getDisableModel(), conn);
        sq.executeQuery();

        return  sq.hasResult();
    }
    /**
     * ����¼���������1
     * @throws DataHandleException
     */
    public void addLoginErrCount() throws DataHandleException {
        DBOperator.updateRecord(((AmsLoginModel) sqlProducer).getAddLoginErrCountModel(),conn);
    }

    /**
     * ����¼�����������
     * @throws DataHandleException
     */
    public void clearLoginErrCount() throws DataHandleException {
        DBOperator.updateRecord(((AmsLoginModel) sqlProducer).getClearLoginErrCountModel(),conn);
    }

    /**
     * �����Ƿ���ϵͳĬ������
     * @return boolean
     * @throws QueryException
     */
    public boolean isDefaultPassword() throws QueryException {
        SimpleQuery sq = new SimpleQuery(((AmsLoginModel) sqlProducer).getIsDefaultPasswordModel(), conn);
        sq.executeQuery();
        return sq.hasResult();
    }
}
