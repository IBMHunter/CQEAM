package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EamItemDisposeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamItemDisposeModel</p>
 * <p>Description:�����Զ�����SQL��������EamItemDisposeModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamItemDisposeModel extends AMSSQLProducer {

	/**
	 * ���ܣ����ʴ��õ���(EAM) EAM_ITEM_DISPOSE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamItemDisposeDTO ���β���������
	 */
	public EamItemDisposeModel(SfUserDTO userAccount, EamItemDisposeDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ��������ʴ��õ���(EAM) EAM_ITEM_DISPOSE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamItemDisposeDTO dto = (EamItemDisposeDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EID.DISPOSE_ID,"
			+ " EID.BARCODE,"
			+ " EID.SYSTEMID,"
			+ " ESI.ITEM_CATEGORY2,"
			+ " ESI.ITEM_NAME,"
			+ " ESI.ITEM_SPEC,"
			+ " EII.ITEM_QTY,"
			+ " EII.PRICE,"
			+ " AMS_PUB_PKG.GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) DEPT_NAME,"
			+ " EII.RESPONSIBILITY_USER,"
			+ " AME.USER_NAME,"
			+ " EII.ADDRESS_ID,"
			+ " EO.WORKORDER_OBJECT_NAME,"
			+ " EII.MAINTAIN_USER,"
			+ " EII.LAST_LOC_CHG_DATE,"
			+ " EII.ATTRIBUTE3,"
			+ " EII.REMARK EII_REMARK,"
			+ " AMS_PUB_PKG.GET_FLEX_VALUE(EID.DISPOSE_REASON,'DZYH_DISPOSE_REASON') DISPOSE_REASON,"
			+ " AMS_PUB_PKG.GET_FLEX_VALUE(EID.DISPOSE_TYPE,'DZYH_DISPOSE_TYPE') DISPOSE_TYPE,"
			+ " EID.REMARK,"
			+ " AMS_PUB_PKG.GET_USER_NAME(EID.CREATED_BY) CREATED_BY,"
			+ " EID.CREATION_DATE,"
			+ " EID.LAST_UPDATE_BY,"
			+ " EID.LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_ITEM_DISPOSE EID,"
			+ " ETS_ITEM_INFO EII,"
			+ " ETS_SYSTEM_ITEM ESI,"
			+ " ETS_OBJECT EO,"
			+ " AMS_OBJECT_ADDRESS AOA,"
			+ " AMS_MIS_EMPLOYEE AME"
			+ " WHERE"
			+ " EID.SYSTEMID = EII.SYSTEMID"
			+ " AND EII.ITEM_CODE=ESI.ITEM_CODE"
			+ " AND EII.ADDRESS_ID = AOA.ADDRESS_ID"
			+ " AND EO.WORKORDER_OBJECT_NO = AOA.OBJECT_NO "
			+ " AND AOA.BOX_NO = '0000'"
			+ " AND AOA.NET_UNIT = '0000'"
			+ " AND EII.RESPONSIBILITY_USER=AME.EMPLOYEE_ID"
			+ " AND DISPOSE_ID = ?";
		sqlArgs.add(dto.getDisposeId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ʴ��õ���(EAM) EAM_ITEM_DISPOSEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamItemDisposeDTO dto = (EamItemDisposeDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " EID.DISPOSE_ID,"
				+ " EID.BARCODE,"
				+ " ESI.ITEM_CATEGORY2,"
				+ " ESI.ITEM_NAME,"
				+ " ESI.ITEM_SPEC,"
				+ " EII.ADDRESS_ID,"
				+ " EO.WORKORDER_OBJECT_NAME,"
				+ " EII.RESPONSIBILITY_DEPT,"
				+ " AMS_PUB_PKG.GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) DEPT_NAME,"
				+ " EII.MAINTAIN_USER,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(EID.DISPOSE_REASON,'DZYH_DISPOSE_REASON') DISPOSE_REASON,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(EID.DISPOSE_TYPE,'DZYH_DISPOSE_TYPE') DISPOSE_TYPE,"
				+ " AMS_PUB_PKG.GET_USER_NAME(EID.CREATED_BY) CREATED_BY,"
				+ " EID.CREATION_DATE"
				+ " FROM"
				+ " EAM_ITEM_DISPOSE   EID,"
				+ " ETS_ITEM_INFO      EII,"
				+ " ETS_SYSTEM_ITEM    ESI,"
				+ " AMS_OBJECT_ADDRESS AOA,"
				+ " ETS_OBJECT         EO"
				+ " WHERE"
				+ " EID.SYSTEMID = EII.SYSTEMID"
				+ " AND EII.ITEM_CODE=ESI.ITEM_CODE"
				+ " AND EII.ADDRESS_ID = AOA.ADDRESS_ID"
				+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO"
				+ " AND AOA.BOX_NO = '0000'"
				+ " AND AOA.NET_UNIT = '0000'"
				+ " AND EID.BARCODE LIKE dbo.NVL(?, EID.BARCODE)"
				+ " AND ESI.ITEM_CATEGORY2 LIKE dbo.NVL(?, ESI.ITEM_CATEGORY2)"
				+ " AND ESI.ITEM_NAME LIKE dbo.NVL(?, ESI.ITEM_NAME)"
				+ " AND ESI.ITEM_SPEC LIKE dbo.NVL(?, ESI.ITEM_SPEC)"
				+ " AND EID.DISPOSE_REASON = dbo.NVL(?, EID.DISPOSE_REASON)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EID.DISPOSE_TYPE LIKE ?)"
				+ " AND EID.CREATION_DATE >= ISNULL(?, EID.CREATION_DATE)"
				+ " AND EID.CREATION_DATE <= ISNULL(?, EID.CREATION_DATE)"
				+ " ORDER BY CREATION_DATE DESC";
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemCategory2());
			sqlArgs.add(dto.getItemName());
			sqlArgs.add(dto.getItemSpec());
			sqlArgs.add(dto.getDisposeReason());
			sqlArgs.add(dto.getDisposeType());
			sqlArgs.add(dto.getDisposeType());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getEndDate());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}