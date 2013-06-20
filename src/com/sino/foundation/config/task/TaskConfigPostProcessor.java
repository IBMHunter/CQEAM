package com.sino.foundation.config.task;


import com.sino.base.exception.ConfigException;
import com.sino.base.log.Logger;
import com.sino.foundation.config.ConfigLoadedResult;
import com.sino.foundation.config.ConfigPostProcessor;
import com.sino.foundation.task.TaskRunner;
import org.quartz.SchedulerException;

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
public class TaskConfigPostProcessor implements ConfigPostProcessor {

    /**
     * ���ܣ������ļ����ؽ��������
     *
     * @param configResult ���ؽ��
     * @throws com.sino.base.exception.ConfigException
     *          ����ʧ��ʱͳһ�׳������쳣
     */
    public void postProcessConfig(ConfigLoadedResult configResult) throws ConfigException {
        try {
            TaskConfigs taskConfigs = (TaskConfigs) configResult;
            TaskRunner taskRunner = new TaskRunner();
            taskRunner.startTask(taskConfigs);
        } catch (SchedulerException ex) {
            Logger.logError(ex);
            throw new ConfigException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
    }
}
