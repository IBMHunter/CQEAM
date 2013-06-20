package com.sino.ams.task.service;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.task.util.BackGroundTaskUtil;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.soa.util.SynLogUtil;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ��̨����ĳ���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class AbstractTaskService {

    protected SfUserDTO taskExecutor = null;

    /**
     * <p>����˵������ȡ��̨����ִ���ˡ��÷�����Ϊ�˼�¼��־����  </p>
     * <p>�Ծ��С�ϵͳ����Ա����ɫ�ĵ�һ���û�Ϊ׼</p>
     *
     * @param conn ���ݿ�����
     */
    protected void initTaskExecutor(Connection conn) {
        taskExecutor = BackGroundTaskUtil.getTaskExecutor(conn);
    }

    /**
     * <p>����˵������ȡ���ݿ�����
     *
     * @return ���ݿ�����
     * @throws com.sino.base.exception.PoolException
     *          ���ݿ����ӳ���ʱ�׳����ݿ����ӳ��쳣
     */
    protected Connection getDBConnection() throws PoolException {
        return DBManager.getDBConnection();
    }


    /**
     * <p>����˵���������ݿ����ӷ��ظ����ӳ�
     *
     * @param conn ���ݿ����Ӷ���
     */
    protected void closeDBConnection(Connection conn) {
        DBManager.closeDBConnection(conn);
    }

    /**
     * <p>����˵������ȡָ��ͬ��������һ��ͬ����ʱ��
     *
     * @param synType ͬ������
     * @param conn    ���ݿ�����
     * @return ָ��ͬ��������һ��ͬ����ʱ��
     * @throws QueryException ��ȡָ��ͬ��������һ��ͬ����ʱ�����ʱ�׳���ѯ�쳣
     */
    protected String getLastUpdateDate(String synType, Connection conn) throws QueryException {
        String lastUpdateDate = "";
        try {
            SynLogUtil synLogUtil = new SynLogUtil();
            lastUpdateDate = synLogUtil.getLastUpdateDate(synType, conn);
            if (lastUpdateDate.length() == 0) {
                lastUpdateDate = "2011-11-01 00:00:00";
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return lastUpdateDate;
    }

    /**
     * <p>����˵������ȡ���й�˾�ʲ��˲��б�(�������ʲ��˲�)
     *
     * @param conn ���ݿ�����
     * @return ���й�˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    protected RowSet getMISBookTypeCodes(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getMISBookTypeCodes(conn);
    }

    /**
     * <p>����˵������ȡTD��˾�ʲ��˲��б�(�������ʲ��˲�)
     *
     * @param conn ���ݿ�����
     * @return TD��˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    protected RowSet getTDBookTypeCodes(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getTDBookTypeCodes(conn);
    }


    /**
     * <p>����˵������ȡ���й�˾�б�
     *
     * @param conn ���ݿ�����
     * @return ���й�˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    protected RowSet getCompanyList(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getCompanyList(conn);
    }

    /**
     * <p>����˵������ȡTD��˾�б�
     *
     * @param conn ���ݿ�����
     * @return TD��˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    protected RowSet getTDCompanyList(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getTDCompanyList(conn);
    }

    /**
     * <p>����˵������ȡָ���ʲ��˲����µĹرյĻ���ڼ�
     *
     * @param bookTypeCode �ʲ��˲�����
     * @param conn         ���ݿ�����
     * @return ָ���ʲ��˲����µĹرյĻ���ڼ�
     * @throws QueryException ��ȡ���ݳ���ʱ�׳���ѯ�쳣
     */
    protected String getPeriodName(String bookTypeCode, Connection conn) throws QueryException {
        return BackGroundTaskUtil.getPeriodName(bookTypeCode, conn);
    }

    /**
     * <p>����˵������ȡ���¹رյĻ���ڼ�</p>
     *
     * @param conn         ���ݿ�����
     * @return ָ���ʲ��˲����µĹرյĻ���ڼ�
     * @throws QueryException ��ȡ���¹رյĻ���ڼ�
     */
    protected String getMaxPeriodName(Connection conn) throws QueryException {
        return BackGroundTaskUtil.getMaxPeriodName(conn);
    }

    /**
     * <p>����˵������ȡ��̨����ִ���ˣ�ÿ��OUָ��һ��
     *
     * @param conn           ���ݿ�����
     * @param organizationId OU��֯ID
     * @return ��̨����ִ����
     */
    protected SfUserDTO getOUTaskExecutor(Connection conn, int organizationId) {
        return BackGroundTaskUtil.getOUTaskExecutor(conn, organizationId);
    }
}
