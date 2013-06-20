package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �������� SfProcedure</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfProcedureDTO extends CheckBoxDTO{

	private int projectId = 0;
	private int procedureId = 0;
	private String procedureName = "";
	private int trayType = 0;
	private int duration = 0;
	private String createdBy = "";
	private String creationDate = "";
	private String lastUpdatedBy = "";
	private String lastUpdateDate = "";
	private String description = "";
	private String memo = "";
	private int mainProcedure = 0;
    private String defaultUrl = "";

    public SfProcedureDTO() {
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
	 * ���ܣ����ù����������� ���� ID
	 * @param procedureId String
	 */
	public void setProcedureId(int procedureId){
		this.procedureId = procedureId;
	}

	/**
	 * ���ܣ����ù����������� ��������
	 * @param procedureName String
	 */
	public void setProcedureName(String procedureName){
		this.procedureName = procedureName;
	}

	/**
	 * ���ܣ����ù����������� ����������
	 * @param trayType String
	 */
	public void setTrayType(int trayType){
		this.trayType = trayType;
	}

	/**
	 * ���ܣ����ù����������� �����ܺ�
	 * @param duration String
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}

	/**
	 * ���ܣ����ù����������� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����ù����������� �޸���
	 * @param lastUpdatedBy String
	 */
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * ���ܣ����ù����������� ��������
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ����ù����������� ��ע
	 * @param memo String
	 */
	public void setMemo(String memo){
		this.memo = memo;
	}

	/**
	 * ���ܣ����ù����������� �Ƿ�������, 1:�� 0:��
	 * @param mainProcedure String
	 */
	public void setMainProcedure(int mainProcedure){
		this.mainProcedure = mainProcedure;
	}

    /**
     * ���ܣ����ù����������� �Ƿ�������, 1:�� 0:��
     * @param defaultUrl String
     */
    public void setDefaultUrl(String defaultUrl){
        this.defaultUrl = defaultUrl;
    }

	/**
	 * ���ܣ���ȡ������������ ���� ID
	 * @return String
	 */
	public int getProjectId() {
		return this.projectId;
	}

	/**
	 * ���ܣ���ȡ������������ ���� ID
	 * @return String
	 */
	public int getProcedureId() {
		return this.procedureId;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getProcedureName() {
		return this.procedureName;
	}

	/**
	 * ���ܣ���ȡ������������ ����������
	 * @return String
	 */
	public int getTrayType() {
		return this.trayType;
	}

	/**
	 * ���ܣ���ȡ������������ �����ܺ�
	 * @return String
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * ���ܣ���ȡ������������ ������
	 * @return String
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}


	/**
	 * ���ܣ���ȡ������������ �޸���
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ���ܣ���ȡ������������ ��ע
	 * @return String
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * ���ܣ���ȡ������������ �Ƿ�������, 1:�� 0:��
	 * @return String
	 */
	public int getMainProcedure() {
		return this.mainProcedure;
	}

    /**
     * ���ܣ���ȡ������������ �Ƿ�������, 1:�� 0:��
     * @return defaultUrl String
     */
    public String getDefaultUrl() {
        return this.defaultUrl;
    }

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}