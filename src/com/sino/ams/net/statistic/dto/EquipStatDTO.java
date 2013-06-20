package com.sino.ams.net.statistic.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.math.AdvancedNumber;

/**
 * <p>Title: EQUIP_STAT EquipStat</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */

public class EquipStatDTO extends CheckBoxDTO {
    private int vendorId;
    private String vendorName = "";
    private String itemStatus = "";
    private String finaceProp = "";
    private int organizationId  ;
    private String organizationName = "";
    private String countyCode = "";
    private String workorderObjectCode = "";
    private String workorderObjectName = "";
    private String workorderObjectLocation = "";
    private String objectCategory = "";
    private String invType = "";
    private String itemCode = "";
    private String itemName = "";
    private String itemSpec = "";
    private String itemCategory = "";
    private AdvancedNumber cnt = null;
    private String qryType = "";
    private String materialAttr = "";       // 1�������豸��2�����豸
    
    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }
    public String getMaterialAttr() {
        return materialAttr;
    }

    public void setMaterialAttr(String materialAttr) {
        this.materialAttr = materialAttr;
    }

    public String getQryType() {
        return qryType;
    }

    public void setQryType(String qryType) {
        this.qryType = qryType;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }


    public EquipStatDTO() {
        super();

        this.cnt = new AdvancedNumber();
    }

    /**
     * ���ܣ�����EQUIP_STAT���� ORGANIZATION_NAME
     *
     * @param organizationName String
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * ���ܣ�����EQUIP_STAT���� WORKORDER_OBJECT_NAME
     *
     * @param workorderObjectName String
     */
    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    /**
     * ���ܣ�����EQUIP_STAT���� WORKORDER_OBJECT_LOCATION
     *
     * @param workorderObjectLocation String
     */
    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }

    /**
     * ���ܣ�����EQUIP_STAT���� ITEM_NAME
     *
     * @param itemName String
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ���ܣ�����EQUIP_STAT���� ITEM_SPEC
     *
     * @param itemSpec String
     */
    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    /**
     * ���ܣ�����EQUIP_STAT���� ITEM_CATEGORY
     *
     * @param itemCategory String
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * ���ܣ�����EQUIP_STAT���� CNT
     *
     * @param cnt AdvancedNumber
     */
    public void setCnt(AdvancedNumber cnt) {
        this.cnt = cnt;
    }


    /**
     * ���ܣ���ȡEQUIP_STAT���� ORGANIZATION_NAME
     *
     * @return String
     */
    public String getOrganizationName() {
        return this.organizationName;
    }

    /**
     * ���ܣ���ȡEQUIP_STAT���� WORKORDER_OBJECT_NAME
     *
     * @return String
     */
    public String getWorkorderObjectName() {
        return this.workorderObjectName;
    }

    /**
     * ���ܣ���ȡEQUIP_STAT���� WORKORDER_OBJECT_LOCATION
     *
     * @return String
     */
    public String getWorkorderObjectLocation() {
        return this.workorderObjectLocation;
    }

    /**
     * ���ܣ���ȡEQUIP_STAT���� ITEM_NAME
     *
     * @return String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * ���ܣ���ȡEQUIP_STAT���� ITEM_SPEC
     *
     * @return String
     */
    public String getItemSpec() {
        return this.itemSpec;
    }

    /**
     * ���ܣ���ȡEQUIP_STAT���� ITEM_CATEGORY
     *
     * @return String
     */
    public String getItemCategory() {
        return this.itemCategory;
    }

    /**
     * ���ܣ���ȡEQUIP_STAT���� CNT
     *
     * @return AdvancedNumber
     */
    public AdvancedNumber getCnt() {
        return this.cnt;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }


    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }


    public String getFinaceProp() {
        return finaceProp;
    }

    public void setFinaceProp(String finaceProp) {
        this.finaceProp = finaceProp;
    }
}