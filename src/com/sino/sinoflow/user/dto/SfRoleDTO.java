package com.sino.sinoflow.user.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: SfRole</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfRoleDTO extends CheckBoxDTO{
	
	private int roleId = 0;
	private String roleName = "";
	private String description = "";
	private int sortno = 0;
	private String isInner = "";
	private int createdBy = -1;
	private int lastUpdateBy = -1;
	private String projectName;
	private String roleDesc = "";
	private String enabled = "";
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * ���ܣ�����DTO���� roleId
	 * @param roleId String
	 */
	public void setRoleId(int roleId){
		this.roleId = roleId;
	}

	/**
	 * ���ܣ�����DTO���� rolename
	 * @param roleName String
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	/**
	 * ���ܣ�����DTO���� description
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ�����DTO���� sortno
	 * @param sortno String
	 */
	public void setSortno(int sortno){
		this.sortno = sortno;
	}

	/**
	 * ���ܣ�����DTO���� isInner
	 * @param isInner String
	 */
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}


	/**
	 * ���ܣ�����DTO���� createdBy
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}


	/**
	 * ���ܣ�����DTO���� lastUpdateBy
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡDTO���� roleId
	 * @return String
	 */
	public int getRoleId(){
		return this.roleId;
	}

	/**
	 * ���ܣ���ȡDTO���� rolename
	 * @return String
	 */
	public String getRoleName(){
		return this.roleName;
	}

	/**
	 * ���ܣ���ȡDTO���� description
	 * @return String
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * ���ܣ���ȡDTO���� sortno
	 * @return String
	 */
	public int getSortno(){
		return this.sortno;
	}

	/**
	 * ���ܣ���ȡDTO���� isInner
	 * @return String
	 */
	public String getIsInner(){
		return this.isInner;
	}

	/**
	 * ���ܣ���ȡDTO���� createdBy
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}


	/**
	 * ���ܣ���ȡDTO���� lastUpdateBy
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}