package com.sino.td.newasset.servlet;

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

import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.bean.FlexValueUtil;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
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
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.StrException;
import com.sino.base.exception.UploadException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.td.commom.TdURLDefineList;
import com.sino.td.newasset.dao.TdAssetsTransHeaderDAO;
import com.sino.td.newasset.dao.TdAssetsTransLineDAO;
import com.sino.td.newasset.dao.TdOrderApproveDAO;
import com.sino.td.newasset.dto.TdAssetsTransHeaderDTO;
import com.sino.td.newasset.dto.TdAssetsTransLineDTO;
import com.sino.td.newasset.model.TdAssetsTransHeaderModel;


/**
 * <p>Title: AmsAssetsTransHeaderServlet</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class TdAssetsTransHeaderServlet extends BaseServlet {

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
            req2DTO.setDTOClassName(TdAssetsTransHeaderDTO.class.getName());
            TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO) req2DTO.getDTO(req);
            ServletConfigDTO servletConfig = getServletConfig(req);
            dto.setServletConfig(servletConfig);
            FlowDTO flowDTO = FlowAction.getDTOFromReq(req);
            String action = dto.getAct();
            conn = getDBConnection(req);
            TdAssetsTransHeaderDAO headerDAO = new TdAssetsTransHeaderDAO(user, dto, conn);
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

            DTOSet lineDTOSetALL = new DTOSet();
            if (action.equals("")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = TdURLDefineList.ASSETS_QUERY_PAGE_TD;
            } else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                BaseSQLProducer sqlProducer = new TdAssetsTransHeaderModel(user, dto);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                CheckBoxProp checkProp = new CheckBoxProp("subCheck");
                checkProp.addDbField("TRANS_ID");
                pageDAO.setWebCheckProp(checkProp);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = TdURLDefineList.ASSETS_QUERY_PAGE_TD;
            } else if (action.equals(AssetsActionConstant.NEW_ACTION)) {
                TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
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
                            forwardURL = TdURLDefineList.TRANS_EDIT_PAGE_TD;
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
                                    forwardURL = TdURLDefineList.TRANS_EDIT_PAGE_TD;
                                }
                            }
                        }
                    } else {
                        req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                        forwardURL = TdURLDefineList.TRANS_EDIT_PAGE_TD;
                    }
                } else {
//                  String groupPid = headerDAO.findGroupFlowId();(ռ���ã����ҵ�ǰ�û�����ID)
//                  req.setAttribute(AssetsWebAttributes.GROUP_PID, groupPid);
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    forwardURL = TdURLDefineList.TRANS_EDIT_PAGE_TD;
                    
                }
            } else if (action.equals(AssetsActionConstant.EDIT_ACTION)) {
                headerDAO.setDTOClassName(TdAssetsTransHeaderDTO.class.getName());
                TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO) headerDAO.getDataByPrimaryKey();
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                } else {
                    headerDTO.setServletConfig(servletConfig);
                    headerDTO = fillOptions(headerDTO, user, conn);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    TdAssetsTransLineDTO lineDTO = new TdAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    TdAssetsTransLineDAO lineDAO = new TdAssetsTransLineDAO(user, lineDTO, conn);
                    lineDAO.setCalPattern(LINE_PATTERN);
                    lineDAO.setDTOClassName(TdAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                    if (ds == null) {
                        ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    }
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    if (servletConfig.getProvinceCode().equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
                        forwardURL = AssetsURLList.TRANS_EDIT_PAGE_NM;
                    } else {
                        forwardURL = TdURLDefineList.TRANS_EDIT_PAGE_TD;
                    }
                }
            } else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) {
                headerDAO.setDTOClassName(TdAssetsTransHeaderDTO.class.getName());
                TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO)headerDAO.getDataByPrimaryKey();

                TdOrderApproveDAO approveDAO = new TdOrderApproveDAO(user, dto, conn);
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
                    TdAssetsTransLineDTO lineDTO = new TdAssetsTransLineDTO();
                    lineDTO.setTransId(headerDTO.getTransId());
                    lineDTO.setTransType(headerDTO.getTransType());
                    TdAssetsTransLineDAO lineDAO = new TdAssetsTransLineDAO(user, lineDTO, conn);
                    lineDAO.setCalPattern(LINE_PATTERN);
                    lineDAO.setDTOClassName(TdAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                    if (ds == null) {
                        ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    }
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    if (servletConfig.getProvinceCode().equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
                        forwardURL = AssetsURLList.TRANS_DETAIL_PAGE_NM;
                    } else {
                        forwardURL = TdURLDefineList.TRANS_DETAIL_PAGE_TD;
                    }
                }
            } else if (action.equals(AssetsActionConstant.SAVE_ACTION)) {
                req2DTO.setDTOClassName(TdAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(TdAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                headerDAO.setDTOParameter(dto);
                headerDAO.saveOrder(orderLines, flowDTO);
                message = headerDAO.getMessage();
                dto = (TdAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = TdURLDefineList.ASSETS_TRANS_SERVLET_TD;
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, orderLines);
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if (action.equals(AssetsActionConstant.SUBMIT_ACTION)) {
                req2DTO.setDTOClassName(TdAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(TdAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
                headerDAO.setDTOParameter(dto);
                headerDAO.submitOrder(orderLines, flowDTO);
                message = headerDAO.getMessage();
                dto = (TdAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = TdURLDefineList.ASSETS_TRANS_SERVLET_TD;
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if(action.equals(AssetsActionConstant.DELETE_ACTION)){
                req2DTO.setDTOClassName(TdAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(TdAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.IN_PROCESS);
                headerDAO.setDTOParameter(dto);
                headerDAO.doDelete(orderLines, flowDTO);
                message = headerDAO.getMessage();
                dto = (TdAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.OrderApproveServlet";
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if (action.equals(AssetsActionConstant.CANCEL_ACTION)) { //�����ݴ�ĵ���
                CheckBoxProp checkProp = new CheckBoxProp("subCheck");
                checkProp.addDbField("TRANS_ID");
                RequestParser parser = new RequestParser();
                parser.setCheckBoxProp(checkProp);
                parser.transData(req);
                String[] transIds = parser.getParameterValues("transId");
                boolean operateResult = headerDAO.cancelOrders(transIds);
                message = headerDAO.getMessage();
                forwardURL = TdURLDefineList.ASSETS_TRANS_SERVLET_TD;
                if (parser.contains("fromGroup")) {
                    if (operateResult) {
                        forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    } else {
                        forwardURL += "?act=" + AssetsActionConstant.EDIT_ACTION;
                    }
                    forwardURL += "&transId=" + dto.getTransId();
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.QUERY_ACTION;
                }
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
                String opt = optProducer.getTargetOrganization(StrUtil.strToInt(dto.getFromOrganizationId()), 0);
                out.print(opt);
                out.close();
            } else if(action.equals("excel")) {
                TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
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
                TdAssetsTransHeaderModel assetsTransHeaderModel = new TdAssetsTransHeaderModel(user, dto);
                String excel= StrUtil.nullToString(req.getParameter("excel"));
                SQLModel sqlModel = new SQLModel();
                sqlModel = assetsTransHeaderModel.getQueryBarcodeExcelModel(excel, headerDTO);
                SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//				simpleQuery.executeQuery();
//				RowSet rowSet = simpleQuery.getSearchResult();
                simpleQuery.setDTOClassName(TdAssetsTransLineDTO.class.getName());
                simpleQuery.executeQuery();
                lineDTOSet = simpleQuery.getDTOSet();

                //EXCEL����ʱ���벻�ɹ���DTOSET(�˷����Ҳ���ϵͳ�в����ڵ�BARCODE������Ҫ��ѭ����ȡ�ã�)
//                String returnModel = assetsTransHeaderModel.getBarcodeReturnModel(excel, headerDTO);
//                sqlModel = assetsTransHeaderModel.getQueryBarcodeAllExcelModel(excel, headerDTO, returnModel);
//                SimpleQuery simpleQuery2 = new SimpleQuery(sqlModel, conn);
//                simpleQuery2.setDTOClassName(TdAssetsTransLineDTO.class.getName());
//                simpleQuery2.executeQuery();
//                DTOSet lineDTOSetALL = simpleQuery2.getDTOSet();

                //EXCEL����ʱ���벻�ɹ���DTOSET
                lineDTOSetALL = new DTOSet();
                Map mp1=new HashMap();
                for (int j=0;j<lineDTOSet.getSize();j++){
                    String barcode=((TdAssetsTransLineDTO)lineDTOSet.getDTO(j)).getBarcode();
                    mp1.put(barcode,barcode);
                }
//                lineDTOSetALL.clearData();
                String[] arr=StrUtil.splitStr(excel,",");
                for (int i = 0; i < arr.length; i++) {
                    String s = arr[i].replaceAll("'","");
                    if(!mp1.containsKey(s)){
                      TdAssetsTransLineDTO dtoa=new TdAssetsTransLineDTO();
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
                forwardURL = TdURLDefineList.TRANS_EDIT_PAGE_TD;
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
        } catch (StrException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (UploadException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataTransException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (WebFileDownException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (CalendarException e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
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
     * @param dto  TdAssetsTransHeaderDTO
     * @param user SfUserDTO
     * @param conn Connection
     * @return TdAssetsTransHeaderDTO
     * @throws DTOException
     * @throws QueryException
     * @throws CalendarException
     */
    private TdAssetsTransHeaderDTO fillData(TdAssetsTransHeaderDTO dto, SfUserDTO user, Connection conn) throws
            DTOException, QueryException, CalendarException {
        dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD); //���õ��ݺ�
        dto.setCreatedBy(user.getUserId()); //���ô�����
        dto.setCreated(user.getUsername()); //���ô�����
        dto.setFromOrganizationId(StrUtil.nullToString(user.getOrganizationId()));
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
     * @param dto  TdAssetsTransHeaderDTO
     * @param user SfUserDTO
     * @param conn Connection
     * @return TdAssetsTransHeaderDTO
     * @throws QueryException
     */
    private TdAssetsTransHeaderDTO fillOptions(TdAssetsTransHeaderDTO dto, SfUserDTO user, Connection conn) throws
            QueryException {
        FlexValueUtil flexUtil = new FlexValueUtil(user, conn);
        dto.setTransTypeValue(flexUtil.getFlexValue(AssetsDictConstant.ORDER_TYPE_ASSETS, dto.getTransType()));
        AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
        String fromDept = dto.getFromDept();
        if (StrUtil.isEmpty(fromDept)) {
            fromDept = user.getDeptCode();
        }
        String deptOptions = optProducer.getUserAsssetsDeptOption(fromDept);
        dto.setFromDeptOption(deptOptions);
        String transType = dto.getTransType();
        if (transType.equals(AssetsDictConstant.ASS_RED)) {
            if (dto.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP)) {
                String opt = optProducer.getTargetOrganization(user.getOrganizationId(), StrUtil.strToInt(dto.getToOrganizationId()));
                dto.setToCompanyOption(opt);
                ServletConfigDTO config = dto.getServletConfig();
                String provinceCode = config.getProvinceCode();
                if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
                    String fromOrgId = dto.getFromOrganizationId();
                    if (fromOrgId.equals("")) {
                        fromOrgId = StrUtil.nullToString(user.getOrganizationId());
                    }
                    opt = optProducer.getAllOrganization(StrUtil.strToInt(fromOrgId));
                    dto.setFromCompanyOption(opt);
                    opt = optProducer.getBookTypeOption2(fromOrgId);
                    dto.setBookTypeOption(opt);
                }
            } else {
                if (dto.getTransId().equals("")) {
                    dto.setToOrganizationId(StrUtil.nullToString(user.getOrganizationId()));
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
