package com.sino.sinoflow.user.dto;

/**
* <p>Title: SfUserRight</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfUserWithProjectDTO extends SfUserBaseDTO {
    private String projectName="";
	private String groupName = "";
	private String roleName = "";

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    public String getProjectName() {
        return projectName;
    }

    public String getGroupName() {
		return groupName;
	}

	public String getRoleName() {
		return roleName;
	}

}
