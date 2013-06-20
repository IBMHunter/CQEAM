package com.sino.rds.foundation.db.structure;

import com.sino.base.db.ConnBase;
import com.sino.base.exception.DatabaseException;
import com.sino.base.log.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class ConnParser extends ConnBase {
    private static final String SybaseDBProduct = "Adaptive Server Enterprise";
    private static final String OracleDBProduct = "Oracle";

    private String dbURL = "";
    private String dbUser = "";
    private String dbDriver = "";
    private String dbProductName = "";
    private String driverVersion = "";

    /**
     * ���ܣ��޲������캯����
     *
     */
    public ConnParser() {
        this(null);
    }

    /**
     * ���ܣ����캯�����������ء�
     *
     * @param conn Connection ���ݿ�����
     */
    public ConnParser(Connection conn) {
        setDBConnection(conn);
    }

    /**
     * ���ܣ��������ݿ�����
     *
     * @param conn Connection
     */
    public void setDBConnection(Connection conn) {
        try {
            super.setDBConnection(conn);
            if (conn != null) {
                parseConn(conn);
            }
        } catch (DatabaseException ex) {
            Logger.logError(ex);
            throw new RuntimeException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * ���ܣ���ȡ���ݿ�URL��
     *
     * @return String
     */
    public String getDbURL() {
        return dbURL;
    }

    /**
     * ���ܣ���ȡ���ݿ��û���
     *
     * @return String
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * ���ܣ���ȡ���ݿ���������
     *
     * @return String
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * ���ܣ���ȡ���ݿ��Ʒ���ơ�
     *
     * @return String
     */
    public String getDbProductName() {
        return dbProductName;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    private void parseConn(Connection conn) throws DatabaseException {
        try {
            DatabaseMetaData dbmtData = conn.getMetaData();
            dbURL = dbmtData.getURL();
            dbUser = dbmtData.getUserName();
            dbDriver = dbmtData.getDriverName();
            driverVersion = dbmtData.getDriverVersion();
            dbProductName = dbmtData.getDatabaseProductName();
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DatabaseException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DatabaseException(ex.getMessage());
        }
    }

    public boolean isOracleDBProduct() {
        return (dbProductName.indexOf(OracleDBProduct) > -1);
    }

    public boolean isSybaseDBProduct() {
        return (dbProductName.indexOf(SybaseDBProduct) > -1);
    }
}
