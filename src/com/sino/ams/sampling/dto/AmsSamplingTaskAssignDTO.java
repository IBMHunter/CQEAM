package com.sino.ams.sampling.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;

/**
* <p>Title: ����������� AmsSamplingTaskAssign</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsSamplingTaskAssignDTO extends CommonRecordDTO{

	private String taskId = "";
	private int organizationId;

	public AmsSamplingTaskAssignDTO() {
		super();
	}

	/**
	 * ���ܣ����ó�������������� ��������ID
	 * @param taskId String
	 */
	public void setTaskId(String taskId){
		this.taskId = taskId;
	}

	/**
	 * ���ܣ����ó�������������� ��֯
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ���ȡ��������������� ��������ID
	 * @return String
	 */
	public String getTaskId() {
		return this.taskId;
	}

	/**
	 * ���ܣ���ȡ��������������� ��֯
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}
}
