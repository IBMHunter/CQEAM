package com.sino.nm.spare2.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;

import com.sino.framework.sql.BaseSQLProducer;
import com.sino.nm.spare2.dto.AmsItemAllocateDDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemAllocateDModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemAllocateDModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Herui
 * @version 1.0
 */


public class AmsItemAllocateDModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�AMS_ITEM_ALLOCATE_D ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemAllocateDDTO ���β���������
	 */
	public AmsItemAllocateDModel(SfUserDTO userAccount, AmsItemAllocateDDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ�����AMS_ITEM_ALLOCATE_D���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemAllocateDDTO amsItemAllocateD = (AmsItemAllocateDDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " AMS_ITEM_ALLOCATE_D("
			+ " TRANS_ID,"
			+ " DETAIL_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " BATCH_NO,"
			+ " SOURCE_ID,"
			+ " BARCODE"
			+ ") VALUES ("
			+ " ?, AMS_ITEM_ALLOCATE_D_S.NEXTVAL, ?, ?, ?, ?, ?)";
		
		sqlArgs.add(amsItemAllocateD.getTransId());
//		sqlArgs.add(amsItemAllocateD.getDetailId());
		sqlArgs.add(amsItemAllocateD.getItemCode());
		sqlArgs.add(amsItemAllocateD.getAllocateQty());
		sqlArgs.add(amsItemAllocateD.getBatchNo());
		sqlArgs.add(amsItemAllocateD.getSourceId());
		sqlArgs.add(amsItemAllocateD.getBarcode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����AMS_ITEM_ALLOCATE_D���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemAllocateDDTO amsItemAllocateD = (AmsItemAllocateDDTO)dtoParameter;
		String sqlStr = "UPDATE AMS_ITEM_ALLOCATE_D"
			+ " SET"
			+ " TRANS_ID = ?,"
			+ " DETAIL_ID = ?,"
			+ " ITEM_CODE = ?,"
			+ " QUANTITY = ?,"
			+ " BATCH_NO = ?,"
			+ " SOURCE_ID = ?,"
			+ " BARCODE = ?"
			;
		sqlArgs.add(amsItemAllocateD.getTransId());
		sqlArgs.add(amsItemAllocateD.getDetailId());
		sqlArgs.add(amsItemAllocateD.getItemCode());
		sqlArgs.add(amsItemAllocateD.getQuantity());
		sqlArgs.add(amsItemAllocateD.getBatchNo());
		sqlArgs.add(amsItemAllocateD.getSourceId());
		sqlArgs.add(amsItemAllocateD.getBarcode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����AMS_ITEM_ALLOCATE_D����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemAllocateDDTO amsItemAllocateD = (AmsItemAllocateDDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " AMS_ITEM_ALLOCATE_D"	;
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����AMS_ITEM_ALLOCATE_D������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemAllocateDDTO amsItemAllocateD = (AmsItemAllocateDDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " DETAIL_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " BATCH_NO,"
			+ " SOURCE_ID,"
			+ " BARCODE"
			+ " WHERE"
			+ " ROWNUM = 1";
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����AMS_ITEM_ALLOCATE_D����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemAllocateDDTO amsItemAllocateD = (AmsItemAllocateDDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " DETAIL_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " BATCH_NO,"
			+ " SOURCE_ID,"
			+ " BARCODE"
			+ " FROM"
			+ " AMS_ITEM_ALLOCATE_D"
			+ " WHERE"
			+ " (? IS NULL OR TRANS_ID LIKE ?)"
			+ " AND (? IS NULL OR DETAIL_ID LIKE ?)"
			+ " AND (? IS NULL OR ITEM_CODE LIKE ?)"
			+ " AND (? IS NULL OR QUANTITY LIKE ?)"
			+ " AND (? IS NULL OR BATCH_NO LIKE ?)"
			+ " AND (? IS NULL OR SOURCE_ID LIKE ?)"
			+ " AND (? IS NULL OR BARCODE LIKE ?)";
		sqlArgs.add(amsItemAllocateD.getTransId());
		sqlArgs.add(amsItemAllocateD.getTransId());
		sqlArgs.add(amsItemAllocateD.getDetailId());
		sqlArgs.add(amsItemAllocateD.getDetailId());
		sqlArgs.add(amsItemAllocateD.getItemCode());
		sqlArgs.add(amsItemAllocateD.getItemCode());
		sqlArgs.add(amsItemAllocateD.getQuantity());
		sqlArgs.add(amsItemAllocateD.getQuantity());
		sqlArgs.add(amsItemAllocateD.getBatchNo());
		sqlArgs.add(amsItemAllocateD.getBatchNo());
		sqlArgs.add(amsItemAllocateD.getSourceId());
		sqlArgs.add(amsItemAllocateD.getSourceId());
		sqlArgs.add(amsItemAllocateD.getBarcode());
		sqlArgs.add(amsItemAllocateD.getBarcode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����AMS_ITEM_ALLOCATE_Dҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemAllocateDDTO amsItemAllocateD = (AmsItemAllocateDDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " DETAIL_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " BATCH_NO,"
			+ " SOURCE_ID,"
			+ " BARCODE"
			+ " FROM"
			+ " AMS_ITEM_ALLOCATE_D"
			+ " WHERE"
			+ " (? IS NULL OR TRANS_ID LIKE ?)"
			+ " AND (? IS NULL OR DETAIL_ID LIKE ?)"
			+ " AND (? IS NULL OR ITEM_CODE LIKE ?)"
			+ " AND (? IS NULL OR QUANTITY LIKE ?)"
			+ " AND (? IS NULL OR BATCH_NO LIKE ?)"
			+ " AND (? IS NULL OR SOURCE_ID LIKE ?)"
			+ " AND (? IS NULL OR BARCODE LIKE ?)";
		sqlArgs.add(amsItemAllocateD.getTransId());
		sqlArgs.add(amsItemAllocateD.getTransId());
		sqlArgs.add(amsItemAllocateD.getDetailId());
		sqlArgs.add(amsItemAllocateD.getDetailId());
		sqlArgs.add(amsItemAllocateD.getItemCode());
		sqlArgs.add(amsItemAllocateD.getItemCode());
		sqlArgs.add(amsItemAllocateD.getQuantity());
		sqlArgs.add(amsItemAllocateD.getQuantity());
		sqlArgs.add(amsItemAllocateD.getBatchNo());
		sqlArgs.add(amsItemAllocateD.getBatchNo());
		sqlArgs.add(amsItemAllocateD.getSourceId());
		sqlArgs.add(amsItemAllocateD.getSourceId());
		sqlArgs.add(amsItemAllocateD.getBarcode());
		sqlArgs.add(amsItemAllocateD.getBarcode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}