package com.sino.ams.system.house.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;

import com.sino.framework.sql.BaseSQLProducer;
import com.sino.ams.system.house.dto.AmsItemFilesDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemFilesModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemFilesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class AmsItemFilesModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��豸��ظ���(EAM) AMS_ITEM_FILES ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemFilesDTO ���β���������
	 */
	public AmsItemFilesModel(SfUserDTO userAccount, AmsItemFilesDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " AMS_ITEM_FILES("
			+ " BARCODE,"
			+ " FILE_DESC,"
			+ " FILE_PATH,"
			+ " SYSTEM_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY"
			+ ") VALUES ("
			+ " ?, ?, ?, ?, GETDATE(), ?)";
		
		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlArgs.add(sfUser.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "UPDATE AMS_ITEM_FILES"
			+ " SET"
			+ " BARCODE = ?,"
			+ " FILE_DESC = ?,"
			+ " FILE_PATH = ?,"
			+ " LAST_UPDATE_DATE = GETDATE(),"
			+ " LAST_UPDATE_BY = ?"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		
		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(amsItemFiles.getSystemId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
			+ " AMS_ITEM_FILES"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " BARCODE,"
			+ " FILE_DESC,"
			+ " FILE_PATH,"
			+ " SYSTEM_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " AMS_ITEM_FILES"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		sqlArgs.add(amsItemFiles.getSystemId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILES����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " BARCODE,"
			+ " FILE_DESC,"
			+ " FILE_PATH,"
			+ " SYSTEM_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " AMS_ITEM_FILES"
			+ " WHERE"
			+ "(? IS NULL OR BARCODE LIKE ?)"
			+ "(? IS NULL OR FILE_DESC LIKE ?)"
			+ "(? IS NULL OR FILE_PATH LIKE ?)"
			+ "(? IS NULL OR SYSTEM_ID LIKE ?)"
			+ "(? IS NULL OR CREATION_DATE LIKE ?)"
			+ "(? IS NULL OR CREATED_BY LIKE ?)"
			+ "(? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
			+ "(? IS NULL OR LAST_UPDATE_BY LIKE ?)";
		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlArgs.add(amsItemFiles.getCreationDate());
		sqlArgs.add(amsItemFiles.getCreationDate());
		sqlArgs.add(amsItemFiles.getCreatedBy());
		sqlArgs.add(amsItemFiles.getCreatedBy());
		sqlArgs.add(amsItemFiles.getLastUpdateDate());
		sqlArgs.add(amsItemFiles.getLastUpdateDate());
		sqlArgs.add(amsItemFiles.getLastUpdateBy());
		sqlArgs.add(amsItemFiles.getLastUpdateBy());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " BARCODE,"
			+ " FILE_DESC,"
			+ " FILE_PATH,"
			+ " SYSTEM_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " AMS_ITEM_FILES"
			+ " WHERE"
			+ " (? IS NULL OR BARCODE LIKE ?)"
			+ "AND (? IS NULL OR FILE_DESC LIKE ?)"
			+ "AND (? IS NULL OR FILE_PATH LIKE ?)"
			+ "AND (? IS NULL OR SYSTEM_ID LIKE ?)"
			+ "AND (? IS NULL OR CREATION_DATE LIKE ?)"
			+ "AND (? IS NULL OR CREATED_BY LIKE ?)"
			+ "AND (? IS NULL OR LAST_UPDATE_DATE LIKE ?)"
			+ "AND (? IS NULL OR LAST_UPDATE_BY LIKE ?)";
		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlArgs.add(amsItemFiles.getCreationDate());
		sqlArgs.add(amsItemFiles.getCreationDate());
		sqlArgs.add(amsItemFiles.getCreatedBy());
		sqlArgs.add(amsItemFiles.getCreatedBy());
		sqlArgs.add(amsItemFiles.getLastUpdateDate());
		sqlArgs.add(amsItemFiles.getLastUpdateDate());
		sqlArgs.add(amsItemFiles.getLastUpdateBy());
		sqlArgs.add(amsItemFiles.getLastUpdateBy());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


     /**
     * ���ܣ������ֶ� barcode �����ѯ����SQL��
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByCompanyIdModel(String barcode){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
			+ " BARCODE,"
			+ " FILE_DESC,"
			+ " FILE_PATH,"
			+ " SYSTEM_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " AMS_ITEM_FILES"
			+ " WHERE"
            + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ֶ� barcode ��ȡ����
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey){
        SQLModel sqlModel = null;
        AmsItemFilesDTO amsItemFilesDTO = (AmsItemFilesDTO)dtoParameter;
        if(foreignKey.equals("barcode")){
            sqlModel = getDataByCompanyIdModel(amsItemFilesDTO.getBarcode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ����ݹ����ֶ� barcode ��������ɾ��SQL��
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByCompanyIdModel(String barcode){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
			          + " AMS_ITEM_FILES"
			          + " WHERE"
                      + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ֶ�barcodeɾ������
     * @return SQLModel
     */
    public SQLModel getDeleteByForeignKeyModel(String foreignKey){
        SQLModel sqlModel = null;
        AmsItemFilesDTO amsItemFilesDTO = (AmsItemFilesDTO)dtoParameter;
        if(foreignKey.equals("barcode")){
            sqlModel = getDeleteByCompanyIdModel(amsItemFilesDTO.getBarcode());
        }
        return sqlModel;
    }

    /**
	 * ���ܣ�����Զ������豸��ظ���(EAM) AMS_ITEM_FILES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemFilesDTO amsItemFiles = (AmsItemFilesDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " AMS_ITEM_FILES("
			+ " BARCODE,"
			+ " FILE_DESC,"
			+ " FILE_PATH,"
			+ " SYSTEM_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY"
			+ ") VALUES ("
			+ " ?, ?, ?, ?, GETDATE(), ?)";

		sqlArgs.add(amsItemFiles.getBarcode());
		sqlArgs.add(amsItemFiles.getFileDesc());
		sqlArgs.add(amsItemFiles.getFilePath());
		sqlArgs.add(amsItemFiles.getSystemId());
		sqlArgs.add(sfUser.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


      /**
     * ���ܣ����ݹ����ֶ� barcode ��������ɾ��SQL��
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getDeleteModel(String barcode){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
			          + " AMS_ITEM_FILES"
			          + " WHERE"
                      + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}