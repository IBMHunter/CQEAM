package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.dzyh.constant.DzyhActionConstant;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhCheckHeaderModel</p>
 * <p>Description:�����Զ�����SQL��������EamDhCheckHeaderModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class EamDhCheckHeaderModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ֵ�׺��̵㹤��ͷ�� EAM_DH_CHECK_HEADER ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCheckHeaderDTO ���β���������
	 */
	public EamDhCheckHeaderModel(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺��̵㹤��ͷ�� EAM_DH_CHECK_HEADER���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
			String sqlStr = "INSERT INTO "
							+ " EAM_DH_CHECK_HEADER("
							+ " HEADER_ID,"
							+ " BATCH_ID,"
							+ " CHECK_TASK_ID,"
							+ " CHECK_LOCATION,"
							+ " ORDER_NO,"
							+ " IMPLEMENT_BY,"
							+ " START_TIME,"
							+ " IMPLEMENT_DAYS,"
							+ " DISTRIBUTE_DATE,"
							+ " DISTRIBUTE_BY,"
							+ " ORGANIZATION_ID,"
							+ " ORDER_STATUS,"
							+ " ORDER_TYPE,"
							+ " CHECK_TOOLS,"
							+ " CREATION_DATE,"
							+ " CREATED_BY"
							+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";
			String act = dto.getAct();
			if (act.equals(DzyhActionConstant.SAVE_ACTION)) {//�ݴ湤��
				dto.setDistributeBy( -1 );
				dto.setDistributeDate("");
				dto.setOrderStatus(LvecDicts.ORDER_STS1_SAVE_TEMP);
			} else if (act.equals(DzyhActionConstant.DISTRI_ORDER)) {//�·�����
				dto.setDistributeBy(userAccount.getUserId());
				dto.setCurrCalendar("distributeDate");
				dto.setOrderStatus(LvecDicts.ORDER_STS1_DISTRIBUTED);
			}
			sqlArgs.add(dto.getHeaderId());
			sqlArgs.add(dto.getBatchId());
			sqlArgs.add(dto.getCheckTaskId());
			sqlArgs.add(dto.getCheckLocation());
			sqlArgs.add(dto.getOrderNo());
			sqlArgs.add(dto.getImplementBy());
			sqlArgs.add(dto.getStartTime());
			sqlArgs.add(dto.getImplementDays());
			sqlArgs.add(dto.getDistributeDate());
			sqlArgs.add(dto.getDistributeBy());
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(dto.getOrderStatus());
			sqlArgs.add(dto.getOrderType());
			sqlArgs.add(dto.getCheckTools());
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
	 * ���ܣ�����Զ����ɵ�ֵ�׺��̵㹤��ͷ�� EAM_DH_CHECK_HEADER������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " ECT.TASK_NAME,"
						+ " ECT.CHECK_TYPE,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(ECT.CHECK_TYPE, 'CHECK_TYPE') CHECK_TYPE_VALUE,"
						+ " ECT.START_DATE,"
						+ " ECT.END_DATE,"
						+ " EDCB.BATCH_NO,"
						+ " EDCH.HEADER_ID,"
						+ " EDCH.ORDER_NO,"
						+ " EDCH.START_TIME,"
						+ " EDCH.ORDER_STATUS,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_STATUS, 'CHKORDER_STATUS') ORDER_STATUS_VALUE,"
						+ " EDCH.ORDER_TYPE,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_TYPE, 'ORDER_TYPE_ASSETS') ORDER_TYPE_VALUE,"
						+ " EO.LOCATION_CODE,"
						+ " EO.WORKORDER_OBJECT_NAME LOCATION_NAME,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.CREATED_BY) CREATED_USER,"
						+ " EDCH.CREATION_DATE,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.DISTRIBUTE_BY) DISTRIBUTE_USER,"
						+ " EDCH.DISTRIBUTE_DATE,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.DOWNLOAD_BY) DOWNLOAD_USER,"
						+ " EDCH.DOWNLOAD_DATE,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.UPLOAD_BY) UPLOAD_USER,"
						+ " EDCH.UPLOAD_DATE,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.ARCHIVED_BY) ARCHIVED_USER,"
						+ " EDCH.ARCHIVED_DATE,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.IMPLEMENT_BY) IMPLEMENT_USER,"
						+ " EDCH.IMPLEMENT_DAYS,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.CHECK_TOOLS, 'CHECK_TOOLS') CHECK_TOOLS_VALUE,"
						+ " AMD.DEPT_NAME IMP_DEPT_NAME"
						+ " FROM"
						+ " AMS_MIS_DEPT        AMD,"
						+ " ETS_OBJECT          EO,"
						+ " EAM_CHECK_TASK      ECT,"
						+ " EAM_DH_CHECK_BATCH  EDCB,"
						+ " EAM_DH_CHECK_HEADER EDCH"
						+ " WHERE"
						+ " EDCH.BATCH_ID = EDCB.BATCH_ID"
						+ " AND EDCB.CHECK_TASK_ID = ECT.CHECK_TASK_ID"
						+ " AND EDCH.CHECK_LOCATION = EO.WORKORDER_OBJECT_NO"
						+ " AND EO.DEPT_CODE = AMD.DEPT_CODE"
						+ " AND EDCH.HEADER_ID = ?";
		sqlArgs.add(dto.getHeaderId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�����ҳ�淭ҳ��ѯʱ����Ҫ��SQLModel
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPageQueryModel() throws SQLModelException{
		SQLModel sqlModel = new SQLModel();
		try {
			EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
			List sqlArgs = new ArrayList();
			String sqlStr = "SELECT"
							+ " ECT.TASK_NAME,"
							+ " ECT.START_DATE,"
							+ " ECT.END_DATE,"
							+ " EDCB.BATCH_NO,"
							+ " EDCH.HEADER_ID,"
							+ " EDCH.ORDER_NO,"
							+ " EO.WORKORDER_OBJECT_NAME LOCATION_NAME,"
							+ " EDCH.START_TIME,"
							+ " EDCH.IMPLEMENT_DAYS,"
							+ " SU.USERNAME IMPLEMENT_USER,"
							+ " EDCH.ORDER_TYPE,"
							+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_TYPE, 'ORDER_TYPE_ASSETS') ORDER_TYPE_VALUE,"
							+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.CHECK_TOOLS, 'CHECK_TOOLS') CHECK_TOOLS_VALUE,"
							+ " EDCH.ORDER_STATUS,"
							+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_STATUS, 'CHKORDER_STATUS') ORDER_STATUS_VALUE,"
							+ " EDCH.DISTRIBUTE_DATE,"
							+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.DISTRIBUTE_BY) DISTRIBUTE_USER,"
							+ " EDCH.DOWNLOAD_DATE,"
							+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.DOWNLOAD_BY) DOWNLOAD_USER,"
							+ " EDCH.SCANOVER_DATE,"
							+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.SCANOVER_BY) SCANOVER_USER,"
							+ " EDCH.UPLOAD_DATE,"
							+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.UPLOAD_BY) UPLOAD_USER,"
							+ " EDCH.ARCHIVED_DATE,"
							+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.ARCHIVED_BY) ARCHIVED_USER"
							+ " FROM"
							+ " ETS_OBJECT          EO,"
							+ " SF_USER             SU,"
							+ " EAM_CHECK_TASK      ECT,"
							+ " EAM_DH_CHECK_BATCH  EDCB,"
							+ " EAM_DH_CHECK_HEADER EDCH"
							+ " WHERE"
							+ " ECT.CHECK_TASK_ID = EDCB.CHECK_TASK_ID"
							+ " AND EDCB.BATCH_ID = EDCH.BATCH_ID"
							+ " AND EDCH.CHECK_LOCATION = EO.WORKORDER_OBJECT_NO"
							+ " AND EO.WORKORDER_OBJECT_NAME = dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)"
							+ " AND EO.DEPT_CODE = dbo.NVL(?, EO.DEPT_CODE)"
							+ " AND EDCH.IMPLEMENT_BY = SU.USER_ID"
							+ " AND EDCH.ORDER_NO LIKE dbo.NVL(?, EDCH.ORDER_NO)"
							+ " AND SU.USERNAME LIKE dbo.NVL(?, SU.USERNAME)"
							+ " AND ((ECT.START_DATE >= ISNULL(?, ECT.START_DATE)"
							+ " AND ECT.START_DATE <= ISNULL(?, ECT.START_DATE))"
							+ " OR (ECT.END_DATE >= ISNULL(?, ECT.END_DATE)"
							+ " AND ECT.END_DATE <= ISNULL(?, ECT.END_DATE)))"
							+ " AND EDCH.ORDER_TYPE = dbo.NVL(?, EDCH.ORDER_TYPE)"
							+ " AND ECT.TASK_NAME LIKE dbo.NVL(?, ECT.TASK_NAME)";

			sqlArgs.add(dto.getLocationName());
			sqlArgs.add(dto.getImpDeptCode());
			sqlArgs.add(dto.getOrderNo());
			sqlArgs.add(dto.getImplementUser());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getOrderType());
			sqlArgs.add(dto.getTaskName());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ����������������ɾ��SQL
	 * @param foreignKey String
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
		if(foreignKey.equals("batchId")){
			sqlModel = getDeleteByBatchIdModel(dto.getBatchId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ����ݹ�����ID���칤��ɾ��SQL
	 * @param batchId String
	 * @return SQLModel
	 */
	private SQLModel getDeleteByBatchIdModel(String batchId) {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "DELETE FROM"
						+ " EAM_DH_CHECK_HEADER EDCH"
						+ " WHERE"
						+ " EDCH.BATCH_ID = ?";

		List sqlArgs = new ArrayList();
		sqlArgs.add(batchId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����������칤�����ݻ�ȡSQL
	 * @param foreignKey String
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
		if(foreignKey.equals("batchId")){
			sqlModel = getDataByBatchIdModel(dto.getBatchId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ����ݹ�����ID���칤�����ݻ�ȡSQL
	 * @param batchId String
	 * @return SQLModel
	 */
	private SQLModel getDataByBatchIdModel(String batchId) {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " ECT.TASK_NAME,"
						+ " EDCH.HEADER_ID,"
						+ " EDCH.ORDER_NO,"
						+ " EDCH.START_TIME,"
						+ " EDCH.ORDER_STATUS,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_STATUS, 'CHKORDER_STATUS') ORDER_STATUS_VALUE,"
						+ " EDCH.ORDER_TYPE,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_TYPE, 'ORDER_TYPE_ASSETS') ORDER_TYPE_VALUE,"
						+ " EDCH.CHECK_LOCATION,"
						+ " EO.LOCATION_CODE,"
						+ " EO.WORKORDER_OBJECT_NAME LOCATION_NAME,"
						+ " EDCH.IMPLEMENT_BY,"
						+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.IMPLEMENT_BY) IMPLEMENT_USER,"
						+ " EDCH.IMPLEMENT_DAYS,"
						+ " EDCH.CHECK_TOOLS,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.CHECK_TOOLS, 'CHECK_TOOLS') CHECK_TOOLS_VALUE,"
						+ " AMD.DEPT_CODE IMP_DEPT_CODE,"
						+ " AMD.DEPT_NAME IMP_DEPT_NAME"
						+ " FROM"
						+ " AMS_MIS_DEPT        AMD,"
						+ " ETS_OBJECT          EO,"
						+ " EAM_CHECK_TASK      ECT,"
						+ " EAM_DH_CHECK_HEADER EDCH"
						+ " WHERE"
						+ " ECT.CHECK_TASK_ID = EDCH.CHECK_TASK_ID"
						+ " AND EDCH.CHECK_LOCATION = EO.WORKORDER_OBJECT_NO"
						+ " AND EO.DEPT_CODE = AMD.DEPT_CODE"
						+ " AND EDCH.BATCH_ID = ?";

		List sqlArgs = new ArrayList();
		sqlArgs.add(batchId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�������������������SQL
	 * @param batchIdArr String[]
	 * @return SQLModel
	 */
	public SQLModel getOrderCancelByBatchIdsModel(String[] batchIdArr){
		SQLModel sqlModel = new SQLModel();
		String batchIds = ArrUtil.arrToSqlStr(batchIdArr);
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " EAM_DH_CHECK_HEADER EDCH"
						+ " SET"
						+ " EDCH.ORDER_STATUS     = ?,"
						+ " EDCH.LAST_UPDATE_DATE = GETDATE(),"
						+ " EDCH.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EDCH.BATCH_ID IN (" + batchIds + ")"
						+ " AND EDCH.ORDER_STATUS = ?";

		sqlArgs.add(LvecDicts.ORDER_STS1_CANCELED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(LvecDicts.ORDER_STS1_SAVE_TEMP);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ������ȡ����״̬SQL
	 * @return SQLModel
	 */
	public SQLModel getCheckToolsModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EFV.CODE,"
						+ " EFV.VALUE"
						+ " FROM"
						+ " ETS_FLEX_VALUE_SET EFVS,"
						+ " ETS_FLEX_VALUES    EFV"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFVS.CODE = ?";

		sqlArgs.add(LvecDicts.CHECK_TOOLS);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}



	/**
	 * ���ܣ�������ݹ�����Ż�ȡ����ID��������ŵ�SQL
	 * @return SQLModel
	 */
	public SQLModel getOrderByNoModel() {
		SQLModel sqlModel = new SQLModel();
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EDCH.HEADER_ID,"
						+ " EDCH.ORDER_NO"
						+ " FROM"
						+ " EAM_DH_CHECK_HEADER EDCH"
						+ " WHERE"
						+ " EDCH.ORDER_NO = ?";
		sqlArgs.add(dto.getOrderNo());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
