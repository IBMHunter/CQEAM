package com.sino.ams.task.model.soa.td;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.config.SinoConfig;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ģ�Ͳ����</p>
 * <p>����: �����ȡָ��OU��֯������ص㷢���䶯��ģ�Ͷ���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDLocationSegment2SearchModel extends BaseSQLProducer {


    /**
     * <p>����˵����EAM�����ص�ͬ�� ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  BaseUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter DTO ���β���������
     */
    public TDLocationSegment2SearchModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * <p>����˵���������ȡָ��OU��֯������ص㷢���䶯��ģ�Ͷ���</p>
     *
     * @param organizationId OU��֯ID
     * @return SQLModel ����ָ��OU��֯������ص㷢���䶯��ģ�Ͷ���
     */
    public SQLModel getChangedLocationSegment2Model(int organizationId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT TOTAL.LOC2_CODE,\n" +
                        "       TOTAL.LOC2_DESC,\n" +
                        "       TOTAL.OLD_LOC_DESC,\n" +
                        "       TOTAL.COMPANY_CODE,\n" +
                        "       TOTAL.ORGANIZATION_ID,\n" +
                        "       TOTAL.COMPANY_NAME,\n" +
                        "       TOTAL.WORKORDER_CATEGORY,\n" +
                        "       TOTAL.AREA_TYPE_NAEM,\n" +
                        "       TOTAL.CITY,\n" +
                        "       TOTAL.COUNTY,\n" +
                        "       TOTAL.LOC_TYPE\n" +
                        "  FROM (SELECT EOL.LOC2_CODE,\n" +
                        "               EOL.LOC2_DESC,\n" +
                        "               '' OLD_LOC_DESC,\n" +
                        "               EOL.COMPANY_CODE,\n" +
                        " EOCM.MATCH_ORGANIZATION_ID ORGANIZATION_ID,\n" +
                        "               EOCM.COMPANY COMPANY_NAME,\n" +
                        "               dbo.GET_FLEX_VALUE(EOL.OBJECT_CATEGORY, 'OBJECT_CATEGORY') WORKORDER_CATEGORY,\n" +
                        "               dbo.GET_FLEX_VALUE(EOL.AREA_TYPE, 'ADDR_AREA_TYPE') AREA_TYPE_NAEM,\n" +
                        "               EOL.CITY,\n" +
                        "               EOL.COUNTY,\n" +
                        "               '����' LOC_TYPE\n" +
                        "          FROM ETS_OBJECT_LOC2 EOL, ETS_OU_CITY_MAP EOCM\n" +
                        "         WHERE EOL.COMPANY_CODE = EOCM.COMPANY_CODE\n" +
                        "	AND (EOL.SHARE_TYPE='3' OR EOL.SHARE_TYPE='2')\n" +
                        "           AND NOT EXISTS\n" +
                "         (SELECT 1\n" +
                "                  FROM M_FND_FLEX_VALUE_SETS MFFVS, M_FND_FLEX_VALUES MFFV\n" +
                "                 WHERE MFFVS.FLEX_VALUE_SET_ID = MFFV.FLEX_VALUE_SET_ID\n" +
                "                   AND MFFVS.FLEX_VALUE_SET_NAME = ?\n" +
                "                   AND EOL.LOC2_CODE = MFFV.FLEX_VALUE \n" +
                        "           AND MFFVS.SOURCE='TDMIS')\n" +
                "           AND EOCM.MATCH_ORGANIZATION_ID = ?\n";
        sqlStr +=
                "        UNION ALL\n" +
                        "        SELECT EOL.LOC2_CODE,\n" +
                        "               EOL.LOC2_DESC,\n" +
                        "               MFFV.DESCRIPTION OLD_LOC_DESC,\n" +
                        "               EOL.COMPANY_CODE,\n" +
                        " EOCM.MATCH_ORGANIZATION_ID ORGANIZATION_ID,\n" +
                "               EOCM.COMPANY COMPANY_NAME,\n" +
                        "               dbo.GET_FLEX_VALUE(EOL.OBJECT_CATEGORY, 'OBJECT_CATEGORY') WORKORDER_CATEGORY,\n" +
                        "               dbo.GET_FLEX_VALUE(EOL.AREA_TYPE, 'ADDR_AREA_TYPE') AREA_TYPE_NAEM,\n" +
                        "               EOL.CITY,\n" +
                        "               EOL.COUNTY,\n" +
                        "               '�޸�' LOC_TYPE\n" +
                        "          FROM ETS_OBJECT_LOC2       EOL,\n" +
                        "               M_FND_FLEX_VALUE_SETS MFFVS,\n" +
                        "               M_FND_FLEX_VALUES     MFFV,\n" +
                        "               ETS_OU_CITY_MAP       EOCM\n" +
                        "         WHERE MFFVS.FLEX_VALUE_SET_ID = MFFV.FLEX_VALUE_SET_ID\n" +
                        "           AND MFFVS.FLEX_VALUE_SET_NAME = ?\n" +
                        "           AND EOL.LOC2_CODE = MFFV.FLEX_VALUE\n" +
                        "         AND MFFVS.SOURCE='TDMIS'\n" +
                "	     AND EOCM.MATCH_ORGANIZATION_ID = ?" +
                "	     AND (EOL.SHARE_TYPE='3' OR EOL.SHARE_TYPE='2')\n" +
                        "     AND EOL.LOC2_DESC <> MFFV.DESCRIPTION\n" +
                "           AND EOL.COMPANY_CODE = EOCM.COMPANY_CODE\n" +
                "           ) TOTAL\n" +
                " WHERE NOT EXISTS (SELECT NULL\n" +
                "          FROM ETS_FA_NEW_LOC EFC\n" +
                "         WHERE EFC.LOCATION = TOTAL.LOC2_DESC\n" +
                "           AND EFC.ORGANIZATION_ID = TOTAL.ORGANIZATION_ID\n" +
                "           AND EFC.STATUS = 1\n" +
                "           AND CONVERT(INT, CONVERT(CHAR, EFC.CREATION_DATE, 112)) =\n" +
                "               CONVERT(INT, CONVERT(CHAR, GETDATE(), 112)))";
        sqlArgs.add(SinoConfig.getFlexValueSetNameTD());
        sqlArgs.add(organizationId);
        sqlArgs.add(SinoConfig.getFlexValueSetNameTD());
        sqlArgs.add(organizationId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
