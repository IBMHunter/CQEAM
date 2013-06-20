package com.sino.ams.synchronize.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.synchronize.dto.AmsSynTmpDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AmsSynTmpModel extends AMSSQLProducer {

	public AmsSynTmpModel(BaseUserDTO userAccount, AmsSynTmpDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ��������ݲ���SQL
	 * @return SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		AmsSynTmpDTO dto = (AmsSynTmpDTO)dtoParameter;
		String sqlStr = "INSERT INTO AMS_SYN_TMP(SOURCE_STR, TARGET_STR) VALUES(?, ?)";
		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getSourceStr());
		sqlArgs.add(dto.getTargetStr());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ɾ��SQL
	 * @return SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "DELETE FROM AMS_SYN_TMP";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
}
