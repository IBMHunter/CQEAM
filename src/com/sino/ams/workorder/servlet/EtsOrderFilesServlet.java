package com.sino.ams.workorder.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.EtsOrderFilesDAO;
import com.sino.ams.workorder.dto.EtsOrderFilesDTO;
import com.sino.ams.workorder.model.EtsOrderFilesModel;
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
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsOrderFilesServlet</p>
 * <p>Description:�����Զ����ɷ������EtsOrderFilesServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class EtsOrderFilesServlet extends BaseServlet {

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
			req2DTO.setDTOClassName(EtsOrderFilesDTO.class.getName());
			EtsOrderFilesDTO dtoParameter = (EtsOrderFilesDTO)req2DTO.getDTO(req);
			String action = dtoParameter.getAct();
			conn = getDBConnection(req);
			EtsOrderFilesDAO etsOrderFilesDAO = new EtsOrderFilesDAO(user, dtoParameter, conn);
			if (action.equals("")) {
				forwardURL = "com.sino.ams.workorder.servlet.EtsOrderFilesServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new EtsOrderFilesModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = "com.sino.ams.workorder.servlet.EtsOrderFilesServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
				EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)req.getAttribute("��ȡ��Ϊʧ�ܶ����ֵ����ݣ������ʵ������޸�");
				if(etsOrderFiles == null){
					etsOrderFiles= dtoParameter;//��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.workorder.dto.EtsOrderFilesDTO�Ĺ��캯��ȷ��
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsOrderFiles);
				forwardURL = "com.sino.ams.workorder.servlet.EtsOrderFilesServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				etsOrderFilesDAO.setDTOClassName(EtsOrderFilesDTO.class.getName());
				EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)etsOrderFilesDAO.getDataByPrimaryKey();
				if(etsOrderFiles == null){
					etsOrderFiles = new EtsOrderFilesDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsOrderFiles);
				forwardURL = "com.sino.ams.workorder.servlet.EtsOrderFilesServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
			} else if (action.equals(WebActionConstant.CREATE_ACTION)) {
				etsOrderFilesDAO.createData();
				forwardURL = "���ٴ�ִ��com.sino.ams.workorder.servlet.EtsOrderFilesServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
				etsOrderFilesDAO.updateData();
				forwardURL = "���ٴ�ִ��com.sino.ams.workorder.servlet.EtsOrderFilesServlet��QUERY_ACTION�������ʵ�����ȷ��";
			} else if (action.equals(WebActionConstant.DELETE_ACTION)) {
				etsOrderFilesDAO.deleteData();
				forwardURL = "���ٴ�ִ��com.sino.ams.workorder.servlet.EtsOrderFilesServlet��QUERY_ACTION�������ʵ�����ȷ��";
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