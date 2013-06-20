package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsFaChgHistoryDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: AmsFaChgHistoryModel</p>
 * <p>Description:�����Զ�����SQL��������AmsFaChgHistoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsFaChgHistoryModel extends AMSSQLProducer {

    /**
     * ���ܣ��̶��ʲ������(EAM) AMS_FA_CHG_HISTORY ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsFaChgHistoryDTO ���β���������
     */
    public AmsFaChgHistoryModel(SfUserDTO userAccount,
                                AmsFaChgHistoryDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ����ɹ̶��ʲ������(EAM) AMS_FA_CHG_HISTORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsFaChgHistoryDTO dto = (AmsFaChgHistoryDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " AMS_FA_CHG_HISTORY("
                        + " CHG_LOG_ID,"
                        + " BARCODE,"
                        + " FROM_ORGANIZATION_ID,"
                        + " TO_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " TO_DEPT,"
                        + " TRANS_ID,"
                        + " FROM_OBJECT_NO,"
                        + " TO_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " TO_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATED_BY"
                        + ") VALUES ("
                        +
                "  NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(dto.getFromOrganizationId());
        sqlArgs.add(dto.getToOrganizationId());
        sqlArgs.add(dto.getFromDept());
        sqlArgs.add(dto.getToDept());
        sqlArgs.add(dto.getTransId());
        sqlArgs.add(dto.getFromObjectNo());
        sqlArgs.add(dto.getToObjectNo());
        sqlArgs.add(dto.getFromPerson());
        sqlArgs.add(dto.getToPerson());
        sqlArgs.add(dto.getFromStatus());
        sqlArgs.add(dto.getToStatus());
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ����ɹ̶��ʲ������(EAM) AMS_FA_CHG_HISTORY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsFaChgHistoryDTO dto = (AmsFaChgHistoryDTO) dtoParameter;
        String sqlStr = "SELECT "
                        + " CHG_LOG_ID,"
                        + " BARCODE,"
                        + " FROM_ORGANIZATION_ID,"
                        + " TO_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " TO_DEPT,"
                        + " TRANS_ID,"
                        + " FROM_OBJECT_NO,"
                        + " TO_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " TO_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATION_DATE,"
                        + " CREATED_BY"
                        + " FROM"
                        + " AMS_FA_CHG_HISTORY"
                        + " WHERE"
                        + " CHG_LOG_ID = ?";
        sqlArgs.add(dto.getChgLogId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�������������ֶ� barcode �����ѯ����SQL��
     * ����Զ��������ݹ̶��ʲ������(EAM) AMS_FA_CHG_HISTORY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                        + " BARCODE,"
                        + " CHG_LOG_ID,"
                        + " FROM_ORGANIZATION_ID,"
                        + " TO_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " TO_DEPT,"
                        + " TRANS_ID,"
                        + " FROM_OBJECT_NO,"
                        + " TO_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " TO_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATION_DATE,"
                        + " CREATED_BY"
                        + " FROM"
                        + " AMS_FA_CHG_HISTORY"
                        + " WHERE"
                        + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� transId �����ѯ����SQL��
     * ����Զ��������ݹ̶��ʲ������(EAM) AMS_FA_CHG_HISTORY��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param transId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByTransIdModel(String transId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                        + " CHG_LOG_ID,"
                        + " BARCODE,"
                        + " FROM_ORGANIZATION_ID,"
                        + " TO_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " TO_DEPT,"
                        + " FROM_OBJECT_NO,"
                        + " TO_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " TO_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATION_DATE,"
                        + " CREATED_BY"
                        + " FROM"
                        + " AMS_FA_CHG_HISTORY"
                        + " WHERE"
                        + " TRANS_ID = ?";
        sqlArgs.add(transId);
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
        AmsFaChgHistoryDTO dto = (AmsFaChgHistoryDTO) dtoParameter;
        if (foreignKey.equals("barcode")) {
            sqlModel = getDataByBarcodeModel(dto.getBarcode());
        } else if (foreignKey.equals("transId")) {
            sqlModel = getDataByTransIdModel(dto.getTransId());
        }
        return sqlModel;
    }


    /**
     * ���ܣ������ʲ�������ʷ��¼
     * @return SQLModel
     */
    public SQLModel getAssetsTransferLogModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsFaChgHistoryDTO dto = (AmsFaChgHistoryDTO) dtoParameter;
        String sqlStr = "INSERT INTO"
                        + " AMS_FA_CHG_HISTORY("
                        + " CHG_LOG_ID,"
                        + " BARCODE,"
                        + " BARCODE,"
                        + " TRANS_ID,"
                        + " FROM_ORGANIZATION_ID,"
                        + " TO_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " TO_DEPT,"
                        + " FROM_OBJECT_NO,"
                        + " TO_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " TO_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATED_BY"
                        + " ) ("
                        + " SELECT"
                        + "  NEWID() ,"
                        + " EFA.BARCODE,"
                        + " EFA.BARCODE,"
                        + " AATH.TRANS_ID,"
                        + " AATH.ORGANIZATION_ID,"
                        + " EOCM.ORGANIZATION_ID,"
                        + " AATH.FROM_DEPT,"
                        + " AATH.TO_DEPT,"
                        + " EO.WORKORDER_OBJECT_NO,"
                        + " AATL.ASSIGNED_TO_LOCATION,"
                        + " EFA.ASSIGNED_TO_NUMBER,"
                        + " AATL.RESPONSIBILITY_USER,"
                        + " ?,"
                        + " ?,"
                        + " ?"
                        + " FROM"
                        + " ETS_FA_ASSETS       EFA,"
                        + " AMS_ASSETS_TRANS_HEADER  AATH,"
                        + " ETS_OBJECT          EO,"
                        + " AMS_MIS_DEPT        AMD,"
                        + " ETS_OU_CITY_MAP     EOCM,"
                        + " AMS_ASSETS_TRANS_LINE  AATL"
                        + " WHERE"
                        + " EFA.BARCODE = AATL.BARCODE"
                        + " AND AATL.TRANS_ID = AATH.TRANS_ID"
                        +
                " AND EFA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION"
                        + " AND EO.ORGANIZATION_ID = AATH.ORGANIZATION_ID"
                        + " AND AATH.TO_DEPT = AMD.DEPT_CODE"
                        + " AND AMD.COMPANY_CODE = EOCM.COMPANY_CODE"
                        + " AND AATH.TRANS_ID = ?"
                        + " AND AATL.BARCODE = ?)";
        sqlArgs.add(AssetsDictConstant.ASSETS_STS_NORMAL);
        sqlArgs.add(AssetsDictConstant.ASSETS_STS_NORMAL);
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getTransId());
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ʲ�������ʷ��¼
     * @return SQLModel
     */
    public SQLModel getAssetsDiscardLogModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsFaChgHistoryDTO dto = (AmsFaChgHistoryDTO) dtoParameter;
        String sqlStr = "INSERT INTO"
                        + " AMS_FA_CHG_HISTORY("
                        + " CHG_LOG_ID,"
                        + " BARCODE,"
                        + " BARCODE,"
                        + " TRANS_ID,"
                        + " FROM_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " FROM_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATED_BY"
                        + " ) ("
                        + " SELECT"
                        + "  NEWID() ,"
                        + " EFA.BARCODE,"
                        + " EFA.BARCODE,"
                        + " AATH.TRANS_ID,"
                        + " AATH.ORGANIZATION_ID,"
                        + " AATH.FROM_DEPT,"
                        + " EO.WORKORDER_OBJECT_NO,"
                        + " EFA.ASSIGNED_TO_NUMBER,"
                        + " ?,"
                        + " ?,"
                        + " ?"
                        + " FROM"
                        + " ETS_FA_ASSETS       EFA,"
                        + " AMS_ASSETS_TRANS_HEADER  AATH,"
                        + " ETS_OBJECT          EO,"
                        + " AMS_ASSETS_TRANS_LINE  AATL"
                        + " WHERE"
                        + " EFA.BARCODE = AATL.BARCODE"
                        + " AND AATL.TRANS_ID = AATH.TRANS_ID"
                        +
                " AND EFA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION"
                        + " AND EO.ORGANIZATION_ID = AATH.ORGANIZATION_ID"
                        + " AND AATH.TRANS_ID = ?"
                        + " AND AATL.BARCODE = ?)";
        sqlArgs.add(AssetsDictConstant.ASSETS_STS_NORMAL);
        sqlArgs.add(AssetsDictConstant.ASSETS_STS_DISCARD);
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getTransId());
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ʲ�������ʷ��¼
     * @return SQLModel
     */
    public SQLModel getAssetsClearLogModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsFaChgHistoryDTO dto = (AmsFaChgHistoryDTO) dtoParameter;
        String sqlStr = "INSERT INTO"
                        + " AMS_FA_CHG_HISTORY("
                        + " CHG_LOG_ID,"
                        + " BARCODE,"
                        + " BARCODE,"
                        + " TRANS_ID,"
                        + " FROM_ORGANIZATION_ID,"
                        + " FROM_DEPT,"
                        + " FROM_OBJECT_NO,"
                        + " FROM_PERSON,"
                        + " FROM_STATUS,"
                        + " TO_STATUS,"
                        + " CREATED_BY"
                        + " ) ("
                        + " SELECT"
                        + "  NEWID(),"
                        + " EFA.BARCODE,"
                        + " EFA.BARCODE,"
                        + " AATH.TRANS_ID,"
                        + " AATH.ORGANIZATION_ID,"
                        + " AATH.FROM_DEPT,"
                        + " EO.WORKORDER_OBJECT_NO,"
                        + " EFA.ASSIGNED_TO_NUMBER,"
                        + " ?,"
                        + " ?,"
                        + " ?"
                        + " FROM"
                        + " ETS_FA_ASSETS       EFA,"
                        + " AMS_ASSETS_TRANS_HEADER  AATH,"
                        + " ETS_OBJECT          EO,"
                        + " AMS_ASSETS_TRANS_LINE  AATL"
                        + " WHERE"
                        + " EFA.BARCODE = AATL.BARCODE"
                        + " AND AATL.TRANS_ID = AATH.TRANS_ID"
                        +
                " AND EFA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION"
                        + " AND EO.ORGANIZATION_ID = AATH.ORGANIZATION_ID"
                        + " AND AATH.TRANS_ID = ?"
                        + " AND AATL.BARCODE = ?)";
        sqlArgs.add(AssetsDictConstant.ASSETS_STS_NORMAL);
        sqlArgs.add(AssetsDictConstant.ASSETS_STS_CLEAR);
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getTransId());
        sqlArgs.add(dto.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
