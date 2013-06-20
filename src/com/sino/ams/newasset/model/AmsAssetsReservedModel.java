package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * <p>
 * Title: AmsAssetsReservedModel
 * </p>
 * <p>
 * Description:�����Զ�����SQL��������AmsAssetsReservedModel�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsReservedModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ��������� AMS_ASSETS_RESERVED ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            AmsAssetsReservedDTO ���β���������
	 */
	public AmsAssetsReservedModel(SfUserDTO userAccount,
			AmsAssetsReservedDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ��������� AMS_ASSETS_RESERVED���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsReservedDTO dto = (AmsAssetsReservedDTO) dtoParameter;
			String sqlStr = "INSERT INTO " + " AMS_ASSETS_RESERVED("
					+ " TRANS_ID," + " RESERVED_DATE," + " BARCODE"
					+ ") VALUES (" + " ?, ?, ?)";
			sqlArgs.add(dto.getTransId());
			sqlArgs.add(dto.getReservedDate());
			sqlArgs.add(dto.getBarcode());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��������� AMS_ASSETS_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsReservedDTO dto = (AmsAssetsReservedDTO) dtoParameter;
		String sqlStr = "DELETE FROM" + " AMS_ASSETS_RESERVED" + " WHERE"
				+ " TRANS_ID = ?";
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��������� AMS_ASSETS_RESERVED������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsReservedDTO dto = (AmsAssetsReservedDTO) dtoParameter;
		String sqlStr = "SELECT " + " TRANS_ID," + " RESERVED_DATE,"
				+ " BARCODE" + " FROM" + " AMS_ASSETS_RESERVED" + " WHERE"
				+ " TRANS_ID = ?";
		sqlArgs.add(dto.getTransId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� transId �����ѯ����SQL�� ����Զ����������ʲ��������� AMS_ASSETS_RESERVED��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @param transId
	 *            String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByTransIdModel(String transId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT " + " RESERVED_DATE," + " BARCODE" + " FROM"
				+ " AMS_ASSETS_RESERVED" + " WHERE" + " TRANS_ID = ?";
		sqlArgs.add(transId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcode �����ѯ����SQL�� ����Զ����������ʲ��������� AMS_ASSETS_RESERVED��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @param barcode
	 *            String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByBarcodeModel(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT " + " TRANS_ID," + " RESERVED_DATE," + " FROM"
				+ " AMS_ASSETS_RESERVED" + " WHERE" + " BARCODE = ?";
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * 
	 * @param foreignKey
	 *            ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsAssetsReservedDTO dto = (AmsAssetsReservedDTO) dtoParameter;
		if (foreignKey.equals("transId")) {
			sqlModel = getDataByTransIdModel(dto.getTransId());
		} else if (foreignKey.equals("barcode")) {
			sqlModel = getDataByBarcodeModel(dto.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� transId ��������ɾ��SQL�� ����Զ����������ʲ��������� AMS_ASSETS_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @param transId
	 *            String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByTransIdModel(String transId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE "
                + " FROM"
                + " AMS_ASSETS_RESERVED"
                + " WHERE"
				+ " TRANS_ID = ?";
		sqlArgs.add(transId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcode ��������ɾ��SQL�� ����Զ����������ʲ��������� AMS_ASSETS_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @param barcode
	 *            String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByBarcodeModel(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE " + " FROM" + " AMS_ASSETS_RESERVED" + " WHERE"
				+ " BARCODE = ?";
		sqlArgs.add(barcode);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * 
	 * @param foreignKey
	 *            ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsAssetsReservedDTO dto = (AmsAssetsReservedDTO) dtoParameter;
		if (foreignKey.equals("transId")) {
			sqlModel = getDeleteByTransIdModel(dto.getTransId());
		} else if (foreignKey.equals("barcode")) {
			sqlModel = getDeleteByBarcodeModel(dto.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��������� AMS_ASSETS_RESERVEDҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsReservedDTO dto = (AmsAssetsReservedDTO) dtoParameter;
			String sqlStr = "SELECT " + " AAR.TRANS_ID,"
					+ " AAR.RESERVED_DATE," + " AAR.BARCODE" + " FROM"
					+ " AMS_ASSETS_RESERVED AAR" + " WHERE"
					+ " AAR.RESERVED_DATE >= ISNULL(?, AAR.RESERVED_DATE)"
					+ " AND AAR.RESERVED_DATE <= ISNULL(?, AAR.RESERVED_DATE)"
					+ " AND AAR.BARCODE LIKE dbo.NVL(?, AAR.BARCODE)";
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getBarcode());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��������� AMS_ASSETS_RESERVED���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel(String transId, String barCode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO " 
						+ " AMS_ASSETS_RESERVED(" 
						+ " TRANS_ID,"
						+ " RESERVED_DATE," 
						+ " BARCODE" 
						+ ") VALUES ("
						+ " ?, GETDATE() , ?)";
		sqlArgs.add(transId);
		sqlArgs.add(barCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��������� AMS_ASSETS_RESERVED����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel( String transId ) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM" 
						+ " AMS_ASSETS_RESERVED" 
						+ " WHERE"
						+ " TRANS_ID = ?";
		sqlArgs.add( transId );
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
