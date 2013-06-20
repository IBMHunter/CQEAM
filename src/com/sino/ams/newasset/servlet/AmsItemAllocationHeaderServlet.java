package com.sino.ams.newasset.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
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
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsItemAllocationHeaderDAO;
import com.sino.ams.newasset.dao.AmsItemAllocationLineDAO;
import com.sino.ams.newasset.dao.ApproveContentDAO;
import com.sino.ams.newasset.dao.OrderApproveDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AmsItemAllocationHeaderModel;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

public class AmsItemAllocationHeaderServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) req2DTO.getDTO(req);
            if(dto.getTransId().equals("")){
                dto.setTransId(dto.getApp_dataID());
            }
            ServletConfigDTO servletConfig = getServletConfig(req);
            dto.setServletConfig(servletConfig);
            String action = dto.getAct();
            conn = getDBConnection(req);
            AmsItemAllocationHeaderDAO headerDAO = new AmsItemAllocationHeaderDAO(user, dto, conn);
            headerDAO.setServletConfig(servletConfig);
            String transferype = dto.getTransferType();
            AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String option = optProducer.getTransferOption(dto.getTransferType());
            dto.setTransferTypeOption(option);
            option = optProducer.getItemContentOption(dto.getFaContentCode());
            dto.setFaContentOption(option);
            if("".equals(dto.getEmergentLevel())) {
                dto.setEmergentLevel("0");
            }
            String emergentLevelOption = optProducer.getAmsEmergentLevel(dto.getEmergentLevel());
            dto.setEmergentLevelOption(emergentLevelOption);
            DTOSet lineDTOSetALL = null;
            if (action.equals("")) {
                if (dto.getApp_dataID().equals("")) {
                    AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
                    if (headerDTO == null) {
                        headerDTO = fillData(dto, user, conn);
                    } else {
                        option = optProducer.getItemContentOption(dto.getFaContentCode());
                        headerDTO.setFaContentOption(option);
                        String deptOptions = optProducer.getUserAsssetsDeptOption("");
                        dto.setFromDeptOption(deptOptions);
                    }
                    headerDTO.setTransferType(transferype);
                    headerDTO.setServletConfig(servletConfig);
                    headerDTO.setCalPattern(LINE_PATTERN);
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    forwardURL = "/newasset/itemAllocationEdit.jsp";
                } else {
                    if(StrUtil.isEmpty(dto.getTransId())){
                        dto.setTransId(dto.getApp_dataID());
                        headerDAO.setDTOParameter(dto);
                    }
                    headerDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
                    AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) headerDAO.getDataByPrimaryKey();
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
                        AmsItemAllocationLineDAO lineDAO = new AmsItemAllocationLineDAO(user, lineDTO, conn);
                        lineDAO.setCalPattern(LINE_PATTERN);
                        lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                        DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                        if (ds == null) {
                            ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                        }
                          headerDTO.setEmergentLevelOption(optProducer.getAmsEmergentLevel(headerDTO.getEmergentLevel()));
                        req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                        req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                        forwardURL = "/newasset/itemAllocationEdit.jsp";
                    }
                }
            } else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                BaseSQLProducer sqlProducer = new AmsItemAllocationHeaderModel(user, dto);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                CheckBoxProp checkProp = new CheckBoxProp("subCheck");
                checkProp.addDbField("TRANS_ID");
                pageDAO.setWebCheckProp(checkProp);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = AssetsURLList.ASSETS_ALLOCATION_QUERY;
            } else if (action.equals(AssetsActionConstant.NEW_ACTION)) {
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                } else {
                    option = optProducer.getItemContentOption(dto.getFaContentCode());
                    headerDTO.setFaContentOption(option);
                    String deptOptions = optProducer.getUserAsssetsDeptOption("");
                    dto.setFromDeptOption(deptOptions);
                }
                headerDTO.setTransferType(transferype);
                headerDTO.setServletConfig(servletConfig);
                headerDTO.setCalPattern(LINE_PATTERN);
                req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                forwardURL = "/newasset/itemAllocationEdit.jsp";
            } else if (action.equals(AssetsActionConstant.EDIT_ACTION)) {
                headerDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());

                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) headerDAO.getDataByPrimaryKey();
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
                    AmsItemAllocationLineDAO lineDAO = new AmsItemAllocationLineDAO(user, lineDTO, conn);
                    lineDAO.setCalPattern(LINE_PATTERN);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                    if (ds == null) {
                        ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    }
                      headerDTO.setEmergentLevelOption(dto.getEmergentLevelOption());
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    forwardURL = "/newasset/itemAllocationEdit.jsp";
                }
            } else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) {
                headerDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) headerDAO.getDataByPrimaryKey();

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
                    AmsItemAllocationLineDAO lineDAO = new AmsItemAllocationLineDAO(user, lineDTO, conn);
                    lineDAO.setCalPattern(LINE_PATTERN);
                    lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                    if (ds == null) {
                        ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
                    }
                    
                    String tableName = "AMS_ASSETS_TRANS_HEADER";                    
                    RowSet rows = null;
    				rows = ApproveContentDAO.getApproveContent(conn, headerDTO.getTransId(), tableName);
    				req.setAttribute(AssetsWebAttributes.APPROVE_CONTENT, rows);
    				
    				headerDTO.setEmergentLevelOption(optProducer.getAmsEmergentLevel(headerDTO.getEmergentLevel()));
                    
                    req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                    req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                    forwardURL = "/newasset/itemAllocationDetail.jsp";
                }
            } else if (action.equals(AssetsActionConstant.SAVE_ACTION)) {
                req2DTO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                req2DTO.setIgnoreFields(AmsAssetsTransHeaderDTO.class);
                DTOSet orderLines = req2DTO.getDTOSet(req);
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                headerDAO.setDTOParameter(dto);
                headerDAO.saveOrder(orderLines);
                message = headerDAO.getMessage();
                dto = (AmsAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.AmsItemAllocationHeaderServlet";
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
                headerDAO.submitOrder(orderLines);
                message = headerDAO.getMessage();
                dto = (AmsAssetsTransHeaderDTO) headerDAO.getDTOParameter();
                String transId = dto.getTransId();
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.AmsItemAllocationHeaderServlet";
                if (transId.equals("")) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
            } else if (action.equals(AssetsActionConstant.DELETE_ACTION)) {
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
                boolean operateResult = headerDAO.cancelOrder();
                message = headerDAO.getMessage();
                forwardURL = "/servlet/com.sino.ams.newasset.servlet.AmsItemAllocationHeaderServlet";
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
                String opt = optProducer.getTargetOrganization(dto.getFromOrganizationId(), dto.getFromOrganizationId());
                out.print(opt);
                out.close();
            } else if (action.equals("excel")) {
                AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) req.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                } else {
                    option = optProducer.getItemContentOption(dto.getFaContentCode());
                    headerDTO.setFaContentOption(option);
                    String deptOptions = optProducer.getUserAsssetsDeptOption("");
                    dto.setFromDeptOption(deptOptions);
                }
                headerDTO.setServletConfig(servletConfig);
                headerDTO.setCalPattern(LINE_PATTERN);

                AmsItemAllocationHeaderModel assetsTransHeaderModel = new AmsItemAllocationHeaderModel(user, dto);
                String excel = StrUtil.nullToString(req.getParameter("excel"));
                String[] arr = StrUtil.splitStr(excel, ",");
                
                //ɾ��IMP_BARCODE�������ڵ�ǰ�û������BARCODE��¼
                SQLModel delSqlModel = new SQLModel();
                delSqlModel=assetsTransHeaderModel.getImpBarcodeDeleteModel();
                DBOperator.updateRecord(delSqlModel, conn);
                //��ӵ�ǰ�û�Ҫ�����BARCODE��¼����IMP_BARCODE��
                SQLModel insertSqlModel = new SQLModel();
                //������Ҫ���ظ��ж�
                Collection c=new ArrayList<String>();
                for (int i = 0; i < arr.length; i++) {
                	String s = arr[i].replaceAll("'","");
                	if (!c.contains(s)) {
	                	insertSqlModel=assetsTransHeaderModel.getDataInsertModel(s);
	                	DBOperator.updateRecord(insertSqlModel, conn);
	                	c.add(s);
                	}
				}
                
                SQLModel sqlModel = assetsTransHeaderModel.getQueryBarcodeExcelModel(excel, headerDTO);
                SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
                simpleQuery.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
                simpleQuery.executeQuery();
                DTOSet lineDTOSet = simpleQuery.getDTOSet();

                //EXCEL����ʱ���벻�ɹ���DTOSET
                lineDTOSetALL = new DTOSet();
                Map mp1 = new HashMap();
                for (int j = 0; j < lineDTOSet.getSize(); j++) {
                    String barcode = ((AmsAssetsTransLineDTO) lineDTOSet.getDTO(j)).getBarcode();
                    mp1.put(barcode, barcode);
                }
                for (int i = 0; i < arr.length; i++) {
                    String s = arr[i].replaceAll("'", "");
                    if(!mp1.containsKey(s)){
                        AmsAssetsTransLineDTO dtoa=new AmsAssetsTransLineDTO();
                        //��ѯETS_ITEM_INFO�е�barcode
                        SQLModel queryModel = new SQLModel();
                        queryModel = assetsTransHeaderModel.queryBarcode(s);
                        SimpleQuery qeuryQuery = new SimpleQuery(queryModel, conn);
                        qeuryQuery.executeQuery();
                        RowSet rs=qeuryQuery.getSearchResult();
                        int n=Integer.parseInt(rs.getRowValues().get(0).toString().substring(1, 2));
                        String erroMsg="";
                        if (n==0) {
                      	  erroMsg="ϵͳ�в����ڸ�����";
                        }else{
                      	  erroMsg="�����벻��������Ȩ�޷�Χ";
                        }
                        String excelLineId=String.valueOf(i+2);
                        dtoa.setBarcode(s);
                        dtoa.setErrorMsg(erroMsg);
                        dtoa.setExcelLineId(excelLineId);
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
                list.add(0, "1��ϵͳ�п��ܲ����ڸ�����");
                list.add(1, "2����������ܲ����ڱ���˾");
                list.add(2, "3����������ܲ���������Ȩ�޷�Χ");
                list.add(3, "4����������ܴ����ڵ�����(�̵㡢���������ϵ�)");

                req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
                req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, lineDTOSet);
                req.setAttribute(AssetsWebAttributes.PRIVI_DATA, lineDTOSetALL);
                req.setAttribute("REMARK_LIST", list);
//                forwardURL = AssetsURLList.ASSETS_ALLOCATION_EDIT;
                forwardURL = "/servlet/com.sino.sinoflow.servlet.NewCase?sf_appName=" +headerDTO.getSf_appName() + "&act=&fromExcel=Y" ;
//                forwardURL = "/newasset/itemAllocationEdit.jsp";
            } else if (action.equals(AMSActionConstant.VALIDATE_ACTION)) {//��֤barcode�Ƿ����
                res.setContentType("text/html;charset=GBK");
                PrintWriter out = res.getWriter();
                String fromGroup = req.getParameter("fromGroup");
                boolean result = false;
                if (fromGroup != null && !fromGroup.equals("")) {
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
        } catch (Throwable ex) {
            Logger.logError(ex);
            message = getMessage("");
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
     *
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
//        dto.setUserDeptName(user.getDeptName());
        dto.setUserDeptName(dto.getUserDeptName());
        DTOSet assetsGroups = user.getUserGroups();
        if (assetsGroups.getSize() == 1) {
            SfGroupDTO sfGRoup = (SfGroupDTO) assetsGroups.getDTO(0);
            dto.setFromGroup(sfGRoup.getGroupId());
            dto.setFromGroupName(sfGRoup.getGroupname());
            dto.setGroupProp(sfGRoup.getGroupProp());
        }
        dto = fillOptions(dto, user, conn);
        return dto;
    }

    /**
     * ���ܣ������ʲ���Դ��������������
     *
     * @param dto  AmsAssetsTransHeaderDTO
     * @param user SfUserDTO
     * @param conn Connection
     * @return AmsAssetsTransHeaderDTO
     * @throws QueryException
     */
    private AmsAssetsTransHeaderDTO fillOptions(AmsAssetsTransHeaderDTO dto, SfUserDTO user, Connection conn) throws QueryException {
        FlexValueUtil flexUtil = new FlexValueUtil(user, conn);
        dto.setTransTypeValue(flexUtil.getFlexValue(AssetsDictConstant.ORDER_TYPE_ASSETS, dto.getTransType()));
        AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
//        String fromDept = dto.getFromDept();
        String fromDept = StrUtil.nullToString(dto.getFromDept());
        
        if (StrUtil.isEmpty(fromDept)) {
            fromDept = user.getDeptCode();
        }
        String deptOptions = optProducer.getUserAsssetsDeptOption(fromDept);
        dto.setFromDeptOption(deptOptions);
        if (dto.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP)) {
            String opt = optProducer.getTargetOrganization(user.getOrganizationId(), dto.getToOrganizationId());
            dto.setToCompanyOption(opt);
        } else {
            if (dto.getTransId().equals("")) {
                dto.setToOrganizationId(user.getOrganizationId());
            }
        }
        String transOption = optProducer.getTransferFaOption(dto.getFaContentCode(), dto.getTransferType());
        dto.setFaContentOption(transOption);
        String option = optProducer.getItemContentOption(dto.getFaContentCode());
        dto.setFaContentOption(option);       
        return dto;
    }
}