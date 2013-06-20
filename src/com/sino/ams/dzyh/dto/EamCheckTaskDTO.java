package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �̵������ EamCheckTask</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EamCheckTaskDTO extends CommonRecordDTO{

	private String checkTaskId = "";
	private String taskName = "";
	private String checkType = "";
	private String checkTypeValue = "";
	private String checkTypeOpt = "";
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String remark = "";
	private String taskStatus = "";
	private String taskStatusValue = "";
	private String taskStatusOpt = "";
	private String organizationName = "";
	private String createdUser = "";
	private String updateUser = "";
	private String orgOption = "";

	private SimpleCalendar fromDate = null;
	private SimpleCalendar toDate = null;
	private String objectCategory = "";

	public EamCheckTaskDTO() {
		super();
		this.fromDate = new SimpleCalendar();
		this.toDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ������̵���������� �̵�����ID
	 * @param checkTaskId String
	 */
	public void setCheckTaskId(String checkTaskId){
		this.checkTaskId = checkTaskId;
	}

	/**
	 * ���ܣ������̵���������� �̵���������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	/**
	 * ���ܣ������̵���������� �̵����ͣ�DZYH/ASSETS/YQYB��
	 * @param checkType String
	 */
	public void setCheckType(String checkType){
		this.checkType = checkType;
	}

	/**
	 * ���ܣ������̵���������� OU��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ������̵���������� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public void setTaskStatusValue(String taskStatusValue) {

		this.taskStatusValue = taskStatusValue;
	}

	public void setTaskStatusOpt(String taskStatusOpt) {
		this.taskStatusOpt = taskStatusOpt;
	}

	public void setCheckTypeValue(String checkTypeValue) {
		this.checkTypeValue = checkTypeValue;
	}

	public void setOrgOption(String orgOption) {
		this.orgOption = orgOption;
	}

	public void setCheckTypeOpt(String checkTypeOpt) {
		this.checkTypeOpt = checkTypeOpt;
	}

	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}

	/**
	 * ���ܣ����ÿ�ʼ����
	 * @param fromDate String
	 * @throws CalendarException
	 */
	public void setFromDate(String fromDate) throws CalendarException {
		this.fromDate.setCalendarValue(fromDate);
	}


	/**
	 * ���ܣ����ý�������
	 * @param toDate String
	 * @throws CalendarException
	 */
	public void setToDate(String toDate) throws CalendarException {
		this.toDate.setCalendarValue(toDate);
	}


	/**
	 * ���ܣ���ȡ�̵���������� �̵�����ID
	 * @return String
	 */
	public String getCheckTaskId() {
		return this.checkTaskId;
	}

	/**
	 * ���ܣ���ȡ�̵���������� �̵���������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * ���ܣ���ȡ�̵���������� �̵����ͣ�DZYH/ASSETS/YQYB��
	 * @return String
	 */
	public String getCheckType() {
		return this.checkType;
	}

	/**
	 * ���ܣ���ȡ�̵���������� OU��֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ�̵���������� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public String getTaskStatusValue() {
		return taskStatusValue;
	}

	public String getTaskStatusOpt() {
		return taskStatusOpt;
	}

	public String getCheckTypeValue() {
		if(checkTypeValue.equals("")){
			String[] list1 = LvecDicts.TASK_TYPE1_LIST;
			String[] list2 = LvecDicts.TASK_TYPE2_LIST;
			for(int i = 0; i < list1.length; i++){
				if(checkType.equals(list1[i])){
					checkTypeValue = list2[i];
					break;
				}
			}
		}
		return checkTypeValue;
	}

	public String getOrgOption() {
		return orgOption;
	}

	public String getCheckTypeOpt() {
		return checkTypeOpt;
	}

	/**
	 * ���ܣ���ȡ��ʼ����
	 * @return SimpleCalendar
	 * @throws CalendarException
	 */
	public SimpleCalendar getFromDate() throws CalendarException {
		fromDate.setCalPattern(getCalPattern());
		return fromDate;
	}

	/**
	 * ���ܣ���ȡ��������
	 * @return SimpleCalendar
	 * @throws CalendarException
	 */
	public SimpleCalendar getToDate() throws CalendarException {
		toDate.setCalPattern(getCalPattern());
		return toDate;
	}

	public String getObjectCategory() {
		return objectCategory;
	}

	public SimpleCalendar getSQLToDate(){
		return getLastValueOfDate("toDate");
	}
}
