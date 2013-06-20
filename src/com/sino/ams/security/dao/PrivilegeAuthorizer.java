package com.sino.ams.security.dao;

import java.sql.Connection;

import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.security.model.PrivilegeAuthorizerModel;
import com.sino.ams.system.resource.dto.SfResDefineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.TreeConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.NodeException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.treeview.DataBaseTree;
import com.sino.base.treeview.RelateSQLProperty;
import com.sino.base.treeview.StyleProperty;
import com.sino.base.treeview.Tree;
import com.sino.base.treeview.WebPageTree;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class PrivilegeAuthorizer {
	private Connection conn = null;
	private PrivilegeAuthorizerModel authorizeModel = null;

	public PrivilegeAuthorizer(BaseUserDTO userAccount, Connection conn) {
		authorizeModel = new PrivilegeAuthorizerModel(userAccount);
		this.conn = conn;
	}

	/**
	 * ���ܣ���ȡ��ǰ�û���Ȩ�޷��ʵĵ�һ���˵���Ŀ��
	 * @return SfResDefineDTO
	 * @throws QueryException
	 */
	public SfResDefineDTO getFirstAuthorizedResource() throws QueryException {
		SfResDefineDTO firstResource = new SfResDefineDTO();
		DTOSet authorizedResources = getAuthorizedRootResource();
		if(authorizedResources != null && !authorizedResources.isEmpty()){
			firstResource = (SfResDefineDTO)authorizedResources.getDTO(0);
		}
		return firstResource;
	}

	/**
	 * ���ܣ���ȡ��Ȩ��ǰ�û����ʵĸ��˵���Դ��
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getAuthorizedRootResource() throws QueryException {
		SQLModel sqlModel = authorizeModel.getAuthorizeRootModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(SfResDefineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}


	/**
	 * ���ܣ�������ԴID��ȡ����Դ������ҳ����߲˵���Ŀ��ʾ��
	 * @param resourcePid String
	 * @return String
	 * @throws QueryException
	 */
	public String getAuthorizedDeepResource(String resourcePid) throws QueryException {
		StringBuffer treeHtml = new StringBuffer();
		try {
			if (!StrUtil.isEmpty(resourcePid)) {
				SQLModel sqlModel = authorizeModel.getAuthorizeDeepModel(resourcePid);
				RelateSQLProperty sqlProp = new RelateSQLProperty();
				sqlProp.setSqlModel(sqlModel);
				sqlProp.setIdField("RES_ID");
				sqlProp.setPidField("RES_PAR_ID");
				sqlProp.setDescField("RES_NAME");
				sqlProp.setUrlField("RES_URL");
				sqlProp.setPopupField("IS_POPUP");
				sqlProp.setPopscriptField("POPSCRIPT");
				DataBaseTree dbTree = new DataBaseTree(conn, sqlProp);
				Tree tree = dbTree.getTree();
				WebPageTree webTree = new WebPageTree(tree, TreeConstant.SIMPLE_DIV_TREE);
				StyleProperty styleProperty = new StyleProperty();
				styleProperty.setInitExpand(false);
				webTree.setStyleProperty(styleProperty);
				webTree.setTarFrame("main");
				webTree.setUrlPrefix("/servlet/com.sino.ams.bean.URLForwardServlet?resourceId=");
				treeHtml.append(webTree.getTreeDataHtml());
				treeHtml.append(webTree.getPageJs());
			}
		} catch (NodeException ex) {
			ex.printLog();
			throw new QueryException(ex.getMessage());
		}
		return treeHtml.toString();
	}

	/**
	 * ���ܣ����ݵ����URL��ԴID����ȡ��Դ��ϸ��Ϣ��
	 * @param resourceId String
	 * @return SfResDefineDTO
	 * @throws QueryException
	 */
	public SfResDefineDTO getAuthorizedResourceById(String resourceId) throws QueryException {
		SfResDefineDTO authorizedResource = null;
		SQLModel sqlModel = authorizeModel.getAuthResourceByIdModel(resourceId);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(SfResDefineDTO.class.getName());
		simp.executeQuery();
		if(simp.hasResult()){
			authorizedResource = (SfResDefineDTO)simp.getFirstDTO();
		}
		return authorizedResource;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			SfUserDTO user = new SfUserDTO();
			user.setLoginName("XYADMIN");
			user.setPassword("sinoets");
			conn = DBManager.getDBConnection();
			UserLoginDAO loginDAO = new UserLoginDAO(user, conn);
			if (loginDAO.isValidUser()) {
				user = (SfUserDTO)loginDAO.getUserAccount();
				PrivilegeAuthorizer authorizer = new PrivilegeAuthorizer(user,
					conn);
				String resTree = authorizer.getAuthorizedDeepResource("1");
				System.out.println(resTree);
			} else {
				System.out.println("not valid user...");
			}
		} catch (PoolException ex) {
			ex.printLog();
		} finally{
			DBManager.closeDBConnection(conn);
			System.exit(0);
		}
	}
}
