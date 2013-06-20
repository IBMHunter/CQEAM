package com.sino.soa.mis.srv.transTaskInfo.servlet;

import com.sino.ams.bean.OrgOptionProducer;
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
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.common.SrvWebActionConstant;
import com.sino.soa.mis.srv.transTaskInfo.dto.TransTaskInfoDTO;
import com.sino.soa.mis.srv.transTaskInfo.srv.TransTaskInfoSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.sql.Connection;

/**
 * <p>Title: TransAssetDistributionSevlet</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzp
 * @version 1.0
 * MISϵͳ��Ŀ������Ϣ(ODI)
 */

public class TransTaskInfoSevlet extends BaseServlet {

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
        boolean autoCommit = true;
        long resumeTime = 0;
        try {
            conn = getDBConnection(req);
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(TransTaskInfoDTO.class.getName());
            TransTaskInfoDTO dtoParameter = (TransTaskInfoDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            String assetsType = StrUtil.nullToString(req.getParameter("assetsType"));
            OrgOptionProducer optionProducer = new OrgOptionProducer(user, conn);
            String range = assetsType.equals("TD") ? "Y" : "N";
            dtoParameter.setAssetsType(assetsType);
            String opt = optionProducer.getBookTypeCodeOption(dtoParameter.getProjectNum(),range);   //��ȡ��Ŀ��� ��Ҫ�޸�
            dtoParameter.setOrgOption(opt);
            req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
            if (action.equals("")) {
                forwardURL = SrvURLDefineList.TRANS_TASKINFO_PAGE;
            } else if (action.equals(SrvWebActionConstant.INFORSYN) && !AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) { //��TD
                long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                DTOSet ds = new DTOSet();
                ds.addDTO(dtoParameter);
                String envCode = SynUpdateDateUtils.getEnvCode("TransTaskInfoSrv", conn);   //
                TransTaskInfoSrv service = new TransTaskInfoSrv();
                service.setEnvCode(envCode);
                service.setProjectNum(dtoParameter.getProjectNum());  //��Ŀ���
                service.setTaskNum(dtoParameter.getTaskNum());        //������
                // service.setBookTypeCode(dtoParameter.getBookTypeCode());
                if (StrUtil.isNotEmpty(dtoParameter.getEndLastUpdateDate())) {
                    service.setStratLastUpdateDate(dtoParameter.getStratLastUpdateDate());
                }
                if (StrUtil.isNotEmpty(dtoParameter.getEndLastUpdateDate())) {
                    service.setEndLastUpdateDate(dtoParameter.getEndLastUpdateDate());
                }
                service.excute();
                SrvReturnMessage srvMessage = service.getReturnMessage();
                resumeTime = System.currentTimeMillis() - start;
                message = new Message();
                if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_PA_TASK, conn);          //������Ҫȷ��
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_PA_TASK);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��'MISϵͳ��Ŀ������Ϣ(ODI)'�ɹ�, ��ʱ" + resumeTime + "����, ��Ŀ���:"+dtoParameter.getProjectNum());
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_PA_TASK, conn);
                    message.setMessageValue("ͬ��'MISϵͳ��Ŀ������Ϣ(ODI)'�ɹ�, ��ʱ" + resumeTime + "����");
                } else {
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_PA_TASK);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��'MISϵͳ��Ŀ������Ϣ(ODI)'ʧ��, ��ʱ" + resumeTime + "����, ��Ŀ���:"+dtoParameter.getProjectNum()+", ������Ϣ:"+srvMessage.getErrorMessage());
                    logUtil.synLog(logDTO, conn);
                    message.setMessageValue("ͬ��'MISϵͳ��Ŀ������Ϣ(ODI)'ʧ��, ��ʱ" + resumeTime + "����, ������Ϣ:"+srvMessage.getErrorMessage());
                }
                forwardURL = SrvURLDefineList.TRANS_TASKINFO_PAGE;
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