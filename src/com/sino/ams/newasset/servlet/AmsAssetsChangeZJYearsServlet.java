package com.sino.ams.newasset.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dao.AmsAssetsChangeZJYearsDAO;
import com.sino.ams.newasset.dto.AmsAssetsCJYCDTO;
import com.sino.ams.newasset.model.AmsAssetsChangeZJYearsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
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
 * Created by IntelliJ IDEA.
 * User: srf
 * Date: 2008-4-7
 * Time: 17:19:37
 * To change this template use File | Settings | File Templates.
 */
public class AmsAssetsChangeZJYearsServlet extends BaseServlet {


    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        String shMsg = "";
        String faCatName2111 = StrUtil.nullToString(req.getParameter("faCatName2"));
        try {
            conn = getDBConnection(req);

            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsAssetsCJYCDTO.class.getName());
            AmsAssetsCJYCDTO dtoParameter = (AmsAssetsCJYCDTO) req2DTO.getDTO(req);
            AmsAssetsChangeZJYearsDAO dao = new AmsAssetsChangeZJYearsDAO(user, dtoParameter, conn);

            String action = req.getParameter("act");
            action = StrUtil.nullToString(action);
            OptionProducer op = new OptionProducer(user, conn);
            int organizationId = dtoParameter.getOrganizationId();
            String companySelect = op.getAllOrganization(organizationId, true);
            req.setAttribute("OU", companySelect);

            String bookTypeCode = dtoParameter.getBookTypeCode();
            String bookSelect = op.getAllBookTypeName(bookTypeCode, true);
            req.setAttribute("BOOK_TYPE_CODE", bookSelect);
            req.setAttribute("AMSBJTRANSNOHDTO", dtoParameter);
            if (action.equals("")) {
                forwardURL = "/newasset/amsAssetsChangeZJYears.jsp";
            } else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new AmsAssetsChangeZJYearsModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.setCountPages(false);
                pageDAO.produceWebData();
                req.setAttribute("AMSBJTRANSNOHDTO", dtoParameter);
                forwardURL = "/newasset/amsAssetsChangeZJYears.jsp";
            } else if (action.equals("BATCH")) {
                String year = req.getParameter("years");
                String faCatName2 = req.getParameter("faCatName2");
                int orgId=StrUtil.strToInt(req.getParameter("organizationId"));
                dtoParameter.setFaCatName2(faCatName2);
                dtoParameter.setNewYears(year);
                dtoParameter.setOrganizationId(orgId);
                boolean vlues = dao.batchUpdateYears();
                if (vlues) {
                    shMsg = "���������۾����޳ɹ���";
                } else {
                    shMsg = "���������۾�����ʧ�ܣ�����������";
                }

                forwardURL = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsChangeZJYearsServlet?act=" + AssetsActionConstant.QUERY_ACTION + "&faCatName2=" + dtoParameter.getFaCatName2();
            } else if (action.equals(AssetsActionConstant.SAVE_ACTION)) {
                String faCatName2 = req.getParameter("faCatName2");
                dtoParameter.setFaCatName2(faCatName2);
                String[] faCategory2 = req.getParameterValues("subCheck");
                String year[] = req.getParameterValues("newYears");
                for (int i = 0; i < faCategory2.length; i++) {
                    String faCategory1[] = faCategory2[i].split(";");
                    int j = Integer.parseInt(faCategory1[1]);
                    dao.updateYear(faCategory1[0], year[j],faCategory1[2]);
                }
                shMsg = "�����۾����޳ɹ���";
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsChangeZJYearsServlet?act=" + AssetsActionConstant.QUERY_ACTION + "&faCatName2=" + dtoParameter.getFaCatName2();
            }else if(action.equals(AssetsActionConstant.EXPORT_ACTION)){
                    File file = dao.exportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            }
        } catch (DTOException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataHandleException ex) {
            /** @todo Handle this exception */
        } catch (SQLException e) {
            Logger.logError(e);
            message = getMessage(MsgKeyConstant.SQL_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        }catch (WebFileDownException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        }  finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            if (shMsg.equals("")) {
                forwarder.forwardView(forwardURL);
            } else {
                forwarder.forwardView(forwardURL, shMsg);
            }
        }

    }
}
