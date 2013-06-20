package com.sino.ams.expand.model;


import java.util.*;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.expand.dto.EtsItemInfoExSearchDTO;


/**
 * <p>Title: EtsItemInfoExSearchModel</p>
 * <p>Description:�����Զ�����SQL��������EtsItemInfoExSearchModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EtsItemInfoExSearchModel extends AMSSQLProducer {


	/**
	 * ���ܣ��ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EX ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoExSearchDTO ���β���������
	 */
	public EtsItemInfoExSearchModel(SfUserDTO userAccount, EtsItemInfoExSearchDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EXҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoExSearchDTO dto = (EtsItemInfoExSearchDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EIIE.ITEM_INFO_EX_ID,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME,"
			+ " ESI.ITEM_SPEC,"
			+ " EII.RESPONSIBILITY_DEPT,"
			+ " EIIE.ATTRIBUTE1,"
			+ " EIIE.ATTRIBUTE2,"
			+ " EIIE.ATTRIBUTE3,"
			+ " EIIE.ATTRIBUTE4,"
			+ " EIIE.ATTRIBUTE5,"
			+ " dbo.APP_GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) DEPT_NAME,"
			+ " EII.RESPONSIBILITY_USER,"
			+ " dbo.EAM_MAINTAIN_GET_EMPLOYEE_NAME(EII.RESPONSIBILITY_USER) EMPLOYEE_NAME,"
			+ " EII.FINANCE_PROP,"
			+ " dbo.APP_GET_FLEX_VALUE(EII.FINANCE_PROP,'FINANCE_PROP') FINANCE_PROP_NAME,"
			+ " EFA.DATE_PLACED_IN_SERVICE"
			+ " FROM"
			+ " ETS_ITEM_INFO    EII,"
			+ " ETS_SYSTEM_ITEM  ESI,"
			+ " ETS_ITEM_INFO_EX EIIE,"
			+ " ETS_ITEM_MATCH   EIM,"
			+ " ETS_FA_ASSETS    EFA"
			+ " WHERE"
			+ " EII.ITEM_CODE = ESI.ITEM_CODE"
			+ " AND EII.SYSTEMID = EIIE.SYSTEM_ID"
			+ " AND EII.SYSTEMID = EIM.SYSTEMID"
			+ " AND EIM.ASSET_ID = EFA.ASSET_ID"
			+ " AND EIIE.ITEM_TYPE='IT'"
			+ " AND EII.ORGANIZATION_ID=?"
			+ " AND (LTRIM(?) IS NULL OR EII.BARCODE LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR ESI.ITEM_NAME LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR ESI.ITEM_SPEC LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR EII.RESPONSIBILITY_DEPT LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR EII.RESPONSIBILITY_USER LIKE '%' || ? || '%')"
			+ " AND EII.FINANCE_PROP = dbo.NVL(LTRIM(?), EII.FINANCE_PROP)";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getFinanceProp());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
	
		return sqlModel;
	}

}