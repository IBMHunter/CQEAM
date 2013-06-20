package com.sino.ams.system.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.system.user.dto.EtsOuCityMapDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: EtsOuCityMapModel</p>
 * <p>Description:�����Զ�����SQL��������EtsOuCityMapModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EtsOuCityMapModel extends AMSSQLProducer {

	/**
	 * ���ܣ�ETS_OU_CITY_MAP ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsOuCityMapDTO ���β���������
	 */
	public EtsOuCityMapModel(SfUserDTO userAccount, EtsOuCityMapDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		EtsOuCityMapDTO dto = (EtsOuCityMapDTO)dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " *"
						+ " FROM"
						+ " ETS_OU_CITY_MAP"
						+ " WHERE"
						+ " ORGANIZATION_ID = ?";
		sqlArgs.add(dto.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ���ȡʡ��˾OU��������SQL
	 * @return SQLModel
	 */
	public SQLModel getProOuNameModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " COMPANY"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " EOCM.ORGANIZATION_ID = ?";
		sqlArgs.add(servletConfig.getProvinceOrgId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * �ж��Ƿ�TD�û���SQL
	 * @param orgId
	 * @return
	 */
	public SQLModel getOrganizationTdPropModel(int orgId){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EOCM.IS_TD"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM"
						+ " WHERE"
						+ " EOCM.ORGANIZATION_ID = ?";
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ��ȡOrgId��Ӧ�����й�˾����
	 * @return
	 */
	public SQLModel getCompanyCodeByOrgIdModel(int orgId){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " COMPANY_CODE"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM";
		if (userAccount.getIsTd().equals("N")) {
			sqlStr += " WHERE EOCM.MIS_ORGANIZATION_ID = ?";
		} else {
			sqlStr += " WHERE EOCM.MATCH_ORGANIZATION_ID = ?";
		}
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ��ȡOrgId��Ӧ���й�˾����
	 * @param orgId
	 * @return
	 */
	public SQLModel getCompanyCodeModel(int orgId){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " RIGHT(BOOK_TYPE_CODE,4) COMPANY_CODE"
						+ " FROM"
						+ " ETS_OU_CITY_MAP"
						+ " WHERE 1=1 ";
		
		if(userAccount.getIsTt().equals("Y")) {
			sqlStr += " AND ORGANIZATION_ID= (SELECT MATCH_ORGANIZATION_ID FROM ETS_OU_CITY_MAP WHERE ORGANIZATION_ID= ? )";
		} else {
			if (userAccount.getIsTd().equals("N")) {
	    		sqlStr += " AND ORGANIZATION_ID = ?\n" ;
	    	} else {
	    		sqlStr += " AND MATCH_ORGANIZATION_ID = ?\n";
	    	}
		}
		
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
