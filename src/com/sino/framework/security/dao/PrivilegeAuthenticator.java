package com.sino.framework.security.dao;

import java.sql.Connection;

import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public abstract class PrivilegeAuthenticator {
	protected BaseUserDTO userAccount = null;
	protected Connection conn = null;
	protected boolean hasPrivilege = false;
	protected String urlName = "";

	public PrivilegeAuthenticator(BaseUserDTO userAccount, Connection conn) {
		this.userAccount = userAccount;
		this.conn = conn;
	}

	/**
	 * ���ܣ���֤��ǰ�û���ָ����Դ�Ƿ���Ȩ��
	 * @param requestURL ��ǰ��֤����Դ
	 * @return boolean ����true��ʾ��Ȩ�ޣ������ʾ��Ȩ��
	 * @throws QueryException
	 */
	public boolean hasPrivilege(String requestURL) throws QueryException{
		authenticate(requestURL);
		return hasPrivilege;
	}

	/**
	 * ���ܣ�ִ��Ȩ����֤
	 * @param requestURL ��ǰ��֤����Դ
	 * ���󷽷�����������ļ̳���ʵ��
	 * @throws QueryException
	 */
	protected abstract void authenticate(String requestURL)throws QueryException;

	/**
	 * ���ܣ���ȡURL���ơ����û���Ȩ�޷��ʸ�URLʱ�������Ѻ���ʾ����
	 * @return String
	 */
	public String getUrlName(){
		return urlName;
	}
}
