package com.sino.foundation.config;

import com.sino.base.exception.ConfigException;
import com.sino.foundation.config.task.TaskConfigLoader;
import com.sino.foundation.config.task.TaskConfigs;

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
public abstract class DefaultConfigManager{

	private static final String TASK_LOADER = TaskConfigLoader.class.getName();

	/**
	 * ���ܣ���ȡ��ʱ��������
	 *
	 * @return TaskConfigs
     * @throws ConfigException
	 */
	public static TaskConfigs getTaskConfig() throws ConfigException {
		return ConfigManager.getLoadedResultByLoader(TASK_LOADER);
	}
}
