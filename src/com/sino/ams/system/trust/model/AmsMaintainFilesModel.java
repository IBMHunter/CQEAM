package com.sino.ams.system.trust.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.trust.dto.AmsMaintainFilesDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsMaintainFilesModel</p>
 * <p>Description:�����Զ�����SQL��������AmsMaintainFilesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsMaintainFilesModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ά��˾����ļ� AMS_MAINTAIN_FILES ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsMaintainFilesDTO ���β���������
	 */
	public AmsMaintainFilesModel(SfUserDTO userAccount, AmsMaintainFilesDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ����ɴ�ά��˾����ļ� AMS_MAINTAIN_FILES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " AMS_MAINTAIN_FILES("
			+ " SYSTEM_ID,"
			+ " FILE_DESCRIPTION,"
			+ " FILE_PATH,"
			+ " COMPANY_ID,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " FILE_NAME"
			+ ") VALUES ("
			+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?)";
		
		sqlArgs.add(amsMaintainFiles.getFileDescription());
		sqlArgs.add(amsMaintainFiles.getFilePath());
		sqlArgs.add(amsMaintainFiles.getCompanyId());
		sqlArgs.add(amsMaintainFiles.getCreatedBy());
		sqlArgs.add(amsMaintainFiles.getLastUpdateDate());
		sqlArgs.add(amsMaintainFiles.getLastUpdateBy());
		sqlArgs.add(amsMaintainFiles.getFileName());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɴ�ά��˾����ļ� AMS_MAINTAIN_FILES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
		String sqlStr = "UPDATE AMS_MAINTAIN_FILES"
			+ " SET"
			+ " FILE_DESCRIPTION = ?,"
			+ " FILE_PATH = ?,"
			+ " COMPANY_ID = ?,"
			+ " CREATION_DATE = ?,"
			+ " CREATED_BY = ?,"
			+ " LAST_UPDATE_DATE = ?,"
			+ " LAST_UPDATE_BY = ?,"
			+ " FILE_NAME = ?"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		
		sqlArgs.add(amsMaintainFiles.getFileDescription());
		sqlArgs.add(amsMaintainFiles.getFilePath());
		sqlArgs.add(amsMaintainFiles.getCompanyId());
		sqlArgs.add(amsMaintainFiles.getCreationDate());
		sqlArgs.add(amsMaintainFiles.getCreatedBy());
		sqlArgs.add(amsMaintainFiles.getLastUpdateDate());
		sqlArgs.add(amsMaintainFiles.getLastUpdateBy());
		sqlArgs.add(amsMaintainFiles.getFileName());
		sqlArgs.add(amsMaintainFiles.getSystemId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɴ�ά��˾����ļ� AMS_MAINTAIN_FILES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
			+ " AMS_MAINTAIN_FILES"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		sqlArgs.add(amsMaintainFiles.getSystemId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɴ�ά��˾����ļ� AMS_MAINTAIN_FILES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " FILE_DESCRIPTION,"
			+ " FILE_PATH,"
			+ " COMPANY_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " FILE_NAME"
			+ " FROM"
			+ " AMS_MAINTAIN_FILES"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		sqlArgs.add(amsMaintainFiles.getSystemId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɴ�ά��˾����ļ� AMS_MAINTAIN_FILES����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " FILE_DESCRIPTION,"
			+ " FILE_PATH,"
			+ " COMPANY_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " FILE_NAME"
			+ " FROM"
			+ " AMS_MAINTAIN_FILES"
			+ " WHERE"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR SYSTEM_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR FILE_DESCRIPTION LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR FILE_PATH LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR COMPANY_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR FILE_NAME LIKE ?)";
		sqlArgs.add(amsMaintainFiles.getSystemId());
		sqlArgs.add(amsMaintainFiles.getSystemId());
		sqlArgs.add(amsMaintainFiles.getFileDescription());
		sqlArgs.add(amsMaintainFiles.getFileDescription());
		sqlArgs.add(amsMaintainFiles.getFilePath());
		sqlArgs.add(amsMaintainFiles.getFilePath());
		sqlArgs.add(amsMaintainFiles.getCompanyId());
		sqlArgs.add(amsMaintainFiles.getCompanyId());
		sqlArgs.add(amsMaintainFiles.getCreationDate());
		sqlArgs.add(amsMaintainFiles.getCreationDate());
		sqlArgs.add(amsMaintainFiles.getCreatedBy());
		sqlArgs.add(amsMaintainFiles.getCreatedBy());
		sqlArgs.add(amsMaintainFiles.getLastUpdateDate());
		sqlArgs.add(amsMaintainFiles.getLastUpdateDate());
		sqlArgs.add(amsMaintainFiles.getLastUpdateBy());
		sqlArgs.add(amsMaintainFiles.getLastUpdateBy());
		sqlArgs.add(amsMaintainFiles.getFileName());
		sqlArgs.add(amsMaintainFiles.getFileName());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


    /**
     * ���ܣ�������������ֶ� companyId �����ѯ����SQL��
     * ����Զ��������ݴ�ά��˾����ļ� AMS_MAINTAIN_FILES��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param companyId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByCompanyIdModel(String  companyId){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
            + " SYSTEM_ID,"
            + " FILE_DESCRIPTION,"
            + " FILE_PATH,"
            + " CREATION_DATE,"
            + " CREATED_BY,"
            + " LAST_UPDATE_DATE,"
            + " LAST_UPDATE_BY,"
            + " FILE_NAME"
            + " FROM"
            + " AMS_MAINTAIN_FILES"
            + " WHERE"
            + " COMPANY_ID = ?";
        sqlArgs.add(companyId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey){
        SQLModel sqlModel = null;
        AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
        if(foreignKey.equals("companyId")){
            sqlModel = getDataByCompanyIdModel(amsMaintainFiles.getCompanyId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� companyId ��������ɾ��SQL��
     * ����Զ��������ݴ�ά��˾����ļ� AMS_MAINTAIN_FILES ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param companyId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByCompanyIdModel(String  companyId){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                        + " AMS_MAINTAIN_FILES"
                        + " WHERE"
                        + " COMPANY_ID = ?";
        sqlArgs.add(companyId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���������ֶ�ɾ������
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDeleteByForeignKeyModel(String foreignKey){
        SQLModel sqlModel = null;
        AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
        if(foreignKey.equals("companyId")){
            sqlModel = getDeleteByCompanyIdModel(amsMaintainFiles.getCompanyId());
        }
        return sqlModel;
    }
    
    /**
	 * ���ܣ�����Զ����ɴ�ά��˾����ļ� AMS_MAINTAIN_FILESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMaintainFilesDTO amsMaintainFiles = (AmsMaintainFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " FILE_DESCRIPTION,"
			+ " FILE_PATH,"
			+ " COMPANY_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " FILE_NAME"
			+ " FROM"
			+ " AMS_MAINTAIN_FILES"
			+ " WHERE"
			+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEM_ID LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_DESCRIPTION LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_PATH LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR COMPANY_ID LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR FILE_NAME LIKE ?)";
		sqlArgs.add(amsMaintainFiles.getSystemId());
		sqlArgs.add(amsMaintainFiles.getSystemId());
		sqlArgs.add(amsMaintainFiles.getFileDescription());
		sqlArgs.add(amsMaintainFiles.getFileDescription());
		sqlArgs.add(amsMaintainFiles.getFilePath());
		sqlArgs.add(amsMaintainFiles.getFilePath());
		sqlArgs.add(amsMaintainFiles.getCompanyId());
		sqlArgs.add(amsMaintainFiles.getCompanyId());
		sqlArgs.add(amsMaintainFiles.getCreationDate());
		sqlArgs.add(amsMaintainFiles.getCreationDate());
		sqlArgs.add(amsMaintainFiles.getCreatedBy());
		sqlArgs.add(amsMaintainFiles.getCreatedBy());
		sqlArgs.add(amsMaintainFiles.getLastUpdateDate());
		sqlArgs.add(amsMaintainFiles.getLastUpdateDate());
		sqlArgs.add(amsMaintainFiles.getLastUpdateBy());
		sqlArgs.add(amsMaintainFiles.getLastUpdateBy());
		sqlArgs.add(amsMaintainFiles.getFileName());
		sqlArgs.add(amsMaintainFiles.getFileName());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}