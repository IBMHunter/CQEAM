package com.sino.ams.task.dao.soa.td;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.task.model.soa.td.TDAssetsRetirementSynchronizeModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ErrorItem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ���������ݷ��ʲ����</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ�ı����ʲ�</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDAssetsRetirementSynchronizeDAO extends AMSBaseDAO {

    public TDAssetsRetirementSynchronizeDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        sqlProducer = new TDAssetsRetirementSynchronizeModel(userAccount, dtoParameter);
    }

    /**
     * <p>����˵���������û�����</p>
     * <p>Ӧ�ó�����ѭ��OU����ͬ��������OUʱ��Ҫ����ͬ����</p>
     *
     * @param userAccount ��̨����ִ����
     */
    public void setUserAccount(BaseUserDTO userAccount) {
        this.userAccount = (SfUserDTO) userAccount;
        TDAssetsRetirementSynchronizeModel modelProducer = (TDAssetsRetirementSynchronizeModel) sqlProducer;
        modelProducer.setUserAccount(userAccount);
    }


    /**
     * <p>����˵������ȡָ��OU�´�ͬ���ı����ʲ���</p>
     *
     * @param organizationId OU��֯ID
     * @return ָ��OU�´�ͬ�������ʲ���û�������򷵻ؿյ�RowSet����
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ���ݳ���ʱ�׳����쳣
     */
    public RowSet getOU2RetiredAssets(int organizationId) throws QueryException {
        TDAssetsRetirementSynchronizeModel modelProducer = (TDAssetsRetirementSynchronizeModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getRetiredAssetsModel(organizationId);
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.executeQuery();
        return splq.getSearchResult();
    }


    /**
     * д����ͬ����־
     *
     * @param systemId �ʲ�IDS
     * @param batchId  ͬ����
     * @throws com.sino.base.exception.DataHandleException
     *          д��־����ʱ�׳����쳣
     */
    public void logRetireAssets(String systemId[], String batchId) throws DataHandleException {
        TDAssetsRetirementSynchronizeModel modelProducer = (TDAssetsRetirementSynchronizeModel) sqlProducer;
        String systemIds = ArrUtil.arrToSqlStr(systemId);
        SQLModel sqlModel = modelProducer.getLogAssetsModel(systemIds, batchId);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ����ͬ����ϸ��־
     *
     * @param batchId ͬ����
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    public void updateResponseLog(String batchId) throws DataHandleException {
        TDAssetsRetirementSynchronizeModel modelProducer = (TDAssetsRetirementSynchronizeModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getUpdateLogCompleteModel(batchId);
        DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ����ͬ����ϸ��־
     *
     * @param errorItemList ����
     * @param batchId       ͬ����
     * @throws DataHandleException
     */
    public void updateErrorLog(List<ErrorItem> errorItemList, String batchId) throws DataHandleException {
        SQLModel sqlModel = null;
        List sqlModelList = new ArrayList();
        TDAssetsRetirementSynchronizeModel modelProducer = (TDAssetsRetirementSynchronizeModel) sqlProducer;
        for (int i = 0; i < errorItemList.size(); i++) {
            ErrorItem errorItem = errorItemList.get(i);
            sqlModel = modelProducer.getUpdateLogModel(batchId, errorItem.getRECORDNUMBER(), 2, errorItem.getERRORMESSAGE());
            sqlModelList.add(sqlModel);
        }
        if (sqlModelList.size() > 0) {
            DBOperator.updateBatchRecords(sqlModelList, conn);
        }
    }
}
