package com.sino.ams.bean;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.sinoflow.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 */
public class GetSpecialityGroupServlet extends BaseServlet {

    private static final String m_sContentType = "text/html; charset=GBK";

    /**
     * ���е�Servlet������ʵ�ֵķ�����
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType(m_sContentType);
        PrintWriter resout = res.getWriter();
        String barcode = util.getReqPara(req, "no");
        Connection conn = null;
        try {
            conn = getDBConnection(req);
            SQLModel sqlModel = getSpecialityGroup(barcode);
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            RowSet rs = null;
            String group = "";
            if(simpleQuery.hasResult()) {
                rs = simpleQuery.getSearchResult();
                Row row;
                for(int i = 0; i < rs.getSize(); i++) {
                    row = rs.getRow(i);
                    if(group == "")
                        group = row.getStrValue("GROUP_NAME");
                    else
                        group += ";" + row.getStrValue("GROUP_NAME");
                }
               resout.write(group);
            } else {
                resout.write(group);
            }
        } catch (QueryException ex) {
            ex.printLog();
        } catch (PoolPassivateException ex) {
            ex.printLog();
        } catch (ContainerException ex) {
            ex.printLog();
        } finally {
            DBManager.closeDBConnection(conn);
        }
    }

    public SQLModel getSpecialityGroup(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SG.GROUP_NAME"
            + " FROM"
            + " SF_GROUP SG, SINO_GROUP_MATCH SGM, SINO_MIS_DEPT SMD, ETS_ITEM_INFO EII"
            + " WHERE"
            + " EII.BARCODE = ?"
            + " AND EII.SPECIALITY_DEPT = SMD.DEPT_ID"
            + " AND SGM.DEPT_ID = SMD.DEPT_ID"
            + " AND SGM.GROUP_ID = SG.GROUP_ID";

        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
