package com.sino.sinoflow.user.dao;

import java.sql.Connection;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.model.ChangeUserPasswordModel;

/**
 *
 * <p>Title: SinoCMS</p>
 * <p>Description: �����ƶ���ͬ����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChangeUserPasswordDAO extends BaseDAO {

	public ChangeUserPasswordDAO(SfUserBaseDTO userAccount, SfUserBaseDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		sqlProducer = new ChangeUserPasswordModel(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ��ж��û��Ƿ�������ȷ��ԭʼ����
	 * @return boolean
	 */
	public boolean authenticateUser() {
		boolean authenticate = false;
		try {
			ChangeUserPasswordModel modelProducer = (ChangeUserPasswordModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getUserAuthenticateModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			authenticate = simp.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return authenticate;
	}

	/**
	 * ���ܣ��޸��û�����
	 * @throws DataHandleException ���������ݿ��쳣ʱ�׳����쳣
	 */
	public void changeUserPassword() throws DataHandleException {
	   ChangeUserPasswordModel modelProducer =(ChangeUserPasswordModel)sqlProducer;
	   SQLModel sqlModel = modelProducer.getChangeUserPasswordModel();
	   DBOperator.updateRecord(sqlModel, conn);
	}
	
	/**
	 * ���ܣ��޸��û���������û���
	 * @throws DataHandleException ���������ݿ��쳣ʱ�׳����쳣
	 */
	public void changeUserPasswordByLoginName() throws DataHandleException {
	   ChangeUserPasswordModel modelProducer =(ChangeUserPasswordModel)sqlProducer;
	   SQLModel sqlModel = modelProducer.getChangeUserPasswordModelByLoginName();
	   DBOperator.updateRecord(sqlModel, conn);
	}
}
