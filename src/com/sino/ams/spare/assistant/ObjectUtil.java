package com.sino.ams.spare.assistant;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ������
 * @version 0.1
 *          Date: 2008-3-17
 */
public class ObjectUtil {

    /**
     * ����רҵ���ͺ�OU��ȡOJBECT_NO,ֻ�����ڴ��޿�,���޿��ÿ������ֻ��һ�������
     * @param objectCategory �ص�����
     * @param organizationId ��֯ID
     * @param conn           ����
     * @return objectNo
     * @throws com.sino.base.exception.QueryException
     * @throws com.sino.base.exception.ContainerException
     */
    public static String getObjectNo(String objectCategory, int organizationId, Connection conn) throws QueryException, ContainerException {
        String objectNo = "";
        String sqlStr = "SELECT EO.WORKORDER_OBJECT_NO\n" +
                "  FROM ETS_OBJECT EO\n" +
                " WHERE EO.OBJECT_CATEGORY = ?\n" +
                "   AND EO.ORGANIZATION_ID = ?";
        List argList = new ArrayList();
        argList.add(objectCategory);
        argList.add(organizationId);
        SQLModel sqlModel = new SQLModel();
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(argList);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        objectNo = sq.getFirstRow().getValue("WORKORDER_OBJECT_NO").toString();
        return objectNo;

    }
}
