package com.sino.ams.yj.model;

import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.yj.dto.AmsYjIsatphoneDTO;

/**
 * <p>Title: AmsYjIsatphoneModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjIsatphoneModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-���ǵ绰ά��
 */

public class AmsYjIsatphoneModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ����ǵ绰��Ϣ�� AMS_YJ_ISATPHONE ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjIsatphoneDTO ���β���������
     */
    public AmsYjIsatphoneModel(SfUserDTO userAccount, AmsYjIsatphoneDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ��������ǵ绰��Ϣ�� AMS_YJ_ISATPHONE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjIsatphoneDTO amsYjIsatphone = (AmsYjIsatphoneDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_YJ_ISATPHONE("
                + " ISATPHONE_ID,"
                + " ISATPHONE_NAME,"
                + " ISATPHONE_TYPE,"
                + " ISATPHONE_MODEL,"
                + " ISATPHONE_ADDRESS,"
                + " TEL,"
                + " BUYING_TIME,"
                + " COST,"
                + " USED_NUMBER,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " RESOURCE_ID"
                + ") VALUES ("
                + " CONVERT(FLOAT,?), ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?,CONVERT(FLOAT,?))";

        sqlArgs.add(amsYjIsatphone.getIsatphoneId());
        sqlArgs.add(amsYjIsatphone.getIsatphoneName());
        sqlArgs.add(amsYjIsatphone.getIsatphoneType());
        sqlArgs.add(amsYjIsatphone.getIsatphoneModel());
        sqlArgs.add(amsYjIsatphone.getIsatphoneAddress());
        sqlArgs.add(amsYjIsatphone.getTel());
        sqlArgs.add(amsYjIsatphone.getBuyingTime());
        sqlArgs.add(amsYjIsatphone.getCost());
        sqlArgs.add(amsYjIsatphone.getUsedNumber());
        sqlArgs.add(amsYjIsatphone.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjIsatphone.getResourceId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ǵ绰��Ϣ�� AMS_YJ_ISATPHONE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjIsatphoneDTO amsYjIsatphone = (AmsYjIsatphoneDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_ISATPHONE"
                + " SET"
                + " ISATPHONE_NAME = ?,"
                + " ISATPHONE_TYPE = ?,"
                + " ISATPHONE_MODEL = ?,"
                + " ISATPHONE_ADDRESS = ?,"
                + " TEL = ?,"
                + " BUYING_TIME = ?,"
                + " COST = ?,"
                + " USED_NUMBER = ?,"
                + " ORGANIZATION_ID = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = ?,"
                + " RESOURCE_ID = CONVERT(FLOAT,?)"
                + " WHERE"
                + " ISATPHONE_ID = CONVERT(FLOAT,?)";

        sqlArgs.add(amsYjIsatphone.getIsatphoneName());
        sqlArgs.add(amsYjIsatphone.getIsatphoneType());
        sqlArgs.add(amsYjIsatphone.getIsatphoneModel());
        sqlArgs.add(amsYjIsatphone.getIsatphoneAddress());
        sqlArgs.add(amsYjIsatphone.getTel());
        sqlArgs.add(amsYjIsatphone.getBuyingTime());
        sqlArgs.add(amsYjIsatphone.getCost());
        sqlArgs.add(amsYjIsatphone.getUsedNumber());
        sqlArgs.add(amsYjIsatphone.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjIsatphone.getResourceId());
        sqlArgs.add(amsYjIsatphone.getIsatphoneId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ǵ绰��Ϣ�� AMS_YJ_ISATPHONE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjIsatphoneDTO amsYjIsatphone = (AmsYjIsatphoneDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_YJ_ISATPHONE"
                + " WHERE"
                + " ISATPHONE_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjIsatphone.getIsatphoneId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ǵ绰��Ϣ�� AMS_YJ_ISATPHONE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjIsatphoneDTO amsYjIsatphone = (AmsYjIsatphoneDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " AYI.ISATPHONE_ID,"
                + " AYI.ISATPHONE_NAME,"
                + " AYI.ISATPHONE_TYPE,"
                + " AYI.ISATPHONE_MODEL,"
                + " AYI.ISATPHONE_ADDRESS,"
                + " AYI.TEL,"
                + " AYI.BUYING_TIME,"
                + " AYI.COST,"
                + " AYI.USED_NUMBER,"
                + " AYI.ORGANIZATION_ID,"
                + " AYI.CREATION_DATE,"
                + " AYI.CREATE_USER,"
                + " AYI.LAST_UPDATE_DATE,"
                + " AYI.LAST_UPDATE_USER,"
                + " AYCR.RESOURCE_ID,"
                + " AYCR.EQUIPMENT_NAME"
                + " FROM "
                + " AMS_YJ_ISATPHONE AYI,AMS_YJ_COMMUNICATE_RESOURCE AYCR\n"
                + " WHERE AYI.RESOURCE_ID *= AYCR.RESOURCE_ID\n"
                + " AND AYI.ISATPHONE_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjIsatphone.getIsatphoneId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ǵ绰��Ϣ�� AMS_YJ_ISATPHONE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjIsatphoneDTO amsYjIsatphone = (AmsYjIsatphoneDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " ISATPHONE_ID,"
                    + " ISATPHONE_NAME,"
                    + " ISATPHONE_TYPE,"
                    + " ISATPHONE_MODEL,"
                    + " ISATPHONE_ADDRESS,"
                    + " TEL,"
                    + " BUYING_TIME,"
                    + " COST,"
                    + " USED_NUMBER,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_USER"
                    + " FROM"
                    + " AMS_YJ_ISATPHONE"
                    + " WHERE"
                    + " (? IS NULL OR ISATPHONE_ID LIKE ?)"
                    + " AND (? IS NULL OR ISATPHONE_NAME LIKE ?)"
                    + " AND (? IS NULL OR ISATPHONE_TYPE LIKE ?)"
                    + " AND (? IS NULL OR ISATPHONE_MODEL LIKE ?)"
                    + " AND (? IS NULL OR ISATPHONE_ADDRESS LIKE ?)"
                    + " AND (? IS NULL OR TEL LIKE ?)"
                    + " AND (? IS NULL OR BUYING_TIME LIKE ?)"
                    + " AND (? IS NULL OR COST LIKE ?)"
                    + " AND (? IS NULL OR USED_NUMBER LIKE ?)"
                    + " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)";
            sqlArgs.add(amsYjIsatphone.getIsatphoneId());
            sqlArgs.add(amsYjIsatphone.getIsatphoneId());
            sqlArgs.add(amsYjIsatphone.getIsatphoneName());
            sqlArgs.add(amsYjIsatphone.getIsatphoneName());
            sqlArgs.add(amsYjIsatphone.getIsatphoneType());
            sqlArgs.add(amsYjIsatphone.getIsatphoneType());
            sqlArgs.add(amsYjIsatphone.getIsatphoneModel());
            sqlArgs.add(amsYjIsatphone.getIsatphoneModel());
            sqlArgs.add(amsYjIsatphone.getIsatphoneAddress());
            sqlArgs.add(amsYjIsatphone.getIsatphoneAddress());
            sqlArgs.add(amsYjIsatphone.getTel());
            sqlArgs.add(amsYjIsatphone.getTel());
            sqlArgs.add(amsYjIsatphone.getBuyingTime());
            sqlArgs.add(amsYjIsatphone.getBuyingTime());
            sqlArgs.add(amsYjIsatphone.getCost());
            sqlArgs.add(amsYjIsatphone.getCost());
            sqlArgs.add(amsYjIsatphone.getUsedNumber());
            sqlArgs.add(amsYjIsatphone.getUsedNumber());
            sqlArgs.add(amsYjIsatphone.getOrganizationId());
            sqlArgs.add(amsYjIsatphone.getOrganizationId());
            sqlArgs.add(amsYjIsatphone.getCreationDate());
            sqlArgs.add(amsYjIsatphone.getCreationDate());
            sqlArgs.add(amsYjIsatphone.getCreateUser());
            sqlArgs.add(amsYjIsatphone.getCreateUser());
            sqlArgs.add(amsYjIsatphone.getLastUpdateDate());
            sqlArgs.add(amsYjIsatphone.getLastUpdateDate());
            sqlArgs.add(amsYjIsatphone.getLastUpdateUser());
            sqlArgs.add(amsYjIsatphone.getLastUpdateUser());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ǵ绰��Ϣ�� AMS_YJ_ISATPHONEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjIsatphoneDTO amsYjIsatphone = (AmsYjIsatphoneDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " dbo.APP_GET_ORGNIZATION_NAME(ORGANIZATION_ID) ORGANIZATION_NAME,"
                    + " ISATPHONE_ID,"
                    + " ISATPHONE_NAME,"
                    + " ISATPHONE_TYPE,"
                    + " ISATPHONE_MODEL,"
                    + " ISATPHONE_ADDRESS,"
                    + " TEL,"
                    + " BUYING_TIME,"
                    + " COST,"
                    + " USED_NUMBER,"
                    + " CREATION_DATE,"
                    + " dbo.APP_GET_USER_NAME(CREATE_USER) CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " dbo.APP_GET_USER_NAME(LAST_UPDATE_USER)  LAST_UPDATE_USER,"
                    + " RESOURCE_ID"
                    + " FROM"
                    + " AMS_YJ_ISATPHONE"
                    + " WHERE"                    
//                    + " AND (? IS NULL OR ISATPHONE_NAME LIKE ?)"
//                    + " AND (? IS NULL OR ISATPHONE_TYPE LIKE ?)"
//                    + " AND (? IS NULL OR ISATPHONE_MODEL LIKE ?)"
                    + " ("+SyBaseSQLUtil.isNull()+" OR ISATPHONE_ADDRESS LIKE ?)"
//                    + " AND (? IS NULL OR TEL LIKE ?)"
//                    + " AND (? IS NULL OR BUYING_TIME LIKE ?)"
//                    + " AND (? IS NULL OR COST LIKE ?)"
//                    + " AND (? IS NULL OR USED_NUMBER LIKE ?)"
                    + " AND (? =-1 OR ORGANIZATION_ID = ?)";
//                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
//                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
//                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
//                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)"
//                    + " AND (? IS NULL OR RESOURCE_ID LIKE ?)";
	            if(!amsYjIsatphone.getIsatphoneId().equals("")){
	            	sqlStr += " AND ISATPHONE_ID =CONVERT(FLOAT,?)";
	            }else{
	            	sqlStr += " AND CONVERT(FLOAT,?) =0 ";
	            }
//            sqlArgs.add(amsYjIsatphone.getIsatphoneName());
//            sqlArgs.add(amsYjIsatphone.getIsatphoneName());
//            sqlArgs.add(amsYjIsatphone.getIsatphoneType());
//            sqlArgs.add(amsYjIsatphone.getIsatphoneType());
//            sqlArgs.add(amsYjIsatphone.getIsatphoneModel());
//            sqlArgs.add(amsYjIsatphone.getIsatphoneModel());
            sqlArgs.add(amsYjIsatphone.getIsatphoneAddress());
            sqlArgs.add(amsYjIsatphone.getIsatphoneAddress());
//            sqlArgs.add(amsYjIsatphone.getTel());
//            sqlArgs.add(amsYjIsatphone.getTel());
//            sqlArgs.add(amsYjIsatphone.getBuyingTime());
//            sqlArgs.add(amsYjIsatphone.getBuyingTime());
//            sqlArgs.add(amsYjIsatphone.getCost());
//            sqlArgs.add(amsYjIsatphone.getCost());
//            sqlArgs.add(amsYjIsatphone.getUsedNumber());
//            sqlArgs.add(amsYjIsatphone.getUsedNumber());
            sqlArgs.add(amsYjIsatphone.getOrganizationId());
            sqlArgs.add(amsYjIsatphone.getOrganizationId());
//            sqlArgs.add(amsYjIsatphone.getCreationDate());
//            sqlArgs.add(amsYjIsatphone.getCreationDate());
//            sqlArgs.add(amsYjIsatphone.getCreateUser());
//            sqlArgs.add(amsYjIsatphone.getCreateUser());
//            sqlArgs.add(amsYjIsatphone.getLastUpdateDate());
//            sqlArgs.add(amsYjIsatphone.getLastUpdateDate());
//            sqlArgs.add(amsYjIsatphone.getLastUpdateUser());
//            sqlArgs.add(amsYjIsatphone.getLastUpdateUser());
//            sqlArgs.add(amsYjIsatphone.getResourceId());
//            sqlArgs.add(amsYjIsatphone.getResourceId());
            sqlArgs.add(amsYjIsatphone.getIsatphoneId());
              
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (Exception ex) {
			ex.printStackTrace();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}