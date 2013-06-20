package com.sino.soa.mis.srv.vendor.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.common.SrvWebActionConstant;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.soa.mis.srv.vendor.dao.SrvVendorInfoDAO;
import com.sino.soa.mis.srv.vendor.dto.SrvVendorInfoDTO;
import com.sino.soa.mis.srv.vendor.srv.InquiryVendorInfoSrv;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.TimeException;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.base.util.StrUtil;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;


/**
 * <p>Title: SrvAssetBookServlet</p>
 * <p>Description:�����Զ����ɷ������SrvAssetBookServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * DATE : 2011-09-08 wangzhipeng
 * DSC : ��Ӧ��ͬ��_��TD
 */


public class SrvVendorInfoSrvServlet extends BaseServlet {

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
	    int count = 0;
	    long resumeTime = 0;
	    int errorCount = 0;
		try {
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(SrvVendorInfoDTO.class.getName());
			SrvVendorInfoDTO dtoParameter = (SrvVendorInfoDTO)req2DTO.getDTO(req);
			String action = dtoParameter.getAct();
			String assetsType = StrUtil.nullToString(req.getParameter("assetsType"));
			dtoParameter.setAssetsType(assetsType);
			conn = getDBConnection(req);
			SrvVendorInfoDAO srvAssetBookDAO = new SrvVendorInfoDAO(user, dtoParameter, conn);
			req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
			if (action.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
				forwardURL = SrvURLDefineList.SRV_VENDOR_INFO_PAGE;
			} else if (action.equals(SrvWebActionConstant.QUERY_ACTION)) {
				InquiryVendorInfoSrv service=new InquiryVendorInfoSrv();
				service.setVendorName(dtoParameter.getVendorName());
				service.setVendorNumber(dtoParameter.getVendorNumber());
				service.setVatFlag(dtoParameter.getVatFlag());
				service.setVendorTypeDisp(dtoParameter.getVendorTypeDisp());
				if(dtoParameter.getLastUpdateDate()!=null){
					service.setLastUpdateDate(dtoParameter.getLastUpdateDate().toString());
				}
				service.excute();
				SrvReturnMessage srm=service.getReturnMessage();
				if(srm.getErrorFlag()!=null&&srm.getErrorFlag().equals("Y")){
					DTOSet ds=service.getDs();	
					req.setAttribute(SrvWebActionConstant.ASSETBOOKTRANSOU, ds);
					forwardURL = SrvURLDefineList.SRV_VENDOR_INFO_PAGE;
				}else{
					message.setMessageValue("����WebService����ʧ�ܣ�"+srm.getErrorMessage());
					forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
				}
			}else if (action.equals(SrvWebActionConstant.INFORSYN)&& !AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) {
				long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_VENDOR);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS��Ӧ����Ϣ��ʼ");
                logUtil.synLog(logDTO, conn);

                InquiryVendorInfoSrv service=new InquiryVendorInfoSrv();
				service.setVendorName(dtoParameter.getVendorName());
				service.setVendorNumber(dtoParameter.getVendorNumber());
				service.setVatFlag(dtoParameter.getVatFlag());
				service.setVendorTypeDisp(dtoParameter.getVendorTypeDisp());
				service.setLastUpdateDate(dtoParameter.getLastUpdateDate().getCalendarValue());
				service.excute();
				SrvReturnMessage srm=service.getReturnMessage();
				if(srm.getErrorFlag().equals("Y")){
					SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_VENDOR, conn);
					DTOSet ds=service.getDs();
					if(ds.getSize()>0){
						count=srvAssetBookDAO.synVendorInfo(ds);   
					}
					errorCount = srvAssetBookDAO.getErrorCount();
					resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_VENDOR);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS��Ӧ����Ϣ����, ͬ��"+(count+errorCount)+"����¼���ɹ�"+count+"��ʧ��"+errorCount+"����ʱ"+resumeTime+"����");
                    logUtil.synLog(logDTO, conn);
                	SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_VENDOR, conn);
                    message=new Message();
                    message.setMessageValue("ͬ��MIS��Ӧ����Ϣ "+(count+errorCount)+"����¼���ɹ�"+count+"��ʧ��"+errorCount+"����ʱ"+resumeTime+"����");
				} else {
					resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_VENDOR);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS��Ӧ����Ϣʧ��, ��ʱ"+resumeTime+"���롣 ������Ϣ:"+srm.getErrorMessage());
                    logUtil.synLog(logDTO, conn);
                    message=new Message();
                    message.setMessageValue("ͬ��MIS��Ӧ����Ϣʧ��, ��ʱ"+resumeTime+"���롣 ������Ϣ:"+srm.getErrorMessage());
				} 
				req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
				forwardURL = SrvURLDefineList.SRV_VENDOR_INFO_PAGE;
			}
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message.setMessageValue("ͬ��ʧ��");
			forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
		} catch (DTOException ex) {
			ex.printLog();
			message.setMessageValue("ͬ��ʧ��");
			forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
		} catch (DatatypeConfigurationException ex1) {
			message.setMessageValue("ͬ��ʧ��");
			forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
			ex1.printStackTrace();
		} catch (TimeException e) {
			message.setMessageValue("ͬ��ʧ��");
			forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
			e.printStackTrace();
		} catch (Exception e) {
			message.setMessageValue("ͬ��ʧ��");
			forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
			e.printStackTrace();
		} finally {
		 	DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}
}