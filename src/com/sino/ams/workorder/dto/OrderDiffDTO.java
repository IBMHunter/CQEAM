package com.sino.ams.workorder.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * User: zhoujs
 * Date: 2007-11-10
 * Time: 20:18:54
 * Function:��������DTO
 */
public class OrderDiffDTO extends CheckBoxDTO {
    private String barcode=""; //��ǩ��
    private String confirmResult=""; //��ʵ���
    private String scanStatus ="";//�豸ɨ��״̬
    private String scanStatusDesc="";//�豸ɨ��״̬����
    private String itemName="";//�豸����
    private String itemSpec="";//�豸����ͺ�
    private String itemCategory="";//�豸רҵ
    private String itemCategoryDesc="";//�豸רҵ˵��
    private String vendorName="";//��Ӧ��
    private String itemStatus=""; //�豸��ϵͳ״̬
    private String itemStatusDesc="";//�豸ϵͳ״̬����
    private String verifyResult="";//��ʵ���
    private String differenceReason="";//��ʵ��ע(0:��ɨ����Ϊ׼��1:���豸Ŀǰ״̬Ϊ׼��2:����Ҫ����null:δ����)

    private String parentBarcode="";//����ǩ��

    private String preBoxNo="";//�豸��ǰ����
    private String preNetUnit="";// �豸��ǰ��Ԫ
    private String boxNo="";//�豸ɨ�����
    private String netUnit="";//�豸ɨ����Ԫ
    private String responsibilityUserName="";//������
    private String responsibilityDept="";//���β���
    private String systemResponsibilityUserName="";//ϵͳ������
    private String systemResponsibilityDept="";//ϵͳ���β���
    private String systemItemName="";//ϵͳ�ʲ�����
    private String systemItemSpec="";//ϵͳ�ʲ�����ͺ�


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getConfirmResult() {
        return confirmResult;
    }

    public void setConfirmResult(String confirmResult) {
        this.confirmResult = confirmResult;
    }

    public String getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
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

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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

    public String getPreBoxNo() {
        return preBoxNo;
    }

    public void setPreBoxNo(String preBoxNo) {
        this.preBoxNo = preBoxNo;
    }

    public String getPreNetUnit() {
        return preNetUnit;
    }

    public void setPreNetUnit(String preNetUnit) {
        this.preNetUnit = preNetUnit;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getNetUnit() {
        return netUnit;
    }

    public void setNetUnit(String netUnit) {
        this.netUnit = netUnit;
    }

    public String getScanStatusDesc() {
        return scanStatusDesc;
    }

    public void setScanStatusDesc(String scanStatusDesc) {
        this.scanStatusDesc = scanStatusDesc;
    }

    public String getItemStatusDesc() {
        return itemStatusDesc;
    }

    public void setItemStatusDesc(String itemStatusDesc) {
        this.itemStatusDesc = itemStatusDesc;
    }


    public String getItemCategoryDesc() {
        return itemCategoryDesc;
    }

    public void setItemCategoryDesc(String itemCategoryDesc) {
        this.itemCategoryDesc = itemCategoryDesc;
    }

    public String getParentBarcode() {
        return parentBarcode;
    }

    public void setParentBarcode(String parentBarcode) {
        this.parentBarcode = parentBarcode;
    }


    public String getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(String verifyResult) {
        this.verifyResult = verifyResult;
    }

    public String getDifferenceReason() {
        return differenceReason;
    }

    public void setDifferenceReason(String differenceReason) {
        this.differenceReason = differenceReason;
    }

    public String getResponsibilityUserName() {
        return responsibilityUserName;
    }

    public void setResponsibilityUserName(String responsibilityUserName) {
        this.responsibilityUserName = responsibilityUserName;
    }

    public String getResponsibilityDept() {
        return responsibilityDept;
    }

    public void setResponsibilityDept(String responsibilityDept) {
        this.responsibilityDept = responsibilityDept;
    }

    public String getSystemResponsibilityUserName() {
        return systemResponsibilityUserName;
    }

    public void setSystemResponsibilityUserName(String systemResponsibilityUserName) {
        this.systemResponsibilityUserName = systemResponsibilityUserName;
    }

    public String getSystemResponsibilityDept() {
        return systemResponsibilityDept;
    }

    public void setSystemResponsibilityDept(String systemResponsibilityDept) {
        this.systemResponsibilityDept = systemResponsibilityDept;
    }

    public String getSystemItemName() {
        return systemItemName;
    }

    public void setSystemItemName(String systemItemName) {
        this.systemItemName = systemItemName;
    }

    public String getSystemItemSpec() {
        return systemItemSpec;
    }

    public void setSystemItemSpec(String systemItemSpec) {
        this.systemItemSpec = systemItemSpec;
    }
}
