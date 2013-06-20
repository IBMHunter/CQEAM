package com.sino.ams.security.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 *
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class PrivilegeAuthenticatorModel {

	private SfUserDTO userAccount = null;

	public PrivilegeAuthenticatorModel(SfUserDTO userAccount){
		super();
		this.userAccount = userAccount;
	}

	/**
	 * ���ܣ���ȡ���ָ��URL��Դ�Ƿ���ҪȨ����֤��SQLModel
	 * @param requestURL ������URL��Դ
	 * @return SQLModel
	 */
	public SQLModel getHasInControlModel(String requestURL){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " *"
						+ " FROM"
						+ " SF_RES_DEFINE SRD"
						+ " WHERE"
						+ " SRD.RES_URL = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(requestURL);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��ָ��URL��ԴȨ����֤��SQLModel
	 * @param requestURL ����֤��URL��Դ
	 * @return SQLModel
	 */
	public SQLModel getAuthenticateModel(String requestURL){
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
						+ " 1"
						+ " FROM"
						+ " SF_USER SU,"
						+ " SF_GROUP SG,"
						+ " SF_ROLE SR,"
						+ " SF_USER_RIGHT SUR,"
						+ " SF_RES_DEFINE SRD,"
						+ " SF_RES_PRIVS SRP"
						+ " WHERE"
						+ " SUR.USER_ID = SU.USER_ID"
						+ " AND SUR.GROUP_ID = SG.GROUP_ID"
						+ " AND SUR.ROLE_ID = SR.ROLE_ID"
						+ " AND SR.ROLE_NAME = SRP.ROLE_NAME"
						+ " AND SRP.SYSTEM_ID = SRD.SYSTEM_ID"
						+ " AND SU.LOGIN_NAME = ?"
						+ " AND SRD.RES_URL LIKE ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(userAccount.getLoginName());
		sqlArgs.add(requestURL + "%");
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
