package com.sino.ams.system.dict.dao;

import java.sql.Connection;

import com.sino.ams.constant.URLDefineList;
import com.sino.ams.system.dict.dto.EtsFlexValueSetDTO;
import com.sino.ams.system.dict.dto.EtsFlexValuesDTO;
import com.sino.ams.system.dict.model.DictionaryAnalyseTreeModel;
import com.sino.base.constant.TreeConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.NodeException;
import com.sino.base.exception.QueryException;
import com.sino.base.treeview.RelateNode;
import com.sino.base.treeview.RelateTree;
import com.sino.base.treeview.StyleProperty;
import com.sino.base.treeview.Tree;
import com.sino.base.treeview.WebPageTree;
import com.sino.base.util.StrUtil;
/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DictionaryAnalyseTreeDAO {

	private Connection conn = null;
	private DictionaryAnalyseTreeModel treeModel = null;

	public DictionaryAnalyseTreeDAO(Connection conn) {
		this.conn = conn;
		treeModel = new DictionaryAnalyseTreeModel();
	}

	/**
	 * ���ܣ���ȡ�ֵ������չʾ
	 * @return WebPageTree
	 * @throws QueryException
	 */
	public WebPageTree getDictTree() throws QueryException {
		WebPageTree webTree = null;
		try {
			DTOSet dictSet = getAllDictSets();
			if (dictSet != null && !dictSet.isEmpty()) {
				RelateNode rootNode = new RelateNode();
				rootNode.setDescript("�ֵ���Ŀ");
				String urlTemplate = URLDefineList.DICT_ANALYSE_QUERYRY_SERVLET;
				String url = "";
				for (int i = 0; i < dictSet.getSize(); i++) {
					EtsFlexValueSetDTO parent = (EtsFlexValueSetDTO) dictSet.getDTO(i);
					RelateNode parentNode = new RelateNode(StrUtil.nullToString(parent.getFlexValueSetId()));
					parentNode.setDescript(parent.getDescription());
					url = urlTemplate
						  + "&flexValueSetId="
						  + parent.getFlexValueSetId()
						  + "&flexValueSetName="
						  + parent.getName()+"&remark="+parent.getRemark();
					parentNode.setTarURL(url);
					DTOSet dictionarys = getDictionaryBySetId(StrUtil.nullToString(parent.getFlexValueSetId()));
					for (int j = 0; j < dictionarys.getSize(); j++) {
						EtsFlexValuesDTO child = (EtsFlexValuesDTO) dictionarys.getDTO(j);
						RelateNode childNode = new RelateNode(StrUtil.nullToString(child.getFlexValueId()));
						childNode.setDescript(child.getValue());
						url = urlTemplate +"&flexValueId=" + child.getFlexValueId()+"&remark="+parent.getRemark();
						childNode.setTarURL(url);
						parentNode.addChild(childNode);
					}
					rootNode.addChild(parentNode);
				}
				Tree tree = new RelateTree(rootNode);
				webTree = new WebPageTree(tree, TreeConstant.SIMPLE_DIV_TREE);
				StyleProperty styleProperty = new StyleProperty();
				styleProperty.setShowParURL(true);
				webTree.setStyleProperty(styleProperty);
				webTree.setTarFrame("right");
				webTree.setStyleProperty(styleProperty);
//				webTree.setUrlPrefix(url);
			}
		} catch (NodeException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return webTree;
	}


	/**
	 * ���ܣ���ȡ�����ֵ����
	 * @return EtsFlexValueSetDTO
	 * @throws QueryException
	 */
	public DTOSet getAllDictSets() throws QueryException {
		DTOSet dictSet = new DTOSet();
		SQLModel sqlModel = treeModel.getAllDictSetModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(EtsFlexValueSetDTO.class.getName());
		simp.executeQuery();
		dictSet = simp.getDTOSet();
		return dictSet;
	}


	/**
	 * ���ܣ���ȡ�����ֵ�
	 * @param setId String
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getDictionaryBySetId(String setId) throws QueryException {
		DTOSet dictionarys = new DTOSet();
		SQLModel sqlModel = treeModel.getDictionaryBySetIdModel(setId);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(EtsFlexValuesDTO.class.getName());
		simp.executeQuery();
		dictionarys = simp.getDTOSet();
		return dictionarys;
	}


	/**
	 * ���ܣ���ȡ��һ���ֵ����
	 * @return EtsFlexValueSetDTO
	 * @throws QueryException
	 */
	public EtsFlexValueSetDTO getFirstDictSet() throws QueryException {
		EtsFlexValueSetDTO firstDictSet = new EtsFlexValueSetDTO();
		DTOSet dictSet = getAllDictSets();
		if(dictSet != null && !dictSet.isEmpty()){
			firstDictSet = (EtsFlexValueSetDTO)dictSet.getDTO(0);
		}
		return firstDictSet;
	}

}
