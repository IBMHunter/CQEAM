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

public class SfGroupDTO extends CheckBoxDTO{

	private String projectName = "";
	private int groupId = 0;
	private String groupName = "";
	private int parentId = 0;
	private String groupDesc = "";
	private String enabled = "";

	public SfGroupDTO() {
		super();

	}

	/**
	 * ���ܣ���������������� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ���������������� ��� ID
	 * @param groupId String
	 */
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}

	/**
	 * ���ܣ���������������� �������
	 * @param groupName String
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	/**
	 * ���ܣ���������������� ����� ID
	 * @param parentId String
	 */
	public void setParentId(int parentId){
		this.parentId = parentId;
	}

	/**
	 * ���ܣ���������������� ����
	 * @param groupDesc String
	 */
	public void setGroupDesc(String groupDesc){
		this.groupDesc = groupDesc;
	}

	/**
	 * ���ܣ���������������� ENABLED
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}


	/**
	 * ���ܣ���ȡ����������� ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡ����������� ��� ID
	 * @return String
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * ���ܣ���ȡ����������� �������
	 * @return String
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * ���ܣ���ȡ����������� ����� ID
	 * @return String
	 */
	public int getParentId() {
		return this.parentId;
	}

	/**
	 * ���ܣ���ȡ����������� ����
	 * @return String
	 */
	public String getGroupDesc() {
		return this.groupDesc;
	}

	/**
	 * ���ܣ���ȡ����������� ENABLED
	 * @return String
	 */
	public String getEnabled() {
		return this.enabled;
	}

}