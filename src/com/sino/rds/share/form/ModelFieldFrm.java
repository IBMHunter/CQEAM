package com.sino.rds.share.form;

import com.sino.rds.appbase.form.RDSBaseFrm;
import com.sino.rds.foundation.web.component.WebOptions;


/**
 * <p>Title: ����ģ���ֶζ��� RDS_MODEL_FIELD</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ModelFieldFrm extends RDSBaseFrm {

    private String fieldId = "";
    private String modelId = "";
    private String fieldName = "";
    private String fieldDesc = "";
    private String fieldType = "";

    private String modelName = "";
    private String dataSrcType = "";
    private String dataSrcTypeName = "";
    private String owner = "";

    private WebOptions modelOptions = null;

    public ModelFieldFrm() {
        super();
        primaryKeyName = "fieldId";
    }

    /**
     * ���ܣ���������ģ���ֶζ������� �ֶ�ID
     *
     * @param fieldId String
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * ���ܣ���������ģ���ֶζ������� ����ģ��
     *
     * @param modelId String
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    /**
     * ���ܣ���������ģ���ֶζ������� �ֶ�����
     *
     * @param fieldName String
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * ���ܣ���������ģ���ֶζ������� �ֶ�����
     *
     * @param fieldDesc String
     */
    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    /**
     * ���ܣ���������ģ���ֶζ������� �ֶ�����
     *
     * @param fieldType String
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * ���ܣ���ȡ����ģ���ֶζ������� �ֶ�ID
     *
     * @return String
     */
    public String getFieldId() {
        return this.fieldId;
    }

    /**
     * ���ܣ���ȡ����ģ���ֶζ������� ����ģ��
     *
     * @return String
     */
    public String getModelId() {
        return this.modelId;
    }

    /**
     * ���ܣ���ȡ����ģ���ֶζ������� �ֶ�����
     *
     * @return String
     */
    public String getFieldName() {
        return this.fieldName;
    }

    /**
     * ���ܣ���ȡ����ģ���ֶζ������� �ֶ�����
     *
     * @return String
     */
    public String getFieldDesc() {
        return this.fieldDesc;
    }

    /**
     * ���ܣ���ȡ����ģ���ֶζ������� �ֶ�����
     *
     * @return String
     */
    public String getFieldType() {
        return this.fieldType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public WebOptions getModelOptions() {
        return modelOptions;
    }

    public void setModelOptions(WebOptions modelOptions) {
        this.modelOptions = modelOptions;
    }

    public String getDataSrcTypeName() {
        return dataSrcTypeName;
    }

    public void setDataSrcTypeName(String dataSrcTypeName) {
        this.dataSrcTypeName = dataSrcTypeName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDataSrcType() {
        return dataSrcType;
    }

    public void setDataSrcType(String dataSrcType) {
        this.dataSrcType = dataSrcType;
    }
}