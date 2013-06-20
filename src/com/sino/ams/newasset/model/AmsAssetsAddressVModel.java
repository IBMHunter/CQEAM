package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsAssetsAddressVModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsAddressVModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsAddressVModel extends AMSSQLProducer {

	/**
	 * ���ܣ�AMS_ASSETS_ADDRESS_V ���ݿ�SQL����㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsAddressVDTO ���β���������
	 */
	public AmsAssetsAddressVModel(SfUserDTO userAccount,
								  AmsAssetsAddressVDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ����������ȡ����
	 * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
	 * @param foreignKey ����ֶ����ƶ�Ӧ��DTO���ԡ�
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey) throws
			SQLModelException {
		SQLModel sqlModel = null;
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		if (foreignKey.equals("workorderObjectNo")) {
			sqlModel = getDataByWorkobjectNoModel(dto.getWorkorderObjectNo());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ����ݵص��Ż�ȡ������豸
	 * @param workorderObjectNo String
	 * @return SQLModel
	 */
	private SQLModel getDataByWorkobjectNoModel(String workorderObjectNo) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " AMS_ASSETS_ADDRESS_V AAAV"
						+ " WHERE"
						+ "  " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + ""
						+ " AND AAAV.WORKORDER_OBJECT_NO = ?";
		sqlArgs.add(workorderObjectNo);
		String mtlMgrProps = userAccount.getMtlMgrProps();
		if (!userAccount.isComAssetsManager()) {
			if (userAccount.isDptAssetsManager()) {
				DTOSet depts = userAccount.getPriviDeptCodes();
				if (depts != null && !depts.isEmpty()) {
					AmsMisDeptDTO dept = null;
					String deptCodes = "'";
					for (int i = 0; i < depts.getSize(); i++) {
						dept = (AmsMisDeptDTO) depts.getDTO(i);
						deptCodes += dept.getDeptCode() + "', '";
					}
					deptCodes += "'";
					sqlStr += " AND AAAV.DEPT_CODE IN (" + deptCodes + ")";
				}
			} else {
				sqlStr += " AND AAAV.RESPONSIBILITY_USER = ?";
				sqlArgs.add(userAccount.getEmployeeId());
			}
		}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
