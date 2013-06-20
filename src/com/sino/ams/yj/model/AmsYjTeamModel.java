package com.sino.ams.yj.model;


import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.yj.dto.AmsYjTeamDTO;


/**
 * <p>Title: AmsYjTeamModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjTeamModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ�����϶���
 */


public class AmsYjTeamModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��ͨ�ű��϶���� AMS_YJ_TEAM ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjTeamDTO ���β���������
     */
    public AmsYjTeamModel(SfUserDTO userAccount, AmsYjTeamDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_TEAM���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_YJ_TEAM("
                + " TEAM_ID,"
                + " TEAM_NAME,"
                + " RESPONSIBILITY_USER,"
                + " TEL,"
                + " SITUATION,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER"
                + ") VALUES ("
                + " CONVERT(FLOAT,?), ?, ?, ?, ?, ?, GETDATE(), ?)";

        sqlArgs.add(amsYjTeam.getTeamId() );
        sqlArgs.add(amsYjTeam.getTeamName());
        sqlArgs.add(amsYjTeam.getResponsibilityUser());
        sqlArgs.add(amsYjTeam.getTel());
        sqlArgs.add(amsYjTeam.getSituation());
        sqlArgs.add(amsYjTeam.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_TEAM���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_TEAM"
                + " SET"
                + " TEAM_NAME = ?,"
                + " RESPONSIBILITY_USER = ?,"
                + " TEL = ?,"
                + " SITUATION = ?,"
                + " ORGANIZATION_ID = ?,"
//                    + " CREATION_DATE = ?,"
//                    + " CREATE_USER = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = ?"
                + " WHERE"
                + " TEAM_ID = CONVERT(FLOAT,?)";

        sqlArgs.add(amsYjTeam.getTeamName());
        sqlArgs.add(amsYjTeam.getResponsibilityUser());
        sqlArgs.add(amsYjTeam.getTel());
        sqlArgs.add(amsYjTeam.getSituation());
        sqlArgs.add(amsYjTeam.getOrganizationId());
//            sqlArgs.add(amsYjTeam.getCreationDate());
//            sqlArgs.add(amsYjTeam.getCreateUser());
//            sqlArgs.add(amsYjTeam.getLastUpdateDate());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjTeam.getTeamId());

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
        AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_TEAM\n" 
		                + "SET DISABLE_DATE=GETDATE(),"
		                + " LAST_UPDATE_DATE = GETDATE(),"
		                + " LAST_UPDATE_USER = ?"
		                + " WHERE"
		                + " TEAM_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjTeam.getTeamId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_TEAM������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " TEAM_ID,"
                + " TEAM_NAME,"
                + " RESPONSIBILITY_USER,"
                + " TEL,"
                + " SITUATION,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER"
                + " FROM"
                + " AMS_YJ_TEAM"
                + " WHERE"
                + " TEAM_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjTeam.getTeamId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_TEAM����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " TEAM_ID,"
                    + " TEAM_NAME,"
                    + " RESPONSIBILITY_USER,"
                    + " TEL,"
                    + " SITUATION,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_USER"
                    + " FROM"
                    + " AMS_YJ_TEAM"
                    + " WHERE"
                    + " (? IS NULL OR TEAM_ID LIKE ?)"
                    + " AND (? IS NULL OR TEAM_NAME LIKE ?)"
                    + " AND (? IS NULL OR RESPONSIBILITY_USER LIKE ?)"
                    + " AND (? IS NULL OR TEL LIKE ?)"
                    + " AND (? IS NULL OR SITUATION LIKE ?)"
                    + " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)";
            sqlArgs.add(amsYjTeam.getTeamId());
            sqlArgs.add(amsYjTeam.getTeamId());
            sqlArgs.add(amsYjTeam.getTeamName());
            sqlArgs.add(amsYjTeam.getTeamName());
            sqlArgs.add(amsYjTeam.getResponsibilityUser());
            sqlArgs.add(amsYjTeam.getResponsibilityUser());
            sqlArgs.add(amsYjTeam.getTel());
            sqlArgs.add(amsYjTeam.getTel());
            sqlArgs.add(amsYjTeam.getSituation());
            sqlArgs.add(amsYjTeam.getSituation());
            sqlArgs.add(amsYjTeam.getOrganizationId());
            sqlArgs.add(amsYjTeam.getOrganizationId());
            sqlArgs.add(amsYjTeam.getCreationDate());
            sqlArgs.add(amsYjTeam.getCreationDate());
            sqlArgs.add(amsYjTeam.getCreateUser());
            sqlArgs.add(amsYjTeam.getCreateUser());
            sqlArgs.add(amsYjTeam.getLastUpdateDate());
            sqlArgs.add(amsYjTeam.getLastUpdateDate());
            sqlArgs.add(amsYjTeam.getLastUpdateUser());
            sqlArgs.add(amsYjTeam.getLastUpdateUser());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ű��϶���� AMS_YJ_TEAMҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
        String sqlStr = "SELECT "
		                + " CONVERT(VARCHAR,TEAM_ID) TEAM_ID,"
		                + " TEAM_NAME,"
		                + " RESPONSIBILITY_USER,"
		                + " TEL,"
		                + " CONVERT(VARCHAR,(SELECT SUM(1) FROM AMS_YJ_USER AYU WHERE AYU.TEAM_ID=AYT.TEAM_ID)) QUANTITY,"
		                + " SITUATION,"
		                + " dbo.APP_GET_ORGNIZATION_NAME(AYT.ORGANIZATION_ID) ORGANIZATION_NAME,"
		                + " CREATION_DATE,"
		                + " dbo.APP_GET_USER_NAME(CREATE_USER) CREATE_USER,"
		                + " LAST_UPDATE_DATE,"
		                + " dbo.APP_GET_USER_NAME(LAST_UPDATE_USER) LAST_UPDATE_USER,"
		                + " DISABLE_DATE"
		                + " FROM"
		                + " AMS_YJ_TEAM AYT"
		                + " WHERE"
		                + " ("+ SyBaseSQLUtil.isNull() +" OR TEAM_NAME LIKE ?)"
		                + " AND (? =-1 OR ORGANIZATION_ID = ?)";   
		                if(!amsYjTeam.getTeamId().equals("")){
		                    sqlStr+=" AND TEAM_ID = CONVERT(FLOAT,?)";
		                }else{
		                    sqlStr+=" AND CONVERT(FLOAT,?) =0 ";
		                }       
		                sqlStr+= " ORDER BY AYT.ORGANIZATION_ID DESC";

        sqlArgs.add(amsYjTeam.getTeamName());
        sqlArgs.add(amsYjTeam.getTeamName());
        sqlArgs.add(amsYjTeam.getOrganizationId());
        sqlArgs.add(amsYjTeam.getOrganizationId());  
        sqlArgs.add(amsYjTeam.getTeamId());
        
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
	/**
	 * ��Ч
	 */
    public SQLModel getEnableModel(String teamId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjTeamDTO amsYjTeam = (AmsYjTeamDTO) dtoParameter;
        String sqlStr = "UPDATE"
	                + " AMS_YJ_TEAM"
	                + " SET"
	                + " DISABLE_DATE = NULL,"
	                + " LAST_UPDATE_DATE = GETDATE(),"
	                + " LAST_UPDATE_USER = ?"
	                + " WHERE"
	                + " TEAM_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjTeam.getTeamId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel doVerify(String teamName) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql = "SELECT 1 FROM AMS_YJ_TEAM AYT WHERE AYT.TEAM_NAME = ?";
        strArg.add(teamName);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }

    /**
     * ��������ͳ��
     * @return
     */
    public SQLModel getTeamStat() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjTeamDTO teamDTO=(AmsYjTeamDTO)dtoParameter; 
        String sqlStr =
                        "SELECT dbo.APP_GET_ORGNIZATION_NAME(AYT.ORGANIZATION_ID) ORGANIZATION_NAME,\n" +
                        "       dbo.YEP_GET_ORG_TEAMS(AYT.ORGANIZATION_ID) TEAM_COUNT,\n" +
                        "       dbo.YEP_GET_ORG_USERS(AYT.ORGANIZATION_ID) USER_COUNT,\n" +
                        "       0\n" +
                        "  FROM AMS_YJ_TEAM AYT\n" +
                        " WHERE (? =-1 OR AYT.ORGANIZATION_ID = ?)\n" +
                        " GROUP BY AYT.ORGANIZATION_ID\n" +
                        " UNION ALL\n" +
                        "   SELECT '�ϼ�' ORGANIZATION_NAME,\n" +
                        "       dbo.YEP_GET_ORG_TEAMS(?),\n" +
                        "       dbo.YEP_GET_ORG_USERS(?),\n" +
                        "       SUM(AYT.ORGANIZATION_ID)\n" +
                        "  FROM AMS_YJ_TEAM AYT\n" +
                        " WHERE (? =-1 OR AYT.ORGANIZATION_ID = ?)";

        sqlArgs.add(teamDTO.getOrganizationId());
        sqlArgs.add(teamDTO.getOrganizationId());
        sqlArgs.add(teamDTO.getOrganizationId());
        sqlArgs.add(teamDTO.getOrganizationId());
        sqlArgs.add(teamDTO.getOrganizationId());
        sqlArgs.add(teamDTO.getOrganizationId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
}