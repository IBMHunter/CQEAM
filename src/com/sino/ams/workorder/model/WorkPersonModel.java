package com.sino.ams.workorder.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.ArrUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-1-18
 * Time: 10:04:09
 * To change this template use File | Settings | File Templates.
 */
public class WorkPersonModel extends AMSSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsRentDeadlineDTO ���β���������
     */
    public WorkPersonModel(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException
     *          ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
        EtsWorkorderDTO etsWorkorderDTO = (EtsWorkorderDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_RENT_DEADLINE("
                + " DEADLINE_ID,"
                + " BARCODE,"
                + " NOTICE_BEFORE,"
                + " ORGANIZATION_ID,"
                + " CTREATION_DATE,"
                + " CREATED_BY"
                + ") VALUES ("
                + "  NEWID() , ?, ?, ?, GETDATE(),?)";


        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
        EtsWorkorderDTO etsWorkorderDTO = (EtsWorkorderDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_RENT_DEADLINE"
                + " SET"
                + " BARCODE = ?,"
                + " NOTICE_BEFORE = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " DEADLINE_ID = ?";


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�������������(EAM) AMS_RENT_DEADLINE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsWorkorderDTO etsWorkorderDTO = (EtsWorkorderDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_RENT_DEADLINE"
                + " WHERE"
                + " DEADLINE_ID = ?";

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {                   //��ϸ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsWorkorderDTO etsWorkorderDTO = (EtsWorkorderDTO) dtoParameter;
        String sqlStr = "SELECT"
                + " ARD.DEADLINE_ID,\n"
                + " ESI.ITEM_NAME,\n"
                + " ESI.ITEM_SPEC,\n"
                + " ESI.ITEM_CATEGORY,\n"
                + " ARD.BARCODE,\n"
                + " AHI.END_DATE,\n"
                + " ARD.NOTICE_BEFORE\n"
                + " FROM "
                + " ETS_ITEM_INFO     EII,\n"
                + " ETS_SYSTEM_ITEM   ESI,\n"
                + " AMS_HOUSE_INFO    AHI,\n"
                + " AMS_RENT_DEADLINE ARD\n"
                + " WHERE "
                + " AHI.BARCODE = ARD.BARCODE\n"
                + " AND EII.BARCODE = AHI.BARCODE\n"
                + " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
                + " AND  " + SyBaseSQLUtil.isNotNull("AHI.END_DATE") + " \n"
                + " AND ARD.DEADLINE_ID = ?"
                + " UNION ALL\n"
                + " SELECT"
                + " ARD.DEADLINE_ID,\n"
                + " ESI.ITEM_NAME,\n"
                + " ESI.ITEM_SPEC,\n"
                + " ESI.ITEM_CATEGORY,\n"
                + " ARD.BARCODE,\n"
                + " ALI.END_DATE,\n"
                + " ARD.NOTICE_BEFORE\n"
                + " FROM "
                + " ETS_ITEM_INFO     EII,\n"
                + " ETS_SYSTEM_ITEM   ESI,\n"
                + " AMS_LAND_INFO     ALI,\n"
                + " AMS_RENT_DEADLINE ARD\n"
                + " WHERE "
                + " ALI.BARCODE = ARD.BARCODE\n"
                + " AND EII.BARCODE = ALI.BARCODE\n"
                + " AND ESI.ITEM_CODE = EII.ITEM_CODE\n"
                + " AND  " + SyBaseSQLUtil.isNotNull("ALI.END_DATE") + " "
                + " AND ARD.DEADLINE_ID = ?";


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getMuxDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
        EtsWorkorderDTO etsWorkorderDTO = (EtsWorkorderDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " DEADLINE_ID,"
                + " BARCODE,"
                + " NOTICE_BEFORE,"
                + " ORGANIZATION_ID,"
                + " CTREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_RENT_DEADLINE"
                + " WHERE"
                + " ( " + SyBaseSQLUtil.isNull() + "  OR DEADLINE_ID LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR BARCODE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR NOTICE_BEFORE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CTREATION_DATE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + " AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� barcode �����ѯ����SQL��
     *
     * @param barcode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " DEADLINE_ID,"
                + " NOTICE_BEFORE,"
                + " ORGANIZATION_ID,"
                + " CTREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_RENT_DEADLINE"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�������������ֶ� barcode ��������ɾ��SQL��
     *
     * @param barcode String
     * @return SQLModel ��������ɾ����SQLModel
     */
    private SQLModel getDeleteByBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " DEADLINE_ID,"
                + " NOTICE_BEFORE,"
                + " ORGANIZATION_ID,"
                + " CTREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_RENT_DEADLINE"
                + " WHERE"
                + " BARCODE = ?";
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {            //��ѯ
        SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsWorkorderDTO workorderDTO = (EtsWorkorderDTO) dtoParameter;
			String sqlStr = "SELECT " +
							" dbo.APP_GET_ORGNIZATION_NAME(EW.ORGANIZATION_ID) ORG_NAME,\n" +
							" EW.SYSTEMID,\n" +
							" EW.WORKORDER_NO,\n" +
							" dbo.APP_GET_FLEX_VALUE(EW.WORKORDER_FLAG, 'WORKORDER_STATUS') WORKORDER_FLAG_DESC,\n" +
							" EW.ATTRIBUTE4,\n" +
							" dbo.APP_GET_FLEX_VALUE(EW.WORKORDER_TYPE, 'WORKORDER_TYPE') WORKORDER_TYPE_DESC,\n" +
							" dbo.APP_GET_OBJECT_CODE(EW.WORKORDER_OBJECT_NO) WORKORDER_OBJECT_CODE,\n" +
							" dbo.APP_GET_OBJECT_NAME(EW.WORKORDER_OBJECT_NO) WORKORDER_OBJECT_NAME,\n" +
							" EW.START_DATE,\n" +
							" EW.IMPLEMENT_DAYS,\n" +
							" dbo.APP_GET_USER_NAME(EW.IMPLEMENT_BY) IMPLEMENT_USER,\n" +
							" EW.UPLOAD_DATE,\n" +
							" CASE EW.DIFFERENCE_REASON WHEN NULL THEN '��' ELSE '��' END DIFF,\n" +
							" CASE DATEDIFF(dd,ISNULL(EW.UPLOAD_DATE, GETDATE()),dbo.AWP_GET_DEADLINE_DATE(EW.START_DATE,EW.IMPLEMENT_DAYS)) WHEN -1 THEN '��' ELSE '��' END OVERTIME, \n" +
							" EW.ORGANIZATION_ID ,\n" +
							" EW.WORKORDER_BATCH \n" +
							" FROM   " +
							" ETS_WORKORDER EW, \n" +
							" ETS_WORKORDER_BATCH EWB, \n" +
							" ETS_OBJECT EO \n" +
							" WHERE " +
							" EW.WORKORDER_BATCH=EWB.WORKORDER_BATCH \n" +
							" AND EW.WORKORDER_OBJECT_NO=EO.WORKORDER_OBJECT_NO \n" +
							" AND EW.IMPLEMENT_BY= " + 
							userAccount.getUserId() +
//                " AND SU.USER_ID ="+ userAccount.getUserId()+
							" AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.WORKORDER_NO LIKE ?)\n" +
							" AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.START_DATE >= ?)\n" +
							" AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.WORKORDER_FLAG = ?)\n" +
//                " AND  SU.USERNAME = " + userAccount.
							" AND EW.ORGANIZATION_ID = ISNULL(?,EW.ORGANIZATION_ID)";
			sqlArgs.add(workorderDTO.getWorkorderNo());
			sqlArgs.add(workorderDTO.getWorkorderNo());
			sqlArgs.add(workorderDTO.getStartDate());
			sqlArgs.add(workorderDTO.getStartDate());
			sqlArgs.add(workorderDTO.getWorkorderFlag());
			sqlArgs.add(workorderDTO.getWorkorderFlag());
//        sqlArgs.add(workorderDTO.getExecuteUserName());
//        sqlArgs.add(workorderDTO.getExecuteUserName());
			if (userAccount.getOrganizationId() == SyBaseSQLUtil.NULL_INT_VALUE) {
				sqlArgs.add(null);
			} else {
				sqlArgs.add(userAccount.getOrganizationId());
			}
			//sqlArgs.add(workorderDTO.getOrganizationId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}

        return sqlModel;
    }


    /**
     * ���ܣ���ȡ�жϵ�ǰ�û��Ƿ���Ȩ��ִ�����ݱ༭������BARCODE �Ĵ�����
     *
     * @param barcode String
     * @return SQLModel
     */
    public SQLModel getVerifyBarcodeModel(String barcode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql = "SELECT "
                + " ARD.*"
                + " FROM"
                + " AMS_RENT_DEADLINE  ARD\n"
                + " WHERE"
                + " ARD.BARCODE = ?";
        strArg.add(barcode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }

    public SQLModel getUpdateTmpDataModel(String [] systemids,String implementBy) {
          SQLModel sqlModel = new SQLModel();
          List sqlArgs = new ArrayList();
          String inStr = ArrUtil.arrToSqlStr(systemids);
          String sqlStr = "UPDATE ETS_WORKORDER " +
                  " SET IMPLEMENT_BY='"+implementBy+"'"+
                  " WHERE SYSTEMID IN (" + inStr + ")";
          sqlModel.setSqlStr(sqlStr);
          sqlModel.setArgs(sqlArgs);
          return sqlModel;
      }


       public SQLModel getUsersOfGroup(String groupId) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT DISTINCT SU.USER_ID, SU.USERNAME\n" +
                "  FROM SF_USER_RIGHT SUR, SF_USER SU\n" +
                " WHERE SU.USER_ID = SUR.USER_ID\n" +
                "   AND SUR.GROUP_ID = ?\n" +
                " ORDER BY USERNAME";
        sqlArgs.add(groupId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
       }

     /**
     * ���ܣ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPersonQueryModel() throws SQLModelException {            //��ѯ
        SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			EtsWorkorderDTO workorderDTO = (EtsWorkorderDTO) dtoParameter;
			String sqlStr = "SELECT " +
							" AMS_PUB_PKG.GET_ORGNIZATION_NAME(EW.ORGANIZATION_ID) ORG_NAME,\n" +
							" EW.SYSTEMID,\n" +
							" EW.WORKORDER_NO,\n" +
							" AMS_PUB_PKG.GET_FLEX_VALUE(EW.WORKORDER_FLAG, 'WORKORDER_STATUS') WORKORDER_FLAG_DESC,\n" +
							" EW.ATTRIBUTE4,\n" +
							" AMS_PUB_PKG.GET_FLEX_VALUE(EW.WORKORDER_TYPE, 'WORKORDER_TYPE') WORKORDER_TYPE_DESC,\n" +
							" AMS_PUB_PKG.GET_OBJECT_CODE(EW.WORKORDER_OBJECT_NO) WORKORDER_OBJECT_CODE,\n" +
							" AMS_PUB_PKG.GET_OBJECT_NAME(EW.WORKORDER_OBJECT_NO) WORKORDER_OBJECT_NAME,\n" +
							" EW.START_DATE,\n" +
							" EW.IMPLEMENT_DAYS,\n" +
							" AMS_PUB_PKG.GET_USER_NAME(EW.IMPLEMENT_BY) IMPLEMENT_USER,\n" +
							" EW.UPLOAD_DATE,\n" +
							" CASE WHEN EW.DIFFERENCE_REASON IS NULL THEN '��' ELSE '��' END  DIFF,"+
							" DECODE(SIGN(dbo.NVL(EW.UPLOAD_DATE, GETDATE()) -\n" +
							"             AMS_WORKORDER_PKG.GET_DEADLINE_DATE(EW.START_DATE,\n" +
							"                                                 EW.IMPLEMENT_DAYS)),\n" +
							"        -1,\n" +
							"        '��',\n" +
							"        '��') OVERTIME,\n" +
							" EW.ORGANIZATION_ID ,\n" +
							" EW.WORKORDER_BATCH \n" +
							" FROM   " +
							" ETS_WORKORDER EW," +
							" ETS_WORKORDER_BATCH EWB ," +
							" ETS_OBJECT EO " +
							" WHERE " +
							" EW.WORKORDER_BATCH=EWB.WORKORDER_BATCH" +
							" AND EW.WORKORDER_OBJECT_NO=EO.WORKORDER_OBJECT_NO" +
							" AND EW.IMPLEMENT_BY=" + userAccount.getUserId() +
//                " AND SU.USER_ID ="+ userAccount.getUserId()+
							" AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.WORKORDER_NO LIKE ?)\n" +
							" AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.START_DATE >= ?)\n" +
							" AND (EW.WORKORDER_FLAG = ? OR EW.WORKORDER_FLAG = ?)\n" +
//                " AND  SU.USERNAME = " + userAccount.
							" AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.ORGANIZATION_ID = ?)";
			sqlArgs.add(workorderDTO.getWorkorderNo());
			sqlArgs.add(workorderDTO.getWorkorderNo());
			sqlArgs.add(workorderDTO.getStartDate());
			sqlArgs.add(workorderDTO.getStartDate());
			sqlArgs.add(DictConstant.WOR_STATUS_NEW);
			sqlArgs.add(DictConstant.WOR_STATUS_DEPLOY);
//        sqlArgs.add(workorderDTO.getExecuteUserName());
//        sqlArgs.add(workorderDTO.getExecuteUserName());
			sqlArgs.add(workorderDTO.getOrganizationId());
			sqlArgs.add(workorderDTO.getOrganizationId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
        return sqlModel;
    }
}
