package com.sino.ams.freeflow;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: OrderApproveModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransHeaderModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author
 * jiaozhiwei
 */

public class OrderApproveModel extends AMSSQLProducer {
      private String aggBarcodes = "";
    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     */
    public OrderApproveModel(SfUserDTO userAccount, FreeFlowDTO dtoParameter) {
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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
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
                        + " (SELECT"
                        + " GROUP_ID"
                        + " FROM"
                        + " SF_GROUP SGP"
                        + " WHERE"
                        + " SGP.GROUP_ID = SG.GROUP_PID) GROUP_PID,"
                        + " AMD.DEPT_NAME FROM_DEPT_NAME,"
                        + " AATH.FA_CONTENT_CODE,"
                        + " AMS_PUB_PKG.GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
                        + " AMS_PUB_PKG.GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
                        + " AMS_PUB_PKG.GET_FLEX_VALUE(AATH.FA_CONTENT_CODE, 'FA_CONTENT_CODE') FA_CONTENT_NAME,"
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
                        + " CASE WHEN AATH.TRANSFER_TYPE='BTW_COMP' THEN EOCM2.COMPANY ELSE EOCM.COMPANY END TO_COMPANY_NAME,"
                        + " (SELECT"
                        + " SGM.GROUP_ID"
                        + " FROM"
                        + " SF_GROUP_MATCH SGM"
                        + " WHERE"
                        + " SGM.DEPT_CODE = AATH.TO_DEPT) TO_GROUP,"
                        + " CASE WHEN NEW_COUNT IS NULL OR NEW_COUNT='' OR NEW_COUNT='0' THEN 'N' ELSE 'Y' END PRODUCED_NEW_BARCODE "
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
                        + " AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                        + " AND AATH.FROM_DEPT *= AMD.DEPT_CODE"
                        + " AND AATH.FROM_GROUP = SG.GROUP_ID"
                        + " AND AATH.CREATED_BY = SU.USER_ID"
                        + " AND SU.EMPLOYEE_NUMBER *= AME.EMPLOYEE_NUMBER"
                        + " AND AME.DEPT_CODE *= AMD2.DEPT_CODE"
                        + " AND AATH.TO_ORGANIZATION_ID *= EOCM2.ORGANIZATION_ID"
                        + " AND AATH.TRANS_ID *= TMP_V.TRANS_ID"
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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
        String transType = dto.getTransType();
        String sqlStr = "";
        List sqlArgs = new ArrayList();
        sqlStr = "UPDATE" //��������TRANS_DATE���ʲ�ȷ��ʱ����
                 + " AMS_ASSETS_TRANS_HEADER AATH"
                 + " SET"
                 + " AATH.TRANS_STATUS = ?,"
                 + " AATH.APPROVED_DATE = GETDATE(),"
                 + " AATH.APPROVED_BY = ?,"
                 + " AATH.LAST_UPDATE_DATE = GETDATE(),"
                 + " AATH.LAST_UPDATE_BY = ?"
                 + " WHERE"
                 + " AATH.TRANS_ID = ?";
        if (!transType.equals(AssetsDictConstant.ASS_RED) && dto.isFlow2End()) { //���ϵ������õ���TRANS_DATE�ڵ����������ʱ����
            sqlStr = "UPDATE"
                     + " AMS_ASSETS_TRANS_HEADER AATH"
                     + " SET"
                     + " AATH.TRANS_STATUS = ?,"
                     + " AATH.APPROVED_DATE = GETDATE(),"
                     + " AATH.APPROVED_BY = ?,"
                     + " AATH.LAST_UPDATE_DATE = GETDATE(),"
                     + " AATH.TRANS_DATE = GETDATE(),"
                     + " AATH.LAST_UPDATE_BY = ?"
                     + " WHERE"
                     + " AATH.TRANS_ID = ?";
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
       * ���ܣ���ȡ���������е�ǰ�ڵ��������ɫ����
       * @return SQLModel
       */
      public SQLModel getCurrApproveRoleModel() {
          SQLModel sqlModel = new SQLModel();
          FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
          List sqlArgs = new ArrayList();
          String sqlStr = "SELECT"
                          + " SR.ROLE_NAME"
                          + " FROM"
                          + " SF_TASK_DEFINE STD,"
                          + " SF_ROLE        SR"
                          + " WHERE"
                          + " STD.ROLE_ID = SR.ROLE_ID"
                          + " AND STD.TASK_ID = ?";
          sqlArgs.add(dto.getCurrTaskId());

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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE"
                        + " ETS_ITEM_INFO EII"
                        + " SET"
                        + " EII.ITEM_STATUS      = ?,"
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
        sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_DISCARDED);
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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
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
    public SQLModel getAssetsShareModel() {
        SQLModel sqlModel = new SQLModel();
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE"
                        + " ETS_ITEM_INFO EII"
                        + " SET"
//                        + " EII.ITEM_STATUS     = ?,"
                        + "	EII.IS_SHARE = 'Y',"
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
//        sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_SHARE);
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
        FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
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
		FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE"
						+ " AMS_ASSETS_TRANS_LINE AATL"
						+ " SET"
						+ " AATL.LINE_STATUS     = ?"
						+ " WHERE"
						+ " AATL.TRANS_ID = ?";
		sqlArgs.add(AssetsDictConstant.APPROVED);

        sqlArgs.add(dto.getTransId());
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
		FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT COUNT(*) ACCESS_SHEET FROM AMS_ASSETS_ATTACH AAA WHERE AAA.ORDER_PK_NAME = ?";
		sqlArgs.add(dto.getTransId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    public SQLModel updateTransLineRemark() {
           SQLModel sqlModel = new SQLModel();
           FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
           List sqlArgs = new ArrayList();
           String sqlStr = "UPDATE " +
                   "AMS_ASSETS_TRANS_LINE " +
                   "SET " +
                   "REMARK = '��������' " +
                   "WHERE " +
                   "TRANS_ID = ? " +
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
		FreeFlowDTO dto = (FreeFlowDTO) dtoParameter;
		List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE " +
                "AMS_ASSETS_TRANS_LINE" +
                " SET REMARK = NULL  " +
                "WHERE " +
                "BARCODE = ?";
        sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

}
