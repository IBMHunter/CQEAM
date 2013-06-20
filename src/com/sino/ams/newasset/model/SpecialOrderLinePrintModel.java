package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: OrderLinePrintModel</p>
 * <p>Description:�����Զ�����SQL��������OrderLinePrintModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class SpecialOrderLinePrintModel extends AMSSQLProducer {
	private String printType = "";

	/**
	 * ���ܣ�AMS_ASSETS_TRANS_LINE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransLineDTO ���β���������
	 */
	public SpecialOrderLinePrintModel(SfUserDTO userAccount, AmsAssetsTransLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ����ô�ӡ���ͣ�������ӡ���ǵ����ӡ(�����ڵ�����)
	 * @param printType String
	 */
	public void setPrintType(String printType) {
		this.printType = printType;
	}

	/**
	 * ���ܣ�������������ֶ� transId �����ѯ����SQL��
	 * ����Զ���������AMS_ASSETS_TRANS_LINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param transId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByTransIdModel(String transId) {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "";
		String transType = dto.getTransType();
		if (transType.equals(AssetsDictConstant.ASS_SUB)) { //�ʲ���ֵ����
			sqlStr = "SELECT"
					 + " AATL.TRANS_ID,"
					 + " AATL.LINE_ID,"
					 + " AATL.BARCODE,"
					 + " AATL.REMARK,"
					 + " AAAV.ITEM_NAME,"
					 + " AAAV.ITEM_SPEC,"
					 + " AAAV.ASSET_NUMBER,"
					 + " AAAV.ASSETS_DESCRIPTION,"
					 + " AAAV.MODEL_NUMBER,"
					 + " ISNULL(AAAV.CURRENT_UNITS, 1) CURRENT_UNITS,"
					 + " AAAV.VENDOR_NAME,"
					 + " AAAV.UNIT_OF_MEASURE,"
					 + " AAAV.COST,"
					 + " AAAV.DEPRN_COST,"
					 + " AAAV.DEPRECIATION,"
					 + " AAAV.DATE_PLACED_IN_SERVICE,"
					 + " AAAV.LIFE_IN_YEARS,"
					 + " AATL.FA_CATEGORY_CODE,"
					 + " AATL.DEPRECIATION_ACCOUNT,"
					 + " AATL.NET_UNIT,"
					 + " AATL.ASSET_ID,"
					 + " AATL.SOFT_INUSE_VERSION,"
					 + " AATL.SOFT_DEVALUE_VERSION,"
					 + " AATL.DEPRECIATION,"
					 + " AATL.DEPRN_COST,"
					 + " AATL.PREPARE_DEVALUE"
					 + " FROM"
					 + " AMS_ASSETS_TRANS_LINE AATL,"
					 + " AMS_ASSETS_ADDRESS_V  AAAV"
					 + " WHERE"
					 + " AATL.BARCODE = AAAV.BARCODE"
					 + " AND AATL.TRANS_ID = ?";
            sqlArgs.add(transId);
        } else { //�������ݣ��ݲ�����
			sqlStr = "SELECT"
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
					 + " AAAV.LIFE_IN_YEARS,"
					 + " EOO.WORKORDER_OBJECT_NO OLD_LOCATION,"
					 + " EOO.WORKORDER_OBJECT_NAME OLD_LOCATION_NAME,"
					 + " AMEO.EMPLOYEE_ID OLD_RESPONSIBILITY_USER,"
					 + " AMEO.USER_NAME OLD_RESPONSIBILITY_USER_NAME,"
					 + " AMDO.DEPT_CODE OLD_RESPONSIBILITY_DEPT,"
					 + " AMDO.DEPT_NAME OLD_RESPONSIBILITY_DEPT_NAME,"
					 + " EON.WORKORDER_OBJECT_NO ASSIGNED_TO_LOCATION,"
					 + " EON.WORKORDER_OBJECT_NAME ASSIGNED_TO_LOCATION_NAME,"
					 + " AMEN.EMPLOYEE_ID RESPONSIBILITY_USER,"
					 + " AMEN.USER_NAME RESPONSIBILITY_USER_NAME,"
					 + " AMDN.DEPT_CODE RESPONSIBILITY_DEPT,"
					 + " AMDN.DEPT_NAME RESPONSIBILITY_DEPT_NAME,"
					 + " AMS_ASSETS_PKG.GET_TO_ORGNIZATION_ID(AATL.LINE_ID) TO_ORGANIZATION_ID,"
					 + " AATL.OLD_FA_CATEGORY_CODE,"
					 + " AATL.OLD_DEPRECIATION_ACCOUNT,"
					 + " dbo.NVL(AATL.FA_CATEGORY_CODE, AAAV.FA_CATEGORY_CODE) FA_CATEGORY_CODE,"
					 + " AATL.DEPRECIATION_ACCOUNT,"
					 + " AATL.LINE_TRANS_DATE,"
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
					 + " AMS_ASSETS_TRANS_LINE AATL,"
					 + " AMS_MIS_EMPLOYEE      AMEO,"
					 + " AMS_MIS_DEPT          AMDO,"
					 + " ETS_OBJECT            EOO,"
					 + " AMS_MIS_EMPLOYEE      AMEN,"
					 + " AMS_MIS_DEPT          AMDN,"
					 + " ETS_OBJECT            EON,"
					 + " AMS_ASSETS_ADDRESS_V  AAAV"
					 + " WHERE"
					 + " AATL.OLD_LOCATION = EOO.WORKORDER_OBJECT_NO"
					 + " AND AATL.OLD_RESPONSIBILITY_USER *= AMEO.EMPLOYEE_ID"
					 + " AND AATL.OLD_RESPONSIBILITY_DEPT *= AMDO.DEPT_CODE"
					 + " AND AATL.ASSIGNED_TO_LOCATION *= EON.WORKORDER_OBJECT_NO"
					 + " AND AATL.RESPONSIBILITY_USER *= AMEN.EMPLOYEE_ID"
					 + " AND AATL.RESPONSIBILITY_DEPT *= AMDN.DEPT_CODE"
					 + " AND AATL.BARCODE = AAAV.BARCODE"
					 + " AND AATL.TRANS_ID = ?";
            sqlArgs.add(transId);
            if (transType.equals(AssetsDictConstant.ASS_RED)) {
				if (printType.equals(AssetsWebAttributes.PRINT_TRANS_IN)) {
					sqlStr = sqlStr
							 + " AND  " + SyBaseSQLUtil.isNotNull("AATL.CONFIRM_DATE") + " "   ;
							// + " AND AATL.RESPONSIBILITY_USER = ?";
					//sqlArgs.add(userAccount.getEmployeeId());
				} else if (printType.equals(AssetsWebAttributes.PRINT_TRANS_OUT)) {
					//Ȩ�޿��Ʒſ�
					/*sqlStr = sqlStr
							 + " AND EXISTS("
							 + " SELECT"
							 + " NULL"
							 + " FROM"
							 + " SF_ACT_LOG              SAL,"
							 + " AMS_ASSETS_TRANS_HEADER AATH"
							 + " WHERE"
							 + " AATH.TRANS_ID = SAL.APP_ID"
							 + " AND AATH.TRANS_NO = SAL.APPLY_NUMBER"
							 + " AND AATH.TRANS_ID = AATL.TRANS_ID"
							 +
							 " AND (SAL.CUR_USERID = ? OR SAL.COMPLETE_USER = ?))";
								   sqlArgs.add(userAccount.getUserId());
								   sqlArgs.add(userAccount.getUserId());*/
				}
			}
		}

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
		AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
		if (foreignKey.equals("transId")) {
			sqlModel = getDataByTransIdModel(dto.getTransId());
		}
		return sqlModel;
	}
}
