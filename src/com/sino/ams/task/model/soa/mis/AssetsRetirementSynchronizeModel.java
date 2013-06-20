package com.sino.ams.task.model.soa.mis;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ģ�͹������(�ʲ�����ͬ��ģ��)</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ���ʲ���������</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsRetirementSynchronizeModel extends BaseSQLProducer {

    public AssetsRetirementSynchronizeModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    public void setUserAccount(BaseUserDTO userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * <p>����˵���������ͬ���ı����ʲ���ģ�Ͷ���</p>
     *
     * @param organizationId OU��֯ID
     * @return SQLModel ���ر����ʲ���ѯ��ģ�Ͷ���
     */
    public SQLModel getRetiredAssetsModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EII.SYSTEMID,\n" +
                        "       EOCM.ORGANIZATION_ID,\n" +
                        "       EOCM.COMPANY,\n" +
                        "       EOCM.BOOK_TYPE_CODE,\n" +
                        "       EII.BARCODE,\n" +
                        "       ESI.ITEM_NAME,\n" +
                        "       GETDATE() DATE_RRETIRED,\n" +
                        "       EII.ITEM_STATUS,\n" +
                        "       AATL.RETIREMENT_COST,\n" +
                        "       AATH.CREATION_DATE RRETIRED_DATE,\n" +
                        "       EFA.ASSET_ID,\n" +
                        "       EFA.TAG_NUMBER,\n" +
                        "       EFA.COST,\n" +
                        "       AATL.REJECT_TYPE \n" +
                        "  FROM AMS_ASSETS_TRANS_HEADER AATH,\n" +
                        "       AMS_ASSETS_TRANS_LINE   AATL,\n" +
                        "       ETS_ITEM_INFO           EII,\n" +
                        "       ETS_SYSTEM_ITEM         ESI,\n" +
                        "       ETS_OU_CITY_MAP         EOCM,\n" +
                        "       ETS_FA_ASSETS           EFA,\n" +
                        "       ETS_ITEM_MATCH          EIM\n" +
                        " WHERE AATH.TRANS_ID = AATL.TRANS_ID\n" +
                        "   AND AATH.TRANS_TYPE = 'ASS-DIS'\n" +
                        "   AND AATH.TRANS_STATUS = 'COMPLETED'\n" +
                        "   AND EIM.SYSTEMID = EII.SYSTEMID\n" +
                        "   AND EIM.ASSET_ID = EFA.ASSET_ID\n" +
                        "   AND EII.BARCODE = AATL.BARCODE\n" +
                        "   AND EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                        "   AND EII.ORGANIZATION_ID = EOCM.ORGANIZATION_ID\n" +
                        "   AND EII.ITEM_STATUS = 'TO_DISCARD'\n" +
                        "   AND NOT EXISTS (SELECT NULL\n" +
                        "          FROM ETS_MISFA_UPDATE_LOG EMUL\n" +
                        "         WHERE EMUL.BARCODE = EII.BARCODE\n" +
                        "           AND EMUL.UPDATE_TYPE = 'SRV_RETIRE'\n" +
                        "           AND (EMUL.TRANS_STATUS = 0 OR\n" +
                        "               (EMUL.TRANS_STATUS = 1 AND\n" +
                        "               EMUL.CREATION_DATE =\n" +
                        "               GETDATE())))\n" +
                        "   AND EII.ORGANIZATION_ID = ?\n";

        sqlArgs.add(organizationId);
        sqlModel.setSqlStr(sqlStr);

        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * д����ͬ����־
     *
     * @param systemIds �ʲ�IDS
     * @param batchId   ͬ����
     * @return SQLModel
     */
    public SQLModel getLogAssetsModel(String systemIds, String batchId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "INSERT INTO ETS_MISFA_UPDATE_LOG\n" +
                        "  (LOGID,\n" +
                        "   BATCH_ID,\n" +
                        "   BARCODE,\n" +
                        "   ASSET_ID,\n" +   //
                        "   ORGANIZATION_ID,\n" +  //
                        "   TRANS_STATUS,\n" +   //
                        "   UPDATE_TYPE)\n" +
                        "  (SELECT NEWID(),\n" +
                        "          '" + batchId + "',\n" +
                        "          EII.BARCODE,\n" +
                        "         CONVERT(int,EFA.ASSET_ID),\n" +
                        "         CONVERT(int,EII.ORGANIZATION_ID),\n" +
                        "          0,\n" +
                        "          'SRV_RETIRE'\n" +
                        "     FROM AMS_ASSETS_TRANS_HEADER AATH,\n" +
                        "          AMS_ASSETS_TRANS_LINE   AATL,\n" +
                        "          ETS_ITEM_INFO           EII,\n" +
                        "          ETS_FA_ASSETS           EFA,\n" +
                        "          ETS_ITEM_MATCH          EIM\n" +
                        "    WHERE AATH.TRANS_ID = AATL.TRANS_ID\n" +
                        "      AND AATH.TRANS_TYPE = 'ASS-DIS'\n" +
                        "      AND AATH.TRANS_STATUS = 'COMPLETED'\n" +
                        "      AND EIM.SYSTEMID = EII.SYSTEMID\n" +
                        "      AND EIM.ASSET_ID = EFA.ASSET_ID\n" +
                        "      AND EII.BARCODE = AATL.BARCODE\n" +
                        "      AND EII.ITEM_STATUS = 'TO_DISCARD'\n" +
                        "      AND EII.SYSTEMID IN (" + systemIds + "))";


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel getUpdateLogCompleteModel(String batchId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE ETS_MISFA_UPDATE_LOG \n" +
                "   SET TRANS_STATUS = ?\n" +
                " WHERE BATCH_ID = ?";

        sqlArgs.add(1);
        sqlArgs.add(batchId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���±�����־
     *
     * @param batchId     ������
     * @param barcode     ����
     * @param transStatus ͬ��״̬
     * @param errorMsg    ͬ����Ϣ
     * @return SQLModel
     */
    public SQLModel getUpdateLogModel(String batchId, String barcode, int transStatus, String errorMsg) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE ETS_MISFA_UPDATE_LOG \n" +
                "   SET TRANS_STATUS = ?, TRANS_ERRORMSG = ?\n" +
                " WHERE BATCH_ID = ?\n" +
                "   AND BARCODE = ?";

        sqlArgs.add(transStatus);
        sqlArgs.add(errorMsg);
        sqlArgs.add(batchId);
        sqlArgs.add(barcode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

}
