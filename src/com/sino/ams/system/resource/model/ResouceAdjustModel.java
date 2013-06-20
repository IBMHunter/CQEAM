package com.sino.ams.system.resource.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.resource.dto.SfResDefineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: SfResDefineModel</p>
 * <p>Description:�����Զ�����SQL��������SfResDefineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class ResouceAdjustModel extends AMSSQLProducer {

    /**
     * ���ܣ�SF_RES_DEFINE ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfResDefineDTO ���β���������
     */
    public ResouceAdjustModel(SfUserDTO userAccount, SfResDefineDTO dtoParameter) {
        super(userAccount, dtoParameter);
        dtoParameter.setCreatedBy(userAccount.getUserId());
    }

    public SQLModel getChildrenModel(String resParId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SRD.RES_ID, SRD.RES_NAME\n" +
                "  FROM SF_RES_DEFINE SRD\n";
        if (StrUtil.isEmpty(resParId)) {
            sqlStr += " WHERE SRD.RES_PAR_ID " + SyBaseSQLUtil.isNullNoParam() + " \n" +
                    "  ORDER BY SRD.SORT_NO";
        } else {
            sqlStr += " WHERE SRD.RES_PAR_ID = ?\n" +
                    "  ORDER BY SRD.SORT_NO";
            sqlArgs.add(resParId);
        }


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel getUpdateResOrderModel(String resId, int sortNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE SF_RES_DEFINE " +
                "   SET SORT_NO = ? " +
                "   WHERE RES_ID = ?";

        sqlArgs.add( sortNo );
//        sqlArgs.add(String.valueOf(sortNo));
        sqlArgs.add(resId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    public SQLModel getResourceOptionModel(String resourceId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//        String sqlStr = "SELECT SRD.RES_ID, SRD.RES_NAME, SRD.SORT_NO\n" +
//                "  FROM SF_RES_DEFINE SRD\n" +
//                " START WITH SRD.RES_PAR_ID " + SyBaseSQLUtil.isNull() + " \n" +
//                "CONNECT BY PRIOR SRD.RES_ID = SRD.RES_PAR_ID\n" +
//                " ORDER SIBLINGS BY SRD.SORT_NO";
        
        String sqlStr = "SELECT SRD.RES_ID, SRD.RES_NAME, SRD.SORT_NO\n" +
        "  FROM SF_RES_DEFINE SRD\n" +
        "  WHERE (SRD.RES_ID <> ? AND SRD.RES_ID NOT LIKE ?)" +
        "  OR ? = '' ORDER BY SRD.RES_ID,SRD.RES_PAR_ID";

        sqlArgs.add(resourceId);
        sqlArgs.add(resourceId + ".%");
        sqlArgs.add(resourceId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}