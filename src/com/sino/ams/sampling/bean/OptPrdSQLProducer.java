package com.sino.ams.sampling.bean;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.base.db.sql.model.SQLModel;
/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class OptPrdSQLProducer {

	/**
	 * ���ܣ�����ָ������δ�����OU��֯�����б��ѯSQL
	 * @param taskId String
	 * @return SQLModel
	 */
	public static SQLModel getTaskLeftOuOptModel(String taskId){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " EOCM.ORGANIZATION_ID,"
						+ " EOCM.COMPANY"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " NOT EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_SAMPLING_TASK_ASSIGN ASTA"
						+ " WHERE"
						+ " ASTA.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
						+ " AND ASTA.TASK_ID = ?)";
		List sqlArgs = new ArrayList();
		sqlArgs.add(taskId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����ָ�������ѷ����OU��֯�����б��ѯSQL
	 * @param taskId String
	 * @return SQLModel
	 */
	public static SQLModel getTaskSampledOuOptModel(String taskId){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " EOCM.ORGANIZATION_ID,"
						+ " EOCM.COMPANY"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " EXISTS ("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " AMS_SAMPLING_TASK_ASSIGN ASTA"
						+ " WHERE"
						+ " ASTA.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
						+ " AND ASTA.TASK_ID = ?)";
		List sqlArgs = new ArrayList();
		sqlArgs.add(taskId);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��鹤��״̬
	 * @return SQLModel
	 */
	public static SQLModel getOrderStatusModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String limitStatus = //"'ARCHIEVED', 'CANCELED', 'DISTRIBUTED', 'DOWNLOADED', 'SAVE_TEMP'";
			"'CANCELED', 'SAVE_TEMP', 'DISTRIBUTED', 'DOWNLOADED', 'UPLOADED'";
		String sqlStr = "SELECT"
						+ " EFV.CODE,"
						+ " EFV.VALUE"
						+ " FROM"
						+ " ETS_FLEX_VALUES    EFV,"
						+ " ETS_FLEX_VALUE_SET EFVS"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFV.ENABLED = 'Y'"
						+ " AND EFV.CODE IN (" + limitStatus + ")"
						+ " AND EFVS.CODE = ?";
		sqlArgs.add(SamplingDicts.CHKORDER_STATUS);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}
}
