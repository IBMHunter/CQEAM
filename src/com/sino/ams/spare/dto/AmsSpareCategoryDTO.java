package com.sino.ams.spare.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */

public class AmsSpareCategoryDTO extends CheckBoxDTO {

    private String barcode = "";
    private String itemCode = "";
    private SimpleCalendar creationDate = null;
    private String createdBy = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateBy = "";
    private String spareUsage = "";
    private String itemName = "";
    private String itemSpec = "";
    private String itemCategory = "";
    private String vendorId = "";
    private String itemUnit = "";
    private String remark = "";
    private String enabled = "";
    private int organizationId = -1;
    private String vendorName = "";
    private String objectNo = "";
    private String batchNo = "";
    private String barcode1 = "";
    private String spareId = "";

    private String storeType = "";

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getSpareId() {
        return spareId;
    }

    public void setSpareId(String spareId) {
        this.spareId = spareId;
    }

    public String getBarcode1() {
        return barcode1;
    }

    public void setBarcode1(String barcode1) {
        this.barcode1 = barcode1;
    }

    private String objectCategory="";

    public String getObjectNo() {
        return objectNo;
    }

    public void setObjectNo(String objectNo) {
        this.objectNo = objectNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public AmsSpareCategoryDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();

    }

    /**
     * ���ܣ����ñ����豸��������� ������
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ����ñ����豸��������� ETS_SYSTEM_ITEM�豸����
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ����ñ����豸��������� CREATION_DATE
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ����ñ����豸��������� CREATED_BY
     * @param createdBy String
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ñ����豸��������� LAST_UPDATE_DATE
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ����ñ����豸��������� LAST_UPDATE_BY
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����ñ����豸��������� �����;
     * @param spareUsage String
     */
    public void setSpareUsage(String spareUsage) {
        this.spareUsage = spareUsage;
    }

    /**
     * ���ܣ����ñ����豸��������� �豸����
     * @param itemName String
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ���ܣ����ñ����豸��������� ����ͺ�
     * @param itemSpec String
     */
    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    /**
     * ���ܣ����ñ����豸��������� �豸����
     * @param itemCategory String
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * ���ܣ����ñ����豸��������� ��������
     * @param vendorId String
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * ���ܣ����ñ����豸��������� ������λ
     * @param itemUnit String
     */
    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    /**
     * ���ܣ����ñ����豸��������� ��ע
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����ñ����豸��������� �Ƿ���Ч
     * @param enabled String
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


    /**
     * ���ܣ���ȡ�����豸��������� ������
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * ���ܣ���ȡ�����豸��������� ETS_SYSTEM_ITEM�豸����
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡ�����豸��������� CREATION_DATE
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ�����豸��������� CREATED_BY
     * @return String
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ�����豸��������� LAST_UPDATE_DATE
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ�����豸��������� LAST_UPDATE_BY
     * @return String
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ�����豸��������� �����;
     * @return String
     */
    public String getSpareUsage() {
        return this.spareUsage;
    }

    
    /**
     * ���ܣ���ȡ�����豸��������� �豸����
     * @return String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * ���ܣ���ȡ�����豸��������� ����ͺ�
     * @return String
     */
    public String getItemSpec() {
        return this.itemSpec;
    }

    /**
     * ���ܣ���ȡ�����豸��������� �豸����
     * @return String
     */
    public String getItemCategory() {
        return this.itemCategory;
    }

    /**
     * ���ܣ���ȡ�����豸��������� ��������
     * @return String
     */
    public String getVendorId() {
        return this.vendorId;
    }

    /**
     * ���ܣ���ȡ�����豸��������� ������λ
     * @return String
     */
    public String getItemUnit() {
        return this.itemUnit;
    }

    /**
     * ���ܣ���ȡ�����豸��������� ��ע
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ�����豸��������� �Ƿ���Ч
     * @return String
     */
    public String getEnabled() {
        return this.enabled;
    }


    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }
}