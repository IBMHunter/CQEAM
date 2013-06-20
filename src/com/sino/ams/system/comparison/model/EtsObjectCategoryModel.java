package com.sino.ams.system.comparison.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.comparison.dto.EtsObjectCategoryDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EtsObjectCategoryModel</p>
 * <p>Description:�����Զ�����SQL��������EtsObjectCategoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectCategoryModel extends AMSSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectCategoryDTO ���β���������
	 */
	public EtsObjectCategoryModel(SfUserDTO userAccount, EtsObjectCategoryDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����ɹ�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel(String objectCategory,String searchCategory) throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsObjectCategoryDTO etsObjectCategory = (EtsObjectCategoryDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " ETS_OBJECT_CATEGORY("
				+ " SYSTEMID,"
				+ " OBJECT_CATEGORY,"
				+ " SEARCH_CATEGORY,"
				+ " ORGANIZATION_ID,"
				+ " REMARK,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY"
				+ ") VALUES ("
				+ " NEWID() , ?, ?, ?, ?, GETDATE(), ?, ?, ?)";
		
			sqlArgs.add(objectCategory);
			sqlArgs.add(searchCategory);
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(etsObjectCategory.getRemark());
//			sqlArgs.add(etsObjectCategory.getCreationDate());
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(etsObjectCategory.getLastUpdateDate());
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
	 * ���ܣ�����Զ����ɹ�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsObjectCategoryDTO etsObjectCategory = (EtsObjectCategoryDTO)dtoParameter;
			String sqlStr = "UPDATE ETS_OBJECT_CATEGORY"
				+ " SET"
				+ " OBJECT_CATEGORY = ?,"
				+ " SEARCH_CATEGORY = ?,"
				+ " ORGANIZATION_ID = ?,"
				+ " REMARK = ?,"
				+ " CREATION_DATE = ?,"
				+ " CREATED_BY = ?,"
				+ " LAST_UPDATE_DATE = ?,"
				+ " LAST_UPDATE_BY = ?"
				+ " WHERE"
				+ " SYSTEMID = ?";
		
			sqlArgs.add(etsObjectCategory.getObjectCategory());
			sqlArgs.add(etsObjectCategory.getSearchCategory());
			sqlArgs.add(etsObjectCategory.getOrganizationId());
			sqlArgs.add(etsObjectCategory.getRemark());
			sqlArgs.add(etsObjectCategory.getCreationDate());
			sqlArgs.add(etsObjectCategory.getCreatedBy());
			sqlArgs.add(etsObjectCategory.getLastUpdateDate());
			sqlArgs.add(etsObjectCategory.getLastUpdateBy());
			sqlArgs.add(etsObjectCategory.getSystemid());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(String objectCategory) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectCategoryDTO etsObjectCategory = (EtsObjectCategoryDTO)dtoParameter;
		String sqlStr = "DELETE"
                + " FROM"
				+ " ETS_OBJECT_CATEGORY"
				+ " WHERE"
				+ " OBJECT_CATEGORY = '"+objectCategory+"'"
                + " AND ORGANIZATION_ID = ?" ;
//			sqlArgs.add(etsObjectCategory.getSystemid());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectCategoryDTO etsObjectCategory = (EtsObjectCategoryDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEMID,"
			+ " OBJECT_CATEGORY,"
			+ " SEARCH_CATEGORY,"
			+ " ORGANIZATION_ID,"
			+ " REMARK,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_OBJECT_CATEGORY"
			+ " WHERE"
			+ " SYSTEMID = '10'" ;
//		sqlArgs.add(etsObjectCategory.getSystemid());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsObjectCategoryDTO etsObjectCategory = (EtsObjectCategoryDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " SYSTEMID,"
				+ " OBJECT_CATEGORY,"
				+ " SEARCH_CATEGORY,"
				+ " ORGANIZATION_ID,"
				+ " REMARK,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY"
				+ " FROM"
				+ " ETS_OBJECT_CATEGORY"
				+ " WHERE"
				+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR OBJECT_CATEGORY LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SEARCH_CATEGORY LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
			sqlArgs.add(etsObjectCategory.getSystemid());
			sqlArgs.add(etsObjectCategory.getSystemid());
			sqlArgs.add(etsObjectCategory.getObjectCategory());
			sqlArgs.add(etsObjectCategory.getObjectCategory());
			sqlArgs.add(etsObjectCategory.getSearchCategory());
			sqlArgs.add(etsObjectCategory.getSearchCategory());
			sqlArgs.add(etsObjectCategory.getOrganizationId());
			sqlArgs.add(etsObjectCategory.getOrganizationId());
			sqlArgs.add(etsObjectCategory.getRemark());
			sqlArgs.add(etsObjectCategory.getRemark());
			sqlArgs.add(etsObjectCategory.getCreationDate());
			sqlArgs.add(etsObjectCategory.getCreationDate());
			sqlArgs.add(etsObjectCategory.getCreatedBy());
			sqlArgs.add(etsObjectCategory.getCreatedBy());
			sqlArgs.add(etsObjectCategory.getLastUpdateDate());
			sqlArgs.add(etsObjectCategory.getLastUpdateDate());
			sqlArgs.add(etsObjectCategory.getLastUpdateBy());
			sqlArgs.add(etsObjectCategory.getLastUpdateBy());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ�������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
//		try {
			List sqlArgs = new ArrayList();
			EtsObjectCategoryDTO etsObjectCategory = (EtsObjectCategoryDTO)dtoParameter;
			String sqlStr = " SELECT EOC.SYSTEMID,\n" +
                    "        " + SyBaseSQLUtil.getDBOwner()+ "APP_GET_ORGNIZATION_NAME(EOC.ORGANIZATION_ID) COMPANY,\n" +
                    "        EFV.DESCRIPTION,\n"+
//                    "        " + SyBaseSQLUtil.getDBOwner()+ "APP_GET_FLEX_VALUE( EFV.CODE, ?),\n" +
                    "        " + SyBaseSQLUtil.getDBOwner()+ "APP_GET_FLEX_VALUE( EFV.CODE, ?) OBJECT_CATEGORY,\n" +
                    "        " + SyBaseSQLUtil.getDBOwner()+ "APP_GET_FLEX_VALUE( EFV.CODE, ?) SEARCH_CATEGORY\n" +
                    "   FROM ETS_OBJECT_CATEGORY EOC,\n" +
                    "        ETS_FLEX_VALUES     EFV,\n" +
                    "        ETS_FLEX_VALUE_SET  EFVS\n" +
                    "  WHERE EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                    "    AND EFVS.CODE = 'OBJECT_CATEGORY' " +
                    "     AND EFV.CODE = CONVERT( VARCHAR , EOC.OBJECT_CATEGORY )\n"+
                    "    AND EOC.ORGANIZATION_ID =?"+
                    " AND ( " + SyBaseSQLUtil.nullStringParam() + " OR EOC.OBJECT_CATEGORY  = ?) "+
                    " AND ( " + SyBaseSQLUtil.nullIntParam() + " OR EOC.ORGANIZATION_ID = ?)";
//				+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR OBJECT_CATEGORY LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SEARCH_CATEGORY LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
//			sqlArgs.add(etsObjectCategory.getSystemid());
//			sqlArgs.add(etsObjectCategory.getSystemid());
//			sqlArgs.add(etsObjectCategory.getObjectCategory());
//			sqlArgs.add(etsObjectCategory.getObjectCategory());
//			sqlArgs.add(etsObjectCategory.getSearchCategory());
//			sqlArgs.add(etsObjectCategory.getSearchCategory());
//			sqlArgs.add(etsObjectCategory.getOrganizationId());
//			sqlArgs.add(etsObjectCategory.getOrganizationId());
//			sqlArgs.add(etsObjectCategory.getRemark());
//			sqlArgs.add(etsObjectCategory.getRemark());
//			sqlArgs.add(etsObjectCategory.getCreationDate());
//			sqlArgs.add(etsObjectCategory.getCreationDate());
//			sqlArgs.add(etsObjectCategory.getCreatedBy());
//			sqlArgs.add(etsObjectCategory.getCreatedBy());
//			sqlArgs.add(etsObjectCategory.getLastUpdateDate());
//			sqlArgs.add(etsObjectCategory.getLastUpdateDate());
//			sqlArgs.add(etsObjectCategory.getLastUpdateBy());
//			sqlArgs.add(etsObjectCategory.getLastUpdateBy());
//			sqlArgs.add(DictConstant.OBJECT_CATEGORY);
            sqlArgs.add(DictConstant.OBJECT_CATEGORY);
            sqlArgs.add(DictConstant.OBJECT_CATEGORY);
            sqlArgs.add(userAccount.getOrganizationId());
            
            SyBaseSQLUtil.nullStringParamArgs(sqlArgs, etsObjectCategory.getObjectCategory() );
            SyBaseSQLUtil.nullIntParamArgs(sqlArgs, etsObjectCategory.getCompany() );
//            sqlArgs.add(etsObjectCategory.getObjectCategory());
//            sqlArgs.add(etsObjectCategory.getObjectCategory());
//            sqlArgs.add(etsObjectCategory.getCompany());
//            sqlArgs.add(etsObjectCategory.getCompany());
            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
		return sqlModel;
	}

}