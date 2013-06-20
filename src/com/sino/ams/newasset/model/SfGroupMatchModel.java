package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.SfGroupMatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: GroupMatchModel</p>
 * <p>Description:�����Զ�����SQL��������GroupMatchModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfGroupMatchModel extends AMSSQLProducer {


    /**
     * ���ܣ�GROUP_MATCH ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfGroupMatchDTO ���β���������
     */
    public SfGroupMatchModel(SfUserDTO userAccount,
                             SfGroupMatchDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ�����GROUP_MATCH���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfGroupMatchDTO dto = (SfGroupMatchDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " SF_GROUP_MATCH("
                        + " DEPT_CODE,"
                        + " GROUP_ID"
                        + ") VALUES ("
                        + " ?, ?)";

        sqlArgs.add(dto.getDeptCode());
        sqlArgs.add(dto.getGroupId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ�����GROUP_MATCH����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfGroupMatchDTO dto = (SfGroupMatchDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                        + " SF_GROUP_MATCH"
                        + " WHERE"
                        + " DEPT_CODE = ?"
                        + " AND GROUP_ID = ?";
        sqlArgs.add(dto.getDeptCode());
        sqlArgs.add(dto.getGroupId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����GROUP_MATCH������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfGroupMatchDTO dto = (SfGroupMatchDTO) dtoParameter;
        String sqlStr = "SELECT"
                        + " AMD.DEPT_CODE,"
                        + " AMD.DEPT_NAME,"
                        + " SG.GROUP_ID,"
                        + " SG.GROUP_NAME,"
                        + " EOCM.ORGANIZATION_ID,"
                        + " EOCM.COMPANY_CODE,"
                        + " EOCM.COMPANY"
                        + " FROM"
                        + " AMS_MIS_DEPT   AMD,"
                        + " SF_GROUP_MATCH SGM,"
                        + " SF_GROUP       SG,"
                        + " ETS_OU_CITY_MAP  EOCM"
                        + " WHERE"
                        + " AMD.DEPT_CODE = SGM.DEPT_CODE"
                        + " AND SGM.GROUP_ID = SG.GROUP_ID"
                        + " AND SG.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                        + " AND SGM.DEPT_CODE = ?"
                        + " AND SGM.GROUP_ID = ?";
        sqlArgs.add(dto.getDeptCode());
        sqlArgs.add(dto.getGroupId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����GROUP_MATCHҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfGroupMatchDTO dto = (SfGroupMatchDTO) dtoParameter;
        String sqlStr = "SELECT"
                        + " AMD.DEPT_CODE,"
                        + " AMD.DEPT_NAME,"
                        + " SG.GROUP_ID,"
                        + " SG.GROUP_NAME,"
                        + " EOCM.ORGANIZATION_ID,"
                        + " EOCM.COMPANY_CODE,"
                        + " EOCM.COMPANY"
                        + " FROM"
                        + " AMS_MIS_DEPT   AMD,"
                        + " SF_GROUP_MATCH SGM,"
                        + " SF_GROUP       SG,"
                        + " ETS_OU_CITY_MAP  EOCM"
                        + " WHERE"
                        + " AMD.DEPT_CODE = SGM.DEPT_CODE"
                        + " AND SGM.GROUP_ID = SG.GROUP_ID"
                        + " AND SG.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                        + " AND SGM.DEPT_CODE = dbo.NVL(?, SGM.DEPT_CODE)"
                        + " AND SGM.GROUP_ID = dbo.NVL(?, SGM.GROUP_ID)";
        sqlArgs.add(dto.getDeptCode());
        sqlArgs.add(dto.getGroupId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
