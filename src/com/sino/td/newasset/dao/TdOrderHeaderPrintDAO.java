package com.sino.td.newasset.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.td.newasset.dto.TdAssetsTransHeaderDTO;
import com.sino.td.newasset.model.TdOrderHeaderPrintModel;

/**
 * <p>Title: AmsAssetsTransHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class TdOrderHeaderPrintDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter TdAssetsTransHeaderDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public TdOrderHeaderPrintDAO(SfUserDTO userAccount,
                               TdAssetsTransHeaderDTO dtoParameter,
                               Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        TdAssetsTransHeaderDTO dtoPara = (TdAssetsTransHeaderDTO)
                                          dtoParameter;
        super.sqlProducer = new TdOrderHeaderPrintModel((SfUserDTO) userAccount,
                dtoPara);
    }
}
