package com.sino.ams.synchronize.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: wangzp
 * Date: 2011-12-21 
 * Time: 12:49:32
 * To ������״̬��ѯ
 */
public class TransStatusQueryModel extends AMSSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�eAM�����ص�ͬ�� ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     */
    public TransStatusQueryModel(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���� ������ѯSYSTEMID��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {

        SQLModel sqlModel = null;
        try {
            EamSyschronizeDTO dto = (EamSyschronizeDTO) dtoParameter;
            sqlModel = new SQLModel();
            List sqlArgs = new ArrayList();
            String sqlStr;
            sqlStr = "SELECT \n" +
                    "  EMUB.BATCH_ID,\n" +
                    "  EMUB.REMARK,\n" +
                    "  EMUB.START_DATE,\n" +
                    "  EMUB.END_DATE,\n" +
                    "  EMUB.ORGANIZATION_ID,\n" +
                    "  EMUB.ERRMSG, \n" +
                    " CASE WHEN EMUB.TRANS_STATUS=0 THEN  '�ȴ�ִ��' " +
                    "      WHEN EMUB.TRANS_STATUS=1 THEN  '��������' " +
                    "      WHEN EMUB.TRANS_STATUS=2 THEN  '�������' " +
                    "      END TRANS_STATUS "+
                    " FROM ETS_MISFA_UPDATE_BATCH EMUB\n" +
                    " WHERE EMUB.ORGANIZATION_ID = ?\n";
            if (dto.getTransStatus().equals("3")) {   //����
                sqlStr = sqlStr + " AND EMUB.TRANS_STATUS = 2 AND  " + SyBaseSQLUtil.isNotNull("EMUB.ERRMSG") + " ";
            } else if (dto.getTransStatus().equals("4")) {    //ȫ��
                sqlStr = sqlStr + " AND 1 = 1 ";
            } else if (dto.getTransStatus().equals("2")) { //�ɹ�
                sqlStr = sqlStr + " AND EMUB.TRANS_STATUS = 2 AND EMUB.ERRMSG  " + SyBaseSQLUtil.isNullNoParam() + " ";
            } else {
                sqlStr = sqlStr + " AND EMUB.TRANS_STATUS =" + dto.getTransStatus();
            }
            //" AND EMUB.TRANS_STATUS =?\n" +
            sqlStr = sqlStr +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR EMUB.CREATION_DATE >= ?)\n" +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR EMUB.CREATION_DATE <= ?)" +
                    " ORDER BY EMUB.CREATION_DATE DESC";
            sqlArgs.add(sfUser.getOrganizationId());
            //sqlArgs.add(dto.getTransStatus());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException e) {
            e.printLog();
        }

        return sqlModel;
    }

    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EamSyschronizeDTO dto = (EamSyschronizeDTO) dtoParameter;
        String sqlStr = "\n" +
                "SELECT \n" +
                "    EMUL.BATCH_ID,\n" +
                "    EMUL.TAG_NUMBER_FROM,\n" +
                "    EMUL.TAG_NUMBER_TO,\n" +
                "    EMUL.ASSET_ID,\n" +
                "    EMUL.NAME_TO,\n" +
                "    EMUL.MODEL_FROM,\n" +
                "    EMUL.MODEL_TO,\n" +
                "    EMUL.LOCATION_FROM,\n" +
                "    EMUL.LOCATION_TO,\n" +
                "    AME.USER_NAME OLD_USER_NAME,\n" +
                "    AME2.USER_NAME NEW_USER_NAME,\n" +
                "    EMUL.TRANS_STATUS,\n" +
                "    EMUL.TRANS_ERRORMSG,\n" +
                "    EMUL.REMARK\n" +
                " FROM \n" +
                "    ETS_MISFA_UPDATE_LOG EMUL,\n" +
                "    AMS_MIS_EMPLOYEE     AME,\n" +
                "    AMS_MIS_EMPLOYEE     AME2    \n" +
                " WHERE EMUL.OWNER_FROM *= AME.EMPLOYEE_ID\n" +
                "  AND EMUL.OWNER_TO *= AME2.EMPLOYEE_ID\n" +
                "  AND EMUL.ORGANIZATION_ID=?\n" +
                "  AND EMUL.BATCH_ID=?";

        sqlArgs.add(dto.getLogId());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(dto.getBatchId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     *
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        EamSyschronizeDTO dto = (EamSyschronizeDTO) dtoParameter;
        // if (foreignKey.equals("batchId")) {
        sqlModel = getDataByBatchIdModel(dto.getBatchId());
        //   }
        return sqlModel;
    }

    private SQLModel getDataByBatchIdModel(String batchId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "\n" +
                "SELECT EMUL.BATCH_ID BATCH_ID,\n" +
                "       EMUL.BARCODE BARCODE,\n" +
                "       EMUL.TAG_NUMBER_FROM,\n" +
                "       EMUL.TAG_NUMBER_TO,\n" +
                "       EMUL.ASSET_ID ASSET_ID,\n" +
                "       EMUL.NAME_FROM NAME_FROM,\n" +
                "       EMUL.NAME_TO NAME_TO,\n" +
                "       EMUL.MODEL_FROM MODEL_FROM,\n" +
                "       EMUL.MODEL_TO MODEL_TO,\n" +
                "       EMUL.LOCATION_TO LOCATION_TO,\n" +
                "       EMUL.LOCATION_FROM LOCATION_FROM,\n" +
                "       AME.USER_NAME OLD_USER_NAME,\n" +
                "       AME2.USER_NAME NEW_USER_NAME,\n" +
                " CASE WHEN EMUL.TRANS_STATUS=0 THEN 'δ����' WHEN EMUL.TRANS_STATUS=1 THEN '���' WHEN EMUL.TRANS_STATUS=1 THEN '����' END TRANS_STATUS,"+
                "       EMUL.TRANS_ERRORMSG,\n" +
                "       EMUL.REMARK,\n" +
                "       EMUL.LOGID," +
                "       EMUL.TRANS_ERRORMSG MSG\n" +
                "  FROM ETS_MISFA_UPDATE_LOG EMUL,\n" +
                "       AMS_MIS_EMPLOYEE     AME,\n" +
                "       AMS_MIS_EMPLOYEE     AME2\n" +
                " WHERE EMUL.OWNER_FROM *= AME.EMPLOYEE_ID\n" +
                "   AND EMUL.OWNER_TO *= AME2.EMPLOYEE_ID\n" +
                "   AND EMUL.BATCH_ID = ?\n" +
                "UNION ALL\n" +
                "SELECT EFNL.BATCH_ID BATCH_ID,\n" +
                "       NULL BARCODE,\n" +
                "       NULL TAG_NUMBER_FROM,\n" +
                "       NULL TAG_NUMBER_TO,\n" +
                "       NULL ASSET_ID,\n" +
                "       NULL NAME_FROM,\n" +
                "       NULL NAME_TO,\n" +
                "       NULL MODEL_FROM,\n" +
                "       NULL MODEL_TO,\n" +
                "       EFNL.LOCATION LOCATION_TO,\n" +
                "       NULL LOCATION_FROM,\n" +
                "       NULL OLD_USER_NAME,\n" +
                "       NULL NEW_USER_NAME,\n" +
                " CASE WHEN EFNL.STATUS=0 THEN 'δ����' WHEN EFNL.STATUS=1 THEN '���' WHEN EFNL.STATUS=1 THEN '����' END TRANS_STATUS,"+
                "       EFNL.MSG TRANS_ERRORMSG,\n" +
                "       EFNL.MSG REMARK,\n" +
                "       NULL LOGID," +
                "       EFNL.MSG \n" +
                "  FROM ETS_FA_NEW_LOC EFNL\n" +
                " WHERE EFNL.BATCH_ID = ?";
        //sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(batchId);
        // sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(batchId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel getLogInf(String logId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT dob.APP_GET_FLEX_VALUE(EMTE.TRANSACTION_TYPE, 'ERP_TRANS_TYPE') TRANSACTION_TYPE,\n" +
                "       EMTE.ASSET_ID,\n" +
                "       EMTE.ERROR_MSG,\n" +
                " CASE WHEN EMTE.TRANS_STATUS='0' THEN '����' WHEN EMTE.TRANS_STATUS='1' THEN '����'  END STATUS,"+
                "       EMTE.REF_ID,\n" +
                "       EMTE.CREATION_DATE\n" +
                "  FROM ETS_MISFA_TRANSACTION_ERRORS EMTE\n" +
                " WHERE EMTE.LOG_ID = ?";
        sqlArgs.add(logId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
