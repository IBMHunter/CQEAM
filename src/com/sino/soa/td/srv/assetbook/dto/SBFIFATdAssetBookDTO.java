package com.sino.soa.td.srv.assetbook.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-6
 * Time: 12:27:41
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFATdAssetBookDTO extends CheckBoxDTO {

	private String bookTypeCode = "";
	private String bookTypeName = "";
	private String orgName = "";
	private String setOfBooksName = "";
	private String prorateCalendar = "";
	private int orgId = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar dateIneffective = null;
	private SimpleCalendar lastDeprnRunDate = null;
	private String deprnStatus = "";
	private SimpleCalendar lastUpdateDate = null;
	private String orgOption="";
	private String assetsType ="";
	/**
	 * @return the orgOption
	 */
	public String getOrgOption() {
		return orgOption;
	}

	/**
	 * @param orgOption the orgOption to set
	 */
	public void setOrgOption(String orgOption) {
		this.orgOption = orgOption;
	}

	public SBFIFATdAssetBookDTO() {
		super();
		this.dateIneffective = new SimpleCalendar();
		this.lastDeprnRunDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ������ʲ��˲��������� �˲�����
	 * @param bookTypeCode String
	 */
	public void setBookTypeCode(String bookTypeCode){
		this.bookTypeCode = bookTypeCode;
	}

	/**
	 * ���ܣ������ʲ��˲��������� �˲�����
	 * @param bookTypeName String
	 */
	public void setBookTypeName(String bookTypeName){
		this.bookTypeName = bookTypeName;
	}

	/**
	 * ���ܣ������ʲ��˲��������� ��֯����
	 * @param orgName String
	 */
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}

	/**
	 * ���ܣ������ʲ��˲��������� �ʲ�������������
	 * @param setOfBooksName String
	 */
	public void setSetOfBooksName(String setOfBooksName){
		this.setOfBooksName = setOfBooksName;
	}

	/**
	 * ���ܣ������ʲ��˲��������� �ʲ���̯����
	 * @param prorateCalendar String
	 */
	public void setProrateCalendar(String prorateCalendar){
		this.prorateCalendar = prorateCalendar;
	}

	/**
	 * ���ܣ������ʲ��˲��������� ��֯ID
	 * @param orgId String
	 */
	public void setOrgId(int orgId){
		this.orgId = orgId;
	}

	/**
	 * ���ܣ������ʲ��˲��������� �˲���Ч����
	 * @param dateIneffective SimpleCalendar
	 * @throws com.sino.base.exception.CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDateIneffective(String dateIneffective) throws CalendarException {
		this.dateIneffective.setCalendarValue(dateIneffective);
	}

	/**
	 * ���ܣ������ʲ��˲��������� �ϴ������۾�ʱ��
	 * @param lastDeprnRunDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastDeprnRunDate(String lastDeprnRunDate) throws CalendarException{
		this.lastDeprnRunDate.setCalendarValue(lastDeprnRunDate);
	}

	/**
	 * ���ܣ������ʲ��˲��������� �۾����״̬
	 * @param deprnStatus String
	 */
	public void setDeprnStatus(String deprnStatus){
		this.deprnStatus = deprnStatus;
	}

	/**
	 * ���ܣ������ʲ��˲��������� ���¸���ʱ��
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}


	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �˲�����
	 * @return String
	 */
	public String getBookTypeCode() {
		return this.bookTypeCode;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �˲�����
	 * @return String
	 */
	public String getBookTypeName() {
		return this.bookTypeName;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� ��֯����
	 * @return String
	 */
	public String getOrgName() {
		return this.orgName;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �ʲ�������������
	 * @return String
	 */
	public String getSetOfBooksName() {
		return this.setOfBooksName;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �ʲ���̯����
	 * @return String
	 */
	public String getProrateCalendar() {
		return this.prorateCalendar;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� ��֯ID
	 * @return String
	 */
	public int getOrgId() {
		return this.orgId;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �˲���Ч����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDateIneffective() throws CalendarException {
		dateIneffective.setCalPattern(getCalPattern());
		return this.dateIneffective;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �ϴ������۾�ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastDeprnRunDate() throws CalendarException {
		lastDeprnRunDate.setCalPattern(getCalPattern());
		return this.lastDeprnRunDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� �۾����״̬
	 * @return String
	 */
	public String getDeprnStatus() {
		return this.deprnStatus;
	}

	/**
	 * ���ܣ���ȡ�ʲ��˲��������� ���¸���ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

}