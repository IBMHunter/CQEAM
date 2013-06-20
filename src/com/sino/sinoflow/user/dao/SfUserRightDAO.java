package com.sino.sinoflow.user.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.dto.SfUserRightDTO;
import com.sino.sinoflow.user.model.SfUserRightModel;


/**
 * <p>Title: SfUserRightDAO</p>
 * <p>Description:�����Զ����ɷ������SfUserRightDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfUserRightDAO extends BaseDAO {

	/**
	 * ���ܣ�SF_USER_RIGHT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfUserRightDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfUserRightDAO(SfUserBaseDTO userAccount, SfUserRightDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		SfUserRightDTO dtoPara = (SfUserRightDTO)dtoParameter;
		super.sqlProducer = new SfUserRightModel((SfUserBaseDTO)userAccount, dtoPara);
	}
}
