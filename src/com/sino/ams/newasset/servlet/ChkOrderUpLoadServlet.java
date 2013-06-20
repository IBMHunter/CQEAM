package com.sino.ams.newasset.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.newasset.dao.ChkOrderUpLoadDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.base.web.request.upload.UploadFile;
import com.sino.base.web.request.upload.UploadFileSaver;
import com.sino.base.web.request.upload.UploadRow;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChkOrderUpLoadServlet extends BaseServlet {
	private static final String CONTENT_TYPE = "application/xml; charset=GBK";

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws
			ServletException, IOException {
		Connection conn = null;
		boolean hasError = true;
		try {
			RequestParser parser = new RequestParser();
			parser.transData(req);
			String loginName = parser.getParameter("username"); //��¼��
			SfUserDTO user = null;
			conn = getDBConnection(req);
			if (!StrUtil.isEmpty(loginName)) {
				user = new SfUserDTO();
				user.setLoginName(loginName);
				UserLoginDAO loginDAO = new UserLoginDAO(user, conn);
				loginDAO.setFromPDA(true);
				if (loginDAO.isValidUser()) {
					user = (SfUserDTO) loginDAO.getUserAccount();
				} else {
					user = null;
				}
			} else {
				user = (SfUserDTO) getUserAccount(req);
			}
			StringBuffer responeXML = new StringBuffer();
			res.setContentType(CONTENT_TYPE);
			PrintWriter writer = res.getWriter();

			if (user != null) {
				UploadFileSaver saver = parser.getFileSaver();
				saver.saveFiles("/assetsChk/" + user.getCompanyCode() + "/");
				UploadRow row = saver.getRow();
				UploadFile[] files = row.getFiles();
				if (files != null && files.length > 0) {
					UploadFile file = files[0];
					String filePath = file.getAbsolutePath();
					ChkOrderUpLoadDAO dao = new ChkOrderUpLoadDAO(user, conn);
					hasError = !dao.uploadOrders(filePath);
					saver.saveFiles("/assetsChk/" + user.getCompanyCode() +
									"/bak/" + CalendarUtil.getCurrDate() + "/");
				} else {
					hasError = false;
				}
				String msg = "�ʲ��̵㹤�����سɹ�";
				if (hasError) {
					msg = "�ʲ��̵㹤������ʧ��";
				}
				responeXML.append(
						"<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
				responeXML.append("<result message=\"" + msg + "\">");
				responeXML.append(!hasError);
				responeXML.append("</result>");
			} else {
				responeXML.append(
						"<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
				responeXML.append("<result message=\"ϵͳ�����շǷ��û����صĹ���\">");
				responeXML.append(false);
				responeXML.append("</result>");
			}
			writer.write(responeXML.toString());
			writer.close();
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			closeDBConnection(conn);
		}
	}
}
