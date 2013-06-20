package com.sino.ams.expand.dao;


import java.sql.Connection;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.expand.dto.EtsItemInfoExDTO;
import com.sino.ams.expand.model.EtsItemInfoExModel;


/**
 * <p>Title: EtsItemInfoExDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoExDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EtsItemInfoExDAO extends AMSBaseDAO {


	/**
	 * ���ܣ��ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EX ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoExDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsItemInfoExDAO(SfUserDTO userAccount, EtsItemInfoExDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsItemInfoExDTO dtoPara = (EtsItemInfoExDTO)dtoParameter;
		super.sqlProducer = new EtsItemInfoExModel((SfUserDTO)userAccount, dtoPara);
	}

}