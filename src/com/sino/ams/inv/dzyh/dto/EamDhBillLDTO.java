package com.sino.ams.inv.dzyh.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.ct.dto.EtsItemInfoDTO;

/**
 * <p>Title: ��ֵ�׺�Ʒ����-̨�˲�ѯ���� EamDhBillL</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ��ʿ��
 * @version 1.0
 */
public class EamDhBillLDTO extends EtsItemInfoDTO {

	private String billLineId = ""; //EAM_DH_BILL_L������
	private int catalogValueId = SyBaseSQLUtil.NULL_INT_VALUE; //Ŀ¼���
	private int catalogValueId2 = SyBaseSQLUtil.NULL_INT_VALUE; //Ϊ������ҳ����ظ�ʹ�����
	
	private String workorderObjectNoName1 = ""; //Ϊ������ҳ����ظ�ʹ�����
	
	//------------------------------���²���EAM_DH_BILL_L���е��ֶ�-----------------------------------------//
	private String catalogNameId = ""; //����ʶ��EAM_DH_CATALOG_VALUES���е�CATALOG_NAME�ֶ�
	
	//------------------------------������EAM_DH_CHG_LOG(�䶯��ʷ)���е��ֶ�-----------------------------------------//
	private String dhChgLogId = ""; //EAM_DH_CHG_LOG���е�����ID
	private String chgType = ""; //�䶯����
	private String fromDept = ""; //ԭʹ�ò���
	private String toDept = ""; //��ʹ�ò���
	private int fromAddressId = SyBaseSQLUtil.NULL_INT_VALUE; //ԭ�ص�
	private int toAddressId = SyBaseSQLUtil.NULL_INT_VALUE; //�µص�
	private int fromResponsibilityUser; //ԭ������
	private int toResponsibilityUser; //��������
	private String fromMaintainUser = ""; //ԭ������
	private String toMaintainUser = ""; //�±�����
	private String fromStatus = ""; //ԭʹ��״̬
	private String toStatus = ""; //��ʹ��״̬
	
	private String deptCode = ""; //AMS_MIS_DEPT���е�DEPT_CODE(���Ŵ���)�ֶ�
	private String deptName = ""; //AMS_MIS_DEPT���е�DEPT_NAME(��������)�ֶ�
	private int employeeId; //AMS_MIS_EMPLOYEE���е�EMPLOYEE_ID(Ա��ID)�ֶ�
	private String eamployeeNumber = ""; //AMS_MIS_EMPLOYEE���е�EMPLOYEE_NUMBER(Ա�����)�ֶ�
	private String userName = ""; //AMS_MIS_EMPLOYEE���е�USER_NAME(Ա������)�ֶ�
	
	//------------------------------������EAM_DH_CATALOG_VALUES���е��ֶ�--------------------------------------//
	private String barcodeFlag = ""; //��ǩ���
	private String catalogCode = ""; //��Ʒ���
	private String catalogName = ""; //Ʒ��
	
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDhChgLogId() {
		return dhChgLogId;
	}

	public void setDhChgLogId(String dhChgLogId) {
		this.dhChgLogId = dhChgLogId;
	}

	public String getChgType() {
		return chgType;
	}

	public void setChgType(String chgType) {
		this.chgType = chgType;
	}

	public int getFromAddressId() {
		return fromAddressId;
	}

	public void setFromAddressId(int fromAddressId) {
		this.fromAddressId = fromAddressId;
	}

	public int getToAddressId() {
		return toAddressId;
	}

	public void setToAddressId(int toAddressId) {
		this.toAddressId = toAddressId;
	}

	public int getFromResponsibilityUser() {
		return fromResponsibilityUser;
	}

	public void setFromResponsibilityUser(int fromResponsibilityUser) {
		this.fromResponsibilityUser = fromResponsibilityUser;
	}

	public int getToResponsibilityUser() {
		return toResponsibilityUser;
	}

	public void setToResponsibilityUser(int toResponsibilityUser) {
		this.toResponsibilityUser = toResponsibilityUser;
	}

	public String getFromMaintainUser() {
		return fromMaintainUser;
	}

	public void setFromMaintainUser(String fromMaintainUser) {
		this.fromMaintainUser = fromMaintainUser;
	}

	public String getToMaintainUser() {
		return toMaintainUser;
	}

	public void setToMaintainUser(String toMaintainUser) {
		this.toMaintainUser = toMaintainUser;
	}

	public String getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getToStatus() {
		return toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

	public String getCatalogNameId() {
		return catalogNameId;
	}

	public void setCatalogNameId(String catalogNameId) {
		this.catalogNameId = catalogNameId;
	}
	
	public int getCatalogValueId() {
		return catalogValueId;
	}
	public void setCatalogValueId(int catalogValueId) {
		this.catalogValueId = catalogValueId;
	}

	public String getBillLineId() {
		return billLineId;
	}

	public void setBillLineId(String billLineId) {
		this.billLineId = billLineId;
	}

	public String getFromDept() {
		return fromDept;
	}

	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}

	public String getToDept() {
		return toDept;
	}

	public void setToDept(String toDept) {
		this.toDept = toDept;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getCatalogValueId2() {
		return catalogValueId2;
	}

	public void setCatalogValueId2(int catalogValueId2) {
		this.catalogValueId2 = catalogValueId2;
	}

	public String getEamployeeNumber() {
		return eamployeeNumber;
	}

	public void setEamployeeNumber(String eamployeeNumber) {
		this.eamployeeNumber = eamployeeNumber;
	}

	public String getWorkorderObjectNoName1() {
		return workorderObjectNoName1;
	}

	public void setWorkorderObjectNoName1(String workorderObjectNoName1) {
		this.workorderObjectNoName1 = workorderObjectNoName1;
	}

	public String getBarcodeFlag() {
		return barcodeFlag;
	}

	public void setBarcodeFlag(String barcodeFlag) {
		this.barcodeFlag = barcodeFlag;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
}
