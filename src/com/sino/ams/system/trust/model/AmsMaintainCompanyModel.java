package com.sino.ams.system.trust.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.trust.dto.AmsMaintainCompanyDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsMaintainCompanyModel</p>
 * <p>Description:�����Զ�����SQL��������AmsMaintainCompanyModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 *    
 * �޸���:wangzhipeng 
 * ���ڣ�2011.04.06
 * ����:getPageQueryModel ���� sql�����������: "AND AMC.ORGANIZATION_ID = EC.ORGANIZATION_ID  \n"  ��˾--->����   ��������  
 * 
 */


public class AmsMaintainCompanyModel extends AMSSQLProducer {


    /**
     * ���ܣ���ά��˾��(EAM) AMS_MAINTAIN_COMPANY ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsMaintainCompanyDTO ���β���������
     */
    public AmsMaintainCompanyModel(SfUserDTO userAccount, AmsMaintainCompanyDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��˾��(EAM) AMS_MAINTAIN_COMPANY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
		AmsMaintainCompanyDTO dto = (AmsMaintainCompanyDTO)dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO "
                + " AMS_MAINTAIN_COMPANY("
                + " COMPANY_ID,"
                + " NAME,"
                + " ADDRESS,"
                + " LEGAL_REPRESENTATIVE,"
                + " CONTACT_PEOPLE,"
                + " OFFICE_TELEPHONE,"
                + " CONTACT_TELEPHONE,"
                + " FAX_NUMBER,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " COUNTY_CODE,"
                + " REMARK"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, " + SyBaseSQLUtil.getCurDate() + ", ?, " + SyBaseSQLUtil.getCurDate() + ",?,?,?)";
        
        sqlArgs.add(dto.getCompanyId());
        sqlArgs.add(dto.getName());
        sqlArgs.add(dto.getAddress());
        sqlArgs.add(dto.getLegalRepresentative());
        sqlArgs.add(dto.getContactPeople());
        sqlArgs.add(dto.getOfficeTelephone());
        sqlArgs.add(dto.getContactTelephone());
        sqlArgs.add(dto.getFaxNumber());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(dto.getRemark());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��˾��(EAM) AMS_MAINTAIN_COMPANY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
		AmsMaintainCompanyDTO dto = (AmsMaintainCompanyDTO)dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE AMS_MAINTAIN_COMPANY"
                + " SET"
                + " NAME = ?,"
                + " ADDRESS = ?,"
                + " LEGAL_REPRESENTATIVE = ?,"
                + " CONTACT_PEOPLE = ?,"
                + " OFFICE_TELEPHONE = ?,"
                + " CONTACT_TELEPHONE = ?,"
                + " FAX_NUMBER = ?,"
                + " ORGANIZATION_ID = ?,"
         //       + " CREATION_DATE = ?,"
         //       + " CREATED_BY = ?,"
                + " LAST_UPDATE_DATE = " + SyBaseSQLUtil.getCurDate() + ","
                + " LAST_UPDATE_BY = ?,"
                + " COUNTY_CODE = ?,"
                + " REMARK = ?"
                + " WHERE"
                + " COMPANY_ID = ?";

        sqlArgs.add(dto.getName());
        sqlArgs.add(dto.getAddress());
        sqlArgs.add(dto.getLegalRepresentative());
        sqlArgs.add(dto.getContactPeople());
        sqlArgs.add(dto.getOfficeTelephone());
        sqlArgs.add(dto.getContactTelephone());
        sqlArgs.add(dto.getFaxNumber());
        sqlArgs.add(userAccount.getOrganizationId());
     //   sqlArgs.add(dto.getCreationDate());
     //   sqlArgs.add(dto.getCreatedBy());
     //   sqlArgs.add(dto.getLastUpdateDate());
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(dto.getRemark());
        sqlArgs.add(dto.getCompanyId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��˾��(EAM) AMS_MAINTAIN_COMPANY����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
		AmsMaintainCompanyDTO dto = (AmsMaintainCompanyDTO)dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " AMS_MAINTAIN_COMPANY"
						+ " WHERE"
						+ " COMPANY_ID = ?";
        sqlArgs.add(dto.getCompanyId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɴ�ά��˾��(EAM) AMS_MAINTAIN_COMPANY������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		AmsMaintainCompanyDTO dto = (AmsMaintainCompanyDTO)dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " COMPANY_ID,"
						+ " NAME,"
						+ " ADDRESS,"
						+ " LEGAL_REPRESENTATIVE,"
						+ " CONTACT_PEOPLE,"
						+ " OFFICE_TELEPHONE,"
						+ " CONTACT_TELEPHONE,"
						+ " FAX_NUMBER,"
						+ " ORGANIZATION_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY,"
						+ " COUNTY_CODE,"
						+ " REMARK"
						+ " FROM"
						+ " AMS_MAINTAIN_COMPANY"
						+ " WHERE"
						+ " COMPANY_ID = ?";
        sqlArgs.add(dto.getCompanyId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ����ɴ�ά��˾��(EAM) AMS_MAINTAIN_COMPANYҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
		AmsMaintainCompanyDTO dto = (AmsMaintainCompanyDTO)dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT " +
                "        AMC.COMPANY_ID,/*��˾����*/ " +
                "        AMC.NAME, /*��˾����*/\n" +
                "        AMC.CONTACT_PEOPLE, /*��ϵ��*/\n" +
                "        AMC.CONTACT_TELEPHONE, /*��ϵ�˵绰*/\n" +
                "        AMC.OFFICE_TELEPHONE,/*�칫�绰*/\n" +
                "        EOCM.COMPANY ,/*������֯*/\n" +
                "        EC.COUNTY_NAME ,/*��������*/\n" +
                "        AMC.REMARK /*��ע*/\n" +
                " FROM   ETS_OU_CITY_MAP EOCM, AMS_MAINTAIN_COMPANY AMC,ETS_COUNTY EC\n" +
                " WHERE  EOCM.ORGANIZATION_ID = AMC.ORGANIZATION_ID  \n" +
                " AND    AMC.ORGANIZATION_ID = EC.ORGANIZATION_ID  \n " +        //���Ӳ�ѯ���� ��˾--->���� ����
                " AND    ( EC.COUNTY_CODE_MIS =AMC.COUNTY_CODE OR EC.COUNTY_CODE =AMC.COUNTY_CODE )\n" +
                " AND    (" + SyBaseSQLUtil.nullStringParam() + " OR  AMC.CONTACT_PEOPLE LIKE  ? )\n" +
                " AND   (" + SyBaseSQLUtil.nullStringParam() + " OR  AMC.NAME LIKE  ? )\n" +
                " AND   (" + SyBaseSQLUtil.nullStringParam() + " OR ?='-1' OR  AMC.COUNTY_CODE = ?) \n"+
                " AND    EOCM.ORGANIZATION_ID = ? ";

       // System.out.println("��ά��˾=="+sqlStr);
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getContactPeople() );
        SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getName() );
       
        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(dto.getCountyCode());
//        sqlArgs.add(dto.getContactPeople());
//        sqlArgs.add(dto.getContactPeople());
//        sqlArgs.add(dto.getName());
//        sqlArgs.add(dto.getName());
//        sqlArgs.add(dto.getCountyCode());
//        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}

}
