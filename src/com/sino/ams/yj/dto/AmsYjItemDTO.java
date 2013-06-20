package com.sino.ams.yj.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: Ӧ�������豸�����ֵ�� AmsYjItem</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsYjItemDTO extends CheckBoxDTO{

	private String itemCode = "";
	private String itemName = "";
	private String itemCategory = "";
	private SimpleCalendar creationDate = null;
	private SimpleCalendar lastUpdateDate = null;
	private int createUser = SyBaseSQLUtil.NULL_INT_VALUE;;
	private int lastUpdateUser = SyBaseSQLUtil.NULL_INT_VALUE;;

	public AmsYjItemDTO() {
		super();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ ���
	 * @param itemCode AdvancedNumber
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ װ������
	 * @param itemName String
	 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ �ֵ�����
	 * @param itemCategory String
	 */
	public void setItemCategory(String itemCategory){
		this.itemCategory = itemCategory;
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ ������
	 * @param createUser AdvancedNumber
	 */
	public void setCreateUser(int createUser){
		this.createUser = createUser;
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����Ӧ�������豸�����ֵ������ �ϴ��޸���
	 * @param lastUpdateUser AdvancedNumber
	 */
	public void setLastUpdateUser(int lastUpdateUser){
		this.lastUpdateUser = lastUpdateUser;
	}


	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ ���
	 * @return AdvancedNumber
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ װ������
	 * @return String
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ �ֵ�����
	 * @return String
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ ������
	 * @return AdvancedNumber
	 */
	public int getCreateUser() {
		return this.createUser;
	}

	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡӦ�������豸�����ֵ������ �ϴ��޸���
	 * @return AdvancedNumber
	 */
	public int getLastUpdateUser() {
		return this.lastUpdateUser;
	}

}