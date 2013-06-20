package com.sino.ams.match.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.match.dto.EtsItemMatchRecDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>
 * Title: EtsItemMatchRecModel
 * </p>
 * <p>
 * Description:�����Զ�����SQL��������EtsItemMatchRecModel�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author V-yuanshuai
 * @version 1.0
 */

public class EtsItemMatchRecModel extends AMSSQLProducer {

	/**
	 * ���ܣ�ETS_ITEM_MATCH_REC ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            EtsItemMatchRecDTO ���β���������
	 */
	public EtsItemMatchRecModel(SfUserDTO userAccount,
			EtsItemMatchRecDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ�����ETS_ITEM_MATCH_REC���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO etsItemMatchRec = (EtsItemMatchRecDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "INSERT INTO " + " ETS_ITEM_MATCH_TD_REC(" + " ID,"
					+ " MATCH_DATE," + " MATCH_USER_ID," + " SYSTEM_ID,"
					+ " ASSET_ID," + " MATCH_TYPE," + " OLD_FINANCE_PROP,"
					+ " NEW_FINANCE_PROP" + ") VALUES ("
					+ " NEWID(), ?, ?, ?, ?, ?, ?, ?)";
			try {
				sqlArgs.add(etsItemMatchRec.getMatchDate());
			} catch (CalendarException ex) {
				ex.printLog();
				throw new SQLModelException(ex);
			}
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "INSERT INTO " + " ETS_ITEM_MATCH_REC(" + " ID,"
					+ " MATCH_DATE," + " MATCH_USER_ID," + " SYSTEM_ID,"
					+ " ASSET_ID," + " MATCH_TYPE," + " OLD_FINANCE_PROP,"
					+ " NEW_FINANCE_PROP" + ") VALUES ("
					+ " NEWID(), ?, ?, ?, ?, ?, ?, ?)";
			try {
				sqlArgs.add(etsItemMatchRec.getMatchDate());
			} catch (CalendarException ex) {
				ex.printLog();
				throw new SQLModelException(ex);
			}
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ETS_ITEM_MATCH_REC���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO etsItemMatchRec = (EtsItemMatchRecDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_MATCH_TD_REC" + " SET"
					+ " MATCH_DATE = ?," + " MATCH_USER_ID = ?,"
					+ " SYSTEM_ID = ?," + " ASSET_ID = ?," + " MATCH_TYPE = ?,"
					+ " OLD_FINANCE_PROP = ?," + " NEW_FINANCE_PROP = ?"
					+ " WHERE" + " ID = ?";
			try {
				sqlArgs.add(etsItemMatchRec.getMatchDate());
			} catch (CalendarException ex) {
				ex.printLog();
				throw new SQLModelException(ex);
			}
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());
			sqlArgs.add(etsItemMatchRec.getId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_MATCH_REC" + " SET"
					+ " MATCH_DATE = ?," + " MATCH_USER_ID = ?,"
					+ " SYSTEM_ID = ?," + " ASSET_ID = ?," + " MATCH_TYPE = ?,"
					+ " OLD_FINANCE_PROP = ?," + " NEW_FINANCE_PROP = ?"
					+ " WHERE" + " ID = ?";
			try {
				sqlArgs.add(etsItemMatchRec.getMatchDate());
			} catch (CalendarException ex) {
				ex.printLog();
				throw new SQLModelException(ex);
			}
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());
			sqlArgs.add(etsItemMatchRec.getId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ETS_ITEM_MATCH_REC����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO etsItemMatchRec = (EtsItemMatchRecDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "DELETE FROM" + " ETS_ITEM_MATCH_TD_REC" + " WHERE"
					+ " ID = ?";
			sqlArgs.add(etsItemMatchRec.getId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "DELETE FROM" + " ETS_ITEM_MATCH_REC" + " WHERE"
					+ " ID = ?";
			sqlArgs.add(etsItemMatchRec.getId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ETS_ITEM_MATCH_REC������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO etsItemMatchRec = (EtsItemMatchRecDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT " + " ID," + " MATCH_DATE,"
					+ " MATCH_USER_ID," + " SYSTEM_ID," + " ASSET_ID,"
					+ " MATCH_TYPE," + " OLD_FINANCE_PROP,"
					+ " NEW_FINANCE_PROP" + " FROM" + " ETS_ITEM_MATCH_TD_REC"
					+ " WHERE" + " ID = ?";
			sqlArgs.add(etsItemMatchRec.getId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT " + " ID," + " MATCH_DATE,"
					+ " MATCH_USER_ID," + " SYSTEM_ID," + " ASSET_ID,"
					+ " MATCH_TYPE," + " OLD_FINANCE_PROP,"
					+ " NEW_FINANCE_PROP" + " FROM" + " ETS_ITEM_MATCH_REC"
					+ " WHERE" + " ID = ?";
			sqlArgs.add(etsItemMatchRec.getId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ETS_ITEM_MATCH_REC����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getMuxDataModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();

		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO etsItemMatchRec = (EtsItemMatchRecDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT " + " ID," + " MATCH_DATE,"
					+ " MATCH_USER_ID," + " SYSTEM_ID," + " ASSET_ID,"
					+ " MATCH_TYPE," + " OLD_FINANCE_PROP,"
					+ " NEW_FINANCE_PROP" + " FROM" + " ETS_ITEM_MATCH_TD_REC"
					+ " WHERE" + " (? ='' OR ID LIKE ?)"
					+ " AND (? ='' OR MATCH_DATE LIKE ?)"
					+ " AND (? =-1 OR MATCH_USER_ID LIKE ?)"
					+ " AND (? ='' OR SYSTEM_ID LIKE ?)"
					+ " AND (? =-1 OR ASSET_ID LIKE ?)"
					+ " AND (? ='' OR MATCH_TYPE LIKE ?)"
					+ " AND (? ='' OR OLD_FINANCE_PROP LIKE ?)"
					+ " AND (? ='' OR NEW_FINANCE_PROP LIKE ?)";
			sqlArgs.add(etsItemMatchRec.getId());
			sqlArgs.add(etsItemMatchRec.getId());
			try {
				sqlArgs.add(etsItemMatchRec.getMatchDate());
				sqlArgs.add(etsItemMatchRec.getMatchDate());
			} catch (CalendarException ex) {
				ex.printLog();
				throw new SQLModelException(ex);
			}
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT " + " ID," + " MATCH_DATE,"
					+ " MATCH_USER_ID," + " SYSTEM_ID," + " ASSET_ID,"
					+ " MATCH_TYPE," + " OLD_FINANCE_PROP,"
					+ " NEW_FINANCE_PROP" + " FROM" + " ETS_ITEM_MATCH_REC"
					+ " WHERE" + " (? ='' OR ID LIKE ?)"
					+ " AND (? ='' OR MATCH_DATE LIKE ?)"
					+ " AND (? =-1 OR MATCH_USER_ID LIKE ?)"
					+ " AND (? ='' OR SYSTEM_ID LIKE ?)"
					+ " AND (? =-1 OR ASSET_ID LIKE ?)"
					+ " AND (? ='' OR MATCH_TYPE LIKE ?)"
					+ " AND (? ='' OR OLD_FINANCE_PROP LIKE ?)"
					+ " AND (? ='' OR NEW_FINANCE_PROP LIKE ?)";
			sqlArgs.add(etsItemMatchRec.getId());
			sqlArgs.add(etsItemMatchRec.getId());
			try {
				sqlArgs.add(etsItemMatchRec.getMatchDate());
				sqlArgs.add(etsItemMatchRec.getMatchDate());
			} catch (CalendarException ex) {
				ex.printLog();
				throw new SQLModelException(ex);
			}
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getMatchUserId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getSystemId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getAssetId());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getMatchType());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getOldFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());
			sqlArgs.add(etsItemMatchRec.getNewFinanceProp());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);

		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ETS_ITEM_MATCH_RECҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();

		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO etsItemMatchRec = (EtsItemMatchRecDTO) dtoParameter;
		String matchMode = etsItemMatchRec.getMatchType();
		String sqlStr = "";
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			if (!matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlStr = "SELECT EII.BARCODE,\n"
						+ "       EII.SYSTEMID SYSTEM_ID,\n"
						+ "       EII.FINANCE_PROP,\n"
						+ "       EII.ITEM_CODE,\n"
						+ "       ESI.ITEM_NAME,\n"
						+ "       ESI.ITEM_SPEC,\n"
						+ "       dbo.APP_GET_FLEX_VALUE(ESI.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY,"
						+ "       EO.WORKORDER_OBJECT_NAME,\n"
						+ "		  EPPA.NAME\n"
						+ "  FROM ETS_ITEM_INFO      EII,\n"
						+ "       ETS_SYSTEM_ITEM    ESI,\n"
						+ "       AMS_OBJECT_ADDRESS AOA,\n"
						+ "       ETS_OBJECT         EO,\n"
						+ "       ETS_PA_PROJECTS_ALL EPPA "
						+ " WHERE EII.ITEM_CODE = ESI.ITEM_CODE\n"
						+ "   AND EII.ADDRESS_ID = AOA.ADDRESS_ID\n"
						+ "	  AND EII.PROJECTID *= EPPA.PROJECT_ID"
						+ "   AND (CONVERT(INT, EO.OBJECT_CATEGORY) < = 70 OR CONVERT(INT, EO.OBJECT_CATEGORY) = 80)\n"
						+ "   AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
						+ "   AND EII.DISABLE_DATE IS  NULL"
						+ "   AND EII.FINANCE_PROP = ?\n";

				if (matchMode.equals(WebAttrConstant.MATCH_MODE_SPARE)) { //����ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_SPARE_RET)) { //��������
					sqlArgs.add(DictConstant.FIN_PROP_SPARE);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_PRJMTL)) { //��������ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)) { //�������ʳ���
					sqlArgs.add(DictConstant.FIN_PROP_PRJ);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_OTHER)) { //�豸����
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_0THER_RET)) { //�豸���γ���
					sqlArgs.add(DictConstant.FIN_PROP_OTHER);
					sqlStr = sqlStr + " AND EII.ATTRIBUTE1 IS NULL\n";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_RENT)) { //�����ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%ZL%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_RENT_RET)) { //�����ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_RENT);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_DG)) { //�����ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%DG%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_DG_RET)) { //�����ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_DG);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_LC)) { //��ֵ�׺��ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%DH%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_LC_RET)) { //��ֵ�׺��ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_DH);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_TD)) { //TD�ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%TD%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_TD_RET)) { //TD�ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_TD);
				}
				//else if (matchMode.equals(WebAttrConstant.MATCH_MODE_CT)) { //��ͨ�ʲ�ȷ��(���޴�ͨ)
				//	sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				//}
			} else { //TD �����ʲ�ƥ��
				String flag = etsItemMatchRec.getUnyokeFlag();
				sqlStr = "SELECT "
						+ " EII.BARCODE,"
						+ " EII.ITEM_CODE, "
						+ " ESI.ITEM_NAME,"
						+ " ESI.ITEM_SPEC, "
						+ " ER.SYSTEM_ID, \n"
						+ " EM.MATCH_DATE, "
						+ " EM.USER_ID,"
						+ " EM.ASSET_ID,"
						+ " EFA.MIS_TAG_NUMBER TAG_NUMBER,\n"
						//��ȡMIS_TAG_NUMBER modified by Jenson 2008-12-29
						+ " EFA.ASSETS_DESCRIPTION,\n"
						+ " EFA.MODEL_NUMBER,\n"
						+ " EFA.ASSETS_LOCATION_CODE,\n"
						+ " EO.WORKORDER_OBJECT_CODE,\n"
						+ " dbo.APP_GET_FLEX_VALUE(ER.MATCH_TYPE,'MATCH_TYPE') MATCH_TYPE,\n "
						+ " EO.WORKORDER_OBJECT_NAME, "
						+ " dbo.APP_GET_FLEX_VALUE(ER.OLD_FINANCE_PROP,'FINANCE_PROP') OLD_FINANCE_PROP, \n"
						+ " dbo.APP_GET_FLEX_VALUE(ER.NEW_FINANCE_PROP,'FINANCE_PROP') NEW_FINANCE_PROP,\n"
						+ " dbo.APP_GET_USER_NAME(ER.MATCH_USER_ID) MATCH_USER_NAME,"
                        + " EII.CONTENT_CODE,\n"
                        + " EII.CONTENT_NAME,\n"
                        + " EFA.SEGMENT1 || '.' || EFA.SEGMENT2 || '.' || EFA.SEGMENT3 FA_CODE,\n"
                        + " EFA.FA_CATEGORY2\n"
						+ " FROM  "
						+ " ETS_ITEM_INFO      EII, "
						+ " AMS_OBJECT_ADDRESS AOA, "
						+ " ETS_OBJECT         EO,\n"
						+ " ETS_SYSTEM_ITEM    ESI, "
						+ " ETS_ITEM_MATCH_TD  EM, "
						+ " ETS_FA_ASSETS_TD   EFA,\n  "
						+ " ETS_ITEM_MATCH_TD_REC ER"
						+ " WHERE   "
						+ " EII.ADDRESS_ID = AOA.ADDRESS_ID "
						+ " AND EFA.ASSET_ID = EM.ASSET_ID"
						//" AND EFA.SEGMENT2 NOT LIKE '07-71%'" +//���ݲ�����
						//" AND EFA.SEGMENT2 NOT LIKE '80-03%'" +//���ز�����
						+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO "
						+ " AND  EII.SYSTEMID= ER.SYSTEM_ID \n"
						+ " AND EII.SYSTEMID=EM.SYSTEMID   "
						+ " AND EII.ITEM_CODE = ESI.ITEM_CODE "
						//" AND (EO.OBJECT_CATEGORY < = 70 OR EO.OBJECT_CATEGORY = 80)" + //�����ֿⲻ�μ�ƥ��
						+ " AND ER.NEW_FINANCE_PROP = 'TD_ASSETS'\n"

						+ " AND NOT EXISTS("//���������ʲ����ܳ���
						+ " 	SELECT"
						+ " 	NULL"
						+ " 	FROM"
						+ " 	TD_ASSETS_TRANS_LINE AATL,"
						+ " 	TD_ASSETS_TRANS_HEADER AATH"
						+ " 	WHERE"
						+ " 	EII.BARCODE = AATL.BARCODE"
						+ " 	AND AATL.TRANS_ID = AATH.TRANS_ID"
						+ " 	AND AATH.TRANS_STATUS <> 'CANCELED')"

						+ " AND NOT EXISTS("//�Ѿ�ͬ�������ʲ����ܳ���  --����ͬ���ģ�������ͬ����ɻ���ͬ������
						+ " 	SELECT"
						+ " 	NULL"
						+ " 	FROM"
						+ " 	ETS_MISFA_UPDATE_LOG EMUL"
						+ " 	WHERE"
						+ " 	EII.BARCODE = EMUL.BARCODE AND EMUL.TRANS_STATUS=0)";

				if (flag.equals("1")) { //�ʲ�ƥ�䳷��
					sqlStr += " AND ER.OLD_FINANCE_PROP = 'UNKNOW' \n";
				} else if (flag.equals("0")) { //ת��ƥ�䳷��
					sqlStr += " AND ER.OLD_FINANCE_PROP ='PRJ_MTL' \n";
				}
			}
			sqlStr += " AND ( (? ='' OR EO.WORKORDER_OBJECT_NAME LIKE ?)\n"
					+ " 	 OR ( ? ='' OR EO.WORKORDER_OBJECT_CODE LIKE ?))\n"
					+ " AND (? ='' OR ESI.ITEM_NAME LIKE ?)\n"
					+ " AND (? ='' OR ESI.ITEM_SPEC LIKE ? )\n"
					+ " AND (? ='' OR EII.BARCODE LIKE ? )\n" ;
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlStr += 
					  " AND ( " + SyBaseSQLUtil.isNull() + " OR EM.MATCH_DATE >= ISNULL(?, EM.MATCH_DATE))  \n"
					+ " AND ( " + SyBaseSQLUtil.isNull() + " OR DATEADD(  DD, -1, EM.MATCH_DATE ) <= ISNULL(?, EM.MATCH_DATE))  \n"
					+ " AND (? = '' OR EFA.TAG_NUMBER LIKE ? )\n"
					+ " AND (? = -1 OR ER.MATCH_USER_ID = ? )\n";
			}
			sqlStr += " AND EII.ORGANIZATION_ID = ? ";
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlStr += " ORDER BY EM.MATCH_DATE DESC, EO.WORKORDER_OBJECT_CODE, ESI.ITEM_NAME ";
			}
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getItemName());
			sqlArgs.add(etsItemMatchRec.getItemName());
			sqlArgs.add(etsItemMatchRec.getItemSpec());
			sqlArgs.add(etsItemMatchRec.getItemSpec());
			sqlArgs.add(etsItemMatchRec.getBarcode());
			sqlArgs.add(etsItemMatchRec.getBarcode());
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlArgs.add(etsItemMatchRec.getStartDate());
				sqlArgs.add(etsItemMatchRec.getStartDate());
				sqlArgs.add(etsItemMatchRec.getEndDate());
				sqlArgs.add(etsItemMatchRec.getEndDate());
				sqlArgs.add(etsItemMatchRec.getTagNumber());
				sqlArgs.add(etsItemMatchRec.getTagNumber());
				sqlArgs.add(etsItemMatchRec.getMatchUserId());
				sqlArgs.add(etsItemMatchRec.getMatchUserId());
			}
			sqlArgs.add(userAccount.getOrganizationId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else { //��TD
			if (!matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlStr =  "SELECT EII.BARCODE,\n"
						+ "       EII.SYSTEMID SYSTEM_ID,\n"
						+ "       EII.FINANCE_PROP,\n"
						+ "       EII.ITEM_CODE,\n"
						+ "       ESI.ITEM_NAME,\n"
						+ "       ESI.ITEM_SPEC,\n"
						+ "       dbo.APP_GET_FLEX_VALUE(ESI.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY,"
						+ "       EO.WORKORDER_OBJECT_NAME,\n"
						+ "		  EPPA.NAME\n"
						+ "  FROM ETS_ITEM_INFO      EII,\n"
						+ "       ETS_SYSTEM_ITEM    ESI,\n"
						+ "       AMS_OBJECT_ADDRESS AOA,\n"
						+ "       ETS_OBJECT         EO,\n"
						+ "       ETS_PA_PROJECTS_ALL EPPA "
						+ " WHERE EII.ITEM_CODE = ESI.ITEM_CODE\n"
						+ "   AND EII.ADDRESS_ID = AOA.ADDRESS_ID\n"
						+ "	  AND EII.PROJECTID *= EPPA.PROJECT_ID"
						+ "   AND (CONVERT(INT, EO.OBJECT_CATEGORY) < = 70 OR CONVERT(INT, EO.OBJECT_CATEGORY) = 80)\n"
						+ "   AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
						+ "   AND EII.DISABLE_DATE IS  NULL"
						+ "   AND EII.FINANCE_PROP = ?\n";

				if (matchMode.equals(WebAttrConstant.MATCH_MODE_SPARE)) { // ����ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_SPARE_RET)) { // ��������
					sqlArgs.add(DictConstant.FIN_PROP_SPARE);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_PRJMTL)) { // ��������ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)) { // �������ʳ���
					sqlArgs.add(DictConstant.FIN_PROP_PRJ);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_OTHER)) { // �豸����
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_0THER_RET)) { // �豸���γ���
					sqlArgs.add(DictConstant.FIN_PROP_OTHER);
					sqlStr = sqlStr + " AND EII.ATTRIBUTE1 IS NULL\n";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_RENT)) { // �����ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%ZL%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_RENT_RET)) { // �����ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_RENT);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_DG)) { // �����ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%DG%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_DG_RET)) { // �����ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_DG);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_LC)) { // ��ֵ�׺��ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%DH%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_LC_RET)) { // ��ֵ�׺��ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_DH);
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_TD)) { // TD�ʲ�ȷ��
					sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
					sqlStr += " AND EII.BARCODE LIKE'%TD%'";
				} else if (matchMode.equals(WebAttrConstant.MATCH_MODE_TD_RET)) { // TD�ʲ�����
					sqlArgs.add(DictConstant.FIN_PROP_TD);
				}
				// else if (matchMode.equals(WebAttrConstant.MATCH_MODE_CT)) {
				// //��ͨ�ʲ�ȷ��(���޴�ͨ)
				// sqlArgs.add(DictConstant.FIN_PROP_UNKNOW);
				// }
			} else { //��TD�������ʲ�ƥ��
				String flag = etsItemMatchRec.getUnyokeFlag();
				sqlStr =  "SELECT "
						+ " EII.BARCODE, \n"
						+ " EII.ITEM_CODE, \n"
						+ " ESI.ITEM_NAME, \n"
						+ " ESI.ITEM_SPEC, \n"
						+ " ER.SYSTEM_ID, \n"
						+ " EM.MATCH_DATE, \n"
						+ " EM.USER_ID, \n"
						+ " EM.ASSET_ID, \n"
						+ " EFA.MIS_TAG_NUMBER TAG_NUMBER, \n"
						// ��ȡMIS_TAG_NUMBER modified by Jenson 2008-12-29
						+ " EFA.ASSETS_DESCRIPTION, \n"
						+ " EFA.MODEL_NUMBER, \n"
						+ " EFA.ASSETS_LOCATION_CODE, \n"
						+ " EO.WORKORDER_OBJECT_CODE, \n"
						//+ " dbo.APP_GET_FLEX_VALUE(ER.MATCH_TYPE,'MATCH_TYPE') MATCH_TYPE, \n "
						+ "(CASE ER.MATCH_TYPE WHEN '8' THEN '�豸���ʲ�����ƥ��' WHEN '9' THEN '�豸���ʲ��˹�ƥ��' WHEN '4' THEN '�豸����' WHEN '7' THEN '�����ʲ�ƥ���ϵ' WHEN '5' THEN '�����豸����' WHEN '0' THEN '����ȷ��' WHEN '1' THEN '��������ƥ���ϵ' WHEN '10' THEN '����ƥ��' WHEN '11' THEN '����ƥ��' WHEN '2' THEN '�ڽ�����ȷ��' WHEN '3' THEN '�����ڽ�����ƥ���ϵ' WHEN '6' THEN 'ת��ƥ��' ELSE '����' END) MATCH_TYPE, \n"
						+ " EO.WORKORDER_OBJECT_NAME, \n"
						//+ " dbo.APP_GET_FLEX_VALUE(ER.OLD_FINANCE_PROP,'FINANCE_PROP') OLD_FINANCE_PROP, \n"
						//+ " dbo.APP_GET_FLEX_VALUE(ER.NEW_FINANCE_PROP,'FINANCE_PROP') NEW_FINANCE_PROP, \n"
					    + "(CASE ER.OLD_FINANCE_PROP WHEN 'ASSETS' THEN '�ʲ�' WHEN 'UNKNOW' THEN 'δ֪' WHEN 'DG_ASSETS' THEN 'ͨ����ͨ�ʲ�' WHEN 'DH_ASSETS' THEN '��ֵ�׺��ʲ�' WHEN 'OTHERS' THEN '����' WHEN 'PRJ_MTL' THEN 'Ԥת���ʲ�' WHEN 'QD_ASSETS' THEN 'ͨ�������ʲ�' WHEN 'RENT_ASSETS' THEN '��Ӫ�����ʲ�' WHEN 'SPARE' THEN '��Ʒ����' WHEN 'TD_ASSETS' THEN 'TD�ʲ�' WHEN 'TT_ASSETS' THEN '��ͨ�ʲ�' ELSE '����' END) OLD_FINANCE_PROP, \n"
					    + "(CASE ER.NEW_FINANCE_PROP WHEN 'ASSETS' THEN '�ʲ�' WHEN 'UNKNOW' THEN 'δ֪' WHEN 'DG_ASSETS' THEN 'ͨ����ͨ�ʲ�' WHEN 'DH_ASSETS' THEN '��ֵ�׺��ʲ�' WHEN 'OTHERS' THEN '����' WHEN 'PRJ_MTL' THEN 'Ԥת���ʲ�' WHEN 'QD_ASSETS' THEN 'ͨ�������ʲ�' WHEN 'RENT_ASSETS' THEN '��Ӫ�����ʲ�' WHEN 'SPARE' THEN '��Ʒ����' WHEN 'TD_ASSETS' THEN 'TD�ʲ�' WHEN 'TT_ASSETS' THEN '��ͨ�ʲ�' ELSE '����' END) NEW_FINANCE_PROP, \n"
						//+ " dbo.APP_GET_USER_NAME(ER.MATCH_USER_ID) MATCH_USER_NAME, \n"
					    + " SU.USERNAME MATCH_USER_NAME, \n"
                        + " EII.CONTENT_CODE, \n"
                        + " EII.CONTENT_NAME, \n"
                        + " EFA.SEGMENT1 || '.' || EFA.SEGMENT2 || '.' || EFA.SEGMENT3 FA_CODE, \n"
                        + " EFA.FA_CATEGORY2 \n" 
						+ " FROM \n"
						+ " ETS_ITEM_INFO      EII, \n"
						+ " AMS_OBJECT_ADDRESS AOA, \n"
						+ " ETS_OBJECT         EO, \n"
						+ " ETS_SYSTEM_ITEM    ESI, \n"
						+ " ETS_ITEM_MATCH     EM, \n"
						+ " ETS_FA_ASSETS      EFA, \n"
						+ " ETS_ITEM_MATCH_REC ER, \n"
						+ " SF_USER            SU \n"
						+ " WHERE \n"
						+ " EII.ADDRESS_ID = AOA.ADDRESS_ID \n"
						+ " AND EFA.ASSET_ID = EM.ASSET_ID \n"
						//" AND EFA.SEGMENT2 NOT LIKE '07-71%'" +//���ݲ�����
						//" AND EFA.SEGMENT2 NOT LIKE '80-03%'" +//���ز�����
						+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO \n"
						+ " AND EII.SYSTEMID = ER.SYSTEM_ID \n"
						+ " AND EII.SYSTEMID = EM.SYSTEMID \n"
						+ " AND EII.ITEM_CODE = ESI.ITEM_CODE \n"
						//" AND (EO.OBJECT_CATEGORY < = 70 OR EO.OBJECT_CATEGORY = 80)" //�����ֿⲻ�μ�ƥ��
						+ " AND ER.NEW_FINANCE_PROP = 'ASSETS' \n"

						+ " AND NOT EXISTS( \n"// ���������ʲ����ܳ���
						+ " 	SELECT \n"
						+ " 	NULL \n"
						+ " 	FROM \n"
						+ " 	AMS_ASSETS_TRANS_LINE AATL, \n"
						+ " 	AMS_ASSETS_TRANS_HEADER AATH \n"
						+ " 	WHERE \n"
						+ " 	EII.BARCODE = AATL.BARCODE \n"
						+ " 	AND AATL.TRANS_ID = AATH.TRANS_ID \n"
						+ " 	AND AATH.TRANS_STATUS <> 'CANCELED') \n"

						+ " AND NOT EXISTS( \n"// �Ѿ�ͬ�������ʲ����ܳ���
						// --����ͬ���ģ�������ͬ����ɻ���ͬ������
						+ " 	SELECT \n"
						+ " 	NULL \n"
						+ " 	FROM \n"
						+ " 	ETS_MISFA_UPDATE_LOG EMUL \n"
						+ " 	WHERE \n"
						+ " 	EII.BARCODE = EMUL.BARCODE AND EMUL.TRANS_STATUS = 0) \n";

				if (flag.equals("1")) { // �ʲ�ƥ�䳷��
					sqlStr += " AND ER.OLD_FINANCE_PROP = 'UNKNOW' \n";
				} else if (flag.equals("0")) { // ת��ƥ�䳷��
					sqlStr += " AND ER.OLD_FINANCE_PROP ='PRJ_MTL' \n";
				}
			}
			sqlStr += " AND ( (? ='' OR EO.WORKORDER_OBJECT_NAME LIKE ?)\n"
					+ " 	 OR ( ? ='' OR EO.WORKORDER_OBJECT_CODE LIKE ?))\n"
					+ " AND (? ='' OR ESI.ITEM_NAME LIKE ?)\n"
					+ " AND (? ='' OR ESI.ITEM_SPEC LIKE ? )\n"
					+ " AND (? ='' OR EII.BARCODE LIKE ? )\n" ;
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlStr +=
					  " AND ( " + SyBaseSQLUtil.isNull()+ "  OR EM.MATCH_DATE >= ISNULL(?, EM.MATCH_DATE))  \n"
	                + " AND ( " + SyBaseSQLUtil.isNull()+ "  OR DATEADD( DD, -1, EM.MATCH_DATE ) <= ISNULL(?, EM.MATCH_DATE))  \n"
					+ " AND (? = '' OR EFA.TAG_NUMBER LIKE ? )\n"
					+ " AND (? = -1 OR ER.MATCH_USER_ID = ? )\n";
			}
			sqlStr += " AND EII.ORGANIZATION_ID = ? \n" ;
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlStr += 
					 " AND SU.ORGANIZATION_ID = ? \n"
				   + " AND ER.MATCH_USER_ID *= SU.USER_ID \n"
				   + " ORDER BY  EM.MATCH_DATE DESC, EO.WORKORDER_OBJECT_CODE, ESI.ITEM_NAME ";
			}
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getWorkorderObjectName());
			sqlArgs.add(etsItemMatchRec.getItemName());
			sqlArgs.add(etsItemMatchRec.getItemName());
			sqlArgs.add(etsItemMatchRec.getItemSpec());
			sqlArgs.add(etsItemMatchRec.getItemSpec());
			sqlArgs.add(etsItemMatchRec.getBarcode());
			sqlArgs.add(etsItemMatchRec.getBarcode());
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlArgs.add(etsItemMatchRec.getStartDate());
				sqlArgs.add(etsItemMatchRec.getStartDate());
				sqlArgs.add(etsItemMatchRec.getEndDate());
				sqlArgs.add(etsItemMatchRec.getEndDate());
				sqlArgs.add(etsItemMatchRec.getTagNumber());
				sqlArgs.add(etsItemMatchRec.getTagNumber());
				sqlArgs.add(etsItemMatchRec.getMatchUserId());
				sqlArgs.add(etsItemMatchRec.getMatchUserId());
			}
			sqlArgs.add(userAccount.getOrganizationId());
			if (matchMode.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
				sqlArgs.add(userAccount.getOrganizationId());
			}
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel getHasBeenModel(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT 1 " + "FROM ETS_ITEM_MATCH_TD_REC ER "
					+ "WHERE ER.SYSTEM_ID = ?\n";
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT 1 " + "FROM ETS_ITEM_MATCH_REC ER "
					+ "WHERE ER.SYSTEM_ID = ?\n";
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel updateFinanceProp(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = " UPDATE ETS_ITEM_INFO \n"
					+ " SET  FINANCE_PROP = ?\n" + " WHERE SYSTEMID= ?";
			sqlArgs.add(dto.getNewFinanceProp());
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = " UPDATE ETS_ITEM_INFO \n"
					+ " SET  FINANCE_PROP = ?\n" + " WHERE SYSTEMID= ?";
			if (!dto.getNewFinanceProp().equals("")) {
				sqlArgs.add(dto.getNewFinanceProp());
			} else {
				sqlArgs.add("UNKNOW");
			}
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel delFromMatchTbl(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "DELETE FROM ETS_ITEM_MATCH_TD WHERE SYSTEMID = ?";
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "DELETE FROM ETS_ITEM_MATCH WHERE SYSTEMID = ?";
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel updateeimcModel(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_MATCH_TD_COMPLET \n"
					+ "SET    LAST_UPDATE_DATE = GETDATE(),\n"
					+ "       LAST_UPDATE_BY   = ?,\n"
					+ "       CURRENT_UNITS    = CURRENT_UNITS - 1\n"
					+ "WHERE  EXISTS (SELECT 'a'\n"
					+ "        FROM   ETS_ITEM_MATCH_TD EIM\n"
					+ "        WHERE  EIM.ASSET_ID = ASSET_ID\n"
					+ "               AND EIM.SYSTEMID = ?)";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_MATCH_COMPLET \n"
					+ "SET    LAST_UPDATE_DATE = GETDATE(),\n"
					+ "       LAST_UPDATE_BY   = ?,\n"
					+ "       CURRENT_UNITS    = CURRENT_UNITS - 1\n"
					+ "WHERE  EXISTS (SELECT 'a'\n"
					+ "        FROM   ETS_ITEM_MATCH EIM\n"
					+ "        WHERE  EIM.ASSET_ID = ASSET_ID\n"
					+ "               AND EIM.SYSTEMID = ?)";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel insertIntoEIMRModel(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "INSERT INTO \n" + "  ETS_ITEM_MATCH_TD_REC\n"
					+ "(       \n" + "ID,          \n" + "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n" + "SYSTEM_ID        ,\n"
					+ "MATCH_TYPE       ,\n" + "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) " + "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,?,?)";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemId());
			sqlArgs.add(dto.getMatchType());
			sqlArgs.add(dto.getOldFinanceProp());
			sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "INSERT INTO \n" + "  ETS_ITEM_MATCH_REC\n"
					+ "(       \n" + "ID,          \n" + "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n" + "SYSTEM_ID        ,\n"
					+ "MATCH_TYPE       ,\n" + "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) " + "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,?,?)";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemId());
			sqlArgs.add(dto.getMatchType());
			sqlArgs.add(dto.getOldFinanceProp());
			sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel updateEIMRModel(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE   " 
				+ "ETS_ITEM_MATCH_TD_REC \n" 
				+ "SET \n"
				+ "MATCH_DATE = GETDATE(),\n" 
				+ "MATCH_USER_ID = ?,\n"
				+ "MATCH_TYPE = ?,\n" 
				+ "OLD_FINANCE_PROP = dbo.NVL(LTRIM(?), OLD_FINANCE_PROP),\n"
				+ "NEW_FINANCE_PROP = dbo.NVL(LTRIM(?), NEW_FINANCE_PROP) \n" 
				+ "WHERE SYSTEM_ID = ? \n";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getMatchType());
			sqlArgs.add(dto.getOldFinanceProp());
			sqlArgs.add(dto.getNewFinanceProp());
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "UPDATE   " 
				+ "ETS_ITEM_MATCH_REC \n" 
				+ "SET \n"
				+ "MATCH_DATE = GETDATE(), \n" 
				+ "MATCH_USER_ID = ?, \n"
				+ "MATCH_TYPE = ?, \n" 
				+ "OLD_FINANCE_PROP = dbo.NVL(LTRIM(?), OLD_FINANCE_PROP), \n"
				+ "NEW_FINANCE_PROP = dbo.NVL(LTRIM(?), NEW_FINANCE_PROP)  \n" 
				+ "WHERE SYSTEM_ID = ? \n";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getMatchType());
			sqlArgs.add(dto.getOldFinanceProp());
			sqlArgs.add(dto.getNewFinanceProp());
			sqlArgs.add(dto.getSystemId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel insertIntoEIMRLModel(EtsItemMatchRecDTO dto) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC_LOG \n"
					+ "(ID              ,\n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID        ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,?,?,?)";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getMatchType());
			sqlArgs.add(dto.getOldFinanceProp());
			sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "INSERT INTO \n" + "  ETS_ITEM_MATCH_REC_LOG \n"
					+ "(ID              ,\n" + "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n" + "SYSTEM_ID        ,\n"
					+ "ASSET_ID        ,\n" + "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n" + "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,?,?,?)";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getMatchType());
			sqlArgs.add(dto.getOldFinanceProp());
			sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel updateRentInfoModel(String inSqlStr) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("RENT");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("RENT");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel updateDGModel(String inSqlStr) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("DG");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET EII.ATTRIBUTE1       =?,\n"
					+ "       EII.FINANCE_PROP     = ?,\n"
					+ "       EII.LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE EII.SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("DG");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel updateLCModel(String inSqlStr) {// ��ֵ�׺��ʲ�ȷ��
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_INFO\n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("DZYH");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("DZYH");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel updateCTModel(String inSqlStr) {// ��ͨ�ʲ�ȷ��
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("CT");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_INFO \n"
					+ "   SET ATTRIBUTE1       =?,\n"
					+ "       FINANCE_PROP     = ?,\n"
					+ "       LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE SYSTEMID IN (" + inSqlStr + ")";
			sqlArgs.add("CT");
			sqlArgs.add("OTHER");

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel updateFAInfo(int assetId, String tagNumber,
			String misTagNumber) { // 2009.4.23�޸ģ�su��
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_FA_ASSETS_TD \n"
					+ "   SET TAG_NUMBER     = ?\n" +
					//            "       EFA.MIS_TAG_NUMBER = ?\n" +
					" WHERE ASSET_ID = ?";

			//    sqlArgs.add(tagNumber);
			sqlArgs.add(misTagNumber);
			sqlArgs.add(assetId);

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE ETS_FA_ASSETS \n"
					+ "   SET TAG_NUMBER     = ?\n" +
					// " EFA.MIS_TAG_NUMBER = ?\n" +
					" WHERE ASSET_ID = ?";

			// sqlArgs.add(tagNumber);
			sqlArgs.add(misTagNumber);
			sqlArgs.add(assetId);

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel getTagNumber(int assetId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT EFA.TAG_NUMBER, EFA.MIS_TAG_NUMBER\n"
					+ "  FROM ETS_FA_ASSETS_TD EFA\n"
					+ " WHERE EFA.ASSET_ID = ?";

			sqlArgs.add(assetId);

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "SELECT EFA.TAG_NUMBER, EFA.MIS_TAG_NUMBER\n"
					+ "  FROM ETS_FA_ASSETS EFA\n" + " WHERE EFA.ASSET_ID = ?";

			sqlArgs.add(assetId);

			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel updateFA_MIS(String tagNumber) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE FA_ADDITIONS_B@MIS_FA\n"
					+ "   SET TAG_NUMBER=ATTRIBUTE20, LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE TAG_NUMBER = ? "
					+ "   AND ATTRIBUTE20 IS NOT NULL";
			sqlArgs.add(tagNumber);
			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE FA_ADDITIONS_B@MIS_FA\n"
					+ "   SET TAG_NUMBER=ATTRIBUTE20, LAST_UPDATE_DATE = GETDATE()\n"
					+ " WHERE TAG_NUMBER = ? "
					+ "   AND ATTRIBUTE20 IS NOT NULL";
			sqlArgs.add(tagNumber);
			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

	public SQLModel getUpdateDistributePrj(String prjId, String params) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsItemMatchRecDTO dto = (EtsItemMatchRecDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_INFO  \n";
			if (dto.getPrjId() != null && !dto.getPrjId().equals("")) {
				sqlStr += "	SET PROJECTID = NVL(?, PROJECTID) \n";
				sqlArgs.add(prjId);
			} else {
				sqlStr += "	SET PROJECTID = NULL \n";
			}
			sqlStr += "	WHERE SYSTEMID IN " + params;
			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		} else {
			String sqlStr = "UPDATE ETS_ITEM_INFO  \n";
			if (dto.getPrjId() != null && !dto.getPrjId().equals("")) {
				sqlStr += "	SET PROJECTID = NVL(?, PROJECTID) \n";
				sqlArgs.add(prjId);
			} else {
				sqlStr += "	SET PROJECTID = NULL \n";
			}
			sqlStr += "	WHERE  SYSTEMID IN " + params;
			sqlModel.setArgs(sqlArgs);
			sqlModel.setSqlStr(sqlStr);
		}
		return sqlModel;
	}

}
