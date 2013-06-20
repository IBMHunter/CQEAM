package com.sino.ams.newasset.model;


import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: AmsAssetsTransLineModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransLineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */


public class AssetsDiscardLineModel extends AMSSQLProducer {

    /**
     * ���ܣ�AMS_ASSETS_TRANS_LINE ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  BaseUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter DTO ���β���������
     */
    public AssetsDiscardLineModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ�����AMS_ASSETS_TRANS_LINE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException
     *
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
            String sqlStr = "INSERT INTO"
                    + " AMS_ASSETS_TRANS_LINE("
                    + " LINE_ID,"
                    + " TRANS_ID,"
                    + " BARCODE,"
                    + " OLD_LOCATION,"
                    + " OLD_RESPONSIBILITY_USER,"
                    + " OLD_RESPONSIBILITY_DEPT,"
                    + " OLD_DEPRECIATION_ACCOUNT,"
                    + " OLD_FA_CATEGORY_CODE,"
                    + " ASSIGNED_TO_LOCATION,"
                    + " RESPONSIBILITY_USER,"
                    + " RESPONSIBILITY_DEPT,"
                    + " DEPRECIATION_ACCOUNT,"
                    + " FA_CATEGORY_CODE,"
                    + " LINE_STATUS,"
                    + " LINE_TRANS_DATE,"
                    + " LINE_REASON, "
                    + " REMARK,"
                    + " NET_UNIT,"
                    + " ASSET_ID,"
                    + " SOFT_INUSE_VERSION,"
                    + " SOFT_DEVALUE_VERSION,"
                    + " DEPRECIATION,"
                    + " DEPRN_COST,"
                    + " IMPAIR_RESERVE,"
                    + " MANUFACTURER_NAME,"
                    + " PREPARE_DEVALUE,"
                    + " RETIREMENT_COST," +
                    " REJECT_TYPE"
                    + ") VALUES ("
                    + " NEWID() , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

            sqlArgs.add(dto.getTransId());
            sqlArgs.add(dto.getBarcode());
            sqlArgs.add(dto.getOldLocation());
            sqlArgs.add(dto.getOldResponsibilityUser());
            sqlArgs.add(dto.getOldResponsibilityDept());
            sqlArgs.add(dto.getOldDepreciationAccount());
            sqlArgs.add(dto.getOldFaCategoryCode());
            sqlArgs.add(dto.getAssignedToLocation());
            sqlArgs.add(dto.getResponsibilityUser());
            sqlArgs.add(dto.getResponsibilityDept());
            sqlArgs.add(dto.getDepreciationAccount());
            sqlArgs.add(dto.getFaCategoryCode());
            sqlArgs.add(dto.getLineStatus());
            sqlArgs.add(dto.getLineTransDate());
            sqlArgs.add(dto.getLineReason());
            sqlArgs.add(dto.getRemark());
            sqlArgs.add(dto.getNetUnit());
            sqlArgs.add(dto.getAssetId());
            sqlArgs.add(dto.getSoftInuseVersion());
            sqlArgs.add(dto.getSoftDevalueVersion());
            sqlArgs.add(dto.getDepreciation());
            sqlArgs.add(dto.getDeprnCost());
            sqlArgs.add(dto.getImpairReserve());
            sqlArgs.add(dto.getManufacturerName());
            sqlArgs.add(dto.getPrepareDevalue());
            sqlArgs.add(dto.getRetirementCost());
            sqlArgs.add(dto.getRejectType());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     *
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

    /**
     * ���ܣ�������������ֶ� transId �����ѯ����SQL��
     * ����Զ���������AMS_ASSETS_TRANS_LINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param transId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByTransIdModel(String transId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
        sqlStr = "SELECT"
                + " AATL.TRANS_ID,"
                + " AATL.LINE_ID,"
                + " AATL.BARCODE,"
                + " AATL.NEW_BARCODE,"
                + " AAAV.ITEM_NAME ASSETS_DESCRIPTION,"
                + " AAAV.ITEM_SPEC MODEL_NUMBER,"
                + " AATL.REMARK,"
                + " AATL.LINE_REASON,"
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
                + " AATL.FA_CATEGORY_CODE,"
                + " AATL.DEPRECIATION_ACCOUNT,"
                + " AATL.LINE_TRANS_DATE,"
                + " AATL.NET_UNIT,"
                + " AATL.ASSET_ID,"
                + " AATL.SOFT_INUSE_VERSION,"
                + " AATL.SOFT_DEVALUE_VERSION,"
                + " AATL.DEPRECIATION,"
                + " AATL.DEPRN_COST,"
                + " AATL.RETIREMENT_COST,"
                + " AATL.IMPAIR_RESERVE,"
                + " AATL.MANUFACTURER_NAME,"
                + " AATL.PREPARE_DEVALUE,"
                + " (SELECT"
                + " AOA.ADDRESS_ID"
                + " FROM"
                + " AMS_OBJECT_ADDRESS AOA"
                + " WHERE"
                + " AOA.OBJECT_NO = EON.WORKORDER_OBJECT_NO"
                + " AND AOA.BOX_NO = '0000'"
                + " AND AOA.NET_UNIT = '0000'"
                + " ) ADDRESS_ID," +
                "   AATL.REJECT_TYPE," +
                "   AMS_PUB_PKG.GET_FLEX_VALUE(AATL.REJECT_TYPE, 'DIS_TYPE') REJECT_TYPE_NAME  "
                + " FROM"
                + " AMS_ASSETS_TRANS_LINE AATL,"
                + " AMS_MIS_EMPLOYEE      AMEO,"
                + " AMS_MIS_DEPT          AMDO,"
                + " ETS_OBJECT            EOO,"
                + " AMS_MIS_EMPLOYEE      AMEN,"
                + " AMS_MIS_DEPT          AMDN,"
                + " ETS_OBJECT            EON,";
        if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
            sqlStr = sqlStr + " TD_ASSETS_ADDRESS_V  AAAV";
        } else {
            sqlStr = sqlStr + " AMS_ASSETS_ADDRESS_V  AAAV";
        }
        sqlStr = sqlStr
                + " WHERE"
                + " AATL.OLD_LOCATION *= EOO.WORKORDER_OBJECT_NO"
                + " AND AATL.OLD_RESPONSIBILITY_USER *= AMEO.EMPLOYEE_ID"
                + " AND AATL.OLD_RESPONSIBILITY_DEPT = AMDO.DEPT_CODE"
                + " AND AATL.ASSIGNED_TO_LOCATION *= EON.WORKORDER_OBJECT_NO"
                + " AND AATL.RESPONSIBILITY_USER *= AMEN.EMPLOYEE_ID"
                + " AND AATL.RESPONSIBILITY_DEPT *= AMDN.DEPT_CODE"
                + " AND AATL.BARCODE = AAAV.BARCODE"
                + " AND AATL.TRANS_ID = ?";
        sqlArgs.add(transId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
