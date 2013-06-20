package com.sino.ams.sampling.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.sampling.dto.AmsAssetsSamplingBatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: AmsAssetsSamplingBatchModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsSamplingBatchModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsSamplingBatchModel extends AMSSQLProducer {

	/**
	 * ���ܣ��������� AMS_ASSETS_SAMPLING_BATCH ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsSamplingBatchDTO ���β���������
	 */
	public AmsAssetsSamplingBatchModel(SfUserDTO userAccount, AmsAssetsSamplingBatchDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɹ������� AMS_ASSETS_SAMPLING_BATCH���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_ASSETS_SAMPLING_BATCH("
						+ " BATCH_ID,"
						+ " BATCH_NO,"
						+ " TASK_ID,"
						+ " BATCH_REMARK,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " ORGANIZATION_ID,"
						+ " CREATED_OU"
						+ ") VALUES (?, ?, ?, ?, GETDATE(), ?, ?, ?)";

		sqlArgs.add(dto.getBatchId());
		sqlArgs.add(dto.getBatchNo());
		sqlArgs.add(dto.getTaskId());
		sqlArgs.add(dto.getBatchRemark());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getSampledOu());
		sqlArgs.add(userAccount.getOrganizationId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� AMS_ASSETS_SAMPLING_BATCH���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
		String sqlStr = "UPDATE AMS_ASSETS_SAMPLING_BATCH"
						+ " SET"
						+ " LAST_UPDATE_DATE = GETDATE(),"
						+ " LAST_UPDATE_BY = ?"
						+ " WHERE"
						+ " BATCH_ID = ?";

		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getBatchId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� AMS_ASSETS_SAMPLING_BATCH������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " AAST.TASK_ID,"
						+ " AAST.TASK_NO,"
						+ " AAST.TASK_NAME,"
						+ " AAST.TASK_DESC,"
						+ " AAST.START_DATE,"
						+ " AAST.END_DATE,"
						+ " AAST.SAMPLING_TYPE,"
						+ " dbo.APP_GET_FLEX_VALUE(AAST.SAMPLING_TYPE, 'SAMPLING_TYPE') SAMPLING_TYPE_VALUE,"
						+ " AAST.SAMPLING_RATIO,"
						+ " AAST.CREATED_OU,"
						+ " EOCM.COMPANY CREATED_OU_NAME,"
						+ " AAST.CREATION_DATE,"
						+ " SU1.USERNAME CREATED_USER,"
						+ " AAST.LAST_UPDATE_DATE,"
						+ " AAST.LAST_UPDATE_BY,"
						+ " AAST.TASK_STATUS,"
						+ " dbo.APP_GET_FLEX_VALUE(AAST.TASK_STATUS, 'TASK_STATUS') TASK_STATUS_VALUE,"
						+ " AAST.CLOSED_DATE,"
						+ " AAST.CLOSED_BY,"
						+ " SU3.USERNAME CLOSED_USER,"
						+ " AAST.OPENED_DATE,"
						+ " AAST.OPENED_BY,"
						+ " SU2.USERNAME OPENED_USER,"
						+ " AAST.CANCELED_DATE,"
						+ " AAST.CANCELED_BY,"
						+ " SU4.USERNAME CANCELED_USER,"
						+ " AASB.BATCH_ID,"
						+ " AASB.BATCH_NO,"
						+ " AASB.BATCH_REMARK,"
						+ " AASB.ORGANIZATION_ID SAMPLED_OU,"
						+ " EOCMI.COMPANY SAMPLED_OU_NAME"
						+ " FROM"
						+ " ETS_OU_CITY_MAP           EOCM,"
						+ " ETS_OU_CITY_MAP           EOCMI,"
						+ " SF_USER                   SU1,"
						+ " SF_USER                   SU2,"
						+ " SF_USER                   SU3,"
						+ " SF_USER                   SU4,"
						+ " AMS_ASSETS_SAMPLING_TASK  AAST,"
						+ " AMS_ASSETS_SAMPLING_BATCH AASB"
						+ " WHERE"
						+ " AAST.TASK_ID = AASB.TASK_ID"
						+ " AND AAST.CREATED_OU = EOCM.ORGANIZATION_ID"
						+ " AND AASB.ORGANIZATION_ID = EOCMI.ORGANIZATION_ID"
						+ " AND AAST.CREATED_BY = SU1.USER_ID"
						+ " AND AAST.OPENED_BY *= SU2.USER_ID"
						+ " AND AAST.CLOSED_BY *= SU3.USER_ID"
						+ " AND AAST.CANCELED_BY *= SU4.USER_ID"
						+ " AND AASB.BATCH_ID = ?";

		sqlArgs.add(dto.getBatchId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� taskId �����ѯ����SQL��
	 * ����Զ��������ݹ������� AMS_ASSETS_SAMPLING_BATCH��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param taskId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByTaskIdModel(String taskId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " BATCH_ID,"
						+ " BATCH_NO,"
                        + " BATCH_REMARK,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_BATCH"
						+ " WHERE"
						+ " TASK_ID = ?";
		sqlArgs.add(taskId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
		if (foreignKey.equals("taskId")) {
			sqlModel = getDataByTaskIdModel(dto.getTaskId());
		}
		return sqlModel;
	}


	/**
	 * ���ܣ�����Զ����ɹ������� AMS_ASSETS_SAMPLING_BATCHҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
			String sqlStr = "SELECT "
							+ " BATCH_ID,"
							+ " BATCH_NO,"
							+ " TASK_ID,"
							+ " BATCH_REMARK,"
							+ " CREATION_DATE,"
							+ " CREATED_BY,"
							+ " LAST_UPDATE_DATE,"
							+ " LAST_UPDATE_BY"
							+ " FROM"
							+ " AMS_ASSETS_SAMPLING_BATCH"
							+ " WHERE"
							+ " ( " + SyBaseSQLUtil.isNull() + "  OR BATCH_ID LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR BATCH_NO LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR TASK_ID LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR BATCH_REMARK LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
							+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
			sqlArgs.add(dto.getBatchId());
			sqlArgs.add(dto.getBatchId());
			sqlArgs.add(dto.getBatchNo());
			sqlArgs.add(dto.getBatchNo());
			sqlArgs.add(dto.getTaskId());
			sqlArgs.add(dto.getTaskId());
			sqlArgs.add(dto.getBatchRemark());
			sqlArgs.add(dto.getBatchRemark());
			sqlArgs.add(dto.getCreationDate());
			sqlArgs.add(dto.getCreationDate());
			sqlArgs.add(dto.getCreatedBy());
			sqlArgs.add(dto.getCreatedBy());
			sqlArgs.add(dto.getLastUpdateDate());
			sqlArgs.add(dto.getLastUpdateDate());
			sqlArgs.add(dto.getLastUpdateBy());
			sqlArgs.add(dto.getLastUpdateBy());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ������� AMS_ASSETS_SAMPLING_BATCHҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getDataByBatchNoModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " AAST.TASK_ID,"
						+ " AAST.TASK_NO,"
						+ " AAST.TASK_NAME,"
						+ " AAST.TASK_DESC,"
						+ " AAST.START_DATE,"
						+ " AAST.END_DATE,"
						+ " AAST.SAMPLING_TYPE,"
						+ " dbo.APP_GET_FLEX_VALUE(AAST.SAMPLING_TYPE, 'SAMPLING_TYPE') SAMPLING_TYPE_VALUE,"
						+ " AAST.SAMPLING_RATIO,"
						+ " AAST.CREATED_OU,"
						+ " EOCM.COMPANY CREATED_OU_NAME,"
						+ " AAST.CREATION_DATE,"
						+ " SU1.USERNAME CREATED_USER,"
						+ " AAST.LAST_UPDATE_DATE,"
						+ " AAST.LAST_UPDATE_BY,"
						+ " AAST.TASK_STATUS,"
						+ " dbo.APP_GET_FLEX_VALUE(AAST.TASK_STATUS, 'TASK_STATUS') TASK_STATUS_VALUE,"
						+ " AAST.CLOSED_DATE,"
						+ " AAST.CLOSED_BY,"
						+ " SU3.USERNAME CLOSED_USER,"
						+ " AAST.OPENED_DATE,"
						+ " AAST.OPENED_BY,"
						+ " SU2.USERNAME OPENED_USER,"
						+ " AAST.CANCELED_DATE,"
						+ " AAST.CANCELED_BY,"
						+ " SU4.USERNAME CANCELED_USER,"
						+ " AASB.BATCH_ID,"
						+ " AASB.BATCH_NO"
						+ " FROM"
						+ " ETS_OU_CITY_MAP           EOCM,"
						+ " SF_USER                   SU1,"
						+ " SF_USER                   SU2,"
						+ " SF_USER                   SU3,"
						+ " SF_USER                   SU4,"
						+ " AMS_ASSETS_SAMPLING_TASK  AAST,"
						+ " AMS_ASSETS_SAMPLING_BATCH AASB"
						+ " WHERE"
						+ " AAST.TASK_ID = AASB.TASK_ID"
						+ " AND AAST.CREATED_OU = EOCM.ORGANIZATION_ID"
						+ " AND AAST.CREATED_BY = SU1.USER_ID"
						+ " AND AAST.OPENED_BY *= SU2.USER_ID"
						+ " AND AAST.CLOSED_BY *= SU3.USER_ID"
						+ " AND AAST.CANCELED_BY *= SU4.USER_ID"
						+ " AND AASB.BATCH_NO = ?";

		sqlArgs.add(dto.getBatchNo());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
