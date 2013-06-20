package com.sino.ams.yj.model;


import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.yj.dto.AmsYjUserDTO;


/**
 * <p>Title: AmsYjUserModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjUserModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ��������Աά��
 */


public class AmsYjUserModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��ͨ�ű��϶���� AMS_YJ_USER ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjUserDTO ���β���������
     */
    public AmsYjUserModel(SfUserDTO userAccount, AmsYjUserDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_USER���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_YJ_USER("
                + " TEAM_ID,"
                + " USER_NAME,"
                + " POST,"
                + " TEL,"
                + " CATEGORY,"
                + " ATTRIBUTE,"
                + " REMARK,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " USER_ID"
                + ") VALUES ("
                + " CONVERT(FLOAT,?), ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?,CONVERT(FLOAT,?))";

	        sqlArgs.add(amsYjUser.getTeamId());
	        sqlArgs.add(amsYjUser.getUserName());
	        sqlArgs.add(amsYjUser.getPost());
	        sqlArgs.add(amsYjUser.getTel());
	        sqlArgs.add(amsYjUser.getCategory());
	        sqlArgs.add(amsYjUser.getAttribute());
	        sqlArgs.add(amsYjUser.getRemark());
	        sqlArgs.add(amsYjUser.getOrganizationId());
	        sqlArgs.add(sfUser.getUserId());
	        sqlArgs.add(amsYjUser.getUserId() );
	        sqlModel.setSqlStr(sqlStr);
	        sqlModel.setArgs(sqlArgs);
	        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_USER���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_USER"
                + " SET"
                + " TEAM_ID = CONVERT(FLOAT,?),"
                + " USER_NAME = ?,"
                + " POST = ?,"
                + " TEL = ?,"
                + " CATEGORY = ?,"
                + " ATTRIBUTE = ?,"
                + " REMARK = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = ?"
                + " WHERE"
                + " USER_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjUser.getTeamId());
        sqlArgs.add(amsYjUser.getUserName());
        sqlArgs.add(amsYjUser.getPost());
        sqlArgs.add(amsYjUser.getTel());
        sqlArgs.add(amsYjUser.getCategory());
        sqlArgs.add(amsYjUser.getAttribute());
        sqlArgs.add(amsYjUser.getRemark());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_USER����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_YJ_USER"
                + " WHERE"
                + " USER_ID =CONVERT(FLOAT,?)";

        sqlArgs.add(amsYjUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_USER������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " TEAM_ID,"
                + " USER_NAME,"
                + " POST,"
                + " TEL,"
                + " CATEGORY,"
                + " ATTRIBUTE,"
                + " REMARK,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER,"
                + " USER_ID"
                + " FROM"
                + " AMS_YJ_USER"
                + " WHERE"
                + " USER_ID = CONVERT(FLOAT,?)";

        sqlArgs.add(amsYjUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_USER����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " TEAM_ID,"
                    + " USER_NAME,"
                    + " POST,"
                    + " TEL,"
                    + " CATEGORY,"
                    + " ATTRIBUTE,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_USER,"
                    + " USER_ID"
                    + " FROM"
                    + " AMS_YJ_USER"
                    + " WHERE"
                    + " (? IS NULL OR TEAM_ID LIKE ?)"
                    + " AND (? IS NULL OR USER_NAME LIKE ?)"
                    + " AND (? IS NULL OR POST LIKE ?)"
                    + " AND (? IS NULL OR TEL LIKE ?)"
                    + " AND (? IS NULL OR CATEGORY LIKE ?)"
                    + " AND (? IS NULL OR ATTRIBUTE LIKE ?)"
                    + " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)"
                    + " AND (? IS NULL OR USER_ID LIKE ?)";
            sqlArgs.add(amsYjUser.getTeamId());
            sqlArgs.add(amsYjUser.getTeamId());
            sqlArgs.add(amsYjUser.getUserName());
            sqlArgs.add(amsYjUser.getUserName());
            sqlArgs.add(amsYjUser.getPost());
            sqlArgs.add(amsYjUser.getPost());
            sqlArgs.add(amsYjUser.getTel());
            sqlArgs.add(amsYjUser.getTel());
            sqlArgs.add(amsYjUser.getCategory());
            sqlArgs.add(amsYjUser.getCategory());
            sqlArgs.add(amsYjUser.getAttribute());
            sqlArgs.add(amsYjUser.getAttribute());
            sqlArgs.add(amsYjUser.getOrganizationId());
            sqlArgs.add(amsYjUser.getOrganizationId());
            sqlArgs.add(amsYjUser.getCreationDate());
            sqlArgs.add(amsYjUser.getCreationDate());
            sqlArgs.add(amsYjUser.getCreateUser());
            sqlArgs.add(amsYjUser.getCreateUser());
            sqlArgs.add(amsYjUser.getLastUpdateDate());
            sqlArgs.add(amsYjUser.getLastUpdateDate());
            sqlArgs.add(amsYjUser.getLastUpdateUser());
            sqlArgs.add(amsYjUser.getLastUpdateUser());
            sqlArgs.add(amsYjUser.getUserId());
            sqlArgs.add(amsYjUser.getUserId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� teamId �����ѯ����SQL��
     * ����Զ���������Ӧ��ͨ�ű��϶���� AMS_YJ_USER��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param teamId AdvancedNumber
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByTeamIdModel(String teamId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " USER_NAME,"
                + " POST,"
                + " TEL,"
                + " CATEGORY,"
                + " ATTRIBUTE,"
                + " REMARK,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER,"
                + " USER_ID"
                + " FROM"
                + " AMS_YJ_USER"
                + " WHERE"
                + " TEAM_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(teamId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     *
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        if (foreignKey.equals("teamId")) {
            sqlModel = getDataByTeamIdModel(amsYjUser.getTeamId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� teamId ��������ɾ��SQL��
     * ����Զ���������Ӧ��ͨ�ű��϶���� AMS_YJ_USER����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @param teamId AdvancedNumber
     * @return SQLModel ��������ɾ����SQLModel
     */
    private SQLModel getDeleteByTeamIdModel(String teamId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE "
                + " USER_NAME,"
                + " POST,"
                + " TEL,"
                + " CATEGORY,"
                + " ATTRIBUTE,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER"
                + " FROM"
                + " AMS_YJ_USER"
                + " WHERE"
                + " TEAM_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(teamId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���������ֶ�ɾ������
     *
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        if (foreignKey.equals("teamId")) {
            sqlModel = getDeleteByTeamIdModel(amsYjUser.getTeamId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_USERҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjUserDTO amsYjUser = (AmsYjUserDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " dbo.APP_GET_ORGNIZATION_NAME(AYU.ORGANIZATION_ID) ORGANIZATION_NAME,"
                + " AYU.TEAM_ID,"
                + " AYT.TEAM_NAME,"
                + " AYT.RESPONSIBILITY_USER,"
                + " AYT.TEL TEL1,"
                + " AYT.SITUATION,"
                + " AYU.USER_NAME,"
                + " AYU.POST,"
                + " AYU.TEL,"
                + " AYU.CATEGORY,"
                + " AYU.ATTRIBUTE,"
                + " AYU.REMARK,"
                + " AYU.CREATION_DATE,"
                + " dbo.APP_GET_USER_NAME(AYU.CREATE_USER) CREATE_USER,"
                + " AYU.LAST_UPDATE_DATE,"
                + " dbo.APP_GET_USER_NAME(AYU.LAST_UPDATE_USER) LAST_UPDATE_USER,"
                + " AYU.USER_ID"
                + " FROM"
                + " AMS_YJ_USER AYU,"
                + " AMS_YJ_TEAM AYT"
                + " WHERE"
                + " AYT.TEAM_ID=AYU.TEAM_ID"
                + " AND ("+ SyBaseSQLUtil.isNull() +" OR AYT.TEAM_NAME LIKE ?)"
                + " AND ("+ SyBaseSQLUtil.isNull() +" OR AYU.USER_NAME LIKE ?)"
                + " AND (? =-1 OR AYU.ORGANIZATION_ID= ?)" ;

        sqlArgs.add(amsYjUser.getTeamName());
        sqlArgs.add(amsYjUser.getTeamName());
        sqlArgs.add(amsYjUser.getUserName());
        sqlArgs.add(amsYjUser.getUserName());
        sqlArgs.add(amsYjUser.getOrganizationId());
        sqlArgs.add(amsYjUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}

}