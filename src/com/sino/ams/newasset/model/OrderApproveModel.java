package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: OrderApproveModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransHeaderModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class OrderApproveModel extends AMSSQLProducer {
    private String aggBarcodes = "";

    /**
	 * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
	 */
	public OrderApproveModel(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
        if (dtoParameter.getBarcodess()!=null) {
           initBarcodes(dtoParameter.getBarcodess());
        }
    }

    //��ʼ����ȡ��BARCODE
    private String initBarcodes (String[] barcodes) {
        aggBarcodes = "(";
        for (int i = 0; i < barcodes.length; i++) {
             String barcode = barcodes[i];
             aggBarcodes += "'" + barcode + "', ";
        }
        aggBarcodes += "'aa')";
//        int cc = aggBarcodes.lastIndexOf(",");
//        aggBarcodes = aggBarcodes.substring(0,cc)+")";
        return aggBarcodes;
    }

    /**
	 * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		String sqlStr = " SELECT"
						+ " AATH.TRANS_ID,"
						+ " AATH.TRANS_NO,"
						+ " AATH.FROM_DEPT,"
						+ " AATH.TO_DEPT,"
						+ " AATH.TRANS_TYPE,"
						+ " AATH.TRANSFER_TYPE,"
						+ " AATH.TRANS_STATUS,"
						+ " AATH.TRANS_DATE,"
						+ " AATH.CREATION_DATE,"
						+ " AATH.CREATED_BY,"
						+ " AATH.LAST_UPDATE_DATE,"
						+ " AATH.LAST_UPDATE_BY,"
						+ " AATH.CANCELED_DATE,"
						+ " AATH.CANCELED_REASON,"
						+ " AATH.CREATED_REASON,"
						+ " AATH.APPROVED_DATE,"
						+ " AATH.FROM_ORGANIZATION_ID,"
						+ " AATH.FROM_GROUP,"
						+ " AATH.IS_THRED,"
						+ " CASE AATH.EMERGENT_LEVEL WHEN '1' THEN '��' WHEN '2' THEN '�Ӽ�' WHEN '3' THEN '�ؼ�' ELSE '����' END EMERGENT_LEVEL,\n"
						+ " (SELECT"
						+ " GROUP_ID"
						+ " FROM"
						+ " SF_GROUP SGP"
						+ " WHERE"
						+ " CONVERT(VARCHAR,SGP.GROUP_ID) = CONVERT(VARCHAR,SG.GROUP_PID)) GROUP_PID,"
						+ " AMD.DEPT_NAME FROM_DEPT_NAME,"
						+ " AATH.FA_CONTENT_CODE,"
						+ " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
						+ " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
						+ " dbo.APP_GET_FLEX_VALUE(AATH.FA_CONTENT_CODE, 'FA_CONTENT_CODE') FA_CONTENT_NAME,"
						+ " SU.USERNAME CREATED,"
						+ " SU.EMAIL,"
						+ " SU.MOBILE_PHONE PHONE_NUMBER,"
						+ " SG.GROUP_NAME FROM_GROUP_NAME,"
						+ " 0 GROUP_PROP,"
						+ " EOCM.BOOK_TYPE_CODE,"
						+ " EOCM.BOOK_TYPE_NAME,"
						+ " EOCM.COMPANY FROM_COMPANY_NAME,"
						+ " AMD2.DEPT_NAME USER_DEPT_NAME,"
						+ " AATH.TO_ORGANIZATION_ID,"
						+" CASE WHEN AATH.TRANSFER_TYPE='BTW_COMP' THEN EOCM2.COMPANY ELSE EOCM.COMPANY END AS  TO_COMPANY_NAME ,"
						+ " (SELECT"
						+ " SGM.GROUP_ID"
						+ " FROM"
						+ " SF_GROUP_MATCH SGM"
						+ " WHERE"
						+ " CONVERT(VARCHAR,SGM.DEPT_CODE )= CONVERT(VARCHAR,AATH.TO_DEPT)) TO_GROUP,"
						+ "  CASE WHEN NEW_COUNT IS NULL THEN 'N' WHEN CONVERT(VARCHAR,NEW_COUNT)='0'THEN 'N' ELSE 'Y' END AS PRODUCED_NEW_BARCODE "
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " ETS_OU_CITY_MAP         EOCM,"
						+ " AMS_MIS_DEPT            AMD,"
						+ " SF_GROUP                SG,"
						+ " SF_USER                 SU,"
						+ " AMS_MIS_EMPLOYEE        AME,"
						+ " AMS_MIS_DEPT            AMD2,"
						+ " ETS_OU_CITY_MAP         EOCM2,"
						+ " (SELECT"
						+ " AATL.TRANS_ID,"
						+ " COUNT(1) NEW_COUNT"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_LINE AATL"
						+ " WHERE"
						+ "  " + SyBaseSQLUtil.isNotNull("AATL.NEW_BARCODE") + " "
						+ " GROUP BY"
						+ " AATL.TRANS_ID) TMP_V"
						+ " WHERE"
						+ " CONVERT(VARCHAR,AATH.FROM_ORGANIZATION_ID) *= CONVERT(VARCHAR,EOCM.ORGANIZATION_ID)"
						+ " AND CONVERT(VARCHAR,AATH.FROM_DEPT) *= CONVERT(VARCHAR,AMD.DEPT_CODE)"
						+ " AND CONVERT(VARCHAR,AATH.FROM_GROUP) *= CONVERT(VARCHAR,SG.GROUP_ID)"
						+ " AND CONVERT(VARCHAR,AATH.CREATED_BY) *= CONVERT(VARCHAR,SU.USER_ID)"
						+ " AND CONVERT(VARCHAR,SU.EMPLOYEE_NUMBER) *= CONVERT(VARCHAR,AME.EMPLOYEE_NUMBER) "
						+ " AND CONVERT(VARCHAR,AME.DEPT_CODE) *= CONVERT(VARCHAR,AMD2.DEPT_CODE)"
						+ " AND CONVERT(VARCHAR,AATH.TO_ORGANIZATION_ID) *= CONVERT(VARCHAR,EOCM2.ORGANIZATION_ID)"
						+ " AND CONVERT(VARCHAR,AATH.TRANS_ID) *= CONVERT(VARCHAR,TMP_V.TRANS_ID)"
						+ " AND AATH.TRANS_ID = ?";
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
//		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
//		AmsAssetsTransHeaderModel modelProducer = new AmsAssetsTransHeaderModel(userAccount, dto);
//		return modelProducer.getPrimaryKeyDataModel();
	}

	/**
	 * ���ܣ���ȡ�жϸõ����Ƿ��ܹ�����
	 * @return SQLModel
	 */
	public SQLModel getCanApproveModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " 1"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH"
						+ " WHERE"
						+ " AATH.TRANS_ID = ?"
						+ " AND (AATH.TRANS_STATUS = ? OR AATH.TRANS_STATUS = ?)";
		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getTransId());
		sqlArgs.add(AssetsDictConstant.IN_PROCESS);
		sqlArgs.add(AssetsDictConstant.REJECTED);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ��ı䵥��״̬
	 * @return SQLModel
	 */
	public SQLModel getOrderApproveModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		String transType = dto.getTransType();
		String sqlStr = "";
		List sqlArgs = new ArrayList();
		sqlStr = "UPDATE" //��������TRANS_DATE���ʲ�ȷ��ʱ����
				 + " AMS_ASSETS_TRANS_HEADER "
				 + " SET"
				 + " TRANS_STATUS = ?,"
				 + " APPROVED_DATE = GETDATE(),"
				 + " APPROVED_BY = ?,"
				 + " LAST_UPDATE_DATE = GETDATE(),"
				 + "LAST_UPDATE_BY = ?"
				 + " WHERE"
				 + " TRANS_ID = ?";
		if (!transType.equals(AssetsDictConstant.ASS_RED) && dto.isFlow2End()) { //���ϵ������õ���TRANS_DATE�ڵ����������ʱ����
			sqlStr = "UPDATE"
					 + " AMS_ASSETS_TRANS_HEADER AATH"
					 + " SET"
					 + " TRANS_STATUS = ?,"
					 + " APPROVED_DATE = GETDATE(),"
					 + " APPROVED_BY = ?,"
					 + " LAST_UPDATE_DATE = GETDATE(),"
					 + " TRANS_DATE = GETDATE(),"
					 + " LAST_UPDATE_BY = ?"
					 + " WHERE"
					 + " TRANS_ID = ?";
		}
		sqlArgs.add(dto.getTransStatus());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsDiscardModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO "
						+ " SET"
						+ " ITEM_STATUS      = ?,"
						+ " LAST_UPDATE_DATE = GETDATE(),"
						+ " LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = ETS_ITEM_INFO.BARCODE"
                        + " AND ((AATL.REMARK <> '��������') OR (AATL.REMARK " + SyBaseSQLUtil.isNullNoParam() + " ))"
                        + " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STAY_DISCARDED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsClearModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS     = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE(),"
						+ " EII.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_CLEARED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsFreeModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS     = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE(),"
						+ " EII.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsSellModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS     = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE(),"
						+ " EII.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_SELL);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsRentModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS     = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE(),"
						+ " EII.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_RENT);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ���ȡ�ʲ�����SQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsDonaModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS     = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE(),"
						+ " EII.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_DONATION);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	

	/**
	 * ���ܣ���ȡ�ʲ���ֵSQL
	 * @return SQLModel
	 */
	public SQLModel getAssetsSubModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " ETS_ITEM_INFO EII"
						+ " SET"
						+ " EII.ITEM_STATUS     = ?,"
						+ " EII.LAST_UPDATE_DATE = GETDATE(),"
						+ " EII.LAST_UPDATE_BY   = ?"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER AATH,"
						+ " AMS_ASSETS_TRANS_LINE   AATL"
						+ " WHERE"
						+ " AATH.TRANS_ID = AATL.TRANS_ID"
						+ " AND AATL.BARCODE = EII.BARCODE"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.TRANS_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_SUB);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getTransType());
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ������ʲ�����������״̬Ϊ������
	 * @return SQLModel
	 */
	public SQLModel getLineStatusUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " AMS_ASSETS_TRANS_LINE"
						+ " SET"
						+ " LINE_STATUS     = ?"
						+ " WHERE"
						+ " TRANS_ID = ?";
		sqlArgs.add(AssetsDictConstant.APPROVED);
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    /**
	 * ���ܣ���ȡ���������е�ǰ�ڵ��������ɫ����
	 * @return SQLModel
	 */
	public SQLModel getCurrApproveRoleModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT STD.ROLE_NAME FROM SF_TASK STD WHERE CONVERT(VARCHAR,STD.TASK_ID) = CONVERT(VARCHAR,?)";
		sqlArgs.add(dto.getCurrTaskId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    /**
	 * ���ܣ���ȡ��������
	 * @return SQLModel
	 */
	public SQLModel getAccessSheet() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT COUNT(*) ACCESS_SHEET FROM AMS_ASSETS_ATTACH AAA WHERE AAA.ORDER_PK_NAME = ?";
		sqlArgs.add(dto.getTransId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    /**
	 * ���ܣ����²��ܱ��ϵ��ʲ����������б�REMARKΪ���������ϡ���
	 * @return SQLModel
	 */
//	public SQLModel updateTransLineRemark(String aggBarcodes) {
//		SQLModel sqlModel = new SQLModel();
//		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
//		List sqlArgs = new ArrayList();
//        String sqlStr = "UPDATE AMS_ASSETS_TRANS_LINE AATL SET AATL.REMARK = '��������' WHERE AATL.TRANS_ID = ? AND AATL.BARCODE NOT IN ?";
//		sqlArgs.add(dto.getTransId());
//        sqlArgs.add(aggBarcodes);
//		sqlModel.setSqlStr(sqlStr);
//		sqlModel.setArgs(sqlArgs);
//		return sqlModel;
//	}
    public SQLModel updateTransLineRemark() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE " +
                "AMS_ASSETS_TRANS_LINE " +
                "SET " +
                "REMARK = '��������' " +
                "WHERE TRANS_ID = ? " +
                "AND BARCODE NOT IN "+aggBarcodes;
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    /**
	 * ���ܣ����⴦�������ѡ���ʲ�REMARK
	 * @return SQLModel
	 */
	public SQLModel deleteTransLineRemark(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE " +
                "AMS_ASSETS_TRANS_LINE " +
                "SET " +
                "REMARK = NULL " +
                " WHERE BARCODE = ?";
        sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}
}
