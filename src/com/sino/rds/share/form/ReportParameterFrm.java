package com.sino.rds.share.form;

import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.foundation.web.component.WebRadio;


/**
 * <p>Title: �����ѯ���� RDS_REPORT_PARAMETER</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ReportParameterFrm extends ModelFieldFrm {

    private String fieldAlignName = "";
    private String inputTypeName = "";
    private String nullAble = "";
    private String nullAbleName = "";
    private String sortNumber = "";
    private String reportId = "";
    private String defaultValue = "";
    private String reportCode = "";
    private String reportName = "";
    private String parameterUrl = "";
    private String lookUpName = "";
    private String lovId = "";
    private String lovName = "";
    private String inputType = "";
    private String fieldAlign = "";
    private String fieldWidth = "";
    private String fieldIds = "";
    private String parameterId = "";
    private String parameterIds = "";

    private WebOptions fieldAlignOptions = null;
    private WebOptions inputTypeOptions = null;
    private WebOptions nullAbleOptions = null;
    private WebRadio nullAbleRadio = null;
    private WebOptions reportOptions = null;
    private WebOptions lovOptions = null;
    private WebOptions lookUpOptions = null;

    private String calendarType = "";
    private String calendarTypeName = "";
    private WebOptions calendarTypeOptions = null;

    public ReportParameterFrm() {
        super();
        primaryKeyName = "parameterId";
    }

    /**
     * ���ܣ����ñ����ѯ�������� ����ID
     *
     * @param parameterId String
     */
    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    /**
     * ���ܣ����ñ����ѯ�������� �ֶο��
     *
     * @param fieldWidth String
     */
    public void setFieldWidth(String fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    /**
     * ���ܣ����ñ����ѯ�������� ˮƽ����
     *
     * @param fieldAlign String
     */
    public void setFieldAlign(String fieldAlign) {
        this.fieldAlign = fieldAlign;
    }

    /**
     * ���ܣ����ñ����ѯ�������� �������ͣ�1����ͨ��2��������3��LOV��4��LookUp��5��URL�������ڷ��ز�����
     *
     * @param inputType String
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    /**
     * ���ܣ����ñ����ѯ�������� ֵ�б��ѯSQL
     *
     * @param lovId String
     */
    public void setLovId(String lovId) {
        this.lovId = lovId;
    }

    /**
     * ���ܣ����ñ����ѯ�������� ��������URL
     *
     * @param parameterUrl String
     */
    public void setParameterUrl(String parameterUrl) {
        this.parameterUrl = parameterUrl;
    }

    /**
     * ���ܣ����ñ����ѯ�������� ����ID
     *
     * @param reportId String
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� ����ID
     *
     * @return String
     */
    public String getParameterId() {
        return this.parameterId;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� �ֶο��
     *
     * @return String
     */
    public String getFieldWidth() {
        return this.fieldWidth;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� ˮƽ����
     *
     * @return String
     */
    public String getFieldAlign() {
        return this.fieldAlign;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� �������ͣ�1����ͨ��2��������3��LOV��4��LookUp��5��URL�������ڷ��ز�����
     *
     * @return String
     */
    public String getInputType() {
        return this.inputType;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� ֵ�б��ѯSQL
     *
     * @return String
     */
    public String getLovId() {
        return this.lovId;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� ��������URL
     *
     * @return String
     */
    public String getParameterUrl() {
        return this.parameterUrl;
    }

    /**
     * ���ܣ���ȡ�����ѯ�������� ����ID
     *
     * @return String
     */
    public String getReportId() {
        return this.reportId;
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

    public String getInputTypeName() {
        return inputTypeName;
    }

    public void setInputTypeName(String inputTypeName) {
        this.inputTypeName = inputTypeName;
    }

    public WebOptions getInputTypeOptions() {
        return inputTypeOptions;
    }

    public void setInputTypeOptions(WebOptions inputTypeOptions) {
        this.inputTypeOptions = inputTypeOptions;
    }

    public String getNullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    public WebOptions getNullAbleOptions() {
        return nullAbleOptions;
    }

    public void setNullAbleOptions(WebOptions nullAbleOptions) {
        this.nullAbleOptions = nullAbleOptions;
    }

    public WebOptions getReportOptions() {
        return reportOptions;
    }

    public void setReportOptions(WebOptions reportOptions) {
        this.reportOptions = reportOptions;
    }

    public String getParameterIds() {
        return parameterIds;
    }

    public void setParameterIds(String parameterIds) {
        this.parameterIds = parameterIds;
    }

    public String getFieldIds() {
        return fieldIds;
    }

    public void setFieldIds(String fieldIds) {
        this.fieldIds = fieldIds;
    }

    public WebRadio getNullAbleRadio() {
        return nullAbleRadio;
    }

    public void setNullAbleRadio(WebRadio nullAbleRadio) {
        this.nullAbleRadio = nullAbleRadio;
    }

    public String getLookUpName() {
        return lookUpName;
    }

    public void setLookUpName(String lookUpName) {
        this.lookUpName = lookUpName;
    }

    public String getLovName() {
        return lovName;
    }

    public void setLovName(String lovName) {
        this.lovName = lovName;
    }

    public WebOptions getLovOptions() {
        return lovOptions;
    }

    public void setLovOptions(WebOptions lovOptions) {
        this.lovOptions = lovOptions;
    }

    public WebOptions getLookUpOptions() {
        return lookUpOptions;
    }

    public void setLookUpOptions(WebOptions lookUpOptions) {
        this.lookUpOptions = lookUpOptions;
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

    public String getNullAbleName() {
        return nullAbleName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setNullAbleName(String nullAbleName) {
        this.nullAbleName = nullAbleName;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public String getCalendarTypeName() {
        return calendarTypeName;
    }

    public void setCalendarTypeName(String calendarTypeName) {
        this.calendarTypeName = calendarTypeName;
    }

    public WebOptions getCalendarTypeOptions() {
        return calendarTypeOptions;
    }

    public void setCalendarTypeOptions(WebOptions calendarTypeOptions) {
        this.calendarTypeOptions = calendarTypeOptions;
    }
}