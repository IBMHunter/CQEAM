package com.sino.ams.yj.servlet;

import java.sql.Connection;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.constant.message.*;
import com.sino.base.db.conn.DBManager;
import com.sino.base.message.Message;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.base.constant.message.MsgKeyConstant;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

import com.sino.ams.yj.dto.AmsYjTeamDTO;
import com.sino.ams.yj.model.AmsYjTeamModel;
import com.sino.ams.yj.util.YjManagerUtil;
import com.sino.ams.yj.dao.AmsYjTeamDAO;
import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.newasset.bean.AssetsOptProducer;

/**
 * <p>Title: AmsYjTeamServlet</p>
 * <p>Description:�����Զ����ɷ������AmsYjTeamServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ�����϶���
 */

public class AmsYjTeamServlet extends BaseServlet {

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
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsYjTeamDTO.class.getName());
            AmsYjTeamDTO dtoParameter = (AmsYjTeamDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            conn = getDBConnection(req);
            AmsYjTeamDAO amsYjTeamDAO = new AmsYjTeamDAO(user, dtoParameter, conn);
            OptionProducer prd = new OptionProducer(user, conn);
            String ouoption = prd.getAllOrganizationYj(dtoParameter.getOrganizationId(), true);
            req.setAttribute(WebAttrConstant.OU_OPTION, ouoption);

            AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String opt = optProducer.getAllOu(dtoParameter.getOrganizationId());
            dtoParameter.setOrganizationOption(opt);
            if (action.equals("")) {
                forwardURL = "/yj/yjTeamSearch.jsp";
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new AmsYjTeamModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                forwardURL = "/yj/yjTeamSearch.jsp";
            } else if (action.equals(WebActionConstant.NEW_ACTION)) {
                AmsYjTeamDTO amsYjTeam = new AmsYjTeamDTO();
                amsYjTeam.setOrganizationOption(prd.getAllOrganization(dtoParameter.getOrganizationId(), false));
                req.setAttribute("AMS_YJ_TEAM", amsYjTeam);
                forwardURL = "/yj/yjTeamDetail.jsp";
            } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                amsYjTeamDAO.setDTOClassName(AmsYjTeamDTO.class.getName());
                AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) amsYjTeamDAO.getDataByPrimaryKey();
                if (amsYjTeam == null) {
                    amsYjTeam = new AmsYjTeamDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                opt = optProducer.getAllOu(amsYjTeam.getOrganizationId());
                amsYjTeam.setOrganizationOption(opt);
                req.setAttribute("AMS_YJ_TEAM", amsYjTeam);
                forwardURL = "/yj/yjTeamDetail.jsp";
            } else if (action.equals(WebActionConstant.CREATE_ACTION)) {
                boolean isNew = StrUtil.isEmpty(dtoParameter.getTeamId());
              	YjManagerUtil yjUtil= new YjManagerUtil(user, conn);
                if (isNew) {
                    dtoParameter.setTeamId(String.valueOf(yjUtil.getYjManagerMax("AMS_YJ_TEAM_SEQ")));
                }
                amsYjTeamDAO.createData();
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?act=" + WebActionConstant.QUERY_ACTION + "&teamId=" + dtoParameter.getTeamId();
            } else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
                amsYjTeamDAO.updateData();
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?act=" + WebActionConstant.QUERY_ACTION + "&teamId=" + dtoParameter.getTeamId();
            } else if (action.equals(WebActionConstant.DELETE_ACTION)) {       //ʧЧ
                amsYjTeamDAO.deleteData();
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(AMSActionConstant.INURE_ACTION)) {       //��Ч
                String amsYjTeam = "";
                amsYjTeamDAO.enableItem(amsYjTeam);
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) {      //������Excel
                File file = amsYjTeamDAO.exportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            }else if (action.equals("TEAM")) {      //��������ͳ��
                File file = amsYjTeamDAO.exportTeam();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals("verifyTeamName")) {
                String teamName = req.getParameter("teamName");
                boolean success = amsYjTeamDAO.doVerify(teamName);
                PrintWriter out = res.getWriter();
                if (success) {
                    out.print("Y");
                }
                out.flush();
                out.close();
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
        } catch (DataHandleException ex) {
            ex.printLog();
            //�����ʵ�����������Ϣ
            forwardURL = "���ֽ���¼������ݣ����ص�ԭҳ�棬����ʾ�����������Ϣ";
        } catch (WebFileDownException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
            //����ʵ������޸�ҳ����ת���롣
        }
    }
}