package com.sino.ams.newasset.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.bean.FlexValueUtil;
import com.sino.ams.newasset.constant.*;
import com.sino.ams.newasset.dao.AmsAssetsTransHeaderDAO;
import com.sino.ams.newasset.dao.AmsAssetsTransLineDAO;
import com.sino.ams.newasset.dao.OrderApproveDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AmsAssetsTransHeaderModel;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsAssetsTransHeaderServlet</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsTransHeaderServlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws	ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) req2DTO.getDTO(req);
            ServletConfigDTO servletConfig = getServletConfig(req);
            dto.setServletConfig(servletConfig);
            String action = dto.getAct();
            conn = getDBConnection(req);
            AmsAssetsTransHeaderDAO headerDAO = new AmsAssetsTransHeaderDAO(user, dto, conn);
            headerDAO.setServletConfig(servletConfig);
            String transType = dto.getTransType();
            String transferype = dto.getTransferType();
            AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String option = "";
            if (transType.equals(AssetsDictConstant.ASS_RED)) {
                option = optProducer.getTransferOption(dto.getTransferType());
                dto.setTransferTypeOption(option);
            }
            option = optProducer.getFAContentOption(dto.getFaContentCode());
            dto.setFaContentOption(option);
            boolean isGroupPID = headerDAO.isGroupFlowId();
            req.setAttribute(AssetsWebAttributes.IS_GROUP_PID, isGroupPID+"");
            boolean isFinanceGroup = headerDAO.isFinanceGroup();
            req.setAttribute(AssetsWebAttributes.IS_FINANCE_GROUP, isFinanceGroup+"");

            DTOSet lineDTOSetALL = new DTOSet();
            if (action.equals("")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = AssetsURLList.ASSETS_QUERY_PAGE;
            } else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                BaseSQLProducer sqlProducer = new AmsAssetsTransHeaderModel(user, dto);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                CheckBoxProp checkProp = new CheckBoxProp("subCheck");
                checkProp.addDbField("TRANS_ID");
                pageDAO.setWebCheckProp(checkProp);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = AssetsURLList.ASSETS_QUERY_PAGE;
            } else if (action.equals(AssetsActionConstant.NEW_ACTION)) {
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                } else {
                    option = optProducer.getFAContentOption(dto.getFaContentCode());
                    headerDTO.setFaContentOption(option);
                    String deptOptions = optProducer.getUserAsssetsDeptOption("");
                    dto.setFromDeptOption(deptOptions);
                }
                headerDTO.setServletConfig(servletConfig);
                String provinceCode = servletConfig.getProvinceCode();
                headerDTO.setCalPattern(LINE_PATTERN);
                if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) { //��������
                    if (transType.equals(AssetsDictConstant.ASS_SUB) ||transferype.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
                        DTOSet specGroups = getSpecGroups(user);
                        if (specGroups.getSize() < 1) {
                            message = getMessage(AssetsMessageKeys.NO_SPEC_GROUPS);
                            message.setIsError(true);
                            message.setNeedClose(true);
                            forwardURL = MessageConstant.MSG_PRC_SERVLET;
                        } else {
                            boolean hasSpecRole = hasSpecRole(user,servletConfig);
                            if (!hasSpecRole) {
                                message = getMessage(AssetsMessageKeys.NO_SPEC_ROLES);
                                message.setIsError(true);
                                message.setNeedClose(true);
                                forwardURL = MessageConstant.MSG_PRC_SERVLET;
                            } else {
                                req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                                forwardURL = AssetsURLList.TRANS_EDIT_PAGE_NM;
                            }
                        }
                    } else {
                        req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                        forwardURL = AssetsURLList.TRANS_EDIT_PAGE_NM;
                    }
                } else if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SX)) { //ɽ���ƶ�
                    if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        if(user.isProvinceUser()){
                            req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                            forwardURL = AssetsURLList.TRANS_EDIT_PAGE;
                        } else {
                            DTOSet specGroups = getSpecGroups(user);
                            if (specGroups.getSize() < 1) {
                                message = getMessage(AssetsMessageKeys.NO_SPEC_GROUPS);
                                message.setIsError(true);
                                message.setNeedClose(true);
                                forwardURL = MessageConstant.MSG_PRC_SERVLET;
                            } else {
                                if (!hasSpecRole(user, servletConfig)) {
                                    message = getMessage(AssetsMessageKeys.NO_SPEC_ROLES);
                                    message.setIsError(true);
                                    message.setNeedClose(true);
                                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                                } else {
                                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                                    forwardURL = AssetsURLList.TRANS_EDIT_PAGE;
                                }
                            }
                        }
                    } else {
                        req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                        forwardURL = AssetsURLList.TRANS_EDIT_PAGE;
                    }
                } else {
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    forwardURL = AssetsURLList.TRANS_EDIT_PAGE;
                    
                }
            } else if (action.equals(AssetsActionConstant.EDIT_ACTION)) {
                headerDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) headerDAO.getDataByPrimaryKey();
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                } else {
                    headerDTO.setServletConfig(servletConfig);
                    headerDTO = fillOptions(headerDTO, user, conn);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(user, lineDTO, conn);
                    lineDAO.setCalPattern(LINE_PATTERN);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                    if (ds == null) {
                        ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    }
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    if (servletConfig.getProvinceCode().equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
                        forwardURL = AssetsURLList.TRANS_EDIT_PAGE_NM;
                    } else {
                        forwardURL = AssetsURLList.TRANS_EDIT_PAGE;
                    }
                }
            } else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) {
                headerDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)headerDAO.getDataByPrimaryKey();

                OrderApproveDAO approveDAO = new OrderApproveDAO(user, dto, conn);
                String accessSheet = approveDAO.getAccessSheet();//��������
                headerDTO.setAccessSheet(accessSheet);

                if (headerDTO == null) {
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                } else {
                    headerDTO.setServletConfig(servletConfig);
                    headerDTO = fillOptions(headerDTO, user, conn);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(user, lineDTO, conn);
                    lineDAO.setCalPattern(LINE_PATTERN);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                    if (ds == null) {
                        ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    }
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    if (servletConfig.getProvinceCode().equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
                        forwardURL = AssetsURLList.TRANS_DETAIL_PAGE_NM;
                    } else {
                        forwardURL = AssetsURLList.TRANS_DETAIL_PAGE;
                    }
                }
            } else if (action.equals(AssetsActionConstant.SAVE_ACTION)) {
                req2DTO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(AmsAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                headerDAO.setDTOParameter(dto);
                headerDAO.newSaveOrder(orderLines);
                message = headerDAO.getMessage();
                dto = (AmsAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = AssetsURLList.ASSETS_TRANS_SERVLET;
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, orderLines);
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if (action.equals(AssetsActionConstant.SUBMIT_ACTION)) {
                req2DTO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(AmsAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
                headerDAO.setDTOParameter(dto);
                headerDAO.newSubmitOrder(orderLines);
                message = headerDAO.getMessage();
                dto = (AmsAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = AssetsURLList.ASSETS_TRANS_SERVLET;
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if(action.equals(AssetsActionConstant.DELETE_ACTION)){
                req2DTO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(AmsAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
                headerDAO.setDTOParameter(dto);
                headerDAO.doDelete(orderLines);
                message = headerDAO.getMessage();
                dto = (AmsAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.OrderApproveServlet";
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if (action.equals(AssetsActionConstant.CANCEL_ACTION)) { //�����ݴ�ĵ���
                boolean operateResult = headerDAO.cancelOrders();
                message = headerDAO.getMessage();
                if (operateResult) {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                }
                forwardURL += "&transId=" + dto.getTransId();
            } else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) { //����
                File file = headerDAO.exportFile();
                headerDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals(AssetsActionConstant.GET_TARGET_OU)) { //��ȡĿ��OU
                res.setContentType("text/html;charset=GBK");
                PrintWriter out = res.getWriter();
                String opt = optProducer.getTargetOrganization(dto.getFromOrganizationId(), 0);
                out.print(opt);
                out.close();
            } else if(action.equals("excel")) {
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                } else {
                    option = optProducer.getFAContentOption(dto.getFaContentCode());
                    headerDTO.setFaContentOption(option);
                    String deptOptions = optProducer.getUserAsssetsDeptOption("");
                    dto.setFromDeptOption(deptOptions);
                }
                headerDTO.setServletConfig(servletConfig);
                headerDTO.setCalPattern(LINE_PATTERN);

                DTOSet lineDTOSet = new DTOSet();
                AmsAssetsTransHeaderModel assetsTransHeaderModel = new AmsAssetsTransHeaderModel(user, dto);
                String excel= StrUtil.nullToString(req.getParameter("excel"));
                SQLModel sqlModel = new SQLModel();
                sqlModel = assetsTransHeaderModel.getQueryBarcodeExcelModel(excel, headerDTO);
                SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//				simpleQuery.executeQuery();
//				RowSet rowSet = simpleQuery.getSearchResult();
                simpleQuery.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                simpleQuery.executeQuery();
                lineDTOSet = simpleQuery.getDTOSet();

                //EXCEL����ʱ���벻�ɹ���DTOSET
                lineDTOSetALL = new DTOSet();
                Map mp1=new HashMap();
                for (int j=0;j<lineDTOSet.getSize();j++){
                    String barcode=((AmsAssetsTransLineDTO)lineDTOSet.getDTO(j)).getBarcode();
                    mp1.put(barcode,barcode);
                }
//                lineDTOSetALL.clearData();
                String[] arr=StrUtil.splitStr(excel,",");
                for (int i = 0; i < arr.length; i++) {
                    String s = arr[i].replaceAll("'","");
                    if(!mp1.containsKey(s)){
                      AmsAssetsTransLineDTO dtoa=new AmsAssetsTransLineDTO();
                      dtoa.setBarcode(s);
                      lineDTOSetALL.addDTO(dtoa);
                    }
                }
                if (lineDTOSetALL.isEmpty()) {
                    message = getMessage(CustMessageKey.EXPORT_SUCCESS);
                    message.setIsError(false);
                } else {
                    message = getMessage(CustMessageKey.EXPORT_FAILURE);
                    message.setIsError(true);
                }
                List list = new ArrayList();
                list.add(0,"1��ϵͳ�п��ܲ����ڸ�����");
                list.add(1,"2����������ܲ����ڱ���˾");
                list.add(2,"3����������ܲ���������Ȩ�޷�Χ");
                list.add(3,"4����������ܴ����ڵ�����(�̵㡢���������ϵ�)");

                req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
//                req.setAttribute(WebAttrConstant.WORKORDER_LOC_ROWSET, rowSet);
                req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA,lineDTOSet);
                req.setAttribute(AssetsWebAttributes.PRIVI_DATA,lineDTOSetALL);
                req.setAttribute("REMARK_LIST",list);
                forwardURL = AssetsURLList.TRANS_EDIT_PAGE;
            } else if (action.equals(AMSActionConstant.VALIDATE_ACTION)) {//��֤barcode�Ƿ����
				res.setContentType("text/html;charset=GBK"); 
				PrintWriter out = res.getWriter();
				String fromGroup = req.getParameter("fromGroup");
                boolean result = false;
				if(fromGroup != null && !fromGroup.equals("")){
				}
                out.print(result);
				out.close();
			} else if (action.equals("DO_THRED_DEPT")) {
                String fDept = StrUtil.nullToString(req.getParameter("fDept"));
                String tDept = StrUtil.nullToString(req.getParameter("tDept"));
                boolean isThredDept = headerDAO.findThredDept(fDept, tDept);
				PrintWriter out = res.getWriter();
				if (isThredDept) {
					out.print("Y");
				} else {
                    out.print("N");
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
        } catch (QueryException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (Throwable ex) {
            Logger.logError(ex);
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } finally {
            closeDBConnection(conn);
            setHandleMessage(req, message);
            if (!StrUtil.isEmpty(forwardURL)) {
                ServletForwarder forwarder = new ServletForwarder(req, res);
                forwarder.forwardView(forwardURL);
            }
        }
    }

    /**
     * ����:���DTO����
     * @param dto  AmsAssetsTransHeaderDTO
     * @param user SfUserDTO
     * @param conn Connection
     * @return AmsAssetsTransHeaderDTO
     * @throws DTOException
     * @throws QueryException
     * @throws CalendarException
     */
    private AmsAssetsTransHeaderDTO fillData(AmsAssetsTransHeaderDTO dto, SfUserDTO user, Connection conn) throws
            DTOException, QueryException, CalendarException {
        dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD); //���õ��ݺ�
        dto.setCreatedBy(user.getUserId()); //���ô�����
        dto.setCreated(user.getUsername()); //���ô�����
        dto.setFromOrganizationId(user.getOrganizationId());
        dto.setCurrCreationDate();
        dto.setFromCompanyName(user.getCompany());
        dto.setBookTypeName(user.getBookTypeCode() + "--" + user.getBookTypeName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getMobilePhone());
        dto.setUserDeptName(user.getDeptName());
        DTOSet assetsGroups = user.getUserGroups();
        String provinceCode = dto.getServletConfig().getProvinceCode();
        if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) { //���ɹ����⴦��
            String transferType = dto.getTransferType();
            SfGroupDTO sfGRoup = null;
            if (transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
                DTOSet specGroups = getSpecGroups(user);
                if (specGroups.getSize() == 1) {
                    sfGRoup = (SfGroupDTO) specGroups.getDTO(0);
                    dto.setFromGroup(sfGRoup.getGroupId());
                    dto.setFromGroupName(sfGRoup.getGroupname());
                }
            } else {
                if (assetsGroups.getSize() == 1) {
                    sfGRoup = (SfGroupDTO) assetsGroups.getDTO(0);
                    dto.setFromGroup(sfGRoup.getGroupId());
                    dto.setFromGroupName(sfGRoup.getGroupname());
                }
            }
        } else if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SX)) {//ɽ�����⴦��
            String transType = dto.getTransType();
            if(transType.equals(AssetsDictConstant.ASS_RED)){
                if (assetsGroups.getSize() == 1) {
                    SfGroupDTO sfGRoup = (SfGroupDTO) assetsGroups.getDTO(0);
                    dto.setFromGroup(sfGRoup.getGroupId());
                    dto.setFromGroupName(sfGRoup.getGroupname());
                    dto.setGroupProp(sfGRoup.getGroupProp());
                }
            } else {
                if(!user.isProvinceUser()){
                    assetsGroups = getSpecGroups(user);
                }
                if (assetsGroups.getSize() == 1) {
                    SfGroupDTO sfGRoup = (SfGroupDTO) assetsGroups.getDTO(0);
                    dto.setFromGroup(sfGRoup.getGroupId());
                    dto.setFromGroupName(sfGRoup.getGroupname());
                    dto.setGroupProp(sfGRoup.getGroupProp());
                }
            }
        } else {
            if (assetsGroups.getSize() == 1) {
                SfGroupDTO sfGRoup = (SfGroupDTO) assetsGroups.getDTO(0);
                dto.setFromGroup(sfGRoup.getGroupId());
                dto.setFromGroupName(sfGRoup.getGroupname());
                dto.setGroupProp(sfGRoup.getGroupProp());
            }
        }
        dto = fillOptions(dto, user, conn);
        return dto;
    }

    /**
     * ���ܣ���ȡ�û���רҵ���
     * @param user SfUserDTO
     * @return DTOSet
     * @throws DTOException
     */
    private DTOSet getSpecGroups(SfUserDTO user) throws DTOException {
        DTOSet specGroups = new DTOSet();
        DTOSet assetsGroups = user.getUserGroups();
        SfGroupDTO sfGroup = null;
        for (int i = 0; i < assetsGroups.getSize(); i++) {
            sfGroup = (SfGroupDTO) assetsGroups.getDTO(i);
            if (!sfGroup.getGroupProp().equals(AssetsDictConstant.GROUP_PROP_SPEC)) {
                continue;
            }
            specGroups.addDTO(sfGroup);
        }
        return specGroups;
    }

    /**
     * ���ܣ���ȡ�û���רҵ���
     * @param user          SfUserDTO
     * @param servletConfig servlet������Ϣ
     * @return DTOSet
     * @throws DTOException
     */
    private boolean hasSpecRole(SfUserDTO user, ServletConfigDTO servletConfig) throws
            DTOException {
        boolean hasSpecRole = false;
        DTOSet specGroups = getSpecGroups(user);
        if (specGroups != null && !specGroups.isEmpty()) {
            DTOSet groupRoles = user.getUserRight();
            SfGroupDTO sfGroup = null;
            SfUserRightDTO sfUserRight = null;
            for (int i = 0; i < specGroups.getSize(); i++) {
                sfGroup = (SfGroupDTO) specGroups.getDTO(i);
                for (int j = 0; j < groupRoles.getSize(); j++) {
                    sfUserRight = (SfUserRightDTO) groupRoles.getDTO(j);
                    if (sfUserRight.getGroupId()!=sfGroup.getGroupId()) {
                        continue;
                    }
                    if (!sfUserRight.getRoleName().equals(servletConfig.
                            getMtlAssetsMgr())) {
                        continue;
                    }
                    hasSpecRole = true;
                    break;
                }
                if (hasSpecRole) {
                    break;
                }
            }
        }
        return hasSpecRole;
    }

    /**
     * ���ܣ������ʲ���Դ��������������
     * @param dto  AmsAssetsTransHeaderDTO
     * @param user SfUserDTO
     * @param conn Connection
     * @return AmsAssetsTransHeaderDTO
     * @throws QueryException
     */
    private AmsAssetsTransHeaderDTO fillOptions(AmsAssetsTransHeaderDTO dto, SfUserDTO user, Connection conn) throws
            QueryException {
        FlexValueUtil flexUtil = new FlexValueUtil(user, conn);
        dto.setTransTypeValue(flexUtil.getFlexValue(AssetsDictConstant.ORDER_TYPE_ASSETS, dto.getTransType()));
        AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
        String fromDept = StrUtil.nullToString(dto.getFromDept());
        if (StrUtil.isEmpty(fromDept)) {
            fromDept = user.getDeptCode();
        }
        String deptOptions = optProducer.getUserAsssetsDeptOption(fromDept);
        dto.setFromDeptOption(deptOptions);
        String transType = dto.getTransType();
        if (transType.equals(AssetsDictConstant.ASS_RED)) {
            if (dto.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP)) {
                String opt = optProducer.getTargetOrganization(user.getOrganizationId(), dto.getToOrganizationId());
                dto.setToCompanyOption(opt);
                ServletConfigDTO config = dto.getServletConfig();
                String provinceCode = config.getProvinceCode();
                if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
                    int fromOrgId = dto.getFromOrganizationId();
                    if (fromOrgId==0) {
                        fromOrgId = user.getOrganizationId();
                    }
                    opt = optProducer.getAllOrganization(fromOrgId);
                    dto.setFromCompanyOption(opt);
                    opt = optProducer.getBookTypeOption2(StrUtil.nullToString(fromOrgId));
                    dto.setBookTypeOption(opt);
                }
            } else {
                if (dto.getTransId().equals("")) {
                    dto.setToOrganizationId(user.getOrganizationId());
                }
            }
        }
        String transOption = "";
        if (transType.equals(AssetsDictConstant.ASS_RED)) {
            transOption = optProducer.getTransferFaOption(dto.getFaContentCode(),
                    dto.getTransferType());
        } else {
            transOption = optProducer.getFAContentOption(dto.getFaContentCode());
        }
        dto.setFaContentOption(transOption);
        return dto;
    }
}
