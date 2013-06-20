package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * <p>
 * Title: OrderQueryModel
 * </p>
 * <p>
 * Description:�����Զ�����SQL��������OrderQueryModel�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ����ʤ
 * @version 1.0
 */

public class OrderHeaderPrintModel extends AmsAssetsTransHeaderModel {

	/**
	 * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            AmsAssetsTransHeaderDTO ���β���������
	 */
	public OrderHeaderPrintModel(SfUserDTO userAccount,	 AmsAssetsTransHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADERҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
			String sqlStr = "SELECT"
					+ " AATH.TRANS_ID,"
					+ " AATH.TRANS_NO,"
					+ " AATH.TRANS_TYPE,"
					+ " AATH.TRANSFER_TYPE,"
					+ " AATH.TRANS_STATUS,"
					+ " AATH.FROM_ORGANIZATION_ID,"
					+ " EOCM.COMPANY,"
					+ " dbo.NVL(AMD.DEPT_NAME, EOCM.COMPANY) FROM_DEPT_NAME,"
					+ " AATH.RECEIVED_USER,"
					+ " AATH.CREATION_DATE,"
					+ " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
					+ " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
					+ " SU.USERNAME CREATED"
					+ " FROM"
					+ " AMS_ASSETS_TRANS_HEADER AATH,"
					+ " AMS_MIS_DEPT       AMD,"
					+ " ETS_OU_CITY_MAP    EOCM,"
					+ " SF_USER            SU"
					+ " WHERE"
					+ " AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
					+ " AND CONVERT(VARCHAR,AATH.FROM_DEPT) *= AMD.DEPT_CODE"
					+ " AND AATH.CREATED_BY = SU.USER_ID";
			
					if( dto.getTransType().equals(AssetsDictConstant.ASS_RED) && dto.getPrintType().compareTo("PRINT_TRANS_IN") == 0  ){
						//�����ĵ����ѯ SJ ADD
					}else{
						sqlStr+= " AND AATH.FROM_ORGANIZATION_ID = ?";
					}
					
					sqlStr+= " AND AATH.TRANS_TYPE = ?"
					+ " AND ( "
					+ SyBaseSQLUtil.isNull()
					+ " OR ? IS  NULL OR AATH.CREATION_DATE >= ISNULL(?, AATH.CREATION_DATE) )"
					+ " AND ( "
					+ SyBaseSQLUtil.isNull()
					+ "  OR ? IS  NULL OR AATH.CREATION_DATE <= ISNULL(?, AATH.CREATION_DATE) )"
					+ " AND ( "
					+ SyBaseSQLUtil.isNull()
					+ "  OR ? IS  NULL OR AATH.TRANSFER_TYPE = dbo.NVL(?, AATH.TRANSFER_TYPE) )"
					+ " AND ( "
					+ SyBaseSQLUtil.isNull()
					+ "  OR ? IS  NULL OR AATH.TRANS_NO LIKE dbo.NVL(?, AATH.TRANS_NO) )";
			if( dto.getTransType().equals(AssetsDictConstant.ASS_RED) && dto.getPrintType().compareTo("PRINT_TRANS_IN") == 0  ){
				//�����ĵ����ѯ SJ ADD
			}else{
				sqlArgs.add(userAccount.getOrganizationId());
			}
			
			sqlArgs.add(dto.getTransType());

			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getStartDate());

			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getSQLEndDate());

			sqlArgs.add(dto.getTransferType());
			sqlArgs.add(dto.getTransferType());
			sqlArgs.add(dto.getTransferType());

			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getTransNo());
			sqlArgs.add(dto.getTransNo());

			if (dto.getTransType().equals(AssetsDictConstant.ASS_RED)) {// ��������

				if (dto.getPrintType().compareTo("PRINT_TRANS_IN") == 0) {//�����ӡ
					// ����
					/* 2009-09-21 libo ����Ҫ�ϸ���Ʋ�ѯȨ�޶���ӡ� */
					if (userAccount.isProvAssetsManager()) {
						// ʡ�ʲ�����Ա��������
					} else if (userAccount.isComAssetsManager()) {
						sqlStr += " AND (AATH.TO_ORGANIZATION_ID = ?)";
						sqlArgs.add(userAccount.getOrganizationId());
					} else if (userAccount.isMtlAssetsManager()) {
						sqlStr += " AND ((AATH.TO_ORGANIZATION_ID = ?"
								+ " 		AND AATH.FA_CONTENT_CODE IN ("
								+ userAccount.getMtlMgrProps()
								+ "))"
								+ "        OR (? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
								+ " 				AND AATH.TO_ORGANIZATION_ID = ? AND CONVERT(VARCHAR,AATH.TO_DEPT) = CONVERT(VARCHAR,?) ))";
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getLoginName().toUpperCase());
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getDeptCode());
					} else if (userAccount.isDptAssetsManager()) {
						DTOSet ds = userAccount.getPriviDeptCodes();
						StringBuffer mngDepts = new StringBuffer("");
						if( null != ds ){
							for (int i = 0; i < ds.getSize(); i++) {
								mngDepts.append(((AmsMisDeptDTO) ds.getDTO(i))
										.getDeptCode());
								if (i < ds.getSize() - 1) {
									mngDepts.append(",");
								}
							}
						}
						sqlStr = sqlStr
								+ " AND ((AATH.TO_ORGANIZATION_ID=? AND CONVERT(INT,AATH.TO_DEPT) IN ("
								+ mngDepts.toString()
								+ "))"
								+ "      OR (? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
								+ " 		AND AATH.TO_ORGANIZATION_ID = ? AND CONVERT(VARCHAR,AATH.TO_DEPT) = CONVERT(VARCHAR,?)))";

						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getLoginName().toUpperCase());
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getDeptCode());

					} else {
						sqlStr = sqlStr
								+ " AND ? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
								+ " AND AATH.TO_ORGANIZATION_ID = ? AND CONVERT(VARCHAR,AATH.TO_DEPT) = CONVERT(VARCHAR,?)";
						sqlArgs.add(userAccount.getLoginName());
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getDeptCode());
					}
				} else {//������ӡ
					/* 2009-09-21 libo ����Ҫ�ϸ���Ʋ�ѯȨ�޶���ӡ� */
					if (userAccount.isProvAssetsManager()) {
						// ʡ�ʲ�����Ա��������
					} else if (userAccount.isComAssetsManager()) {
						sqlStr += " AND (AATH.FROM_ORGANIZATION_ID = ? )";
						sqlArgs.add(userAccount.getOrganizationId());
					} else if (userAccount.isMtlAssetsManager()) {
						sqlStr += " AND ((AATH.FROM_ORGANIZATION_ID = ?"
								+ " 		AND AATH.FA_CONTENT_CODE IN ("
								+ userAccount.getMtlMgrProps()
								+ "))"
								+ "        OR (? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
								+ " 				AND AATH.FROM_ORGANIZATION_ID = ? AND CONVERT(VARCHAR,AATH.FROM_DEPT) = CONVERT(VARCHAR,?)))";
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getLoginName().toUpperCase());
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getDeptCode());
					} else if (userAccount.isDptAssetsManager()) {
						DTOSet ds = userAccount.getPriviDeptCodes();
						StringBuffer mngDepts = new StringBuffer("");
						if( null != ds ){
							for (int i = 0; i < ds.getSize(); i++) {
								mngDepts.append(((AmsMisDeptDTO) ds.getDTO(i))
										.getDeptCode());
								if (i < ds.getSize() - 1) {
									mngDepts.append(",");
								}
							}
						}
						sqlStr = sqlStr
								+ " AND ((AATH.FROM_ORGANIZATION_ID = ? AND CONVERT(INT,AATH.FROM_DEPT) IN ("
								+ mngDepts.toString()
								+ "))"
								+ "      OR (? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
								+ " 		AND AATH.FROM_ORGANIZATION_ID = ? AND CONVERT(VARCHAR,AATH.FROM_DEPT) = CONVERT(VARCHAR,?)))";

						sqlArgs.add(userAccount.getOrganizationId());

						sqlArgs.add(userAccount.getLoginName().toUpperCase());
						sqlArgs.add(userAccount.getOrganizationId());
						sqlArgs.add(userAccount.getDeptCode());

					} else {
                        sqlStr = sqlStr
                                + " AND ? IN (SELECT SAL.SFACT_TASK_USERS FROM SF_ACT_PROCESS_V SAL WHERE SAL.SFACT_APPL_COLUMN_1 = AATH.TRANS_NO)"
                                + " AND AATH.FROM_ORGANIZATION_ID = ? AND CONVERT(VARCHAR,AATH.FROM_DEPT) = CONVERT(VARCHAR,?)";
                        sqlArgs.add(userAccount.getLoginName().toUpperCase());
                        sqlArgs.add(userAccount.getOrganizationId());
                        sqlArgs.add(userAccount.getDeptCode());
					}
				}
				if (dto.getPrintType().equals(AssetsWebAttributes.PRINT_TRANS_OUT)
                        ||dto.getPrintType().equals(AssetsWebAttributes.PRINT_TRANS_IN)) { // ������(����������)��ӡ
					StringBuffer acceptStatus = new StringBuffer();
					acceptStatus.append("'");
					acceptStatus.append(AssetsDictConstant.APPROVED);
					acceptStatus.append("','");
					acceptStatus.append(AssetsDictConstant.COMPLETED);
					acceptStatus.append("','");
					acceptStatus.append(AssetsDictConstant.ORDER_STS_ASSIGNED);
					acceptStatus.append("','");
					acceptStatus.append(AssetsDictConstant.ORDER_STS_CONFIRMD);
					acceptStatus.append("'");
					sqlStr = sqlStr
							+ " AND AATH.TRANS_STATUS IN ("
							+ acceptStatus
							+ ")";
				}
			} else if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS_OTHER)) { //��������
//				sqlStr += " AND (AATH.FROM_ORGANIZATION_ID = ? OR AATH.TO_ORGANIZATION_ID = ?)";
				sqlStr += " AND (AATH.TRANS_STATUS = ? OR AATH.TRANS_STATUS = ?)"; 
				sqlStr += " AND AATH.CREATED_BY = ?";
//				sqlArgs.add(userAccount.getOrganizationId());
//				sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(AssetsDictConstant.COMPLETED);
				sqlArgs.add(AssetsDictConstant.APPROVED); 
				sqlArgs.add(userAccount.getUserId());
			} else {// ��������
//				sqlStr += " AND (AATH.FROM_ORGANIZATION_ID = ? OR AATH.TO_ORGANIZATION_ID = ?)";
				sqlStr += " AND (AATH.TRANS_STATUS = ? OR AATH.TRANS_STATUS = ?)";
                sqlStr += " AND AATH.CREATED_BY = ?";
//				sqlArgs.add(userAccount.getOrganizationId());
//				sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(AssetsDictConstant.COMPLETED);
				sqlArgs.add(AssetsDictConstant.APPROVED);
                sqlArgs.add(userAccount.getUserId());
				// sqlStr += " AND EXISTS ("
				// + " SELECT"
				// + " NULL"
				// + " FROM"
				// + " SF_ACT_LOG SAL"
				// + " WHERE"
				// + " AATH.TRANS_ID = SAL.APP_ID"
				// + " AND AATH.TRANS_NO = SAL.APPLY_NUMBER"
				// + " AND (SAL.CUR_USERID = ? OR SAL.COMPLETE_USER = ?))";
				// sqlArgs.add(userAccount.getUserId());
				// sqlArgs.add(userAccount.getUserId());
			}
			if (dto.getTransType().equals(AssetsDictConstant.ASS_RED)) {
				sqlStr = sqlStr + " ORDER BY" + " AATH.TRANSFER_TYPE,"
						+ " AATH.CREATION_DATE DESC";
			} else {
				sqlStr += " ORDER BY AATH.CREATION_DATE DESC";
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}

		return sqlModel;
	}
}
