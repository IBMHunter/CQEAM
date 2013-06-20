package com.sino.ams.dzyh.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
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
public class DeptSelectModel extends AMSSQLProducer {

	public DeptSelectModel(BaseUserDTO userAccount, EtsObjectDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ����ض������ݵ�SQLModel
	 * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
	 * @return SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		EtsObjectDTO dto = (EtsObjectDTO)dtoParameter;
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
						+ " AND EO.OBJECT_CATEGORY = ?"
						+ " AND EO.ORGANIZATION_ID = ?)"
						+ " AND AMD.ENABLED = 'Y'"
						+ " AND AMD.COMPANY_CODE = ?";
		List sqlArgs = new ArrayList();

		sqlArgs.add(dto.getObjectCategory());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(userAccount.getCompanyCode());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
