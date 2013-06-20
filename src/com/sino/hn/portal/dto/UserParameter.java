package com.sino.hn.portal.dto;

/**
 * Title: SinoBase
 * Description: ����˼ŵ����Ϣ�Ƽ����޹�˾�����⣺�û�������Ϣ
 * Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006
 * Company: ����˼ŵ����Ϣ�Ƽ����޹�˾
 * 
 * @author ����ʤ
 * @version 1.0
 */
public class UserParameter {
	private String loginName;
	private String loginPwd;
	private String userId = "";
	private boolean encrypt = true;
	private String arithmetic = "ETS_ENCRYPT.ENCODE";
	private String source;

	public UserParameter() {
		super();
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public void setArithmetic(String arithmetic) {
		this.arithmetic = arithmetic;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public String getUserId() {
		return userId;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public String getArithmetic() {
		return arithmetic;
	}

	public String getSource() {
		return source;
	}
}
