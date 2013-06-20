package com.sino.nm.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

public class InformationMaterialManageDTO extends CommonRecordDTO {

	// primary key
	private String itemId = "";

	// �ʲ���ǩ
	private String barcode = "";

	// �ʲ�����
	private String itemName = "";

	// �ʲ��ͺ�
	private String itemSpec = "";

	// �ʲ�״̬
	private String itemStatus = "";

	// �ʲ�Ʒ��
	private String itemBrand = "";

	// �ʲ����к�
	private String itemSerial = "";

	// �ʲ���������
	// private String creationDate;

	// ������
	private int createdBy;

	// �ϴ��޸�����
	// private String lastUpdateDate;

	// �ϴ��޸���
	private int lastUpdateBy;

	// ����ϵͳ
	private String useBySystem = "";

	// ��Ʒ��
	private String productId = "";

	// ������
	private String responsibilityUser = "";

	// ���β���
	private String responsibilityDept = "";

	// �ڴ�
	private String memory = "";

	// CPU ������
	private String cpu = "";

	// IP
	private String ipAddress = "";

	// Ӳ����Ϣ
	private String diskInformation = "";

	// ����ϵͳ
	private String systemName = "";

	// �й�����
	private String trusteeshipType = "";

	// ��Ҫ�̶�
	private String importantLevel = "";

	// ���ʴ����
	private String itemCategory1 = "";

	// ���ʶ������
	private String itemCategory2 = "";

	// �Ƿ���Ч
	private String enabled = "";

	// ʧЧ����
	private String disableDate = "";

	// �����������
	private String itemCategory3 = "";

	// ��չ����1
	private String attribute1 = "";

	// ��չ����2
	private String attribute2 = "";

	// ��չ����3
	private String attribute3 = "";

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getItemSerial() {
		return itemSerial;
	}

	public void setItemSerial(String itemSerial) {
		this.itemSerial = itemSerial;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(int lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getUseBySystem() {
		return useBySystem;
	}

	public void setUseBySystem(String useBySystem) {
		this.useBySystem = useBySystem;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDiskInformation() {
		return diskInformation;
	}

	public void setDiskInformation(String diskInformation) {
		this.diskInformation = diskInformation;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getTrusteeshipType() {
		return trusteeshipType;
	}

	public void setTrusteeshipType(String trusteeshipType) {
		this.trusteeshipType = trusteeshipType;
	}

	public String getImportantLevel() {
		return importantLevel;
	}

	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}

	public String getItemCategory1() {
		return itemCategory1;
	}

	public void setItemCategory1(String itemCategory1) {
		this.itemCategory1 = itemCategory1;
	}

	public String getItemCategory2() {
		return itemCategory2;
	}

	public void setItemCategory2(String itemCategory2) {
		this.itemCategory2 = itemCategory2;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getItemCategory3() {
		return itemCategory3;
	}

	public void setItemCategory3(String itemCategory3) {
		this.itemCategory3 = itemCategory3;
	}

	public String getAttribute1() {
		return attribute1;
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

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
