package com.sino.ams.system.house.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ���������ʲ�(EAM) AmsLandInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class AmsLandInfoDTO extends CheckBoxDTO {


    //	private String barcodeNo = "";
    private String barcode = "";

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    private String rentPerson = "";
    private SimpleCalendar rentDate = null;
    private String areaUnit = "";
    private String rental = "";
    private String moneyUnit = "";
    private String payType = "";
    private SimpleCalendar endDate = null;
    private String landArea = "";
    private String rentUsage = "";
    private String remark = "";
    private String itemCode = "";
    private String itemName = "";
    private String itemSpec = "";
    private String systemId = "";
    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;
    private String isRent = "";
    private String rentId = "";
    private String landCertficateNo = "";
    private String hasCertficate = "";
    private String landAddress = "";
    private String addressId = "";

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLandAddress() {
        return landAddress;
    }

    public void setLandAddress(String landAddress) {
        this.landAddress = landAddress;
    }

    public String getHasCertficate() {
        return hasCertficate;
    }

    public void setHasCertficate(String hasCertficate) {
        this.hasCertficate = hasCertficate;
    }

    public String getLandCertficateNo() {
        return landCertficateNo;
    }

    public void setLandCertficateNo(String landCertficateNo) {
        this.landCertficateNo = landCertficateNo;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

//    private Timestamp fromDate = null;
//    private Timestamp toDate = null;


    public AmsLandInfoDTO() {
        super();
        this.rentDate = new SimpleCalendar();
        this.endDate = new SimpleCalendar();
        this.fromDate = new SimpleCalendar();
        this.toDate = new SimpleCalendar();
    }

    public String getIsRent() {
        return isRent;
    }

    public void setIsRent(String isRent) {
        this.isRent = isRent;
    }


    public SimpleCalendar getToDate() throws CalendarException {
        toDate.setCalPattern(getCalPattern());
        return this.toDate;
    }

    public void setToDate(String toDate) throws CalendarException {
        this.toDate.setCalendarValue(toDate);
    }

    public SimpleCalendar getFromDate() throws CalendarException {
        fromDate.setCalPattern(getCalPattern());
        return this.fromDate;
    }

    public void setFromDate(String fromDate) throws CalendarException {
        this.fromDate.setCalendarValue(fromDate);
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ��������
     * @param barcodeNo String
     */
//	public void setBarcodeNo(String barcodeNo){
//		this.barcodeNo = barcodeNo;
//	}

    /**
     * ���ܣ��������������ʲ�(EAM)���� ������
     *
     * @param rentPerson String
     */
    public void setRentPerson(String rentPerson) {
        this.rentPerson = rentPerson;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ��������
     *
     * @param rentDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setRentDate(String rentDate) throws CalendarException {
        if (!StrUtil.isEmpty(rentDate)) {
            this.rentDate = new SimpleCalendar(rentDate);
        } else {
            this.rentDate = null;
        }
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� �ػ���λ
     *
     * @param areaUnit String
     */
    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ���
     *
     * @param rental String
     */
    public void setRental(String rental) {
        this.rental = rental;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ��λ
     *
     * @param moneyUnit String
     */
    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ���ʽ
     *
     * @param payType String
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ��������
     *
     * @param endDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setEndDate(String endDate) throws CalendarException {
        if (!StrUtil.isEmpty(endDate)) {
            this.endDate = new SimpleCalendar(endDate);
        } else {
            this.endDate = null;
        }
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� �������
     *
     * @param landArea String
     */
    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ������;
     *
     * @param rentUsage String
     */
    public void setRentUsage(String rentUsage) {
        this.rentUsage = rentUsage;
    }

    /**
     * ���ܣ��������������ʲ�(EAM)���� ��ע
     *
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ��������
     * @return String
     */
//	public String getBarcodeNo() {
//		return this.barcodeNo;
//	}

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ������
     *
     * @return String
     */
    public String getRentPerson() {
        return this.rentPerson;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getRentDate() throws CalendarException {
        if (rentDate != null) {
            rentDate.setCalPattern(getCalPattern());
        }
        return this.rentDate;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� �ػ���λ
     *
     * @return String
     */
    public String getAreaUnit() {
        return this.areaUnit;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ���
     *
     * @return String
     */
    public String getRental() {
        return this.rental;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ��λ
     *
     * @return String
     */
    public String getMoneyUnit() {
        return this.moneyUnit;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ���ʽ
     *
     * @return String
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getEndDate() throws CalendarException {
        if (endDate != null) {
            endDate.setCalPattern(getCalPattern());
        }
        return this.endDate;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� �������
     *
     * @return String
     */
    public String getLandArea() {
        return this.landArea;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ������;
     *
     * @return String
     */
    public String getRentUsage() {
        return this.rentUsage;
    }

    /**
     * ���ܣ���ȡ���������ʲ�(EAM)���� ��ע
     *
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

//      public Timestamp getFromDate() {
//        return fromDate;
//    }
//
//    public void setFromDate(String fromDate) throws CalendarException {
//        if (!StrUtil.isEmpty(fromDate)) {
//            SimpleDate startDate = new SimpleDate(fromDate);
//            SimpleTime startTime = null;
//            try {
//                startTime = SimpleTime.getStartTime();
//            } catch (TimeException e) {
//                throw new CalendarException(e.getMessage());
//            }
//            SimpleCalendar cal = new SimpleCalendar(startDate, startTime);
//            this.fromDate = cal.getSQLTimestamp();
//        }
//    }
//
//    public Timestamp getToDate() {
//        return toDate;
//    }
//
//    public void setToDate(String toDate) throws CalendarException {
//        if (!StrUtil.isEmpty(toDate)) {
//            SimpleDate startDate = new SimpleDate(toDate);
//            SimpleTime startTime = null;
//            try {
//                startTime = SimpleTime.getEndTime();
//            } catch (TimeException e) {
//                throw new CalendarException(e.getMessage());
//            }
//            SimpleCalendar cal = new SimpleCalendar(startDate, startTime);
//            this.toDate = cal.getSQLTimestamp();
//        }
//    }
}