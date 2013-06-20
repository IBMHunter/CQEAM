package com.sino.ams.web.ele.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.web.ele.dto.EtsObjectEleDTO;
import com.sino.ams.web.ele.model.EtsObjectEleModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsObjectEleDAO</p>
 * <p>Description:�����Զ����ɷ������EtsObjectEleDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class EtsObjectEleDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���վ���ά����(EAM) ETS_OBJECT_ELE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectEleDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsObjectEleDAO(SfUserDTO userAccount, EtsObjectEleDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsObjectEleDTO dtoPara = (EtsObjectEleDTO)dtoParameter;
		super.sqlProducer = new EtsObjectEleModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������վ���ά����(EAM)��ETS_OBJECT_ELE�����ݡ�

	 */
	public  void createData() throws DataHandleException {
 super.createData();
        getMessage().addParameterValue("��վ���ά����(EAM)");

    }

	/**
	 * ���ܣ����»�վ���ά����(EAM)��ETS_OBJECT_ELE�����ݡ�

	 */
	public void updateData() throws DataHandleException {
     super.updateData();
        getMessage().addParameterValue("��վ���ά����(EAM)");

    }

	/**
	 * ���ܣ�ɾ����վ���ά����(EAM)��ETS_OBJECT_ELE�����ݡ�

	 */
	public void deleteData() throws DataHandleException {
      super.deleteData();
        getMessage().addParameterValue("��վ���ά����(EAM)");

    }

}