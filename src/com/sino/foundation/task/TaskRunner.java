package com.sino.foundation.task;


import com.sino.base.log.Logger;
import com.sino.foundation.config.task.SchedulerConfig;
import com.sino.foundation.config.task.TaskConfig;
import com.sino.foundation.config.task.TaskConfigs;
import com.sino.foundation.config.task.TriggerConfig;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

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

public class TaskRunner {

    private static Scheduler scheduler = null;
    private TaskConfigs taskConfigs = null;

    /**
     * ���ܣ�����������������������ȡ�
     *
     * @param taskConfigs ��������
     * @throws SchedulerException
     */
    public void startTask(TaskConfigs taskConfigs) throws SchedulerException {
        if (taskConfigs != null) {
            this.taskConfigs = taskConfigs;
            produceScheduler();
            List<SchedulerConfig> schedulers = taskConfigs.getSchedulers();
            int count = 0;
            for (SchedulerConfig schConfig : schedulers) {
                if (!schConfig.isStartTask()) {
                    continue;
                }
                count++;
                processTask(schConfig);
            }
            if (count > 0) {
                scheduler.start();//����������
            } else {
                scheduler = null;
            }
        }
    }

    /**
     * ���ܣ����������������
     *
     * @throws SchedulerException
     */
    private void produceScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        if (scheduler == null) {
            scheduler = schedulerFactory.getScheduler();
        }
    }

    /**
     * ���ܣ���������������
     *
     * @param schConfig ������ȶ���
     * @throws SchedulerException ����ʱ����������������ʱ�׳����쳣
     */
    private void processTask(SchedulerConfig schConfig) throws SchedulerException {
        try {
            TaskConfig task = taskConfigs.getTaskConfig(schConfig.getTaskName());
            TriggerConfig triCfg = taskConfigs.getTriggerConfig(schConfig.getTriggerName());
            String className = task.getTaskClass();
            String taskType = task.getTaskType();
            Class cls = Class.forName(className);
            if (taskType.equals(TaskConfig.TASK_TYPE_COMMON)) {
                if (task.isStateful()) {
                    cls = StatefulTaskProxy.class;
                } else {
                    cls = StatelessTaskProxy.class;
                }
            }
            JobDetail job = new JobDetail(task.getName(), schConfig.getTaskGroup(), cls);//������ҵ����
            job.setDescription(task.getTaskDesc());

            CronTrigger trigger = new CronTrigger(triCfg.getName(), schConfig.getTriggerGroup());//newһ��������
            trigger.setCronExpression(triCfg.getCronExpression());
            trigger.setPriority(triCfg.getPriority());
            scheduler.scheduleJob(job, trigger);//��ҵ�ʹ��������õ���������
            System.out.println("��ѯ����" + task.getTaskDesc() + "���������...");
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new SchedulerException(ex);
        }
    }

    public static void destroyTask() throws SchedulerException {
        if(scheduler != null){
            scheduler.shutdown(true);
            scheduler = null;
        }
    }
}