package com.sino.ams.system.trust.dao;


import java.sql.Connection;

import com.sino.ams.system.trust.dto.AmsMaintainFilesDTO;
import com.sino.ams.system.trust.model.AmsMaintainFilesModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsMaintainFilesDAO</p>
 * <p>Description:�����Զ����ɷ������AmsMaintainFilesDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsMaintainFilesDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ά��˾����ļ� AMS_MAINTAIN_FILES ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsMaintainFilesDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsMaintainFilesDAO(SfUserDTO userAccount, AmsMaintainFilesDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsMaintainFilesDTO dtoPara = (AmsMaintainFilesDTO)dtoParameter;
		super.sqlProducer = new AmsMaintainFilesModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������ά��˾����ļ���AMS_MAINTAIN_FILES�����ݡ�

	 */
	public void createData() throws DataHandleException {
      super.createData();
        getMessage().addParameterValue("��ά��˾����ļ�");

    }

	/**
	 * ���ܣ����´�ά��˾����ļ���AMS_MAINTAIN_FILES�����ݡ�

	 */
	public void updateData()throws DataHandleException{
  super.updateData();
		getMessage().addParameterValue("��ά��˾����ļ�");

	}

	/**
	 * ���ܣ�ɾ����ά��˾����ļ���AMS_MAINTAIN_FILES�����ݡ�

	 */
	public void deleteData()throws DataHandleException{
	  super.deleteData();
		getMessage().addParameterValue("��ά��˾����ļ�");

	}

}