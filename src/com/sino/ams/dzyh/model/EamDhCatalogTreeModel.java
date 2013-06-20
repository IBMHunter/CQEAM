package com.sino.ams.dzyh.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */
public class EamDhCatalogTreeModel {
	public EamDhCatalogTreeModel() {
		super();
	}


	/**
	 * ���ܣ���ȡ���е�ֵ�׺ķ���
	 * @return SQLModel
	 */
	public SQLModel getAllDzyhSetModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " EAM_DH_CATALOG_SET EDCS"
						+ " ORDER BY"
						+ " EDCS.CATLOG_SET_ID";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ����ݵ�ֵ�׺ķ����ȡ��ӦĿ¼
	 * @param catalogSetId ��ֵ�׺ķ�����
	 * @return SQLModel
	 */
	public SQLModel getDzyhBySetIdModel(String catlogSetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " EAM_DH_CATALOG_VALUES EDCV"
						+ " WHERE"
						+ " EDCV.CATALOG_SET_ID = ?"
						+ " ORDER BY"
						+ " EDCV.CATALOG_VALUE_ID";
		sqlArgs.add(catlogSetId);
		sqlModel.setArgs(sqlArgs);
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
}
