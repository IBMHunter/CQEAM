package com.sino.ams.task.util;


import java.sql.Connection;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.task.model.TaskModelProducer;
import com.sino.soa.common.MIS_CONSTANT;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������</p>
 * <p>����: ��̨���񹤾���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class BackGroundTaskUtil {


    /**
     * <p>����˵������ȡ���й�˾�б� </p>
     *
     * @param conn ���ݿ�����
     * @return ���й�˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    public static RowSet getCompanyList(Connection conn) throws QueryException {
        SQLModel sqlModel = TaskModelProducer.getCompanyListModel("N");
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * <p>����˵������ȡTD��˾�б�
     *
     * @param conn ���ݿ�����
     * @return TD��˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    public static RowSet getTDCompanyList(Connection conn) throws QueryException {
        SQLModel sqlModel = TaskModelProducer.getCompanyListModel("Y");
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }


    /**
     * <p>����˵������ȡ���й�˾�ʲ��˲��б�(�������ʲ��˲�)
     *
     * @param conn ���ݿ�����
     * @return ���й�˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    public static RowSet getMISBookTypeCodes(Connection conn) throws QueryException {
        SQLModel sqlModel = TaskModelProducer.getBookTypeCodeModel("N");
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * <p>����˵������ȡTD��˾�ʲ��˲��б�(�������ʲ��˲�)
     *
     * @param conn ���ݿ�����
     * @return TD��˾�ʲ��˲��б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    public static RowSet getTDBookTypeCodes(Connection conn) throws QueryException {
        SQLModel sqlModel = TaskModelProducer.getBookTypeCodeModel("Y");
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * <p>����˵������ȡMISֵ�������б�
     *
     * @param conn ���ݿ�����
     * @return MISֵ�������б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    public static RowSet getMISFlexValueSets(Connection conn) throws QueryException {
        SQLModel sqlModel = TaskModelProducer.getFlexValueSetModel("MIS");
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * <p>����˵������ȡTDֵ�������б�
     *
     * @param conn ���ݿ�����
     * @return TDֵ�������б�
     * @throws QueryException ��ѯ���ݿ����ʱ�׳����쳣
     */
    public static RowSet getTDFlexValueSets(Connection conn) throws QueryException {
        SQLModel sqlModel = TaskModelProducer.getFlexValueSetModel(MIS_CONSTANT.SOURCE_TD);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * <p>����˵������ȡ��̨����ִ����
     * <p>�Ծ��С�ϵͳ����Ա����ɫ�ĵ�һ���û�Ϊ׼</p>
     *
     * @param conn ���ݿ�����
     * @return ��̨����ִ����
     */
    public static SfUserDTO getTaskExecutor(Connection conn) {
        SfUserDTO executor = null;
        try {
            SQLModel sqlModel = TaskModelProducer.getTaskExecutorModel();
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.setDTOClassName(SfUserDTO.class.getName());
            sq.executeQuery();
            if (sq.hasResult()) {
                executor = (SfUserDTO) sq.getFirstDTO();
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return executor;
    }

    /**
     * <p>����˵������ȡ��̨����ִ���ˣ�ÿ��OUָ��һ��
     *
     * @param conn           ���ݿ�����
     * @param organizationId OU��֯ID
     * @return ��̨����ִ����
     */
    public static SfUserDTO getOUTaskExecutor(Connection conn, int organizationId) {
        SfUserDTO executor = null;
        try {
            SQLModel sqlModel = TaskModelProducer.getOUTaskExecutorModel(organizationId);
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.setDTOClassName(SfUserDTO.class.getName());
            sq.executeQuery();
            if (sq.hasResult()) {
                executor = (SfUserDTO) sq.getFirstDTO();
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return executor;
    }


    /**
     * <p>����˵������ȡָ���ʲ��˲����µĹرյĻ���ڼ�
     *
     * @param bookTypeCode �ʲ��˲�����
     * @param conn         ���ݿ�����
     * @return ָ���ʲ��˲����µĹرյĻ���ڼ�
     * @throws QueryException ��ȡ���ݳ���ʱ�׳���ѯ�쳣
     */
    public static String getPeriodName(String bookTypeCode, Connection conn) throws QueryException {
        String periodName = "";
        try {
            SQLModel sqlModel = TaskModelProducer.getPeriodNameModel(bookTypeCode);
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                row.getStrValue("MIS_PERIOD_NAME");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return periodName;
    }

    /**
     * <p>����˵������ȡ���¹رյĻ���ڼ�
     *
     * @param conn         ���ݿ�����
     * @return ��ȡ���¹رյĻ���ڼ�
     * @throws QueryException ��ȡ���ݳ���ʱ�׳���ѯ�쳣
     */
    public static String getMaxPeriodName(Connection conn) throws QueryException {
        String periodName = "";
        try {
            SQLModel sqlModel = TaskModelProducer.getMaxPeriodNameModel();
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                periodName=row.getStrValue("MIS_PERIOD_NAME");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return periodName;
    }
}
