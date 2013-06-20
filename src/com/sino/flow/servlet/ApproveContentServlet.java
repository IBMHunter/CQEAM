package com.sino.flow.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.PubServlet;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.flow.constant.FlowURLDefineList;
import com.sino.flow.constant.ReqAttributeList;
import com.sino.flow.dao.ApproveContentDAO;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-21
 * Time: 11:56:00
 */
public class ApproveContentServlet extends PubServlet {
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nextPage = "";
		Connection conn = null;
		try {
			String actId = req.getParameter("actId");
			String appId = StrUtil.nullToString(req.getParameter("appId"));
			String appTableName = StrUtil.nullToString(req.getParameter("appTableName"));
			String act = StrUtil.nullToString(req.getParameter("act"));
			conn = DBManager.getDBConnection();
			ApproveContentDAO dao = new ApproveContentDAO(conn, req);
			if (!appId.equals("")) {
				if (!act.equals(WebActionConstant.EXPORT_ACTION)) {
					dao.getApproveContent(appId, appTableName);
					nextPage = FlowURLDefineList.APPROVE_CONTENT_PAGE;
				} else {
					File file = dao.getExportFile(appId, appTableName);
					WebFileDownload fileDown = new WebFileDownload(req, res);
					fileDown.setFilePath(file.getAbsolutePath());
					fileDown.download();
					file.delete();
				}
			} else {
				if (!act.equals(WebActionConstant.EXPORT_ACTION)) {
					dao.getApproveContent(actId);
					nextPage = FlowURLDefineList.APPROVE_CONTENT_PAGE;
				} else {
					File file = dao.getExportFile(actId);
					WebFileDownload fileDown = new WebFileDownload(req, res);
					fileDown.setFilePath(file.getAbsolutePath());
					fileDown.download();
					file.delete();
				}
			}

		} catch (PoolException e) {
			req.setAttribute(ReqAttributeList.ERROR_MSG, "ȡ���ӳ���");
			nextPage = FlowURLDefineList.ERROR_PAGE;
			e.printLog();
		} catch (QueryException e) {
			req.setAttribute(ReqAttributeList.ERROR_MSG, "��ѯ���ݿ����");
			nextPage = FlowURLDefineList.ERROR_PAGE;
			e.printLog();
		} catch (DataTransException ex) {
			req.setAttribute(ReqAttributeList.ERROR_MSG, "�����ݵ�Excelʱ����");
			nextPage = FlowURLDefineList.ERROR_PAGE;
			ex.printLog();
		} catch (WebFileDownException ex) {
			req.setAttribute(ReqAttributeList.ERROR_MSG, "�����ļ�ʱ����");
			nextPage = FlowURLDefineList.ERROR_PAGE;
			ex.printLog();
		} finally {
			DBManager.closeDBConnection(conn);
			if(!StrUtil.isEmpty(nextPage)){
				req.getRequestDispatcher(nextPage).forward(req, res);
			}
		}
	}
}
