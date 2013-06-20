package com.sino.ams.system.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: SfGroupModel</p>
 * <p>Description:�����Զ�����SQL��������SfGroupModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfGroupModel extends BaseSQLProducer {

	private SfGroupDTO sfGroup = null;
	private SfUserDTO sfUser=null;

	/**
	 * ���ܣ�SF_GROUP ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfGroupDTO ���β���������
	 */
	public SfGroupModel(SfUserDTO userAccount, SfGroupDTO dtoParameter) {
		super(userAccount, dtoParameter);
		this.sfGroup = (SfGroupDTO)dtoParameter;
		this.sfUser=userAccount;
	}
	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO "
			+ " SF_GROUP("
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID,"
            + " GROUP_THRED"    
			+ ") VALUES ("
			+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?)";

		sqlArgs.add(sfGroup.getGroupCode());
		sqlArgs.add(sfGroup.getGroupname());
		sqlArgs.add(sfGroup.getGroupPid());
		sqlArgs.add(sfGroup.getOrganizationId());
		sqlArgs.add(sfGroup.getSortno());
		sqlArgs.add(sfGroup.getIsroot());
		sqlArgs.add(sfGroup.getCategory());
		sqlArgs.add(sfGroup.getEnabled());
		sqlArgs.add(sfGroup.getIsInner());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfGroup.getLastUpdateDate());
		sqlArgs.add(sfGroup.getLastUpdateBy());
		sqlArgs.add(sfGroup.getIsDesigner());
		sqlArgs.add(sfGroup.getpFlowId());
		sqlArgs.add(sfGroup.getGroupThred());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE SF_GROUP"
			+ " SET"
			+ " GROUP_CODE = ?,"
			+ " GROUPNAME = ?,"
			+ " GROUP_PID = ?,"
			+ " ORGANIZATION_ID = ?,"
			+ " SORTNO = ?,"
			+ " ISROOT = ?,"
			+ " CATEGORY = ?,"
			+ " ENABLED = ?,"
			+ " IS_INNER = ?,"
			+ " LAST_UPDATE_DATE = GETDATE(),"
			+ " LAST_UPDATE_BY = ?,"
			+ " IS_DESIGNER = ?,"
			+ " P_FLOW_ID = ?,"
            + " GROUP_THRED = ?"    
			+ " WHERE"
			+ " GROUP_ID = ?";

		sqlArgs.add(sfGroup.getGroupCode());
		sqlArgs.add(sfGroup.getGroupname());
		sqlArgs.add(sfGroup.getGroupPid());
		sqlArgs.add(sfGroup.getOrganizationId());
		sqlArgs.add(sfGroup.getSortno());
		sqlArgs.add(sfGroup.getIsroot());
		sqlArgs.add(sfGroup.getCategory());
		sqlArgs.add(sfGroup.getEnabled());
		sqlArgs.add(sfGroup.getIsInner());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfGroup.getIsDesigner());
		sqlArgs.add(sfGroup.getpFlowId());
        sqlArgs.add(sfGroup.getGroupThred());
		sqlArgs.add(sfGroup.getGroupId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
			+ " SF_GROUP"
			+ " WHERE"
			+ " GROUP_ID = ?";
		sqlArgs.add(sfGroup.getGroupId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID,"
            + " GROUP_THRED"    
			+ " FROM"
			+ " SF_GROUP  "
			+ " WHERE"
			+ " GROUP_ID = ?";
		sqlArgs.add(sfGroup.getGroupId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɶ���������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID"
			+ " FROM"
			+ " SF_GROUP"
			+ " WHERE"
			+ " GROUP_ID = ?"
			+ " GROUP_CODE = ?"
			+ " GROUPNAME = ?"
			+ " GROUP_PID = ?"
			+ " ORGANIZATION_ID = ?"
			+ " SORTNO = ?"
			+ " ISROOT = ?"
			+ " CATEGORY = ?"
			+ " ENABLED = ?"
			+ " IS_INNER = ?"
			+ " CREATION_DATE = ?"
			+ " CREATED_BY = ?"
			+ " LAST_UPDATE_DATE = ?"
			+ " LAST_UPDATE_BY = ?"
			+ " IS_DESIGNER = ?"
			+ " P_FLOW_ID = ?";
		sqlArgs.add(sfGroup.getGroupId());
		sqlArgs.add(sfGroup.getGroupCode());
		sqlArgs.add(sfGroup.getGroupname());
		sqlArgs.add(sfGroup.getGroupPid());
		sqlArgs.add(sfGroup.getOrganizationId());
		sqlArgs.add(sfGroup.getSortno());
		sqlArgs.add(sfGroup.getIsroot());
		sqlArgs.add(sfGroup.getCategory());
		sqlArgs.add(sfGroup.getEnabled());
		sqlArgs.add(sfGroup.getIsInner());
		sqlArgs.add(sfGroup.getCreationDate());
		sqlArgs.add(sfGroup.getCreatedBy());
		sqlArgs.add(sfGroup.getLastUpdateDate());
		sqlArgs.add(sfGroup.getLastUpdateBy());
		sqlArgs.add(sfGroup.getIsDesigner());
		sqlArgs.add(sfGroup.getpFlowId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� createdBy �����ѯ����SQL��
	 * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param createdBy String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByCreatedByModel(int createdBy){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID"
			+ " FROM"
			+ " SF_GROUP"
			+ " WHERE"
			+ " CREATED_BY = ?";
		sqlArgs.add(createdBy);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� lastUpdateBy �����ѯ����SQL��
	 * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param lastUpdateBy String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByLastUpdateByModel(int lastUpdateBy){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID"
			+ " FROM"
			+ " SF_GROUP"
			+ " WHERE"
			+ " LAST_UPDATE_BY = ?";
		sqlArgs.add(lastUpdateBy);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		if(foreignKey.equals("createdBy")){
			sqlModel = getDataByCreatedByModel(sfGroup.getCreatedBy());
		} else if(foreignKey.equals("lastUpdateBy")){
			sqlModel = getDataByLastUpdateByModel(sfGroup.getLastUpdateBy());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
				+ " GROUP_ID,"
				+ " GROUP_CODE,"
				+ " GROUPNAME,"
				+ " GROUP_PID,"
				+ " AMS_PUB_PKG.GET_GROUP_NAME(GROUP_PID) P_NAME,"
				+ " ORGANIZATION_ID,"
				+ " AMS_PUB_PKG.GET_ORGNIZATION_NAME(ORGANIZATION_ID) ORGNIZATION_NAME ,"
				+ " SORTNO,"
				+ " ISROOT,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(CATEGORY,'GROUP_CATEGORY') CATEGORY,"
				+ " CASE WHEN ENABLED='Y' THEN '��' ELSE '��' END ENABLED,"
				+ " IS_INNER,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " CASE WHEN IS_DESIGNER='1' THEN '�������' WHEN IS_DESIGNER='2' THEN '�������' ELSE '��ͨ���' END IS_DESIGNER,"
				+ " P_FLOW_ID"
				+ " FROM"
				+ " SF_GROUP"
				+ " WHERE"
				+ " GROUPNAME LIKE dbo.NVL(?, GROUPNAME)"
				+ " AND ENABLED = dbo.NVL(?, ENABLED)"
				+ " AND IS_DESIGNER = dbo.NVL(?, IS_DESIGNER)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR P_FLOW_ID = ?)"
				+ " AND ORGANIZATION_ID=?";
		sqlArgs.add(sfGroup.getGroupname());
		sqlArgs.add(sfGroup.getEnabled());
		sqlArgs.add(sfGroup.getIsDesigner());
		sqlArgs.add(sfGroup.getpFlowId());
		sqlArgs.add(sfGroup.getpFlowId());
		sqlArgs.add(sfUser.getOrganizationId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}



    /**
	 * ���ܣ�������������ѡ��ĸ�����GROUP_THRED�ֶ����ó��Լ���GROUP_ID
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getUpdateThirdGroupModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE SF_GROUP SET GROUP_THRED = ? WHERE GROUP_ID = ?";
		sqlArgs.add(sfGroup.getGroupThred());
		sqlArgs.add(sfGroup.getGroupThred());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
