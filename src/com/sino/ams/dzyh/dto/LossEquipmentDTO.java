package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;

/**
* <p>Title: (��ֵ�׺�Ʒ)������ʧ�豸(EAM) </p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class LossEquipmentDTO extends CommonRecordDTO{

	private String dhChgLogId = "";	//ϵͳID(���к�)
	private int fromAddressId = SyBaseSQLUtil.NULL_INT_VALUE;	//ԭ�ص�
	
	//==============================================ETS_ITEM_INFO===========================
	private String barcode = "";	//�����ǩ
	private int itemQty = SyBaseSQLUtil.NULL_INT_VALUE;	//�豸����
	private float price = 0;	//����
	private String responsibilityDept = "";	//ʹ�ò���
	private SimpleCalendar lastLocChgDate = null;	//�豸�ص����䶯ʱ�䣨�������ڣ�
	private int responsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE;	//������
	private int maintainUser = SyBaseSQLUtil.NULL_INT_VALUE;	//������
	private int addressId = SyBaseSQLUtil.NULL_INT_VALUE;	//�ʲ��ص�ID
	private String attribute3 = "";	//��չ����3�����ң�
	private String remark = "";		//��ע

	private String deptName = "";	//ʹ�ò���
	private String userName = "";	//������
	
	//==============================================ETS_SYSTEM_ITEM===========================
	private String itemCategory2 = "";	//Ŀ¼���
	private String itemName = "";		//Ʒ��
	private String itemSpec = "";		//����ͺ�
	
	//================================================ETS_0BJECT==============================
	private String workorderObjectName = "";	//�ص�
	
	//===============================EAM_CHECK_TASK==============================================
	private String checkTaskId="";	//�̵�����ID
	private String taskName="";	//��������
	private String checkType="";	//�����̵�����

	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public LossEquipmentDTO(){
		super();
		this.lastLocChgDate=new SimpleCalendar();
	}
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDhChgLogId() {
		return dhChgLogId;
	}

	public void setDhChgLogId(String dhChgLogId) {
		this.dhChgLogId = dhChgLogId;
	}

	public int getFromAddressId() {
		return fromAddressId;
	}

	public void setFromAddressId(int fromAddressId) {
		this.fromAddressId = fromAddressId;
	}

	public String getItemCategory2() {
		return itemCategory2;
	}

	public void setItemCategory2(String itemCategory2) {
		this.itemCategory2 = itemCategory2;
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

	public int getMaintainUser() {
		return maintainUser;
	}

	public void setMaintainUser(int maintainUser) {
		this.maintainUser = maintainUser;
	}

	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkorderObjectName() {
		return workorderObjectName;
	}

	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getCheckTaskId() {
		return checkTaskId;
	}

	public void setCheckTaskId(String checkTaskId) {
		this.checkTaskId = checkTaskId;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	public SimpleCalendar getLastLocChgDate() {
		return lastLocChgDate;
	}

	public void setLastLocChgDate(SimpleCalendar lastLocChgDate) {
		this.lastLocChgDate = lastLocChgDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(int responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}