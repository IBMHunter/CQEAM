package com.sino.ams.system.rent.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.ArrUtil;
import com.sino.framework.security.dto.ServletConfigDTO;


/**
 * <p>Title: AmsAssetsPriviServlet</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsPriviServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class RentAssetsTreeModel extends AMSSQLProducer {
    private String constant = "";
	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsAddressVDTO ���β���������
	 */
	public RentAssetsTreeModel(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}


	public void setServletConfig(ServletConfigDTO servletConfig, String constant){
		super.setServletConfig(servletConfig);
		this.constant = constant;
	}

	/**
	 * ���ܣ���ȡ�û����ܲ����ĸ����ʲ�
	 * @return SQLModel
	 */
	public SQLModel getPersonalTreeModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
//		String sqlStr = "SELECT"
//						+ " AAAV.FA_CATEGORY1,"
//						+ " AAAV.FA_CATEGORY2,"
//						+ " AAAV.ASSETS_DESCRIPTION,"
//						+ " AAAV.MODEL_NUMBER"
//						+ " FROM"
//						+ " AMS_ASSETS_ADDRESS_V AAAV"
//						+ " WHERE"
//						+ " AAAV.RESPONSIBILITY_USER = ?"
//						+ " AND  " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " "
//						+ " AND (AAAV.ITEM_STATUS " + SyBaseSQLUtil.isNull() + "  OR AAAV.ITEM_STATUS = ?)"
//						+ " AND NOT EXISTS("
//						+ " SELECT"
//						+ " NULL"
//						+ " FROM"
//						+ " AMS_ASSETS_RESERVED AAR"
//						+ " WHERE"
//						+ " AAAV.BARCODE = AAR.BARCODE)"
//						+ " ORDER BY"
//						+ " AAAV.FA_CATEGORY1,"
//						+ " AAAV.FA_CATEGORY2,"
//						+ " AAAV.ASSETS_DESCRIPTION,"
//						+ " AAAV.MODEL_NUMBER";

		  String sqlStr = "SELECT ?  ";
        sqlArgs.add(userAccount.getUsername());
//        sqlArgs.add(userAccount.getEmployeeId());
//		sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�û��������Է�δ���յ��ʲ�
	 * @return SQLModel
	 */
	public SQLModel getTransferTreeModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EFA.FA_CATEGORY1,"
						+ " EFA.FA_CATEGORY2,"
						+ " EFA.ASSETS_DESCRIPTION,"
						+ " EFA.MODEL_NUMBER"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_HEADER      AATH,"
						+ " AMS_ASSETS_TRANS_LINE        AATL,"
						+ " ETS_ITEM_INFO                EII,"
						+ " ETS_FA_ASSETS                EFA,"
						+ " ETS_ITEM_MATCH               EIM"
						+ " WHERE"
						+ " EFA.ASSET_ID = EIM.ASSET_ID"
						+ " AND EIM.SYSTEMID = EII.SYSTEMID"
						+ " AND EII.BARCODE = AATL.BARCODE"
						+ " AND AATL.CONFIRM_DATE " + SyBaseSQLUtil.isNullNoParam() + " "
						+ " AND AATL.TRANS_ID = AATH.TRANS_ID"
						+ " AND AATH.TRANS_TYPE = ?"
						+ " AND AATH.CREATED_BY = ?"
						+ " AND EXISTS("
						+ " SELECT"
						+ " NULL"
						+ " FROM"
						+ " ETS_SYSITEM_DISTRIBUTE ESD"
						+ " WHERE"
						+ " ESD.ITEM_CODE = EII.ITEM_CODE"
						+ " AND ESD.ORGANIZATION_ID = ?)";
		sqlArgs.add(AssetsDictConstant.ASS_RED);
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�û����ܲ����Ĳ����ʲ�
	 * @param deptCodes String[]
	 * @return SQLModel
	 */
	public SQLModel getDeptTreeModel(String[] deptCodes) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

        	String sqlStr = "SELECT"
                        + " ? COMPANY_NAME,"
						+ " AMD.DEPT_NAME"
						+ " FROM"
						+ " AMS_MIS_DEPT     AMD,"
						+ " AMS_ASSETS_PRIVI AAP,"
						+ " SF_ROLE          SR"
						+ " WHERE"
						+ " AMD.DEPT_CODE = AAP.DEPT_CODE"
						+ " AND AAP.PROVINCE_CODE = ?"
						+ " AND AAP.ROLE_ID = SR.ROLE_ID"
						+ " AND SR.ROLE_NAME = ?"
						+ " AND AAP.COMPANY_CODE = ?"
						+ " AND  " + SyBaseSQLUtil.isNotNull("AAP.DEPT_CODE") + " "
						+ " AND AAP.USER_ID = ?";
        sqlArgs.add(userAccount.getCompany());
        sqlArgs.add(servletConfig.getProvinceCode());
		sqlArgs.add(servletConfig.getDeptAssetsMgr());
		sqlArgs.add(userAccount.getCompanyCode());
		sqlArgs.add(userAccount.getUserId());

        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�û����ܲ����Ĺ�˾�ʲ�
	 * @return SQLModel
	 */
	public SQLModel getCompanyTreeModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
//		String sqlStr = "SELECT"
//						+ " EOCM.COMPANY COMPANY_NAME,"
//						+ " AMD.DEPT_NAME,"
//						+ " AAAV.FA_CATEGORY1,"
//						+ " AAAV.FA_CATEGORY2,"
//						+ " AAAV.ASSETS_DESCRIPTION,"
//						+ " AAAV.MODEL_NUMBER"
//						+ " FROM"
//						+ " ETS_OU_CITY_MAP        EOCM,"
//						+ " AMS_MIS_DEPT           AMD,"
//						+ " AMS_ASSETS_ADDRESS_V   AAAV"
//						+ " WHERE"
//						+ " AMD.COMPANY_CODE = EOCM.COMPANY_CODE"
//						+ " AND EOCM.ORGANIZATION_ID = ?"
//						+ " AND AMD.DEPT_CODE = AAAV.DEPT_CODE"
//						+ " AND  " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " "
//						+ " AND (AAAV.ITEM_STATUS " + SyBaseSQLUtil.isNull() + "  OR AAAV.ITEM_STATUS = ?)"
//						+ " AND NOT EXISTS("
//						+ " SELECT"
//						+ " NULL"
//						+ " FROM"
//						+ " AMS_ASSETS_RESERVED AAR"
//						+ " WHERE"
//						+ " AAAV.BARCODE = AAR.BARCODE)";
//		sqlArgs.add(userAccount.getOrganizationId());
//		sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
//
//		if (!userAccount.isProvinceUser()) {
//			sqlStr = sqlStr
//					 + " AND EXISTS("
//					 + " SELECT"
//					 + " NULL"
//					 + " FROM"
//					 + " ETS_SYSITEM_DISTRIBUTE ESD"
//					 + " WHERE"
//					 + " ESD.ITEM_CODE = AAAV.ITEM_CODE"
//					 + " AND ESD.ORGANIZATION_ID = ?)";
//			sqlArgs.add(userAccount.getOrganizationId());
//		}
//		sqlStr = sqlStr
//				 + " ORDER BY"
//				 + " EOCM.COMPANY,"
//				 + " AMD.DEPT_NAME,"
//				 + " AAAV.FA_CATEGORY1,"
//				 + " AAAV.FA_CATEGORY2,"
//				 + " AAAV.ASSETS_DESCRIPTION,"
//				 + " AAAV.MODEL_NUMBER";
        	String sqlStr = "SELECT ? COMPANY_NAME, SG.GROUP_NAME DEPT_NAME FROM SF_GROUP SG WHERE SG.ORGANIZATION_ID = ?";
//		sqlArgs.add(servletConfig.getCompAssetsMgr());
//		sqlArgs.add(servletConfig.getProvinceCode());
//		sqlArgs.add(userAccount.getCompanyCode());
        sqlArgs.add(userAccount.getCompany());
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ�û����ܲ�����ȫʡ�ʲ�
	 * @return SQLModel
	 */
	public SQLModel getProvinceTreeModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " '" + constant + "' PROVINCE_NAME,"
						+ " EOCM.COMPANY COMPANY_NAME,"
						+ " AMD.DEPT_NAME"
						+ " FROM"
						+ " ETS_OU_CITY_MAP        EOCM,"
						+ " AMS_MIS_DEPT           AMD"
						+ " WHERE"
						+ " AMD.COMPANY_CODE = EOCM.COMPANY_CODE"
						+ " ORDER BY"
						+ " '" + constant + "', "
						+ " EOCM.COMPANY,"
						+ " AMD.DEPT_NAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ���ȡ�û����û�ȷ�ϵ��ʲ�
	 * @return SQLModel
	 */
	public SQLModel getConfirmTreeModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EFA.FA_CATEGORY1,"
						+ " EFA.FA_CATEGORY2,"
						+ " EFA.ASSETS_DESCRIPTION,"
						+ " EFA.MODEL_NUMBER"
						+ " FROM"
						+ " AMS_ASSETS_TRANS_LINE        AATL,"
						+ " ETS_ITEM_INFO                EII,"
						+ " ETS_FA_ASSETS                EFA,"
						+ " ETS_ITEM_MATCH               EIM"
						+ " WHERE"
						+ " EFA.ASSET_ID = EIM.ASSET_ID"
						+ " AND EIM.SYSTEMID = EII.SYSTEMID"
						+ " AND EII.BARCODE = AATL.BARCODE"
						+ " AND AATL.CONFIRM_DATE " + SyBaseSQLUtil.isNullNoParam() + " "
						+ " AND AATL.RESPONSIBILITY_USER = ?";
		sqlArgs.add(userAccount.getEmployeeId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���ȡ��˾����SQL
	 * @return SQLModel
	 */
	public SQLModel getCompanyCountyModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " EOCM.COMPANY COMPANY_NAME,"
						+ " EC.COUNTY_NAME"
						+ " FROM"
						+ " ETS_OU_CITY_MAP EOCM,"
						+ " ETS_COUNTY      EC"
						+ " WHERE"
						+ " EOCM.COMPANY_CODE = EC.COMPANY_CODE";
		if(!userAccount.isProvinceUser()){
			sqlStr += " AND EOCM.ORGANIZATION_ID = ?";
			sqlArgs.add(userAccount.getOrganizationId());
		}
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
