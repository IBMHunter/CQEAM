package com.sino.sinoflow.user.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfProjectDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.model.SfProjectModel;


/**
 * <p>Title: SfProjectDAO</p>
 * <p>Description:�����Զ����ɷ������SfProjectDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfProjectDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��������� SF_PROJECT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfProjectDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfProjectDAO(SfUserBaseDTO userAccount, SfProjectDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfProjectDTO dtoPara = (SfProjectDTO)dtoParameter;
		super.sqlProducer = new SfProjectModel((SfUserBaseDTO)userAccount, dtoPara);
	}

}