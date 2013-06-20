package com.sino.ams.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.base.db.conn.DBManager;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-10-9
 * Time: 15:31:54
 */
public class OrderNumGenerator {
    private Connection conn = null;
    private String type = null; //����ʲô���͵ı��
    private String companyCode = null;
    private int orderLength;

    /**
     * ���ܣ����캯��
     * @param conn        Connection ���ݿ�����
     * @param companyCode String ��˾����
     * @param type        String ��������
     */
    public OrderNumGenerator(Connection conn, String companyCode, String type) {
        this.conn = conn;
        this.companyCode = companyCode;
        this.type = type;
    }

    /**
     * ���ܣ��������ɵ������ֲ����ַ������ȡ���������Ĭ��Ϊ5λ
     * @param orderLength int
     */
    public void setOrderLength(int orderLength) {
        this.orderLength = orderLength;
    }

    /**
     * ���ܣ����ù�˾����
     * @param companyCode String
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * ���ܣ����õ�������
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * ���ܣ����ɸ��ֵ��ݺš�
     * @return String
     * @throws SQLException
     */
    public String getOrderNum() throws SQLException {
        String no = null;
        CallableStatement cst = null;
        String sqlStr = "{CALL dbo.AONP_GET_ORDER_NO(?, ?, ?, ?)}";
        try {
            cst = conn.prepareCall(sqlStr);
            cst.setString(1, companyCode);
            cst.setString(2, type);
            cst.setInt(3, orderLength);
            cst.registerOutParameter(4, Types.VARCHAR);
            conn.setAutoCommit(true);            
            cst.execute();
            no = cst.getString(4);
        } finally {
            DBManager.closeDBStatement(cst);
        }
        return no;
    }

    /**
     * ���ɱ������쵥��
     * @return String
     * @throws SQLException
     */
    public String getBjslOrderNo() throws SQLException {
        String no = null;
        CallableStatement cst = null;
        String sqlStr = "BEGIN ? := AMS_ORDERNO_PKG.GET_BJSL_ORDER_NO(?); END;";
        try {
            cst = conn.prepareCall(sqlStr);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.setString(2, companyCode);
            cst.execute();
            no = cst.getString(1);
        } finally {
            DBManager.closeDBStatement(cst);
        }
        return no;
    }

    /**
     * ���ݱ������쵥��������������,�������DB
     * @param slOrderNo �������쵥��
     * @param bjType    ������������,���������DB
     * @return String
     * @throws SQLException
     */
    public String getBjOrderNo(String slOrderNo, String bjType) throws SQLException {
        String no = null;
        CallableStatement cst = null;
        String sqlStr = "BEGIN ? := AMS_ORDERNO_PKG.GET_BJ_ORDER_NO(?,?); END;";
        try {
            cst = conn.prepareCall(sqlStr);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.setString(2, slOrderNo);
            cst.setString(3, bjType);
            cst.execute();
            no = cst.getString(1);
        } finally {
            DBManager.closeDBStatement(cst);
        }
        return no;
    }
}
