package com.sino.sinoflow.dao;


import java.sql.Connection;

import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfDelegationDTO;
import com.sino.sinoflow.model.SfDelegationModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfDelegationDAO</p>
 * <p>Description:�����Զ����ɷ������SfDelegationDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfDelegationDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�ί�ɶ��� SF_DELEGATION ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfDelegationDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfDelegationDAO(SfUserBaseDTO userAccount, SfDelegationDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfDelegationDTO dtoPara = (SfDelegationDTO)dtoParameter;
		super.sqlProducer = new SfDelegationModel((SfUserBaseDTO)userAccount, dtoPara);
	}
	

	/**
	 * ���ܣ���ɾ��Ӧ��
	 * @param String[]
	 * @throws DataHandleException 
	 */
	public void del(String[] ids) throws DataHandleException {
		DBOperator.updateRecord(((SfDelegationModel)sqlProducer).del(ids), conn);
	}

}