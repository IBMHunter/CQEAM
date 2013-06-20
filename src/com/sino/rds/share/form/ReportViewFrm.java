package com.sino.rds.share.form;

import com.sino.rds.foundation.web.component.WebOptions;


/**
 * <p>Title: ����ҳ��չʾ RDS_REPORT_VIEW</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ReportViewFrm extends ModelFieldFrm {
    private String viewId = "";
    private String viewIds = "";
    private String reportId = "";
    private String reportCode = "";
    private String reportName = "";
    private String fieldId = "";
    private String fieldAlignName = "";
    private String dataSrcType = "";
    private String dataPattern = "";
    private String dotNumber = "";
    private String fieldAlign = "";
    private String fieldWidth = "";
    private String sortNumber = "";
    private String computeExpression = "";
    private String computeExpressionName = "";
    private String fieldIds = "";

    private WebOptions fieldAlignOptions = null;
    private WebOptions dataPatternOptions = null;
    private WebOptions dataSrcTypeOptions = null;
    private WebOptions reportOptions = null;
    private WebOptions computeExpressionOptions = null;

    public ReportViewFrm() {
        super();
        primaryKeyName = "viewId";
    }

    /**
     * ���ܣ����ñ���ҳ��չʾ�ֶ����Զ��� չʾ����ID
     *
     * @param viewId String
     */
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    /**
     * ���ܣ����ñ���ҳ��չʾ�ֶ����Զ��� �����ֶ�
     *
     * @param fieldId String
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * ���ܣ����ñ���ҳ��չʾ�ֶ����Զ��� �ֶο��
     *
     * @param fieldWidth String
     */
    public void setFieldWidth(String fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    /**
     * ���ܣ����ñ���ҳ��չʾ�ֶ����Զ��� ���򲼾�
     *
     * @param fieldAlign String
     */
    public void setFieldAlign(String fieldAlign) {
        this.fieldAlign = fieldAlign;
    }

    /**
     * ���ܣ����ñ���ҳ��չʾ�ֶ����Զ��� ������Դ���ͣ�������ֶΣ�������ֶμ�����ʽ��
     *
     * @param dataSrcType String
     */
    public void setDataSrcType(String dataSrcType) {
        this.dataSrcType = dataSrcType;
    }

    /**
     * ���ܣ����ñ���ҳ��չʾ�ֶ����Զ��� ������ʽ
     *
     * @param computeExpression String
     */
    public void setComputeExpression(String computeExpression) {
        this.computeExpression = computeExpression;
    }

    /**
     * ���ܣ���ȡ����ҳ��չʾ�ֶ����Զ��� չʾ����ID
     *
     * @return String
     */
    public String getViewId() {
        return this.viewId;
    }

    /**
     * ���ܣ���ȡ����ҳ��չʾ�ֶ����Զ��� �����ֶ�
     *
     * @return String
     */
    public String getFieldId() {
        return this.fieldId;
    }

    /**
     * ���ܣ���ȡ����ҳ��չʾ�ֶ����Զ��� �ֶο��
     *
     * @return String
     */
    public String getFieldWidth() {
        return this.fieldWidth;
    }

    /**
     * ���ܣ���ȡ����ҳ��չʾ�ֶ����Զ��� ���򲼾�
     *
     * @return String
     */
    public String getFieldAlign() {
        return this.fieldAlign;
    }


    /**
     * ���ܣ���ȡ����ҳ��չʾ�ֶ����Զ��� ������Դ���ͣ�������ֶΣ�������ֶμ�����ʽ��
     *
     * @return String
     */
    public String getDataSrcType() {
        return this.dataSrcType;
    }

    /**
     * ���ܣ���ȡ����ҳ��չʾ�ֶ����Զ��� ������ʽ
     *
     * @return String
     */
    public String getComputeExpression() {
        return this.computeExpression;
    }

    public String getFieldAlignName() {
        return fieldAlignName;
    }

    public void setFieldAlignName(String fieldAlignName) {
        this.fieldAlignName = fieldAlignName;
    }

    public WebOptions getFieldAlignOptions() {
        return fieldAlignOptions;
    }

    public void setFieldAlignOptions(WebOptions fieldAlignOptions) {
        this.fieldAlignOptions = fieldAlignOptions;
    }

    public WebOptions getDataSrcTypeOptions() {
        return dataSrcTypeOptions;
    }

    public void setDataSrcTypeOptions(WebOptions dataSrcTypeOptions) {
        this.dataSrcTypeOptions = dataSrcTypeOptions;
    }

    public String getDataPattern() {
        return dataPattern;
    }

    public void setDataPattern(String dataPattern) {
        this.dataPattern = dataPattern;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getViewIds() {
        return viewIds;
    }

    public void setViewIds(String viewIds) {
        this.viewIds = viewIds;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public WebOptions getReportOptions() {
        return reportOptions;
    }

    public void setReportOptions(WebOptions reportOptions) {
        this.reportOptions = reportOptions;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public WebOptions getDataPatternOptions() {
        return dataPatternOptions;
    }

    public void setDataPatternOptions(WebOptions dataPatternOptions) {
        this.dataPatternOptions = dataPatternOptions;
    }

    public String getFieldIds() {
        return fieldIds;
    }

    public void setFieldIds(String fieldIds) {
        this.fieldIds = fieldIds;
    }

    public String getDotNumber() {
        return dotNumber;
    }

    public void setDotNumber(String dotNumber) {
        this.dotNumber = dotNumber;
    }

    public String getComputeExpressionName() {
        return computeExpressionName;
    }

    public void setComputeExpressionName(String computeExpressionName) {
        this.computeExpressionName = computeExpressionName;
    }

    public WebOptions getComputeExpressionOptions() {
        return computeExpressionOptions;
    }

    public void setComputeExpressionOptions(WebOptions computeExpressionOptions) {
        this.computeExpressionOptions = computeExpressionOptions;
    }
}