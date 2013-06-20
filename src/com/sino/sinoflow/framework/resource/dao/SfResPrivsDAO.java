package com.sino.sinoflow.framework.resource.dao;


import java.sql.Connection;

import com.sino.base.constant.TreeConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.treeview.*;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.constant.CustMessageKey;
import com.sino.sinoflow.framework.resource.dto.SfResDefineDTO;
import com.sino.sinoflow.framework.resource.dto.SfResPrivsDTO;
import com.sino.sinoflow.framework.resource.model.SfResPrivsModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfResPrivsDAO</p>
 * <p>Description:�����Զ����ɷ������SfResPrivsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 * 
 * �޸��ˣ��׼�
 * �޸����ڣ�2008.8.20
 */


public class SfResPrivsDAO extends BaseDAO {
    private SfResPrivsModel sfResPrivsModel = null;
    /**
     * ���ܣ�SF_RES_PRIVS ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfResPrivsDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public SfResPrivsDAO(SfUserBaseDTO userAccount, SfResPrivsDTO dtoParameter, Connection conn) {
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
        super.sqlProducer = new SfResPrivsModel((SfUserBaseDTO)userAccount, dtoPara);
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
     * @throws NodeException 
     */
    public String getTreeHtml(String systemName) throws QueryException, NodeException {
//        StringBuffer treeHtml = new StringBuffer();
//        try {
//            SQLModel sqlModel = sfResPrivsModel.getResourceTreeModel();
//            RelateSQLProperty sqlProp = new RelateSQLProperty();
//            sqlProp.setSqlModel(sqlModel);
//            sqlProp.setIdField("RES_ID");
//            sqlProp.setPidField("RES_PAR_ID");
//            sqlProp.setDescField("RES_NAME");
//            sqlProp.setUrlField("RES_URL");
//            sqlProp.setPopupField("IS_POPUP");
//            sqlProp.setPopscriptField("POPSCRIPT");
//            DataBaseTree dbTree = new DataBaseTree(conn, sqlProp);
//            Tree tree = dbTree.getTree();
//
//            tree.setRootDesc(systemName);
//            WebPageTree webTree = new WebPageTree(tree, TreeConstant.SIMPLE_DIV_TREE);
//            StyleProperty styleProperty = new StyleProperty();
//            styleProperty.setShowParURL(true);
//            webTree.setStyleProperty(styleProperty);
//            webTree.setTarFrame("right");
//            //ԭ���� webTree.setUrlPrefix("/servlet/com.sino.ams.system.resource.servlet.SfResPrivsServlet?act=" + WebActionConstant.QUERY_ACTION + "&resId=");
//            //�޸ĺ�
//            webTree.setUrlPrefix("/servlet/com.sino.sinoflow.framework.resource.servlet.SfResPrivsServlet?act=" + WebActionConstant.QUERY_ACTION + "&resId=");
//            treeHtml.append(webTree.getTreeDataHtml());
//            treeHtml.append(webTree.getPageJs());
//        } catch (NodeException ex) {
//            Logger.logError(ex);
//            throw new QueryException(ex.getMessage());
//        }
//        return treeHtml.toString();
    	
    	
//    	MzTree2 mzTree = new MzTree2(conn);
//    	SfResPrivsModel modelProducer = (SfResPrivsModel) sqlProducer;
//		mzTree.setSqModel(modelProducer.getResourceTreeModel() );
//		mzTree.setTarget("right");
//		mzTree.setUrl("/servlet/com.sino.sinoflow.framework.resource.servlet.SfResPrivsServlet" );
//		mzTree.setLevels(3);
//		mzTree.setRootText( "˼ŵ��������ϵͳ" );
//		mzTree.setTransParaName("resId");
//		mzTree.produceTree3();
//		return mzTree.getTreeStr();
		
    	StringBuffer treeHtml = new StringBuffer();
    	SfResPrivsModel modelProducer = (SfResPrivsModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getResourceTreeModel();
        RelateSQLProperty sqlProp = new RelateSQLProperty();
        sqlProp.setSqlModel(sqlModel);
        sqlProp.setIdField("RES_ID");
        sqlProp.setPidField("RES_PAR_ID");
        sqlProp.setDescField("RES_NAME");
        sqlProp.setUrlField("RES_URL");
        //sqlProp.setPopupField("IS_POPUP");
        //sqlProp.setPopscriptField("POPSCRIPT");
        DataBaseTree dbTree = new DataBaseTree(conn, sqlProp);
        Tree tree = dbTree.getTree();
        tree.setRootDesc(systemName);
        WebPageTree webTree = new WebPageTree(tree, TreeConstant.DIV_TREE);
        StyleProperty styleProperty = new StyleProperty();
        styleProperty.setInitExpand(false);  
        styleProperty.setShowParURL(true);
        webTree.setStyleProperty(styleProperty); 
        webTree.setTarFrame("right");
        webTree.setUrlPrefix("/servlet/com.sino.sinoflow.framework.resource.servlet.SfResPrivsServlet?act=" + WebActionConstant.QUERY_ACTION + "&resId=" );
        treeHtml.append(webTree.getTreeDataHtml());
        treeHtml.append(webTree.getPageJs());
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
            Logger.logError(ex);
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
      * @param resId systemId
      * @return boolean
      */
     public boolean saveResPrivis(DTOSet dtoSet,int resId){
         boolean operateResult = false;
         try {
             SfResPrivsDTO resPrivi = null;

             //ɾ����ǰ��SfResPrivis��ĿȨ��.
             deleteExistPrivi(resId);

             //��ȡ���˵���DTOSet SfResPrivsDTO
              DTOSet parentRes = getParentRes(resId);

             if(parentRes != null){//�����ڵ�Ҳ����ͬ��Ȩ��
                 int parentCount = parentRes.getSize();
                 SfResDefineDTO resource = null;
                 DTOSet appendRole = new DTOSet();
                 for(int i = 0; i < parentCount; i++){
                     resource = (SfResDefineDTO)parentRes.getDTO(i);
                     resId = resource.getSystemId();
                     SfResPrivsDTO tmpPrivi = null;
                     for(int j = 0; j < dtoSet.getSize(); j++){//��ÿ�����ڵ�����ӽڵ�Ľ�ɫȨ��
                         tmpPrivi = new SfResPrivsDTO();
                         resPrivi = (SfResPrivsDTO)dtoSet.getDTO(j);
                         tmpPrivi.setResId(resId);
                         tmpPrivi.setRoleName(resPrivi.getRoleName());//����RoleName
                         tmpPrivi.setGroupName(resPrivi.getGroupName());
                         tmpPrivi.setCreatedBy(resPrivi.getCreatedBy());
                         appendRole.addDTO(tmpPrivi);
                     }
                 }
                 dtoSet.addDTOs(appendRole);
             }

             for(int i = 0; i < dtoSet.getSize(); i++){
                 resPrivi = (SfResPrivsDTO)dtoSet.getDTO(i);
                 saveResPrivis(resPrivi);
             }

             for (int i = 0; i < parentRes.getSize(); i++) {
                 SfResDefineDTO resDefineDTO = (SfResDefineDTO) parentRes.getDTO(i);
                 deleteInvalidatePrivi(resDefineDTO);
             }
             operateResult = true;
             prodMessage(CustMessageKey.PRIVI_SAVE_SUCCESS);
         } catch (DataHandleException ex) {
             Logger.logError(ex);
             prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
         } catch (QueryException ex) {
             Logger.logError(ex);
             prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
         } catch (DTOException ex) {
             Logger.logError(ex);
             prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
         }
         return operateResult;
     }


    /**
     * ���ܣ�ɾ��Ȩ�ޡ�
     * @param resId systemId
     * @throws DataHandleException
     */
    public void deleteExistPrivi(int resId) throws DataHandleException {
        SQLModel sqlModel = sfResPrivsModel.getPriviDeleteModel(resId);
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void deleteInvalidatePrivi(SfResDefineDTO resDefineDTO) throws DataHandleException {
        SQLModel sqlModel = sfResPrivsModel.getDeleteInValidatePriviModel(resDefineDTO);
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
            Logger.logError(ex);
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

    public String getInitDate() throws QueryException{
    	DTOSet dtoSet = null;
        SQLModel sqlModel = sfResPrivsModel.getPriviModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResPrivsDTO.class.getName());
        simp.executeQuery();
        if(simp.hasResult()){
        	StringBuffer arr = new StringBuffer();
        	dtoSet = simp.getDTOSet();
        	for(int i=0;i<dtoSet.getSize();i++){
        		SfResPrivsDTO dto = (SfResPrivsDTO)dtoSet.getDTO(i);
        		String group = dto.getGroupName();
        		arr.append("new Array('"+group+"','"+dto.getRoleName()+"'),");
        	}
        	String tp = arr.toString();
        	return tp.substring(0,tp.length()-1);
        }

    	return "";
    }    
}
