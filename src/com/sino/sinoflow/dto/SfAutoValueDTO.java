package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �Զ���ֵ��Ϣ SfAutoValue</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfAutoValueDTO extends CheckBoxDTO{

	private int autoValueId = 0;
	private String projectName = "";
	private String procedureName = "";
	private String taskName = "";
	private String fieldName = "";
	private String fieldDesc = "";
	private int assignOn = 0;
	private int assignType = 0;
	private int assignValue = 0;
	private String meno = "";
	private int projectId = 0;
	private int procedureId = 0;
	private int taskId= 0;
    private int taskTid = 0;

    /* ��ʱ��ֵΪ����ҳ������ʾ */
	private boolean qs = false;
	private boolean wc = false;
	private boolean zc = false;
	private boolean ts = false;
	private boolean th = false;

	public SfAutoValueDTO() {
		super();
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� AUTO_VALUE_ID
	 * @param autoValueId String
	 */
	public void setAutoValueId(int autoValueId){
		this.autoValueId = autoValueId;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��������
	 * @param procedureName String
	 */
	public void setProcedureName(String procedureName){
		this.procedureName = procedureName;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

    /**
     * ���ܣ������Զ���ֵ��Ϣ���� ��������
     * @param taskTid String
     */
    public void setTaskTid(int taskTid){
        this.taskTid = taskTid;
    }

    /**
	 * ���ܣ������Զ���ֵ��Ϣ���� ����
	 * @param fieldName String
	 */
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ������
	 * @param fieldDesc String
	 */
	public void setFieldDesc(String fieldDesc){
		this.fieldDesc = fieldDesc;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��ʱ��ֵ
	 * @param assignOn String
	 */
	public void setAssignOn(int assignOn){
		this.assignOn = assignOn;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��ֵ��ʽ
	 * @param assignType String
	 */
	public void setAssignType(int assignType){
		this.assignType = assignType;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��ֵ����
	 * @param assignValue String
	 */
	public void setAssignValue(int assignValue){
		this.assignValue = assignValue;
	}

	/**
	 * ���ܣ������Զ���ֵ��Ϣ���� ��ע
	 * @param meno String
	 */
	public void setMeno(String meno){
		this.meno = meno;
	}


	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� AUTO_VALUE_ID
	 * @return String
	 */
	public int getAutoValueId() {
		return this.autoValueId;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��������
	 * @return String
	 */
	public String getProcedureName() {
		return this.procedureName;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

    /**
     * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��������
     * @return String
     */
    public int getTaskTid() {
        return this.taskTid;
    }

    /**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ����
	 * @return String
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ������
	 * @return String
	 */
	public String getFieldDesc() {
		return this.fieldDesc;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��ʱ��ֵ
	 * @return String
	 */
	public int getAssignOn() {
		return this.assignOn;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��ֵ��ʽ
	 * @return String
	 */
	public int getAssignType() {
		return this.assignType;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��ֵ����
	 * @return String
	 */
	public int getAssignValue() {
		return this.assignValue;
	}

	/**
	 * ���ܣ���ȡ�Զ���ֵ��Ϣ���� ��ע
	 * @return String
	 */
	public String getMeno() {
		return this.meno;
	}

	public boolean isQs() {
		return qs;
	}

	public void setQs(boolean qs) {
		this.qs = qs;
	}

	public boolean isWc() {
		return wc;
	}

	public void setWc(boolean wc) {
		this.wc = wc;
	}

	public boolean isZc() {
		return zc;
	}

	public void setZc(boolean zc) {
		this.zc = zc;
	}

	public boolean isTs() {
		return ts;
	}

	public void setTs(boolean ts) {
		this.ts = ts;
	}

	public boolean isTh() {
		return th;
	}

	public void setTh(boolean th) {
		this.th = th;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(int procedureId) {
		this.procedureId = procedureId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


}