package com.sino.ams.newSite.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.util.StrUtil;

/**
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 12, 2011 10:24:45 AM
 *          ��˵�� :�����ص����̱�  ����Ϣ
 */
public class EamAddressAddLDTO extends CheckBoxDTO {

    private String lineId = "";
    private String transId = "";              //����ID
    private String workorderObjectCode = "";  //�ص����  1
    private String workorderObjectName = "";  //�ص�����  2
    private String objectCategory = "";       //�ص�רҵ  3
    private String countyCode = "";           //��������
    private String areaType = "";             //�������  5
    private String btsNo = "";               //��վ��Ӫҵ������

    private String city = "";                 //��
    private String county = "";               //���� 4

    private String remark = "";               //��ע
    private int organizationId;
    private String addrMaintainType = ""; //�ص�ά������
    private String errorMessage = "";
    
    private String shareType = ""; //�Ƿ���

    private String ExcelLineId = "";//Excel�к�

    public String getExcelLineId() {
        return ExcelLineId;
    }

    public void setExcelLineId(String excelLineId) {
        ExcelLineId = excelLineId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddrMaintainType() {
        return addrMaintainType;
    }

    public void setAddrMaintainType(String addrMaintainType) {
        this.addrMaintainType = addrMaintainType;
    }

    public String getBtsNo() {
        return btsNo;
    }

    public void setBtsNo(String btsNo) {
        this.btsNo = btsNo;
    }

    public boolean isAddLocation(){
        return (StrUtil.isNotEmpty(addrMaintainType) && addrMaintainType.equals("����"));
    }

    public boolean isUpdateLocation(){
        return (StrUtil.isNotEmpty(addrMaintainType) && addrMaintainType.equals("�޸�"));
    }

    public boolean isDisableLocation(){
        return (StrUtil.isNotEmpty(addrMaintainType) && addrMaintainType.equals("ʧЧ"));
    }

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}
}
