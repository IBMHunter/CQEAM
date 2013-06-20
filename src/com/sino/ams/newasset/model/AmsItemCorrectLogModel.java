package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.SeqProducer;


/**
 * <p>Title: AmsItemCorrectLogModel</p>
 * <p>Description:�����Զ�����SQL��������AmsItemCorrectLogModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsItemCorrectLogModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ�̨��ά����־ AMS_ITEM_CORRECT_LOG ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemCorrectLogDTO ���β���������
	 */
	public AmsItemCorrectLogModel(SfUserDTO userAccount, AmsItemCorrectLogDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ�̨��ά����־ AMS_ITEM_CORRECT_LOG���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsItemCorrectLogDTO dto = (AmsItemCorrectLogDTO) dtoParameter;
//		String guid = SyBaseSQLUtil.generateGUID();
		String sqlStr = "INSERT INTO "
						+ " AMS_ITEM_CORRECT_LOG("
						+ " LOG_ID,"
						+ " BARCODE,"
						+ " CORRECT_CONTENT,"
						+ " CREATED_BY,"
						+ " CREATION_DATE"
						+ ") VALUES ("
						//+ "  " + SyBaseSQLUtil.getNewID( "AMS_ITEM_CORRECT_LOG_S" ) + " , ?, ?, ?, GETDATE())";
						+ "NEWID() , ?, ?, ?, GETDATE())";
		//sqlArgs.add(dto.getLogId());	//ACT�ݷ���LOG ID			
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getCorrectContent());
		sqlArgs.add(userAccount.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsItemCorrectLogDTO amsItemCorrectLog = (AmsItemCorrectLogDTO)dtoParameter;
		if(foreignKey.equals("barcode")){
			sqlModel = getDataByBarcodeModel(amsItemCorrectLog.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcode �����ѯ����SQL��
	 * ����Զ����������ʲ�̨��ά����־ AMS_ITEM_CORRECT_LOG��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param barcode String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByBarcodeModel(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " AICL.LOG_ID,"
						+ " SU.USERNAME CREATED_USER,"
						+ " AICL.CREATION_DATE,"
						+ " AICL.CORRECT_CONTENT"
						+ " FROM"
						+ " AMS_ITEM_CORRECT_LOG AICL,"
		                + " SF_USER              SU"
						+ " WHERE"
		                + " AICL.CREATED_BY = SU.USER_ID"
						+ " AND AICL.BARCODE = ?";
		sqlArgs.add(barcode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
