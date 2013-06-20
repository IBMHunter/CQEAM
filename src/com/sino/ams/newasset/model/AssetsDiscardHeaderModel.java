package com.sino.ams.newasset.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: AmsAssetsTransHeaderModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransHeaderModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsDiscardHeaderModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
	 * @param userAccount  BaseUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter DTO ���β���������
	 */
	public AssetsDiscardHeaderModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
			String sqlStr = "INSERT INTO "
					+ " AMS_ASSETS_TRANS_HEADER("
					+ " TRANS_ID,"
					+ " TRANS_NO,"
					+ " TRANS_TYPE,"
					+ " TRANSFER_TYPE,"
					+ " TRANS_STATUS,"
					+ " FROM_ORGANIZATION_ID,"
					+ " TO_ORGANIZATION_ID,"
					+ " FROM_DEPT,"
					+ " FROM_GROUP,"
					+ " TO_DEPT,"
					+ " FROM_OBJECT_NO,"
					+ " TO_OBJECT_NO,"
					+ " FROM_PERSON,"
					+ " TO_PERSON,"
					+ " CREATED_REASON,"
					+ " CREATION_DATE,"
					+ " CREATED_BY,"
					+ " RECEIVED_USER,"
					+ " TRANS_DATE,"
					+ " FA_CONTENT_CODE,"
					+ " LOSSES_NAME,"
					+ " LOSSES_DATE,"
					+ " IS_THRED"
					+ ") VALUES ("
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?)";
			sqlArgs.add(dto.getTransId());
			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getTransType());
			sqlArgs.add(dto.getTransferType());
			sqlArgs.add(dto.getTransStatus());
			sqlArgs.add(dto.getFromOrganizationId());
			sqlArgs.add(dto.getToOrganizationId());
			sqlArgs.add(dto.getFromDept());
			sqlArgs.add(dto.getFromGroup());
			sqlArgs.add(dto.getToDept());
			sqlArgs.add(dto.getFromObjectNo());
			sqlArgs.add(dto.getToObjectNo());
			sqlArgs.add(dto.getFromPerson());
			sqlArgs.add(dto.getToPerson());
			sqlArgs.add(dto.getCreatedReason());
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getReceivedUser());
			sqlArgs.add(dto.getTransDate());
			sqlArgs.add(dto.getFaContentCode());
			sqlArgs.add(dto.getLossesName());
			sqlArgs.add(dto.getLossesDate());
			sqlArgs.add(dto.getThred());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
			String sqlStr = "UPDATE AMS_ASSETS_TRANS_HEADER" + " SET"
					+ " TRANS_NO = ?," + " TRANS_STATUS = ?,"
					+ " TO_ORGANIZATION_ID = ?," + " FROM_DEPT = ?,"
					+ " TO_DEPT = ?," + " FROM_OBJECT_NO = ?,"
					+ " TO_OBJECT_NO = ?," + " FROM_PERSON = ?,"
					+ " TO_PERSON = ?," + " CREATED_REASON = ?,"
					+ " LAST_UPDATE_DATE = GETDATE()," + " LAST_UPDATE_BY = ?,"
					+ " RECEIVED_USER = ?," + " FA_CONTENT_CODE = ?,"
					+ " TRANS_DATE = ?," + " LOSSES_DATE = ?,"
					+ " LOSSES_NAME = ?" + " WHERE" + " TRANS_ID = ?";

			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getTransStatus());
			sqlArgs.add(dto.getToOrganizationId());
			sqlArgs.add(dto.getFromDept());
			sqlArgs.add(dto.getToDept());
			sqlArgs.add(dto.getFromObjectNo());
			sqlArgs.add(dto.getToObjectNo());
			sqlArgs.add(dto.getFromPerson());
			sqlArgs.add(dto.getToPerson());
			sqlArgs.add(dto.getCreatedReason());
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getReceivedUser());
			sqlArgs.add(dto.getFaContentCode());
			sqlArgs.add(dto.getTransDate());
			sqlArgs.add(dto.getLossesDate());
			sqlArgs.add(dto.getLossesName());
			sqlArgs.add(dto.getTransId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}


	/**
	 * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		String sqlStr = " SELECT"
				+ " AATH.TRANS_ID,"
				+ " AATH.TRANS_NO,"
				+ " AATH.FROM_DEPT,"
				+ " AATH.TO_DEPT,"
				+ " AATH.TRANS_TYPE,"
				+ " AATH.TRANSFER_TYPE,"
				+ " AATH.TRANS_STATUS,"
				+ " AATH.TRANS_DATE,"
				+ " AATH.CREATION_DATE,"
				+ " AATH.CREATED_BY,"
				+ " AATH.LAST_UPDATE_DATE,"
				+ " AATH.LAST_UPDATE_BY,"
				+ " AATH.CANCELED_DATE,"
				+ " AATH.CANCELED_REASON,"
				+ " AATH.CREATED_REASON,"
				+ " AATH.APPROVED_DATE,"
				+ " AATH.FROM_ORGANIZATION_ID,"
				+ " AATH.FROM_GROUP,"
				+ " AATH.FA_CONTENT_CODE,"
				+ " AATH.LOSSES_NAME,"
				+ " AATH.LOSSES_DATE,"
				+ " AATH.IS_THRED,"
				+ " AMD.DEPT_NAME FROM_DEPT_NAME,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
				+ " AMS_PUB_PKG.GET_FLEX_VALUE(AATH.FA_CONTENT_CODE, 'FA_CONTENT_CODE') FA_CONTENT_NAME,"
				+ " SU.USERNAME CREATED," + " SU.EMAIL,"
				+ " SU.MOBILE_PHONE PHONE_NUMBER,"
				+ " SG.GROUP_NAME FROM_GROUP_NAME," + "0 GROUP_PROP,"
				+ " EOCM.BOOK_TYPE_CODE," + " EOCM.BOOK_TYPE_NAME,"
				+ " EOCM.COMPANY FROM_COMPANY_NAME,"
				+ " AMD2.DEPT_NAME USER_DEPT_NAME,"
				+ " AATH.TO_ORGANIZATION_ID,"
				+ " EOCM2.COMPANY TO_COMPANY_NAME" + " FROM"
				+ " AMS_ASSETS_TRANS_HEADER AATH,"
				+ " ETS_OU_CITY_MAP         EOCM,"
				+ " AMS_MIS_DEPT            AMD,"
				+ " SF_GROUP                SG,"
				+ " SF_USER                 SU,"
				+ " AMS_MIS_EMPLOYEE        AME,"
				+ " AMS_MIS_DEPT            AMD2,"
				+ " ETS_OU_CITY_MAP         EOCM2" + " WHERE"
				+ " AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
				+ " AND AATH.FROM_DEPT *= AMD.DEPT_CODE"
				+ " AND AATH.FROM_GROUP = SG.GROUP_ID"
				+ " AND AATH.CREATED_BY = SU.USER_ID"
				+ " AND SU.EMPLOYEE_NUMBER *= AME.EMPLOYEE_NUMBER"
				+ " AND AME.DEPT_CODE *= AMD2.DEPT_CODE"
				+ " AND AATH.TO_ORGANIZATION_ID *= EOCM2.ORGANIZATION_ID"
				+ " AND TRANS_ID = ?";
		sqlArgs.add(dto.getTransId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ���������
	 * @param transId String
	 * @return SQLModel
	 */
	public SQLModel getOrderCancelModel(String transId) {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "UPDATE"
                + " AMS_ASSETS_TRANS_HEADER AATH"
                + " SET"
				+ " AATH.TRANS_STATUS = ?,"
                + " AATH.CANCELED_DATE = GETDATE(),"
				+ " AATH.CANCELED_REASON = ?,"
				+ " AATH.LAST_UPDATE_DATE = GETDATE(),"
				+ " AATH.LAST_UPDATE_BY = ?"
                + " WHERE"
                + " AATH.TRANS_ID = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(AssetsDictConstant.CANCELED);
		sqlArgs.add(AssetsDictConstant.CANCAL_REASON);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(transId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
