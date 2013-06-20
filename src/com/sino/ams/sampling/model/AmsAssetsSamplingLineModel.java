package com.sino.ams.sampling.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsAssetsSamplingLineModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsSamplingLineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsSamplingLineModel extends AMSSQLProducer {

	/**
	 * ���ܣ���鹤���б� AMS_ASSETS_SAMPLING_LINE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsSamplingLineDTO ���β���������
	 */
	public AmsAssetsSamplingLineModel(SfUserDTO userAccount, AmsAssetsSamplingLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɳ�鹤���б� AMS_ASSETS_SAMPLING_LINE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO) dtoParameter;
		String sqlStr = "INSERT INTO"
						+ " AMS_ASSETS_SAMPLING_LINE ("
						+ " HEADER_ID,"
						+ " BATCH_ID,"
						+ " TASK_ID,"
						+ " BARCODE,"
						+ " SYSTEM_STATUS,"
						+ " ITEM_CODE,"
						+ " ITEM_CATEGORY,"
						+ " ITEM_NAME,"
						+ " ITEM_SPEC,"
						+ " START_DATE,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_DEPT,"
						+ " MAINTAIN_USER)("
						+ " SELECT"
						+ " AASH.HEADER_ID,"
						+ " AASH.BATCH_ID,"
						+ " AASH.TASK_ID,"
						+ " EII.BARCODE,"
						+ " 'Y',"
						+ " ESI.ITEM_CODE,"
						+ " ESI.ITEM_CATEGORY,"
						+ " ESI.ITEM_NAME,"
						+ " ESI.ITEM_SPEC,"
						+ " EII.START_DATE,"
						+ " EII.RESPONSIBILITY_USER,"
						+ " EII.RESPONSIBILITY_DEPT,"
						+ " EII.MAINTAIN_USER"
						+ " FROM"
						+ " ETS_ITEM_INFO              EII,"
						+ " ETS_SYSTEM_ITEM            ESI,"
						+ " AMS_OBJECT_ADDRESS         AOA,"
						+ " ETS_OBJECT                 EO,"
						+ " AMS_ASSETS_SAMPLING_HEADER AASH"
						+ " WHERE"
						+ " EII.ADDRESS_ID = AOA.ADDRESS_ID"
						+ " AND EII.ITEM_CODE = ESI.ITEM_CODE"
						+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO"
						+ " AND AOA.BOX_NO = '0000'"
						+ " AND AOA.NET_UNIT = '0000'"
						+ " AND EO.WORKORDER_OBJECT_NO = AASH.SAMPLING_LOCATION"
						+ " AND NOT EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_LINE AASL"
						+ " WHERE"
						+ " AASL.HEADER_ID = AASH.HEADER_ID"
						+ " AND AASL.BARCODE = EII.BARCODE)"
						+ " AND AASH.HEADER_ID = ?)";

		sqlArgs.add(dto.getHeaderId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� headerId �����ѯ����SQL��
	 * ����Զ��������ݳ�鹤���б� AMS_ASSETS_SAMPLING_LINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param headerId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByHeaderIdModel(String headerId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AASL.HEADER_ID,"
						+ " AASL.TASK_ID,"
						+ " AASL.BARCODE,"
                        + " CASE AASL.SYSTEM_STATUS WHEN 'Y' THEN '��' ELSE '��' END SYSTEM_STATUS,"
                        + " CASE AASL.SCAN_STATUS WHEN 'Y' THEN '��' ELSE '��' END SCAN_STATUS,"
						+ " AASL.REMARK,"
						+ " AASL.ITEM_CODE,"
						+ " AASL.ITEM_CATEGORY,"
						+ " dbo.APP_GET_FLEX_VALUE(AASL.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY_VALUE,"
						+ " AASL.ITEM_NAME,"
						+ " AASL.ITEM_SPEC,"
						+ " AASL.START_DATE,"
						+ " AASL.RESPONSIBILITY_USER,"
						+ " AMES.USER_NAME RESPONSIBILITY_USER_NAME,"
						+ " AASL.RESPONSIBILITY_DEPT,"
						+ " AMDS.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
						+ " AASL.MAINTAIN_USER,"
						+ " AASL.SCAN_ITEM_CODE,"
						+ " AASL.SCAN_ITEM_CATEGORY,"
						+ " dbo.APP_GET_FLEX_VALUE(AASL.SCAN_ITEM_CATEGORY, 'ITEM_TYPE') SCAN_ITEM_CATEGORY_VALUE,"
						+ " AASL.SCAN_ITEM_NAME,"
						+ " AASL.SCAN_ITEM_SPEC,"
						+ " AASL.SCAN_START_DATE,"
						+ " AASL.SCAN_RESPONSIBILITY_USER,"
						+ " AMEC.USER_NAME SCAN_RESPONSIBILITY_USER_NAME,"
						+ " AASL.SCAN_RESPONSIBILITY_DEPT,"
						+ " AMDC.DEPT_NAME SCAN_RESPONSIBILITY_DEPT_NAME,"
						+ " AASL.SCAN_MAINTAIN_USER"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_LINE AASL,"
						+ " AMS_MIS_EMPLOYEE         AMES,"
						+ " AMS_MIS_DEPT             AMDS,"
						+ " AMS_MIS_EMPLOYEE         AMEC,"
						+ " AMS_MIS_DEPT             AMDC"
						+ " WHERE"
						+ " AASL.RESPONSIBILITY_USER *= AMES.EMPLOYEE_ID"
						+ " AND AASL.RESPONSIBILITY_DEPT *= AMDS.DEPT_CODE"
						+ " AND AASL.SCAN_RESPONSIBILITY_USER *= AMEC.EMPLOYEE_ID"
						+ " AND AASL.SCAN_RESPONSIBILITY_DEPT *= AMDC.DEPT_CODE"
						+ " AND AASL.HEADER_ID = ?";
		sqlArgs.add(headerId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� taskId �����ѯ����SQL��
	 * ����Զ��������ݳ�鹤���б� AMS_ASSETS_SAMPLING_LINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param taskId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByTaskIdModel(String taskId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " HEADER_ID,"
						+ " BARCODE,"
						+ " SYSTEM_STATUS,"
						+ " SCAN_STATUS,"
						+ " REMARK,"
						+ " ITEM_CODE,"
						+ " ITEM_CATEGORY,"
						+ " ITEM_NAME,"
						+ " ITEM_SPEC,"
						+ " START_DATE,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_DEPT,"
						+ " MAINTAIN_USER,"
						+ " SCAN_ITEM_CODE,"
						+ " SCAN_ITEM_CATEGORY,"
						+ " SCAN_ITEM_NAME,"
						+ " SCAN_ITEM_SPEC,"
						+ " SCAN_START_DATE,"
						+ " SCAN_RESPONSIBILITY_USER,"
						+ " SCAN_RESPONSIBILITY_DEPT,"
						+ " SCAN_MAINTAIN_USER"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_LINE"
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
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO) dtoParameter;
		if (foreignKey.equals("headerId")) {
			sqlModel = getDataByHeaderIdModel(dto.getHeaderId());
		} else if (foreignKey.equals("taskId")) {
			sqlModel = getDataByTaskIdModel(dto.getTaskId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� headerId ��������ɾ��SQL��
	 * ����Զ��������ݳ�鹤���б� AMS_ASSETS_SAMPLING_LINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param headerId String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByHeaderIdModel(String headerId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_LINE"
						+ " WHERE"
						+ " HEADER_ID = ?";
		sqlArgs.add(headerId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� batchId ��������ɾ��SQL��
	 * ����Զ��������ݳ�鹤���б� AMS_ASSETS_SAMPLING_LINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param batchId String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByBatchIdModel(String batchId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " AMS_ASSETS_SAMPLING_LINE"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " 1"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_HEADER AASH"
						+ " WHERE"
						+ " HEADER_ID = AASH.HEADER_ID"
						+ " AND AASH.ORDER_STATUS = 'SAVE_TEMP')"
						+ " AND BATCH_ID = ?";
		sqlArgs.add(batchId);

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
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO) dtoParameter;
		if (foreignKey.equals("headerId")) {
			sqlModel = getDeleteByHeaderIdModel(dto.getHeaderId());
		} else if (foreignKey.equals("batchId")) {
			sqlModel = getDeleteByBatchIdModel(dto.getBatchId());
		}
		return sqlModel;
	}
}
