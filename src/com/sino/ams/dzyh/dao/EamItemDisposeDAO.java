package com.sino.ams.dzyh.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamItemDisposeDTO;
import com.sino.ams.dzyh.model.EamItemDisposeModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamItemDisposeDAO</p>
 * <p>Description:�����Զ����ɷ������EamItemDisposeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamItemDisposeDAO extends AMSBaseDAO {

	/**
	 * ���ܣ����ʴ��õ���(EAM) EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamItemDisposeDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamItemDisposeDAO(SfUserDTO userAccount, EamItemDisposeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EamItemDisposeDTO dtoPara = (EamItemDisposeDTO)dtoParameter;
		super.sqlProducer = new EamItemDisposeModel((SfUserDTO)userAccount, dtoPara);
	}

}