package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: AmsMisDeptModel</p>
 * <p>Description:�����Զ�����SQL��������AmsHrDeptModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsMisDeptModel extends AMSSQLProducer {

	/**
	 * ���ܣ�MIS����(HR) AMS_MIS_DEPT ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsMisDeptDTO ���β���������
	 */
	public AmsMisDeptModel(SfUserDTO userAccount, AmsMisDeptDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	public SQLModel getDeptByNameModel() {
		SQLModel sqlModel = new SQLModel();
		AmsMisDeptDTO dto = (AmsMisDeptDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " AMS_MIS_DEPT AMD"
						+ " WHERE"
						+ " AMD.DEPT_NAME = ?"
						+ " AND AMD.COMPANY_CODE = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getDeptName());
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�����������ϸ��Ϣ��SQLModel
	 * @return SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		AmsMisDeptDTO dto = (AmsMisDeptDTO) dtoParameter;
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " AMS_MIS_DEPT AMD"
						+ " WHERE"
						+ " AMD.DEPT_CODE = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(dto.getDeptCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ������ȡ��OU��֯�µ����в��ŵ�SQL
	 * @return SQLModel
	 */
	public SQLModel getAllMisDeptModel(){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " AMS_MIS_DEPT AMD"
						+ " WHERE"
						+ " AMD.COMPANY_CODE = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(userAccount.getCompanyCode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
