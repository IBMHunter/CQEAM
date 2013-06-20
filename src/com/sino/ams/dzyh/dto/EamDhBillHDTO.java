package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;

/**
* <p>Title: ��ṹ����-H(EAM) EamDhBillH</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamDhBillHDTO extends CommonRecordDTO{

	private String billHeaderId = "";
	private String billNo = "";//���ݱ��
	private int billStatus = 0;//����״̬
	private int orgId = SyBaseSQLUtil.NULL_INT_VALUE;//��֯id
	
	private String edbhCompany="";//��˾����
	private String edbhUsername="";//������

	public String getEdbhCompany() {
		return edbhCompany;
	}

	public void setEdbhCompany(String edbhCompany) {
		this.edbhCompany = edbhCompany;
	}

	public String getEdbhUsername() {
		return edbhUsername;
	}

	public void setEdbhUsername(String edbhUsername) {
		this.edbhUsername = edbhUsername;
	}

	/**
	 * ���ܣ����ñ�ṹ����-H(EAM)���� EAM_DH_BILL_H_S.NEXTVAL
	 * @param billHeaderId String
	 */
	public void setBillHeaderId(String billHeaderId){
		this.billHeaderId = billHeaderId;
	}

	/**
	 * ���ܣ����ñ�ṹ����-H(EAM)���� ���ݱ��
	 * @param billNo String
	 */
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	/**
	 * ���ܣ����ñ�ṹ����-H(EAM)���� ����״̬(0:������  1�������)���μ��ֵ䡰��ֵ�׺ĵ���״̬��
	 * @param billStatus String
	 */
	public void setBillStatus(int billStatus){
		this.billStatus = billStatus;
	}

	/**
	 * ���ܣ����ñ�ṹ����-H(EAM)���� ��֯ID
	 * @param orgId String
	 */
	public void setOrgId(int orgId){
		this.orgId = orgId;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-H(EAM)���� EAM_DH_BILL_H_S.NEXTVAL
	 * @return String
	 */
	public String getBillHeaderId() {
		return this.billHeaderId;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-H(EAM)���� ���ݱ��
	 * @return String
	 */
	public String getBillNo() {
		return this.billNo;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-H(EAM)���� ����״̬(0:������  1�������)���μ��ֵ䡰��ֵ�׺ĵ���״̬��
	 * @return String
	 */
	public int getBillStatus() {
		return this.billStatus;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-H(EAM)���� ��֯ID
	 * @return String
	 */
	public int getOrgId() {
		return this.orgId;
	}

}