package com.sino.ams.system.house.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ���޷���(EAM) AmsHouseInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsHouseInfoDTO extends CheckBoxDTO{

//	private String barcodeNo = "";
    private String barcode = "";
    private String rentPerson = "";
	private SimpleCalendar rentDate = null;
	private String houseAddress = "";
	private int floorNumber = 0;
	private String houseNo = "";
	private float houseArea ;
	private String areaUnit = "";
	private String rental = "";
	private String moneyUnit = "";
	private String payType = "";
	private SimpleCalendar endDate = null;
    private String itemSpec="";
    private String itemCode = "";
    private String systemId = "";
    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;
	private String itemName = "";
    private String workorderObjectLocation = "";
    private String workorderObjectCode = "";
    private String isRent = "";
    private String rentUsage = "";
    private String rentId = "";
    private String houseCertificateNo = "";       //����֤��
    private String houseUsage = "";               //������;
    private float businessArea ;             //Ӫҵ�����
    private float produceHosuseArea  ;       //�������������
    private float produceBaseArea ;         //������վ�����
    private float officeArea;              //�칫�����
    private String houseStatus = "";              //����״̬
    private String isCertificate = "";
    private String addressId ="";
    private String bts= "";
    private String category ="";
    private String landCertficateNo = "";
    private float occupyArea;
    private String officeUsage = "";
    private String officeType = "" ;
    private String hremark = "";
    private String temp = "";
    private String hasLandNo = "";
    private String landType ="";            //��������
    private String rentFee  = "";
    private String rentUnit  = "";
    private String contactPerson  = "";
    private String contactPhone  = "";
   	private SimpleCalendar rentStartDate = null;
   	private SimpleCalendar rentEndDate = null;
    private String look = "";
    private String contractName = "";
    private String isProvince = "";

      public AmsHouseInfoDTO() {
		this.rentDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
		this.fromDate = new SimpleCalendar();
		this.toDate = new SimpleCalendar();
		this.rentStartDate = new SimpleCalendar();
		this.rentEndDate = new SimpleCalendar();

    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCertificate() {
        return isCertificate;
    }

    public void setCertificate(String certificate) {
        isCertificate = certificate;
    }

    public String getHouseCertificateNo() {
        return houseCertificateNo;
    }

    public void setHouseCertificateNo(String houseCertificateNo) {
        this.houseCertificateNo = houseCertificateNo;
    }

    public String getHouseUsage() {
        return houseUsage;
    }

    public void setHouseUsage(String houseUsage) {
        this.houseUsage = houseUsage;
    }

    public float getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(float businessArea) {
        this.businessArea = businessArea;
    }

    public float getProduceHosuseArea() {
        return produceHosuseArea;
    }

    public void setProduceHosuseArea(float produceHosuseArea) {
        this.produceHosuseArea = produceHosuseArea;
    }

    public float getProduceBaseArea() {
        return produceBaseArea;
    }

    public void setProduceBaseArea(float produceBaseArea) {
        this.produceBaseArea = produceBaseArea;
    }

    public float getOfficeArea() {
        return officeArea;
    }

    public void setOfficeArea(float officeArea) {
        this.officeArea = officeArea;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getRentId() {
       return this.rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public void setRentUsage(String rentUsage){
        this.rentUsage = rentUsage;
    }

   	public String getRentUsage() {
		return this.rentUsage;
	} 

    public String getIsRent() {
        return isRent;
    }

    public void setIsRent(String isRent) {
        this.isRent = isRent;
    }

    private String has = "";

    public String getHas() {
        return has;
    }

    public void setHas(String has) {
        this.has = has;
    }



    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }



	public SimpleCalendar getToDate() throws CalendarException {
		toDate.setCalPattern(getCalPattern());
        return this.toDate;
    }

    public void setToDate(String toDate)throws CalendarException{
		this.toDate.setCalendarValue(toDate);
	}

    public SimpleCalendar getFromDate() throws CalendarException {
		fromDate.setCalPattern(getCalPattern());
        return this.fromDate;
    }

    public void setFromDate(String fromDate)throws CalendarException{
		this.fromDate.setCalendarValue(fromDate);
	}

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

   public String getItemCode(){
		return this.itemCode;
	}

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }
    /**
	 * ���ܣ��������޷���(EAM)���� ��������
	 * @param barcodeNo String
	 */
//	public void setBarcodeNo(String barcodeNo){
//		this.barcodeNo = barcodeNo;
//	}

	/**
	 * ���ܣ��������޷���(EAM)���� ������(��Ϊ��Ȼ�˻���)
	 * @param rentPerson String
	 */
	public void setRentPerson(String rentPerson){
		this.rentPerson = rentPerson;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ��������
	 * @param rentDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setRentDate(String rentDate) throws CalendarException{
		this.rentDate.setCalendarValue(rentDate);
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ���ڵ�ַ
	 * @param houseAddress String
	 */
	public void setHouseAddress(String houseAddress){
		this.houseAddress = houseAddress;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ¥��
	 * @param floorNumber String
	 */
	public void setFloorNumber(int floorNumber){
		this.floorNumber = floorNumber;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ���ݱ��
	 * @param houseNo String
	 */
	public void setHouseNo(String houseNo){
		this.houseNo = houseNo;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� �������
	 * @param houseArea String
	 */
	public void setHouseArea(float houseArea){
		this.houseArea = houseArea;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� �����λ
	 * @param areaUnit String
	 */
	public void setAreaUnit(String areaUnit){
		this.areaUnit = areaUnit;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ���
	 * @param rental String
	 */
	public void setRental(String rental){
		this.rental = rental;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ���λ
	 * @param moneyUnit String
	 */
	public void setMoneyUnit(String moneyUnit){
		this.moneyUnit = moneyUnit;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ���ʽ
	 * @param payType String
	 */
	public void setPayType(String payType){
		this.payType = payType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * ���ܣ��������޷���(EAM)���� ��������
	 * @param endDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setEndDate(String endDate) throws CalendarException{
		this.endDate.setCalendarValue(endDate);
	}


	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ��������
	 * @return String
	 */
//	public String getBarcodeNo(){
//		return this.barcodeNo;
//	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ������(��Ϊ��Ȼ�˻���)
	 * @return String
	 */
	public String getRentPerson(){
		return this.rentPerson;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException
	 */
	public SimpleCalendar getRentDate() throws CalendarException {
		rentDate.setCalPattern(getCalPattern());
		return this.rentDate;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ���ڵ�ַ
	 * @return String
	 */
	public String getHouseAddress(){
		return this.houseAddress;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ¥��
	 * @return String
	 */
	public int getFloorNumber(){
		return this.floorNumber;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ���ݱ��
	 * @return String
	 */
	public String getHouseNo(){
		return this.houseNo;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� �������
	 * @return String
	 */
	public float getHouseArea(){
		return this.houseArea;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� �����λ
	 * @return String
	 */
	public String getAreaUnit(){
		return this.areaUnit;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ���
	 * @return String
	 */
	public String getRental(){
		return this.rental;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ���λ
	 * @return String
	 */
	public String getMoneyUnit(){
		return this.moneyUnit;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ���ʽ
	 * @return String
	 */
	public String getPayType(){
		return this.payType;
	}

	/**
	 * ���ܣ���ȡ���޷���(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException
	 */
	public SimpleCalendar getEndDate() throws CalendarException {
		endDate.setCalPattern(getCalPattern());
		return this.endDate;
	}

	public String getItemName() {
		return itemName;
	}


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBts() {
        return bts;
    }

    public void setBts(String bts) {
        this.bts = bts;
    }


    public String getLandCertficateNo() {
        return landCertficateNo;
    }

    public void setLandCertficateNo(String landCertficateNo) {
        this.landCertficateNo = landCertficateNo;
    }


    public float getOccupyArea() {
        return occupyArea;
    }

    public void setOccupyArea(float occupyArea) {
        this.occupyArea = occupyArea;
    }


    public String getOfficeUsage() {
        return officeUsage;
    }

    public void setOfficeUsage(String officeUsage) {
        this.officeUsage = officeUsage;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }


    public String getHremark() {
        return hremark;
    }

    public void setHremark(String hremark) {
        this.hremark = hremark;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHasLandNo() {
        return hasLandNo;
    }

    public void setHasLandNo(String hasLandNo) {
        this.hasLandNo = hasLandNo;
    }


    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }


    public String getRentFee() {
        return rentFee;
    }

    public void setRentFee(String rentFee) {
        this.rentFee = rentFee;
    }

    public String getRentUnit() {
        return rentUnit;
    }

    public void setRentUnit(String rentUnit) {
        this.rentUnit = rentUnit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public SimpleCalendar getRentStartDate() throws CalendarException {
		rentStartDate.setCalPattern(getCalPattern());
        return this.rentStartDate;
    }

    public void setRentStartDate(String rentStartDate)throws CalendarException{
		this.rentStartDate.setCalendarValue(rentStartDate);
	}


    public SimpleCalendar getRentEndDate() throws CalendarException {
		rentEndDate.setCalPattern(getCalPattern());
        return this.rentEndDate;
    }

    public void setRentEndDate(String rentEndDate)throws CalendarException{
		this.rentEndDate.setCalendarValue(rentEndDate);
	}


    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }


    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }


    public String getProvince() {
        return isProvince;
    }

    public void setProvince(String province) {
        isProvince = province;
    }
}
