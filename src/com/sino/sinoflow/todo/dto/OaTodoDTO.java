package com.sino.sinoflow.todo.dto;

import com.sino.base.dto.DTO;

/**
 * 
 * @ϵͳ����: 
 * @��������: OA_TODO
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 29, 2011
 */
public class OaTodoDTO implements DTO{

	private String docId = ""; 
	private String workId = "";
	private String userId = "";
	private String title = "";
	private String startTime = "";
	private String todoUrl = "";
	private String pri = "" ; 
	private String todoType = "";
	
	//docType �����ǡ���˾���ġ������������ġ������������롱��˵�����֣���ͬ��������ƽ̨��
	private String docType = "";
	private String sender = "";
	private String sourceId = "";
	private String sysId = "" ;
	private String closeTime = "";
	
    private String resultCode="";
    private String resultDesc=""; 
	
	 
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
  
	public String getTodoUrl() {
		return todoUrl;
	}
	public void setTodoUrl(String todoUrl) {
		this.todoUrl = todoUrl;
	}

	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	public String getTodoType() {
		return todoType;
	}
	public void setTodoType(String todoType) {
		this.todoType = todoType;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}  

	
}