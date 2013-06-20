package com.sino.ams.newasset.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.allocation.dao.AmsAssetsAllocationApproveDAO;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsTransHeaderDAO;
import com.sino.ams.newasset.dao.AmsAssetsTransLineDAO;
import com.sino.ams.newasset.dao.OrderApproveDAO;
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
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * Created by IntelliJ IDEA.
 * User: T_yuyao
 * Date: 2011-3-31
 * Time: 21:59:22
 * To change this template use File | Settings | File Templates.
 */
public class DisOrderApproveServlet extends BaseServlet {

    /**
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
            req2DTO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) req2DTO.getDTO(req);
            ServletConfigDTO servletConfig = getServletConfig(req);
            String provinceCode = servletConfig.getProvinceCode();
            dto.setServletConfig(servletConfig);
            String action = dto.getAct();
            conn = getDBConnection(req);
            //���������ʲ�����,��ȡѡ�еı����ʲ�������ʼ��
            RequestParser par = new RequestParser();
            par.transData(req);
            String[] barcodess = par.getParameterValues("subCheck");
            dto.setBarcodess(barcodess);
            String transId = StrUtil.nullToString(req.getParameter("sf_appDataID"));
            OrderApproveDAO approveDAO = new OrderApproveDAO(user, dto, conn);
            approveDAO.setServletConfig(servletConfig);
            boolean isTdProvinceUser = user.getCompanyCode().equals(servletConfig.getTdProCompanyCode());
            req.setAttribute("isTdProvinceUser", isTdProvinceUser);
            if (action.equals(AssetsActionConstant.EDIT_ACTION)) { //��������ҳ��
                dto.setTransId(transId);
                approveDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());

                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) approveDAO.getDataByPrimaryKey();
                String accessSheet = approveDAO.getAccessSheet();//��������
                headerDTO.setAccessSheet(accessSheet);
                if (headerDTO == null) {
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                } else {
                    AmsAssetsTransHeaderDAO headerDAO = new AmsAssetsTransHeaderDAO(user, dto, conn);
                    boolean isGroupPID = headerDAO.isGroupFlowId();
                    req.setAttribute(AssetsWebAttributes.IS_GROUP_PID, isGroupPID + "");

                    headerDTO.setAttribute1(dto.getAttribute1());
                    headerDTO.setAttribute2(dto.getAttribute2());
                    headerDTO.setAttribute3(dto.getAttribute3());
                    headerDTO.setAttribute4(dto.getAttribute4());
                    headerDTO.setAttribute5(dto.getAttribute5());
                    headerDTO.setSectionRight(dto.getSectionRight());
                    headerDTO.setHiddenRight(dto.getHiddenRight());

                    headerDTO.setServletConfig(servletConfig);
                    AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(user, lineDTO, conn);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    AmsAssetsAllocationApproveDAO approveDAO2 = new AmsAssetsAllocationApproveDAO(user, headerDTO, conn);//�ж��Ƿ���ʵ�ﲿ����
                    boolean isFinanceGroup = headerDAO.isCurrFlowFinanceGroup(headerDTO.getFromGroup());//�жϽ�������Ƿ����ڲ���
                    req.setAttribute(AssetsWebAttributes.IS_FINANCE_GROUP, isFinanceGroup + "");
                    boolean isSpecialGroup = approveDAO2.isSpecialGroup(headerDTO.getFromGroup());//�ж��Ƿ�����ʵ�ﲿ��
                    req.setAttribute(AssetsWebAttributes.IS_SPECIAL_GROUP, isSpecialGroup + "");
                    forwardURL = "/newasset/assetsDisTransApprove.jsp";
                }
            } else if (action.equals(AssetsActionConstant.APPROVE_ACTION)) { //�������̣����Ӹ�����ͷ����˻�����(2008-12-01 17:34)
                DTOSet orderLines = new DTOSet();
                if (dto.getAttribute4().equals(AssetsDictConstant.EDIT_ACCOUNT)) {//�ýڵ����۾ɷ����˻��ɱ༭�ڵ�
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
                //���������ʲ�����
                boolean operateResult = approveDAO.newApproveOrder();
                dto = (AmsAssetsTransHeaderDTO) approveDAO.getDTOParameter();
                message = approveDAO.getMessage();
                if (operateResult) {
                    if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) { //������������
                        forwardURL = AssetsURLList.ORDER_APPROVE_SERVLET;
                        forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    } else { //��������������
                        forwardURL = "/servlet/" + AmsAssetsDisTransHeaderServlet.class.getName();
                        forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    }
                } else {
                    forwardURL = AssetsURLList.ORDER_APPROVE_SERVLET;
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                }
                forwardURL += "&transType=" + dto.getTransType();
                forwardURL += "&transId=" + dto.getTransId();
            } else if (action.equals("REJECT_ACTION")) { //�������̣����Ӹ�����ͷ����˻�����(2008-12-01 17:34)
                approveDAO.rejectOrder();
                message = approveDAO.getMessage();
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsDisTransHeaderServlet";
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
                    AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(user, lineDTO, conn);
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

        } catch (Throwable ex) {
            Logger.logError(ex);
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
