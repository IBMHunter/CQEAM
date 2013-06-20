package com.sino.ams.system.resource.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.resource.dao.SfResPrivsDAO;
import com.sino.ams.system.resource.dto.SfResPrivsDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ResPriviTreeServlet extends BaseServlet {

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
     */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = null;
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		Connection conn = null;
		try {
			SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(req);
			conn = DBManager.getDBConnection();
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(SfResPrivsDTO.class.getName());
			SfResPrivsDTO respRrivDTO = (SfResPrivsDTO) req2DTO.getDTO(req);
			SfResPrivsDAO resourceDAO = new SfResPrivsDAO(userAccount, respRrivDTO, conn);
			if (action.equals(WebActionConstant.TREE_ACTION)) {
				ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(req);
				String webTree = resourceDAO.getTreeHtml(servletConfig.getSystemName());
				req.setAttribute(WebAttrConstant.MENU_TREE, webTree);
				forwardURL = URLDefineList.RES_PRIVI_TREE;
			} else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (PoolException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.CONN_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder serForwarder = new ServletForwarder(req, res);
			serForwarder.forwardView(forwardURL);
		}
    }
}
