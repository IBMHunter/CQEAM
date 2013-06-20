package com.sino.nm.spare2.model;


import com.sino.nm.spare2.dto.AmsItemTransLDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: AmsItemTransLModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemTransLModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsItemTransLModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ����������б�(AMS) AMS_ITEM_TRANS_L ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemTransLDTO ���β���������
     */
    public AmsItemTransLModel(SfUserDTO userAccount, AmsItemTransLDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ����ɱ��������б�(AMS) AMS_ITEM_TRANS_L���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_ITEM_TRANS_L("
                + " TRANS_ID,"
                + " LINE_ID,"
                + " BARCODE,"
                + " ITEM_CODE,"
                + " QUANTITY,"
                + " BATCH_NO,"
                + " STORAGE_ID,"
                + " NORMAL_QUANTITY,"
                + " BAD_QUANTITY"
                + ") VALUES ("
                + " ?, NEWID(), ?,?,?,?,?,?,?)";

        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getBarcode());
        sqlArgs.add(amsItemTransL.getItemCode());
        sqlArgs.add(amsItemTransL.getQuantity());
        sqlArgs.add(amsItemTransL.getBatchNo());
        sqlArgs.add(amsItemTransL.getStorageId());
        sqlArgs.add(amsItemTransL.getNormalQuantity());
        sqlArgs.add(amsItemTransL.getBadQuantity());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ��������б�(AMS) AMS_ITEM_TRANS_L���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_ITEM_TRANS_L"
                + " SET"
                + " TRANS_ID = ?,"
                + " BARCODE = ?"
                + " WHERE"
                + " LINE_ID = ?";

        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getBarcode());
        sqlArgs.add(amsItemTransL.getLineId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ��������б�(AMS) AMS_ITEM_TRANS_L����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + " LINE_ID = ?";
        sqlArgs.add(amsItemTransL.getLineId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ��������б�(AMS) AMS_ITEM_TRANS_L������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " TRANS_ID,"
                + " LINE_ID,"
                + " BARCODE"
                + " FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + " LINE_ID = ?";
        sqlArgs.add(amsItemTransL.getLineId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ��������б�(AMS) AMS_ITEM_TRANS_L����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " TRANS_ID,"
                + " LINE_ID,"
                + " BARCODE"
                + " FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + "("+ SyBaseSQLUtil.nullStringParam() +" OR TRANS_ID LIKE ?)"
                + "("+ SyBaseSQLUtil.nullStringParam() +"  OR LINE_ID LIKE ?)"
                + "("+ SyBaseSQLUtil.nullStringParam() +"  OR BARCODE LIKE ?)";
        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getLineId());
        sqlArgs.add(amsItemTransL.getLineId());
        sqlArgs.add(amsItemTransL.getLineId());
        sqlArgs.add(amsItemTransL.getBarcode());
        sqlArgs.add(amsItemTransL.getBarcode());
        sqlArgs.add(amsItemTransL.getBarcode());


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� transId �����ѯ����SQL��
     * ����Զ��������ݱ��������б�(AMS) AMS_ITEM_TRANS_L��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param transId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataByTransIdModel(String transId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AITL.LINE_ID,\n" +
                "       AITL.BARCODE,\n" +
                "       ESI.ITEM_CODE,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       ESI.ITEM_UNIT,\n" +
                "       AITL.QUANTITY,\n" +
                "       AITL.STORAGE_ID,\n" +
                "       AITL.BATCH_NO,\n" +
                "       dbo.AMS_ITEM_TRANS2_GET_IF_DEALED_WITH(AITL.LINE_ID,ESI.ITEM_CODE) DEALED_WITH\n" +
                "  FROM AMS_ITEM_TRANS_L AITL, ETS_SYSTEM_ITEM ESI\n" +
                " WHERE AITL.ITEM_CODE = ESI.ITEM_CODE\n" +
                "   AND TRANS_ID = ?";
//		sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(transId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getDataByTransIdModel1(String transId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AITL.LINE_ID,\n" +
                "       AITL.QUANTITY,\n" +
                "       ASI.BARCODE,\n" +
                "       ESI.ITEM_CODE,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       ASI.OBJECT_NO,\n" +
                "       ASI.QUANTITY ONHAND_QTY\n" +
                "  FROM AMS_SPARE_INFO     ASI,\n" +
                "       AMS_SPARE_CATEGORY AMSC,\n" +
                "       ETS_SYSTEM_ITEM    ESI,\n" +
                "       AMS_ITEM_TRANS_L   AITL\n" +
                " WHERE ASI.BARCODE = AMSC.BARCODE\n" +
                "   AND AMSC.ITEM_CODE = ESI.ITEM_CODE\n" +
                "   AND ASI.BARCODE = AITL.BARCODE\n" +
                "   AND ASI.ITEM_STATUS = '����'\n" +
                "   AND AITL.TRANS_ID = ?";
        sqlArgs.add(transId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    public SQLModel getDeptDataByTransIdModel(String transId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AITL.LINE_ID,\n" +
                "       AITL.BARCODE,\n" +
                "       ESI.ITEM_CODE,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       ESI.ITEM_UNIT,\n" +
                "       AITL.QUANTITY,\n" +
                "       AITL.STORAGE_ID,\n" +
                "       AITL.BATCH_NO,\n" +
                "       dbo.AMS_ITEM_TRANS2_GET_IF_DEALED_WITH(AITL.LINE_ID, ESI.ITEM_CODE) DEALED_WITH,\n" +
                "       EII.RESPONSIBILITY_USER RESP_USER,\n" +
                "       EII.RESPONSIBILITY_DEPT RESP_DEPT,\n" +
                "       EII.ADDRESS_ID,\n" +
                "       dbo.AMP_GET_EMPLOYEE_NAME(EII.RESPONSIBILITY_USER) USER_NAME,\n" +
                "       dbo.APP_GET_DEPT_NAME(EII.RESPONSIBILITY_DEPT) DEPT_NAME,\n" +
                "       EO.WORKORDER_OBJECT_NAME\n" +
                "  FROM AMS_ITEM_TRANS_L   AITL,\n" +
                "       ETS_SYSTEM_ITEM    ESI,\n" +
                "       ETS_ITEM_INFO      EII,\n" +
                "       AMS_OBJECT_ADDRESS AOA,\n" +
                "       ETS_OBJECT         EO\n" +
                " WHERE AITL.ITEM_CODE = ESI.ITEM_CODE\n" +
                "   AND EII.BARCODE = AITL.BARCODE\n" +
                "   AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND AOA.ADDRESS_ID = EII.ADDRESS_ID\n" +
                "   AND AITL.TRANS_ID = ?";
        sqlArgs.add(transId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    /**
     * ���ܣ�������������ֶ� barcodeNo �����ѯ����SQL��
     * ����Զ��������ݱ��������б�(AMS) AMS_ITEM_TRANS_L��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param barcodeNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeNoModel(String barcodeNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " TRANS_ID,"
                + " LINE_ID"
                + " FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcodeNo);

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
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        if (foreignKey.equals("transId")) {
            sqlModel = getDataByTransIdModel(amsItemTransL.getTransId());
        } else if (foreignKey.equals("barcodeNo")) {
            sqlModel = getDataByBarcodeNoModel(amsItemTransL.getBarcode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� transId ��������ɾ��SQL��
     * ����Զ��������ݱ��������б�(AMS) AMS_ITEM_TRANS_L ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param transId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getDeleteByTransIdModel(String transId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + " TRANS_ID = ?";
        sqlArgs.add(transId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcodeNo ��������ɾ��SQL��
     * ����Զ��������ݱ��������б�(AMS) AMS_ITEM_TRANS_L ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param barcodeNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByBarcodeNoModel(String barcodeNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcodeNo);

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
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        if (foreignKey.equals("transId")) {
            sqlModel = getDeleteByTransIdModel(amsItemTransL.getTransId());
        } else if (foreignKey.equals("barcodeNo")) {
            sqlModel = getDeleteByBarcodeNoModel(amsItemTransL.getBarcode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ��������б�(AMS) AMS_ITEM_TRANS_Lҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransLDTO amsItemTransL = (AmsItemTransLDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " TRANS_ID,"
                + " LINE_ID,"
                + " BARCODE"
                + " FROM"
                + " AMS_ITEM_TRANS_L"
                + " WHERE"
                + " (? IS NULL OR TRANS_ID LIKE ?)"
                + "AND (? IS NULL OR LINE_ID LIKE ?)"
                + "AND (? IS NULL OR BARCODE LIKE ?)";
        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getTransId());
        sqlArgs.add(amsItemTransL.getLineId());
        sqlArgs.add(amsItemTransL.getLineId());
        sqlArgs.add(amsItemTransL.getBarcode());
        sqlArgs.add(amsItemTransL.getBarcode());

        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}