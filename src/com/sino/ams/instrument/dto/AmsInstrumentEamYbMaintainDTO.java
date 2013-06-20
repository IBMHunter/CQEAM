package com.sino.ams.instrument.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class AmsInstrumentEamYbMaintainDTO extends CommonRecordDTO {

//-----------------------------------------������EAM_YB_MAINTAIN(�����Ǳ�ά�ޱ�)�е��ֶ�--------------------------//
	private String maintainId = ""; //EAM_YB_MAINTAIN_S.NEXTVAL
	private String barcode = ""; //�Ǳ�����  ��  ��ǩ���(��EAM_YB_BORROW_LOG�д����Ǳ�����)
	private String barcode1 = ""; //Ϊ������ҳ���ϵ�ͬ������
	private String status = ""; //״̬,���ֵ�"YB_STATUS"
	private int repairUserId = SyBaseSQLUtil.NULL_INT_VALUE; //������
	private SimpleCalendar repairDate = null; //��������
	private SimpleCalendar returnDatePlan = null; //Ԥ�ƹ黹ʱ��
	private SimpleCalendar returnDateActual = null; //ʵ�ʹ黹����
	private int returnUserId = SyBaseSQLUtil.NULL_INT_VALUE; //����������
	private String remark = ""; //��ע
	private int orgId = SyBaseSQLUtil.NULL_INT_VALUE; //��֯ID
	
//----------------------------------------������ETS_SYSTEM_ITEM���е��ֶ�---------------------------------------//
	private String itemCategory2 = ""; //Ŀ¼���
	private String itemName = ""; //Ʒ��
	private String itemSpec = ""; //����ͺ�
	private String itemUnit = ""; //������λ
	private String itemCode = ""; //�������
	
//----------------------------------------������ETS_ITEM_INFO���е��ֶ�-----------------------------------------//
	private String vendorBarcode = ""; //��Ʒ���
	private String attribute3 = ""; //�������̣��Ǳ���;
	private SimpleCalendar startDate = null; //��������
	
//--------------------------------------�����Ǳ���Ĺ��췽����SET��GET����----------------------------------------//
	public AmsInstrumentEamYbMaintainDTO() {
		this.repairDate = new SimpleCalendar();
		this.returnDatePlan = new SimpleCalendar();
		this.returnDateActual = new SimpleCalendar();
		this.startDate = new SimpleCalendar();
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
	 * @param repairDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setRepairDate(String repairDate) throws CalendarException{
		this.repairDate.setCalendarValue(repairDate);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getRepairDate() throws CalendarException {
		repairDate.setCalPattern(getCalPattern());
		return this.repairDate;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� Ԥ�ƹ黹ʱ��
	 * @param returnDatePlan SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReturnDatePlan(String returnDatePlan) throws CalendarException{
		this.returnDatePlan.setCalendarValue(returnDatePlan);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� Ԥ�ƹ黹ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReturnDatePlan() throws CalendarException {
		returnDatePlan.setCalPattern(getCalPattern());
		return this.returnDatePlan;
	}
	
	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ʵ�ʹ黹����
	 * @param returnDateActual SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReturnDateActual(String returnDateActual) throws CalendarException{
		this.returnDateActual.setCalendarValue(returnDateActual);
	}
	
	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ʵ�ʹ黹����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReturnDateActual() throws CalendarException {
		returnDateActual.setCalPattern(getCalPattern());
		return this.returnDateActual;
	}

	public String getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRepairUserId() {
		return repairUserId;
	}

	public void setRepairUserId(int repairUserId) {
		this.repairUserId = repairUserId;
	}

	public int getReturnUserId() {
		return returnUserId;
	}

	public void setReturnUserId(int returnUserId) {
		this.returnUserId = returnUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getVendorBarcode() {
		return vendorBarcode;
	}

	public void setVendorBarcode(String vendorBarcode) {
		this.vendorBarcode = vendorBarcode;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getBarcode1() {
		return barcode1;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}
}
