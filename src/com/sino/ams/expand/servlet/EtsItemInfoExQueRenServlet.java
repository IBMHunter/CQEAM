package com.sino.ams.expand.servlet;


import java.sql.Connection;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.constant.message.*;
import com.sino.base.db.conn.DBManager;
import com.sino.base.message.Message;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.base.constant.message.MsgKeyConstant;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.expand.dto.EtsItemInfoExQueRenDTO;
import com.sino.ams.expand.model.EtsItemInfoExQueRenModel;
import com.sino.ams.expand.dao.EtsItemInfoExQueRenDAO;


/**
 * <p>Title: EtsItemInfoExQueRenServlet</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoExQueRenServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EtsItemInfoExQueRenServlet extends BaseServlet {

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
			req2DTO.setDTOClassName(EtsItemInfoExQueRenDTO.class.getName());
			EtsItemInfoExQueRenDTO dtoParameter = (EtsItemInfoExQueRenDTO)req2DTO.getDTO(req);
			String action = dtoParameter.getAct();
			conn = getDBConnection(req);
			EtsItemInfoExQueRenDAO etsItemInfoExQueRenDAO = new EtsItemInfoExQueRenDAO(user, dtoParameter, conn);
			OptionProducer optProducer = new OptionProducer(user, conn);
			String financePropOpt=optProducer.getDictOption(WebAttrConstant.FINANCE_PROP, dtoParameter.getFinanceProp());
			req.setAttribute(WebAttrConstant.FINANCE_PROP_OPTION, financePropOpt);
			if (action.equals("")) {
				forwardURL = URLDefineList.ETS_ITEM_INFO_EX_QUEREN_QUERY_PAGE;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new EtsItemInfoExQueRenModel(user, dtoParameter);		
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = URLDefineList.ETS_ITEM_INFO_EX_QUEREN_QUERY_PAGE;
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
				EtsItemInfoExQueRenDTO etsItemInfoEx = (EtsItemInfoExQueRenDTO)req.getAttribute(WebAttrConstant.DZYH_DATA);
				if(etsItemInfoEx == null){
					etsItemInfoEx= dtoParameter;//��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.dzyh.dto.EtsItemInfoExDTO�Ĺ��캯��ȷ��
				}
				req.setAttribute(WebAttrConstant.DZYH_DATA, etsItemInfoEx);
				forwardURL = URLDefineList.ETS_ITEM_INFO_EX_QUEREN_DETAIL_PAGE;
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				etsItemInfoExQueRenDAO.setDTOClassName(EtsItemInfoExQueRenDTO.class.getName());
				EtsItemInfoExQueRenDTO etsItemInfoEx = (EtsItemInfoExQueRenDTO)etsItemInfoExQueRenDAO.getDataByPrimaryKey();
				if(etsItemInfoEx == null){
					etsItemInfoEx = new EtsItemInfoExQueRenDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				req.setAttribute(WebAttrConstant.DZYH_DATA, etsItemInfoEx);
				forwardURL = URLDefineList.ETS_ITEM_INFO_EX_QUEREN_DETAIL_PAGE;
			} else if (action.equals(WebActionConstant.CREATE_ACTION)) {
				etsItemInfoExQueRenDAO.createData();
				forwardURL = URLDefineList.ETS_ITEM_INFO_EX_QUEREN_QUERY_PAGE;
			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
				etsItemInfoExQueRenDAO.updateData();
				forwardURL = URLDefineList.ETS_ITEM_INFO_EX_QUEREN_QUERY_PAGE;
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