package com.sino.ams.system.user.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: SfUserRight</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfUserRightDTO extends CheckBoxDTO{
	private int groupId ;
	private String roleId = "";
	private int userId ;
	private Timestamp creationDate = null;
	private int createdBy;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy;

	private String groupCode = "";
	private String groupName = "";
	private String roleName = "";
	private String groupProp = "";
	private String deptId = "";
	private String deptName = "";
	/**
	 * ���ܣ�����DTO���� groupId
	 * @param groupId String
	 */
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}

	/**
	 * ���ܣ�����DTO���� roleId
	 * @param roleId String
	 */
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	/**
	 * ���ܣ�����DTO���� userId
	 * @param userId String
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ�����DTO���� creationDate
	 * @param creationDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			SimpleCalendar cal = new SimpleCalendar(creationDate);
			this.creationDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ�����DTO���� createdBy
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����DTO���� lastUpdateDate
	 * @param lastUpdateDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
			this.lastUpdateDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ�����DTO���� lastUpdateBy
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public void setGroupName(String groupname) {
		this.groupName = groupname;
	}

	public void setRoleName(String rolename) {
		this.roleName = rolename;
	}

	public void setGroupProp(String groupProp) {
		this.groupProp = groupProp;
	}

	/**
	 * ���ܣ���ȡDTO���� groupId
	 * @return String
	 */
	public int getGroupId(){
		return this.groupId;
	}

	/**
	 * ���ܣ���ȡDTO���� roleId
	 * @return String
	 */
	public String getRoleId(){
		return this.roleId;
	}

	/**
	 * ���ܣ���ȡDTO���� userId
	 * @return String
	 */
	public int getUserId(){
		return this.userId;
	}

	/**
	 * ���ܣ���ȡDTO���� creationDate
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡDTO���� createdBy
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateDate
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateBy
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getGroupProp() {
		return groupProp;
	}

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
