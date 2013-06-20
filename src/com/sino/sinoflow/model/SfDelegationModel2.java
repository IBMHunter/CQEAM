package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfDelegationDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfDelegationModel</p>
 * <p>Description:�����Զ�����SQL��������SfDelegationModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfDelegationModel2 extends BaseSQLProducer {

	/**
	 * ���ܣ�ί�ɶ��� SF_DELEGATION ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfDelegationDTO ���β���������
	 */
	public SfDelegationModel2(SfUserBaseDTO userAccount, SfDelegationDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ�����ί�ɶ��� SF_DELEGATIONҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
	
			List sqlArgs = new ArrayList();
			SfDelegationDTO sfDelegation = (SfDelegationDTO)dtoParameter;

            String sqlStr = "SELECT "
				+ " CONVERT(INT,D.DELEGATION_ID) DELEGATION_ID,"
				+ " D.USER_ID,"
				+ " D.DELEGATE_TO,"
				+ " D.STATUS_CTL,"
				+ " D.START_DATE,"
				+ " D.END_DATE,"
				+ " US.USERNAME S_NAME,"
				+ " UE.USERNAME E_NAME"
				+ " FROM"
				+ " SF_DELEGATION D,"
				+ " SF_USER US,"
				+ " SF_USER UE"
				+ " WHERE"
				+ " (? <= 0 OR D.DELEGATION_ID = ?)"
				+ " AND (? <= 0 OR D.USER_ID = ?)"
				+ " AND (? <= 0 OR D.DELEGATE_TO = ?)"
				+ " AND (? <= 0 OR D.STATUS_CTL = ?)"
                + " AND D.USER_ID = ?"
                + " AND D.USER_ID = US.USER_ID"
				+ " AND D.DELEGATE_TO = UE.USER_ID";

            sqlArgs.add(sfDelegation.getDelegationId());
			sqlArgs.add(sfDelegation.getDelegationId());
			sqlArgs.add(sfDelegation.getUserId());
			sqlArgs.add(sfDelegation.getUserId());
			sqlArgs.add(sfDelegation.getDelegateTo());
			sqlArgs.add(sfDelegation.getDelegateTo());
			sqlArgs.add(sfDelegation.getStatusCtl());
			sqlArgs.add(sfDelegation.getStatusCtl());
            sqlArgs.add(((SfUserBaseDTO)this.userAccount).getUserId());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}
}