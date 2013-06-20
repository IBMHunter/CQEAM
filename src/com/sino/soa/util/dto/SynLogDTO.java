package com.sino.soa.util.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �Զ�ͬ����־��(EAM) EtsAutoSynLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SynLogDTO extends CheckBoxDTO{

	private String synId = null;
	private String synType = "";
	private SimpleCalendar synDate = null;
	private String synMsg = "";
	private SimpleCalendar creationDate = null;
	private int createdBy = -1;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy = -1;

	public SynLogDTO() {
		super();
		this.synDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
	}

    /**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� SYN_ID
	 * @param synId String
	 */
	public void setSynId(String synId){
		this.synId = synId;
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� SYN_TYPE
	 * @param synType String
	 */
	public void setSynType(String synType){
		this.synType = synType;
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� SYN_DATE
	 * @param synDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setSynDate(String synDate) throws CalendarException{
		this.synDate.setCalendarValue(synDate);
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� SYN_MSG
	 * @param synMsg String
	 */
	public void setSynMsg(String synMsg){
		this.synMsg = synMsg;
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� ������
	 * @param createdBy int
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ������Զ�ͬ����־��(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy int
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� SYN_ID
	 * @return String
	 */
	public String getSynId() {
		return this.synId;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� SYN_TYPE
	 * @return String
	 */
	public String getSynType() {
		return this.synType;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� SYN_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getSynDate() throws CalendarException {
		synDate.setCalPattern(getCalPattern());
		return this.synDate;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� SYN_MSG
	 * @return String
	 */
	public String getSynMsg() {
		return this.synMsg;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� ������
	 * @return int
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�Զ�ͬ����־��(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy() {
		return this.lastUpdateBy;
	}
}