package com.sino.sinoflow.framework.security.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.framework.security.dto.SfUserAuthorityDTO;
import com.sino.sinoflow.framework.security.model.SfUserAuthorityModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfUserAuthorityDAO</p>
 * <p>Description:�����Զ����ɷ������SfUserAuthorityDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfUserAuthorityDAO extends BaseDAO {

//	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��Ñ�ְλȨ����Ϣ SF_USER_AUTHORITY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfUserAuthorityDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfUserAuthorityDAO(SfUserBaseDTO userAccount, SfUserAuthorityDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfUserAuthorityDTO dtoPara = (SfUserAuthorityDTO)dtoParameter;
		super.sqlProducer = new SfUserAuthorityModel((SfUserBaseDTO)userAccount, dtoPara);
	}

}