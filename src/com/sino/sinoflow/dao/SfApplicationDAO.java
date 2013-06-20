package com.sino.sinoflow.dao;


import java.sql.Connection;

import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfApplicationDTO;
import com.sino.sinoflow.model.SfApplicationModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfApplicationDAO</p>
 * <p>Description:�����Զ����ɷ������SfApplicationDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfApplicationDAO extends BaseDAO {

	/**
	 * ���ܣ�Ӧ����Ϣ SF_APPLICATION ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfApplicationDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfApplicationDAO(SfUserBaseDTO userAccount, SfApplicationDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfApplicationDTO dtoPara = (SfApplicationDTO)dtoParameter;
		super.sqlProducer = new SfApplicationModel((SfUserBaseDTO)userAccount, dtoPara);
	}
	
	/**
	 * ���ܣ���ɾ��Ӧ��
	 * @param String[]
	 * @throws DataHandleException 
	 */
	public void delApp(String[] ids) throws DataHandleException {
		DBOperator.updateRecord(((SfApplicationModel)sqlProducer).delApp(ids), conn);
	}
	
}