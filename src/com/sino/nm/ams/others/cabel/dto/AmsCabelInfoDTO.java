package com.sino.nm.ams.others.cabel.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �������豸��չ��Ϣ(AMS) AmsCabelInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class AmsCabelInfoDTO extends CheckBoxDTO {
    public AmsCabelInfoDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    private String barcode = "";
    private String fromAddress = "";
    private String toAddress = "";
    private String spreadType = "";
    private String cabelUsage = "";
    private String fromTude = "";
    private String toTude = "";
    private String cabelDepth = "";
    private SimpleCalendar creationDate = null;
    private String createdBy = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateBy = "";
    //------------------------------------
    private String itemCode = "";
    private String itemCategory = "";
    private String itemName = "";
    private String itemSpec = "";


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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


    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� �豸����
     *
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ʼ�ص�
     *
     * @param fromAddress String
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ֹ�ص�
     *
     * @param toAddress String
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ���跽ʽ
     *
     * @param spreadType String
     */
    public void setSpreadType(String spreadType) {
        this.spreadType = spreadType;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ������;
     *
     * @param cabelUsage String
     */
    public void setCabelUsage(String cabelUsage) {
        this.cabelUsage = cabelUsage;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ʼ�ص㾭γ��
     *
     * @param fromTude String
     */
    public void setFromTude(String fromTude) {
        this.fromTude = fromTude;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ֹ�ص㾭γ��
     *
     * @param toTude String
     */
    public void setToTude(String toTude) {
        this.toTude = toTude;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ��������
     *
     * @param cabelDepth String
     */
    public void setCabelDepth(String cabelDepth) {
        this.cabelDepth = cabelDepth;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ��������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� ������
     *
     * @param createdBy String
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ������������豸��չ��Ϣ(AMS)���� �ϴ��޸���
     *
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }


    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� �豸����
     *
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ʼ�ص�
     *
     * @return String
     */
    public String getFromAddress() {
        return this.fromAddress;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ֹ�ص�
     *
     * @return String
     */
    public String getToAddress() {
        return this.toAddress;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ���跽ʽ
     *
     * @return String
     */
    public String getSpreadType() {
        return this.spreadType;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ������;
     *
     * @return String
     */
    public String getCabelUsage() {
        return this.cabelUsage;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ʼ�ص㾭γ��
     *
     * @return String
     */
    public String getFromTude() {
        return this.fromTude;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ֹ�ص㾭γ��
     *
     * @return String
     */
    public String getToTude() {
        return this.toTude;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ��������
     *
     * @return String
     */
    public String getCabelDepth() {
        return this.cabelDepth;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        if (creationDate != null) {
            creationDate.setCalPattern(getCalPattern());
        }
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� ������
     *
     * @return String
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� �ϴ��޸�����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        if (lastUpdateDate != null) {
            lastUpdateDate.setCalPattern(getCalPattern());
        }
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ�������豸��չ��Ϣ(AMS)���� �ϴ��޸���
     *
     * @return String
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

}