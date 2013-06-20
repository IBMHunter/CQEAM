package com.sino.ams.newasset.urgenttrans.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.urgenttrans.constant.UrgentURLListConstant;
import com.sino.ams.newasset.urgenttrans.dto.UrgentDTO;
import com.sino.ams.newasset.urgenttrans.dto.UrgentHeaderDTO;
import com.sino.ams.newasset.urgenttrans.dto.UrgentLineDTO;
import com.sino.ams.newasset.urgenttrans.model.UrgentModel;
import com.sino.ams.newasset.urgenttrans.service.UrgentService;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.StrException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * 
 * @ϵͳ����: ����������
 * @��������: �鵵����
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾�� 1.0
 * @��������: sj
 * @����ʱ��: Aug 1, 2011
 */
public class UrgentArchiveServlet extends BaseServlet {

	@Override
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forwardURL = "";
		String msg = "";
		boolean isClose = false;
		String action = null;
		Connection conn = null;
		UrgentService service = null;
		UrgentDTO urgentDTO = null;
		SfUserDTO user = null;
		boolean isReturn = false;
		
		try {
			user = (SfUserDTO) getUserAccount(req);
			// // ����ͷ����
			urgentDTO = this.getDTOFromReq(req);
			conn = getDBConnection(req);
			action = urgentDTO.getAct();
			service = new UrgentService(user, urgentDTO, conn);
			if (action.equals("")) { //��ѯ
				forwardURL = UrgentURLListConstant.URGENT_ARCHIVE_QUERY_PAGE;
			} else if (action.equals(AssetsActionConstant.QUERY_ACTION)) { //��ѯ
				UrgentHeaderDTO headerDTO = urgentDTO.getHeaderDTO();
				headerDTO.setArchive( true );
				BaseSQLProducer sqlProducer = new UrgentModel( user, headerDTO );
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				CheckBoxProp checkProp = new CheckBoxProp("subCheck");
				checkProp.addDbField("TRANS_ID");
				pageDAO.setWebCheckProp(checkProp);
				pageDAO.setCalPattern(LINE_PATTERN);
				pageDAO.produceWebData();
				req.setAttribute( QueryConstant.QUERY_DTO, urgentDTO.getHeaderDTO() );
				forwardURL = UrgentURLListConstant.URGENT_ARCHIVE_QUERY_PAGE;
			}  else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) { //��ϸ��Ϣ
				service.prodArchiveData();
				urgentDTO = service.getForm();
				// urgentDTO = setOption(conn, req, userInfo, applyDTO);
				req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, urgentDTO);
				forwardURL = UrgentURLListConstant.URGENT_ARCHIVE_DETAIL_PAGE;
			} else if (action.equals( AssetsActionConstant.ARCHIVE_ACTION )) { //�鵵
				service.doArchive();
				forwardURL = null;
			} else if (action.equals( AssetsActionConstant.EXPORT_ACTION )) { //����
				urgentDTO.getHeaderDTO().setArchive( true );
				service = new UrgentService(user, urgentDTO, conn); 				
				File file = service.exportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
                isReturn = true;
			}
			msg = service.getMsg();
		} catch (PoolPassivateException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (StrException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DataTransException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (WebFileDownException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			// DBManager.closeDBConnection( conn );
			closeDBConnection(conn);
			
			if( isReturn ){
				return;
			}
			 
			ServletForwarder forwarder = new ServletForwarder(req, res);

			if (!StrUtil.isEmpty(forwardURL)) {
				forwarder.forwardView(forwardURL);
			} else {
				forwarder.forwardOpenerView(forwardURL, msg);
			}
		}
	}

	/**
	 * ȡ����
	 * 
	 * @param req
	 * @return
	 * @throws DTOException
	 */
	public UrgentDTO getDTOFromReq(HttpServletRequest req) throws DTOException {
		UrgentDTO dto = new UrgentDTO();
		UrgentHeaderDTO headerDTO = null;
		DTOSet lines = null;

		Request2DTO req2DTO = new Request2DTO();
		req2DTO.setDTOClassName(UrgentDTO.class.getName());
		dto = (UrgentDTO) req2DTO.getDTO(req);

		// ����ͷ����
		req2DTO.setDTOClassName(UrgentHeaderDTO.class.getName());
		headerDTO = (UrgentHeaderDTO) req2DTO.getDTO(req);
		dto.setHeaderDTO(headerDTO);

		// �����в���
		req2DTO.setDTOClassName(UrgentLineDTO.class.getName());
		req2DTO.setIgnoreFields(UrgentHeaderDTO.class);
		lines = req2DTO.getDTOSet(req);
		dto.setLines(lines);

		return dto;
	}

}
