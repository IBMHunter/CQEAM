package com.sino.ams.web.item.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.web.item.dto.EtsItemFixfeeDTO;
import com.sino.ams.web.item.model.EtsItemFixfeeModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsItemFixfeeDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemFixfeeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class EtsItemFixfeeDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�ʵ���ʲ�ά�޳ɱ�(EAM) ETS_ITEM_FIXFEE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemFixfeeDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsItemFixfeeDAO(SfUserDTO userAccount, EtsItemFixfeeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EtsItemFixfeeDTO dtoPara = (EtsItemFixfeeDTO)dtoParameter;
		super.sqlProducer = new EtsItemFixfeeModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)��ETS_ITEM_FIXFEE�����ݡ�

	 */
	public void createData() throws DataHandleException {
  super.createData();
        getMessage().addParameterValue("ʵ���ʲ�ά�޳ɱ�(EAM)");

    }

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)��ETS_ITEM_FIXFEE�����ݡ�

	 */
	public void updateData() throws DataHandleException{
  super.updateData();
		getMessage().addParameterValue("ʵ���ʲ�ά�޳ɱ�(EAM)");

	}

	/**
	 * ���ܣ�ɾ��ʵ���ʲ�ά�޳ɱ�(EAM)��ETS_ITEM_FIXFEE�����ݡ�

	 */
	public void deleteData() throws DataHandleException{
  super.deleteData();
		getMessage().addParameterValue("ʵ���ʲ�ά�޳ɱ�(EAM)");

	}

}