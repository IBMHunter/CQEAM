package com.sino.ams.newasset.bean;

import java.sql.Connection;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class CostCenterProcssor {
	private CostCenterProcssor() {
		super();
	}

	/**
	 * ���ܣ�����ɱ�������ʱ�����ݣ���Ҫ���ڽ����ѯͳ���ٶ�����
	 * @param conn Connection
	 * @throws DataHandleException
	 */
	public static void insertTmpCostCenter(Connection conn) throws DataHandleException {
		DBOperator.updateRecord(getInsertTmpCostModel(), conn);
	}

	/**
	 * ���ܣ���ȡ����ɱ�������ʱ�����ݵ�SQL
	 * @return SQLModel
	 */
	private static SQLModel getInsertTmpCostModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "INSERT INTO"
						+ " AMS_COST_CENTER_TMP"
						+ " SELECT"
						+ " *"
						+ " FROM"
						+ " AMS_COST_CENTER_V ACCV"
						+ " WHERE"
						+ " NOT EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_COST_CENTER_TMP ACCT"
						+ " WHERE"
						+ " ACCV.COST_CENTER_CODE = ACCT.COST_CENTER_CODE)";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
}
