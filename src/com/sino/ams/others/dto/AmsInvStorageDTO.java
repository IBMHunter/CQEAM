package com.sino.ams.others.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.math.AdvancedNumber;

/**
 * <p>Title: AMS_INV_STORAGE AmsInvStorage</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsInvStorageDTO extends CheckBoxDTO {

    private AdvancedNumber storageId = null;
    private String batchNo = "";
    private AdvancedNumber invCode = null;
    private AdvancedNumber organizationId = null;
    private String itemCode = "";
    private AdvancedNumber quantity = null;
    private String itemName = "";
    private String itemSpec = "";
    private String itemUnit = "";

    public AmsInvStorageDTO() {
        super();

        this.storageId = new AdvancedNumber();
        this.invCode = new AdvancedNumber();
        this.organizationId = new AdvancedNumber();
        this.quantity = new AdvancedNumber();
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� STORAGE_ID
     * @param storageId AdvancedNumber
     */
    public void setStorageId(AdvancedNumber storageId) {
        this.storageId = storageId;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� ����
     * @param batchNo String
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� �ֿ�ص�
     * @param invCode AdvancedNumber
     */
    public void setInvCode(AdvancedNumber invCode) {
        this.invCode = invCode;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� OU
     * @param organizationId AdvancedNumber
     */
    public void setOrganizationId(AdvancedNumber organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� �豸����
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� ����
     * @param quantity AdvancedNumber
     */
    public void setQuantity(AdvancedNumber quantity) {
        this.quantity = quantity;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� ITEM_NAME
     * @param itemName String
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� ITEM_SPEC
     * @param itemSpec String
     */
    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    /**
     * ���ܣ�����AMS_INV_STORAGE���� ITEM_UNIT
     * @param itemUnit String
     */
    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }


    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� STORAGE_ID
     * @return AdvancedNumber
     */
    public AdvancedNumber getStorageId() {
        return this.storageId;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� ����
     * @return String
     */
    public String getBatchNo() {
        return this.batchNo;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� �ֿ�ص�
     * @return AdvancedNumber
     */
    public AdvancedNumber getInvCode() {
        return this.invCode;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� OU
     * @return AdvancedNumber
     */
    public AdvancedNumber getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� �豸����
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� ����
     * @return AdvancedNumber
     */
    public AdvancedNumber getQuantity() {
        return this.quantity;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� ITEM_NAME
     * @return String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� ITEM_SPEC
     * @return String
     */
    public String getItemSpec() {
        return this.itemSpec;
    }

    /**
     * ���ܣ���ȡAMS_INV_STORAGE���� ITEM_UNIT
     * @return String
     */
    public String getItemUnit() {
        return this.itemUnit;
    }

}