package com.sino.hn.todo.log.dto;

import com.sino.sinoflow.todo.dto.OaTodoDTO;

/**
 * 
 * @ϵͳ����: 
 * @��������: OA_TODO_LOG ��־
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 30, 2011
 */
public class OaTodoLogDTO extends OaTodoDTO {

	private String beginSendTime = "";
	private String endSendTime = "";
	private String eamMsg = "";
	private String resultCode = "";
	private String resultDesc = "";

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

	public String getEamMsg() {
		return eamMsg;
	}

	public void setEamMsg(String eamMsg) {
		this.eamMsg = eamMsg;
	}

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
	 
}
