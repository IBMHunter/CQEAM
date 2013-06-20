package com.sino.ams.task.service.soa;


import com.sino.ams.task.service.AbstractTaskService;
import com.sino.ams.task.util.BackGroundTaskUtil;
import com.sino.base.data.RowSet;
import com.sino.base.exception.QueryException;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ODI��̨����ĳ���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class AbstractTaskSOAService extends AbstractTaskService {

    public AbstractTaskSOAService() {
        super();
    }


    /**
     * <p>����˵������ȡMISֵ�������б� </p>
     *
     * @param conn ���ݿ�����
     * @return MISֵ�������б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    protected RowSet getMISFlexValueSets(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getMISFlexValueSets(conn);
    }

    /**
     * <p>����˵������ȡTDֵ�������б�
     *
     * @param conn ���ݿ�����
     * @return TDֵ�������б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    protected RowSet getTDFlexValueSets(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getTDFlexValueSets(conn);
    }
}
