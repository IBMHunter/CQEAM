package com.sino.soa.td.srv.projectInfo.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.soa.td.srv.projectInfo.dto.TDSrvProjectInfoDTO;

/**
 * <p>Title: SrvProjectInfoModel</p>
 * <p>Description:�����Զ�����SQL��������SrvProjectInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzp
 * @version 1.0
 */

public class TDSrvProjectInfoModel extends AMSSQLProducer {

    /**
     * ���ܣ���Ŀ��Ϣ���� SRV_PROJECT_INFO ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvProjectInfoDTO ���β���������
     */
    public TDSrvProjectInfoModel(SfUserDTO userAccount, TDSrvProjectInfoDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ�������Ŀ��Ϣ���� SRV_PROJECT_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            TDSrvProjectInfoDTO srvProjectInfo = (TDSrvProjectInfoDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
				+ " ETS_PA_PROJECTS_ALL("
				+ " PROJECT_ID,"
				+ " NAME,"
				+ " SEGMENT1,"
				+ " PROJECT_TYPE,"
				+ " PROJECT_STATUS_CODE,"
				+ " START_DATE,"
				+ " COMPLETION_DATE,"
				+ " ENABLED_FLAG,"
				+ " SOURCE,"
				+ " CREATION_DATE," 	
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY," 
				+ " MIS_PROJECT_ID,"
				+ " ORGANIZATION_ID,"
				+ " PROJECT_CLASS,"
				+ " DESCRIPTION,"
				+ " PROJECT_MANAGER,"
				+ " PM_PROJECT_REFERENCE,"
				+ " PM_PRODUCT_CODE"
				+ ") VALUES ("
				+ " NEWID(), ?, ?, ?, ?, ?, ?, ?, ?, ?" +
						", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
						")";
		
			sqlArgs.add(srvProjectInfo.getName());
			sqlArgs.add(srvProjectInfo.getSegment1());
			sqlArgs.add(srvProjectInfo.getProjectType());
			sqlArgs.add(srvProjectInfo.getProjectStatusCode());
			sqlArgs.add(srvProjectInfo.getStartDate());
			sqlArgs.add(srvProjectInfo.getCompletionDate());
			sqlArgs.add(srvProjectInfo.getEnabledFlag());
			sqlArgs.add("TDMIS"); 
			sqlArgs.add(srvProjectInfo.getCreationDate());
			sqlArgs.add(userAccount.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getLastUpdateDate());
			sqlArgs.add(userAccount.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getMisProjectId());
			sqlArgs.add(srvProjectInfo.getOrganizationId());
			sqlArgs.add(srvProjectInfo.getProjectClass());
			sqlArgs.add(srvProjectInfo.getDescription());
			sqlArgs.add(srvProjectInfo.getProjectManager());
			sqlArgs.add(srvProjectInfo.getPmProjectReference());
			sqlArgs.add(srvProjectInfo.getPmProductCode());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�������Ŀ��Ϣ���� SRV_PROJECT_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            TDSrvProjectInfoDTO srvProjectInfo = (TDSrvProjectInfoDTO) dtoParameter;
            String sqlStr = "UPDATE ETS_PA_PROJECTS_ALL"
				+ " SET"
				+ " NAME = ?,"
				//+ " PROJECT_ID = ?,"
				+ " PROJECT_TYPE = ?,"
				+ " PROJECT_STATUS_CODE = ?,"
				+ " START_DATE = ?,"
				+ " COMPLETION_DATE = ?,"
				+ " ENABLED_FLAG = ?,"
//				+ " SOURCE = ?,"
//				+ " CREATION_DATE = ?,"
//
//				+ " CREATED_BY = ?,"
				+ " LAST_UPDATE_DATE = ?,"
				+ " LAST_UPDATE_BY = ?," 
				
//				+ " MIS_PROJECT_ID = ?,"
//				+ " ORGANIZATION_ID = ?,"
				//	+	""
				+ " PROJECT_CLASS = ?,"
				+ " DESCRIPTION = ?,"
				+ " PROJECT_MANAGER = ?,"
				+ " PM_PROJECT_REFERENCE = ?,"
				+ " PM_PRODUCT_CODE = ?"
				+ " WHERE"
				+ " SEGMENT1 = ?";
		
			sqlArgs.add(srvProjectInfo.getName());
			//sqlArgs.add(srvProjectInfo.getProjectId());
			sqlArgs.add(srvProjectInfo.getProjectType());
			sqlArgs.add(srvProjectInfo.getProjectStatusCode());
			sqlArgs.add(srvProjectInfo.getStartDate());
			sqlArgs.add(srvProjectInfo.getCompletionDate());
			sqlArgs.add(srvProjectInfo.getEnabledFlag());
//			sqlArgs.add("TDMIS");
//			sqlArgs.add(srvProjectInfo.getCreationDate());
//			sqlArgs.add(sfUser.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getLastUpdateDate());
			sqlArgs.add(userAccount.getCreatedBy());
//			sqlArgs.add(srvProjectInfo.getMisProjectId());
//			sqlArgs.add(srvProjectInfo.getOrganizationId());
			sqlArgs.add(srvProjectInfo.getProjectClass());
			sqlArgs.add(srvProjectInfo.getDescription());
			sqlArgs.add(srvProjectInfo.getProjectManager());
			sqlArgs.add(srvProjectInfo.getPmProjectReference());
			sqlArgs.add(srvProjectInfo.getPmProductCode());
			sqlArgs.add(srvProjectInfo.getSegment1());
			
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�������Ŀ��Ϣ���� SRV_PROJECT_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        TDSrvProjectInfoDTO srvProjectInfo = (TDSrvProjectInfoDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " ETS_PA_PROJECTS_ALL";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�������Ŀ��Ϣ���� SRV_PROJECT_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		TDSrvProjectInfoDTO srvProjectInfo = (TDSrvProjectInfoDTO) dtoParameter;
		String sqlStr = "SELECT "
			+ " PROJECT_ID,"
			+ " NAME,"
			+ " SEGMENT1,"
			+ " PROJECT_TYPE,"
			+ " PROJECT_STATUS_CODE,"
			+ " START_DATE,"
			+ " COMPLETION_DATE,"
			+ " ENABLED_FLAG,"
			+ " SOURCE,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " MIS_PROJECT_ID,"
			+ " ORGANIZATION_ID,"
			+ " PROJECT_CLASS,"
			+ " DESCRIPTION,"
			+ " PROJECT_MANAGER,"
			+ " PM_PROJECT_REFERENCE,"
			+ " PM_PRODUCT_CODE"
			+ " FROM"
			+ " ETS_PA_PROJECTS_ALL"
			+ " WHERE"
			+ " PROJECT_ID = ?";
		sqlArgs.add(srvProjectInfo.getProjectId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
    /**
     * ���ܣ�����Զ�������Ŀ��Ϣ���� SRV_PROJECT_INFO����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			TDSrvProjectInfoDTO srvProjectInfo = (TDSrvProjectInfoDTO) dtoParameter;
			String sqlStr = "SELECT "
				+ " PROJECT_ID,"
				+ " NAME,"
				+ " SEGMENT1,"
				+ " PROJECT_TYPE,"
				+ " PROJECT_STATUS_CODE,"
				+ " START_DATE,"
				+ " COMPLETION_DATE,"
				+ " ENABLED_FLAG,"
				+ " SOURCE,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY,"
				+ " MIS_PROJECT_ID,"
				+ " ORGANIZATION_ID,"
				+ " PROJECT_CLASS,"
				+ " DESCRIPTION,"
				+ " PROJECT_MANAGER,"
				+ " PM_PROJECT_REFERENCE,"
				+ " PM_PRODUCT_CODE"
				+ " FROM"
				+ " ETS_PA_PROJECTS_ALL"
				+ " WHERE"
				+ " (? IS NULL OR PROJECT_ID LIKE ?)"
				+ " AND (? IS NULL OR NAME LIKE ?)"
				+ " AND (? IS NULL OR SEGMENT1 LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_TYPE LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_STATUS_CODE LIKE ?)"
				+ " AND (? IS NULL OR START_DATE LIKE ?)"
				+ " AND (? IS NULL OR COMPLETION_DATE LIKE ?)"
				+ " AND (? IS NULL OR ENABLED_FLAG LIKE ?)"
				+ " AND (? IS NULL OR SOURCE LIKE ?)"
				+ " AND (? IS NULL OR CREATION_DATE LIKE ?)"
				+ " AND (? IS NULL OR CREATED_BY LIKE ?)"
				+ " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
				+ " AND (? IS NULL OR LAST_UPDATE_BY LIKE ?)"
				+ " AND (? IS NULL OR MIS_PROJECT_ID LIKE ?)"
				+ " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_CLASS LIKE ?)"
				+ " AND (? IS NULL OR DESCRIPTION LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_MANAGER LIKE ?)"
				+ " AND (? IS NULL OR PM_PROJECT_REFERENCE LIKE ?)"
				+ " AND (? IS NULL OR PM_PRODUCT_CODE LIKE ?)";
			sqlArgs.add(srvProjectInfo.getProjectId());
			sqlArgs.add(srvProjectInfo.getProjectId());
			sqlArgs.add(srvProjectInfo.getName());
			sqlArgs.add(srvProjectInfo.getName());
			sqlArgs.add(srvProjectInfo.getSegment1());
			sqlArgs.add(srvProjectInfo.getSegment1());
			sqlArgs.add(srvProjectInfo.getProjectType());
			sqlArgs.add(srvProjectInfo.getProjectType());
			sqlArgs.add(srvProjectInfo.getProjectStatusCode());
			sqlArgs.add(srvProjectInfo.getProjectStatusCode());
			sqlArgs.add(srvProjectInfo.getStartDate());
			sqlArgs.add(srvProjectInfo.getStartDate());
			sqlArgs.add(srvProjectInfo.getCompletionDate());
			sqlArgs.add(srvProjectInfo.getCompletionDate());
			sqlArgs.add(srvProjectInfo.getEnabledFlag());
			sqlArgs.add(srvProjectInfo.getEnabledFlag());
			sqlArgs.add(srvProjectInfo.getSource());
			sqlArgs.add(srvProjectInfo.getSource());
			sqlArgs.add(srvProjectInfo.getCreationDate());
			sqlArgs.add(srvProjectInfo.getCreationDate());
			sqlArgs.add(srvProjectInfo.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getLastUpdateDate());
			sqlArgs.add(srvProjectInfo.getLastUpdateDate());
			sqlArgs.add(srvProjectInfo.getLastUpdateBy());
			sqlArgs.add(srvProjectInfo.getLastUpdateBy());
			sqlArgs.add(srvProjectInfo.getMisProjectId());
			sqlArgs.add(srvProjectInfo.getMisProjectId());
			sqlArgs.add(srvProjectInfo.getOrganizationId());
			sqlArgs.add(srvProjectInfo.getOrganizationId());
			sqlArgs.add(srvProjectInfo.getProjectClass());
			sqlArgs.add(srvProjectInfo.getProjectClass());
			sqlArgs.add(srvProjectInfo.getDescription());
			sqlArgs.add(srvProjectInfo.getDescription());
			sqlArgs.add(srvProjectInfo.getProjectManager());
			sqlArgs.add(srvProjectInfo.getProjectManager());
			sqlArgs.add(srvProjectInfo.getPmProjectReference());
			sqlArgs.add(srvProjectInfo.getPmProjectReference());
			sqlArgs.add(srvProjectInfo.getPmProductCode());
			sqlArgs.add(srvProjectInfo.getPmProductCode());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

    /**
     * ���ܣ�����Զ�������Ŀ��Ϣ���� SRV_PROJECT_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            TDSrvProjectInfoDTO srvProjectInfo = (TDSrvProjectInfoDTO) dtoParameter;
            String sqlStr = "SELECT "
				+ " PROJECT_ID,"
				+ " NAME,"
				+ " SEGMENT1,"
				+ " PROJECT_TYPE,"
				+ " PROJECT_STATUS_CODE,"
				+ " START_DATE,"
				+ " COMPLETION_DATE,"
				+ " ENABLED_FLAG,"
				+ " SOURCE,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY,"
				+ " MIS_PROJECT_ID,"
				+ " ORGANIZATION_ID,"
				+ " PROJECT_CLASS,"
				+ " DESCRIPTION,"
				+ " PROJECT_MANAGER,"
				+ " PM_PROJECT_REFERENCE,"
				+ " PM_PRODUCT_CODE"
				+ " FROM"
				+ " ETS_PA_PROJECTS_ALL"
				+ " WHERE"
				+ " (? IS NULL OR PROJECT_ID LIKE ?)"
				+ " AND (? IS NULL OR NAME LIKE ?)"
				+ " AND (? IS NULL OR SEGMENT1 LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_TYPE LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_STATUS_CODE LIKE ?)"
				+ " AND (? IS NULL OR START_DATE LIKE ?)"
				+ " AND (? IS NULL OR COMPLETION_DATE LIKE ?)"
				+ " AND (? IS NULL OR ENABLED_FLAG LIKE ?)"
				+ " AND (? IS NULL OR SOURCE LIKE ?)"
				+ " AND (? IS NULL OR CREATION_DATE LIKE ?)"
				+ " AND (? IS NULL OR CREATED_BY LIKE ?)"
				+ " AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
				+ " AND (? IS NULL OR LAST_UPDATE_BY LIKE ?)"
				+ " AND (? IS NULL OR MIS_PROJECT_ID LIKE ?)"
				+ " AND (? IS NULL OR ORGANIZATION_ID LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_CLASS LIKE ?)"
				+ " AND (? IS NULL OR DESCRIPTION LIKE ?)"
				+ " AND (? IS NULL OR PROJECT_MANAGER LIKE ?)"
				+ " AND (? IS NULL OR PM_PROJECT_REFERENCE LIKE ?)"
				+ " AND (? IS NULL OR PM_PRODUCT_CODE LIKE ?)";
			sqlArgs.add(srvProjectInfo.getProjectId());
			sqlArgs.add(srvProjectInfo.getProjectId());
			sqlArgs.add(srvProjectInfo.getName());
			sqlArgs.add(srvProjectInfo.getName());
			sqlArgs.add(srvProjectInfo.getSegment1());
			sqlArgs.add(srvProjectInfo.getSegment1());
			sqlArgs.add(srvProjectInfo.getProjectType());
			sqlArgs.add(srvProjectInfo.getProjectType());
			sqlArgs.add(srvProjectInfo.getProjectStatusCode());
			sqlArgs.add(srvProjectInfo.getProjectStatusCode());
			sqlArgs.add(srvProjectInfo.getStartDate());
			sqlArgs.add(srvProjectInfo.getStartDate());
			sqlArgs.add(srvProjectInfo.getCompletionDate());
			sqlArgs.add(srvProjectInfo.getCompletionDate());
			sqlArgs.add(srvProjectInfo.getEnabledFlag());
			sqlArgs.add(srvProjectInfo.getEnabledFlag());
			sqlArgs.add(srvProjectInfo.getSource());
			sqlArgs.add(srvProjectInfo.getSource());
			sqlArgs.add(srvProjectInfo.getCreationDate());
			sqlArgs.add(srvProjectInfo.getCreationDate());
			sqlArgs.add(srvProjectInfo.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getCreatedBy());
			sqlArgs.add(srvProjectInfo.getLastUpdateDate());
			sqlArgs.add(srvProjectInfo.getLastUpdateDate());
			sqlArgs.add(srvProjectInfo.getLastUpdateBy());
			sqlArgs.add(srvProjectInfo.getLastUpdateBy());
			sqlArgs.add(srvProjectInfo.getMisProjectId());
			sqlArgs.add(srvProjectInfo.getMisProjectId());
			sqlArgs.add(srvProjectInfo.getOrganizationId());
			sqlArgs.add(srvProjectInfo.getOrganizationId());
			sqlArgs.add(srvProjectInfo.getProjectClass());
			sqlArgs.add(srvProjectInfo.getProjectClass());
			sqlArgs.add(srvProjectInfo.getDescription());
			sqlArgs.add(srvProjectInfo.getDescription());
			sqlArgs.add(srvProjectInfo.getProjectManager());
			sqlArgs.add(srvProjectInfo.getProjectManager());
			sqlArgs.add(srvProjectInfo.getPmProjectReference());
			sqlArgs.add(srvProjectInfo.getPmProjectReference());
			sqlArgs.add(srvProjectInfo.getPmProductCode());
			sqlArgs.add(srvProjectInfo.getPmProductCode());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

    
    public SQLModel existsProjectModel(String projectNum) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT * FROM ETS_PA_PROJECTS_ALL SPI WHERE SPI.SEGMENT1 = ?";

        sqlArgs.add(projectNum);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

}