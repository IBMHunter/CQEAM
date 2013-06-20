package com.sino.soa.mis.srv.InquiryRetiredAssetDetail.servlet;

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
import com.sino.ams.newasset.bean.AssetsOptProducer;
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
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.soa.mis.srv.InquiryRetiredAssetDetail.dao.PagRetiredAssetDetailDAO;
import com.sino.soa.mis.srv.InquiryRetiredAssetDetail.dto.PageRetiredAssetDTO;
import com.sino.soa.mis.srv.InquiryRetiredAssetDetail.srv.PageInquiryRetiredAssetDetailSrv;
//import srv.tdams.srv.TDInquiryRetiredAssetDetailSrv; //TD

/**
 * <p>Title: SrvRetiredAssetDetailServlet</p>
 * <p>Description:�����Զ����ɷ������SrvRetiredAssetDetailServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * user:wangzp
 * function:��ѯ�����ʲ�������Ϣ����ҳ��
 */


public class PageRetiredAssetDetailServlet extends BaseServlet {

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
        int errerCount = 0;
        long resumeTime = 0;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(PageRetiredAssetDTO.class.getName());
            PageRetiredAssetDTO dtoParameter = (PageRetiredAssetDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            String assetsType = req.getParameter("assetsType");
            conn = getDBConnection(req);
            dtoParameter.setAssetsType(assetsType);
            PagRetiredAssetDetailDAO retiredAssetDetailDAO = new PagRetiredAssetDetailDAO(user, dtoParameter, conn);
            AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String opt = optProducer.getBookTypeOption(dtoParameter.getBookTypeCode());
            dtoParameter.setOrgOption(opt);
            if (action.equals("")) {
            	req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.SRV_RETIRED_ASSET_DETAIL1_PAGE;
            } else if (action.equals(SrvWebActionConstant.QUERY_ACTION)) {
                PageInquiryRetiredAssetDetailSrv service = new PageInquiryRetiredAssetDetailSrv();
                service.setBookTypeCode(dtoParameter.getBookTypeCode());   //�ʲ��˲�
                service.setAssetNumber(dtoParameter.getAssetNumber());     
                service.setLocalionDep(dtoParameter.getLocalionDep());     //����
                service.setTagNumber(dtoParameter.getTagNumber());
                service.setRetirementTypeCode(dtoParameter.getRetirementTypeCode());
                service.setDateRettredFrom(dtoParameter.getDateRettredFrom());
                service.setDateRettredTo(dtoParameter.getDateRettredTo());
                service.setDateEffectiveFrom(dtoParameter.getDateEffectiveFrom());
                service.setDateEffectiveTo(dtoParameter.getDateEffectiveTo());

                service.excute();
                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag() != null && srm.getErrorFlag().equals("Y")) {
                    DTOSet ds = service.getDs();
                    req.setAttribute(SrvWebActionConstant.ASSETBOOKTRANSOU, ds);
                    req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                    forwardURL = SrvURLDefineList.SRV_RETIRED_ASSET_DETAIL1_PAGE;
                } else {
                    message.setMessageValue("����WebService����ʧ�ܣ�" + srm.getErrorMessage());
                    req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                    forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
                }
            } else if (action.equals(SrvWebActionConstant.INFORSYN)&& !AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) { //��TD
                long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ����ȡMIS�����ʲ���ʼ");
                logUtil.synLog(logDTO, conn);
                
                PageInquiryRetiredAssetDetailSrv service = new PageInquiryRetiredAssetDetailSrv();
                
                service.setBookTypeCode(dtoParameter.getBookTypeCode());
                service.setAssetNumber(dtoParameter.getAssetNumber());
                service.setLocalionDep(dtoParameter.getLocalionDep());
                service.setTagNumber(dtoParameter.getTagNumber());    //4187-00000002 
                service.setRetirementTypeCode(dtoParameter.getRetirementTypeCode());
                
                service.setDateEffectiveFrom(dtoParameter.getDateEffectiveFrom());
                service.setDateEffectiveTo(dtoParameter.getDateEffectiveTo());
                service.setDateRettredFrom(dtoParameter.getDateRettredFrom());
                service.setDateRettredTo(dtoParameter.getDateRettredTo());
                service.excute();
                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag().equals("Y")) {
                	SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    DTOSet ds = service.getDs();
                    if (ds.getSize() > 0) {
                        count = retiredAssetDetailDAO.saveRetireAssets(ds);
                    } 
                    errerCount = retiredAssetDetailDAO.getErrorCount();
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ����ȡMIS�����ʲ�����, ͬ��"+(errerCount+count)+"����¼���ɹ�"+count+"��ʧ��"+(errerCount)+"����ʱ"+resumeTime+"����, �ʲ��˲���"+dtoParameter.getBookTypeCode());
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    message=new Message();
                    message.setMessageValue("ͬ����ȡMIS�����ʲ�"+(errerCount+count)+"����¼���ɹ�"+count+"��ʧ��"+(errerCount)+"����ʱ"+resumeTime+"����");
                }else{
                	 resumeTime = System.currentTimeMillis() - start;
                     logDTO = new SynLogDTO();
                     logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                     logDTO.setCreatedBy(user.getUserId());
                     logDTO.setSynMsg("ͬ����ȡMIS�����ʲ�ʧ��, ��ʱ"+resumeTime+"����, �ʲ��˲���"+dtoParameter.getBookTypeCode()+"��������Ϣ:"+srm.getErrorMessage());
                     logUtil.synLog(logDTO, conn);
                     message=new Message();
                     message.setMessageValue("ͬ����ȡMIS�����ʲ�ʧ�ܣ���ʱ"+resumeTime+"����, �ʲ��˲���"+dtoParameter.getBookTypeCode()+"��������Ϣ:"+srm.getErrorMessage());
                }
                
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.SRV_RETIRED_ASSET_DETAIL1_PAGE;  
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