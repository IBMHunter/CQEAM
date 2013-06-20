package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransConfigDTO;
import com.sino.ams.newasset.model.AmsAssetsTransConfigModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsAssetsTransConfigDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransConfigDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsAssetsTransConfigDAO extends AMSBaseDAO {

    /**
     * ���ܣ��̶��ʲ������������� AMS_ASSETS_TRANS_CONFIG ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransConfigDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsAssetsTransConfigDAO(SfUserDTO userAccount,
                                   AmsAssetsTransConfigDTO dtoParameter,
                                   Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransConfigDTO dtoPara = (AmsAssetsTransConfigDTO)
                                          dtoParameter;
        super.sqlProducer = new AmsAssetsTransConfigModel((SfUserDTO)
                userAccount, dtoPara);
    }

    /**
     * ���ܣ���ȡ�ʲ�����������Ϣ���û���¼ʱ����ȡ������session��
     * @return DTOSet
     * @throws QueryException
     */
    public DTOSet getTransConfigs() throws QueryException {
        AmsAssetsTransConfigModel modelProducer = (AmsAssetsTransConfigModel)
                                                  sqlProducer;
        SQLModel sqlModel = modelProducer.getTransConfigModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(AmsAssetsTransConfigDTO.class.getName());
        simp.executeQuery();
        return simp.getDTOSet();
    }
}
