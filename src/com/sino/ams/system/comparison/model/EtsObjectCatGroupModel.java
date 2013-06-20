package com.sino.ams.system.comparison.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.comparison.dto.EtsObjectCatGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsObjectCatGroupModel</p>
 * <p>Description:�����Զ�����SQL��������EtsObjectCatGroupModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectCatGroupModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectCatGroupDTO ���β���������
	 */
	public EtsObjectCatGroupModel(SfUserDTO userAccount, EtsObjectCatGroupDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ��������-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel(String groupId,String Category) throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
//		try {
			List sqlArgs = new ArrayList();
			EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)dtoParameter;
			String sqlStr =" INSERT INTO "
				+ " ETS_OBJECT_CAT_GROUP("
				+ " SYSTEMID,"
				+ " GROUP_ID,"
				+ " OBJECT_CATEGORY,"
				+ " CREATION_DATE,"
				+ " CREATED_BY"
//				+ " LAST_UPDATE_DATE,"
//				+ " LAST_UPDATE_BY"
				+ ") VALUES ("
				+ " NEWID() , "+ groupId +", "+ Category +", GETDATE() , ?)";
		
//			sqlArgs.add(etsObjectCatGroup.getGroupId());
//			sqlArgs.add(etsObjectCatGroup.getObjectCategory());
//			sqlArgs.add(etsObjectCatGroup.getCreationDate());
			sqlArgs.add(sfUser.getUserId());
//			sqlArgs.add(etsObjectCatGroup.getLastUpdateDate());
//			sqlArgs.add(etsObjectCatGroup.getLastUpdateBy());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)dtoParameter;
			String sqlStr = "UPDATE ETS_OBJECT_CAT_GROUP"
				+ " SET"
				+ " GROUP_ID = ?,"
				+ " OBJECT_CATEGORY = ?,"
				+ " CREATION_DATE = ?,"
				+ " CREATED_BY = ?,"
				+ " LAST_UPDATE_DATE = ?,"
				+ " LAST_UPDATE_BY = ?"
				+ " WHERE"
				+ " SYSTEMID = ?";
		
			sqlArgs.add(etsObjectCatGroup.getGroupId());
			sqlArgs.add(etsObjectCatGroup.getObjectCategory());
			sqlArgs.add(etsObjectCatGroup.getCreationDate());
			sqlArgs.add(etsObjectCatGroup.getCreatedBy());
			sqlArgs.add(etsObjectCatGroup.getLastUpdateDate());
			sqlArgs.add(etsObjectCatGroup.getLastUpdateBy());
			sqlArgs.add(etsObjectCatGroup.getSystemid());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(String groupId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " ETS_OBJECT_CAT_GROUP"
				+ " WHERE"
				+ " GROUP_ID = "+groupId;
//			sqlArgs.add(etsObjectCatGroup.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEMID,"
			+ " GROUP_ID,"
			+ " OBJECT_CATEGORY,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_OBJECT_CAT_GROUP"
			+ " WHERE"
			+ " SYSTEMID = ?";
		sqlArgs.add(etsObjectCatGroup.getSystemid());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " SYSTEMID,"
				+ " GROUP_ID,"
				+ " OBJECT_CATEGORY,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY"
				+ " FROM"
				+ " ETS_OBJECT_CAT_GROUP"
				+ " WHERE"
				+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR GROUP_ID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR OBJECT_CATEGORY LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
			sqlArgs.add(etsObjectCatGroup.getSystemid());
			sqlArgs.add(etsObjectCatGroup.getSystemid());
			sqlArgs.add(etsObjectCatGroup.getGroupId());
			sqlArgs.add(etsObjectCatGroup.getGroupId());
			sqlArgs.add(etsObjectCatGroup.getObjectCategory());
			sqlArgs.add(etsObjectCatGroup.getObjectCategory());
			sqlArgs.add(etsObjectCatGroup.getCreationDate());
			sqlArgs.add(etsObjectCatGroup.getCreationDate());
			sqlArgs.add(etsObjectCatGroup.getCreatedBy());
			sqlArgs.add(etsObjectCatGroup.getCreatedBy());
			sqlArgs.add(etsObjectCatGroup.getLastUpdateDate());
			sqlArgs.add(etsObjectCatGroup.getLastUpdateDate());
			sqlArgs.add(etsObjectCatGroup.getLastUpdateBy());
			sqlArgs.add(etsObjectCatGroup.getLastUpdateBy());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUPҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
//		try {
			List sqlArgs = new ArrayList();
			EtsObjectCatGroupDTO etsObjectCatGroup = (EtsObjectCatGroupDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " EOCG.SYSTEMID,"
				+ " EOCG.GROUP_ID,"
                + " dbo.APP_GET_GROUP_NAME(SG.GROUP_ID) GROUP_NAME,"
                + " dbo.APP_GET_FLEX_VALUE(  CONVERT( VARCHAR , EOCG.OBJECT_CATEGORY ), ?) OBJECT_CATEGORY,\n"
//                + " EOCG.OBJECT_CATEGORY,"
//				+ " EOCG.CREATION_DATE,"
				+ " EOCG.CREATED_BY,"
//				+ " EOCG.LAST_UPDATE_DATE,"
				+ " EOCG.LAST_UPDATE_BY"
				+ " FROM"
				+ " ETS_OBJECT_CAT_GROUP EOCG,"
				+ " SF_GROUP SG"
				+ " WHERE"
				+ " SG.GROUP_ID = EOCG.GROUP_ID"
				+ " AND SG.ORGANIZATION_ID = ?"
				+ " AND ( " + SyBaseSQLUtil.nullIntParam() + " OR EOCG.GROUP_ID = ?)"
				+ " AND ( " + SyBaseSQLUtil.nullIntParam() + " OR EOCG.OBJECT_CATEGORY = ?)";
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EOCG.CREATION_DATE LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EOCG.CREATED_BY = ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EOCG.LAST_UPDATE_DATE LIKE ?)"
//				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR EOCG.LAST_UPDATE_BY = ?)";
			
            sqlArgs.add(DictConstant.OBJECT_CATEGORY);
            sqlArgs.add(sfUser.getOrganizationId());
            SyBaseSQLUtil.nullIntParamArgs(sqlArgs,  etsObjectCatGroup.getGroupId() );
            SyBaseSQLUtil.nullStringParamArgs(sqlArgs,  etsObjectCatGroup.getObjectCategory() );
            
//            sqlArgs.add(etsObjectCatGroup.getGroupId());
//			sqlArgs.add(etsObjectCatGroup.getGroupId());
//			sqlArgs.add(etsObjectCatGroup.getObjectCategory());
//			sqlArgs.add(etsObjectCatGroup.getObjectCategory());
//			sqlArgs.add(etsObjectCatGroup.getCreationDate());
//			sqlArgs.add(etsObjectCatGroup.getCreationDate());
//			sqlArgs.add(etsObjectCatGroup.getCreatedBy());
//			sqlArgs.add(etsObjectCatGroup.getCreatedBy());
//			sqlArgs.add(etsObjectCatGroup.getLastUpdateDate());
//			sqlArgs.add(etsObjectCatGroup.getLastUpdateDate());
//			sqlArgs.add(etsObjectCatGroup.getLastUpdateBy());
//			sqlArgs.add(etsObjectCatGroup.getLastUpdateBy());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
		return sqlModel;
	}

}