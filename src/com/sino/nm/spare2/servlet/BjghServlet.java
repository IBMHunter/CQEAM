package com.sino.nm.spare2.servlet;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.nm.spare2.dao.AmsItemTransHDAO;
import com.sino.nm.spare2.dao.BjghDAO;
import com.sino.nm.spare2.dto.AmsItemTransHDTO;
import com.sino.nm.spare2.dto.AmsItemTransLDTO;
import com.sino.nm.spare2.model.BjghModel;
import com.sino.nm.spare2.model.AmsItemTransHModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.WebPageView;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
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
 * <p>Title: SinoEAMS</p>
 * <p>Description: �����黹</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2008-3-13
 */
public class BjghServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        String showMsg = "";
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            AmsItemTransHDTO dtoParameter = null;
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsItemTransHDTO.class.getName());
            dtoParameter = (AmsItemTransHDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            AmsItemTransHDAO itemTransHDAO = new AmsItemTransHDAO(user, dtoParameter, conn);
            OptionProducer optProducer = new OptionProducer(user, conn);
            if (action.equals("")) {
                String ouOption = optProducer.getAllOU(dtoParameter.getFromOrganizationId(), true); 
                req.setAttribute(WebAttrConstant.OU_OPTION, ouOption);
                if ("ACCEPT".equals(dtoParameter.getFlag())) {
                    forwardURL = "/nm/spare2/bjghAccept.jsp";
                } else {
                    String orderStatus = optProducer.getDictOption("ORDER_STATUS", dtoParameter.getTransStatus());
                    req.setAttribute(WebAttrConstant.TRANS_STATUS, orderStatus);
                    forwardURL = "/nm/spare2/bjghCreate.jsp";
                }
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new BjghModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                String ouOption = optProducer.getAllOU(dtoParameter.getFromOrganizationId(), true); ;
                req.setAttribute(WebAttrConstant.OU_OPTION, ouOption);
                if ("ACCEPT".equals(dtoParameter.getFlag())) {
                    forwardURL = "/nm/spare2/bjghAccept.jsp";
                } else {
                    String orderStatus = optProducer.getDictOption("ORDER_STATUS", dtoParameter.getTransStatus());
                    req.setAttribute(WebAttrConstant.TRANS_STATUS, orderStatus);
                    forwardURL = "/nm/spare2/bjghCreate.jsp";
                }
            } else if (action.equals(WebActionConstant.NEW_ACTION)) {
                AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) req.getAttribute("AIT_HEADER");
                if (amsItemTransH == null) {
                    amsItemTransH = dtoParameter;
                }
                amsItemTransH.setTransNo(WebAttrConstant.ORDER_NO_AUTO_PRODUCE);
                amsItemTransH.setTransType(DictConstant.BJGH);
                amsItemTransH.setCreatedBy(user.getUserId());
                amsItemTransH.setCreatedUser(user.getUsername());
                amsItemTransH.setCreationDate(CalendarUtil.getCurrDate());
                amsItemTransH.setTransStatus(DictConstant.SAVE_TEMP);
                amsItemTransH.setTransStatusName("δ���");
                amsItemTransH.setFromOrganizationId(user.getOrganizationId());
                req.setAttribute("AIT_HEADER", amsItemTransH);
                forwardURL = "/nm/spare2/bjghDetail.jsp";
            } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                itemTransHDAO.setDTOClassName(AmsItemTransHDTO.class.getName());
                AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) itemTransHDAO.getDataByPrimaryKey();
                if (amsItemTransH == null) {
                    amsItemTransH = new AmsItemTransHDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                req.setAttribute("AIT_HEADER", amsItemTransH);
                //��ѯ����Ϣ
                if("PRINT".equals(dtoParameter.getFlag())){
                    WebPageView wpv = new WebPageView(req, conn);
                    BjghModel hModel = new BjghModel(user, dtoParameter);
                    wpv.setPrintProp(true);
                    wpv.produceWebData(hModel.getDataByForeignKeyModel(dtoParameter.getTransId()));
                    forwardURL = "/nm/spare2/bjghPrint.jsp";
                }else{
                    BjghDAO ldao = new BjghDAO(user, dtoParameter, conn);
                    req.setAttribute("AIT_LINES", ldao.getLines());
                    forwardURL = "/nm/spare2/bjghDetail.jsp";
                }
            } else if (action.equals(WebActionConstant.SAVE_ACTION)) {
                Request2DTO r2 = new Request2DTO();
                r2.setDTOClassName(AmsItemTransLDTO.class.getName());
                r2.setIgnoreFields(AmsItemTransHDTO.class);
                DTOSet lineSet = r2.getDTOSet(req);
                boolean operateResult = itemTransHDAO.saveOrder(lineSet, null);
                message = itemTransHDAO.getMessage();
                message.setIsError(!operateResult);
                if (operateResult) {
                    forwardURL = "/servlet/com.sino.nm.spare2.servlet.BjghServlet?act=" + WebActionConstant.DETAIL_ACTION
                            + "&transId=" + dtoParameter.getTransId();
                } else {
                    req.setAttribute("AIT_HEADER", dtoParameter);
                    forwardURL = "/nm/spare2/bjghDetail.jsp";
                }
            } else if (action.equals(WebActionConstant.SUBMIT_ACTION)) {
                Request2DTO r2 = new Request2DTO();
                r2.setDTOClassName(AmsItemTransLDTO.class.getName());
                r2.setIgnoreFields(AmsItemTransHDTO.class);
                DTOSet lineSet = r2.getDTOSet(req);

//                        BjckDAO bjckDAO = new BjckDAO(user, dtoParameter, conn);
                dtoParameter.setTransStatus(DictConstant.IN_PROCESS);
                boolean operateResult = itemTransHDAO.submitOrder(lineSet, null,null);
                message = itemTransHDAO.getMessage();
                message.setIsError(!operateResult);
                if (operateResult) {
                    forwardURL = "/servlet/com.sino.nm.spare2.servlet.BjghServlet?act=" + WebActionConstant.QUERY_ACTION;
                    showMsg = "�������ⵥ" + dtoParameter.getTransNo() + "���ύ��";
                } else {
                    forwardURL = "/servlet/com.sino.nm.spare2.servlet.BjghServlet?act=" + WebActionConstant.DETAIL_ACTION
                            + "&transId=" + dtoParameter.getTransId();
                }
            } else if (action.equals(WebActionConstant.RECEIVE_ACTION)) {     //����ȷ��
                dtoParameter.setTransStatus(DictConstant.ACCEPTED);
                BjghDAO bjghDAO = new BjghDAO(user, dtoParameter, conn);
                boolean success = bjghDAO.accept();
                message = bjghDAO.getMessage();
                message.setIsError(!success);
                if (success) {
                    showMsg = "�����黹��" + dtoParameter.getTransNo() + "�ѽ���ȷ�ϣ�";
                } else {
                    forwardURL = "/servlet/com.sino.nm.spare2.servlet.BjghServlet?flag=ACCEPT&act=" + WebActionConstant.DETAIL_ACTION
                            + "&transId=" + dtoParameter.getTransId();
                }
            }else if (action.equals(WebActionConstant.REJECT_ACTION)) {     //�˻�
                dtoParameter.setTransStatus(DictConstant.REJECTED);
                BjghDAO bjghDAO = new BjghDAO(user, dtoParameter, conn);
                boolean success = bjghDAO.reject();
                message = bjghDAO.getMessage();
                message.setIsError(!success);
                if (success) {
                    showMsg = "�����黹��" + dtoParameter.getTransNo() + "���˻أ�";
                } else {
                    forwardURL = "/servlet/com.sino.nm.spare2.servlet.BjghServlet?flag=ACCEPT&act=" + WebActionConstant.DETAIL_ACTION
                            + "&transId=" + dtoParameter.getTransId();
                }
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
        } catch (CalendarException e) {
            e.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
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
            if (showMsg.equals("")) {
                forwarder.forwardView(forwardURL);
            } else {
                forwarder.forwardOpenerView(forwardURL, showMsg);
            }
            //����ʵ������޸�ҳ����ת���롣
        }
    }
}
