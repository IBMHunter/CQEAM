package com.sino.sinoflow.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfWorkHourDTO;
import com.sino.sinoflow.model.SfWorkHourModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfWorkHourDAO</p>
 * <p>Description:�����Զ����ɷ������SfWorkHourDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfWorkHourDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ���ʱ���� SF_WORK_HOUR ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfWorkHourDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfWorkHourDAO(SfUserBaseDTO userAccount, SfWorkHourDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfWorkHourDTO dtoPara = (SfWorkHourDTO)dtoParameter;
		super.sqlProducer = new SfWorkHourModel((SfUserBaseDTO)userAccount, dtoPara);
	}

}