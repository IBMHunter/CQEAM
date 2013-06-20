package com.sino.ams.newasset.lease.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.ApproveContentDAO;
import com.sino.ams.newasset.lease.constant.LeaseURLListConstant;
import com.sino.ams.newasset.lease.dto.LeaseDTO;
import com.sino.ams.newasset.lease.model.LeaseModel;
import com.sino.ams.newasset.lease.service.LeaseService;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.data.RowSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * 
 * @ϵͳ����: ���ݲ�ѯ
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Jul 19, 2011
 */
public class LeaseQueryServlet extends LeaseServlet {

	@Override
	public void performTask(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		String forwardURL = "";
		String msg = "";
		String action = null;
		Connection conn = null;
		LeaseService service = null;
		LeaseDTO leaseDTO = null;
		SfUserDTO user = null;
		boolean isReturn = false;
		try {
			user =  (SfUserDTO) getUserAccount(req);
			// // ����ͷ����
			leaseDTO = this.getDTOFromReq(req);
			conn = getDBConnection(req);
			action = leaseDTO.getAct();
			service = new LeaseService( user , leaseDTO , conn );
			
			if( action.equals( "" ) ){
				req.setAttribute(QueryConstant.QUERY_DTO, leaseDTO.getHeaderDTO() );
				forwardURL = LeaseURLListConstant.LEASE_QUERY_PAGE ;
			}else if( action.equals( WebActionConstant.QUERY_ACTION ) ){
				BaseSQLProducer sqlProducer = new LeaseModel(user, leaseDTO.getHeaderDTO() );
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setCalPattern(LINE_PATTERN);
				pageDAO.produceWebData(); 
				req.setAttribute(QueryConstant.QUERY_DTO, leaseDTO.getHeaderDTO() );
				forwardURL = LeaseURLListConstant.LEASE_QUERY_PAGE ;
			}else if( action.equals( WebActionConstant.DETAIL_ACTION ) ){
				service.prodData();
				leaseDTO = service.getForm();
				
				//���չ��
                super.doApproveContent(req, conn, leaseDTO.getHeaderDTO().getTransId() );
				
//				leaseDTO = setOption(conn, req, userInfo, applyDTO);
                req.setAttribute( AssetsWebAttributes.ORDER_HEAD_DATA , leaseDTO );
                forwardURL = LeaseURLListConstant.LEASE_DETAIL_PAGE ;
			} else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) {
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
		} catch (DataTransException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (WebFileDownException ex) {
			ex.printLog();
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
//			DBManager.closeDBConnection( conn );
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

}
