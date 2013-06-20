package com.sino.ams.net.equip.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: ITEM_INFO ItemInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class ItemInfoDTO extends CheckBoxDTO {

    private int systemid;
    private String barcode = "";
    private String faBarcode = "";
    private SimpleCalendar startDate = null;
    private SimpleCalendar disableDate = null;
    private int projectId;
    private String sendtomisFlag = "";
    private String financeProp = "";
    private int organizationId;
    private String itemCode = "";
    private String itemName = "";
    private String itemSpec = "";
    private String itemCategory = "";
    private String workorderObjectName = "";
    private int addressId;
    private String boxNo = "";
    private String netUnit = "";
    private String countyCode = null;
    private String countyName = "";
    private String qryType = "";
    private String projectName = "";
    private String objectCategory = "";
    private String workorderObjectCode = "";
    private SimpleCalendar minTime = null;
    private SimpleCalendar maxTime = null;
    private String invType = "";
    private String daiwei = "";

    public ItemInfoDTO() {
        super();
        this.startDate = new SimpleCalendar();
        this.disableDate = new SimpleCalendar();
//        this.systemid = new AdvancedNumber();
//        this.projectId = new AdvancedNumber();
//        this.addressId = new AdvancedNumber();
//        this.countyCode = new AdvancedNumber();
        this.minTime = new SimpleCalendar();
        this.maxTime = new SimpleCalendar();
    }

    public String getDaiwei() {
        return daiwei;
    }

    public void setDaiwei(String daiwei) {
        this.daiwei = daiwei;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public SimpleCalendar getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = new SimpleCalendar(minTime);
    }

    public SimpleCalendar getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = new SimpleCalendar(maxTime);
    }

    /**
     * ���ܣ�����ITEM_INFO���� SYSTEMID
     *
     * @param systemid String
     */
    public void setSystemid(int systemid) {
        this.systemid = systemid;
    }

    /**
     * ���ܣ�����ITEM_INFO���� BARCODE
     *
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ�����ITEM_INFO���� FA_BARCODE
     *
     * @param faBarcode String
     */
    public void setFaBarcode(String faBarcode) {
        this.faBarcode = faBarcode;
    }

    /**
     * ���ܣ�����ITEM_INFO���� START_DATE
     *
     * @param startDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setStartDate(String startDate) throws CalendarException {
        this.startDate.setCalendarValue(startDate);
    }

    /**
     * ���ܣ�����ITEM_INFO���� DISABLE_DATE
     *
     * @param disableDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDisableDate(String disableDate) throws CalendarException {
        this.disableDate.setCalendarValue(disableDate);
    }

    /**
     * ���ܣ�����ITEM_INFO���� PROJECTID
     *
     * @param projectid String
     */
    public void setProjectid(int projectid) {
        this.projectId = projectid;
    }

    /**
     * ���ܣ�����ITEM_INFO���� SENDTOMIS_FLAG
     *
     * @param sendtomisFlag String
     */
    public void setSendtomisFlag(String sendtomisFlag) {
        this.sendtomisFlag = sendtomisFlag;
    }

    /**
     * ���ܣ�����ITEM_INFO���� FINANCE_PROP
     *
     * @param financeProp String
     */
    public void setFinanceProp(String financeProp) {
        this.financeProp = financeProp;
    }

    /**
     * ���ܣ�����ITEM_INFO���� ORGANIZATION_ID
     *
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ�����ITEM_INFO���� ITEM_CODE
     *
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ�����ITEM_INFO���� ITEM_NAME
     *
     * @param itemName String
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ���ܣ�����ITEM_INFO���� ITEM_SPEC
     *
     * @param itemSpec String
     */
    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    /**
     * ���ܣ�����ITEM_INFO���� ITEM_CATEGORY
     *
     * @param itemCategory String
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * ���ܣ�����ITEM_INFO���� WORKORDER_OBJECT_NAME
     *
     * @param workorderObjectName String
     */
    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    /**
     * ���ܣ�����ITEM_INFO���� ADDRESS_ID
     *
     * @param addressId String
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * ���ܣ�����ITEM_INFO���� BOX_NO
     *
     * @param boxNo String
     */
    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    /**
     * ���ܣ�����ITEM_INFO���� NET_UNIT
     *
     * @param netUnit String
     */
    public void setNetUnit(String netUnit) {
        this.netUnit = netUnit;
    }

    /**
     * ���ܣ�����ITEM_INFO���� COUNTY_CODE
     *
     * @param countyCode String
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * ���ܣ�����ITEM_INFO���� COUNTY_NAME
     *
     * @param countyName String
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }


    /**
     * ���ܣ���ȡITEM_INFO���� SYSTEMID
     *
     * @return String
     */
    public int getSystemid() {
        return this.systemid;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� BARCODE
     *
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� FA_BARCODE
     *
     * @return String
     */
    public String getFaBarcode() {
        return this.faBarcode;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� START_DATE
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getStartDate() throws CalendarException {
        startDate.setCalPattern(getCalPattern());
        return this.startDate;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� DISABLE_DATE
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getDisableDate() throws CalendarException {
        disableDate.setCalPattern(getCalPattern());
        return this.disableDate;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� PROJECTID
     *
     * @return String
     */
    public int getProjectid() {
        return this.projectId;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� SENDTOMIS_FLAG
     *
     * @return String
     */
    public String getSendtomisFlag() {
        return this.sendtomisFlag;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� FINANCE_PROP
     *
     * @return String
     */
    public String getFinanceProp() {
        return this.financeProp;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� ORGANIZATION_ID
     *
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� ITEM_CODE
     *
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� ITEM_NAME
     *
     * @return String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� ITEM_SPEC
     *
     * @return String
     */
    public String getItemSpec() {
        return this.itemSpec;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� ITEM_CATEGORY
     *
     * @return String
     */
    public String getItemCategory() {
        return this.itemCategory;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� WORKORDER_OBJECT_NAME
     *
     * @return String
     */
    public String getWorkorderObjectName() {
        return this.workorderObjectName;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� ADDRESS_ID
     *
     * @return String
     */
    public int getAddressId() {
        return this.addressId;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� BOX_NO
     *
     * @return String
     */
    public String getBoxNo() {
        return this.boxNo;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� NET_UNIT
     *
     * @return String
     */
    public String getNetUnit() {
        return this.netUnit;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� COUNTY_CODE
     *
     * @return String
     */
    public String getCountyCode() {
        return this.countyCode;
    }

    /**
     * ���ܣ���ȡITEM_INFO���� COUNTY_NAME
     *
     * @return String
     */
    public String getCountyName() {
        return this.countyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getQryType() {
        return qryType;
    }

    public void setQryType(String qryType) {
        this.qryType = qryType;
    }
}