package com.sino.ams.spare.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CommonUtilityDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.math.AdvancedNumber;

/**
* <p>Title: AMS_SPARE_INFO AmsSpareInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsSpareInfoDTO extends CommonUtilityDTO{

	private AdvancedNumber spareId = null;
	private String barcode = "";
	private String itemName = "";
	private String itemSpec = "";
	private String itemUnit = "";
	private AdvancedNumber objectNo = null;
	private AdvancedNumber quantity = null;
	private AdvancedNumber reserveQuantity = null;
	private AdvancedNumber organizationId = null;
	private AdvancedNumber itemCode = null;
	private String itemStatus = "";
	private String remark = "";
	private SimpleCalendar creationDate = null;
	private AdvancedNumber createdBy = null;
	private SimpleCalendar lastUpdateDate = null;
	private AdvancedNumber lastUpdateBy = null;

	public AmsSpareInfoDTO() {
		super();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();

		this.spareId = new AdvancedNumber();
		this.itemCode = new AdvancedNumber();
		this.objectNo = new AdvancedNumber();
		this.quantity = new AdvancedNumber();
		this.reserveQuantity = new AdvancedNumber();
		this.organizationId = new AdvancedNumber();
		this.createdBy = new AdvancedNumber();
		this.lastUpdateBy = new AdvancedNumber();
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� ID,������
	 * @param spareId AdvancedNumber
	 */
	public void setSpareId(AdvancedNumber spareId){
		this.spareId = spareId;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� ������
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� �ص�
	 * @param objectNo AdvancedNumber
	 */
	public void setObjectNo(AdvancedNumber objectNo){
		this.objectNo = objectNo;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� ����
	 * @param quantity AdvancedNumber
	 */
	public void setQuantity(AdvancedNumber quantity){
		this.quantity = quantity;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� ������
	 * @param reserveQuantity AdvancedNumber
	 */
	public void setReserveQuantity(AdvancedNumber reserveQuantity){
		this.reserveQuantity = reserveQuantity;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� ��֯ID
	 * @param organizationId AdvancedNumber
	 */
	public void setOrganizationId(AdvancedNumber organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� ����״̬(����,����,����)
	 * @param itemStatus String
	 */
	public void setItemStatus(String itemStatus){
		this.itemStatus = itemStatus;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� REMARK
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� CREATION_DATE
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� CREATED_BY
	 * @param createdBy AdvancedNumber
	 */
	public void setCreatedBy(AdvancedNumber createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� LAST_UPDATE_DATE
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����AMS_SPARE_INFO���� LAST_UPDATE_BY
	 * @param lastUpdateBy AdvancedNumber
	 */
	public void setLastUpdateBy(AdvancedNumber lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� ID,������
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getSpareId() {
		return this.spareId;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� ������
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� �ص�
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getObjectNo() {
		return this.objectNo;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� ����
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getQuantity() {
		return this.quantity;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� ������
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getReserveQuantity() {
		return this.reserveQuantity;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� ��֯ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� ����״̬(����,����,����)
	 * @return String
	 */
	public String getItemStatus() {
		return this.itemStatus;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� REMARK
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� CREATION_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� CREATED_BY
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� LAST_UPDATE_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡAMS_SPARE_INFO���� LAST_UPDATE_BY
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getLastUpdateBy() {
		return this.lastUpdateBy;
	}

    public AdvancedNumber getItemCode() {
        return itemCode;
    }

    public void setItemCode(AdvancedNumber itemCode) {
        this.itemCode = itemCode;
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

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }
}