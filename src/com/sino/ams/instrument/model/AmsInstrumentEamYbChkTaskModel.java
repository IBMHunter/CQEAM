package com.sino.ams.instrument.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.instrument.dto.AmsInstrumentEamYbChkTaskDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsInstrumentEamYbChkTaskModel</p>
 * <p>Description:�����Ǳ������������</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yushibo
 * @version 1.0
 */
public class AmsInstrumentEamYbChkTaskModel extends AMSSQLProducer {

	/**
     * ���ܣ������Ǳ���������(EAM) EAM_YB_CHK_TASK ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentEamYbChkTaskDTO ���β���������
     */
	public AmsInstrumentEamYbChkTaskModel(BaseUserDTO userAccount, AmsInstrumentEamYbChkTaskDTO dtoParameter) {
		super(userAccount, dtoParameter);
		// TODO Auto-generated constructor stub
	}

	/**
     * ���ܣ�����Զ����ɼ��������(EAM) EAM_YB_CHK_TASKҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentEamYbChkTaskDTO dto = (AmsInstrumentEamYbChkTaskDTO) dtoParameter;
        
        String sqlStr = "";
        	   sqlStr = "SELECT" +
        	   			" EYCT.TASK_ID," +
        	   			" EYCT.TASK_NAME," +
        	   			" EYCT.REMARK," +
        	   			" EYCT.ORGANIZATION_ID," +
        	   			" EYCT.START_DATE," +
        	   			" EYCT.END_DATE," +
        	   			" EYCT.CREATED_BY," +
        	   			" EYCT.CREATION_DATE," +
        	   			" EYCT.LAST_UPDATE_BY," +
        	   			" EYCT.LAST_UPDATE_DATE," +
        	   			" AMS_PUB_PKG.GET_ORGNIZATION_NAME(EYCT.ORGANIZATION_ID) COMPANY" +
        	   			" FROM" +
        	   			" EAM_YB_CHK_TASK EYCT" + 
        	   			" WHERE" +
        	   			" ( " + SyBaseSQLUtil.isNull() + "  OR EYCT.TASK_NAME LIKE ?)";
        
        sqlArgs.add(dto.getTaskName());
        sqlArgs.add(dto.getTaskName());
        
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        
        return sqlModel;
    }
    
    /**
     * ���ܣ�����Զ����ɼ��ޱ�(EAM) EAM_YB_CHK_TASK������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentEamYbChkTaskDTO dto = (AmsInstrumentEamYbChkTaskDTO) dtoParameter;
        String sqlStr = "";
	        sqlStr = "SELECT" +
				" EYCT.TASK_ID," +
				" EYCT.TASK_NAME," +
				" EYCT.REMARK," +
				" EYCT.ORGANIZATION_ID," +
				" EYCT.START_DATE," +
				" EYCT.END_DATE," +
				" EYCT.CREATED_BY," +
				" EYCT.CREATION_DATE," +
				" EYCT.LAST_UPDATE_BY," +
				" EYCT.LAST_UPDATE_DATE," +
				" AMS_PUB_PKG.GET_ORGNIZATION_NAME(EYCT.ORGANIZATION_ID) COMPANY" +
				" FROM" +
				" EAM_YB_CHK_TASK EYCT" +
				" WHERE" +
				" EYCT.TASK_ID = ?";
	        
        sqlArgs.add(dto.getTaskId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ�����Զ����ɼ��������(EAM) EAM_YB_CHK_TASK���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        try {
	        AmsInstrumentEamYbChkTaskDTO dto = (AmsInstrumentEamYbChkTaskDTO) dtoParameter;
	        String sqlStr = "INSERT INTO "
	                + " EAM_YB_CHK_TASK("
	                + " TASK_ID,"
	                + " TASK_NAME,"
	                + " REMARK,"
	                + " ORGANIZATION_ID,"
	                + " START_DATE,"
	                + " END_DATE,"
	                + " CREATED_BY,"
	                + " CREATION_DATE"
	                + ") VALUES ("
	                + " NEWID() , ?, ?, ?, ?, ?, ?, GETDATE())";
	
	        sqlArgs.add(dto.getTaskName());
	        sqlArgs.add(dto.getRemark());
	        sqlArgs.add(userAccount.getOrganizationId());  
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getEndDate());
			sqlArgs.add(userAccount.getUserId());

	        sqlModel.setSqlStr(sqlStr);
	        sqlModel.setArgs(sqlArgs);
		} catch (CalendarException e) {
			e.printLog();
		}

        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɼ��������(EAM) EAM_YB_CHK_TASK���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentEamYbChkTaskDTO dto = (AmsInstrumentEamYbChkTaskDTO) dtoParameter;
        String sqlStr = "UPDATE EAM_YB_CHK_TASK"
                + " SET"
                + " TASK_NAME = ?"
                + " WHERE"
                + " TASK_ID = ?";

        sqlArgs.add(dto.getTaskName());
        sqlArgs.add(dto.getTaskId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ����TASK_NAME�Ƿ��ظ�
     * @param objName ��������
     * @return SQLModel
     */
    public SQLModel getNameHasBeenModel(String objName) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT 1 FROM EAM_YB_CHK_TASK EYCT " +
                " WHERE EYCT.TASK_NAME = ?  ";
        sqlArgs.add(objName);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
