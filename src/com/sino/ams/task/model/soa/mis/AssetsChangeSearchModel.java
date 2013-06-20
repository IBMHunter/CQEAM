package com.sino.ams.task.model.soa.mis;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ģ�͹������</p>
 * <p>����: ��ѯEAMϵͳ����Ҫͬ����MISϵͳ���ʲ��������ݱ䶯</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsChangeSearchModel extends BaseSQLProducer {

    public AssetsChangeSearchModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * <p>����˵��������ָ��OU�´�ͬ���ʲ�������ģ�� �� </p>
     * <p>����˵�������±䶯��ģ�ͷ�Χ�ڣ� </p>
     * <li>���Ʒ����䶯���ʲ�
     * <li>�ͺŷ����䶯���ʲ�
     * <li>���̷����䶯���ʲ�
     *
     * @param organizationId OU��֯ID
     * @return SQLModel ���ر䶯�ʲ���ѯ��ģ�Ͷ���
     */
    public SQLModel getChangedAssetsModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT TOP 500 EII.SYSTEMID,\n" +
                "       EFA.ASSET_ID\n" +
                "FROM   ETS_SYSTEM_ITEM    ESI,\n" +
                "       ETS_FA_ASSETS      EFA,\n" +
                "       ETS_OBJECT         EO,\n" +
                "       AMS_OBJECT_ADDRESS AOA,\n" +
                "       ETS_ITEM_MATCH     EIM,\n" +
                "       ETS_ITEM_INFO      EII,\n" +
                "       AMS_MIS_EMPLOYEE   AME,\n" +
                "       AMS_MANUFACTURER   AM\n" +
                "WHERE  EII.SYSTEMID = EIM.SYSTEMID\n" +
                "       AND EIM.ASSET_ID = EFA.ASSET_ID\n" +
                "       AND EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                "       AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                "       AND EII.MANUFACTURER_ID = AM.MANUFACTURER_ID\n" +
                "       AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "       AND EFA.ASSIGNED_TO_NUMBER *= AME.EMPLOYEE_NUMBER\n" +
                "       AND (EFA.MODEL_NUMBER <> ESI.ITEM_SPEC OR\n" +
                "       EFA.MANUFACTURER_NAME <> AM.MANUFACTURER_NAME OR\n" +
                "       EFA.ASSETS_DESCRIPTION <> ESI.ITEM_NAME)\n" +
                "       AND AME.ENABLED = 'Y'\n" +
                "       AND EFA.COST > 0\n" +
                "       AND (EO.OBJECT_CATEGORY < '70' OR EO.OBJECT_CATEGORY = '80')\n" +
                "       AND EII.FINANCE_PROP = 'ASSETS'\n" +
                "       AND EII.ORGANIZATION_ID = ?\n" +
        "   AND NOT EXISTS\n" +
                "   (SELECT NULL\n" +
                "          FROM ETS_MISFA_UPDATE_LOG EMUL\n" +
                "         WHERE EMUL.ASSET_ID = EFA.ASSET_ID\n" +
                "           AND EMUL.UPDATE_TYPE = 'ASSETSINFO'\n" +
                "           AND (EMUL.TRANS_STATUS = 0 OR\n" +
                "               (EMUL.TRANS_STATUS = 1 AND\n" +
                "                EMUL.CREATION_DATE =\n" +
                "                GETDATE() )))\n";
        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(organizationId);

        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
