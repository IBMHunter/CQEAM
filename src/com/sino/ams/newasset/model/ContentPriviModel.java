package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsAssetsPriviDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: AmsAssetsChkLogModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsChkLogModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */
public class ContentPriviModel extends AMSSQLProducer {

    /**
     * ���ܣ��ʲ��̵��¼ AMS_ASSETS_CHK_LOG ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsPriviDTO ���β���������
     */
    public ContentPriviModel(SfUserDTO userAccount,
                             AmsAssetsPriviDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ������ʲ��̵��¼ AMS_ASSETS_CHK_LOG���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsPriviDTO dto = (AmsAssetsPriviDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " AMS_ASSETS_PRIVI("
                        + " PRIVI_ID,"
                        + " USER_ID,"
                        + " ROLE_ID,"
                        + " COMPANY_CODE,"
                        + " FA_CATEGORY_CODE,"
                        + " CREATION_DATE,"
                        + " PROVINCE_CODE,"
                        + " CREATED_BY"
                        + ") VALUES ("
                        +
                        "  NEWID() , ?, ?, ?, ?, GETDATE(), ?,?)";
        sqlArgs.add(dto.getUserId());
        sqlArgs.add(dto.getRoleId());
        sqlArgs.add(userAccount.getCompanyCode());
        sqlArgs.add(dto.getFaCategoryCode());
        sqlArgs.add(servletConfig.getProvinceCode());
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ���������ɾ����SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsPriviDTO dto = (AmsAssetsPriviDTO) dtoParameter;
        String sqlStr = "DELETE FROM "
                        + " AMS_ASSETS_PRIVI AAP"
                        + " WHERE"
                        + " AAP.USER_ID = ?"
                        + " AND  " + SyBaseSQLUtil.isNotNull("AAP.FA_CATEGORY_CODE") + " "
                        + " AND AAP.ROLE_ID = ("
                        + " SELECT"
                        + " ROLE_ID"
                        + " FROM"
                        + " SF_ROLE SR"
                        + " WHERE"
                        + " SR.ROLE_NAME = ?)";
        sqlArgs.add(dto.getUserId());
        sqlArgs.add(servletConfig.getMtlAssetsMgr());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
