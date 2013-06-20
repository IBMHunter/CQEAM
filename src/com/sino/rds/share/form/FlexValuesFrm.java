package com.sino.rds.share.form;

/**
 * <p>Title: RDS_FLEX_VALUES</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class FlexValuesFrm extends FlexValueSetFrm {

    private String valueId = "";
    private String code = "";
    private String value = "";
    private String description = "";

    public FlexValuesFrm() {
        super();
        primaryKeyName = "valueId";
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUES���� �ֵ�ֵID
     *
     * @param valueId String
     */
    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUES���� �ֵ����
     *
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUES���� �ֵ�ֵ
     *
     * @param value String
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUES���� �ֵ�����
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * ���ܣ���ȡRDS_FLEX_VALUES���� �ֵ�ֵID
     *
     * @return String
     */
    public String getValueId() {
        return this.valueId;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUES���� �ֵ����
     *
     * @return String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUES���� �ֵ�ֵ
     *
     * @return String
     */
    public String getValue() {
        return this.value;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUES���� �ֵ�����
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }
}