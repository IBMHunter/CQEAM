package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfWorkScheduleDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfWorkScheduleModel</p>
 * <p>Description:�����Զ�����SQL��������SfWorkScheduleModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class SfWorkScheduleModel extends BaseSQLProducer {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�����ʱ���ܱ� SF_WORK_SCHEDULE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfWorkScheduleDTO ���β���������
	 */
	public SfWorkScheduleModel(SfUserBaseDTO userAccount, SfWorkScheduleDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
	
			List sqlArgs = new ArrayList();
			SfWorkScheduleDTO sfWorkSchedule = (SfWorkScheduleDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " SF_WORK_SCHEDULE("
				+ " WORK_SCHEDULE_NAME,"
				+ " WORK_HOUR_ID,"
				+ " MEMO,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " HOLIDAY_NAME"
				+ ") VALUES ("
				+ " ?, ?, ?, ?, GETDATE(), ?, GETDATE(), ?)";
		
			sqlArgs.add(sfWorkSchedule.getWorkScheduleName());
			sqlArgs.add(sfWorkSchedule.getWorkHourId());
			sqlArgs.add(sfWorkSchedule.getMemo());
			sqlArgs.add(sfUser.getLoginName());
			sqlArgs.add(sfUser.getLoginName());
			sqlArgs.add(sfWorkSchedule.getHolidayName());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			SfWorkScheduleDTO sfWorkSchedule = (SfWorkScheduleDTO)dtoParameter;
			String sqlStr = "UPDATE SF_WORK_SCHEDULE"
				+ " SET"
				+ " WORK_SCHEDULE_NAME = ?,"
				+ " WORK_HOUR_ID = ?,"
				+ " MEMO = ?,"
				+ " LAST_UPDATED_BY = ?,"
				+ " LAST_UPDATE_DATE = GETDATE(),"
				+ " HOLIDAY_NAME = ?"
				+ " WHERE"
				+ " WORK_SCHEDULE_ID = ?";
		
			sqlArgs.add(sfWorkSchedule.getWorkScheduleName());
			sqlArgs.add(sfWorkSchedule.getWorkHourId());
			sqlArgs.add(sfWorkSchedule.getMemo());
			sqlArgs.add(sfUser.getLoginName());
			sqlArgs.add(sfWorkSchedule.getHolidayName());
			sqlArgs.add(sfWorkSchedule.getWorkScheduleId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfWorkScheduleDTO sfWorkSchedule = (SfWorkScheduleDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_WORK_SCHEDULE"
				+ " WHERE"
				+ " WORK_SCHEDULE_ID = ?";
			sqlArgs.add(sfWorkSchedule.getWorkScheduleId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfWorkScheduleDTO sfWorkSchedule = (SfWorkScheduleDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " WORK_SCHEDULE_ID,"
			+ " WORK_SCHEDULE_NAME,"
			+ " WORK_HOUR_ID,"
			+ " HOLIDAY_NAME"
			+ " FROM"
			+ " SF_WORK_SCHEDULE"
			+ " WHERE"
			+ " WORK_SCHEDULE_ID = ?";
		sqlArgs.add(sfWorkSchedule.getWorkScheduleId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			SfWorkScheduleDTO sfWorkSchedule = (SfWorkScheduleDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " WORK_SCHEDULE_ID,"
				+ " WORK_SCHEDULE_NAME,"
				+ " WORK_HOUR_ID,"
				+ " MEMO,"
				+ " CREATED_BY,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " HOLIDAY_NAME"
				+ " FROM"
				+ " SF_WORK_SCHEDULE"
				+ " WHERE"
				+ " (? <= 0 OR WORK_SCHEDULE_ID = ?)"
				+ " AND (? = '' OR WORK_SCHEDULE_NAME LIKE ?)"
				+ " AND (? <= 0 OR WORK_HOUR_ID = ?)"
				+ " AND (? = '' OR MEMO LIKE ?)"
				+ " AND (? <=0 OR CREATED_BY = ?)"
				+ " AND (? = '' OR CREATION_DATE LIKE ?)"
				+ " AND (? <=0 OR LAST_UPDATED_BY = ?)"
				+ " AND (? = '' OR LAST_UPDATE_DATE LIKE ?)"
				+ " AND (? = '' OR HOLIDAY_NAME LIKE ?)";
			sqlArgs.add(sfWorkSchedule.getWorkScheduleId());
			sqlArgs.add(sfWorkSchedule.getWorkScheduleId());
			sqlArgs.add(sfWorkSchedule.getWorkScheduleName());
			sqlArgs.add(sfWorkSchedule.getWorkScheduleName());
			sqlArgs.add(sfWorkSchedule.getWorkHourId());
			sqlArgs.add(sfWorkSchedule.getWorkHourId());
			sqlArgs.add(sfWorkSchedule.getMemo());
			sqlArgs.add(sfWorkSchedule.getMemo());
			sqlArgs.add(sfWorkSchedule.getCreatedBy());
			sqlArgs.add(sfWorkSchedule.getCreatedBy());
			sqlArgs.add(sfWorkSchedule.getCreationDate());
			sqlArgs.add(sfWorkSchedule.getCreationDate());
			sqlArgs.add(sfWorkSchedule.getLastUpdatedBy());
			sqlArgs.add(sfWorkSchedule.getLastUpdatedBy());
			sqlArgs.add(sfWorkSchedule.getLastUpdateDate());
			sqlArgs.add(sfWorkSchedule.getLastUpdateDate());
			sqlArgs.add(sfWorkSchedule.getHolidayName());
			sqlArgs.add(sfWorkSchedule.getHolidayName());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

    /**
     * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param userid user ID
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getWorkScheduleByUserModel(String userid) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
//            + " WORK_SCHEDULE_ID,"
//            + " WORK_SCHEDULE_NAME,"
//            + " WORK_HOUR_ID,"
//            + " HOLIDAY_NAME"
            + " SCH.*"
            + " FROM"
            + " SF_WORK_SCHEDULE SCH, SF_USER SU"
            + " WHERE"
            + " SU.USER_ID = ?"
            + " AND SCH.WORK_SCHEDULE_ID = SU.WORK_SCHEDULE_ID";
        sqlArgs.add(userid);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
	 * ���ܣ�����Զ����ɹ���ʱ���ܱ� SF_WORK_SCHEDULEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			String sqlStr = 
			   " SELECT"
				
			 + " SWH.WORKING_DATE,"
			 + " SWH.WORK_BEGIN_1,"
			 + " SWH.WORK_END_1,"
			 + " SWH.WORK_BEGIN_2,"
			 + " SWH.WORK_END_2,"
			 
			 + " SH.HOLIDAYS,"
			 
			 + " CONVERT(INT,SWS.WORK_SCHEDULE_ID) WORK_SCHEDULE_ID,"
			 + " SWS.WORK_SCHEDULE_NAME"
			 
			 + " FROM "
			 + " SF_WORK_HOUR SWH,"
			 + " SF_WORK_SCHEDULE SWS,"
			 + " SF_HOLIDAYS SH"
			 + " WHERE SWS.WORK_HOUR_ID = SWH.WORK_HOUR_ID" 
			 + " AND SWS.HOLIDAY_NAME = SH.NAME"
			 + " AND CONVERT(VARCHAR, SH.YEAR) = CONVERT(VARCHAR, YEAR(GETDATE()))";
			sqlModel.setSqlStr(sqlStr);

		return sqlModel;
	}

	/**
     * ���ܣ�ɾ��
     * @param ids IDs
     * @return SQLModel 
     */
    public SQLModel del(String[] ids){
	    String str = "";
		for (int i = 0; i < ids.length; i++) {
			str += ids[i]+",";
		}
		str = str.substring(0,str.length()-1);
    	SQLModel sqlModel = new SQLModel();
        String sqlStr = "DELETE" 
        		+ " FROM "
        		+ " SF_WORK_SCHEDULE"
        		+ " WHERE"
        		+ " WORK_SCHEDULE_ID IN("+str+")";
        sqlModel.setSqlStr(sqlStr);        
        return sqlModel;
    }
    
    /**
     * ���ܣ������б�
     * @return SQLModel SQLModel
     */
    public SQLModel getSelectOption(){

    	SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT"
        		+ " WORK_SCHEDULE_ID,"
        		+ " WORK_SCHEDULE_NAME"
        		+ " FROM "
        		+ " SF_WORK_SCHEDULE";
        sqlModel.setSqlStr(sqlStr);        
        return sqlModel;
    }
}