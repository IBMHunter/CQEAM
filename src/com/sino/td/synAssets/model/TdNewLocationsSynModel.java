package com.sino.td.synAssets.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * Author:      ���W
 * Date:        2009-9-8
 * Time:        17:25:55
 * Function��    TD�������cͬ��
 */
public class TdNewLocationsSynModel extends AMSSQLProducer {
	private EamSyschronizeDTO dto = null;

	/**
	 * ���ܣ�TD�������cͬ�� ���ݿ�SQL����㹹�캯��
	 *
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemMatchDTO ���β���������
	 */
	public TdNewLocationsSynModel(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter) {
		super(userAccount, dtoParameter);
		dto = dtoParameter;
	}

    /**
	 * ���ܣ�����Զ�����LOCUSҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr;
		sqlStr = "SELECT DISTINCT \n" +
                "                EO.WORKORDER_OBJECT_NO,\n" +
                "                EO.WORKORDER_OBJECT_CODE,\n" +
                "                EO.WORKORDER_OBJECT_LOCATION,\n" +
                "                EO.LOCATION_CODE,\n" +
                "                TT.ASSETS_LOCATION_CODE,\n" +
                "                TT.ASSETS_LOCATION,\n" +
                "                AMS_PUB_PKG.GET_FLEX_VALUE(EO.OBJECT_CATEGORY,\n" +
                "                                           'OBJECT_CATEGORY') WORKORDER_CATEGORY,\n" +
                "                EO.ORGANIZATION_ID,\n" +
                "                EO.CREATED_BY,\n" +
                "                EO.WORKORDER_OBJECT_NAME,\n" +
                "                EO.LAST_UPDATE_DATE,\n" +
                "                SU.USERNAME,\n" +
                "                EO.REMARK,\n" +
                "                EO.CREATION_DATE\n" +
                "  FROM ETS_OBJECT EO, ETS_FA_ASSETS_LOCATION_TD TT, SF_USER SU\n" +
                " WHERE (EO.DISABLE_DATE ='' OR EO.DISABLE_DATE IS NULL)\n" +
                "   AND EO.WORKORDER_OBJECT_NAME NOT LIKE '%.��Ч'\n" +
                "   AND EO.WORKORDER_OBJECT_NAME NOT LIKE '%.ȱʡ'\n" +
                "   AND EO.LOCATION_CODE *= TT.ASSETS_LOCATION_CODE\n" +
                "   AND EO.ORGANIZATION_ID *= TT.ORG_ID\n" +
                "   AND EO.LAST_UPDATE_BY *= SU.USER_ID\n" +
                "   AND (EO.OBJECT_CATEGORY < 70 OR EO.OBJECT_CATEGORY = 80)\n" +
                "   AND (NOT EXISTS\n" +
                "        (SELECT NULL\n" +
                "           FROM ETS_FA_ASSETS_LOCATION_TD T\n" +
                "          WHERE T.ASSETS_LOCATION_CODE = EO.LOCATION_CODE\n" +
                "            AND T.ORG_ID = EO.ORGANIZATION_ID) OR EXISTS\n" +
                "        (SELECT NULL\n" +
                "           FROM ETS_FA_ASSETS_LOCATION_TD T\n" +
                "          WHERE T.ASSETS_LOCATION_CODE = EO.LOCATION_CODE\n" +
                "            AND T.ORG_ID = EO.ORGANIZATION_ID\n" +
                "            AND T.ASSETS_LOCATION <> EO.WORKORDER_OBJECT_NAME))\n" +
                "   AND NOT EXISTS\n" +
                " (SELECT NULL\n" +
                "          FROM ETS_FA_NEW_LOC EFC\n" +
                "         WHERE EFC.LOCATION = EO.WORKORDER_OBJECT_LOCATION\n" +
                "           AND EFC.ORGANIZATION_ID = EO.ORGANIZATION_ID\n" +
                "           AND (EFC.STATUS = 0 OR\n" +
                "               (EFC.STATUS = 1 AND CONVERT(VARCHAR,EFC.CREATION_DATE,110) =\n" +
                "               CONVERT(VARCHAR,GETDATE(),110))))\n" +
				" AND EO.WORKORDER_OBJECT_LOCATION LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_LOCATION)"
				+ " AND (" + SyBaseSQLUtil.isNull() + " OR EO.WORKORDER_OBJECT_CODE LIKE ?)"
				+ " AND (EO.DISABLE_DATE  " + SyBaseSQLUtil.isNullNoParam() + "  OR EO.DISABLE_DATE >= GETDATE() )"
				+ " AND EO.ORGANIZATION_ID=?"

                + " AND (" + SyBaseSQLUtil.isNull() + " OR EO.CREATION_DATE >= ?)"
                + " AND (" + SyBaseSQLUtil.isNull() + " OR EO.CREATION_DATE < ? + 1)"
                + "ORDER BY EO.CREATION_DATE DESC";
        try {
            sqlArgs.add(dto.getNewAssetsLocation());
            sqlArgs.add(dto.getWorkorderObjectCode());
            sqlArgs.add(dto.getWorkorderObjectCode());
            sqlArgs.add(userAccount.getOrganizationId());


            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlArgs.add(dto.getSQLEndDate());
        } catch (CalendarException e) {
            e.printStackTrace();
        }
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}