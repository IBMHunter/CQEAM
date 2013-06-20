package com.sino.ams.dzyh.dao;


import java.sql.Connection;
import java.util.Date;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhCatalogSetDTO;
import com.sino.ams.dzyh.model.EamDhCatalogSetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhCatalogSetDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhCatalogSetDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EamDhCatalogSetDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���ֵ�׺�Ʒ����(EAM) EAM_DH_CATALOG_SET ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCatalogSetDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhCatalogSetDAO(SfUserDTO userAccount, EamDhCatalogSetDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EamDhCatalogSetDTO dtoPara = (EamDhCatalogSetDTO)dtoParameter;
		super.sqlProducer = new EamDhCatalogSetModel((SfUserDTO)userAccount, dtoPara);
	}

	public void createData() throws DataHandleException {
		EamDhCatalogSetDTO eamDto = (EamDhCatalogSetDTO)dtoParameter;
		String lastDate = (new Date()).toLocaleString();
		int enabled = eamDto.getEnabled();
		try
		{
			if (enabled == 0)
				eamDto.setEndDate(lastDate);
			else
				eamDto.setEndDate("");
		}
		catch (CalendarException ex)
		{
			ex.printLog();
		}
		super.createData();
	}

	public void updateData() throws DataHandleException {
		EamDhCatalogSetDTO eamDto = (EamDhCatalogSetDTO)dtoParameter;
		String lastDate = (new Date()).toLocaleString();
		int enabled = eamDto.getEnabled();
		try
		{
			if (enabled==0)
				eamDto.setEndDate(lastDate);
			else
				eamDto.setEndDate("");
		}
		catch (CalendarException ex)
		{
			ex.printLog();
		}
		super.updateData();
	}
}
