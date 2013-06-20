package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsFaChgHistoryDTO;
import com.sino.ams.newasset.model.AmsFaChgHistoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsFaChgHistoryDAO</p>
 * <p>Description:�����Զ����ɷ������AmsFaChgHistoryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AssetsChgHistoryDAO extends AMSBaseDAO {

    /**
     * ���ܣ��̶��ʲ������(EAM) AMS_FA_CHG_HISTORY ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsFaChgHistoryDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AssetsChgHistoryDAO(SfUserDTO userAccount,
                               AmsFaChgHistoryDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsFaChgHistoryDTO dtoPara = (AmsFaChgHistoryDTO) dtoParameter;
        super.sqlProducer = new AmsFaChgHistoryModel((SfUserDTO) userAccount,
                dtoPara);
    }

    /**
     * ���ܣ������ʲ�������־��¼
     * @throws DataHandleException
     */
    public void createAssetsTransferLog() throws DataHandleException {
        AmsFaChgHistoryModel modelProducer = (AmsFaChgHistoryModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getAssetsTransferLogModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ������ʲ�������־��¼
     * @throws DataHandleException
     */
    public void createAssetsDiscardLog() throws DataHandleException {
        AmsFaChgHistoryModel modelProducer = (AmsFaChgHistoryModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getAssetsDiscardLogModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ������ʲ�������־��¼
     * @throws DataHandleException
     */
    public void createAssetsClearLog() throws DataHandleException {
        AmsFaChgHistoryModel modelProducer = (AmsFaChgHistoryModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getAssetsClearLogModel();
        DBOperator.updateRecord(sqlModel, conn);
    }
}
