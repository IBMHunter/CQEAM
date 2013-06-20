package com.sino.sms.service;

import com.sino.base.exception.SinoBaseException;
import com.sino.base.log.Logger;
import com.sino.base.util.SystemUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SMSGateWayFactory {
	private static SMSGateWayFactory factory = null;

	private SMSGateWayFactory() {
		super();
	}

	/**
	 * ���ܣ���ȡ�������ܹ���ʵ��
	 * @return SMSGateWayFactory
	 */
	public static SMSGateWayFactory getFactory(){
		if(factory == null){
			factory = new SMSGateWayFactory();
		}
		return factory;
	}

	/**
	 * ���ܣ���ȡ�������ؽӿ�ʵ��
	 * @param servletConfig ServletConfigDTO
	 * @return SMSGateWay
	 * @throws SinoBaseException
	 */
	public SMSGateWay getSMSGateWay(ServletConfigDTO servletConfig) throws SinoBaseException{
		SMSGateWay ggateWay = null;
		try {
			String instanceName = SystemUtil.getClassName(SMSGateWay.class);
			String packageName = SystemUtil.getPackageName(SMSGateWay.class);
			instanceName = packageName + "." + instanceName + servletConfig.getProvinceCode();
			ggateWay = (SMSGateWay) Class.forName(instanceName).newInstance();
			ggateWay.setServletConfig(servletConfig);
		} catch (ClassNotFoundException ex) {
			Logger.logError(ex);
			throw new SinoBaseException(ex);
		} catch (IllegalAccessException ex) {
			Logger.logError(ex);
			throw new SinoBaseException(ex);
		} catch (InstantiationException ex) {
			Logger.logError(ex);
			throw new SinoBaseException(ex);
		}
		return ggateWay;
	}
}
