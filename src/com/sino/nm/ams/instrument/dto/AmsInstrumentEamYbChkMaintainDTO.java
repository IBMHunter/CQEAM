package com.sino.nm.ams.instrument.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

import com.sino.ams.bean.CommonRecordDTO;

public class AmsInstrumentEamYbChkMaintainDTO extends CommonRecordDTO {

//----------------------------������EAM_YB_CHK_MAINSTAIN(�����Ǳ���ޱ�)�е��ֶ�--------------------------------//
	private String maintainId = ""; //EAM_YB_CHK_MAINSTAIN_S.NEXTVAL
	private String barcode = ""; //�Ǳ�����
	private String barcode1 = ""; //Ϊ������ҳ���barcode����
	private String barcodeNo = ""; //Ϊ������ҳ���barcode���ƣ���Ϊҳ��checkbox��ֵ
	private String status = ""; //״̬,���ֵ�"YB_STATUS"
	private String checkUserId = ""; //������
	private SimpleCalendar checkDate = null; //��������
	private String checkStatus = ""; //���޽�����ֵ�"YB_CHECK_RESULT"
	private String remark = ""; //��ע
	private String orgId = ""; //��֯ID

//----------------------------------------������ETS_SYSTEM_ITEM���е��ֶ�-------------------------------------//
	private String itemCategory2 = ""; //Ŀ¼���
	private String itemName = ""; //Ʒ��
	private String itemSpec = ""; //����ͺ�
	private String itemUnit = ""; //������λ
	private String itemCode = ""; //�������
	private SimpleCalendar minTime = null; //��С����ʱ��
	private SimpleCalendar maxTime = null; //������ʱ��

//---------------------------------------������ETS_ITEM_INFO���е��ֶ�----------------------------------------//
	private int organizationId = -1; //��˾ID

//---------------------------------------������SF_USER���е��ֶ�----------------------------------------------//
	private String userId = ""; //��Ӧ�����˵�ID
	private String username = ""; //��ʾ����������
	private String checkStatusName = ""; //���޽����ȡ�ֵ���״̬��VALUEֵ�����ֶ��Ǳ���

//---------------------------------------������EAM_YB_CHK_TASK����ֶ�
	private String taskId = ""; //����ID
	private String taskName = ""; //��������
	private SimpleCalendar startDate = null; //��ʼʱ��(YYYY-MM-DD HH24:MI:SS)
	private SimpleCalendar endDate = null; //����ʱ��(YYYY-MM-DD HH24:MI:SS)
	
//---------------------------------------�����Ǳ���Ĺ��췽����SET��GET����-------------------------------------//
	public AmsInstrumentEamYbChkMaintainDTO() {
		this.checkDate = new SimpleCalendar();
		this.minTime = new SimpleCalendar();
		this.maxTime = new SimpleCalendar();
		this.startDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ������豸������Ϣ(AMS)���� ��������
	 * @param checkDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setChckDate(String checkDate) throws CalendarException {
		this.checkDate.setCalendarValue(checkDate);
	}

	/**
	 * ���ܣ���ȡ�豸������Ϣ(AMS)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCheckDate() throws CalendarException {
		checkDate.setCalPattern(getCalPattern());
		return this.checkDate;
	}

	/**
	 * ���ܣ������豸������Ϣ(AMS)���� ��������
	 * @param minTime SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setMinTime(String minTime) throws CalendarException {
		this.minTime.setCalendarValue(minTime);
	}

	/**
	 * ���ܣ���ȡ�豸������Ϣ(AMS)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getMinTime() throws CalendarException {
		minTime.setCalPattern(getCalPattern());
		return this.minTime;
	}

	/**
	 * ���ܣ������豸������Ϣ(AMS)���� ��������
	 * @param maxTime SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setMaxTime(String maxTime) throws CalendarException {
		this.maxTime.setCalendarValue(maxTime);
	}

	/**
	 * ���ܣ���ȡ�豸������Ϣ(AMS)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getMaxTime() throws CalendarException {
		maxTime.setCalPattern(getCalPattern());
		return this.maxTime;
	}

	/**
	 * ���ܣ������豸������Ϣ(AMS)���� ��������
	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException {
		this.startDate.setCalendarValue(startDate);
	}

	/**
	 * ���ܣ���ȡ�豸������Ϣ(AMS)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}

	/**
	 * ���ܣ������豸������Ϣ(AMS)���� ��������
	 * @param endDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setEndDate(String endDate) throws CalendarException {
		this.endDate.setCalendarValue(endDate);
	}

	/**
	 * ���ܣ���ȡ�豸������Ϣ(AMS)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getEndDate() throws CalendarException {
		endDate.setCalPattern(getCalPattern());
		return this.endDate;
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

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
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

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCheckStatusName() {
		return checkStatusName;
	}

	public void setCheckStatusName(String checkStatusName) {
		this.checkStatusName = checkStatusName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getBarcode1() {
		return barcode1;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}

	public String getBarcodeNo() {
		return barcodeNo;
	}

	public void setBarcodeNo(String barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
}
