package com.sino.pda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.framework.servlet.BaseServlet;

/**
 * User: zhoujs
 * Date: 2008-4-24
 * Time: 11:58:01
 * Function:��ȡ��Ŀ��Ϣ������������������������
 */
public class GetProject extends BaseServlet {
    private static final String CONTENT_TYPE = "application/xml; charset=GBK";
    private static final String DOC_TYPE = null;

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType(CONTENT_TYPE);
        PrintWriter out = res.getWriter();

        String lastUpdateDate = StrUtil.nullToString(req.getParameter("last_update_date"));

        if (DOC_TYPE != null) {
            out.println(DOC_TYPE);
        }

        Logger.logInfo("PDA run getProjects servlet begin....");

        out.println("<?xml version=\"1.0\" encoding=\"GB2312\"?>");

        out.println("<projects>");
        Connection conn = null;
        try {
            conn = DBManager.getDBConnection();
            getProject(conn, out, lastUpdateDate);
        } catch (PoolException e) {
            e.printLog();
        } finally {
            DBManager.closeDBConnection(conn);
        }

        out.println("</projects>");

        out.close();
    }


    private void getProject(Connection conn, PrintWriter out, String lastUpdateDate) {

        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT PROJECT_ID, NAME, SEGMENT1, ENABLED_FLAG FROM ETS_PA_PROJECTS_ALL\n" +
                "  WHERE  " + SyBaseSQLUtil.isNull() + "  OR " +
                " (LAST_UPDATE_DATE >?  OR CREATION_DATE>?)";

        sqlArgs.add(lastUpdateDate);
        sqlArgs.add(lastUpdateDate);
        sqlArgs.add(lastUpdateDate);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        try {
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();

            if (simpleQuery.hasResult()) {
                RowSet rs = simpleQuery.getSearchResult();
                Row row = null;
                for (int i = 0; i < rs.getSize(); i++) {
                    row = rs.getRow(i);
                    StringBuffer stringBuffer = new StringBuffer("");

                    stringBuffer.append("<project ");
                    stringBuffer.append(" projectId=\"").append(PDAUtil.xmlFormat(row.getStrValue("PROJECT_ID"))).append("\"");
                    stringBuffer.append(" projectCode=\"").append(PDAUtil.xmlFormat(row.getStrValue("SEGMENT1"))).append("\"");
                    stringBuffer.append(" projectName=\"").append(PDAUtil.xmlFormat(row.getStrValue("NAME"))).append("\"");
                    stringBuffer.append(" enable=\"").append(PDAUtil.xmlFormat(row.getStrValue("ENABLED_FLAG"))).append("\"");
                    stringBuffer.append(" />");
                    out.println(stringBuffer.toString());
                }
            }

        } catch (QueryException e) {
            e.printLog();
        } catch (PoolException e) {
            e.printLog();
        } catch (ContainerException e) {
            e.printLog();
        }
    }
}