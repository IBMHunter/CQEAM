package com.sino.ams.newasset.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.EventHandler;
import com.sino.base.web.EventHandlers;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.*;
import com.sino.ams.newasset.dao.AmsItemCorrectLogDAO;
import com.sino.ams.newasset.dao.ItemCostEasyDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.newasset.model.ItemCostEasyModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

public class ItemCostEasyServlet extends BaseServlet {

	/**
	 * ���ܣ���ֵ�׺�̨��ά��
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws
		ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
        try {
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) req2DTO.getDTO(req);
			if(dto.getAttribute1().equals("")){
				dto.setAttribute1(AssetsDictConstant.STATUS_NO);
			}
			String action = dto.getAct();
			conn = getDBConnection(req);
			ItemCostEasyDAO costEasyDAO = new ItemCostEasyDAO(user, dto, conn);
			AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            if (action.equals("")) {
                produceWebComponent(dto, optProducer, req, user);
				forwardURL = "/newasset/itemCostEasyData.jsp";
			} else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new ItemCostEasyModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                CheckBoxProp checkProp = new CheckBoxProp("subCheck");
				checkProp.addDbField("BARCODE");
				EventHandlers handlers = new EventHandlers();
				EventHandler handler = new EventHandler();
				handler.setFunName("do_TransData");
				handler.setEventName("onClick");
				handlers.addHandler(handler);
				checkProp.setHandlers(handlers);
				pageDAO.setWebCheckProp(checkProp);
				pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.setPageSize(20);
				pageDAO.produceWebData();


                produceWebComponent(dto, optProducer, req, user);
				forwardURL = "/newasset/itemCostEasyData.jsp";
			} else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) {
				costEasyDAO.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
				dto = (AmsAssetsAddressVDTO) costEasyDAO.getDataByPrimaryKey();
				AmsItemCorrectLogDTO logDTO = new AmsItemCorrectLogDTO();
				logDTO.setBarcode(dto.getBarcode());
				AmsItemCorrectLogDAO logDAO= new AmsItemCorrectLogDAO(user, logDTO, conn);
				logDAO.setDTOClassName(AmsItemCorrectLogDTO.class.getName());
				DTOSet barcodeLogs = (DTOSet)logDAO.getDataByForeignKey("barcode");
				req.setAttribute(AssetsWebAttributes.BARCODE_LOGS, barcodeLogs);
				req.setAttribute(AssetsWebAttributes.ITEM_INFO_DTO, dto);
				forwardURL = AssetsURLList.ITEM_DETAIL_PAGE;
			} else if (action.equals(AssetsActionConstant.UPDATE_ACTION)) {
				RequestParser parser = new RequestParser();
				CheckBoxProp checkProp = new CheckBoxProp("subCheck");
				checkProp.setIgnoreOtherField(true);
				parser.setCheckBoxProp(checkProp);
				parser.transData(req);
				String[] barcodes = parser.getParameterValues("barcode");
                int yczCount = costEasyDAO.checkItemStatus(barcodes, "DISCARDED_YCZ");//�����Ѵ���
                int yubfCount = costEasyDAO.checkItemStatus(barcodes, "PRE_DISCARDE");//Ԥ����
                int zhCount = costEasyDAO.checkItemStatus(barcodes, "EXCHANGE");//�û�
                int yzzCount = costEasyDAO.checkItemStatus(barcodes, "PRE_ASSETS");//Ԥת��
                int xsCount = costEasyDAO.checkItemStatus(barcodes, "SELL");//����
                int czCount = costEasyDAO.checkItemStatus(barcodes, "RENT");//����
                int zyCount = costEasyDAO.checkItemStatus(barcodes, "NORMAL");//����
                int ztCount = costEasyDAO.checkItemStatus(barcodes, "ON_WAY");//��;
                int sxCount = costEasyDAO.checkItemStatus(barcodes, "SEND_REPAIR");//����
                int dxCount = costEasyDAO.checkItemStatus(barcodes, "TO_REPAIR");//����
                int dbfCount = costEasyDAO.checkItemStatus(barcodes, "TO_DISCARD");//������
                int yibfCount = costEasyDAO.checkItemStatus(barcodes, "DISCARDED");//�ѱ���
                int yczfCount = costEasyDAO.checkItemStatus(barcodes, "CLEARED");//�Ѵ���
                int xzCount = costEasyDAO.checkItemStatus(barcodes, "FREE");//����
                int shCount = costEasyDAO.checkItemStatus(barcodes, "DAMAGED");//��
                int yibfGsjCount = costEasyDAO.checkItemStatus(barcodes, "DISCARDED_TRANS");//�ѱ���-��˾�����
                int jzCount = costEasyDAO.checkItemStatus(barcodes, "DONATE");//����
                int dbfGsjCount = costEasyDAO.checkItemStatus(barcodes, "TO_DISCARD_TRANS");//������-��˾�����
                boolean errorCount = true;
                if (!dto.getItemStatus().equals("") && dto.getItemStatus().equals("DISCARDED_YCZ")) {//�����Ѵ���:ֻ���޸�״̬���ѱ��ϵ��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || zyCount > 0 || ztCount > 0 || sxCount > 0 || dxCount > 0 || dbfCount > 0 || yczfCount > 0 || xzCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_YCZ_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("NORMAL")) {//���ã��ų��ѱ��ϡ������Ѵ��á����������⡢���۵��ʲ�
                    if (yubfCount > 0 || zhCount > 0 || yzzCount > 0 || zyCount > 0 || ztCount > 0 || sxCount > 0 || dxCount > 0 || dbfCount > 0 || yczfCount > 0 || xzCount > 0 || shCount > 0 || yibfGsjCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_ZY_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("FREE") ) {// ���ã�ֻ�������õ��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || ztCount > 0 || sxCount > 0 || dxCount > 0 || dbfCount > 0 || yibfCount > 0 || yczfCount > 0 || xzCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_XZ_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("DONATE") ) {//������ֻ�������õ��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || ztCount > 0 || sxCount > 0 || dxCount > 0 || dbfCount > 0 || yibfCount > 0 || yczfCount > 0 || xzCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_JZ_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("EXCHANGE") ) {//�û���ֻ�������õ��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || ztCount > 0 || sxCount > 0 || dxCount > 0 || dbfCount > 0 || yibfCount > 0 || yczfCount > 0 || xzCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_ZH_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("PRE_DISCARDE") ) {//Ԥ���ϣ�ֻ�������á����á����޵��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || ztCount > 0 || sxCount > 0 || dbfCount > 0 || yibfCount > 0 || yczfCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_YBF_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("RENT") ) {//���⣺ֻ�������á����á����ޡ��ѱ��ϵ��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || ztCount > 0 || sxCount > 0 || dbfCount > 0 || yczfCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_CZ_DATA);
                        message.setIsError(true);
                    }
                } else if(!dto.getItemStatus().equals("") && dto.getItemStatus().equals("SELL") ) {//���ۣ�ֻ�������á����á����ޡ��ѱ��ϵ��ʲ�
                    if (yczCount > 0 || yubfCount > 0 || zhCount > 0 || yzzCount > 0 || xsCount > 0 || czCount > 0 || ztCount > 0 || sxCount > 0 || dbfCount > 0 || yczfCount > 0 || shCount > 0 || yibfGsjCount > 0 || jzCount > 0 || dbfGsjCount > 0) {
                        errorCount = false;
                        message = getMessage(CustMessageKey.UPDATE_XS_DATA);
                        message.setIsError(true);
                    }
                }
                if (errorCount) {
                    costEasyDAO.updateItems(barcodes);
                    costEasyDAO.logItemChgHistory(barcodes);
				    message = costEasyDAO.getMessage();
                }
				forwardURL = "/servlet/com.sino.ams.newasset.servlet.ItemCostEasyServlet";
				forwardURL += "?act=" + AssetsActionConstant.QUERY_ACTION;
			} else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) {
				File file = costEasyDAO.getExportFile();
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			} else if(action.equals("GET_DEPT_OPTION")){
                String organizationId = req.getParameter("organizationId");
                String deptOptionX = optProducer.getResDeptOption(organizationId,"");
                res.setContentType("text/html;charset=GBK");
                PrintWriter out = res.getWriter();
                out.print(deptOptionX);
                out.flush();
                out.close();
            }
            else {
				message = getMessage(AssetsMessageKeys.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (Throwable ex) {
            Logger.logError(ex);
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			closeDBConnection(conn);
			setHandleMessage(req, message);
			if(!forwardURL.equals("")){
				ServletForwarder forwarder = new ServletForwarder(req, res);
				forwarder.forwardView(forwardURL);
			}
		}
	}

    private void produceWebComponent(AmsAssetsAddressVDTO dto,
                                     AssetsOptProducer optProducer,
                                     HttpServletRequest req,
                                     SfUserDTO user) throws QueryException{
        String opt = optProducer.getItemCategoryOption(dto.getItemCategory());
        dto.setItemCategoryOpt(opt);
        opt = optProducer.getMainCompanyOption(dto.getMaintainCompany());
        dto.setMaintainCompanyOpt(opt);
        String itemStatus = optProducer.getDictOption(AssetsDictConstant.ITEM_STATUS, dto.getItemStatus());
        req.setAttribute(AssetsWebAttributes.ITEM_STATUS_OPTIONS, itemStatus);
        String deptOpt = optProducer.getUserAsssetsDeptOption(dto.getResponsibilityDept());
        req.setAttribute(AssetsWebAttributes.DEPT_OPTIONS, deptOpt);
        String specialDepOpt = optProducer.getSpecialAsssetsDeptOption(dto.getSpecialityDept());
        req.setAttribute("DEPT_OPTIONS2", specialDepOpt);

        opt = optProducer.getAllOrganization(user.getOrganizationId(), false);
        req.setAttribute(AssetsWebAttributes.CITY_OPTION, opt);

        req.setAttribute(QueryConstant.QUERY_DTO, dto);
    }
}
