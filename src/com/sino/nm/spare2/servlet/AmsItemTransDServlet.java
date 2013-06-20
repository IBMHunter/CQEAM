package com.sino.nm.spare2.servlet;



import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;

import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.nm.spare2.dao.AmsItemTransDDAO;
import com.sino.nm.spare2.dto.AmsItemTransDDTO;
import com.sino.nm.spare2.model.AmsItemTransDModel;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemTransDServlet</p>
 * <p>Description:�����Զ����ɷ������AmsItemTransDServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Herui
 * @version 1.0
 */


public class AmsItemTransDServlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            AmsItemTransDDTO dtoParameter = null;
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsItemTransDDTO.class.getName());
            List list = new ArrayList();
//            list.add("organizationId");
            list.add("quantity");
            req2DTO.setIgnoreFields(list);
            dtoParameter = (AmsItemTransDDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            AmsItemTransDDAO amsItemTransDDAO = new AmsItemTransDDAO(user, dtoParameter, conn);
            if (action.equals("")) {
                forwardURL = "��ҳ��ѯҳ�棬�����ʵ������޸�";
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new AmsItemTransDModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                forwardURL = "��ҳ��ѯҳ�棬�����ʵ������޸�";
            } else if (action.equals(WebActionConstant.NEW_ACTION)) {
                AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO) req.getAttribute("��ϸ�������ԣ������ʵ������޸�");
                if (amsItemTransD == null) {
                    amsItemTransD = dtoParameter;
                }
                req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsItemTransD);
                forwardURL = "��ϸ����ҳ�棬��ʱ����Ϊ�գ������ʵ������޸�";
            } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                amsItemTransDDAO.setDTOClassName(AmsItemTransDDTO.class.getName());
                AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO) amsItemTransDDAO.getDataByPrimaryKey();
                if (amsItemTransD == null) {
                    amsItemTransD = new AmsItemTransDDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsItemTransD);
                forwardURL = "��ϸ����ҳ�棬��ʱ�����ݣ������ʵ������޸�";
            } else if (action.equals(WebActionConstant.CREATE_ACTION)) {
                boolean operateResult = false;
                message = amsItemTransDDAO.getMessage();
                message.setIsError(!operateResult);
                if (operateResult) {
                    forwardURL = "���ٴ�ִ�и�Servlet��QUERY_ACTION�������ʵ�����ȷ��";
                } else {
                    req.setAttribute("��¼�����ϸ��Ϣ���ݣ������ʵ�����ȷ��", dtoParameter);
                    forwardURL = "����ԭ���ݻص��½�ҳ�棬�����ʵ�����ȷ��";
                }
            } else if (action.equals(WebActionConstant.SAVE_ACTION)) {
                boolean operateResult = false;
                Request2DTO r2d = new Request2DTO();
                r2d.setDTOClassName(AmsItemTransDDTO.class.getName());
                List ignorList = new ArrayList();
//                ignorList.add("itemCode");
//                ignorList.add("lineId");
//                ignorList.add("act");
//                ignorList.add("transId");
                r2d.setIgnoreFields(ignorList);
                DTOSet dtos = r2d.getDTOSet(req);
                amsItemTransDDAO.writeDetails(dtos);    //��Ҫд������
                message = amsItemTransDDAO.getMessage();
                message.setIsError(false);
                forwardURL = "/servlet/com.sino.nm.spare2.servlet.ItemCountByOuServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(WebActionConstant.DELETE_ACTION)) {
                boolean operateResult = false;
                message = amsItemTransDDAO.getMessage();
                message.setIsError(!operateResult);
                forwardURL = "���ٴ�ִ�и�Servlet��QUERY_ACTION�������ʵ�����ȷ��";
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
        } catch (SQLException e) {
            Logger.logError(e);
            message = getMessage(MsgKeyConstant.SQL_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
            //����ʵ������޸�ҳ����ת���롣
        }
    }
}