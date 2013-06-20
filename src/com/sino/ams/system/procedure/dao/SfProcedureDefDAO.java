package com.sino.ams.system.procedure.dao;


import java.sql.Connection;

import com.sino.ams.system.procedure.dto.SfProcedureDefDTO;
import com.sino.ams.system.procedure.model.SfProcedureDefModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: SfProcedureDefDAO</p>
 * <p>Description:�����Զ����ɷ������SfProcedureDefDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class SfProcedureDefDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ת���̶��� SF_PROCEDURE_DEF ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfProcedureDefDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfProcedureDefDAO(SfUserDTO userAccount, SfProcedureDefDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfProcedureDefDTO dtoPara = (SfProcedureDefDTO)dtoParameter;
		super.sqlProducer = new SfProcedureDefModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ�������ת���̶����SF_PROCEDURE_DEF�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException {
		super.createData();
		getMessage().addParameterValue("��ת���̶���");
	}

	/**
	 * ���ܣ�������ת���̶����SF_PROCEDURE_DEF�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException {
		 super.updateData();
		getMessage().addParameterValue("��ת���̶���");
	}

	/**
	 * ���ܣ�ɾ����ת���̶����SF_PROCEDURE_DEF�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException {
		 super.deleteData();
		getMessage().addParameterValue("��ת���̶���");
	}

}