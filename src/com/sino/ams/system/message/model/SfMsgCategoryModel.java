package com.sino.ams.system.message.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.sms.dto.SfMsgCategoryDTO;

/**
 * <p>Title: SfMsgCategoryModel</p>
 * <p>Description:�����Զ�����SQL��������SfMsgCategoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class SfMsgCategoryModel extends AMSSQLProducer {

	/**
	 * ���ܣ���Ϣ����� SF_MSG_CATEGORY ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfMsgCategoryDTO ���β���������
	 */
	public SfMsgCategoryModel(SfUserDTO userAccount, SfMsgCategoryDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ�������Ϣ����� SF_MSG_CATEGORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfMsgCategoryDTO dto = (SfMsgCategoryDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " SF_MSG_CATEGORY("
						+ " MSG_CATEGORY_ID,"
						+ " MSG_DESC,"
						+ " NEED_RESEND,"
						+ " RESEND_MAXTIMES,"
						+ " RESEND_DISTANCE,"
						+ " ORGANIZATION_ID,"
						+ " CREATED_BY,"
						+ " ENABLED,"
						+ " COLLECT_SEND,"
						+ " CREATION_DATE"
						+ ") VALUES ("
						+ " ?, ?, ?, ?, ?, ?, ?, ?, ?,  " +  SyBaseSQLUtil.getCurDate() + " )";
		sqlArgs.add(dto.getMsgCategoryId());
		sqlArgs.add(dto.getMsgDesc());
		sqlArgs.add(dto.getNeedResend());
		sqlArgs.add(dto.getResendMaxtimes());
		sqlArgs.add(dto.getResendDistance());
		sqlArgs.add(dto.getOrganizationId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getCollectSend());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������Ϣ����� SF_MSG_CATEGORY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfMsgCategoryDTO dto = (SfMsgCategoryDTO) dtoParameter;
		String sqlStr = "UPDATE SF_MSG_CATEGORY"
						+ " SET"
						+ " MSG_DESC = ?,"
						+ " NEED_RESEND = ?,"
						+ " RESEND_MAXTIMES = ?,"
						+ " RESEND_DISTANCE = ?,"
						+ " LAST_UPDATE_DATE =  " +  SyBaseSQLUtil.getCurDate() + " ,"
						+ " LAST_UPDATE_BY = ?,"
						+ " COLLECT_SEND = ?,"
						+ " ENABLED = ?"
						+ " WHERE"
						+ " MSG_CATEGORY_ID = ?";

		sqlArgs.add(dto.getMsgDesc());
		sqlArgs.add(dto.getNeedResend());
		sqlArgs.add(dto.getResendMaxtimes());
		sqlArgs.add(dto.getResendDistance());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getCollectSend());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getMsgCategoryId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������Ϣ����� SF_MSG_CATEGORY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfMsgCategoryDTO dto = (SfMsgCategoryDTO) dtoParameter;
		String sqlStr = "DELETE FROM"
						+ " SF_MSG_CATEGORY"
						+ " WHERE"
						+ " MSG_CATEGORY_ID = ?";
		sqlArgs.add(dto.getMsgCategoryId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������Ϣ����� SF_MSG_CATEGORY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfMsgCategoryDTO dto = (SfMsgCategoryDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " SMC.MSG_CATEGORY_ID,"
						+ " EOCM.COMPANY COMPANY_NAME,"
						+ " SMC.MSG_DESC,"
						+ " SMC.NEED_RESEND,"
						+ " SMC.RESEND_MAXTIMES,"
						+ " SMC.RESEND_DISTANCE,"
						+ " SMC.ORGANIZATION_ID,"
						+ " SMC.CREATED_BY,"
						+ " SMC.LAST_UPDATE_DATE,"
						+ " SMC.LAST_UPDATE_BY,"
						+ " SMC.ENABLED,"
						+ " SMC.COLLECT_SEND,"
						+ " SMC.CREATION_DATE"
						+ " FROM"
						+ " SF_MSG_CATEGORY SMC,"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " SMC.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
						+ " AND SMC.MSG_CATEGORY_ID = ?";
		sqlArgs.add(dto.getMsgCategoryId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������Ϣ����� SF_MSG_CATEGORYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfMsgCategoryDTO dto = (SfMsgCategoryDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " SMC.MSG_CATEGORY_ID,"
						+ " EOCM.COMPANY COMPANY_NAME,"
						+ " SMC.MSG_DESC,"
						+ " SMC.NEED_RESEND,"
						+ " SMC.RESEND_MAXTIMES,"
						+ " SMC.RESEND_DISTANCE,"
						+ " SMC.ORGANIZATION_ID,"
						+ " SMC.CREATED_BY,"
						+ " SMC.LAST_UPDATE_DATE,"
						+ " SMC.LAST_UPDATE_BY,"
						+ " SMC.ENABLED,"
						+ " SMC.CREATION_DATE"
						+ " FROM"
						+ " SF_MSG_CATEGORY SMC,"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " SMC.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
						+ " AND EOCM.ORGANIZATION_ID = ISNULL(?, EOCM.ORGANIZATION_ID)"
						+ " AND ( ? = '' OR ? IS NULL OR SMC.MSG_DESC LIKE '%' || dbo.NVL(?, SMC.MSG_DESC) || '%')"
						+ " AND ( ? = '' OR ? IS NULL OR SMC.NEED_RESEND = dbo.NVL(?, SMC.NEED_RESEND))"
						+ " AND ( ? = '' OR ? IS NULL OR SMC.COLLECT_SEND = dbo.NVL(?, SMC.COLLECT_SEND))";
		sqlArgs.add(dto.getOrganizationId());
		sqlArgs.add(dto.getMsgDesc());
		sqlArgs.add(dto.getMsgDesc());
		sqlArgs.add(dto.getMsgDesc());
		sqlArgs.add(dto.getNeedResend());
		sqlArgs.add(dto.getNeedResend());
		sqlArgs.add(dto.getNeedResend());
		sqlArgs.add(dto.getCollectSend());
		sqlArgs.add(dto.getCollectSend());
		sqlArgs.add(dto.getCollectSend());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ����������ó�ʼ��SQLModel��ϵͳ����Աʹ�á�
	 * @return SQLModel ����Ϣ��ʼ��SQL
	 */
	public SQLModel getMessageInitModel(){
		SQLModel sqlModel = new SQLModel();
		SfMsgCategoryDTO dto = (SfMsgCategoryDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String strSql = //SyBaseSQLUtil.createNextSeqInInsert( "AMS_NLE" ) 
						 "INSERT INTO SF_MSG_CATEGORY("
						+ " MSG_CATEGORY_ID,"
						+ " MSG_DESC,"
						+ " NEED_RESEND,"
						+ " RESEND_MAXTIMES,"
						+ " RESEND_DISTANCE,"
						+ " ORGANIZATION_ID,"
						+ " ENABLED,"
						+ " FLOW_ID,"
						+ " COLLECT_SEND,"
						+ " CREATION_DATE,"
						+ " CREATED_BY) ("
						+ " SELECT"
						+ "  NEWID() , "
						+ " SPD.PROC_NAME || '--' || SFD.FLOW_DESC,"
						+ " ?, ?, ?,"
						+ " EOCM.ORGANIZATION_ID, ?, SFD.FLOW_ID, ?,  " +  SyBaseSQLUtil.getCurDate() + " , ?"
						+ " FROM"
						+ " SF_PROCEDURE_DEF SPD,"
						+ " SF_FLOW_DEFINE SFD,"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " SPD.PROC_ID = SFD.PROC_ID"
						+ " AND NOT EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " SF_MSG_CATEGORY SMC"
						+ " WHERE"
						+ " SFD.FLOW_ID = SMC.FLOW_ID"
						+ " AND SMC.ORGANIZATION_ID = EOCM.ORGANIZATION_ID))";
		sqlArgs.add(dto.getNeedResend());
		sqlArgs.add(dto.getResendMaxtimes());
		sqlArgs.add(dto.getResendDistance());
		sqlArgs.add(dto.getEnabled());
		sqlArgs.add(dto.getCollectSend());
		sqlArgs.add(userAccount.getUserId());
		sqlModel.setSqlStr(strSql);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
    }
}
