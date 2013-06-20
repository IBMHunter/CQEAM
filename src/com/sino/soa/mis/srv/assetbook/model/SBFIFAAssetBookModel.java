package com.sino.soa.mis.srv.assetbook.model;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.assetbook.dto.SBFIFAAssetBookDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-5
 * Time: 20:02:31
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFAAssetBookModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SrvAssetBookDTO ���β���������
	 */
	public SBFIFAAssetBookModel(SfUserDTO userAccount, SBFIFAAssetBookDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOK���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SBFIFAAssetBookDTO srvAssetBookDTO = (SBFIFAAssetBookDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " ETS_OU_CITY_MAP("
			+ " ORGANIZATION_ID,"
			+ " MIS_ORGANIZATION_ID,"
			+ " COMPANY,"
			+ " COMPANY_CODE,"
			+ " BOOK_TYPE_CODE,"
			+ " BOOK_TYPE_NAME,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " IS_TD"
			+ ") VALUES ("
			+ " ?, ?, ?, ?, ?, ?,GETDATE(), ?, 'N')";

		sqlArgs.add(srvAssetBookDTO.getOrgId());
		sqlArgs.add(srvAssetBookDTO.getOrgId());
		sqlArgs.add(srvAssetBookDTO.getOrgName());
		String temp=srvAssetBookDTO.getBookTypeCode();
		sqlArgs.add(temp.substring(temp.length()-4,temp.length()));
		sqlArgs.add(srvAssetBookDTO.getBookTypeCode());
		sqlArgs.add(srvAssetBookDTO.getBookTypeName());
		sqlArgs.add(sfUser.getUserId());


		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOK���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SBFIFAAssetBookDTO srvAssetBookDTO = (SBFIFAAssetBookDTO)dtoParameter;
		String sqlStr = "UPDATE "
			+ " ETS_OU_CITY_MAP SET"
			+ " BOOK_TYPE_CODE=?,"
			+ " BOOK_TYPE_NAME=?,"
			+ " LAST_UPDATE_DATE=GETDATE(),"
			+ " LAST_UPDATE_BY=?"
			+ " WHERE ORGANIZATION_ID=?";
		sqlArgs.add(srvAssetBookDTO.getBookTypeCode());
		sqlArgs.add(srvAssetBookDTO.getBookTypeName());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(srvAssetBookDTO.getOrgId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	public SQLModel getEcouInforModel() {
		SQLModel sqlModel = new SQLModel();
			String sqlStr = "SELECT"
			+"	ECOM.BOOK_TYPE_CODE, "
			+"	ECOM.ORGANIZATION_ID "
			+"	FROM ETS_OU_CITY_MAP ECOM";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOK����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SBFIFAAssetBookDTO srvAssetBook = (SBFIFAAssetBookDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SRV_ASSET_BOOK"	;
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOK������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SBFIFAAssetBookDTO srvAssetBook = (SBFIFAAssetBookDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " BOOK_TYPE_CODE,"
			+ " BOOK_TYPE_NAME,"
			+ " ORG_NAME,"
			+ " SET_OF_BOOKS_NAME,"
			+ " PRORATE_CALENDAR,"
			+ " ORG_ID,"
			+ " DATE_INEFFECTIVE,"
			+ " LAST_DEPRN_RUN_DATE,"
			+ " DEPRN_STATUS,"
			+ " LAST_UPDATE_DATE"
			+ " WHERE"
			+ " ROWNUM = 1";

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOK����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			SBFIFAAssetBookDTO srvAssetBook = (SBFIFAAssetBookDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " BOOK_TYPE_CODE,"
				+ " BOOK_TYPE_NAME,"
				+ " ORG_NAME,"
				+ " SET_OF_BOOKS_NAME,"
				+ " PRORATE_CALENDAR,"
				+ " ORG_ID,"
				+ " DATE_INEFFECTIVE,"
				+ " LAST_DEPRN_RUN_DATE,"
				+ " DEPRN_STATUS,"
				+ " LAST_UPDATE_DATE"
				+ " FROM"
				+ " SRV_ASSET_BOOK"
				+ " WHERE"
                + " ( " + SyBaseSQLUtil.isNull() + " OR BOOK_TYPE_CODE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR BOOK_TYPE_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR ORG_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR SET_OF_BOOKS_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR PRORATE_CALENDAR LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR ORG_ID LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR DATE_INEFFECTIVE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR LAST_DEPRN_RUN_DATE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR DEPRN_STATUS LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR LAST_UPDATE_DATE LIKE ?)";
			sqlArgs.add(srvAssetBook.getBookTypeCode());
			sqlArgs.add(srvAssetBook.getBookTypeCode());
			sqlArgs.add(srvAssetBook.getBookTypeName());
			sqlArgs.add(srvAssetBook.getBookTypeName());
			sqlArgs.add(srvAssetBook.getOrgName());
			sqlArgs.add(srvAssetBook.getOrgName());
			sqlArgs.add(srvAssetBook.getSetOfBooksName());
			sqlArgs.add(srvAssetBook.getSetOfBooksName());
			sqlArgs.add(srvAssetBook.getProrateCalendar());
			sqlArgs.add(srvAssetBook.getProrateCalendar());
			sqlArgs.add(srvAssetBook.getOrgId());
			sqlArgs.add(srvAssetBook.getOrgId());
			sqlArgs.add(srvAssetBook.getDateIneffective());
			sqlArgs.add(srvAssetBook.getDateIneffective());
			sqlArgs.add(srvAssetBook.getLastDeprnRunDate());
			sqlArgs.add(srvAssetBook.getLastDeprnRunDate());
			sqlArgs.add(srvAssetBook.getDeprnStatus());
			sqlArgs.add(srvAssetBook.getDeprnStatus());
			sqlArgs.add(srvAssetBook.getLastUpdateDate());
			sqlArgs.add(srvAssetBook.getLastUpdateDate());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOKҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			SBFIFAAssetBookDTO srvAssetBook = (SBFIFAAssetBookDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " BOOK_TYPE_CODE,"
				+ " BOOK_TYPE_NAME,"
				+ " ORG_NAME,"
				+ " SET_OF_BOOKS_NAME,"
				+ " PRORATE_CALENDAR,"
				+ " ORG_ID,"
				+ " DATE_INEFFECTIVE,"
				+ " LAST_DEPRN_RUN_DATE,"
				+ " DEPRN_STATUS,"
				+ " LAST_UPDATE_DATE"
				+ " FROM"
				+ " SRV_ASSET_BOOK"
				+ " WHERE"
                + " ( " + SyBaseSQLUtil.isNull() + " OR BOOK_TYPE_CODE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR BOOK_TYPE_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR ORG_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR SET_OF_BOOKS_NAME LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR PRORATE_CALENDAR LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR ORG_ID LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR DATE_INEFFECTIVE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR LAST_DEPRN_RUN_DATE LIKE ?)"   
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR DEPRN_STATUS LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + " OR LAST_UPDATE_DATE LIKE ?)";
			sqlArgs.add(srvAssetBook.getBookTypeCode());
			sqlArgs.add(srvAssetBook.getBookTypeCode());
			sqlArgs.add(srvAssetBook.getBookTypeName());
			sqlArgs.add(srvAssetBook.getBookTypeName());
			sqlArgs.add(srvAssetBook.getOrgName());
			sqlArgs.add(srvAssetBook.getOrgName());
			sqlArgs.add(srvAssetBook.getSetOfBooksName());
			sqlArgs.add(srvAssetBook.getSetOfBooksName());
			sqlArgs.add(srvAssetBook.getProrateCalendar());
			sqlArgs.add(srvAssetBook.getProrateCalendar());
			sqlArgs.add(srvAssetBook.getOrgId());
			sqlArgs.add(srvAssetBook.getOrgId());
			sqlArgs.add(srvAssetBook.getDateIneffective());
			sqlArgs.add(srvAssetBook.getDateIneffective());
			sqlArgs.add(srvAssetBook.getLastDeprnRunDate());
			sqlArgs.add(srvAssetBook.getLastDeprnRunDate());
			sqlArgs.add(srvAssetBook.getDeprnStatus());
			sqlArgs.add(srvAssetBook.getDeprnStatus());
			sqlArgs.add(srvAssetBook.getLastUpdateDate());
			sqlArgs.add(srvAssetBook.getLastUpdateDate());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}
