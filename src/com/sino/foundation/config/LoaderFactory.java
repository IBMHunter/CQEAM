package com.sino.foundation.config;


import com.sino.base.exception.ConfigException;
import com.sino.base.log.Logger;
import com.sino.base.util.SystemUtil;
import com.sino.foundation.config.system.EntryConfig;
import com.sino.foundation.exception.EmptyException;
import com.sino.foundation.validate.JavaBeanValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>@todo����ʱλ�ڸð��£�����ʵ����Ŀ����֤֮�󣬽����������⣬��ȡ��Ŀǰ���������ù���</p>
 * <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public abstract class LoaderFactory {

	private static Map<String, IConfigLoader> loaderMap = new HashMap<String, IConfigLoader>();

	/**
	 * ���ܣ�����������ڶ����ȡ�����ļ�������
	 * @param entryConfig EntryConfig �����ļ���ڶ���
	 * @return AbstractConfigLoader ��ڶ����ض��������ļ�������
	 * @throws ConfigException
	 */
	public static IConfigLoader getConfigLoader(EntryConfig entryConfig) throws ConfigException {
		String configName = entryConfig.getName();
		IConfigLoader configLoader = loaderMap.get(configName);
		if(configLoader == null){
			configLoader = createNewLoader(entryConfig);
            String fileExtention = entryConfig.getFileExtention();
            ConfigFileFilter configFilter = new ConfigFileFilter(fileExtention);
            configLoader.setConfigFilter(configFilter);
            loaderMap.put(configName, configLoader);
		} else {
            System.out.println("����ԭ�е������ļ�������" + configLoader.getClass().getName());
        }
		return configLoader;
	}

	/**
	 * ���ܣ���ȡ�µ�Loaderʵ��
	 * @param entryConfig EntryConfig
	 * @return AbstractConfigLoader
	 * @throws ConfigException
	 */
	private static IConfigLoader createNewLoader(EntryConfig entryConfig) throws ConfigException {
		IConfigLoader configLoader = null;
		String errorMsg = "";
		try {
            List<String> ignoreFields = new ArrayList<String>();
            ignoreFields.add("postProcessor");
            ignoreFields.add("fileExtention");
            ignoreFields.add("primaryKeyName");
			JavaBeanValidator.validateJavaBean(entryConfig, ignoreFields);
		} catch (EmptyException ex) {
			throw new ConfigException(ex);
		}
		String loaderClassName = entryConfig.getLoaderClassName();
		try {
			Class baseCls = AbstractConfigLoader.class;
			Class thisCls = Class.forName(loaderClassName);
			if (!SystemUtil.isDerivedClass(thisCls, baseCls)) {
				errorMsg = "��"
						   + entryConfig.getDescription()
						   + "�����õ�������"
						   + loaderClassName
						   + "��û�м̳������ļ����������ࡰ"
						   + baseCls.getName()
						   + "��";
				throw new ConfigException(errorMsg);
			}
			configLoader = (IConfigLoader) thisCls.newInstance();
            System.out.println("�����µ������ļ�������" + thisCls.getName());
		} catch (ClassNotFoundException ex) {
			Logger.logError(ex);
			throw new ConfigException(ex);
		} catch (IllegalAccessException ex) {
			Logger.logError(ex);
			throw new ConfigException(ex);
		} catch (InstantiationException ex) {
			Logger.logError(ex);
			throw new ConfigException(ex);
		}
		return configLoader;
	}
}
