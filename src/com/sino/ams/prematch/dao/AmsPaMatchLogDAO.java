package com.sino.ams.prematch.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.prematch.dto.AmsPaMatchLogDTO;
import com.sino.ams.prematch.model.AmsPaMatchLogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsPaMatchLogDAO</p>
 * <p>Description:�����Զ����ɷ������AmsPaMatchLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsPaMatchLogDAO extends AMSBaseDAO {

	/**
	 * ���ܣ�EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ�� AMS_PA_MATCH_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsPaMatchLogDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsPaMatchLogDAO(SfUserDTO userAccount, AmsPaMatchLogDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsPaMatchLogDTO dto = (AmsPaMatchLogDTO)dtoParameter;
		sqlProducer = new AmsPaMatchLogModel((SfUserDTO)userAccount, dto);
	}
}
