package com.sino.framework.security.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class UserLogOutServlet extends BaseServlet {

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FilterConfigDTO filterDTO = SessionUtil.getFilterConfigDTO(req);
		String loginURL = filterDTO.getLoginUrl();
		String method = req.getMethod();
		SessionUtil.invalidateSession(req);
		Message message = null;
		ServletForwarder forwarder = new ServletForwarder(req, res);
		if (!method.equalsIgnoreCase("post")) {
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			loginURL = MessageConstant.MSG_PRC_SERVLET;
			forwarder.forwardView(loginURL);
		}
		else
		{		
		forwarder.forwardView(loginURL);
		}
	}
}
