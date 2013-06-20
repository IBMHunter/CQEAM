package com.sino.ams.system.resource.dao;


import java.sql.Connection;

import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.resource.dto.SfResDefineDTO;
import com.sino.ams.system.resource.dto.SfResPrivsDTO;
import com.sino.ams.system.resource.model.SfResPrivsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.TreeConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.NodeException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.treeview.DataBaseTree;
import com.sino.base.treeview.RelateSQLProperty;
import com.sino.base.treeview.StyleProperty;
import com.sino.base.treeview.Tree;
import com.sino.base.treeview.WebPageTree;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: SfResPrivsDAO</p>
 * <p>Description:�����Զ����ɷ������SfResPrivsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfResPrivsDAO extends BaseDAO {
	private SfResPrivsModel sfResPrivsModel = null;
	/**
	 * ���ܣ�SF_RES_PRIVS ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfResPrivsDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfResPrivsDAO(SfUserDTO userAccount, SfResPrivsDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		initSQLProducer(userAccount, dtoParameter);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfResPrivsDTO dtoPara = (SfResPrivsDTO)dtoParameter;
		super.sqlProducer = new SfResPrivsModel((SfUserDTO)userAccount, dtoPara);
		sfResPrivsModel = (SfResPrivsModel)sqlProducer;
	}

	/**
	 * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�
	 * @param selectedResource String ѡ�е���Դ���
	 * @return String
	 * @throws QueryException
	 */
	public String getResourceOption(String selectedResource) throws QueryException {
		StringBuffer resourceOption = new StringBuffer();
		SQLModel sqlModel = sfResPrivsModel.getResourceOptionModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(SfResDefineDTO.class.getName());
		simp.executeQuery();
		DTOSet resources = simp.getDTOSet();
		if(resources != null && !resources.isEmpty()){
			SfResDefineDTO resource = null;
			int dotCount = 0;
			String resourceId = "";
			String resourceName = "";
			resourceOption.append("<option value=\"\">��ѡ��</option>");
			for(int i = 0; i < resources.getSize(); i++){
				resource = (SfResDefineDTO)resources.getDTO(i);
				resourceId = resource.getResId();
				resourceName = resource.getResName();
				dotCount = StrUtil.containNum(resourceId, ".");
				resourceName = StrUtil.getStrByCount("&nbsp;&nbsp;", dotCount)
							   + resourceId
							   + " "
							   + resourceName;
				resourceOption.append("<option value=\"");
				resourceOption.append(resourceId);
				resourceOption.append("\"");
				if(resourceId.equals(selectedResource)){
					resourceOption.append(" selected");
				}
				resourceOption.append(">");
				resourceOption.append(resourceName);
				resourceOption.append("</option>");
			}
		}
		return resourceOption.toString();
	}

	/**
	 * ���ܣ���ȡ���ж������Դ����Ŀ�����á�
	 * @param systemName ϵͳ����
	 * @return String
	 * @throws QueryException
	 */
	public String getTreeHtml(String systemName) throws QueryException {
		StringBuffer treeHtml = new StringBuffer();
		try {
			SQLModel sqlModel = sfResPrivsModel.getResourceTreeModel();
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

			tree.setRootDesc(systemName);
			WebPageTree webTree = new WebPageTree(tree, TreeConstant.SIMPLE_DIV_TREE);
			StyleProperty styleProperty = new StyleProperty();
			styleProperty.setShowParURL(true);
			webTree.setStyleProperty(styleProperty);
			webTree.setTarFrame("right");
			webTree.setUrlPrefix("/servlet/com.sino.ams.system.resource.servlet.SfResPrivsServlet?act=" + WebActionConstant.QUERY_ACTION + "&resId=");
			treeHtml.append(webTree.getTreeDataHtml());
			treeHtml.append(webTree.getPageJs());
		} catch (NodeException ex) {
			ex.printLog();
			throw new QueryException(ex.getMessage());
		}
		return treeHtml.toString();
	}

	/**
	 * ���ܣ���ȡҳ����ʾȨ������
	 * @throws QueryException
	 * @return DTOSet
	 */
	public DTOSet getPageQueryDTOs() throws QueryException {
		DTOSet dtos = new DTOSet();
		try {
			SQLModel sqlModel = sfResPrivsModel.getPageQueryModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.setDTOClassName(SfResPrivsDTO.class.getName());
			simp.executeQuery();
			dtos = simp.getDTOSet();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return dtos;
	}

	/**
	 * ���ܣ���ȡ��һ���˵���Ŀ��
	 * @return SfResDefineDTO
	 * @throws QueryException
	 */
	public SfResDefineDTO getFirstResource() throws QueryException {
		SfResDefineDTO firstResource = new SfResDefineDTO();
		SQLModel sqlModel = sfResPrivsModel.getFirstResourceModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(SfResDefineDTO.class.getName());
		simp.executeQuery();
		firstResource = (SfResDefineDTO)simp.getFirstDTO();
		return firstResource;
	}

	/**
	 * ���ܣ�������Դ��Ż�ȡ��Դ��
	 * @return SfResDefineDTO
	 * @throws QueryException
	 */
	public SfResDefineDTO getResourceById() throws QueryException {
		SfResDefineDTO firstResource = new SfResDefineDTO();
		SQLModel sqlModel = sfResPrivsModel.getResourceByIdModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(SfResDefineDTO.class.getName());
		simp.executeQuery();
		firstResource = (SfResDefineDTO)simp.getFirstDTO();
		return firstResource;
	}

	/**
	 * ���ܣ�������Ŀ����Ȩ�ޡ�
	 * @param resPrivis DTOSet
	 * @return boolean
	 */
	public boolean saveResPrivis(DTOSet resPrivis){
		boolean operateResult = false;
		try {
			SfResPrivsDTO resPrivi = (SfResPrivsDTO) resPrivis.getDTO(0);
			deleteExistPrivi(resPrivi);
			int systemId = resPrivi.getResId();
			DTOSet parentRes = getParentRes(systemId);
			if(parentRes != null){
				int parentCount = parentRes.getSize();
				SfResDefineDTO resource = null;
				DTOSet appendPrivis = new DTOSet();
				for(int i = 0; i < parentCount; i++){
					resource = (SfResDefineDTO)parentRes.getDTO(i);
					systemId = resource.getSystemId();
					SfResPrivsDTO tmpPrivi = null;
					for(int j = 0; j < resPrivis.getSize(); j++){
						tmpPrivi = new SfResPrivsDTO();
						resPrivi = (SfResPrivsDTO)resPrivis.getDTO(j);
						tmpPrivi.setResId(systemId);
						tmpPrivi.setRoleId(resPrivi.getRoleId());
						tmpPrivi.setCreatedBy(resPrivi.getCreatedBy());
						appendPrivis.addDTO(tmpPrivi);
					}
				}
				resPrivis.addDTOs(appendPrivis);
			}
			for(int i = 0; i < resPrivis.getSize(); i++){
				resPrivi = (SfResPrivsDTO)resPrivis.getDTO(i);
				saveResPrivis(resPrivi);
			}
			operateResult = true;
			prodMessage(CustMessageKey.PRIVI_SAVE_SUCCESS);
		} catch (DataHandleException ex) {
			ex.printLog();
			prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
		} catch (QueryException ex) {
			ex.printLog();
			prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
		} catch (DTOException ex) {
			ex.printLog();
			prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
		}
		return operateResult;
	}

	/**
	 * ���ܣ�ɾ��Ȩ�ޡ�
	 * @param resPrivi SfResPrivsDTO
	 * @throws DataHandleException
	 */
	private void deleteExistPrivi(SfResPrivsDTO resPrivi) throws DataHandleException {
		SQLModel sqlModel = sfResPrivsModel.getPriviDeleteModel(resPrivi);
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ�����Ȩ��
	 * @param resPrivi SfResPrivsDTO
	 * @throws DataHandleException
	 */
	private void saveResPrivis(SfResPrivsDTO resPrivi) throws DataHandleException{
		try {
			if (!hasPriviData(resPrivi)) {
				SQLModel sqlModel = sfResPrivsModel.getPriviCreateModel(resPrivi);
				DBOperator.updateRecord(sqlModel, conn);
			}
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}


	/**
	 *
	 * @param resPrivi SfResPrivsDTO
	 * @return boolean
	 * @throws QueryException
	 */
	private boolean hasPriviData(SfResPrivsDTO resPrivi) throws QueryException {
		boolean hasData = false;
		SQLModel sqlModel = sfResPrivsModel.getPriviExistModel(resPrivi);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		hasData = simp.hasResult();
		return hasData;
	}
	/**
	 *
	 * @param systemId String
	 * @return DTOSet
	 * @throws QueryException
	 */
	private DTOSet getParentRes(int systemId) throws QueryException {
		DTOSet parentRes = new DTOSet();
		SQLModel sqlModel = sfResPrivsModel.getParentResModel(systemId);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(SfResDefineDTO.class.getName());
		simp.executeQuery();
		parentRes = simp.getDTOSet();
		return parentRes;
	}
}
