package com.sino.sinoflow.user.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.sinoflow.constant.URLDefineList;
import com.sino.sinoflow.user.dao.ChangeUserPasswordDAO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 *
 * <p>Title: SinoCMS</p>
 * <p>Description: �����ƶ���ͬ����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChangeUserPasswordServlet extends BaseServlet {
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String act = req.getParameter("act");
		act = StrUtil.nullToString(act);
		Connection conn = null;
		boolean isClose = true;
		try {
			SfUserBaseDTO user = (SfUserBaseDTO) SessionUtil.getUserAccount(req);
			SfUserBaseDTO dtoParameter;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(SfUserBaseDTO.class.getName());
			dtoParameter = (SfUserBaseDTO) req2DTO.getDTO(req);
			conn = getDBConnection(req);
			ChangeUserPasswordDAO cupDAO = new ChangeUserPasswordDAO(user, dtoParameter, conn);
			if (act.equals("")) {
				forwardURL = URLDefineList.CHANGE_USER_PAGE;
                isClose = false;
			} else if (act.equals(WebActionConstant.UPDATE_ACTION)) {
				if (cupDAO.authenticateUser()) {
					cupDAO.changeUserPassword();
					message = new Message();
					message.setMessageValue("�����޸ĳɹ���");
				} else {
					forwardURL = URLDefineList.CHANGE_USER_PAGE;
					message = getMessage("USER.CHANGE_PASSWORD");
					isClose = false;
				}
			}
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			DBManager.closeDBConnection(conn);
			ServletForwarder sforwarder = new ServletForwarder(req, res);
			if (isClose) {
				sforwarder.forwardOpenerView(forwardURL, message.getMessageValue());
			} else {
				setHandleMessage(req, message);
				sforwarder.forwardView(forwardURL);
			}
		}
	}
}
