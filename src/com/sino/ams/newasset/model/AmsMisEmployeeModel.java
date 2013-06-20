package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: AmsMisEmployeeModel</p>
 * <p>Description:�����Զ�����SQL��������AmsMisEmployeeModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsMisEmployeeModel {
	private int orgId = -1;
	/**
	 * ���ܣ�MISԱ���� AMS_MIS_EMPLOYEE ���ݿ�SQL����㹹�캯��
	 * @param orgId String ��֯id
	 */
	public AmsMisEmployeeModel(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * ���ܣ��жϴ������֯�Ƿ�Ϸ�
	 * @return SQLModel ����SQLModel
	 */
	public SQLModel getIsOrgValidModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " 1"
						+ " FROM"
						+ " ETS_OU_CITY_MAP  EOCM"
						+ " WHERE"
						+ " EOCM.ORGANIZATION_ID = ?";
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����MISԱ���� AMS_MIS_EMPLOYEE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param lastUpdateDate �ϴθ�������
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPersonDept4PDAModel(String lastUpdateDate) throws
			SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			String sqlStr = "SELECT "
							+ " AME.EMPLOYEE_ID,"
							+ " AME.USER_NAME,"
							+ " AMD.DEPT_CODE,"
							+ " AMD.DEPT_NAME,"
							+ " AME.ENABLED"
							+ " FROM"
							+ " AMS_MIS_EMPLOYEE AME,"
							+ " AMS_MIS_DEPT     AMD,"
							+ " ETS_OU_CITY_MAP  EOCM"
							+ " WHERE"
							+ " AME.DEPT_CODE = AMD.DEPT_CODE"
							+ " AND AMD.COMPANY_CODE = EOCM.COMPANY_CODE"
							+ " AND EOCM.ORGANIZATION_ID = ?";
			sqlArgs.add(orgId);
			if (!StrUtil.isEmpty(lastUpdateDate)) {
				SimpleCalendar cal = new SimpleCalendar();
				cal.setCalendarValue(lastUpdateDate);
				sqlStr += " AND AMD.LAST_UPDATE_DATE >= ?";
				sqlArgs.add(cal);
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}
}
