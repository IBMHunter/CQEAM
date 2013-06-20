package com.sino.ams.system.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����ʤ��Ȩ����Copyright (c) 2003~2007��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Copyright: ������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 0.1
 */


public class SfUserModel extends BaseSQLProducer {

	private SfUserDTO userInfo = null;

	/**
	 * ���ܣ��û�ά��Model���캯��
	 *
	 * @param userAccount  BaseUserDTO ����ִ�е�ǰ�������û�
	 * @param dtoParameter SfUserDTO ����ǰ����������
	 */
	public SfUserModel(BaseUserDTO userAccount, SfUserDTO dtoParameter) {
		super(userAccount, dtoParameter);
		this.userInfo = (SfUserDTO) userAccount;
	}

	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SfUserDTO sfUser = (SfUserDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO "
				+ " SF_USER("
				+ " USER_ID,"
				+ " LOGIN_NAME,"
				+ " PASSWORD,"
				+ " USERNAME,"
				+ " WORKNO,"
				+ " OFFICE_TEL,"
				+ " FAX,"
				+ " MOBILE_PHONE,"
				+ " DISABLE_DATE,"
				+ " EMAIL,"
				+ " DISPLAY_ORDER,"
				+ " ORGANIZATION_ID,"
				+ " IS_INNER,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " EMPLOYEE_NUMBER,"
				+ " IS_MAINTAIN_USER,"
                + " DEPT_CATEGORY,"
                + " OA_NAME,"
				+ " MAINTAIN_COMPANY"
				+ ") VALUES ("
				+ " ?, ?, ETS_ENCRYPT.ENCODE(?), ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, GETDATE(), ?, ?, ?, ?, ? ,?)";

		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfUser.getLoginName().toUpperCase());
		sqlArgs.add(sfUser.getPassword());
		sqlArgs.add(sfUser.getUsername());
		sqlArgs.add(sfUser.getWorkno());
		sqlArgs.add(sfUser.getOfficeTel());
		sqlArgs.add(sfUser.getFax());
		sqlArgs.add(sfUser.getMobilePhone());
		sqlArgs.add(sfUser.getEndDate());
		sqlArgs.add(sfUser.getEmail());
		sqlArgs.add(sfUser.getDisplayOrder());
		sqlArgs.add(sfUser.getOrganizationId());
		sqlArgs.add(sfUser.getIsInner());
		sqlArgs.add(sfUser.getCreatedBy());
		sqlArgs.add(sfUser.getEmployeeNumber());
		sqlArgs.add(sfUser.getIsMaintainUser());
        sqlArgs.add(sfUser.getDeptCategory());
        sqlArgs.add(sfUser.getOaName());
		sqlArgs.add(sfUser.getMaintainCompany());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SfUserDTO sfUser = (SfUserDTO) super.dtoParameter;

		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE SF_USER"
				+ " SET"
				+ " LOGIN_NAME = ?,"
				+ " PASSWORD = (CASE WHEN PASSWORD=? THEN PASSWORD ELSE ETS_ENCRYPT.ENCODE(?) END) ,"
				+ " USERNAME = ?,"
				+ " WORKNO = ?,"
				+ " EMPLOYEE_NUMBER = ?,"
				+ " OFFICE_TEL = ?,"
				+ " FAX = ?,"
				+ " MOBILE_PHONE = ?,"
				+ " EMAIL = ?,"
				+ " DISPLAY_ORDER = ?,"
				+ " IS_INNER = ?,"
				+ " ORGANIZATION_ID = ?,"
				+ " LAST_UPDATE_DATE = GETDATE(),"
				+ " LAST_UPDATE_BY = ?,"
				+ " IS_MAINTAIN_USER = ?,"
				+ " MAINTAIN_COMPANY = ?," +
				"   LOGIN_ERR_COUNT=0," +
				"   DISABLE_DATE=?,"
                +  "DEPT_CATEGORY = ?,"
                + " OA_NAME = ?"
				+ " WHERE"
				+ " USER_ID = ?";

		sqlArgs.add(sfUser.getLoginName().toUpperCase());
		sqlArgs.add(sfUser.getPassword());
		sqlArgs.add(sfUser.getPassword());
		sqlArgs.add(sfUser.getUsername());
		sqlArgs.add(sfUser.getWorkno());
		sqlArgs.add(sfUser.getEmployeeNumber());
		sqlArgs.add(sfUser.getOfficeTel());
		sqlArgs.add(sfUser.getFax());
		sqlArgs.add(sfUser.getMobilePhone());
		sqlArgs.add(sfUser.getEmail());
		sqlArgs.add(sfUser.getDisplayOrder());
		sqlArgs.add(sfUser.getIsInner());
		sqlArgs.add(sfUser.getOrganizationId());
		sqlArgs.add(userInfo.getUserId());
		sqlArgs.add(sfUser.getIsMaintainUser());
		sqlArgs.add(sfUser.getMaintainCompany());
		sqlArgs.add(sfUser.getEndDate());
		sqlArgs.add(sfUser.getDeptCategory());
        sqlArgs.add(sfUser.getOaName());
        sqlArgs.add(sfUser.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SfUserDTO sfUser = (SfUserDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE "
				+ " SF_USER"
				+ " SET END_DATE=?," +
				"   LAST_UPDATE_DATE=GETDATE()," +
				"   LAST_UPDATE_BY=?"
				+ " WHERE"
				+ " USER_ID = ?";
		sqlArgs.add(sfUser.getEndDate());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfUser.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SfUserDTO sfUser = (SfUserDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
				+ " USER_ID,"
				+ " LOGIN_NAME,"
				+ " PASSWORD,"
				+ " USERNAME,"
				+ " WORKNO,"
				+ " OFFICETEL,"
				+ " FAX,"
				+ " MOVETEL,"
				+ " DISABLE_DATE,"
				+ " EMAIL,"
				+ " SORTNO,"
				+ " PASSWORD_DATE,"
				+ " ORGANIZATION_ID,"
				+ " IS_INNER,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY,"
				+ " EMPLOYEE_NUMBER,"
				+ " IS_MAINTAIN_USER,"
                + " DEPT_CATEGORY,"
				+ " MAINTAIN_COMPANY,"
				+ " OA_NAME"
				+ " FROM"
				+ " SF_USER "
				+ " WHERE"
				+ " USER_ID = ?";
		sqlArgs.add(sfUser.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɶ���������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SfUserDTO sfUser = (SfUserDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =  "SELECT " +
				" SU.USER_ID,\n" +
				" SU.LOGIN_NAME,\n" +
				" SU.PASSWORD,\n" +
				" SU.USERNAME,\n" +
				" SU.WORKNO,\n" +
				" SU.OFFICETEL,\n" +
				" SU.FAX,\n" +
				" SU.MOVETEL MOBILE_PHONE,\n" +
				" SU.END_DATE,\n" +
				" SU.EMAIL,\n" +
				" SU.SORTNO,\n" +
				" SU.PASSWORD_DATE,\n" +
				" SU.ORGANIZATION_ID,\n" +
				" SU.IS_INNER,\n" +
				" SU.CREATION_DATE,\n" +
				" SU.CREATED_BY,\n" +
				" SU.LAST_UPDATE_DATE,\n" +
				" SU.LAST_UPDATE_BY,\n" +
				" SU.EMPLOYEE_NUMBER,\n" +
				" SU.IS_MAINTAIN_USER,\n" +
				" SU.MAINTAIN_COMPANY,\n" +
				" SU.OA_NAME,\n" +
				" EOCM.COMPANY_CODE,\n" +
				" EOCM.BOOK_TYPE_CODE,\n" +
				" EOCM.COMPANY\n" +
				" FROM " +
				" SF_USER SU, " +
				" ETS_OU_CITY_MAP EOCM\n" +
				" WHERE " +
				" SU.ORGANIZATION_ID = EOCM.ORGANIZATION_ID\n" +
				" AND SU.LOGIN_NAME = ?";
		sqlArgs.add(sfUser.getLoginName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SfUserDTO sfUser = (SfUserDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
				+ " SU.USER_ID,"
				+ " SU.LOGIN_NAME,"
				+ " SU.PASSWORD,"
				+ " SU.USERNAME,"
				+ " SU.WORKNO,"
				+ " SU.OFFICETEL,"
				+ " SU.FAX,"
				+ " SU.MOVETEL,"
				+ " SU.DISABLE_DATE,"
				+ " SU.EMAIL,"
				+ " SU.SORTNO,"
				+ " SU.PASSWORD_DATE,"
				+ " SU.ORGANIZATION_ID,"
				+ " AMS_PUB_PKG.GET_ORGNIZATION_NAME(SU.ORGANIZATION_ID) ORGANIZATION_NAME,"
				+ " SU.IS_INNER,"
				+ " SU.CREATION_DATE,"
				+ " SU.CREATED_BY,"
				+ " SU.LAST_UPDATE_DATE,"
				+ " SU.LAST_UPDATE_BY,"
				+ " SU.EMPLOYEE_NUMBER,"
				+ " SU.IS_MAINTAIN_USER,"
				+ " AMS_PUB_PKG.GET_MAINTAIN_COMPANY_NAME(SU.MAINTAIN_COMPANY) MAINTAIN_COMPANY_NAME"
				+ " FROM"
				+ " SF_USER SU"
				+ " WHERE"
				+ " SU.LOGIN_NAME LIKE dbo.NVL(?, SU.LOGIN_NAME)"
				+ " AND SU.USERNAME LIKE dbo.NVL(?, SU.USERNAME)"
				+ " AND SU.ORGANIZATION_ID = ISNULL(?, SU.ORGANIZATION_ID)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SU.IS_MAINTAIN_USER = ?)"
				+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR SU.MAINTAIN_COMPANY = ?)";

		sqlArgs.add(sfUser.getLoginName().toUpperCase());
		sqlArgs.add(sfUser.getUsername());
		sqlArgs.add(sfUser.getOrganizationId());
		sqlArgs.add(sfUser.getIsMaintainUser());
		sqlArgs.add(sfUser.getIsMaintainUser());
		sqlArgs.add(sfUser.getMaintainCompany());
		sqlArgs.add(sfUser.getMaintainCompany());
		if ( sfUser.getCurrGroupId() > 0 && sfUser.getRoleId().equals("")) {
			sqlStr += " AND EXISTS (SELECT 1\n" +
					"       FROM SF_USER_RIGHT SUR\n" +
					"      WHERE SUR.USER_ID = SU.USER_ID\n" +
					"        AND SUR.GROUP_ID = ?)";
			sqlArgs.add(sfUser.getCurrGroupId());
		} else if ( sfUser.getCurrGroupId() > 0  && !sfUser.getRoleId().equals("")) {
			sqlStr += " AND EXISTS (SELECT 1\n" +
					"       FROM SF_USER_RIGHT SUR\n" +
					"      WHERE SUR.USER_ID = SU.USER_ID\n" +
					"        AND SUR.ROLE_ID = ?)";
			sqlArgs.add(sfUser.getRoleId());
		} else if ( sfUser.getCurrGroupId() > 0  && !sfUser.getRoleId().equals("")) {
			sqlStr += " AND EXISTS (SELECT 1\n" +
					"       FROM SF_USER_RIGHT SUR\n" +
					"      WHERE SUR.USER_ID = SU.USER_ID\n" +
					"        AND SUR.GROUP_ID = ?\n" +
					"        AND SUR.ROLE_ID = ?)";
				sqlArgs.add(sfUser.getCurrGroupId());
				sqlArgs.add(sfUser.getRoleId());
		}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
