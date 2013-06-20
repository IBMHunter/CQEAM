package com.sino.ams.sampling.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.sampling.dto.AmsSamplingTaskAssignDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: AmsSamplingTaskAssignModel</p>
 * <p>Description:�����Զ�����SQL��������AmsSamplingTaskAssignModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsSamplingTaskAssignModel extends AMSSQLProducer {

	/**
	 * ���ܣ������������ AMS_SAMPLING_TASK_ASSIGN ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsSamplingTaskAssignDTO ���β���������
	 */
	public AmsSamplingTaskAssignModel(SfUserDTO userAccount, AmsSamplingTaskAssignDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɳ���������� AMS_SAMPLING_TASK_ASSIGN���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsSamplingTaskAssignDTO dto = (AmsSamplingTaskAssignDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_SAMPLING_TASK_ASSIGN("
						+ " TASK_ID,"
						+ " ORGANIZATION_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY"
						+ ") VALUES (?, ?, GETDATE(), ?)";

		sqlArgs.add(dto.getTaskId());
		sqlArgs.add(dto.getOrganizationId());
		sqlArgs.add(userAccount.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�������������ֶ� taskId ��������ɾ��SQL��
	 * ����Զ��������ݳ���������� AMS_SAMPLING_TASK_ASSIGN����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param taskId String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByTaskIdModel(String taskId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
						+ " FROM"
						+ " AMS_SAMPLING_TASK_ASSIGN"
						+ " WHERE"
						+ " TASK_ID = ?";
		sqlArgs.add(taskId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsSamplingTaskAssignDTO dto = (AmsSamplingTaskAssignDTO) dtoParameter;
		if (foreignKey.equals("taskId")) {
			sqlModel = getDeleteByTaskIdModel(dto.getTaskId());
		}
		return sqlModel;
	}
}
