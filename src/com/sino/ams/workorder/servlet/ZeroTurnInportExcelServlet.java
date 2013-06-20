package com.sino.ams.workorder.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iplanet.dpro.session.Session;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.ZeroTurnHeaderDAO;
import com.sino.ams.workorder.dto.ZeroTurnHeaderDTO;
import com.sino.ams.workorder.dto.ZeroTurnLineDTO;
import com.sino.ams.workorder.model.ZeroTurnModel;
import com.sino.ams.workorder.util.ReadZeroTurnExcel;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

public class ZeroTurnInportExcelServlet extends BaseServlet {

	private static final long serialVersionUID = -8615679481878506637L;
	
    private static final int startRowNum = 1;
    private static final int columnNum = 31;

    /***
     * �������⣺
     * 1.
     * 
     */
	@Override
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		SfUserDTO user = (SfUserDTO) getUserAccount(req);
		Request2DTO req2DTO = new Request2DTO();
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		HttpSession session = req.getSession(); 
//		session.GetAttribute(AssetsWebAttributes.ORDER_LINE_DATA, null);
		try {
			req2DTO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
			ZeroTurnHeaderDTO dto = (ZeroTurnHeaderDTO) req2DTO.getDTO(req);
			ServletConfigDTO servletConfig = getServletConfig(req);
			int organaztionId=user.getOrganizationId();
			conn = getDBConnection(req);
			if (!dto.getApp_dataID().equals("")) {
				dto.setTransId(dto.getApp_dataID());
			}
			ZeroTurnHeaderDAO headerDAO = new ZeroTurnHeaderDAO(user, dto, conn);
			headerDAO.setServletConfig(servletConfig);
			
			AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String option = optProducer.getAmsEmergentLevel(dto.getEmergentLevel());
            dto.setEmergentLevelOption(option);
            
//            int groupId = StrUtil.strToInt(req.getParameter("groupId"));
//    		String groupName = StrUtil.nullToString(req.getParameter("groupName"));
    		
			if(action.equals("")){
				
				forwardURL ="/workorder/zeroTurnInportExcel.jsp";
				
			
			}else if (action.equals("insertData")){
				boolean fg=true;
				try {
					
				
//				req2DTO.setDTOClassName(ZeroTurnLineDTO.class.getName());
//				req2DTO.setIgnoreFields(ZeroTurnHeaderDTO.class);
//				DTOSet orderLines = req2DTO.getDTOSet(req);
//				DTOSet lineDs = (DTOSet)headerDAO.getAllExcelData();
				DTOSet lineDs=(DTOSet) session.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
				headerDAO.insertEtsItem(lineDs);
				forwardURL ="/workorder/zeroTurnInportExcel.jsp";
				if(fg=true){
					message = new Message();
                    message.setMessageValue("�������ݳɹ���");
                    message.setIsError(false);
				}
				} catch (Exception e) {
					message = new Message();
                    message.setMessageValue("��������ʧ�ܣ�");
                    message.setIsError(true);
				}
			}else if (action.equals("queryData")){
				headerDAO.setDTOClassName(ZeroTurnLineDTO.class.getName());
				DTOSet lineDs = (DTOSet)headerDAO.getAllExcelData();
				req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA,lineDs);
//				BaseSQLProducer sqlProducer = new ZeroTurnModel(user, dto);
//				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
//				pageDAO.setCalPattern(CalendarConstant.LINE_PATTERN);           //
//				pageDAO.produceWebData();
				forwardURL ="/workorder/zeroTurnInportExcel.jsp";
			}else if (action.equals("excel")) {
				 ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) req.getAttribute(AssetsWebAttributes.ZERO_TURN_DATA);
				 if (headerDTO == null) {
	                    try {
							headerDTO = fillData(dto, user, conn);
						} catch (CalendarException e) {
							e.printStackTrace();
						}
				 }
				 ZeroTurnModel turnModel = new ZeroTurnModel(user, headerDTO);
                 String excel2 = req.getParameter("excel");
                 DTOSet dtoSet = null;
                 DTOSet dtoSet_old = new DTOSet();
                 DTOSet dtoSet2 = new DTOSet();
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
	                boolean fg=true;
	                //����EXCEL�ɹ�
	                if (!parseError) {
						try {
							headerDAO.setDTOParameter(dto);
							fg=headerDAO.validateImport(dtoSet);
							if (!fg) {
							    message = new Message();
			                    message.setMessageValue("����EXCEL��������,��鿴������ʾ");
			                    message.setIsError(true);
							}else {
								
//							//ɾ����ETS_ITEM_TURN����[��˵��ɾ��]
//							SQLModel delSqlModel = new SQLModel();
//							delSqlModel=turnModel.getImpTurnDeleteModel();
//							DBOperator.updateRecord(delSqlModel, conn);
							//׼�����ݵ�ETS_ITEM_TURN
							Collection<ZeroTurnLineDTO> existUnitC=new ArrayList<ZeroTurnLineDTO>();
							Collection<ZeroTurnLineDTO> noExitC=new ArrayList<ZeroTurnLineDTO>();
							for (int i = 0; i < dtoSet.getSize(); i++) {
								ZeroTurnLineDTO  lineDTO=(ZeroTurnLineDTO) dtoSet.getDTO(i);
								String costCenterCode=lineDTO.getCostCenterCode();//�ɱ�����
								String objectNo = lineDTO.getObjectNo();//�ص���
								String contentCode=lineDTO.getContentCode();
								String assetsDescription =lineDTO.getAssetsDescription();
								String itemSpec=lineDTO.getItemSpec();
								String unitOfMeasure=lineDTO.getUnitOfMeasure();
								String manufacturerName=lineDTO.getManufacturerName();
								String manufactureId=headerDAO.getManufactuerId(manufacturerName);
								lineDTO.setManufacturerId(manufactureId);
								String companyCode=lineDTO.getCompanyCode();//��˾���룻
								
								String barcode=lineDTO.getBarcode();//�ʲ���ǩ��
								if (barcode.equals("")) {
									String newCode=headerDAO.getBarcode(companyCode);
									lineDTO.setBarcode(newCode);
								}
								
								String responsibilityDept=lineDTO.getResponsibilityDept();
								String deptCode=headerDAO.getDeptCode(responsibilityDept);
//								lineDTO.setResponsibilityDept(deptCode);//���β���
								
								String address=headerDAO.getAddressId(objectNo,companyCode);
								lineDTO.setAddressId(address);
								
								String itemCode="";
								itemCode=headerDAO.getItemCode(contentCode, assetsDescription, itemSpec, unitOfMeasure, organaztionId);
								lineDTO.setItemCode(itemCode);
								
								SQLModel queryModel = new SQLModel();
								queryModel = turnModel.getUnitMaager(costCenterCode);
								SimpleQuery qeuryQuery = new SimpleQuery(queryModel, conn);
								qeuryQuery.executeQuery();
								RowSet rs = qeuryQuery.getSearchResult();
								//һ��һ���ɱ�����ֻ��һ����λ�ʲ�����Ա
								if (rs.getSize()==1) {
									String str =rs.getRowValues().get(0).toString();
									int index=str.indexOf(",");
									String str1=str.substring(1,index);
									String str2=str.substring(index+1, str.length()-1);
									lineDTO.setUnitId(str1);
									lineDTO.setUnitManager(str2);
									existUnitC.add(lineDTO);
								}else{
									noExitC.add(lineDTO);
								}
								
								//�������ݵ���ʱ��
								SQLModel insertSqlModel = new SQLModel();
								lineDTO.setCreatedBy(user.getUserId());
								insertSqlModel=turnModel.insertEtsItemTurn(lineDTO);
								
								DBOperator.updateRecord(insertSqlModel, conn);
								dtoSet_old.addDTO(lineDTO);
							}
//							dtoSet.clearData();
//							dtoSet=dtoSet_old;
							//�������
							 if (noExitC.size()>0) {
								for (ZeroTurnLineDTO zeroTurnLineDTO : noExitC) {
									dtoSet2.addDTO(zeroTurnLineDTO);
								}
							 }
							 if (existUnitC.size()>0) {
								 for (ZeroTurnLineDTO zeroTurnLineDTO : existUnitC) {
									 dtoSet2.addDTO(zeroTurnLineDTO);
								 }
							 }
					  }
							 
						} catch (DataHandleException e1) {
							message = new Message();
		                    message.setMessageValue("����EXCEL����ʧ��");
		                    message.setIsError(true);
						}
					}
	                headerDTO.setCalPattern(LINE_PATTERN);
	                req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO); // ͷ��Ϣ
	                session.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO); // ͷ��Ϣ
	                if (!fg) {
	                	req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet);
	                	session.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet);
					}else{
						req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet_old);
						session.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet_old);
					}
	                forwardURL ="/workorder/zeroTurnInportExcel.jsp";
			}else if(action.equals(AssetsActionConstant.EXPORT_ACTION)){
				String barcode="";
				DTOSet ds=(DTOSet) session.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
				for (int i = 0; i < ds.getSize(); i++) {
					ZeroTurnLineDTO ztlDTO=(ZeroTurnLineDTO)ds.getDTO(i);
					barcode=ztlDTO.getBarcode();
					barcode=barcode+",";
				}
				
				File file = headerDAO.getExportFile(barcode);
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
                file.delete();
			}
		} catch (SQLModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataTransException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (DTOException e) {
			e.printStackTrace();

		} catch (WebFileDownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (PoolPassivateException e) {
			e.printStackTrace();

		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContainerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
     * ����:���DTO���� ��ַ��Ϣ����
     */
    private ZeroTurnHeaderDTO fillData(ZeroTurnHeaderDTO dto, SfUserDTO user, Connection conn) throws DTOException, QueryException,
            CalendarException {
        dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD);// ���õ��ݺ�
        dto.setCreatedBy(user.getUserId()); // ���ô�����Id
        dto.setCreatedByName(user.getUsername());
        dto.setDeptCode(user.getDeptCode());
        dto.setDeptName(user.getDeptName());
        dto.setTransType("�ʲ��㹺ά����"); // ��������
        dto.setOrganizationId(StrUtil.strToInt(user.getCompanyCode()));//��˾����
        dto.setOrganizationName(user.getCompany());//��˾����
        dto.setCurrCreationDate();
        //dto.setIsCreated("");//�Ƿ��Ѿ������˹�����Ĭ��Ϊû��
        DTOSet assetsGroups = user.getUserGroups();
        if (assetsGroups.getSize() == 1) {
            SfGroupDTO sfGRoup = (SfGroupDTO) assetsGroups.getDTO(0);
            dto.setGroupId(sfGRoup.getGroupId());
            dto.setGroupName(sfGRoup.getGroupname());
            //dto.setGroupProp(sfGRoup.getGroupProp());
        }
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
            ReadZeroTurnExcel xlsUtil = new ReadZeroTurnExcel();
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
    
    
}

