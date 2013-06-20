package com.sino.framework.security.servlet;

import com.sino.base.config.PoolConfig;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dao.BaseLoginDAO;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * <p>
 * Title: SinoApplication
 * </p>
 * <p>
 * Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������
 * </p>
 * <p>
 * Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>
 * Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ����ʤ
 * @version 0.1
 */
public class UserLoginServlet extends BaseServlet {

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param res
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection conn = null;
		String forwardURL = "";
		String flag = "";
		Message message = null;
		FilterConfigDTO filterDTO = SessionUtil.getFilterConfigDTO(req);
		try {
			String method = req.getMethod();

			if (!method.equalsIgnoreCase("post")) {
				req.getSession().removeAttribute(WebConstant.USER_INFO);
				message = getMessage("NOT_ALLOWED_METHOD");
				message.setIsError(true);
				forwardURL = filterDTO.getLoginUrl();
				flag = "xss";

			} else {
				Request2DTO req2DTO = new Request2DTO();
				req2DTO.setDTOClassName(filterDTO.getUserDTO());
				BaseUserDTO loginUser = (BaseUserDTO) req2DTO.getDTO(req);
				String loginDAO = filterDTO.getLoginDAO();
				conn = getDBConnection(req);
				if (conn == null) {
					message = getMessage(MsgKeyConstant.INVALID_CONN_POOL);
					PoolConfig poolConfig = getPoolConfig();
					if (poolConfig != null) {
						message.addParameterValue(poolConfig.toString());
					}
					message.setIsError(true);
					message.setNeedBack(true);
					forwardURL = MessageConstant.MSG_PRC_SERVLET;
				} else {
					Object[] consParas = new Object[2];
					consParas[0] = loginUser;
					consParas[1] = conn;
					BaseLoginDAO userLoginDAO;
					ServletConfigDTO servletConfig = SessionUtil
							.getServletConfigDTO(req);
					userLoginDAO = (BaseLoginDAO) ReflectionUtil.getInstance(
							loginDAO, consParas);
					userLoginDAO.setServletConfig(servletConfig);
					userLoginDAO.setUserDtoName(filterDTO.getUserDTO()); // ��web.xml�����õ�userDto���ȥ
					if (userLoginDAO.isValidUser()) {
						BaseUserDTO userAccount = userLoginDAO.getUserAccount();
						userAccount.setRemoteIp(req.getRemoteAddr());
						SessionUtil.saveUserSession(req, userAccount);
						forwardURL = filterDTO.getLoginSuccessURL();
					} else {
						message = userLoginDAO.getMessage();
						message.setIsError(true);
						forwardURL = filterDTO.getLoginUrl();
					}
				}
			}
		} catch (Throwable ex) {
			Logger.logError(ex);
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			if (!flag.equals("xss")) {
				forwarder.forwardView(forwardURL);
			} else {
				message = getMessage(MsgKeyConstant.COMMON_ERROR);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
				forwarder.forwardView(forwardURL);
			}
		}
	}

}