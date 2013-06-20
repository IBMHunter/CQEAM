package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ������ SfFlow</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfFlowDTO extends CheckBoxDTO{

	private int procedureId = 0;
	private int flowId = 0;
	private String flowDesc = "";
	private int fromTaskId = 0;
	private int toTaskId = 0;
	private String flowCode = "";
    private int flowProp = 0;
    private int flowType = 0;
	private String hint = "";

	public SfFlowDTO() {
		super();

	}

	/**
	 * ���ܣ��������������� ���� ID
	 * @param procedureId String
	 */
	public void setProcedureId(int procedureId){
		this.procedureId = procedureId;
	}

	/**
	 * ���ܣ��������������� �� ID
	 * @param flowId String
	 */
	public void setFlowId(int flowId){
		this.flowId = flowId;
	}

	/**
	 * ���ܣ��������������� ������
	 * @param flowDesc String
	 */
	public void setFlowDesc(String flowDesc){
		this.flowDesc = flowDesc;
	}

	/**
	 * ���ܣ��������������� ��������
	 * @param fromTaskId String
	 */
	public void setFromTaskId(int fromTaskId){
		this.fromTaskId = fromTaskId;
	}

	/**
	 * ���ܣ��������������� ��������
	 * @param toTaskId String
	 */
	public void setToTaskId(int toTaskId){
		this.toTaskId = toTaskId;
	}

	/**
	 * ���ܣ��������������� ����������
	 * @param flowCode String
	 */
	public void setFlowCode(String flowCode){
		this.flowCode = flowCode;
	}

    /**
     * ���ܣ��������������� ������
     * @param flowProp String
     */
    public void setFlowProp(int flowProp){
        this.flowProp = flowProp;
    }

    /**
	 * ���ܣ��������������� ������
	 * @param flowType String
	 */
	public void setFlowType(int flowType){
		this.flowType = flowType;
	}

	/**
	 * ���ܣ��������������� ������ʾ
	 * @param hint String
	 */
	public void setHint(String hint){
		this.hint = hint;
	}


	/**
	 * ���ܣ���ȡ���������� ���� ID
	 * @return String
	 */
	public int getProcedureId() {
		return this.procedureId;
	}

	/**
	 * ���ܣ���ȡ���������� �� ID
	 * @return String
	 */
	public int getFlowId() {
		return this.flowId;
	}

	/**
	 * ���ܣ���ȡ���������� ������
	 * @return String
	 */
	public String getFlowDesc() {
		return this.flowDesc;
	}

	/**
	 * ���ܣ���ȡ���������� ��������
	 * @return String
	 */
	public int getFromTaskId() {
		return this.fromTaskId;
	}

	/**
	 * ���ܣ���ȡ���������� ��������
	 * @return String
	 */
	public int getToTaskId() {
		return this.toTaskId;
	}

	/**
	 * ���ܣ���ȡ���������� ����������
	 * @return String
	 */
	public String getFlowCode() {
		return this.flowCode;
	}

    /**
     * ���ܣ���ȡ���������� ������
     * @return String
     */
    public int getFlowProp() {
        return this.flowProp;
    }

	/**
	 * ���ܣ���ȡ���������� ������
	 * @return String
	 */
	public int getFlowType() {
		return this.flowType;
	}

	/**
	 * ���ܣ���ȡ���������� ������ʾ
	 * @return String
	 */
	public String getHint() {
		return this.hint;
	}

}