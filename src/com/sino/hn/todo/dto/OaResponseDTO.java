package com.sino.hn.todo.dto;

import com.sino.base.dto.DTO;
import com.sino.sinoflow.todo.constant.HNOAConstant;

/**
 * 
 * @ϵͳ����: �ӿڷ�����Ϣ
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 1, 2011
 */
public class OaResponseDTO implements DTO {
	private boolean isAccess = false;	//����Խ�
	private boolean isSuccess = false;  //�������ͳɹ�
	
	private String retXML = "";
	private String resultCode = HNOAConstant.RESULT_CODE_FAILURE ;
	private String resultDesc = "";
	
	private String beginSendTime = "";
	private String endSendTime = "";
	
	private String eamMsg = "";
	

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getEamMsg() {
		return eamMsg;
	}

	public void setEamMsg(String eamMsg) {
		this.eamMsg = eamMsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public boolean isAccess() {
		return isAccess;
	}

	public void setAccess(boolean isAccess) {
		this.isAccess = isAccess;
	}

	public String getRetXML() {
		return retXML;
	}

	public void setRetXML(String retXML) {
		this.retXML = retXML;
	}

	public String getBeginSendTime() {
		return beginSendTime;
	}

	public void setBeginSendTime(String beginSendTime) {
		this.beginSendTime = beginSendTime;
	}

	public String getEndSendTime() {
		return endSendTime;
	}

	public void setEndSendTime(String endSendTime) {
		this.endSendTime = endSendTime;
	}
}
