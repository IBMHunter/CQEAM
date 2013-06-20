package com.sino.ams.net.equip.dto;

import com.sino.base.dto.CalendarUtililyDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Owner
 * Date: 2008-1-28
 * Time: 11:45:55
 * To change this template use File | Settings | File Templates.
 */
public class IntegratedDTO extends  CalendarUtililyDTO {


    private int userId;
	private String fieldName = "";
	private String fieldDesc = "";
	private String fieldUsage = "";
	private int sortNo;
	
    /**
     * ���ܣ������ʲ��ۺϲ�ѯ�������� �û�ID
     * @param userId String
     */
    public void setUserId(int userId){
        this.userId = userId;
    }

	/**
	 * ���ܣ������ʲ��ۺϲ�ѯ�������� �ֶ�����
	 * @param fieldName String
	 */
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}

	/**
	 * ���ܣ������ʲ��ۺϲ�ѯ�������� �ֶ���;
	 * @param fieldUsage String
	 */
	public void setFieldUsage(String fieldUsage){
		this.fieldUsage = fieldUsage;
	}

	/**
	 * ���ܣ������ʲ��ۺϲ�ѯ�������� �����
	 * @param sortNo String
	 */
	public void setSortNo(int sortNo){
		this.sortNo = sortNo;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ۺϲ�ѯ�������� �û�ID
	 * @return String
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ۺϲ�ѯ�������� �ֶ�����
	 * @return String
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ۺϲ�ѯ�������� �ֶ���;
	 * @return String
	 */
	public String getFieldUsage() {
		return this.fieldUsage;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ۺϲ�ѯ�������� �����
	 * @return String
	 */
	public int getSortNo() {
		return this.sortNo;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}
}
