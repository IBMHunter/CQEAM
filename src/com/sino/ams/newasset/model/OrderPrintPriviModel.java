package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: AmsAssetsTransHeaderModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransHeaderModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderPrintPriviModel extends AMSSQLProducer {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     */
    public OrderPrintPriviModel(SfUserDTO userAccount,
                                AmsAssetsTransHeaderDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���������
     * @return SQLModel
     */
    public SQLModel getHasPrintPriviModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE"
                        + " AMS_ASSETS_TRANS_HEADER AATH"
                        + " SET"
                        + " AATH.TRANS_STATUS = ?,"
                        + " AATH.LAST_UPDATE_DATE = GETDATE(),"
                        + " AATH.LAST_UPDATE_BY = ?"
                        + " WHERE"
                        + " AATH.TRANS_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(AssetsDictConstant.CANCELED);
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
