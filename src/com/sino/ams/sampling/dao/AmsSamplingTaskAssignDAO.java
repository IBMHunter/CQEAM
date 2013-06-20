package com.sino.ams.sampling.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.dto.AmsSamplingTaskAssignDTO;
import com.sino.ams.sampling.model.AmsSamplingTaskAssignModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsSamplingTaskAssignDAO</p>
 * <p>Description:�����Զ����ɷ������AmsSamplingTaskAssignDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsSamplingTaskAssignDAO extends AMSBaseDAO {

	/**
	 * ���ܣ������������ AMS_SAMPLING_TASK_ASSIGN ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsSamplingTaskAssignDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsSamplingTaskAssignDAO(SfUserDTO userAccount, AmsSamplingTaskAssignDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsSamplingTaskAssignDTO dto = (AmsSamplingTaskAssignDTO)dtoParameter;
		SfUserDTO user = (SfUserDTO)userAccount;
		sqlProducer = new AmsSamplingTaskAssignModel(user, dto);
	}
}
