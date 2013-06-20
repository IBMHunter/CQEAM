package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.ct.dto.EtsItemInfoDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �����Ǳ������־ EamYbBorrowLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EamYbBorrowLogDTO extends EtsItemInfoDTO{

	private String borrowLogId = "";
	private String status = "";
	private String statusValue = "";
	private int borrowUserId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String borrowUser = "";
	private int approveUserId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String approveUser = "";
	private int handleUserId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String handleUser = "";
	private int orgId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String isApplyed = "";
	private String catalogName = "";
	private String createdUser = "";
	private int groupId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String groupName = "";
	private String groupOption = "";
	private String approveContent = "";
	private String statusRadio = "";

	private SimpleCalendar borrowDate = null;
	private SimpleCalendar cancelDate = null;
	private SimpleCalendar returnDatePlan = null;
	private SimpleCalendar returnDateActual = null;
	private SimpleCalendar approveDate = null;
	private SimpleCalendar handleDate = null;

	public EamYbBorrowLogDTO() {
		super();
		this.borrowDate = new SimpleCalendar();
		this.returnDatePlan = new SimpleCalendar();
		this.returnDateActual = new SimpleCalendar();
		this.approveDate = new SimpleCalendar();
		this.handleDate = new SimpleCalendar();
		this.cancelDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����������Ǳ������־���� PK
	 * @param borrowLogId String
	 */
	public void setBorrowLogId(String borrowLogId){
		this.borrowLogId = borrowLogId;
	}

	/**
	 * ���ܣ����������Ǳ������־���� ״̬(������������������رա�����) ���ֵ�"YB_BORROW_STATUS"
	 * @param status String
	 */
	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * ���ܣ����������Ǳ������־���� ������
	 * @param borrowUserId String
	 */
	public void setBorrowUserId(int borrowUserId){
		this.borrowUserId = borrowUserId;
	}

	/**
	 * ���ܣ����������Ǳ������־���� ��������
	 * @param borrowDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setBorrowDate(String borrowDate) throws CalendarException{
		this.borrowDate.setCalendarValue(borrowDate);
	}

	/**
	 * ���ܣ����������Ǳ������־���� Ԥ�ƹ黹ʱ��
	 * @param returnDatePlan SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReturnDatePlan(String returnDatePlan) throws CalendarException{
		this.returnDatePlan.setCalendarValue(returnDatePlan);
	}

	/**
	 * ���ܣ����������Ǳ������־���� ʵ�ʹ黹����
	 * @param returnDateActual SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReturnDateActual(String returnDateActual) throws CalendarException{
		this.returnDateActual.setCalendarValue(returnDateActual);
	}

	/**
	 * ���ܣ����������Ǳ������־���� ������
	 * @param approveUserId String
	 */
	public void setApproveUserId(int approveUserId){
		this.approveUserId = approveUserId;
	}

	/**
	 * ���ܣ����������Ǳ������־���� ��������
	 * @param approveDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setApproveDate(String approveDate) throws CalendarException{
		this.approveDate.setCalendarValue(approveDate);
	}

	/**
	 * ���ܣ����������Ǳ������־���� ������
	 * @param handleUserId String
	 */
	public void setHandleUserId(int handleUserId){
		this.handleUserId = handleUserId;
	}

	/**
	 * ���ܣ����������Ǳ������־���� ��������
	 * @param handleDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setHandleDate(String handleDate) throws CalendarException{
		this.handleDate.setCalendarValue(handleDate);
	}

	/**
	 * ���ܣ����������Ǳ������־���� ��֯ID
	 * @param orgId String
	 */
	public void setOrgId(int orgId){
		this.orgId = orgId;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}

	public void setBorrowUser(String borrowUser) {
		this.borrowUser = borrowUser;
	}

	public void setIsApplyed(String isApplyed) {
		this.isApplyed = isApplyed;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupOption(String groupOption) {
		this.groupOption = groupOption;
	}

	public void setStatusRadio(String statusRadio) {
		this.statusRadio = statusRadio;
	}

	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}

	public void setCancelDate(String cancelDate) throws CalendarException {
		this.cancelDate.setCalendarValue(cancelDate);
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� PK
	 * @return String
	 */
	public String getBorrowLogId() {
		return this.borrowLogId;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ״̬(������������������رա�����) ���ֵ�"YB_BORROW_STATUS"
	 * @return String
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ������
	 * @return String
	 */
	public int getBorrowUserId() {
		return this.borrowUserId;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getBorrowDate() throws CalendarException {
		borrowDate.setCalPattern(getCalPattern());
		return this.borrowDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� Ԥ�ƹ黹ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReturnDatePlan() throws CalendarException {
		returnDatePlan.setCalPattern(getCalPattern());
		getStatus();
		return this.returnDatePlan;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ʵ�ʹ黹����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReturnDateActual() throws CalendarException {
		returnDateActual.setCalPattern(getCalPattern());
		return this.returnDateActual;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ������
	 * @return String
	 */
	public int getApproveUserId() {
		return this.approveUserId;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getApproveDate() throws CalendarException {
		approveDate.setCalPattern(getCalPattern());
		return this.approveDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ������
	 * @return String
	 */
	public int getHandleUserId() {
		return this.handleUserId;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getHandleDate() throws CalendarException {
		handleDate.setCalPattern(getCalPattern());
		return this.handleDate;
	}

	/**
	 * ���ܣ���ȡ�����Ǳ������־���� ��֯ID
	 * @return String
	 */
	public int getOrgId() {
		return this.orgId;
	}

	public String getApproveUser() {
		return approveUser;
	}

	public String getHandleUser() {
		return handleUser;
	}

	public String getBorrowUser() {
		return borrowUser;
	}

	public SimpleCalendar getCancelDate() throws CalendarException {
		cancelDate.setCalPattern(getCalPattern());
		return cancelDate;
	}

	public String getIsApplyed() {
		return isApplyed;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getGroupOption() {
		return groupOption;
	}

	public String getStatusRadio() {
		return statusRadio;
	}

	public String getApproveContent() {
		return approveContent;
	}
}
