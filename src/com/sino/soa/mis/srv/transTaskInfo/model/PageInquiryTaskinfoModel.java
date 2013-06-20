package com.sino.soa.mis.srv.transTaskInfo.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.config.SinoConfig;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.transTaskInfo.dto.PageInquiryTaskinfoDTO;

/**
 * <p>Title: SrvTaskinfoModel</p>
 * <p>Description:�����Զ�����SQL��������SrvTaskinfoModel��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzp
 * function:��ѯ��Ŀ������Ϣ����(��ҳ) 
 */

public class PageInquiryTaskinfoModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�SRV_TASKINFO ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SrvTaskinfoDTO ���β���������
	 */
	public PageInquiryTaskinfoModel(SfUserDTO userAccount, PageInquiryTaskinfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ�����SRV_TASKINFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " ZTE_PA_PROJECT_TASK_INFO("
				+ " ORG_ID,"
				+ " ORG_NAME,"
				+ " PROJECT_ID,"
				+ " SEGMENT1,"
				+ " TASK_ID,"
				+ " TASK_NUMBER,"
				+ " TASK_NAME,"
				+ " DESCRIPTION,"
				+ " WBS_LEVEL,"
				+ " TASK_MANAGER,"
				+ " PARENT_TASK_ID,"
				+ " PARENT_TASK_NUM," 
				+ " START_DATE,"
				+ " COMPLETION_DATE,"
				+ " SERVICE_TYPE_CODE,"
				+ " CHARGEABLE_FLAG,"
				+ " BILLABLE_FLAG,"
				+ " COST_FLAG,"
				+ " ATTRIBUTE1,"
				+ " ATTRIBUTE2,"
				+ " ATTRIBUTE3,"
				+ " ATTRIBUTE4,"
				+ " ATTRIBUTE5,"
				+ " ATTRIBUTE6,"
				+ " PM_PRODUCT_CODE,"
				+ " PM_TASK_REFERENCE,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATE_DATE"
				+ ") VALUES ("
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getOrgId()));
			sqlArgs.add(srvTaskinfo.getOrgName());
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getProjectId()));
			sqlArgs.add(srvTaskinfo.getSegment1());
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getTaskId()));
			sqlArgs.add(srvTaskinfo.getTaskNumber());
			sqlArgs.add(srvTaskinfo.getTaskName());
			sqlArgs.add(srvTaskinfo.getDescription());
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getWbsLevel()));
			sqlArgs.add(srvTaskinfo.getTaskManager());
			if(!srvTaskinfo.getParentTaskId().equals("")){
				sqlArgs.add(Integer.parseInt(srvTaskinfo.getParentTaskId()));
			}else{
				sqlArgs.add(0);
			}
			sqlArgs.add(srvTaskinfo.getParentTaskNum());
			sqlArgs.add(srvTaskinfo.getStartDate());
			sqlArgs.add(srvTaskinfo.getCompletionDate());
			sqlArgs.add(srvTaskinfo.getServiceTypeCode());
			sqlArgs.add(srvTaskinfo.getChargeableFlag());
			sqlArgs.add(srvTaskinfo.getBillableFlag());
			sqlArgs.add(srvTaskinfo.getCostFlag());
			sqlArgs.add(srvTaskinfo.getAttribute1());
			sqlArgs.add(srvTaskinfo.getAttribute2());
			sqlArgs.add(srvTaskinfo.getAttribute3());
			sqlArgs.add(srvTaskinfo.getAttribute4());
			sqlArgs.add(srvTaskinfo.getAttribute5());
			sqlArgs.add(srvTaskinfo.getAttribute6());
			sqlArgs.add(srvTaskinfo.getPmProductCode());
			sqlArgs.add(srvTaskinfo.getPmTaskReference());
			sqlArgs.add(srvTaskinfo.getCreationDate());
			sqlArgs.add(srvTaskinfo.getLastUpdateDate());
			
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SRV_TASKINFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
			String sqlStr = "UPDATE ZTE_PA_PROJECT_TASK_INFO"
				+ " SET"
				+ " ORG_ID = ?,"
				+ " ORG_NAME = ?,"
				+ " PROJECT_ID = ?,"
				+ " TASK_ID = ?,"
				+ " TASK_NAME = ?,"
				+ " DESCRIPTION = ?,"
				+ " WBS_LEVEL = ?,"
				+ " TASK_MANAGER = ?,"
				+ " PARENT_TASK_ID = ?,"
				+ " PARENT_TASK_NUM = ?,"
				
				+ " START_DATE = ?,"
				+ " COMPLETION_DATE = ?,"
				+ " SERVICE_TYPE_CODE = ?,"
				+ " CHARGEABLE_FLAG = ?,"
				+ " BILLABLE_FLAG = ?,"
				+ " COST_FLAG = ?,"
				+ " ATTRIBUTE1 = ?,"
				+ " ATTRIBUTE2 = ?,"
				+ " ATTRIBUTE3 = ?,"
				+ " ATTRIBUTE4 = ?,"
				+ " ATTRIBUTE5 = ?,"
				+ " ATTRIBUTE6 = ?,"
				+ " PM_PRODUCT_CODE = ?,"
				+ " PM_TASK_REFERENCE = ?,"
				+ " CREATION_DATE = ?,"
				+ " LAST_UPDATE_DATE = ? \n"
				+ " WHERE \n"
				+ " TASK_NUMBER = ?"
				+ " AND  SEGMENT1 = ?"
				;
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getOrgId()));
			sqlArgs.add(srvTaskinfo.getOrgName());
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getProjectId()));
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getTaskId()));
			sqlArgs.add(srvTaskinfo.getTaskName());
			sqlArgs.add(srvTaskinfo.getDescription());
			sqlArgs.add(Integer.parseInt(srvTaskinfo.getWbsLevel()));
			sqlArgs.add(srvTaskinfo.getTaskManager());
			if(!srvTaskinfo.getParentTaskId().equals("")){
				sqlArgs.add(Integer.parseInt(srvTaskinfo.getParentTaskId()));
			}else{
				sqlArgs.add(0);
			}
			sqlArgs.add(srvTaskinfo.getParentTaskNum());
			
			sqlArgs.add(srvTaskinfo.getStartDate());
			sqlArgs.add(srvTaskinfo.getCompletionDate());
			sqlArgs.add(srvTaskinfo.getServiceTypeCode());
			sqlArgs.add(srvTaskinfo.getChargeableFlag());
			sqlArgs.add(srvTaskinfo.getBillableFlag());
			sqlArgs.add(srvTaskinfo.getCostFlag());
			sqlArgs.add(srvTaskinfo.getAttribute1());
			sqlArgs.add(srvTaskinfo.getAttribute2());
			sqlArgs.add(srvTaskinfo.getAttribute3());
			sqlArgs.add(srvTaskinfo.getAttribute4());
			sqlArgs.add(srvTaskinfo.getAttribute5());
			sqlArgs.add(srvTaskinfo.getAttribute6());
			sqlArgs.add(srvTaskinfo.getPmProductCode());
			sqlArgs.add(srvTaskinfo.getPmTaskReference());
			sqlArgs.add(srvTaskinfo.getCreationDate());
			sqlArgs.add(srvTaskinfo.getLastUpdateDate());
			sqlArgs.add(srvTaskinfo.getTaskNumber());
			sqlArgs.add(srvTaskinfo.getSegment1());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SRV_TASKINFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
		String sqlStr = "DELETE FROM ZTE_PA_PROJECT_TASK_INFO"	;
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SRV_TASKINFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " ORG_ID,"
			+ " ORG_NAME,"
			+ " PROJECT_ID,"
			+ " SEGMENT1,"
			+ " TASK_ID,"
			+ " TASK_NUMBER,"
			+ " TASK_NAME,"
			+ " DESCRIPTION,"
			+ " WBS_LEVEL,"
			+ " TASK_MANAGER,"
			+ " PARENT_TASK_ID,"
			+ " PARENT_TASK_NUM,"
			+ " START_DATE,"
			+ " COMPLETION_DATE,"
			+ " SERVICE_TYPE_CODE,"
			+ " CHARGEABLE_FLAG,"
			+ " BILLABLE_FLAG,"
			+ " COST_FLAG,"
			+ " ATTRIBUTE1,"
			+ " ATTRIBUTE2,"
			+ " ATTRIBUTE3,"
			+ " ATTRIBUTE4,"
			+ " ATTRIBUTE5,"
			+ " ATTRIBUTE6,"
			+ " PM_PRODUCT_CODE,"
			+ " PM_TASK_REFERENCE,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_DATE"
			+ " WHERE"
			+ " ROWNUM = 1";
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SRV_TASKINFO����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " ORG_ID,"
				+ " ORG_NAME,"
				+ " PROJECT_ID,"
				+ " SEGMENT1,"
				+ " TASK_ID,"
				+ " TASK_NUMBER,"
				+ " TASK_NAME,"
				+ " DESCRIPTION,"
				+ " WBS_LEVEL,"
				+ " TASK_MANAGER,"
				+ " PARENT_TASK_ID,"
				+ " PARENT_TASK_NUMBER,"
				+ " START_DATE,"
				+ " COMPLETION_DATE,"
				+ " SERVICE_TYPE_CODE,"
				+ " CHARGEABLE_FLAG,"
				+ " BILLABLE_FLAG,"
				+ " COST_FLAG,"
				+ " ATTRIBUTE1,"
				+ " ATTRIBUTE2,"
				+ " ATTRIBUTE3,"
				+ " ATTRIBUTE4,"
				+ " ATTRIBUTE5,"
				+ " ATTRIBUTE6,"
				+ " PM_PRODUCT_CODE,"
				+ " PM_TASK_REFERENCE,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATE_DATE"
				+ " FROM"
				+ " ZTE_PA_PROJECT_TASK_INFO"
				+ " WHERE"
				+ " (? IS NULL OR ORG_ID LIKE ?)"
				+ " AND (? IS NULL OR ORG_NAME LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_ID LIKE ?)"
				+ " AND (? IS NULL OR SEGMENT1 LIKE ?)"
				+ " AND (? IS NULL OR TASK_ID LIKE ?)"
				+ " AND (? IS NULL OR TASK_NUMBER LIKE ?)"
				+ " AND (? IS NULL OR TASK_NAME LIKE ?)"
				+ " AND (? IS NULL OR DESCRIPTION LIKE ?)"
				+ " AND (? IS NULL OR WBS_LEVEL LIKE ?)"
				+ " AND (? IS NULL OR TASK_MANAGER LIKE ?)"
				+ " AND (? IS NULL OR PARENT_TASK_ID LIKE ?)"
				+ " AND (? IS NULL OR PARENT_TASK_NUM LIKE ?)"
				+ " AND (? IS NULL OR START_DATE LIKE ?)"
				+ " AND (? IS NULL OR COMPLETION_DATE LIKE ?)"
				+ " AND (? IS NULL OR SERVICE_TYPE_CODE LIKE ?)"
				+ " AND (? IS NULL OR CHARGEABLE_FLAG LIKE ?)"
				+ " AND (? IS NULL OR BILLABLE_FLAG LIKE ?)"
				+ " AND (? IS NULL OR COST_FLAG LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE1 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE2 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE3 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE4 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE5 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE6 LIKE ?)"
				+ " AND (? IS NULL OR PM_PRODUCT_CODE LIKE ?)"
				+ " AND (? IS NULL OR PM_TASK_REFERENCE LIKE ?)"
				+ " AND (? IS NULL OR CREATION_DATE LIKE ?)"
				+ " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)";
			sqlArgs.add(srvTaskinfo.getOrgId());
			sqlArgs.add(srvTaskinfo.getOrgId());
			sqlArgs.add(srvTaskinfo.getOrgName());
			sqlArgs.add(srvTaskinfo.getOrgName());
			sqlArgs.add(srvTaskinfo.getProjectId());
			sqlArgs.add(srvTaskinfo.getProjectId());
			sqlArgs.add(srvTaskinfo.getSegment1());
			sqlArgs.add(srvTaskinfo.getSegment1());
			sqlArgs.add(srvTaskinfo.getTaskId());
			sqlArgs.add(srvTaskinfo.getTaskId());
			sqlArgs.add(srvTaskinfo.getTaskNumber());
			sqlArgs.add(srvTaskinfo.getTaskNumber());
			sqlArgs.add(srvTaskinfo.getTaskName());
			sqlArgs.add(srvTaskinfo.getTaskName());
			sqlArgs.add(srvTaskinfo.getDescription());
			sqlArgs.add(srvTaskinfo.getDescription());
			sqlArgs.add(srvTaskinfo.getWbsLevel());
			sqlArgs.add(srvTaskinfo.getWbsLevel());
			sqlArgs.add(srvTaskinfo.getTaskManager());
			sqlArgs.add(srvTaskinfo.getTaskManager());
			sqlArgs.add(srvTaskinfo.getParentTaskId());
			sqlArgs.add(srvTaskinfo.getParentTaskId());
			sqlArgs.add(srvTaskinfo.getParentTaskNum());
			sqlArgs.add(srvTaskinfo.getParentTaskNum());
			sqlArgs.add(srvTaskinfo.getStartDate());
			sqlArgs.add(srvTaskinfo.getStartDate());
			sqlArgs.add(srvTaskinfo.getCompletionDate());
			sqlArgs.add(srvTaskinfo.getCompletionDate());
			sqlArgs.add(srvTaskinfo.getServiceTypeCode());
			sqlArgs.add(srvTaskinfo.getServiceTypeCode());
			sqlArgs.add(srvTaskinfo.getChargeableFlag());
			sqlArgs.add(srvTaskinfo.getChargeableFlag());
			sqlArgs.add(srvTaskinfo.getBillableFlag());
			sqlArgs.add(srvTaskinfo.getBillableFlag());
			sqlArgs.add(srvTaskinfo.getCostFlag());
			sqlArgs.add(srvTaskinfo.getCostFlag());
			sqlArgs.add(srvTaskinfo.getAttribute1());
			sqlArgs.add(srvTaskinfo.getAttribute1());
			sqlArgs.add(srvTaskinfo.getAttribute2());
			sqlArgs.add(srvTaskinfo.getAttribute2());
			sqlArgs.add(srvTaskinfo.getAttribute3());
			sqlArgs.add(srvTaskinfo.getAttribute3());
			sqlArgs.add(srvTaskinfo.getAttribute4());
			sqlArgs.add(srvTaskinfo.getAttribute4());
			sqlArgs.add(srvTaskinfo.getAttribute5());
			sqlArgs.add(srvTaskinfo.getAttribute5());
			sqlArgs.add(srvTaskinfo.getAttribute6());
			sqlArgs.add(srvTaskinfo.getAttribute6());
			sqlArgs.add(srvTaskinfo.getPmProductCode());
			sqlArgs.add(srvTaskinfo.getPmProductCode());
			sqlArgs.add(srvTaskinfo.getPmTaskReference());
			sqlArgs.add(srvTaskinfo.getPmTaskReference());
			sqlArgs.add(srvTaskinfo.getCreationDate());
			sqlArgs.add(srvTaskinfo.getCreationDate());
			sqlArgs.add(srvTaskinfo.getLastUpdateDate());
			sqlArgs.add(srvTaskinfo.getLastUpdateDate());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SRV_TASKINFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " ORG_ID,"
				+ " ORG_NAME,"
				+ " PROJECT_ID,"
				+ " SEGMENT1,"
				+ " TASK_ID,"
				+ " TASK_NUMBER,"
				+ " TASK_NAME,"
				+ " DESCRIPTION,"
				+ " WBS_LEVEL,"
				+ " TASK_MANAGER,"
				+ " PARENT_TASK_ID,"
				+ " PARENT_TASK_NUM,"
				+ " START_DATE,"
				+ " COMPLETION_DATE,"
				+ " SERVICE_TYPE_CODE,"
				+ " CHARGEABLE_FLAG,"
				+ " BILLABLE_FLAG,"
				+ " COST_FLAG,"
				+ " ATTRIBUTE1,"
				+ " ATTRIBUTE2,"
				+ " ATTRIBUTE3,"
				+ " ATTRIBUTE4,"
				+ " ATTRIBUTE5,"
				+ " ATTRIBUTE6,"
				+ " PM_PRODUCT_CODE,"
				+ " PM_TASK_REFERENCE,"
				+ " CREATION_DATE,"
				+ " LAST_UPDATE_DATE"
				+ " FROM"
				+ " ZTE_PA_PROJECT_TASK_INFO"
				+ " WHERE"
				+ " (? IS NULL OR ORG_ID LIKE ?)"
				+ " AND (? IS NULL OR ORG_NAME LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_ID LIKE ?)"
				+ " AND (? IS NULL OR SEGMENT1 LIKE ?)"
				+ " AND (? IS NULL OR TASK_ID LIKE ?)"
				+ " AND (? IS NULL OR TASK_NUMBER LIKE ?)"
				+ " AND (? IS NULL OR TASK_NAME LIKE ?)"
				+ " AND (? IS NULL OR DESCRIPTION LIKE ?)"
				+ " AND (? IS NULL OR WBS_LEVEL LIKE ?)"
				+ " AND (? IS NULL OR TASK_MANAGER LIKE ?)"
				+ " AND (? IS NULL OR PARENT_TASK_ID LIKE ?)"
				+ " AND (? IS NULL OR PARENT_TASK_NUM LIKE ?)"
				+ " AND (? IS NULL OR START_DATE LIKE ?)"
				+ " AND (? IS NULL OR COMPLETION_DATE LIKE ?)"
				+ " AND (? IS NULL OR SERVICE_TYPE_CODE LIKE ?)"
				+ " AND (? IS NULL OR CHARGEABLE_FLAG LIKE ?)"
				+ " AND (? IS NULL OR BILLABLE_FLAG LIKE ?)"
				+ " AND (? IS NULL OR COST_FLAG LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE1 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE2 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE3 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE4 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE5 LIKE ?)"
				+ " AND (? IS NULL OR ATTRIBUTE6 LIKE ?)"
				+ " AND (? IS NULL OR PM_PRODUCT_CODE LIKE ?)"
				+ " AND (? IS NULL OR PM_TASK_REFERENCE LIKE ?)"
				+ " AND (? IS NULL OR CREATION_DATE LIKE ?)"
				+ " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)";
			sqlArgs.add(srvTaskinfo.getOrgId());
			sqlArgs.add(srvTaskinfo.getOrgId());
			sqlArgs.add(srvTaskinfo.getOrgName());
			sqlArgs.add(srvTaskinfo.getOrgName());
			sqlArgs.add(srvTaskinfo.getProjectId());
			sqlArgs.add(srvTaskinfo.getProjectId());
			sqlArgs.add(srvTaskinfo.getSegment1());
			sqlArgs.add(srvTaskinfo.getSegment1());
			sqlArgs.add(srvTaskinfo.getTaskId());
			sqlArgs.add(srvTaskinfo.getTaskId());
			sqlArgs.add(srvTaskinfo.getTaskNumber());
			sqlArgs.add(srvTaskinfo.getTaskNumber());
			sqlArgs.add(srvTaskinfo.getTaskName());
			sqlArgs.add(srvTaskinfo.getTaskName());
			sqlArgs.add(srvTaskinfo.getDescription());
			sqlArgs.add(srvTaskinfo.getDescription());
			sqlArgs.add(srvTaskinfo.getWbsLevel());
			sqlArgs.add(srvTaskinfo.getWbsLevel());
			sqlArgs.add(srvTaskinfo.getTaskManager());
			sqlArgs.add(srvTaskinfo.getTaskManager());
			sqlArgs.add(srvTaskinfo.getParentTaskId());
			sqlArgs.add(srvTaskinfo.getParentTaskId());
			sqlArgs.add(srvTaskinfo.getParentTaskNum());
			sqlArgs.add(srvTaskinfo.getParentTaskNum());
			sqlArgs.add(srvTaskinfo.getStartDate());
			sqlArgs.add(srvTaskinfo.getStartDate());
			sqlArgs.add(srvTaskinfo.getCompletionDate());
			sqlArgs.add(srvTaskinfo.getCompletionDate());
			sqlArgs.add(srvTaskinfo.getServiceTypeCode());
			sqlArgs.add(srvTaskinfo.getServiceTypeCode());
			sqlArgs.add(srvTaskinfo.getChargeableFlag());
			sqlArgs.add(srvTaskinfo.getChargeableFlag());
			sqlArgs.add(srvTaskinfo.getBillableFlag());
			sqlArgs.add(srvTaskinfo.getBillableFlag());
			sqlArgs.add(srvTaskinfo.getCostFlag());
			sqlArgs.add(srvTaskinfo.getCostFlag());
			sqlArgs.add(srvTaskinfo.getAttribute1());
			sqlArgs.add(srvTaskinfo.getAttribute1());
			sqlArgs.add(srvTaskinfo.getAttribute2());
			sqlArgs.add(srvTaskinfo.getAttribute2());
			sqlArgs.add(srvTaskinfo.getAttribute3());
			sqlArgs.add(srvTaskinfo.getAttribute3());
			sqlArgs.add(srvTaskinfo.getAttribute4());
			sqlArgs.add(srvTaskinfo.getAttribute4());
			sqlArgs.add(srvTaskinfo.getAttribute5());
			sqlArgs.add(srvTaskinfo.getAttribute5());
			sqlArgs.add(srvTaskinfo.getAttribute6());
			sqlArgs.add(srvTaskinfo.getAttribute6());
			sqlArgs.add(srvTaskinfo.getPmProductCode());
			sqlArgs.add(srvTaskinfo.getPmProductCode());
			sqlArgs.add(srvTaskinfo.getPmTaskReference());
			sqlArgs.add(srvTaskinfo.getPmTaskReference());
			sqlArgs.add(srvTaskinfo.getCreationDate());
			sqlArgs.add(srvTaskinfo.getCreationDate());
			sqlArgs.add(srvTaskinfo.getLastUpdateDate());
			sqlArgs.add(srvTaskinfo.getLastUpdateDate());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}
	
	public SQLModel existsProjectTaskModel(String taskNumber , String projectNum){
		
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		PageInquiryTaskinfoDTO srvTaskinfo = (PageInquiryTaskinfoDTO)dtoParameter;
		String sqlStr ="SELECT * FROM ZTE_PA_PROJECT_TASK_INFO ST WHERE ST.TASK_NUMBER = ? AND ST.SEGMENT1= ?";
		sqlArgs.add(taskNumber);
		sqlArgs.add(projectNum);
		sqlModel.setArgs(sqlArgs);
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
		
	}
	

}