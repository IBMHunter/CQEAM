package com.sino.ams.task.model.internal;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ģ�͹������</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ���ʲ��ص��������</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AccountPeriodSearchModel extends BaseSQLProducer {

    public AccountPeriodSearchModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * <p>����˵���������ȡ������ڼ��ģ�Ͷ���</p>
     *
     * @return SQLModel ��ȡ������ڼ��ģ�Ͷ���
     */
    public SQLModel getMaxAccountPeriodModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT MAX(T.PERIOD_NAME) AS PERIOD_NAME\n" +
                "FROM   SRV_ASSET_PERIOD_STATUS T\n" +
                "WHERE  T.PERIOD_STATUS = 'CLOSE'";
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }
}
