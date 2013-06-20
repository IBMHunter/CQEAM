package com.sino.ams.system.assetcatalog.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.assetcatalog.dto.AssetCatalogDTO;
import com.sino.ams.system.assetcatalog.model.AssetCatalogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

public class AssetCatalogDAO extends AMSBaseDAO {
    private SfUserDTO sfUser = null;
      public AssetCatalogDAO(SfUserDTO userAccount, AssetCatalogDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    	AssetCatalogDTO dtoPara = (AssetCatalogDTO) dtoParameter;
        super.sqlProducer = new AssetCatalogModel((SfUserDTO) userAccount, dtoPara);
    }
}
