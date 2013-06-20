package com.sino.ams.task.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.config.SinoConfig;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ģ�Ͳ����</p>
 * <p>����: ������������Ҫ�õ��Ĺ���ģ�͹�����</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class TaskModelProducer {

    /**
     * <p>����˵���������ȡ�����ʲ��˲���ģ�Ͷ��� </p>
     *
     * @param isTd �Ƿ�TD,�Ǵ�Y�����ߴ�N
     * @return ��ȡ�����ʲ��˲���ģ�Ͷ���
     */
    public static SQLModel getBookTypeCodeModel(String isTd) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT ACB.BOOK_TYPE_CODE\n" +
                "  FROM AMS_COMPANY_BOOK ACB\n" +
                " WHERE (ACB.BOOK_TYPE_CODE LIKE '%FA%' OR ACB.BOOK_TYPE_CODE LIKE '%IA%')\n" +
                "   AND EXISTS (SELECT NULL\n" +
                "          FROM ETS_OU_CITY_MAP EOCM\n" +
                "         WHERE ACB.COMPANY_CODE = EOCM.COMPANY_CODE\n" +
                "           AND EOCM.IS_TD = ?)";

        List<String> sqlArgs = new ArrayList<String>();
        sqlArgs.add(isTd);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * <p>����˵���������ȡ����OU��֯��ģ�Ͷ��� </p>
     *
     * @param isTd �Ƿ�TD,�Ǵ�Y�����ߴ�N
     * @return ��ȡ�����ʲ��˲���ģ�Ͷ���
     */
    public static SQLModel getCompanyListModel(String isTd) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT EOCM.ORGANIZATION_ID,\n" +
                "       EOCM.COMPANY,\n" +
                "       EOCM.COMPANY_CODE,\n" +
                "       EOCM.BOOK_TYPE_CODE,\n" +
                "       EOCM.BOOK_TYPE_NAME\n" +
                "FROM   ETS_OU_CITY_MAP EOCM\n" +
                "WHERE  EOCM.IS_TD = ?\n" +
                "ORDER  BY EOCM.ORGANIZATION_ID";

        List<String> sqlArgs = new ArrayList<String>();
        sqlArgs.add(isTd);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * <p>����˵���������ȡֵ�����Ƶ�ģ�Ͷ���  </p>
     *
     * @param source ֵ��������Դ����ѡֵΪMIS����TDMIS
     * @return ��ȡ�����ʲ��˲���ģ�Ͷ���
     */
    public static SQLModel getFlexValueSetModel(String source) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT MFFVS.FLEX_VALUE_SET_NAME,\n" +
                "       MFFVS.FLEX_VALUE_SET_NAME\n" +
                "FROM   M_FND_FLEX_VALUE_SETS MFFVS\n" +
                "       WHERE MFFVS.SOURCE = ?";

        List<String> sqlArgs = new ArrayList<String>();
        sqlArgs.add(source);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }


    /**
     * <p>����˵���������ȡ��ʱ����ִ���˵�ģ�Ͷ���  </p>
     *
     * @return ��ȡ��ʱ����ִ���˵�ģ�Ͷ���
     * @throws UnsupportedEncodingException ����SinoConfig��ȡϵͳ����Ա��ɫ����ʱ�׳����쳣
     */
    public static SQLModel getTaskExecutorModel() throws UnsupportedEncodingException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT TOP 1 SU.*\n" +
                "FROM   SF_USER SU\n" +
                "WHERE  EXISTS (SELECT NULL\n" +
                "        FROM   SF_USER_AUTHORITY SUA\n" +
                "        WHERE  SU.USER_ID = SUA.USER_ID\n" +
                "               AND SUA.ROLE_NAME = ?)";
        List<String> sqlArgs = new ArrayList<String>();
        sqlArgs.add(SinoConfig.getSystemAdminRole());

        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);

        return sqlModel;
    }

    /**
     * <p>����˵���������ȡ��ʱ����ִ���˵�ģ�Ͷ��� </p>
     *
     * @param organizationId OU��֯ID
     * @return ��ȡ��ʱ����ִ���˵�ģ�Ͷ���
     */
    public static SQLModel getOUTaskExecutorModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT TOP 1 SU.USER_ID,\n" +
                "       SU.USERNAME,\n" +
                "       SU.LOGIN_NAME,\n" +
                "       SU.ENABLED,\n" +
                "       SU.ORGANIZATION_ID,\n" +
                "       SU.EMPLOYEE_NUMBER\n" +
                "FROM   SF_USER                    SU,\n" +
                "       ETS_MISFA_TRANSACTION_RESP EMTR\n" +
                "WHERE  SU.EMPLOYEE_NUMBER = EMTR.EMPLOYEE_NUMBER\n" +
                "       AND SU.ORGANIZATION_ID = ?\n";

        List<Integer> sqlArgs = new ArrayList<Integer>();
        sqlArgs.add(organizationId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * <p>����˵���������ȡָ���ʲ��˲����µĹرյĻ���ڼ��ģ�Ͷ��� </p>
     *
     * @param bookTypeCode �ʲ��˲�����
     * @return ��ȡ��ȡָ���ʲ��˲����µĹرյĻ���ڼ��ģ�Ͷ���
     */
    public static SQLModel getPeriodNameModel(String bookTypeCode) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT MAX(SAPS.MIS_PERIOD_NAME) MIS_PERIOD_NAME\n" +
                "FROM   SRV_ASSET_PERIOD_STATUS SAPS\n" +
                "WHERE  SAPS.PERIOD_STATUS = 'CLOSE'\n" +
                "       AND SAPS.BOOK_TYPE_CODE = ?";
        List<String> sqlArgs = new ArrayList<String>();
        sqlArgs.add(bookTypeCode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * <p>����˵���������ȡ���¹رյĻ���ڼ��ģ�Ͷ��� </p>
     *
     * @return �����ȡ���¹رյĻ���ڼ��ģ�Ͷ���
     */
    public static SQLModel getMaxPeriodNameModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT MAX(MIS_PERIOD_NAME) MIS_PERIOD_NAME FROM SRV_ASSET_PERIOD_STATUS " +
                "WHERE PERIOD_NAME IN (SELECT MAX(PERIOD_NAME) FROM SRV_ASSET_PERIOD_STATUS WHERE PERIOD_STATUS='CLOSE')";

        sqlModel.setSqlStr(sqlStr);

        return sqlModel;
    }
}
