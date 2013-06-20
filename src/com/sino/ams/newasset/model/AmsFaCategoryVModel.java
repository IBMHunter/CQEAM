package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsFaCategoryVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: AmsFaCategoryVModel</p>
 * <p>Description:�����Զ�����SQL��������AmsFaCategoryVModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsFaCategoryVModel extends BaseSQLProducer {

    /**
     * ���ܣ�AMS_FA_CATEGORY_V ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsFaCategoryVDTO ���β���������
     */
    public AmsFaCategoryVModel(SfUserDTO userAccount,
                               AmsFaCategoryVDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ�����AMS_FA_CATEGORY_Vҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsFaCategoryVDTO dto = (AmsFaCategoryVDTO) dtoParameter;
        String mtlPrivi = dto.getMtlPrivi();
        String tmpVariable = "";
        if (mtlPrivi.equals(AssetsWebAttributes.MTL_PRIVI_N)) {
            tmpVariable = " NOT";
        }
        String sqlStr = "SELECT "
                        + " AFCV.FA_CAT_CODE_1,"
                        + " AFCV.FA_CAT_NAME_1,"
                        + " AFCV.FA_CAT_CODE_2,"
                        + " AFCV.FA_CAT_NAME_2,"
                        + " AFCV.FA_CAT_CODE_3,"
                        + " AFCV.FA_CAT_NAME_3,"
                        + " AFCV.FA_CATEGORY_CODE,"
                        + " AFCV.FA_CATEGORY_NAME"
                        + " FROM"
                        + " AMS_FA_CATEGORY_V AFCV"
                        + " WHERE"
                        +
                " CHARINDEX(FA_CATEGORY_NAME, dbo.NVL(?, FA_CATEGORY_NAME)) > 0"
                        + " AND"
                        + tmpVariable
                        + " EXISTS("
                        + " SELECT"
                        + " NULL"
                        + " FROM"
                        + " AMS_ASSETS_PRIVI  AAP"
                        + " WHERE"
                        + " AFCV.FA_CATEGORY_CODE = AAP.FA_CATEGORY_CODE"
                        + " AND AAP.USER_ID = ?)"
                        + " ORDER BY"
                        + " AFCV.FA_CAT_CODE_1,"
                        + " AFCV.FA_CAT_CODE_2,"
                        + " AFCV.FA_CAT_CODE_3";
        sqlArgs.add(dto.getFaCategoryName());
        sqlArgs.add(dto.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
