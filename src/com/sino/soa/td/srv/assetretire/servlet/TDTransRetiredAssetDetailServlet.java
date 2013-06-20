package com.sino.soa.td.srv.assetretire.servlet;

import com.sino.ams.bean.OrgOptionProducer;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.TimeException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
//import com.sino.soa.mis.srv.assetretire.srv.TransRetiredAssetDetailSrv;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.common.SrvWebActionConstant;
import com.sino.soa.td.srv.assetretire.dto.TDSrvRetiredAssetDTO;    //�ʲ����״̬  
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.soa.td.srv.assetretire.srv.TDTransRetiredAssetDetailSrv;      //TD����

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.sql.Connection;

/**
 * User: wangzhipeng
 * Date: 2011-10-09
 * Function:�����ʲ���Ϣ��ȡ_TD��ODI��  
 */

public class TDTransRetiredAssetDetailServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        int count = 0;
        int errerCount = 0;
        long resumeTime = 0;
        try {
            conn = getDBConnection(req);
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(TDSrvRetiredAssetDTO.class.getName());
            TDSrvRetiredAssetDTO dtoParameter = (TDSrvRetiredAssetDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            message = new Message();
            String assetsType = StrUtil.nullToString(req.getParameter("assetsType"));
            String range = assetsType.equals("TD") ? "Y" : "N";
            dtoParameter.setAssetsType(assetsType);
            OrgOptionProducer optProducer = new OrgOptionProducer(user, conn);
            String opt = optProducer.getBookTypeCodeOption(dtoParameter.getBookTypeCode(), range);
            dtoParameter.setOrgOption(opt);
            if (action.equals("")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.TD_SRV_TRANS_RETIRED_PAGE;
            } /*else if (action.equals(SrvWebActionConstant.INFORSYN) && !AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) {  //��TD
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                long start = System.currentTimeMillis();

                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ�������ʲ���ʼ");
                logUtil.synLog(logDTO, conn);
                String envCode = SynUpdateDateUtils.getEnvCode("TransRetiredAssetDetailSrv", conn); //��������
                TransRetiredAssetDetailSrv srv = new TransRetiredAssetDetailSrv();
                srv.setEnvCode(envCode);
                srv.setBookTypeCode(dtoParameter.getBookTypeCode());
                srv.setStartRetireDate(dtoParameter.getDateEffectiveFrom());
                srv.setStartRetireDate(dtoParameter.getDateRettredFrom());
                srv.setEndRetireDate(dtoParameter.getDateRettredTo());
                srv.excute();

                SrvReturnMessage srm = srv.getReturnMessage();
                if (srm.getErrorFlag().equals("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ�������ʲ��ɹ���");
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    message = new Message();
                    message.setMessageValue("ͬ�������ʲ��ɹ�������ʱ" + resumeTime + "����");
                } else {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ�������ʲ�ʧ�ܣ�");
                    logUtil.synLog(logDTO, conn);
                    message = new Message();
                    message.setMessageValue("ͬ�������ʲ�ʧ�ܣ�����ʱ" + resumeTime + "����");
                }

                forwardURL = SrvURLDefineList.SRV_TRANS_RETIRED_PAGE;

            } */else if (action.equals(SrvWebActionConstant.INFORSYN) && AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) { //TD
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                long start = System.currentTimeMillis();

                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ�������ʲ���ʼ");
                logUtil.synLog(logDTO, conn);
                
                

                String envCode = SynUpdateDateUtils.getEnvCode("TDTransRetiredAssetDetailSrv", conn);
//                String envCode = SynUpdateDateUtils.getEnvCode("TransRetiredAssetDetailSrv", conn);
                TDTransRetiredAssetDetailSrv srv = new TDTransRetiredAssetDetailSrv();
                srv.setEnvCode(envCode);
                srv.setBookTypeCode(dtoParameter.getBookTypeCode());
                srv.setStartRetireDate(dtoParameter.getDateEffectiveFrom());
                srv.setStartRetireDate(dtoParameter.getDateRettredFrom());
                srv.setEndRetireDate(dtoParameter.getDateRettredTo());
                srv.excute();

                SrvReturnMessage srm = srv.getReturnMessage();
                if (srm.getErrorFlag().equals("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ������TD�ʲ��ɹ���");
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    message = new Message();
                    message.setMessageValue("ͬ������TD�ʲ��ɹ�������ʱ" + resumeTime + "����");
                } else {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_RETIRE);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ������TD�ʲ�ʧ�ܣ�");
                    logUtil.synLog(logDTO, conn);
                    message = new Message();
                    message.setMessageValue("ͬ������TD�ʲ�ʧ�ܣ�����ʱ" + resumeTime + "����");
                }

                forwardURL = SrvURLDefineList.TD_SRV_TRANS_RETIRED_PAGE;
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
