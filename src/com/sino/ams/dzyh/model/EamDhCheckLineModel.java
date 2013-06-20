package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamDhCheckLineModel</p>
 * <p>Description:�����Զ�����SQL��������EamDhCheckLineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EamDhCheckLineModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ֵ�׺��̵㹤���б� EAM_DH_CHECK_LINE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCheckLineDTO ���β���������
	 */
	public EamDhCheckLineModel(SfUserDTO userAccount, EamDhCheckLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɵ�ֵ�׺��̵㹤���б� EAM_DH_CHECK_LINE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO) dtoParameter;
		String orderType = dto.getOrderType();
		String sqlStr = "INSERT INTO"
						+ " EAM_DH_CHECK_LINE("
						+ " HEADER_ID,"
						+ " BATCH_ID,"
						+ " BARCODE,"
						+ " ITEM_CODE,"
						+ " ITEM_CATEGORY,"
						+ " ITEM_NAME,"
						+ " ITEM_SPEC,"
						+ " ITEM_CATEGORY2,"
						+ " PRICE,"
						+ " START_DATE,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_USER_NAME,"
						+ " RESPONSIBILITY_DEPT,"
						+ " RESPONSIBILITY_DEPT_NAME,"
						+ " MAINTAIN_USER,"
						+ " VENDOR_NAME,"
						+ " SYSTEM_STATUS"
						+ " ) ("
						+ " SELECT"
						+ " EDCH.HEADER_ID,"
						+ " EDCH.BATCH_ID,"
						+ " EII.BARCODE,"
						+ " ESI.ITEM_CODE,"
						+ " ESI.ITEM_CATEGORY,"
						+ " ESI.ITEM_NAME,"
						+ " ESI.ITEM_SPEC,"
						+ " ESI.ITEM_CATEGORY2,"
						+ " EII.PRICE,"
						+ " EII.START_DATE,"
						+ " EII.RESPONSIBILITY_USER,"
						+ " AME.USER_NAME,"
						+ " AMD.DEPT_CODE,"
						+ " AMD.DEPT_NAME,"
						+ " EII.MAINTAIN_USER,"
						+ " EII.ATTRIBUTE3,"
						+ " 'Y'"
						+ " FROM"
						+ " EAM_DH_CHECK_HEADER EDCH,"
						+ " ETS_OBJECT          EO,"
						+ " AMS_OBJECT_ADDRESS  AOA,"
						+ " ETS_ITEM_INFO       EII,"
						+ " ETS_SYSTEM_ITEM     ESI,"
						+ " AMS_MIS_EMPLOYEE    AME,"
						+ " AMS_MIS_DEPT        AMD"
						+ " WHERE"
						+ " EDCH.CHECK_LOCATION = EO.WORKORDER_OBJECT_NO"
						+ " AND EO.WORKORDER_OBJECT_NO = AOA.OBJECT_NO"
						+ " AND AOA.BOX_NO = '0000'"
						+ " AND AOA.NET_UNIT = '0000'"
						+ " AND AOA.ADDRESS_ID = EII.ADDRESS_ID"
						+ " AND EII.ITEM_CODE = ESI.ITEM_CODE"
						+ " AND EII.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID"
						+ " AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE"
						+ " AND NOT EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " EAM_DH_CHECK_LINE EDCL"
						+ " WHERE"
						+ " EDCH.HEADER_ID = EDCL.HEADER_ID"
						+ " AND EII.BARCODE = EDCL.BARCODE)"
						+ " AND EDCH.HEADER_ID = ?";

		sqlArgs.add(dto.getHeaderId());
		if(orderType.equals(LvecDicts.ORD_TYPE1_YQYB)){//�����Ǳ���
			sqlStr = sqlStr
					 + " AND ESI.ITEM_CATEGORY='YQYB')";
		} else {//��ֵ�׺���
			sqlStr = sqlStr
					 + " AND ESI.ITEM_CATEGORY='DZYH')";
		}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ȡ����������SQL
	 * @param foreignKey String
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO) dtoParameter;
		if(foreignKey.equals("headerId")){
			sqlModel = getDataByHeaderIdModel(dto.getHeaderId());
		}
		return sqlModel;
	}

	/**
	 *���ܣ�������ݹ���ͷID��ȡ������SQL
	 * @param headerId String
	 * @return SQLModel
	 */
	private SQLModel getDataByHeaderIdModel(String headerId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " HEADER_ID,"
						+ " BATCH_ID,"
						+ " BARCODE,"
						+ " ITEM_CODE,"
						+ " ITEM_CATEGORY,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY_VALUE,"
						+ " ITEM_NAME,"
						+ " ITEM_SPEC,"
						+ " ITEM_CATEGORY2,"
						+ " PRICE,"
						+ " START_DATE,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_USER_NAME,"
						+ " RESPONSIBILITY_DEPT,"
						+ " RESPONSIBILITY_DEPT_NAME,"
						+ " MAINTAIN_USER,"
						+ " VENDOR_ID,"
						+ " VENDOR_NAME,"
						+ " SCAN_ITEM_CODE,"
						+ " SCAN_ITEM_CATEGORY,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(SCAN_ITEM_CATEGORY, 'ITEM_TYPE') SCAN_ITEM_CATEGORY_VALUE,"
						+ " SCAN_ITEM_NAME,"
						+ " SCAN_ITEM_SPEC,"
						+ " SCAN_ITEM_CATEGORY2,"
						+ " SCAN_PRICE,"
						+ " SCAN_START_DATE,"
						+ " SCAN_RESPONSIBILITY_USER,"
						+ " SCAN_RESPONSIBILITY_USER_NAME,"
						+ " SCAN_RESPONSIBILITY_DEPT,"
						+ " SCAN_RESPONSIBILITY_DEPT_NAME,"
						+ " SCAN_MAINTAIN_USER,"
						+ " SCAN_VENDOR_ID,"
						+ " SCAN_VENDOR_NAME,"
						+ " ARCH_ITEM_CODE,"
						+ " ARCH_ITEM_CATEGORY,"
						+ " AMS_PUB_PKG.GET_FLEX_VALUE(ARCH_ITEM_CATEGORY, 'ITEM_TYPE') ARCH_ITEM_CATEGORY_VALUE,"
						+ " ARCH_ITEM_NAME,"
						+ " ARCH_ITEM_SPEC,"
						+ " ARCH_ITEM_CATEGORY2,"
						+ " ARCH_PRICE,"
						+ " ARCH_START_DATE,"
						+ " ARCH_RESPONSIBILITY_USER,"
						+ " ARCH_RESPONSIBILITY_DEPT,"
						+ " ARCH_RESPONSIBILITY_USER_NAME,"
						+ " ARCH_RESPONSIBILITY_DEPT_NAME,"
						+ " ARCH_MAINTAIN_USER,"
						+ " ARCH_VENDOR_ID,"
						+ " ARCH_VENDOR_NAME,"
						+ " ITEM_CODE_DIFF_REASON,"
						+ " ADDRESS_DIFF_REASON,"
						+ " USER_DIFF_REASON,"
						+ " DEPT_DIFF_REASON,"
						+ " CATEGORY2_DIFF_REASON,"
						+ " VENDOR_DIFF_REASON,"
						+ " PRICE_DIFF_REASON,"
						+ " START_DATE_DIFF_REASON,"
						+ " MAIN_USER_DIFF_REASON,"
						+ " CONFIRM_USER,"
						+ " AMS_PUB_PKG.GET_USER_NAME(CONFIRM_USER) CONFIRM_USER_NAME,"
						+ " CONFIRM_DATE,"
						+ " SYSTEM_STATUS,"
						+ " SCAN_STATUS,"
						+ " ARCHIVE_STATUS,"
						+ " REMARK,"
						+ " ARCHIVE_REMARK,"
						+ " ARCH_TO_TEMP_INV"
						+ " FROM"
						+ " EAM_DH_CHECK_LINE"
						+ " WHERE"
						+ " HEADER_ID = ?";
		sqlArgs.add(headerId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����������ɾ������������SQL
	 * @param foreignKey String
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO) dtoParameter;
		if(foreignKey.equals("batchId")){
			sqlModel = getDeleteByBatchIdModel(dto.getBatchId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������ݹ�����IDɾ������������SQL
	 * @param batchId String
	 * @return SQLModel
	 */
	private SQLModel getDeleteByBatchIdModel(String batchId) {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "DELETE FROM"
						+ " EAM_DH_CHECK_HEADER EDCH"
						+ " WHERE"
						+ " EDCH.BATCH_ID = ?";

		List sqlArgs = new ArrayList();
		sqlArgs.add(batchId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
