package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsChkLogDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: AmsAssetsChkLogModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsChkLogModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsAssetsChkLogModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ��̵��¼ AMS_ASSETS_CHK_LOG ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsChkLogDTO ���β���������
	 */
	public AmsAssetsChkLogModel(SfUserDTO userAccount,
								AmsAssetsChkLogDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ��̵��¼ AMS_ASSETS_CHK_LOG���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsChkLogDTO dto = (AmsAssetsChkLogDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " AMS_ASSETS_CHK_LOG("
						+ " BARCODE,"
						+ " CHK_TIMES,"
						+ " LAST_CHK_DATE,"
						+ " LAST_CHK_NO,"
						+ " ITEM_CODE,"
						+ " ITEM_NAME,"
						+ " ITEM_SPEC,"
						+ " ITEM_CATEGORY,"
						+ " RESPONSIBILITY_USER,"
						+ " RESPONSIBILITY_DEPT,"
						+ " ADDRESS_ID,"
						+ " ORGANIZATION_ID,"
						+ " HEADER_ID,"
						+ " BATCH_ID,"
						+ " ORDER_DTL_URL,"
						+ " SYN_STATUS,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " ORDER_TYPE,"
						+ " IS_EXIST"
						+ ") VALUES ("
						+ " ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";

		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getChkTimes());
		sqlArgs.add(dto.getLastChkNo());
		sqlArgs.add(dto.getItemCode());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getItemCategory());
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getAddressId());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(dto.getHeaderId());
		sqlArgs.add(dto.getBatchId());
		sqlArgs.add(dto.getOrderDtlUrl());
		
		sqlArgs.add( 0 );
//		sqlArgs.add(AssetsDictConstant.SYN_STATUS_NO);
		
		sqlArgs.add(dto.getCreatedBy());
		sqlArgs.add(dto.getOrderType());
		sqlArgs.add(dto.getIsExist());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ��̵��¼ AMS_ASSETS_CHK_LOG���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsChkLogDTO dto = (AmsAssetsChkLogDTO) dtoParameter;
		String sqlStr = "UPDATE AMS_ASSETS_CHK_LOG"
						+ " SET"
						+ " LAST_CHK_DATE = GETDATE(),"
						+ " LAST_CHK_NO = ?,"
						+ " ITEM_CODE = ?,"
						+ " ITEM_NAME = ?,"
						+ " ITEM_SPEC = ?,"
						+ " ITEM_CATEGORY = ?,"
						+ " RESPONSIBILITY_USER = ?,"
						+ " RESPONSIBILITY_DEPT = ?,"
						+ " ADDRESS_ID = ?,"
						+ " ORGANIZATION_ID = ?,"
						+ " HEADER_ID = ?,"
						+ " BATCH_ID = ?,"
						+ " ORDER_DTL_URL = ?,"
						+ " SYN_STATUS = ?,"
						+ " LAST_UPDATE_DATE = GETDATE(),"
						+ " LAST_UPDATE_BY = ?,"
						+ " ORDER_TYPE = ?,"
						+ " IS_EXIST = ?";
		if (dto.getOrderType().equals(AssetsDictConstant.ASS_CHK)) {
			sqlStr += ", CHK_TIMES = ISNULL(CHK_TIMES, 0) + 1";
		}
		sqlStr += " WHERE BARCODE = ?";
		sqlArgs.add(dto.getLastChkNo());
		sqlArgs.add(dto.getItemCode());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getItemCategory());
		sqlArgs.add(dto.getResponsibilityUser());
		sqlArgs.add(dto.getResponsibilityDept());
		sqlArgs.add(dto.getAddressId());
		sqlArgs.add(dto.getOrganizationId());
		sqlArgs.add(dto.getHeaderId());
		sqlArgs.add(dto.getBatchId());
		sqlArgs.add(dto.getOrderDtlUrl());
		sqlArgs.add(0);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getOrderType());
		sqlArgs.add(dto.getIsExist());
		sqlArgs.add(dto.getBarcode());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������̵��ʲ�����״̬ AMS_ASSETS_CHECK_STATUS������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getHasDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsChkLogDTO dto = (AmsAssetsChkLogDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " 1"
						+ " FROM"
						+ " AMS_ASSETS_CHK_LOG"
						+ " WHERE"
						+ " BARCODE = ?";
		sqlArgs.add(dto.getBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
