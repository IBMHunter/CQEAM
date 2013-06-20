package com.sino.ams.task.dao.soa;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.task.model.soa.LocationCombinationSearchModel;
import com.sino.ams.task.model.soa.TDLocationCombinationSearchModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;


/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ���������ݷ��ʲ����</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ���ʲ��ص��������</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDLocationCombinationSearchDAO extends AMSBaseDAO {

    public TDLocationCombinationSearchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        sqlProducer = new TDLocationCombinationSearchModel(userAccount, dtoParameter);
    }


    /**
     * <p>����˵������ȡָ��OU�´�ͬ�����ʲ��ص�������ϡ�</p>
     * <p>����˵������ѯ��EAMϵͳ�������������ĵص㣺</p>
     * <li>EAMϵͳ��������ϵص㣻</>
     * <li>���������䶯����ϵص㣻</>
     * <li>��ǰͬ��ʧ�ܵĵص㣻</>
     *
     * @param organizationId OU��֯ID
     * @return ָ��OU�´�ͬ���ʲ��ص�������ϣ�û�������򷵻ؿյ�RowSet����
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ���ݳ���ʱ�׳����쳣
     */
    public RowSet getTDOU2SynchronizeLocations(int organizationId) throws QueryException {
        TDLocationCombinationSearchModel modelProducer = (TDLocationCombinationSearchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getTDChangedLocationCombinationModel(organizationId);
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.executeQuery();
        return splq.getSearchResult();
    }
}
