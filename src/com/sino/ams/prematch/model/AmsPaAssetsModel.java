package com.sino.ams.prematch.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.prematch.dto.AmsPaAssetsDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: AmsPaAssetsModel</p>
 * <p>Description:�����Զ�����SQL��������AmsPaAssetsModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsPaAssetsModel extends AMSSQLProducer {

/**
	 * ���ܣ�MISת��׼���嵥 AMS_PA_ASSETS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsPaAssetsDTO ���β���������
	 */
	public AmsPaAssetsModel(SfUserDTO userAccount, AmsPaAssetsDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}


	/**
	 * ���ܣ�����Զ�����MISת��׼���嵥 AMS_PA_ASSETS������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsPaAssetsDTO dto = (AmsPaAssetsDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " *"
						+ " FROM"
						+ " AMS_PA_ASSETS APA"
						+ " WHERE"
						+ " TAG_NUMBER = ?";
		sqlArgs.add(dto.getTagNumber());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����MISת��׼���嵥 AMS_PA_ASSETSҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " *"
						+ " FROM"
						+ " AMS_PA_ASSETS APA"
						+ " WHERE"
						+ " (APA.PROJECT_NUMBER LIKE dbo.NVL(?, APA.PROJECT_NUMBER)"
						+ " OR APA.PROJECT_NAME LIKE dbo.NVL(?, APA.PROJECT_NAME))"
						+ " AND (APA.ASSETS_LOCATION_CODE LIKE dbo.NVL(?, APA.ASSETS_LOCATION_CODE)"
						+ " OR APA.ASSETS_LOCATION LIKE dbo.NVL(?, APA.ASSETS_LOCATION))"
						+ " AND (APA.TASK_NAME LIKE dbo.NVL(?, APA.TASK_NAME)"
						+ " OR APA.TASK_NUMBER LIKE dbo.NVL(?, APA.TASK_NUMBER))"
						+ " AND APA.ORGANIZATION_ID = ?";
		AmsPaAssetsDTO dto = (AmsPaAssetsDTO) dtoParameter;
		sqlArgs.add(dto.getProjectNumber());
		sqlArgs.add(dto.getProjectNumber());
		sqlArgs.add(dto.getAssetsLocationCode());
		sqlArgs.add(dto.getAssetsLocationCode());
		sqlArgs.add(dto.getTaskNumber());
		sqlArgs.add(dto.getTaskNumber());

		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
