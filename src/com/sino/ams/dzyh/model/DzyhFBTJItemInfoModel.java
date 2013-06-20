package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EtsItemInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: DzyhFBTJItemInfoModel</p>
 * <p>Description:�����Զ�����SQL��������DzyhFBTJItemInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class DzyhFBTJItemInfoModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoDTO ���β���������
	 */
	public DzyhFBTJItemInfoModel(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɱ�ǩ����Ϣ(EAM) ETS_ITEM_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EII.SYSTEMID,"
			+ " ESI.ITEM_CATEGORY2 EII_ITEM_CATEGORY2,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME EII_ITEM_NAME,"
			+ " ESI.ITEM_SPEC EII_ITEM_SPEC,"
			+ " EII.ITEM_QTY,"
			+ " EII.PRICE,"
			+ " EII.RESPONSIBILITY_DEPT,"
			+ " AMD.DEPT_NAME EII_DEPT_NAME,"
			+ " EII.RESPONSIBILITY_USER,"
			+ " AME.USER_NAME EII_USER_NAME,"
			+ " EII.ADDRESS_ID,"
			+ " EO.WORKORDER_OBJECT_NAME EII_WORKORDER_OBJECT_NAME,"
			+ " EII.MAINTAIN_USER,"
			+ " EII.LAST_LOC_CHG_DATE,"
			+ " EII.ATTRIBUTE3,"
			+ " EII.REMARK"
			+ " FROM"
			+ " ETS_SYSTEM_ITEM ESI,"
			+ " ETS_ITEM_INFO EII,"
			+ " AMS_MIS_DEPT AMD,"
			+ " ETS_OBJECT EO,"
			+ " AMS_OBJECT_ADDRESS AOA,"
			+ " AMS_MIS_EMPLOYEE AME"
			+ " WHERE"
			+ " ESI.ITEM_CODE=EII.ITEM_CODE"
			+ " AND EII.RESPONSIBILITY_DEPT=AMD.DEPT_CODE"
			+ " AND EII.ADDRESS_ID=AOA.ADDRESS_ID"
			+ " AND AOA.BOX_NO='0000'"
			+ " AND AOA.NET_UNIT='0000'"
			+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
			+ " AND EII.RESPONSIBILITY_USER=AME.EMPLOYEE_ID"
//			+ " AND ESI.ITEM_CATEGORY2 = ?"
//			+ " AND ESI.ITEM_NAME = ?"
//			+ " AND ESI.ITEM_SPEC = ?"
			+ " AND SYSTEMID = ?";
//		sqlArgs.add(dto.getEiiItemCategory2());
//		sqlArgs.add(dto.getEiiItemName());
//		sqlArgs.add(dto.getEiiItemSpec());
		sqlArgs.add(dto.getSystemid());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ֵ�׺�Ʒ����Ϣ��ѯ(EAM) ETS_ITEM_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EII.SYSTEMID,"
			+ " ESI.ITEM_CATEGORY2 EII_ITEM_CATEGORY2,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME EII_ITEM_NAME,"
			+ " ESI.ITEM_SPEC EII_ITEM_SPEC,"
			+ " EII.ITEM_QTY,"
			+ " ESI.ITEM_UNIT,"
			+ " AMS_PUB_PKG.GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) EII_DEPT_NAME"
			+ " FROM"
			+ " ETS_SYSTEM_ITEM ESI,"
			+ " ETS_ITEM_INFO EII,"
			+ " ETS_OBJECT EO,"
			+ " AMS_OBJECT_ADDRESS AOA"
			+ " WHERE"
			+ " ESI.ITEM_CODE=EII.ITEM_CODE"
			+ " AND EII.FINANCE_PROP='DZYH'"
			+ " AND EII.ADDRESS_ID=AOA.ADDRESS_ID"
			+ " AND AOA.BOX_NO='0000'"
			+ " AND AOA.NET_UNIT='0000'"
			+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_CATEGORY2 LIKE ?)"
			+ " AND EII.RESPONSIBILITY_DEPT LIKE dbo.NVL(? ,EII.RESPONSIBILITY_DEPT)";
		
		sqlArgs.add(dto.getEiiItemCategory2());
		sqlArgs.add(dto.getEiiItemCategory2());
		sqlArgs.add(dto.getResponsibilityDept());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

}