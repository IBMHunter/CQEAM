package com.sino.sinoflow.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �߰�����Ϣ�� SfEnote</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfEnoteDTO extends CheckBoxDTO{

	private int enoteId = 0;
	private String actId = "";
	private int fromUserId = 0;
	private int toUserId = 0;
	private SimpleCalendar fromDate = null;
	private String url = "";
	private String msg = "";
	private String read = "";
    private String enabled = "Y";

    private String sfactProcName = "";
	private String sfactApplMsg = "";
	private String sfactApplColumn1 = "";
	private String sfactApplColumn2 = "";
	private String fromUserName = "";
	private String toUserName = "";

	public SfEnoteDTO() {
		super();
		this.fromDate = new SimpleCalendar();
	}

	
	public String getFromUserName() {
		return fromUserName;
	}


	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}


	public String getToUserName() {
		return toUserName;
	}


	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}


	public String getSfactProcName() {
		return sfactProcName;
	}


	public void setSfactProcName(String sfactProcName) {
		this.sfactProcName = sfactProcName;
	}


	public String getSfactApplMsg() {
		return sfactApplMsg;
	}


	public void setSfactApplMsg(String sfactApplMsg) {
		this.sfactApplMsg = sfactApplMsg;
	}


	public String getSfactApplColumn1() {
		return sfactApplColumn1;
	}


	public void setSfactApplColumn1(String sfactApplColumn1) {
		this.sfactApplColumn1 = sfactApplColumn1;
	}


	public String getSfactApplColumn2() {
		return sfactApplColumn2;
	}


	public void setSfactApplColumn2(String sfactApplColumn2) {
		this.sfactApplColumn2 = sfactApplColumn2;
	}


	/**
	 * ���ܣ����ô߰�����Ϣ������ ����������
	 * @param enoteId AdvancedNumber
	 */
	public void setEnoteId(int enoteId){
		this.enoteId = enoteId;
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ �߰��id
	 * @param actId String
	 */
	public void setActId(String actId){
		this.actId = actId;
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ �߰���ID
	 * @param fromUserId String
	 */
	public void setFromUserId(int fromUserId){
		this.fromUserId = fromUserId;
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ ���߰���ID
	 * @param toUserId String
	 */
	public void setToUserId(int toUserId){
		this.toUserId = toUserId;
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ ��ʼ�߰�����
	 * @param fromDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setFromDate(String fromDate) throws CalendarException{
		this.fromDate.setCalendarValue(fromDate);
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ ����ʽ��·������
	 * @param url String
	 */
	public void setUrl(String url){
		this.url = url;
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ �߰�����
	 * @param msg String
	 */
	public void setMsg(String msg){
		this.msg = msg;
	}

	/**
	 * ���ܣ����ô߰�����Ϣ������ �Ƿ񱻶�����1������0û�У�
	 * @param read String
	 */
	public void setRead(String read){
		this.read = read;
	}

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
	 * ���ܣ���ȡ�߰�����Ϣ������ ����������
	 * @return AdvancedNumber
	 */
	public int getEnoteId() {
		return this.enoteId;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ �߰��id
	 * @return String
	 */
	public String getActId() {
		return this.actId;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ �߰���ID
	 * @return String
	 */
	public int getFromUserId() {
		return this.fromUserId;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ ���߰���ID
	 * @return String
	 */
	public int getToUserId() {
		return this.toUserId;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ ��ʼ�߰�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getFromDate() throws CalendarException {
		fromDate.setCalPattern(getCalPattern());
		return this.fromDate;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ ����ʽ��·������
	 * @return String
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ �߰�����
	 * @return String
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * ���ܣ���ȡ�߰�����Ϣ������ �Ƿ񱻶�����1������0û�У�
	 * @return String
	 */
	public String getRead() {
		return this.read;
	}

    public String getEnabled() {
        return this.enabled;
    }

}