package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;

/**
* <p>Title: (��ֵ�׺�Ʒ)ִ�й�����ѯ(EAM) </p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamCheckImplementDTO extends CommonRecordDTO{

	//==============================================EAM_DH_CHECK_HEADER===========================
	private String headerId = "";	//�̵㵥���к�
	private int batchId;	//�̵������к�
	private int checkTaskId;	//�̵�����ID
	private String orderNo = "";	//���ݺ�(�������)
	private int checkLocation;	//�̵�ص�(�����ص�)
	private SimpleCalendar implementDays = null;	//ִ������
	private int implementBy;	//ִ����
	private String userName = "";	//ִ����
	private SimpleCalendar downloadDate = null;		//��������
	private SimpleCalendar uploadDate = null;		//�ϴ�����/ʵ���������(�ύ����)
	
	//==============================================EAM_DH_CHECK_BATCH===========================
	private String deptCode = "";	//���Ŵ���
	private String deptName = "";	//�̵㲿��(ִ�в���)

	//================================================ETS_0BJECT==============================
	private String workorderObjectName = "";	//�ص�
	
	//===============================EAM_CHECK_TASK==============================================
	private String taskName="";	//��������
	private String checkType="";	//�����̵�����

	public EamCheckImplementDTO(){
		super();
		this.implementDays=new SimpleCalendar();
		this.downloadDate=new SimpleCalendar();
		this.uploadDate=new SimpleCalendar();
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getCheckLocation() {
		return checkLocation;
	}

	public void setCheckLocation(int checkLocation) {
		this.checkLocation = checkLocation;
	}

	public int getCheckTaskId() {
		return checkTaskId;
	}

	public void setCheckTaskId(int checkTaskId) {
		this.checkTaskId = checkTaskId;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public SimpleCalendar getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(SimpleCalendar downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	public int getImplementBy() {
		return implementBy;
	}

	public void setImplementBy(int implementBy) {
		this.implementBy = implementBy;
	}

	public SimpleCalendar getImplementDays() {
		return implementDays;
	}

	public void setImplementDays(SimpleCalendar implementDays) {
		this.implementDays = implementDays;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public SimpleCalendar getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(SimpleCalendar uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getWorkorderObjectName() {
		return workorderObjectName;
	}

	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}