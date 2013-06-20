package com.sino.ams.newasset.report.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Function:    ����ͳ�Ʊ���
 * Author:      ����
 * Date:        2009-10-28
 */
public class WorkorderStatisticsModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;


    /**
     * ���ܣ�EQUIP_STAT ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EquipStatDTO ���β���������
     */
    public WorkorderStatisticsModel(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }


    /**
     * ���ܣ�����Զ�����EQUIP_STATҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
//10	�½�����
//11	���ݹ���
//12	Ѳ�칤��
//13	ά�޹���
//14	��Ǩ����
//15	���ݹ���
//16	�滻����
        String sqlStr = "";
        try {
            sqlStr = "SELECT EOCM.COMPANY ORGANIZATION_NAME, \n" +
                    "        NEW_OBJECT.TOTAL_COUNT NEW_OBJECT_COUNT, --�����ص㹤��\n" +
                    "        DECODE(EWT.T1, NULL, '0', EWT.T1) CONNECT_COUNT , --���ӹ���\n" +
                    "        DECODE(EWT.T2, NULL, '0', EWT.T2) PATROL_COUNT,  --Ѳ�칤��\n" +
                    "        DECODE(EWT.T3, NULL, '0', EWT.T3) MAINTENANCE_COUNT, --ά�޹���\n" +
                    "        DECODE(EWT.T4, NULL, '0', EWT.T4) MOVE_COUNT,--��Ǩ����\n" +
//                    "        DECODE(EWT.T5, NULL, '0', EWT.T5) TRANS_COUNT, --��������\n" +
                    "        DECODE(TRANS.TOTAL_COUNT, NULL, '0', TRANS.TOTAL_COUNT) TRANS_COUNT,        --��������\n" +
                    "        DECODE(DISCARDED.TOTAL_COUNT, NULL, '0', DISCARDED.TOTAL_COUNT) DISCARDED_COUNT,        --���Ϲ���\n" +
                    "        (DECODE(NEW_OBJECT.TOTAL_COUNT, NULL, 0, NEW_OBJECT.TOTAL_COUNT) + \n" +
                    "         DECODE(EWT.SUM, NULL, 0, EWT.SUM) + \n" +
                    "         DECODE(DISCARDED.TOTAL_COUNT, NULL, 0, DISCARDED.TOTAL_COUNT) + DECODE(TRANS.TOTAL_COUNT, NULL, 0, TRANS.TOTAL_COUNT)) SUM_COUNT               --�ϼ�\n" +
                    "   FROM (SELECT SUM(DECODE(EW.WORKORDER_TYPE, 18, 1, 0)) T1,\n" +
                    "                SUM(DECODE(EW.WORKORDER_TYPE, 12, 1, 0)) T2,\n" +
                    "                SUM(DECODE(EW.WORKORDER_TYPE, 13, 1, 0)) T3,\n" +
                    "                SUM(DECODE(EW.WORKORDER_TYPE, 14, 1, 0)) T4,\n" +
//                    "                SUM(DECODE(EW.WORKORDER_TYPE, 17, 1, 0)) T5,       \n" +
                    "                COUNT(EW.WORKORDER_TYPE) SUM,\n" +
                    "                EW.ORGANIZATION_ID\n" +
                    "           FROM ETS_WORKORDER EW\n" +
                    "          WHERE EW.UPLOAD_DATE >= ISNULL(?, EW.UPLOAD_DATE)\n" +
                    "            AND EW.UPLOAD_DATE <= ISNULL(?, EW.UPLOAD_DATE)\n" +
//                     "            AND EW.WORKORDER_TYPE IN (18, 12, 13, 14, 17)\n" +
                    "            AND EW.WORKORDER_TYPE IN (18, 12, 13, 14)\n" +
                    "          GROUP BY EW.ORGANIZATION_ID) EWT,\n" +
                    "        (SELECT EO.ORGANIZATION_ID, COUNT(1) TOTAL_COUNT\n" +
                    "                   FROM ETS_OBJECT EO\n" +
                    "                  WHERE EO.CREATION_DATE >= ISNULL(?, EO.CREATION_DATE) \n" +
                    "                    AND EO.CREATION_DATE <= ISNULL(?, EO.CREATION_DATE)\n" +
                    "               GROUP BY EO.ORGANIZATION_ID) NEW_OBJECT, \n" +
                    "        (SELECT AATH.FROM_ORGANIZATION_ID, COUNT(1) TOTAL_COUNT FROM AMS_ASSETS_TRANS_HEADER AATH \n" +
                    "                                WHERE AATH.TRANS_TYPE = 'ASS-RED' \n" +
                    "                                  AND AATH.TRANS_STATUS = 'CONFIRMD'\n" +
                    "                                  AND AATH.CREATION_DATE >= ISNULL(?, AATH.CREATION_DATE) \n" +
                    "                    AND AATH.CREATION_DATE <= ISNULL(?, AATH.CREATION_DATE)\n" +
                    "               GROUP BY AATH.FROM_ORGANIZATION_ID) TRANS, \n" +
                    "        (SELECT AATH.FROM_ORGANIZATION_ID, COUNT(1) TOTAL_COUNT FROM AMS_ASSETS_TRANS_HEADER AATH \n" +
                    "                                WHERE AATH.TRANS_TYPE = 'ASS-DIS' \n" +
                    "                                  AND AATH.TRANS_STATUS = 'COMPLETED'\n" +
                    "                                  AND AATH.CREATION_DATE >= ISNULL(?, AATH.CREATION_DATE) \n" +
                    "                    AND AATH.CREATION_DATE <= ISNULL(?, AATH.CREATION_DATE)\n" +
                    "               GROUP BY AATH.FROM_ORGANIZATION_ID) DISCARDED, \n" +
                    "        ETS_OU_CITY_MAP EOCM\n" +
                    "  WHERE EOCM.ORGANIZATION_ID *= EWT.ORGANIZATION_ID\n" +
                    "    AND EOCM.ORGANIZATION_ID *= NEW_OBJECT.ORGANIZATION_ID\n" +
                    "    AND EOCM.ORGANIZATION_ID *= DISCARDED.FROM_ORGANIZATION_ID\n" +
                    "    AND EOCM.ORGANIZATION_ID *= TRANS.FROM_ORGANIZATION_ID\n" +
                    "    AND EOCM.IS_TD = 'N'\n" +
                    " ORDER BY EOCM.ORGANIZATION_ID";
                sqlArgs.add(dto.getStartDate());
                sqlArgs.add(dto.getSQLEndDate());
                sqlArgs.add(dto.getStartDate());
                sqlArgs.add(dto.getSQLEndDate());
                sqlArgs.add(dto.getStartDate());
                sqlArgs.add(dto.getSQLEndDate());
                sqlArgs.add(dto.getStartDate());
                sqlArgs.add(dto.getSQLEndDate());
        } catch (CalendarException e) {
            e.printStackTrace();  
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}