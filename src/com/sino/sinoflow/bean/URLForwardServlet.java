package com.sino.sinoflow.bean;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.util.ResUtil;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.sinoflow.framework.resource.dao.SfResDefineDAO;
import com.sino.sinoflow.framework.resource.dto.SfResDefineDTO;
import com.sino.sinoflow.framework.security.dao.PrivilegeAuthorizer;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * <p>Title: SinoFlo</p>
 * <p>Description: ����������ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 */
public class URLForwardServlet extends BaseServlet {

    /**
     * ���е�Servlet������ʵ�ֵķ�����
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        String resourceId = req.getParameter("resourceId");
        Connection conn = null;
        SfUserBaseDTO userAccount = (SfUserBaseDTO)getUserAccount(req);
        Message message = null;
        try {
            conn = getDBConnection(req);
            PrivilegeAuthorizer authorizer = new PrivilegeAuthorizer(userAccount, conn);
            SfResDefineDTO resourceDTO = authorizer.getAuthorizedResourceById(resourceId);
            if (resourceDTO != null) {
                forwardURL = resourceDTO.getResUrl();
                if (StrUtil.isEmpty(forwardURL)) {
                    if(resourceDTO.getHasChild().equals("0")){
                        forwardURL = MessageConstant.MSG_PRC_SERVLET;
                        message = getMessage(MsgKeyConstant.RES_NOT_FINISHED);
                        message.addParameterValue(resourceDTO.getResName());
                        message.addParameterValue(resourceDTO.getPrincipal());
                    } else {
                        forwardURL = MessageConstant.MSG_PRC_SERVLET;
                        message = getMessage("RES_NOT_PERMITTED");
                        message.setIsError(true);
                        message.addParameterValue(resourceDTO.getResName());
                    }
                }
            } else {
                resourceDTO = new SfResDefineDTO();
                resourceDTO.setResId(resourceId);
                SfResDefineDAO resourceDAO = new SfResDefineDAO(userAccount, resourceDTO, conn);
                resourceDTO = resourceDAO.getResourceById();
                FilterConfigDTO filterDTO = getFilterConfig(req);
                forwardURL = filterDTO.getNoPriviledgeURL();
                message = getMessage(MsgKeyConstant.NO_RES_PRIVI);
                message.setIsError(true);
                message.addParameterValue(resourceDTO.getResName());
            }
        } catch (QueryException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
        } catch (PoolPassivateException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.CONN_ERROR);
            message.setIsError(true);
        } catch (ContainerException ex) {
        	Logger.logError(ex);
            message = getMessage(MsgKeyConstant.CONN_ERROR);
            message.setIsError(true);
		} finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            RequestDispatcher rdis = req.getRequestDispatcher(forwardURL);
            rdis.forward(req, res);
        }
    }
}
