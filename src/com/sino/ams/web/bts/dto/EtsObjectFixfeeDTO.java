package com.sino.ams.web.bts.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ��վά�޳ɱ�(EAM) EtsObjectFixfee</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class EtsObjectFixfeeDTO extends CheckBoxDTO {

    private String systemId = "";
    private SimpleCalendar fixDate = null;
    private int amount ;
    private String fixNo = "";
    private String attribute1 = "";
    private String attribute2 = "";
    private String remark = "";
    private SimpleCalendar creationDate = null;
    private int createdBy ;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy ;
    private int objectNo ;
    private String fromDate = null;
    private String toDate = null;
    private String workorderObjectName = "";
    private String workorderObjectLocation = "";
    private String workorderObjectNo = "";
    private String workorderObjectCode = "";
    private String objectCategory="";
    private String company = "";
    private int organizationId;

    public EtsObjectFixfeeDTO() {
        this.fixDate = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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

    public String getWorkorderObjectNo() {
        return workorderObjectNo;
    }

    public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
    }

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }


    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ���к�
     *
     * @param systemId String
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ά������
     *
     * @throws CalendarException ����ֵ������ȷ������
     * @param fixDate SimpleCalendar�����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setFixDate(String fixDate) throws CalendarException {
        if (!StrUtil.isEmpty(fixDate)) {
            this.fixDate = new SimpleCalendar(fixDate);
        }
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ά�޷���
     *
     * @param amount String
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� null
     *
     * @param fixNo String
     */
    public void setFixNo(String fixNo) {
        this.fixNo = fixNo;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� null
     *
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� null
     *
     * @param attribute2 String
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ��ע
     *
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ��������
     *
     * @param creationDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        if (!StrUtil.isEmpty(creationDate)) {
            this.creationDate = new SimpleCalendar(creationDate);
        }
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ������
     *
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        if (!StrUtil.isEmpty(lastUpdateDate)) {
            this.lastUpdateDate =  new SimpleCalendar(lastUpdateDate);
        }
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� �ϴ��޸���
     *
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����û�վά�޳ɱ�(EAM)���� ��վ���к�
     *
     * @param objectNo String
     */
    public void setObjectNo(int objectNo) {
        this.objectNo = objectNo;
    }


    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ���к�
     *
     * @return String
     */
    public String getSystemId() {
        return this.systemId;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ά������
     *
     * @return Timestamp
     */
    public SimpleCalendar getFixDate() {
        return this.fixDate;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ά�޷���
     *
     * @return String
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� null
     *
     * @return String
     */
    public String getFixNo() {
        return this.fixNo;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� null
     *
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� null
     *
     * @return String
     */
    public String getAttribute2() {
        return this.attribute2;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ��ע
     *
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ��������
     *
     * @return SimpleCalendar
     */
    public SimpleCalendar getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ������
     *
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� �ϴ��޸�����
     *
     * @return SimpleCalendar
     */
    public SimpleCalendar getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� �ϴ��޸���
     *
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ��վά�޳ɱ�(EAM)���� ��վ���к�
     *
     * @return String
     */
    public int getObjectNo() {
        return this.objectNo;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }
}
