package com.sino.ams.system.item.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �豸�����(EAM) EtsSystemItem</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class EtsSystemItemDTO extends CommonRecordDTO {

	private String itemCode = "";
	private String itemName = "";
	private String itemSpec = "";
	private String itemCategory = "";
	private String misItemCode = "";
	private String enabled = "";
	private String memo = "";
	private int years  ;
	private String itemUnit = "";
	private String vendorId  ;
	private String isFixedAssets = "";
	private int masterOrganizationId ;
	private String vendorName = "";
	private String vendorCode = "";
	private String isParentItem = "";      //	�Ƿ��豸
	private String barcodeNullable = "";   //	�Ƿ�ɵ������������豸
	private String isTmpCode = "";         //	�Ƿ���ʱ�豸
	private SimpleCalendar fromDate = null;
	private SimpleCalendar toDate = null;
	private String repairQuery = "";   //�豸���޲�ѯ��ʽ
	private int orgId = 0;
	private String lastUpdateByName = ""; //�ϴ��޸���
	
	private String itemCategory2 = ""; //Ŀ¼���

	public EtsSystemItemDTO() {
		super();
		this.toDate = new SimpleCalendar();
		this.fromDate = new SimpleCalendar();
	}

	public SimpleCalendar getToDate() throws CalendarException {
		toDate.setCalPattern(getCalPattern());
		return this.toDate;
	}

	public void setToDate(String toDate) throws CalendarException {
		this.toDate.setCalendarValue(toDate);
	}

	public SimpleCalendar getFromDate() throws CalendarException {
		fromDate.setCalPattern(getCalPattern());
		return this.fromDate;
	}

	public void setFromDate(String fromDate) throws CalendarException {
		this.fromDate.setCalendarValue(fromDate);
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getRepairQuery() {
		return repairQuery;
	}

	public void setRepairQuery(String repairQuery) {
		this.repairQuery = repairQuery;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� �豸����
	 *
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� �豸����
	 *
	 * @param itemName String
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� ����ͺ�
	 *
	 * @param itemSpec String
	 */
	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� �豸����
	 *
	 * @param itemCategory String
	 */
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� MIS���ϱ���
	 *
	 * @param misItemCode String
	 */
	public void setMisItemCode(String misItemCode) {
		this.misItemCode = misItemCode;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� �Ƿ���Ч
	 *
	 * @param enabled String
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� ��ע
	 *
	 * @param memo String
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� ʹ������
	 *
	 * @param years String
	 */
	public void setYears(int years) {
		this.years = years;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� ������λ
	 *
	 * @param itemUnit String
	 */
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� ��������
	 *
	 * @param vendorId String
	 */
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * ���ܣ������豸�����(EAM)���� �Ƿ�Ϊ�̶��ʲ��豸
	 *
	 * @param isFixedAssets String
	 */
	public void setIsFixedAssets(String isFixedAssets) {
		this.isFixedAssets = isFixedAssets;
	}


	/**
	 * ���ܣ������豸�����(EAM)���� ����֯ID
	 *
	 * @param masterOrganizationId String
	 */
	public void setMasterOrganizationId(int masterOrganizationId) {
		this.masterOrganizationId = masterOrganizationId;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� �豸����
	 *
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� �豸����
	 *
	 * @return String
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� ����ͺ�
	 *
	 * @return String
	 */
	public String getItemSpec() {
		return this.itemSpec;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� �豸����
	 *
	 * @return String
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� MIS���ϱ���
	 *
	 * @return String
	 */
	public String getMisItemCode() {
		return this.misItemCode;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� �Ƿ���Ч
	 *
	 * @return String
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� ��ע
	 *
	 * @return String
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� ʹ������
	 *
	 * @return String
	 */
	public int getYears() {
		return this.years;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� ������λ
	 *
	 * @return String
	 */
	public String getItemUnit() {
		return this.itemUnit;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� ��������
	 *
	 * @return String
	 */
	public String getVendorId() {
		return this.vendorId;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� �Ƿ�Ϊ�̶��ʲ��豸
	 *
	 * @return String
	 */
	public String getIsFixedAssets() {
		return this.isFixedAssets;
	}

	/**
	 * ���ܣ���ȡ�豸�����(EAM)���� ����֯ID
	 *
	 * @return String
	 */
	public int getMasterOrganizationId() {
		return this.masterOrganizationId;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public String getFixedAssets() {
		return isFixedAssets;
	}

	public void setFixedAssets(String fixedAssets) {
		isFixedAssets = fixedAssets;
	}

	public String getParentItem() {
		return isParentItem;
	}

	public void setParentItem(String parentItem) {
		isParentItem = parentItem;
	}

	public String getBarcodeNullable() {
		return barcodeNullable;
	}

	public String getIsTmpCode() {
		return isTmpCode;
	}

	public void setBarcodeNullable(String barcodeNullable) {
		this.barcodeNullable = barcodeNullable;
	}

	public void setIsTmpCode(String isTmpCode) {
		this.isTmpCode = isTmpCode;
	}

	public String getItemCategory2() {
		return itemCategory2;
	}

	public void setItemCategory2(String itemCategory2) {
		this.itemCategory2 = itemCategory2;
	}

	public String getLastUpdateByName() {
		return lastUpdateByName;
	}

	public void setLastUpdateByName(String lastUpdateByName) {
		this.lastUpdateByName = lastUpdateByName;
	}
}
