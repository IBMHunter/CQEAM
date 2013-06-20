package com.sino.nm.ams.inv.storeman.dto;

import com.sino.ams.system.basepoint.dto.EtsObjectDTO;


/**
 * <p>Title: Ĭ�ϲֹ�Աά�� EamInvStoreman</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ��ʿ��
 * @version 1.0
 */
public class EamInvStoremanDTO extends EtsObjectDTO {

	private String storemanId = ""; //Sequence����ID
	private int userId; //�ֹ�ԱID��sf_user.user_id��
	private String userName = ""; //SF_USER���е�USERNAME����
	private String createdUser = ""; //SF_USER���е�USERNAME���ԣ�Ϊ��ʶ�𴴽�������
	private String updatedUser = ""; //SF_USER���е�USERNAME���ԣ�Ϊ��ʶ���޸�������
	private String invCategoryName = ""; //��Ӧ�ֿ������ֶ�
	private String bizCategoryName = ""; //��Ӧҵ�������ֶ�
	private String createdById = ""; //EAM_INV_STOREMAN���е�CREATED_BY����ֶεĶ�Ӧ�ֶ�
	private String lastUpdateById = ""; //EAM_INV_STOREMAN���е�LAST_UPDATE_BY����ֶζ�Ӧ���ֶ�
	private String ouOption = "";//��˾��Ϣ�����������б�
	private String invCategoryOpt = "";//�ֿ����������������б�
	private String bizCategoryOpt = "";//ҵ�����������������б�
	
	//------------------------------------����ΪAMS_INV_PRICI���е��ֶ�------------------------------------//
	private String invCode = ""; //�ֿ�ID
	private String actionCode = ""; //�������ͣ������ֵ�INV_PRIVI(���Ȩ��)
	private String priviId = ""; //���������к�
	
	
	private String codeExist = ""; //���ֿ�����Ƿ��Ѿ�����
	
	private String workorderObjectNo1 = ""; 

	public String getCodeExist() {
		return codeExist;
	}

	public void setCodeExist(String codeExist) {
		this.codeExist = codeExist;
	}

	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getPriviId() {
		return priviId;
	}

	public void setPriviId(String priviId) {
		this.priviId = priviId;
	}

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public String getLastUpdateById() {
		return lastUpdateById;
	}

	public void setLastUpdateById(String lastUpdateById) {
		this.lastUpdateById = lastUpdateById;
	}

	public String getInvCategoryName() {
		return invCategoryName;
	}

	public void setInvCategoryName(String invCategoryName) {
		this.invCategoryName = invCategoryName;
	}

	public String getBizCategoryName() {
		return bizCategoryName;
	}

	public void setBizCategoryName(String bizCategoryName) {
		this.bizCategoryName = bizCategoryName;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStoremanId() {
		return storemanId;
	}

	public void setStoremanId(String storemanId) {
		this.storemanId = storemanId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getOuOption() {
		return ouOption;
	}

	public String getBizCategoryOpt() {
		return bizCategoryOpt;
	}

	public String getInvCategoryOpt() {
		return invCategoryOpt;
	}

	public void setOuOption(String ouOption) {
		this.ouOption = ouOption;
	}

	public void setBizCategoryOpt(String bizCategoryOpt) {
		this.bizCategoryOpt = bizCategoryOpt;
	}

	public void setInvCategoryOpt(String invCategoryOpt) {
		this.invCategoryOpt = invCategoryOpt;
	}

	public String getWorkorderObjectNo1() {
		return workorderObjectNo1;
	}

	public void setWorkorderObjectNo1(String workorderObjectNo1) {
		this.workorderObjectNo1 = workorderObjectNo1;
	}
}
