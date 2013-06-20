package com.sino.td.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.td.newasset.dto.TdAssetsTransLineDTO;

/**
 *
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class TdRcvLineModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ�ҵ���б�(EAM) TD_ASSETS_TRANS_LINE ���ݿ�SQL����㹹�캯��
	 *
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter TdAssetsTransLineDTO ���β���������
	 */
	public TdRcvLineModel(SfUserDTO userAccount, TdAssetsTransLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) throws SQLModelException {
		SQLModel sqlModel = null;
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		if (foreignKey.equals("transId")) {
			sqlModel = getDataByTransIdModel(dto.getTransId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� transId �����ѯ����SQL��
	 * ����Զ���������TD_ASSETS_TRANS_LINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param transId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 * @throws SQLModelException
	 */
	private SQLModel getDataByTransIdModel(String transId) throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			String sqlStr = "SELECT"
							+ " AATL.TRANS_ID,"
							+ " AATL.LINE_ID,"
							+ " AATL.BARCODE,"
							+ " AATL.NEW_BARCODE,"
							+ " AATL.REMARK,"
							+ " AAAV.ASSET_NUMBER,"
							+ " AAAV.ASSETS_DESCRIPTION,"
							+ " AAAV.MODEL_NUMBER,"
							+ " ISNULL(AAAV.CURRENT_UNITS, 1) CURRENT_UNITS,"
							+ " AAAV.COST,"
							+ " AAAV.DEPRN_COST,"
							+ " AAAV.DEPRECIATION,"
							+ " AAAV.DATE_PLACED_IN_SERVICE,"
							+ " EOO.WORKORDER_OBJECT_NO OLD_LOCATION,"
							+ " EOO.WORKORDER_OBJECT_NAME OLD_LOCATION_NAME,"
							+ " AMEO.EMPLOYEE_ID OLD_RESPONSIBILITY_USER,"
							+ " AMEO.USER_NAME OLD_RESPONSIBILITY_USER_NAME,"
							+ " AMDO.DEPT_CODE OLD_RESPONSIBILITY_DEPT,"
							+ " AMDO.DEPT_NAME OLD_RESPONSIBILITY_DEPT_NAME,"
							+ " AATL.OLD_FA_CATEGORY_CODE,"
							+ " AATL.OLD_DEPRECIATION_ACCOUNT,"
							+ " AATL.FA_CATEGORY_CODE,"
							+ " AATL.DEPRECIATION_ACCOUNT,"
							+ " AATL.LINE_TRANS_DATE,"
							+ " EON.WORKORDER_OBJECT_NO ASSIGNED_TO_LOCATION,"
							+ " EON.WORKORDER_OBJECT_NAME ASSIGNED_TO_LOCATION_NAME,"
							+ " AMS_ASSETS_PKG.GET_TO_ORGNIZATION_ID(AATL.LINE_ID) TO_ORGANIZATION_ID,"
							+ " AMEN.EMPLOYEE_ID RESPONSIBILITY_USER,"
							+ " AMEN.USER_NAME RESPONSIBILITY_USER_NAME,"
							+ " AMDN.DEPT_CODE RESPONSIBILITY_DEPT,"
							+ " AMDN.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
							+ " (SELECT"
							+ " AOA.ADDRESS_ID"
							+ " FROM"
							+ " AMS_OBJECT_ADDRESS AOA"
							+ " WHERE"
							+ " AOA.OBJECT_NO = EON.WORKORDER_OBJECT_NO"
							+ " AND AOA.BOX_NO = '0000'"
							+ " AND AOA.NET_UNIT = '0000'"
							+ " ) ADDRESS_ID"
							+ " FROM"
							+ " TD_ASSETS_TRANS_LINE AATL,"
							+ " AMS_MIS_EMPLOYEE      AMEO,"
							+ " AMS_MIS_DEPT          AMDO,"
							+ " ETS_OBJECT            EOO,"
							+ " AMS_MIS_EMPLOYEE      AMEN,"
							+ " AMS_MIS_DEPT          AMDN,"
							+ " ETS_OBJECT            EON,"
							+ " TD_ASSETS_ADDRESS_V  AAAV"
							+ " WHERE"
							+ " AATL.OLD_LOCATION = EOO.WORKORDER_OBJECT_NO"
							+ " AND AATL.OLD_RESPONSIBILITY_USER *= AMEO.EMPLOYEE_ID"
							+ " AND AATL.OLD_RESPONSIBILITY_DEPT *= AMDO.DEPT_CODE"
							+ " AND AATL.ASSIGNED_TO_LOCATION *= EON.WORKORDER_OBJECT_NO"
							+ " AND AATL.RESPONSIBILITY_USER *= AMEN.EMPLOYEE_ID"
							+ " AND AATL.RESPONSIBILITY_DEPT *= AMDN.DEPT_CODE"
							+ " AND AATL.BARCODE = AAAV.BARCODE"
							+ " AND AATL.ASSIGNED_DATE  " + SyBaseSQLUtil.isNullNoParam() + " "
							+ " AND AATL.TRANS_ID = ?";
			sqlArgs.add(transId);
			if (!userAccount.isComAssetsManager()) {
				DTOSet depts = userAccount.getPriviDeptCodes();
				String deptCodes = "''";
				if (depts != null && !depts.isEmpty()) {
					List deptList = depts.toList("deptCode");
					deptCodes = StrUtil.list2String(deptList);
				}
				sqlStr += " AND AATL.RESPONSIBILITY_DEPT IN (" + deptCodes + ")";
			} else {
				sqlStr = sqlStr
						 + " AND EXISTS ("
						 + " SELECT"
						 + " NULL"
						 + " FROM"
						 + " AMS_MIS_DEPT          AMD"
						 + " WHERE"
						 + " AATL.RESPONSIBILITY_DEPT = AMD.DEPT_CODE"
						 + " AND AMD.COMPANY_CODE = ?)";
				sqlArgs.add(userAccount.getCompanyCode());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (DTOException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ʲ�����SQL��
	 * @return SQLModel
	 */
	public SQLModel getAssetsAssignModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String sqlStr = "UPDATE"
						+ " TD_ASSETS_TRANS_LINE AATL"
						+ " SET"
						+ " AATL.RESPONSIBILITY_USER = ?,"
						+ " AATL.ASSIGNED_TO_LOCATION = ?,"
						+ " AATL.RESPONSIBILITY_DEPT = ?,"
						+ " AATL.LINE_STATUS = ?,"
						+ " AATL.RECEIVED_USER = ?,"
						+ " AATL.DEPRECIATION_ACCOUNT = ?,"
						+ " AATL.FA_CATEGORY_CODE = ?,"
						+ " AATL.RECEIVED_DATE = GETDATE() ,"
						+ " AATL.ASSIGNED_DATE = GETDATE() "
						+ " WHERE"
						+ " AATL.TRANS_ID = ?"
						+ " AND AATL.BARCODE = ?";
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getAssignedToLocation());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(AssetsDictConstant.ORDER_STS_ASSIGNED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getDepreciationAccount());
		sqlArgs.add(dto.getFaCategoryCode());

		sqlArgs.add(dto.getTransId());
		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ������豸��ETS_ITEM_INFO��Ϣ
	 * @return SQLModel
	 */
	public SQLModel getAssetsPropUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.RESPONSIBILITY_USER = ?,"
						+ " EII.RESPONSIBILITY_DEPT = ?,"
						+ " EII.ORGANIZATION_ID = ?,"
						+ " EII.ADDRESS_ID = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE() ,"
						+ " EII.LAST_UPDATE_BY = ?"
						+ " WHERE"
						+ " EII.BARCODE = ?";
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(dto.getAddressId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ʲ�ȷ��SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsConfirmModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		String sqlStr = "UPDATE"
						+ " TD_ASSETS_TRANS_LINE"
						+ " SET"
						+ " CONFIRM_DATE = GETDATE() ,"
						+ " LINE_STATUS = ?"
						+ " WHERE"
						+ " TRANS_ID = ?"
						+ " AND BARCODE = ?";
		sqlArgs.add(AssetsDictConstant.ORDER_STS_CONFIRMD);
		sqlArgs.add(dto.getTransId());
		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
