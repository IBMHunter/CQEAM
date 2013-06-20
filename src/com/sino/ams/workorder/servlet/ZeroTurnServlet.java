package com.sino.ams.workorder.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

public class ZeroTurnServlet extends BaseServlet {

	private static final long serialVersionUID = -8615679481878506637L;
	
    private static final int startRowNum = 1;
    private static final int columnNum = 26;

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
		int organaztionId=user.getOrganizationId();
		Request2DTO req2DTO = new Request2DTO();
		try {
			req2DTO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
			ZeroTurnHeaderDTO dto = (ZeroTurnHeaderDTO) req2DTO.getDTO(req);
			ServletConfigDTO servletConfig = getServletConfig(req);
			String action = dto.getAct();
			conn = getDBConnection(req);
			if (!dto.getApp_dataID().equals("")) {
				dto.setTransId(dto.getApp_dataID());
			}
			ZeroTurnHeaderDAO headerDAO = new ZeroTurnHeaderDAO(user, dto, conn);
			headerDAO.setServletConfig(servletConfig);
			
			AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String option = optProducer.getAmsEmergentLevel(dto.getEmergentLevel());
            dto.setEmergentLevelOption(option);
            
			if (action.equals("")||action.equals(AssetsActionConstant.NEW_ACTION)) {
			   headerDAO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
			   ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) headerDAO.getDataByPrimaryKey();
			   
			   if(headerDTO!=null){
				   String transStatus=headerDTO.getTransStatus();
				   if(transStatus.equals("ALR_FILLING")){
					     DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	                     if (ds == null) {
	                         ds = (DTOSet) headerDAO.getLineData();
	                     }
	                     AssetsOptProducer optProducer2 = new AssetsOptProducer(user, conn);
	                     String option2 = optProducer2.getAmsEmergentLevel(headerDTO.getEmergentLevel());
	                     headerDTO.setEmergentLevelOption(option2);
	                     
	                     headerDTO.setCalPattern(LINE_PATTERN);
	                     req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
	                     req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
	                     forwardURL ="/workorder/zeroTurnApprove.jsp";
				   }else {
					   if (headerDTO==null) {
							  headerDTO=fillData(dto, user, conn);
						  }
						DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
		                if (ds == null) {
		                    ds = (DTOSet) headerDAO.getLineData();
		                }
					  req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
					  req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
					  headerDTO.setCalPattern(LINE_PATTERN);
					  forwardURL ="/workorder/zeroTurnEdit.jsp";
				   }
			   }else{
					if (headerDTO==null) {
						  headerDTO=fillData(dto, user, conn);
					}
					DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	                if (ds == null) {
	                    ds = (DTOSet) headerDAO.getLineData();
	                }
					req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
					req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
					headerDTO.setCalPattern(LINE_PATTERN);
					forwardURL ="/workorder/zeroTurnEdit.jsp";
			   }
			}else if(action.equals("keepSave")){
				 headerDAO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
				 ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) headerDAO.getDataByPrimaryKey();
				 String transStatus=headerDTO.getTransStatus();
				 if(transStatus.equals("ALR_FILLING")){
					 DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                     if (ds == null) {
                         ds = (DTOSet) headerDAO.getLineData();
                     }
                     AssetsOptProducer optProducer2 = new AssetsOptProducer(user, conn);
                     String option2 = optProducer2.getAmsEmergentLevel(headerDTO.getEmergentLevel());
                     headerDTO.setEmergentLevelOption(option2);
                     
                     headerDTO.setCalPattern(LINE_PATTERN);
                     req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
                     req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                     forwardURL ="/workorder/zeroTurnApprove.jsp";
				 }else {
					 if (headerDTO==null) {
						  headerDTO=fillData(dto, user, conn);
					 }
					 DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	                 if (ds == null) {
	                    ds = (DTOSet) headerDAO.getLineData();
	                 }
	                 //headerDTO.setIsCreated("1");//�Ѿ������˵���
					 req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
					 req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
					 headerDTO.setCalPattern(LINE_PATTERN);
					 forwardURL ="/workorder/zeroTurnEdit.jsp";
				 }
			}else if(action.equals(AssetsActionConstant.EDIT_ACTION)){
				headerDAO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
                ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) headerDAO.getDataByPrimaryKey();
                if (headerDTO == null) {
                    headerDTO = fillData(dto, user, conn);
                    req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                }else {
                     DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                     if (ds == null) {
                         ds = (DTOSet) headerDAO.getLineData();
                     }
                     AssetsOptProducer optProducer2 = new AssetsOptProducer(user, conn);
                     String option2 = optProducer2.getAmsEmergentLevel(headerDTO.getEmergentLevel());
                     headerDTO.setEmergentLevelOption(option2);
                     
                     headerDTO.setCalPattern(LINE_PATTERN);
                     req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
                     req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
                     forwardURL ="/workorder/zeroTurnApprove.jsp";
                }
			}else if(action.equals(AssetsActionConstant.SUBMIT_ACTION)){//�ύ��
				
			   String orderExecuter = req.getParameter("orderExecuter");//����ִ����
			   String orderFiler = req.getParameter("orderFiler");//�����鵵��
			   
			   String orderExecuterName = req.getParameter("orderExecuterName");//����ִ����
			   String orderFilerName = req.getParameter("orderFilerName");//�����鵵��
				
				req2DTO.setDTOClassName(ZeroTurnLineDTO.class.getName());
				req2DTO.setIgnoreFields(ZeroTurnHeaderDTO.class);
				ZeroTurnHeaderDTO headerD=new ZeroTurnHeaderDTO();
				ZeroTurnModel turnModel = new ZeroTurnModel(user, headerD);
				DTOSet orderLines = req2DTO.getDTOSet(req);
				//Ŀǰ����ʽ����ô�User��ֱ�ӻ�ȡ��
				dto.setUserId(user.getUserId());
				dto.setOrganizationId(user.getOrganizationId());
				headerDAO.setDTOParameter(dto);
				ZeroTurnLineDTO ztlDTO=(ZeroTurnLineDTO)orderLines.getDTO(0);
				String barcode=ztlDTO.getRecord();//��ǩ��
				
				SQLModel queryModel = new SQLModel();
				queryModel = turnModel.getGroupId(user.getUserId(),barcode);
				SimpleQuery qeuryQuery = new SimpleQuery(queryModel, conn);
				qeuryQuery.executeQuery();
				RowSet rs = qeuryQuery.getSearchResult();
	            int groupId = 0;
	    		String groupName = "";
				//��ȡ���
				if (rs.getSize()>0) {
					Row row = qeuryQuery.getSearchResult().getRow(0);
					groupName=row.getStrValue("GROUP_NAME");
					groupId=Integer.parseInt(row.getStrValue("GROUP_ID"));
				}
				headerDAO.subIn(orderLines,user,groupId,groupName, orderExecuter, orderFiler);
				boolean Smsg=headerDAO.submitOrder(orderLines, orderExecuter,  orderExecuterName, orderFiler, orderFilerName);
                message = headerDAO.getMessage();
                dto = (ZeroTurnHeaderDTO) headerDAO.getDTOParameter();
                forwardURL ="/servlet/com.sino.ams.workorder.servlet.ZeroTurnServlet";
                if (!Smsg) {
                    forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                } else {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    forwardURL += "&transId=" + dto.getTransId();
                }
			}
			else if(action.equals(AssetsActionConstant.SAVE_ACTION)){//�ύ��
				req2DTO.setDTOClassName(ZeroTurnLineDTO.class.getName());
				req2DTO.setIgnoreFields(ZeroTurnHeaderDTO.class);
				DTOSet orderLines = req2DTO.getDTOSet(req);
				//Ŀǰ����ʽ����ô�User��ֱ�ӻ�ȡ��
				dto.setUserId(user.getUserId());
				dto.setOrganizationId(user.getOrganizationId());
				headerDAO.setDTOParameter(dto);
				boolean Smsg=headerDAO.saveOrder(orderLines);
                message = headerDAO.getMessage();
                dto = (ZeroTurnHeaderDTO) headerDAO.getDTOParameter();
                forwardURL ="/servlet/com.sino.ams.workorder.servlet.ZeroTurnServlet";
                if (Smsg) {
                    forwardURL += "?act=" + "keepSave";
                    forwardURL += "&transId=" + dto.getTransId();
                } else {
                	forwardURL += "?act=" + AssetsActionConstant.NEW_ACTION;
                }
			}else if(action.equals(AssetsActionConstant.DETAIL_ACTION)){
				headerDAO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
                ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) headerDAO.getDataByPrimaryKey();
                headerDTO.setCalPattern(LINE_PATTERN);
                DTOSet lineDs = headerDAO.getLineData();
                AssetsOptProducer optProducer2 = new AssetsOptProducer(user, conn);
                String option2 = optProducer2.getAmsEmergentLevel(headerDTO.getEmergentLevel());
                headerDTO.setEmergentLevelOption(option2);
                req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO); // ͷ��Ϣ
                req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, lineDs);
                forwardURL = "/workorder/zeroTurnDetail.jsp";
			}else if (action.equals("REJECT_ACTION")) { //�˻�����
                headerDAO.rejectOrder();
                message = headerDAO.getMessage();
                forwardURL ="/servlet/com.sino.ams.workorder.servlet.ZeroTurnServlet";
                forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                forwardURL += "&transId=" + dto.getTransId();
			}else if (action.equals(AssetsActionConstant.CANCEL_ACTION)) { //�����ݴ�ĵ���
                boolean operateResult = headerDAO.cancelOrder();
                message = headerDAO.getMessage();
                forwardURL = "/servlet/com.sino.ams.workorder.servlet.ZeroTurnServlet";
                if (operateResult) {
                    forwardURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                } else {
                    forwardURL += "?act=";
                }
                forwardURL += "&transId=" + dto.getTransId();
            }
			else if (action.equals("VALIDATE_ACTION")) {
                String transId = StrUtil.nullToString(req.getParameter("transId"));
                String isTrue = headerDAO.validateFilter(transId);
				PrintWriter out = res.getWriter();
//				if (isTrue=="0") {
//					out.print("Y");
//				} else {
//                    out.print("N");
//                }
				out.print(isTrue);
				out.flush();
				out.close();
			}

		} catch (DTOException e) {
			e.printStackTrace();

		} catch (PoolPassivateException e) {
			e.printStackTrace();

		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CalendarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLModelException e) {
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
        dto.setOrganizationId(user.getOrganizationId());//��˾����
        dto.setOrganizationName(user.getOrganization());//��˾����
//        dto.setOrganizationId(StrUtil.strToInt(user.getCompanyCode()));//��˾����
//        dto.setOrganizationName(user.getCompany());//��˾����
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
    
    /*else if (action.equals("excel")) {
	 ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) req.getAttribute(AssetsWebAttributes.ZERO_TURN_DATA);
	 if (headerDTO == null) {
           headerDTO = fillData(dto, user, conn);
	 }
	 ZeroTurnModel turnModel = new ZeroTurnModel(user, headerDTO);
    String excel2 = req.getParameter("excel");
    DTOSet dtoSet = null;
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
				
//				//ɾ����ETS_ITEM_TURN����[��˵��ɾ��]
//				SQLModel delSqlModel = new SQLModel();
//				delSqlModel=turnModel.getImpTurnDeleteModel();
//				DBOperator.updateRecord(delSqlModel, conn);
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
					
					String barcode=lineDTO.getBarcode();//�ʲ���ǩ��
					if (barcode.equals("")) {
						String newCode=headerDAO.getBarcode();
						lineDTO.setBarcode(newCode);
					}
					
					String responsibilityDept=lineDTO.getResponsibilityDept();
					String deptCode=headerDAO.getDeptCode(responsibilityDept);
					lineDTO.setResponsibilityDept(deptCode);//���β���
					
					String address=headerDAO.getAddressId(objectNo);
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
					insertSqlModel=turnModel.insertEtsItemTurn(lineDTO);
					DBOperator.updateRecord(insertSqlModel, conn);
					
				}
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
				 
				 
				 
					if (dtoSet2!=null) {
						if (!dtoSet2.isEmpty()) {
							//��ͷ�Ĳɹ����š�Ĭ�ϵ���������ݲɹ����Ŷ���һ���ġ�
							ZeroTurnLineDTO  lDTO=(ZeroTurnLineDTO) dtoSet2.getDTO(0);
							String orderNo=lDTO.getProcureCode();
							headerDTO.setOrderNo(orderNo);
							headerDTO.setTransStatus("unknow");
							for (int i = 0; i < dtoSet2.getSize(); i++) {
								try {
									ZeroTurnLineDTO  lineDTO=(ZeroTurnLineDTO) dtoSet2.getDTO(i);
									String procureCode=lineDTO.getProcureCode();//�ɹ�����
									String costCenterCode=lineDTO.getCostCenterCode();//�ɱ�����
									//�Զ����ɽ��ӵ���
									String zeroNo=procureCode+costCenterCode;
									lineDTO.setZeroNo(zeroNo);//���ӵ���
									
									//����&�ύ��������
									SQLModel insertSqlModel = new SQLModel();
									insertSqlModel=turnModel.insertEtsItemInfo(lineDTO);
									DBOperator.updateRecord(insertSqlModel, conn);
								} catch (DataHandleException e) {
									message = new Message();
				                    message.setMessageValue("���ɹ���ʧ�ܣ�");
				                    message.setIsError(true);
								}
							}
							
							 //�ύ�����㹺����ʱ�Ĳ���
							 //boolean falg=true;
							 //for (int i = 0; i < orderLines.getSize(); i++) {
								// ZeroTurnLineDTO dto2=(ZeroTurnLineDTO) orderLines.getDTO(i);
							 //falg=headerDAO.subIn(orderLines,user,groupId,groupName);
							// }//
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
       if (!fg) {
       	req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet);
		}else{
			req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, dtoSet2);
		}
       forwardURL ="/workorder/zeroTurnEdit.jsp";
}*//*else if(action.equals("createTrans")){//����&�ύ����
	req2DTO.setDTOClassName(ZeroTurnLineDTO.class.getName());
	req2DTO.setIgnoreFields(ZeroTurnHeaderDTO.class);
	DTOSet orderLines = req2DTO.getDTOSet(req);
	ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) req.getAttribute(AssetsWebAttributes.ZERO_TURN_DATA);
   if (headerDTO==null) {
	    headerDTO=fillData(dto, user, conn);
   }
   ZeroTurnModel ztModel = new ZeroTurnModel(user, headerDTO);
	if (orderLines!=null) {
		if (!orderLines.isEmpty()) {
			//��ͷ�Ĳɹ����š�Ĭ�ϵ���������ݲɹ����Ŷ���һ���ġ�
			ZeroTurnLineDTO  lDTO=(ZeroTurnLineDTO) orderLines.getDTO(0);
			String orderNo=lDTO.getProcureCode();
			headerDTO.setOrderNo(orderNo);
			headerDTO.setTransStatus("unknow");
			for (int i = 0; i < orderLines.getSize(); i++) {
//				try {
					ZeroTurnLineDTO  lineDTO=(ZeroTurnLineDTO) orderLines.getDTO(i);
					String procureCode=lineDTO.getProcureCode();//�ɹ�����
					String costCenterCode=lineDTO.getCostCenterCode();//�ɱ�����
					//�Զ����ɽ��ӵ���
					String zeroNo=procureCode+costCenterCode;
					lineDTO.setZeroNo(zeroNo);//���ӵ���
					
//					//����&�ύ��������
//					SQLModel insertSqlModel = new SQLModel();
//					insertSqlModel=ztModel.insertEtsItemInfo(lineDTO);
//					DBOperator.updateRecord(insertSqlModel, conn);
//				} catch (DataHandleException e) {
//					message = new Message();
//                   message.setMessageValue("���ɹ���ʧ�ܣ�");
//                   message.setIsError(true);
//				}
			}
			
		}
	}
   req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
   headerDTO.setCalPattern(LINE_PATTERN);
   req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, orderLines);
   forwardURL ="/workorder/zeroTurnEdit.jsp";
}*//*else if (action.equals(WebActionConstant.QUERY_ACTION)) {
    headerDAO.setDTOClassName(ZeroTurnHeaderDTO.class.getName());
    ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) headerDAO.getDataByPrimaryKey();
	if (headerDTO==null) {
		  headerDTO=fillData(dto, user, conn);
	}
	String costCode=req.getParameter("centerCode");
    DTOSet ds = (DTOSet) req.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    if (ds == null) {
       ds = (DTOSet) headerDAO.getZeroTurnDto();
    }
	req.setAttribute(AssetsWebAttributes.ZERO_TURN_DATA, headerDTO);
	req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
	headerDTO.setCalPattern(LINE_PATTERN);
	forwardURL ="/workorder/zeroTurnEdit.jsp";
}*/
    
    
}

