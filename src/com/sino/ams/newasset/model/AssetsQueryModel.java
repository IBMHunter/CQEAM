package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * <p>Title: AmsFaAssetsModel</p>
 * <p>Description:�����Զ�����SQL��������AmsFaAssetsModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AssetsQueryModel extends AMSSQLProducer {

    /**
     * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsAddressVDTO ���β���������
     */
    public AssetsQueryModel(SfUserDTO userAccount,
                            AmsAssetsAddressVDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ������ȡ�����ʲ���SQL
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
            List sqlArgs = new ArrayList();
            String treeCategory = dto.getTreeCategory();
            String transType = AssetsDictConstant.ASS_DIS;
            if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CLEAR)) { //�����ʲ�
                transType = AssetsDictConstant.ASS_CLR;
            }
            String sqlStr = "SELECT"
                            + " AAAV.BARCODE,"
                            + " AAAV.ASSET_NUMBER,"
                            + " AAAV.ASSETS_DESCRIPTION,"
                            + " AAAV.MODEL_NUMBER,"
                            + " AAAV.COST,"
                            + " AAAV.DEPRN_COST,"
                            + " AAAV.DATE_PLACED_IN_SERVICE,"
                            + " AAAV.RESPONSIBILITY_USER_NAME,"
                            + " AAAV.MAINTAIN_USER_NAME,"
                            + " AAAV.RESPONSIBILITY_USER,"
                            + " AAAV.DEPT_NAME,"
                            + " AAAV.DEPT_CODE,"
                            + " AAAV.CURRENT_UNITS,"
                            + " AAAV.WORKORDER_OBJECT_NAME,"
                            + " AAAV.WORKORDER_OBJECT_NO,"
                            + " SU.USERNAME,"
                            + " AATH.TRANS_DATE"
                            + " FROM"
                            + " AMS_ASSETS_ADDRESS_V    AAAV,"
                            + " AMS_ASSETS_TRANS_LINE   AATL,"
                            + " AMS_ASSETS_TRANS_HEADER AATH,"
                            + " SF_USER                 SU,"
                            + " AMS_MIS_DEPT            AMD,"
                            + " ETS_OU_CITY_MAP         EOCM"
                            + " WHERE"
                            + " AAAV.BARCODE = AATL.BARCODE"
                            + " AND AATL.TRANS_ID = AATH.TRANS_ID"
                            + " AND AATH.FROM_DEPT = AMD.DEPT_CODE"
                            +
                    " AND AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                            + " AND AATH.CREATED_BY = SU.USER_ID"
                            + " AND AATH.TRANS_TYPE = ?"
                            + " AND AATH.TRANS_STATUS = ?"
                            + " AND AATH.CREATED_BY = ?"
                            +
                    " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
                            +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
                            + " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
                            + " AND AATH.TRANS_DATE >= dbo.NVL(?, AATH.TRANS_DATE)"
                            + " AND AATH.TRANS_DATE <= dbo.NVL(?, AATH.TRANS_DATE)";
            sqlArgs.add(transType);
            sqlArgs.add(AssetsDictConstant.APPROVED);
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getAssetsDescription());
            sqlArgs.add(dto.getModelNumber());
            sqlArgs.add(dto.getModelNumber());
            sqlArgs.add(dto.getBarcode());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }
}
