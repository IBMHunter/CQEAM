package com.sino.ams.expand.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.expand.dto.AmsItemFilesDTO;
import com.sino.ams.expand.model.AmsItemFilesModel;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemFilesDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemFilesDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class AmsItemFilesDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��豸��ظ���(AMS) AMS_ITEM_FILES ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemFilesDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsItemFilesDAO(SfUserDTO userAccount, AmsItemFilesDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsItemFilesDTO dtoPara = (AmsItemFilesDTO)dtoParameter;
		super.sqlProducer = new AmsItemFilesModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������豸��ظ���(AMS)��AMS_ITEM_FILES�����ݡ�
	 * @return boolean
	 */
	public void createData()throws DataHandleException {
		 super.createData();
		getMessage().addParameterValue("������ظ���");
//		return operateResult;
	}

	/**
	 * ���ܣ������豸��ظ���(AMS)��AMS_ITEM_FILES�����ݡ�
	 * @return boolean
	 */
	public void updateData()throws DataHandleException{
		 super.updateData();
		getMessage().addParameterValue("������ظ���");
//		return operateResult;
	}

	/**
	 * ���ܣ�ɾ���豸��ظ���(AMS)��AMS_ITEM_FILES�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException{
//		boolean operateResult = super.deleteData();
         super.deleteData();
        getMessage().addParameterValue("������ظ���");
//		return operateResult;
	}

}