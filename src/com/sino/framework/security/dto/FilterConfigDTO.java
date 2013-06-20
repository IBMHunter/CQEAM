package com.sino.framework.security.dto;

import com.sino.framework.dto.BaseLocaleDTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class FilterConfigDTO extends BaseLocaleDTO{
	private String authenticator = "";//Ȩ����֤��
	private String loginUrl = "";//��¼ҳ��
	private String loginServlet = "";//��¼ҳ���ύ��Servlet
	private String logOutServlet = "";//�˳���¼�õ�Servlet
	private String sessionServlet = "";//�Ự��Ч�Թ���Servlet
	private String timeOutUrl = "";//�Ự����ҳ��
	private String noPriviledgeURL = "";//��Ȩ��ʱ��ҳ��
	private String exceptServlets = "";//������֤��servlet
	private String exceptJSPs = "";//������֤��JSP
	private String exceptions = "";//������֤���������ļ�
	private String userDTO = "";//�����û�����������
	private String loginDAO = "";//�û���¼ʱʹ�õ���Ч����֤��
	private String loginSuccessURL = "";//��½�ɹ������ҳ��
	private String loginName = "";//��¼�ʺű�����
	private String loginPwd = "";//��¼���������

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setTimeOutUrl(String timeOutUrl) {
		this.timeOutUrl = timeOutUrl;
	}

	public void setNoPriviledgeURL(String noPriviledgeURL) {
		this.noPriviledgeURL = noPriviledgeURL;
	}

	public void setExceptServlets(String exceptServlets) {
		this.exceptServlets = exceptServlets;
	}

	public void setExceptJSPs(String exceptJSPs) {
		this.exceptJSPs = exceptJSPs;
	}

	public void setExceptions(String exceptions) {
		this.exceptions = exceptions;
	}

	public void setUserDTO(String userDTO) {
		this.userDTO = userDTO;
	}

	public void setLoginSuccessURL(String loginSuccessURL) {
		this.loginSuccessURL = loginSuccessURL;
	}

	public void setLoginDAO(String loginDAO) {
		this.loginDAO = loginDAO;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public void setLoginServlet(String loginServlet) {
		this.loginServlet = loginServlet;
	}

	public void setLogOutServlet(String logOutServlet) {
		this.logOutServlet = logOutServlet;
	}

	public void setSessionServlet(String sessionServlet) {
		this.sessionServlet = sessionServlet;
	}

	public String getAuthenticator() {
		return authenticator;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public String getTimeOutUrl() {
		return timeOutUrl;
	}

	public String getNoPriviledgeURL() {
		return noPriviledgeURL;
	}

	public String getExceptServlets() {
		return exceptServlets;
	}

	public String getExceptJSPs() {
		return exceptJSPs;
	}

	public String getExceptions() {
		return exceptions;
	}

	public String getUserDTO() {
		return userDTO;
	}

	public String getLoginSuccessURL() {
		return loginSuccessURL;
	}

	public String getLoginDAO() {
		return loginDAO;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public String getLoginServlet() {
		return loginServlet;
	}

	public String getLogOutServlet() {
		return logOutServlet;
	}

	public String getSessionServlet() {
		return sessionServlet;
	}
}
