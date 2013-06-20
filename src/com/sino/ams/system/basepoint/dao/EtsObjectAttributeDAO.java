package com.sino.ams.system.basepoint.dao;


import java.sql.Connection;

import com.sino.ams.system.basepoint.dto.EtsObjectAttributeDTO;
import com.sino.ams.system.basepoint.model.EtsObjectAttributeModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsObjectAttributeDAO</p>
 * <p>Description:�����Զ����ɷ������EtsObjectAttributeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectAttributeDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ʲ��ص�������չ������ ETS_OBJECT_ATTRIBUTE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectAttributeDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsObjectAttributeDAO(SfUserDTO userAccount, EtsObjectAttributeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsObjectAttributeDTO dtoPara = (EtsObjectAttributeDTO)dtoParameter;
		super.sqlProducer = new EtsObjectAttributeModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������ʲ��ص�������չ�������ETS_OBJECT_ATTRIBUTE�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException {
//		boolean operateResult = super.createData();
		 super.createData();
		getMessage().addParameterValue("�ʲ��ص�������չ������");
//		return operateResult;
	}

	/**
	 * ���ܣ������ʲ��ص�������չ�������ETS_OBJECT_ATTRIBUTE�����ݡ�
	 * @return boolean
	 */
	public void updateData()throws DataHandleException{
//		boolean operateResult = super.updateData();
		 super.updateData();
		getMessage().addParameterValue("�ʲ��ص�������չ������");
//		return operateResult;
	}

	/**
	 * ���ܣ�ɾ���ʲ��ص�������չ�������ETS_OBJECT_ATTRIBUTE�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException{
//		boolean operateResult = super.deleteData();
		super.deleteData();
		getMessage().addParameterValue("�ʲ��ص�������չ������");
//		return operateResult;
	}

}