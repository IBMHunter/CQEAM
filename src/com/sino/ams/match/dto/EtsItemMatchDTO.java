package com.sino.ams.match.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �ʲ�ƥ��-ƥ�����ݴ洢(EAM) EtsItemMatch</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author jiachuanchuan
 * @version 1.0
 */

public class EtsItemMatchDTO extends CheckBoxDTO {

    private String systemid = "";
    private String itemAttr = "";
    private int assetId;
    private String quantity = "";
    private int batchid;
    private SimpleCalendar matchDate = null;
    private String flag = "";
    private int userId;
    private SimpleCalendar creationDate = null;
    private int createdBy;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy;
    private int organizationId;
    private String barcode = "";
    private String tagNumber = "";
    private int projectId;
    private String workorderObjectNo = "";
    private String workorderObjectLocation = "";
    private String itemName = "";
    private String itemSpec = "";
    private String financeProp = "";
    private String oldFinanceProp = "";
    private String newFinanceProp = "";
    private String matchType = "";
    private String segment1 = "";
    private String assetsLocation = "";

    public String getAssetsLocation() {
        return assetsLocation;
    }

    public void setAssetsLocation(String assetsLocation) {
        this.assetsLocation = assetsLocation;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getOldFinanceProp() {
        return oldFinanceProp;
    }

    public void setOldFinanceProp(String oldFinanceProp) {
        this.oldFinanceProp = oldFinanceProp;
    }

    public String getNewFinanceProp() {
        return newFinanceProp;
    }

    public void setNewFinanceProp(String newFinanceProp) {
        this.newFinanceProp = newFinanceProp;
    }


    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }


    public String getFinanceProp() {
        return financeProp;
    }

    public void setFinanceProp(String financeProp) {
        this.financeProp = financeProp;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getWorkorderObjectNo() {
        return workorderObjectNo;
    }

    public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
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

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public EtsItemMatchDTO() {
        super();
        this.matchDate = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� SYSTEMID
     *
     * @param systemid String
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ITEM_ATTR
     *
     * @param itemAttr String
     */
    public void setItemAttr(String itemAttr) {
        this.itemAttr = itemAttr;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ASSET_ID
     *
     * @param assetId String
     */
    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� QUANTITY
     *
     * @param quantity String
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� BATCHID
     *
     * @param batchid String
     */
    public void setBatchid(int batchid) {
        this.batchid = batchid;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� MATCH_DATE
     *
     * @param matchDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setMatchDate(String matchDate) throws CalendarException {
        this.matchDate.setCalendarValue(matchDate);
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� FLAG
     *
     * @param flag String
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� USER_ID
     *
     * @param userId String
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ��������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ������
     *
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� �ϴ��޸���
     *
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ������ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� �ϴ��޸���
     *
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� SYSTEMID
     *
     * @return String
     */
    public String getSystemid() {
        return this.systemid;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ITEM_ATTR
     *
     * @return String
     */
    public String getItemAttr() {
        return this.itemAttr;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ASSET_ID
     *
     * @return String
     */
    public int getAssetId() {
        return this.assetId;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� QUANTITY
     *
     * @return String
     */
    public String getQuantity() {
        return this.quantity;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� BATCHID
     *
     * @return String
     */
    public int getBatchid() {
        return this.batchid;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� MATCH_DATE
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getMatchDate() throws CalendarException {
        matchDate.setCalPattern(getCalPattern());
        return this.matchDate;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� FLAG
     *
     * @return String
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� USER_ID
     *
     * @return String
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� ������
     *
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� �ϴ��޸�����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� �ϴ��޸���
     *
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ�ʲ�ƥ��-ƥ�����ݴ洢(EAM)���� �ϴ��޸���
     *
     * @return String
     */
    public int getOrganizationId() {
        return organizationId;
    }
}