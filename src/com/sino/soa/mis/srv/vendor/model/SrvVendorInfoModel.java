package com.sino.soa.mis.srv.vendor.model;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.vendor.dto.SrvVendorInfoDTO;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title: SrvAssetBookModel</p>
 * <p>Description:�����Զ�����SQL��������SrvAssetBookModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzhipeng
 * DATE:2011-09-08
 * DSC:ͬ��������Ϣ
 */


public class SrvVendorInfoModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SrvAssetBookDTO ���β���������
	 */
	public SrvVendorInfoModel(SfUserDTO userAccount, SrvVendorInfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ������ʲ��˲����� SRV_ASSET_BOOK���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SrvVendorInfoDTO srvVendorInfoDTO = (SrvVendorInfoDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " ETS_MIS_PO_VENDORS("
			+"  VENDOR_ID,"
			+ " VENDOR_NAME,"
			+ " VENDOR_NAME_ALT,"
			+ " SEGMENT1,"
			+ " SUMMARY_FLAG,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ "	LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " SOURCE,"
			+ " VENDOR_TYPE"
			+ ") VALUES ("
			+ " NEWID(), ?, ?, ?, ?, GETDATE(), ?, GETDATE(), ?, 'MIS',0)";
		
		sqlArgs.add(srvVendorInfoDTO.getVendorName());
		sqlArgs.add(srvVendorInfoDTO.getVendorNameAlt());
		sqlArgs.add(srvVendorInfoDTO.getVendorNumber());
		sqlArgs.add(srvVendorInfoDTO.getVatFlag());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfUser.getUserId());
//		sqlArgs.add(srvVendorInfoDTO.getVendorTypeDisp());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����OU��֯��Ϣ���� SRV_OU_ORGANIZATION���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SrvVendorInfoDTO srvVendorInfoDTO = (SrvVendorInfoDTO)dtoParameter;
		String sqlStr = "UPDATE "
			+ " ETS_MIS_PO_VENDORS SET" 
			+ " VENDOR_NAME=?,"
			+ " VENDOR_NAME_ALT=?,"
			+ " SEGMENT1=?,"
			+ " SUMMARY_FLAG=?,"
			+ " CREATION_DATE=GETDATE(),"
			+ " CREATED_BY=?,"
			+ "	LAST_UPDATE_DATE=GETDATE(),"
			+ " LAST_UPDATE_BY=?,"
			+ " SOURCE='MIS',"
			+ " VENDOR_TYPE=0"
			+ " WHERE SEGMENT1=?"
			;
		sqlArgs.add(srvVendorInfoDTO.getVendorName());
		sqlArgs.add(srvVendorInfoDTO.getVendorNameAlt());
		sqlArgs.add(srvVendorInfoDTO.getVendorNumber());
		sqlArgs.add(srvVendorInfoDTO.getVatFlag());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(srvVendorInfoDTO.getVendorNumber());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	public SQLModel getEcouInforModel() {
		SQLModel sqlModel = new SQLModel();
			String sqlStr = "SELECT" 
			+"	ECOM.SEGMENT1 "
			+"	FROM ETS_MIS_PO_VENDORS ECOM " 
			+"  WHERE ECOM.SOURCE='MIS'";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
}