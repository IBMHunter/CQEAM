package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.newasset.model.AmsMisDeptModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsHrDeptDAO</p>
 * <p>Description:�����Զ����ɷ������AmsHrDeptDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsMisDeptDAO extends AMSBaseDAO {


	/**
	 * ���ܣ�MIS����(HR) AMS_MIS_DEPT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsHrDeptDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsMisDeptDAO(SfUserDTO userAccount, AmsMisDeptDTO dtoParameter,
						 Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsMisDeptDTO dtoPara = (AmsMisDeptDTO) dtoParameter;
		super.sqlProducer = new AmsMisDeptModel((SfUserDTO) userAccount,
												dtoPara);
	}

	/**
	 * ���ܣ����ݲ������ƻ�ȡ����������Ϣ
	 * @return AmsMisDeptDTO
	 * @throws QueryException
	 */
	public AmsMisDeptDTO getDeptByName() throws QueryException {
		AmsMisDeptDTO dept = new AmsMisDeptDTO();
		AmsMisDeptModel modelProducer = (AmsMisDeptModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getDeptByNameModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsMisDeptDTO.class.getName());
		simp.executeQuery();
		if (simp.hasResult()) {
			dept = (AmsMisDeptDTO) simp.getFirstDTO();
		}
		return dept;
	}

	/**
	 * ���ܣ���ȡ��OU��֯�µ����в���
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getAllMisDept() throws QueryException {
		AmsMisDeptModel modelProducer = (AmsMisDeptModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getAllMisDeptModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsMisDeptDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}
}
