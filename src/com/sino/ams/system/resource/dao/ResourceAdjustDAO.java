package com.sino.ams.system.resource.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.resource.dto.SfResDefineDTO;
import com.sino.ams.system.resource.model.ResouceAdjustModel;
import com.sino.ams.system.resource.model.SfResDefineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SfResDefineDAO</p>
 * <p>Description:�����Զ����ɷ������SfResDefineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class ResourceAdjustDAO extends AMSBaseDAO {


    /**
     * ���ܣ�SF_RES_DEFINE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfResDefineDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ResourceAdjustDAO(SfUserDTO userAccount, SfResDefineDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SfResDefineDTO dtoPara = (SfResDefineDTO) dtoParameter;
        super.sqlProducer = new SfResDefineModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�
     * @param sfResource SfResDefineDTO ѡ�е���Դ
     * @return String
     * @throws com.sino.base.exception.QueryException
     *
     */
    public String getResourceOption(SfResDefineDTO sfResource) throws QueryException {
        StringBuffer resourceOption = new StringBuffer();
        String selectedResource = sfResource.getResParId();
        ResouceAdjustModel adjustModel=new ResouceAdjustModel(userAccount,(SfResDefineDTO)dtoParameter);
        SQLModel sqlModel = adjustModel.getResourceOptionModel(sfResource.getResId());
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        DTOSet resources = simp.getDTOSet();
        if (resources != null && !resources.isEmpty()) {
            SfResDefineDTO resource = null;
            int dotCount = 0;
            String resourceId = "";
            String resourceName = "";
            resourceOption.append("<option value=\"\">ѡ���ϼ���Ŀ</option>");
            resourceOption.append("<option value=\"\">��Ŀ¼</option>");
            for (int i = 0; i < resources.getSize(); i++) {
                resource = (SfResDefineDTO) resources.getDTO(i);
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
                if (resourceId.equals(selectedResource)) {
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
     * ���ܣ�����URL��Դ
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("URL��Դ");
    }

    /**
     * ���ܣ�����URL��Դ
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    public void updateData() throws DataHandleException {
        boolean autoCommit = true;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            setDTOClassName(SfResDefineDTO.class.getName());
            SfResDefineDTO oldResource = (SfResDefineDTO) getDataByPrimaryKey();
            SfResDefineDTO newResource = (SfResDefineDTO) dtoParameter;
            if (newResource.getEnabled().equals(WebAttrConstant.FALSE_VALUE)) {//���ҪʧЧ������ʧЧ
                passviateResource();
            } else if (newResource.getEnabled().equals(WebAttrConstant.TRUE_VALUE)) {//���Ҫ��Ч��������Ч
                activateResource();
            }
            if (oldResource.getResParId().equals(newResource.getResParId())) {
                super.updateData();
            } else {
                updateResourceRecursive(oldResource);
            }
            conn.commit();
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
            hasError = false;
        } catch (QueryException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.UPDATE_DATA_FAILURE);
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.UPDATE_DATA_FAILURE);
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.UPDATE_DATA_FAILURE);
        } finally {
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
     * @throws com.sino.base.exception.DataHandleException
     *
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
            String newResourceId = "";
            SfResDefineDTO parResource = null;
            for (int i = 0; i < resources.getSize(); i++) {//�����µĽṹ����
                SfResDefineDTO currResource = (SfResDefineDTO) resources.getDTO(i);
                if (StrUtil.isEmpty(currResource.getNewResParId())) {
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
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (DTOException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
        return operateResult;
    }

    /**
     * ���ܣ���ȡָ����Դ����������Դ����������
     * �޸�URL��Դʱʹ�ã����������ڽ�������Ѿ��޸ģ��ʲ�Ӧ��������
     * @param resourcePid String
     * @return DTOSet
     * @throws com.sino.base.exception.QueryException
     *
     */
    private DTOSet getAllChildResources(String resourcePid) throws QueryException {
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
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
     * @throws com.sino.base.exception.QueryException
     *
     */
    private String getNewChildId(String resourcePid) throws QueryException {
        String nextResourceId = "";
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getNewChildModel(resourcePid);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        if (simp.hasResult()) {
            SfResDefineDTO nextDTO = (SfResDefineDTO) simp.getFirstDTO();
            nextResourceId = nextDTO.getResId();
        }
        return nextResourceId;
    }

    /**
     * ���ܣ�ʧЧURL��Դ������URL��Դ������Դ��
     * @return ����true��ʾ�ɹ��������ʾʧ��
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private boolean passviateResource() throws DataHandleException {
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPassviateResourceModel();
        return DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ���URL��Դ������URL��Դ��ֱϵ�ϼ���Դ��
     * @return ����true��ʾ�ɹ��������ʾʧ��
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private boolean activateResource() throws DataHandleException {
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getActivateResourceModel();
        return DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�������ԴID��ȡ��Դ
     * @return SfResDefineDTO
     * @throws com.sino.base.exception.QueryException
     *
     */
    public SfResDefineDTO getResourceById() throws QueryException {
        SfResDefineDTO resource = new SfResDefineDTO();
        SfResDefineModel modelProducer = (SfResDefineModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getResourceByIdModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfResDefineDTO.class.getName());
        simp.executeQuery();
        if (simp.hasResult()) {
            resource = (SfResDefineDTO) simp.getFirstDTO();
        }
        return resource;
    }

    public RowSet getChildren(String resId) throws QueryException {
        RowSet rs = null;
        ResouceAdjustModel adjustModel = new ResouceAdjustModel(userAccount, (SfResDefineDTO) dtoParameter);
        SQLModel sqlModel = adjustModel.getChildrenModel(resId);
        SimpleQuery sq=new SimpleQuery(sqlModel,conn);
        sq.executeQuery();
        if (sq.hasResult()) {
            rs=sq.getSearchResult();
        }
        return rs;
    }
    public void updateChildrenOrder(String resIds[]) throws DataHandleException {
        ResouceAdjustModel adjustModel = new ResouceAdjustModel(userAccount, (SfResDefineDTO) dtoParameter);
        List sqlModelList =new ArrayList();
        for (int i = 0; i < resIds.length; i++) {
            String resId = resIds[i];
            SQLModel sqlModel=adjustModel.getUpdateResOrderModel(resId, i);
            sqlModelList.add(sqlModel);
        }
        DBOperator.updateBatchRecords(sqlModelList,conn);

    }
}