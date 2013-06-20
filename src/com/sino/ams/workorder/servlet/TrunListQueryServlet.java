package com.sino.ams.workorder.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.TrunListQueryDAO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.TrunListQueryModel;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Author:		����
 * Date: 2009-6-4
 * Time: 9:29:22
 * Function:	ת���嵥��ѯServlet
 */
public class TrunListQueryServlet extends BaseServlet {
	@SuppressWarnings("unchecked")
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		boolean bo=false;
		try {
			conn = DBManager.getDBConnection();
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);//��session�л�ȡ���ݣ�����ʵ����������޸ġ�
			EtsWorkorderDTO dtoParameter = null;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EtsWorkorderDTO.class.getName());
			dtoParameter = (EtsWorkorderDTO) req2DTO.getDTO(req);
			int organizationId = StrUtil.isEmpty(dtoParameter.getOrganizationId()) ? user.getOrganizationId() : dtoParameter.getOrganizationId();
			//��֯
			OptionProducer prd = new OptionProducer(user, conn);
			String ouoption = "";
			
			if("82".equals(user.getOrganizationId())){
				if("".equals(dtoParameter.getOrganizationId())){
					ouoption = prd.getAllOrganization(0, true);
				}else{
					ouoption = prd.getAllOrganization(organizationId, true);
				}					
			}else{
				ouoption = prd.getAllOrganization(organizationId);
			}
			req.setAttribute(WebAttrConstant.OU_OPTION, ouoption);
			
			String faOption = this.getFAOption( dtoParameter.getFinanceProp() );
			req.setAttribute("FA_OPTION" , faOption );
			
            String cat = prd.getDictOption(DictConstant.OBJECT_CATEGORY, dtoParameter.getObjectCategory());
			req.setAttribute("CATEGORY", cat);

			SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
			if (action.equals("")) {
				String deptOption = prd.getDeptOptionByOrgId(organizationId, dtoParameter.getDeptCode());//�õ������µ��������ò���
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, deptOption);
				req.setAttribute(QueryConstant.QUERY_DTO,dtoParameter);
				forwardURL = URLDefineList.TRUN_LIST_QUERY;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				String deptOption = prd.getDeptOptionByOrgId(organizationId, dtoParameter.getDeptCode());//�õ������µ��������ò���
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, deptOption);
				req.setAttribute(QueryConstant.QUERY_DTO,dtoParameter);
				BaseSQLProducer sqlProducer = new TrunListQueryModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = URLDefineList.TRUN_LIST_QUERY;
			} else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) {
				TrunListQueryDAO dao = new TrunListQueryDAO(user, dtoParameter, conn);
				File file = dao.getExportFile(dtoParameter);
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			}else if (action.equals("addressQy")) {
				try {
					List addList = new ArrayList();
					req.setAttribute(QueryConstant.QUERY_DTO,dtoParameter);
					TrunListQueryModel trunListQueryModel = new TrunListQueryModel(user, dtoParameter);
					//TrunListQueryDAO dao = new TrunListQueryDAO(user, dtoParameter, conn);
					//��ӵ�ǰ�û�Ҫ�����BARCODE��¼����IMP_BARCODE��
//					SQLModel insertSqlModel = new SQLModel();
					//������Ҫ���ظ��ж�
					String sqlAddressQy = trunListQueryModel.getAddressDataQueryModel(dtoParameter.getPrjId(),dtoParameter.getContentCode());
					Statement statement = null;
					ResultSet set = null; 
					
					try {
						statement = conn.createStatement();
						set =statement.executeQuery(sqlAddressQy); 					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new SQLException(e);
					}   
					Map<String,String> map=new HashMap<String,String>();
					while (set.next()) {
						EtsWorkorderDTO dtoPa  = new EtsWorkorderDTO();
						dtoPa.setContentCode(set.getString("ADDRESS_ID"));//��������1
						dtoPa.setPrjId(set.getString("PROJECTID"));//��������2
						dtoPa.setWorkorderObjectCode(set.getString("WORKORDER_OBJECT_CODE"));//�ص����
						dtoPa.setWorkorderObjectName(set.getString("WORKORDER_OBJECT_NAME"));//�ص�����
						dtoPa.setProjectCode(set.getString("SEGMENT1"));//���̱���
						dtoPa.setProjectName(set.getString("NAME"));//��������
						String workCode=dtoPa.getWorkorderObjectCode();
						String proCode=dtoPa.getProjectCode();
						String key=workCode.trim()+proCode.trim();
						if (!map.containsKey(key)) {
							map.put(key, key);
							addList.add(dtoPa);
						}
						
						
					}
					
					req.setAttribute("addList", addList);
					
//					DBOperator.updateRecord(insertSqlModel, conn);
					forwardURL = "/workorder/trunListAddressQuery.jsp";
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
            }else if (action.equals("CONFIRM")) {
				try {
					TrunListQueryModel trunListQueryModel = new TrunListQueryModel(user, dtoParameter);
					//TrunListQueryDAO dao = new TrunListQueryDAO(user, dtoParameter, conn);
					//��ӵ�ǰ�û�Ҫ�����BARCODE��¼����IMP_BARCODE��
//					SQLModel insertSqlModel = new SQLModel();\
					String str[]= req.getParameterValues("params");
					
					for (int i = 0; i < str.length; i++) {
						String index=str[i];
						String arry[]=index.split("-");
						String prjId=arry[0];
						String contextId=arry[1];
						
						if (prjId!=null&&prjId!=""
							 &&contextId!=null&&contextId!="") {
							//������Ҫ���ظ��ж�
							String sqlInsert = trunListQueryModel.getDataInsertModel(prjId,contextId);
							Statement statement = null;
							try {
								statement = conn.createStatement();
								statement.execute(sqlInsert);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new SQLException(e);
							} 
							
							String sqlDelete = trunListQueryModel.getDelInsertModel(prjId,contextId);
							
							Statement statementDel = null;
							try {
								statementDel = conn.createStatement();
								statementDel.execute(sqlDelete);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new SQLException(e);
							}  
						}
						
						
					}
//					DBOperator.updateRecord(insertSqlModel, conn);
//					forwardURL = "/servlet/com.sino.ams.workorder.servlet.TrunListQueryServlet";
//					forwardURL += "?act=" + "";
					forwardURL = "/workorder/trunListAddressQuery.jsp";
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
            }else{
                message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
            }
		} catch (PoolException e) {
			e.printStackTrace();
		} catch (QueryException e) {
			e.printStackTrace();
		} catch (DTOException e) {
			e.printStackTrace();
		} catch (DataTransException e) {
			e.printStackTrace();
		} catch (WebFileDownException e) {
			e.printStackTrace();
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}
	
	/**
	 * ���ܣ������Ƿ�������
	 * @param selectedValue String
	 * @return String
	 */
	public String getFAOption(String selectedValue) {
		StringBuffer strOpt = new StringBuffer();
		if (selectedValue == null) {
			selectedValue = "";
		} 
		strOpt.append("<option value=\"");
		strOpt.append( "ASSETS" );
		strOpt.append("\"");
		if (selectedValue.equals( "ASSETS" )) {
			strOpt.append(" selected");
		}
		strOpt.append(">�ʲ�</option>");
		strOpt.append("<option value=\"");
		strOpt.append( "PRJ_MTL" );
		strOpt.append("\"");
		if (selectedValue.equals( "PRJ_MTL" )) {
			strOpt.append(" selected");
		}
		strOpt.append(">Ԥת��</option>");
		
		
		//��ȷ��ת���ʲ�
		strOpt.append("<option value=\"");
		strOpt.append( "CFM_PRJ_MTL" );
		strOpt.append("\"");
		if (selectedValue.equals( "CFM_PRJ_MTL" )) {
			strOpt.append(" selected");
		}
		strOpt.append(">ȷ��Ԥת��</option>");
		
		return strOpt.toString();
	}
	
}
