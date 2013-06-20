package com.sino.sinoflow.user.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * Title: SfGroup
 * Description: �����Զ�����DTO���ݴ������
 * Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006
 * Company: ����˼ŵ����Ϣ�Ƽ����޹�˾
 *@author ����ʤ
 * @version 1.0
 * �޸��ˣ��׼� 
 * �޸����ڣ�2008.8.20
 */

public class SfGroupDTO extends CheckBoxDTO {
	private int groupId = 0;

	private String groupName = "";

	private String enabled = "";

	private String projectName = "";

	private int parentId = 0;

	private String parentName = "";

	private String groupDesc = "";

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
