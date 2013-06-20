package com.sino.soa.td.srv.assetLocComb.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.message.Message;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.ams.bean.OrgOptionProducer;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.td.srv.assetLocComb.dao.TDSrvAssetLocCombDAO;
import com.sino.soa.td.srv.assetLocComb.dto.TDSrvAssetLocCombDTO;
import com.sino.soa.td.srv.assetLocComb.model.TDSrvAssetLocCombModel;
/**
* date��2011-09-16
* user��wangzhipeng
* function���ʲ��ص������������_TD
*/
public class TDSrvAssetLocCombServlet extends BaseServlet{

	 public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        String forwardURL = "";
	        String msg = "";
	        Message message = SessionUtil.getMessage(req);
	        String action = StrUtil.nullToString(req.getParameter("act")); 
	        Connection conn = null;
	        try {
	            conn = getDBConnection(req);
	            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
	            Request2DTO req2DTO = new Request2DTO();
	            req2DTO.setDTOClassName(TDSrvAssetLocCombDTO.class.getName());
	            TDSrvAssetLocCombDTO dto = (TDSrvAssetLocCombDTO) req2DTO.getDTO(req);
	            TDSrvAssetLocCombDAO eamNewLocusDAO = new TDSrvAssetLocCombDAO(user, dto, conn);
	            OrgOptionProducer optionProducer=new OrgOptionProducer(user,conn);
//	            dto.setOrganizationOpt(optionProducer.getOrgnizationOption(dto.getOrganizationId(), "Y"));    //TD��˾
	            dto.setOrganizationOpt(optionProducer.getSynOrgnizationOption(dto.getOrganizationId(), "Y"));    //TD��˾
	            if (action.equals("")) {
	                req.setAttribute(WebAttrConstant.SYSCHRONIZE_DTO, dto);
	                forwardURL = SrvURLDefineList.TD_SRV_ASSET_LOC_COMB_PAGE;
	            } else if (action.equals(WebActionConstant.QUERY_ACTION)) { //��ѯ����
	                BaseSQLProducer sqlProducer = new TDSrvAssetLocCombModel(user, dto);
	                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
	                pageDAO.setCalPattern(LINE_PATTERN);
	                pageDAO.produceWebData();
	                req.setAttribute(WebAttrConstant.SYSCHRONIZE_DTO, dto);
	                forwardURL = SrvURLDefineList.TD_SRV_ASSET_LOC_COMB_PAGE;
	            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) { //����
	                File file = eamNewLocusDAO.getExportFile();
	                WebFileDownload fileDown = new WebFileDownload(req, res);
	                fileDown.setFilePath(file.getAbsolutePath());
	                fileDown.download();
	                file.delete();
	            }  else if (action.equals(WebActionConstant.SYSCHRONIZE_ACTION)) { //ͬ������
	                String[] systemIds = req.getParameterValues("systemids"); //��ѯ����
	                String systemId = ArrUtil.arrToSqlStr(systemIds);
	                long startTime = System.currentTimeMillis();
	                //ͬ���ص�_begin
					String isRespExist=eamNewLocusDAO.synLocComb(systemId);
				    String msgValue = "";
					if(isRespExist.equals("NO")){
						msgValue = "������ͬ���û����ٲ�����";
		                message = new Message();
		                message.setMessageValue(msgValue);
		                message.setIsError(true);
					}else{
					//���ش�����Ϣ
		                SrvReturnMessage returnMessage = eamNewLocusDAO.getReturnMessage();      
		                if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
		                    msgValue = "TD�ʲ��ص�ͬ���ɹ�����ʱ" + (System.currentTimeMillis() - startTime) + "����";
		                    msgValue += "\n" + returnMessage.getErrorMessage();
		                } else {
		                    msgValue = "TD�ʲ��ص�ͬ��ʧ�ܣ���ʱ" + (System.currentTimeMillis() - startTime) + "����";
		                    msgValue += "\n" + returnMessage.getErrorMessage();
		                }
		                message = new Message();
		                message.setMessageValue(msgValue);
					}
	                forwardURL = "/servlet/com.sino.soa.td.srv.assetLocComb.servlet.TDSrvAssetLocCombServlet?act=QUERY_ACTION";   //

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
	        } catch (DataTransException ex) {
	            ex.printLog();
	            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
	            message.setIsError(true);
	            forwardURL = MessageConstant.MSG_PRC_SERVLET;
	        } catch (WebFileDownException ex) {
	            ex.printLog();
	            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
	            message.setIsError(true);
	            forwardURL = MessageConstant.MSG_PRC_SERVLET;
	        } catch (ContainerException e) {
	            e.printStackTrace();  
	        } catch (SQLException e) {
	            e.printStackTrace();  
	        } catch (DataHandleException e) {
	            e.printStackTrace();  
	        } finally {
	            DBManager.closeDBConnection(conn);
	            setHandleMessage(req, message);
	            if (!StrUtil.isEmpty(forwardURL)) {
	                ServletForwarder forwarder = new ServletForwarder(req, res);
	                if (msg != "" && msg != null) {
	                    forwarder.forwardOpenerView(forwardURL, msg);
	                } else {
	                    forwarder.forwardView(forwardURL);
	                }
	            }
	        }
	    }
	
	
}
