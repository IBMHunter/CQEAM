package com.sino.soa.util.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.math.AdvancedNumber;

/**
* <p>Title: ETS_MISFA_TRANSACTION_RESP EtsMisfaTransactionResp</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsMisfaTransactionRespDTO extends CheckBoxDTO{

	private String transactionType = "";
	private AdvancedNumber organizationId = null;
	private AdvancedNumber userId = null;
	private AdvancedNumber respId = null;
	private AdvancedNumber respApplId = null;
	private String employeeNumber = "";

	private String snCode = "";           //SN_CODE ֧�����豸����
	private String opeCode = "";           //OPE_CODE ֧�����豸����
	
	public EtsMisfaTransactionRespDTO() {
		super();

		this.organizationId = new AdvancedNumber();
		this.userId = new AdvancedNumber();
		this.respId = new AdvancedNumber();
		this.respApplId = new AdvancedNumber();
	}

	/**
	 * ���ܣ�����ETS_MISFA_TRANSACTION_RESP���� TRANSACTION_TYPE
	 * @param transactionType String
	 */
	public void setTransactionType(String transactionType){
		this.transactionType = transactionType;
	}

	/**
	 * ���ܣ�����ETS_MISFA_TRANSACTION_RESP���� ORGANIZATION_ID
	 * @param organizationId AdvancedNumber
	 */
	public void setOrganizationId(AdvancedNumber organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_TRANSACTION_RESP���� USER_ID
	 * @param userId AdvancedNumber
	 */
	public void setUserId(AdvancedNumber userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_TRANSACTION_RESP���� RESP_ID
	 * @param respId AdvancedNumber
	 */
	public void setRespId(AdvancedNumber respId){
		this.respId = respId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_TRANSACTION_RESP���� RESP_APPL_ID
	 * @param respApplId AdvancedNumber
	 */
	public void setRespApplId(AdvancedNumber respApplId){
		this.respApplId = respApplId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_TRANSACTION_RESP���� Ա�����
	 * @param employeeNumber String
	 */
	public void setEmployeeNumber(String employeeNumber){
		this.employeeNumber = employeeNumber;
	}


	/**
	 * ���ܣ���ȡETS_MISFA_TRANSACTION_RESP���� TRANSACTION_TYPE
	 * @return String
	 */
	public String getTransactionType() {
		return this.transactionType;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_TRANSACTION_RESP���� ORGANIZATION_ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_TRANSACTION_RESP���� USER_ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getUserId() {
		return this.userId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_TRANSACTION_RESP���� RESP_ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getRespId() {
		return this.respId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_TRANSACTION_RESP���� RESP_APPL_ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getRespApplId() {
		return this.respApplId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_TRANSACTION_RESP���� Ա�����
	 * @return String
	 */
	public String getEmployeeNumber() {
		return this.employeeNumber;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String getOpeCode() {
		return opeCode;
	}

	public void setOpeCode(String opeCode) {
		this.opeCode = opeCode;
	}

}