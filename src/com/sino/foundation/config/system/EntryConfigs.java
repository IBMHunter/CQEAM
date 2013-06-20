package com.sino.foundation.config.system;


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
public class EntryConfigs {
    private List<String> configNames = new ArrayList<String>();
    private List<EntryConfig> configs = new ArrayList<EntryConfig>();

	public EntryConfigs() {
		super();
	}

	public void addEntryConfig(EntryConfig config) {
		if (!configNames.contains(config.getName())) {
            configNames.add(config.getName());
            configs.add(config);
		}
	}

	/**
	 * ���ܣ���ȡ�����ļ������е������������
	 * @return Iterator
	 */
	public List<String> getConfigNames() {
		return configNames;
	}

	/**
	 * ���ܣ���ȡ�����ļ������е������������
	 * @return Iterator
	 */
	public List<EntryConfig> getAllConfigs() {
		return configs;
	}

	/**
	 * ��ȡָ�����ӳ�����
	 * @param configName String ���ӳ�����
	 * @return EntryConfig
	 */
	public EntryConfig getEntryConfig(String configName) {
        EntryConfig config = null;
        int index = configNames.indexOf(configName);
        if(index > -1){
            config = configs.get(index);
        }
		return config;
	}

	public EntryConfig removeEntryConfig(String configName) {
		EntryConfig config = null;
        int index = configNames.indexOf(configName);
        if(index > -1){
            config = configs.remove(index);
            configNames.remove(index);
        }
		return config;
	}

	public void clearConfigs(){
		configs.clear();
		configNames.clear();
	}
}
