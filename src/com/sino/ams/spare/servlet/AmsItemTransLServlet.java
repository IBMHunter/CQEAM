package com.sino.ams.spare.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;

import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.ams.spare.dao.AmsItemTransLDAO;
import com.sino.ams.spare.dto.AmsItemTransLDTO;
import com.sino.ams.spare.model.AmsItemTransLModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: AmsItemTransLServlet</p>
 * <p>Description:�����Զ����ɷ������AmsItemTransLServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ������
 * @version 1.0
 */


public class AmsItemTransLServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
			AmsItemTransLDTO dtoParameter = null;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsItemTransLDTO.class.getName());
			dtoParameter = (AmsItemTransLDTO)req2DTO.getDTO(req);
			conn = getDBConnection(req);
			AmsItemTransLDAO amsItemTransLDAO = new AmsItemTransLDAO(user, dtoParameter, conn);
			if (action.equals("")) {
				forwardURL = "��ҳ��ѯҳ�棬�����ʵ������޸�";
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new AmsItemTransLModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = "��ҳ��ѯҳ�棬�����ʵ������޸�";
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
				AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO)req.getAttribute("��ϸ�������ԣ������ʵ������޸�");
				if(amsItemTransL == null){
					amsItemTransL= dtoParameter;
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsItemTransL);
				forwardURL = "��ϸ����ҳ�棬��ʱ����Ϊ�գ������ʵ������޸�";
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				amsItemTransLDAO.setDTOClassName(AmsItemTransLDTO.class.getName());
				AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO)amsItemTransLDAO.getDataByPrimaryKey();
				if(amsItemTransL == null){
					amsItemTransL = new AmsItemTransLDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsItemTransL);
				forwardURL = "��ϸ����ҳ�棬��ʱ�����ݣ������ʵ������޸�";
			} else if (action.equals(WebActionConstant.CREATE_ACTION)) {
			 amsItemTransLDAO.createData();
				message = amsItemTransLDAO.getMessage();

					forwardURL = "���ٴ�ִ�и�Servlet��QUERY_ACTION�������ʵ�����ȷ��";

			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
		 amsItemTransLDAO.updateData();
				message = amsItemTransLDAO.getMessage();
					forwardURL = "���ٴ�ִ�и�Servlet��QUERY_ACTION�������ʵ�����ȷ��";

			} else if (action.equals(WebActionConstant.DELETE_ACTION)) {
		 amsItemTransLDAO.deleteData();
				message = amsItemTransLDAO.getMessage();

				forwardURL = "���ٴ�ִ�и�Servlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (DataHandleException ex) {
			 ex.printLog();
		}catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
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
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			//����ʵ������޸�ҳ����ת���롣
		}
	}
}
