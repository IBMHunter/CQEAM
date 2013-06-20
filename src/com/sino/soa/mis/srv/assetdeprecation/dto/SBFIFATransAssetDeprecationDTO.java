package com.sino.soa.mis.srv.assetdeprecation.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-10-12
 * Time: 15:06:14
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFATransAssetDeprecationDTO extends CheckBoxDTO {

	private String bookTypeCode = "";
	private String periodName = "";
	private SimpleCalendar startDate = null;
	private SimpleCalendar endDate = null;
	private SimpleCalendar periodOpenDate = null;
	private SimpleCalendar periodCloseDate = null;
	private String glTransferFlag = "";
	private String periodStatus = "";
    private String bookOption="";
    private String asstesType ="";

	public SBFIFATransAssetDeprecationDTO() {
		super();
		this.startDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
		this.periodOpenDate = new SimpleCalendar();
		this.periodCloseDate = new SimpleCalendar();

	}

	/**
	 * ���ܣ������ʲ������״̬���� �ʲ��˲�
	 * @param bookTypeCode String
	 */
	public void setBookTypeCode(String bookTypeCode){
		this.bookTypeCode = bookTypeCode;
	}

	/**
	 * ���ܣ������ʲ������״̬���� �ڼ�����
	 * @param periodName String
	 */
	public void setPeriodName(String periodName){
		this.periodName = periodName;
	}

	/**
	 * ���ܣ������ʲ������״̬���� ��ʼ����
	 * @param startDate SimpleCalendar
	 * @throws com.sino.base.exception.CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException {
		this.startDate.setCalendarValue(startDate);
	}

	/**
	 * ���ܣ������ʲ������״̬���� ��ֹ����
	 * @param endDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setEndDate(String endDate) throws CalendarException{
		this.endDate.setCalendarValue(endDate);
	}

	/**
	 * ���ܣ������ʲ������״̬���� �ڼ������
	 * @param periodOpenDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setPeriodOpenDate(String periodOpenDate) throws CalendarException{
		this.periodOpenDate.setCalendarValue(periodOpenDate);
	}

	/**
	 * ���ܣ������ʲ������״̬���� �ڼ�ر�����
	 * @param periodCloseDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setPeriodCloseDate(String periodCloseDate) throws CalendarException{
		this.periodCloseDate.setCalendarValue(periodCloseDate);
	}

	/**
	 * ���ܣ������ʲ������״̬���� �Ƿ������� Y/N
	 * @param glTransferFlag String
	 */
	public void setGlTransferFlag(String glTransferFlag){
		this.glTransferFlag = glTransferFlag;
	}

	/**
	 * ���ܣ������ʲ������״̬���� ����ڼ�״̬ CLOSE/OPEN
	 * @param periodStatus String
	 */
	public void setPeriodStatus(String periodStatus){
		this.periodStatus = periodStatus;
	}


	/**
	 * ���ܣ���ȡ�ʲ������״̬���� �ʲ��˲�
	 * @return String
	 */
	public String getBookTypeCode() {
		return this.bookTypeCode;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� �ڼ�����
	 * @return String
	 */
	public String getPeriodName() {
		return this.periodName;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� ��ʼ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� ��ֹ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getEndDate() throws CalendarException {
		endDate.setCalPattern(getCalPattern());
		return this.endDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� �ڼ������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getPeriodOpenDate() throws CalendarException {
		periodOpenDate.setCalPattern(getCalPattern());
		return this.periodOpenDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� �ڼ�ر�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getPeriodCloseDate() throws CalendarException {
		periodCloseDate.setCalPattern(getCalPattern());
		return this.periodCloseDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� �Ƿ������� Y/N
	 * @return String
	 */
	public String getGlTransferFlag() {
		return this.glTransferFlag;
	}

	/**
	 * ���ܣ���ȡ�ʲ������״̬���� ����ڼ�״̬ CLOSE/OPEN
	 * @return String
	 */
	public String getPeriodStatus() {
		return this.periodStatus;
	}

    public String getBookOption() {
        return bookOption;
    }

    public void setBookOption(String bookOption) {
        this.bookOption = bookOption;
    }

	public String getAsstesType() {
		return asstesType;
	}

	public void setAsstesType(String asstesType) {
		this.asstesType = asstesType;
	}

}