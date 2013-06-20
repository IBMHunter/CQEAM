package com.sino.foundation.config;

import com.sino.base.exception.ConfigException;
import com.sino.foundation.config.system.EntryConfig;
import com.sino.foundation.config.system.EntryConfigs;

import java.util.ArrayList;
import java.util.List;

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
public class ConfigManager {
	private static List<String> configNames = new ArrayList<String>();
	private static List<String> configLoaders = new ArrayList<String>();
	private static List<ConfigLoadedResult> configResults = new ArrayList<ConfigLoadedResult>();
	private static EntryConfigs configs = new EntryConfigs();

	synchronized void addConfig(EntryConfig entgryConfig, ConfigLoadedResult configResult) {
		String configName = entgryConfig.getName();
		if(configNames.contains(configName)){
			return;
		}
		configNames.add(entgryConfig.getName());
		configLoaders.add(entgryConfig.getLoaderClassName());
		configs.addEntryConfig(entgryConfig);
		configResults.add(configResult);
	}

	/**
	 * ���ܣ������������ƻ�ȡ���ý��
	 * @param configName String
	 * @return LoadedResult
     * @throws ConfigException �������Ϊ���سɹ������׳����ó�ʼ���쳣
	 */
	public static ConfigLoadedResult getLoadedResult(String configName) throws ConfigException {
		ConfigLoadedResult configResult = null;
		int index = configNames.indexOf(configName);
		if(index != -1){
			configResult = configResults.get(index);
		}
        if(configResult == null){
            throw new ConfigException(configName + "�����ļ�δ���أ��޷����к�������...");
        }
		return configResult;
	}


	/**
	 * ���ܣ��������ü�������ȡ���ý�����������ء�
     * <B>ע�⣺���ñ�������ζ�ű�������Ϊ�Ǳؼ��������ü�����δ���أ��򷵻�NULL</B>
	 * @param loaderName String ������ȫ·����
	 * @return LoadedResult
	 */
	public static <T extends ConfigLoadedResult> T  getLoadedResultByLoader(String loaderName) throws ConfigException {
        T configResult = null;
        int index = configLoaders.indexOf(loaderName);
        if(index != -1){
            configResult = (T)configResults.get(index);
        }
        if(configResult == null){
            throw new ConfigException(loaderName + "δ���������ļ����޷����к�������...");
        }
        return configResult;
	}

	/**
	 * ���ܣ�����Ӧ��������Ϣ�Ƴ�
	 * @param configName String
	 */
	public synchronized void removeConfig(String configName){
		int index = configNames.indexOf(configName);
		if(index != -1){
            String description = configs.getEntryConfig(configName).getDescription();
			configNames.remove(index);
			configLoaders.remove(index);
			configResults.remove(index);
			configs.removeEntryConfig(configName);
            System.out.println(description + " ж�سɹ�");
		}
	}

	/**
	 * ���ܣ����ݼ�����ȫ�޶�������Ӧ��������Ϣ�Ƴ�
	 * @param loaderName String
	 */
	public synchronized void removeConfigByLoaderName(String loaderName){
		int index = configLoaders.indexOf(loaderName);
		if(index != -1){
			String configName = configNames.remove(index);
			configLoaders.remove(index);
			configResults.remove(index);
			EntryConfig config = configs.removeEntryConfig(configName);
			System.out.println(config.getDescription() + " ж�سɹ�");
		}
	}

    public EntryConfigs getAllConfigs(){
        return configs;
    }

    public boolean contains(String configName){
        return configNames.contains(configName);
    }

	/**
	 * ���ܣ���ȡָ�����Ƶ��������
	 * @param configName String
	 * @return EntryConfig
	 */
	public static EntryConfig getEntryConfig(String configName){
		return configs.getEntryConfig(configName);
	}
}
