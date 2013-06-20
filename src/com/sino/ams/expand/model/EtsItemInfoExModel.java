package com.sino.ams.expand.model;


import java.util.*;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.expand.dto.EtsItemInfoExDTO;


/**
 * <p>Title: EtsItemInfoExModel</p>
 * <p>Description:�����Զ�����SQL��������EtsItemInfoExModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EtsItemInfoExModel extends AMSSQLProducer {


	/**
	 * ���ܣ��ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EX ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoExDTO ���β���������
	 */
	public EtsItemInfoExModel(SfUserDTO userAccount, EtsItemInfoExDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EX���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoExDTO dto = (EtsItemInfoExDTO)dtoParameter;
		String sqlStr = "UPDATE ETS_ITEM_INFO_EX"
			+ " SET"
			+ " ATTRIBUTE1 = ?,"
			+ " ATTRIBUTE4 = ?,"
			+ " LAST_UPDATE_BY = ?,"
			+ " LAST_UPDATE_DATE = GETDATE()"
			+ " WHERE"
			+ " ITEM_INFO_EX_ID = ?";
	
		sqlArgs.add(dto.getAttribute1());
		sqlArgs.add(dto.getAttribute4());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getItemInfoExId());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EX������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoExDTO dto = (EtsItemInfoExDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EIIE.ITEM_INFO_EX_ID,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME,"
			+ " ESI.ITEM_SPEC,"
			+ " EIIE.ATTRIBUTE1,"	//����
			+ " EIIE.ATTRIBUTE4,"	//��ʻԱ
			+ " EFA.DATE_PLACED_IN_SERVICE,"
			+ " EFA.LIFE_IN_YEARS,"
			+ " EII.RESPONSIBILITY_USER,"
			+ " dbo.EAM_MAINTAIN_GET_EMPLOYEE_NAME(EII.RESPONSIBILITY_USER) EMPLOYEE_NAME"
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
			+ " AND EIIE.ITEM_INFO_EX_ID = ?";
		sqlArgs.add(dto.getItemInfoExId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EXҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemInfoExDTO dto = (EtsItemInfoExDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " EIIE.ITEM_INFO_EX_ID,"
			+ " EII.BARCODE,"
			+ " ESI.ITEM_NAME,"
			+ " ESI.ITEM_SPEC,"
			+ " EIIE.ATTRIBUTE1,"	//����
			+ " EIIE.ATTRIBUTE4,"	//��ʻԱ
			+ " EFA.DATE_PLACED_IN_SERVICE,"
			+ " EFA.LIFE_IN_YEARS,"
			+ " EII.RESPONSIBILITY_USER,"
			//+ " EAM_MAINTAIN_PKG.GET_EMPLOYEE_NAME(EII.RESPONSIBILITY_USER) EMPLOYEE_NAME"
			+ " dbo.EAM_MAINTAIN_GET_EMPLOYEE_NAME(EII.RESPONSIBILITY_USER) EMPLOYEE_NAME"
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
			+ " AND ESI.ITEM_CATEGORY='CAR'"
			+ " AND EII.ORGANIZATION_ID=?"
			+ " AND (LTRIM(?) IS NULL OR EII.BARCODE LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR ESI.ITEM_NAME LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR ESI.ITEM_SPEC LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR EIIE.ATTRIBUTE1 LIKE '%' || ? || '%')"
			+ " AND (LTRIM(?) IS NULL OR EIIE.ATTRIBUTE4 LIKE '%' || ? || '%')";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getAttribute1());
		sqlArgs.add(dto.getAttribute1());
		sqlArgs.add(dto.getAttribute4());
		sqlArgs.add(dto.getAttribute4());
	
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
	
		return sqlModel;
	}

}