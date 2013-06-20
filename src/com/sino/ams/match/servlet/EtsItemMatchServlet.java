package com.sino.ams.match.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.match.dao.EtsItemMatchDAO;
import com.sino.ams.match.dto.EtsItemMatchDTO;
import com.sino.ams.match.model.EtsItemMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsItemMatchServlet</p>
 * <p>Description:�����Զ����ɷ������EtsItemMatchServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-jiachuanchuan
 * @version 1.0
 */


public class EtsItemMatchServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			EtsItemMatchDTO dtoParameter = null;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EtsItemMatchDTO.class.getName());
			dtoParameter = (EtsItemMatchDTO)req2DTO.getDTO(req);
			conn = getDBConnection(req);
			EtsItemMatchDAO etsItemMatchDAO = new EtsItemMatchDAO(user, dtoParameter, conn);
			if (action.equals("")) {
				forwardURL = "com.sino.ams.match.servlet.EtsItemMatchServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new EtsItemMatchModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = "com.sino.ams.match.servlet.EtsItemMatchServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
				EtsItemMatchDTO etsItemMatch = (EtsItemMatchDTO)req.getAttribute("��ȡ��Ϊʧ�ܶ����ֵ����ݣ������ʵ������޸�");
				if(etsItemMatch == null){
					etsItemMatch= dtoParameter;//��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.match.dto.EtsItemMatchDTO�Ĺ��캯��ȷ��
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsItemMatch);
				forwardURL = "com.sino.ams.match.servlet.EtsItemMatchServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				etsItemMatchDAO.setDTOClassName(EtsItemMatchDTO.class.getName());
				EtsItemMatchDTO etsItemMatch = (EtsItemMatchDTO)etsItemMatchDAO.getDataByPrimaryKey();
				if(etsItemMatch == null){
					etsItemMatch = new EtsItemMatchDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsItemMatch);
				forwardURL = "com.sino.ams.match.servlet.EtsItemMatchServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.CREATE_ACTION)) {
				etsItemMatchDAO.createData();
				forwardURL = "���ٴ�ִ��com.sino.ams.match.servlet.EtsItemMatchServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
				etsItemMatchDAO.updateData();
				forwardURL = "���ٴ�ִ��com.sino.ams.match.servlet.EtsItemMatchServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else if (action.equals(WebActionConstant.DELETE_ACTION)) {
				etsItemMatchDAO.deleteData();
				forwardURL = "���ٴ�ִ��com.sino.ams.match.servlet.EtsItemMatchServlet��QUERY_ACTION�������ʵ�����ȷ��";
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