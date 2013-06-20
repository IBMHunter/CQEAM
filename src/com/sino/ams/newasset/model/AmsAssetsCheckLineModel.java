package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsAssetsCheckLineModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsCheckLineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsCheckLineModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsCheckLineDTO ���β���������
	 */
	public AmsAssetsCheckLineModel(SfUserDTO userAccount,
								   AmsAssetsCheckLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsCheckLineDTO dto = (AmsAssetsCheckLineDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_ASSETS_CHECK_LINE("
						+ " HEADER_ID,"
						+ " BARCODE,"
						+ " SYSTEM_STATUS,"
						+ " REMARK"
						+ ") VALUES ("
						+ " ?, ?, ?, ?)";

		sqlArgs.add(dto.getHeaderId());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(AssetsDictConstant.STATUS_YES);
		sqlArgs.add(dto.getRemark());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�������������ֶ� headerId �����ѯ����SQL��
	 * ����Զ����������ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param headerId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByHeaderIdModel(String headerId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AACL.BARCODE,"
						+ " AACL.ITEM_CODE,"
						+ " ESI.ITEM_CATEGORY,"
						+ " dbo.APP_GET_FLEX_VALUE(ESI.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY_NAME,"
						+ " ESI.ITEM_NAME,"
						+ " ESI.ITEM_SPEC,"
						+ " AME1.EMPLOYEE_ID RESPONSIBILITY_USER,"
						+ " AME1.USER_NAME RESPONSIBILITY_USER_NAME,"
						+ " AMD1.DEPT_CODE RESPONSIBILITY_DEPT,"
						+ " AMD1.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
						+ " AACL.SCAN_ITEM_CODE,"
						+ " AACL.SCAN_ITEM_CATEGORY,"
						+ " dbo.APP_GET_FLEX_VALUE(AACL.SCAN_ITEM_CATEGORY, 'ITEM_TYPE') SCAN_ITEM_CATEGORY_NAME,"
						+ " AACL.SCAN_ITEM_NAME,"
						+ " AACL.SCAN_ITEM_SPEC,"
						+ " AME2.EMPLOYEE_ID SCAN_RESPONSIBILITY_USER,"
						+ " AME2.USER_NAME SCAN_RESPONSIBILITY_USER_NAME,"
						+ " AMD2.DEPT_CODE SCAN_RESPONSIBILITY_DEPT,"
						+ " AMD2.DEPT_NAME SCAN_RESPONSIBILITY_DEPT_NAME,"
						+ " AACL.HEADER_ID,"
						+ " AACH.CHECK_LOCATION,"
						+ " EO.WORKORDER_OBJECT_LOCATION,"
						+ " AOA.ADDRESS_ID,"
						+ " AACL.ARCHIVE_REMARK,"
						+ " AACL.SYSTEM_STATUS,"
						+ " AACL.SCAN_STATUS,"
						+ " AACL.ARCHIVE_STATUS,"
						+ " AACL.SCAN_START_DATE,"
						+ " case AACL.SYSTEM_STATUS when 'Y' then '��' else  '��' end SYSTEM_STATUS_NAME,"
						+ " case AACL.SCAN_STATUS when 'Y' then '��' else '��' end SCAN_STATUS_NAME,"
						+ " case AACL.ARCHIVE_STATUS when 'Y' then '��' else '��'end ARCHIVE_STATUS_NAME,"
						+ " AACL2.CHK_TIMES,"
						+ " AACL2.LAST_CHK_DATE,"
						+ " AACL2.LAST_CHK_NO,"
						+ " AACL.MAINTAIN_USER,"
						+ " AACL.SCAN_MAINTAIN_USER,"
						+ " AACL.ARCH_MAINTAIN_USER,"
						+ " AACL.MANUFACTURER_ID,"
						+ " AACL.IS_SHARE,"
						+ " AACL.CONTENT_CODE,"
						+ " AACL.CONTENT_NAME,"
						+ " AACL.POWER,"
						+ " AACL.REPLACE_FLAG,"
						+ " AACL.NEW_TAG,"
						+ " AACL.CONSTRUCT_STATUS,"
						+ " AACL.LNE_ID,"
						+ " AACL.CEX_ID,"
						+ " AACL.OPE_ID,"
						+ " AACL.NLE_ID"
						+ " FROM"
						+ " AMS_ASSETS_CHECK_LINE     AACL,"
						+ " ETS_SYSTEM_ITEM           ESI,"
						+ " AMS_ASSETS_CHECK_HEADER   AACH,"
						+ " ETS_OBJECT                EO,"
						+ " AMS_OBJECT_ADDRESS        AOA,"
						+ " AMS_MIS_DEPT              AMD1,"
						+ " AMS_MIS_DEPT              AMD2,"
						+ " AMS_MIS_EMPLOYEE          AME1,"
						+ " AMS_MIS_EMPLOYEE          AME2,"
						+ " AMS_ASSETS_CHK_LOG        AACL2"
						+ " WHERE"
						+ " AACL.ITEM_CODE *= ESI.ITEM_CODE"
						+ " AND AACL.HEADER_ID = AACH.HEADER_ID"
						+ " AND AACL.BARCODE *= AACL2.BARCODE"
						+ " AND AACH.HEADER_ID = ?"
						+ " AND AACH.CHECK_LOCATION = EO.WORKORDER_OBJECT_NO"
						+ " AND EO.WORKORDER_OBJECT_NO *= AOA.OBJECT_NO"
						+ " AND '0000' = AOA.BOX_NO"
						+ " AND '0000' = AOA.NET_UNIT"
						+ " AND AACL.RESPONSIBILITY_USER *= AME1.EMPLOYEE_ID"
						+ " AND AACL.RESPONSIBILITY_DEPT *= AMD1.DEPT_CODE"
						+ " AND AACL.SCAN_RESPONSIBILITY_USER *= AME2.EMPLOYEE_ID"
						+ " AND AACL.SCAN_RESPONSIBILITY_DEPT *= AMD2.DEPT_CODE"
//						+ " AND EXISTS("
//						+ " SELECT"
//						+ " NULL"
//						+ " FROM"
//						+ " ETS_SYSTEM_ITEM ESI"
//						+ " WHERE"
//						+ " ESI.ITEM_CODE = AACL.ITEM_CODE"
//						+ " AND ESI.ITEM_CATEGORY = dbo.NVL(AACH.CHECK_CATEGORY, ESI.ITEM_CATEGORY))"
						+ " ORDER BY"
						+ " AACL.RESPONSIBILITY_DEPT,"
						+ " AACL.RESPONSIBILITY_USER";
		sqlArgs.add(headerId);
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
		AmsAssetsCheckLineDTO dto = (AmsAssetsCheckLineDTO) dtoParameter;
		if (foreignKey.equals("headerId")) {
			sqlModel = getDataByHeaderIdModel(dto.getHeaderId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� headerId ��������ɾ��SQL��
	 * ����Զ����������ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param headerId String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByHeaderIdModel(String headerId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE"
						+ " FROM"
						+ " AMS_ASSETS_CHECK_LINE"
						+ " WHERE"
						+ " HEADER_ID = ?";
		sqlArgs.add(headerId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� batchId ��������ɾ��SQL��
	 * ����Զ����������ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param batchId String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByBatchIdModel(int batchId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE"
						+ " FROM"
						+ " AMS_ASSETS_CHECK_LINE"
						+ " WHERE"
						+ " BATCH_ID = ?";
		sqlArgs.add(batchId);
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
		AmsAssetsCheckLineDTO dto = (AmsAssetsCheckLineDTO) dtoParameter;
		if (foreignKey.equals("headerId")) {
			sqlModel = getDeleteByHeaderIdModel(dto.getHeaderId());
		} else if (foreignKey.equals("batchId")) {
			sqlModel = getDeleteByBatchIdModel(dto.getBatchId());
		}
		return sqlModel;
	}
}
