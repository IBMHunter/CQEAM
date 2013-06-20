package com.sino.ams.net.equip.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.net.equip.dto.IntegratedDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * Created by IntelliJ IDEA.
 * User: Owner
 * Date: 2008-1-28
 * Time: 11:46:19
 * To change this template use File | Settings | File Templates.
 */
public class IntegratedModel extends AMSSQLProducer {

    /**
     * ���ܣ�δƥ���ʲ��嵥 ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     */
    public IntegratedModel(SfUserDTO userAccount,IntegratedDTO dtoParameter) {
        super(userAccount, dtoParameter);

    }

    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        IntegratedDTO dto = (IntegratedDTO) dtoParameter;
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
//        try {
//            FileUtil.appendStrContent(sqlModel.toString(), "C:\\cust.sql");
//            FileUtil.appendStrContent(WorldConstant.ENTER_CHAR, "C:\\cust.sql");
//        } catch (FileException ex) {
//        }
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
