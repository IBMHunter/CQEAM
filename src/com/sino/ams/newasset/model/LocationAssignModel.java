package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class LocationAssignModel extends AMSSQLProducer {

    public LocationAssignModel(SfUserDTO userAccount, EtsObjectDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���ȡ�ص������б���SQL
     * @return SQLModel
     */
    public SQLModel getLocationOptionsModel() {
        SQLModel sqlModel = new SQLModel();
        EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                        + " EO.WORKORDER_OBJECT_NO,"
                        +
                " EO.WORKORDER_OBJECT_LOCATION || '[' || EO.WORKORDER_OBJECT_CODE || ']'"
                        + " FROM"
                        + " ETS_OBJECT EO"
                        + " WHERE"
                        + " EO.OBJECT_CATEGORY < ?"
                        + " AND EO.IS_TEMP_ADDR = ?"
                        + " AND EO.ORGANIZATION_ID = ?"
                        +
                " AND EO.WORKORDER_OBJECT_CODE = dbo.NVL(?, EO.WORKORDER_OBJECT_CODE)"
                        +
                " AND EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)"
                        +
                " AND (EO.DISABLE_DATE " + SyBaseSQLUtil.isNullNoParam() + "  OR EO.DISABLE_DATE > GETDATE() OR EO.DISABLE_DATE IS NULL)";
        sqlArgs.add(AssetsDictConstant.INV_NORMAL);
        sqlArgs.add(AssetsDictConstant.LOCATION_TMP_NO);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(dto.getWorkorderObjectCode());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
