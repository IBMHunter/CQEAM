package com.sino.ams.prematch.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.prematch.dao.AmsPaMatchLogDAO;
import com.sino.ams.prematch.dto.AmsPaMatchLogDTO;
import com.sino.ams.prematch.model.AmsPaMatchLogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsPaMatchLogServlet</p>
 * <p>Description:�����Զ����ɷ������AmsPaMatchLogServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsPaMatchLogServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsPaMatchLogDTO.class.getName());
			AmsPaMatchLogDTO dtoParameter = (AmsPaMatchLogDTO)req2DTO.getDTO(req);
			String action = dtoParameter.getAct();
			conn = getDBConnection(req);
			AmsPaMatchLogDAO amsPaMatchLogDAO = new AmsPaMatchLogDAO(user, dtoParameter, conn);
			if (action.equals("")) {
				forwardURL = "com.sino.ams.prematch.servlet.AmsPaMatchLogServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(AMSActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new AmsPaMatchLogModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = "com.sino.ams.prematch.servlet.AmsPaMatchLogServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(AMSActionConstant.NEW_ACTION)) {
				AmsPaMatchLogDTO amsPaMatchLog = (AmsPaMatchLogDTO)req.getAttribute("��ȡ��Ϊʧ�ܶ����ֵ����ݣ������ʵ������޸�");
				if(amsPaMatchLog == null){
					amsPaMatchLog= dtoParameter;//��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.prematch.dto.AmsPaMatchLogDTO�Ĺ��캯��ȷ��
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsPaMatchLog);
				forwardURL = "com.sino.ams.prematch.servlet.AmsPaMatchLogServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(AMSActionConstant.DETAIL_ACTION)) {
				amsPaMatchLogDAO.setDTOClassName(AmsPaMatchLogDTO.class.getName());
				AmsPaMatchLogDTO amsPaMatchLog = (AmsPaMatchLogDTO)amsPaMatchLogDAO.getDataByPrimaryKey();
				if(amsPaMatchLog == null){
					amsPaMatchLog = new AmsPaMatchLogDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsPaMatchLog);
				forwardURL = "com.sino.ams.prematch.servlet.AmsPaMatchLogServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(AMSActionConstant.CREATE_ACTION)) {
				amsPaMatchLogDAO.createData();
				forwardURL = "���ٴ�ִ��com.sino.ams.prematch.servlet.AmsPaMatchLogServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else if (action.equals(AMSActionConstant.UPDATE_ACTION)) {
				amsPaMatchLogDAO.updateData();
				forwardURL = "���ٴ�ִ��com.sino.ams.prematch.servlet.AmsPaMatchLogServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else if (action.equals(AMSActionConstant.DELETE_ACTION)) {
				amsPaMatchLogDAO.deleteData();
				forwardURL = "���ٴ�ִ��com.sino.ams.prematch.servlet.AmsPaMatchLogServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (PoolPassivateException ex) {
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
		} catch (DataHandleException ex) {
			ex.printLog();
			//�����ʵ�����������Ϣ
			forwardURL = "���ֽ���¼������ݣ����ص�ԭҳ�棬����ʾ�����������Ϣ";
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			//����ʵ������޸�ҳ����ת���롣
		}
	}
}