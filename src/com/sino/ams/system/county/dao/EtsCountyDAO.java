package com.sino.ams.system.county.dao;


import java.sql.Connection;

import com.sino.ams.system.county.dto.EtsCountyDTO;
import com.sino.ams.system.county.model.sybase.EtsCountyModelImpl;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsCountyDAO</p>
 * <p>Description:�����Զ����ɷ������EtsCountyDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zz_jlc
 * @version 1.0
 */


public class EtsCountyDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����ر�(EAM) ETS_COUNTY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsCountyDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsCountyDAO(SfUserDTO userAccount, EtsCountyDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsCountyDTO dtoPara = (EtsCountyDTO)dtoParameter;
		super.sqlProducer = new EtsCountyModelImpl((SfUserDTO)userAccount, dtoPara);
	}

}