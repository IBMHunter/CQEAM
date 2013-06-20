package com.sino.sinoflow.framework.resource.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.MzTree2;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.appbase.dao.SFBaseDAO;
import com.sino.sinoflow.constant.WebAttrConstant;
import com.sino.sinoflow.framework.resource.dto.SfResDefineDTO;
import com.sino.sinoflow.framework.resource.model.SfResDefineModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfResDefineDAO</p>
 * <p>Description:�����Զ����ɷ������SfResDefineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 * 
 * �޸��ˣ��׼�
 * �޸����ڣ�2008.8.20
 */


public class SfResDefineDAO extends SFBaseDAO {

    /**
     * ���ܣ�SF_RES_DEFINE ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfResDefineDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public SfResDefineDAO(SfUserBaseDTO userAccount, SfResDefineDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SfResDefineDTO dtoPara = (SfResDefineDTO)dtoParameter;
        super.sqlProducer = new SfResDefineModel((SfUserBaseDTO)userAccount, dtoPara);
    }

    /**
     * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�
     * @param sfResource SfResDefineDTO ѡ�е���Դ
     * @return String
     * @throws QueryException QueryException
     */
    public String getResourceOption(SfResDefineDTO sfResource) throws QueryException {
        StringBuffer resourceOption = new StringBuffer();
        String selectedResource = sfResource.getResParId();
        SfResDefineModel modelProducer = (SfResDefineModel)sqlProducer;
        SQLModel sqlModel = modelProducer.getResourceOptionModel(sfResource.getResId());
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        DTOSet resources = simp.getDTOSet();
        if(resources != null && !resources.isEmpty()){
            SfResDefineDTO resource;
            int dotCount;
            String resourceId;
            String resourceName;
            resourceOption.append("<option value=\"\">ѡ���ϼ���Ŀ</option>");
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
     * @throws QueryException QueryException
     */
    public String getTreeHtml(String systemName) throws QueryException {
//        StringBuffer treeHtml = new StringBuffer();
//        try {
//            SfResDefineModel modelProducer = (SfResDefineModel)sqlProducer;
//            SQLModel sqlModel = modelProducer.getResourceTreeModel();
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
////			styleProperty.setInitExpand(true);
//            styleProperty.setShowParURL(true);
//            webTree.setStyleProperty(styleProperty);
//            webTree.setTarFrame("right");
//            //ԭ���� webTree.setUrlPrefix("/servlet/com.sino.ams.system.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.QUERY_ACTION + "&resId=");
//            //�޸ĺ�
//            webTree.setUrlPrefix("/servlet/com.sino.sinoflow.framework.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.QUERY_ACTION + "&resId=");
//            treeHtml.append(webTree.getTreeDataHtml());
//            treeHtml.append(webTree.getPageJs());
//        } catch (NodeException ex) {
//            Logger.logError(ex);
//            throw new QueryException(ex.getMessage());
//        }
//        return treeHtml.toString();
    	MzTree2 mzTree = new MzTree2(conn);
    	SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
		mzTree.setSqModel(modelProducer.getResourceTreeModel() );
		mzTree.setTarget("right");
		mzTree.setUrl("/servlet/com.sino.sinoflow.framework.resource.servlet.SfResDefineServlet" );
		mzTree.setLevels(3);
		mzTree.setRootText( systemName );
		mzTree.setTransParaName("act=QUERY_ACTION&resId");
		mzTree.produceTree3();
		return mzTree.getTreeStr();
    }

    /**
     * ���ܣ�����URL��Դ
     * @throws DataHandleException
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("URL��Դ");
    }

    public String createData2() throws DataHandleException {
        String result = super.createData2();
        getMessage().addParameterValue("URL��Դ");
        return result;
    }

    /**
     * ���ܣ�����URL��Դ
     * @throws DataHandleException
     */
    public void updateData() throws DataHandleException{
        boolean autoCommit = true;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            setDTOClassName(SfResDefineDTO.class.getName());
            SfResDefineDTO oldResource = (SfResDefineDTO) getDataByPrimaryKey();
            SfResDefineDTO newResource = (SfResDefineDTO) dtoParameter;
            if(newResource.getEnabled().equals(WebAttrConstant.FALSE_VALUE)){//���ҪʧЧ������ʧЧ
                passviateResource();
            } else if(newResource.getEnabled().equals(WebAttrConstant.TRUE_VALUE)){//���Ҫ��Ч��������Ч
                activateResource();
            }
            if (oldResource.getResParId().equals(newResource.getResParId())) {
                super.updateData();
            	//SfResDefineModel modelProducer = (SfResDefineModel)sqlProducer;
            	
            	
            } else {
                updateResourceRecursive(oldResource);
            }
            conn.commit();
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
            hasError = false;
        } catch (QueryException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.UPDATE_DATA_FAILURE);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.UPDATE_DATA_FAILURE);
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.UPDATE_DATA_FAILURE);
        } finally{
            try {
                if (!hasError) {
                    conn.rollback();
                }
                getMessage().addParameterValue("URL��Դ");
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
    }


    /**
     * ���ܣ��ݹ������Դ��Ϣ��
     * @param oldResource ҳ����µ�URL����
     * @return boolean
     * @throws DataHandleException DataHandleException
     */
    private boolean updateResourceRecursive(SfResDefineDTO oldResource) throws DataHandleException {
        boolean operateResult = false;
        try {
            SfResDefineDTO newResource = (SfResDefineDTO) dtoParameter;
            String newResParId = newResource.getResParId();
            newResource.setResParId(oldResource.getResParId());
            newResource.setNewResParId(newResParId);
            DTOSet resources = new DTOSet();
            resources.addDTO(newResource);
            DTOSet tmpResources = getAllChildResources(oldResource.getResId());
            if (tmpResources != null && !tmpResources.isEmpty()) {
                resources.addDTOs(tmpResources);
            }
            String newResourceId;
            SfResDefineDTO parResource;
            for (int i = 0; i < resources.getSize(); i++) {//�����µĽṹ����
                SfResDefineDTO currResource = (SfResDefineDTO) resources.getDTO(i);
                if(StrUtil.isEmpty(currResource.getNewResParId())){
                    parResource = currResource.getParent(resources);
                    newResParId = parResource.getNewResId();
                }
                newResourceId = getNewChildId(newResParId);
                currResource.setNewResParId(newResParId);
                currResource.setNewResId(newResourceId);

                newResourceId = currResource.getResId();//���ݺ�����
                newResParId = currResource.getResParId();

                currResource.setResId(currResource.getNewResId());//׼����������
                currResource.setResParId(currResource.getNewResParId());
                SfResDefineModel tmpModel = new SfResDefineModel(userAccount, currResource);
                DBOperator.updateRecord(tmpModel.getDataUpdateModel(), conn);

                currResource.setResId(newResourceId);//�ָ����ݣ��Ա���һ����Դ���Ҹ���Դʹ�á�
                currResource.setResParId(newResParId);
                resources.set(i, currResource);
            }
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
            getMessage().addParameterValue("URL��Դ");
        } catch (QueryException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (DTOException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
        return operateResult;
    }

    /**
     * ���ܣ���ȡָ����Դ����������Դ����������
     * �޸�URL��Դʱʹ�ã����������ڽ�������Ѿ��޸ģ��ʲ�Ӧ��������
     * @param resourcePid String
     * @return DTOSet
     * @throws QueryException QueryException
     */
    private DTOSet getAllChildResources(String resourcePid) throws QueryException {
        SfResDefineModel modelProducer = (SfResDefineModel)sqlProducer;
        SQLModel sqlModel = modelProducer.getAllChildModel(resourcePid);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        return simp.getDTOSet();
    }

    /**
     * ���ܣ���ȡָ������Դ�µ���һ����Դ���
     * @param resourcePid String
     * @return String
     * @throws QueryException QueryException
     */
    private String getNewChildId(String resourcePid) throws QueryException {
        String nextResourceId = "";
        SfResDefineModel modelProducer = (SfResDefineModel)sqlProducer;
        SQLModel sqlModel = modelProducer.getNewChildModel(resourcePid);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        if(simp.hasResult()){
            SfResDefineDTO nextDTO = (SfResDefineDTO) simp.getFirstDTO();
            nextResourceId = nextDTO.getResId();
        }
        return nextResourceId;
    }

    /**
     * ���ܣ�ʧЧURL��Դ������URL��Դ������Դ��
     * @throws DataHandleException DataHandleException
     * @return ����true��ʾ�ɹ��������ʾʧ��
     */
    private boolean passviateResource() throws DataHandleException {
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPassviateResourceModel();
        return DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ���URL��Դ������URL��Դ��ֱϵ�ϼ���Դ��
     * @throws DataHandleException DataHandleException
     * @return ����true��ʾ�ɹ��������ʾʧ��
     */
    private boolean activateResource() throws DataHandleException {
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getActivateResourceModel();
        return DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�������ԴID��ȡ��Դ
     * @return SfResDefineDTO
     * @throws QueryException QueryException
     */
    public SfResDefineDTO getResourceById() throws QueryException {
        SfResDefineDTO resource = new SfResDefineDTO();
        SfResDefineModel modelProducer = (SfResDefineModel)sqlProducer;
        SQLModel sqlModel = modelProducer.getResourceByIdModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        if(simp.hasResult()){
            resource = (SfResDefineDTO) simp.getFirstDTO();
        }
        return resource;
    }
}
