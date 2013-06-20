package com.sino.ams.newasset.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class FlexValueUtil {
    private Connection conn = null;
    private SfUserDTO userAccount = null;

    public FlexValueUtil(SfUserDTO userAccount, Connection conn) {
        this.userAccount = userAccount;
        this.conn = conn;
    }

    /**
     * ���ܣ���ȡָ���ֵ�����£�ָ���ֵ���������ֵ
     * @param dictCode1 String �ֵ����
     * @param dictCode2 String �ֵ����
     * @return String ���������ֵ
     * @throws QueryException
     */
    public String getFlexValue(String dictCode1, String dictCode2) throws
            QueryException {
        String flexValue = "";
        try {
            SQLModel sqlModel = new SQLModel();
            List sqlArgs = new ArrayList();
            String sqlStr = "SELECT"
                            + " EFV.VALUE"
                            + " FROM"
                            + " ETS_FLEX_VALUES    EFV,"
                            + " ETS_FLEX_VALUE_SET EFVS"
                            + " WHERE"
                            + " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
                            + " AND EFVS.CODE = ?"
                            + " AND EFV.CODE = ?";
            sqlArgs.add(dictCode1);
            sqlArgs.add(dictCode2);
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                Row row = simp.getFirstRow();
                flexValue = row.getStrValue("VALUE");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return flexValue;
    }
}
