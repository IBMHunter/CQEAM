package com.sino.ams.net.locus.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.math.AdvancedNumber;

/**
* <p>Title: LOCUS Locus</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class LocusDTO extends CheckBoxDTO{

	private AdvancedNumber workorderObjectNo = null;
	private String workorderObjectCode = "";
	private String workorderObjectName = "";
	private String workorderObjectLocation = "";
	private AdvancedNumber organizationId = null;
	private AdvancedNumber countyCode = null;
	private SimpleCalendar disableDate = null;
	private String remark = "";
	private String objectCategory = "";
	private String category = "";
	private AdvancedNumber isall = null;
	private AdvancedNumber isTempAddr = null;
	private SimpleCalendar creationDate = null;
	private AdvancedNumber createdBy = null;
	private SimpleCalendar lastUpdateDate = null;
	private AdvancedNumber lastUpdateBy = null;
	private AdvancedNumber projectId = null;

	public LocusDTO() {
		super();
		this.disableDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();

		this.workorderObjectNo = new AdvancedNumber();
		this.organizationId = new AdvancedNumber();
		this.countyCode = new AdvancedNumber();

		this.isall = new AdvancedNumber();
		this.isTempAddr = new AdvancedNumber();
		this.createdBy = new AdvancedNumber();
		this.lastUpdateBy = new AdvancedNumber();
		this.projectId = new AdvancedNumber();
	}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
	 * ���ܣ�����LOCUS���� WORKORDER_OBJECT_NO
	 * @param workorderObjectNo AdvancedNumber
	 */
	public void setWorkorderObjectNo(AdvancedNumber workorderObjectNo){
		this.workorderObjectNo = workorderObjectNo;
	}

	/**
	 * ���ܣ�����LOCUS���� WORKORDER_OBJECT_CODE
	 * @param workorderObjectCode String
	 */
	public void setWorkorderObjectCode(String workorderObjectCode){
		this.workorderObjectCode = workorderObjectCode;
	}

	/**
	 * ���ܣ�����LOCUS���� WORKORDER_OBJECT_NAME
	 * @param workorderObjectName String
	 */
	public void setWorkorderObjectName(String workorderObjectName){
		this.workorderObjectName = workorderObjectName;
	}

	/**
	 * ���ܣ�����LOCUS���� WORKORDER_OBJECT_LOCATION
	 * @param workorderObjectLocation String
	 */
	public void setWorkorderObjectLocation(String workorderObjectLocation){
		this.workorderObjectLocation = workorderObjectLocation;
	}

	/**
	 * ���ܣ�����LOCUS���� ORGANIZATION_ID
	 * @param organizationId AdvancedNumber
	 */
	public void setOrganizationId(AdvancedNumber organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����LOCUS���� COUNTY_CODE
	 * @param countyCode AdvancedNumber
	 */
	public void setCountyCode(AdvancedNumber countyCode){
		this.countyCode = countyCode;
	}

	/**
	 * ���ܣ�����LOCUS���� DISABLE_DATE
	 * @param disableDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDisableDate(String disableDate) throws CalendarException{
		this.disableDate.setCalendarValue(disableDate);
	}

	/**
	 * ���ܣ�����LOCUS���� REMARK
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����LOCUS���� OBJECT_CATEGORY
	 * @param objectCategory AdvancedNumber
	 */
	public void setObjectCategory(String objectCategory){
		this.objectCategory = objectCategory;
	}

	/**
	 * ���ܣ�����LOCUS���� ISALL
	 * @param isall AdvancedNumber
	 */
	public void setIsall(AdvancedNumber isall){
		this.isall = isall;
	}

	/**
	 * ���ܣ�����LOCUS���� IS_TEMP_ADDR
	 * @param isTempAddr AdvancedNumber
	 */
	public void setIsTempAddr(AdvancedNumber isTempAddr){
		this.isTempAddr = isTempAddr;
	}

	/**
	 * ���ܣ�����LOCUS���� CREATION_DATE
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����LOCUS���� CREATED_BY
	 * @param createdBy AdvancedNumber
	 */
	public void setCreatedBy(AdvancedNumber createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����LOCUS���� LAST_UPDATE_DATE
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����LOCUS���� LAST_UPDATE_BY
	 * @param lastUpdateBy AdvancedNumber
	 */
	public void setLastUpdateBy(AdvancedNumber lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ�����LOCUS���� PROJECT_ID
	 * @param projectId AdvancedNumber
	 */
	public void setProjectId(AdvancedNumber projectId){
		this.projectId = projectId;
	}


	/**
	 * ���ܣ���ȡLOCUS���� WORKORDER_OBJECT_NO
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getWorkorderObjectNo() {
		return this.workorderObjectNo;
	}

	/**
	 * ���ܣ���ȡLOCUS���� WORKORDER_OBJECT_CODE
	 * @return String
	 */
	public String getWorkorderObjectCode() {
		return this.workorderObjectCode;
	}

	/**
	 * ���ܣ���ȡLOCUS���� WORKORDER_OBJECT_NAME
	 * @return String
	 */
	public String getWorkorderObjectName() {
		return this.workorderObjectName;
	}

	/**
	 * ���ܣ���ȡLOCUS���� WORKORDER_OBJECT_LOCATION
	 * @return String
	 */
	public String getWorkorderObjectLocation() {
		return this.workorderObjectLocation;
	}

	/**
	 * ���ܣ���ȡLOCUS���� ORGANIZATION_ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡLOCUS���� COUNTY_CODE
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getCountyCode() {
		return this.countyCode;
	}

	/**
	 * ���ܣ���ȡLOCUS���� DISABLE_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDisableDate() throws CalendarException {
		disableDate.setCalPattern(getCalPattern());
		return this.disableDate;
	}

	/**
	 * ���ܣ���ȡLOCUS���� REMARK
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡLOCUS���� OBJECT_CATEGORY
	 * @return AdvancedNumber
	 */
	public String getObjectCategory() {
		return this.objectCategory;
	}

	/**
	 * ���ܣ���ȡLOCUS���� ISALL
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getIsall() {
		return this.isall;
	}

	/**
	 * ���ܣ���ȡLOCUS���� IS_TEMP_ADDR
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getIsTempAddr() {
		return this.isTempAddr;
	}

	/**
	 * ���ܣ���ȡLOCUS���� CREATION_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡLOCUS���� CREATED_BY
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡLOCUS���� LAST_UPDATE_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡLOCUS���� LAST_UPDATE_BY
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡLOCUS���� PROJECT_ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getProjectId() {
		return this.projectId;
	}

}