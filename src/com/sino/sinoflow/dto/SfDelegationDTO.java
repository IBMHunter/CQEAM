package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ί�ɶ��� SfDelegation</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfDelegationDTO extends CheckBoxDTO{

	private int delegationId = 0;
	private int userId = 0;
	private int delegateTo = 0;
	private int statusCtl = 0;
	private String startDate = "";
	private String endDate = "";
	private String sName = "";
	private String eName = "";

	public String getSName() {
		return sName;
	}

	public void setSName(String name) {
		sName = name;
	}

	public String getEName() {
		return eName;
	}

	public void setEName(String name) {
		eName = name;
	}

	public SfDelegationDTO() {
		super();
	}

	/**
	 * ���ܣ�����ί�ɶ������� ID
	 * @param delegationId String
	 */
	public void setDelegationId(int delegationId){
		this.delegationId = delegationId;
	}

	/**
	 * ���ܣ�����ί�ɶ������� USER_ID
	 * @param userId String
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ�����ί�ɶ������� DELEGATE_TO
	 * @param delegateTo String
	 */
	public void setDelegateTo(int delegateTo){
		this.delegateTo = delegateTo;
	}

	/**
	 * ���ܣ�����ί�ɶ������� 0:δί�� 1:��ί��
	 * @param statusCtl String
	 */
	public void setStatusCtl(int statusCtl){
		this.statusCtl = statusCtl;
	}

	/**
	 * ���ܣ���ȡί�ɶ������� ID
	 * @return String
	 */
	public int getDelegationId() {
		return this.delegationId;
	}

	/**
	 * ���ܣ���ȡί�ɶ������� USER_ID
	 * @return String
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * ���ܣ���ȡί�ɶ������� DELEGATE_TO
	 * @return String
	 */
	public int getDelegateTo() {
		return this.delegateTo;
	}

	/**
	 * ���ܣ���ȡί�ɶ������� 0:δί�� 1:��ί��
	 * @return String
	 */
	public int getStatusCtl() {
		return this.statusCtl;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}