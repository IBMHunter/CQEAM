package com.sino.ams.inv.dzyh.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.inv.dzyh.dto.EamDhBillLDTO;
import com.sino.ams.inv.dzyh.model.EamDhChgLogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dto.BaseUserDTO;

public class EamDhChgLogDAO extends AMSBaseDAO {

	EamDhChgLogModel eamDhChgLogModel = null;
	
	/**
	 * ���ܣ�Ĭ�ϲֹ�Ա��(EAM) EAM_DH_CHG_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhBillLDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhChgLogDAO(SfUserDTO userAccount, EamDhBillLDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		eamDhChgLogModel = (EamDhChgLogModel)sqlProducer;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		super.sqlProducer = new EamDhChgLogModel((SfUserDTO) userAccount, (EamDhBillLDTO) dtoParameter);
	}

	/**
	 * ���ܣ���¼�䶯��ʷ(EAM)�� EAM_DH_CHG_LOG�����ݡ�
	 */
	public void createData(String barcode, String catalogValueId, String oldDept, String newDept, String oldAddress, String newAddress, String oldUser, String newUser, String oldMaintain, String newMaintain) throws DataHandleException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
		sqlModel = eamDhChgLogModel.getDataCreateModel(barcode, catalogValueId, oldDept, newDept, oldAddress, newAddress, oldUser, newUser, oldMaintain, newMaintain);
		if (sqlModel != null && !sqlModel.isEmpty()) {
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		}
	}
}
