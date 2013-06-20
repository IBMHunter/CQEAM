package com.sino.ams.task.dao.soa.mis;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.task.model.soa.mis.LocationSegment2SearchModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;


/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ���������ݷ��ʲ����</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ������ص�</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class LocationSegment2SearchDAO extends AMSBaseDAO {

    public LocationSegment2SearchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        sqlProducer = new LocationSegment2SearchModel(userAccount, dtoParameter);
    }


    /**
     * <p>����˵������ȡָ��OU�´�ͬ��������ص㡣</p>
     * <p>����˵������ѯ��EAMϵͳ�������������ĵص㣺</p>
     * <li>EAMϵͳ����������ص㣻</>
     * <li>���������䶯������ص㣻</>
     * <li>��ǰͬ��ʧ�ܵ�����ص㣻</>
     *
     * @param organizationId OU��֯ID
     * @return ָ��OU�´�ͬ���ʲ��ص�������ϣ�û�������򷵻ؿյ�RowSet����
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ���ݳ���ʱ�׳����쳣
     */
    public RowSet getOU2SynchronizeLocationSegment2(int organizationId) throws QueryException {
        LocationSegment2SearchModel modelProducer = (LocationSegment2SearchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getChangedLocationSegment2Model(organizationId);
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.executeQuery();
        return splq.getSearchResult();
    }
}
