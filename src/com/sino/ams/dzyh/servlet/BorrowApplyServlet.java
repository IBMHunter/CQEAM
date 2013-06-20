package com.sino.ams.dzyh.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.dzyh.bean.LvecOptProducer;
import com.sino.ams.dzyh.constant.DzyhActionConstant;
import com.sino.ams.dzyh.constant.LvecURLs;
import com.sino.ams.dzyh.constant.LvecWebAttributes;
import com.sino.ams.dzyh.dao.EamYbBorrowLogDAO;
import com.sino.ams.dzyh.dto.EamYbBorrowLogDTO;
import com.sino.ams.dzyh.model.EamYbBorrowLogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class BorrowApplyServlet extends BaseServlet {

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
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EamYbBorrowLogDTO.class.getName());
			EamYbBorrowLogDTO dto = (EamYbBorrowLogDTO) req2DTO.getDTO(req);
			String action = dto.getAct();
			conn = getDBConnection(req);
			EamYbBorrowLogDAO borrowDAO = new EamYbBorrowLogDAO(user, dto, conn);
			LvecOptProducer optPrd = new LvecOptProducer(user, conn);
			if (action.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = LvecURLs.BORROW_LIST_PAGE;
			} else if (action.equals(DzyhActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new EamYbBorrowLogModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setDTOClassName(EamYbBorrowLogDTO.class.getName());
				pageDAO.setCalPattern(LINE_PATTERN);
				pageDAO.produceWebData();

				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = LvecURLs.BORROW_LIST_PAGE;
			} else if (action.equals(DzyhActionConstant.EXPORT_ACTION)) {
				File file = borrowDAO.getExportFile();
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			} else if (action.equals(DzyhActionConstant.NEW_ACTION)) {//�򿪿յ���������������Ǳ��豸����
				EamYbBorrowLogDTO borrowDTO = (EamYbBorrowLogDTO) req.getAttribute(LvecWebAttributes.BORROW_DTO);
				if (borrowDTO == null) {
					borrowDTO = borrowDAO.getBarcodeApply(); //��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.lvec.dto.EamDhCheckBatchDTO�Ĺ��캯��ȷ��
				}
				if(borrowDTO == null){
					borrowDTO = dto;
					message = borrowDAO.getMessage();
				}
				borrowDTO.setGroupOption(optPrd.getUserGroupOption(borrowDTO.getGroupId()));
				borrowDTO.setCalPattern(LINE_PATTERN);
				req.setAttribute(LvecWebAttributes.BORROW_DTO, borrowDTO);
				forwardURL = LvecURLs.BORROW_DATA_PAGE;
			} else if (action.equals(DzyhActionConstant.DETAIL_ACTION)) {//�򿪾�����Ǳ���������
				EamYbBorrowLogDTO borrowDTO = (EamYbBorrowLogDTO) req.getAttribute(LvecWebAttributes.BORROW_DTO);
				if (borrowDTO == null) {
					borrowDAO.setDTOClassName(EamYbBorrowLogDTO.class.getName());
					borrowDTO = (EamYbBorrowLogDTO) borrowDAO.getDataByPrimaryKey();
				}
				borrowDTO.setGroupOption(optPrd.getUserGroupOption(borrowDTO.getGroupId()));
				borrowDTO.setCalPattern(LINE_PATTERN);
				req.setAttribute(LvecWebAttributes.BORROW_DTO, borrowDTO);
				forwardURL = LvecURLs.BORROW_DATA_PAGE;
			} else if (action.equals(DzyhActionConstant.SAVE_ACTION)) {//��������
				borrowDAO.saveBorrowApply();
				message = borrowDAO.getMessage();
				EamYbBorrowLogDTO borrowDTO = (EamYbBorrowLogDTO)borrowDAO.getDTOParameter();
				String borrowLogId = borrowDTO.getBorrowLogId();
				if(borrowLogId.equals("")){
					forwardURL = LvecURLs.BORROW_SERVLET;
					forwardURL += "?act=" + DzyhActionConstant.NEW_ACTION;
				} else {
					forwardURL = LvecURLs.BORROW_SERVLET;
					forwardURL += "?act=" + DzyhActionConstant.DETAIL_ACTION;
					forwardURL += "&borrowLogId=" + borrowDTO.getBorrowLogId();
				}
				borrowDTO.setCalPattern(LINE_PATTERN);
				req.setAttribute(LvecWebAttributes.BORROW_DTO, borrowDTO);
			} else if (action.equals(DzyhActionConstant.SUBMIT_ACTION)) {//�ύ����
				borrowDAO.saveBorrowApply();
				message = borrowDAO.getMessage();
				EamYbBorrowLogDTO borrowDTO = (EamYbBorrowLogDTO)borrowDAO.getDTOParameter();
				String borrowLogId = borrowDTO.getBorrowLogId();
				if(borrowLogId.equals("")){
					forwardURL = LvecURLs.BORROW_SERVLET;
					forwardURL += "?act=" + DzyhActionConstant.NEW_ACTION;
				} else {
					forwardURL = LvecURLs.BORROW_SERVLET;
					forwardURL += "?act=" + DzyhActionConstant.DETAIL_ACTION;
					forwardURL += "&borrowLogId=" + borrowDTO.getBorrowLogId();
				}
				borrowDTO.setCalPattern(LINE_PATTERN);
				req.setAttribute(LvecWebAttributes.BORROW_DTO, borrowDTO);
			} else if (action.equals(DzyhActionConstant.CANCEL_ACTION)) {//��������
				borrowDAO.cancelBorrowApply();
				message = borrowDAO.getMessage();
				EamYbBorrowLogDTO borrowDTO = (EamYbBorrowLogDTO)borrowDAO.getDTOParameter();
				req.setAttribute(LvecWebAttributes.BORROW_DTO, borrowDTO);
				String borrowLogId = borrowDTO.getBorrowLogId();
				if(borrowLogId.equals("")){
					forwardURL = LvecURLs.BORROW_SERVLET;
					forwardURL += "?act=" + DzyhActionConstant.NEW_ACTION;
				} else {
					forwardURL = LvecURLs.BORROW_SERVLET;
					forwardURL += "?act=" + DzyhActionConstant.DETAIL_ACTION;
					forwardURL += "&borrowLogId=" + borrowDTO.getBorrowLogId();
				}
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
		} catch (WebFileDownException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			if (!forwardURL.equals("")) {
				ServletForwarder forwarder = new ServletForwarder(req, res);
				forwarder.forwardView(forwardURL);
			}
		}
	}
}
