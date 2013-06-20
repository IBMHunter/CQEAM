package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.dzyh.dto.EamCheckImplementDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamCheckImplementModel</p>
 * <p>Description:�����Զ�����SQL������"EamCheckImplementModel"���������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamCheckImplementModel extends AMSSQLProducer {

	/**
	 * ���ܣ�����ִ�в�ѯ  ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamCheckImplementDTO ���β���������
	 */
	public EamCheckImplementModel(SfUserDTO userAccount, EamCheckImplementDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�������ʧ�豸   ������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
//		EamCheckImplementDTO dto = (EamCheckImplementDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EII.SYSTEMID,"
			+ " ESI.ITEM_CATEGORY2,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME,"
			+ " ESI.ITEM_SPEC,"
			+ " EII.ITEM_QTY,"
			+ " EII.PRICE,"
			+ " EII.RESPONSIBILITY_DEPT,"
			+ " AMD.DEPT_NAME,"
			+ " EII.RESPONSIBILITY_USER,"
			+ " AME.USER_NAME,"
			+ " EII.ADDRESS_ID,"
			+ " EO.WORKORDER_OBJECT_NAME,"
			+ " EII.MAINTAIN_USER,"
			+ " EII.LAST_LOC_CHG_DATE,"
			+ " EII.ATTRIBUTE3,"
			+ " EII.REMARK"
			+ " FROM"
			+ " EAM_DH_CHG_LOG EDCL,"
			+ " ETS_ITEM_INFO EII,"
			+ " ETS_SYSTEM_ITEM ESI,"
			+ " AMS_OBJECT_ADDRESS AOA,"
			+ " ETS_OBJECT EO"
			+ " WHERE"
			+ " ESI.ITEM_CODE=EII.ITEM_CODE"
			+ " AND EII.RESPONSIBILITY_DEPT=AMD.DEPT_CODE"
			+ " AND EII.ADDRESS_ID=AOA.ADDRESS_ID"
			+ " AND AOA.BOX_NO='0000'"
			+ " AND AOA.NET_UNIT='0000'"
			+ " AND EII.BARCODE = ?";
//		sqlArgs.add(dto.getBarcode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����ִ�в�ѯ  ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamCheckImplementDTO dto = (EamCheckImplementDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " EDCH.HEADER_ID,"
				+ " EDCH.CHECK_TASK_ID,"
				+ " ECT.TASK_NAME,"
				+ " EDCH.ORDER_NO,"
				+ " AMD.DEPT_NAME,"
				+ " EDCH.CHECK_LOCATION,"
				+ " EO.WORKORDER_OBJECT_NAME,"
				+ " EDCH.IMPLEMENT_DAYS,"
				+ " AMS_PUB_PKG.GET_USER_NAME(EDCH.IMPLEMENT_BY) USER_NAME,"
				+ " EDCH.DOWNLOAD_DATE,"
				+ " EDCH.UPLOAD_DATE,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(EDCH.ORDER_STATUS,'CHKORDER_STATUS') ORDER_STATUS"
				+ " FROM"
				+ " EAM_CHECK_TASK 		ECT,"
				+ " EAM_DH_CHECK_HEADER EDCH,"
				+ " ETS_OBJECT 			EO,"
				+ " AMS_OBJECT_ADDRESS  AOA,"
				+ " AMS_MIS_DEPT		AMD"
				+ " WHERE"
				+ " ECT.CHECK_TASK_ID=EDCH.CHECK_TASK_ID"
				+ " AND EDCH.CHECK_LOCATION=AOA.ADDRESS_ID"
				+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
				+ " AND AOA.BOX_NO='0000'"
				+ " AND AOA.NET_UNIT='0000'"
				+ " AND EO.DEPT_CODE=AMD.DEPT_CODE"
				+ "	AND EDCH.CHECK_TASK_ID = dbo.NVL(?, EDCH.CHECK_TASK_ID)"
				+ " AND EDCH.CREATION_DATE >= ISNULL(?, EDCH.CREATION_DATE)"
				+ " AND EDCH.CREATION_DATE <= ISNULL(?, EDCH.CREATION_DATE)"
				+ " AND AMD.DEPT_CODE = dbo.NVL(?, AMD.DEPT_CODE)"
				+ " AND EDCH.ORDER_NO LIKE dbo.NVL(?, EDCH.ORDER_NO)"
				+ " AND EDCH.IMPLEMENT_BY = dbo.NVL(?, EDCH.IMPLEMENT_BY)";
//			sqlArgs.add(dto.getTaskName());
			sqlArgs.add(dto.getCheckTaskId());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getEndDate());
			sqlArgs.add(dto.getDeptCode());
			sqlArgs.add(dto.getOrderNo());
			sqlArgs.add(dto.getImplementBy());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}