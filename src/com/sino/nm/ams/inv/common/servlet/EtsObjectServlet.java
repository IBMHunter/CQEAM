package com.sino.nm.ams.inv.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.nm.ams.inv.common.constant.CommonWebAttrConstant;
import com.sino.nm.ams.inv.common.bean.InvOptionProducer;
import com.sino.nm.ams.inv.common.dao.EtsObjectDAO;
import com.sino.nm.ams.inv.common.model.EtsObjectModel;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>Title: EtsObjectServlet</p>
 * <p>Description:�ֿ�ص�����</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-Yushibo
 * @version 1.0
 */
public class EtsObjectServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String action = req.getParameter("act");

		String[] workorderObjectCodes = req.getParameterValues("workorderObjectCodes");
		action = StrUtil.nullToString(action);
		Connection conn = null;

		try {
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
			EtsObjectDTO dtoParameter = null;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EtsObjectDTO.class.getName());
			dtoParameter = (EtsObjectDTO) req2DTO.getDTO(req);
			conn = getDBConnection(req);
			EtsObjectDAO etsObjectDAO = new EtsObjectDAO(user, dtoParameter, conn);
			InvOptionProducer optProducer = new InvOptionProducer(user, conn);
		
			//�����ɱ������б�
			String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
			req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
			//�ֿ������б�
			String warehouseTypeOption = optProducer.getWarehouseTypeOption(dtoParameter.getObjectCategory());
			req.setAttribute(WebAttrConstant.WAREHOUSE_TYPE_OPTION, warehouseTypeOption);
			//ҵ�������б�
			String busihouseTypeOption = optProducer.getDictOption("INV_BIZ_CATEGORY", dtoParameter.getBusinessCategory());
			req.setAttribute(WebAttrConstant.BUSIHOUSE_TYPE_OPTION, busihouseTypeOption);
			//���������б�
			String areaTypeOption = optProducer.getDictOption("ADDR_AREA_TYPE", dtoParameter.getAreaType());
			req.setAttribute(CommonWebAttrConstant.AREA_TYPE_OPTION, areaTypeOption);

			if (action.equals("")) {
				forwardURL = "/nm/inv/warehouseQuery.jsp";
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new EtsObjectModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				forwardURL = "/nm/inv/warehouseQuery.jsp";
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
				EtsObjectDTO etsObject = (EtsObjectDTO) req.getAttribute(WebAttrConstant.WAREHOUSE_ATTR);
				if (etsObject == null) {
					etsObject = dtoParameter;
				}
				 //��ȡ�������������б�
                String CityName =optProducer.getCityName(String.valueOf(etsObject.getCityId()),true) ;
                req.setAttribute("CITY_NAME",CityName);
                //��ȡ�����������������б�
                String AreaName=optProducer.getAreaName(String.valueOf(etsObject.getAreaId())) ;
                req.setAttribute("AREA_NAME",AreaName);
				req.setAttribute(WebAttrConstant.WAREHOUSE_ATTR, etsObject);
				forwardURL = "/nm/inv/warehouseDetail.jsp";
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				etsObjectDAO.setDTOClassName(EtsObjectDTO.class.getName());
				EtsObjectDTO etsObject = (EtsObjectDTO) etsObjectDAO.getDataByPrimaryKey();

				//�����ɱ������б�
				countyOption = optProducer.getCountyOption(etsObject.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				//�ֿ������б�
				warehouseTypeOption = optProducer.getWarehouseTypeOption(etsObject.getObjectCategory());
				req.setAttribute(WebAttrConstant.WAREHOUSE_TYPE_OPTION, warehouseTypeOption);
				//ҵ�������б�
				busihouseTypeOption = optProducer.getDictOption2("INV_BIZ_CATEGORY", etsObject.getBusinessCategory());
				req.setAttribute(WebAttrConstant.BUSIHOUSE_TYPE_OPTION, busihouseTypeOption);
				//���������б�
				areaTypeOption = optProducer.getDictOption2("ADDR_AREA_TYPE", etsObject.getAreaType());
				req.setAttribute(CommonWebAttrConstant.AREA_TYPE_OPTION, areaTypeOption);
				 //��ȡ�������������б�
                String CityName =optProducer.getCityName(String.valueOf(etsObject.getCityId()),true) ;
                req.setAttribute("CITY_NAME",CityName);
                //��ȡ�����������������б�
                String AreaName=optProducer.getAreaName(String.valueOf(etsObject.getAreaId())) ;
                req.setAttribute("AREA_NAME",AreaName);
				req.setAttribute(WebAttrConstant.WAREHOUSE_ATTR, etsObject);
				req.setAttribute("DETAIL_ACTION", "DETAIL_ACTION");
				forwardURL = "/nm/inv/warehouseDetail.jsp";
			} else if (action.equals("CHECK_CATEGORY_ACTION")) {     //INV_NORMAL�������͵Ĳֿ�һ��OUֻ������һ��
				String objCategory = req.getParameter("objectCategory");
				PrintWriter out = res.getWriter();
				if (!objCategory.equals(DictConstant.INV_NORMAL)) {
					String objectCode = etsObjectDAO.checkCategory(objCategory);
					if (!objectCode.equals("")) {
						out.print(objectCode);
					} else out.print(WebAttrConstant.CATEGORY_NOT_EXIST);
				} else out.print(WebAttrConstant.CATEGORY_NOT_EXIST);
				out.flush();
				out.close();
			} else if (action.equals("CHECK_CODE_ACTION")) {     //INV_NORMAL�������͵Ĳֿ�һ��OUֻ������һ��
				String objCode = req.getParameter("workorderObjectCode");
				PrintWriter out = res.getWriter();
				boolean hasBeen = etsObjectDAO.checkCode(objCode);
				if (hasBeen) out.print(WebAttrConstant.CODE_EXIST);
				else out.print(WebAttrConstant.CODE_NOT_EXIST);
				out.flush();
				out.close();
			}  else if (action.equals("CHECK_FIELDS_ACTION")) {     //һ��OUֻ������һ�������Ǳ�������
				String objCode = req.getParameter("workorderObjectCode");
				String objectCategory = req.getParameter("objectCategory");
				String businessCategory = req.getParameter("businessCategory");
				System.out.println(objectCategory + "   " + businessCategory);
				boolean hasBeen = false;
				PrintWriter out = res.getWriter();
				
				if((objectCategory.equals("71") || objectCategory == "71") && (businessCategory.equals("INV_BIZ_INSTRU") || businessCategory == "INV_BIZ_INSTRU")) {
				
					if("".equals(objCode)){
						hasBeen = etsObjectDAO.checkFields(objectCategory, businessCategory);
					} else {
						hasBeen = etsObjectDAO.checkFields(objCode, objectCategory, businessCategory);
					}
					if (hasBeen) out.print(WebAttrConstant.FIELDS_EXIST);
					else out.print(WebAttrConstant.FIELDS_NOT_EXIST);
				
				} else {
					out.print(WebAttrConstant.FIELDS_NOT_EXIST);
				}
					
				out.flush();
				out.close();
			} else if (action.equals(WebActionConstant.CREATE_ACTION)) {
				String countyCodeMis = req.getParameter("objValue");
				etsObjectDAO.createEoData(countyCodeMis);
				 //��ȡ�������������б�
                String CityName =optProducer.getCityName(String.valueOf(dtoParameter.getCityId()),true) ;
                req.setAttribute("CITY_NAME",CityName);          
                 //��ȡ�����������������б�
                String AreaName=optProducer.getAreaName(String.valueOf(dtoParameter.getAreaId())) ;
                req.setAttribute("AREA_NAME",AreaName);
				message = etsObjectDAO.getMessage();
				req.setAttribute(WebAttrConstant.WAREHOUSE_ATTR, dtoParameter);
				forwardURL = URLDefineList.WINDOW_CLOSE_PAGE;
			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
				String countyCodeMis = req.getParameter("objValue");
				//etsObjectDAO.updateData();
				etsObjectDAO.updateData(countyCodeMis);
				 //��ȡ�������������б�
                String CityName =optProducer.getCityName(String.valueOf(dtoParameter.getCityId()),true) ;
                req.setAttribute("CITY_NAME",CityName);
                //��ȡ�����������������б�
                String AreaName=optProducer.getAreaName(String.valueOf(dtoParameter.getAreaId())) ;
                req.setAttribute("AREA_NAME",AreaName);
				message = etsObjectDAO.getMessage();
				forwardURL = URLDefineList.WINDOW_CLOSE_PAGE;
			} /*else if (action.equals(WebActionConstant.DELETE_ACTION)) {
				boolean operateResult = etsObjectDAO.deleteData();
				message = etsObjectDAO.getMessage();
				message.setIsError(!operateResult);
				forwardURL = URLDefineList.WAREHOUSE_QUERY_PAGE;
			}*/ /*else if (action.equals(WebActionConstant.DELETE_ACTION)) {        //��ϸҳ��ʧЧ����������ʧЧ��
				boolean operateResult = etsObjectDAO.deleteData();
				message = etsObjectDAO.getMessage();
				message.setIsError(!operateResult);
				//�����б�
				countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				//�ֿ������б�
				warehouseTypeOption = optProducer.getWarehouseTypeOption(dtoParameter.getObjectCategory());
				req.setAttribute(WebAttrConstant.WAREHOUSE_TYPE_OPTION, warehouseTypeOption);

				req.setAttribute(WebAttrConstant.WAREHOUSE_ATTR, dtoParameter);
				forwardURL = URLDefineList.WAREHOUSE_DETAIL_PAGE;
			} else if (action.equals(AMSActionConstant.INURE_ACTION)) {    // ��ϸҳ����Ч���� ��������Ч��
				etsObjectDAO.inureData();
				//�����б�
				countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				//�ֿ������б�
				warehouseTypeOption = optProducer.getWarehouseTypeOption(dtoParameter.getObjectCategory());
				req.setAttribute(WebAttrConstant.WAREHOUSE_TYPE_OPTION, warehouseTypeOption);

				req.setAttribute(WebAttrConstant.WAREHOUSE_ATTR, dtoParameter);
				forwardURL = URLDefineList.WAREHOUSE_DETAIL_PAGE;
			}*/
			else if (action.equals(AMSActionConstant.DISABLED_ACTION)) {    //��ѯҳ�� ����ʧЧ
				etsObjectDAO.do_disable(workorderObjectCodes);
				message = etsObjectDAO.getMessage();
				forwardURL = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=" + WebActionConstant.QUERY_ACTION;
			} else if (action.equals(AMSActionConstant.EFFICIENT_ACTION)) {      //��ѯҳ�� ������Ч
				etsObjectDAO.efficientData(workorderObjectCodes);
				message = etsObjectDAO.getMessage();
				forwardURL = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=" + WebActionConstant.QUERY_ACTION;
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
		} catch (DataHandleException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (ContainerException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}

}
