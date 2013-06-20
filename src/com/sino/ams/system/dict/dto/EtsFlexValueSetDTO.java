package com.sino.ams.system.dict.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: �ֵ�����(AMS) EtsFlexValueSet</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsFlexValueSetDTO extends CheckBoxDTO{

	private String flexValueSetId = "";
	private String code = "";
	private String name = "";
	private String description = "";
	private String isInner = "Y";
	private String maintainFlag = "Y";
	private SimpleCalendar creationDate = null;
	private int createdBy = 0;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy = 0;
	private String enabled = "Y";
	private String remark = "";

	/**
	 * ���ܣ������ֵ�����(AMS)���� ϵͳID
	 * @param flexValueSetId String
	 */
	public void setFlexValueSetId(String flexValueSetId){
		this.flexValueSetId = flexValueSetId;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �������
	 * @param code String
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ��������
	 * @param name String
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ��������
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �Ƿ����÷��࣬���÷��಻����ά��
	 * @param isInner String
	 */
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ά�����أ�'Y'��ʾ��ά����'N'��ʾ����ά��
	 * @param maintainFlag String
	 */
	public void setMaintainFlag(String maintainFlag){
		this.maintainFlag = maintainFlag;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ��������
	 * @param creationDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			this.creationDate = new SimpleCalendar(creationDate);
		}
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �ϴ��޸�����
	 * @param lastUpdateDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
		}
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ϵͳID
	 * @return String
	 */
	public String getFlexValueSetId(){
		return this.flexValueSetId;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �������
	 * @return String
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ��������
	 * @return String
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ��������
	 * @return String
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �Ƿ����÷��࣬���÷��಻����ά��
	 * @return String
	 */
	public String getIsInner(){
		return this.isInner;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ά�����أ�'Y'��ʾ��ά����'N'��ʾ����ά��
	 * @return String
	 */
	public String getMaintainFlag(){
		return this.maintainFlag;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ��������
	 * @return Timestamp
	 */
	public SimpleCalendar getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �ϴ��޸�����
	 * @return Timestamp
	 */
	public SimpleCalendar getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getEnabled() {
		return enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
