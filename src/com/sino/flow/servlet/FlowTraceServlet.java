package com.sino.flow.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.PubServlet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.flow.constant.FlowURLDefineList;
import com.sino.flow.constant.MessageDefineList;
import com.sino.flow.constant.ReqAttributeList;
import com.sino.flow.dao.FlowTraceDAO;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-25
 * Time: 10:03:03
 * ת����ҳ,ȡ���̸ſ�
 */
public class FlowTraceServlet extends PubServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nextPage = "/index.jsp";
        Connection conn = null;
        try {
            conn = DBManager.getDBConnection();
            String userId = req.getParameter("userId");
            FlowTraceDAO dao = new FlowTraceDAO(req, conn);
            dao.getInboxTotal(userId); //ȡ�ռ�������
            dao.getOutboxTotal(userId);//ȡ����������
            dao.getPersonalTotal(userId);//ȡ������������
            dao.getCancelTotal(userId);//��ǰ�˿���ȡ�����������
            //dao.getAllTotal();//ȡ������������
        } catch (PoolException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, MessageDefineList.GET_CONN_ERROR);
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } catch (QueryException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, MessageDefineList.QUERY_ERROR);
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } catch (ContainerException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, MessageDefineList.CONTAINER_ERROR);
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        }finally{
            DBManager.closeDBConnection(conn);
            req.getRequestDispatcher(nextPage).forward(req, res);
        }

    }
}
