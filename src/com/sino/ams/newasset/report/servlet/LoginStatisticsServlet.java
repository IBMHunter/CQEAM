package com.sino.ams.newasset.report.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.dao.LoginStatisticsDAO;
import com.sino.ams.newasset.report.model.LoginStatisticsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Function:    ����ͳ�Ʊ���
 * Author:      ����
 * Date:        2009-10-28
 */
public class LoginStatisticsServlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            AmsAssetsCheckHeaderDTO dtoParameter;
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsAssetsCheckHeaderDTO.class.getName());
            dtoParameter = (AmsAssetsCheckHeaderDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            LoginStatisticsDAO dao = new LoginStatisticsDAO(user, dtoParameter, conn);
            OptionProducer optProducer = new OptionProducer(user, conn);
            String opt = optProducer.getIsOrNotTdOrganization(dtoParameter.getOrganizationId(), "N", true);
            dtoParameter.setOrgOpt(opt);
            if (action.equals("")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                this.getDeptCategory(dao, dtoParameter, "DEPT_CATEGORY");
                forwardURL = URLDefineList.LOGIN_STATISTICS_PAGE;
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new LoginStatisticsModel(user, dtoParameter);
                this.getDeptCategory(dao, dtoParameter, "DEPT_CATEGORY");
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                forwardURL = URLDefineList.LOGIN_STATISTICS_PAGE;
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) {      //������Excel
                File file = dao.exportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else {
                message = getMessage(MsgKeyConstant.INVALID_REQ);
                message.setIsError(true);
                forwardURL = MessageConstant.MSG_PRC_SERVLET;
            }
        } catch (WebFileDownException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
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
        } catch (ContainerException e) {
            e.printLog();
            Logger.logError(e.toString());
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            if (!StrUtil.isEmpty(forwardURL)) {
                ServletForwarder forwarder = new ServletForwarder(req, res);
                forwarder.forwardView(forwardURL);
                //����ʵ������޸�ҳ����ת���롣
            }
        }
    }

    public void getDeptCategory(LoginStatisticsDAO dao, AmsAssetsCheckHeaderDTO dto, String dicValue) throws QueryException, ContainerException {
        RowSet rows = dao.getDeptCategoryByDic(dicValue);
        ArrayList codes = new ArrayList(0);
        StringBuffer values = new StringBuffer(0);
        for(int i = 0; i <rows.getSize(); i++){
            codes.add(rows.getRow(i).getValue("CODE"));
            values.append("'" + rows.getRow(i).getValue("VALUE") + "',");
        }
        values.append("'�ϼ�'");
        dto.setDeptCategoryCodes(codes);
        dto.setDeptCategoryValues(values.toString());
    }
}