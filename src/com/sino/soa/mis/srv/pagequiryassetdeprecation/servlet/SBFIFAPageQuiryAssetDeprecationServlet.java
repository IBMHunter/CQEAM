package com.sino.soa.mis.srv.pagequiryassetdeprecation.servlet;

import com.sino.ams.bean.OrgOptionProducer;
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
import com.sino.soa.mis.srv.pagequiryassetdeprecation.dao.SBFIFAPageQuiryAssetDeprecationDAO;
import com.sino.soa.mis.srv.pagequiryassetdeprecation.dto.SBFIFAPageQuiryAssetDeprecationDTO;
import com.sino.soa.mis.srv.pagequiryassetdeprecation.srv.SBFIFAPageQuiryAssetDeprecationSrv;
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
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-10-14
 * Time: 16:02:44
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFAPageQuiryAssetDeprecationServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        int count = 0;
        long resumeTime = 0;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(SBFIFAPageQuiryAssetDeprecationDTO.class.getName());
            SBFIFAPageQuiryAssetDeprecationDTO dtoParameter = (SBFIFAPageQuiryAssetDeprecationDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            conn = getDBConnection(req);
            SBFIFAPageQuiryAssetDeprecationDAO srvAssetCategoryDAO = new SBFIFAPageQuiryAssetDeprecationDAO(user, dtoParameter, conn);
            OrgOptionProducer optionProducer = new OrgOptionProducer(user, conn);
            String range = "N";//��TD
            String opt = optionProducer.getBookTypeCodeOption(dtoParameter.getBookTypeCode(), range);
            dtoParameter.setOrgOption(opt);
            req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
            if (action.equals("")) {
                forwardURL = SrvURLDefineList.PAGE_TRANS_ASSET_DEPRECATION_PAGE;
            } else if (action.equals(SrvWebActionConstant.QUERY_ACTION)) {
                SBFIFAPageQuiryAssetDeprecationSrv service = new SBFIFAPageQuiryAssetDeprecationSrv();
                String bookTypeCode = req.getParameter("bookTypeCode");
                String lastUpdateDate = req.getParameter("lastUpdateDate");
                String segment1 = req.getParameter("segment1");
                String concatenatedSegments = req.getParameter("concatenatedSegments");
                if (StrUtil.isNotEmpty(bookTypeCode)) {
                    service.setBookTypeCode(bookTypeCode);
                }
                service.execute();
                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag() != null && srm.getErrorFlag().equals("Y")) {
                    DTOSet ds = service.getDs();
                    req.setAttribute(SrvWebActionConstant.ASSETBOOKTRANSOU, ds);
                    forwardURL = SrvURLDefineList.PAGE_TRANS_ASSET_DEPRECATION_PAGE;
                }
            } else if (action.equals(SrvWebActionConstant.INFORSYN)) {
                long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_PAGE_ASSET_DEPERACTION);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS�ʲ��۾���Ϣ��ʼ");
                logUtil.synLog(logDTO, conn);

                SBFIFAPageQuiryAssetDeprecationSrv service = new SBFIFAPageQuiryAssetDeprecationSrv();
                service.setBookTypeCode(dtoParameter.getBookTypeCode());
                service.setTagNumber(dtoParameter.getTagNumber());
                service.setStartLastUpdateDate(dtoParameter.getStartLastUpdateDate());
                service.setEndLastUpdateDate(dtoParameter.getEndLastUpdateDate());
                service.execute();

                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag().equalsIgnoreCase("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_PAGE_ASSET_DEPERACTION, conn);
                    DTOSet ds = service.getDs();
                    if (ds.getSize() > 0) {
                        count = srvAssetCategoryDAO.SavaAssetCategory(ds);
                    }
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_PAGE_ASSET_DEPERACTION);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS�ʲ��۾���Ϣ������");
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_PAGE_ASSET_DEPERACTION, conn);

                    message = new Message();
                    message.setMessageValue("ͬ��" + (ds.getSize()) + "����¼���ɹ�" + count + "��ʧ��" + (ds.getSize() - count) + "����ʱ" + resumeTime + "����");
                }
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.PAGE_TRANS_ASSET_DEPRECATION_PAGE;

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