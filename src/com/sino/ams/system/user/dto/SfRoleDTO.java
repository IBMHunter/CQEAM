package com.sino.ams.system.user.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: SfRole</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfRoleDTO extends CheckBoxDTO{
	private String roleId = "";
	private String rolename = "";
	private String description = "";
	private String sortno = "";
	private String isInner = "";
	private Timestamp creationDate = null;
	private int createdBy;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy ;
	/**
	 * ���ܣ�����DTO���� roleId
	 * @param roleId String
	 */
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	/**
	 * ���ܣ�����DTO���� rolename
	 * @param rolename String
	 */
	public void setRolename(String rolename){
		this.rolename = rolename;
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
	public void setSortno(String sortno){
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

	/**
	 * ���ܣ���ȡDTO���� roleId
	 * @return String
	 */
	public String getRoleId(){
		return this.roleId;
	}

	/**
	 * ���ܣ���ȡDTO���� rolename
	 * @return String
	 */
	public String getRolename(){
		return this.rolename;
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
	public String getSortno(){
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

}