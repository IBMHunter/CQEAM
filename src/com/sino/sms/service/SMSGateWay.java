package com.sino.sms.service;

import com.sino.base.SinoBaseObject;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class SMSGateWay extends SinoBaseObject {
	private ServletConfigDTO servletConfig = null;

	public void setServletConfig(ServletConfigDTO servletConfig){
		this.servletConfig = servletConfig;
	}

	/**
	 * ���ܣ����Ͷ���
	 * @param recvPhoneNo String �����ֻ���
	 * @param message String ��������
	 * @return boolean ����true��ʾ���ͳɹ�������false��ʾ����ʧ��
	 */
	abstract boolean sendMessage(String recvPhoneNo,String message);
}
