package com.sino.ams.system.switches.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.switches.dao.EtsObjectDAO;
import com.sino.ams.system.switches.model.EtsObjectModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
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
 * <p>Title: EtsObjectServlet</p>
 * <p>Description:�����Զ����ɷ������EtsObjectServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String action = req.getParameter("act");
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
			dtoParameter.setCategoryName(etsObjectDAO.getCategoryName());
			OptionProducer optProducer = new OptionProducer(user, conn);
			if (action.equals("")) {
				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				req.setAttribute(WebAttrConstant.ETS_OBJECT_DTO, dtoParameter);
				forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				BaseSQLProducer sqlProducer = new EtsObjectModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
				pageDAO.produceWebData();
				req.setAttribute(WebAttrConstant.ETS_OBJECT_DTO, dtoParameter);
				forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {      //����������
				String patrolOption = optProducer.getDictOption(DictConstant.CHECK_MODLE, StrUtil.nullToString(dtoParameter.getIsall()));
				req.setAttribute(WebAttrConstant.CHECK_OPTION, patrolOption);
				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				req.setAttribute(WebAttrConstant.ETS_OBJECT_DTO, dtoParameter);
				forwardURL = URLDefineList.SWITCHES_DETAIL_PAGE;
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {        //����ϸ����
				etsObjectDAO.setDTOClassName(EtsObjectDTO.class.getName());
				EtsObjectDTO etsObject = (EtsObjectDTO) etsObjectDAO.getDataByPrimaryKey();
				if (etsObject == null) {
					etsObject = new EtsObjectDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				String countyOption = optProducer.getCountyOption(etsObject.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				String patrolOption = optProducer.getDictOption(DictConstant.CHECK_MODLE, StrUtil.nullToString(etsObject.getIsall()));
				req.setAttribute(WebAttrConstant.CHECK_OPTION, patrolOption);
				etsObject.setCategoryName(dtoParameter.getCategoryName());
				req.setAttribute(WebAttrConstant.ETS_OBJECT_DTO, etsObject);
				forwardURL = URLDefineList.SWITCHES_DETAIL_PAGE;
			} else if (action.equals(WebActionConstant.CREATE_ACTION)) {  //��save����
				etsObjectDAO.createData2();
				message = etsObjectDAO.getMessage();

				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				req.setAttribute(WebAttrConstant.ETS_OBJECT_DTO, dtoParameter);
				forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;

			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {    //�޸Ĳ���
				etsObjectDAO.updateData();
				message = etsObjectDAO.getMessage();

				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				req.setAttribute(WebAttrConstant.ETS_OBJECT_DTO, dtoParameter);
				forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;

			} else if (action.equals(WebActionConstant.DELETE_ACTION)) {      //ʧЧ���� ������ʧЧ��
				etsObjectDAO.deleteData();
				message = etsObjectDAO.getMessage();

				forwardURL = URLDefineList.SWITCHES_QUERY_SERVLET;
			} else if (action.equals(AMSActionConstant.INURE_ACTION)) {    // ��ϸҳ����Ч���� ��������Ч��
				etsObjectDAO.inureData();
				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				String cityOption = optProducer.getAllOrganization(0);
				req.setAttribute(WebAttrConstant.CITY_OPTION, cityOption);
				forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;
			} else if (action.equals(AMSActionConstant.DISABLED_ACTION)) {    //��ѯҳ�� ����ʧЧ
				String[] workorderObjectNos = req.getParameterValues("workorderObjectNos");

				etsObjectDAO.disabledData(workorderObjectNos);
				message = etsObjectDAO.getMessage();
				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				String cityOption = optProducer.getAllOrganization(0);
				req.setAttribute(WebAttrConstant.CITY_OPTION, cityOption);
//                forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;
				forwardURL = URLDefineList.SWITCHES_QUERY_SERVLET;
			} else if (action.equals(AMSActionConstant.EFFICIENT_ACTION)) {      //��ѯҳ�� ������Ч
				String[] workorderObjectNos = req.getParameterValues("workorderObjectNos");

				etsObjectDAO.efficientData(workorderObjectNos);
				message = etsObjectDAO.getMessage();
				String countyOption = optProducer.getCountyOption(dtoParameter.getCountyCode());
				req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
				String cityOption = optProducer.getAllOrganization(0);
				req.setAttribute(WebAttrConstant.CITY_OPTION, cityOption);
//                forwardURL = URLDefineList.SWITCHES_QUERY_PAGE;
				forwardURL = URLDefineList.SWITCHES_QUERY_SERVLET;
			} else if (action.equals(WebActionConstant.EXPORT_ACTION)) {      //������Excel
//                etsObjectDAO.setParameter(paTasksDTO);
//                Export2Excel excel = new Export2Excel(conn, req);
//                etsObjectDAO.setExportProp(excel);
//                excel.setFileName("��������");
//                excel.excute();
				File file = etsObjectDAO.exportFile();
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			} else if (action.equals("verifyworkNo")) {                                          //��֤barcode�Ƿ����
				String workorderObjectCode = StrUtil.nullToString(req.getParameter("workorderObjectCode"));
				boolean success = etsObjectDAO.doVerifyWorkNo(workorderObjectCode);
				PrintWriter out = res.getWriter();
				if (success) {
					out.print("Y");
				}
				out.flush();
				out.close();
			} else if (action.equals("verifyObjectNos")) {                                          //��֤�õص����Ƿ�����豸
				String[] workorderObjectNos = req.getParameterValues("workorderObjectNos");
//                 System.out.println("1111111111111");
//                System.out.println("workorderObjectNos=" + workorderObjectNos);
				boolean success = etsObjectDAO.doVerifyWorkBarcode(workorderObjectNos);
				PrintWriter out = res.getWriter();
				if (success) {
					out.print("Y");
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
		} catch (WebFileDownException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			if (!forwardURL.equals("")) {
				forwarder.forwardView(forwardURL);
				//����ʵ������޸�ҳ����ת���롣
			}
		}
	}
}
