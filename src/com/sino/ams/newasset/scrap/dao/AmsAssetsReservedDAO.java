package com.sino.ams.newasset.scrap.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.model.AmsAssetsReservedModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsReservedDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsReservedDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsReservedDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ��������� AMS_ASSETS_RESERVED ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsReservedDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsAssetsReservedDAO(SfUserDTO userAccount,
                                AmsAssetsReservedDTO dtoParameter,
                                Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsReservedDTO dtoPara = (AmsAssetsReservedDTO) dtoParameter;
        super.sqlProducer = new AmsAssetsReservedModel((SfUserDTO) userAccount,
                dtoPara);
    }
}
