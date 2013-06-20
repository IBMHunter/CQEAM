package com.sino.rds.foundation.db.structure;

import com.sino.base.db.conn.DBManager;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.DatabaseException;
import com.sino.base.log.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StructureParser {

    /**
     * ���ܣ���ȡ������е��ֶ����顣
     *
     * @param rs ResultSet
     * @return List<Field> �����ֶ��б�
     * @throws DatabaseException
     */
    public static List<Field> getFields(ResultSet rs) throws DatabaseException {
        List<Field> fields = null;
        try {
            int fieldCount = 0;
            Field field = null;
            ResultSetMetaData rsmd = rs.getMetaData();
            fieldCount = rsmd.getColumnCount();
            fields = new ArrayList<Field>(fieldCount);
            for (int i = 0; i < fieldCount; i++) {
                field = new Field();
                field.setName(rsmd.getColumnName(i + 1));
                field.setType(rsmd.getColumnTypeName(i + 1).toUpperCase());
                field.setLength(rsmd.getColumnDisplaySize((i + 1)));
                if (!field.isLobField()) {
                    field.setPrecision(rsmd.getPrecision(i + 1));
                }
                field.setClassName(rsmd.getColumnClassName((i + 1)));
                field.setCatalogName(rsmd.getCatalogName((i + 1)));
                field.setNullAble((rsmd.isNullable(i + 1) == ResultSetMetaData.columnNullable));
                fields.add(field);
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DatabaseException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DatabaseException(ex.getMessage());
        }
        return fields;
    }

    /**
     * ���ܣ���SQL����ȡSQLObject����
     *
     * @param sqlModel SQLModel
     * @param conn     Connection
     * @return SQLObject
     * @throws DatabaseException
     */
    public static SQLObject getSQLObject(SQLModel sqlModel, Connection conn) throws DatabaseException {
        SQLObject sqlObject = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            sqlObject = new SQLObject();
            String sqlStr = sqlModel.getSqlString();
            sqlStr = "SELECT * FROM (" + sqlStr + ") TMP_V WHERE 1 = 2";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStr);
            if (rs != null) {
                sqlObject.setSQL(sqlModel);
                sqlObject.setFields(getFields(rs));
                rs.next();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DatabaseException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DatabaseException(ex.getMessage());
        } finally {
            DBManager.closeDBResultSet(rs);
            DBManager.closeDBStatement(stmt);
        }
        return sqlObject;
    }
}
