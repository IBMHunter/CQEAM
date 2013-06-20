package com.sino.sinoflow.dao;


import java.sql.Connection;

import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfAdminAuthorityDTO;
import com.sino.sinoflow.model.SfAdminAuthorityModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfAdminAuthorityDAO</p>
 * <p>Description:�����Զ����ɷ������SfAdminAuthorityDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfAdminAuthorityDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�����ԱȨ����Ϣ SF_ADMIN_AUTHORITY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfAdminAuthorityDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfAdminAuthorityDAO(SfUserBaseDTO userAccount, SfAdminAuthorityDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfAdminAuthorityDTO dtoPara = (SfAdminAuthorityDTO)dtoParameter;
		super.sqlProducer = new SfAdminAuthorityModel((SfUserBaseDTO)userAccount, dtoPara);
	}
	
	/**
	 * ���ܣ��������Ա��
	 */
	public String[] spliptStr(String str) {
		String[] arr = null;
		if(str != null){
			arr = str.split(";");
		}
		return arr;
	}	
	
	/**
	 * ���ܣ���ɾ��Ӧ��
	 * @param ids String[]
	 * @throws DataHandleException 
	 */
	public void del(String[] ids) throws DataHandleException {
		DBOperator.updateRecord((( SfAdminAuthorityModel)sqlProducer).del(ids), conn);
	}

    public void add(String loginName) throws DataHandleException {
        DBOperator.updateRecord((( SfAdminAuthorityModel)sqlProducer).add(loginName), conn);
    }
}