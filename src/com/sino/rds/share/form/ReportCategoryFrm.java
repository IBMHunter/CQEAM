package com.sino.rds.share.form;

import com.sino.rds.foundation.web.component.WebOptions;


/**
 * <p>Title: ��������ֶζ��� RDS_REPORT_CATEGORY</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ReportCategoryFrm extends ModelFieldFrm {
    private String categoryId = "";
    private String categoryIds = "";
    private String categoryName = "";
    private String reportId = "";
    private String reportCode = "";
    private String reportName = "";
    private String fieldId = "";
    private String fieldIds = "";
    private String categoryLevel = "";
    private String viewLocation = "";
    private String viewLocationName = "";
    private String fieldWidth = "";
    private String fieldAlign = "";
    private String fieldAlignName = "";
    private String summaryFlagName = "";
    private String displayFlag = "Y";
    private String displayFlagName = "";
    private WebOptions displayFlagOptions = null;
    private WebOptions reportOptions = null;
    private WebOptions fieldAlignOptions = null;
    private String viewIds = "";

    public ReportCategoryFrm() {
        super();
        primaryKeyName = "categoryId";
    }

    /**
     * ���ܣ����ñ�������ֶζ������� ����ID
     *
     * @param categoryId String
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * ���ܣ����ñ�������ֶζ������� ��������
     *
     * @param categoryName String
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * ���ܣ����ñ�������ֶζ������� ����ID
     *
     * @param reportId String
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * ���ܣ����ñ�������ֶζ������� �����ֶ�
     *
     * @param fieldId String
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * ���ܣ����ñ�������ֶζ������� ����㼶
     *
     * @param categoryLevel String
     */
    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    /**
     * ���ܣ����ñ�������ֶζ������� ��������(�����������ڱ���չʾ)
     *
     * @param viewLocation String
     */
    public void setViewLocation(String viewLocation) {
        this.viewLocation = viewLocation;
    }


    /**
     * ���ܣ���ȡ��������ֶζ������� ����ID
     *
     * @return String
     */
    public String getCategoryId() {
        return this.categoryId;
    }

    /**
     * ���ܣ���ȡ��������ֶζ������� ��������
     *
     * @return String
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * ���ܣ���ȡ��������ֶζ������� ����ID
     *
     * @return String
     */
    public String getReportId() {
        return this.reportId;
    }

    /**
     * ���ܣ���ȡ��������ֶζ������� �����ֶ�
     *
     * @return String
     */
    public String getFieldId() {
        return this.fieldId;
    }

    /**
     * ���ܣ���ȡ��������ֶζ������� ����㼶
     *
     * @return String
     */
    public String getCategoryLevel() {
        return this.categoryLevel;
    }

    /**
     * ���ܣ���ȡ��������ֶζ������� ��������(�����������ڱ���չʾ)
     *
     * @return String
     */
    public String getViewLocation() {
        return this.viewLocation;
    }

    public String getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(String fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public String getFieldAlign() {
        return fieldAlign;
    }

    public void setFieldAlign(String fieldAlign) {
        this.fieldAlign = fieldAlign;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getFieldAlignName() {
        return fieldAlignName;
    }

    public void setFieldAlignName(String fieldAlignName) {
        this.fieldAlignName = fieldAlignName;
    }

    public String getViewLocationName() {
        return viewLocationName;
    }

    public void setViewLocationName(String viewLocationName) {
        this.viewLocationName = viewLocationName;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getSummaryFlagName() {
        return summaryFlagName;
    }

    public void setSummaryFlagName(String summaryFlagName) {
        this.summaryFlagName = summaryFlagName;
    }

    public WebOptions getReportOptions() {
        return reportOptions;
    }

    public void setReportOptions(WebOptions reportOptions) {
        this.reportOptions = reportOptions;
    }

    public String getFieldIds() {
        return fieldIds;
    }

    public void setFieldIds(String fieldIds) {
        this.fieldIds = fieldIds;
    }

    public WebOptions getFieldAlignOptions() {
        return fieldAlignOptions;
    }

    public void setFieldAlignOptions(WebOptions fieldAlignOptions) {
        this.fieldAlignOptions = fieldAlignOptions;
    }

    public String getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(String displayFlag) {
        this.displayFlag = displayFlag;
    }

    public String getDisplayFlagName() {
        return displayFlagName;
    }

    public void setDisplayFlagName(String displayFlagName) {
        this.displayFlagName = displayFlagName;
    }

    public WebOptions getDisplayFlagOptions() {
        return displayFlagOptions;
    }

    public void setDisplayFlagOptions(WebOptions displayFlagOptions) {
        this.displayFlagOptions = displayFlagOptions;
    }

    public String getViewIds() {
        return viewIds;
    }

    public void setViewIds(String viewIds) {
        this.viewIds = viewIds;
    }
}