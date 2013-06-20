package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �Ϸ��Լ����Ϣ SfValidation</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfValidationDTO extends CheckBoxDTO{

	private int validateId = 0;
	private int projectId = 0;
	private int procedureId = 0;
	private int taskId = 0;
	private String projectName = "";
	private String procedureName = "";
	private String taskName = "";
	private String fieldName = "";
	private String fieldDesc = "";
	private int validationType = 0;
	private int sizeType = 0;
	private int checkSize = 0;
	private String matchPattern = "";
	private String memo = "";
	private boolean empty = false;
    private int taskTid = 0;

    public boolean getEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public SfValidationDTO() {
		super();
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� �Ϸ��Լ�� ID
	 * @param validateId String
	 */
	public void setValidateId(int validateId){
		this.validateId = validateId;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ��������
	 * @param procedureName String
	 */
	public void setProcedureName(String procedureName){
		this.procedureName = procedureName;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ��������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

    public void setTaskTid(int taskTid) {
        this.taskTid = taskTid;
    }

    /**
	 * ���ܣ����úϷ��Լ����Ϣ���� ������
	 * @param fieldName String
	 */
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ������
	 * @param fieldDesc String
	 */
	public void setFieldDesc(String fieldDesc){
		this.fieldDesc = fieldDesc;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ��鷽ʽ, BIT 0 - NOT NULL, BIT 1 - PATTERN MATCH, BIT 2 - SIZE CHECK
	 * @param validationType String
	 */
	public void setValidationType(int validationType){
		this.validationType = validationType;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ���ȿ��Ʒ�ʽ, 1:EQ 2:LT 3:GT
	 * @param sizeType String
	 */
	public void setSizeType(int sizeType){
		this.sizeType = sizeType;
	}


	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ƥ����ʽ, ������ͨ��� ��*��, ��?��,�硱2008-*-???��
	 * @param matchPattern String
	 */
	public void setMatchPattern(String matchPattern){
		this.matchPattern = matchPattern;
	}

	/**
	 * ���ܣ����úϷ��Լ����Ϣ���� ��ע
	 * @param memo String
	 */
	public void setMemo(String memo){
		this.memo = memo;
	}


	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� �Ϸ��Լ�� ID
	 * @return String
	 */
	public int getValidateId() {
		return this.validateId;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ��������
	 * @return String
	 */
	public String getProcedureName() {
		return this.procedureName;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ��������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

    public int getTaskTid() {
        return this.taskTid;
    }

    /**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ������
	 * @return String
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ������
	 * @return String
	 */
	public String getFieldDesc() {
		return this.fieldDesc;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ��鷽ʽ, BIT 0 - NOT NULL, BIT 1 - PATTERN MATCH, BIT 2 - SIZE CHECK
	 * @return String
	 */
	public int getValidationType() {
		return this.validationType;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ���ȿ��Ʒ�ʽ, 1:EQ 2:LT 3:GT
	 * @return String
	 */
	public int getSizeType() {
		return this.sizeType;
	}


	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ƥ����ʽ, ������ͨ��� ��*��, ��?��,�硱2008-*-???��
	 * @return String
	 */
	public String getMatchPattern() {
		return this.matchPattern;
	}

	/**
	 * ���ܣ���ȡ�Ϸ��Լ����Ϣ���� ��ע
	 * @return String
	 */
	public String getMemo() {
		return this.memo;
	}

	public int getCheckSize() {
		return checkSize;
	}

	public void setCheckSize(int checkSize) {
		this.checkSize = checkSize;
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