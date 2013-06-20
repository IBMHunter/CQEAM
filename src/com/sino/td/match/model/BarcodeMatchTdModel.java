package com.sino.td.match.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.match.dto.BarcodeMatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;

/**
 * User: Zyun
 * Date: 2007-11-29
 * Time: 9:36:13
 */
public class BarcodeMatchTdModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH ���ݿ�SQL����㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemMatchDTO ���β���������
	 */
	public BarcodeMatchTdModel(SfUserDTO userAccount, BarcodeMatchDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		String sqlStr = "INSERT INTO "
				//+ " ETS_ITEM_MATCH("
				+ " ETS_ITEM_MATCH_TD("
				+ " SYSTEMID,"
				+ " ITEM_ATTR,"
				+ " ASSET_ID,"
				+ " QUANTITY,"
				+ " MATCH_DATE,"
				+ " CREATION_DATE,"
				+ " CREATED_BY"
				+ ") VALUES ("
				+ " ?, 0, ?, '1', sysdate, sysdate, ?)";

		sqlArgs.add(dto.getSystemid());
		sqlArgs.add(dto.getAssetId());
		sqlArgs.add(userAccount.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE ETS_ITEM_INFO\n" +
				" SET \n" +
				" FINANCE_PROP = ?, \n" +
				" LAST_UPDATE_DATE = GETDATE() , \n" +
				" LAST_UPDATE_BY = ?" +
				" WHERE SYSTEMID = ?";
		//sqlArgs.add(DictConstant.FIN_PROP_ASSETS);
		sqlArgs.add(DictConstant.FIN_PROP_TD);		
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	public SQLModel getHasBeenModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT 1 " +
				"  FROM ETS_ITEM_MATCH_TD_REC ER " +
				" WHERE ER.SYSTEM_ID = ?\n";
		sqlArgs.add(dto.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	public SQLModel insertIntoEIMRModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO \n" +
				"  ETS_ITEM_MATCH_TD_REC\n" +
				"(       \n" +
				"ID,          \n" +
				"MATCH_DATE       ,\n" +
				"MATCH_USER_ID    ,\n" +
				"SYSTEM_ID        ,\n" +
				"ASSET_ID         ,\n" +
				"MATCH_TYPE       ,\n" +
				"OLD_FINANCE_PROP ,\n" +
				"NEW_FINANCE_PROP  ) " +
				"VALUES(\n" +
				" NEWID() ,GETDATE() ,?,?,?,'8','UNKNOW','TD_ASSETS')";
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getSystemid());
		sqlArgs.add(dto.getAssetId());
//        sqlArgs.add(dto.getOldFinanceProp());
//        sqlArgs.add(dto.getNewFinanceProp());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	public SQLModel updateEIMRModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE   " +
				"ETS_ITEM_MATCH_TD_REC \n" +
				"SET \n" +
				"MATCH_DATE = GETDATE() ,\n" +
				"MATCH_USER_ID = ?,\n" +
				"MATCH_TYPE = '8',\n" +
				"OLD_FINANCE_PROP = 'UNKNOW',\n" +
				"NEW_FINANCE_PROP = 'TD_ASSETS'\n" +
				"WHERE SYSTEM_ID = ?";
		sqlArgs.add(userAccount.getUserId());
//        sqlArgs.add(dto.getOldFinanceProp());
//        sqlArgs.add(dto.getNewFinanceProp());
		sqlArgs.add(dto.getSystemid());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	public SQLModel insertIntoEIMRLModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO \n" +
				"  ETS_ITEM_MATCH_TD_REC_LOG \n" +
				"(ID              ,\n" +
				"MATCH_DATE       ,\n" +
				"MATCH_USER_ID    ,\n" +
				"SYSTEM_ID        ,\n" +
				"ASSET_ID         ,\n" +
				"MATCH_TYPE       ,\n" +
				"OLD_FINANCE_PROP ,\n" +
				"NEW_FINANCE_PROP  ) " +
				"VALUES(\n" +
				" NEWID() ,GETDATE() ,?,?,?,'8','UNKNOW','TD_ASSETS')";
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getSystemid());
		sqlArgs.add(dto.getAssetId());
//        sqlArgs.add(dto.getOldFinanceProp());
//        sqlArgs.add(dto.getNewFinanceProp());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	public SQLModel updateEIMCModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " UPDATE " +
				" ETS_ITEM_MATCH_TD_COMPLET\n" +
				" SET " +
				" CURRENT_UNITS = CURRENT_UNITS + 1,\n" +
				" LAST_UPDATE_DATE = GETDATE() ,\n" +
				" LAST_UPDATE_BY =? " +
				" WHERE " +
				" ASSET_ID = ?";

		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(dto.getAssetId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
				+ " ETS_ITEM_MATCH";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
				+ " SYSTEMID,"
				+ " ITEM_ATTR,"
				+ " ASSET_ID,"
				+ " QUANTITY,"
				+ " BATCHID,"
				+ " MATCH_DATE,"
				+ " FLAG,"
				+ " USER_ID,"
				+ " CREATION_DATE,"
				+ " CREATED_BY,"
				+ " LAST_UPDATE_DATE,"
				+ " LAST_UPDATE_BY"
				+ " WHERE"
				+ " ROWNUM = 1";

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCHҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String code = userAccount.getCompanyCode();
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;

		if (dto.getMatch().equals("N")) {
			String sqlStr = "SELECT /*+rule */\n" +
					" EII.BARCODE,   \n" +    //--AMS����
					" EFTA.TAG_NUMBER FA_BARCODE,   \n" +   //--�ʲ������
					" ESI.ITEM_NAME ITEM_DESCRIPTION,  \n" +  //-- EAM�豸����
					" ESI.ITEM_SPEC SPEC,  \n" +    //--EAM�豸�ͺ�
					" EFTA.ASSETS_DESCRIPTION, \n" + // MIS�ʲ�����
					" EFTA.CURRENT_UNITS, \n" +    //--MIS�ʲ�����
					" EFTA.MODEL_NUMBER MIS_SPEC, \n" +     //MIS�豸�ͺ�
					" EO.WORKORDER_OBJECT_CODE,\n" +		//EAM�ص����
					" EFTA.ASSETS_LOCATION_CODE,\n" +		//MIS�ص����
					" EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION, \n" +   //EAM�ص�
					" EFTA.ASSETS_LOCATION MIS_LOCATION, \n" +    //MIS�ص�
					" EII.SYSTEMID,\n" +					//EAMϵͳID
					" EFTA.ASSET_ID,\n" +					//MIS�ʲ�ID
					" AME.USER_NAME,\n" +					//EAMԱ������
					" EFTA.ASSIGNED_TO_NAME,\n" +			//MIS������
					" EO.ORGANIZATION_ID\n" +				//	EAM��֯id
					" FROM " +
					//" ETS_FA_ASSETS      EFA,\n" +
					" ETS_FA_ASSETS_TD      EFTA,\n" +
					
					" ETS_OBJECT         EO,\n" +
					" AMS_OBJECT_ADDRESS AOA,\n" +
					" ETS_ITEM_INFO   EII,\n" +
					" ETS_SYSTEM_ITEM ESI,\n" +
					" AMS_MIS_EMPLOYEE AME,\n" +
					" AMS_MIS_DEPT AMD\n" +
					" WHERE" +
					" AOA.ADDRESS_ID = EII.ADDRESS_ID\n" +		//��ϵص�
					" AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +		//�ص�ID
					" AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
					" AND EII.BARCODE = EFTA.TAG_NUMBER\n" +
					" AND EII.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID\n" +
					" AND EII.DISABLE_DATE  " + SyBaseSQLUtil.isNullNoParam() + "   \n" +      //ʧЧ����Ϊ��
					" AND EII.ITEM_STATUS <>'DISCARDED'  \n" +      //�Ǳ��ϣ�EAM��
					" AND EII.BARCODE LIKE '" + code + "%'\n" +
					" AND (EFTA.IS_RETIREMENTS = 0 OR EFTA.IS_RETIREMENTS = 2) \n" +       //�Ǳ���
					" AND EO.IS_TEMP_ADDR = 0  \n" +         //����ʱ�ص�
                    " AND EII.FINANCE_PROP <> 'OTHER'  \n" + 
					" AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE  \n"+
					//" AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH WHERE  SYSTEMID = EII.SYSTEMID OR ASSET_ID=EFTA.ASSET_ID)\n" +
                    " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE  SYSTEMID = EII.SYSTEMID OR ASSET_ID=EFTA.ASSET_ID)\n" +
					//" AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH ENM WHERE ENM.SYSTEMID = EII.SYSTEMID OR ENM.ASSET_ID = EFTA.ASSET_ID)" + //��ETS_NO_MATCH�е��ʲ����μ�ƥ��
                    " AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH_TD ENM WHERE ENM.SYSTEMID = EII.SYSTEMID OR ENM.ASSET_ID = EFTA.ASSET_ID)" + //��ETS_NO_MATCH�е��ʲ����μ�ƥ��
					" AND EII.ORGANIZATION_ID = ?\n" +
                    " AND EFTA.ORGANIZATION_ID = ?\n" +
                    " AND (EO.OBJECT_CATEGORY < = 70 OR EO.OBJECT_CATEGORY = 80)\n" ;

		if (servletConfig.getProvinceCode().equals(DictConstant.PROVINCE_CODE_JIN)) {
	   sqlStr +=    " AND EII.BARCODE IN (\n" +
					" SELECT BARCODE\n" +
					"  FROM (SELECT EWDD.BARCODE\n" +                         //Ѳ��
					"          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n" +
					"         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n" +
					"           AND EW.WORKORDER_FLAG = 14\n" +
					"           AND EWDD.VERIFY_RESULT = 'ɨ����Ϊ׼'\n" +
					"        UNION ALL\n" +
					"        SELECT EWDD.BARCODE\n" +
					"          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n" +
					"         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n" +
					"           AND EWDD.VERIFY_RESULT = 'ϵͳ����Ϊ׼'\n" +
					"           AND NOT EXISTS (SELECT 1\n" +
					"                  FROM ETS_WORKORDER_DTL EWD\n" +
					"                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n" +
					"                   AND EWDD.BARCODE = EWD.BARCODE)\n" +
					"        UNION ALL\n" +
					"        SELECT EWD.BARCODE\n" +
					"          FROM ETS_WORKORDER_DTL EWD, ETS_WORKORDER EW\n" +
					"         WHERE EWD.WORKORDER_NO = EW.WORKORDER_NO\n" +
					"           AND EWD.ITEM_STATUS < 6\n" +
					"           AND NOT EXISTS (SELECT 1\n" +
					"                  FROM ETS_WORKORDER_DIFF_DTL EWDD\n" +
					"                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n" +
					"                   AND EWD.BARCODE = EWDD.BARCODE)\n" +
					"         UNION ALL\n" +
					"         SELECT AACL.BARCODE\n" +                          //�̵�
					"          FROM AMS_ASSETS_CHK_LOG AACL\n" +
					"         WHERE AACL.IS_EXIST = 'Y'))\n" ;
		}
		sqlStr +=   "   AND (" + SyBaseSQLUtil.isNull() + " OR  ESI.ITEM_NAME LIKE ?)" +
					"   AND (" + SyBaseSQLUtil.isNull() + " OR ESI.ITEM_SPEC LIKE ?)" +
					"   AND (" + SyBaseSQLUtil.isNull() + " OR EII.BARCODE LIKE ?)" +
					"   AND ((" + SyBaseSQLUtil.isNull() + " OR EII.RESPONSIBILITY_DEPT LIKE ?) OR (" + SyBaseSQLUtil.isNull() + " OR AMD.DEPT_NAME LIKE ?))"+
					"   AND EFTA.DEPRECIATION_ACCOUNT LIKE '%'||dbo.NVL(?, EFTA.DEPRECIATION_ACCOUNT)||'%'\n"+
					"   AND ((" + SyBaseSQLUtil.isNull() + " OR EO.WORKORDER_OBJECT_CODE LIKE ?)" +
					"   OR (" + SyBaseSQLUtil.isNull() + " OR EO.WORKORDER_OBJECT_LOCATION LIKE ?))"+
					"   ORDER BY EO.WORKORDER_OBJECT_CODE DESC,ESI.ITEM_NAME,ESI.ITEM_SPEC";

			sqlArgs.add(userAccount.getOrganizationId());
            sqlArgs.add(userAccount.getOrganizationId());
            sqlArgs.add(dto.getItemName());
			sqlArgs.add(dto.getItemName());
			sqlArgs.add(dto.getItemSpec());
			sqlArgs.add(dto.getItemSpec());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getCostCenter());

			sqlArgs.add(dto.getWorkorderObjectLocation());
			sqlArgs.add(dto.getWorkorderObjectLocation());
			sqlArgs.add(dto.getWorkorderObjectLocation());
			sqlArgs.add(dto.getWorkorderObjectLocation());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else if (dto.getMatch().equals("Y")) {
			String sqlStr = "SELECT " +
					" EII.BARCODE,\n" +
					" EII.BARCODE FA_BARCODE,\n" +
					" ESI.ITEM_NAME ITEM_DESCRIPTION,\n" +
					" ESI.ITEM_SPEC SPEC,\n" +
					" EFTA.ASSETS_DESCRIPTION,\n" +
					" EFTA.CURRENT_UNITS,\n" +
					" EFTA.MODEL_NUMBER MIS_SPEC,\n" +
					" EO.WORKORDER_OBJECT_CODE,\n" +
					" EFTA.ASSETS_LOCATION_CODE,\n" +
					" EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION,\n" +
					" EFTA.ASSETS_LOCATION MIS_LOCATION,\n" +
					" EII.SYSTEMID,\n" +
					" AME.USER_NAME,\n" +
					" EFTA.ASSIGNED_TO_NAME,\n" +
					" EFTA.ASSET_ID,\n" +
					" EO.ORGANIZATION_ID\n" +
					" FROM " +
					" ETS_ITEM_INFO      EII,\n" +
					" ETS_SYSTEM_ITEM    ESI,\n" +
					" ETS_FA_ASSETS_TD      EFTA,\n" +
					" AMS_OBJECT_ADDRESS AOA,\n" +
					" ETS_OBJECT         EO,\n" +
					" AMS_MIS_EMPLOYEE AME,\n" +
                    " AMS_MIS_DEPT AMD,\n" +
                    //" ETS_ITEM_MATCH_REC EIM\n" +
                    " ETS_ITEM_MATCH_TD_REC EIMTR\n" +
					" WHERE " +
					" EIMTR.MATCH_TYPE = '8'\n" +
					" AND EIMTR.SYSTEM_ID = EII.SYSTEMID\n" +
					" AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
					" AND EII.BARCODE = EFTA.TAG_NUMBER\n" +
					" AND AOA.ADDRESS_ID = EII.ADDRESS_ID\n" +
					" AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
					" AND EII.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID\n" +
                    " AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE  \n"+
                    " AND EO.ORGANIZATION_ID = ?" +
					" AND EII.BARCODE LIKE dbo.NVL(?,EII.BARCODE)" +
					" AND (" + SyBaseSQLUtil.isNull() + " OR  ESI.ITEM_NAME LIKE ?)" +
					" AND (" + SyBaseSQLUtil.isNull() + " OR ESI.ITEM_SPEC LIKE ?)" +
                    " AND ((" + SyBaseSQLUtil.isNull() + " OR EII.RESPONSIBILITY_DEPT LIKE ?) OR (" + SyBaseSQLUtil.isNull() + " OR AMD.DEPT_NAME LIKE ?))"+
                    " AND (" + SyBaseSQLUtil.isNull() + " OR EO.WORKORDER_OBJECT_LOCATION LIKE ?)";
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(dto.getBarcode());
			sqlArgs.add(dto.getItemName());
			sqlArgs.add(dto.getItemName());
			sqlArgs.add(dto.getItemSpec());
			sqlArgs.add(dto.getItemSpec());
            sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getResponsibilityDept());
			sqlArgs.add(dto.getResponsibilityDept());
            sqlArgs.add(dto.getWorkorderObjectLocation());
			sqlArgs.add(dto.getWorkorderObjectLocation());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}
}

