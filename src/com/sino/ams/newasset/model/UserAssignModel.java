package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class UserAssignModel extends AMSSQLProducer {

    public UserAssignModel(SfUserDTO userAccount,
                           AmsAssetsAddressVDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���ȡ��Ա�����б���SQL
     * @return SQLModel
     */
    public SQLModel getUserOptionsModel(boolean selectUserId) {
        SQLModel sqlModel = new SQLModel();
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        if (selectUserId) {
            sqlStr = "SELECT"
                     + " SU.USER_ID,"
                     + " SU.USERNAME"
                     + " FROM"
                     + " SF_USER SU"
                     + " WHERE"
                     + " SU.USERNAME LIKE dbo.NVL(?, SU.USERNAME)"
                     + " AND SU.ORGANIZATION_ID = ?";
        } else {
            sqlStr = "SELECT"
                     + " SU.USERNAME,"
                     + " SU.USERNAME"
                     + " FROM"
                     + " SF_USER SU"
                     + " WHERE"
                     + " SU.USERNAME LIKE dbo.NVL(?, SU.USERNAME)"
                     + " AND SU.ORGANIZATION_ID = ?";
        }
        sqlArgs.add(dto.getMaintainUserName());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
