package com.sino.ams.newasset.allocation.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.allocation.dao.AmsAssetsAllocationApproveDAO;
import com.sino.ams.newasset.allocation.dao.AmsAssetsAllocationLineDAO;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.UploadException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-3-31
 * Time: 12:53:23
 * To change this template use File | Settings | File Templates.
 */
public class AmsAssetsAllocationApproveServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) req2DTO.getDTO(req);
            ServletConfigDTO servletConfig = getServletConfig(req);
            String provinceCode = servletConfig.getProvinceCode();
            dto.setServletConfig(servletConfig);
            String action = dto.getAct();
            conn = getDBConnection(req);
            if (!dto.getApp_dataID().equals("")) {
                dto.setTransId(dto.getApp_dataID());
            }
            //���������ʲ�����,��ȡѡ�еı����ʲ�������ʼ��
            RequestParser par = new RequestParser();
            par.transData(req);
            String[] barcodess = par.getParameterValues("subCheck");
            dto.setBarcodess(barcodess);

            AmsAssetsAllocationApproveDAO approveDAO = new AmsAssetsAllocationApproveDAO(user, dto, conn);
            approveDAO.setServletConfig(servletConfig);
            if (action.equals(AssetsActionConstant.EDIT_ACTION)) { //��������ҳ��
                approveDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) approveDAO.getDataByPrimaryKey();
                if (headerDTO == null) {
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                } else {
                    String accessSheet = approveDAO.getAccessSheet();//��������
                    headerDTO.setAccessSheet(accessSheet);
                    boolean isFinanceGroup = approveDAO.isFinanceGroup(headerDTO.getFromGroup());//�ж��Ƿ����ڲ���
                    req.setAttribute(AssetsWebAttributes.IS_FINANCE_GROUP, isFinanceGroup + "");
                    boolean isSpecialGroup = approveDAO.isSpecialGroup(headerDTO.getFromGroup());//�ж��Ƿ�����ʵ�ﲿ��
                    req.setAttribute(AssetsWebAttributes.IS_SPECIAL_GROUP, isSpecialGroup + "");
                    headerDTO.setServletConfig(servletConfig);
                    AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(user, lineDTO, conn);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    headerDTO.setAttribute1(dto.getSf_task_attribute1());
                    headerDTO.setAttribute2(dto.getSf_task_attribute2());
                    headerDTO.setAttribute3(dto.getSf_task_attribute3());
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    forwardURL = AssetsURLList.ASSETS_ALLOCATION_APPROVE;
                }
            } else if (action.equals(AssetsActionConstant.APPROVE_ACTION)) { //�������̣����Ӹ�����ͷ����˻�����(2008-12-01 17:34)
                DTOSet orderLines = new DTOSet();
                if (dto.getSf_task_attribute2().equals(AssetsDictConstant.EDIT_ACCOUNT)) {//�ýڵ����۾ɷ����˻��ɱ༭�ڵ�
                    req2DTO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    req2DTO.setIgnoreFields(AmsAssetsTransHeaderDTO.class);
                    orderLines = req2DTO.getDTOSet(req);
                }
                if (dto.getSf_task_attribute1().equals("DEPT")) {//���ż�������벿���ʲ�����Ա�������ص���������
                    req2DTO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    req2DTO.setIgnoreFields(AmsAssetsTransHeaderDTO.class);
                    orderLines = req2DTO.getDTOSet(req);
                }
                if (orderLines != null) {
                    int lineCount = orderLines.getSize();
                    for (int i = 0; i < lineCount; i++) {
                        AmsAssetsTransLineDTO lineDTO = (AmsAssetsTransLineDTO) orderLines.getDTO(i);
                        lineDTO.setTransId(dto.getTransId());
                        orderLines.set(i, lineDTO);
                    }
                }
                boolean operateResult = approveDAO.approveOrder(orderLines);
                dto = (AmsAssetsTransHeaderDTO) approveDAO.getDTOParameter();
                message = approveDAO.getMessage();
                if (operateResult) {
                    forwardURL = AssetsURLList.ASSETS_ALLOCATION_SERVLET;
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                } else {
                    forwardURL = AssetsURLList.ASSETS_ALLOCATION_APPROVE_SERVLET;
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                }
                forwardURL += "&transType=" + dto.getTransType();
                forwardURL += "&transId=" + dto.getTransId();
            } else if (action.equals("REJECT_ACTION")) { //�˻�����
                approveDAO.rejectOrder();
                message = approveDAO.getMessage();
                forwardURL = AssetsURLList.ASSETS_ALLOCATION_SERVLET;
                forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                forwardURL += "&transType=" + dto.getTransType();
                forwardURL += "&transId=" + dto.getTransId();
            } else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) { //��������ҳ��
                approveDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
                approveDAO.setCalPattern(LINE_PATTERN);
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) approveDAO.getDataByPrimaryKey();
                String accessSheet = approveDAO.getAccessSheet();//��������
                headerDTO.setAccessSheet(accessSheet);
                if (headerDTO == null) {
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                } else {
                    headerDTO.setServletConfig(servletConfig);
                    AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(user, lineDTO, conn);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) { //������������
                        forwardURL = AssetsURLList.APPROVE_DETL_NM;
                    } else {
                        forwardURL = AssetsURLList.APPROVE_DETL_PAGE;
                    }
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
        } catch (UploadException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } finally {
            closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }
}
