package com.sino.ams.system.trust.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.trust.dto.AmsMaintainPeopleDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsMaintainPeopleModel</p>
 * <p>Description:�����Զ�����SQL��������AmsMaintainPeopleModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsMaintainPeopleModel extends BaseSQLProducer {

    private AmsMaintainPeopleDTO amsMaintainPeople = null;
    private SfUserDTO SfUser = null;

    /**
     * ���ܣ���ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsMaintainPeopleDTO ���β���������
     */
    public AmsMaintainPeopleModel(SfUserDTO userAccount, AmsMaintainPeopleDTO dtoParameter) {
        super(userAccount, dtoParameter);
        SfUser = userAccount;
        this.amsMaintainPeople = (AmsMaintainPeopleDTO) dtoParameter;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = 
        		 "INSERT INTO "
                + " AMS_MAINTAIN_PEOPLE("
                + " USER_ID,"
                + " USER_NAME,"
                + " USER_TELEPHONE,"
                + " USER_MOBILE_PHONE,"
                + " EMAIL,"
                + " BP_NUMBER,"
                + " COMPANY_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + ") VALUES ("
                + "  ?, ?, ?, ?, ?, ?, ?, " +  SyBaseSQLUtil.getCurDate() + " , ?,  " +  SyBaseSQLUtil.getCurDate() + " , ?)";
       sqlArgs.add(amsMaintainPeople.getUserId());
        sqlArgs.add(amsMaintainPeople.getUserName());
        sqlArgs.add(amsMaintainPeople.getUserTelephone());
        sqlArgs.add(amsMaintainPeople.getUserMobilePhone());
        sqlArgs.add(amsMaintainPeople.getEmail());
        sqlArgs.add(amsMaintainPeople.getBpNumber());
        sqlArgs.add(amsMaintainPeople.getCompanyId()+"");
        sqlArgs.add(SfUser.getUserId());
        sqlArgs.add(SfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE AMS_MAINTAIN_PEOPLE"
                + " SET"
                + " USER_NAME = ?,"
                + " USER_TELEPHONE = ?,"
                + " USER_MOBILE_PHONE = ?,"
                + " EMAIL = ?,"
                + " BP_NUMBER = ?,"
                + " COMPANY_ID = ?,"
                /*	+ " CREATION_DATE = ?,"
                + " CREATED_BY = ?,"*/
                + " LAST_UPDATE_DATE =  " +  SyBaseSQLUtil.getCurDate() + "  ,"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " USER_ID = ?";

        sqlArgs.add(amsMaintainPeople.getUserName());
        sqlArgs.add(amsMaintainPeople.getUserTelephone());
        sqlArgs.add(amsMaintainPeople.getUserMobilePhone());
        sqlArgs.add(amsMaintainPeople.getEmail());
        sqlArgs.add(amsMaintainPeople.getBpNumber());
        sqlArgs.add(amsMaintainPeople.getCompanyId()+"");
/*		sqlArgs.add(amsMaintainPeople.getCreationDate());
		sqlArgs.add(amsMaintainPeople.getCreatedBy());
		sqlArgs.add(amsMaintainPeople.getLastUpdateDate());*/
        sqlArgs.add(SfUser.getUserId());
        sqlArgs.add(amsMaintainPeople.getUserId()+"");

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_MAINTAIN_PEOPLE"
                + " WHERE"
                + " USER_ID = ?";
        sqlArgs.add(amsMaintainPeople.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " USER_ID,"
                + " USER_NAME,"
                + " USER_TELEPHONE,"
                + " USER_MOBILE_PHONE,"
                + " EMAIL,"
                + " BP_NUMBER,"
                + " COMPANY_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_MAINTAIN_PEOPLE"
                + " WHERE"
                + " USER_ID = ?";
        sqlArgs.add(amsMaintainPeople.getUserId()+"");

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " USER_ID,"
                + " USER_NAME,"
                + " USER_TELEPHONE,"
                + " USER_MOBILE_PHONE,"
                + " EMAIL,"
                + " BP_NUMBER,"
                + " COMPANY_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_MAINTAIN_PEOPLE"
                + " WHERE"
                + "( " + SyBaseSQLUtil.isNull() + "  OR USER_ID LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR USER_NAME LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR USER_TELEPHONE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR USER_MOBILE_PHONE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR EMAIL LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR BP_NUMBER LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR COMPANY_ID LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
        sqlArgs.add(amsMaintainPeople.getUserId());
        sqlArgs.add(amsMaintainPeople.getUserId());
        sqlArgs.add(amsMaintainPeople.getUserName());
        sqlArgs.add(amsMaintainPeople.getUserName());
        sqlArgs.add(amsMaintainPeople.getUserTelephone());
        sqlArgs.add(amsMaintainPeople.getUserTelephone());
        sqlArgs.add(amsMaintainPeople.getUserMobilePhone());
        sqlArgs.add(amsMaintainPeople.getUserMobilePhone());
        sqlArgs.add(amsMaintainPeople.getEmail());
        sqlArgs.add(amsMaintainPeople.getEmail());
        sqlArgs.add(amsMaintainPeople.getBpNumber());
        sqlArgs.add(amsMaintainPeople.getBpNumber());
        sqlArgs.add(amsMaintainPeople.getCompanyId());
        sqlArgs.add(amsMaintainPeople.getCompanyId());
        sqlArgs.add(amsMaintainPeople.getCreationDate());
        sqlArgs.add(amsMaintainPeople.getCreationDate());
        sqlArgs.add(amsMaintainPeople.getCreatedBy());
        sqlArgs.add(amsMaintainPeople.getCreatedBy());
        sqlArgs.add(amsMaintainPeople.getLastUpdateDate());
        sqlArgs.add(amsMaintainPeople.getLastUpdateDate());
        sqlArgs.add(amsMaintainPeople.getLastUpdateBy());
        sqlArgs.add(amsMaintainPeople.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��Ա��(EAM) AMS_MAINTAIN_PEOPLEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "   SELECT \n" +
                " AMP.USER_ID USER_ID , \n" +
                " AMP.USER_NAME USER_NAME, \n" +
                " AMP.USER_TELEPHONE USER_TELEPHONE, \n" +
                " AMP.USER_MOBILE_PHONE USER_MOBILE_PHONE, \n" +
                " AMP.EMAIL EMAIL, \n" +
                " AMC.NAME COMPANY_NAME \n" +
                " FROM \n" +
                " AMS_MAINTAIN_COMPANY AMC, \n" +
                " AMS_MAINTAIN_PEOPLE  AMP \n" +
                " WHERE \n" +
                " AMC.COMPANY_ID = AMP.COMPANY_ID  \n " +
                " AND (" + SyBaseSQLUtil.nullStringParam() + " OR AMP.USER_NAME LIKE ?) \n" +
                " AND (" + SyBaseSQLUtil.nullStringParam() + " OR ?='-1' OR AMP.COMPANY_ID = ? ) \n" +
                " AND AMC.ORGANIZATION_ID = ? \n ";

        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, amsMaintainPeople.getUserName() );
        sqlArgs.add(amsMaintainPeople.getCompanyId()+"");
        sqlArgs.add(amsMaintainPeople.getCompanyId()+"");
        sqlArgs.add(amsMaintainPeople.getCompanyId()+"");
        sqlArgs.add(amsMaintainPeople.getCompanyId()+"");
        
//        sqlArgs.add(amsMaintainPeople.getUserName());
//        sqlArgs.add(amsMaintainPeople.getUserName());
//        sqlArgs.add(amsMaintainPeople.getCompanyId());
//        sqlArgs.add(amsMaintainPeople.getCompanyId());
        sqlArgs.add(SfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}