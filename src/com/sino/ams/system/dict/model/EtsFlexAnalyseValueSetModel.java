package com.sino.ams.system.dict.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.dict.dto.EtsFlexValueSetDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsFlexValueSetModel</p>
 * <p>Description:�����Զ�����SQL��������EtsFlexValueSetModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author kouzh
 * @version 1.0
 */


public class EtsFlexAnalyseValueSetModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ֵ�����(AMS) ETS_FLEX_VALUE_SET ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsFlexValueSetDTO ���β���������
	 */
	public EtsFlexAnalyseValueSetModel(SfUserDTO userAccount, EtsFlexValueSetDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUE_SET���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValueSetDTO etsFlexValueSet = (EtsFlexValueSetDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " ETS_FLEX_ANALYSE_VALUE_SET("
			+ " FLEX_VALUE_SET_ID,"
			+ " CODE,"
			+ " NAME,"
			+ " DESCRIPTION,"
			+ " IS_INNER,"
			+ " MAINTAIN_FLAG,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ ") VALUES ("
			+ " NEWID()  , ?, ?, ?, ?, ?, ?, ?, ?)";

		sqlArgs.add(etsFlexValueSet.getCode());
		sqlArgs.add(etsFlexValueSet.getName());
		sqlArgs.add(etsFlexValueSet.getDescription());
		sqlArgs.add(etsFlexValueSet.getIsInner());
		sqlArgs.add(etsFlexValueSet.getMaintainFlag());
		sqlArgs.add(etsFlexValueSet.getCreatedBy());
		sqlArgs.add(etsFlexValueSet.getLastUpdateDate());
		sqlArgs.add(etsFlexValueSet.getLastUpdateBy());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUE_SET���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValueSetDTO etsFlexValueSet = (EtsFlexValueSetDTO)dtoParameter;
		String sqlStr = "UPDATE ETS_FLEX_ANALYSE_VALUE_SET"
			+ " SET"
			+ " CODE = ?,"
			+ " NAME = ?,"
			+ " DESCRIPTION = ?,"
			+ " IS_INNER = ?,"
			+ " MAINTAIN_FLAG = ?,"
			+ " CREATION_DATE = ?,"
			+ " CREATED_BY = ?,"
			+ " LAST_UPDATE_DATE = ?,"
			+ " LAST_UPDATE_BY = ?"
			+ " WHERE"
			+ " FLEX_VALUE_SET_ID = ?";

		sqlArgs.add(etsFlexValueSet.getCode());
		sqlArgs.add(etsFlexValueSet.getName());
		sqlArgs.add(etsFlexValueSet.getDescription());
		sqlArgs.add(etsFlexValueSet.getIsInner());
		sqlArgs.add(etsFlexValueSet.getMaintainFlag());
		sqlArgs.add(etsFlexValueSet.getCreationDate());
		sqlArgs.add(etsFlexValueSet.getCreatedBy());
		sqlArgs.add(etsFlexValueSet.getLastUpdateDate());
		sqlArgs.add(etsFlexValueSet.getLastUpdateBy());
		sqlArgs.add(etsFlexValueSet.getFlexValueSetId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUE_SET����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValueSetDTO etsFlexValueSet = (EtsFlexValueSetDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
			+ " ETS_FLEX_ANALYSE_VALUE_SET"
			+ " WHERE"
			+ " FLEX_VALUE_SET_ID = ?";
		sqlArgs.add(etsFlexValueSet.getFlexValueSetId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUE_SET������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValueSetDTO etsFlexValueSet = (EtsFlexValueSetDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " FLEX_VALUE_SET_ID,"
			+ " CODE,"
			+ " NAME,"
			+ " DESCRIPTION,"
			+ " IS_INNER,"
			+ " MAINTAIN_FLAG,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_FLEX_ANALYSE_VALUE_SET"
			+ " WHERE"
			+ " FLEX_VALUE_SET_ID = ?";
		sqlArgs.add(etsFlexValueSet.getFlexValueSetId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUE_SET����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValueSetDTO etsFlexValueSet = (EtsFlexValueSetDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " FLEX_VALUE_SET_ID,"
			+ " CODE,"
			+ " NAME,"
			+ " DESCRIPTION,"
			+ " IS_INNER,"
			+ " MAINTAIN_FLAG,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_FLEX_ANALYSE_VALUE_SET"
			+ " WHERE"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR FLEX_VALUE_SET_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CODE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR NAME LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR DESCRIPTION LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR IS_INNER LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR MAINTAIN_FLAG LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
		sqlArgs.add(etsFlexValueSet.getFlexValueSetId());
		sqlArgs.add(etsFlexValueSet.getFlexValueSetId());
		sqlArgs.add(etsFlexValueSet.getCode());
		sqlArgs.add(etsFlexValueSet.getCode());
		sqlArgs.add(etsFlexValueSet.getName());
		sqlArgs.add(etsFlexValueSet.getName());
		sqlArgs.add(etsFlexValueSet.getDescription());
		sqlArgs.add(etsFlexValueSet.getDescription());
		sqlArgs.add(etsFlexValueSet.getIsInner());
		sqlArgs.add(etsFlexValueSet.getIsInner());
		sqlArgs.add(etsFlexValueSet.getMaintainFlag());
		sqlArgs.add(etsFlexValueSet.getMaintainFlag());
		sqlArgs.add(etsFlexValueSet.getCreationDate());
		sqlArgs.add(etsFlexValueSet.getCreationDate());
		sqlArgs.add(etsFlexValueSet.getCreatedBy());
		sqlArgs.add(etsFlexValueSet.getCreatedBy());
		sqlArgs.add(etsFlexValueSet.getLastUpdateDate());
		sqlArgs.add(etsFlexValueSet.getLastUpdateDate());
		sqlArgs.add(etsFlexValueSet.getLastUpdateBy());
		sqlArgs.add(etsFlexValueSet.getLastUpdateBy());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ֵ�����(AMS) ETS_FLEX_ANALYSE_VALUE_SETҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsFlexValueSetDTO etsFlexValueSet = (EtsFlexValueSetDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " FLEX_VALUE_SET_ID,"
			+ " CODE,"
			+ " NAME,"
			+ " DESCRIPTION,"
			+ " CREATION_DATE,"
			+ " CASE WHEN ENABLED='Y' THEN '��' ELSE '��' END ENABLED,"
			+ " CASE WHEN IS_INNER='Y' THEN '��' ELSE '��' END IS_INNER ,"
			+ " CASE WHEN MAINTAIN_FLAG='Y' THEN '��' ELSE '��' END MAINTAIN_FLAG "
			+ " FROM"
			+ " ETS_FLEX_ANALYSE_VALUE_SET"
			+ " WHERE"
			+ " ( " + SyBaseSQLUtil.isNull() + "  OR CODE LIKE ?)"
			+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR NAME LIKE ?)"
			+ " AND IS_INNER = ?"
			+ " AND MAINTAIN_FLAG = ?"
			+ " AND ENABLED = ?";
		sqlArgs.add(etsFlexValueSet.getCode());
		sqlArgs.add(etsFlexValueSet.getCode());
		sqlArgs.add(etsFlexValueSet.getName());
		sqlArgs.add(etsFlexValueSet.getName());
		sqlArgs.add(etsFlexValueSet.getIsInner());
		sqlArgs.add(etsFlexValueSet.getMaintainFlag());
		sqlArgs.add(etsFlexValueSet.getEnabled());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
