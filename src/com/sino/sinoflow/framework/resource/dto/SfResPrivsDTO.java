package com.sino.sinoflow.framework.resource.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: SfResPrivs</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfResPrivsDTO extends CheckBoxDTO {
	private int roleId = 0;
	private int systemId = 0;
	private String creationDate = "";
	private int createdBy = -1;
	private String lastUpdateDate = "";
	private int lastUpdateBy = -1;
	private String roleName = "";
	private String resName = "";
    private String resId = "";
    private String groupName = "";
    private String hiValue="";

    public String getHiValue() {
        return hiValue;
    }

    public void setHiValue(String hiValue) {
        this.hiValue = hiValue;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
	 * ���ܣ�����DTO���� roleId
	 * @param roleId String
	 */
	public void setRoleId(int roleId){
		this.roleId = roleId;
	}

	/**
	 * ���ܣ�����DTO���� resId
	 * @param systemId String
	 */
	public void setResId(int systemId){
		this.systemId = systemId;
	}

	/**
	 * ���ܣ�����DTO���� creationDate
	 * @param creationDate Timestamp
	 * @throws com.sino.base.exception.CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException {
/*
        if(!StrUtil.isEmpty(creationDate)){
			SimpleCalendar cal = new SimpleCalendar(creationDate);
			this.creationDate = cal.getSQLTimestamp();
		}
*/
        this.creationDate = creationDate;
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
/*
        if(!StrUtil.isEmpty(lastUpdateDate)){
			SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
			this.lastUpdateDate = cal.getSQLTimestamp();
		}
*/
        this.lastUpdateDate = lastUpdateDate;
    }

	/**
	 * ���ܣ�����DTO���� lastUpdateBy
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * ���ܣ���ȡDTO���� roleId
	 * @return String
	 */
	public int getRoleId(){
		return this.roleId;
	}

	/**
	 * ���ܣ���ȡDTO���� resId
	 * @return String
	 */
	public int getSystemId(){
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡDTO���� creationDate
	 * @return Timestamp
	 */
	public String getCreationDate(){
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
	public String getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateBy
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getResName() {
		return resName;
	}

	public String getRoleName() {
		return roleName;
	}

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getResId() {
        return resId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
