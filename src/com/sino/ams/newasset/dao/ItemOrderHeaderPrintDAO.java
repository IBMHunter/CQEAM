package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.base.dto.DTO;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.model.ItemOrderHeaderPrintModel;
import com.sino.ams.system.user.dto.SfUserDTO;
public class ItemOrderHeaderPrintDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ItemOrderHeaderPrintDAO(SfUserDTO userAccount,
                               AmsAssetsTransHeaderDTO dtoParameter,
                               Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO)
                                          dtoParameter;
        super.sqlProducer = new ItemOrderHeaderPrintModel((SfUserDTO) userAccount,
                dtoPara);
    }
}
