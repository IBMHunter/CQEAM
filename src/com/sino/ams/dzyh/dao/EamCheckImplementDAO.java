package com.sino.ams.dzyh.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamCheckImplementDTO;
import com.sino.ams.dzyh.model.EamCheckImplementModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamCheckImplementDAO</p>
 * <p>Description:�����Զ����ɷ������EamCheckImplementDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamCheckImplementDAO extends AMSBaseDAO {


	/**
	 * ���ܣ����ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamCheckImplementDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamCheckImplementDAO(SfUserDTO userAccount, EamCheckImplementDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EamCheckImplementDTO dtoPara = (EamCheckImplementDTO)dtoParameter;
		super.sqlProducer = new EamCheckImplementModel((SfUserDTO)userAccount, dtoPara);
	}

}