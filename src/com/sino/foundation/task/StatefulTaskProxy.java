package com.sino.foundation.task;

import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.foundation.config.DefaultConfigManager;
import com.sino.foundation.config.task.TaskConfig;
import com.sino.foundation.config.task.TaskConfigs;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import java.util.HashMap;
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
public class StatefulTaskProxy implements StatefulJob {
    private final static Map<String, Integer> taskExecuteTimes = new HashMap<String, Integer>();

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			TaskConfigs taskConfigs = DefaultConfigManager.getTaskConfig();
			JobDetail job = context.getJobDetail();
            String taskName = job.getName();
            TaskConfig taskConfig = taskConfigs.getTaskConfig(taskName);
            synchronized (taskExecuteTimes){
                Integer executeTimes = taskExecuteTimes.get(taskName);
                if(executeTimes == null){
                    executeTimes = 1;
                } else {
                    executeTimes++;
                }
                taskExecuteTimes.put(taskName, executeTimes);
                System.out.println("��" + (++executeTimes) + "�δ�������"+taskConfig.getTaskDesc()+"����ʱ�䣺" + CalendarUtil.getCurrCalendar());
            }
			Class cls = Class.forName(taskConfig.getTaskClass());
			Object obj = cls.newInstance();
			String methodName = taskConfig.getTaskMethod();
            Object[] para = new Object[0];
			ReflectionUtil.invokeMethod(obj, methodName, para);
		} catch (Throwable ex) {
			Logger.logError(ex);
			throw new JobExecutionException(ex);
		}
	}
}
