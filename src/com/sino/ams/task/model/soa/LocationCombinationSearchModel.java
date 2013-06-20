package com.sino.ams.task.model.soa;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ģ�Ͳ����</p>
 * <p>����: �����ȡָ��OU��֯�µص���Ϸ����䶯��ģ�Ͷ���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class LocationCombinationSearchModel extends BaseSQLProducer {


    /**
     * <p>����˵����EAM�����ص�ͬ�� ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  BaseUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter DTO ���β���������
     */
    public LocationCombinationSearchModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * <p>����˵���������ȡָ��OU��֯�µص���Ϸ����䶯��ģ�Ͷ���</p>
     *
     * @param organizationId OU��֯ID
     * @return SQLModel ����ָ��OU��֯�µص���Ϸ����䶯��ģ�Ͷ���
     */
    public SQLModel getChangedLocationCombinationModel(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT TOP 500 EO.WORKORDER_OBJECT_NO\n" +
                "FROM   ETS_OBJECT             EO,\n" +
                "       ETS_FA_ASSETS_LOCATION TT\n" +
                "WHERE  EO.WORKORDER_OBJECT_NAME NOT LIKE '%.��Ч'\n" +
                "       AND EO.WORKORDER_OBJECT_CODE *= TT.ASSETS_LOCATION_CODE\n" +
                "       AND EO.ORGANIZATION_ID *= TT.ORG_ID\n" +
                "       AND (CONVERT(INT, EO.OBJECT_CATEGORY) < 70 OR\n" +
                "       CONVERT(INT, EO.OBJECT_CATEGORY) = 80)\n" +
                "       AND (NOT EXISTS\n" +
                "        (SELECT NULL\n" +
                "             FROM   ETS_FA_ASSETS_LOCATION T\n" +
                "             WHERE  T.ASSETS_LOCATION_CODE = EO.WORKORDER_OBJECT_CODE\n" +
                "                    AND T.ORG_ID = EO.ORGANIZATION_ID) OR EXISTS\n" +
                "        (SELECT NULL\n" +
                "             FROM   ETS_FA_ASSETS_LOCATION T\n" +
                "             WHERE  T.ASSETS_LOCATION_CODE = EO.WORKORDER_OBJECT_CODE\n" +
                "                    AND T.ORG_ID = EO.ORGANIZATION_ID\n" +
                "                    AND T.ASSETS_LOCATION <> EO.WORKORDER_OBJECT_NAME))\n" +
                "       AND NOT EXISTS\n" +
                " (SELECT NULL\n" +
                "        FROM   ETS_FA_NEW_LOC EFC\n" +
                "        WHERE  EFC.LOCATION = EO.WORKORDER_OBJECT_NAME\n" +
                "               AND EFC.ORGANIZATION_ID = EO.ORGANIZATION_ID\n" +
                "               AND EFC.STATUS = 1\n" +
                "               AND DATEDIFF(DD, EO.CREATION_DATE, GETDATE()) = 0)\n" +
                "       AND (EO.DISABLE_DATE IS NULL OR EO.DISABLE_DATE = '' OR\n" +
                "       EO.DISABLE_DATE >= GETDATE())\n" +
                "       AND EO.ORGANIZATION_ID = ?\n" +
                "ORDER  BY EO.CREATION_DATE DESC ";

        sqlArgs.add(organizationId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
