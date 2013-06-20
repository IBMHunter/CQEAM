package com.sino.ams.equipment.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: AmsInstrumentTJDTO</p>
 * <p>Description:�����Զ����ɷ������AmsInstrumentTJDTO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */

public class AmsInstrumentTJDTO extends CommonRecordDTO {

//----------------------------------------������ETS_SYSTEM_ITEM���е��ֶ�---------------------------------------//
	private String itemCategory2 = ""; //Ŀ¼���
	private String itemName = ""; //Ʒ��
	private String itemSpec = ""; //����ͺ�
	private String itemUnit = ""; //������λ
	private String itemCode = ""; //�������
	
//----------------------------------------������ETS_ITEM_INFO���е��ֶ�-----------------------------------------//
	private String barcode = ""; //��ǩ���   (��EAM_YB_BORROW_LOG�д����Ǳ�����)
	private String vendorBarcode = ""; //��Ʒ���
	private String itemQty = ""; //����
	private String price = ""; //�ɱ�
	private String attribute3 = ""; //�������̣��Ǳ���;
	private String responsibilityDept = ""; //ʹ�ò���
	private String deptCode = "";
	private int responsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE; //������
	private String responsibilityUser1 = ""; //������1
	private String employeeId = "";
	private SimpleCalendar startDate = null; //��������
	private String itemStatus = ""; //ʹ��״̬
	private String maintainUser = ""; //ʹ����
	private String remark = ""; //��ע
	private SimpleCalendar disableDate = null; //ʧЧ����
	
//----------------------------------------������ETS_OBJECT���е��ֶ�--------------------------------------------//
	private String workorderObjectNo = ""; //�ص�ID
	private String workorderObjectName = ""; //��ŵص�
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE; //��˾����
	
//--------------------------------------������EAM_ITEM_DISPOSE���е��ֶ�----------------------------------------//
	private String disposeReason = ""; //����ԭ��(��Ҫ�����Ӵ���)
	
//--------------------------------------�����ǳ�ҳ����Ҫ��ʾ�ֶ�������Ҫ�õ����ֶ�-----------------------------------//
	private String deptName = ""; //��������
	private String userName = ""; //����������
	private String barcode1 = ""; //Ϊ������ʼ��ǩ���(����ǩ��ŷ�Χ��ѯʱʹ��)
	private String barcode2 = ""; //Ϊ������ʼ��ǩ���(����ǩ��ŷ�Χ��ѯʱʹ��)
	private String barcodeNo = ""; //Ϊ�˱�ʶradio��ǩ��
	private String vendorBarcode1 = ""; //Ϊ������ʼ��Ʒ���(����Ʒ��ŷ�Χ��ѯʱʹ��)
	
//--------------------------------------�����������Ǳ������־(EAM_YB_BORROW_LOG)���е��ֶ�------------------------//
	private String borrowLogId = ""; //������(Sequence����)
	private String status = ""; //״̬(������������������رա�����) ���ֵ�"YB_BORROW_STATUS"
	private int borrowUserId = SyBaseSQLUtil.NULL_INT_VALUE; //������
	private SimpleCalendar borrowDate = null; //��������
	private SimpleCalendar borrowDate1 = null; //��������1
	private SimpleCalendar returnDatePlan = null; //Ԥ�ƹ黹ʱ��
	private SimpleCalendar returnDateActual = null; //ʵ�ʹ黹����
	private int approveUserId = SyBaseSQLUtil.NULL_INT_VALUE; //������
	private SimpleCalendar approveDate = null; //��������
	private int handleUserId = SyBaseSQLUtil.NULL_INT_VALUE; //������
	private SimpleCalendar handleDate = null; //��������
	private int orgId = SyBaseSQLUtil.NULL_INT_VALUE; //��֯ID
	
//--------------------------------------�����Ǳ���Ĺ��췽����SET��GET����----------------------------------------//

	public AmsInstrumentTJDTO() {
		super();
		this.startDate = new SimpleCalendar();
		this.borrowDate = new SimpleCalendar();
		this.borrowDate1 = new SimpleCalendar();
		this.returnDatePlan = new SimpleCalendar();
		this.returnDateActual= new SimpleCalendar();
		this.approveDate = new SimpleCalendar();
		this.handleDate = new SimpleCalendar();
		this.disableDate = new SimpleCalendar();
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException{
		this.startDate.setCalendarValue(startDate);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param borrowDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setBorrowDate(String borrowDate) throws CalendarException{
		this.borrowDate.setCalendarValue(borrowDate);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getBorrowDate() throws CalendarException {
		borrowDate.setCalPattern(getCalPattern());
		return this.borrowDate;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param borrowDate1 SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setBorrowDate1(String borrowDate1) throws CalendarException{
		this.borrowDate1.setCalendarValue(borrowDate1);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getBorrowDate1() throws CalendarException {
		borrowDate1.setCalPattern(getCalPattern());
		return this.borrowDate1;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param returnDatePlan SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReturnDatePlan(String returnDatePlan) throws CalendarException{
		this.returnDatePlan.setCalendarValue(returnDatePlan);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReturnDatePlan() throws CalendarException {
		returnDatePlan.setCalPattern(getCalPattern());
		return this.returnDatePlan;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param returnDateActual SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReturnDateActual(String returnDateActual) throws CalendarException{
		this.returnDateActual.setCalendarValue(returnDateActual);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReturnDateActual() throws CalendarException {
		returnDateActual.setCalPattern(getCalPattern());
		return this.returnDateActual;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param approveDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setApproveDate(String approveDate) throws CalendarException{
		this.approveDate.setCalendarValue(approveDate);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getApproveDate() throws CalendarException {
		approveDate.setCalPattern(getCalPattern());
		return this.approveDate;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param handleDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setHandleDate(String handleDate) throws CalendarException{
		this.handleDate.setCalendarValue(handleDate);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getHandleDate() throws CalendarException {
		handleDate.setCalPattern(getCalPattern());
		return this.handleDate;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param disableDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDisableDate(String disableDate) throws CalendarException{
		this.disableDate.setCalendarValue(disableDate);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDisableDate() throws CalendarException {
		disableDate.setCalPattern(getCalPattern());
		return this.disableDate;
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

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getVendorBarcode() {
		return vendorBarcode;
	}

	public void setVendorBarcode(String vendorBarcode) {
		this.vendorBarcode = vendorBarcode;
	}

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}

	public int getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(int responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getMaintainUser() {
		return maintainUser;
	}

	public void setMaintainUser(String maintainUser) {
		this.maintainUser = maintainUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorkorderObjectName() {
		return workorderObjectName;
	}

	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getDisposeReason() {
		return disposeReason;
	}

	public void setDisposeReason(String disposeReason) {
		this.disposeReason = disposeReason;
	}
	
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

	public String getBarcode1() {
		return barcode1;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}

	public String getVendorBarcode1() {
		return vendorBarcode1;
	}

	public void setVendorBarcode1(String vendorBarcode1) {
		this.vendorBarcode1 = vendorBarcode1;
	}

	public String getBarcodeNo() {
		return barcodeNo;
	}

	public void setBarcodeNo(String barcodeNo) {
		this.barcodeNo = barcodeNo;
	}

	public String getWorkorderObjectNo() {
		return workorderObjectNo;
	}

	public void setWorkorderObjectNo(String workorderObjectNo) {
		this.workorderObjectNo = workorderObjectNo;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getBorrowLogId() {
		return borrowLogId;
	}

	public void setBorrowLogId(String borrowLogId) {
		this.borrowLogId = borrowLogId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBorrowUserId() {
		return borrowUserId;
	}

	public void setBorrowUserId(int borrowUserId) {
		this.borrowUserId = borrowUserId;
	}

	public int getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(int approveUserId) {
		this.approveUserId = approveUserId;
	}

	public int getHandleUserId() {
		return handleUserId;
	}

	public void setHandleUserId(int handleUserId) {
		this.handleUserId = handleUserId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getBarcode2() {
		return barcode2;
	}

	public void setBarcode2(String barcode2) {
		this.barcode2 = barcode2;
	}

	public String getResponsibilityUser1() {
		return responsibilityUser1;
	}

	public void setResponsibilityUser1(String responsibilityUser1) {
		this.responsibilityUser1 = responsibilityUser1;
	}
}
