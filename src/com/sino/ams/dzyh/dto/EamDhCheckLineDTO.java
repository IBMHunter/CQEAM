package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��ֵ�׺��̵㹤���б� EamDhCheckLine</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EamDhCheckLineDTO extends EamDhCheckHeaderDTO{

	private String barcode = "";
	private String itemCode = "";
	private String itemCategory = "";
	private String itemName = "";
	private String itemSpec = "";
	private String itemCategory2 = "";
	private String itemCategory2Value = "";
	private String catalogValueId = "";
	private int price = 0;
	private int responsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String responsibilityUserName = "";
	private String responsibilityDept = "";
	private String responsibilityDeptName = "";
	private String maintainUser = "";
	private int vendorId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String vendorName = "";
	private String scanItemCode = "";
	private String scanItemCategory = "";
	private String scanItemName = "";
	private String scanItemSpec = "";
	private String scanItemCategory2 = "";
	private String scanCatalogValueId = "";
	private float scanPrice = 0;
	private SimpleCalendar scanStartDate = null;
	private int scanResponsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE ;
	private String scanResponsibilityUserName = "";
	private String scanResponsibilityDept = "";
	private String scanResponsibilityDeptName = "";
	private String scanMaintainUser = "";
	private int scanVendorId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String scanVendorName = "";
	private String archItemCode = "";
	private String archItemCategory = "";
	private String archItemName = "";
	private String archItemSpec = "";
	private String archItemCategory2 = "";
	private String archCatalogValueId = "";
	private float archPrice = 0;
	private SimpleCalendar archStartDate = null;
	private int archResponsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String archResponsibilityDept = "";
	private String archResponsibilityUserName = "";
	private String archResponsibilityDeptName = "";
	private String archMaintainUser = "";
	private int archVendorId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String archVendorName = "";
	private String itemCodeDiffReason = "";
	private String addressDiffReason = "";
	private String userDiffReason = "";
	private String deptDiffReason = "";
	private String category2DiffReason = "";
	private String vendorDiffReason = "";
	private String priceDiffReason = "";
	private String startDateDiffReason = "";
	private String mainUserDiffReason = "";
	private int confirmUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar confirmDate = null;
	private String systemStatus = "";
	private String scanStatus = "";
	private String archiveStatus = "";
	private String archiveRemark = "";
	private String archToTempInv = "";
	private String confirmUserName = "";

	private SimpleCalendar scanDate = null;
	private String netUnit = "0000";
	private String boxNo = "0000";
	private String itemCategoryValue = "";
	private String scanItemCategoryValue = "";
	private String archItemCategoryValue = "";

	public EamDhCheckLineDTO() {
		super();
		this.scanStartDate = new SimpleCalendar();
		this.archStartDate = new SimpleCalendar();
		this.confirmDate = new SimpleCalendar();
		this.scanDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ��ǩ��
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ�������
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ�豸����
	 * @param itemCategory String
	 */
	public void setItemCategory(String itemCategory){
		this.itemCategory = itemCategory;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ�豸����
	 * @param itemName String
	 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ����ͺ�
	 * @param itemSpec String
	 */
	public void setItemSpec(String itemSpec){
		this.itemSpec = itemSpec;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭĿ¼���
	 * @param itemCategory2 String
	 */
	public void setItemCategory2(String itemCategory2){
		this.itemCategory2 = itemCategory2;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ����
	 * @param price String
	 */
	public void setPrice(int price){
		this.price = price;
	}


	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ������ID
	 * @param responsibilityUser String
	 */
	public void setResponsibilityUser(int responsibilityUser){
		this.responsibilityUser = responsibilityUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ����������
	 * @param responsibilityUserName String
	 */
	public void setResponsibilityUserName(String responsibilityUserName){
		this.responsibilityUserName = responsibilityUserName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ���β��Ŵ���
	 * @param responsibilityDept String
	 */
	public void setResponsibilityDept(String responsibilityDept){
		this.responsibilityDept = responsibilityDept;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ���β�������
	 * @param responsibilityDeptName String
	 */
	public void setResponsibilityDeptName(String responsibilityDeptName){
		this.responsibilityDeptName = responsibilityDeptName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭʹ����
	 * @param maintainUser String
	 */
	public void setMaintainUser(String maintainUser){
		this.maintainUser = maintainUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ����ID
	 * @param vendorId String
	 */
	public void setVendorId(int vendorId){
		this.vendorId = vendorId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ԭ��������
	 * @param vendorName String
	 */
	public void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�ķ������
	 * @param scanItemCode String
	 */
	public void setScanItemCode(String scanItemCode){
		this.scanItemCode = scanItemCode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ���豸����
	 * @param scanItemCategory String
	 */
	public void setScanItemCategory(String scanItemCategory){
		this.scanItemCategory = scanItemCategory;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ���豸����
	 * @param scanItemName String
	 */
	public void setScanItemName(String scanItemName){
		this.scanItemName = scanItemName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�Ĺ���ͺ�
	 * @param scanItemSpec String
	 */
	public void setScanItemSpec(String scanItemSpec){
		this.scanItemSpec = scanItemSpec;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ��Ŀ¼���
	 * @param scanItemCategory2 String
	 */
	public void setScanItemCategory2(String scanItemCategory2){
		this.scanItemCategory2 = scanItemCategory2;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�ĵ���
	 * @param scanPrice String
	 */
	public void setScanPrice(float scanPrice){
		this.scanPrice = scanPrice;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ����������
	 * @param scanStartDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setScanStartDate(String scanStartDate) throws CalendarException{
		this.scanStartDate.setCalendarValue(scanStartDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ��������ID
	 * @param scanResponsibilityUser String
	 */
	public void setScanResponsibilityUser(int scanResponsibilityUser){
		this.scanResponsibilityUser = scanResponsibilityUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ������������
	 * @param scanResponsibilityUserName String
	 */
	public void setScanResponsibilityUserName(String scanResponsibilityUserName){
		this.scanResponsibilityUserName = scanResponsibilityUserName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�����β��Ŵ���
	 * @param scanResponsibilityDept String
	 */
	public void setScanResponsibilityDept(String scanResponsibilityDept){
		this.scanResponsibilityDept = scanResponsibilityDept;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�����β�������
	 * @param scanResponsibilityDeptName String
	 */
	public void setScanResponsibilityDeptName(String scanResponsibilityDeptName){
		this.scanResponsibilityDeptName = scanResponsibilityDeptName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ��ʹ����
	 * @param scanMaintainUser String
	 */
	public void setScanMaintainUser(String scanMaintainUser){
		this.scanMaintainUser = scanMaintainUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�ĳ���ID
	 * @param scanVendorId String
	 */
	public void setScanVendorId(int scanVendorId){
		this.scanVendorId = scanVendorId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ���ύ�ĳ�������
	 * @param scanVendorName String
	 */
	public void setScanVendorName(String scanVendorName){
		this.scanVendorName = scanVendorName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵�������
	 * @param archItemCode String
	 */
	public void setArchItemCode(String archItemCode){
		this.archItemCode = archItemCode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵�豸����
	 * @param archItemCategory String
	 */
	public void setArchItemCategory(String archItemCategory){
		this.archItemCategory = archItemCategory;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵�豸����
	 * @param archItemName String
	 */
	public void setArchItemName(String archItemName){
		this.archItemName = archItemName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵�豸�ͺ�
	 * @param archItemSpec String
	 */
	public void setArchItemSpec(String archItemSpec){
		this.archItemSpec = archItemSpec;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵Ŀ¼���
	 * @param archItemCategory2 String
	 */
	public void setArchItemCategory2(String archItemCategory2){
		this.archItemCategory2 = archItemCategory2;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵����
	 * @param archPrice String
	 */
	public void setArchPrice(float archPrice){
		this.archPrice = archPrice;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵��������
	 * @param archStartDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setArchStartDate(String archStartDate) throws CalendarException{
		this.archStartDate.setCalendarValue(archStartDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵������ID
	 * @param archResponsibilityUser String
	 */
	public void setArchResponsibilityUser(int archResponsibilityUser){
		this.archResponsibilityUser = archResponsibilityUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵���β��Ŵ���
	 * @param archResponsibilityDept String
	 */
	public void setArchResponsibilityDept(String archResponsibilityDept){
		this.archResponsibilityDept = archResponsibilityDept;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵����������
	 * @param archResponsibilityUserName String
	 */
	public void setArchResponsibilityUserName(String archResponsibilityUserName){
		this.archResponsibilityUserName = archResponsibilityUserName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵���β�������
	 * @param archResponsibilityDeptName String
	 */
	public void setArchResponsibilityDeptName(String archResponsibilityDeptName){
		this.archResponsibilityDeptName = archResponsibilityDeptName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵ʹ����
	 * @param archMaintainUser String
	 */
	public void setArchMaintainUser(String archMaintainUser){
		this.archMaintainUser = archMaintainUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵����ID
	 * @param archVendorId String
	 */
	public void setArchVendorId(int archVendorId){
		this.archVendorId = archVendorId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵��������
	 * @param archVendorName String
	 */
	public void setArchVendorName(String archVendorName){
		this.archVendorName = archVendorName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �豸�����ԭ��
	 * @param itemCodeDiffReason String
	 */
	public void setItemCodeDiffReason(String itemCodeDiffReason){
		this.itemCodeDiffReason = itemCodeDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �ص�������;��ԭ��
	 * @param addressDiffReason String
	 */
	public void setAddressDiffReason(String addressDiffReason){
		this.addressDiffReason = addressDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �����˱��ԭ��
	 * @param userDiffReason String
	 */
	public void setUserDiffReason(String userDiffReason){
		this.userDiffReason = userDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ���β��ű��ԭ��
	 * @param deptDiffReason String
	 */
	public void setDeptDiffReason(String deptDiffReason){
		this.deptDiffReason = deptDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� Ŀ¼��ű��ԭ��
	 * @param category2DiffReason String
	 */
	public void setCategory2DiffReason(String category2DiffReason){
		this.category2DiffReason = category2DiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ���ұ��ԭ��
	 * @param vendorDiffReason String
	 */
	public void setVendorDiffReason(String vendorDiffReason){
		this.vendorDiffReason = vendorDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ���۱��ԭ��
	 * @param priceDiffReason String
	 */
	public void setPriceDiffReason(String priceDiffReason){
		this.priceDiffReason = priceDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �������ڱ��ԭ��
	 * @param startDateDiffReason String
	 */
	public void setStartDateDiffReason(String startDateDiffReason){
		this.startDateDiffReason = startDateDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ʹ���˱��ԭ��
	 * @param mainUserDiffReason String
	 */
	public void setMainUserDiffReason(String mainUserDiffReason){
		this.mainUserDiffReason = mainUserDiffReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ��ǰ��WEB��ʽȷ����
	 * @param confirmUser String
	 */
	public void setConfirmUser(int confirmUser){
		this.confirmUser = confirmUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ��ǰ��WEB��ʽȷ��ʱ��
	 * @param confirmDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setConfirmDate(String confirmDate) throws CalendarException{
		this.confirmDate.setCalendarValue(confirmDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ϵͳ״̬(�������̵�ص����޸��豸)
	 * @param systemStatus String
	 */
	public void setSystemStatus(String systemStatus){
		this.systemStatus = systemStatus;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ɨ��״̬(������ص����޸��豸)
	 * @param scanStatus String
	 */
	public void setScanStatus(String scanStatus){
		this.scanStatus = scanStatus;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �鵵״̬(0:��ɨ����Ϊ׼��1:��Ŀǰ״̬Ϊ׼)
	 * @param archiveStatus String
	 */
	public void setArchiveStatus(String archiveStatus){
		this.archiveStatus = archiveStatus;
	}
	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� ��ʵ��ע(��ɨ����Ϊ׼����Ŀǰ״̬Ϊ׼)
	 * @param archiveRemark String
	 */
	public void setArchiveRemark(String archiveRemark){
		this.archiveRemark = archiveRemark;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���б����� �Ƿ�鵵����;��
	 * @param archToTempInv String
	 */
	public void setArchToTempInv(String archToTempInv){
		this.archToTempInv = archToTempInv;
	}

	public void setConfirmUserName(String confirmUserName) {
		this.confirmUserName = confirmUserName;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public void setNetUnit(String netUnit) {
		this.netUnit = netUnit;
	}

	public void setArchItemCategoryValue(String archItemCategoryValue) {
		this.archItemCategoryValue = archItemCategoryValue;
	}

	public void setItemCategoryValue(String itemCategoryValue) {
		this.itemCategoryValue = itemCategoryValue;
	}

	public void setScanItemCategoryValue(String scanItemCategoryValue) {
		this.scanItemCategoryValue = scanItemCategoryValue;
	}

	public void setItemCategory2Value(String itemCategory2Value) {
		this.itemCategory2Value = itemCategory2Value;
	}

	public void setArchCatalogValueId(String archCatalogValueId) {
		this.archCatalogValueId = archCatalogValueId;
	}

	public void setCatalogValueId(String catalogValueId) {
		this.catalogValueId = catalogValueId;
	}

	public void setScanCatalogValueId(String scanCatalogValueId) {
		this.scanCatalogValueId = scanCatalogValueId;
	}

	public void setScanDate(String scanDate) throws CalendarException {
		this.scanDate.setCalendarValue(scanDate);
	}


	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ��ǩ��
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ�������
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ�豸����
	 * @return String
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ�豸����
	 * @return String
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ����ͺ�
	 * @return String
	 */
	public String getItemSpec() {
		return this.itemSpec;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭĿ¼���
	 * @return String
	 */
	public String getItemCategory2() {
		return this.itemCategory2;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ����
	 * @return String
	 */

	public int getPrice() {
		return this.price;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ������ID
	 * @return String
	 */
	public int getResponsibilityUser() {
		return this.responsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ����������
	 * @return String
	 */
	public String getResponsibilityUserName() {
		return this.responsibilityUserName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ���β��Ŵ���
	 * @return String
	 */
	public String getResponsibilityDept() {
		return this.responsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ���β�������
	 * @return String
	 */
	public String getResponsibilityDeptName() {
		return this.responsibilityDeptName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭʹ����
	 * @return String
	 */
	public String getMaintainUser() {
		return this.maintainUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ����ID
	 * @return String
	 */
	public int getVendorId() {
		return this.vendorId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ԭ��������
	 * @return String
	 */
	public String getVendorName() {
		return this.vendorName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�ķ������
	 * @return String
	 */
	public String getScanItemCode() {
		return this.scanItemCode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ���豸����
	 * @return String
	 */
	public String getScanItemCategory() {
		return this.scanItemCategory;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ���豸����
	 * @return String
	 */
	public String getScanItemName() {
		return this.scanItemName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�Ĺ���ͺ�
	 * @return String
	 */
	public String getScanItemSpec() {
		return this.scanItemSpec;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ��Ŀ¼���
	 * @return String
	 */
	public String getScanItemCategory2() {
		return this.scanItemCategory2;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�ĵ���
	 * @return String
	 */
	public float getScanPrice() {
		return this.scanPrice;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ����������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getScanStartDate() throws CalendarException {
		scanStartDate.setCalPattern(getCalPattern());
		return this.scanStartDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ��������ID
	 * @return String
	 */
	public int getScanResponsibilityUser() {
		return this.scanResponsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ������������
	 * @return String
	 */
	public String getScanResponsibilityUserName() {
		return this.scanResponsibilityUserName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�����β��Ŵ���
	 * @return String
	 */
	public String getScanResponsibilityDept() {
		return this.scanResponsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�����β�������
	 * @return String
	 */
	public String getScanResponsibilityDeptName() {
		return this.scanResponsibilityDeptName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ��ʹ����
	 * @return String
	 */
	public String getScanMaintainUser() {
		return this.scanMaintainUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�ĳ���ID
	 * @return String
	 */
	public int getScanVendorId() {
		return this.scanVendorId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ���ύ�ĳ�������
	 * @return String
	 */
	public String getScanVendorName() {
		return this.scanVendorName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵�������
	 * @return String
	 */
	public String getArchItemCode() {
		return this.archItemCode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵�豸����
	 * @return String
	 */
	public String getArchItemCategory() {
		return this.archItemCategory;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵�豸����
	 * @return String
	 */
	public String getArchItemName() {
		return this.archItemName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵�豸�ͺ�
	 * @return String
	 */
	public String getArchItemSpec() {
		return this.archItemSpec;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵Ŀ¼���
	 * @return String
	 */
	public String getArchItemCategory2() {
		return this.archItemCategory2;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵����
	 * @return String
	 */
	public float getArchPrice() {
		return this.archPrice;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getArchStartDate() throws CalendarException {
		archStartDate.setCalPattern(getCalPattern());
		return this.archStartDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵������ID
	 * @return String
	 */
	public int getArchResponsibilityUser() {
		return this.archResponsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵���β��Ŵ���
	 * @return String
	 */
	public String getArchResponsibilityDept() {
		return this.archResponsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵����������
	 * @return String
	 */
	public String getArchResponsibilityUserName() {
		return this.archResponsibilityUserName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵���β�������
	 * @return String
	 */
	public String getArchResponsibilityDeptName() {
		return this.archResponsibilityDeptName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵ʹ����
	 * @return String
	 */
	public String getArchMaintainUser() {
		return this.archMaintainUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵����ID
	 * @return String
	 */
	public int getArchVendorId() {
		return this.archVendorId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵��������
	 * @return String
	 */
	public String getArchVendorName() {
		return this.archVendorName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �豸�����ԭ��
	 * @return String
	 */
	public String getItemCodeDiffReason() {
		return this.itemCodeDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �ص�������;��ԭ��
	 * @return String
	 */
	public String getAddressDiffReason() {
		return this.addressDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �����˱��ԭ��
	 * @return String
	 */
	public String getUserDiffReason() {
		return this.userDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ���β��ű��ԭ��
	 * @return String
	 */
	public String getDeptDiffReason() {
		return this.deptDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� Ŀ¼��ű��ԭ��
	 * @return String
	 */
	public String getCategory2DiffReason() {
		return this.category2DiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ���ұ��ԭ��
	 * @return String
	 */
	public String getVendorDiffReason() {
		return this.vendorDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ���۱��ԭ��
	 * @return String
	 */
	public String getPriceDiffReason() {
		return this.priceDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �������ڱ��ԭ��
	 * @return String
	 */
	public String getStartDateDiffReason() {
		return this.startDateDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ʹ���˱��ԭ��
	 * @return String
	 */
	public String getMainUserDiffReason() {
		return this.mainUserDiffReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ��ǰ��WEB��ʽȷ����
	 * @return String
	 */
	public int getConfirmUser() {
		return this.confirmUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ��ǰ��WEB��ʽȷ��ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getConfirmDate() throws CalendarException {
		confirmDate.setCalPattern(getCalPattern());
		return this.confirmDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ϵͳ״̬(�������̵�ص����޸��豸)
	 * @return String
	 */
	public String getSystemStatus() {
		return this.systemStatus;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ɨ��״̬(������ص����޸��豸)
	 * @return String
	 */
	public String getScanStatus() {
		return this.scanStatus;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �鵵״̬(0:��ɨ����Ϊ׼��1:��Ŀǰ״̬Ϊ׼)
	 * @return String
	 */
	public String getArchiveStatus() {
		return this.archiveStatus;
	}
	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� ��ʵ��ע(��ɨ����Ϊ׼����Ŀǰ״̬Ϊ׼)
	 * @return String
	 */
	public String getArchiveRemark() {
		return this.archiveRemark;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���б����� �Ƿ�鵵����;��
	 * @return String
	 */
	public String getArchToTempInv() {
		return this.archToTempInv;
	}

	public String getConfirmUserName() {
		return confirmUserName;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public String getNetUnit() {
		return netUnit;
	}

	public SimpleCalendar getScanDate() throws CalendarException {
		scanDate.setCalPattern(getCalPattern());
		return scanDate;
	}

	public String getArchItemCategoryValue() {
		return archItemCategoryValue;
	}

	public String getItemCategoryValue() {
		return itemCategoryValue;
	}

	public String getScanItemCategoryValue() {
		return scanItemCategoryValue;
	}

	public String getItemCategory2Value() {
		return itemCategory2Value;
	}

	public String getArchCatalogValueId() {
		return archCatalogValueId;
	}

	public String getCatalogValueId() {
		return catalogValueId;
	}

	public String getScanCatalogValueId() {
		return scanCatalogValueId;
	}
}
