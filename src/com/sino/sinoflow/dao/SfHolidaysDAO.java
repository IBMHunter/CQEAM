package com.sino.sinoflow.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfHolidaysDTO;
import com.sino.sinoflow.model.SfHolidaysModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfHolidaysDAO</p>
 * <p>Description:�����Զ����ɷ������SfHolidaysDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfHolidaysDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�SF_HOLIDAYS ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfHolidaysDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfHolidaysDAO(SfUserBaseDTO userAccount, SfHolidaysDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfHolidaysDTO dtoPara = (SfHolidaysDTO)dtoParameter;
		super.sqlProducer = new SfHolidaysModel((SfUserBaseDTO)userAccount, dtoPara);
	}

}