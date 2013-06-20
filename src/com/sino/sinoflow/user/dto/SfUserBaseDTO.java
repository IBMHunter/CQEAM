package com.sino.sinoflow.user.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: �Ñ���Ϣ SfUser</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class SfUserBaseDTO extends BaseUserDTO {

    private int userId = 0;
    private String username = "";
    private String loginName = "";
    private String password = "";
    private String employeeId = "";
    private String employeeNumber = "";
    private int displayOrder = 0;
    private String officeTel = "";
    private String fax = "";
    private String mobilePhone = "";
    private String email = "";
    private String organization = "";
    private int organizationId = 0;
    private int orgId = 0;
    private boolean sysAdmin = false;
    private boolean provinceUser = false;
    private int workScheduleId = 0;

    private String groupName = "";
    private String projectName = "";
    private String roleName = "";
    private String categoryCode = "";
    private DTOSet authoritys = null;

    private String enabled = "";
    private SimpleCalendar startDate;
    private SimpleCalendar endDate;
    private SimpleCalendar disableDate;

    public SfUserBaseDTO() {
        super();
        authoritys = new DTOSet();
        startDate=new SimpleCalendar();
        endDate=new SimpleCalendar();
        disableDate=new SimpleCalendar();
    }
    	public SimpleCalendar getDisableDate() {
		return disableDate;
	}

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setDisableDate(String disableDate) throws CalendarException {
		this.disableDate.setCalendarValue(disableDate);
	}
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
 
    public SimpleCalendar getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) throws CalendarException {
        if (!StrUtil.isEmpty(startDate)) {
            this.startDate.setCalendarValue(startDate);
        }
    }

    public SimpleCalendar getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String disableDate) throws CalendarException {
        if (!StrUtil.isEmpty(disableDate)) {
            this.endDate.setCalendarValue(disableDate);
        }
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� �û� ID
     * @param userId String
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� ��½�ʺ�
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� �û���ʵ����
     * @param loginName String
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� ��½����
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� Ա�����
     * @param employeeId String
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� �칫�绰
     * @param officeTel String
     */
    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� �������
     * @param fax String
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� �ƶ��绰
     * @param mobilePhone String
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� E-Mail ��ַ
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * ���ܣ������Ñ���Ϣ���� ��˾
     * @param organization String
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public void setSysAdmin(boolean sysAdmin) {
        this.sysAdmin = sysAdmin;
    }

    public void setProvinceUser(boolean provinceUser) {
        this.provinceUser = provinceUser;
    }

    public void setWorkScheduleId(int workScheduleId) {
        this.workScheduleId = workScheduleId;
    }
/*
	public void setUserGroups(DTOSet userGroups) {
		this.userGroups = userGroups;
	}
*/

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� �û� ID
     * @return String
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� ��½�ʺ�
     * @return String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� �û���ʵ����
     * @return String
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� ��½����
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� Ա�����
     * @return String
     */
    public String getEmployeeId() {
        return this.employeeId;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� �칫�绰
     * @return String
     */
    public String getOfficeTel() {
        return this.officeTel;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� �������
     * @return String
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� �ƶ��绰
     * @return String
     */
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� E-Mail ��ַ
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * ���ܣ���ȡ�Ñ���Ϣ���� ��˾
     * @return String
     */
    public String getOrganization() {
        return this.organization;
    }

    public int getOrganizationId() {
        return this.organizationId;
    }

    public boolean isSysAdmin() {
        return sysAdmin;
    }

    public boolean isProvinceUser() {
        return provinceUser;
    }

    public int getWorkScheduleId() {
        return workScheduleId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public String getProjectName() {
        return projectName;
    }

    public DTOSet getAuthoritys() {
        return authoritys;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setAuthoritys(DTOSet authoritys) {
        this.authoritys = authoritys;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId; 
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /*
	public DTOSet getUserGroups() {
		return userGroups;
	}
*/
}
