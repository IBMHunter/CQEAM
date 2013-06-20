package com.sino.pda.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class TaskDownloadModel{
	private int organizationId = -1;

	public TaskDownloadModel(int organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ���ȡ��OU�����صĳ�������б�
	 * @return SQLModel
	 */
	public SQLModel getTaskDownloadModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AAST.TASK_NO,"
						+ " AAST.TASK_NAME,"
						+ " (CASE WHEN AAST.END_DATE >= GETDATE() THEN 'Y' ELSE 'N' END) ENABLED"
						+ " FROM   AMS_ASSETS_SAMPLING_TASK AAST,"
						+ " AMS_SAMPLING_TASK_ASSIGN ASTA"
						+ " WHERE  AAST.TASK_ID = ASTA.TASK_ID"
						+ " AND ASTA.ORGANIZATION_ID = ?"
						+ " AND AAST.TASK_STATUS = 'OPENING'";

		sqlArgs.add(organizationId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ��жϴ��ݵ�OU�Ƿ�Ϸ���OU����
	 * @return SQLModel
	 */
	public SQLModel getOrgValidModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " 1"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " EOCM.ORGANIZATION_ID = ?";
		sqlArgs.add(organizationId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
