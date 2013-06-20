package com.sino.ams.system.rent.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
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

public class RentDTO extends CheckBoxDTO{

	private String barcode = "";
	private String rentPerson = "";
	private SimpleCalendar rentDate = null;
	private String houseAddress = "";
	private int floorNumber;
	private String houseNo = "";
	private int houseArea ;
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
    private String maintainDeptName = "";
    private String remark = "";
    private String maintainDept = "";
    private String responsibilityDept = "";
    private String username = "";
    private double tenancy = 0d ;
    private String yearRental = "";
    private String monthReantal = "";
    private String addressloc = "";
    private String addressId = "";
    private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private String maintainUser = "";
    
    private String historyId = "";
    private String toRentTime = "";
    private String accessType = "";   //��������
    private String disabled = "";		//�Ƿ���Ч
    
    private String contentName = "";  //�ʲ��������
    
    //�ʲ�������˾����
    private String companyName = "";
    
    //�ʲ��������β�������
    private String responsibilityDeptName = "";
    

    public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getMaintainUser() {
        return maintainUser;
    }

    public void setMaintainUser(String maintainUser) {
        this.maintainUser = maintainUser;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    private String deptId = "";

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId = SyBaseSQLUtil.NULL_INT_VALUE ;

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    private String itemQty = "";

    public String getAddressloc() {
        return addressloc;
    }

    public void setAddressloc(String addressloc) {
        this.addressloc = addressloc;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getMaintainDept() {
        return maintainDept;
    }

    public void setMaintainDept(String maintainDept) {
        this.maintainDept = maintainDept;
    }

    public String getResponsibilityDept() {
        return responsibilityDept;
    }

    public void setResponsibilityDept(String responsibilityDept) {
        this.responsibilityDept = responsibilityDept;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTenancy() {
        return tenancy;
    }

    public void setTenancy(double tenancy) {
        this.tenancy = tenancy;
    } 

    public String getYearRental() {
        return yearRental;
    }

    public void setYearRental(String yearRental) {
        this.yearRental = yearRental;
    }

    public String getMonthReantal() {
        return monthReantal;
    }

    public void setMonthReantal(String monthReantal) {
        this.monthReantal = monthReantal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRent() {
        return isRent;
    }

    public void setRent(String rent) {
        isRent = rent;
    }

    public String getMaintainDeptName() {
        return maintainDeptName;
    }

    public void setMaintainDeptName(String maintainDeptName) {
        this.maintainDeptName = maintainDeptName;
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

    public RentDTO() {
		this.rentDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
		this.fromDate = new SimpleCalendar();
		this.toDate = new SimpleCalendar();
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
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

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
	public void setHouseArea(int houseArea){
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
	public String getBarcode(){
		return this.barcode;
	}

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
	public int getHouseArea(){
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


    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

	public String getToRentTime() {
		return toRentTime;
	}

	public void setToRentTime(String toRentTime) {
		this.toRentTime = toRentTime;
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getResponsibilityDeptName() {
		return responsibilityDeptName;
	}

	public void setResponsibilityDeptName(String responsibilityDeptName) {
		this.responsibilityDeptName = responsibilityDeptName;
	}
	
}
