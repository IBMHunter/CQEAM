package com.sino.ams.system.rent.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.rent.model.RentAssetsTreeModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.NodeException;
import com.sino.base.exception.QueryException;
import com.sino.base.treeview.DataBaseTree;
import com.sino.base.treeview.FieldSQLProperty;
import com.sino.base.treeview.Tree;
import com.sino.base.treeview.WebPageTree;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;


/**
 * <p>Title: AmsAssetsPriviServlet</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsPriviServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class RentAssetsTreeDAO extends AMSBaseDAO {
    private String constant = "";
	private String newValue = "";
	/**
	 * ���ܣ��̶������ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsAddressVDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public RentAssetsTreeDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	public void setServletConfig(ServletConfigDTO servletConfig){
		super.setServletConfig(servletConfig);
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel)sqlProducer;
		constant = "a";
		modelProducer.setServletConfig(servletConfig, constant);
		newValue = servletConfig.getProCompanyName();
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO)dtoParameter;
		super.sqlProducer = new RentAssetsTreeModel((SfUserDTO)userAccount, dtoPara);
	}


	/**
	 * ���ܣ���ȡ�����ʲ�̨�˹���ƽ̨���չʾ�����û����������ʲ���
	 * @return String
	 * @throws com.sino.base.exception.QueryException
     */
	public String getPersonalAssetsTree() throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getPersonalTreeModel();
		assetsTree.append(prodAssetsTree(sqlModel));
		return assetsTree.toString();
	}


	/**
	 * ���ܣ���ȡ�û��������Է�δ���յ������ʲ�
	 * @return String
	 * @throws QueryException
	 */
	public String getTransferAssetsTree() throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getTransferTreeModel();
		assetsTree.append(prodAssetsTree(sqlModel));
		return assetsTree.toString();
	}

	/**
	 * ���ܣ���ȡ�����ʲ�̨�˹���ƽ̨���չʾ�������������ʲ�
	 * @param priviDepts String[] ��Ȩ���ʵĲ���
	 * @return String
	 * @throws QueryException
	 */
	public String getDeptTree(String[] priviDepts) throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getDeptTreeModel(priviDepts);
		assetsTree.append(prodAssetsTree(sqlModel));
		return assetsTree.toString();
	}

	/**
	 * ���ܣ���ȡ�����ʲ�̨�˹���ƽ̨���չʾ��������˾�����¸����������ʲ�
	 * @return String
	 * @throws QueryException
	 */
	public String getCompanyTree() throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getCompanyTreeModel();
		assetsTree.append(prodAssetsTree(sqlModel));
		return assetsTree.toString();
	}

	/**
	 * ���ܣ���ȡ�����ʲ�̨�˹���ƽ̨���չʾ����ȫʡ�����ʲ�
	 * @return String
	 * @throws QueryException
	 */
	public String getProvinceTree() throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getProvinceTreeModel();
		assetsTree.append(prodAssetsTree(sqlModel));
		return assetsTree.toString();
	}

	/**
	 * ���ܣ���ȡ�����ʲ�̨�˹���ƽ̨���չʾ�����û����˴�ȷ�������ʲ���
	 * @return String
	 * @throws QueryException
	 */
	public String getConfirmAssetsTree() throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getConfirmTreeModel();
		assetsTree.append(prodAssetsTree(sqlModel));
		return assetsTree.toString();
	}

	/**
	 * ���ܣ����ݲ�ͬ��SQL���조�����ʲ�̨�˹������νṹ
	 * @param sqlModel SQLModel
	 * @return StringBuffer
	 * @throws QueryException
	 */
	private StringBuffer prodAssetsTree(SQLModel sqlModel) throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		try {
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)dtoParameter;
			String treeCategory = dto.getTreeCategory();
			String treeTitle = "";
			if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PERSON)){//���������ʲ�
				treeTitle = AssetsWebAttributes.RENT_ASSETS_PERSON;
			} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_DEPART)){//���������ʲ�
				treeTitle = AssetsWebAttributes.RENT_ASSETS_DEPART;
			} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_COMPAN)){//��˾�����ʲ�
				treeTitle = AssetsWebAttributes.RENT_ASSETS_COMPAN;
			} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PROVIN)){//ȫʡ�����ʲ�
				treeTitle = AssetsWebAttributes.RENT_ASSETS_PROVIN;
//			} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)){//��ȷ�������ʲ�
//				treeTitle = AssetsWebAttributes.ASSETS_CONFIRM;
//			} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)){//���������ʲ�
//				treeTitle = AssetsWebAttributes.ASSETS_TRANSFER;
//			} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_DISCARD)){//���������ʲ�
//				treeTitle = AssetsWebAttributes.ASSETS_DISCARD;
//			} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CLEAR)){//���������ʲ�
//				treeTitle = AssetsWebAttributes.ASSETS_CLEAR;
			}
			FieldSQLProperty sqlProperty = new FieldSQLProperty();
			sqlProperty.setSqlModel(sqlModel);
			DataBaseTree treeBuilder = new DataBaseTree(conn, sqlProperty);
			treeBuilder.setTreeName(treeTitle);
			if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PROVIN)){
				treeBuilder.setReplaceValue(constant, newValue);
			}
//			treeBuilder.setMethod("do_AssetsQuery()");
			Tree tree = treeBuilder.getTree();
			WebPageTree webTree = new WebPageTree(tree);
			webTree.setTarFrame("assetsMain");
//			String url = AssetsURLList.ASSETS_QRY_SERVLET;
//			String url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=AssetsActionConstant.QUERY_ACTION;";
            String url = "/servlet/com.sino.ams.system.rent.servlet.AmsRentAssetServlet?act=QUERY_ACTION";
			url += "&treeCategory=" + treeCategory;
			webTree.setUrlPrefix(url);
			assetsTree.append(webTree.getPageJs());
			assetsTree.append(webTree.getTreeDataHtml());
		} catch (NodeException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return assetsTree;
	}

	/**
	 * ���ܣ���ȡ�����ʲ�������Χ��Ĭ������չʾ��
	 * @return String
	 */
	public String getFirstTreeCategory(){
//		return AssetsWebAttributes.ASSETS_TREE_TYPES[0];
		return AssetsWebAttributes.RENT_ASSETS_TYPES[0];
	}

	/**
	 * ����:��ȡ�ص��ѯ����
	 * @return String
	 * @throws QueryException
	 */
	public String getLocationQueryTree() throws QueryException {
		StringBuffer assetsTree = new StringBuffer();
		try {
			RentAssetsTreeModel modelProducer = (RentAssetsTreeModel) sqlProducer;
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
			SQLModel sqlModel = modelProducer.getCompanyCountyModel();
			FieldSQLProperty sqlProperty = new FieldSQLProperty();
			sqlProperty.setSqlModel(sqlModel);
			DataBaseTree treeBuilder = new DataBaseTree(conn, sqlProperty);
			treeBuilder.setTreeName("�ص��ѯ");
			Tree tree = treeBuilder.getTree();
			WebPageTree webTree = new WebPageTree(tree);
			webTree.setTarFrame("assetsMain");
			String url = AssetsURLList.LOCATION_QUERY_SERVLET;
			url += "?act=" + AssetsActionConstant.QUERY_ACTION;
			url += "&treeCategory=" + dto.getTreeCategory();
			webTree.setUrlPrefix(url);
			assetsTree.append(webTree.getPageJs());
			assetsTree.append(webTree.getTreeDataHtml());
		} catch (NodeException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return assetsTree.toString();
	}

	/**
	 * ����:��ȡ�Զ����ѯ����
	 * @return String
	 */
	public String getCustomQueryTree(){
		StringBuffer queryTree = new StringBuffer();
		queryTree.append("<script language=\"JavaScript\" src=\"/WebLibary/js/MzTreeView10.js\"></script>");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("<script language=\"JavaScript\">");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("var tree = new MzTreeView(\"tree\");");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("with(tree){");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("icons[\"property\"] = \"property.gif\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("icons[\"css\"] = \"collection.gif\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("icons[\"book\"]  = \"book.gif\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("iconsExpand[\"book\"] = \"bookopen.gif\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("setIconPath(\"/images/mzTree/\");");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("nodes[\"0_rootId\"]=\"text:�����ʲ��Զ����ѯ;\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("nodes[\"rootId_1\"]=\"text:��ѯ����;url:/servlet/com.sino.ams.newasset.servlet.CustomQuerySetServlet\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("nodes[\"rootId_2\"]=\"text:�����ʲ���ѯ;url:/servlet/com.sino.ams.newasset.servlet.CustomQueryServlet\";");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("setTarget(\"assetsMain\");");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("}");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append(WorldConstant.TAB_CHAR);
		queryTree.append("document.write(tree.toString());");
		queryTree.append(WorldConstant.ENTER_CHAR);
		queryTree.append("</script>");
		return queryTree.toString();
	}
}
