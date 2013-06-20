package com.sino.ams.system.dict.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: �ֵ�����(AMS) EtsFlexValues</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsFlexValuesDTO extends CheckBoxDTO{

	private String flexValueId = "";
	private String flexValueSetId = "";
	private String flexValueSetName = "";
	private String code = "";
	private String value = "";
	private String description = "";
	private String enabled = "Y";
	private String attribute1 = "";
	private String attribute2 = "";
	private String isInner = "Y";
	private Timestamp creationDate = null;
	private int createdBy ;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy ;
	private String fileVersion = "";
    private String attribute3 = "";
	private String attribute4 = "";
	private String attribute5 = "";
	private String attribute6 = "";
	private String remark = "";
	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

//	public void setCreationDate(Timestamp creationDate) {
//		this.creationDate = creationDate;
//	}
//
//	public void setLastUpdateDate(Timestamp lastUpdateDate) {
//		this.lastUpdateDate = lastUpdateDate;
//	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ϵͳID
	 * @param flexValueId String
	 */
	public void setFlexValueId(String flexValueId){
		this.flexValueId = flexValueId;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ����ID
	 * @param flexValueSetId String
	 */
	public void setFlexValueSetId(String flexValueSetId){
		this.flexValueSetId = flexValueSetId;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ����
	 * @param code String
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ֵ
	 * @param value String
	 */
	public void setValue(String value){
		this.value = value;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ����
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �Ƿ���Ч
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� null
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� null
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �Ƿ������ֵ䣬�����ֵ䲻�����޸�
	 * @param isInner String
	 */
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� ��������
	 * @param creationDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			SimpleCalendar cal = new SimpleCalendar(creationDate);
			this.creationDate = cal.getSQLTimestamp();
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
			SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
			this.lastUpdateDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ������ֵ�����(AMS)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setFlexValueSetName(String flexValueSetName) {
		this.flexValueSetName = flexValueSetName;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ϵͳID
	 * @return String
	 */
	public String getFlexValueId(){
		return this.flexValueId;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ����ID
	 * @return String
	 */
	public String getFlexValueSetId(){
		return this.flexValueSetId;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ����
	 * @return String
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ֵ
	 * @return String
	 */
	public String getValue(){
		return this.value;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ����
	 * @return String
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �Ƿ���Ч
	 * @return String
	 */
	public String getEnabled(){
		return this.enabled;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� null
	 * @return String
	 */
	public String getAttribute1(){
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� null
	 * @return String
	 */
	public String getAttribute2(){
		return this.attribute2;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �Ƿ������ֵ䣬�����ֵ䲻�����޸�
	 * @return String
	 */
	public String getIsInner(){
		return this.isInner;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
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
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�ֵ�����(AMS)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getFlexValueSetName() {
		return flexValueSetName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

//	public String getLastUpdateDate() {
//		return lastUpdateDate;
//	}
//
//	public void setLastUpdateDate(String lastUpdateDate) {
//		this.lastUpdateDate = lastUpdateDate;
//	}

}
