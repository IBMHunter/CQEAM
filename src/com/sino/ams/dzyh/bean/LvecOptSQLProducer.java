package com.sino.ams.dzyh.bean;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class LvecOptSQLProducer {

	/**
	 * ���ܣ������ȡ������״̬SQL
	 * @return SQLModel
	 */
	public static SQLModel getBatchStatusModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EFV.CODE,"
						+ " EFV.VALUE"
						+ " FROM"
						+ " ETS_FLEX_VALUE_SET EFVS,"
						+ " ETS_FLEX_VALUES    EFV"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFVS.CODE = ?"
						+ " AND EFV.CODE IN ('SAVE_TEMP', 'DISTRIBUTED', 'CANCELED')";

		sqlArgs.add(LvecDicts.CHKORDER_STATUS);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ����״̬SQL
	 * @return SQLModel
	 */
	public static SQLModel getOrderStatusModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EFV.CODE,"
						+ " EFV.VALUE"
						+ " FROM"
						+ " ETS_FLEX_VALUE_SET EFVS,"
						+ " ETS_FLEX_VALUES    EFV"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFVS.CODE = ?"
						+ " AND EFV.CODE IN ('SAVE_TEMP', 'DISTRIBUTED', 'DOWNLOADED', 'CANCELED', 'UPLOADED', 'ARCHIEVED')";

		sqlArgs.add(LvecDicts.CHKORDER_STATUS);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ����ִ�в��ŵ�SQL
	 * @param userAccount �û���Ϣ
	 * @param order EamDhCheckHeaderDTO
	 * @return SQLModel
	 */
	public static SQLModel getImpDeptModel(SfUserDTO userAccount, EamDhCheckHeaderDTO order){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AMD.DEPT_CODE,"
						+ " AMD.DEPT_NAME"
						+ " FROM"
						+ " AMS_MIS_DEPT AMD"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " ETS_OBJECT EO"
						+ " WHERE"
						+ " AMD.DEPT_CODE = EO.DEPT_CODE"
						+ " AND (EO.DISABLE_DATE " + SyBaseSQLUtil.isNullNoParam() + "  OR EO.DISABLE_DATE > GETDATE()) OR EO.DISABLE_DATE IS NULL"
						+ " AND EO.OBJECT_CATEGORY = dbo.NVL(?, EO.OBJECT_CATEGORY)"
						+ " AND EO.ORGANIZATION_ID = ?)"
						+ " AND AMD.COMPANY_CODE = ?";

		sqlArgs.add(order.getObjectCategory());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(userAccount.getCompanyCode());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ������ȡ��������SQL
	 * @return SQLModel
	 */
	public static SQLModel getOrderTypeModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EFV.CODE,"
						+ " EFV.VALUE"
						+ " FROM"
						+ " ETS_FLEX_VALUE_SET EFVS,"
						+ " ETS_FLEX_VALUES    EFV"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFVS.CODE = ?"
						+ " AND EFV.CODE IN ('DZYH-CHK', 'DHBS-CHK', 'YQYB-CHK')";

		sqlArgs.add(LvecDicts.ORDER_TYPE_ASSETS);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
