package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��ֵ�׺��̵㹤������ EamDhCheckBatch</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EamDhCheckBatchDTO extends EamCheckTaskDTO{

	private String batchId = "";
	private String batchNo = "";
	private int checkDept = 0;
	private String batchStatus = "";
	private String batchStatusValue = "";
	private String batchStatusOpt = "";
	private SimpleCalendar distributeDate = null;
	private int distributeBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private String distributeUser = "";
	private String checkDeptName = "";
	private String defaultImpBy = "";
	private String defaultImpUser = "";
	private String createdLoginUser = "";

	private int defaultImpDays = 60;
	private String orderType = "";
	private String orderTypeValue = "";

	private String checkTools = "";
	private String checkToolsValue = "";
	private String checkToolsOpt = "";

	public EamDhCheckBatchDTO() {
		super();
		this.distributeDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���������� �̵������к�
	 * @param batchId String
	 */
	public void setBatchId(String batchId){
		this.batchId = batchId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���������� �̵㹤������
	 * @param batchNo String
	 */
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���������� �̵㲿��
	 * @param checkDept String
	 */
	public void setCheckDept(int checkDept){
		this.checkDept = checkDept;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���������� �̵㹤����״̬(������״̬�ֵ�)
	 * @param batchStatus String
	 */
	public void setBatchStatus(String batchStatus){
		this.batchStatus = batchStatus;
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���������� �·�����
	 * @param distributeDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDistributeDate(String distributeDate) throws CalendarException{
		this.distributeDate.setCalendarValue(distributeDate);
	}

	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤���������� �·���
	 * @param distributeBy String
	 */
	public void setDistributeBy(int distributeBy){
		this.distributeBy = distributeBy;
	}

	public void setBatchStatusValue(String batchStatusValue) {
		this.batchStatusValue = batchStatusValue;
	}

	public void setDistributeUser(String distributeUser) {
		this.distributeUser = distributeUser;
	}

	public void setCheckDeptName(String checkDeptName) {
		this.checkDeptName = checkDeptName;
	}

	public void setBatchStatusOpt(String batchStatusOpt) {
		this.batchStatusOpt = batchStatusOpt;
	}

	public void setDefaultImpBy(String defaultImpBy) {
		this.defaultImpBy = defaultImpBy;
	}

	public void setDefaultImpDays(int defaultImpDays) {
		this.defaultImpDays = defaultImpDays;
	}

	public void setDefaultImpUser(String defaultImpUser) {
		this.defaultImpUser = defaultImpUser;
	}

	public void setCreatedLoginUser(String createdLoginUser) {
		this.createdLoginUser = createdLoginUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���������� �̵������к�
	 * @return String
	 */
	public String getBatchId() {
		return this.batchId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���������� �̵㹤������
	 * @return String
	 */
	public String getBatchNo() {
		return this.batchNo;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���������� �̵㲿��
	 * @return String
	 */
	public int getCheckDept() {
		return this.checkDept;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���������� �̵㹤����״̬(������״̬�ֵ�)
	 * @return String
	 */
	public String getBatchStatus() {
		return this.batchStatus;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���������� �·�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDistributeDate() throws CalendarException {
		distributeDate.setCalPattern(getCalPattern());
		return this.distributeDate;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤���������� �·���
	 * @return String
	 */
	public int getDistributeBy() {
		return this.distributeBy;
	}

	public String getBatchStatusValue() {
		return batchStatusValue;
	}

	public String getDistributeUser() {
		return distributeUser;
	}

	public String getCheckDeptName() {
		return checkDeptName;
	}

	public String getBatchStatusOpt() {
		return batchStatusOpt;
	}

	public String getDefaultImpBy() {
		return defaultImpBy;
	}

	public int getDefaultImpDays() {
		return defaultImpDays;
	}

	public String getDefaultImpUser() {
		return defaultImpUser;
	}

	public String getCreatedLoginUser() {
		return createdLoginUser;
	}


	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ �̵㵥������
	 * @param orderType String
	 */
	public void setOrderType(String orderType){
		this.orderType = orderType;
	}

	public void setOrderTypeValue(String orderTypeValue) {
		this.orderTypeValue = orderTypeValue;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ �̵㵥������
	 * @return String
	 */
	public String getOrderType() {
		return this.orderType;
	}

	public String getOrderTypeValue() {
		if(orderTypeValue.equals("")){
			String[] orderTypes = LvecDicts.ORD_TYPE1_LIST;
			String[] orderTypeValues = LvecDicts.ORD_TYPE2_LIST;
			int typeCount = orderTypes.length;
			for(int i = 0; i < typeCount; i++){
				if(orderTypes[i].equals(orderType)){
					orderTypeValue = orderTypeValues[i];
					break;
				}
			}
		}
		return orderTypeValue;
	}


	/**
	 * ���ܣ����õ�ֵ�׺��̵㹤��ͷ������ ȷ�Ϸ�ʽ��0��PDAȷ�ϣ�1��WEBȷ��
	 * @param checkTools String
	 */
	public void setCheckTools(String checkTools){
		this.checkTools = checkTools;
	}

	public void setCheckToolsOpt(String checkToolsOpt) {
		this.checkToolsOpt = checkToolsOpt;
	}

	public void setCheckToolsValue(String checkToolsValue) {
		this.checkToolsValue = checkToolsValue;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��̵㹤��ͷ������ ȷ�Ϸ�ʽ��0��PDAȷ�ϣ�1��WEBȷ��
	 * @return String
	 */
	public String getCheckTools() {
		return this.checkTools;
	}

	public String getCheckToolsOpt() {
		return checkToolsOpt;
	}

	public String getCheckToolsValue() {
		return checkToolsValue;
	}
}
