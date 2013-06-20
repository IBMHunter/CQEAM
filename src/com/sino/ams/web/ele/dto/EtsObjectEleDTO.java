package com.sino.ams.web.ele.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ��վ���ά����(EAM) EtsObjectEle</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class EtsObjectEleDTO extends CheckBoxDTO {

    private String systemid = "";
    private String workorderObjectNo = "";
    private String period = "";
    private String unitPrice = "";
    private String quantity = "";
    private String startDate = "";
    private String endDate = "";
    private String remark = "";
    private SimpleCalendar creationDate = null;
    private int createdBy;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy;
    private String workorderObjectName = "";
    private String fromDate = null;
    private String toDate = null;
    private String company = "";
    private String ammeterReading = "";

    public EtsObjectEleDTO() {
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }


    public String getSystemid() {
        return systemid;
    }

    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ��վ�ص�ID
     * @param workorderObjectNo String
     */
    public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ����ڼ�
     * @param period String
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ����
     * @param unitPrice String
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ����
     * @param quantity String
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ��ʼ����
     * @param startDate String
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ��������
     * @param endDate String
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ��ע
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ��������
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        if (!StrUtil.isEmpty(creationDate)) {
            this.creationDate = new SimpleCalendar(creationDate);
        }
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� �ϴ��޸�����
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        if (!StrUtil.isEmpty(lastUpdateDate)) {
            this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
        }
    }

    /**
     * ���ܣ����û�վ���ά����(EAM)���� �ϴ��޸���
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ����
     * @return String
     */


    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ��վ�ص�ID
     * @return String
     */
    public String getWorkorderObjectNo() {
        return this.workorderObjectNo;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ����ڼ�
     * @return String
     */
    public String getPeriod() {
        return this.period;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ����
     * @return String
     */
    public String getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ����
     * @return String
     */
    public String getQuantity() {
        return this.quantity;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ��ʼ����
     * @return String
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ��������
     * @return String
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ��ע
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� �ϴ��޸�����
     * @return SimpleCalendar
     */
    public SimpleCalendar getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ��վ���ά����(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public String getAmmeterReading() {
        return ammeterReading;
    }

    public void setAmmeterReading(String ammeterReading) {
        this.ammeterReading = ammeterReading;
    }
}