package com.sino.ams.web.bts.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.web.bts.dto.EtsObjectFixfeeDTO;
import com.sino.ams.web.bts.model.EtsObjectFixfeeModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsObjectFixfeeDAO</p>
 * <p>Description:�����Զ����ɷ������EtsObjectFixfeeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class EtsObjectFixfeeDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectFixfeeDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsObjectFixfeeDAO(SfUserDTO userAccount, EtsObjectFixfeeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsObjectFixfeeDTO dtoPara = (EtsObjectFixfeeDTO)dtoParameter;
		super.sqlProducer = new EtsObjectFixfeeModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������վά�޳ɱ�(EAM)��ETS_OBJECT_FIXFEE�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException {
		 super.createData();
		getMessage().addParameterValue("��վά�޳ɱ�(EAM)");
	}

	/**
	 * ���ܣ����»�վά�޳ɱ�(EAM)��ETS_OBJECT_FIXFEE�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException {
		 super.updateData();
		getMessage().addParameterValue("��վά�޳ɱ�(EAM)");
	}

	/**
	 * ���ܣ�ɾ����վά�޳ɱ�(EAM)��ETS_OBJECT_FIXFEE�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException {
		 super.deleteData();
		getMessage().addParameterValue("��վά�޳ɱ�(EAM)");
	}

}