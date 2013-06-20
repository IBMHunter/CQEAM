package com.sino.flow.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.flow.dto.FlowExtendDTO;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-11-6
 * Time: 14:56:33
 * ���̵���չ������������ĳһ�ڵ�����˵�ְ�𻮷ֵȡ�
 * ����ת�����Ҫ������FlowBusiness����
 */
public class FlowExtend {
    private Connection conn;
    private FlowExtendDTO dto;

    //��Ҫ�����в����ķ������ô˹�����
    public FlowExtend(Connection conn, FlowExtendDTO dto) {
        this.conn = conn;
        this.dto = dto;
    }

    //�����޲����ķ��������ô˹�����
    public FlowExtend(Connection conn) {
        this.conn = conn;
    }

    //ĳһ�ڵ�����ж�������ˣ�һ��ᰴְ�𻮷ֵ�������ˣ�
    //����������ȡ�����ĸ��ˣ�
    public String getAppointPerson() throws SQLException {
        int userId;
        CallableStatement cStmt = null;
        try {
            String sql = "{? = call SF_EXTEND_PKG.GET_APPOINT_PERSON(?,?,?)}";
            cStmt = conn.prepareCall(sql);
            cStmt.registerOutParameter(1, Types.INTEGER); //����userId
            cStmt.setString(2, dto.getKey());//�����ȽϵĹؼ�ֵ
            cStmt.setString(3, dto.getOrgId());//��Щ���̣���ͬ��OU���ǲ�ͬ�ģ����ʱ����Ҫ����OU
            cStmt.setString(4, dto.getAppointType());//ָ����������
            cStmt.execute();
            userId = cStmt.getInt(1);
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
        return String.valueOf(userId);
    }

    /**
     * ����Ӧ�������ͱ�����ȡactId
     * @param appId     Ӧ������
     * @param tableName ����
     * @return String
     * @throws QueryException
     */
    public String getActId(String appId, String tableName) throws QueryException {
        String actId = "";
        String sqlStr = "SELECT SAC.ACTID\n" +
                "  FROM SF_APPROVE_CONTENT SAC, SF_PROCEDURE_DEF SPD\n" +
                " WHERE SAC.PROC_ID = SPD.PROC_ID\n" +
                "   AND SAC.APPLY_ID = ?\n" +
                "   AND SPD.APP_TABLE_NAME = ?";
        SQLModel sqlModel = new SQLModel();
        List argList = new ArrayList();
        argList.add(appId);
        argList.add(tableName);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(argList);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        try {
            actId = sq.getFirstRow().getValue("ACTID").toString();
        } catch (ContainerException e) {
            Logger.logError("��ȡactId����!" + e);
        }
        return actId;
    }
}
