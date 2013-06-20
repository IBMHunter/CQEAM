package com.sino.ams.inv.storeman.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.inv.storeman.dto.EamInvStoremanDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInvPriviModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ֿ�Ȩ�ޱ�(EAM) AMS_INV_PRIVI ���ݿ�SQL����㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param eamInvStoremanDTO EamInvStoremanDTO ���β���������
	 */
	public AmsInvPriviModel(BaseUserDTO userAccount, EamInvStoremanDTO eamInvStoremanDTO) {
		super(userAccount, eamInvStoremanDTO);
	}

	/**
	 * ���ܣ�����Զ������ʲ��̵��¼ AMS_INV_PRIVI���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamInvStoremanDTO dto = (EamInvStoremanDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_INV_PRIVI("
						+ " PRIVI_ID,"
						+ " USER_ID,"
						+ " INV_CODE,"
						+ " ACTION_CODE"
						+ ") VALUES ("
						+ " NEWID(), ?, ?, 'INV_IN')";

		sqlArgs.add(dto.getUserId());
		sqlArgs.add(dto.getWorkorderObjectNo());
//		sqlArgs.add(code);
		//sqlArgs.add(dto.getInvCode());
		//sqlArgs.add(dto.getActionCode());
		//sqlArgs.add(userAccount.getOrganizationId());

		//sqlArgs.add(AssetsDictConstant.SYN_STATUS_NO);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}
	
	/**
	 * ���ܣ�����Զ������ʲ��̵��¼ AMS_INV_PRIVI���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDatasCreateModel(String code) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamInvStoremanDTO dto = (EamInvStoremanDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_INV_PRIVI("
						+ " PRIVI_ID,"
						+ " USER_ID,"
						+ " INV_CODE,"
						+ " ACTION_CODE"
						+ ") VALUES ("
						+ "  NEWID() , ?, ?, ?)";

		sqlArgs.add(dto.getUserId());
		sqlArgs.add(dto.getWorkorderObjectNo());
		sqlArgs.add(code);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}
}
