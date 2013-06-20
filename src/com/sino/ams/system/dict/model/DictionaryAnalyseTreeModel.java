package com.sino.ams.system.dict.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DictionaryAnalyseTreeModel {
	public DictionaryAnalyseTreeModel() {
		super();
	}


	/**
	 * ���ܣ���ȡ�����ֵ����
	 * @return SQLModel
	 */
	public SQLModel getAllDictSetModel() {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " ETS_FLEX_ANALYSE_VALUE_SET EFVS"
						+ " ORDER BY"
						+ " EFVS.FLEX_VALUE_SET_ID";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
	 * ���ܣ������ֵ�����ȡ��Ӧ�ֵ�
	 * @param flexValueSetId �ֵ������
	 * @return SQLModel
	 */
	public SQLModel getDictionaryBySetIdModel(String flexValueSetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " ETS_FLEX_ANALYSE_VALUES EFV"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = ?"
						+ " ORDER BY"
						+ " EFV.FLEX_VALUE_ID";
		sqlArgs.add(flexValueSetId);
		sqlModel.setArgs(sqlArgs);
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
}
