package com.sino.ams.match.dto;
import com.sino.base.dto.CheckBoxDTO;

public class TfAmsMactPropertyDTO  extends CheckBoxDTO {


		private String barcode = "";//ETS����
		private String tagNumber = "";//�ʲ�����
		private String itemName= "";//ETS�豸����
		private String itemSpec = "";//ETS�豸�ͺ�
		private String  modelNumber = "";//MIS�ʲ��ͺ�
		private String assetsDescription = "";//MIS�ʲ�����
		private String workorderObjectLocation = "";//ETS�ʲ��ص�
		private String assetsLocation="";//MIS�ʲ��ص�
		private String workorderObjectName  ="";// �ص�����
		private String costCenterCode = "";
		private String costCenterName = "";

	public String getWorkorderObjectName() {
		return workorderObjectName;
	}

	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
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

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getAssetsDescription() {
		return assetsDescription;
	}

	public void setAssetsDescription(String assetsDescription) {
		this.assetsDescription = assetsDescription;
	}

	public String getWorkorderObjectLocation() {
		return workorderObjectLocation;
	}

	public void setWorkorderObjectLocation(String workorderObjectLocation) {
		this.workorderObjectLocation = workorderObjectLocation;
	}

	public String getAssetsLocation() {
		return assetsLocation;
	}

	public void setAssetsLocation(String assetsLocation) {
		this.assetsLocation = assetsLocation;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
}
