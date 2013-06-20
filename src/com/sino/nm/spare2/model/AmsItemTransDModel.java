package com.sino.nm.spare2.model;



import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;

import com.sino.framework.sql.BaseSQLProducer;
import com.sino.nm.spare2.dto.AmsItemTransDDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemTransDModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemTransDModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Herui
 * @version 1.0
 */


public class AmsItemTransDModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�����ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemTransDDTO ���β���������
	 */
	public AmsItemTransDModel(SfUserDTO userAccount, AmsItemTransDDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ����ɱ���ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " AMS_ITEM_TRANS_D("
			+ " TRANS_ID,"
			+ " LINE_ID,"
			+ " DETAIL_ID,"
			+ " ORGANIZATION_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " CONFIRM_QUANTITY,"
			+ " CUR_ONHAND_QTY"
			+ ") VALUES ("
			+ " ?, ?, AMS_ITEM_TRANS_D_S.NEXTVAL, ?, ?, ?, ?, ?)";

		sqlArgs.add(amsItemTransD.getTransId());
		sqlArgs.add(amsItemTransD.getLineId());
		sqlArgs.add(amsItemTransD.getOrganizationId());
		sqlArgs.add(amsItemTransD.getItemCode());
		sqlArgs.add(amsItemTransD.getQuantity());
		sqlArgs.add(amsItemTransD.getConfirmQuantity());
		sqlArgs.add(amsItemTransD.getCurOnhandQty());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO)dtoParameter;
		String sqlStr = "UPDATE AMS_ITEM_TRANS_D"
			+ " SET"
			+ " TRANS_ID = ?,"
			+ " LINE_ID = ?,"
			+ " ORGANIZATION_ID = ?,"
			+ " ITEM_CODE = ?,"
			+ " QUANTITY = ?,"
			+ " CONFIRM_QUANTITY = ?,"
			+ " CUR_ONHAND_QTY = ?"
			+ " WHERE"
			+ " DETAIL_ID = ?";

		sqlArgs.add(amsItemTransD.getTransId());
		sqlArgs.add(amsItemTransD.getLineId());
		sqlArgs.add(amsItemTransD.getOrganizationId());
		sqlArgs.add(amsItemTransD.getItemCode());
		sqlArgs.add(amsItemTransD.getQuantity());
		sqlArgs.add(amsItemTransD.getConfirmQuantity());
		sqlArgs.add(amsItemTransD.getCurOnhandQty());
		sqlArgs.add(amsItemTransD.getDetailId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " AMS_ITEM_TRANS_D"
				+ " WHERE"
				+ " DETAIL_ID = ?";
			sqlArgs.add(amsItemTransD.getDetailId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " LINE_ID,"
			+ " DETAIL_ID,"
			+ " ORGANIZATION_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " CONFIRM_QUANTITY,"
			+ " CUR_ONHAND_QTY"
			+ " FROM"
			+ " AMS_ITEM_TRANS_D"
			+ " WHERE"
			+ " DETAIL_ID = ?";
		sqlArgs.add(amsItemTransD.getDetailId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " LINE_ID,"
			+ " DETAIL_ID,"
			+ " ORGANIZATION_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " CONFIRM_QUANTITY,"
			+ " CUR_ONHAND_QTY"
			+ " FROM"
			+ " AMS_ITEM_TRANS_D"
			+ " WHERE"
			+ " (? IS NULL OR TRANS_ID LIKE ?)"
			+ " AND (? IS NULL OR LINE_ID LIKE ?)"
			+ " AND (? IS NULL OR DETAIL_ID LIKE ?)"
			+ " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
			+ " AND (? IS NULL OR ITEM_CODE LIKE ?)"
			+ " AND (? IS NULL OR QUANTITY LIKE ?)"
			+ " AND (? IS NULL OR CONFIRM_QUANTITY LIKE ?)"
			+ " AND (? IS NULL OR CUR_ONHAND_QTY LIKE ?)";
		sqlArgs.add(amsItemTransD.getTransId());
		sqlArgs.add(amsItemTransD.getTransId());
		sqlArgs.add(amsItemTransD.getLineId());
		sqlArgs.add(amsItemTransD.getLineId());
		sqlArgs.add(amsItemTransD.getDetailId());
		sqlArgs.add(amsItemTransD.getDetailId());
		sqlArgs.add(amsItemTransD.getOrganizationId());
		sqlArgs.add(amsItemTransD.getOrganizationId());
		sqlArgs.add(amsItemTransD.getItemCode());
		sqlArgs.add(amsItemTransD.getItemCode());
		sqlArgs.add(amsItemTransD.getQuantity());
		sqlArgs.add(amsItemTransD.getQuantity());
		sqlArgs.add(amsItemTransD.getConfirmQuantity());
		sqlArgs.add(amsItemTransD.getConfirmQuantity());
		sqlArgs.add(amsItemTransD.getCurOnhandQty());
		sqlArgs.add(amsItemTransD.getCurOnhandQty());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ���ҵ����ϸ��(AMS) AMS_ITEM_TRANS_Dҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemTransDDTO amsItemTransD = (AmsItemTransDDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " TRANS_ID,"
			+ " LINE_ID,"
			+ " DETAIL_ID,"
			+ " ORGANIZATION_ID,"
			+ " ITEM_CODE,"
			+ " QUANTITY,"
			+ " CONFIRM_QUANTITY,"
			+ " CUR_ONHAND_QTY"
			+ " FROM"
			+ " AMS_ITEM_TRANS_D"
			+ " WHERE"
			+ " (? IS NULL OR TRANS_ID LIKE ?)"
			+ " AND (? IS NULL OR LINE_ID LIKE ?)"
			+ " AND (? IS NULL OR DETAIL_ID LIKE ?)"
			+ " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
			+ " AND (? IS NULL OR ITEM_CODE LIKE ?)"
			+ " AND (? IS NULL OR QUANTITY LIKE ?)"
			+ " AND (? IS NULL OR CONFIRM_QUANTITY LIKE ?)"
			+ " AND (? IS NULL OR CUR_ONHAND_QTY LIKE ?)";
		sqlArgs.add(amsItemTransD.getTransId());
		sqlArgs.add(amsItemTransD.getTransId());
		sqlArgs.add(amsItemTransD.getLineId());
		sqlArgs.add(amsItemTransD.getLineId());
		sqlArgs.add(amsItemTransD.getDetailId());
		sqlArgs.add(amsItemTransD.getDetailId());
		sqlArgs.add(amsItemTransD.getOrganizationId());
		sqlArgs.add(amsItemTransD.getOrganizationId());
		sqlArgs.add(amsItemTransD.getItemCode());
		sqlArgs.add(amsItemTransD.getItemCode());
		sqlArgs.add(amsItemTransD.getQuantity());
		sqlArgs.add(amsItemTransD.getQuantity());
		sqlArgs.add(amsItemTransD.getConfirmQuantity());
		sqlArgs.add(amsItemTransD.getConfirmQuantity());
		sqlArgs.add(amsItemTransD.getCurOnhandQty());
		sqlArgs.add(amsItemTransD.getCurOnhandQty());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}