package com.sino.rds.share.util;

import com.sino.base.db.sql.model.SQLModel;

import java.util.ArrayList;
import java.util.List;

public abstract class RDSOptionSQLProducer {

    public static SQLModel getAllDictionaryModel(String dictionaryCode) {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RFV.CODE,\n" +
                "       RFV.VALUE\n" +
                "FROM   RDS_FLEX_VALUES    RFV,\n" +
                "       RDS_FLEX_VALUE_SET RFVS\n" +
                "WHERE  RFV.VALUE_SET_ID = RFVS.VALUE_SET_ID\n" +
                "       AND RFVS.VALUE_SET_CODE = ?\n" +
                "       AND RFV.ENABLED = 'Y'\n" +
                "ORDER  BY RFV.CODE";
        List<String> sqlArgs = new ArrayList<String>();

        sqlArgs.add(dictionaryCode);
        sqlModel.setSqlStr(slqStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public static SQLModel getEnabledDictionaryModel(String dictionaryCode) {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RFV.CODE,\n" +
                "       RFV.VALUE\n" +
                "FROM   RDS_FLEX_VALUE_SET RFVS,\n" +
                "       RDS_FLEX_VALUES    RFV\n" +
                "WHERE  RFVS.VALUE_SET_ID = RFV.VALUE_SET_ID\n" +
                "       AND RFVS.ENABLED = 'Y'\n" +
                "       AND RFV.ENABLED = 'Y'\n" +
                "       AND RFVS.VALUE_SET_CODE = ?";
        List<String> sqlArgs = new ArrayList<String>();

        sqlArgs.add(dictionaryCode);
        sqlModel.setSqlStr(slqStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ȡ��ǰOracleʵ���µ������û���SQL
     *
     * @return ��ȡ��ǰOracleʵ���µ������û���SQL
     */
    public static SQLModel getEnabledOracleUsersModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT DU.USERNAME,\n" +
                "       DU.USERNAME\n" +
                "FROM   DBA_USERS DU\n" +
                "WHERE  DU.ACCOUNT_STATUS = 'OPEN'\n" +
                "ORDER  BY DU.USER_ID";
        List<String> sqlArgs = new ArrayList<String>();

        sqlModel.setSqlStr(slqStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�����Ѿ����������ģ��
     *
     * @return ��ȡ��ǰOracleʵ���µ������û���SQL
     */
    public static SQLModel getAllDataModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RDM.MODEL_ID,\n" +
                "       RDM.MODEL_NAME\n" +
                "FROM   RDS_DATA_MODEL RDM\n" +
                "ORDER BY RDM.MODEL_ID";

        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }


    /**
     * ���ܣ���ȡ�����Ѿ����������ģ��
     *
     * @return ��ȡ��ǰOracleʵ���µ������û���SQL
     */
    public static SQLModel getAllReportModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RRD.REPORT_ID,\n" +
                "       RRD.REPORT_NAME\n" +
                "FROM   RDS_REPORT_DEFINE RRD\n" +
                "ORDER  BY RRD.REPORT_ID";
        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�����Ѿ�����Ľ��汨��
     *
     * @return ��ȡ�����Ѿ�����Ľ��汨��SQL
     */
    public static SQLModel getIntersectReportModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RRD.REPORT_ID,\n" +
                "       RRD.REPORT_NAME,\n" +
                "       RRD.REPORT_TYPE\n" +
                "FROM   RDS_REPORT_DEFINE RRD\n" +
                "WHERE  RRD.REPORT_TYPE IN ('2', '3', '4')\n" +
                "ORDER BY RRD.REPORT_ID ";
        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�����Ѿ������ֵ�б�
     *
     * @return ��ȡ�����Ѿ������ֵ�б�
     */
    public static SQLModel getLovModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RLD.LOV_ID,\n" +
                "       RLD.LOV_NAME\n" +
                "FROM   RDS_LOV_DEFINE RLD\n" +
                "WHERE  RLD.ENABLED = 'Y'\n" +
                "ORDER  BY RLD.LOV_ID";
        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�����Ѿ������ֵ�б�
     *
     * @return ��ȡ�����Ѿ������ֵ�б�
     */
    public static SQLModel getAllConnectionModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RDC.CONNECTION_ID,\n" +
                "       RDC.CONNECTION_NAME\n" +
                "FROM   RDS_DB_CONNECTION RDC\n" +
                "ORDER  BY RDC.CONNECTION_ID";
        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }


    /**
     * ���ܣ���ȡ�����Ѿ������LookUp�б�
     *
     * @return ��ȡ�����Ѿ������LookUp�б�
     */
    public static SQLModel getLookUpModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RLD.LOOK_UP_ID,\n" +
                "       RLD.LOOK_UP_NAME\n" +
                "FROM   RDS_LOOKUP_DEFINE RLD\n" +
                "WHERE  RLD.ENABLED = 'Y'\n" +
                "ORDER  BY RLD.LOOK_UP_ID";
        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�����Ѿ��������Ч����̶�����
     *
     * @return ��ȡ�����Ѿ��������Ч����̶�����
     */
    public static SQLModel getFixedCategoryReportModel() {
        SQLModel sqlModel = new SQLModel();
        String slqStr = "SELECT RRD.REPORT_ID,\n" +
                "       RRD.REPORT_NAME,\n" +
                "       RRD.REPORT_TYPE\n" +
                "FROM   RDS_REPORT_DEFINE RRD\n" +
                "WHERE  RRD.REPORT_TYPE IN ('3', '4')\n" +
                "       AND RRD.ENABLED = 'Y'";
        sqlModel.setSqlStr(slqStr);
        return sqlModel;
    }
}
