package com.sino.rds.share.form;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;
import com.sino.rds.appbase.form.RDSBaseFrm;


/**
 * <p>Title: �����ѯȨ�� RDS_REPORT_AUTHORITY</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ReportAuthorityFrm extends RDSBaseFrm {

    private String authorityId = "";
    private String reportId = "";
    private String roleId = "";
    private String userId = "";
    private SimpleCalendar effectiveStartDate = null;
    private SimpleCalendar effectiveEndDate = null;

    private String reportName = "";
    private String roleName = "";
    private String userName = "";

    public ReportAuthorityFrm() {
        super();
        primaryKeyName = "authorityId";
        this.effectiveStartDate = new SimpleCalendar();
        this.effectiveEndDate = new SimpleCalendar();
    }


    /**
     * ���ܣ����ñ����ѯȨ������ Ȩ��ID
     *
     * @param authorityId String
     */
    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * ���ܣ����ñ����ѯȨ������ ����ID
     *
     * @param reportId String
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * ���ܣ����ñ����ѯȨ������ ��ɫID
     *
     * @param roleId String
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * ���ܣ����ñ����ѯȨ������ �û�ID
     *
     * @param userId String
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ���ܣ����ñ����ѯȨ������ ��Ч����
     *
     * @param effectiveStartDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setEffectiveStartDate(String effectiveStartDate) throws CalendarException {
        this.effectiveStartDate.setCalendarValue(effectiveStartDate);
    }

    /**
     * ���ܣ����ñ����ѯȨ������ ʧЧ����
     *
     * @param effectiveEndDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setEffectiveEndDate(String effectiveEndDate) throws CalendarException {
        this.effectiveEndDate.setCalendarValue(effectiveEndDate);
    }


    /**
     * ���ܣ���ȡ�����ѯȨ������ Ȩ��ID
     *
     * @return String
     */
    public String getAuthorityId() {
        return this.authorityId;
    }

    /**
     * ���ܣ���ȡ�����ѯȨ������ ����ID
     *
     * @return String
     */
    public String getReportId() {
        return this.reportId;
    }

    /**
     * ���ܣ���ȡ�����ѯȨ������ ��ɫID
     *
     * @return String
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * ���ܣ���ȡ�����ѯȨ������ �û�ID
     *
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * ���ܣ���ȡ�����ѯȨ������ ��Ч����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getEffectiveStartDate() throws CalendarException {
        effectiveStartDate.setCalPattern(getCalPattern());
        return this.effectiveStartDate;
    }

    /**
     * ���ܣ���ȡ�����ѯȨ������ ʧЧ����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getEffectiveEndDate() throws CalendarException {
        effectiveEndDate.setCalPattern(getCalPattern());
        return this.effectiveEndDate;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}