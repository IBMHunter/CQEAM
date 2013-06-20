package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsAssetsCommQueryDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: AmsAssetsCommQueryModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsCommQueryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class CustomQuerySetModel extends AMSSQLProducer {

    /**
     * ���ܣ��ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCommQueryDTO ���β���������
     */
    public CustomQuerySetModel(SfUserDTO userAccount,
                               AmsAssetsCommQueryDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ������ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsCommQueryDTO dto = (AmsAssetsCommQueryDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " AMS_ASSETS_COMM_QUERY("
                        + " USER_ID,"
                        + " FIELD_NAME,"
                        + " FIELD_DESC,"
                        + " FIELD_USAGE,"
                        + " SORT_NO"
                        + ") VALUES ("
                        + " ?, ?, ?, ?, ?)";
        sqlArgs.add(dto.getUserId());
        sqlArgs.add(dto.getFieldName());
        sqlArgs.add(dto.getFieldDesc());
        sqlArgs.add(dto.getFieldUsage());
        sqlArgs.add(dto.getSortNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDeleteFieldsByUserIdModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                        + " AMS_ASSETS_COMM_QUERY"
                        + " WHERE"
                        + " USER_ID = ?";
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�û��Զ�����ֶ��б�
     * @param fieldUsage String
     * @return SQLModel
     */
    public SQLModel getFieldsByUserIdModel(String fieldUsage) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                        + " *"
                        + " FROM"
                        + " AMS_ASSETS_COMM_QUERY"
                        + " WHERE"
                        + " USER_ID = ?"
                        + " AND FIELD_USAGE = ?"
                        + " ORDER BY"
                        + " SORT_NO";
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(fieldUsage);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
