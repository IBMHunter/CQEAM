package com.sino.ams.sampling.dto;

import com.sino.ams.bean.SyBaseSQLUtil;

/**
* <p>Title: �������� AmsAssetsSamplingBatch</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsAssetsSamplingBatchDTO extends AmsAssetsSamplingTaskDTO {
    
	private String batchId = "";
	private String batchNo = "";
	private String batchRemark = "";
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;;

	public AmsAssetsSamplingBatchDTO() {
		super();
	}

	/**
	 * ���ܣ����ù����������� ������ID
	 * @param batchId String
	 */
	public void setBatchId(String batchId){
		this.batchId = batchId;
	}

	/**
	 * ���ܣ����ù����������� ��������
	 * @param batchNo String
	 */
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	/**
	 * ���ܣ����ù����������� ��ע��
	 * @param batchRemark String
	 */
	public void setBatchRemark(String batchRemark){
		this.batchRemark = batchRemark;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}


	/**
	 * ���ܣ���ȡ������������ ������ID
	 * @return String
	 */
	public String getBatchId() {
		return this.batchId;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getBatchNo() {
		return this.batchNo;
	}

	/**
	 * ���ܣ���ȡ������������ ��ע��
	 * @return String
	 */
	public String getBatchRemark() {
		return this.batchRemark;
	}

	public int getOrganizationId() {
		return organizationId;
	}
}
