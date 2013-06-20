package com.sino.ams.plan.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: �����ƻ����� AmsWorkPlan</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsWorkPlanDTO extends CheckBoxDTO {

    private String planId = "";
    private String planName = "";
    private String planDesc = "";
    private int executeUser;
    private String executeUserName = "";


    private String planStatus = "";
    private Timestamp creationDate = null;
    private int createdBy;
    private Timestamp lastUpdateDate = null;
    private int lastUpdateBy;
    private String fromDate = null;
    private String toDate = null;
    private SimpleCalendar executeTime = null;
    private String strExecuteTime = "";
    private String loginName = "";
    private int organizationId;

    public AmsWorkPlanDTO() {
        this.executeTime = new SimpleCalendar();
    }

    public SimpleCalendar getExecuteTime() throws CalendarException {
        executeTime.setCalPattern(getCalPattern());
        return this.executeTime;
    }

    public void setExecuteTime(String executeTime) throws CalendarException {
        this.executeTime.setCalendarValue(executeTime);
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }
    /*  public Timestamp getExecuteTime() {
        return executeTime;
    }*/
/*	public void setExecuteTime(String executeTime) throws CalendarException{
		if(!StrUtil.isEmpty(executeTime)){
			SimpleCalendar cal = new SimpleCalendar(executeTime);
			this.executeTime = cal.getSQLTimestamp();
		}
	}*/

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param planId String
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param planName String
     */
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param planDesc String
     */
    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param executeUser String
     */
    public void setExecuteUser(int executeUser) {
        this.executeUser = executeUser;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param planStatus String
     */
    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param creationDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        if (!StrUtil.isEmpty(creationDate)) {
            SimpleCalendar cal = new SimpleCalendar(creationDate);
            this.creationDate = cal.getSQLTimestamp();
        }
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param lastUpdateDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        if (!StrUtil.isEmpty(lastUpdateDate)) {
            SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
            this.lastUpdateDate = cal.getSQLTimestamp();
        }
    }

    /**
     * ���ܣ����ù����ƻ��������� �ϴ��޸���
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }


    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public String getPlanId() {
        return this.planId;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public String getPlanName() {
        return this.planName;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public String getPlanDesc() {
        return this.planDesc;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public int getExecuteUser() {
        return this.executeUser;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public String getPlanStatus() {
        return this.planStatus;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return Timestamp
     */
    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return Timestamp
     */
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ�����ƻ��������� �ϴ��޸���
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /*public String getStrExecuteTime() throws DateException {
        if(executeTime != null){
            SimpleCalendar cal = new SimpleCalendar(executeTime);
            strExecuteTime = cal.getSimpleDate().getDateValue();
        }
        return strExecuteTime;
    }*/
}