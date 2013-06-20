package com.sino.ams.yj.model;

import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.ams.yj.dto.AmsYjDrillDTO;

/**
 * <p>Title: AmsYjDrillModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjDrillModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ���������ά��
 */

public class AmsYjDrillModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ����������� AMS_YJ_DRILL ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjDrillDTO ���β���������
     */
    public AmsYjDrillModel(SfUserDTO userAccount, AmsYjDrillDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����Ӧ����������� AMS_YJ_DRILL���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
                    + " AMS_YJ_DRILL("
                    + " DRILL_ID,"
                    + " DRILL_TYPE,"
                    + " DRILL_NATURE,"
                    + " DRILL_NAME,"
                    + " DRILL_DATE,"    //
                    + " DRILL_ADDRESS,"
                    + " PEOPLE_QUALITY,"
                    + " EQUIPMENT_QUANTITY,"
                    + " PLAN1,"
                    + " QUESTION,"
                    + " IS_PERFECT,"
                    + " PLAN_DATE,"       //
                    + " REMARK,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER"
                    + ") VALUES ("
                    + " CONVERT(FLOAT,?), ?, ?, ?, ?, ?, CONVERT(FLOAT,?), ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";

            sqlArgs.add(amsYjDrill.getDrillId());
            sqlArgs.add(amsYjDrill.getDrillType());
            sqlArgs.add(amsYjDrill.getDrillNature());         
            sqlArgs.add(amsYjDrill.getDrillName());
            if(!amsYjDrill.getDrillDate().getCalendarValue().equals("")){
            	sqlArgs.add(amsYjDrill.getDrillDate());
            }else{
            	sqlArgs.add(null);
            }
            sqlArgs.add(amsYjDrill.getDrillAddress());
            sqlArgs.add(amsYjDrill.getPeopleQuality());   //
            sqlArgs.add(amsYjDrill.getEquipmentQuantity());
            sqlArgs.add(amsYjDrill.getPlan1());
            sqlArgs.add(amsYjDrill.getQuestion());
            sqlArgs.add(amsYjDrill.getIsPerfect());
            if(!amsYjDrill.getPlanDate().getCalendarValue().equals("")){
            	 sqlArgs.add(amsYjDrill.getPlanDate());
            }else{
            	 sqlArgs.add(null);
            } 
            sqlArgs.add(amsYjDrill.getRemark());
            sqlArgs.add(amsYjDrill.getOrganizationId());
            sqlArgs.add(sfUser.getUserId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ����������� AMS_YJ_DRILL���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) dtoParameter;
            String sqlStr = "UPDATE AMS_YJ_DRILL"
                    + " SET"
                    + " DRILL_TYPE = ?,"
                    + " DRILL_NATURE = ?,"
                    + " DRILL_NAME = ?,"
                    + " DRILL_DATE = ?,"
                    + " DRILL_ADDRESS = ?,"
                    + " PEOPLE_QUALITY = CONVERT(FLOAT,?),"
                    + " EQUIPMENT_QUANTITY = ?,"
                    + " PLAN1 = ?,"
                    + " QUESTION = ?,"
                    + " IS_PERFECT = ?,"
                    + " PLAN_DATE = ?,"
                    + " REMARK = ?,"
                    + " ORGANIZATION_ID = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " LAST_UPDATE_USER = ?"
                    + " WHERE"
                    + " DRILL_ID = CONVERT(FLOAT,?)";

            sqlArgs.add(amsYjDrill.getDrillType());
            sqlArgs.add(amsYjDrill.getDrillNature());
            sqlArgs.add(amsYjDrill.getDrillName());
            if(!amsYjDrill.getDrillDate().getCalendarValue().equals("")){
            	sqlArgs.add(amsYjDrill.getDrillDate());
            }else{
            	sqlArgs.add(null);
            }
            sqlArgs.add(amsYjDrill.getDrillAddress());
            sqlArgs.add(amsYjDrill.getPeopleQuality());
            sqlArgs.add(amsYjDrill.getEquipmentQuantity());
            sqlArgs.add(amsYjDrill.getPlan1());
            sqlArgs.add(amsYjDrill.getQuestion());
            sqlArgs.add(amsYjDrill.getIsPerfect());
            if(!amsYjDrill.getPlanDate().getCalendarValue().equals("")){
           	 sqlArgs.add(amsYjDrill.getPlanDate());
           }else{
           	 sqlArgs.add(null);
           } 
            sqlArgs.add(amsYjDrill.getRemark());
            sqlArgs.add(amsYjDrill.getOrganizationId());
            sqlArgs.add(sfUser.getUserId());
            sqlArgs.add(amsYjDrill.getDrillId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ����������� AMS_YJ_DRILL����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_YJ_DRILL"
                + " WHERE"
                + " DRILL_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjDrill.getDrillId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ����������� AMS_YJ_DRILL������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " DRILL_ID,"
                + " DRILL_TYPE,"
                + " DRILL_NATURE,"
                + " DRILL_NAME,"
                + " DRILL_DATE,"
                + " DRILL_ADDRESS,"
                + " PEOPLE_QUALITY,"
                + " EQUIPMENT_QUANTITY,"
                + " PLAN1,"
                + " QUESTION,"
                + " IS_PERFECT,"
                + " PLAN_DATE,"
                + " REMARK,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATE_USER,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_USER"
                + " FROM"
                + " AMS_YJ_DRILL"
                + " WHERE"
                + " DRILL_ID = CONVERT(FLOAT,?)";
        sqlArgs.add(amsYjDrill.getDrillId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ����������� AMS_YJ_DRILL����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " DRILL_ID,"
                    + " DRILL_TYPE,"
                    + " DRILL_NATURE,"
                    + " DRILL_NAME,"
                    + " DRILL_DATE,"
                    + " DRILL_ADDRESS,"
                    + " PEOPLE_QUALITY,"
                    + " EQUIPMENT_QUANTITY,"
                    + " PLAN1,"
                    + " QUESTION,"
                    + " IS_PERFECT,"
                    + " PLAN_DATE,"
                    + " REMARK,"
                    + " ORGANIZATION_ID,"
                    + " CREATION_DATE,"
                    + " CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " LAST_UPDATE_USER"
                    + " FROM"
                    + " AMS_YJ_DRILL"
                    + " WHERE"
                    + " (? IS NULL OR DRILL_ID LIKE ?)"
                    + " AND (? IS NULL OR DRILL_TYPE LIKE ?)"
                    + " AND (? IS NULL OR DRILL_NATURE LIKE ?)"
                    + " AND (? IS NULL OR DRILL_NAME LIKE ?)"
                    + " AND (? IS NULL OR DRILL_DATE LIKE ?)"
                    + " AND (? IS NULL OR DRILL_ADDRESS LIKE ?)"
                    + " AND (? IS NULL OR PEOPLE_QUALITY LIKE ?)"
                    + " AND (? IS NULL OR EQUIPMENT_QUANTITY LIKE ?)"
                    + " AND (? IS NULL OR PLAN LIKE ?)"
                    + " AND (? IS NULL OR QUESTION LIKE ?)"
                    + " AND (? IS NULL OR IS_PERFECT LIKE ?)"
                    + " AND (? IS NULL OR PLAN_DATE LIKE ?)"
                    + " AND (? IS NULL OR REMARK LIKE ?)"
                    + " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)";
            sqlArgs.add(amsYjDrill.getDrillId());
            sqlArgs.add(amsYjDrill.getDrillId());
            sqlArgs.add(amsYjDrill.getDrillType());
            sqlArgs.add(amsYjDrill.getDrillType());
            sqlArgs.add(amsYjDrill.getDrillNature());
            sqlArgs.add(amsYjDrill.getDrillNature());
            sqlArgs.add(amsYjDrill.getDrillName());
            sqlArgs.add(amsYjDrill.getDrillName());
            sqlArgs.add(amsYjDrill.getDrillDate());
            sqlArgs.add(amsYjDrill.getDrillDate());
            sqlArgs.add(amsYjDrill.getDrillAddress());
            sqlArgs.add(amsYjDrill.getDrillAddress());
            sqlArgs.add(amsYjDrill.getPeopleQuality());
            sqlArgs.add(amsYjDrill.getPeopleQuality());
            sqlArgs.add(amsYjDrill.getEquipmentQuantity());
            sqlArgs.add(amsYjDrill.getEquipmentQuantity());
            sqlArgs.add(amsYjDrill.getPlan1());
            sqlArgs.add(amsYjDrill.getPlan1());
            sqlArgs.add(amsYjDrill.getQuestion());
            sqlArgs.add(amsYjDrill.getQuestion());
            sqlArgs.add(amsYjDrill.getIsPerfect());
            sqlArgs.add(amsYjDrill.getIsPerfect());
            sqlArgs.add(amsYjDrill.getPlanDate());
            sqlArgs.add(amsYjDrill.getPlanDate());
            sqlArgs.add(amsYjDrill.getRemark());
            sqlArgs.add(amsYjDrill.getRemark());
            sqlArgs.add(amsYjDrill.getOrganizationId());
            sqlArgs.add(amsYjDrill.getOrganizationId());
            sqlArgs.add(amsYjDrill.getCreationDate());
            sqlArgs.add(amsYjDrill.getCreationDate());
            sqlArgs.add(amsYjDrill.getCreateUser());
            sqlArgs.add(amsYjDrill.getCreateUser());
            sqlArgs.add(amsYjDrill.getLastUpdateDate());
            sqlArgs.add(amsYjDrill.getLastUpdateDate());
            sqlArgs.add(amsYjDrill.getLastUpdateUser());
            sqlArgs.add(amsYjDrill.getLastUpdateUser());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ����������� AMS_YJ_DRILLҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) dtoParameter;
            String sqlStr = "SELECT "
                    + " dbo.APP_GET_ORGNIZATION_NAME(AYD.ORGANIZATION_ID) ORGANIZATION_NAME,"
                    + " DRILL_ID,"
                    + " DRILL_TYPE,"
                    + " DRILL_NATURE,"
                    + " DRILL_NAME,"
                    + " DRILL_DATE,"
                    + " DRILL_ADDRESS,"
                    + " PEOPLE_QUALITY,"
                    + " EQUIPMENT_QUANTITY,"
                    + " PLAN1,"
                    + " QUESTION,"
                    + " IS_PERFECT,"
                    + " PLAN_DATE,"
                    + " REMARK,"
                    + " CREATION_DATE,"
                    + " dbo.APP_GET_USER_NAME(AYD.CREATE_USER) CREATE_USER,"
                    + " LAST_UPDATE_DATE,"
                    + " dbo.APP_GET_USER_NAME(AYD.LAST_UPDATE_USER) LAST_UPDATE_USER"
                    + " FROM"
                    + " AMS_YJ_DRILL AYD"
                    + " WHERE"
//                    + " (? IS NULL OR DRILL_ID LIKE ?)"
//                    + " AND (? IS NULL OR DRILL_TYPE LIKE ?)"
//                    + " AND (? IS NULL OR DRILL_NATURE LIKE ?)"
                    + " ("+SyBaseSQLUtil.isNull()+" OR DRILL_NAME LIKE ?)"     
                    + " AND (? = NULL OR ? = '' OR ? >= DRILL_DATE)"
                    + " AND (? = NULL OR ? = '' OR ? <= DRILL_DATE)"
                    + " AND ("+SyBaseSQLUtil.isNull()+" OR DRILL_ADDRESS LIKE ?)"  
//                    + " AND (? IS NULL OR PEOPLE_QUALITY LIKE ?)"
//                    + " AND (? IS NULL OR EQUIPMENT_QUANTITY LIKE ?)"
//                    + " AND (? IS NULL OR PLAN LIKE ?)"
//                    + " AND (? IS NULL OR QUESTION LIKE ?)"
//                    + " AND (? IS NULL OR IS_PERFECT LIKE ?)"
//                    + " AND (? IS NULL OR PLAN_DATE LIKE ?)"
//                    + " AND (? IS NULL OR REMARK LIKE ?)"
                    + " AND (?=-1 OR ORGANIZATION_ID = ?)"  
//                    + " AND (? IS NULL OR CREATION_DATE LIKE ?)"
//                    + " AND (? IS NULL OR CREATE_USER LIKE ?)"
//                    + " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
//                    + " AND (? IS NULL OR LAST_UPDATE_USER LIKE ?)"
                    + " ORDER BY \n" +                           //2011-4-14 ���������
                    "ORGANIZATION_ID,\n" +
                    "DRILL_DATE";
//            sqlArgs.add(amsYjDrill.getDrillId());
//            sqlArgs.add(amsYjDrill.getDrillId());
//            sqlArgs.add(amsYjDrill.getDrillType());
//            sqlArgs.add(amsYjDrill.getDrillType());
//            sqlArgs.add(amsYjDrill.getDrillNature());
//            sqlArgs.add(amsYjDrill.getDrillNature());
            sqlArgs.add(amsYjDrill.getDrillName());
            sqlArgs.add(amsYjDrill.getDrillName());
            sqlArgs.add(amsYjDrill.getStartDate());
            sqlArgs.add(amsYjDrill.getStartDate());
            sqlArgs.add(amsYjDrill.getStartDate());
            sqlArgs.add(amsYjDrill.getEndDate());
            sqlArgs.add(amsYjDrill.getEndDate());
            sqlArgs.add(amsYjDrill.getEndDate());
            sqlArgs.add(amsYjDrill.getDrillAddress());
            sqlArgs.add(amsYjDrill.getDrillAddress());
//            sqlArgs.add(amsYjDrill.getPeopleQuality());
//            sqlArgs.add(amsYjDrill.getPeopleQuality());
//            sqlArgs.add(amsYjDrill.getEquipmentQuantity());
//            sqlArgs.add(amsYjDrill.getEquipmentQuantity());
//            sqlArgs.add(amsYjDrill.getPlan1());
//            sqlArgs.add(amsYjDrill.getPlan1());
//            sqlArgs.add(amsYjDrill.getQuestion());
//            sqlArgs.add(amsYjDrill.getQuestion());
//            sqlArgs.add(amsYjDrill.getIsPerfect());
//            sqlArgs.add(amsYjDrill.getIsPerfect());
//            sqlArgs.add(amsYjDrill.getPlanDate());
//            sqlArgs.add(amsYjDrill.getPlanDate());
//            sqlArgs.add(amsYjDrill.getRemark());
//            sqlArgs.add(amsYjDrill.getRemark());
            sqlArgs.add(amsYjDrill.getOrganizationId());
            sqlArgs.add(amsYjDrill.getOrganizationId());
//            sqlArgs.add(amsYjDrill.getCreationDate());
//            sqlArgs.add(amsYjDrill.getCreationDate());
//            sqlArgs.add(amsYjDrill.getCreateUser());
//            sqlArgs.add(amsYjDrill.getCreateUser());
//            sqlArgs.add(amsYjDrill.getLastUpdateDate());
//            sqlArgs.add(amsYjDrill.getLastUpdateDate());
//            sqlArgs.add(amsYjDrill.getLastUpdateUser());
//            sqlArgs.add(amsYjDrill.getLastUpdateUser());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}