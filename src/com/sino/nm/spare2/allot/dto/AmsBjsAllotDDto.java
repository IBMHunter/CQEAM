package com.sino.nm.spare2.allot.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2007-11-7
 * Time: 11:24:24
 */
public class AmsBjsAllotDDto extends CheckBoxDTO {

    private String transId = "";
    private String lineId = "";
    private String detailId = "";
    private int organizationId ;
    private String itemCode = "";
    private String quantity = "";
    private String confirmQuantity = "";
    private String curOnhandQty = "";
    private String itemName = "";
    private String itemSpec = "";
    private String itemAmount = "";
    private String code = "";
    private String value = "";
    private String description = "";
    private String flexValueId = "";
    private String workorderObjectName = "";
    private String workorderObjectLocation = "";
    private String addressId = "";
    private String barcode = "";
    private String itemQty = "";
    private String objectCategory = "";
    private String type = "";
    private String allotQty = "";

    public AmsBjsAllotDDto() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlexValueId() {
        return flexValueId;
    }

    public void setFlexValueId(String flexValueId) {
        this.flexValueId = flexValueId;
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

    public String getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(String itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ������ID
     * @param transId String
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ID
     * @param lineId String
     */
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ϸID
     * @param detailId String
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� OU
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� �豸����
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ����
     * @param quantity String
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ȷ������
     * @param confirmQuantity String
     */
    public void setConfirmQuantity(String confirmQuantity) {
        this.confirmQuantity = confirmQuantity;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ǰ���������
     * @param curOnhandQty String
     */
    public void setCurOnhandQty(String curOnhandQty) {
        this.curOnhandQty = curOnhandQty;
    }


    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ������ID
     * @return String
     */
    public String getTransId() {
        return this.transId;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ID
     * @return String
     */
    public String getLineId() {
        return this.lineId;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ϸID
     * @return String
     */
    public String getDetailId() {
        return this.detailId;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� OU
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� �豸����
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ����
     * @return String
     */
    public String getQuantity() {
        return this.quantity;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ȷ������
     * @return String
     */
    public String getConfirmQuantity() {
        return this.confirmQuantity;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ǰ���������
     * @return String
     */
    public String getCurOnhandQty() {
        return this.curOnhandQty;
    }


    public String getAllotQty() {
        return allotQty;
    }

    public void setAllotQty(String allotQty) {
        this.allotQty = allotQty;
    }
}
