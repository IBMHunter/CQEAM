package com.sino.ams.match.amsMisLocMatch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.match.amsMisLocMatch.dto.AmsMisLocMatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-11-21
 * Time: 19:43:18
 * To change this template use File | Settings | File Templates.
 */
public class AmsMisLocMatchModel extends BaseSQLProducer {
    private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����޷���(EAM) AMS_HOUSE_INFO ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsHouseInfoDTO ���β���������
	 */
	public AmsMisLocMatchModel(SfUserDTO userAccount, AmsMisLocMatchDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�����Զ��������޷���(EAM) AMS_HOUSE_INFO���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 * @throws com.sino.base.exception.SQLModelException
     */
	public SQLModel getDataCreateModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsMisLocMatchDTO amsHouseInfo = (AmsMisLocMatchDTO) dtoParameter;
			String sqlStr = "INSERT INTO "
							+ " AMS_RENT_INFO("
                            + " RENT_ID,"
                            + " BARCODE,"
							+ " RENT_DATE,"
							+ " END_DATE,"
							+ " RENT_PERSON"
                            + ") VALUES ("
							+ "  NEWID() , ?, ?, ?, ?)";

			sqlArgs.add(amsHouseInfo.getBarcode());
//			sqlArgs.add(amsHouseInfo.getRentDate());
			sqlArgs.add(amsHouseInfo.getEndDate());
//			sqlArgs.add(amsHouseInfo.getRentPerson());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������޷���(EAM) AMS_HOUSE_INFO���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getDataUpdateModel() throws SQLModelException{
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsMisLocMatchDTO rentDTO = (AmsMisLocMatchDTO) dtoParameter;
			String sqlStr = "UPDATE "
							+ " AMS_RENT_INFO"
							+ " SET"
							+ " RENT_PERSON = ?,"
							+ " RENT_DATE = ?,"
							+ " END_DATE = ?"
							+ " WHERE"
							+ " RENT_ID = ?";
//            sqlArgs.add(rentDTO.getRentPerson());
//            sqlArgs.add(rentDTO.getRentDate());
            sqlArgs.add(rentDTO.getEndDate());
//            sqlArgs.add(rentDTO.getRentId());
            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������޷���(EAM) AMS_HOUSE_INFO����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMisLocMatchDTO rentDTO = (AmsMisLocMatchDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
			+ " AMS_RENT_INFO"
			+ " WHERE"
			+ " RENT_ID = ?";
//		sqlArgs.add(rentDTO.getRentId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������޷���(EAM) AMS_HOUSE_INFO������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){                   //����
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsMisLocMatchDTO rentDTO = (AmsMisLocMatchDTO)dtoParameter;
		String sqlStr = "SELECT ARI.BARCODE,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       ARI.RENT_ID,\n" +
                "       ARI.RENT_PERSON,\n" +
                "       ARI.RENT_DATE,\n" +
                "       ARI.END_DATE\n" +
                "  FROM ETS_ITEM_INFO EII, ETS_SYSTEM_ITEM ESI, AMS_RENT_INFO ARI\n" +
                " WHERE ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                "   AND EII.BARCODE = ARI.BARCODE\n" +
                "   AND EII.ATTRIBUTE1 = 'RENT'\n" +
                "   AND EII.FINANCE_PROP = 'ASSETS'"+
                "   AND ARI.RENT_ID = ?";
//        sqlArgs.add(rentDTO.getRentId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�������������ֶ� barcodeNo �����ѯ����SQL��
	 * ����Զ������������޷���(EAM) AMS_HOUSE_INFO��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param barcodeNo String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByBarcodeNoModel(String barcodeNo){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " RENT_PERSON,"
			+ " RENT_DATE,"
			+ " HOUSE_ADDRESS,"
			+ " FLOOR_NUMBER,"
			+ " HOUSE_NO,"
			+ " HOUSE_AREA,"
			+ " AREA_UNIT,"
			+ " RENTAL,"
			+ " MONEY_UNIT,"
			+ " PAY_TYPE,"
			+ " END_DATE"
			+ " FROM"
			+ " AMS_HOUSE_INFO"
			+ " WHERE"
			+ " BARCODE = ?";
		sqlArgs.add(barcodeNo);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		AmsMisLocMatchDTO amsHouseInfo = (AmsMisLocMatchDTO)dtoParameter;
		if(foreignKey.equals("barcodeNo")){
			sqlModel = getDataByBarcodeNoModel(amsHouseInfo.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� barcodeNo ��������ɾ��SQL��
	 * ����Զ������������޷���(EAM) AMS_HOUSE_INFO ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param barcodeNo String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDeleteByBarcodeNoModel(String barcodeNo){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " AMS_HOUSE_INFO"
						+ " WHERE"
						+ " BARCODE = ?";
		sqlArgs.add(barcodeNo);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		AmsMisLocMatchDTO amsHouseInfo = (AmsMisLocMatchDTO)dtoParameter;
		if(foreignKey.equals("barcodeNo")){
			sqlModel = getDeleteByBarcodeNoModel(amsHouseInfo.getBarcode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ��г�δƥ��EAM�ص㡣
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPageQueryModel() throws SQLModelException{             // �г�δƥ��EAM�ص�
		SQLModel sqlModel = new SQLModel();
//		try {
			int orgid = sfUser.getOrganizationId();
            List sqlArgs = new ArrayList();
			AmsMisLocMatchDTO locDTO = (AmsMisLocMatchDTO) dtoParameter;
			 String etsSql="	SELECT EO.WORKORDER_OBJECT_NO , " +
                      "        EO.WORKORDER_OBJECT_CODE, "  +
                      " 	   EO.WORKORDER_OBJECT_LOCATION  " +
					  "	FROM   ETS_OBJECT EO " +
					  //<70�����80���ǲֿ�ص�
					  " WHERE ( EO.OBJECT_CATEGORY <=70 OR EO.OBJECT_CATEGORY  = 80)" +
                      "	AND  EO.IS_TEMP_ADDR = 0 "+
					  " AND  (EO.DISABLE_DATE IS NULL OR EO.DISABLE_DATE='')  "+
					  "	AND  NOT EXISTS "+
					  "        (SELECT 1 FROM ETS_MIS_LOCATION_MATCH EMLM " +
					  "			WHERE EMLM.WORKORDER_OBJECT_NO=EO.WORKORDER_OBJECT_NO " +
					  "			AND EMLM.ORGANIZATION_ID="+orgid+")"+
					  " AND  EO.ORGANIZATION_ID="+orgid+
                      " AND ( " + SyBaseSQLUtil.isNull() + "  OR  EO.WORKORDER_OBJECT_LOCATION  LIKE ?) " +
                      " AND ( " + SyBaseSQLUtil.isNull() + "  OR  EO.WORKORDER_OBJECT_CODE  LIKE ?) " +
                      " ORDER BY EO.WORKORDER_OBJECT_LOCATION  ";

            sqlArgs.add(locDTO.getEAMworkObjectLocation());
            sqlArgs.add(locDTO.getEAMworkObjectLocation());
            sqlArgs.add(locDTO.getWorkorderObjectCode());
            sqlArgs.add(locDTO.getWorkorderObjectCode());

            sqlModel.setSqlStr(etsSql);
			sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
		return sqlModel;
	}


	/**
	 * ���ܣ��г�δƥ��MIS�ص㡣
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getMISLoc() throws SQLModelException{             // �г�δƥ��MIS�ص�
		SQLModel sqlModel = new SQLModel();
            int orgid = sfUser.getOrganizationId();
            List sqlArgs = new ArrayList();
			AmsMisLocMatchDTO locDTO = (AmsMisLocMatchDTO) dtoParameter;
		    String misSql=" SELECT " +
                      " EFA.ASSETS_LOCATION_CODE," +
                      " EFA.ASSETS_LOCATION  " +
					  " FROM ETS_FA_ASSETS_LOCATION EFA ,ETS_OU_CITY_MAP EOCM "+
					  " WHERE SUBSTRING(EFA.BOOK_TYPE_CODE,-4,4)=EOCM.COMPANY_CODE "+
		              " AND EOCM.ORGANIZATION_ID="+orgid+
		              " AND  NOT EXISTS "+
		              "  	(SELECT 1 FROM ETS_MIS_LOCATION_MATCH EMLM "+
		              "       WHERE EMLM.ASSETS_LOCATION=EFA.ASSETS_LOCATION "+
		              "       AND EMLM.ORGANIZATION_ID="+orgid+
		              " 	)"	+
                      " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.ASSETS_LOCATION_CODE LIKE ?)"+
                      " AND ( " + SyBaseSQLUtil.isNull() + "  OR EFA.ASSETS_LOCATION LIKE ?)"+
                      "	GROUP BY  EFA.ASSETS_LOCATION_CODE , EFA.ASSETS_LOCATION ";
//                      " AND  ( " + SyBaseSQLUtil.isNull() + "  OR  EFA.ASSETS_LOCATION LIKE ?)";
             sqlArgs.add(locDTO.getAssetsLocationCode());
             sqlArgs.add(locDTO.getAssetsLocationCode());

            sqlArgs.add(locDTO.getMISAssetsLocation());
            sqlArgs.add(locDTO.getMISAssetsLocation());

            sqlModel.setSqlStr(misSql);
			sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


    /**
	 * �г���ƥ��ĵص���Ϣ
	 * @return webData
	 */
    public SQLModel getMatchedModel(int orgId){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMisLocMatchDTO locDTO = (AmsMisLocMatchDTO) dtoParameter;
       String matchedSql=	" SELECT " +
                        "        EMLM.WORKORDER_OBJECT_NO||'@@@'||EMLM.ASSETS_LOCATION  NO_AND_LOCATION,"+
                        "        EMLM.WORKORDER_OBJECT_NO ," +
						" 		 EO.WORKORDER_OBJECT_LOCATION ," +
						" 	     EMLM.ASSETS_LOCATION " +
						" FROM 	 ETS_MIS_LOCATION_MATCH EMLM,ETS_OBJECT EO " +
						" WHERE  EMLM.ORGANIZATION_ID="+orgId +
                        "	AND  EO.IS_TEMP_ADDR = 0 "+
                        "   AND  EMLM.WORKORDER_OBJECT_NO=EO.WORKORDER_OBJECT_NO";
        sqlModel.setSqlStr(matchedSql);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


     /**
	 * ƥ��ETSMIS�ص�
	 * @return boolean
	 */
	public boolean matchETSMIS(String[] location ,String orgid){
		Connection conn=null;
		int org=Integer.parseInt(orgid);
		String sql="INSERT INTO ETS_MIS_LOCATION_MATCH" +
				"(WORKORDER_OBJECT_NO,ASSETS_LOCATION,ORGANIZATION_ID) " +
				" VALUES(?,?,?)";
		try{
			conn= DBManager.getDBConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<location.length;i++){
				conn.setAutoCommit(false);
				String[] loc= StrUtil.splitStr(location[i],"----");
				String etsNO=loc[0];
				String misLocation=loc[1];
				int no=Integer.parseInt(etsNO);
				pstmt.setInt(1,no);
				pstmt.setString(2,misLocation);
				pstmt.setInt(3,org);
				pstmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(true);
			}
			return true;
		}catch(Exception e){
			Logger.logError(e);
			try{
				conn.rollback();
			}catch(Exception ee){
				Logger.logError(ee);
			}
			return false;
		}finally{
            DBManager.closeDBConnection(conn);
        }
	}



	/**
	 *����; ƥ��ETSMIS�ص�
	 * @return boolean
	 */
	public SQLModel matchETSMISLoc() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
			List sqlArgs = new ArrayList();
			AmsMisLocMatchDTO LocDTO = (AmsMisLocMatchDTO) dtoParameter;
			String sqlStr="INSERT INTO ETS_MIS_LOCATION_MATCH" +
				"(WORKORDER_OBJECT_NO,ASSETS_LOCATION,ORGANIZATION_ID) " +
				" VALUES(?,?,?)";
			sqlArgs.add(LocDTO.getWorkorderObjectNo());
			sqlArgs.add(LocDTO.getAssetsLocation());
			sqlArgs.add(sfUser.getOrganizationId());

            sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    /**
	 * ���ƥ��
	 * @param arr
	 * @return  boolean
	 */
	public boolean dematch(String[] arr){
		Connection conn=null;
		String sql="DELETE FROM ETS_MIS_LOCATION_MATCH " +
				"	WHERE WORKORDER_OBJECT_NO=? " +
				"	AND ASSETS_LOCATION=?";
		try{
			conn=DBManager.getDBConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<arr.length;i++){
				conn.setAutoCommit(false);
				String[] loc=StrUtil.splitStr(arr[i],"----");
				String etsNO=loc[0];
				String misLocation=loc[1];
				int no=Integer.parseInt(etsNO);
				pstmt.setInt(1,no);
				pstmt.setString(2,misLocation);
				pstmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(true);
			}
			return true;
		}catch(Exception e){
			Logger.logError(e);
			try{
				conn.rollback();
			}catch(Exception ee){
				Logger.logError(ee);
			}
			return false;
		}finally{
            DBManager.closeDBConnection(conn);
        }
	}

  /*
  **���ܣ����н��ƥ�������
   */
    public SQLModel getDelMatchModel(){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMisLocMatchDTO locDTO = (AmsMisLocMatchDTO) dtoParameter;
        String sqlStr="DELETE FROM ETS_MIS_LOCATION_MATCH " +
				"	WHERE WORKORDER_OBJECT_NO=? " +
				"	AND ASSETS_LOCATION=?";
       sqlArgs.add(locDTO.getWorkorderObjectNo());
       sqlArgs.add(locDTO.getAssetsLocation());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getupdataEIIModel(){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsMisLocMatchDTO rentDTO = (AmsMisLocMatchDTO) dtoParameter;
        String sqlStr = " UPDATE" +
                "  ETS_ITEM_INFO EII\n" +
                "  SET" +
                "  EII.ATTRIBUTE1 = 'RENT'\n" +
                "  WHERE " +
                "  EII.BARCODE = ?\n" +
          sqlArgs.add(rentDTO.getBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
