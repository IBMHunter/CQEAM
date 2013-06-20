package com.sino.rds.share.form;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.config.ConfigLoader;
import com.sino.base.config.QueryConfig;
import com.sino.base.exception.CalendarException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.foundation.web.component.WebRadio;
import com.sino.rds.share.constant.RDSDictionaryList;

/**
 * <p>Title: ������� RDS_REPORT_DEFINE</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ReportDefineFrm extends DataModelFrm {

    private String reportId = "";
    private String reportIds = "";
    private String reportCode = "";
    private String reportName = "";
    private String reportDesc = "";
    private String reportType = "";
    private String reportTypeName = "";
    private String drillDownType = "";
    private String drillDownTypeName = "";
    private String supportDrillDown = "N";
    private String supportDrillDownName = "N";
    private String drillDownUrl = "";
    private String drillDownParameters = "";
    private String drillDownReport = "";
    private String drillDownReportName = "";
    private String reportWidth = "";
    private String displayType = "";
    private String displayTypeName = "";
    private String pageSize = "";
    private String countPages = "";
    private String countPagesName = "";
    private String sumPosition = "";
    private String sumPositionName = "";
    private String supportSubSummary = "";
    private String supportSubSummaryName = "";

    private WebOptions reportTypeOptions = null;
    private WebOptions sumPositionOptions = null;
    private WebOptions drillTypeOptions = null;
    private WebOptions drillDownReportOptions = null;
    private WebRadio supportDrillRadio = null;
    private WebRadio drillTypeRadio = null;
    private WebRadio displayTypeRadio = null;
    private WebRadio countPagesRadio = null;
    private WebRadio supportSubSummaryRadio = null;

    private String actualSql = "";
    private String aboveDimensionSql = "";
    private String leftDimensionSql = "";
    private String bottomExpressionSql = "";
    private String verticalExpressionSql = "";
    private String optimizedSql = "";//ͨ��ά������¼����Ż�SQL
    private String checkedSttus = "";//������ƺϷ��Լ��״̬

    private String sqlModified = "";
    private SimpleCalendar modifiedTime = null; //SQL�����仯��ʱ��
    private SimpleCalendar checkedTime = null; //������ƺϷ��Լ��ʱ��
    private SimpleCalendar optimizedTime = null; //SQL�Ż�ʱ��

    private String clientWidth = "";

    public ReportDefineFrm() {
        super();
        primaryKeyName = "reportId";
        modifiedTime = new SimpleCalendar();
        checkedTime = new SimpleCalendar();
        optimizedTime = new SimpleCalendar();
        initPageSize();
    }

    private void initPageSize() {
        try {
            QueryConfig queryConfig = ConfigLoader.loadQueryConfig();
            if (queryConfig != null) {
                setPageSize(String.valueOf(queryConfig.getPageSize()));
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }

    /**
     * ���ܣ����ñ���������� ����ID
     *
     * @param reportId String
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * ���ܣ����ñ���������� �������
     *
     * @param reportCode String
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * ���ܣ����ñ���������� ��������
     *
     * @param reportName String
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * ���ܣ����ñ���������� ��������
     *
     * @param reportType String
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * ���ܣ����ñ���������� ���������ֵ��URL
     *
     * @param drillDownUrl String
     */
    public void setDrillDownUrl(String drillDownUrl) {
        this.drillDownUrl = drillDownUrl;
    }

    /**
     * ���ܣ����ñ���������� ���������ֵʱ���ݸ�URL�Ĳ���
     *
     * @param drillDownParameters String
     */
    public void setDrillDownParameters(String drillDownParameters) {
        this.drillDownParameters = drillDownParameters;
    }

    /**
     * ���ܣ����ñ���������� ��̽��������ID
     *
     * @param drillDownReport String
     */
    public void setDrillDownReport(String drillDownReport) {
        this.drillDownReport = drillDownReport;
    }


    /**
     * ���ܣ���ȡ����������� ����ID
     *
     * @return String
     */
    public String getReportId() {
        return this.reportId;
    }

    /**
     * ���ܣ���ȡ����������� �������
     *
     * @return String
     */
    public String getReportCode() {
        return this.reportCode;
    }

    /**
     * ���ܣ���ȡ����������� ��������
     *
     * @return String
     */
    public String getReportName() {
        return this.reportName;
    }

    /**
     * ���ܣ���ȡ����������� ��������
     *
     * @return String
     */
    public String getReportType() {
        return this.reportType;
    }

    /**
     * ���ܣ���ȡ����������� ���������ֵ��URL
     *
     * @return String
     */
    public String getDrillDownUrl() {
        return this.drillDownUrl;
    }

    /**
     * ���ܣ���ȡ����������� ���������ֵʱ���ݸ�URL�Ĳ���
     *
     * @return String
     */
    public String getDrillDownParameters() {
        return this.drillDownParameters;
    }

    /**
     * ���ܣ���ȡ����������� ��̽��������ID
     *
     * @return String
     */
    public String getDrillDownReport() {
        return this.drillDownReport;
    }

    public String getReportWidth() {
        return reportWidth;
    }

    public void setReportWidth(String reportWidth) {
        this.reportWidth = reportWidth;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public WebOptions getReportTypeOptions() {
        return reportTypeOptions;
    }

    public void setReportTypeOptions(WebOptions reportTypeOptions) {
        this.reportTypeOptions = reportTypeOptions;
    }

    public String getDrillDownType() {
        return drillDownType;
    }

    public void setDrillDownType(String drillDownType) {
        this.drillDownType = drillDownType;
    }

    public String getSupportDrillDown() {
        return supportDrillDown;
    }

    public void setSupportDrillDown(String supportDrillDown) {
        this.supportDrillDown = supportDrillDown;
    }

    public WebOptions getDrillTypeOptions() {
        return drillTypeOptions;
    }

    public void setDrillTypeOptions(WebOptions drillTypeOptions) {
        this.drillTypeOptions = drillTypeOptions;
    }

    public WebRadio getSupportDrillRadio() {
        return supportDrillRadio;
    }

    public void setSupportDrillRadio(WebRadio supportDrillRadio) {
        this.supportDrillRadio = supportDrillRadio;
    }

    public String getSupportDrillDownName() {
        return supportDrillDownName;
    }

    public void setSupportDrillDownName(String supportDrillDownName) {
        this.supportDrillDownName = supportDrillDownName;
    }

    public String getDrillDownTypeName() {
        return drillDownTypeName;
    }

    public void setDrillDownTypeName(String drillDownTypeName) {
        this.drillDownTypeName = drillDownTypeName;
    }

    public String getDrillDownReportName() {
        return drillDownReportName;
    }

    public void setDrillDownReportName(String drillDownReportName) {
        this.drillDownReportName = drillDownReportName;
    }

    public WebRadio getDrillTypeRadio() {
        return drillTypeRadio;
    }

    public void setDrillTypeRadio(WebRadio drillTypeRadio) {
        this.drillTypeRadio = drillTypeRadio;
    }

    public WebOptions getDrillDownReportOptions() {
        return drillDownReportOptions;
    }

    public void setDrillDownReportOptions(WebOptions drillDownReportOptions) {
        this.drillDownReportOptions = drillDownReportOptions;
    }

    public String getReportIds() {
        return reportIds;
    }

    public void setReportIds(String reportIds) {
        this.reportIds = reportIds;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        if (StrUtil.isInteger(pageSize)) {
            int tempPageSize = Integer.parseInt(pageSize);
            if(tempPageSize > 0){
                this.pageSize = pageSize;
            }
        }
    }

    public String getCountPages() {
        return countPages;
    }

    public void setCountPages(String countPages) {
        this.countPages = countPages;
    }

    public WebRadio getDisplayTypeRadio() {
        return displayTypeRadio;
    }

    public void setDisplayTypeRadio(WebRadio displayTypeRadio) {
        this.displayTypeRadio = displayTypeRadio;
    }

    public WebRadio getCountPagesRadio() {
        return countPagesRadio;
    }

    public void setCountPagesRadio(WebRadio countPagesRadio) {
        this.countPagesRadio = countPagesRadio;
    }

    public boolean isDisplayAll() {
        return (displayType.equals(RDSDictionaryList.DISPLAY_TYPE_ALL));
    }

    public String getDisplayTypeName() {
        return displayTypeName;
    }

    public void setDisplayTypeName(String displayTypeName) {
        this.displayTypeName = displayTypeName;
    }

    public String getCountPagesName() {
        return countPagesName;
    }

    public void setCountPagesName(String countPagesName) {
        this.countPagesName = countPagesName;
    }

    public String getActualSql() {
        return actualSql;
    }

    public void setActualSql(String actualSql) {
        this.actualSql = actualSql;
    }

    public String getSqlModified() {
        return sqlModified;
    }

    public void setSqlModified(String sqlModified) {
        this.sqlModified = sqlModified;
    }

    public SimpleCalendar getModifiedTime() throws CalendarException {
        modifiedTime.setCalPattern(getCalPattern());
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) throws CalendarException {
        this.modifiedTime.setCalendarValue(modifiedTime);
    }

    public String getAboveDimensionSql() {
        return aboveDimensionSql;
    }

    public void setAboveDimensionSql(String aboveDimensionSql) {
        this.aboveDimensionSql = aboveDimensionSql;
    }

    public String getLeftDimensionSql() {
        return leftDimensionSql;
    }

    public void setLeftDimensionSql(String leftDimensionSql) {
        this.leftDimensionSql = leftDimensionSql;
    }

    public String getVerticalExpressionSql() {
        return verticalExpressionSql;
    }

    public void setVerticalExpressionSql(String verticalExpressionSql) {
        this.verticalExpressionSql = verticalExpressionSql;
    }

    public String getBottomExpressionSql() {
        return bottomExpressionSql;
    }

    public void setBottomExpressionSql(String bottomExpressionSql) {
        this.bottomExpressionSql = bottomExpressionSql;
    }

    public String getSumPosition() {
        return sumPosition;
    }

    public void setSumPosition(String sumPosition) {
        this.sumPosition = sumPosition;
    }

    public String getSumPositionName() {
        return sumPositionName;
    }

    public void setSumPositionName(String sumPositionName) {
        this.sumPositionName = sumPositionName;
    }

    public WebOptions getSumPositionOptions() {
        return sumPositionOptions;
    }

    public void setSumPositionOptions(WebOptions sumPositionOptions) {
        this.sumPositionOptions = sumPositionOptions;
    }

    public String getOptimizedSql() {
        return optimizedSql;
    }

    public void setOptimizedSql(String optimizedSql) {
        this.optimizedSql = optimizedSql;
    }

    public String getCheckedSttus() {
        return checkedSttus;
    }

    public void setCheckedSttus(String checkedSttus) {
        this.checkedSttus = checkedSttus;
    }

    public SimpleCalendar getCheckedTime() throws CalendarException {
        checkedTime.setCalPattern(getCalPattern());
        return checkedTime;
    }

    public void setCheckedTime(String checkedTime) throws CalendarException {
        this.checkedTime.setCalendarValue(checkedTime);
    }

    public SimpleCalendar getOptimizedTime() throws CalendarException {
        optimizedTime.setCalPattern(getCalPattern());
        return optimizedTime;
    }

    public void setOptimizedTime(String optimizedTime) throws CalendarException {
        this.optimizedTime.setCalendarValue(optimizedTime);
    }

    public String getClientWidth() {
        return clientWidth;
    }

    public void setClientWidth(String clientWidth) {
        this.clientWidth = clientWidth;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getSupportSubSummary() {
        return supportSubSummary;
    }

    public void setSupportSubSummary(String supportSubSummary) {
        this.supportSubSummary = supportSubSummary;
    }

    public String getSupportSubSummaryName() {
        return supportSubSummaryName;
    }

    public void setSupportSubSummaryName(String supportSubSummaryName) {
        this.supportSubSummaryName = supportSubSummaryName;
    }

    public WebRadio getSupportSubSummaryRadio() {
        return supportSubSummaryRadio;
    }

    public void setSupportSubSummaryRadio(WebRadio supportSubSummaryRadio) {
        this.supportSubSummaryRadio = supportSubSummaryRadio;
    }

    public boolean isIntersectReport() {
        return (reportType.length() > 0 &&
                !reportType.equals(RDSDictionaryList.REPORT_TYPE_LIST));
    }
}