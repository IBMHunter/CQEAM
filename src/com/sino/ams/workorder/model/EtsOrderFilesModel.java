package com.sino.ams.workorder.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsOrderFilesDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsOrderFilesModel</p>
 * <p>Description:�����Զ�����SQL��������EtsOrderFilesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class EtsOrderFilesModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ������ļ�����(EAM) ETS_ORDER_FILES ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsOrderFilesDTO ���β���������
	 */
	public EtsOrderFilesModel(SfUserDTO userAccount, EtsOrderFilesDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����ɹ����ļ�����(EAM) ETS_ORDER_FILES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " ETS_ORDER_FILES("
				+ " SYSTEMID,"
				+ " WORKORDER_BATCH,"
				+ " TITEL,"
				+ " FILE_NAME,"
				+ " FILE_PATH,"
				+ " FILE_TYPE,"
				+ " IS_TRUEFILE,"
				+ " REMARK,"
				+ " HANDLER,"
				+ " RECORD_DATE"
				+ ") VALUES ("
				+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
			sqlArgs.add(etsOrderFiles.getWorkorderBatch());
			sqlArgs.add(etsOrderFiles.getTitel());
			sqlArgs.add(etsOrderFiles.getFileName());
			sqlArgs.add(etsOrderFiles.getFilePath());
			sqlArgs.add(etsOrderFiles.getFileType());
			sqlArgs.add(etsOrderFiles.getIsTruefile());
			sqlArgs.add(etsOrderFiles.getRemark());
			sqlArgs.add(etsOrderFiles.getHandler());
			sqlArgs.add(etsOrderFiles.getRecordDate());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ����ļ�����(EAM) ETS_ORDER_FILES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)dtoParameter;
			String sqlStr = "UPDATE ETS_ORDER_FILES"
				+ " SET"
				+ " WORKORDER_BATCH = ?,"
				+ " TITEL = ?,"
				+ " FILE_NAME = ?,"
				+ " FILE_PATH = ?,"
				+ " FILE_TYPE = ?,"
				+ " IS_TRUEFILE = ?,"
				+ " REMARK = ?,"
				+ " HANDLER = ?,"
				+ " RECORD_DATE = ?"
				+ " WHERE"
				+ " SYSTEMID = ?";
		
			sqlArgs.add(etsOrderFiles.getWorkorderBatch());
			sqlArgs.add(etsOrderFiles.getTitel());
			sqlArgs.add(etsOrderFiles.getFileName());
			sqlArgs.add(etsOrderFiles.getFilePath());
			sqlArgs.add(etsOrderFiles.getFileType());
			sqlArgs.add(etsOrderFiles.getIsTruefile());
			sqlArgs.add(etsOrderFiles.getRemark());
			sqlArgs.add(etsOrderFiles.getHandler());
			sqlArgs.add(etsOrderFiles.getRecordDate());
			sqlArgs.add(etsOrderFiles.getSystemid());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ����ļ�����(EAM) ETS_ORDER_FILES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " ETS_ORDER_FILES"
				+ " WHERE"
				+ " SYSTEMID = ?";
			sqlArgs.add(etsOrderFiles.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ����ļ�����(EAM) ETS_ORDER_FILES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEMID,"
			+ " WORKORDER_BATCH,"
			+ " TITEL,"
			+ " FILE_NAME,"
			+ " FILE_PATH,"
			+ " FILE_TYPE,"
			+ " IS_TRUEFILE,"
			+ " REMARK,"
			+ " HANDLER,"
			+ " RECORD_DATE"
			+ " FROM"
			+ " ETS_ORDER_FILES"
			+ " WHERE"
			+ " SYSTEMID = ?";
		sqlArgs.add(etsOrderFiles.getSystemid());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ����ļ�����(EAM) ETS_ORDER_FILES����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " SYSTEMID,"
				+ " WORKORDER_BATCH,"
				+ " TITEL,"
				+ " FILE_NAME,"
				+ " FILE_PATH,"
				+ " FILE_TYPE,"
				+ " IS_TRUEFILE,"
				+ " REMARK,"
				+ " HANDLER,"
				+ " RECORD_DATE"
				+ " FROM"
				+ " ETS_ORDER_FILES"
				+ " WHERE"
				+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR WORKORDER_BATCH LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR TITEL LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_NAME LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_PATH LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_TYPE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR IS_TRUEFILE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR HANDLER LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR RECORD_DATE LIKE ?)";
			sqlArgs.add(etsOrderFiles.getSystemid());
			sqlArgs.add(etsOrderFiles.getSystemid());
			sqlArgs.add(etsOrderFiles.getWorkorderBatch());
			sqlArgs.add(etsOrderFiles.getWorkorderBatch());
			sqlArgs.add(etsOrderFiles.getTitel());
			sqlArgs.add(etsOrderFiles.getTitel());
			sqlArgs.add(etsOrderFiles.getFileName());
			sqlArgs.add(etsOrderFiles.getFileName());
			sqlArgs.add(etsOrderFiles.getFilePath());
			sqlArgs.add(etsOrderFiles.getFilePath());
			sqlArgs.add(etsOrderFiles.getFileType());
			sqlArgs.add(etsOrderFiles.getFileType());
			sqlArgs.add(etsOrderFiles.getIsTruefile());
			sqlArgs.add(etsOrderFiles.getIsTruefile());
			sqlArgs.add(etsOrderFiles.getRemark());
			sqlArgs.add(etsOrderFiles.getRemark());
			sqlArgs.add(etsOrderFiles.getHandler());
			sqlArgs.add(etsOrderFiles.getHandler());
			sqlArgs.add(etsOrderFiles.getRecordDate());
			sqlArgs.add(etsOrderFiles.getRecordDate());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ����ļ�����(EAM) ETS_ORDER_FILESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsOrderFilesDTO etsOrderFiles = (EtsOrderFilesDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " SYSTEMID,"
				+ " WORKORDER_BATCH,"
				+ " TITEL,"
				+ " FILE_NAME,"
				+ " FILE_PATH,"
				+ " FILE_TYPE,"
				+ " IS_TRUEFILE,"
				+ " REMARK,"
				+ " HANDLER,"
				+ " RECORD_DATE"
				+ " FROM"
				+ " ETS_ORDER_FILES"
				+ " WHERE"
				+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR WORKORDER_BATCH LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR TITEL LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_NAME LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_PATH LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_TYPE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR IS_TRUEFILE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR HANDLER LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR RECORD_DATE LIKE ?)";
			sqlArgs.add(etsOrderFiles.getSystemid());
			sqlArgs.add(etsOrderFiles.getSystemid());
			sqlArgs.add(etsOrderFiles.getWorkorderBatch());
			sqlArgs.add(etsOrderFiles.getWorkorderBatch());
			sqlArgs.add(etsOrderFiles.getTitel());
			sqlArgs.add(etsOrderFiles.getTitel());
			sqlArgs.add(etsOrderFiles.getFileName());
			sqlArgs.add(etsOrderFiles.getFileName());
			sqlArgs.add(etsOrderFiles.getFilePath());
			sqlArgs.add(etsOrderFiles.getFilePath());
			sqlArgs.add(etsOrderFiles.getFileType());
			sqlArgs.add(etsOrderFiles.getFileType());
			sqlArgs.add(etsOrderFiles.getIsTruefile());
			sqlArgs.add(etsOrderFiles.getIsTruefile());
			sqlArgs.add(etsOrderFiles.getRemark());
			sqlArgs.add(etsOrderFiles.getRemark());
			sqlArgs.add(etsOrderFiles.getHandler());
			sqlArgs.add(etsOrderFiles.getHandler());
			sqlArgs.add(etsOrderFiles.getRecordDate());
			sqlArgs.add(etsOrderFiles.getRecordDate());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}