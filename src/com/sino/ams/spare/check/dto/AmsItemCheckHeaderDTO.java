package com.sino.ams.spare.check.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �����̵�ͷ��(AMS) AmsItemCheckHeader</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsItemCheckHeaderDTO extends CheckBoxDTO {

	private String headerId = "";
	private String checkLocation = "";
	private String implementBy = "";
	private String implementDays = "";
	private SimpleCalendar distributeDate = null;
	private String distributeBy = "";
	private SimpleCalendar downloadDate = null;
	private String downloadBy = "";
	private SimpleCalendar scanoverDate = null;
	private String scanoverBy = "";
	private SimpleCalendar uploadDate = null;
	private String uploadBy = "";
	private SimpleCalendar archivedDate = null;
	private String archivedBy = "";
	private String differenceReason = "";
	private String organizationId = "";
	private String responsibleUser = "";
	private SimpleCalendar creationDate = null;
	private int createdBy = -1;
	private SimpleCalendar lastUpdateDate = null;
	private String lastUpdateBy = "";
	private String orderStatus = "";
	private String orderStatusName = "";
	private SimpleCalendar startDate = null;
    private String transNo = "";
    private String transType = "";
    private String createdUser = "";
    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;
    private String itemName="";
    private String itemSpec="";
    private String   workorderObjectName="";
    private String addressId="";
    private String implementByName="";
    private String checkType="";
    private  String checkLocationName="";


    public AmsItemCheckHeaderDTO() {
		super();
		this.distributeDate = new SimpleCalendar();
		this.downloadDate = new SimpleCalendar();
		this.scanoverDate = new SimpleCalendar();
		this.uploadDate = new SimpleCalendar();
		this.archivedDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
		this.startDate = new SimpleCalendar();
        this.fromDate=new SimpleCalendar();
        this.toDate=new SimpleCalendar();
    }

    public String getCheckLocationName() {
        return checkLocationName;
    }

    public void setCheckLocationName(String checkLocationName) {
        this.checkLocationName = checkLocationName;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getImplementByName() {
        return implementByName;
    }

    public void setImplementByName(String implementByName) {
        this.implementByName = implementByName;
    }

    public SimpleCalendar getFromDate() throws CalendarException {
        return this.fromDate;
    }

    public void setFromDate(String fromDate) throws CalendarException {
        this.fromDate.setCalendarValue(fromDate);
    }

    public void setToDate(String toDate) throws CalendarException{
		this.toDate.setCalendarValue(toDate);
	}

   public SimpleCalendar getToDate() {
		return this.toDate;
	}

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
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

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �̵㵥���к�
	 * @param headerId String
	 */
	public void setHeaderId(String headerId){
		this.headerId = headerId;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �̵�ص�
	 * @param checkLocation String
	 */
	public void setCheckLocation(String checkLocation){
		this.checkLocation = checkLocation;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ���ݺ�
	 * @param transNo String
	 */
	public void setTransNo(String transNo){
		this.transNo = transNo;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ִ����
	 * @param implementBy String
	 */
	public void setImplementBy(String implementBy){
		this.implementBy = implementBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ִ������
	 * @param implementDays String
	 */
	public void setImplementDays(String implementDays){
		this.implementDays = implementDays;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �·�����
	 * @param distributeDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDistributeDate(String distributeDate) throws CalendarException{
		this.distributeDate.setCalendarValue(distributeDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �·���
	 * @param distributeBy String
	 */
	public void setDistributeBy(String distributeBy){
		this.distributeBy = distributeBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ��������
	 * @param downloadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDownloadDate(String downloadDate) throws CalendarException{
		this.downloadDate.setCalendarValue(downloadDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ������
	 * @param downloadBy String
	 */
	public void setDownloadBy(String downloadBy){
		this.downloadBy = downloadBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ɨ���������
	 * @param scanoverDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setScanoverDate(String scanoverDate) throws CalendarException{
		this.scanoverDate.setCalendarValue(scanoverDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ɨ����
	 * @param scanoverBy String
	 */
	public void setScanoverBy(String scanoverBy){
		this.scanoverBy = scanoverBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �ϴ�����/ʵ���������
	 * @param uploadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setUploadDate(String uploadDate) throws CalendarException{
		this.uploadDate.setCalendarValue(uploadDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �ϴ���
	 * @param uploadBy String
	 */
	public void setUploadBy(String uploadBy){
		this.uploadBy = uploadBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �鵵����
	 * @param archivedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setArchivedDate(String archivedDate) throws CalendarException{
		this.archivedDate.setCalendarValue(archivedDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �鵵��
	 * @param archivedBy String
	 */
	public void setArchivedBy(String archivedBy){
		this.archivedBy = archivedBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ����ԭ��
	 * @param differenceReason String
	 */
	public void setDifferenceReason(String differenceReason){
		this.differenceReason = differenceReason;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ��֯
	 * @param organizationId String
	 */
	public void setOrganizationId(String organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ������
	 * @param responsibleUser String
	 */
	public void setResponsibleUser(String responsibleUser){
		this.responsibleUser = responsibleUser;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(String lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� �̵㵥״̬(10:������11:���·���12:�����أ�13:����ɣ�14:�Ѻ�ʵ��15:��ȡ��)
	 * @param orderStatus String
	 */
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	/**
	 * ���ܣ����ñ����̵�ͷ��(AMS)���� ��ʼʱ��
	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException{
		this.startDate.setCalendarValue(startDate);
	}


	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �̵㵥���к�
	 * @return String
	 */
	public String getHeaderId() {
		return this.headerId;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �̵�ص�
	 * @return String
	 */
	public String getCheckLocation() {
		return this.checkLocation;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ���ݺ�
	 * @return String
	 */
	public String getTransNo() {
		return this.transNo;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ִ����
	 * @return String
	 */
	public String getImplementBy() {
		return this.implementBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ִ������
	 * @return String
	 */
	public String getImplementDays() {
		return this.implementDays;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �·�����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getDistributeDate() {
		return this.distributeDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �·���
	 * @return String
	 */
	public String getDistributeBy() {
		return this.distributeBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ��������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getDownloadDate() {
		return this.downloadDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ������
	 * @return String
	 */
	public String getDownloadBy() {
		return this.downloadBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ɨ���������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getScanoverDate() {
		return this.scanoverDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ɨ����
	 * @return String
	 */
	public String getScanoverBy() {
		return this.scanoverBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �ϴ�����/ʵ���������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getUploadDate() {
		return this.uploadDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �ϴ���
	 * @return String
	 */
	public String getUploadBy() {
		return this.uploadBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �鵵����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getArchivedDate() {
		return this.archivedDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �鵵��
	 * @return String
	 */
	public String getArchivedBy() {
		return this.archivedBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ����ԭ��
	 * @return String
	 */
	public String getDifferenceReason() {
		return this.differenceReason;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ��֯
	 * @return String
	 */
	public String getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ������
	 * @return String
	 */
	public String getResponsibleUser() {
		return this.responsibleUser;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ��������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getCreationDate() {
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ������
	 * @return String
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �ϴ��޸���
	 * @return String
	 */
	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� �̵㵥״̬(10:������11:���·���12:�����أ�13:����ɣ�14:�Ѻ�ʵ��15:��ȡ��)
	 * @return String
	 */
	public String getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * ���ܣ���ȡ�����̵�ͷ��(AMS)���� ��ʼʱ��
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getStartDate() {
		return this.startDate;
	}

}