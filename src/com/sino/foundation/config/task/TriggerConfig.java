package com.sino.foundation.config.task;


import com.sino.base.SinoBaseObject;

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
public class TriggerConfig extends SinoBaseObject {//��ʱ��������
	private String name = "";
	private String triggerDesc = "";
	private String cronExpression = "";
	private int priority = 5;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		if(priority > Thread.MAX_PRIORITY){
			priority = Thread.MAX_PRIORITY;
		} else if(priority < Thread.MIN_PRIORITY){
			priority = Thread.MIN_PRIORITY;
		}
		this.priority = priority;
	}

	public String getTriggerDesc() {
		return triggerDesc;
	}

	public void setTriggerDesc(String triggerDesc) {
		this.triggerDesc = triggerDesc;
	}
}