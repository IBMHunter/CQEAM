package com.sino.ams.workorder.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.TrunListDetailQueryDAO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.TrunListDetailQueryModel;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

public class TrunListDetailQueryServlet extends BaseServlet {
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		try {
			conn = DBManager.getDBConnection();
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);// ��session�л�ȡ���ݣ�����ʵ����������޸ġ�
			EtsWorkorderDTO dtoParameter = null;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EtsWorkorderDTO.class.getName());
			dtoParameter = (EtsWorkorderDTO) req2DTO.getDTO(req);
			// ��֯
			OptionProducer prd = new OptionProducer(user, conn);
			String ouoption = prd.getAllOrganization(dtoParameter
					.getOrganizationId(), true);
			req.setAttribute(WebAttrConstant.OU_OPTION, ouoption);
			//����״̬
            String status = prd.getDictOption(DictConstant.WORKORDER_STATUS, (String.valueOf(dtoParameter.getWorkorderFlag())));
            req.setAttribute(WebAttrConstant.PLAN_STATUS_OPTION, status);
			String cat = prd.getDictOption(DictConstant.OBJECT_CATEGORY,
					dtoParameter.getObjectCategory());
			req.setAttribute("CATEGORY", cat);
			SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
			if (action.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
				forwardURL = URLDefineList.TRUN_LIST_DITAIL_QUERY;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
				BaseSQLProducer sqlProducer = new TrunListDetailQueryModel(
						user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setCalPattern(LINE_PATTERN);
				pageDAO.produceWebData();
				forwardURL = URLDefineList.TRUN_LIST_DITAIL_QUERY;
			} else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) {
				TrunListDetailQueryDAO dao = new TrunListDetailQueryDAO(user,
						dtoParameter, conn);
				File file = dao.getExportFile(dtoParameter);
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			} else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (PoolException e) {
			e.printLog();
			message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException e) {
			e.printLog();
			message = getMessage(AssetsMessageKeys.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException e) {
			e.printLog();
			message = getMessage(AssetsMessageKeys.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DataTransException e) {
			e.printLog();
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (WebFileDownException e) {
			e.printLog();
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
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