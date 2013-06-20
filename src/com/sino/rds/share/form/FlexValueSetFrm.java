package com.sino.rds.share.form;

import com.sino.rds.appbase.form.RDSBaseFrm;

/**
 * <p>Title: RDS_FLEX_VALUE_SET</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class FlexValueSetFrm extends RDSBaseFrm {

    private String valueSetId = "";
    private String valueSetCode = "";
    private String valueSetName = "";
    private String valueSetDescription = "";

    public FlexValueSetFrm() {
        super();
        primaryKeyName = "valueSetId";
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUE_SET���� �ֵ����ID
     *
     * @param valueSetId String
     */
    public void setValueSetId(String valueSetId) {
        this.valueSetId = valueSetId;
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUE_SET���� �ֵ�������
     *
     * @param valueSetCode String
     */
    public void setValueSetCode(String valueSetCode) {
        this.valueSetCode = valueSetCode;
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUE_SET���� �ֵ��������
     *
     * @param valueSetName String
     */
    public void setValueSetName(String valueSetName) {
        this.valueSetName = valueSetName;
    }

    /**
     * ���ܣ�����RDS_FLEX_VALUE_SET���� �ֵ��������
     *
     * @param valueSetDescription String
     */
    public void setValueSetDescription(String valueSetDescription) {
        this.valueSetDescription = valueSetDescription;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUE_SET���� �ֵ����ID
     *
     * @return String
     */
    public String getValueSetId() {
        return this.valueSetId;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUE_SET���� �ֵ�������
     *
     * @return String
     */
    public String getValueSetCode() {
        return this.valueSetCode;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUE_SET���� �ֵ��������
     *
     * @return String
     */
    public String getValueSetName() {
        return this.valueSetName;
    }

    /**
     * ���ܣ���ȡRDS_FLEX_VALUE_SET���� �ֵ��������
     *
     * @return String
     */
    public String getValueSetDescription() {
        return this.valueSetDescription;
    }
}