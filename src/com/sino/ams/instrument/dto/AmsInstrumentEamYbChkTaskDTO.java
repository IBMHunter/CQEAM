package com.sino.ams.instrument.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class AmsInstrumentEamYbChkTaskDTO extends CommonRecordDTO {

	private String taskId = ""; //����ID
	private String taskName = ""; //��������
	private String remark = ""; //��ע
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE; //OU��֯ID
	private String company = ""; //��˾����
	private String ouOption = "";//��˾��Ϣ�����������б�
	
	private String nameExist = ""; //�����������Ƿ��Ѿ�����
	
	private SimpleCalendar startDate = null; //��ʼʱ��
	private SimpleCalendar endDate = null; //����ʱ��
	
	public AmsInstrumentEamYbChkTaskDTO() {
		this.startDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException {
		this.startDate.setCalendarValue(startDate);
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param endDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setEndDate(String endDate) throws CalendarException {
		this.endDate.setCalendarValue(endDate);
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getEndDate() throws CalendarException {
		endDate.setCalPattern(getCalPattern());
		return this.endDate;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOuOption() {
		return ouOption;
	}

	public void setOuOption(String ouOption) {
		this.ouOption = ouOption;
	}

	public String getNameExist() {
		return nameExist;
	}

	public void setNameExist(String nameExist) {
		this.nameExist = nameExist;
	}
	
}
