package com.sino.ams.workorder.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.URLDefineList;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.GroupScanDAO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class GroupFrameServlet extends BaseServlet {

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
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
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EtsWorkorderDTO.class.getName());
			EtsWorkorderDTO dto = (EtsWorkorderDTO) req2DTO.getDTO(req);
			String action = dto.getAct();
			conn = getDBConnection(req);
			if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				GroupScanDAO scanDAO = new GroupScanDAO(user, dto, conn);
				EtsWorkorderDTO firstDTO = scanDAO.getFirstScanLocation();
				firstDTO.setAct(action);
				firstDTO.setGroupId(dto.getGroupId());
				firstDTO.setStartDate(dto.getStartDate().getCalendarValue());
				firstDTO.setEndDate(dto.getEndDate().getCalendarValue());
				req.setAttribute(QueryConstant.QUERY_DTO, firstDTO);
				forwardURL = URLDefineList.GROUP_FRAME_RPT;
			} else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (CalendarException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}
}
