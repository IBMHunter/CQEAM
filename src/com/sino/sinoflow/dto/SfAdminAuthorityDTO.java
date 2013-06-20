package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ����ԱȨ����Ϣ SfAdminAuthority</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfAdminAuthorityDTO extends CheckBoxDTO{

	private int adminId = 0;
	private int userId = 0;
	private String projectName = "";
	private String groupName = "";
	private String loginName = "";
	private int orgId = 0;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public SfAdminAuthorityDTO() {
		super();
	}

	/**
	 * ���ܣ����ù���ԱȨ����Ϣ���� ����Ա ID
	 * @param adminId String
	 */
	public void setAdminId(int adminId){
		this.adminId = adminId;
	}

	/**
	 * ���ܣ����ù���ԱȨ����Ϣ���� ����Ա���û� ID
	 * @param userId String
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ����ù���ԱȨ����Ϣ���� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ����ù���ԱȨ����Ϣ���� �������
	 * @param groupName String
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * ���ܣ���ȡ����ԱȨ����Ϣ���� ����Ա ID
	 * @return String
	 */
	public int getAdminId() {
		return this.adminId;
	}

	/**
	 * ���ܣ���ȡ����ԱȨ����Ϣ���� ����Ա���û� ID
	 * @return String
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * ���ܣ���ȡ����ԱȨ����Ϣ���� ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡ����ԱȨ����Ϣ���� �������
	 * @return String
	 */
	public String getGroupName() {
		return this.groupName;
	}

	public int getOrgId() {
		return orgId;
	}

}
