package com.sino.ams.newasset.urgenttrans.dto;

import com.sino.ams.appbase.dto.AMSBaseDTO;
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
public class UrgentLineDTO extends AMSBaseDTO {
	private String itemName = ""; // �ʲ�����
	private String itemCode = ""; // �ʲ�����
	private String itemCategory = "";
	private String itemSpec = ""; // ����ͺ�
	
	private String workorderObjectLocation = ""; // �ʲ��ص�����
	private String responsibilityUserName = ""; // ������

//	private SimpleCalendar rentDate = null; // ��������
//	private SimpleCalendar rentEndDate = null; // ֹ������
//	private String rentPerson = ""; // ǩԼ��λ,
//	private String contractNumber = ""; // ��ͬ���
//	private String contractName = ""; // ��ͬ����

//	private float tenancy = 0; // ����
//	private String yearRental = ""; // �����
//	private String monthRental = ""; // �����
	private String remark = "";

	private String lineId = "";
	private String transId = "";
	private String barcode = "";
	private String responsibilityUser = "";
	// TENANCY",
	// "YEAR_RENTAL",
	// "MONTH_REANTAL",
	// "REMARK"
	
	public UrgentLineDTO() {
//		this.rentDate = new SimpleCalendar();
//		this.rentEndDate = new SimpleCalendar();
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getWorkorderObjectLocation() {
		return workorderObjectLocation;
	}

	public void setWorkorderObjectLocation(String workorderObjectLocation) {
		this.workorderObjectLocation = workorderObjectLocation;
	}

	public String getResponsibilityUserName() {
		return responsibilityUserName;
	}

	public void setResponsibilityUserName(String responsibilityUserName) {
		this.responsibilityUserName = responsibilityUserName;
	}
 
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

 

}
