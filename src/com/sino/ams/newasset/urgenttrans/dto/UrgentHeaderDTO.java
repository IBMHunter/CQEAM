package com.sino.ams.newasset.urgenttrans.dto;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * 
 * @ϵͳ����: ����������
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Aug 1, 2011
 */
public class UrgentHeaderDTO extends AMSFlowDTO {
	private String transId = "";
	private String transNo = "";
	private String transStatus = ""; 
	
	private int fromOrganizationId = 0;
	private String fromDept = ""; 
	private int fromGroup = 0; //
	private String transType = "";  //��������
	private String createdReason = ""; //����ԭ��
	
//	private String email = ""; // �����˵����ʼ�
//	private String phoneNumber = ""; // �������ֻ�����
 
	private int createdBy = 0; 
	private String fromObjectNo = ""; 
	private int implementBy = 0; 
    private String toObjectNo = ""; 
    private int toImplementBy = 0;
    private String transferType = ""; //����������һ�����ͣ������ڣ����ż䣬���м�
    private int archivedBy = 0; //�鵵�� 
    private String transStatusName = "";
    private String created = ""; // ����������;
	private String transTypeValue = ""; // �ʲ�ҵ���������� 
	private String fromCompanyName = ""; 
	private String fromDeptName = "";
	private String fromGroupName = "";
	private String fromObjectName = ""; //Ŀ�ĵص����� 
	private String toObjectName = ""; //Ŀ�ĵص����� 
	private String implementByName = "";  
    private String toImplementByName = "";
    private String archivedByName = "";
    
    //����ΪPDA�ֶ�
    private String period = "";
//    private String orderTypeNmme = "";
    private String fromObjectCode = "";
    private String toObjectCode = "";
    private String createdLoginName = "";
    private String orderType = "";
    
    //��ѯ���ж���ͨ��ѯ/�鵵��ѯ
    private boolean isArchive = false;
//    private String toDept = "";  
	private SimpleCalendar creationDate = null;

	public UrgentHeaderDTO() {
		this.creationDate = new SimpleCalendar();
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public int getFromOrganizationId() {
		return fromOrganizationId;
	}

	public void setFromOrganizationId(int fromOrganizationId) {
		this.fromOrganizationId = fromOrganizationId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getCreatedReason() {
		return createdReason;
	}

	public void setCreatedReason(String createdReason) {
		this.createdReason = createdReason;
	}


	public String getFromCompanyName() {
		return fromCompanyName;
	}

	public void setFromCompanyName(String fromCompanyName) {
		this.fromCompanyName = fromCompanyName;
	}

	public String getTransTypeValue() {
		return transTypeValue;
	}

	public void setTransTypeValue(String transTypeValue) {
		this.transTypeValue = transTypeValue;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public SimpleCalendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) throws CalendarException {
		this.creationDate.setCalendarValue(creationDate);
	}

	public String getTransStatusName() {
		return transStatusName;
	}

	public void setTransStatusName(String transStatusName) {
		this.transStatusName = transStatusName;
	}

	public String getFromDept() {
		return fromDept;
	}

	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}

	public String getFromObjectNo() {
		return fromObjectNo;
	}

	public void setFromObjectNo(String fromObjectNo) {
		this.fromObjectNo = fromObjectNo;
	}

	public String getFromObjectName() {
		return fromObjectName;
	}

	public void setFromObjectName(String fromObjectName) {
		this.fromObjectName = fromObjectName;
	}

	public int getImplementBy() {
		return implementBy;
	}

	public void setImplementBy(int implementBy) {
		this.implementBy = implementBy;
	}

	public String getImplementByName() {
		return implementByName;
	}

	public void setImplementByName(String implementByName) {
		this.implementByName = implementByName;
	}

	public String getToObjectNo() {
		return toObjectNo;
	}

	public void setToObjectNo(String toObjectNo) {
		this.toObjectNo = toObjectNo;
	}

	public String getToObjectName() {
		return toObjectName;
	}

	public void setToObjectName(String toObjectName) {
		this.toObjectName = toObjectName;
	}

	public int getToImplementBy() {
		return toImplementBy;
	}

	public void setToImplementBy(int toImplementBy) {
		this.toImplementBy = toImplementBy;
	}

	public String getToImplementByName() {
		return toImplementByName;
	}

	public void setToImplementByName(String toImplementByName) {
		this.toImplementByName = toImplementByName;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getFromDeptName() {
		return fromDeptName;
	}

	public void setFromDeptName(String fromDeptName) {
		this.fromDeptName = fromDeptName;
	}

	public int getArchivedBy() {
		return archivedBy;
	}

	public void setArchivedBy(int archivedBy) {
		this.archivedBy = archivedBy;
	}

	public String getArchivedByName() {
		return archivedByName;
	}

	public void setArchivedByName(String archivedByName) {
		this.archivedByName = archivedByName;
	}

	public int getFromGroup() {
		return fromGroup;
	}

	public void setFromGroup(int fromGroup) {
		this.fromGroup = fromGroup;
	}

	public String getFromGroupName() {
		return fromGroupName;
	}

	public void setFromGroupName(String fromGroupName) {
		this.fromGroupName = fromGroupName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

//	public String getOrderTypeNmme() {
//		return orderTypeNmme;
//	}
//
//	public void setOrderTypeNmme(String orderTypeNmme) {
//		this.orderTypeNmme = orderTypeNmme;
//	}

	public String getFromObjectCode() {
		return fromObjectCode;
	}

	public void setFromObjectCode(String fromObjectCode) {
		this.fromObjectCode = fromObjectCode;
	}

	public String getToObjectCode() {
		return toObjectCode;
	}

	public void setToObjectCode(String toObjectCode) {
		this.toObjectCode = toObjectCode;
	}

	public String getCreatedLoginName() {
		return createdLoginName;
	}

	public void setCreatedLoginName(String createdLoginName) {
		this.createdLoginName = createdLoginName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}
}
