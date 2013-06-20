package com.sino.ams.workorder.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsSuggestionDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsSuggestionModel</p>
 * <p>Description:�����Զ�����SQL��������EtsSuggestionModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class EtsSuggestionModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ����������������(EAM) ETS_SUGGESTION ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsSuggestionDTO ���β���������
     */
    public EtsSuggestionModel(SfUserDTO userAccount, EtsSuggestionDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ����ɹ��������������(EAM) ETS_SUGGESTION���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsSuggestionDTO etsSuggestion = (EtsSuggestionDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " ETS_SUGGESTION("
                + " SYSTEMID,"
                + " WORKORDER_BATCH,"
                + " TITLE,"
                + " REMARK,"
                + " GROUP_ID,"
                + " HANDLER,"
                + " RECORD_DATE,"
                + " COMPLETE_FLAG,"
                + " ACTID"
                + ") VALUES ("
                + "  NEWID() , ?, ?, ?, ?, ?, GETDATE(), ?, ?)";

        sqlArgs.add(etsSuggestion.getWorkorderBatch());
        sqlArgs.add(etsSuggestion.getTitle());
        sqlArgs.add(etsSuggestion.getRemark());
        sqlArgs.add(etsSuggestion.getGroupId());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsSuggestion.getCompleteFlag());
        sqlArgs.add(etsSuggestion.getActId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ��������������(EAM) ETS_SUGGESTION���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            EtsSuggestionDTO etsSuggestion = (EtsSuggestionDTO) dtoParameter;
            String sqlStr = "UPDATE ETS_SUGGESTION"
                    + " SET"
                    + " WORKORDER_BATCH = ?,"
                    + " TITLE = ?,"
                    + " REMARK = ?,"
                    + " GROUP_ID = ?,"
                    + " HANDLER = ?,"
                    + " RECORD_DATE = ?,"
                    + " COMPLETE_FLAG = ?"
                    + " WHERE"
                    + " SYSTEMID = ?";

            sqlArgs.add(etsSuggestion.getWorkorderBatch());
            sqlArgs.add(etsSuggestion.getTitle());
            sqlArgs.add(etsSuggestion.getRemark());
            sqlArgs.add(etsSuggestion.getGroupId());
            sqlArgs.add(etsSuggestion.getHandler());
            sqlArgs.add(etsSuggestion.getRecordDate());
            sqlArgs.add(etsSuggestion.getCompleteFlag());
            sqlArgs.add(etsSuggestion.getSystemid());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ��������������(EAM) ETS_SUGGESTION����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsSuggestionDTO etsSuggestion = (EtsSuggestionDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " ETS_SUGGESTION"
                + " WHERE"
                + " SYSTEMID = ?";
        sqlArgs.add(etsSuggestion.getSystemid());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ��������������(EAM) ETS_SUGGESTION������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsSuggestionDTO etsSuggestion = (EtsSuggestionDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " WORKORDER_BATCH,"
                + " TITLE,"
                + " REMARK,"
                + " GROUP_ID,"
                + " HANDLER,"
                + " RECORD_DATE,"
                + " COMPLETE_FLAG"
                + " FROM"
                + " ETS_SUGGESTION"
                + " WHERE"
                + " SYSTEMID = ?";
        sqlArgs.add(etsSuggestion.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ��������������(EAM) ETS_SUGGESTION����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            EtsSuggestionDTO etsSuggestion = (EtsSuggestionDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " SYSTEMID,"
                    + " WORKORDER_BATCH,"
                    + " TITLE,"
                    + " REMARK,"
                    + " GROUP_ID,"
                    + " HANDLER,"
                    + " RECORD_DATE,"
                    + " COMPLETE_FLAG"
                    + " FROM"
                    + " ETS_SUGGESTION"
                    + " WHERE"
                    + " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR WORKORDER_BATCH LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR TITLE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR GROUP_ID LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR HANDLER LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR RECORD_DATE LIKE ?)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COMPLETE_FLAG LIKE ?)";
            sqlArgs.add(etsSuggestion.getSystemid());
            sqlArgs.add(etsSuggestion.getSystemid());
            sqlArgs.add(etsSuggestion.getWorkorderBatch());
            sqlArgs.add(etsSuggestion.getWorkorderBatch());
            sqlArgs.add(etsSuggestion.getTitle());
            sqlArgs.add(etsSuggestion.getTitle());
            sqlArgs.add(etsSuggestion.getRemark());
            sqlArgs.add(etsSuggestion.getRemark());
            sqlArgs.add(etsSuggestion.getGroupId());
            sqlArgs.add(etsSuggestion.getGroupId());
            sqlArgs.add(etsSuggestion.getHandler());
            sqlArgs.add(etsSuggestion.getHandler());
            sqlArgs.add(etsSuggestion.getRecordDate());
            sqlArgs.add(etsSuggestion.getRecordDate());
            sqlArgs.add(etsSuggestion.getCompleteFlag());
            sqlArgs.add(etsSuggestion.getCompleteFlag());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɹ��������������(EAM) ETS_SUGGESTIONҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsSuggestionDTO etsSuggestion = (EtsSuggestionDTO) dtoParameter;
        String sqlStr = "SELECT" +
                "       ES.SYSTEMID,\n" +
                "       ES.TITLE,\n" +
                "       ES.REMARK,\n" +
                "       AMS_PUB_PKG.GET_USER_NAME(ES.HANDLER) USERNAME,\n" +
                "       ES.RECORD_DATE\n" +
                "  FROM ETS_SUGGESTION ES\n" +
                " WHERE ES.WORKORDER_BATCH = ?";


        sqlArgs.add(etsSuggestion.getWorkorderBatch());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
	}

}
