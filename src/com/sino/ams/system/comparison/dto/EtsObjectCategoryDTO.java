package com.sino.ams.system.comparison.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) EtsObjectCategory</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsObjectCategoryDTO extends CheckBoxDTO{

	private String systemid="" ;
	private String objectCategory="" ;
	private String searchCategory="" ;
	private int organizationId ;
	private String remark = "";
	private SimpleCalendar creationDate = null;
	private int createdBy ;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy ;
    private int company;

    public EtsObjectCategoryDTO() {
		super();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
	}


	/**
	 * ���ܣ����ù�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM)���� REMARK
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ù�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}


	/**
	 * ���ܣ����ù�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}


	/**
	 * ���ܣ���ȡ��������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM)���� REMARK
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ��������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}


	/**
	 * ���ܣ���ȡ��������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}


	public String getSystemid() {
		return systemid;
	}

	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}


	public String getObjectCategory() {
		return objectCategory;
	}


	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}


	public String getSearchCategory() {
		return searchCategory;
	}


	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}


	public int getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public int getLastUpdateBy() {
		return lastUpdateBy;
	}


	public void setLastUpdateBy(int lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}


	public int getCompany() {
		return company;
	}


	public void setCompany(int company) {
		this.company = company;
	}

//
//	public void setCreationDate(SimpleCalendar creationDate) {
//		this.creationDate = creationDate;
//	}
//
//
//	public void setLastUpdateDate(SimpleCalendar lastUpdateDate) {
//		this.lastUpdateDate = lastUpdateDate;
//	}

}