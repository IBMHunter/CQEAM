package com.sino.sinoflow.framework.security.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �Ñ�ְλȨ����Ϣ SfUserAuthority</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfUserAuthorityDTO extends CheckBoxDTO{

	private int authorityId = 0;
	private int userId = 0;
	private String department = "";
	private String position = "";
	private String groupName = "";
	private String roleName = "";

	public SfUserAuthorityDTO() {
		super();
	}

	/**
	 * ���ܣ������Ñ�ְλȨ����Ϣ���� �Ñ�Ȩ�� ID
	 * @param authorityId AdvancedNumber
	 */
	public void setAuthorityId(int authorityId){
		this.authorityId = authorityId;
	}

	/**
	 * ���ܣ������Ñ�ְλȨ����Ϣ���� �Ñ� ID
	 * @param userId AdvancedNumber
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ������Ñ�ְλȨ����Ϣ���� ����
	 * @param department String
	 */
	public void setDepartment(String department){
		this.department = department;
	}

	/**
	 * ���ܣ������Ñ�ְλȨ����Ϣ���� ְλ
	 * @param position String
	 */
	public void setPosition(String position){
		this.position = position;
	}

	/**
	 * ���ܣ������Ñ�ְλȨ����Ϣ���� ���
	 * @param groupName String
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	/**
	 * ���ܣ������Ñ�ְλȨ����Ϣ���� ��ɫ
	 * @param roleName String
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}


	/**
	 * ���ܣ���ȡ�Ñ�ְλȨ����Ϣ���� �Ñ�Ȩ�� ID
	 * @return AdvancedNumber
	 */
	public int getAuthorityId() {
		return this.authorityId;
	}

	/**
	 * ���ܣ���ȡ�Ñ�ְλȨ����Ϣ���� �Ñ� ID
	 * @return AdvancedNumber
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * ���ܣ���ȡ�Ñ�ְλȨ����Ϣ���� ����
	 * @return String
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * ���ܣ���ȡ�Ñ�ְλȨ����Ϣ���� ְλ
	 * @return String
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 * ���ܣ���ȡ�Ñ�ְλȨ����Ϣ���� ���
	 * @return String
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * ���ܣ���ȡ�Ñ�ְλȨ����Ϣ���� ��ɫ
	 * @return String
	 */
	public String getRoleName() {
		return this.roleName;
	}

}
