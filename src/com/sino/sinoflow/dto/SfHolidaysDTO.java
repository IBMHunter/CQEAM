package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: SF_HOLIDAYS SfHolidays</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfHolidaysDTO extends CheckBoxDTO{

	private int holidaysId = 0;
	private String holidays = "";
	private String name = "";
	private int year = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public SfHolidaysDTO() {
		super();
	}

	/**
	 * ���ܣ�����SF_HOLIDAYS���� �ڼ��� ID
	 * @param holidaysId String
	 */
	public void setHolidaysId(int holidaysId){
		this.holidaysId = holidaysId;
	}

	/**
	 * ���ܣ�����SF_HOLIDAYS���� �ڼ���, �����ʽΪXX.XX,��10.01, ��  ;  �ָ�
	 * @param holidays String
	 */
	public void setHolidays(String holidays){
		this.holidays = holidays;
	}


	/**
	 * ���ܣ���ȡSF_HOLIDAYS���� �ڼ��� ID
	 * @return String
	 */
	public int getHolidaysId() {
		return this.holidaysId;
	}

	/**
	 * ���ܣ���ȡSF_HOLIDAYS���� �ڼ���, �����ʽΪXX.XX,��10.01, ��  ;  �ָ�
	 * @return String
	 */
	public String getHolidays() {
		return this.holidays;
	}

}