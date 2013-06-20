package com.sino.soa.td.srv.vendor.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: SRV_VENDOR_INFO SrvVendorInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class TdSrvVendorInfoDTO extends CheckBoxDTO{

	private String vendorId = null;
	private String vendorName = "";
	private String vendorNumber = "";
	private String vendorTypeLookupCode = "";
	private String vendorTypeDisp = "";
	private SimpleCalendar vendorCreationDate = null;
	private String createdBy = null;
	private String vatFlag = "";
	private String vatRegistrationNum = "";
	private SimpleCalendar vendorEndDateActive = null;
	private String attribute1 = "";
	private String conVendorId = null;
	private String employeeName = "";
	private String personId = null;
	private String employeeNumber = "";
	private SimpleCalendar lastUpdateDate = null;
	private String vendorNameAlt = "";
	private String companyCode = "";
	private SimpleCalendar inactiveDate = null;
	private String allowSubstituteReceiptsFlag = "";
	private String assetsType ="";

	public TdSrvVendorInfoDTO() {
		super();
		this.vendorCreationDate = new SimpleCalendar();
		this.vendorEndDateActive = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
		this.inactiveDate = new SimpleCalendar();

	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ��ID
	 * @param vendorId String
	 */
	public void setVendorId(String vendorId){
		this.vendorId = vendorId;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ������
	 * @param vendorName String
	 */
	public void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ�̱��
	 * @param vendorNumber String
	 */
	public void setVendorNumber(String vendorNumber){
		this.vendorNumber = vendorNumber;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ��������
	 * @param vendorTypeLookupCode String
	 */
	public void setVendorTypeLookupCode(String vendorTypeLookupCode){
		this.vendorTypeLookupCode = vendorTypeLookupCode;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ�����
	 * @param vendorTypeDisp String
	 */
	public void setVendorTypeDisp(String vendorTypeDisp){
		this.vendorTypeDisp = vendorTypeDisp;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ�̴���ʱ��
	 * @param vendorCreationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setVendorCreationDate(String vendorCreationDate) throws CalendarException{
		this.vendorCreationDate.setCalendarValue(vendorCreationDate);
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��˰�˱�ʶ
	 * @param vatFlag String
	 */
	public void setVatFlag(String vatFlag){
		this.vatFlag = vatFlag;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ��˰��ǼǺ�
	 * @param vatRegistrationNum String
	 */
	public void setVatRegistrationNum(String vatRegistrationNum){
		this.vatRegistrationNum = vatRegistrationNum;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ�̽�ֹ���� 
	 * @param vendorEndDateActive SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setVendorEndDateActive(String vendorEndDateActive) throws CalendarException{
		this.vendorEndDateActive.setCalendarValue(vendorEndDateActive);
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ������Ӧ������
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ������Ӧ��ID 
	 * @param conVendorId String
	 */
	public void setConVendorId(String conVendorId){
		this.conVendorId = conVendorId;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� Ա������
	 * @param employeeName String
	 */
	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��ԱID 
	 * @param personId String
	 */
	public void setPersonId(String personId){
		this.personId = personId;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� Ա�����
	 * @param employeeNumber String
	 */
	public void setEmployeeNumber(String employeeNumber){
		this.employeeNumber = employeeNumber;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ�̵ص�������ʱ��
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ��Ӧ�̱���
	 * @param vendorNameAlt String
	 */
	public void setVendorNameAlt(String vendorNameAlt){
		this.vendorNameAlt = vendorNameAlt;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ������Ӧ�̴���
	 * @param companyCode String
	 */
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� ʧЧ����
	 * @param inactiveDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setInactiveDate(String inactiveDate) throws CalendarException{
		this.inactiveDate.setCalendarValue(inactiveDate);
	}

	/**
	 * ���ܣ�����SRV_VENDOR_INFO���� �Ƿ���
	 * @param allowSubstituteReceiptsFlag String
	 */
	public void setAllowSubstituteReceiptsFlag(String allowSubstituteReceiptsFlag){
		this.allowSubstituteReceiptsFlag = allowSubstituteReceiptsFlag;
	}


	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ��ID
	 * @return String
	 */
	public String getVendorId() {
		return this.vendorId;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ������
	 * @return String
	 */
	public String getVendorName() {
		return this.vendorName;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ�̱��
	 * @return String
	 */
	public String getVendorNumber() {
		return this.vendorNumber;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ��������
	 * @return String
	 */
	public String getVendorTypeLookupCode() {
		return this.vendorTypeLookupCode;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ�����
	 * @return String
	 */
	public String getVendorTypeDisp() {
		return this.vendorTypeDisp;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ�̴���ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getVendorCreationDate() throws CalendarException {
		vendorCreationDate.setCalPattern(getCalPattern());
		return this.vendorCreationDate;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ������
	 * @return String
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��˰�˱�ʶ
	 * @return String
	 */
	public String getVatFlag() {
		return this.vatFlag;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ��˰��ǼǺ�
	 * @return String
	 */
	public String getVatRegistrationNum() {
		return this.vatRegistrationNum;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ�̽�ֹ���� 
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getVendorEndDateActive() throws CalendarException {
		vendorEndDateActive.setCalPattern(getCalPattern());
		return this.vendorEndDateActive;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ������Ӧ������
	 * @return String
	 */
	public String getAttribute1() {
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ������Ӧ��ID 
	 * @return String
	 */
	public String getConVendorId() {
		return this.conVendorId;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� Ա������
	 * @return String
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��ԱID 
	 * @return String
	 */
	public String getPersonId() {
		return this.personId;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� Ա�����
	 * @return String
	 */
	public String getEmployeeNumber() {
		return this.employeeNumber;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ�̵ص�������ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ��Ӧ�̱���
	 * @return String
	 */
	public String getVendorNameAlt() {
		return this.vendorNameAlt;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ������Ӧ�̴���
	 * @return String
	 */
	public String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� ʧЧ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getInactiveDate() throws CalendarException {
		inactiveDate.setCalPattern(getCalPattern());
		return this.inactiveDate;
	}

	/**
	 * ���ܣ���ȡSRV_VENDOR_INFO���� �Ƿ���
	 * @return String
	 */
	public String getAllowSubstituteReceiptsFlag() {
		return this.allowSubstituteReceiptsFlag;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

}