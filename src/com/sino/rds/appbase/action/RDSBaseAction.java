package com.sino.rds.appbase.action;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.message.MessageManager;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.bean.SessionUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public abstract class RDSBaseAction extends Action implements CalendarConstant {
    /**
     * @param mapping ActionMapping
     * @param form    ActionForm
     * @param req     HttpServletRequest
     * @param res     HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        return performTask(mapping, form, req, res);
    }

    /**
     * ����Action����ʵ�ֵķ���
     *
     * @param mapping ActionMapping
     * @param form    ActionForm
     * @param req     HttpServletRequest
     * @param res     HttpServletResponse
     * @return ActionForward
     * @throws javax.servlet.ServletException
     */
    protected abstract ActionForward performTask(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws ServletException;

    /**
     * ���ܣ���ȡ���ݿ�����
     *
     * @return Connection
     * @throws PoolException
     */
    protected Connection getDBConnection() throws PoolException {
        return DBManager.getDBConnection();
    }

    /**
     * ���ܣ��ر����ݿ�����
     *
     * @param conn Connection
     */
    protected void closeDBConnection(Connection conn) {
        DBManager.closeDBConnection(conn);
    }

    /**
     * ���ܣ���ȡ�û���Ϣ
     *
     * @param req HttpServletRequest
     * @return SfUserDTO
     */
    protected BaseUserDTO getUserAccount(HttpServletRequest req) {
        return SessionUtil.getUserAccount(req);
    }


    /**
     * ���ܣ���ȡforward������URLǰ�������Ϣ��
     *
     * @param req HttpServletRequest
     * @return Message
     * @throws ServletException
     */
    protected Message getPrevMessage(HttpServletRequest req) throws ServletException {
        return SessionUtil.getMessage(req);
    }

    /**
     * ���ܣ�������Ϣ������ȡ��Ϣ��
     *
     * @param messageKey String
     * @return Message
     * @throws ServletException
     */
    protected Message getMessage(String messageKey) throws ServletException {
        Message message = new Message();
        Message refMessage = MessageManager.getMessage(messageKey);
        if (refMessage != null) {
            message.setMessageKey(messageKey);
            message.setMessageValue(refMessage.getMessageValue(false));
            message.setParent(refMessage.getParent());
        } else {
            message = Message.getUndefinedMessage();
        }
        return message;
    }


    /**
     * ���ܣ������쳣���������Ϣ��
     *
     * @param ex Throwable
     * @return Message
     * @throws ServletException
     */
    protected Message getMessage(Throwable ex) throws ServletException {
        Message message = null;
        if(ex != null && !StrUtil.isEmpty(ex.getMessage())){
            message = new Message();
            message.setMessageValue(ex.getMessage());
        } else {
            message = getMessage("COMMON_ERROR");
        }
        message.setIsError(true);
        return message;
    }

    /**
     * ���ܣ�����δ�����������Ϣ��
     *
     * @return Message
     * @throws ServletException
     */
    protected Message getUndefinedRequestMessage() throws ServletException {
        Message message = getMessage("UNDEFINED_ACT");
        message.setIsError(true);
        return message;
    }

    /**
     * ���ܣ�����servlet��������Ϣ��ʾ����ϢӦ����MessageResource.properties�����ļ����������á�
     *
     * @param req     HttpServletRequest
     * @param message Message
     */
    protected void setHandleMessage(HttpServletRequest req, Message message) {
        req.setAttribute(MessageConstant.MESSAGE_DATA, message);
    }


    /**
     * ���ܣ������ļ�
     *
     * @param req  HttpServletRequest
     * @param res  HttpServletResponse
     * @param file File
     * @throws com.sino.base.exception.WebFileDownException
     *
     */
    protected void downloadFile(HttpServletRequest req, HttpServletResponse res, File file) throws WebFileDownException {
        downloadFile(req, res, file, "");
    }

    /**
     * ���ܣ������ļ�
     *
     * @param req      HttpServletRequest
     * @param res      HttpServletResponse
     * @param file     File
     * @param fileName ���õ��ļ�������������˽��Ը��ļ�����ʾ��������file�е��ļ�����ʾ
     * @throws WebFileDownException
     */
    protected void downloadFile(HttpServletRequest req, HttpServletResponse res, File file, String fileName) throws WebFileDownException {
        WebFileDownload webDown = new WebFileDownload(req, res);
        webDown.setFilePath(file.getPath());
        if (!StrUtil.isEmpty(fileName)) {
            webDown.setFileName(fileName);
        }
        webDown.download();
    }


    /**
     * ���ܣ�����Ajax����
     *
     * @param res           Http��Ӧ����
     * @param obj           Http��Ӧ����
     * @param isXMLResponse AJAX����
     * @throws ServletException ��ӦAjax����ʱ�׳����쳣
     */
    protected void processAjaxResponse(HttpServletResponse res, Object obj, boolean isXMLResponse) throws ServletException {
        try {
            if (obj != null) {
                if (!isXMLResponse) {
                    res.setContentType("text/plain;charset=GBK");
                } else {
                    res.setContentType("text/xml;charset=GBK");
                }
                PrintWriter out = res.getWriter();
                out.println(obj);
                out.close();
            }
        } catch (IOException ex) {
            Logger.logError(ex);
            throw new ServletException(ex);
        }
    }
}