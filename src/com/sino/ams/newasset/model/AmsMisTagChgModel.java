package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * <p>
 * Title: AmsMisTagChgModel
 * </p>
 * <p>
 * Description:��ӡ�¾ɱ�ǩ����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author Herry
 * @version 1.0
 */

public class AmsMisTagChgModel extends AMSSQLProducer {
	AmsAssetsTransHeaderDTO assetsHdto = null;

	/**
	 * ���ܣ���¼MIS��ǩ�ű����ʷ AMS_MIS_TAG_CHG ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            AmsMisTagChgDTO ���β���������
	 */
	public AmsMisTagChgModel(SfUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
		assetsHdto = (AmsAssetsTransHeaderDTO) dtoParameter;
	}

	/**
	 * ���ܣ�����Զ����ɼ�¼MIS��ǩ�ű����ʷ AMS_MIS_TAG_CHG���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	/*
	 * public SQLModel getDataCreateModel() throws SQLModelException { SQLModel sqlModel = new
	 * SQLModel(); try { List sqlArgs = new ArrayList(); String sqlStr = "INSERT INTO " + "
	 * AMS_MIS_TAG_CHG(" + " ID," + " FROM_ORGANIZATION_ID," + " TO_ORGANIZATION_ID," + "
	 * TAG_NUMBER_FROM," + " TAG_NUMBER_TO," + " REF_NUMBER," + " CREATION_DATE," + " CREATED_BY" + ")
	 * VALUES (" + " " + SyBaseSQLUtil.getNewID( "AMS_MIS_TAG_CHG_S" ) + " , ?, ?, ?, ?, ?, ?, ?)";
	 * 
	 * sqlArgs.add(assetsHdto.getFromOrganizationId());
	 * sqlArgs.add(assetsHdto.getToOrganizationId()); sqlArgs.add(assetsHdto.getTagNumberFrom());
	 * sqlArgs.add(assetsHdto.getTagNumberTo()); sqlArgs.add(assetsHdto.getRefNumber());
	 * sqlArgs.add(assetsHdto.getCreationDate()); sqlArgs.add(assetsHdto.getCreatedBy());
	 * 
	 * sqlModel.setSqlStr(sqlStr); sqlModel.setArgs(sqlArgs); } catch (CalendarException ex) {
	 * ex.printLog(); throw new SQLModelException(ex); } return sqlModel; }
	 * 
	 * /** ���ܣ�����Զ����ɼ�¼MIS��ǩ�ű����ʷ AMS_MIS_TAG_CHG���ݸ���SQLModel�������ʵ����Ҫ�޸ġ� @return SQLModel
	 * �������ݸ�����SQLModel @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	/*
	 * public SQLModel getDataUpdateModel() throws SQLModelException { SQLModel sqlModel = new
	 * SQLModel(); try { List sqlArgs = new ArrayList(); String sqlStr = "UPDATE AMS_MIS_TAG_CHG" + "
	 * SET" + " FROM_ORGANIZATION_ID = ?," + " TO_ORGANIZATION_ID = ?," + " TAG_NUMBER_FROM = ?," + "
	 * TAG_NUMBER_TO = ?," + " REF_NUMBER = ?," + " CREATION_DATE = ?," + " CREATED_BY = ?" + "
	 * WHERE" + " ID = ?";
	 * 
	 * sqlArgs.add(assetsHdto.getFromOrganizationId());
	 * sqlArgs.add(assetsHdto.getToOrganizationId()); sqlArgs.add(assetsHdto.getTagNumberFrom());
	 * sqlArgs.add(assetsHdto.getTagNumberTo()); sqlArgs.add(assetsHdto.getRefNumber());
	 * sqlArgs.add(assetsHdto.getCreationDate()); sqlArgs.add(assetsHdto.getCreatedBy());
	 * sqlArgs.add(assetsHdto.getId());
	 * 
	 * sqlModel.setSqlStr(sqlStr); sqlModel.setArgs(sqlArgs); } catch (CalendarException ex) {
	 * ex.printLog(); throw new SQLModelException(ex); } return sqlModel; }
	 */

	/**
	 * ���ܣ�����Զ����ɼ�¼MIS��ǩ�ű����ʷ AMS_MIS_TAG_CHG����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	/*
	 * public SQLModel getDataDeleteModel() { SQLModel sqlModel = new SQLModel(); List sqlArgs = new
	 * ArrayList(); String sqlStr = "DELETE FROM" + " AMS_MIS_TAG_CHG" + " WHERE" + " ID = ?";
	 * sqlArgs.add(assetsHdto.getId()); sqlModel.setSqlStr(sqlStr); sqlModel.setArgs(sqlArgs);
	 * return sqlModel; }
	 */

	/**
	 * ���ܣ���
	 * 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT " + " ID," + " FROM_ORGANIZATION_ID,"
				+ " TO_ORGANIZATION_ID," + " TAG_NUMBER_FROM,"
				+ " TAG_NUMBER_TO," + " REF_NUMBER," + " CREATION_DATE,"
				+ " CREATED_BY" + " FROM" + " AMS_MIS_TAG_CHG" + " WHERE"
				+ " ID = ?";
		sqlArgs.add("");

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���
	 * 
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();

			String sqlStr = "SELECT " + " ID," + " FROM_ORGANIZATION_ID,"
					+ " TO_ORGANIZATION_ID," + " TAG_NUMBER_FROM,"
					+ " TAG_NUMBER_TO," + " REF_NUMBER,"
					+ " CREATION_DATE,"
					+ " CREATED_BY"
					+ " FROM"
					+ " AMS_MIS_TAG_CHG"
					+ " WHERE"
					// + " ( " + SyBaseSQLUtil.isNull() + " OR ID LIKE ?)"
					// + " ( " + SyBaseSQLUtil.isNull() + " OR FROM_ORGANIZATION_ID = ?)"
					// + " AND ( " + SyBaseSQLUtil.isNull() + " OR TO_ORGANIZATION_ID = ?)"
					// + " AND ( " + SyBaseSQLUtil.isNull() + " OR TAG_NUMBER_FROM LIKE ?)"
					// + " AND ( " + SyBaseSQLUtil.isNull() + " OR TAG_NUMBER_TO LIKE ?)"
					+ "  REF_NUMBER = ?" + " AND ( " + SyBaseSQLUtil.isNull()
					+ "  OR CREATION_DATE LIKE ?)"
			// + " AND ( " + SyBaseSQLUtil.isNull() + " OR CREATED_BY = ?)"
			;
			// sqlArgs.add(assetsHdto.getFromOrganizationId()+"");
			// sqlArgs.add(assetsHdto.getFromOrganizationId());
			// sqlArgs.add(assetsHdto.getToOrganizationId()+"");
			// sqlArgs.add(assetsHdto.getToOrganizationId());
			sqlArgs.add(assetsHdto.getTransNo());
			sqlArgs.add(assetsHdto.getCreationDate());
			sqlArgs.add(assetsHdto.getCreationDate());
			// sqlArgs.add(assetsHdto.getCreatedBy()+"");
			// sqlArgs.add(assetsHdto.getCreatedBy());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ���
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			String sqlStr = "SELECT AATH.TRANS_ID,\n"
					+ "       AATH.TRANS_NO,\n"
					+ "       AATH.TRANS_TYPE,\n"
					+ "       AATH.TRANSFER_TYPE,\n"
					+ "       AATH.TRANS_STATUS,\n"
					+ "       AATH.FROM_ORGANIZATION_ID,\n"
					+ "       EOCM.COMPANY FROM_COMPANY_NAME,\n"
					+ "       EOCM2.COMPANY TO_COMPANY_NAME,\n"
					+ "       AATH.RECEIVED_USER,\n"
					+ "       AATH.CREATION_DATE,\n"
					+ "       SU.USERNAME CREATED\n"
					+ "  FROM AMS_ASSETS_TRANS_HEADER AATH,\n"
					+ "       ETS_OU_CITY_MAP         EOCM,\n"
					+ "       ETS_OU_CITY_MAP         EOCM2,\n"
					+ "       SF_USER                 SU\n"
					+ " WHERE AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID\n"
					+ "   AND AATH.TO_ORGANIZATION_ID = EOCM2.ORGANIZATION_ID\n"
					+ "   AND AATH.CREATED_BY = SU.USER_ID\n"
					+ "   AND AATH.TRANS_TYPE = 'ASS-RED'\n"
					+ "   AND AATH.TRANSFER_TYPE = 'BTW_COMP'\n"
					+ "   AND (?='' OR EOCM.COMPANY LIKE dbo.NVL(?, EOCM.COMPANY))\n"
					+ "   AND (?='' OR EOCM2.COMPANY LIKE dbo.NVL(?, EOCM2.COMPANY))\n"
					+ "   AND (?='' OR AATH.CREATION_DATE >= ISNULL(?, AATH.CREATION_DATE))\n"
					+ "   AND (?='' OR AATH.CREATION_DATE <= ISNULL(?, AATH.CREATION_DATE))\n"
					+ "   AND (?='' OR AATH.TRANS_NO LIKE dbo.NVL(?, AATH.TRANS_NO))\n"
					+ "   AND (AATH.FROM_ORGANIZATION_ID=? OR AATH.TO_ORGANIZATION_ID=?)"
					+ // ֻ�е��뱾��˾���������˾�Ŀɼ�
					" ORDER BY AATH.CREATION_DATE DESC";
			sqlArgs.add(assetsHdto.getFromCompanyName());
			sqlArgs.add(assetsHdto.getFromCompanyName());
			sqlArgs.add(assetsHdto.getToCompanyName());
			sqlArgs.add(assetsHdto.getToCompanyName());
			sqlArgs.add(assetsHdto.getStartDate());
			sqlArgs.add(assetsHdto.getStartDate());
			sqlArgs.add(assetsHdto.getSQLEndDate());
			sqlArgs.add(assetsHdto.getSQLEndDate());

			sqlArgs.add(assetsHdto.getTransNo());
			sqlArgs.add(assetsHdto.getTransNo());
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(userAccount.getOrganizationId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ���
	 * 
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getBarCodePrintModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT \n ");
		sb.append(" AMTC.REF_NUMBER, \n ");
		sb.append(" ECOM.COMPANY COMPANY_NAME_PRINT , \n ");
		sb.append(" AMTC.TAG_NUMBER_TO BARCODE_PRINT , \n ");
		sb.append(" EII.START_DATE START_DATE_PRINT , \n ");
		sb.append(" ESI.ITEM_NAME ITEM_NAME_PRINT, \n ");
		sb.append(" ESI.ITEM_SPEC ITEM_SPEC_PRINT \n ");
		sb.append(" FROM \n ");
		sb.append(" AMS_MIS_TAG_CHG AMTC , \n ");
		sb.append(" ETS_OU_CITY_MAP ECOM , \n ");
		sb.append(" ETS_ITEM_INFO EII , \n ");
		sb.append(" ETS_SYSTEM_ITEM ESI \n ");
		sb.append(" WHERE  \n ");
		sb.append(" AMTC.TO_ORGANIZATION_ID = ECOM.ORGANIZATION_ID \n ");
		sb.append(" AND AMTC.REF_NUMBER = ?  \n ");
		sb.append(" AND AMTC.TAG_NUMBER_TO = EII.BARCODE \n ");
		sb.append(" AND EII.ITEM_CODE = ESI.ITEM_CODE  \n ");
		sqlArgs.add(assetsHdto.getTransNo());

		sqlModel.setSqlStr(sb.toString());
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}