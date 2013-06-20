package com.sino.ams.task.dao.soa.mis;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.task.model.soa.mis.AssetsRetirementSynchronizeModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ErrorItem;

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
public class AssetsRetirementSynchronizeDAO extends AMSBaseDAO {

    public AssetsRetirementSynchronizeDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        sqlProducer = new AssetsRetirementSynchronizeModel(userAccount, dtoParameter);
    }

    /**
     * <p>����˵���������û�����</p>
     * <p>Ӧ�ó�����ѭ��OU����ͬ��������OUʱ��Ҫ����ͬ����</p>
     *
     * @param userAccount ��̨����ִ����
     */
    public void setUserAccount(BaseUserDTO userAccount) {
        this.userAccount = (SfUserDTO) userAccount;
        AssetsRetirementSynchronizeModel modelProducer = (AssetsRetirementSynchronizeModel) sqlProducer;
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
        AssetsRetirementSynchronizeModel modelProducer = (AssetsRetirementSynchronizeModel) sqlProducer;
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
     * @throws DataHandleException д��־����ʱ�׳����쳣
     */
    public void logRetireAssets(String systemId[], String batchId) throws DataHandleException {
        AssetsRetirementSynchronizeModel modelProducer = (AssetsRetirementSynchronizeModel) sqlProducer;
        String systemIds = ArrUtil.arrToSqlStr(systemId);
        SQLModel sqlModel = modelProducer.getLogAssetsModel(systemIds, batchId);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ����ͬ����ϸ��־
     *
     * @param batchId ͬ����
     * @throws DataHandleException
     */
    public void updateResponseLog(String batchId) throws DataHandleException {
        AssetsRetirementSynchronizeModel modelProducer = (AssetsRetirementSynchronizeModel) sqlProducer;
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
        List sqlModelList = new ArrayList();
        AssetsRetirementSynchronizeModel modelProducer = (AssetsRetirementSynchronizeModel) sqlProducer;
        for (int i = 0; i < errorItemList.size(); i++) {
            ErrorItem errorItem = errorItemList.get(i);
            SQLModel sqlModel = modelProducer.getUpdateLogModel(batchId, errorItem.getRECORDNUMBER(), 2, errorItem.getERRORMESSAGE());
            sqlModelList.add(sqlModel);
        }
        if (sqlModelList.size() > 0) {
            DBOperator.updateBatchRecords(sqlModelList, conn);
        }
    }

}
