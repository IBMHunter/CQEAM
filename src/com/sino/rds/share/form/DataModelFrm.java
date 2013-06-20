package com.sino.rds.share.form;

import com.sino.base.ExtendedArrayList;
import com.sino.rds.appbase.form.RDSBaseFrm;
import com.sino.rds.foundation.web.component.WebOptions;

import java.util.List;

/**
 * <p>Title: ��������ģ�� RDS_DATA_MODEL</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class DataModelFrm extends RDSBaseFrm {

    private String modelId = "";
    private String modelIds = "";
    private String modelName = "";
    private String modelDesc = "";
    private String dataSrcType = "";
    private String dataSrcTypeName = "";
    private WebOptions dataSrcOptions = null;
    private String modelSql = "";
    private String owner = "";
    private String modelCheckViewName = "";
    private String connectionId = "";
    private String connectionName = "";
    private WebOptions ownerOptions = null;
    private WebOptions connectionOptions = null;
    private WebOptions modelOptions = null;
    private List<ModelFieldFrm> fields = new ExtendedArrayList(ModelFieldFrm.class);

    public DataModelFrm() {
        super();
        primaryKeyName = "modelId";
    }

    /**
     * ���ܣ����ñ�������ģ������ ģ��ID
     *
     * @param modelId String
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    /**
     * ���ܣ����ñ�������ģ������ ģ������
     *
     * @param modelName String
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * ���ܣ����ñ�������ģ������ ģ������
     *
     * @param modelDesc String
     */
    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    /**
     * ���ܣ����ñ�������ģ������ ����Դ���ͣ�1����2����ͼ��3����ѯ��䣩
     *
     * @param dataSrcType String
     */
    public void setDataSrcType(String dataSrcType) {
        this.dataSrcType = dataSrcType;
    }

    /**
     * ���ܣ����ñ�������ģ������ ģ�Ͳ�ѯSQL
     *
     * @param modelSql String
     */
    public void setModelSql(String modelSql) {
        this.modelSql = modelSql;
    }


    /**
     * ���ܣ���ȡ��������ģ������ ģ��ID
     *
     * @return String
     */
    public String getModelId() {
        return this.modelId;
    }

    /**
     * ���ܣ���ȡ��������ģ������ ģ������
     *
     * @return String
     */
    public String getModelName() {
        return this.modelName;
    }

    /**
     * ���ܣ���ȡ��������ģ������ ģ������
     *
     * @return String
     */
    public String getModelDesc() {
        return this.modelDesc;
    }

    /**
     * ���ܣ���ȡ��������ģ������ ����Դ���ͣ�1����2����ͼ��3����ѯ��䣩
     *
     * @return String
     */
    public String getDataSrcType() {
        return this.dataSrcType;
    }

    /**
     * ���ܣ���ȡ��������ģ������ ģ�Ͳ�ѯSQL
     *
     * @return String
     */
    public String getModelSql() {
        return this.modelSql;
    }


    public String getDataSrcTypeName() {
        return dataSrcTypeName;
    }

    public void setDataSrcTypeName(String dataSrcTypeName) {
        this.dataSrcTypeName = dataSrcTypeName;
    }

    public WebOptions getDataSrcOptions() {
        return dataSrcOptions;
    }

    public void setDataSrcOptions(WebOptions dataSrcOptions) {
        this.dataSrcOptions = dataSrcOptions;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public WebOptions getOwnerOptions() {
        return ownerOptions;
    }

    public void setOwnerOptions(WebOptions ownerOptions) {
        this.ownerOptions = ownerOptions;
    }

    public String getModelCheckViewName() {
        return modelCheckViewName;
    }

    public void setModelCheckViewName(String modelCheckViewName) {
        this.modelCheckViewName = modelCheckViewName;
    }

    public List<ModelFieldFrm> getFields() {
        return fields;
    }

    public void setFields(List<ModelFieldFrm> fields) {
        this.fields = fields;
    }

    public WebOptions getModelOptions() {
        return modelOptions;
    }

    public void setModelOptions(WebOptions modelOptions) {
        this.modelOptions = modelOptions;
    }

    public String getModelIds() {
        return modelIds;
    }

    public void setModelIds(String modelIds) {
        this.modelIds = modelIds;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public WebOptions getConnectionOptions() {
        return connectionOptions;
    }

    public void setConnectionOptions(WebOptions connectionOptions) {
        this.connectionOptions = connectionOptions;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }
}