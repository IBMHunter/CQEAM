package com.sino.ams.workorder.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ���������������(EAM) EtsSuggestion</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsSuggestionDTO extends CheckBoxDTO{

	private String  systemid = null;
	private String workorderBatch = "";
	private String title = "";
	private String remark = "";
	private String groupId = "";
	private int  handler;
	private SimpleCalendar recordDate = null;
	private int completeFlag;
	private String actId = "";

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public EtsSuggestionDTO() {
		super();
		this.recordDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� ��ˮ��
	 * @param systemid AdvancedNumber
	 */
	public void setSystemid(String  systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� ��������
	 * @param workorderBatch String
	 */
	public void setWorkorderBatch(String workorderBatch){
		this.workorderBatch = workorderBatch;
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� ����
	 * @param title String
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� �������
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� ����
	 * @param groupId String
	 */
	public void setGroupId(String groupId){
		this.groupId = groupId;
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� ������
	 * @param handler AdvancedNumber
	 */
	public void setHandler(int  handler){
		this.handler = handler;
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� �ύ����
	 * @param recordDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setRecordDate(String recordDate) throws CalendarException{
		this.recordDate.setCalendarValue(recordDate);
	}

	/**
	 * ���ܣ����ù��������������(EAM)���� ��ɱ�־(1:��ɣ�0:�ڰ�)
	 * @param completeFlag AdvancedNumber
	 */
	public void setCompleteFlag(int completeFlag){
		this.completeFlag = completeFlag;
	}


	/**
	 * ���ܣ���ȡ���������������(EAM)���� ��ˮ��
	 * @return AdvancedNumber
	 */
	public String  getSystemid() {
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� ��������
	 * @return String
	 */
	public String getWorkorderBatch() {
		return this.workorderBatch;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� ����
	 * @return String
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� �������
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� ����
	 * @return String
	 */
	public String getGroupId() {
		return this.groupId;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� ������
	 * @return AdvancedNumber
	 */
	public int getHandler() {
		return this.handler;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� �ύ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getRecordDate() throws CalendarException {
		recordDate.setCalPattern(getCalPattern());
		return this.recordDate;
	}

	/**
	 * ���ܣ���ȡ���������������(EAM)���� ��ɱ�־(1:��ɣ�0:�ڰ�)
	 * @return AdvancedNumber
	 */
	public int getCompleteFlag() {
		return this.completeFlag;
	}

}