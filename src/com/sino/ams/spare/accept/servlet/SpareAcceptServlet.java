package com.sino.ams.spare.accept.servlet;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.spare.accept.dao.SpareAcceptDAO;
import com.sino.ams.spare.accept.model.SpareAcceptModel;
import com.sino.ams.spare.dto.AmsItemTransHDTO;
import com.sino.ams.spare.dto.AmsItemTransLDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2008-2-18
 */
public class SpareAcceptServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        String showMsg = "";
        String action = StrUtil.nullToString(req.getParameter("act"));
        String transType = StrUtil.nullToString(req.getParameter("transType"));
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            AmsItemTransHDTO dtoParameter = null;
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsItemTransHDTO.class.getName());
            dtoParameter = (AmsItemTransHDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            SpareAcceptDAO srDAO = new SpareAcceptDAO(user, dtoParameter, conn);
            OptionProducer optProducer = new OptionProducer(user, conn);
            if (action.equals("")) {
                String orderStatus = optProducer.getDictOption("ORDER_STATUS", dtoParameter.getTransStatus());
                req.setAttribute(WebAttrConstant.TRANS_STATUS, orderStatus);
                String invOption = optProducer.getInvOption(dtoParameter.getFromObjectNo());
                req.setAttribute(WebAttrConstant.INV_OPTION, invOption);
                forwardURL = "/spare/spareAcceptQuery.jsp";
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new SpareAcceptModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                String orderStatus = optProducer.getDictOption("ORDER_STATUS", dtoParameter.getTransStatus());
                req.setAttribute(WebAttrConstant.TRANS_STATUS, orderStatus);
                String invOption = optProducer.getInvOption(dtoParameter.getFromObjectNo());
                req.setAttribute(WebAttrConstant.INV_OPTION, invOption);
                forwardURL = "/spare/spareAcceptQuery.jsp";
            } else if (action.equals(WebActionConstant.NEW_ACTION)) {
                AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) req.getAttribute("AIT_HEADER");
                if (amsItemTransH == null) {
                    amsItemTransH = dtoParameter;
                }
                amsItemTransH.setTransNo(WebAttrConstant.ORDER_NO_AUTO_PRODUCE);
                amsItemTransH.setCreatedBy(user.getUserId());
                amsItemTransH.setCreatedUser(user.getUsername());
                amsItemTransH.setCreationDate(CalendarUtil.getCurrDate());
                amsItemTransH.setTransStatus(DictConstant.SAVE_TEMP);
                amsItemTransH.setTransStatusName("δ���");
                req.setAttribute("AIT_HEADER", amsItemTransH);
                forwardURL = "/spare/xgrkOrder.jsp";
            } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                srDAO.setDTOClassName(AmsItemTransHDTO.class.getName());
                AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) srDAO.getDataByPrimaryKey();
                if (amsItemTransH == null) {
                    amsItemTransH = new AmsItemTransHDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                req.setAttribute("AIT_HEADER", amsItemTransH);
                //��ѯ����Ϣ
                req.setAttribute("AIT_LINES", srDAO.getDetails(amsItemTransH.getTransId()));
                forwardURL = "/spare/spareAcceptDetail.jsp";
            } else if (action.equals(WebActionConstant.RECEIVE_ACTION)) {
                Request2DTO r2 = new Request2DTO();
                r2.setDTOClassName(AmsItemTransLDTO.class.getName());
                r2.setIgnoreFields(AmsItemTransHDTO.class);
                DTOSet lineSet = r2.getDTOSet(req);
                FlowDTO flowDTO = FlowAction.getDTOFromReq(req);
                flowDTO.setSessionUserId(user.getUserId());
                flowDTO.setSessionUserName(user.getUsername());
                boolean success = srDAO.accept(lineSet);
                message = srDAO.getMessage();
                message.setIsError(!success);
                if (success) {
                    showMsg = "����" + dtoParameter.getTransNo() + "�ѳɹ����գ�";
                } else {
                    forwardURL = "/servlet/com.sino.ams.spare.accept.servlet.SpareAcceptServlet?act=" + WebActionConstant.DETAIL_ACTION
                            + "&transId=" + dtoParameter.getTransId() + "&transType=" + dtoParameter.getTransType();
                }
            } /*else if (action.equals(WebActionConstant.SUBMIT_ACTION)) {
                Request2DTO r2 = new Request2DTO();
                r2.setDTOClassName(AmsItemTransLDTO.class.getName());
                r2.setIgnoreFields(AmsItemTransHDTO.class);
                DTOSet lineSet = r2.getDTOSet(req);
                FlowDTO flowDTO = FlowAction.getDTOFromReq(req);
                flowDTO.setSessionUserId(user.getUserId());
                flowDTO.setSessionUserName(user.getUsername());
                boolean operateResult = itemTransHDAO.submitOrder(lineSet, flowDTO);
                message = itemTransHDAO.getMessage();
                message.setIsError(!operateResult);
                if (operateResult) {
                    forwardURL = "/servlet/com.sino.ams.spare.servlet.AmsItemTransHServlet?act=" + WebActionConstant.QUERY_ACTION;
                    showMsg = "����" + dtoParameter.getTransNo() + "���ύ!";
                } else {
                    forwardURL = "/servlet/com.sino.ams.spare.servlet.AmsItemTransHServlet?act=" + WebActionConstant.DETAIL_ACTION
                            + "&transId=" + dtoParameter.getTransId() + "&transType=" + transType;
                }
            } else if (action.equals(WebActionConstant.DELETE_ACTION)) {
                itemTransHDAO.deleteData();
                message = itemTransHDAO.getMessage();
                forwardURL = "���ٴ�ִ�и�Servlet��QUERY_ACTION�������ʵ�����ȷ��";
            }*/
            else {
                message = getMessage(MsgKeyConstant.INVALID_REQ);
                message.setIsError(true);
                forwardURL = MessageConstant.MSG_PRC_SERVLET;
            }
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
            message.setIsError(true);
            message.setNeedBack(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DTOException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.DTO_ERROR);
            message.setIsError(true);
            message.setNeedBack(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            message.setNeedBack(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (CalendarException e) {
            e.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            message.setNeedBack(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (SQLException e) {
            Logger.logError(e);
            message = getMessage(MsgKeyConstant.SQL_ERROR);
            message.setIsError(true);
            message.setNeedBack(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } /*catch (DataHandleException e) {
            Logger.logError(e);
            message = getMessage(MsgKeyConstant.SQL_ERROR);
            message.setIsError(true);
            message.setNeedBack(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        }*/ finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            if (showMsg.equals("")) {
                forwarder.forwardView(forwardURL);
            } else {
                forwarder.forwardOpenerView(forwardURL, showMsg);
            }
            //����ʵ������޸�ҳ����ת���롣
        }
    }
}
