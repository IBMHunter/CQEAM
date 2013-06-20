package com.sino.ams.match.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.base.dto.DTOSet;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.match.dto.BarcodeMatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;

/**
 * User: Zyun Date: 2007-11-29 Time: 9:36:13
 */
public class BarcodeMatchModel extends AMSSQLProducer {

	/**
	 * ���ܣ��ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            EtsItemMatchDTO ���β���������
	 */
	public BarcodeMatchModel(SfUserDTO userAccount, BarcodeMatchDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "INSERT INTO "
					// + " ETS_ITEM_MATCH("
					+ " ETS_ITEM_MATCH_TD(" + " SYSTEMID," + " ITEM_ATTR,"
					+ " ASSET_ID," + " QUANTITY," + " MATCH_DATE,"
					+ " CREATION_DATE," + " CREATED_BY" + ") VALUES ("
					+ " ?, 0, ?, 1, GETDATE(), GETDATE(), ?)";

			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(userAccount.getUserId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else if("Y".equalsIgnoreCase(userAccount.getIsTt())){
           String sqlStr = "INSERT INTO "
					// + " ETS_ITEM_MATCH("
					+ " ETS_ITEM_MATCH_TD(" + " SYSTEMID," + " ITEM_ATTR,"
					+ " ASSET_ID," + " QUANTITY," + " MATCH_DATE,"
					+ " CREATION_DATE," + " CREATED_BY" + ") VALUES ("
					+ " ?, 0, ?, 1, GETDATE(), GETDATE(), ?)";

			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(userAccount.getUserId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
        }else
        {
			String sqlStr = "INSERT INTO " + " ETS_ITEM_MATCH(" + " SYSTEMID,"
					+ " ITEM_ATTR," + " ASSET_ID," + " QUANTITY,"
					+ " MATCH_DATE," + " USER_ID," + " CREATION_DATE,"
					+ " CREATED_BY" + ") VALUES ("
					+ " ?, 0, ?, 1, GETDATE(), ?, GETDATE(), ?)";

			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(userAccount.getUserId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException
	 *             ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "UPDATE ETS_ITEM_INFO\n" + " SET \n"
					+ " FINANCE_PROP = ?, \n"
					+ " LAST_UPDATE_DATE = GETDATE(), \n" + " LAST_UPDATE_BY = ?,"
					+ "	CONTENT_CODE = (SELECT EFA.SEGMENT1||'.'||EFA.SEGMENT2||'.'||EFA.SEGMENT3 FROM ETS_FA_ASSETS EFA WHERE EFA.ASSET_ID=?),"
					+ "	CONTENT_NAME = (SELECT FA_CATEGORY2 FROM ETS_FA_ASSETS  WHERE ASSET_ID=?)"
					+ " WHERE SYSTEMID = ?";
			// sqlArgs.add(DictConstant.FIN_PROP_ASSETS);
            if("Y".equalsIgnoreCase(userAccount.getIsTt())){
              sqlArgs.add(DictConstant.FIN_PROP_TT);
            } else{
               sqlArgs.add(DictConstant.FIN_PROP_TD);
            }

			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
           String sqlStr = "UPDATE ETS_ITEM_INFO\n" + " SET \n"
					+ " FINANCE_PROP = ?, \n"
					+ " LAST_UPDATE_DATE = GETDATE(), \n" + " LAST_UPDATE_BY = ?,"
					+ "	CONTENT_CODE = (SELECT EFA.SEGMENT1||'.'||EFA.SEGMENT2||'.'||EFA.SEGMENT3 FROM ETS_FA_ASSETS EFA WHERE EFA.ASSET_ID=?),"
					+ "	CONTENT_NAME = (SELECT FA_CATEGORY2 FROM ETS_FA_ASSETS  WHERE ASSET_ID=?)"
					+ " WHERE SYSTEMID = ?";
			// sqlArgs.add(DictConstant.FIN_PROP_ASSETS);

              sqlArgs.add(DictConstant.FIN_PROP_TT);


			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
        }else {
			String sqlStr = "UPDATE ETS_ITEM_INFO\n" + " SET \n"
					+ " FINANCE_PROP = ?, \n"
					+ " LAST_UPDATE_DATE = GETDATE(), \n" + " LAST_UPDATE_BY = ?,"
					+ "	CONTENT_CODE = (SELECT EFA.SEGMENT1||'.'||EFA.SEGMENT2||'.'||EFA.SEGMENT3 FROM ETS_FA_ASSETS EFA WHERE EFA.ASSET_ID=?),"
					+ "	CONTENT_NAME = (SELECT FA_CATEGORY2 FROM ETS_FA_ASSETS  WHERE ASSET_ID=?)"
					+ " WHERE SYSTEMID = ?";
			sqlArgs.add(DictConstant.FIN_PROP_ASSETS);
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getAssetId());
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel getHasBeenModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT 1 " + "  FROM ETS_ITEM_MATCH_TD_REC ER "
					+ " WHERE ER.SYSTEM_ID = ?\n";
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
          String sqlStr = "SELECT 1 " + "  FROM ETS_ITEM_MATCH_TD_REC ER "
					+ " WHERE ER.SYSTEM_ID = ?\n";
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
        } else {
			String sqlStr = "SELECT 1 " + "  FROM ETS_ITEM_MATCH_REC ER "
					+ " WHERE ER.SYSTEM_ID = ?\n";
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel insertIntoEIMRModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String   sqlStr="";
             if("Y".equalsIgnoreCase(userAccount.getIsTt())){
                    sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC\n"
					+ "(       \n"
					+ "ID,          \n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','TT_ASSETS')";
             }else{
                  sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC\n"
					+ "(       \n"
					+ "ID,          \n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','TD_ASSETS')";
             }

			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
           String   sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC\n"
					+ "(       \n"
					+ "ID,          \n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','TD_ASSETS')";
            sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
             } else {
			String sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_REC\n"
					+ "(       \n"
					+ "ID,          \n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','ASSETS')";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel updateEIMRModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
            	String   sqlStr="";
               if ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
                       sqlStr = "UPDATE   " + "ETS_ITEM_MATCH_TD_REC \n" + "SET \n"
					+ "MATCH_DATE = GETDATE(),\n" + "MATCH_USER_ID = ?,\n"
					+ "MATCH_TYPE = '8',\n" + "OLD_FINANCE_PROP = 'UNKNOW',\n"
					+ "NEW_FINANCE_PROP = 'TT_ASSETS'\n"
					+ "WHERE SYSTEM_ID = ?";
               }else{
                     sqlStr = "UPDATE   " + "ETS_ITEM_MATCH_TD_REC \n" + "SET \n"
					+ "MATCH_DATE = GETDATE(),\n" + "MATCH_USER_ID = ?,\n"
					+ "MATCH_TYPE = '8',\n" + "OLD_FINANCE_PROP = 'UNKNOW',\n"
					+ "NEW_FINANCE_PROP = 'TD_ASSETS'\n"
					+ "WHERE SYSTEM_ID = ?";
               }



			sqlArgs.add(userAccount.getUserId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
           String  sqlStr = "UPDATE   " + "ETS_ITEM_MATCH_TD_REC \n" + "SET \n"
					+ "MATCH_DATE = GETDATE(),\n" + "MATCH_USER_ID = ?,\n"
					+ "MATCH_TYPE = '8',\n" + "OLD_FINANCE_PROP = 'UNKNOW',\n"
					+ "NEW_FINANCE_PROP = 'TD_ASSETS'\n"
					+ "WHERE SYSTEM_ID = ?";




			sqlArgs.add(userAccount.getUserId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
        } else {
			String sqlStr = "UPDATE   " + "ETS_ITEM_MATCH_REC \n" + "SET \n"
					+ "MATCH_DATE = GETDATE(),\n" + "MATCH_USER_ID = ?,\n"
					+ "MATCH_TYPE = '8',\n" + "OLD_FINANCE_PROP = 'UNKNOW',\n"
					+ "NEW_FINANCE_PROP = 'ASSETS'\n" + "WHERE SYSTEM_ID = ?";
			sqlArgs.add(userAccount.getUserId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlArgs.add(dto.getSystemid());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel deleteEIMRModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			// ԭtd�����и�model������
		} else {
			String sqlStr = "DELETE ETS_ITEM_MATCH_REC \n"
					+ " WHERE SYSTEM_ID = ?";

			sqlArgs.add(dto.getSystemid());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel insertIntoEIMRLModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
            String   sqlStr="";
             if("Y".equalsIgnoreCase(userAccount.getIsTt())){
                  sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC_LOG \n"
					+ "(ID              ,\n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','TT_ASSETS')";
             }  else{
                sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC_LOG \n"
					+ "(ID              ,\n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','TD_ASSETS')";
             }

			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
        String  sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_TD_REC_LOG \n"
					+ "(ID              ,\n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "ASSET_ID         ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,?,'8','UNKNOW','TD_ASSETS')";


			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemid());
			sqlArgs.add(dto.getAssetId());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
        } else {
			String sqlStr = "INSERT INTO \n"
					+ "  ETS_ITEM_MATCH_REC_LOG \n"
					+ "(ID              ,\n"
					+ "MATCH_DATE       ,\n"
					+ "MATCH_USER_ID    ,\n"
					+ "SYSTEM_ID        ,\n"
					+ "MATCH_TYPE       ,\n"
					+ "OLD_FINANCE_PROP ,\n"
					+ "NEW_FINANCE_PROP  ) "
					+ "VALUES(\n"
					+ "NEWID(),GETDATE(),?,?,'8','UNKNOW','ASSETS')";
			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getSystemid());
			// sqlArgs.add(dto.getOldFinanceProp());
			// sqlArgs.add(dto.getNewFinanceProp());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	public SQLModel updateEIMCModel() {
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = " UPDATE " + " ETS_ITEM_MATCH_TD_COMPLET\n"
					+ " SET " + " CURRENT_UNITS = CURRENT_UNITS + 1,\n"
					+ " LAST_UPDATE_DATE = GETDATE(),\n" + " LAST_UPDATE_BY =? "
					+ " WHERE " + " ASSET_ID = ?";

			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getAssetId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
           String sqlStr = " UPDATE " + " ETS_ITEM_MATCH_TD_COMPLET\n"
					+ " SET " + " CURRENT_UNITS = CURRENT_UNITS + 1,\n"
					+ " LAST_UPDATE_DATE = GETDATE(),\n" + " LAST_UPDATE_BY =? "
					+ " WHERE " + " ASSET_ID = ?";

			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getAssetId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
        } else {
			String sqlStr = " UPDATE " + " ETS_ITEM_MATCH_COMPLET\n" + " SET "
					+ " CURRENT_UNITS = CURRENT_UNITS + 1,\n"
					+ " LAST_UPDATE_DATE = GETDATE(),\n" + " LAST_UPDATE_BY =? "
					+ " WHERE " + " ASSET_ID = ?";

			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getAssetId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "DELETE FROM" + " ETS_ITEM_MATCH";
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "DELETE FROM" + " ETS_ITEM_MATCH";
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
			String sqlStr = "SELECT " + " SYSTEMID," + " ITEM_ATTR,"
					+ " ASSET_ID," + " QUANTITY," + " BATCHID,"
					+ " MATCH_DATE," + " FLAG," + " USER_ID,"
					+ " CREATION_DATE," + " CREATED_BY," + " LAST_UPDATE_DATE,"
					+ " LAST_UPDATE_BY" + " WHERE" + " ROWNUM = 1";

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} else {
			String sqlStr = "SELECT " + " SYSTEMID," + " ITEM_ATTR,"
					+ " ASSET_ID," + " QUANTITY," + " BATCHID,"
					+ " MATCH_DATE," + " FLAG," + " USER_ID,"
					+ " CREATION_DATE," + " CREATED_BY," + " LAST_UPDATE_DATE,"
					+ " LAST_UPDATE_BY" + " WHERE" + " ROWNUM = 1";

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ������ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCHҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String code = userAccount.getCompanyCode();
		BarcodeMatchDTO dto = (BarcodeMatchDTO) dtoParameter;

		if ("Y".equalsIgnoreCase(userAccount.getIsTd())) { // TD�ʲ�
			if (dto.getMatch().equals("N")) {
				String sqlStr = "SELECT \n"
						+ " EII.BARCODE,   \n"
						+ // --AMS����
						" EFTA.TAG_NUMBER FA_BARCODE,   \n"
						+ // --�ʲ������
						" ESI.ITEM_NAME ITEM_DESCRIPTION,  \n"
						+ // -- EAM�豸����
						" ESI.ITEM_SPEC SPEC,  \n"
						+ // --EAM�豸�ͺ�
						" EFTA.ASSETS_DESCRIPTION, \n"
						+ // MIS�ʲ�����
						" EFTA.CURRENT_UNITS, \n"
						+ // --MIS�ʲ�����
						" EFTA.MODEL_NUMBER MIS_SPEC, \n"
						+ // MIS�豸�ͺ�
						" EO.WORKORDER_OBJECT_CODE,\n"
						+ // EAM�ص����
						" EFTA.ASSETS_LOCATION_CODE,\n"
						+ // MIS�ص����
						" EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION, \n"
						+ // EAM�ص�
						" EFTA.ASSETS_LOCATION MIS_LOCATION, \n"
						+ // MIS�ص�
						" EII.SYSTEMID,\n"
						+ // EAMϵͳID
						" EFTA.ASSET_ID,\n"
						+ // MIS�ʲ�ID
						" AME.USER_NAME,\n"
						+ // EAMԱ������
						" EFTA.ASSIGNED_TO_NAME,\n"
						+ // MIS������
						" EO.ORGANIZATION_ID," +
                        " EFTA.SEGMENT1||'.'||EFTA.SEGMENT2||'.'||EFTA.SEGMENT3 FA_CODE ,\n" +
                        "         EFTA.FA_CATEGORY2,\n" +
                        "         EII.CONTENT_CODE,\n" +
                        "         EII.CONTENT_NAME \n"
						+ // EAM��֯id
						" FROM "
						// " ETS_FA_ASSETS EFA,\n" +
						+ " ETS_FA_ASSETS_TD 	EFTA,\n"

						+ " ETS_OBJECT         	EO,\n"
						+ " AMS_OBJECT_ADDRESS 	AOA,\n"
						+ " ETS_ITEM_INFO      	EII,\n"
						+ " ETS_SYSTEM_ITEM    	ESI,\n"
						+ " AMS_MIS_EMPLOYEE 	AME,\n"
						+ " AMS_MIS_DEPT 		AMD\n"
						+ " WHERE"
						+ " AOA.ADDRESS_ID = EII.ADDRESS_ID\n"
						+ // ��ϵص�
						" AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
						+ // �ص�ID
						" AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
						+ " AND EII.BARCODE = EFTA.TAG_NUMBER\n"
						+ " AND EII.RESPONSIBILITY_USER = AME.EMPLOYEE_ID\n"
						+ " AND (EII.DISABLE_DATE IS NULL OR EII.DISABLE_DATE = '')  \n"
						+ // ʧЧ����Ϊ��
						" AND EII.ITEM_STATUS <>'DISCARDED'  \n"
						+ // �Ǳ��ϣ�EAM��
                        " AND EFTA.COST <> 0\n"
//						+ " AND EII.BARCODE LIKE '"
//						+ code
//						+ "%'\n"
						+ " AND (EFTA.IS_RETIREMENTS = 0 OR EFTA.IS_RETIREMENTS = 2) \n"
						+ // �Ǳ���
						" AND EO.IS_TEMP_ADDR = 0  \n"
						+ // ����ʱ�ص�
						" AND EII.FINANCE_PROP <> 'OTHER'  \n"
						+ " AND AMD.DEPT_CODE = EII.RESPONSIBILITY_DEPT\n";
					    if(userAccount.isDptAssetsManager() && !userAccount.isComAssetsManager()){
                        	DTOSet depts = userAccount.getPriviDeptCodes();
                        	AmsMisDeptDTO dept = null;
                            if( null!= depts ){
	                        	String deptCodes = "(";
	                            for (int i = 0; i < depts.getSize(); i++) {
	                            	dept = (AmsMisDeptDTO) depts.getDTO(i);
	                            	deptCodes += "'" + dept.getDeptCode() + "', ";
	                            }
	                            deptCodes += "'')";
	                            sqlStr += " AND EII.RESPONSIBILITY_DEPT IN " + deptCodes;
                            }
					    }
						// " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH WHERE
						// SYSTEMID = EII.SYSTEMID OR ASSET_ID=EFTA.ASSET_ID)\n"
						// +
						sqlStr+=
						" AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE  SYSTEMID = EII.SYSTEMID )\n" +
						" AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE ASSET_ID = EFTA.ASSET_ID) \n" +
						//" AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH ENM
						// WHERE ENM.SYSTEMID = EII.SYSTEMID OR ENM.ASSET_ID =
						// EFTA.ASSET_ID)" + //��ETS_NO_MATCH�е��ʲ����μ�ƥ��
						" AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH_TD ENM WHERE ENM.SYSTEMID = EII.SYSTEMID )" +
                        " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE ASSET_ID = EFTA.ASSET_ID) \n" +
						// ��ETS_NO_MATCH�е��ʲ����μ�ƥ��
                        
                        " AND NOT EXISTS (SELECT NULL FROM AMS_IGNORE_DATA H WHERE EFTA.SEGMENT2 = H.SEGMENT AND EFTA.SEGMENT3 = H.NAME) \n" +

						" AND EII.ORGANIZATION_ID = ?\n" + 
						" AND EFTA.ORGANIZATION_ID = ?\n" +
						" AND (CONVERT(INT, EO.OBJECT_CATEGORY) < = 70 OR CONVERT(INT, EO.OBJECT_CATEGORY) = 80)\n";

				if (servletConfig.getProvinceCode().equals(DictConstant.PROVINCE_CODE_JIN)) {
					sqlStr += " AND EII.BARCODE IN (\n"
							+ " SELECT BARCODE\n"
							+ "  FROM (SELECT EWDD.BARCODE\n"
							+ // Ѳ��
							"          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n"
							+ "         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n"
							+ "           AND EW.WORKORDER_FLAG = 14\n"
							+ "           AND EWDD.VERIFY_RESULT = 'ɨ����Ϊ׼'\n"
							+ "        UNION ALL\n"
							+ "        SELECT EWDD.BARCODE\n"
							+ "          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n"
							+ "         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n"
							+ "           AND EWDD.VERIFY_RESULT = 'ϵͳ����Ϊ׼'\n"
							+ "           AND NOT EXISTS (SELECT 1\n"
							+ "                  FROM ETS_WORKORDER_DTL EWD\n"
							+ "                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n"
							+ "                   AND EWDD.BARCODE = EWD.BARCODE)\n"
							+ "        UNION ALL\n"
							+ "        SELECT EWD.BARCODE\n"
							+ "          FROM ETS_WORKORDER_DTL EWD, ETS_WORKORDER EW\n"
							+ "         WHERE EWD.WORKORDER_NO = EW.WORKORDER_NO\n"
							+ "           AND EWD.ITEM_STATUS < 6\n"
							+ "           AND NOT EXISTS (SELECT 1\n"
							+ "                  FROM ETS_WORKORDER_DIFF_DTL EWDD\n"
							+ "                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n"
							+ "                   AND EWD.BARCODE = EWDD.BARCODE)\n"
							+ "         UNION ALL\n"
							+ "         SELECT AACL.BARCODE\n"
							// �̵�
							+ "          FROM AMS_ASSETS_CHK_LOG AACL\n"
							+ "         WHERE AACL.IS_EXIST = 'Y'))\n";
				}
				
                if(dto.getNameSame().equals("Y")){
                    sqlStr+="AND EFTA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" ;
                }
                if(dto.getSpecSame().equals("Y")){
                    sqlStr+="AND EFTA.MODEL_NUMBER = ESI.ITEM_SPEC \n" ;
                }
                if(dto.getResSame().equals("Y")){
                    sqlStr+="AND EFTA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                }
                if(dto.getLocSame().equals("Y")){
                    sqlStr+="AND EFTA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                            "AND EFTA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" ;
                }
                
                
                if(dto.getCheck().equals("Y")){
                    sqlStr+="  AND EXISTS (SELECT 'A'\n" +
                            "          FROM AMS_ASSETS_CHK_LOG ACL\n" +
                            "         WHERE ACL.BARCODE = EII.BARCODE\n" +
                            "           AND ACL.IS_EXIST = 'Y')";
                }
                sqlStr += "   AND (? ='' OR  ESI.ITEM_NAME LIKE ?)"
						+ "   AND (? ='' OR ESI.ITEM_SPEC LIKE ?)"
						+ "   AND (? ='' OR EII.BARCODE LIKE ?)"
						+ "   AND ((? ='' OR EFTA.ASSIGNED_TO_NAME LIKE ?) OR (? ='' OR AME.USER_NAME LIKE ?))"
						+ "   AND (? =''OR  EFTA.DEPRECIATION_ACCOUNT LIKE '%'||?||'%')\n"
						+ "   AND ((? ='' OR EO.WORKORDER_OBJECT_CODE LIKE ?)"
						+ "   OR (? ='' OR EO.WORKORDER_OBJECT_LOCATION LIKE ?))"
						+ "   ORDER BY EO.WORKORDER_OBJECT_CODE DESC,ESI.ITEM_NAME,ESI.ITEM_SPEC";

				sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getBarcode());
				sqlArgs.add(dto.getBarcode());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getCostCenter());
				sqlArgs.add(dto.getCostCenter());

				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlModel.setSqlStr(sqlStr);
				sqlModel.setArgs(sqlArgs);
			} else if (dto.getMatch().equals("Y")) {
				String sqlStr = "SELECT "
						+ " EII.BARCODE,\n"
						+ " EII.BARCODE FA_BARCODE,\n"
						+ " ESI.ITEM_NAME ITEM_DESCRIPTION,\n"
						+ " ESI.ITEM_SPEC SPEC,\n"
						+ " EFTA.ASSETS_DESCRIPTION,\n"
						+ " EFTA.CURRENT_UNITS,\n"
						+ " EFTA.MODEL_NUMBER MIS_SPEC,\n"
						+ " EO.WORKORDER_OBJECT_CODE,\n"
						+ " EFTA.ASSETS_LOCATION_CODE,\n"
						+ " EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION,\n"
						+ " EFTA.ASSETS_LOCATION MIS_LOCATION,\n"
						+ " EII.SYSTEMID,\n"
						+ " AME.USER_NAME,\n"
						+ " EFTA.ASSIGNED_TO_NAME,\n"
						+ " EFTA.ASSET_ID,\n"
						+ " EO.ORGANIZATION_ID," 
                        + " EFTA.SEGMENT1||'.'||EFTA.SEGMENT2||'.'||EFTA.SEGMENT3 FA_CODE ,\n" 
                        + " EFTA.FA_CATEGORY2,\n" 
                        + " EII.CONTENT_CODE,\n" 
                        + " EII.CONTENT_NAME\n"
						+ " FROM "
						+ " ETS_ITEM_INFO      EII,\n"
						+ " ETS_SYSTEM_ITEM    ESI,\n"
						+ " ETS_FA_ASSETS_TD   EFTA,\n"
						+ " AMS_OBJECT_ADDRESS AOA,\n"
						+ " ETS_OBJECT         EO,\n"
						+ " AMS_MIS_EMPLOYEE   AME,\n"
						+ " AMS_MIS_DEPT       AMD,\n"
						// " ETS_ITEM_MATCH_REC EIM\n" +
						+ " ETS_ITEM_MATCH_TD_REC EIMTR\n"
						+ " WHERE "
						+ " EIMTR.MATCH_TYPE = '8'\n"
						+ " AND EIMTR.SYSTEM_ID = EII.SYSTEMID\n"
						+ " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
						+ " AND EII.BARCODE = EFTA.TAG_NUMBER\n"
						+ " AND AOA.ADDRESS_ID = EII.ADDRESS_ID\n"
						+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
						+ " AND EII.RESPONSIBILITY_USER = AME.EMPLOYEE_ID\n"
						+ " AND AMD.DEPT_CODE = EII.RESPONSIBILITY_DEPT\n"  ;
                        if(userAccount.isDptAssetsManager() && !userAccount.isComAssetsManager()){
                                DTOSet depts = userAccount.getPriviDeptCodes();
                              AmsMisDeptDTO dept = null;
                        	if( null != depts ){
	                        	String deptCodes = "(";
	                        	for (int i = 0; i < depts.getSize(); i++) {
	                        		dept = (AmsMisDeptDTO) depts.getDTO(i);
	                        		deptCodes += "'" + dept.getDeptCode() + "', ";
	                        	}
	                        	deptCodes += "'')";
	                        	sqlStr += " AND EII.RESPONSIBILITY_DEPT IN " + deptCodes;
                            }
                        }
                        sqlStr += " AND EO.ORGANIZATION_ID = ?"
						+ " AND EII.BARCODE LIKE dbo.NVL(?,EII.BARCODE)"
						+ " AND (? ='' OR  ESI.ITEM_NAME LIKE ?)"
						+ " AND (? ='' OR ESI.ITEM_SPEC LIKE ?)"
						+ " AND ((? ='' OR EFTA.ASSIGNED_TO_NAME LIKE ?) OR (? ='' OR AME.USER_NAME LIKE ?))"
						+ " AND (? ='' OR EO.WORKORDER_OBJECT_LOCATION LIKE ?)";
		                /*if(dto.getSame().equals("Y")){
		                	sqlStr+="AND EFTA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" +
		                            "AND EFTA.MODEL_NUMBER = ESI.ITEM_SPEC\n" +
		                            "AND EFTA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
		                            "AND EFTA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" +
		                            "AND EFTA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
		                }*/
                        
                        if(dto.getNameSame().equals("Y")){
                            sqlStr+="AND EFTA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" ;
                        }
                        if(dto.getSpecSame().equals("Y")){
                            sqlStr+="AND EFTA.MODEL_NUMBER = ESI.ITEM_SPEC \n" ;
                        }
                        if(dto.getResSame().equals("Y")){
                            sqlStr+="AND EFTA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                        }
                        if(dto.getLocSame().equals("Y")){
                            sqlStr+="AND EFTA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                                    "AND EFTA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" ;
                        }   
                        
                sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(dto.getBarcode());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());

				sqlModel.setSqlStr(sqlStr);
				sqlModel.setArgs(sqlArgs);
			}
		}else if  ("Y".equalsIgnoreCase(userAccount.getIsTt())) {
            if (dto.getMatch().equals("N")) {
                            String sqlStr = "SELECT \n"
                                    + " EII.BARCODE,   \n"
                                    + // --AMS����
                                    " EFTA.TAG_NUMBER FA_BARCODE,   \n"
                                    + // --�ʲ������
                                    " ESI.ITEM_NAME ITEM_DESCRIPTION,  \n"
                                    + // -- EAM�豸����
                                    " ESI.ITEM_SPEC SPEC,  \n"
                                    + // --EAM�豸�ͺ�
                                    " EFTA.ASSETS_DESCRIPTION, \n"
                                    + // MIS�ʲ�����
                                    " EFTA.CURRENT_UNITS, \n"
                                    + // --MIS�ʲ�����
                                    " EFTA.MODEL_NUMBER MIS_SPEC, \n"
                                    + // MIS�豸�ͺ�
                                    " EO.WORKORDER_OBJECT_CODE,\n"
                                    + // EAM�ص����
                                    " EFTA.ASSETS_LOCATION_CODE,\n"
                                    + // MIS�ص����
                                    " EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION, \n"
                                    + // EAM�ص�
                                    " EFTA.ASSETS_LOCATION MIS_LOCATION, \n"
                                    + // MIS�ص�
                                    " EII.SYSTEMID,\n"
                                    + // EAMϵͳID
                                    " EFTA.ASSET_ID,\n"
                                    + // MIS�ʲ�ID
                                    " AME.USER_NAME,\n"
                                    + // EAMԱ������
                                    " EFTA.ASSIGNED_TO_NAME,\n"
                                    + // MIS������
                                    " EO.ORGANIZATION_ID," +
                                    " EFTA.SEGMENT1||'.'||EFTA.SEGMENT2||'.'||EFTA.SEGMENT3 FA_CODE ,\n" +
                                    "         EFTA.FA_CATEGORY2,\n" +
                                    "         EII.CONTENT_CODE,\n" +
                                    "         EII.CONTENT_NAME \n"
                                    + // EAM��֯id
                                    " FROM "
                                    // " ETS_FA_ASSETS EFA,\n" +
                                    + " ETS_FA_ASSETS_TD 	EFTA,\n"

                                    + " ETS_OBJECT         	EO,\n"
                                    + " AMS_OBJECT_ADDRESS 	AOA,\n"
                                    + " ETS_ITEM_INFO      	EII,\n"
                                    + " ETS_SYSTEM_ITEM    	ESI,\n"
                                    + " AMS_MIS_EMPLOYEE 	AME,\n"
                                    + " AMS_MIS_DEPT 		AMD\n"
                                    + " WHERE"
                                    + " AOA.ADDRESS_ID = EII.ADDRESS_ID\n"
                                    + // ��ϵص�
                                    " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
                                    + // �ص�ID
                                    " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
                                    + " AND EII.BARCODE = EFTA.TAG_NUMBER\n"
                                    + " AND EII.RESPONSIBILITY_USER = AME.EMPLOYEE_ID\n"
                                    + " AND (EII.DISABLE_DATE IS NULL OR EII.DISABLE_DATE = '')  \n"
                                    + // ʧЧ����Ϊ��
                                    " AND EII.ITEM_STATUS <>'DISCARDED'  \n"
                                    + // �Ǳ��ϣ�EAM��
                                    " AND EFTA.COST <> 0\n"
//						+ " AND EII.BARCODE LIKE '"
//						+ code
//						+ "%'\n"
                                    + " AND (EFTA.IS_RETIREMENTS = 0 OR EFTA.IS_RETIREMENTS = 2) \n"
                                    + // �Ǳ���
                                    " AND EO.IS_TEMP_ADDR = 0  \n"
                                    + // ����ʱ�ص�
                                    " AND EII.FINANCE_PROP <> 'OTHER'  \n"
                                    + " AND AMD.DEPT_CODE = EII.RESPONSIBILITY_DEPT\n";
                                    if(userAccount.isDptAssetsManager() && !userAccount.isComAssetsManager()){
                                        DTOSet depts = userAccount.getPriviDeptCodes();
                                        AmsMisDeptDTO dept = null;
                                        if( null!= depts ){
                                            String deptCodes = "(";
                                            for (int i = 0; i < depts.getSize(); i++) {
                                                dept = (AmsMisDeptDTO) depts.getDTO(i);
                                                deptCodes += "'" + dept.getDeptCode() + "', ";
                                            }
                                            deptCodes += "'')";
                                            sqlStr += " AND EII.RESPONSIBILITY_DEPT IN " + deptCodes;
                                        }
                                    }
                                    // " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH WHERE
                                    // SYSTEMID = EII.SYSTEMID OR ASSET_ID=EFTA.ASSET_ID)\n"
                                    // +
                                    sqlStr+=
                                    " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE  SYSTEMID = EII.SYSTEMID )\n" +
                                    " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE ASSET_ID = EFTA.ASSET_ID) \n" +
                                    //" AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH ENM
                                    // WHERE ENM.SYSTEMID = EII.SYSTEMID OR ENM.ASSET_ID =
                                    // EFTA.ASSET_ID)" + //��ETS_NO_MATCH�е��ʲ����μ�ƥ��
                                    " AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH_TD ENM WHERE ENM.SYSTEMID = EII.SYSTEMID )" +
                                    " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH_TD WHERE ASSET_ID = EFTA.ASSET_ID) \n" +
                                    // ��ETS_NO_MATCH�е��ʲ����μ�ƥ��

                                    " AND NOT EXISTS (SELECT NULL FROM AMS_IGNORE_DATA H WHERE EFTA.SEGMENT2 = H.SEGMENT AND EFTA.SEGMENT3 = H.NAME) \n" +

                                    " AND EII.ORGANIZATION_ID = ?\n" +
                                    " AND EFTA.ORGANIZATION_ID = ?\n" +
                                    " AND (CONVERT(INT, EO.OBJECT_CATEGORY) < = 70 OR CONVERT(INT, EO.OBJECT_CATEGORY) = 80)\n";

                            if (servletConfig.getProvinceCode().equals(DictConstant.PROVINCE_CODE_JIN)) {
                                sqlStr += " AND EII.BARCODE IN (\n"
                                        + " SELECT BARCODE\n"
                                        + "  FROM (SELECT EWDD.BARCODE\n"
                                        + // Ѳ��
                                        "          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n"
                                        + "         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n"
                                        + "           AND EW.WORKORDER_FLAG = 14\n"
                                        + "           AND EWDD.VERIFY_RESULT = 'ɨ����Ϊ׼'\n"
                                        + "        UNION ALL\n"
                                        + "        SELECT EWDD.BARCODE\n"
                                        + "          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n"
                                        + "         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n"
                                        + "           AND EWDD.VERIFY_RESULT = 'ϵͳ����Ϊ׼'\n"
                                        + "           AND NOT EXISTS (SELECT 1\n"
                                        + "                  FROM ETS_WORKORDER_DTL EWD\n"
                                        + "                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n"
                                        + "                   AND EWDD.BARCODE = EWD.BARCODE)\n"
                                        + "        UNION ALL\n"
                                        + "        SELECT EWD.BARCODE\n"
                                        + "          FROM ETS_WORKORDER_DTL EWD, ETS_WORKORDER EW\n"
                                        + "         WHERE EWD.WORKORDER_NO = EW.WORKORDER_NO\n"
                                        + "           AND EWD.ITEM_STATUS < 6\n"
                                        + "           AND NOT EXISTS (SELECT 1\n"
                                        + "                  FROM ETS_WORKORDER_DIFF_DTL EWDD\n"
                                        + "                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n"
                                        + "                   AND EWD.BARCODE = EWDD.BARCODE)\n"
                                        + "         UNION ALL\n"
                                        + "         SELECT AACL.BARCODE\n"
                                        // �̵�
                                        + "          FROM AMS_ASSETS_CHK_LOG AACL\n"
                                        + "         WHERE AACL.IS_EXIST = 'Y'))\n";
                            }

                            if(dto.getNameSame().equals("Y")){
                                sqlStr+="AND EFTA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" ;
                            }
                            if(dto.getSpecSame().equals("Y")){
                                sqlStr+="AND EFTA.MODEL_NUMBER = ESI.ITEM_SPEC \n" ;
                            }
                            if(dto.getResSame().equals("Y")){
                                sqlStr+="AND EFTA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                            }
                            if(dto.getLocSame().equals("Y")){
                                sqlStr+="AND EFTA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                                        "AND EFTA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" ;
                            }


                            if(dto.getCheck().equals("Y")){
                                sqlStr+="  AND EXISTS (SELECT 'A'\n" +
                                        "          FROM AMS_ASSETS_CHK_LOG ACL\n" +
                                        "         WHERE ACL.BARCODE = EII.BARCODE\n" +
                                        "           AND ACL.IS_EXIST = 'Y')";
                            }
                            sqlStr += "   AND (? ='' OR  ESI.ITEM_NAME LIKE ?)"
                                    + "   AND (? ='' OR ESI.ITEM_SPEC LIKE ?)"
                                    + "   AND (? ='' OR EII.BARCODE LIKE ?)"
                                    + "   AND ((? ='' OR EFTA.ASSIGNED_TO_NAME LIKE ?) OR (? ='' OR AME.USER_NAME LIKE ?))"
                                    + "   AND (? =''OR  EFTA.DEPRECIATION_ACCOUNT LIKE '%'||?||'%')\n"
                                    + "   AND ((? ='' OR EO.WORKORDER_OBJECT_CODE LIKE ?)"
                                    + "   OR (? ='' OR EO.WORKORDER_OBJECT_LOCATION LIKE ?))"
                                    + "   ORDER BY EO.WORKORDER_OBJECT_CODE DESC,ESI.ITEM_NAME,ESI.ITEM_SPEC";

                            sqlArgs.add(userAccount.getOrganizationId());
                            sqlArgs.add(userAccount.getOrganizationId());
                            sqlArgs.add(dto.getItemName());
                            sqlArgs.add(dto.getItemName());
                            sqlArgs.add(dto.getItemSpec());
                            sqlArgs.add(dto.getItemSpec());
                            sqlArgs.add(dto.getBarcode());
                            sqlArgs.add(dto.getBarcode());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getCostCenter());
                            sqlArgs.add(dto.getCostCenter());

                            sqlArgs.add(dto.getWorkorderObjectLocation());
                            sqlArgs.add(dto.getWorkorderObjectLocation());
                            sqlArgs.add(dto.getWorkorderObjectLocation());
                            sqlArgs.add(dto.getWorkorderObjectLocation());
                            sqlModel.setSqlStr(sqlStr);
                            sqlModel.setArgs(sqlArgs);
                        } else if (dto.getMatch().equals("Y")) {
                            String sqlStr = "SELECT "
                                    + " EII.BARCODE,\n"
                                    + " EII.BARCODE FA_BARCODE,\n"
                                    + " ESI.ITEM_NAME ITEM_DESCRIPTION,\n"
                                    + " ESI.ITEM_SPEC SPEC,\n"
                                    + " EFTA.ASSETS_DESCRIPTION,\n"
                                    + " EFTA.CURRENT_UNITS,\n"
                                    + " EFTA.MODEL_NUMBER MIS_SPEC,\n"
                                    + " EO.WORKORDER_OBJECT_CODE,\n"
                                    + " EFTA.ASSETS_LOCATION_CODE,\n"
                                    + " EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION,\n"
                                    + " EFTA.ASSETS_LOCATION MIS_LOCATION,\n"
                                    + " EII.SYSTEMID,\n"
                                    + " AME.USER_NAME,\n"
                                    + " EFTA.ASSIGNED_TO_NAME,\n"
                                    + " EFTA.ASSET_ID,\n"
                                    + " EO.ORGANIZATION_ID,"
                                    + " EFTA.SEGMENT1||'.'||EFTA.SEGMENT2||'.'||EFTA.SEGMENT3 FA_CODE ,\n"
                                    + " EFTA.FA_CATEGORY2,\n"
                                    + " EII.CONTENT_CODE,\n"
                                    + " EII.CONTENT_NAME\n"
                                    + " FROM "
                                    + " ETS_ITEM_INFO      EII,\n"
                                    + " ETS_SYSTEM_ITEM    ESI,\n"
                                    + " ETS_FA_ASSETS_TD   EFTA,\n"
                                    + " AMS_OBJECT_ADDRESS AOA,\n"
                                    + " ETS_OBJECT         EO,\n"
                                    + " AMS_MIS_EMPLOYEE   AME,\n"
                                    + " AMS_MIS_DEPT       AMD,\n"
                                    // " ETS_ITEM_MATCH_REC EIM\n" +
                                    + " ETS_ITEM_MATCH_TD_REC EIMTR\n"
                                    + " WHERE "
                                    + " EIMTR.MATCH_TYPE = '8'\n"
                                    + " AND EIMTR.SYSTEM_ID = EII.SYSTEMID\n"
                                    + " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
                                    + " AND EII.BARCODE = EFTA.TAG_NUMBER\n"
                                    + " AND AOA.ADDRESS_ID = EII.ADDRESS_ID\n"
                                    + " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
                                    + " AND EII.RESPONSIBILITY_USER = AME.EMPLOYEE_ID\n"
                                    + " AND AMD.DEPT_CODE = EII.RESPONSIBILITY_DEPT\n"  ;
                                    if(userAccount.isDptAssetsManager() && !userAccount.isComAssetsManager()){
                                            DTOSet depts = userAccount.getPriviDeptCodes();
                                          AmsMisDeptDTO dept = null;
                                        if( null != depts ){
                                            String deptCodes = "(";
                                            for (int i = 0; i < depts.getSize(); i++) {
                                                dept = (AmsMisDeptDTO) depts.getDTO(i);
                                                deptCodes += "'" + dept.getDeptCode() + "', ";
                                            }
                                            deptCodes += "'')";
                                            sqlStr += " AND EII.RESPONSIBILITY_DEPT IN " + deptCodes;
                                        }
                                    }
                                    sqlStr += " AND EO.ORGANIZATION_ID = ?"
                                    + " AND EII.BARCODE LIKE dbo.NVL(?,EII.BARCODE)"
                                    + " AND (? ='' OR  ESI.ITEM_NAME LIKE ?)"
                                    + " AND (? ='' OR ESI.ITEM_SPEC LIKE ?)"
                                    + " AND ((? ='' OR EFTA.ASSIGNED_TO_NAME LIKE ?) OR (? ='' OR AME.USER_NAME LIKE ?))"
                                    + " AND (? ='' OR EO.WORKORDER_OBJECT_LOCATION LIKE ?)";
                                    /*if(dto.getSame().equals("Y")){
                                        sqlStr+="AND EFTA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" +
                                                "AND EFTA.MODEL_NUMBER = ESI.ITEM_SPEC\n" +
                                                "AND EFTA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                                                "AND EFTA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" +
                                                "AND EFTA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                                    }*/

                                    if(dto.getNameSame().equals("Y")){
                                        sqlStr+="AND EFTA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" ;
                                    }
                                    if(dto.getSpecSame().equals("Y")){
                                        sqlStr+="AND EFTA.MODEL_NUMBER = ESI.ITEM_SPEC \n" ;
                                    }
                                    if(dto.getResSame().equals("Y")){
                                        sqlStr+="AND EFTA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                                    }
                                    if(dto.getLocSame().equals("Y")){
                                        sqlStr+="AND EFTA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                                                "AND EFTA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" ;
                                    }

                            sqlArgs.add(userAccount.getOrganizationId());
                            sqlArgs.add(dto.getBarcode());
                            sqlArgs.add(dto.getItemName());
                            sqlArgs.add(dto.getItemName());
                            sqlArgs.add(dto.getItemSpec());
                            sqlArgs.add(dto.getItemSpec());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getResponsibilityUser());
                            sqlArgs.add(dto.getWorkorderObjectLocation());
                            sqlArgs.add(dto.getWorkorderObjectLocation());

                            sqlModel.setSqlStr(sqlStr);
                            sqlModel.setArgs(sqlArgs);
                        }

        } else { // �����ʲ�
			if (dto.getMatch().equals("N")) {
				String sqlStr = "SELECT \n"
						+ " EII.BARCODE,   \n"
						// --AMS����
						+ " EFA.TAG_NUMBER FA_BARCODE,   \n"
						// --�ʲ������
						+ " ESI.ITEM_NAME ITEM_DESCRIPTION,  \n"
						// -- EAM�豸����
						+ " ESI.ITEM_SPEC SPEC,  \n"
						// --EAM�豸�ͺ�
						+ " EFA.ASSETS_DESCRIPTION, \n"
						// MIS�ʲ�����
						+ " EFA.CURRENT_UNITS, \n"
						// --MIS�ʲ�����
						+ " EFA.MODEL_NUMBER MIS_SPEC, \n"
						// MIS�豸�ͺ�
						+ " EO.WORKORDER_OBJECT_CODE,\n"
						+ " EFA.ASSETS_LOCATION_CODE,\n"
						+ " EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION, \n"
						// AMS�ص�
						+ " EFA.ASSETS_LOCATION MIS_LOCATION, \n"
						// MIS�ص�
						+ " EII.SYSTEMID,\n"
						+ " EFA.ASSET_ID,\n"
						+ " AME.USER_NAME,\n"
						+ " EFA.ASSIGNED_TO_NAME,\n"
						+ " EO.ORGANIZATION_ID,"
						+ " EFA.SEGMENT1||'.'||EFA.SEGMENT2||'.'||EFA.SEGMENT3 FA_CODE ,\n"
                        + " EFA.FA_CATEGORY2,\n" 
                        + " EII.CONTENT_CODE,\n"
                        + " EII.CONTENT_NAME\n"
						+ " FROM "
						+ " ETS_FA_ASSETS      EFA,\n"
						+ " ETS_OBJECT         EO,\n"
						+ " AMS_OBJECT_ADDRESS AOA,\n"
						+ " ETS_ITEM_INFO      EII,\n"
						+ " ETS_SYSTEM_ITEM    ESI,\n"
						+ " AMS_MIS_EMPLOYEE   AME,\n"
						+ " AMS_MIS_DEPT 	   AMD\n"
						+ " WHERE"
						+ " AOA.ADDRESS_ID = EII.ADDRESS_ID\n"
						+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
						+ " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
						+ " AND EII.BARCODE = EFA.TAG_NUMBER\n"
						+ " AND EII.RESPONSIBILITY_USER = AME.EMPLOYEE_ID\n"
						+ " AND (EII.DISABLE_DATE IS NULL OR EII.DISABLE_DATE = '')  \n"
						// ʧЧ����Ϊ��
						+ " AND EII.ITEM_STATUS <>'DISCARDED'  \n"
						// �Ǳ��ϣ�EAM��
						+ " AND EFA.COST <> 0\n"
//						+" AND EII.BARCODE LIKE '"
//						+ code
//						+ "%'\n"
						+ " AND (EFA.IS_RETIREMENTS = 0 OR EFA.IS_RETIREMENTS = 2) \n"
						// �Ǳ���
						+ " AND EO.IS_TEMP_ADDR = 0  \n"
						// ����ʱ�ص�
						+ " AND EII.FINANCE_PROP <> 'OTHER'  \n" ;
                   		if(userAccount.isDptAssetsManager() && !userAccount.isComAssetsManager()){
                   			DTOSet depts = userAccount.getPriviDeptCodes();
                            AmsMisDeptDTO dept = null;
                            if( null != depts ){
                            String deptCodes = "(";
                            for (int i = 0; i < depts.getSize(); i++) {
                            	dept = (AmsMisDeptDTO) depts.getDTO(i);
                                deptCodes += "'" + dept.getDeptCode() + "', ";
                            }
                            deptCodes += "'')";
                            sqlStr += " AND EII.RESPONSIBILITY_DEPT IN " + deptCodes;
                            }
                   		}
                        sqlStr+= " AND AMD.DEPT_CODE = EII.RESPONSIBILITY_DEPT\n"
						+ " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH WHERE  SYSTEMID = EII.SYSTEMID )" 
                        + " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH WHERE ASSET_ID = EFA.ASSET_ID) \n"
						+ " AND NOT EXISTS( SELECT 1 FROM ETS_NO_MATCH ENM WHERE ENM.SYSTEMID = EII.SYSTEMID )" 
                        + " AND NOT EXISTS( SELECT 1 FROM ETS_ITEM_MATCH WHERE ASSET_ID = EFA.ASSET_ID) \n"
						// ��ETS_NO_MATCH�е��ʲ����μ�ƥ��
                        
                        + " AND NOT EXISTS (SELECT NULL FROM AMS_IGNORE_DATA H WHERE EFA.SEGMENT2 = H.SEGMENT AND EFA.SEGMENT3 = H.NAME) \n"
                        
						+ " AND EII.ORGANIZATION_ID = ?\n"
						+ " AND EFA.ORGANIZATION_ID = ?\n"
						+ " AND (CONVERT(INT, EO.OBJECT_CATEGORY) < = 70 OR CONVERT(INT, EO.OBJECT_CATEGORY) = 80)\n";

				if (servletConfig.getProvinceCode().equals(DictConstant.PROVINCE_CODE_JIN)) {
					sqlStr += " AND EII.BARCODE IN (\n"
							+ " SELECT C.BARCODE\n"
							+ "  FROM (SELECT EWDD.BARCODE\n"
							// Ѳ��
							+ "          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n"
							+ "         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n"
							+ "           AND EW.WORKORDER_FLAG = '14'\n"
							+ "           AND EWDD.VERIFY_RESULT = 'ɨ����Ϊ׼'\n"
							+ "        UNION ALL\n"
							+ "        SELECT EWDD.BARCODE\n"
							+ "          FROM ETS_WORKORDER_DIFF_DTL EWDD, ETS_WORKORDER EW\n"
							+ "         WHERE EWDD.WORKORDER_NO = EW.WORKORDER_NO\n"
							+ "           AND EWDD.VERIFY_RESULT = 'ϵͳ����Ϊ׼'\n"
							+ "           AND NOT EXISTS (SELECT 1\n"
							+ "                  FROM ETS_WORKORDER_DTL EWD\n"
							+ "                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n"
							+ "                   AND EWDD.BARCODE = EWD.BARCODE)\n"
							+ "        UNION ALL\n"
							+ "        SELECT EWD.BARCODE\n"
							+ "          FROM ETS_WORKORDER_DTL EWD, ETS_WORKORDER EW\n"
							+ "         WHERE EWD.WORKORDER_NO = EW.WORKORDER_NO\n"
							+ "           AND EWD.ITEM_STATUS < 6\n"
							+ "           AND NOT EXISTS (SELECT 1\n"
							+ "                  FROM ETS_WORKORDER_DIFF_DTL EWDD\n"
							+ "                 WHERE EWDD.WORKORDER_NO = EWD.WORKORDER_NO\n"
							+ "                   AND EWD.BARCODE = EWDD.BARCODE)\n"
							+ "         UNION ALL\n"
							+ "         SELECT AACL.BARCODE\n"
							// �̵�
							+ "          FROM AMS_ASSETS_CHK_LOG AACL\n"
							+ "         WHERE AACL.IS_EXIST = 'Y') C )\n";
				}
                /*if(dto.getSame().equals("Y")){
                    sqlStr+="AND EFA.ASSETS_DESCRIPTION=ESI.ITEM_NAME\n" +
                            "            AND EFA.MODEL_NUMBER=ESI.ITEM_SPEC\n" +
                            "            AND EFA.ASSETS_LOCATION=EO.WORKORDER_OBJECT_LOCATION\n" +
                            "            AND EFA.ASSETS_LOCATION_CODE=EO.LOCATION_CODE\n" +
                            "            AND EFA.ASSIGNED_TO_NAME=AME.USER_NAME \n";
                }*/
				
                if(dto.getNameSame().equals("Y")){
                    sqlStr+="AND EFA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" ;
                }
                if(dto.getSpecSame().equals("Y")){
                    sqlStr+="AND EFA.MODEL_NUMBER = ESI.ITEM_SPEC \n" ;
                }
                if(dto.getResSame().equals("Y")){
                    sqlStr+="AND EFA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                }
                if(dto.getLocSame().equals("Y")){
                    sqlStr+="AND EFA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                            "AND EFA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" ;
                } 
				
                if(dto.getCheck().equals("Y")){
                    sqlStr+="AND EXISTS (SELECT 'A'\n" +
                            "          FROM AMS_ASSETS_CHK_LOG ACL\n" +
                            "         WHERE ACL.BARCODE = EII.BARCODE\n" +
                            "           AND ACL.IS_EXIST = 'Y') ";
                }
                sqlStr += "   AND (? ='' OR  ESI.ITEM_NAME LIKE ?)"
						+ "   AND (? ='' OR ESI.ITEM_SPEC LIKE ?)"
						+ "   AND (? ='' OR EII.BARCODE LIKE ?)"
						+ "   AND ((? ='' OR EFA.ASSIGNED_TO_NAME LIKE ?) OR (? ='' OR AME.USER_NAME LIKE ?))"
						+ "   AND (? =''OR  EFA.DEPRECIATION_ACCOUNT LIKE '%'||?||'%')\n"
						+ "   AND ((? ='' OR EO.WORKORDER_OBJECT_CODE LIKE ?)"
						+ "   OR (? ='' OR EO.WORKORDER_OBJECT_LOCATION LIKE ?))"
						+ "   ORDER BY EO.WORKORDER_OBJECT_CODE DESC,ESI.ITEM_NAME,ESI.ITEM_SPEC";

				sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getBarcode());
				sqlArgs.add(dto.getBarcode());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getCostCenter());
				sqlArgs.add(dto.getCostCenter());

				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlModel.setSqlStr(sqlStr);
				sqlModel.setArgs(sqlArgs);
			} else if (dto.getMatch().equals("Y")) {
				String sqlStr = "SELECT "
						+ " EII.BARCODE,\n"
						+ " EII.BARCODE FA_BARCODE,\n"
						+ " ESI.ITEM_NAME ITEM_DESCRIPTION,\n"
						+ " ESI.ITEM_SPEC SPEC,\n"
						+ " EFA.ASSETS_DESCRIPTION,\n"
						+ " EFA.CURRENT_UNITS,\n"
						+ " EFA.MODEL_NUMBER MIS_SPEC,\n"
						+ " EO.WORKORDER_OBJECT_CODE,\n"
						+ " EFA.ASSETS_LOCATION_CODE,\n"
						+ " EO.WORKORDER_OBJECT_LOCATION ETS_LOCATION,\n"
						+ " EFA.ASSETS_LOCATION MIS_LOCATION,\n"
						+ " EII.SYSTEMID,\n"
						+ " AME.USER_NAME,\n"
						+ " EFA.ASSIGNED_TO_NAME,\n"
						+ " EFA.ASSET_ID,\n"
						+ " EO.ORGANIZATION_ID," 
						+ " EFA.SEGMENT1||'.'||EFA.SEGMENT2||'.'||EFA.SEGMENT3 FA_CODE ,\n" 
						+ " EFA.FA_CATEGORY2,\n" 
						+ " EII.CONTENT_CODE,\n" 
						+ " EII.CONTENT_NAME\n"
						+ " FROM "
						+ " ETS_ITEM_INFO      EII,\n"
						+ " ETS_SYSTEM_ITEM    ESI,\n"
						+ " ETS_FA_ASSETS      EFA,\n"
						+ " AMS_OBJECT_ADDRESS AOA,\n"
						+ " ETS_OBJECT         EO,\n"
						+ " AMS_MIS_EMPLOYEE   AME,\n"
						+ " AMS_MIS_DEPT 	   AMD,\n"
						+ " ETS_ITEM_MATCH_REC EIM\n"
						+ " WHERE "
						+ " EIM.MATCH_TYPE = '8'\n"
						+ " AND EIM.SYSTEM_ID = EII.SYSTEMID\n"
						+ " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
						+ " AND EII.BARCODE = EFA.TAG_NUMBER\n"
						+ " AND AOA.ADDRESS_ID = EII.ADDRESS_ID\n"
						+ " AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n"
						+ " AND EII.RESPONSIBILITY_USER = AME.EMPLOYEE_ID\n"
						+ " AND AMD.DEPT_CODE = EII.RESPONSIBILITY_DEPT\n"  ;
                if(userAccount.isDptAssetsManager() && !userAccount.isComAssetsManager()){
                	DTOSet depts = userAccount.getPriviDeptCodes();
                	AmsMisDeptDTO dept = null;
                	if( null != depts ){
                		String deptCodes = "(";
                		for (int i = 0; i < depts.getSize(); i++) {
                			dept = (AmsMisDeptDTO) depts.getDTO(i);
                			deptCodes += "'" + dept.getDeptCode() + "', ";
                		}
                		deptCodes += "'')";
                		sqlStr += " AND EII.RESPONSIBILITY_DEPT IN " + deptCodes;
                	}
                }
                sqlStr+=  " AND EO.ORGANIZATION_ID = ?"
                		+ " AND EII.BARCODE LIKE dbo.NVL(?,EII.BARCODE)"
                		+ " AND (? ='' OR  ESI.ITEM_NAME LIKE ?)"
                		+ " AND (? ='' OR ESI.ITEM_SPEC LIKE ?)"
                		+ " AND ((? ='' OR EFA.ASSIGNED_TO_NAME LIKE ?) OR (? ='' OR AME.USER_NAME LIKE ?))"
                		+ " AND (? ='' OR EO.WORKORDER_OBJECT_LOCATION LIKE ?) ";
                /*if(dto.getSame().equals("Y")){
                    sqlStr+="AND EFA.ASSETS_DESCRIPTION=ESI.ITEM_NAME\n" +
                            "            AND EFA.MODEL_NUMBER=ESI.ITEM_SPEC\n" +
                            "            AND EFA.ASSETS_LOCATION=EO.WORKORDER_OBJECT_LOCATION\n" +
                            "            AND EFA.ASSETS_LOCATION_CODE=EO.LOCATION_CODE\n" +
                            "            AND EFA.ASSIGNED_TO_NAME=AME.USER_NAME";
                }*/
                
                if(dto.getNameSame().equals("Y")){
                    sqlStr+="AND EFA.ASSETS_DESCRIPTION = ESI.ITEM_NAME \n" ;
                }
                if(dto.getSpecSame().equals("Y")){
                    sqlStr+="AND EFA.MODEL_NUMBER = ESI.ITEM_SPEC \n" ;
                }
                if(dto.getResSame().equals("Y")){
                    sqlStr+="AND EFA.ASSIGNED_TO_NAME = AME.USER_NAME \n";
                }
                if(dto.getLocSame().equals("Y")){
                    sqlStr+="AND EFA.ASSETS_LOCATION = EO.WORKORDER_OBJECT_LOCATION \n" +
                            "AND EFA.ASSETS_LOCATION_CODE = EO.LOCATION_CODE \n" ;
                } 
                
                sqlArgs.add(userAccount.getOrganizationId());
				sqlArgs.add(dto.getBarcode());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemName());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getItemSpec());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getResponsibilityUser());
				sqlArgs.add(dto.getWorkorderObjectLocation());
				sqlArgs.add(dto.getWorkorderObjectLocation());

				sqlModel.setSqlStr(sqlStr);
				sqlModel.setArgs(sqlArgs);
			}
		}
		return sqlModel;
	}
}
