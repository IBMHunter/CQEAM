package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamCheckTaskDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.FileException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.file.FileUtil;
import com.sino.base.util.ArrUtil;


/**
 * <p>Title: EamCheckTaskModel</p>
 * <p>Description:�����Զ�����SQL��������EamCheckTaskModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EamCheckTaskModel extends AMSSQLProducer {

/**
	 * ���ܣ��̵������ EAM_CHECK_TASK ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamCheckTaskDTO ���β���������
	 */
	public EamCheckTaskModel(SfUserDTO userAccount, EamCheckTaskDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������̵������ EAM_CHECK_TASK���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamCheckTaskDTO dto = (EamCheckTaskDTO) dtoParameter;
			String sqlStr = "INSERT INTO "
							+ " EAM_CHECK_TASK("
							+ " CHECK_TASK_ID,"
							+ " TASK_NAME,"
							+ " CHECK_TYPE,"
							+ " ORGANIZATION_ID,"
							+ " START_DATE,"
							+ " END_DATE,"
							+ " REMARK,"
							+ " TASK_STATUS,"
							+ " CREATION_DATE,"
							+ " CREATED_BY"
							+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";

			sqlArgs.add(dto.getCheckTaskId());
			sqlArgs.add(dto.getTaskName());
			sqlArgs.add(dto.getCheckType());
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getEndDate());
			sqlArgs.add(dto.getRemark());
			sqlArgs.add(dto.getTaskStatus());
			sqlArgs.add(userAccount.getUserId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������̵������ EAM_CHECK_TASK���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamCheckTaskDTO dto = (EamCheckTaskDTO) dtoParameter;
			String sqlStr = "UPDATE EAM_CHECK_TASK"
							+ " SET"
							+ " START_DATE = ?,"
							+ " END_DATE = ?,"
							+ " TASK_STATUS = ?,"
							+ " REMARK = ?,"
							+ " LAST_UPDATE_DATE = GETDATE(),"
							+ " LAST_UPDATE_BY = ?"
							+ " WHERE"
							+ " CHECK_TASK_ID = ?";

			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getEndDate());
			sqlArgs.add(dto.getTaskStatus());
			sqlArgs.add(dto.getRemark());
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getCheckTaskId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}


	/**
	 * ���ܣ�����Զ������̵������ EAM_CHECK_TASK������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamCheckTaskDTO dto = (EamCheckTaskDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " ECT.CHECK_TASK_ID,"
						+ " ECT.TASK_NAME,"
						+ " ECT.CHECK_TYPE,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(ECT.CHECK_TYPE, 'CHECK_TYPE') CHECK_TYPE_VALUE,"
						+ " ECT.ORGANIZATION_ID,"
						+ " AMS_PUB_PKG.GET_ORGNIZATION_NAME(ECT.ORGANIZATION_ID) ORGNIZATION_NAME,"
						+ " ECT.START_DATE,"
						+ " ECT.END_DATE,"
						+ " ECT.REMARK,"
						+ " ECT.CREATION_DATE,"
						+ " ECT.CREATED_BY,"
						+ " AMS_PUB_PKG.GET_USER_NAME(ECT.CREATED_BY) CREATED_USER,"
						+ " ECT.LAST_UPDATE_DATE,"
						+ " ECT.LAST_UPDATE_BY,"
						+ " AMS_PUB_PKG.GET_USER_NAME(ECT.LAST_UPDATE_BY) UPDATE_USER,"
						+ " ECT.TASK_STATUS,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(ECT.TASK_STATUS, 'TASK_STATUS') TASK_STATUS_VALUE"
						+ " FROM"
						+ " EAM_CHECK_TASK ECT"
						+ " WHERE"
						+ " ECT.CHECK_TASK_ID = ?";
		sqlArgs.add(dto.getCheckTaskId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


/**
	 * ���ܣ�����Զ������̵������ EAM_CHECK_TASKҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamCheckTaskDTO dto = (EamCheckTaskDTO) dtoParameter;
			String sqlStr = "SELECT"
							+ " ECT.CHECK_TASK_ID,"
							+ " ECT.TASK_NAME,"
							+ " ECT.CHECK_TYPE,"
							+ " AMS_PUB_PKG.GET_FLEX_VALUE(ECT.CHECK_TYPE, 'CHECK_TYPE') CHECK_TYPE_VALUE,"
							+ " ECT.ORGANIZATION_ID,"
							+ " AMS_PUB_PKG.GET_ORGNIZATION_NAME(ECT.ORGANIZATION_ID) ORGNIZATION_NAME,"
							+ " ECT.START_DATE,"
							+ " ECT.END_DATE,"
							+ " ECT.REMARK,"
							+ " ECT.CREATION_DATE,"
							+ " ECT.CREATED_BY,"
							+ " AMS_PUB_PKG.GET_USER_NAME(ECT.CREATED_BY) CREATED_USER,"
							+ " ECT.LAST_UPDATE_DATE,"
							+ " ECT.LAST_UPDATE_BY,"
							+ " AMS_PUB_PKG.GET_USER_NAME(ECT.LAST_UPDATE_BY) UPDATE_USER,"
							+ " ECT.TASK_STATUS,"
							+ " AMS_PUB_PKG.GET_FLEX_VALUE(ECT.TASK_STATUS, 'TASK_STATUS') TASK_STATUS_VALUE"
							+ " FROM"
							+ " EAM_CHECK_TASK ECT"
							+ " WHERE"
							+ " ECT.CHECK_TYPE = dbo.NVL(?, ECT.CHECK_TYPE)"
							+ " AND ECT.TASK_STATUS = dbo.NVL(?, ECT.TASK_STATUS)"
							+ " AND ECT.TASK_NAME LIKE dbo.NVL(?, ECT.TASK_NAME)"
							+ " AND ECT.CREATION_DATE >= ISNULL(?, ECT.CREATION_DATE)"
							+ " AND ECT.CREATION_DATE <= ISNULL(?, ECT.CREATION_DATE)"
							+ " AND ECT.START_DATE >= ISNULL(?, ECT.START_DATE)"
							+ " AND ECT.START_DATE <= ISNULL(?, ECT.START_DATE)"
							+ " AND ECT.ORGANIZATION_ID = ISNULL(?, ECT.ORGANIZATION_ID)";

			sqlArgs.add(dto.getCheckType());
			sqlArgs.add(dto.getTaskStatus());
			sqlArgs.add(dto.getTaskName());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());

			sqlArgs.add(dto.getFromDate());
			sqlArgs.add(dto.getToDate());
			sqlArgs.add(dto.getOrganizationId());

			if(!userAccount.isProvinceUser()){
				sqlStr += " AND ECT.ORGANIZATION_ID = ?";
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����ȡ������
	 * @param taskIdArr String[]
	 * @return SQLModel
	 */
	public SQLModel getMulTaskCancelModel(String[] taskIdArr){
		SQLModel sqlModel = new SQLModel();
		String taskIds = ArrUtil.arrToSqlStr(taskIdArr);
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " EAM_CHECK_TASK ECT"
						+ " SET"
						+ " ECT.TASK_STATUS      = ?,"
						+ " ECT.LAST_UPDATE_DATE = GETDATE(),"
						+ " ECT.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " ECT.TASK_STATUS = ?"
						+ " AND ECT.CHECK_TASK_ID IN (" + taskIds + ")";
		sqlArgs.add(LvecDicts.TSK_STATUS1_CANCELED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(LvecDicts.TSK_STATUS1_SAVE_TEMP);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����ܣ�����ȷ������
	 * @param taskIdArr String[]
	 * @return SQLModel
	 */
	public SQLModel getMulTaskSubmitModel(String[] taskIdArr){
		SQLModel sqlModel = new SQLModel();
		String taskIds = ArrUtil.arrToSqlStr(taskIdArr);
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " EAM_CHECK_TASK ECT"
						+ " SET"
						+ " ECT.TASK_STATUS      = ?,"
						+ " ECT.LAST_UPDATE_DATE = GETDATE(),"
						+ " ECT.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " ECT.TASK_STATUS = ?"
						+ " AND ECT.CHECK_TASK_ID IN (" + taskIds + ")";
		sqlArgs.add(LvecDicts.TSK_STATUS1_OPENING);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(LvecDicts.TSK_STATUS1_SAVE_TEMP);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡͬһʱ������Ƿ������������SQL
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getDupicateTaskModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamCheckTaskDTO dto = (EamCheckTaskDTO) dtoParameter;
			String taskId = dto.getCheckTaskId();
			String sqlStr = "";
			if(taskId.equals("")){
				sqlStr = "SELECT"
						 + " 1"
						 + " FROM"
						 + " EAM_CHECK_TASK ECT"
						 + " WHERE"
						 + " ECT.START_DATE >= ?"
						 + " AND ECT.END_DATE <= ?"
						 + " AND ECT.CHECK_TYPE = ?";
				sqlArgs.add(dto.getStartDate());
				sqlArgs.add(dto.getSQLEndDate());
				sqlArgs.add(dto.getCheckType());
			} else {
				sqlStr = "SELECT"
						 + " 1"
						 + " FROM"
						 + " EAM_CHECK_TASK ECT"
						 + " WHERE"
						 + " ECT.START_DATE >= ?"
						 + " AND ECT.END_DATE <= ?"
						 + " AND ECT.CHECK_TASK_ID <> ?"
						 + " AND ECT.CHECK_TYPE = ?";
				sqlArgs.add(dto.getStartDate());
				sqlArgs.add(dto.getSQLEndDate());
				sqlArgs.add(dto.getCheckTaskId());
				sqlArgs.add(dto.getCheckType());
			}

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
			try {
				FileUtil.saveStrContent(sqlModel.toString(), "C:\\dup.sql");
			} catch (FileException ex1) {
			}
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}
