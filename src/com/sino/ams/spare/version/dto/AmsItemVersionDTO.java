package com.sino.ams.spare.version.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: �����汾�䶯�� AmsItemVersion</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class AmsItemVersionDTO extends CheckBoxDTO {
    public AmsItemVersionDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    private String barcode = "";
    private String itemCode = "";
    private String vendorBarcode = "";
    private String versionNo = "";
    private String organizationId = "";
    private SimpleCalendar creationDate = null;
    private String createdBy = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateBy = "";
    private String transId = "";
    private String remark = "";
    private String versionId = "";
    
    private String itemName = "";
    private String itemSpec = "";
    private String itemCategory = "";

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


    /**
     * ���ܣ����ñ����汾�䶯������ ��������
     *
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ �豸�������
     *
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ ��Ӧ��������
     *
     * @param vendorBarcode String
     */
    public void setVendorBarcode(String vendorBarcode) {
        this.vendorBarcode = vendorBarcode;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ �汾��
     *
     * @param versionNo String
     */
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ OU��֯ID
     *
     * @param organizationId String
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ ���汾��������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        if (!StrUtil.isEmpty(creationDate)) {
            this.creationDate = new SimpleCalendar(creationDate);
        } else {
            this.creationDate = null;
        }
    }

    /**
     * ���ܣ����ñ����汾�䶯������ ������
     *
     * @param createdBy String
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        if (!StrUtil.isEmpty(lastUpdateDate)) {
            this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
        } else {
            this.lastUpdateDate = null;
        }
    }

    /**
     * ���ܣ����ñ����汾�䶯������ �ϴ��޸���
     *
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ �����汾�䶯�����޷�������ID
     *
     * @param transId String
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ ��ע
     *
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����ñ����汾�䶯������ �汾���к�
     *
     * @param versionId String
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }


    /**
     * ���ܣ���ȡ�����汾�䶯������ ��������
     *
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ �豸�������
     *
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ ��Ӧ��������
     *
     * @return String
     */
    public String getVendorBarcode() {
        return this.vendorBarcode;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ �汾��
     *
     * @return String
     */
    public String getVersionNo() {
        return this.versionNo;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ OU��֯ID
     *
     * @return String
     */
    public String getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ ���汾��������
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
     * ���ܣ���ȡ�����汾�䶯������ ������
     *
     * @return String
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ �ϴ��޸�����
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
     * ���ܣ���ȡ�����汾�䶯������ �ϴ��޸���
     *
     * @return String
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ �����汾�䶯�����޷�������ID
     *
     * @return String
     */
    public String getTransId() {
        return this.transId;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ ��ע
     *
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ�����汾�䶯������ �汾���к�
     *
     * @return String
     */
    public String getVersionId() {
        return this.versionId;
    }

}