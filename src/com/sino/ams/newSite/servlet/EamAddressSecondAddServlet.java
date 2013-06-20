package com.sino.ams.newSite.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newSite.dao.EamAddressSecondAddHeaderDAO;
import com.sino.ams.newSite.dto.EamAddressAddHDTO;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.newSite.model.EamAddressSecondAddHModel;
import com.sino.ams.newSite.util.ReadAddressSecondExcel;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dao.EtsOuCityMapDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.WebPageView;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.ConvertUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 12, 2011 11:57:18 AM ��˵��:�����ص�����
 */
public class EamAddressSecondAddServlet extends BaseServlet {

    private static final int startRowNum = 1;
    private static final int columnNum = 8;

    public void performTask(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            RequestParser reqPar = new RequestParser();
            reqPar.transData(req);

            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(EamAddressAddHDTO.class.getName());
            EamAddressAddHDTO dto = (EamAddressAddHDTO) req2DTO.getDTO(req);
            String action = dto.getAct();
            ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(req);
            conn = getDBConnection(req);
            if (!dto.getApp_dataID().equals("")) {
                dto.setTransId(dto.getApp_dataID());
            }
            EamAddressSecondAddHeaderDAO headerDAO = new EamAddressSecondAddHeaderDAO(user, dto, conn);
            headerDAO.setServletConfig(servletConfig);

            AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String option = optProducer.getAmsEmergentLevel(dto.getEmergentLevel());
            dto.setEmergentLevelOption(option);

            if (action.equals("") || action.equals(AssetsActionConstant.NEW_ACTION)) { // 0.����ҳ
                headerDAO.setDTOClassName(EamAddressAddHDTO.class.getName());
                EamAddressAddHDTO headerDTO = (EamAddressAddHDTO) headerDAO.getDataByPrimaryKey();
                if (headerDTO == null) {
                    headerDTO = dto;
                    headerDTO = fillData(headerDTO, user, conn);
                }
                
                EtsObjectDTO etsObjectDTO = new EtsObjectDTO();
                etsObjectDTO.setObjCategoryOption(optProducer.getObjectCategory2Option(""));
    			etsObjectDTO.setCityOption(optProducer.getCity2Option(ConvertUtil.int2String(dto.getOrganizationId())));
    			etsObjectDTO.setAreaTypeOption(optProducer.getDictOption("ADDR_AREA_TYPE", ""));
                
                //headerDTO.setAddrMaintainTypeOption(optProducer.getDictOption("ADDR_MAINTAIN_TYPE","")); //ά������
                
                headerDTO.setEmergentLevelOption(optProducer.getAmsEmergentLevel(headerDTO.getEmergentLevel()));
                headerDTO.setCalPattern(LINE_PATTERN);
                DTOSet lineDs = headerDAO.getLineData();
                
                req.setAttribute("EtsObjectDTO", etsObjectDTO);
                req.setAttribute(AssetsWebAttributes.ADDRESS_HEAD_DATA, headerDTO); // ͷ��Ϣ
                req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, lineDs);
                forwardURL = "/newasset/newSite/eamAddressSecondAddEdit.jsp";
            } else if (action.equals(AssetsActionConstant.IMPORT_ACTION)) {
                dto.setTransStatus(AssetsDictConstant.SAVE_TEMP);
                String excel2 = req.getParameter("excel");
                DTOSet dtoSet = null;
                boolean parseError = false;
                try {
                    dto.setExcel(excel2);
                    dtoSet = readDataFrmExcel(req);
                } catch (Throwable ex) {
                    message = new Message();
                    message.setMessageValue("����Excel����ʧ��");
                    message.setIsError(true);
                    parseError = true;
                }
                if(!parseError){
                    headerDAO.setDTOParameter(dto);
                    headerDAO.importOrder(dtoSet);                //�ݴ����Ϣ
                    message = headerDAO.getMessage();
                    dto = (EamAddressAddHDTO) headerDAO.getDTOParameter();
                    dtoSet = headerDAO.getImpDS(dto.getTransId());
                }
                
                EtsObjectDTO etsObjectDTO = new EtsObjectDTO();
                etsObjectDTO.setObjCategoryOption(optProducer.getObjectCategory2Option(""));
    			etsObjectDTO.setCityOption(optProducer.getCity2Option(ConvertUtil.int2String(dto.getOrganizationId())));
    			etsObjectDTO.setAreaTypeOption(optProducer.getDictOption("ADDR_AREA_TYPE", ""));
    			req.setAttribute("EtsObjectDTO", etsObjectDTO);
    			
                req.setAttribute(AssetsWebAttributes.ADDRESS_HEAD_DATA, dto); // ͷ��Ϣ
                req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet);
                forwardURL = "/newasset/newSite/eamAddressSecondAddEdit.jsp";
            } else if (action.equals("EMPTY_QUERY_ACTION")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = "/newasset/newSite/eamAddressSecondAddList.jsp";
            } else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
                BaseSQLProducer sqlProducer = new EamAddressSecondAddHModel(user, dto);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
                forwardURL = "/newasset/newSite/eamAddressSecondAddList.jsp";
            } else if (action.equals("EMPTY_LOC_QUERY_ACTION")) {
            	EtsObjectDTO eoDto = new EtsObjectDTO();
            	eoDto.setObjCategoryOption(optProducer.getObjectCategoryOption(""));
            	req.setAttribute(QueryConstant.QUERY_DTO, eoDto);
                forwardURL = "/newasset/newSite/eamAddressSecondQuery.jsp";
            } else if (action.equals("LOC_QUERY_ACTION")) {
            	EtsObjectDTO eoDto = new EtsObjectDTO();
            	EamAddressSecondAddHModel eamAddressSecondAddHModel = new EamAddressSecondAddHModel(user, dto);
            	SQLModel sqlModel = new SQLModel();
            	String objectCategory = req.getParameter("objectCategory");
            	
            	eoDto.setWorkorderObjectCode(req.getParameter("workorderObjectCode"));
            	eoDto.setWorkorderObjectName(req.getParameter("workorderObjectName"));
            	eoDto.setObjectCategory(objectCategory);
            	eoDto.setShareType(req.getParameter("shareType"));
            	eoDto.setStartDate(req.getParameter("startDate"));
            	eoDto.setEndDate(req.getParameter("endDate"));
            	EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(user, null, conn);
        		eoDto.setCompanyCode(etsOuCityMapDAO.getCompanyCodeByOrgId(user.getOrganizationId()));     	
            	sqlModel = eamAddressSecondAddHModel.getEtsObjectLocQueryModel(eoDto);
            	WebPageView pageView = new WebPageView(req, conn);            	
            	pageView.produceWebData(sqlModel); 
            	
            	eoDto.setObjCategoryOption(optProducer.getObjectCategoryOption(objectCategory));
            	req.setAttribute(QueryConstant.QUERY_DTO, eoDto);
            	forwardURL = "/newasset/newSite/eamAddressSecondQuery.jsp";
			} else if (action.equals("LOC_EXPORT_ACTION")) {
				EtsObjectDTO eoDto = new EtsObjectDTO();
				eoDto.setWorkorderObjectCode(req.getParameter("workorderObjectCode"));
            	eoDto.setWorkorderObjectName(req.getParameter("workorderObjectName"));
            	eoDto.setObjectCategory(req.getParameter("objectCategory"));
            	eoDto.setShareType(req.getParameter("shareType"));
            	eoDto.setStartDate(req.getParameter("startDate"));
            	eoDto.setEndDate(req.getParameter("endDate"));
            	EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(user, null, conn);
        		eoDto.setCompanyCode(etsOuCityMapDAO.getCompanyCodeByOrgId(user.getOrganizationId())); 
        		
                File file = exportLocFile(user, dto, eoDto ,conn);
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) {
                File file = exportFile(user, dto, conn);
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals(AssetsActionConstant.SUBMIT_ACTION)) { // 1.�ύ��
                req2DTO.setDTOClassName(EamAddressAddLDTO.class.getName());
                req2DTO.setIgnoreFields(EamAddressAddHDTO.class);
                DTOSet lineSet = req2DTO.getDTOSet(req);
                headerDAO.setDTOParameter(dto);
                boolean Smsg = headerDAO.submitOrder(lineSet);
                message = headerDAO.getMessage();
                dto = (EamAddressAddHDTO) headerDAO.getDTOParameter();
                if (Smsg) {
                    forwardURL = "/servlet/com.sino.ams.newSite.servlet.EamAddressSecondAddServlet?act="
                            + AssetsActionConstant.DETAIL_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                } else {
                    forwardURL = "/servlet/com.sino.ams.newSite.servlet.EamAddressSecondAddServlet?act="
                            + AssetsActionConstant.NEW_ACTION;
                }
            } else if (action.equals(AssetsActionConstant.SAVE_ACTION)) {     //1.�����
                req2DTO.setDTOClassName(EamAddressAddLDTO.class.getName());
                req2DTO.setIgnoreFields(EamAddressAddHDTO.class);
                DTOSet lineSet = req2DTO.getDTOSet(req);
                headerDAO.setDTOParameter(dto);
                boolean Smsg = headerDAO.saveOrder(lineSet);
                dto = (EamAddressAddHDTO) headerDAO.getDTOParameter();
                if (Smsg) {
                    forwardURL = "/servlet/com.sino.ams.newSite.servlet.EamAddressSecondAddServlet?act=";
                    //+ AssetsActionConstant.DETAIL_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                } else {
                    forwardURL = "/servlet/com.sino.ams.newSite.servlet.EamAddressSecondAddServlet?act="
                            + AssetsActionConstant.NEW_ACTION;
                }
            } else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) { // 2.���ύ�ɹ�����ʾҳ��
                headerDAO.setDTOClassName(EamAddressAddHDTO.class.getName());
                EamAddressAddHDTO headerDTO = (EamAddressAddHDTO) headerDAO.getDataByPrimaryKey();
                headerDTO.setCalPattern(LINE_PATTERN);
                DTOSet lineDs = headerDAO.getLineData();

//              EtsObjectDTO etsObjectDTO = new EtsObjectDTO();
//              etsObjectDTO.setObjCategoryOption(optProducer.getObjectCategoryOption(""));
//    			etsObjectDTO.setCityOption(optProducer.getCityOption(ConvertUtil.int2String(dto.getOrganizationId())));
//    			etsObjectDTO.setAreaTypeOption(optProducer.getDictOption("ADDR_AREA_TYPE", ""));
//    			req.setAttribute("EtsObjectDTO", etsObjectDTO);
    			
                req.setAttribute(AssetsWebAttributes.ADDRESS_HEAD_DATA, headerDTO); // ͷ��Ϣ
                req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, lineDs);
                forwardURL = "/newasset/newSite/eamAddressSecondAddQuery.jsp";
            } else if (action.equals("REJECT_ACTION")) { //�˻�����
                headerDAO.rejectOrder();
                message = headerDAO.getMessage();
                forwardURL = "/servlet/com.sino.ams.newSite.servlet.EamAddressSecondAddServlet";
                forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                forwardURL += "&transId=" + dto.getTransId();
            } else if (action.equals(AssetsActionConstant.CANCEL_ACTION)) { //�����ݴ�ĵ���
                boolean operateResult = headerDAO.cancelOrder();
                message = headerDAO.getMessage();
                forwardURL = "/servlet/com.sino.ams.newSite.servlet.EamAddressSecondAddServlet";
                if (operateResult) {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                } else {
                    forwardURL += "?act=";
                }
                forwardURL += "&transId=" + dto.getTransId();
            } else if (action.equals("VALIDATE_OBJECT_NAME")) {
				res.setContentType("text/html;charset=GBK");
				PrintWriter out = res.getWriter();
				EamAddressAddLDTO lineDto = new EamAddressAddLDTO();
				String objectName = java.net.URLDecoder.decode(req.getParameter("workorderObjectName").toString(),"UTF-8");
				lineDto.setWorkorderObjectName(objectName);
				boolean isExist = headerDAO.hasLocName(lineDto);
				boolean isExistInOrder = headerDAO.hasLocNameInOrder(lineDto);
				if (isExist) {
					out.print("Y");
				} else if (isExistInOrder) {
					out.print("N");
				} else {
					out.print("");
				}
			} else if (action.equals("VALIDATE_BTSNO")) {
				res.setContentType("text/html;charset=GBK");
				PrintWriter out = res.getWriter();
				String btsNo = req.getParameter("btsNo");
				boolean isExist = headerDAO.existBtsNo(btsNo);
				if (isExist) {
					out.print("Y");
				} else {
					out.print("N");
				}
			} else if (action.equals("CHANGE_COUNTYS")) {
				String city = java.net.URLDecoder.decode(req.getParameter("city").toString(),"UTF-8");
				String countyOptions = optProducer.getOuCounty2Options(city,"");
				res.setContentType("text/html;charset=GBK");
				PrintWriter out = res.getWriter();
				out.print(countyOptions);
				out.close();
			}
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (Throwable e) {
            Logger.logError(e);
        } finally {
            DBManager.closeDBConnection(conn);
            this.setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }

    /**
     * ����:���DTO���� ��ַ��Ϣ����
     */
    private EamAddressAddHDTO fillData(EamAddressAddHDTO dto, SfUserDTO user, Connection conn) throws DTOException, QueryException,
            CalendarException {
        dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD); // ���õ��ݺ�
        dto.setCreatedBy(user.getUserId()); // ���ô�����Id
        dto.setCreatedByName(user.getUsername());
        dto.setDept(user.getDeptCode());
        dto.setDeptName(user.getDeptName()); // ��������
        dto.setTransType("�ʲ�����ص�ά����"); // ��������
        dto.setTransStatus(""); // ����״̬
        dto.setOrganizationId(StrUtil.strToInt(user.getCompanyCode())); // ��˾Id
        dto.setOrganizationName(user.getCompany()); // ��˾����
        dto.setCurrCreationDate();
        return dto;
    }


    /**
     * ���ܣ���ȡExcel���ݵ�DTOSet
     *
     * @param req ҳ���������
     * @return DTOSet����
     * @throws DTOException
     */
    private DTOSet readDataFrmExcel(HttpServletRequest req) throws DTOException {
        DTOSet dtoSet = null;
        try {
            String fileName = req.getParameter("excelPath");     //�ļ�·��
            ReadAddressSecondExcel xlsUtil = new ReadAddressSecondExcel();
            xlsUtil.setFileName(fileName);
            xlsUtil.setNumberOfColumn(columnNum);      //��������8
            xlsUtil.setStartRowNum(startRowNum);       //��ʼ��
            dtoSet = xlsUtil.readXls(0);
        } catch (IOException ex) {
            Logger.logError(ex);
            throw new DTOException(ex);
        }
        return dtoSet;
    }

    public File exportFile(SfUserDTO user, EamAddressAddHDTO dto, Connection conn) throws DataTransException {
        File file = null;
        try {
            DataTransfer transfer = null;
            BaseSQLProducer sqlProducer = new EamAddressSecondAddHModel(user, dto); //OrderQueryModel
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String transType = StrUtil.nullToString(dto.getTransType());
            String fileName = "";
            fileName = "�ʲ�����ص�ά�����ݱ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();

            fieldMap.put("TRANS_NO", "����ص�ά������");
            fieldMap.put("TRANS_STATUS_DESC", "����״̬");
            fieldMap.put("ORGANIZATION_NAME", "����ص�ά����˾");
            fieldMap.put("DEPT_NAME", "����ص�ά������");
            fieldMap.put("CREATED_BY_NAME", "������");
            fieldMap.put("CREATION_DATE", "��������");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(user.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            //���÷�ҳ��ʾ
            TransferFactory factory = new TransferFactory();
            transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }
    
    public File exportLocFile(SfUserDTO user, EamAddressAddHDTO dto,EtsObjectDTO eoDto, Connection conn) throws DataTransException {
        File file = null;
        DataTransfer transfer = null;
		EamAddressSecondAddHModel eamAddressSecondAddHModel = new EamAddressSecondAddHModel(user, dto);
		SQLModel sqlModel = eamAddressSecondAddHModel.getEtsObjectLocQueryModel(eoDto);
		TransRule rule = new TransRule();
		rule.setDataSource(sqlModel);
		rule.setCalPattern(CalendarConstant.LINE_PATTERN);
		rule.setSourceConn(conn);
		String fileName = "";
		fileName = "�ʲ�����ص���Ϣ.xls";
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);

		DataRange range = new DataRange();
		rule.setDataRange(range);

		Map fieldMap = new HashMap();

		fieldMap.put("LOC2_CODE", "����ص����");
		fieldMap.put("LOC2_DESC", "����ص�����");
		fieldMap.put("OBJECT_CATEGORY_NAME", "�ص�רҵ");
		fieldMap.put("CITY_NAME", "����");
		fieldMap.put("COUNTY_NAME", "����");
		fieldMap.put("AREA_TYPE_NAME", "��������");
		fieldMap.put("BTS_NO", "��վ��Ӫҵ�����");
		fieldMap.put("LATITUDE_LONGITUDE", "��γ��");
		fieldMap.put("SHARE_TYPE", "��������");
		fieldMap.put("DISABLE_DATE", "ʧЧ����");
		fieldMap.put("CREATION_DATE", "��������");

		rule.setFieldMap(fieldMap);

		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(fileName);
		custData.setReportPerson(user.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
		//���÷�ҳ��ʾ
		TransferFactory factory = new TransferFactory();
		transfer = factory.getTransfer(rule);
		transfer.transData();
		file = (File) transfer.getTransResult();
        return file;
    }

}
