package com.sino.ams.inv.storeman.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.inv.storeman.dto.EamInvStoremanDTO;
import com.sino.ams.inv.storeman.model.AmsInvPriviModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInvPriviDAO extends AMSBaseDAO {

	AmsInvPriviModel amsInvPriviModel = null;
	
	/**
	 * ���ܣ�Ĭ�ϲֹ�Ա��(EAM) EAM_INV_STOREMAN ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamInvStoremanDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsInvPriviDAO(SfUserDTO userAccount, EamInvStoremanDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		amsInvPriviModel = (AmsInvPriviModel)sqlProducer;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		super.sqlProducer = new AmsInvPriviModel((SfUserDTO) userAccount, (EamInvStoremanDTO) dtoParameter);
	}

	/**
	 * ���ܣ����ֿ⸳Ȩ��(EAM)�� AMS_INV_PRIVI�����ݡ�
	 */
	public void createData() throws DataHandleException {
		super.createData();
	}
	
	public void createDatas(String code) throws DataHandleException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
		sqlModel = amsInvPriviModel.getDatasCreateModel(code);
		if (sqlModel != null && !sqlModel.isEmpty()) {
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		}
	}
}
