package com.sino.ams.spare.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.sql.BaseSQLProducer;
import com.sino.ams.spare.dto.AmsItemReservedDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemReservedModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemReservedModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Herui
 * @version 1.0
 */


public class AmsItemReservedModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�����ҵ������(AMS) AMS_ITEM_RESERVED ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemReservedDTO ���β���������
	 */
	public AmsItemReservedModel(SfUserDTO userAccount, AmsItemReservedDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ����ɱ���ҵ������(AMS) AMS_ITEM_RESERVED���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " AMS_ITEM_RESERVED("
				+ " TRANS_ID,"
				+ " RESERVED_DATE,"
				+ " ITEM_CODE,"
				+ " RESERVED_COUNT"
				+ ") VALUES ("
				+ " AMS_ITEM_RESERVED_S.NEXTVAL, ?, ?, ?)";
		
			sqlArgs.add(amsItemReserved.getReservedDate());
			sqlArgs.add(amsItemReserved.getItemCode());
			sqlArgs.add(amsItemReserved.getReservedCount());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ������(AMS) AMS_ITEM_RESERVED���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
			String sqlStr = "UPDATE AMS_ITEM_RESERVED"
				+ " SET"
				+ " RESERVED_DATE = ?,"
				+ " ITEM_CODE = ?,"
				+ " RESERVED_COUNT = ?"
				+ " WHERE"
				+ " TRANS_ID = ?";
		
			sqlArgs.add(amsItemReserved.getReservedDate());
			sqlArgs.add(amsItemReserved.getItemCode());
			sqlArgs.add(amsItemReserved.getReservedCount());
			sqlArgs.add(amsItemReserved.getTransId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ������(AMS) AMS_ITEM_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " AMS_ITEM_RESERVED"
				+ " WHERE"
				+ " TRANS_ID = ?";
			sqlArgs.add(amsItemReserved.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ������(AMS) AMS_ITEM_RESERVED������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " RESERVED_DATE,"
			+ " ITEM_CODE,"
			+ " RESERVED_COUNT"
			+ " FROM"
			+ " AMS_ITEM_RESERVED"
			+ " WHERE"
			+ " TRANS_ID = ?";
		sqlArgs.add(amsItemReserved.getTransId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ������(AMS) AMS_ITEM_RESERVED����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳� */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " TRANS_ID,"
				+ " RESERVED_DATE,"
				+ " ITEM_CODE,"
				+ " RESERVED_COUNT"
				+ " FROM"
				+ " AMS_ITEM_RESERVED"
				+ " WHERE"
				+ " (? IS NULL OR TRANS_ID LIKE ?)"
				+ " AND (? IS NULL OR RESERVED_DATE LIKE ?)"
				+ " AND (? IS NULL OR ITEM_CODE LIKE ?)"
				+ " AND (? IS NULL OR RESERVED_COUNT LIKE ?)";
			sqlArgs.add(amsItemReserved.getTransId());
			sqlArgs.add(amsItemReserved.getTransId());
			sqlArgs.add(amsItemReserved.getReservedDate());
			sqlArgs.add(amsItemReserved.getReservedDate());
			sqlArgs.add(amsItemReserved.getItemCode());
			sqlArgs.add(amsItemReserved.getItemCode());
			sqlArgs.add(amsItemReserved.getReservedCount());
			sqlArgs.add(amsItemReserved.getReservedCount());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� transId �����ѯ����SQL��
	 * ����Զ��������ݱ���ҵ������(AMS) AMS_ITEM_RESERVED��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param transId String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByTransIdModel(String transId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " RESERVED_DATE,"
			+ " ITEM_CODE,"
			+ " RESERVED_COUNT"
			+ " FROM"
			+ " AMS_ITEM_RESERVED"
			+ " WHERE"
			+ " TRANS_ID = ?";
		sqlArgs.add(transId);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� itemCode �����ѯ����SQL��
	 * ����Զ��������ݱ���ҵ������(AMS) AMS_ITEM_RESERVED��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param itemCode String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByItemCodeModel(String itemCode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " RESERVED_DATE,"
			+ " RESERVED_COUNT"
			+ " FROM"
			+ " AMS_ITEM_RESERVED"
			+ " WHERE"
			+ " ITEM_CODE = ?";
		sqlArgs.add(itemCode);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
		if(foreignKey.equals("transId")){
			sqlModel = getDataByTransIdModel(amsItemReserved.getTransId());
		} else if(foreignKey.equals("itemCode")){
			sqlModel = getDataByItemCodeModel(amsItemReserved.getItemCode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� transId ��������ɾ��SQL��
	 * ����Զ��������ݱ���ҵ������(AMS) AMS_ITEM_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param transId String 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByTransIdModel(String transId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " RESERVED_DATE,"
			+ " ITEM_CODE,"
			+ " RESERVED_COUNT"
			+ " FROM"
			+ " AMS_ITEM_RESERVED"
			+ " WHERE"
			+ " TRANS_ID = ?";
		sqlArgs.add(transId);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� itemCode ��������ɾ��SQL��
	 * ����Զ��������ݱ���ҵ������(AMS) AMS_ITEM_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param itemCode String 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByItemCodeModel(String itemCode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " RESERVED_DATE,"
			+ " RESERVED_COUNT"
			+ " FROM"
			+ " AMS_ITEM_RESERVED"
			+ " WHERE"
			+ " ITEM_CODE = ?";
		sqlArgs.add(itemCode);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
		if(foreignKey.equals("transId")){
			sqlModel = getDeleteByTransIdModel(amsItemReserved.getTransId());
		} else if(foreignKey.equals("itemCode")){
			sqlModel = getDeleteByItemCodeModel(amsItemReserved.getItemCode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ������(AMS) AMS_ITEM_RESERVEDҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳� */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsItemReservedDTO amsItemReserved = (AmsItemReservedDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " TRANS_ID,"
				+ " RESERVED_DATE,"
				+ " ITEM_CODE,"
				+ " RESERVED_COUNT"
				+ " FROM"
				+ " AMS_ITEM_RESERVED"
				+ " WHERE"
				+ " (? IS NULL OR TRANS_ID LIKE ?)"
				+ " AND (? IS NULL OR RESERVED_DATE LIKE ?)"
				+ " AND (? IS NULL OR ITEM_CODE LIKE ?)"
				+ " AND (? IS NULL OR RESERVED_COUNT LIKE ?)";
			sqlArgs.add(amsItemReserved.getTransId());
			sqlArgs.add(amsItemReserved.getTransId());
			sqlArgs.add(amsItemReserved.getReservedDate());
			sqlArgs.add(amsItemReserved.getReservedDate());
			sqlArgs.add(amsItemReserved.getItemCode());
			sqlArgs.add(amsItemReserved.getItemCode());
			sqlArgs.add(amsItemReserved.getReservedCount());
			sqlArgs.add(amsItemReserved.getReservedCount());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}