package com.sino.ams.newasset.message.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.base.exception.StrException;
import com.sino.base.web.WebRadio;

public class FreeAssetsMsgSetAndQueryDTO extends CommonRecordDTO {

	//private int msgCategoryId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String msgCategoryId = SyBaseSQLUtil.NEW_ID_FUNCTION;
	private String msgDesc = "";
	private String needResend = "Y";
	private int resendMaxtimes = 2;
	private int resendDistance = 300;
	private int organizationId = 0;
	private String enabled = "Y";
	private int flowId = -1 ; //�����е�����ID��-1����֧�ַ����̶���Ϣ����
	private String collectSend = "N"; //���ܷ���
	private String resendFromTimes = "";
	private String resendToTimes = "";
	private String resendFromDistance = "";
	private String resendToDistance = "";

	private String organizationOption = "";
	private String companyName = "";
	private boolean initPrivi = false;
	/**
	 * ���ܣ�������Ϣ��������� ��Ϣ���ID
	 * @param msgCategoryId String
	 */
	public void setMsgCategoryId(String msgCategoryId) {
		this.msgCategoryId = msgCategoryId;
	}

	/**
	 * ���ܣ�������Ϣ��������� ��Ϣ����
	 * @param msgDesc String
	 */
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	/**
	 * ���ܣ�������Ϣ��������� �Ƿ���Ҫ�ظ����͡�'Y'��ʾ��Ҫ�ظ����ͣ�'N'��ʾ����Ҫ�ظ����͡�
	 * @param needResend String
	 */
	public void setNeedResend(String needResend) {
		this.needResend = needResend;
	}

	/**
	 * ���ܣ�������Ϣ��������� ������Ϣ����֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�������Ϣ��������� ��Ϣ�Ƿ���Ч
	 * @param enabled String
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public void setResendFromTimes(String resendFromTimes) {
		this.resendFromTimes = resendFromTimes;
	}

	public void setResendToTimes(String resendToTimes) {
		this.resendToTimes = resendToTimes;
	}

	public void setResendFromDistance(String resendFromDistance) {
		this.resendFromDistance = resendFromDistance;
	}

	public void setResendToDistance(String resendToDistance) {
		this.resendToDistance = resendToDistance;
	}

	public void setOrganizationOption(String organizationOption) {
		this.organizationOption = organizationOption;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public void setCollectSend(String collectSend) {
		this.collectSend = collectSend;
	}

	public void setInitPrivi(boolean initPrivi) {
		this.initPrivi = initPrivi;
	}

	/**
	 * ���ܣ���ȡ��Ϣ��������� ��Ϣ���ID
	 * @return String
	 */
	public String getMsgCategoryId() {
		return this.msgCategoryId;
	}

	/**
	 * ���ܣ���ȡ��Ϣ��������� ��Ϣ����
	 * @return String
	 */
	public String getMsgDesc() {
		return this.msgDesc;
	}

	/**
	 * ���ܣ���ȡ��Ϣ��������� �Ƿ���Ҫ�ظ����͡�'Y'��ʾ��Ҫ�ظ����ͣ�'N'��ʾ����Ҫ�ظ����͡�
	 * @return String
	 */
	public String getNeedResend() {
		return this.needResend;
	}


	/**
	 * ���ܣ���ȡ��Ϣ��������� ������Ϣ����֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ��Ϣ��������� ��Ϣ�Ƿ���Ч
	 * @return String
	 */
	public String getEnabled() {
		return this.enabled;
	}

	public String getResendFromTimes() {
		return resendFromTimes;
	}

	public String getResendToTimes() {
		return resendToTimes;
	}

	public String getResendFromDistance() {
		return resendFromDistance;
	}

	public String getResendToDistance() {
		return resendToDistance;
	}

	public String getOrganizationOption() {
		return organizationOption;
	}

	public String getCompanyName() {
		return companyName;
	}


	public String getCollectSend() {
		return collectSend;
	}

	/**
	 * ���ܣ��жϵ�ǰ�û��Ƿ��г�ʼ��Ȩ��
	 * @return boolean
	 */
	public boolean hasInitPrivi() {
		return initPrivi;
	}

	/**
	 * ���ܣ���ȡ��Ч�Ե�ѡ��ť
	 * @return String
	 */
	public String getEnabledRadio() {
		StringBuffer radioBox = new StringBuffer();
		try {
			String[] values = WebAttrConstant.EFFECTIVE_VAL;
			String[] caps = WebAttrConstant.EFFECTIVE_CAP;
			String radioName = "enabled";
			for (int i = 0; i < values.length; i++) {
				WebRadio radio = new WebRadio(radioName);
				radio.setCheckedValue(getEnabled());
				radio.addValueCaption(values[i], caps[i]);
				radioBox.append(radio.toString());
			}
		} catch (StrException ex) {
			ex.printLog();
		}
		return radioBox.toString();
	}

	/**
	 * ���ܣ���ȡ�Ƿ��ط���ѡ��ť
	 * @return String
	 */
	public String getNeedResendRadio() {
		StringBuffer radioBox = new StringBuffer();
		try {
			String[] values = WebAttrConstant.RESEND_VAL;
			String[] caps = WebAttrConstant.RESEND_CAP;
			String radioName = "needResend";
			for (int i = 0; i < values.length; i++) {
				WebRadio radio = new WebRadio(radioName);
				radio.setCheckedValue(getNeedResend());
				radio.addValueCaption(values[i], caps[i]);
				radioBox.append(radio.toString());
			}
		} catch (StrException ex) {
			ex.printLog();
		}
		return radioBox.toString();
	}

	/**
	 * ���ܣ���ȡ�Ƿ���ܷ��͵�ѡ��ť
	 * @return String
	 */
	public String getCollectSendRadio() {
		StringBuffer radioBox = new StringBuffer();
		try {
			String[] values = WebAttrConstant.COLLECT_VAL;
			String[] caps = WebAttrConstant.COLLECT_CAP;
			String radioName = "collectSend";
			for (int i = 0; i < values.length; i++) {
				WebRadio radio = new WebRadio(radioName);
				radio.setCheckedValue(getCollectSend());
				radio.addValueCaption(values[i], caps[i]);
				radioBox.append(radio.toString());
			}
		} catch (StrException ex) {
			ex.printLog();
		}
		return radioBox.toString();
	}

	public int getResendMaxtimes() {
		return resendMaxtimes;
	}

	public void setResendMaxtimes(int resendMaxtimes) {
		this.resendMaxtimes = resendMaxtimes;
	}

	public int getResendDistance() {
		return resendDistance;
	}

	public void setResendDistance(int resendDistance) {
		this.resendDistance = resendDistance;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
}
