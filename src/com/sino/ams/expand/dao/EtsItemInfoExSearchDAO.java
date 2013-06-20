package com.sino.ams.expand.dao;


import java.sql.Connection;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.expand.dto.EtsItemInfoExSearchDTO;
import com.sino.ams.expand.model.EtsItemInfoExModel;


/**
 * <p>Title: EtsItemInfoExSearchDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoExSearchDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EtsItemInfoExSearchDAO extends AMSBaseDAO {


	/**
	 * ���ܣ��ʲ���չ��Ϣ��ETS_ITEM_INFO_EX ETS_ITEM_INFO_EX ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoExSearchDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsItemInfoExSearchDAO(SfUserDTO userAccount, EtsItemInfoExSearchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsItemInfoExSearchDTO dtoPara = (EtsItemInfoExSearchDTO)dtoParameter;
		super.sqlProducer = new EtsItemInfoExModel((SfUserDTO)userAccount, dtoPara);
	}

}