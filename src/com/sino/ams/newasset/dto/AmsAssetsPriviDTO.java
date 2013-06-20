package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * <p>Title: �ʲ�����Ȩ�ޱ�(EAM) AmsAssetsPrivi</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsPriviDTO extends CommonRecordDTO {

    private String userId = "";
    private String deptCode = "";
    private String roleId = "";
    private String companyCode = "";
    private int priviId;
    private String userName = "";
    private String roleName = "";
    private String deptName = "";
    private String companyName = "";
    private String provinceCode = "";
    private String loginName = "";
    private String srcPage = "";
    private boolean saved = true;

    private String faCategoryCode = "";
    private String faCategoryName = "";
    private String employeeNumber = "";

    private String existPriviOption = "";
    private String noPriviOption = "";
    private int groupId;
    
    private int organizationId;
    
	private String  record = "";
    /**
     * ���ܣ������ʲ�����Ȩ�ޱ�(EAM)���� �û�ID
     * @param userId String
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ���ܣ������ʲ�����Ȩ�ޱ�(EAM)���� ����ID
     * @param deptCode String
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * ���ܣ������ʲ�����Ȩ�ޱ�(EAM)���� ��ɫID
     * @param roleId String
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * ���ܣ������ʲ�����Ȩ�ޱ�(EAM)���� ��˾����
     * @param companyCode String
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * ���ܣ������ʲ�����Ȩ�ޱ�(EAM)���� ���к�
     * @param priviId String
     */
    public void setPriviId(int priviId) {
        this.priviId = priviId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setSrcPage(String srcPage) {
        this.srcPage = srcPage;
    }

    public void setFaCategoryCode(String faCategoryCode) {
        this.faCategoryCode = faCategoryCode;
    }

    public void setFaCategoryName(String faCategoryName) {
        this.faCategoryName = faCategoryName;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setExistPriviOption(String existPriviOption) {
        this.existPriviOption = existPriviOption;
    }

    public void setNoPriviOption(String noPriviOption) {
        this.noPriviOption = noPriviOption;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * ���ܣ���ȡ�ʲ�����Ȩ�ޱ�(EAM)���� �û�ID
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * ���ܣ���ȡ�ʲ�����Ȩ�ޱ�(EAM)���� ����ID
     * @return String
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * ���ܣ���ȡ�ʲ�����Ȩ�ޱ�(EAM)���� ��ɫID
     * @return String
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * ���ܣ���ȡ�ʲ�����Ȩ�ޱ�(EAM)���� ��˾����
     * @return String
     */
    public String getCompanyCode() {
        return this.companyCode;
    }

    /**
     * ���ܣ���ȡ�ʲ�����Ȩ�ޱ�(EAM)���� ���к�
     * @return String
     */
    public int getPriviId() {
        return this.priviId;
    }

    public String getUserName() {
        return userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public boolean isSaved() {
        return saved;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getSrcPage() {
        return srcPage;
    }

    public String getFaCategoryCode() {
        return faCategoryCode;
    }

    public String getFaCategoryName() {
        return faCategoryName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getExistPriviOption() {
        return existPriviOption;
    }

    public String getNoPriviOption() {
        return noPriviOption;
    }

    public int getGroupId() {
        return groupId;
    }

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

}
