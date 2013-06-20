package com.sino.ams.yj.model;


import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.yj.dto.AmsYjItemDTO;


/**
 * <p>Title: AmsYjItemModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjItemModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ������װ������ά��
 */


public class AmsYjItemModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ�������豸�����ֵ�� AMS_YJ_ITEM ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjItemDTO ���β���������
     */
    public AmsYjItemModel(SfUserDTO userAccount, AmsYjItemDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����Ӧ�������豸�����ֵ�� AMS_YJ_ITEM���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_YJ_ITEM("
                + " ITEM_CODE,"
                + " ITEM_NAME,"
                + " ITEM_CATEGORY,"
                + " CREATION_DATE,"
                + " CREATE_USER"
                + ") VALUES ("
                + " CONVERT(FLOAT,?), ?, 'װ��', GETDATE(), ?)";

        sqlArgs.add(amsYjItem.getItemCode());
        sqlArgs.add(amsYjItem.getItemName());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ�������豸�����ֵ�� AMS_YJ_ITEM���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_ITEM"
                + " SET"
                + " ITEM_NAME = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = ?"
                + " WHERE"
                + " ITEM_CODE = CONVERT(FLOAT,?)";

        sqlArgs.add(amsYjItem.getItemName());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjItem.getItemCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ��Ч
     */
    public SQLModel getEnableModel(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
        String sqlStr = "UPDATE"
                + " AMS_YJ_ITEM"
                + " SET"
                + " DISABLE_DATE = NULL,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = ?"
                + " WHERE"
                + " ITEM_CODE =CONVERT(FLOAT,?)";
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjItem.getItemCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ�ʧЧ 
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_ITEM\n" +
                "SET DISABLE_DATE=GETDATE(),"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = ?"
                + " WHERE"
                + " ITEM_CODE = CONVERT(FLOAT,?)";
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjItem.getItemCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ�������豸�����ֵ�� AMS_YJ_ITEM������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " ITEM_CODE,"
                + " ITEM_NAME,"
                + " ITEM_CATEGORY,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER"
                + " FROM"
                + " AMS_YJ_ITEM"
                + " WHERE"
                + " ITEM_CODE = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjItem.getItemCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ�������豸�����ֵ�� AMS_YJ_ITEM����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " ITEM_CODE,"
                    + " ITEM_NAME,"
                    + " ITEM_CATEGORY,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_USER"
                    + " FROM"
                    + " AMS_YJ_ITEM"
                    + " WHERE"
                    + " (? IS NULL OR ITEM_CODE LIKE ?)"
                    + " AND (? IS NULL OR ITEM_NAME LIKE ?)"
                    + " AND (? IS NULL OR ITEM_CATEGORY LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)";
            sqlArgs.add(amsYjItem.getItemCode());
            sqlArgs.add(amsYjItem.getItemCode());
            sqlArgs.add(amsYjItem.getItemName());
            sqlArgs.add(amsYjItem.getItemName());
            sqlArgs.add(amsYjItem.getItemCategory());
            sqlArgs.add(amsYjItem.getItemCategory());
            sqlArgs.add(amsYjItem.getCreationDate());
            sqlArgs.add(amsYjItem.getCreationDate());
            sqlArgs.add(amsYjItem.getCreateUser());
            sqlArgs.add(amsYjItem.getCreateUser());
            sqlArgs.add(amsYjItem.getLastUpdateDate());
            sqlArgs.add(amsYjItem.getLastUpdateDate());
            sqlArgs.add(amsYjItem.getLastUpdateUser());
            sqlArgs.add(amsYjItem.getLastUpdateUser());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ�������豸�����ֵ�� AMS_YJ_ITEMҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjItemDTO amsYjItem = (AmsYjItemDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " CONVERT(VARCHAR,ITEM_CODE) ITEM_CODE,"
                + " ITEM_NAME,"
                + " ITEM_CATEGORY,"
                + " CREATION_DATE,"
                + " dbo.APP_GET_USER_NAME(CREATE_USER) CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " dbo.APP_GET_USER_NAME(LAST_UPDATE_USER) LAST_UPDATE_USER,"
                + " DISABLE_DATE"
                + " FROM"
                + " AMS_YJ_ITEM"
                + " WHERE"
                + " ("+ SyBaseSQLUtil.isNull() +" OR ITEM_NAME LIKE ?)";
                if(!amsYjItem.getItemCode().equals("")){
                    sqlStr+=" AND ITEM_CODE = CONVERT(FLOAT,?)";
                }else{
                    sqlStr+=" AND CONVERT(FLOAT,?) =0 ";
                }
        sqlArgs.add(amsYjItem.getItemName());
        sqlArgs.add(amsYjItem.getItemName());
        sqlArgs.add(amsYjItem.getItemCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
   

     public SQLModel doVerify(String itemName) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql ="SELECT 1 FROM AMS_YJ_ITEM AYI WHERE AYI.ITEM_NAME = ?";
        strArg.add(itemName);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }
}