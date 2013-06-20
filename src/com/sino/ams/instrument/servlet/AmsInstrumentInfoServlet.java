package com.sino.ams.instrument.servlet;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.instrument.dao.AmsInstrumentInfoDAO;
import com.sino.ams.instrument.dto.AmsInstrumentInfoDTO;
import com.sino.ams.instrument.model.AmsInstrumentInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.util.GenBarcode;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.WebPageView;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
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
 * <p>Title: AmsInstrumentInfoServlet</p>
 * <p>Description:�����Զ����ɷ������AmsInstrumentInfoServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class AmsInstrumentInfoServlet extends BaseServlet {

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
        String itemCategory = StrUtil.nullToString(req.getParameter("type"));
        action = StrUtil.nullToString(action);
        Connection conn = null;
        String showMsg = "";
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            AmsInstrumentInfoDTO dtoParameter = null;
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsInstrumentInfoDTO.class.getName());
            dtoParameter = (AmsInstrumentInfoDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            AmsInstrumentInfoDAO amsInstrumentInfoDAO = new AmsInstrumentInfoDAO(user, dtoParameter, conn);
            dtoParameter.setItemCategory(itemCategory);
            OptionProducer op = new OptionProducer(user, conn);
            String cityOption1 = op.getAllOrganization(0, true);
            req.setAttribute(WebAttrConstant.CITY_OPTION, cityOption1);

            if (action.equals("")) {
                req.setAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO, dtoParameter);
                forwardURL = URLDefineList.INSTRUMENT_QUERY;
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new AmsInstrumentInfoModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO, dtoParameter);
                forwardURL = URLDefineList.INSTRUMENT_QUERY;
            } else if (action.equals(WebActionConstant.NEW_ACTION)) {
                AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) req.getAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO);
                if (amsInstrumentInfo == null) {
                    amsInstrumentInfo = dtoParameter;
                }
                int addressId = StrUtil.strToInt(amsInstrumentInfoDAO.getAddressId());  //��õص���Ϣ
                amsInstrumentInfo.setAddressId(addressId);
//                String barcode = amsInstrumentInfoDAO.getOrderNum();   //���������Ϣ
//                amsInstrumentInfo.setBarcode(barcode);
                amsInstrumentInfo.setItemQty("1");
                req.setAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO, amsInstrumentInfo);
                forwardURL = URLDefineList.INSTRUMENT_DETAIL;
            } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                amsInstrumentInfoDAO.setDTOClassName(AmsInstrumentInfoDTO.class.getName());
                AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) amsInstrumentInfoDAO.getDataByPrimaryKey();
                if (amsInstrumentInfo == null) {
                    amsInstrumentInfo = new AmsInstrumentInfoDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                req.setAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO, amsInstrumentInfo);
                forwardURL = URLDefineList.INSTRUMENT_DETAIL;
            } else if (action.equals(WebActionConstant.CREATE_ACTION)) {
                /*boolean operateResult = amsInstrumentInfoDAO.createData();
                    message = amsInstrumentInfoDAO.getMessage();
                    message.setIsError(!operateResult);
                    if(operateResult){
                        forwardURL = URLDefineList.WINDOW_CLOSE_PAGE;
                    } else {
                        req.setAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO, dtoParameter);
                        forwardURL = URLDefineList.INSTRUMENT_DETAIL;
                    } */
                String itemcode = req.getParameter("itemCode");
                String addressId = req.getParameter("addressId");
                amsInstrumentInfoDAO.insertData(conn, itemcode, addressId);
                showMsg = "���������ɹ�!";
                forwardURL = "/servlet/com.sino.ams.instrument.servlet.AmsInstrumentInfoServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
                amsInstrumentInfoDAO.update(conn);
                showMsg = "�������³ɹ�!";
                forwardURL = "/servlet/com.sino.ams.instrument.servlet.AmsInstrumentInfoServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals("barcode")) {
                String barcode = GenBarcode.getAssetBarcode(conn, user.getCompanyCode());
                AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) req.getAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO);
                if (amsInstrumentInfo == null) {
                    amsInstrumentInfo = dtoParameter;
                }
                amsInstrumentInfo.setBarcode(barcode);
                req.setAttribute("BARCODE", amsInstrumentInfo);
                //  forwardURL = URLDefineList.INSTRUMENT_DETAIL;
            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) {
                File file = amsInstrumentInfoDAO.exportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals(WebActionConstant.SUBMIT_ACTION)) {     //���������Ǳ�
                String itemcode = req.getParameter("itemCode");
                String addressId = req.getParameter("addressId");
                amsInstrumentInfoDAO.creatData(conn, itemcode, addressId);
                showMsg = "���������ɹ�!";
                forwardURL = "/servlet/com.sino.ams.instrument.servlet.AmsInstrumentInfoServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(AMSActionConstant.INSTEAD_ACTION)) {   //�޸������Ǳ�
                amsInstrumentInfoDAO.updateDat(conn);
                showMsg = "�������³ɹ�!";
                forwardURL = "/servlet/com.sino.ams.instrument.servlet.AmsInstrumentInfoServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(AMSActionConstant.STATISTICS_ACTION)) {   //�����Ǳ�ͳ�Ʋ���
                AmsInstrumentInfoModel sqlModel = new AmsInstrumentInfoModel(user, dtoParameter);
                SQLModel tmodel = sqlModel.getSQueryModel();
                WebPageView wpv = new WebPageView(req, conn);
//                wpv.setCalPattern(CalendarConstant.LINE_PATTERN);
                wpv.produceWebData(tmodel);
                forwardURL = URLDefineList.INSTRUMENT_QUERY;
            } else if (action.equals("EXPORT_ACTION2")) {
                File file = amsInstrumentInfoDAO.exportFile2();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals("HISTORY_QUERY")) {   //�����Ǳ�䶯��ʷ
                AmsInstrumentInfoModel sqlModel = new AmsInstrumentInfoModel(user, dtoParameter);
                SQLModel tmodel = sqlModel.getHQueryModel();
                WebPageView wpv = new WebPageView(req, conn);
//                wpv.setCalPattern(CalendarConstant.LINE_PATTERN);
                wpv.produceWebData(tmodel);
                forwardURL = "/instrument/instrHistory.jsp";
            } else if (action.equals("QUERY_ACTION2")) { //�����˵Ĳ�ѯ����
                AmsInstrumentInfoModel sqlModel = new AmsInstrumentInfoModel(user, dtoParameter);
                SQLModel tmodel = sqlModel.getQueryRespModel();
                WebPageView wpv = new WebPageView(req, conn);
//                wpv.setCalPattern(CalendarConstant.LINE_PATTERN);
                wpv.produceWebData(tmodel);
                req.setAttribute(WebAttrConstant.AMS_INSTRUMENT_DTO, dtoParameter);
                forwardURL = "/instrument/instrumentRespQuery.jsp";

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
        } catch (WebFileDownException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataTransException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        }
        catch (DataHandleException e) {
            e.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (SQLException e) {
            Logger.logError(e);
            message = getMessage(MsgKeyConstant.SQL_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        }
        finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            if (!forwardURL.equals("")) {
                if (showMsg.equals("")) {
                    forwarder.forwardView(forwardURL);
                } else {
                    forwarder.forwardOpenerView(forwardURL, showMsg);
                }
            }
            //����ʵ������޸�ҳ����ת���롣
        }
    }
}