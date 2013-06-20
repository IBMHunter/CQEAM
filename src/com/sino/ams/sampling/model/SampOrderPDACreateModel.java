package com.sino.ams.sampling.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SampOrderPDACreateModel extends AMSSQLProducer {

	public SampOrderPDACreateModel(BaseUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ���������������
	 * @return SQLModel
	 */
	public SQLModel getOrderBatchCreateModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO"
						+ " AMS_ASSETS_SAMPLING_BATCH("
						+ " BATCH_ID,"
						+ " BATCH_NO,"
						+ " TASK_ID,"
						+ " BATCH_REMARK,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " ORGANIZATION_ID,"
						+ " CREATED_OU"
						+ " ) VALUES (?, ?, ?, ?, GETDATE(), ?, ?, ?)";
		sqlArgs.add(dto.getBatchId());
		sqlArgs.add(dto.getBatchNo());
		sqlArgs.add(dto.getTaskId());
		sqlArgs.add(dto.getBatchRemark());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getOrganizationId());
		sqlArgs.add(userAccount.getOrganizationId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ���������ͷ
	 * @return SQLModel
	 */
	public SQLModel getOrderHeaderCreateModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO"
						+ " AMS_ASSETS_SAMPLING_HEADER("
						+ " HEADER_ID,"
						+ " TASK_ID,"
						+ " SAMPLING_LOCATION,"
						+ " ORDER_NO,"
						+ " IMPLEMENT_BY,"
						+ " START_TIME,"
						+ " IMPLEMENT_DAYS,"
						+ " DISTRIBUTE_DATE,"
						+ " DISTRIBUTE_BY,"
						+ " ORGANIZATION_ID,"
						+ " ORDER_STATUS,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " ORDER_TYPE,"
						+ " BATCH_ID,"
						+ " CREATED_OU"
						+ " ) VALUES (?, ?, ?, ?, ?, GETDATE(), 1, GETDATE(), ?, ?, ?, GETDATE(), ?, ?, ?, ?)";

		sqlArgs.add(dto.getHeaderId());
		sqlArgs.add(dto.getTaskId());
		sqlArgs.add(dto.getSamplingLocation());
		sqlArgs.add(dto.getOrderNo());
		sqlArgs.add(userAccount.getUserId());

		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getOrganizationId());

		sqlArgs.add(SamplingDicts.ORDER_STS1_DISTRIBUTED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(SamplingDicts.ORDR_NO_MARK);

		sqlArgs.add(dto.getBatchId());
		sqlArgs.add(userAccount.getOrganizationId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����������
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getOrderLineCreateModel() throws SQLModelException {
		AmsAssetsSamplingHeaderDTO orderHeader = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		AmsAssetsSamplingLineDTO dto = new AmsAssetsSamplingLineDTO();
		dto.setHeaderId(orderHeader.getHeaderId());
		AmsAssetsSamplingLineModel modelProducer = new AmsAssetsSamplingLineModel(userAccount, dto);
		modelProducer.setServletConfig(servletConfig);
		return modelProducer.getDataCreateModel();
	}

	/**
	 * ���ܣ���ȡ�ж����ڵص��Ƿ�������δ�鵵������SQL
	 * @return SQLModel
	 */
	public SQLModel getHasPreviousOrderModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " 1"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_HEADER AASH,"
						+ " AMS_ASSETS_CHECK_BATCH     AACB"
						+ " WHERE"
						+ " AASH.ORDER_STATUS IN ('SAVE_TEMP', 'DISTRIBUTED', 'DOWNLOADED')"
						+ " AND AASH.SAMPLING_LOCATION = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getSamplingLocation());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ���ص��OU��֯ID
	 * @return SQLModel
	 */
	public SQLModel getOtherDataModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ "  NEWID()  BATCH_ID,"
						+ "  NEWID()  HEADER_ID,"
						+ " EO.ORGANIZATION_ID,"
						+ " AAST.TASK_ID"
						+ " FROM"
						+ " ETS_OBJECT               EO,"
						+ " AMS_ASSETS_SAMPLING_TASK AAST"
						+ " WHERE"
						+ " EO.WORKORDER_OBJECT_NO = ?"
						+ " AND AAST.TASK_NO = ?";

		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getSamplingLocation());
		sqlArgs.add(dto.getTaskNo());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
