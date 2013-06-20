package com.sino.framework.security.dao;

import java.sql.Connection;
import java.util.List;

import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.message.MessageManager;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public abstract class BaseLoginDAO {
	protected BaseUserDTO userAccount = null;
	protected Connection conn = null;
	protected ServletConfigDTO servletConfig = null;
	protected boolean isValidUser = false;
	protected Message message = null;
    protected String userDtoName = "";   //�������web.xml�����õ�userDTO

	public BaseLoginDAO(BaseUserDTO userAccount, Connection conn) {
		this.userAccount = userAccount;
		this.conn = conn;
		this.servletConfig = new ServletConfigDTO();
	}

	/**
	 * ���ܣ����ó�ʼ��Servlet���ö���
	 * @param servletConfig ServletConfigDTO
	 */
	public void setServletConfig(ServletConfigDTO servletConfig) {
		if(servletConfig != null){
			this.servletConfig = servletConfig;
		}
	}
	/**
	 * ���ܣ���ȡ��֤�����ݵõ��ḻ���û���Ϣ
	 * @return BaseUserDTO
	 */
	public abstract BaseUserDTO getUserAccount();

	/**
	 * ���ܣ��жϵ�¼�û��Ƿ�Ϸ��û�
	 * @return boolean
	 * @throws QueryException
	 */
	public abstract boolean isValidUser() throws QueryException;

	public Message getMessage() {
		return message;
	}

	/**
	 * ���ܣ�������Ϣ������ȡ��Ϣ��
	 * @param messageKey String
	 */
	public void prodMessage(String messageKey){
		Message refMessage = MessageManager.getMessage(userAccount.getLocale() ,messageKey);
		if(refMessage != null){
			message = new Message();
			message.setMessageKey(messageKey);
			message.setMessageValue(refMessage.getMessageValue());
			List parameterNames = refMessage.getParameterNames();
			if (!parameterNames.isEmpty()) {
				for (int i = 0; i < parameterNames.size(); i++) {
					message.addParameter((String) parameterNames.get(i));
				}
			}
		} else {
			message = Message.getUndefinedMessage();
		}
	}

    public String getUserDtoName() {
        return userDtoName;
    }

    public void setUserDtoName(String userDtoName) {
        this.userDtoName = userDtoName;
    }
}
