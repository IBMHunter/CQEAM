package com.sino.ams.net.locus.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.net.locus.dto.LocusDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: LocusModel</p>
 * <p>Description:�����Զ�����SQL��������LocusModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class LocusModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�LOCUS ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter LocusDTO ���β���������
     */
    public LocusModel(SfUserDTO userAccount, LocusDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }
 
    /**
     * ���ܣ�����Զ�����LOCUSҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

        List sqlArgs = new ArrayList();
        LocusDTO locus = (LocusDTO) dtoParameter;
        String sqlStr;
        if (locus.getObjectCategory().equals(WebAttrConstant.UNCHECK)) {

           sqlStr = " SELECT EO.WORKORDER_OBJECT_NO, " +
                    " EO.WORKORDER_OBJECT_CODE, " +
                    " EO.WORKORDER_OBJECT_NAME, " +
                    " EO.WORKORDER_OBJECT_LOCATION, " +
                    " EO.COUNTY_CODE, " +
                    " EC.COUNTY_NAME, " +
                    " CASE WHEN CONVERT(VARCHAR,EO.ISALL)='1' THEN 'Ѳ������רҵ' WHEN CONVERT(VARCHAR,EO.ISALL)='0' THEN 'Ѳ�챾רҵ'  END ISALL " +
                    " FROM   ETS_OBJECT EO, " +
                    "        ETS_COUNTY EC, " +
                    "        ETS_OU_CITY_MAP     EOCM "+
                    " WHERE  EO.WORKORDER_OBJECT_NO NOT IN " +
                    " (SELECT EW.WORKORDER_OBJECT_NO FROM ETS_WORKORDER EW WHERE EW.WORKORDER_TYPE = ?) " +
                    " AND    EO.ORGANIZATION_ID = ? " +
                    " AND    EO.COUNTY_CODE *= EC.COUNTY_CODE " +
                    " AND    EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"    +
                    " AND    EC.COMPANY_CODE = EOCM.COMPANY_CODE"          +
                    " AND    (EO.DISABLE_DATE IS NULL OR EO.DISABLE_DATE='') " +
                    " AND    EO.OBJECT_CATEGORY < ? " +
                   "   AND EO.OBJECT_CATEGORY = dbo.NVL(?, EO.OBJECT_CATEGORY)";

            sqlArgs.add(DictConstant.ORDER_TYPE_CHECK);
            sqlArgs.add(sfUser.getOrganizationId());
            sqlArgs.add(WebAttrConstant.INV_CATEGORY);
            sqlArgs.add(locus.getCategory());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            return sqlModel;
        } else {
            sqlStr = "SELECT EO.WORKORDER_OBJECT_NO, " +
                    "    EO.WORKORDER_OBJECT_CODE, " +
                    "    EO.WORKORDER_OBJECT_NAME, " +
                    "    EO.WORKORDER_OBJECT_LOCATION, " +
                    "    EO.ORGANIZATION_ID, " +
                    "    EO.COUNTY_CODE, " +
                    "    EO.DISABLE_DATE, " +
                    "    EO.OBJECT_CATEGORY, " +
                    " CASE WHEN CONVERT(VARCHAR,EO.ISALL)='1' THEN 'Ѳ������רҵ' WHEN CONVERT(VARCHAR,EO.ISALL)='0' THEN 'Ѳ�챾רҵ'  END ISALL ," +
                    "    EC.COUNTY_NAME " +
                    " FROM   ETS_OBJECT EO, " +
                    "    ETS_COUNTY EC, " +
                    "    ETS_OU_CITY_MAP     EOCM "+
                    " WHERE  EO.COUNTY_CODE *= EC.COUNTY_CODE " +
                    "   AND    EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"    +
                    "   AND    EC.COMPANY_CODE = EOCM.COMPANY_CODE"          +
                    "   AND    ( " + SyBaseSQLUtil.isNull() + "  OR WORKORDER_OBJECT_CODE LIKE ?) " +
                    "   AND    ( " + SyBaseSQLUtil.isNull() + "  OR WORKORDER_OBJECT_NAME LIKE ?) " +
                    "   AND    ( " + SyBaseSQLUtil.isNull() + "  OR EO.ORGANIZATION_ID = ?) " +
                    "   AND    ( " + SyBaseSQLUtil.isNull() + "  OR OBJECT_CATEGORY LIKE ?) " +
                    "   AND    ( " + SyBaseSQLUtil.isNull() + "  OR EO.COUNTY_CODE = ?) ";
            sqlArgs.add(locus.getWorkorderObjectCode());
            sqlArgs.add(locus.getWorkorderObjectCode());
            sqlArgs.add(locus.getWorkorderObjectName());
            sqlArgs.add(locus.getWorkorderObjectName());
            sqlArgs.add(sfUser.getOrganizationId());
            sqlArgs.add(sfUser.getOrganizationId());
            sqlArgs.add(locus.getObjectCategory());
            sqlArgs.add(locus.getObjectCategory());
             sqlArgs.add(locus.getCountyCode());
             sqlArgs.add(locus.getCountyCode());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            return sqlModel;
        }

    }

}