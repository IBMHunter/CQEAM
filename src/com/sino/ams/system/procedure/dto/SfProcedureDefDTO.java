package com.sino.ams.system.procedure.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ��ת���̶��� SfProcedureDef</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfProcedureDefDTO extends CheckBoxDTO{

	private int procId ;
	private String procName = "";
	private String description = "";
	private String appTableName = "";
	private String appPkName = "";
	private int organizationId;
	private String disabled = "";
	private String approvePath = "";
	private String editPath = "";
	private String queryPath = "";
	private String cancelPath = "";


	/**
	 * ���ܣ�������ת���̶������� �������к�
	 * @param procId String
	 */
	public void setProcId(int procId){
		this.procId = procId;
	}

	/**
	 * ���ܣ�������ת���̶������� ��������
	 * @param procName String
	 */
	public void setProcName(String procName){
		this.procName = procName;
	}

	/**
	 * ���ܣ�������ת���̶������� ��������
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ�������ת���̶������� Ӧ�ñ�����
	 * @param appTableName String
	 */
	public void setAppTableName(String appTableName){
		this.appTableName = appTableName;
	}

	/**
	 * ���ܣ�������ת���̶������� Ӧ�ñ�����
	 * @param appPkName String
	 */
	public void setAppPkName(String appPkName){
		this.appPkName = appPkName;
	}

	/**
	 * ���ܣ�������ת���̶������� OU��֯���
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�������ת���̶������� �Ƿ�ʧЧ��Ĭ��Ϊ��'N'
	 * @param disabled String
	 */
	public void setDisabled(String disabled){
		this.disabled = disabled;
	}

	/**
	 * ���ܣ�������ת���̶������� ����Ӧ�õ�·��
	 * @param approvePath String
	 */
	public void setApprovePath(String approvePath){
		this.approvePath = approvePath;
	}

	/**
	 * ���ܣ�������ת���̶������� �༭Ӧ�õ�·��
	 * @param editPath String
	 */
	public void setEditPath(String editPath){
		this.editPath = editPath;
	}

	/**
	 * ���ܣ�������ת���̶������� ��ѯӦ��·��
	 * @param queryPath String
	 */
	public void setQueryPath(String queryPath){
		this.queryPath = queryPath;
	}

	/**
	 * ���ܣ�������ת���̶������� ȡ��Ӧ��·��
	 * @param cancelPath String
	 */
	public void setCancelPath(String cancelPath){
		this.cancelPath = cancelPath;
	}


	/**
	 * ���ܣ���ȡ��ת���̶������� �������к�
	 * @return String
	 */
	public int getProcId(){
		return this.procId;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� ��������
	 * @return String
	 */
	public String getProcName(){
		return this.procName;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� ��������
	 * @return String
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� Ӧ�ñ�����
	 * @return String
	 */
	public String getAppTableName(){
		return this.appTableName;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� Ӧ�ñ�����
	 * @return String
	 */
	public String getAppPkName(){
		return this.appPkName;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� OU��֯���
	 * @return String
	 */
	public int getOrganizationId(){
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� �Ƿ�ʧЧ��Ĭ��Ϊ��'N'
	 * @return String
	 */
	public String getDisabled(){
		return this.disabled;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� ����Ӧ�õ�·��
	 * @return String
	 */
	public String getApprovePath(){
		return this.approvePath;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� �༭Ӧ�õ�·��
	 * @return String
	 */
	public String getEditPath(){
		return this.editPath;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� ��ѯӦ��·��
	 * @return String
	 */
	public String getQueryPath(){
		return this.queryPath;
	}

	/**
	 * ���ܣ���ȡ��ת���̶������� ȡ��Ӧ��·��
	 * @return String
	 */
	public String getCancelPath(){
		return this.cancelPath;
	}

}