package com.sino.ams.system.note.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.note.dto.AmsRentDeadlineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsRentDeadlineModel</p>
 * <p>Description:�����Զ�����SQL��������AmsRentDeadlineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class AmsRentDeadlineModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���������(EAM) AMS_RENT_DEADLINE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsRentDeadlineDTO ���β���������
	 */
	public AmsRentDeadlineModel(SfUserDTO userAccount, AmsRentDeadlineDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
			String sqlStr = "INSERT INTO "
				+ " AMS_RENT_DEADLINE("
				+ " DEADLINE_ID,"
				+ " BARCODE,"
				+ " NOTICE_BEFORE,"
				+ " ORGANIZATION_ID,"
				+ " CTREATION_DATE,"
				+ " CREATED_BY"
				+ ") VALUES ("
				+ "  NEWID() , ?, ?, ?, GETDATE(),?)";
		
			sqlArgs.add(amsRentDeadline.getBarcode());
			sqlArgs.add(amsRentDeadline.getNoticeBefore());
			sqlArgs.add(sfUser.getOrganizationId());
			sqlArgs.add(sfUser.getUserId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
			String sqlStr = "UPDATE AMS_RENT_DEADLINE"
                        + " SET"
                        + " BARCODE = ?,"
                        + " NOTICE_BEFORE = ?,"
                        + " LAST_UPDATE_DATE = GETDATE(),"
                        + " LAST_UPDATE_BY = ?"
                        + " WHERE"
                        + " DEADLINE_ID = ?";
		
			sqlArgs.add(amsRentDeadline.getBarcode());
			sqlArgs.add(amsRentDeadline.getNoticeBefore());
			sqlArgs.add(sfUser.getUserId());
			sqlArgs.add(amsRentDeadline.getDeadlineId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " AMS_RENT_DEADLINE"
				+ " WHERE"
				+ " DEADLINE_ID = ?";
			sqlArgs.add(amsRentDeadline.getDeadlineId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {                   //��ϸ
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
		String sqlStr = "SELECT"
                    + " ARD.DEADLINE_ID,\n"
                    + " ESI.ITEM_NAME,\n"
                    + " ESI.ITEM_SPEC,\n"
                    + " ESI.ITEM_CATEGORY,\n"
                    + " ARD.BARCODE,\n"
                    + " AHI.END_DATE,\n"
                    + " ARD.NOTICE_BEFORE\n"
                    + " FROM "
                    + " ETS_ITEM_INFO     EII,\n"
                    + " ETS_SYSTEM_ITEM   ESI,\n"
                    + " AMS_HOUSE_INFO    AHI,\n"
                    + " AMS_RENT_DEADLINE ARD\n"
                    + " WHERE "
                    + " AHI.BARCODE = ARD.BARCODE\n"
                    + " AND EII.BARCODE = AHI.BARCODE\n"
                    + " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
                    + " AND  " + SyBaseSQLUtil.isNotNull("AHI.END_DATE") + " \n"
                    + " AND ARD.DEADLINE_ID = ?"
                    + " UNION ALL\n"
                    + " SELECT"
                    + " ARD.DEADLINE_ID,\n"
                    + " ESI.ITEM_NAME,\n"
                    + " ESI.ITEM_SPEC,\n"
                    + " ESI.ITEM_CATEGORY,\n"
                    + " ARD.BARCODE,\n"
                    + " ALI.END_DATE,\n"
                    + " ARD.NOTICE_BEFORE\n"
                    + " FROM "
                    + " ETS_ITEM_INFO     EII,\n"
                    + " ETS_SYSTEM_ITEM   ESI,\n"
                    + " AMS_LAND_INFO     ALI,\n"
                    + " AMS_RENT_DEADLINE ARD\n"
                    + " WHERE "
                    + " ALI.BARCODE = ARD.BARCODE\n"
                    + " AND EII.BARCODE = ALI.BARCODE\n"
                    + " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
                    + " AND  " + SyBaseSQLUtil.isNotNull("ALI.END_DATE") + " "
			        + " AND ARD.DEADLINE_ID = ?";
		sqlArgs.add(amsRentDeadline.getDeadlineId());
		sqlArgs.add(amsRentDeadline.getDeadlineId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳� */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " DEADLINE_ID,"
				+ " BARCODE,"
				+ " NOTICE_BEFORE,"
				+ " ORGANIZATION_ID,"
				+ " CTREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY"
				+ " FROM"
				+ " AMS_RENT_DEADLINE"
				+ " WHERE"
				+ " ( " + SyBaseSQLUtil.isNull() + "  OR DEADLINE_ID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR BARCODE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR NOTICE_BEFORE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CTREATION_DATE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
			sqlArgs.add(amsRentDeadline.getDeadlineId());
			sqlArgs.add(amsRentDeadline.getDeadlineId());
			sqlArgs.add(amsRentDeadline.getBarcode());
			sqlArgs.add(amsRentDeadline.getBarcode());
			sqlArgs.add(amsRentDeadline.getNoticeBefore());
			sqlArgs.add(amsRentDeadline.getNoticeBefore());
			sqlArgs.add(amsRentDeadline.getOrganizationId());
			sqlArgs.add(amsRentDeadline.getOrganizationId());
			sqlArgs.add(amsRentDeadline.getCtreationDate());
			sqlArgs.add(amsRentDeadline.getCtreationDate());
			sqlArgs.add(amsRentDeadline.getCreatedBy());
			sqlArgs.add(amsRentDeadline.getCreatedBy());
			sqlArgs.add(amsRentDeadline.getLastUpdateDate());
			sqlArgs.add(amsRentDeadline.getLastUpdateDate());
			sqlArgs.add(amsRentDeadline.getLastUpdateBy());
			sqlArgs.add(amsRentDeadline.getLastUpdateBy());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcode �����ѯ����SQL��
	 * ����Զ�����������������(EAM) AMS_RENT_DEADLINE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param barcode String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByBarcodeModel(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " DEADLINE_ID,"
			+ " NOTICE_BEFORE,"
			+ " ORGANIZATION_ID,"
			+ " CTREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " AMS_RENT_DEADLINE"
			+ " WHERE"
			+ " BARCODE = ?";
		sqlArgs.add(barcode);
		
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
		AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
		if(foreignKey.equals("barcode")){
			sqlModel = getDataByBarcodeModel(amsRentDeadline.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcode ��������ɾ��SQL��
	 * ����Զ�����������������(EAM) AMS_RENT_DEADLINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param barcode String
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	private SQLModel getDeleteByBarcodeModel(String barcode) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " DEADLINE_ID,"
			+ " NOTICE_BEFORE,"
			+ " ORGANIZATION_ID,"
			+ " CTREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " AMS_RENT_DEADLINE"
			+ " WHERE"
			+ " BARCODE = ?";
		sqlArgs.add(barcode);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
		SQLModel sqlModel = null;
		AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
		if(foreignKey.equals("barcode")){
			sqlModel = getDeleteByBarcodeModel(amsRentDeadline.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳� */
	public SQLModel getPageQueryModel() throws SQLModelException {            //��ѯ
		SQLModel sqlModel = new SQLModel();

			List sqlArgs = new ArrayList();
			AmsRentDeadlineDTO amsRentDeadline = (AmsRentDeadlineDTO)dtoParameter;
			String sqlStr = "SELECT ARI.BARCODE,\n" +
                    "       ARI.RENT_PERSON,\n" +
                    "       ARI.END_DATE,\n" +
                    "       ARI.END_DATE - GETDATE() ,\n" +
                    "       TRUNC(ARI.END_DATE - GETDATE()) DAYS\n" +
                    "  FROM AMS_RENT_INFO ARI\n" +
                    " WHERE TO_NUMBER(ARI.END_DATE - GETDATE()) > 0\n" +
                    "   AND TO_NUMBER(ARI.END_DATE - GETDATE()) < 30"
//                    + " AND  " + SyBaseSQLUtil.isNotNull("ALI.END_DATE") + " "
				    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ARI.BARCODE LIKE ?)"
				    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ARI.RENT_PERSON LIKE ?)";

            sqlArgs.add(amsRentDeadline.getBarcode());
			sqlArgs.add(amsRentDeadline.getBarcode());
            sqlArgs.add(amsRentDeadline.getRentPerson());
            sqlArgs.add(amsRentDeadline.getRentPerson());
//			sqlArgs.add(amsRentDeadline.getNoticeBefore());
//			sqlArgs.add(amsRentDeadline.getNoticeBefore());
//            sqlArgs.add(amsRentDeadline.getBarcode());
//			sqlArgs.add(amsRentDeadline.getBarcode());
//			sqlArgs.add(amsRentDeadline.getNoticeBefore());
//			sqlArgs.add(amsRentDeadline.getNoticeBefore());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}


	/**
	 * ���ܣ���ȡ�жϵ�ǰ�û��Ƿ���Ȩ��ִ�����ݱ༭������BARCODE �Ĵ�����
	 * @param barcode String
	 * @return SQLModel
	 */
	public SQLModel getVerifyBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql =   "SELECT "
                    + " ARD.*"
                    + " FROM"
                    + " AMS_RENT_DEADLINE  ARD\n"
                    + " WHERE"
                    + " ARD.BARCODE = ?";
        strArg.add(barcode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }

}