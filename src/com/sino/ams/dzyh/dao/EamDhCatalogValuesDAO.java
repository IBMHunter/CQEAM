package com.sino.ams.dzyh.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhCatalogValuesDTO;
import com.sino.ams.dzyh.model.EamDhCatalogValuesModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhCatalogValuesDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhCatalogValuesDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EamDhCatalogValuesDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_CATALOG_VALUES ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCatalogValuesDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhCatalogValuesDAO(SfUserDTO userAccount, EamDhCatalogValuesDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EamDhCatalogValuesDTO dtoPara = (EamDhCatalogValuesDTO)dtoParameter;
		super.sqlProducer = new EamDhCatalogValuesModel((SfUserDTO)userAccount, dtoPara);
	}

}