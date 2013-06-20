package com.sino.ams.instrument.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: �����Ǳ����(EAM) AmsInstrumentInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsInstrumentInfoDTO extends CheckBoxDTO {

	private String barcode = "";
	private String itemCode = "";
	private String instruUsage = "";
	private SimpleCalendar creationDate = null;
	private int createdBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private String currKeepUser = "";
	private String itemName = "";
	private String barcode1 = "";
	private String itemCategory = "";
	private String itemSpec = "";
	private String vendorName = "";
	private String vendorId = "";
	private int borrowUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String borrowDate = "";
	private int confirmUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String confirmDate = "";
	private String transNo = "";
	private String createName = "";
	private String cname = "";
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String objectCategory = "";
	private int addressId = SyBaseSQLUtil.NULL_INT_VALUE;
	private int responsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String responsibilityDept = "";
	private int maintainUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String systemid = "";
	private int userId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String userName = "";
	private String companyName = "";
	private String itemStatus = "";
	private String borrowName = "";
	private String itemQty = "";
	private String responsibilityName = "";
	private String borrowNum = "";
	private String groupname = "";
	private String username = "";
	private String userId2 = "";
	private String assetNumber = "";
	private String assetsDescription = "";
	private String modelNumber = "";
	private String maintainDept = "";
	private String unitPrice = "";
	private String addressloc = "";
	private int groupId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String remark = "";
	private int deptId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String maintainDeptName = "";
	private String returnName = "";

	public String getReturnName() {
		return returnName;
	}

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	//    private String itemStatus = "";
	//    private String responsibilityName="";

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getMaintainDeptName() {
		return maintainDeptName;
	}

	public void setMaintainDeptName(String maintainDeptName) {
		this.maintainDeptName = maintainDeptName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getMaintainDept() {
		return maintainDept;
	}

	public void setMaintainDept(String maintainDept) {
		this.maintainDept = maintainDept;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAddressloc() {
		return addressloc;
	}

	public void setAddressloc(String addressloc) {
		this.addressloc = addressloc;
	}

	public String getAssetsDescription() {
		return assetsDescription;
	}

	public void setAssetsDescription(String assetsDescription) {
		this.assetsDescription = assetsDescription;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId2() {
		return userId2;
	}

	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(String borrowNum) {
		this.borrowNum = borrowNum;
	}

	public String getResponsibilityName() {
		return responsibilityName;
	}

	public void setResponsibilityName(String responsibilityName) {
		this.responsibilityName = responsibilityName;
	}

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSystemid() {
		return systemid;
	}

	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}

	public int getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(int responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}

	public int getMaintainUser() {
		return maintainUser;
	}

	public void setMaintainUser(int maintainUser) {
		this.maintainUser = maintainUser;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getObjectCategory() {
		return objectCategory;
	}

	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}

	private String transType = "";

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public int getBorrowUser() {
		return borrowUser;
	}

	public void setBorrowUser(int borrowUser) {
		this.borrowUser = borrowUser;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public int getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(int confirmUser) {
		this.confirmUser = confirmUser;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getBarcode1() {
		return barcode1;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �Ǿ�����
	 * @param barcode String
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �������
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �Ǿ���;
	 * @param instruUsage String
	 */
	public void setInstruUsage(String instruUsage) {
		this.instruUsage = instruUsage;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException {
		if (!StrUtil.isEmpty(creationDate)) {
			this.creationDate = new SimpleCalendar(creationDate);
		}
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate)
			throws CalendarException {
		if (!StrUtil.isEmpty(lastUpdateDate)) {
			this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
		}
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� ��ǰʹ����
	 * @param currKeepUser String
	 */
	public void setCurrKeepUser(String currKeepUser) {
		this.currKeepUser = currKeepUser;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �Ǿ�����
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �������
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �Ǿ���;
	 * @return String
	 */
	public String getInstruUsage() {
		return this.instruUsage;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� ��������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getCreationDate() {
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� ������
	 * @return String
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� ��ǰʹ����
	 * @return String
	 */
	public String getCurrKeepUser() {
		return this.currKeepUser;
	}

}