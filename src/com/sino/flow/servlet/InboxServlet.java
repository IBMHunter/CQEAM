package com.sino.flow.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.PubServlet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.flow.constant.FlowURLDefineList;
import com.sino.flow.constant.ReqAttributeList;
import com.sino.flow.dao.FlowTraceDAO;
import com.sino.framework.security.bean.SessionUtil;

/**
 * �ռ���SERVLET
 * Created by wwb.
 * User: demo
 * Date: 2006-12-19
 * Time: 20:57:12
 */
public class InboxServlet extends PubServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nextPage = FlowURLDefineList.INBOX_PAGE;
        Connection conn = null;
        try {
            conn = DBManager.getDBConnection();
            SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
            String userId = StrUtil.nullToString(userAccount.getUserId());
            FlowTraceDAO dao = new FlowTraceDAO(req, conn);
            String flag = req.getParameter("flag");
            //�����ǩ�գ��˴���ǩ��
            if (flag != null && flag.equals("sign")) {
                dao.signApply(userId);
            }
            dao.getInbox(userId);
        } catch (PoolException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, "ȡ���ݿ�����ʧ�ܣ�");
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } catch (QueryException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, "��ѯ���ݿ�ʧ�ܣ�");
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } catch (SQLException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, "��ѯ���ݿ�ʧ�ܣ�");
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } catch (DataHandleException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, "��ѯ���ݿ�ʧ�ܣ�");
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } catch (ContainerException e) {
            req.setAttribute(ReqAttributeList.ERROR_MSG, "��ѯ���ݿ�ʧ�ܣ�");
            Logger.logError(e);
            nextPage = FlowURLDefineList.ERROR_PAGE;
        } finally {
            DBManager.closeDBConnection(conn);
            req.getRequestDispatcher(nextPage).forward(req, res);
        }
    }
}
