package com.sino.ams.expand.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: ���������ʲ�(AMS) AmsLandInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����
 * @version 1.0
 */

public class EtsItemLandInfoDTO extends CommonRecordDTO {

    private String hasCertficate = "";//�Ƿ�������ʹ��֤
    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;

    private String systemId = "";
    private String barcode = "";	//��������
    private String itemCode = "";	//
    private String itemName = "";	//��������
    private String itemSpec = "";	//�����ͺ�
    private String addressId = "";	//
    
    //------------------------------------AMS_LAND_INFO----------------------------------------

    private String areaUnit = "";	//�ػ���λ
    private String landArea = "";	//�������
    private String remark = "";	//���ر�ע
    private String isRent = "";	//�Ƿ�����
    private String landCertficateNo = "";	//����֤��
    private String landAddress = "";	//���ڵ�ַ
    
    //------------------------------------AMS_RENT_INFO----------------------------------------
    private String rentId = "";	//���к�
    private String rentPerson = "";	//������/��λ
    private SimpleCalendar rentDate = null;	//��������
    private String rental = "";	//���
    private String moneyUnit = "";	//��������λ
    private String payType = "";	//���ʽ
    private String rentUsage = "";	//������;
    private String tenancy = "";	//����(��)
    private String yearRental = "";	//�����
    private String monthRental = "";	//�����
    
	public EtsItemLandInfoDTO(){
    	super();
    	this.rentDate=new SimpleCalendar();
    	this.fromDate=new SimpleCalendar();
    	this.toDate=new SimpleCalendar();
    }
	
    public SimpleCalendar getFromDate() throws CalendarException {
    	fromDate.setCalPattern(getCalPattern());
		return this.fromDate;
	}

	public void setFromDate(String fromDate) throws CalendarException {
		this.fromDate.setCalendarValue(fromDate);
	}

	public SimpleCalendar getToDate() throws CalendarException {
		toDate.setCalPattern(getCalPattern());
		return this.toDate;
	}

	public void setToDate(String toDate) throws CalendarException {
		this.toDate.setCalendarValue(toDate);
	}
    
	public String getIsRent() {
		return isRent;
	}
	public void setIsRent(String isRent) {
		this.isRent = isRent;
	}
	public SimpleCalendar getRentDate() throws CalendarException {
		rentDate.setCalPattern(getCalPattern());
		return this.rentDate;
	}
	public void setRentDate(String rentDate) throws CalendarException {
		this.rentDate.setCalendarValue(rentDate);
	}

	public String getHasCertficate() {
		return hasCertficate;
	}

	public void setHasCertficate(String hasCertficate) {
		this.hasCertficate = hasCertficate;
	}

	public String getAreaUnit() {
		return areaUnit;
	}

	public void setAreaUnit(String areaUnit) {
		this.areaUnit = areaUnit;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLandAddress() {
		return landAddress;
	}

	public void setLandAddress(String landAddress) {
		this.landAddress = landAddress;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getLandCertficateNo() {
		return landCertficateNo;
	}

	public void setLandCertficateNo(String landCertficateNo) {
		this.landCertficateNo = landCertficateNo;
	}

	public String getMoneyUnit() {
		return moneyUnit;
	}

	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}

	public String getMonthRental() {
		return monthRental;
	}

	public void setMonthRental(String monthRental) {
		this.monthRental = monthRental;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRentId() {
		return rentId;
	}

	public void setRentId(String rentId) {
		this.rentId = rentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	public String getRentPerson() {
		return rentPerson;
	}

	public void setRentPerson(String rentPerson) {
		this.rentPerson = rentPerson;
	}

	public String getRentUsage() {
		return rentUsage;
	}

	public void setRentUsage(String rentUsage) {
		this.rentUsage = rentUsage;
	}

	public String getTenancy() {
		return tenancy;
	}

	public void setTenancy(String tenancy) {
		this.tenancy = tenancy;
	}

	public String getYearRental() {
		return yearRental;
	}

	public void setYearRental(String yearRental) {
		this.yearRental = yearRental;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
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

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

}