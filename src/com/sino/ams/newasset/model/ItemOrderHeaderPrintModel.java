package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

public class ItemOrderHeaderPrintModel extends AmsItemAllocationHeaderModel {

    /**
     * 功能：资产业务头表(EAM)--取代原表 AMS_ASSETS_TRANS_HEADER 数据库SQL构造层构造函数
     *
     * @param userAccount  SfUserDTO 代表本系统的最终操作用户对象
     * @param dtoParameter AmsAssetsTransHeaderDTO 本次操作的数据
     */
    public ItemOrderHeaderPrintModel(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * 功能：框架自动生成资产业务头表(EAM)--取代原表 AMS_ASSETS_TRANS_HEADER页面翻页查询SQLModel，请根据实际需要修改。
     *
     * @return SQLModel 返回页面翻页查询SQLModel
     * @throws SQLModelException 发生日历异常时转化为该异常抛出
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            String sqlStr = "SELECT"
                    + " AATH.TRANS_ID,"
                    + " AATH.TRANS_NO,"
                    + " AATH.TRANS_TYPE,"
                    + " AATH.TRANSFER_TYPE,"
                    + " AATH.TRANS_STATUS,"
                    + " AATH.FROM_ORGANIZATION_ID,"
                    + " EOCM.COMPANY,"
                    + " dbo.NVL(AMD.DEPT_NAME, EOCM.COMPANY) FROM_DEPT_NAME,"
                    + " AATH.RECEIVED_USER,"
                    + " AATH.CREATION_DATE,"
                    + " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
                    + " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
                    + " SU.USERNAME CREATED"
                    + " FROM"
                    + " AMS_ASSETS_TRANS_HEADER AATH,"
                    + " AMS_MIS_DEPT       AMD,"
                    + " ETS_OU_CITY_MAP    EOCM,"
                    + " SF_USER            SU"
                    + " WHERE"
                    + " AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                    + " AND AATH.FROM_DEPT *= AMD.DEPT_CODE"
                    + " AND AATH.CREATED_BY = SU.USER_ID"
                    + " AND AATH.TRANS_TYPE = 'ITEM-RED'"
                    + " AND (?='' OR AATH.CREATION_DATE >=?)"
                    + " AND  (?='' OR AATH.CREATION_DATE <= ?)"
                    + " AND (?='' OR AATH.TRANSFER_TYPE = dbo.NVL(?, AATH.TRANSFER_TYPE))"
                    + " AND AATH.TRANS_NO LIKE dbo.NVL(?, AATH.TRANS_NO)";


            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlArgs.add(dto.getTransferType());
            sqlArgs.add(dto.getTransferType());
            sqlArgs.add(dto.getTransNo());
            sqlStr += " AND (AATH.FROM_ORGANIZATION_ID = ?)";
            sqlArgs.add(userAccount.getOrganizationId());
            if (dto.getTransType().equals(AssetsDictConstant.ASS_RED)) {//调拨单据
                if (dto.getPrintType().compareTo("PRINT_TRANS_IN") == 0) {
                    //调入
                    /*    2009-09-21  libo 因需要严格控制查询权限而添加。*/
                    if (userAccount.isComAssetsManager()) {
                        sqlStr += " AND (AATH.TO_ORGANIZATION_ID = ?)";
                        sqlArgs.add(userAccount.getOrganizationId());
                    } else {
                        sqlStr = sqlStr
                                + " AND ? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
                                + " AND AATH.TO_ORGANIZATION_ID = ? AND AATH.TO_DEPT = ?";
                        sqlArgs.add(userAccount.getLoginName());
                        sqlArgs.add(userAccount.getOrganizationId());
                        sqlArgs.add(userAccount.getDeptCode());
                    }
                } else {
                    /*    2009-09-21  libo 因需要严格控制查询权限而添加。*/
                    if (userAccount.isComAssetsManager()) {
                        sqlStr += " AND (AATH.FROM_ORGANIZATION_ID = ?)";
                        sqlArgs.add(userAccount.getOrganizationId());
                    } else {
                        sqlStr = sqlStr
                                + " AND ? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
                                + " AND AATH.TO_ORGANIZATION_ID = ? AND AATH.TO_DEPT = ?";
                        sqlArgs.add(userAccount.getLoginName());
                        sqlArgs.add(userAccount.getOrganizationId());
                        sqlArgs.add(userAccount.getDeptCode());
                    }
                }
                if (dto.getPrintType().equals(AssetsWebAttributes.PRINT_TRANS_OUT)) { //调出方(含各审批人)打印
                    StringBuffer acceptStatus = new StringBuffer();
                    acceptStatus.append("'");
                    acceptStatus.append(AssetsDictConstant.COMPLETED);
                    acceptStatus.append("','");
                    acceptStatus.append(AssetsDictConstant.ORDER_STS_ASSIGNED);
                    acceptStatus.append("','");
                    acceptStatus.append(AssetsDictConstant.ORDER_STS_CONFIRMD);
                    acceptStatus.append("'");
                    sqlStr = sqlStr
                            + " AND AATH.TRANS_STATUS IN (" + acceptStatus + ")"
                            + " AND EXISTS ("
                            + " SELECT"
                            + " NULL"
                            + " FROM"
                            + " AMS_ASSETS_TRANS_LINE AATL"
                            + " WHERE"
                            + " AATH.TRANS_ID = AATL.TRANS_ID"
                            + " AND AATL.CONFIRM_DATE IS NOT NULL)";
                } else if (dto.getPrintType().equals(AssetsWebAttributes.PRINT_TRANS_IN)) {
                    sqlStr = sqlStr
                            + " AND EXISTS ("
                            + " SELECT"
                            + " NULL"
                            + " FROM"
                            + " AMS_ASSETS_TRANS_LINE AATL"
                            + " WHERE"
                            + " AATH.TRANS_ID = AATL.TRANS_ID"
                            + " AND AATL.CONFIRM_DATE IS NOT NULL)";
                }
            } else {//其他单据
                sqlStr += " AND AATH.TRANS_STATUS = ?";
                sqlArgs.add(AssetsDictConstant.CONFIRMD);
            }
            if (dto.getTransType().equals(AssetsDictConstant.ASS_RED)) {
                sqlStr = sqlStr
                        + " ORDER BY"
                        + " AATH.TRANSFER_TYPE,"
                        + " AATH.CREATION_DATE DESC";
            } else {
                sqlStr += " ORDER BY AATH.CREATION_DATE DESC";
            }
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
		}

		return sqlModel;
	}
}