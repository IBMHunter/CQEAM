package com.sino.ams.match.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2008-7-21
 * Time: 15:17:36
 * To change this template use File | Settings | File Templates.
 */

public class AmsAssetsInfoDTO  extends CheckBoxDTO {
    private String barcode = "";//EAM����
    private String itemName= "";//EAM�豸����
    private String itemSpec = "";//EAM�豸�ͺ�
    private String workorderObjectLocation = "";//EAM�ʲ��ص�
    private String tagNumber = "";//MIS�ʲ�����
    private String assetsNumber = "";//MIS�ʲ����
    private String  modelNumber = "";//MIS�ʲ��ͺ�
    private String assetsDescription = "";//MIS�ʲ�����
    private String assetsLocation="";//MIS�ʲ��ص�
    private String workorderObjectName  ="";// �ص�����

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
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

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getAssetsDescription() {
        return assetsDescription;
    }

    public void setAssetsDescription(String assetsDescription) {
        this.assetsDescription = assetsDescription;
    }

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }

    public String getAssetsLocation() {
        return assetsLocation;
    }

    public void setAssetsLocation(String assetsLocation) {
        this.assetsLocation = assetsLocation;
    }

    public String getAssetsNumber() {
        return assetsNumber;
    }

    public void setAssetsNumber(String assetsNumber) {
        this.assetsNumber = assetsNumber;
    }
}

