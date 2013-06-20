package com.sino.ams.sampling.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��鹤���б� AmsAssetsSamplingLine</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsAssetsSamplingLineDTO extends AmsAssetsSamplingHeaderDTO{

	private String barcode = "";
	private String systemStatus = "";
	private String scanStatus = "";
	private String remark = "";
	private String itemCode = "";
	private String itemCategory = "";
	private String itemCategoryValue = "";
	private String itemName = "";
	private String itemSpec = "";
	private String responsibilityUser = "";
	private String responsibilityUserName = "";
	private String responsibilityDept = "";
	private String responsibilityDeptName = "";
	private String maintainUser = "";
	private String scanItemCode = "";
	private String scanItemCategory = "";
	private String scanItemCategoryValue = "";
	private String scanItemName = "";
	private String scanItemSpec = "";
	private SimpleCalendar scanStartDate = null;
	private String scanResponsibilityUser = "";
	private String scanResponsibilityUserName = "";
	private String scanResponsibilityDept = "";
	private String scanResponsibilityDeptName = "";
	private String scanMaintainUser = "";

	private SimpleCalendar scanDate = null;
	private String netUnit = "0000";
	private String boxNo = "0000";

	public AmsAssetsSamplingLineDTO() {
		super();
		this.scanStartDate = new SimpleCalendar();
		this.scanDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸����
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ϵͳ״̬
	 * @param systemStatus String
	 */
	public void setSystemStatus(String systemStatus){
		this.systemStatus = systemStatus;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ɨ��״̬
	 * @param scanStatus String
	 */
	public void setScanStatus(String scanStatus){
		this.scanStatus = scanStatus;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸�������(ϵͳ)
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸רҵ(ϵͳ)
	 * @param itemCategory String
	 */
	public void setItemCategory(String itemCategory){
		this.itemCategory = itemCategory;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸����(ϵͳ)
	 * @param itemName String
	 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸�ͺ�(ϵͳ)
	 * @param itemSpec String
	 */
	public void setItemSpec(String itemSpec){
		this.itemSpec = itemSpec;
	}


	/**
	 * ���ܣ����ó�鹤���б����� ������(ϵͳ)
	 * @param responsibilityUser String
	 */
	public void setResponsibilityUser(String responsibilityUser){
		this.responsibilityUser = responsibilityUser;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ���β���(ϵͳ)
	 * @param responsibilityDept String
	 */
	public void setResponsibilityDept(String responsibilityDept){
		this.responsibilityDept = responsibilityDept;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ʹ����(ϵͳ)
	 * @param maintainUser String
	 */
	public void setMaintainUser(String maintainUser){
		this.maintainUser = maintainUser;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸�������(ɨ��)
	 * @param scanItemCode String
	 */
	public void setScanItemCode(String scanItemCode){
		this.scanItemCode = scanItemCode;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸רҵ(ɨ��)
	 * @param scanItemCategory String
	 */
	public void setScanItemCategory(String scanItemCategory){
		this.scanItemCategory = scanItemCategory;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸����(ɨ��)
	 * @param scanItemName String
	 */
	public void setScanItemName(String scanItemName){
		this.scanItemName = scanItemName;
	}

	/**
	 * ���ܣ����ó�鹤���б����� �豸�ͺ�(ɨ��)
	 * @param scanItemSpec String
	 */
	public void setScanItemSpec(String scanItemSpec){
		this.scanItemSpec = scanItemSpec;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ��������(ɨ��)
	 * @param scanStartDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setScanStartDate(String scanStartDate) throws CalendarException{
		this.scanStartDate.setCalendarValue(scanStartDate);
	}

	/**
	 * ���ܣ����ó�鹤���б����� ������(ɨ��)
	 * @param scanResponsibilityUser String
	 */
	public void setScanResponsibilityUser(String scanResponsibilityUser){
		this.scanResponsibilityUser = scanResponsibilityUser;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ���β���(ɨ��)
	 * @param scanResponsibilityDept String
	 */
	public void setScanResponsibilityDept(String scanResponsibilityDept){
		this.scanResponsibilityDept = scanResponsibilityDept;
	}

	/**
	 * ���ܣ����ó�鹤���б����� ʹ����(ɨ��)
	 * @param scanMaintainUser String
	 */
	public void setScanMaintainUser(String scanMaintainUser){
		this.scanMaintainUser = scanMaintainUser;
	}

	public void setResponsibilityDeptName(String responsibilityDeptName) {
		this.responsibilityDeptName = responsibilityDeptName;
	}

	public void setResponsibilityUserName(String responsibilityUserName) {
		this.responsibilityUserName = responsibilityUserName;
	}

	public void setItemCategoryValue(String itemCategoryValue) {
		this.itemCategoryValue = itemCategoryValue;
	}

	public void setScanItemCategoryValue(String scanItemCategoryValue) {
		this.scanItemCategoryValue = scanItemCategoryValue;
	}

	public void setScanResponsibilityDeptName(String scanResponsibilityDeptName) {
		this.scanResponsibilityDeptName = scanResponsibilityDeptName;
	}

	public void setScanResponsibilityUserName(String scanResponsibilityUserName) {
		this.scanResponsibilityUserName = scanResponsibilityUserName;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public void setNetUnit(String netUnit) {
		this.netUnit = netUnit;
	}

	public void setScanDate(String scanDate) throws CalendarException {
		this.scanDate.setCalendarValue(scanDate);
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸����
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ϵͳ״̬
	 * @return String
	 */
	public String getSystemStatus() {
		return this.systemStatus;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ɨ��״̬
	 * @return String
	 */
	public String getScanStatus() {
		return this.scanStatus;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸�������(ϵͳ)
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸רҵ(ϵͳ)
	 * @return String
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸����(ϵͳ)
	 * @return String
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸�ͺ�(ϵͳ)
	 * @return String
	 */
	public String getItemSpec() {
		return this.itemSpec;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ������(ϵͳ)
	 * @return String
	 */
	public String getResponsibilityUser() {
		return this.responsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ���β���(ϵͳ)
	 * @return String
	 */
	public String getResponsibilityDept() {
		return this.responsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ʹ����(ϵͳ)
	 * @return String
	 */
	public String getMaintainUser() {
		return this.maintainUser;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸�������(ɨ��)
	 * @return String
	 */
	public String getScanItemCode() {
		return this.scanItemCode;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸רҵ(ɨ��)
	 * @return String
	 */
	public String getScanItemCategory() {
		return this.scanItemCategory;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸����(ɨ��)
	 * @return String
	 */
	public String getScanItemName() {
		return this.scanItemName;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� �豸�ͺ�(ɨ��)
	 * @return String
	 */
	public String getScanItemSpec() {
		return this.scanItemSpec;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ��������(ɨ��)
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getScanStartDate() throws CalendarException {
		scanStartDate.setCalPattern(getCalPattern());
		return this.scanStartDate;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ������(ɨ��)
	 * @return String
	 */
	public String getScanResponsibilityUser() {
		return this.scanResponsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ���β���(ɨ��)
	 * @return String
	 */
	public String getScanResponsibilityDept() {
		return this.scanResponsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��鹤���б����� ʹ����(ɨ��)
	 * @return String
	 */
	public String getScanMaintainUser() {
		return this.scanMaintainUser;
	}

	public String getResponsibilityDeptName() {
		return responsibilityDeptName;
	}

	public String getResponsibilityUserName() {
		return responsibilityUserName;
	}

	public String getItemCategoryValue() {
		return itemCategoryValue;
	}

	public String getScanItemCategoryValue() {
		return scanItemCategoryValue;
	}

	public String getScanResponsibilityDeptName() {
		return scanResponsibilityDeptName;
	}

	public String getScanResponsibilityUserName() {
		return scanResponsibilityUserName;
	}

	public SimpleCalendar getScanDate() throws CalendarException {
		scanDate.setCalPattern(getCalPattern());
		return scanDate;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public String getNetUnit() {
		return netUnit;
	}
}
