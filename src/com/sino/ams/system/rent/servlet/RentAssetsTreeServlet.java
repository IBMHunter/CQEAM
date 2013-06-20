package com.sino.ams.system.rent.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsPriviDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.rent.dao.RentAssetsTreeDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: AmsAssetsPriviServlet</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsPriviServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class RentAssetsTreeServlet extends BaseServlet {


	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
			AmsAssetsAddressVDTO dtoParameter = (AmsAssetsAddressVDTO) req2DTO.getDTO(req);
			conn = getDBConnection(req);
			RentAssetsTreeDAO treeDAO = new RentAssetsTreeDAO(user, dtoParameter, conn);
			ServletConfigDTO servletConfig = getServletConfig(req);
			treeDAO.setServletConfig(servletConfig);
			String treeCategory = dtoParameter.getTreeCategory();
			String tree = "";
			String[] priviDepts = null;
//			AmsRentPriviDAO priviDAO = new AmsRentPriviDAO(user, null, conn);
			AmsAssetsPriviDAO priviDAO = new AmsAssetsPriviDAO(user, null, conn);
			priviDAO.setServletConfig(servletConfig);
			if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PERSON)){//�����ʲ�
				tree = treeDAO.getPersonalAssetsTree();
//				forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
				forwardURL = "/system/rent/rentAssetsTree.jsp";
				req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
			} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_DEPART)){//�����ʲ�
				priviDepts = priviDAO.getPriviDept();
				if(priviDepts == null){
					message = getMessage(AssetsMessageKeys.NOT_DEPT_MANAGER);
					message.setIsError(true);
					message.addParameterValue(user.getCompany());
					forwardURL = MessageConstant.MSG_PRC_SERVLET;
				} else {
					tree = treeDAO.getDeptTree(priviDepts);
//					forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
					forwardURL = "/system/rent/rentAssetsTree.jsp";
					req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
				}
			} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_COMPAN)){//��˾�ʲ�
				priviDAO.setServletConfig(servletConfig);
				if(!priviDAO.isCompanyManager()){
					message = getMessage(AssetsMessageKeys.NOT_COMPANY_MANAGER);
					message.setIsError(true);
					message.addParameterValue(user.getCompany());
					forwardURL = MessageConstant.MSG_PRC_SERVLET;
				} else {
					tree = treeDAO.getCompanyTree();
//					forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
					forwardURL = "/system/rent/rentAssetsTree.jsp";
					req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
				}
			} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PROVIN)){//ȫʡ�ʲ�
				priviDAO.setServletConfig(servletConfig);
				if(!priviDAO.isProvinceManager()){
					message = getMessage(AssetsMessageKeys.NOT_PROVINCE_MANAGER);
					message.setIsError(true);
					message.addParameterValue(servletConfig.getProCompanyName());
					forwardURL = MessageConstant.MSG_PRC_SERVLET;
				} else {
					tree = treeDAO.getProvinceTree();
//					forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
					forwardURL = "/system/rent/rentAssetsTree.jsp";
					req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
				}
			} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)){//��ȷ���ʲ�
				tree = treeDAO.getConfirmAssetsTree();
				forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
				req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
			} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)){//�����ʲ�
				tree = treeDAO.getTransferAssetsTree();
				forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
				req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
			} else if (treeCategory.equals(AssetsWebAttributes.LOCATION_TREE_QUERY)){//�ص��ѯ
				tree = treeDAO.getLocationQueryTree();
				forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
				req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
			} else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CUST_QUERY)){//�Զ����ѯ
				tree = treeDAO.getCustomQueryTree();
				forwardURL = AssetsURLList.ASSETS_TREE_PAGE;
				req.setAttribute(AssetsWebAttributes.ASSETS_TREE, tree);
			}
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
    }
}
