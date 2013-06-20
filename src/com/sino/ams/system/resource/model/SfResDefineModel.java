package com.sino.ams.system.resource.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.resource.dto.SfResDefineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: SfResDefineModel</p>
 * <p>Description:�����Զ�����SQL��������SfResDefineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfResDefineModel extends AMSSQLProducer {

	/**
	 * ���ܣ�SF_RES_DEFINE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfResDefineDTO ���β���������
	 */
	public SfResDefineModel(SfUserDTO userAccount, SfResDefineDTO dtoParameter) {
		super(userAccount, dtoParameter);
		dtoParameter.setCreatedBy(userAccount.getUserId());
	}

	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " SF_RES_DEFINE("
						+ " SYSTEM_ID,"
						+ " RES_ID,"
						+ " RES_PAR_ID,"
						+ " RES_NAME,"
						+ " RES_URL,"
						+ " SORT_NO,"
						+ " IS_POPUP,"
						+ " PRINCIPAL,"
						+ " ENABLED,"
						+ " IS_INNER,"
						+ " VISIBLE,"
						+ " POPSCRIPT,"
						+ " LEVEL_NUM,"
						+ " BUSINESS_DESC,"
						+ " CREATION_DATE,"
						+ " CREATED_BY"
						+ ") VALUES ("
						+ "  NEWID() , SF_RESOURCE_PKG.GET_NEXT_RESID(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		sqlArgs.add(dto.getResParId());
		sqlArgs.add(dto.getResParId());
		sqlArgs.add(dto.getResName());
		sqlArgs.add(dto.getResUrl());
		sqlArgs.add(dto.getSortNo());
		sqlArgs.add(dto.getIsPopup());
		sqlArgs.add(dto.getPrincipal());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getIsInner());
		sqlArgs.add(dto.getVisible());
		sqlArgs.add(dto.getPopscript());
		sqlArgs.add(dto.getLevelNum());
		sqlArgs.add(dto.getBusinessDesc());
		sqlArgs.add(dto.getCreationDate());
		sqlArgs.add(dto.getCreatedBy());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "UPDATE SF_RES_DEFINE"
						+ " SET"
						+ " RES_ID = ?,"
						+ " RES_PAR_ID = ?,"
						+ " RES_NAME = ?,"
						+ " RES_URL = ?,"
						+ " SORT_NO = ?,"
						+ " IS_POPUP = ?,"
						+ " PRINCIPAL = ?,"
						+ " ENABLED = ?,"
						+ " IS_INNER = ?,"
						+ " VISIBLE = ?,"
						+ " POPSCRIPT = ?,"
						+ " LEVEL_NUM = ?,"
						+ " BUSINESS_DESC = ?,"
						+ " LAST_UPDATE_DATE = ?,"
						+ " LAST_UPDATE_BY = ?"
						+ " WHERE"
						+ " SYSTEM_ID = ?";
		sqlArgs.add(dto.getResId());
		sqlArgs.add(dto.getResParId());
		sqlArgs.add(dto.getResName());
		sqlArgs.add(dto.getResUrl());
		sqlArgs.add(dto.getSortNo());
		sqlArgs.add(dto.getIsPopup());
		sqlArgs.add(dto.getPrincipal());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getIsInner());
		sqlArgs.add(dto.getVisible());
		sqlArgs.add(dto.getPopscript());
		sqlArgs.add(dto.getLevelNum());
		sqlArgs.add(dto.getBusinessDesc());

		sqlArgs.add(dto.getLastUpdateDate());
		sqlArgs.add(dto.getLastUpdateBy());
		sqlArgs.add(dto.getSystemId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
						+ " SF_RES_DEFINE"
						+ " WHERE"
						+ " SYSTEM_ID = ?";
		sqlArgs.add(dto.getSystemId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " SF_RES_DEFINE"
						+ " WHERE"
						+ " SYSTEM_ID = ?";
		sqlArgs.add(dto.getSystemId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "SELECT"
						+ " A.SYSTEM_ID,"
						+ " A.RES_ID,"
						+ " A.RES_PAR_ID,"
						+ " A.RES_NAME,"
						+ " A.RES_URL,"
						+"CASE WHEN A.ENABLED='Y' THEN '<font color=\"green\">��Ч</font>' ELSE '<font color=\"red\">��Ч</font>' END ENABLED,"
						+"CASE WHEN A.VISIBLE='Y' THEN '<font color=\"green\">�ɼ�</font>' ELSE '<font color=\"red\">���ɼ�</font>' END VISIBLE,"
						+ " B.RES_NAME PAR_NAME"
						+ " FROM"
						+ " SF_RES_DEFINE A,"
						+ " SF_RES_DEFINE B"
						+ " WHERE"
						+ " A.RES_PAR_ID *= B.RES_ID"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR A.ENABLED = ?)"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR A.VISIBLE = ?)"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR A.RES_NAME LIKE ?)"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR A.RES_URL LIKE ?)"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR (A.RES_ID LIKE ? OR A.RES_ID = ?))"
						+ " ORDER BY"
						+ " A.RES_ID,"
						+ " A.RES_PAR_ID";
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getVisible());
		sqlArgs.add(dto.getVisible());
		sqlArgs.add(dto.getResName());
		sqlArgs.add(dto.getResName());
		sqlArgs.add(dto.getResUrl());
		sqlArgs.add(dto.getResUrl());
		sqlArgs.add(dto.getResId());
		sqlArgs.add(dto.getResId() + ".%");
		sqlArgs.add(dto.getResId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�SQL��
	 * ����ʾָ����Դ��������Դ
	 * @param resourceId ָ����Դ
	 * @return SQLModel
	 */
	public SQLModel getResourceOptionModel(String resourceId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " SRD.RES_ID,"
						+ " SRD.RES_NAME"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " (SRD.RES_ID <> ? "
						+ " AND SRD.RES_ID NOT LIKE ?)"
						+ " OR  " + SyBaseSQLUtil.isNull() + " "
						+ " ORDER  BY"
						+ " SRD.RES_ID,"
						+ " SRD.RES_PAR_ID";
		sqlArgs.add(resourceId);
		sqlArgs.add(resourceId + ".%");
		sqlArgs.add(resourceId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����URL��Դ����SQL����Ŀ������
	 * @return SQLModel
	 */
	public SQLModel getResourceTreeModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = " SELECT"
						+ " A.SYSTEM_ID,"
						+ " A.RES_ID,"
						+ " A.RES_PAR_ID,"
						+ " A.RES_NAME,"
						+ " A.RES_ID RES_URL,"
						+ " A.SORT_NO,"
						+ " 'N' IS_POPUP,"
						+ " A.POPSCRIPT"
						+ " FROM"
						+ " SF_RES_DEFINE A"
						+ " ORDER BY"
						+ " A.RES_ID,"
						+ " A.RES_PAR_ID";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡָ������Դ�µ���һ����Դ���SQL
	 * @param resourcePid String
	 * @return SQLModel
	 */
	public SQLModel getAllChildModel(String resourcePid) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT"
						+ " *"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " SRD.RES_ID LIKE ?"
						+ " ORDER BY"
						+ " RES_ID,"
						+ " RES_PAR_ID";
		sqlArgs.add(resourcePid + ".%");
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡָ������Դ�µ���һ����Դ���SQL
	 * @param resourcePid String
	 * @return SQLModel
	 */
	public SQLModel getNewChildModel(String resourcePid) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT"
						+ " SF_RESOURCE_PKG.GET_NEXT_RESID(?) RES_ID"
						 ;
		sqlArgs.add(resourcePid);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡָ������Դ�µ���һ����ԴʧЧSQL
	 * @return SQLModel
	 */
	public SQLModel getPassviateResourceModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "UPDATE SF_RES_DEFINE"
						+ " SET"
						+ " ENABLED = ?"
						+ " WHERE"
						+ " RES_ID = ?"
						+ " OR RES_ID LIKE ?";
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getResId());
		sqlArgs.add(dto.getResId() + ".%");
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡָ����Դ������ֱϵ�ϼ���Ŀ��Դ����ЧSQL��
	 * @return SQLModel
	 */
	public SQLModel getActivateResourceModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "UPDATE SF_RES_DEFINE"
						+ " SET"
						+ " ENABLED = ?"
						+ " WHERE"
						+ " ? LIKE RES_ID||'%'";
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getResId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������ԴID��ȡURL��ԴSQL��
	 * @return SQLModel
	 */
	public SQLModel getResourceByIdModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
		String sqlStr = "SELECT"
						+ " SRD.*,"
						+ " SF_RESOURCE_PKG.HAS_CHILD(SRD.RES_ID) HAS_CHILD"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " SRD.ENABLED = ?"
						+ " AND SRD.VISIBLE = ?"
						+ " AND SRD.RES_ID = ?";
		sqlArgs.add(WebAttrConstant.TRUE_VALUE);
		sqlArgs.add(WebAttrConstant.TRUE_VALUE);
		sqlArgs.add(dto.getResId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
