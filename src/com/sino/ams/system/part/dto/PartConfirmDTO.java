package com.sino.ams.system.part.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.item.dto.EtsSystemItemDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-12-28
 * Time: 9:34:01
 * To change this template use File | Settings | File Templates.
 */
public class PartConfirmDTO extends EtsSystemItemDTO {

	private int systemId = SyBaseSQLUtil.NULL_INT_VALUE ;
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE ;
	private String itemCode1 = "";
	private String itemName1 = "";
	private String itemSpec1 = "";
	private int company = SyBaseSQLUtil.NULL_INT_VALUE ;

	private String checkId = "";
	private String oldItemCode = "";
	private String oldItemName = "";
	private String oldItemSpec = "";
	private String oldItemCategory = "";
	private String oldItemUnit = "";
	private String oldVendorId = "";
	private String isCorrect = "";
	private String matchReason = "";
	private String submitUser = "";
	private SimpleCalendar submitDate = null;
	private String checkUser = "";
	private SimpleCalendar checkDate = null;
	private String remark = "";
	private String itemName3 = "";
	private String itemSpec3 = "";
	private String items = "";


	public PartConfirmDTO() {
		super();
		this.submitDate = new SimpleCalendar();
		this.checkDate = new SimpleCalendar();
	}


	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}


	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getItemName3() {
		return itemName3;
	}

	public void setItemName3(String itemName3) {
		this.itemName3 = itemName3;
	}

	public String getItemSpec3() {
		return itemSpec3;
	}

	public void setItemSpec3(String itemSpec3) {
		this.itemSpec3 = itemSpec3;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getOldItemCode() {
		return oldItemCode;
	}

	public void setOldItemCode(String oldItemCode) {
		this.oldItemCode = oldItemCode;
	}

	public String getOldItemName() {
		return oldItemName;
	}

	public void setOldItemName(String oldItemName) {
		this.oldItemName = oldItemName;
	}

	public String getOldItemSpec() {
		return oldItemSpec;
	}

	public void setOldItemSpec(String oldItemSpec) {
		this.oldItemSpec = oldItemSpec;
	}

	public String getOldItemCategory() {
		return oldItemCategory;
	}

	public void setOldItemCategory(String oldItemCategory) {
		this.oldItemCategory = oldItemCategory;
	}

	public String getOldItemUnit() {
		return oldItemUnit;
	}

	public void setOldItemUnit(String oldItemUnit) {
		this.oldItemUnit = oldItemUnit;
	}

	public String getOldVendorId() {
		return oldVendorId;
	}

	public void setOldVendorId(String oldVendorId) {
		this.oldVendorId = oldVendorId;
	}


	public String getMatchReason() {
		return matchReason;
	}

	public void setMatchReason(String matchReason) {
		this.matchReason = matchReason;
	}

	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getItemCode1() {
		return itemCode1;
	}

	public void setItemCode1(String itemCode1) {
		this.itemCode1 = itemCode1;
	}

	public String getItemName1() {
		return itemName1;
	}

	public void setItemName1(String itemName1) {
		this.itemName1 = itemName1;
	}

	public String getItemSpec1() {
		return itemSpec1;
	}

	public void setItemSpec1(String itemSpec1) {
		this.itemSpec1 = itemSpec1;
	}

	/**
	 * ���ܣ�����������֯��������� ���к�
	 * @param systemId String
	 */
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	/**
	 * ���ܣ�����������֯��������� ������֯
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}


	/**
	 * ���ܣ���ȡ������֯��������� ���к�
	 * @return String
	 */
	public int getSystemId() {
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡ������֯��������� ������֯
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ������豸����ȷ����Ϣ���� �ύ����
	 * @param submitDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setSubmitDate(String submitDate) throws CalendarException {
		this.submitDate.setCalendarValue(submitDate);
	}

	/**
	 * ���ܣ���ȡ�豸����ȷ����Ϣ���� �ύ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getSubmitDate() throws CalendarException {
		submitDate.setCalPattern(getCalPattern());
		return this.submitDate;
	}

	/**
	 * ���ܣ������豸����ȷ����Ϣ���� �������
	 * @param checkDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCheckDate(String checkDate) throws CalendarException {
		this.checkDate.setCalendarValue(checkDate);
	}

	/**
	 * ���ܣ���ȡ�豸����ȷ����Ϣ���� �������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCheckDate() throws CalendarException {
		checkDate.setCalPattern(getCalPattern());
		return this.checkDate;
	}

	/**
	 * ���ܣ������豸����ȷ����Ϣ���� �Ƿ���ȷ
	 * @param isCorrect String
	 */
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * ���ܣ���ȡ�豸����ȷ����Ϣ���� �Ƿ���ȷ
	 * @return String
	 */
	public String getIsCorrect() {
		return this.isCorrect;
	}
}
