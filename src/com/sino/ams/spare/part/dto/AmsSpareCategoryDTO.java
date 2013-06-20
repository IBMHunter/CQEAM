package com.sino.ams.spare.part.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.ams.bean.CommonRecordDTO;

/**
* <p>Title: �����豸����� AmsSpareCategory</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
* Function; ������ά�� 
*/

public class AmsSpareCategoryDTO extends CommonRecordDTO { //CheckBoxDTO

    private String barcode = "";
    private String itemCode = "";
    private SimpleCalendar creationDate = null;
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateBy = "";
    private String spareUsage = "";
    private String organizationId = "";
    private String itemName = "";
    private String itemSpec = "";
    private String itemCategory = "";
    private String vendorId = "";
    private String itemUnit = "";
    private String remark = "";
    private String enabled = "";
    private String partNo = "";
    private String vendorName = "";
    private String New = "";
    private String createdByName = "";

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getNew() {
        return New;
    }

    public void setNew(String aNew) {
        New = aNew;
    }

    public AmsSpareCategoryDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    public String getVendorName() {
           return vendorName;
       }

       public void setVendorName(String vendorName) {
           this.vendorName = vendorName;
       }


     public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    /**
     * ���ܣ����ñ����豸��������� ������
     * @param barcode String
     */
    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    /**
     * ���ܣ����ñ����豸��������� ETS_SYSTEM_ITEM�豸����
     * @param itemCode String
     */
    public void setItemCode(String itemCode){
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ����ñ����豸��������� CREATION_DATE
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException{
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ����ñ����豸��������� LAST_UPDATE_DATE
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ����ñ����豸��������� LAST_UPDATE_BY
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(String lastUpdateBy){
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����ñ����豸��������� �������
     * @param spareUsage String
     */
    public void setSpareUsage(String spareUsage){
        this.spareUsage = spareUsage;
    }

    /**
     * ���ܣ����ñ����豸��������� OU
     * @param organizationId String
     */
    public void setOrganizationId(String organizationId){
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ����ñ����豸��������� �豸����
     * @param itemName String
     */
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    /**
     * ���ܣ����ñ����豸��������� ����ͺ�
     * @param itemSpec String
     */
    public void setItemSpec(String itemSpec){
        this.itemSpec = itemSpec;
    }

    /**
     * ���ܣ����ñ����豸��������� �豸����
     * @param itemCategory String
     */
    public void setItemCategory(String itemCategory){
        this.itemCategory = itemCategory;
    }

    /**
     * ���ܣ����ñ����豸��������� ��������
     * @param vendorId String
     */
    public void setVendorId(String vendorId){
        this.vendorId = vendorId;
    }

    /**
     * ���ܣ����ñ����豸��������� ������λ
     * @param itemUnit String
     */
    public void setItemUnit(String itemUnit){
        this.itemUnit = itemUnit;
    }

    /**
     * ���ܣ����ñ����豸��������� ��ע
     * @param remark String
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * ���ܣ����ñ����豸��������� �Ƿ���Ч
     * @param enabled String
     */
    public void setEnabled(String enabled){
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
     * ���ܣ���ȡ�����豸��������� LAST_UPDATE_DATE
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ�����豸��������� �������
     * @return String
     */
    public String getSpareUsage() {
        return this.spareUsage;
    }

    /**
     * ���ܣ���ȡ�����豸��������� OU
     * @return String
     */
    public String getOrganizationId() {
        return this.organizationId;
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

}