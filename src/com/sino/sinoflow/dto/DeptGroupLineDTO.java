package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ������� SfGroup</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class DeptGroupLineDTO extends CheckBoxDTO{

    private String isCheck = "";
    private String projectName = "";
    private String groupName = "";
    private String curGroupName = "";
    private int groupId = 0;
    private String deptName = "";
    private String deptId = "";
    private String parentName = "";
    private String parentId = "";
    private String displayOrder = "";
    private String secondDept = "";
    private String specialityDept = "";
    private int orgId = 0;

    public DeptGroupLineDTO() {
		super();

	}

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getIsCheck() {
        return this.isCheck;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	public String getGroupName() {
		return this.groupName;
	}

    public void setCurGroupName(String curGroupName) {
        this.curGroupName = curGroupName;
    }

    public String getCurGroupName() {
        return this.curGroupName;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDisplayOrder() {
        return this.displayOrder;
    }

    public void setSecondDept(String secondDept) {
        this.secondDept = secondDept;
    }

    public String getSecondDept() {
        return this.secondDept;
    }

    public void setSpecialityDept(String specialityDept) {
        this.specialityDept = specialityDept;
    }

    public String getSpeicalityDept() {
        return this.specialityDept;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return this.orgId;
    }
}