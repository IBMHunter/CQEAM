package com.sino.sinoflow.dto;

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
	private int createdBy = -1;
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
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����ù����������� ��������
	 * @param creationDate String
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * ���ܣ����ù����������� �޸���
	 * @param lastUpdatedBy String
	 */
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * ���ܣ����ù����������� �޸�����
	 * @param lastUpdateDate String
	 */
	public void setLastUpdateDate(String lastUpdateDate){
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * ���ܣ����ù����������� ��Ч
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

	/**
	 * ���ܣ����ù����������� ��Ч����
	 * @param effectiveDate String
	 */
	public void setEffectiveDate(String effectiveDate){
		this.effectiveDate = effectiveDate;
	}

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
     * ���ܣ����ù����������� ���ļ���
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
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getCreationDate() {
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ������������ �޸���
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	/**
	 * ���ܣ���ȡ������������ �޸�����
	 * @return String
	 */
	public String getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ������������ ��Ч
	 * @return String
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 * ���ܣ���ȡ������������ ��Ч����
	 * @return String
	 */
	public String getEffectiveDate() {
		return this.effectiveDate;
	}

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
     * ���ܣ���ȡ������������ ����
     * @return String
     */
    public String getFilename() {
        return this.filename;
    }
}