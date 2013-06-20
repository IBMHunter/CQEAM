package com.sino.ams.ct.base;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.constant.web.WebConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTO;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.LookUpException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.ReflectException;
import com.sino.base.lookup.LookUpModel;
import com.sino.base.lookup.LookUpProp;
import com.sino.base.lookup.LoopUpDAO;
import com.sino.base.message.Message;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ��ʿ��
 * @version 0.1
 */
public class LookUpCtServlet extends BaseServlet {

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String forwardURL = "";
		Message message = null;
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		Connection conn = null;
		try {
			LookUpProp lookProp = SessionUtil.getLookUpProp(req);
			if (lookProp == null) {
				message = getMessage(MsgKeyConstant.CANNOT_CONTINUE_SESSION);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			} else {
				if (action.equals("")) {
//					action = WebActionConstant.QUERY_ACTION;
					forwardURL = WebConstant.LOOK_UP_PAGE;
				} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
					String dtoClass = lookProp.getDtoClass().getName();
					String modelClass = lookProp.getModelClass().getName();
					Request2DTO req2DTO = new Request2DTO();
					req2DTO.setDTOClassName(dtoClass);
					DTO dtoParameter = req2DTO.getDTO(req);
					Object[] modelArgs = new Object[3];
					modelArgs[0] = SessionUtil.getUserAccount(req);
					modelArgs[1] = dtoParameter;
					modelArgs[2] = lookProp;
					LookUpModel lookUpModel = (LookUpModel) ReflectionUtil.getInstance(modelClass, modelArgs);
					conn = DBManager.getDBConnection();
					LoopUpDAO lookUpDAO = new LoopUpDAO(lookUpModel, conn, req);
					lookUpDAO.setServletConfig(getServletConfig(req));
					lookUpDAO.produceWebData();
					forwardURL = WebConstant.LOOK_UP_PAGE;
				} else {
					message = getMessage(MsgKeyConstant.INVALID_REQ);
					message.setIsError(true);
					forwardURL = MessageConstant.MSG_PRC_SERVLET;
				}
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
		} catch (ReflectException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (LookUpException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}

}
