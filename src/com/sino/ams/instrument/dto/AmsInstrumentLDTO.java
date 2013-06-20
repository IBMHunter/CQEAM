package com.sino.ams.instrument.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2007-10-24
 * Time: 22:09:20
 * To change this template use File | Settings | File Templates.
 */
public class AmsInstrumentLDTO extends CheckBoxDTO {

	private String barcode = "";
	private String itemCode = "";
	private String instruUsage = "";
	private SimpleCalendar creationDate = null;
	private int createdBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private String currKeepUser = "";
    private String itemName="";
    private String barcodeNo1="";
    private String itemCategory="";
    private String itemSpec="";
    private String vendorName="";
    private int vendorId=SyBaseSQLUtil.NULL_INT_VALUE;
    private int borrowUser=SyBaseSQLUtil.NULL_INT_VALUE;
    private String borrowDate="";
    private int  confirmUser=SyBaseSQLUtil.NULL_INT_VALUE;
    private String confirmDate="";
    private String transNo="";
    private String createName="";
    private int lineId=SyBaseSQLUtil.NULL_INT_VALUE;
    private int transId=SyBaseSQLUtil.NULL_INT_VALUE;
    private String barcodeStatus ="";
    private String cname="";
    private String dname="";
    private String responsibilityName="";
    private String isFull = "";
    private String instruStatus = "";
    private String isFullOption = "";
    private String instruStatusOtpion = "";


    public String getFullOption() {
        return isFullOption;
    }

    public void setFullOption(String fullOption) {
        isFullOption = fullOption;
    }

    public String getInstruStatusOtpion() {
        return instruStatusOtpion;
    }

    public void setInstruStatusOtpion(String instruStatusOtpion) {
        this.instruStatusOtpion = instruStatusOtpion;
    }

    public String getFull() {
        return isFull;
    }

    public void setFull(String full) {
        isFull = full;
    }

    public String getInstruStatus() {
        return instruStatus;
    }

    public void setInstruStatus(String instruStatus) {
        this.instruStatus = instruStatus;
    }


    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBarcodeStatus() {
        return barcodeStatus;
    }

    public void setBarcodeStatus(String barcodeStatus) {
        this.barcodeStatus = barcodeStatus;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public int getBorrowUser() {
        return borrowUser;
    }

    public void setBorrowUser(int borrowUser) {
        this.borrowUser = borrowUser;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getConfirmUser() {
        return confirmUser;
    }

    public void setConfirmUser(int confirmUser) {
        this.confirmUser = confirmUser;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
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

    public String getBarcodeNo1() {
        return barcodeNo1;
    }

    public void setBarcodeNo1(String barcodeNo1) {
        this.barcodeNo1 = barcodeNo1;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
	 * ���ܣ����������Ǳ����(EAM)���� �Ǿ�����
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �������
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �Ǿ���;
	 * @param instruUsage String
	 */
	public void setInstruUsage(String instruUsage){
		this.instruUsage = instruUsage;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws com.sino.base.exception.CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException {
		if(!StrUtil.isEmpty(creationDate)){
			this.creationDate = new SimpleCalendar(creationDate);
		}
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
		}
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ����������Ǳ����(EAM)���� ��ǰʹ����
	 * @param currKeepUser String
	 */
	public void setCurrKeepUser(String currKeepUser){
		this.currKeepUser = currKeepUser;
	}


	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �Ǿ�����
	 * @return String
	 */
	public String getBarcode(){
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �������
	 * @return String
	 */
	public String getItemCode(){
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �Ǿ���;
	 * @return String
	 */
	public String getInstruUsage(){
		return this.instruUsage;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� ��������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ����(EAM)���� ��ǰʹ����
	 * @return String
	 */
	public String getCurrKeepUser(){
		return this.currKeepUser;
	}

}