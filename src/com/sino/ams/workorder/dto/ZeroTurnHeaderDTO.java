package com.sino.ams.workorder.dto;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.base.calen.SimpleCalendar;
import com.sino.framework.security.dto.ServletConfigDTO;

public class ZeroTurnHeaderDTO extends AMSFlowDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7061078340898781512L;

	// ����
	//private ServletConfigDTO servletConfig = null;
    private String excel="";
    
	//��ͷ��Ϣ
    private String transId = "";
	private String transNo = "";          //���ݱ��
	private String transType = "";        //��������
	private String transStatus = "";      //����״̬
	private String statusType = "";//     //����״̬����
	private String createdReason = "";    //���ݴ���ԭ��
	//private String isCreated = "";        //�Ƿ��Ѿ��ύ�����˵���
	private int  createdBy;               //������;
	private String createdByName = "";    //����������
	private int organizationId =-1;       //��˾����
	private String organizationName = ""; //��˾����       
	private String deptCode = "";         //����
	private String deptName = "";         //��������
	private int groupId ;
	private String groupName = "";//���
	
	
	private String computeTims = "";// ִ������
	private String orderExecuter = "";//����ִ����
	private String orderExecuterName = "";//
	private String orderFiler = "";//�����鵵��
	private String orderFilerName = "";
	private String orderNo = "";//�ɹ�����
	private String remark1 = "";//��ע
	private String ccenter ="";//�ɱ�����
	private String copye = "";//��˾����
	
    private String phoneNumber = ""; // �������ֻ�����
    private String email = ""; // �����˵����ʼ�
	//��ͷ��Ϣ
	
	// ���������Ϣ
	private String attribute1 = "";   // �������������Ϣ
	private String attribute2 = "";   // �������������Ϣ
	private String attribute3 = "";   // �������������Ϣ
	private String attribute4 = "";   // �������������Ϣ
	private String attribute5 = "";   // �������������Ϣ
    private String sectionRight = ""; // �������������Ϣ
    private String hiddenRight = "";  // �������������Ϣ
    
    
    //���������Ϣ
    
    //�û������Ϣ[�ݴ�]
    private int userId = 0;
    
    
    private String emergentLevel = ""; //�����̶�
    private String emergentLevelOption = ""; //�����ɶ�������  
	private String centerCode = "";// �ɱ����ģ����䣩
	private String centerName = "";// �ɱ�����
    
    private String costCenterName = "";
    private String costCenterCode = "";
    private String barcode ="";
    
	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the costCenterName
	 */
	public String getCostCenterName() {
		return costCenterName;
	}

	/**
	 * @param costCenterName the costCenterName to set
	 */
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	/**
	 * @return the costCenterCode
	 */
	public String getCostCenterCode() {
		return costCenterCode;
	}

	/**
	 * @param costCenterCode the costCenterCode to set
	 */
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public String getEmergentLevel() {
		return emergentLevel;
	}

	public void setEmergentLevel(String emergentLevel) {
		this.emergentLevel = emergentLevel;
	}

	public String getEmergentLevelOption() {
		return emergentLevelOption;
	}

	public void setEmergentLevelOption(String emergentLevelOption) {
		this.emergentLevelOption = emergentLevelOption;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAttribute1() {
		return attribute1;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSectionRight() {
		return sectionRight;
	}

	public void setSectionRight(String sectionRight) {
		this.sectionRight = sectionRight;
	}

	public String getHiddenRight() {
		return hiddenRight;
	}

	public void setHiddenRight(String hiddenRight) {
		this.hiddenRight = hiddenRight;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public String getCreatedReason() {
		return createdReason;
	}

	public void setCreatedReason(String createdReason) {
		this.createdReason = createdReason;
	}

//	public String getIsCreated() {
//		return isCreated;
//	}
//
//	public void setIsCreated(String isCreated) {
//		this.isCreated = isCreated;
//	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOrderExecuter() {
		return orderExecuter;
	}

	public void setOrderExecuter(String orderExecuter) {
		this.orderExecuter = orderExecuter;
	}

	public String getOrderFiler() {
		return orderFiler;
	}

	public void setOrderFiler(String orderFiler) {
		this.orderFiler = orderFiler;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getComputeTims() {
		return computeTims;
	}

	public void setComputeTims(String computeTims) {
		this.computeTims = computeTims;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getOrderExecuterName() {
		return orderExecuterName;
	}

	public void setOrderExecuterName(String orderExecuterName) {
		this.orderExecuterName = orderExecuterName;
	}

	public String getOrderFilerName() {
		return orderFilerName;
	}

	public void setOrderFilerName(String orderFilerName) {
		this.orderFilerName = orderFilerName;
	}

	public String getCcenter() {
		return ccenter;
	}

	public void setCcenter(String ccenter) {
		this.ccenter = ccenter;
	}

	public String getCopye() {
		return copye;
	}

	public void setCopye(String copye) {
		this.copye = copye;
	}
	
	
}
