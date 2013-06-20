package com.sino.ams.sampling.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��鹤���� AmsAssetsSamplingHeader</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsAssetsSamplingHeaderDTO extends AmsAssetsSamplingBatchDTO{

	private String headerId = "";
	private String samplingLocation = "";
	private String orderNo = "";
	private int implementBy;
	private SimpleCalendar startTime = null;
	private int implementDays;
	private SimpleCalendar distributeDate = null;
	private int distributeBy;
	private String distributeUser = "";
	private SimpleCalendar downloadDate = null;
	private int downloadBy;
	private String downloadUser = "";
	private SimpleCalendar uploadDate = null;
	private int uploadBy;
	private String uploadUser = "";
	private String orderStatus = "";
	private String orderType = "";
	private String orderTypeName = "";
	private String samplingLocationName = "";
	private String implementUser = "";
	private SimpleCalendar taskCreationDate = null;
	private String taskCreatedUser = "";
	private String orderStatusValue = "";
	private String samplingLocationCode = "";
	private String orderStatusOpt = "";
	private SimpleCalendar deadlineDate = null;
	private SimpleCalendar scanoverDate = null;
	private int scanoverBy;
    private String company = "";

    public AmsAssetsSamplingHeaderDTO() {
		super();
		this.startTime = new SimpleCalendar();
		this.distributeDate = new SimpleCalendar();
		this.downloadDate = new SimpleCalendar();
		this.uploadDate = new SimpleCalendar();
		this.taskCreationDate = new SimpleCalendar();
		this.deadlineDate = new SimpleCalendar();
		this.scanoverDate = new SimpleCalendar();
	}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
	 * ���ܣ����ó�鹤�������� ����ID
	 * @param headerId String
	 */
	public void setHeaderId(String headerId){
		this.headerId = headerId;
	}

	/**
	 * ���ܣ����ó�鹤�������� ���ص�
	 * @param samplingLocation String
	 */
	public void setSamplingLocation(String samplingLocation){
		this.samplingLocation = samplingLocation;
	}

	/**
	 * ���ܣ����ó�鹤�������� ������
	 * @param orderNo String
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	/**
	 * ���ܣ����ó�鹤�������� ִ����
	 * @param implementBy String
	 */
	public void setImplementBy(int implementBy){
		this.implementBy = implementBy;
	}

	/**
	 * ���ܣ����ó�鹤�������� ��ʼ����
	 * @param startTime SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartTime(String startTime) throws CalendarException{
		this.startTime.setCalendarValue(startTime);
	}

	/**
	 * ���ܣ����ó�鹤�������� ִ������
	 * @param implementDays String
	 */
	public void setImplementDays(int implementDays){
		this.implementDays = implementDays;
	}

	/**
	 * ���ܣ����ó�鹤�������� �·�����
	 * @param distributeDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDistributeDate(String distributeDate) throws CalendarException{
		this.distributeDate.setCalendarValue(distributeDate);
	}

	/**
	 * ���ܣ����ó�鹤�������� �·���
	 * @param distributeBy String
	 */
	public void setDistributeBy(int distributeBy){
		this.distributeBy = distributeBy;
	}

	/**
	 * ���ܣ����ó�鹤�������� ��������
	 * @param downloadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDownloadDate(String downloadDate) throws CalendarException{
		this.downloadDate.setCalendarValue(downloadDate);
	}

	/**
	 * ���ܣ����ó�鹤�������� ������
	 * @param downloadBy String
	 */
	public void setDownloadBy(int downloadBy){
		this.downloadBy = downloadBy;
	}

	/**
	 * ���ܣ����ó�鹤�������� ��������
	 * @param uploadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setUploadDate(String uploadDate) throws CalendarException{
		this.uploadDate.setCalendarValue(uploadDate);
	}


	/**
	 * ����:�������񴴽�����
	 * @param taskCreationDate String
	 * @throws CalendarException
	 */
	public void setTaskCreationDate(String taskCreationDate) throws CalendarException {
		this.taskCreationDate.setCalendarValue(taskCreationDate);
	}

	/**
	 * ���ܣ����ó�鹤�������� ������
	 * @param uploadBy String
	 */
	public void setUploadBy(int uploadBy){
		this.uploadBy = uploadBy;
	}

	/**
	 * ���ܣ����ó�鹤�������� ����״̬
	 * @param orderStatus String
	 */
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}


	/**
	 * ���ܣ������ʲ������������� �������ͣ�Ĭ��Ϊ�ʲ����
	 * @param orderType String
	 */
	public void setOrderType(String orderType){
		this.orderType = orderType;
	}

	public void setSamplingLocationName(String samplingLocationName) {
		this.samplingLocationName = samplingLocationName;
	}

	public void setImplementUser(String implementUser) {
		this.implementUser = implementUser;
	}


	public void setTaskCreatedUser(String taskCreatedUser) {
		this.taskCreatedUser = taskCreatedUser;
	}

	public void setOrderStatusValue(String orderStatusValue) {
		this.orderStatusValue = orderStatusValue;
	}

	public void setSamplingLocationCode(String samplingLocationCode) {
		this.samplingLocationCode = samplingLocationCode;
	}

	public void setDistributeUser(String distributeUser) {
		this.distributeUser = distributeUser;
	}

	public void setDownloadUser(String downloadUser) {
		this.downloadUser = downloadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public void setOrderStatusOpt(String orderStatusOpt) {
		this.orderStatusOpt = orderStatusOpt;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public void setScanoverBy(int scanoverBy) {
		this.scanoverBy = scanoverBy;
	}

	public void setScanoverDate(String scanoverDate) throws CalendarException {
		this.scanoverDate.setCalendarValue(scanoverDate);
	}

	public void setDeadlineDate(String deadlineDate) throws CalendarException {
		this.deadlineDate.setCalendarValue(deadlineDate);
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ����ID
	 * @return String
	 */
	public String getHeaderId() {
		return this.headerId;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ���ص�
	 * @return String
	 */
	public String getSamplingLocation() {
		return this.samplingLocation;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ������
	 * @return String
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ִ����
	 * @return String
	 */
	public int getImplementBy() {
		return this.implementBy;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ��ʼ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartTime() throws CalendarException {
		startTime.setCalPattern(getCalPattern());
		return this.startTime;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ִ������
	 * @return String
	 */
	public int getImplementDays() {
		return this.implementDays;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� �·�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDistributeDate() throws CalendarException {
		distributeDate.setCalPattern(getCalPattern());
		return this.distributeDate;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� �·���
	 * @return String
	 */
	public int getDistributeBy() {
		return this.distributeBy;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDownloadDate() throws CalendarException {
		downloadDate.setCalPattern(getCalPattern());
		return this.downloadDate;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ������
	 * @return String
	 */
	public int getDownloadBy() {
		return this.downloadBy;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getUploadDate() throws CalendarException {
		uploadDate.setCalPattern(getCalPattern());
		return this.uploadDate;
	}


	/**
	 * ���ܣ���ȡ��鹤�������� ���񴴽�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getTaskCreationDate() throws CalendarException {
		taskCreationDate.setCalPattern(getCalPattern());
		return this.taskCreationDate;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ������
	 * @return String
	 */
	public int getUploadBy() {
		return this.uploadBy;
	}

	/**
	 * ���ܣ���ȡ��鹤�������� ����״̬
	 * @return String
	 */
	public String getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * ���ܣ���ȡ�ʲ������������� �������ͣ�Ĭ��Ϊ�ʲ����
	 * @return String
	 */
	public String getOrderType() {
		return this.orderType;
	}

	public String getSamplingLocationName() {
		return samplingLocationName;
	}

	public String getImplementUser() {
		return implementUser;
	}

	public String getTaskCreatedUser() {
		return taskCreatedUser;
	}

	public String getOrderStatusValue() {
		return orderStatusValue;
	}

	public String getSamplingLocationCode() {
		return samplingLocationCode;
	}

	public String getDistributeUser() {
		return distributeUser;
	}

	public String getDownloadUser() {
		return downloadUser;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public String getOrderStatusOpt() {
		return orderStatusOpt;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public SimpleCalendar getDeadlineDate() throws CalendarException {
		deadlineDate.setCalPattern(getCalPattern());
		return deadlineDate;
	}

	public SimpleCalendar getScanoverDate() throws CalendarException {
		scanoverDate.setCalPattern(getCalPattern());
		return scanoverDate;
	}

	public int getScanoverBy() {
		return scanoverBy;
	}
}
