package com.sino.ams.newasset.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dao.NewTagProduceDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.soa.mis.srv.assetTagNumber.dto.SBFIFAInquiryAssetTagNumberDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class NewTagProduceServlet extends BaseServlet {

    /**
     * ���е�Servlet������ʵ�ֵķ�����
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        boolean hasError = true;
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) getUserAccount(req);
            conn = getDBConnection(req);
            res.setContentType("text/html;charset=GBK");
            PrintWriter out = res.getWriter();

            NewTagProduceDAO newTagProducer = new NewTagProduceDAO(user, null, conn);
            String fromOrganizationId=req.getParameter("fromOrganizationId");
            String organizationId=req.getParameter("toOrganizationId");
            String transId=req.getParameter("transId");
            String transNo=req.getParameter("transNo");
            int count=Integer.parseInt(req.getParameter("count"));

            DTOSet ds=newTagProducer.genNewBarcodes(organizationId,count);
            newTagProducer.logBarcodes(transId,ds);
            SBFIFAInquiryAssetTagNumberDTO dto = null;
            StringBuffer responseContent = new StringBuffer();
            for (int i = 0; i < ds.getSize(); i++) {
                dto = (SBFIFAInquiryAssetTagNumberDTO) ds.getDTO(i);
                responseContent.append(dto.getTagNumber());
                if (i < ds.getSize() - 1) {
                    responseContent.append("&&&");
                }
            }
            out.println(responseContent.toString());
            hasError = false;
        } catch (PoolPassivateException ex) {
            ex.printLog();
        } catch (ContainerException e) {
            e.printLog();
        } catch (QueryException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            Logger.logError(e);
        } finally {
            closeDBConnection(conn);
            if (hasError) {
                message = getMessage(AssetsMessageKeys.COMMON_ERROR);
                message.setIsError(true);
                setHandleMessage(req, message);
                ServletForwarder forwarder = new ServletForwarder(req, res);
                forwarder.forwardView(MessageConstant.MSG_PRC_SERVLET);
            }
        }
    }
}

