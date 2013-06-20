package com.sino.sinoflow.user.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �������� SfProject</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfProjectDTO extends CheckBoxDTO{

	private int projectId = 0;
	private String projectName = "";
	private String createdBy = "";
	private String creationDate = "";
	private String lastUpdatedBy = "";
	private String lastUpdateDate = "";
	private String enabled = "";
	private String effectiveDate = "";
	private String version = "";
	private String description = "";
    private String filename = "";

    public SfProjectDTO() {
		super();
	}

	/**
	 * ���ܣ����ù����������� ���� ID
	 * @param projectId String
	 */
	public void setProjectId(int projectId){
		this.projectId = projectId;
	}

	/**
	 * ���ܣ����ù����������� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ����ù����������� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

//	/**
//	 * ���ܣ����ù����������� ��������
//	 * @param creationDate String
//	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
//	 */
//	public void setCreationDate(String creationDate) throws CalendarException{
//		this.creationDate.setCalendarValue(creationDate);
//	}
//
//	/**
//	 * ���ܣ����ù����������� �޸���
//	 * @param lastUpdatedBy String
//	 */
//	public void setLastUpdatedBy(String lastUpdatedBy){
//		this.lastUpdatedBy = lastUpdatedBy;
//	}
//
//	/**
//	 * ���ܣ����ù����������� �޸�����
//	 * @param lastUpdateDate String
//	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
//	 */
//	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
//		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
//	}

	/**
	 * ���ܣ����ù����������� ��Ч
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

//	/**
//	 * ���ܣ����ù����������� ��Ч����
//	 * @param effectiveDate String
//	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
//	 */
//	public void setEffectiveDate(String effectiveDate) throws CalendarException{
//		this.effectiveDate.setCalendarValue(effectiveDate);
//	}

	/**
	 * ���ܣ����ù����������� �汾��
	 * @param version String
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * ���ܣ����ù����������� ����
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

    /**
     * ���ܣ����ù����������� �ļ���
     * @param filename String
     */
    public void setFilename(String filename){
        this.filename = filename;
    }

    /**
	 * ���ܣ���ȡ������������ ���� ID
	 * @return String
	 */
	public int getProjectId() {
		return this.projectId;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡ������������ ������
	 * @return String
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

//	/**
//	 * ���ܣ���ȡ������������ ��������
//	 * @return String
//	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
//	 */
//	public String getCreationDate() throws CalendarException {
//		creationDate.setCalPattern(getCalPattern());
//		return this.creationDate;
//	}

	/**
	 * ���ܣ���ȡ������������ �޸���
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

//	/**
//	 * ���ܣ���ȡ������������ �޸�����
//	 * @return String
//	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
//	 */
//	public String getLastUpdateDate() throws CalendarException {
//		lastUpdateDate.setCalPattern(getCalPattern());
//		return this.lastUpdateDate;
//	}

	/**
	 * ���ܣ���ȡ������������ ��Ч
	 * @return String
	 */
	public String getEnabled() {
		return this.enabled;
	}
//
//	/**
//	 * ���ܣ���ȡ������������ ��Ч����
//	 * @return String
//	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
//	 */
//	public String getEffectiveDate() throws CalendarException {
//		effectiveDate.setCalPattern(getCalPattern());
//		return this.effectiveDate;
//	}

	/**
	 * ���ܣ���ȡ������������ �汾��
	 * @return String
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * ���ܣ���ȡ������������ ����
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

    /**
     * ���ܣ���ȡ������������ �ļ���
     * @return String
     */
    public String getFilename() {
        return this.filename;
    }


    public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}