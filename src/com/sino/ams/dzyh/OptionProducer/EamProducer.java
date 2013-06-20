package com.sino.ams.dzyh.OptionProducer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.web.DatabaseForWeb;

public class EamProducer extends OptionProducer {

	public EamProducer(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
	}

	/**
	 * ����:��ȡ���������б��
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDzyhParentOption(int selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
				+ " EDCS.CATLOG_SET_ID,"
				+ " EDCS.SET_CODE||'('||EDCS.SET_NAME||')'"
				+ " FROM"
				+ " EAM_DH_CATALOG_SET EDCS";
		sqlModel.setSqlStr(sqlStr);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(String.valueOf(selectedValue), true);
	}

	/**
	 * ����:��ȡ���������б�򣨽�����ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDetailDzyhParentOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT"
				+ " EDCS.CATLOG_SET_ID,"
				+ " EDCS.SET_CODE||'('||EDCS.SET_NAME||')'"
				+ " FROM"
				+ " EAM_DH_CATALOG_SET EDCS";
		sqlModel.setSqlStr(sqlStr);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ������Ƿ�������
	 * @param selectedValue String
	 * @return String
	 */
	public String getBooleanOption(String selectedValue) {
		StringBuffer strOpt = new StringBuffer();
		if (selectedValue == null) {
			selectedValue = "";
		}
		strOpt.append("<option value=\"\">--��ѡ��--</option>");
		strOpt.append("<option value=\"");
		strOpt.append(WebAttrConstant.DZYH_TRUE_VALUE);
		strOpt.append("\"");
		if (selectedValue.equals(WebAttrConstant.DZYH_TRUE_VALUE)) {
			strOpt.append(" selected");
		}
		strOpt.append(">��</option>");
		strOpt.append("<option value=\"");
		strOpt.append(WebAttrConstant.DZYH_FALSE_VALUE);
		strOpt.append("\"");
		if (selectedValue.equals(WebAttrConstant.DZYH_FALSE_VALUE)) {
			strOpt.append(" selected");
		}
		strOpt.append(">��</option>");
		return strOpt.toString();
	}

	/**
	 * ���ܣ������ֵ�dictCode�������б�
	 * @param flexValueSetId      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDictOption(String flexValueSetId, String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EFV.CODE,"
				+ " EFV.VALUE"
				+ " FROM"
				+ " ETS_FLEX_VALUES    EFV,"
				+ " ETS_FLEX_VALUE_SET EFVS"
				+ " WHERE"
				+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
				+ " AND EFV.ENABLED = 'Y'"
				+ " AND EFVS.FLEX_VALUE_SET_ID = ?";
		sqlArgs.add(flexValueSetId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ������ֵ�dictCode�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param flexValueSetId      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDeDictOption(String flexValueSetId, String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EFV.CODE,"
				+ " EFV.VALUE"
				+ " FROM"
				+ " ETS_FLEX_VALUES    EFV,"
				+ " ETS_FLEX_VALUE_SET EFVS"
				+ " WHERE"
				+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
				+ " AND EFV.ENABLED = 'Y'"
				+ " AND EFVS.FLEX_VALUE_SET_ID = ?";
		sqlArgs.add(flexValueSetId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ������ֵ�dictCode�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param dictCode      String �ֵ����
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getDetailDictOption(String dictCode, String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EFV.CODE,"
				+ " EFV.VALUE"
				+ " FROM"
				+ " ETS_FLEX_VALUES    EFV,"
				+ " ETS_FLEX_VALUE_SET EFVS"
				+ " WHERE"
				+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
				+ " AND EFV.ENABLED = 'Y'"
				+ " AND EFVS.CODE = ?";
		sqlArgs.add(dictCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ�����Ŀ¼���category2�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getCategory2Option(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ESI.ITEM_CODE,"
				+ " ESI.ITEM_CATEGORY2"
				+ " FROM"
				+ " ETS_SYSTEM_ITEM    ESI,"
				+ " ETS_ITEM_INFO 	   EII"
				+ " WHERE"
				+ " ESI.ITEM_CODE = EII.ITEM_CODE"
				+ " AND EII.FINANCE_PROP='DZYH'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ�����Ʒ��itemName�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getItemNameOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
			+ " ESI.ITEM_CODE,"
			+ " ESI.ITEM_NAME"
			+ " FROM"
			+ " ETS_SYSTEM_ITEM    ESI,"
			+ " ETS_ITEM_INFO 	   EII"
			+ " WHERE"
			+ " ESI.ITEM_CODE = EII.ITEM_CODE"
			+ " AND EII.FINANCE_PROP='DZYH'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ����ɹ������itemSpec�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getItemSpecOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " ESI.ITEM_CODE,"
				+ " ESI.ITEM_SPEC"
				+ " FROM"
				+ " ETS_SYSTEM_ITEM    ESI,"
				+ " ETS_ITEM_INFO 	   EII"
				+ " WHERE"
				+ " ESI.ITEM_CODE = EII.ITEM_CODE"
				+ " AND EII.FINANCE_PROP='DZYH'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ�����ʹ�ò���responsibilityDept�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getResponsibilityDeptOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AMD.DEPT_CODE,"
				+ " AMD.DEPT_NAME"
				+ " FROM"
				+ " AMS_MIS_DEPT AMD,"
				+ " ETS_ITEM_INFO 	   EII"
				+ " WHERE"
				+ " EII.RESPONSIBILITY_DEPT=AMD.DEPT_CODE"
				+ " AND EII.FINANCE_PROP='DZYH'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ�����������responsibilityUser�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getResponsibilityUserOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " AME.EMPLOYEE_ID,"
				+ " AME.USER_NAME"
				+ " FROM"
				+ " AMS_MIS_EMPLOYEE AME,"
				+ " ETS_ITEM_INFO 	   EII"
				+ " WHERE"
				+ " EII.RESPONSIBILITY_USER=AME.EMPLOYEE_ID"
				+ " AND EII.FINANCE_PROP='DZYH'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

	/**
	 * ���ܣ����ɵص�addressId�������б�������ϸҳ��ʱ��ѡ�У�
	 * @param selectedValue String ѡ����ֵ
	 * @return String
	 * @throws QueryException
	 */
	public String getAddressNameOption(String selectedValue) throws QueryException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
				+ " EII.ADDRESS_ID,"
				+ " EO.WORKORDER_OBJECT_NAME"
				+ " FROM"
				+ " AMS_OBJECT_ADDRESS 	AOA,"
				+ " ETS_OBJECT 			EO,"
				+ " ETS_ITEM_INFO 	   	EII"
				+ " WHERE"
				+ " EII.ADDRESS_ID=AOA.ADDRESS_ID"
				+ " AND EO.WORKORDER_OBJECT_NO=AOA.OBJECT_NO"
				+ " AND EII.FINANCE_PROP='DZYH'";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		DatabaseForWeb webFieldProducer = new DatabaseForWeb(sqlModel, conn);
		return webFieldProducer.getOptionHtml(selectedValue);
	}

}
