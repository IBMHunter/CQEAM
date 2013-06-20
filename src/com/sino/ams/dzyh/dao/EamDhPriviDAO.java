package com.sino.ams.dzyh.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhPriviDTO;
import com.sino.ams.dzyh.model.EamDhPriviModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhPriviDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhPriviDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamDhPriviDAO extends AMSBaseDAO {


	/**
	 * ���ܣ�Ȩ�޶����(EAM) EAM_DH_PRIVI ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhPriviDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhPriviDAO(SfUserDTO userAccount, EamDhPriviDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EamDhPriviDTO dtoPara = (EamDhPriviDTO)dtoParameter;
		super.sqlProducer = new EamDhPriviModel((SfUserDTO)userAccount, dtoPara);
	}
	public boolean doCheckDefaultflag(String defaultflag) throws QueryException
	{
		boolean success = false;
		EamDhPriviModel eamPriModel = (EamDhPriviModel)sqlProducer;
		com.sino.base.db.sql.model.SQLModel sqlModel = eamPriModel.getCheckDefaultflagModel(defaultflag);
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		success = sq.hasResult();
		return success;
	}
}