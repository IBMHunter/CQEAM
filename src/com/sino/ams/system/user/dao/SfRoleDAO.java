package com.sino.ams.system.user.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfRoleDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.model.SfRoleModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: SfRoleDAO</p>
 * <p>Description:�����Զ����ɷ������SfRoleDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfRoleDAO extends BaseDAO {

	/**
	 * ���ܣ�SF_ROLE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfRoleDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfRoleDAO(SfUserDTO userAccount, SfRoleDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		SfRoleDTO dtoPara = (SfRoleDTO)dtoParameter;
		super.sqlProducer = new SfRoleModel((SfUserDTO)userAccount, dtoPara);
	}
}
