package com.sino.ams.net.locus.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.net.locus.dto.LocusDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Created by IntelliJ IDEA.
 * User: V-yuanshuai
 * Date: 2007-12-18
 * Time: 16:41:46
 * To change this template use File | Settings | File Templates.
 */
public class showEqpModel extends BaseSQLProducer {

    private LocusDTO dtoParameter;

    /**
     * ���ܣ�LOCUS ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter LocusDTO ���β���������
     */
    public showEqpModel(SfUserDTO userAccount, LocusDTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.dtoParameter = dtoParameter;
 
    }

    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT EII.BARCODE, ESI.ITEM_NAME, ESI.ITEM_SPEC, ESI.ITEM_CATEGORY, EO.WORKORDER_OBJECT_NAME\n" +
                " FROM   ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA, ETS_OBJECT EO, ETS_SYSTEM_ITEM ESI\n" +
                " WHERE  EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                " AND    AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                " AND    EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                " AND    EO.WORKORDER_OBJECT_NO = ? ";
        sqlArgs.add(dtoParameter.getWorkorderObjectNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}

