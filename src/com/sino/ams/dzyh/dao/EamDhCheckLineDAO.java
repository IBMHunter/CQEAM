package com.sino.ams.dzyh.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.model.EamDhCheckLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhCheckLineDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhCheckLineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EamDhCheckLineDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���ֵ�׺��̵㹤���б� EAM_DH_CHECK_LINE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCheckLineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhCheckLineDAO(SfUserDTO userAccount, EamDhCheckLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EamDhCheckLineDTO dtoPara = (EamDhCheckLineDTO)dtoParameter;
		super.sqlProducer = new EamDhCheckLineModel((SfUserDTO)userAccount, dtoPara);
	}
}
