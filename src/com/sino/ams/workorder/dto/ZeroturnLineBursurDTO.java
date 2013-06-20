package com.sino.ams.workorder.dto;

import com.sino.base.dto.CheckBoxDTO;


public class ZeroturnLineBursurDTO extends CheckBoxDTO {

	private String procureCode = "";// �ɹ����ţ����䣩
	
	private String barcode = "";// ��ǩ��
	
	private String  reimburseStatus = "";//����״̬
	
	private String  reimburseStatusName = "" ;//����״̬���� 
	
	//private String  reimbursementDate = "" ;//��������  REIMBURSEMENT_DATE

	private String reimburseDate = "";    //REIMBURSE_DATE
	
	private String assetsDescription = "";// �ʲ�����
	
	private String itemSpec = "";// ����ͺţ����䣩
	
	private String objectNo = "";// �ص��ţ����䣩
	
	private String responsibilityDept = "";// ���β��ţ����䣩
	
	private String responsibilityUser = "";// �����ˣ����䣩
	
	private String price = "";// �����䣩
	
    private String isCheck = "";
    
    private String isPeriod = "" ;//IS_PERIOD
    

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getProcureCode() {
		return procureCode;
	}

	public void setProcureCode(String procureCode) {
		this.procureCode = procureCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getReimburseStatus() {
		return reimburseStatus;
	}

	public void setReimburseStatus(String reimburseStatus) {
		this.reimburseStatus = reimburseStatus;
	}

	public String getReimburseStatusName() {
		return reimburseStatusName;
	}

	public void setReimburseStatusName(String reimburseStatusName) {
		this.reimburseStatusName = reimburseStatusName;
	}


	public String getAssetsDescription() {
		return assetsDescription;
	}

	public void setAssetsDescription(String assetsDescription) {
		this.assetsDescription = assetsDescription;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getObjectNo() {
		return objectNo;
	}

	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}

	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}

	public String getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public ZeroturnLineBursurDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getReimburseDate() {
		return reimburseDate;
	}

	public void setReimburseDate(String reimburseDate) {
		this.reimburseDate = reimburseDate;
	}

	public String getIsPeriod() {
		return isPeriod;
	}

	public void setIsPeriod(String isPeriod) {
		this.isPeriod = isPeriod;
	}
	
}

