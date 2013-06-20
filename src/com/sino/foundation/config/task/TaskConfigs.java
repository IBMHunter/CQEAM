package com.sino.foundation.config.task;


import com.sino.foundation.config.ConfigLoadedResult;

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
public class TaskConfigs  implements ConfigLoadedResult {//��ʱ��������
	private List<TaskConfig> tasks = null;
	private List<TriggerConfig> triggers = null;
	private List<SchedulerConfig> schedulers = null;

	public List<TaskConfig> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskConfig> tasks) {
		this.tasks = tasks;
	}

	public List<TriggerConfig> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<TriggerConfig> triggers) {
		this.triggers = triggers;
	}

	public List<SchedulerConfig> getSchedulers() {
		return schedulers;
	}

	public void setSchedulers(List<SchedulerConfig> schedulers) {
		this.schedulers = schedulers;
	}

	/**
	 * ���ܣ���ȡָ�����Ƶ�����
	 * @param taskName ��������
	 * @return ����ָ�����Ƶ�����
	 */
	public TaskConfig getTaskConfig(String taskName) {
		TaskConfig taskConfig = null;
		for	(TaskConfig task:tasks){
			if(task.getName().equals(taskName)){
				taskConfig = task;
				break;
			}
		}
		return taskConfig;
	}

	/**
	 * ���ܣ���ȡָ�����ƵĴ�����
	 * @param triggerName ����������
	 * @return ����ָ�����ƵĴ�����
	 */
	public TriggerConfig getTriggerConfig(String triggerName) {
		TriggerConfig triggerConfig = null;
		for	(TriggerConfig trigger:triggers){
			if(trigger.getName().equals(triggerName)){
				triggerConfig = trigger;
				break;
			}
		}
		return triggerConfig;
	}

	public void releaseData() {
		tasks = null;
		triggers = null;
		schedulers = null;
	}
}