package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �ʲ��̵��¼ AmsAssetsChkLog</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsChkLogDTO extends CommonRecordDTO {

    private String barcode = "";
    private int chkTimes = 1;
    private SimpleCalendar lastChkDate = null;
    private String lastChkNo = "";

    private String itemCode = "";
    private String itemName = "";
    private String itemSpec = "";
    private String itemCategory = "";
    private String responsibilityUser =  "";
    private String responsibilityDept = "";
    private String addressId;
    private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private String headerId;
    private String batchId;
    private String orderDtlUrl = "";
    private int synStatus;
    private String orderType = "";
    private String isExist = "Y";

    public AmsAssetsChkLogDTO() {
        super();
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� ��ǩ��
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �豸����
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �豸����
     * @param itemName String
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� ����ͺ�
     * @param itemSpec String
     */
    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �豸����
     * @param itemCategory String
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� ������
     * @param responsibilityUser String
     */
    public void setResponsibilityUser(String responsibilityUser) {
        this.responsibilityUser = responsibilityUser;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� ���β���

     * @param responsibilityDept String
     */
    public void setResponsibilityDept(String responsibilityDept) {
        this.responsibilityDept = responsibilityDept;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �ʲ��ص�ID
     * @param addressId String
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� ��˾OU��֯ID
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �̵㵥���к�
     * @param headerId String
     */
    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �̵������к�
     * @param batchId String
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * ���ܣ������̵��ʲ�����״̬���� �̵㵥��ϸ��ϢURL
     * @param orderDtlUrl String
     */
    public void setOrderDtlUrl(String orderDtlUrl) {
        this.orderDtlUrl = orderDtlUrl;
    }


    /**
     * ���ܣ������̵��ʲ�����״̬���� ͬ��״̬��0��ʾδͬ����1��ʾ��ͬ��
     * @param synStatus String
     */
    public void setSynStatus(int synStatus) {
        this.synStatus = synStatus;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� ��ǩ��
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �豸����
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �豸����
     * @return String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� ����ͺ�
     * @return String
     */
    public String getItemSpec() {
        return this.itemSpec;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �豸����
     * @return String
     */
    public String getItemCategory() {
        return this.itemCategory;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� ������
     * @return String
     */
    public String getResponsibilityUser() {
        return this.responsibilityUser;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� ���β���

     * @return String
     */
    public String getResponsibilityDept() {
        return this.responsibilityDept;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �ʲ��ص�ID
     * @return String
     */
    public String getAddressId() {
        return this.addressId;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� ��˾OU��֯ID
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �̵㵥���к�
     * @return String
     */
    public String getHeaderId() {
        return this.headerId;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �̵������к�
     * @return String
     */
    public String getBatchId() {
        return this.batchId;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� �̵㵥��ϸ��ϢURL
     * @return String
     */
    public String getOrderDtlUrl() {
        return this.orderDtlUrl;
    }

    /**
     * ���ܣ���ȡ�̵��ʲ�����״̬���� ͬ��״̬��0��ʾδͬ����1��ʾ��ͬ��
     * @return String
     */
    public int getSynStatus() {
        return this.synStatus;
    }


    /**
     * ���ܣ������ʲ��̵��¼���� �̵����
     * @param chkTimes String
     */
    public void setChkTimes(int chkTimes) {
        this.chkTimes = chkTimes;
    }

    /**
     * ���ܣ������ʲ��̵��¼���� ����̵�����
     * @param lastChkDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastChkDate(String lastChkDate) throws CalendarException {
        this.lastChkDate.setCalendarValue(lastChkDate);
    }

    /**
     * ���ܣ������ʲ��̵��¼���� ����̵㹤����
     * @param lastChkNo String
     */
    public void setLastChkNo(String lastChkNo) {
        this.lastChkNo = lastChkNo;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��¼���� �̵����
     * @return String
     */
    public int getChkTimes() {
        return this.chkTimes;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��¼���� ����̵�����
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastChkDate() throws CalendarException {
        lastChkDate.setCalPattern(getCalPattern());
        return this.lastChkDate;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��¼���� ����̵㹤����
     * @return String
     */
    public String getLastChkNo() {
        return this.lastChkNo;
    }

    public String getIsExist() {
        return isExist;
    }

    public String getOrderType() {
        return orderType;
    }
}
