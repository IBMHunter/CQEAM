package com.sino.ams.task.dao.soa.mis;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.task.model.soa.mis.AssetsChangeSearchModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;


/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ���������ݷ��ʲ����</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ���ʲ�</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsChangeSearchDAO extends AMSBaseDAO {

    public AssetsChangeSearchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        sqlProducer = new AssetsChangeSearchModel(userAccount, dtoParameter);
    }


    /**
     * <p>����˵������ȡָ��OU�´�ͬ�����ʲ���</p>
     * <p>Ӧ�ó������ʲ�������Ϣ�䶯ͬ��</p>
     * <p>����˵������MISϵͳ��ȣ���EAMϵͳ���������������仯֮һ���ʲ���Ҫͬ����MISϵͳ</p>
     * <li>���Ʒ��������</li>
     * <li>�ͺŷ��������</li>
     * <li>���̷��������</li>
     * <li>���������Ա�����������˱�����ص�����Ҫͨ���ʲ�����ͬ��ʵ��</li>
     * <p/>
     *
     * @param organizationId OU��֯ID
     * @return ָ��OU�´�ͬ���ʲ���û�������򷵻ؿյ�RowSet����
     * @throws QueryException ��ѯ���ݳ���ʱ�׳����쳣
     */
    public RowSet getOU2SynchronizeAssets(int organizationId) throws QueryException {
        AssetsChangeSearchModel modelProducer = (AssetsChangeSearchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getChangedAssetsModel(organizationId);
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.executeQuery();
        return splq.getSearchResult();
    }
}
