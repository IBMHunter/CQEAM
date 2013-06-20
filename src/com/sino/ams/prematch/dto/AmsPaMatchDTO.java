package com.sino.ams.prematch.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ�� AmsPaMatch</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsPaMatchDTO extends AmsPaAssetsDTO{

	private String groupId = "";
	private String printTimes = "";
	private int lastPrintBy;
	private SimpleCalendar lastPrintDate = null;
	private SimpleCalendar synchronizedDate = null;
	private String systemId = "";
	private String remark = "";

	//���²���AMSϵͳ������
	private String barcode = "";
	private String itemCategory = "";
	private String itemCategoryName = "";
	private String itemName = "";
	private String itemSpec = "";
	private String workorderObjectCode = "";
	private String workorderObjectName = "";
	private String projectNumberAms = "";
	private String projectNameAms = "";
	private String userName = "";
	private String srcPage = "";
	//���ϲ���AMSϵͳ������

	public AmsPaMatchDTO() {
		super();
		this.lastPrintDate = new SimpleCalendar();
		this.synchronizedDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ�����ID
	 * @param groupId String
	 */
	public void setGroupId(String groupId){
		this.groupId = groupId;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ��ӡ����
	 * @param printTimes String
	 */
	public void setPrintTimes(String printTimes){
		this.printTimes = printTimes;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ �ϴδ�ӡ��
	 * @param lastPrintBy String
	 */
	public void setLastPrintBy(int lastPrintBy){
		this.lastPrintBy = lastPrintBy;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public void setWorkorderObjectCode(String workorderObjectCode) {
		this.workorderObjectCode = workorderObjectCode;
	}

	public void setProjectNameAms(String projectNameAms) {
		this.projectNameAms = projectNameAms;
	}

	public void setProjectNumberAms(String projectNumberAms) {
		this.projectNumberAms = projectNumberAms;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSrcPage(String srcPage) {
		this.srcPage = srcPage;
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ �ϴδ�ӡʱ��
	 * @param lastPrintDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastPrintDate(String lastPrintDate) throws CalendarException{
		this.lastPrintDate.setCalendarValue(lastPrintDate);
	}

	/**
	 * ���ܣ�����EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ͬ��ʱ��
	 * @param synchronizedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setSynchronizedDate(String synchronizedDate) throws CalendarException{
		this.synchronizedDate.setCalendarValue(synchronizedDate);
	}


	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ƥ�����ID
	 * @return String
	 */
	public String getGroupId() {
		return this.groupId;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ��ӡ����
	 * @return String
	 */
	public String getPrintTimes() {
		return this.printTimes;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ �ϴδ�ӡ��
	 * @return String
	 */
	public int getLastPrintBy() {
		return this.lastPrintBy;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ �ϴδ�ӡʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastPrintDate() throws CalendarException {
		lastPrintDate.setCalPattern(getCalPattern());
		return this.lastPrintDate;
	}

	/**
	 * ���ܣ���ȡEAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ������ ͬ��ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getSynchronizedDate() throws CalendarException {
		synchronizedDate.setCalPattern(getCalPattern());
		return this.synchronizedDate;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public String getWorkorderObjectCode() {
		return workorderObjectCode;
	}

	public String getProjectNameAms() {
		return projectNameAms;
	}

	public String getProjectNumberAms() {
		return projectNumberAms;
	}

	public String getUserName() {
		return userName;
	}

	public String getWorkorderObjectName() {
		return workorderObjectName;
	}

	public String getSystemId() {
		return systemId;
	}

	public String getRemark() {
		return remark;
	}

	public String getSrcPage() {
		return srcPage;
	}
}
