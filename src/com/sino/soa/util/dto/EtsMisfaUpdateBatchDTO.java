package com.sino.soa.util.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ETS_MISFA_UPDATE_BATCH EtsMisfaUpdateBatch</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsMisfaUpdateBatchDTO extends CheckBoxDTO{

	private String batchId = "";
	private int transStatus = -1;
	private String remark = "";
	private String transType = "";
	private int organizationId = -1;
	private SimpleCalendar startDate = null;
	private SimpleCalendar endDate = null;
	private String errmsg = "";
	private SimpleCalendar creationDate = null;
	private int createdBy = -1;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy = -1;

	public EtsMisfaUpdateBatchDTO() {
		super();
		this.startDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();

	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� ETS_MISFA_UPDATE_LOG_B_s.nextval
	 * @param batchId int
	 */
	public void setBatchId(String batchId){
		this.batchId = batchId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� 0: �ȴ�ִ��  1:��������  2:�������
	 * @param transStatus String
	 */
	public void setTransStatus(int transStatus){
		this.transStatus = transStatus;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� REMARK
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� ��������LOCATION/TAGNUMBER/DESC/NEWLOC/LOCATION
	 * @param transType String
	 */
	public void setTransType(String transType){
		this.transType = transType;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� ORGANIZATION_ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� START_DATE
	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException{
		this.startDate.setCalendarValue(startDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� END_DATE
	 * @param endDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setEndDate(String endDate) throws CalendarException{
		this.endDate.setCalendarValue(endDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� ERRMSG
	 * @param errmsg String
	 */
	public void setErrmsg(String errmsg){
		this.errmsg = errmsg;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� ������
	 * @param createdBy int
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_BATCH���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� ETS_MISFA_UPDATE_LOG_B_s.nextval
	 * @return String
	 */
	public String getBatchId() {
		return this.batchId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� 0: �ȴ�ִ��  1:��������  2:�������
	 * @return String
	 */
	public int getTransStatus() {
		return this.transStatus;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� REMARK
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� ��������LOCATION/TAGNUMBER/DESC/NEWLOC/LOCATION
	 * @return String
	 */
	public String getTransType() {
		return this.transType;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� ORGANIZATION_ID
	 * @return int
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� START_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� END_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getEndDate() throws CalendarException {
		endDate.setCalPattern(getCalPattern());
		return this.endDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� ERRMSG
	 * @return String
	 */
	public String getErrmsg() {
		return this.errmsg;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� ������
	 * @return int
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_BATCH���� �ϴ��޸���
	 * @return int
	 */
	public int getLastUpdateBy() {
		return this.lastUpdateBy;
	}

}