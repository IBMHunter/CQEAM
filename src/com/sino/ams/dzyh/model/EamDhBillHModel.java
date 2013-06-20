package com.sino.ams.dzyh.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.dzyh.dto.EamDhBillHDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: EamDhBillHModel</p>
 * <p>Description:�����Զ�����SQL��������EamDhBillHModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamDhBillHModel extends AMSSQLProducer {

	/**
	 * ���ܣ���ṹ����-H(EAM) EAM_DH_BILL_H ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhBillHDTO ���β���������
	 */
	public EamDhBillHModel(SfUserDTO userAccount, EamDhBillHDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ����ɱ�ṹ����-H(EAM) EAM_DH_BILL_H���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillHDTO eamDhBillH = (EamDhBillHDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " EAM_DH_BILL_H("
			+ " BILL_HEADER_ID,"
			+ " BILL_NO,"
			+ " BILL_STATUS,"
			+ " ORG_ID,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE"
			+ ") VALUES ("
			+ "  NEWID() , ?, ?, ?, ?, GETDATE(), ?, GETDATE())";
	
		sqlArgs.add(eamDhBillH.getBillNo());
		sqlArgs.add(eamDhBillH.getBillStatus());
		sqlArgs.add(eamDhBillH.getOrgId());
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(userAccount.getUserId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ṹ����-H(EAM) EAM_DH_BILL_H���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamDhBillHDTO eamDhBillH = (EamDhBillHDTO)dtoParameter;
			String sqlStr = "UPDATE EAM_DH_BILL_H"
				+ " SET"
				+ " BILL_NO = ?,"
				+ " BILL_STATUS = ?,"
				+ " ORG_ID = ?,"
				+ " CREATED_BY = ?,"
				+ " CREATION_DATE = ?,"
				+ " LAST_UPDATE_BY = ?,"
				+ " LAST_UPDATE_DATE = ?"
				+ " WHERE"
				+ " BILL_HEADER_ID = ?";
		
			sqlArgs.add(eamDhBillH.getBillNo());
			sqlArgs.add(eamDhBillH.getBillStatus());
			sqlArgs.add(eamDhBillH.getOrgId());
			sqlArgs.add(eamDhBillH.getCreatedBy());
			sqlArgs.add(eamDhBillH.getCreationDate());
			sqlArgs.add(eamDhBillH.getLastUpdateBy());
			sqlArgs.add(eamDhBillH.getLastUpdateDate());
			sqlArgs.add(eamDhBillH.getBillHeaderId());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ṹ����-H(EAM) EAM_DH_BILL_H������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EamDhBillHDTO eamDhBillH = (EamDhBillHDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " BILL_HEADER_ID,"
			+ " BILL_NO,"
			+ " BILL_STATUS,"
			+ " ORG_ID,"
			+ " CREATED_BY,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " LAST_UPDATE_DATE"
			+ " FROM"
			+ " EAM_DH_BILL_H"
			+ " WHERE"
			+ " BILL_HEADER_ID = ?";
		sqlArgs.add(eamDhBillH.getBillHeaderId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɱ�ṹ����-H(EAM) EAM_DH_BILL_Hҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EamDhBillHDTO eamDhBillH = (EamDhBillHDTO)dtoParameter;
			String sqlStr = "SELECT "
				+ " EDBH.BILL_HEADER_ID,"
				+ " EDBH.ORG_ID,"
				+ " EOCM.COMPANY EDBH_COMPANY,"
				+ " EDBH.BILL_NO,"
				+ " EDBH.CREATED_BY,"
				+ " SU.USERNAME EDBH_USERNAME,"
				+ " EDBH.CREATION_DATE,"
				+" CASE WHEN CONVERT(VARCHAR,EDBH.BILL_STATUS)='1' THEN '�����' WHEN CONVERT(VARCHAR,EDBH.BILL_STATUS)='0'THEN '������' ELSE '����' END BILL_STATUS "
				+ " FROM"
				+ " EAM_DH_BILL_H 	EDBH,"
				+ " SF_USER 		SU,"
				+ " ETS_OU_CITY_MAP EOCM"
				+ " WHERE"
				+ " EDBH.CREATED_BY=SU.USER_ID"
				+ " AND EDBH.ORG_ID=EOCM.ORGANIZATION_ID"
				+ " AND EDBH.ORG_ID = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EDBH.ORG_ID)))"
				+ " AND EDBH.CREATION_DATE >= ISNULL(?, EDBH.CREATION_DATE)"
				+ " AND EDBH.CREATION_DATE <= ISNULL(?, EDBH.CREATION_DATE)"
				+ " AND EDBH.BILL_NO LIKE dbo.NVL(?, EDBH.BILL_NO)"
				+ " AND EDBH.BILL_STATUS = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EDBH.BILL_STATUS)))";
			sqlArgs.add(eamDhBillH.getOrgId());
			sqlArgs.add(eamDhBillH.getStartDate());
			sqlArgs.add(eamDhBillH.getEndDate());
			sqlArgs.add("%" + eamDhBillH.getBillNo() + "%");
			sqlArgs.add(eamDhBillH.getBillStatus());
		
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

}