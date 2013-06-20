package com.sino.ams.newasset.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dao.AmsAssetsCheckLineDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.model.AmsAssetsCheckLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsAssetsCheckLineServlet</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsCheckLineServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsCheckLineServlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsAssetsCheckLineDTO.class.getName());
            AmsAssetsCheckLineDTO dtoParameter = (AmsAssetsCheckLineDTO)
                                                 req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            conn = getDBConnection(req);
            AmsAssetsCheckLineDAO amsAssetsCheckLineDAO = new
                    AmsAssetsCheckLineDAO(user, dtoParameter, conn);
            if (action.equals("")) {
                forwardURL = "com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
            } else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new AmsAssetsCheckLineModel(user,
                        dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                forwardURL = "com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet�ķ�ҳ��ѯJSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
            } else if (action.equals(AssetsActionConstant.NEW_ACTION)) {
                AmsAssetsCheckLineDTO amsAssetsCheckLine = (
                        AmsAssetsCheckLineDTO) req.getAttribute(
                        "��ȡ��Ϊʧ�ܶ����ֵ����ݣ������ʵ������޸�");
                if (amsAssetsCheckLine == null) {
                    amsAssetsCheckLine = dtoParameter; //��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO�Ĺ��캯��ȷ��
                }
                req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsAssetsCheckLine);
                forwardURL = "com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
            } else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) {
                amsAssetsCheckLineDAO.setDTOClassName(AmsAssetsCheckLineDTO.class.
                        getName());
                AmsAssetsCheckLineDTO amsAssetsCheckLine = (
                        AmsAssetsCheckLineDTO) amsAssetsCheckLineDAO.
                        getDataByPrimaryKey();
                if (amsAssetsCheckLine == null) {
                    amsAssetsCheckLine = new AmsAssetsCheckLineDTO();
                    message = getMessage(AssetsMessageKeys.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                req.setAttribute("��ϸ�������ԣ������ʵ������޸�", amsAssetsCheckLine);
                forwardURL = "com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet��ϸ����JSPҳ�棬һ����������URLDefineList�ĳ����ӿ��ж���";
            } else if (action.equals(AssetsActionConstant.CREATE_ACTION)) {
                amsAssetsCheckLineDAO.createData();
                forwardURL = "���ٴ�ִ��com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet��QUERY_ACTION�������ʵ�����ȷ��";
            } else if (action.equals(AssetsActionConstant.UPDATE_ACTION)) {
                amsAssetsCheckLineDAO.updateData();
                forwardURL = "���ٴ�ִ��com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet��QUERY_ACTION�������ʵ�����ȷ��";
            } else if (action.equals(AssetsActionConstant.DELETE_ACTION)) {
                amsAssetsCheckLineDAO.deleteData();
                forwardURL = "���ٴ�ִ��com.sino.ams.newasset.servlet.AmsAssetsCheckLineServlet��QUERY_ACTION�������ʵ�����ȷ��";
            } else {
                message = getMessage(AssetsMessageKeys.INVALID_REQ);
                message.setIsError(true);
                forwardURL = MessageConstant.MSG_PRC_SERVLET;
            }
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DTOException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataHandleException ex) {
            ex.printLog();
            //�����ʵ�����������Ϣ
            forwardURL = "���ֽ���¼������ݣ����ص�ԭҳ�棬����ʾ�����������Ϣ";
        } finally {
            closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
            //����ʵ������޸�ҳ����ת���롣
        }
    }
}
