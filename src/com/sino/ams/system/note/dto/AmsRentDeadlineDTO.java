package com.sino.ams.system.note.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��������(EAM) AmsRentDeadline</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsRentDeadlineDTO extends CheckBoxDTO{

	private String deadlineId = "";
	private String barcode = "";
	private int noticeBefore;
	private int organizationId;
	private SimpleCalendar ctreationDate = null;
	private int createdBy;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy;
    private SimpleCalendar endDate = null;
    private String itemName = "";
	private String itemSpec = "";
	private String itemCategory = "";
    private String rentPerson ="";

    public String getRentPerson() {
        return rentPerson;
    }

    public void setRentPerson(String rentPerson) {
        this.rentPerson = rentPerson;
    }

    public AmsRentDeadlineDTO() {
		super();
		this.ctreationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }


    /**
	 * ���ܣ�������������(EAM)���� ���к�
	 * @param deadlineId String
	 */
	public void setDeadlineId(String deadlineId){
		this.deadlineId = deadlineId;
	}

	/**
	 * ���ܣ�������������(EAM)���� �����ʲ�����
	 * @param barcode String
	 */
	public void setBarcodeNo(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ�������������(EAM)���� ��ǰ֪ͨ����
	 * @param noticeBefore String
	 */
	public void setNoticeBefore(int noticeBefore){
		this.noticeBefore = noticeBefore;
	}

	/**
	 * ���ܣ�������������(EAM)���� OU��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�������������(EAM)���� ��������
	 * @param ctreationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCtreationDate(String ctreationDate) throws CalendarException{
		this.ctreationDate.setCalendarValue(ctreationDate);
	}

	/**
	 * ���ܣ�������������(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�������������(EAM)���� �ϴθ�������
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�������������(EAM)���� �ϴθ�����
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ��������(EAM)���� ���к�
	 * @return String
	 */
	public String getDeadlineId() {
		return this.deadlineId;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �����ʲ�����
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� ��ǰ֪ͨ����
	 * @return String
	 */
	public int getNoticeBefore() {
		return this.noticeBefore;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� OU��֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCtreationDate() throws CalendarException {
		if(ctreationDate != null){
			ctreationDate.setCalPattern(getCalPattern());
		}
		return this.ctreationDate;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� ������
	 * @return String
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴθ�������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		if(lastUpdateDate != null){
			lastUpdateDate.setCalPattern(getCalPattern());
		}
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴθ�����
	 * @return String
	 */
	public int getLastUpdateBy() {
		return this.lastUpdateBy;
	}


   	/**
	 * ���ܣ��������� ��������
	 * @param endDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setEndDate(String endDate) throws CalendarException{
		this.endDate.setCalendarValue(endDate);
	}

   /**
	 * ���ܣ���ȡ���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException
	 */
	public SimpleCalendar getEndDate() throws CalendarException {
		if (endDate != null) {
			endDate.setCalPattern(getCalPattern());
		}
		return this.endDate;
	}
}
