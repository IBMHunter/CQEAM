package com.sino.soa.mis.srv.transTaskInfo.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: SRV_VENDOR_INFO SrvVendorInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author wangzp
* @version 1.0
* MISϵͳ��Ŀ������Ϣ(ODI) 
*/

public class TransTaskInfoDTO extends CheckBoxDTO{
	private String projectNum = "";   //��Ŀ���
	private String taskNum = "";      //������
	private String assetNumber ="";
	private String stratLastUpdateDate="";
	private String endLastUpdateDate="";
	private String orgOption="";
	private String assetsType ="";
	/**
	 * @return the orgOption
	 */
	public String getOrgOption() {
		return orgOption;
	}
	/**
	 * @param orgOption the orgOption to set
	 */
	public void setOrgOption(String orgOption) {
		this.orgOption = orgOption;
	}

	/**
	 * @return the assetNumber
	 */
	public String getAssetNumber() {
		return assetNumber;
	}
	/**
	 * @param assetNumber the assetNumber to set
	 */
	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}
	/**
	 * @return the stratLastUpdateDate
	 */
	public String getStratLastUpdateDate() {
		return stratLastUpdateDate;
	}
	/**
	 * @param stratLastUpdateDate the stratLastUpdateDate to set
	 */
	public void setStratLastUpdateDate(String stratLastUpdateDate) {
		this.stratLastUpdateDate = stratLastUpdateDate;
	}
	/**
	 * @return the endLastUpdateDate
	 */
	public String getEndLastUpdateDate() {
		return endLastUpdateDate;
	}
	/**
	 * @param endLastUpdateDate the endLastUpdateDate to set
	 */
	public void setEndLastUpdateDate(String endLastUpdateDate) {
		this.endLastUpdateDate = endLastUpdateDate;
	}
	public String getAssetsType() {
		return assetsType;
	}
	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}
	
}