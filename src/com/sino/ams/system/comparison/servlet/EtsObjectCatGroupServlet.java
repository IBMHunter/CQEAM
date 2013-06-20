package com.sino.ams.system.comparison.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.comparison.dao.EtsObjectCatGroupDAO;
import com.sino.ams.system.comparison.dto.EtsObjectCatGroupDTO;
import com.sino.ams.system.comparison.model.EtsObjectCatGroupModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.util.ConvertUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsObjectCatGroupServlet</p>
 * <p>Description:�����Զ����ɷ������EtsObjectCatGroupServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectCatGroupServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(EtsObjectCatGroupDTO.class.getName());
			EtsObjectCatGroupDTO dtoParameter = (EtsObjectCatGroupDTO)req2DTO.getDTO(req);
			String action = dtoParameter.getAct();
			conn = getDBConnection(req);
			EtsObjectCatGroupDAO etsObjectCatGroupDAO = new EtsObjectCatGroupDAO(user, dtoParameter, conn);
			OptionProducer prd = new OptionProducer(user, conn);
            if (action.equals("")) {
			    String groupOption = prd.getGroupOption(user.getOrganizationId(), "");
                req.setAttribute(WebAttrConstant.GROUP_OPTION, groupOption);
                String objCateOption = prd.getDictOption(DictConstant.OBJECT_CATEGORY, "");
                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_OPTION, objCateOption);
                forwardURL = URLDefineList.GROUP_COMPAR_QUERY;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
			    BaseSQLProducer sqlProducer = new EtsObjectCatGroupModel(user, dtoParameter);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.produceWebData();
                String groupOption = prd.getGroupOption(user.getOrganizationId(), ConvertUtil.int2String( dtoParameter.getGroupId() ) );
                req.setAttribute(WebAttrConstant.GROUP_OPTION, groupOption);
                String objCateOption = prd.getDictOption(DictConstant.OBJECT_CATEGORY, dtoParameter.getObjectCategory() );
                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_OPTION, objCateOption);
                forwardURL = URLDefineList.GROUP_COMPAR_QUERY;
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
//				EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)req.getAttribute("��ȡ��Ϊʧ�ܶ����ֵ����ݣ������ʵ������޸�");
//				if(etsObjectCatGroup == null){
//					etsObjectCatGroup= dtoParameter;//��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.system.comparison.dto.EtsObjectCatGroupDTO�Ĺ��캯��ȷ��
//				}

//                String objCateOption = prd.getDictOption3(DictConstant.OBJECT_CATEGORY, "", false);
//                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_OPTION, objCateOption);
                String groupOption = prd.getGroupOption(user.getOrganizationId(), "");
                req.setAttribute(WebAttrConstant.GROUP_OPTION, groupOption);
                String LeftGroupOption = prd.getLeftGroupOption("", "");
                req.setAttribute(WebAttrConstant.LEFT_CATEGORY_OPTION, LeftGroupOption);
                String RightGroupOption = prd.getRightGroupOption("", "");
                req.setAttribute(WebAttrConstant.RIGHT_CATEGORY_OPTION, RightGroupOption);
                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_DTO, dtoParameter);
//                req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsObjectCatGroup);
				forwardURL = URLDefineList.GROUP_COMPAR_DETAIL;
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				etsObjectCatGroupDAO.setDTOClassName(EtsObjectCatGroupDTO.class.getName());
				EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)etsObjectCatGroupDAO.getDataByPrimaryKey();
				if(etsObjectCatGroup == null){
					etsObjectCatGroup = new EtsObjectCatGroupDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
                String groupOption = prd.getGroupOption(user.getOrganizationId(),ConvertUtil.int2String( etsObjectCatGroup.getGroupId() ) );
                req.setAttribute(WebAttrConstant.GROUP_OPTION, groupOption);

//                String objCateOption = prd.getDictOption3(DictConstant.OBJECT_CATEGORY, etsObjectCatGroup.getObjectCategory(), false);
//                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_OPTION, objCateOption);
                String LeftGroupOption = prd.getLeftGroupOption( ConvertUtil.int2String(  etsObjectCatGroup.getGroupId() ) , ConvertUtil.int2String(  user.getOrganizationId() ) );
                req.setAttribute(WebAttrConstant.LEFT_CATEGORY_OPTION, LeftGroupOption);
                String RightGroupOption = prd.getRightGroupOption( ConvertUtil.int2String(  etsObjectCatGroup.getGroupId() ) , ConvertUtil.int2String(  user.getOrganizationId() ) );
                req.setAttribute(WebAttrConstant.RIGHT_CATEGORY_OPTION, RightGroupOption);
                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_DTO, etsObjectCatGroup);
				forwardURL = URLDefineList.GROUP_COMPAR_DETAIL;
              } else if (action.equals(WebActionConstant.CHECK_ACTION)) {    //onchang����
//                String systemid = req.getParameter("systemid");
                String groupId = req.getParameter("groupId");
                etsObjectCatGroupDAO.setDTOClassName(EtsObjectCatGroupDTO.class.getName());
                EtsObjectCatGroupDTO etsObjectCategory = (EtsObjectCatGroupDTO) etsObjectCatGroupDAO.getDataByPrimaryKey();
                if (etsObjectCategory == null) {
                    etsObjectCategory = new EtsObjectCatGroupDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                String groupOption = prd.getGroupOption(user.getOrganizationId(),groupId);
                req.setAttribute(WebAttrConstant.GROUP_OPTION, groupOption);

                String LeftGroupOption = prd.getLeftGroupOption(groupId, "" + user.getOrganizationId());
                req.setAttribute(WebAttrConstant.LEFT_CATEGORY_OPTION, LeftGroupOption);
                String RightGroupOption = prd.getRightGroupOption(groupId, "" +user.getOrganizationId());
                req.setAttribute(WebAttrConstant.RIGHT_CATEGORY_OPTION, RightGroupOption);

                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_DTO, etsObjectCategory);
//                etsObjectCategoryDAO.createData(objectCategory,codes);
               forwardURL = URLDefineList.GROUP_COMPAR_DETAIL;
            } else if (action.equals(WebActionConstant.CREATE_ACTION)) {
				etsObjectCatGroupDAO.createData();
				forwardURL = URLDefineList.GROUP_COMPAR_QUERY;
			} else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
//				etsObjectCatGroupDAO.updateData();

                String[] codes = req.getParameterValues("codes");
                String groupId = req.getParameter("groupId");
                 etsObjectCatGroupDAO.updateData2(groupId, codes);
//                String cityOption = prd.getAllOrganization("", true);
//                req.setAttribute(WebAttrConstant.CITY_OPTION, cityOption);
//                String objCateOption = prd.getDictOption3(DictConstant.OBJECT_CATEGORY, "", true);
//                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_OPTION, objCateOption);
                String groupOption = prd.getGroupOption(user.getOrganizationId(), "");
                req.setAttribute(WebAttrConstant.GROUP_OPTION, groupOption);
                String objCateOption = prd.getDictOption(DictConstant.OBJECT_CATEGORY, "");
                req.setAttribute(WebAttrConstant.OBJECT_CATEGORY_OPTION, objCateOption);

                forwardURL = URLDefineList.GROUP_COMPAR_QUERY;
			} else if (action.equals(WebActionConstant.DELETE_ACTION)) {
				etsObjectCatGroupDAO.deleteData();
				forwardURL = URLDefineList.GROUP_COMPAR_QUERY;
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
			//�����ʵ�����������Ϣ
			forwardURL = "���ֽ���¼������ݣ����ص�ԭҳ�棬����ʾ�����������Ϣ";
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			//����ʵ������޸�ҳ����ת���롣
		}
	}
}