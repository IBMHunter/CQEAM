package com.sino.ams.spare.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ����ҵ������(AMS) AmsItemReserved</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsItemReservedDTO extends CheckBoxDTO{
	public AmsItemReservedDTO() {
		super();
		this.reservedDate = new SimpleCalendar();
	}

	private String transId = "";
	private SimpleCalendar reservedDate = null;
	private String itemCode = "";
	private String reservedCount = "0";


	/**
	 * ���ܣ����ñ���ҵ������(AMS)���� ����ID
	 * @param transId String
	 */
	public void setTransId(String transId){
		this.transId = transId;
	}

	/**
	 * ���ܣ����ñ���ҵ������(AMS)���� ��������
	 * @param reservedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReservedDate(String reservedDate) throws CalendarException{
		this.reservedDate.setCalendarValue(reservedDate);
	}

	/**
	 * ���ܣ����ñ���ҵ������(AMS)���� �������
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����ñ���ҵ������(AMS)���� ��������
	 * @param reservedCount String
	 */
	public void setReservedCount(String reservedCount){
		this.reservedCount = reservedCount;
	}


	/**
	 * ���ܣ���ȡ����ҵ������(AMS)���� ����ID
	 * @return String
	 */
	public String getTransId() {
		return this.transId;
	}

	/**
	 * ���ܣ���ȡ����ҵ������(AMS)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReservedDate() throws CalendarException {
		if(reservedDate != null){
			reservedDate.setCalPattern(getCalPattern());
		}
		return this.reservedDate;
	}

	/**
	 * ���ܣ���ȡ����ҵ������(AMS)���� �������
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ����ҵ������(AMS)���� ��������
	 * @return String
	 */
	public String getReservedCount() {
		return this.reservedCount;
	}

}