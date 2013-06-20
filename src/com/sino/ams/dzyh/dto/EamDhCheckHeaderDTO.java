package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��ֵ�׺��̵㹤��ͷ�� EamDhCheckHeader</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EamDhCheckHeaderDTO extends EamDhCheckBatchDTO{

	private String headerId = "";
	private int checkLocation = SyBaseSQLUtil.NULL_INT_VALUE;
	private String orderNo = "";
	private int implementBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private String implementUser = "";
	private SimpleCalendar startTime = null;
	private String implementDays = "";
	private SimpleCalendar downloadDate = null;
	private int downloadBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private String downloadUser = "";
	private SimpleCalendar scanoverDate = null;
	private int scanoverBy = SyBaseSQLUtil.NULL_INT_VALUE ;
	private String scanoverUser = "";
	private SimpleCalendar uploadDate = null;
	private int uploadBy = SyBaseSQLUtil.NULL_INT_VALUE ;
	private String uploadUser = "";
	private SimpleCalendar archivedDate = null;
	private int archivedBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private String archivedUser = "";
	private String differenceReason = "";
	private String responsibleUser = "";
	private String orderStatus = "";
	private String orderStatusValue = "";
	private String orderStatusOpt = "";
	private String impDeptCode = "";
	private String impDeptName = "";
	private String impDeptOpt = "";
	private String locationCode = "";
	private String locationName = "";
	private SimpleCalendar deadlineDate = null;
	private String orderTypeOpt = "";

	public EamDhCheckHeaderDTO() {
		super();
		this.startTime = new SimpleCalendar();
		this.downloadDate = new SimpleCalendar();
		this.scanoverDate = new SimpleCalendar();
		this.uploadDate = new SimpleCalendar();
		this.archivedDate = new SimpleCalendar();
		this.deadlineDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �̵㵥���к�
	 * @param headerId String
	 */
	public void setHeaderId(String headerId){
		this.headerId = headerId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �̵�ص�
	 * @param checkLocation String
	 */
	public void setCheckLocation(int checkLocation){
		this.checkLocation = checkLocation;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ���ݺ�
	 * @param orderNo String
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ִ����
	 * @param implementBy String
	 */
	public void setImplementBy(int implementBy){
		this.implementBy = implementBy;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ��ʼʱ��
	 * @param startTime SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartTime(String startTime) throws CalendarException{
		this.startTime.setCalendarValue(startTime);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ִ������
	 * @param implementDays String
	 */
	public void setImplementDays(String implementDays){
		this.implementDays = implementDays;
	}


	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ��������
	 * @param downloadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDownloadDate(String downloadDate) throws CalendarException{
		this.downloadDate.setCalendarValue(downloadDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ������
	 * @param downloadBy String
	 */
	public void setDownloadBy(int downloadBy){
		this.downloadBy = downloadBy;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ɨ���������
	 * @param scanoverDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setScanoverDate(String scanoverDate) throws CalendarException{
		this.scanoverDate.setCalendarValue(scanoverDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ɨ����
	 * @param scanoverBy String
	 */
	public void setScanoverBy(int scanoverBy){
		this.scanoverBy = scanoverBy;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �ϴ�����/ʵ���������
	 * @param uploadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setUploadDate(String uploadDate) throws CalendarException{
		this.uploadDate.setCalendarValue(uploadDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �ϴ���
	 * @param uploadBy String
	 */
	public void setUploadBy(int uploadBy){
		this.uploadBy = uploadBy;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �鵵����
	 * @param archivedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setArchivedDate(String archivedDate) throws CalendarException{
		this.archivedDate.setCalendarValue(archivedDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �鵵��
	 * @param archivedBy String
	 */
	public void setArchivedBy(int archivedBy){
		this.archivedBy = archivedBy;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ����ԭ��
	 * @param differenceReason String
	 */
	public void setDifferenceReason(String differenceReason){
		this.differenceReason = differenceReason;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ������
	 * @param responsibleUser String
	 */
	public void setResponsibleUser(String responsibleUser){
		this.responsibleUser = responsibleUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �̵㵥״̬
	 * @param orderStatus String
	 */
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public void setImpDeptCode(String impDeptCode) {
		this.impDeptCode = impDeptCode;
	}

	public void setImpDeptName(String impDeptName) {

		this.impDeptName = impDeptName;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setImplementUser(String implementUser) {
		this.implementUser = implementUser;
	}

	public void setArchivedUser(String archivedUser) {
		this.archivedUser = archivedUser;
	}

	public void setDownloadUser(String downloadUser) {
		this.downloadUser = downloadUser;
	}

	public void setOrderStatusValue(String orderStatusValue) {
		this.orderStatusValue = orderStatusValue;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public void setOrderStatusOpt(String orderStatusOpt) {
		this.orderStatusOpt = orderStatusOpt;
	}

	public void setScanoverUser(String scanoverUser) {
		this.scanoverUser = scanoverUser;
	}

	public void setImpDeptOpt(String impDeptOpt) {
		this.impDeptOpt = impDeptOpt;
	}

	public void setOrderTypeOpt(String orderTypeOpt) {
		this.orderTypeOpt = orderTypeOpt;
	}

	public void setDeadlineDate(String deadlineDate) throws CalendarException {
		this.deadlineDate.setCalendarValue(deadlineDate);
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �̵㵥���к�
	 * @return String
	 */
	public String getHeaderId() {
		return this.headerId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �̵�ص�
	 * @return String
	 */
	public int getCheckLocation() {
		return this.checkLocation;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ���ݺ�
	 * @return String
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ִ����
	 * @return String
	 */
	public int getImplementBy() {
		return this.implementBy;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ��ʼʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartTime() throws CalendarException {
		startTime.setCalPattern(getCalPattern());
		return this.startTime;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ִ������
	 * @return String
	 */
	public String getImplementDays() {
		return this.implementDays;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDownloadDate() throws CalendarException {
		downloadDate.setCalPattern(getCalPattern());
		return this.downloadDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ������
	 * @return String
	 */
	public int getDownloadBy() {
		return this.downloadBy;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ɨ���������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getScanoverDate() throws CalendarException {
		scanoverDate.setCalPattern(getCalPattern());
		return this.scanoverDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ɨ����
	 * @return String
	 */
	public int getScanoverBy() {
		return this.scanoverBy;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �ϴ�����/ʵ���������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getUploadDate() throws CalendarException {
		uploadDate.setCalPattern(getCalPattern());
		return this.uploadDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �ϴ���
	 * @return String
	 */
	public int getUploadBy() {
		return this.uploadBy;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �鵵����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getArchivedDate() throws CalendarException {
		archivedDate.setCalPattern(getCalPattern());
		return this.archivedDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �鵵��
	 * @return String
	 */
	public int getArchivedBy() {
		return this.archivedBy;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ����ԭ��
	 * @return String
	 */
	public String getDifferenceReason() {
		return this.differenceReason;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ������
	 * @return String
	 */
	public String getResponsibleUser() {
		return this.responsibleUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �̵㵥״̬
	 * @return String
	 */
	public String getOrderStatus() {
		return this.orderStatus;
	}


	public String getImpDeptName() {
		return impDeptName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getImpDeptCode() {
		return impDeptCode;
	}

	public String getImplementUser() {
		return implementUser;
	}

	public SimpleCalendar getDeadlineDate() throws CalendarException {
		deadlineDate.setCalPattern(getCalPattern());
		return deadlineDate;
	}

	public String getArchivedUser() {
		return archivedUser;
	}

	public String getDownloadUser() {
		return downloadUser;
	}

	public String getOrderStatusValue() {
		return orderStatusValue;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public String getOrderStatusOpt() {
		return orderStatusOpt;
	}

	public String getScanoverUser() {
		return scanoverUser;
	}

	public String getImpDeptOpt() {
		return impDeptOpt;
	}

	public String getOrderTypeOpt() {
		return orderTypeOpt;
	}
}
