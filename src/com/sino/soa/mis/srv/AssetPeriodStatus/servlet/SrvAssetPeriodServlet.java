package com.sino.soa.mis.srv.AssetPeriodStatus.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.db.QueryConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.ams.bean.OrgOptionProducer;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.common.SrvWebActionConstant;
import com.sino.soa.mis.srv.AssetPeriodStatus.dao.SrvAssetPeriodDAO;
import com.sino.soa.mis.srv.AssetPeriodStatus.dto.SrvAssetPeriodStatusDTO;
import com.sino.soa.mis.srv.AssetPeriodStatus.srv.InquiryAssetPeriodStatusSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;


/**
 * <p>Title: SrvAssetBookServlet</p>
 * <p>Description:�����Զ����ɷ������SrvAssetBookServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzhipeng
 * @version 1.0
 * DES: �ʲ�����ڼ�״̬servlet��
 */


public class SrvAssetPeriodServlet extends BaseServlet {

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
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(SrvAssetPeriodStatusDTO.class.getName());
            SrvAssetPeriodStatusDTO dtoParameter = (SrvAssetPeriodStatusDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            String assetsType = StrUtil.nullToString(req.getParameter("assetsType"));      //��TD  assetsType����Ϊnull
            dtoParameter.setAsstesType(assetsType);
            conn = getDBConnection(req);
            OrgOptionProducer orgOptionProducer = new OrgOptionProducer(user, conn);
            String range = assetsType.equals("TD") ? "Y" : "N";      //��TD: N
            dtoParameter.setBookOption(orgOptionProducer.getBookTypeCodeOption(dtoParameter.getBookTypeCode(),range));
            SrvAssetPeriodDAO srvAssetBookDAO = new SrvAssetPeriodDAO(user, dtoParameter, conn);
            req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
            if (action.equals("")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.SRV_ASSET_PERIOD_PAGE;
            } else if (action.equals(SrvWebActionConstant.QUERY_ACTION)) {
                InquiryAssetPeriodStatusSrv service = new InquiryAssetPeriodStatusSrv();
                service.setBookTypeCode(dtoParameter.getBookTypeCode());  
                //service.setBookTypeCode("SXMC_FA_4110");                //����ʹ�� ɽ��ʡ��˾
                //service.setPeriodName(dtoParameter.getPeriodName());
                service.execute();
                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag() != null && srm.getErrorFlag().equals("Y")) {
                    DTOSet ds = service.getDs();  
                    req.setAttribute(SrvWebActionConstant.ASSETBOOKTRANSOU, ds);
                    forwardURL = SrvURLDefineList.SRV_ASSET_PERIOD_PAGE;
                } else {
                    message.setMessageValue("����WebService����ʧ�ܣ�" + srm.getErrorMessage());
                    forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
                }
            } else if (action.equals(SrvWebActionConstant.INFORSYN) && !AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) {   //��TD
                long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_PERIOD);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS�ʲ�����ڼ俪ʼ");
                logUtil.synLog(logDTO, conn);
                InquiryAssetPeriodStatusSrv service = new InquiryAssetPeriodStatusSrv();
                service.setBookTypeCode(dtoParameter.getBookTypeCode());        
                service.setPeriodName(dtoParameter.getPeriodName());
                service.execute();
                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag().equals("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_PERIOD, conn);
                    DTOSet ds = service.getDs();
                    if (ds.getSize() > 0) {
                        count = srvAssetBookDAO.synAssetPeriod(ds);
                    }
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_PERIOD);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS�ʲ�����ڼ����, ͬ��" + (ds.getSize()) + "����¼���ɹ�" + count + "��ʧ��" + (ds.getSize() - count) + "����ʱ" + resumeTime + "����, �ʲ��˲���"+dtoParameter.getBookTypeCode());
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_PERIOD, conn);
                    message = new Message();
                    message.setMessageValue("ͬ��MIS�ʲ�����ڼ�" + (ds.getSize()) + "����¼���ɹ�" + count + "��ʧ��" + (ds.getSize() - count) + "����ʱ" + resumeTime + "����");
                } else {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_PERIOD);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS�ʲ�����ڼ�ʧ��, ��ʱ" + resumeTime + "����, �ʲ��˲���"+dtoParameter.getBookTypeCode()+"��������Ϣ:"+srm.getErrorMessage());
                    logUtil.synLog(logDTO, conn);
                    message = new Message();
                    message.setMessageValue("ͬ��MIS�ʲ�����ڼ�ʧ��, ��ʱ" + resumeTime + "����, �ʲ��˲���"+dtoParameter.getBookTypeCode()+"��������Ϣ:"+srm.getErrorMessage());
                }
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.SRV_ASSET_PERIOD_PAGE;
            } 
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message.setMessageValue("ͬ��ʧ��");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
        } catch (DTOException ex) {
            ex.printLog();
            message.setMessageValue("ͬ��ʧ��:DTOException");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
        } catch (TimeException e) {
            message.setMessageValue("ͬ��ʧ��:TimeException");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
            e.printLog();
        } catch (QueryException e) {
            message.setMessageValue("ͬ��ʧ��:QueryException");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
            e.printLog();
        } catch (DataHandleException e) {
            e.printLog();
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }
}