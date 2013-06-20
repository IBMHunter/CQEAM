package com.sino.ams.task.dao.internal;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.task.model.internal.AccountPeriodSearchModel;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ���������ݷ��ʲ����</p>
 * <p>����: ��ѯ�ѹرյ�������ڼ�</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AccountPeriodSearchDAO extends AMSBaseDAO {

    public AccountPeriodSearchDAO(BaseUserDTO userAccount, Connection conn) {
        super(userAccount, null, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        sqlProducer = new AccountPeriodSearchModel(userAccount, dtoParameter);
    }

    /**
     * <p>����˵������ȡ�����ѹرյĻ���ڼ䡣</p>
     * <p>Ӧ�ó�������̨�������ɱ���ʱ��Ҫ��ȡ����ڼ�</p>
     *
     * @return ���������ѹرյĻ���ڼ䣬δ��ѯ��ʱ�򷵻ؿ��ַ���
     * @throws QueryException ��ѯ����ڼ����ʱ�׳����쳣
     */
    public String getMaxAccountPeriod() throws QueryException {
        String maxAccountPeriod = "";
        try {
            AccountPeriodSearchModel modelProducer = (AccountPeriodSearchModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getMaxAccountPeriodModel();
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                maxAccountPeriod = row.getStrValue("PERIOD_NAME");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return maxAccountPeriod;
    }
}
