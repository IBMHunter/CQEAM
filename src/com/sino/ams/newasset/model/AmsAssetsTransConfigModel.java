package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsTransConfigDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: AmsAssetsTransConfigModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransConfigModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsAssetsTransConfigModel extends AMSSQLProducer {


    /**
     * ���ܣ��̶��ʲ������������� AMS_ASSETS_TRANS_CONFIG ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransConfigDTO ���β���������
     */
    public AmsAssetsTransConfigModel(SfUserDTO userAccount,
                                     AmsAssetsTransConfigDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���ȡ�ʲ�����������Ϣ��SQL
     * @return SQLModel
     */
    public SQLModel getTransConfigModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT"
                        + " AATC.*,"
                        + " EFV.VALUE FA_CONTENT_NAME"
                        + " FROM"
                        + " AMS_ASSETS_TRANS_CONFIG AATC,"
                        + " ETS_FLEX_VALUES         EFV,"
                        + " ETS_FLEX_VALUE_SET      EFVS"
                        + " WHERE"
                        + " AATC.FA_CONTENT_CODE = EFV.CODE"
                        + " AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
                        + " AND EFVS.CODE = ?"
                        + " AND AATC.ORGANIZATION_ID = ?"
                        + " AND AATC.ENABLED = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(AssetsDictConstant.FA_CONTENT_CODE);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(AssetsDictConstant.STATUS_YES);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
