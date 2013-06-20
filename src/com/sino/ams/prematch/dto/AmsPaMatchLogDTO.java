package com.sino.ams.prematch.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ�� AmsPaMatchLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsPaMatchLogDTO extends CommonRecordDTO{

	private String logId = "";
	private int systemId;
	private String tagNumber = "";
	private int organizationId;
	private String act = "";
	private int matchedBy;
	private SimpleCalendar matchedDate = null;
	private String matchedUser = "";
	private String createdUser = "";
	private String remark = "";

	public AmsPaMatchLogDTO() {
		super();
		this.matchedDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ���к�
	 * @param logId String
	 */
	public void setLogId(String logId){
		this.logId = logId;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ETS_ITEM_INFO���к�
	 * @param systemId String
	 */
	public void setSystemId(int systemId){
		this.systemId = systemId;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ MISת��׼���嵥��ǩ��
	 * @param tagNumber String
	 */
	public void setTagNumber(String tagNumber){
		this.tagNumber = tagNumber;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ����֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ��������
	 * @param act String
	 */
	public void setAct(String act){
		this.act = act;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ����
	 * @param matchedBy String
	 */
	public void setMatchedBy(int matchedBy){
		this.matchedBy = matchedBy;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setMatchedUser(String matchedUser) {
		this.matchedUser = matchedUser;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ��ʱ��
	 * @param matchedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setMatchedDate(String matchedDate) throws CalendarException{
		this.matchedDate.setCalendarValue(matchedDate);
	}


	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ���к�
	 * @return String
	 */
	public String getLogId() {
		return this.logId;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ETS_ITEM_INFO���к�
	 * @return String
	 */
	public int getSystemId() {
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ MISת��׼���嵥��ǩ��
	 * @return String
	 */
	public String getTagNumber() {
		return this.tagNumber;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ����֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ��������
	 * @return String
	 */
	public String getAct() {
		return this.act;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ����
	 * @return String
	 */
	public int getMatchedBy() {
		return this.matchedBy;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ��ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getMatchedDate() throws CalendarException {
		matchedDate.setCalPattern(getCalPattern());
		return this.matchedDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public String getMatchedUser() {
		return matchedUser;
	}

	public String getRemark() {
		return remark;
	}
}
