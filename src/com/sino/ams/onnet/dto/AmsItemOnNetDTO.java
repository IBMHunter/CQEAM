package com.sino.ams.onnet.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */

public class AmsItemOnNetDTO extends CheckBoxDTO{

	private String id = "";
	private String partNo = "";
	private int quantity=-1;
	private int organizationId=-1;
	private String remark = "";
	private SimpleCalendar lastUpdateDate = null;
	private String lastUpdateUser = "";
	private String itemCode = "";
	private String itemName = "";
	private String itemSpec = "";
    private String vendorId = "";
    private String vendorName = "";
    private String orgnizationName = "";
    private String spareUsage = "";
    private String partNoError = "";
    private String ouError = "";
    private String quantityError = "";
    private String itemCategory = "";   //�豸����
    
    public String getSpareUsage() {
        return spareUsage;
    }

    public void setSpareUsage(String spareUsage) {
        this.spareUsage = spareUsage;
    }

    public String getOrgnizationName() {
        return orgnizationName;
    }

    public void setOrgnizationName(String orgnizationName) {
        this.orgnizationName = orgnizationName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public AmsItemOnNetDTO() {
		super();
		this.lastUpdateDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ������豸������������ ����
	 * @param id String
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * ���ܣ������豸������������ ����������
	 * @param partNo String
	 */
	public void setPartNo(String partNo){
		this.partNo = partNo;
	}

	/**
	 * ���ܣ������豸������������ ����
	 * @param quantity String
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	/**
	 * ���ܣ������豸������������ ��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ������豸������������ ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ������豸������������ ���䶯����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ������豸������������ ���䶯��
	 * @param lastUpdateUser String
	 */
	public void setLastUpdateUser(String lastUpdateUser){
		this.lastUpdateUser = lastUpdateUser;
	}


	/**
	 * ���ܣ���ȡ�豸������������ ����
	 * @return String
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * ���ܣ���ȡ�豸������������ ����������
	 * @return String
	 */
	public String getPartNo() {
		return this.partNo;
	}

	/**
	 * ���ܣ���ȡ�豸������������ ����
	 * @return String
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * ���ܣ���ȡ�豸������������ ��֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ�豸������������ ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ�豸������������ ���䶯����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�豸������������ ���䶯��
	 * @return String
	 */
	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}


    public String getPartNoError() {
        return partNoError;
    }

    public void setPartNoError(String partNoError) {
        this.partNoError = partNoError;
    }

    public String getOuError() {
        return ouError;
    }

    public void setOuError(String ouError) {
        this.ouError = ouError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

    
}