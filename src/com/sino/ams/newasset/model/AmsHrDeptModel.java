package com.sino.ams.newasset.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsHrDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;


/**
 * <p>Title: AmsHrDeptModel</p>
 * <p>Description:�����Զ�����SQL��������AmsHrDeptModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsHrDeptModel extends AMSSQLProducer {

    /**
     * ���ܣ�MIS����(HR) AMS_MIS_DEPT ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHrDeptDTO ���β���������
     */
    public AmsHrDeptModel(SfUserDTO userAccount, AmsHrDeptDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ�����MIS����(HR) AMS_HR_DEPT���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " AMS_MIS_DEPT("
                        + " DEPT_CODE,"
                        + " DEPT_NAME,"
                        + " COMPANY_CODE"
                        + ") VALUES ("
                        + "  NEWID() , ?, ?)";

        sqlArgs.add(amsHrDept.getHrDeptName());
        sqlArgs.add(amsHrDept.getCompanyCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����MIS����(HR) AMS_HR_DEPT���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_MIS_DEPT"
                        + " SET"
                        + " DEPT_NAME = ?,"
                        + " COMPANY_CODE = ?"
                        + " WHERE"
                        + " DEPT_CODE = ?";

        sqlArgs.add(amsHrDept.getHrDeptName());
        sqlArgs.add(amsHrDept.getCompanyCode());
        sqlArgs.add(amsHrDept.getHrDeptId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����MIS����(HR) AMS_HR_DEPT����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                        + " AMS_MIS_DEPT"
                        + " WHERE"
                        + " DEPT_CODE = ?";
        sqlArgs.add(amsHrDept.getHrDeptId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����MIS����(HR) AMS_HR_DEPT������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        String sqlStr = "SELECT "
                        + " DEPT_CODE,"
                        + " DEPT_NAME,"
                        + " COMPANY_CODE"
                        + " FROM"
                        + " AMS_MIS_DEPT"
                        + " WHERE"
                        + " DEPT_CODE = ?";
        sqlArgs.add(amsHrDept.getHrDeptId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����MIS����(HR) AMS_HR_DEPT����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getMuxDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        String sqlStr = "SELECT "
                        + " DEPT_CODE,"
                        + " DEPT_NAME,"
                        + " COMPANY_CODE"
                        + " FROM"
                        + " AMS_MIS_DEPT"
                        + " WHERE"
                        + " ( " + SyBaseSQLUtil.isNull() + "  OR DEPT_CODE LIKE ?)"
                        + " AND ( " + SyBaseSQLUtil.isNull() + "  OR DEPT_NAME LIKE ?)"
                        + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COMPANY_CODE LIKE ?)";
        sqlArgs.add(amsHrDept.getHrDeptId());
        sqlArgs.add(amsHrDept.getHrDeptId());
        sqlArgs.add(amsHrDept.getHrDeptName());
        sqlArgs.add(amsHrDept.getHrDeptName());
        sqlArgs.add(amsHrDept.getCompanyCode());
        sqlArgs.add(amsHrDept.getCompanyCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� companyCode �����ѯ����SQL��
     * ����Զ���������MIS����(HR) AMS_HR_DEPT��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param companyCode String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByCompanyCodeModel(String companyCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                        + " DEPT_CODE,"
                        + " DEPT_NAME"
                        + " FROM"
                        + " AMS_MIS_DEPT"
                        + " WHERE"
                        + " COMPANY_CODE = ?";
        sqlArgs.add(companyCode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        if (foreignKey.equals("companyCode")) {
            sqlModel = getDataByCompanyCodeModel(amsHrDept.getCompanyCode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� companyCode ��������ɾ��SQL��
     * ����Զ���������MIS����(HR) AMS_HR_DEPT����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param companyCode String
     * @return SQLModel ��������ɾ����SQLModel
     */
    private SQLModel getDeleteByCompanyCodeModel(String companyCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE "
                        + " DEPT_CODE,"
                        + " DEPT_NAME"
                        + " FROM"
                        + " AMS_MIS_DEPT"
                        + " WHERE"
                        + " COMPANY_CODE = ?";
        sqlArgs.add(companyCode);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���������ֶ�ɾ������
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDeleteByForeignKeyModel(String foreignKey) {
        SQLModel sqlModel = null;
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        if (foreignKey.equals("companyCode")) {
            sqlModel = getDeleteByCompanyCodeModel(amsHrDept.getCompanyCode());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����MIS����(HR) AMS_HR_DEPTҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsHrDeptDTO amsHrDept = (AmsHrDeptDTO) dtoParameter;
        String sqlStr = "SELECT "
                        + " DEPT_CODE,"
                        + " DEPT_NAME,"
                        + " COMPANY_CODE"
                        + " FROM"
                        + " AMS_MIS_DEPT"
                        + " WHERE"
                        + " ( " + SyBaseSQLUtil.isNull() + "  OR DEPT_CODE LIKE ?)"
                        + " AND ( " + SyBaseSQLUtil.isNull() + "  OR DEPT_NAME LIKE ?)"
                        + " AND ( " + SyBaseSQLUtil.isNull() + "  OR COMPANY_CODE LIKE ?)";
        sqlArgs.add(amsHrDept.getHrDeptId());
        sqlArgs.add(amsHrDept.getHrDeptId());
        sqlArgs.add(amsHrDept.getHrDeptName());
        sqlArgs.add(amsHrDept.getHrDeptName());
        sqlArgs.add(amsHrDept.getCompanyCode());
        sqlArgs.add(amsHrDept.getCompanyCode());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
