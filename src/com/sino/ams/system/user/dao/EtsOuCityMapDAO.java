package com.sino.ams.system.user.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.EtsOuCityMapDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.model.EtsOuCityMapModel;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsOuCityMapDAO</p>
 * <p>Description:�����Զ����ɷ������EtsOuCityMapDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EtsOuCityMapDAO extends AMSBaseDAO {

	/**
	 * ���ܣ�ETS_OU_CITY_MAP ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsOuCityMapDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsOuCityMapDAO(SfUserDTO userAccount, EtsOuCityMapDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EtsOuCityMapDTO dtoPara = (EtsOuCityMapDTO)dtoParameter;
		super.sqlProducer = new EtsOuCityMapModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ���ȡʡ��˾OU��������
	 * @return String
	 * @throws QueryException
	 */
	public String getProvOuName() throws QueryException {
		String companyName = "";
		try {
			EtsOuCityMapModel modelProducer = (EtsOuCityMapModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getProOuNameModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				companyName = row.getStrValue("COMPANY");
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return companyName;
	}
	
	/**
	 * �ж��Ƿ�ΪTD�û�
	 * @return
	 * @throws QueryException
	 */
	public boolean isTdOrganization(int orgId) throws QueryException {
		boolean isTd = false;
		try {
			EtsOuCityMapModel modelProducer = (EtsOuCityMapModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getOrganizationTdPropModel(orgId);
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				isTd = row.getStrValue("IS_TD").equals("Y");
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return isTd;
	}
	
	/**
	 * ����orgId��ȡ��˾����
	 * @param orgId
	 * @return
	 * @throws QueryException
	 */
	public String getCompanyCodeByOrgId(int orgId) throws QueryException {
		String companyCode = "";
		try {
			EtsOuCityMapModel modelProducer = (EtsOuCityMapModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getCompanyCodeByOrgIdModel(orgId);
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				companyCode = row.getStrValue("COMPANY_CODE");
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return companyCode;
	}
	
	/**
	 * ��ȡOrgId��Ӧ���й�˾����
	 * @param orgId
	 * @return
	 * @throws QueryException
	 */
	public String getCompanyCode(int orgId) throws QueryException {
		String companyCode = "";
		try {
			EtsOuCityMapModel modelProducer = (EtsOuCityMapModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getCompanyCodeModel(orgId);
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				companyCode = row.getStrValue("COMPANY_CODE");
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return companyCode;
	}
}
