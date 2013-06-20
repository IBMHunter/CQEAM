package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckBatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: CheckApproveModel</p>
 * <p>Description:�����Զ�����SQL��������CheckApproveModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class CheckApproveModel extends AmsAssetsCheckBatchModel {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_CHECK_BATCH ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCheckBatchDTO ���β���������
     */
    public CheckApproveModel(SfUserDTO userAccount,
                             AmsAssetsCheckBatchDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���ȡ�жϸõ����Ƿ��ܹ�����
     * @return SQLModel
     */
    public SQLModel getCanApproveModel() {
        SQLModel sqlModel = new SQLModel();
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
        String sqlStr = "SELECT"
                + " 1"
                + " FROM"
                + " AMS_ASSETS_CHECK_BATCH AACB"
                + " WHERE"
                + " AACB.BATCH_ID = ?"
                + " AND (AACB.BATCH_STATUS = ? OR AACB.BATCH_STATUS = ?)"
                + " AND (AACB.APPROVED_B " + SyBaseSQLUtil.isNullNoParam() + "  OR AACB.APPROVED_BY <> ?)";
        List sqlArgs = new ArrayList();
        sqlArgs.add(dto.getBatchId());
        sqlArgs.add(AssetsDictConstant.IN_PROCESS);
        sqlArgs.add(AssetsDictConstant.REJECTED);
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����������
     * @return SQLModel
     */
    public SQLModel getBatchApproveModel() {
        SQLModel sqlModel = new SQLModel();
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE"
                + " AMS_ASSETS_CHECK_BATCH"
                + " SET"
                + " BATCH_STATUS = ?,"
                + " APPROVED_DATE = GETDATE(),"
                + " APPROVED_BY = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " BATCH_ID = ?";
        sqlArgs.add(dto.getBatchStatus());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getBatchId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���������
     * @return SQLModel
     */
    public SQLModel getHeadersApproveModel() {
        SQLModel sqlModel = new SQLModel();
        AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        if (dto.getBatchStatus().equals("DISTRIBUTED")) {
            sqlStr = "UPDATE"
                    + " AMS_ASSETS_CHECK_HEADER"
                    + " SET"
                    + " ORDER_STATUS = ?,"
                    + " DISTRIBUTE_DATE=GETDATE() ,"
                    + " DISTRIBUTE_BY=?,"
                    + " APPROVED_DATE = GETDATE(),"
                    + " APPROVED_BY = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " LAST_UPDATE_BY = ?"
                    + " WHERE"
                    + " BATCH_ID = ?";
            sqlArgs.add(dto.getBatchStatus());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(userAccount.getUserId());
        } else {
            sqlStr = "UPDATE"
                    + " AMS_ASSETS_CHECK_HEADER "
                    + " SET"
                    + " ORDER_STATUS = ?,"
                    + " APPROVED_DATE = GETDATE(),"
                    + " APPROVED_BY = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " LAST_UPDATE_BY = ?"
                    + " WHERE"
                    + " BATCH_ID = ?";
            sqlArgs.add(dto.getBatchStatus());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(userAccount.getUserId());
        }

        sqlArgs.add(dto.getBatchId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
