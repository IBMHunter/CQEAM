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
 * <p>Description:程序自动生成SQL构造器“AmsItemTransLModel”，请根据需要自行修改</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsItemTransLModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * 功能：备件事务行表(AMS) AMS_ITEM_TRANS_L 数据库SQL构造层构造函数
     * @param userAccount  SfUserDTO 代表本系统的最终操作用户对象
     * @param dtoParameter AmsItemTransLDTO 本次操作的数据
     */
    public AmsItemTransLModel(SfUserDTO userAccount, AmsItemTransLDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * 功能：框架自动生成备件事务行表(AMS) AMS_ITEM_TRANS_L数据插入SQLModel，请根据实际需要修改。
     * @return SQLModel 返回数据插入用SQLModel
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
     * 功能：框架自动生成备件事务行表(AMS) AMS_ITEM_TRANS_L数据更新SQLModel，请根据实际需要修改。
     * @return SQLModel 返回数据更新用SQLModel
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
     * 功能：框架自动生成备件事务行表(AMS) AMS_ITEM_TRANS_L数据删除SQLModel，请根据实际需要修改。
     * @return SQLModel 返回数据删除用SQLModel
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
     * 功能：框架自动生成备件事务行表(AMS) AMS_ITEM_TRANS_L数据详细信息查询SQLModel，请根据实际需要修改。
     * @return SQLModel 返回数据详细信息查询用SQLModel
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
     * 功能：框架自动生成备件事务行表(AMS) AMS_ITEM_TRANS_L多条数据信息查询SQLModel，请根据实际需要修改。
     * @return SQLModel 返回多条数据信息查询用SQLModel
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
     * 功能：根据外键关联字段 transId 构造查询数据SQL。
     * 框架自动生成数据备件事务行表(AMS) AMS_ITEM_TRANS_L详细信息查询SQLModel，请根据实际需要修改。
     * @param transId String
     * @return SQLModel 返回数据详细信息查询用SQLModel
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
                "   AND ASI.ITEM_STATUS = '待修'\n" +
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
     * 功能：根据外键关联字段 barcodeNo 构造查询数据SQL。
     * 框架自动生成数据备件事务行表(AMS) AMS_ITEM_TRANS_L详细信息查询SQLModel，请根据实际需要修改。
     * @param barcodeNo String
     * @return SQLModel 返回数据详细信息查询用SQLModel
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
     * 功能：根据外键获取数据
     * @param foreignKey 传入的外键字段名称。
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
     * 功能：根据外键关联字段 transId 构造数据删除SQL。
     * 框架自动生成数据备件事务行表(AMS) AMS_ITEM_TRANS_L 数据删除SQLModel，请根据实际需要修改。
     * @param transId String
     * @return SQLModel 返回数据详细信息查询用SQLModel
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
     * 功能：根据外键关联字段 barcodeNo 构造数据删除SQL。
     * 框架自动生成数据备件事务行表(AMS) AMS_ITEM_TRANS_L 数据删除SQLModel，请根据实际需要修改。
     * @param barcodeNo String
     * @return SQLModel 返回数据详细信息查询用SQLModel
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
     * 功能：根据外键字段删除数据
     * @param foreignKey 传入的外键字段名称。
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
     * 功能：框架自动生成备件事务行表(AMS) AMS_ITEM_TRANS_L页面翻页查询SQLModel，请根据实际需要修改。
     * @return SQLModel 返回页面翻页查询SQLModel
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