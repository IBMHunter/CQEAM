package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ��ʱ���� SfWorkHour</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfWorkHourDTO extends CheckBoxDTO{

	private int workHourId = 0;
	private int workingDate = 0;
	private String workBegin1 = "";
	private String workEnd1 = "";
	private String workBegin2 = "";
	private String workEnd2 = "";
	private int createdBy = -1;
	private String creationDate = "";
	private String lastUpdatedBy = "";
	private String lastUpdateDate = "";
	private String workHourName = "";

	public String getWorkHourName() {
		return workHourName;
	}

	public void setWorkHourName(String workHourName) {
		this.workHourName = workHourName;
	}

	public SfWorkHourDTO() {
		super();
	}

	/**
	 * ���ܣ����ù�ʱ�������� ����ʱ�� ID
	 * @param workHourId String
	 */
	public void setWorkHourId(int workHourId){
		this.workHourId = workHourId;
	}

	/**
	 * ���ܣ����ù�ʱ�������� ������, Bit0:����һ Bit1:���ڶ� Bit2:������ Bit3:������ Bit4:������ Bit5:������ Bit6:������
	 * @param workingDate String
	 */
	public void setWorkingDate(int workingDate){
		this.workingDate = workingDate;
	}

	/**
	 * ���ܣ����ù�ʱ�������� ���翪ʼ����ʱ��(24hh:mm:ss)
	 * @param workBegin1 String
	 */
	public void setWorkBegin1(String workBegin1){
		this.workBegin1 = workBegin1;
	}

	/**
	 * ���ܣ����ù�ʱ�������� �����������ʱ��
	 * @param workEnd1 String
	 */
	public void setWorkEnd1(String workEnd1){
		this.workEnd1 = workEnd1;
	}

	/**
	 * ���ܣ����ù�ʱ�������� ���翪ʼ����ʱ��
	 * @param workBegin2 String
	 */
	public void setWorkBegin2(String workBegin2){
		this.workBegin2 = workBegin2;
	}

	/**
	 * ���ܣ����ù�ʱ�������� �����������ʱ��
	 * @param workEnd2 String
	 */
	public void setWorkEnd2(String workEnd2){
		this.workEnd2 = workEnd2;
	}

	/**
	 * ���ܣ����ù�ʱ�������� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����ù�ʱ�������� ��������
	 * @param lastUpdatedBy String
	 */
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� ����ʱ�� ID
	 * @return String
	 */
	public int getWorkHourId() {
		return this.workHourId;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� ������, Bit0:����һ Bit1:���ڶ� Bit2:������ Bit3:������ Bit4:������ Bit5:������ Bit6:������
	 * @return String
	 */
	public int getWorkingDate() {
		return this.workingDate;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� ���翪ʼ����ʱ��(24hh:mm:ss)
	 * @return String
	 */
	public String getWorkBegin1() {
		return this.workBegin1;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� �����������ʱ��
	 * @return String
	 */
	public String getWorkEnd1() {
		return this.workEnd1;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� ���翪ʼ����ʱ��
	 * @return String
	 */
	public String getWorkBegin2() {
		return this.workBegin2;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� �����������ʱ��
	 * @return String
	 */
	public String getWorkEnd2() {
		return this.workEnd2;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� ������
	 * @return String
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ��ʱ�������� ��������
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}