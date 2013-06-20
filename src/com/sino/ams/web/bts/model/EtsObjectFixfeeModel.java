package com.sino.ams.web.bts.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.web.bts.dto.EtsObjectFixfeeDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsObjectFixfeeModel</p>
 * <p>Description:�����Զ�����SQL��������EtsObjectFixfeeModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class EtsObjectFixfeeModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ���վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectFixfeeDTO ���β���������
     */
    public EtsObjectFixfeeModel(SfUserDTO userAccount, EtsObjectFixfeeDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ����ɻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_OBJECT_FIXFEE\n" +
                "  (SYSTEM_ID,\n" +
                "   FIX_DATE,\n" +
                "   AMOUNT, \n" +
                "   REMARK,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,  \n" +
                "   OBJECT_NO)\n" +
                "VALUES\n" +
                "  ( NEWID() , ?, ?, ?, GETDATE(), ?, ?)";

        sqlArgs.add(etsObjectFixfee.getFixDate());
        sqlArgs.add(etsObjectFixfee.getAmount());
        sqlArgs.add(etsObjectFixfee.getRemark());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsObjectFixfee.getWorkorderObjectNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_OBJECT_FIXFEE\n" +
                "   SET FIX_DATE         = ?,\n" +
                "       AMOUNT           = ?,\n" +
                "       REMARK           = ?,\n" +
                "       LAST_UPDATE_DATE = GETDATE(),\n" +
                "       LAST_UPDATE_BY   = ?,\n" +
                "       OBJECT_NO        = ?\n" +
                " WHERE SYSTEM_ID = ?";

        sqlArgs.add(etsObjectFixfee.getFixDate());
        sqlArgs.add(etsObjectFixfee.getAmount());
        sqlArgs.add(etsObjectFixfee.getRemark());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsObjectFixfee.getWorkorderObjectNo());
        sqlArgs.add(etsObjectFixfee.getSystemId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " ETS_OBJECT_FIXFEE"
                + " WHERE"
                + " SYSTEM_ID = ?";
        sqlArgs.add(etsObjectFixfee.getSystemId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        String sqlStr = "SELECT EOF.SYSTEM_ID,\n" +
                "       EOF.FIX_DATE,\n" +
                "       EOF.AMOUNT,\n" +
                "       EOF.REMARK,\n" +
                "       EO.WORKORDER_OBJECT_NO,\n" +
                "       EO.WORKORDER_OBJECT_NAME\n" +
                "  FROM ETS_OBJECT_FIXFEE EOF, ETS_OBJECT EO\n" +
                " WHERE EOF.OBJECT_NO = EO.WORKORDER_OBJECT_NO      \n" +
                "   AND SYSTEM_ID = ?";
        sqlArgs.add(etsObjectFixfee.getSystemId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEM_ID,"
                + " FIX_DATE,"
                + " AMOUNT,"
                + " FIX_NO,"
                + " ATTRIBUTE1,"
                + " ATTRIBUTE2,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " OBJECT_NO"
                + " FROM"
                + " ETS_OBJECT_FIXFEE"
                + " WHERE"
                + "( " + SyBaseSQLUtil.isNull() + "  OR SYSTEM_ID LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR FIX_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR AMOUNT LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR FIX_NO LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR ATTRIBUTE1 LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR ATTRIBUTE2 LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR OBJECT_NO LIKE ?)";
        sqlArgs.add(etsObjectFixfee.getSystemId());
        sqlArgs.add(etsObjectFixfee.getSystemId());
        sqlArgs.add(etsObjectFixfee.getFixDate());
        sqlArgs.add(etsObjectFixfee.getFixDate());
        sqlArgs.add(etsObjectFixfee.getAmount());
        sqlArgs.add(etsObjectFixfee.getAmount());
        sqlArgs.add(etsObjectFixfee.getFixNo());
        sqlArgs.add(etsObjectFixfee.getFixNo());
        sqlArgs.add(etsObjectFixfee.getAttribute1());
        sqlArgs.add(etsObjectFixfee.getAttribute1());
        sqlArgs.add(etsObjectFixfee.getAttribute2());
        sqlArgs.add(etsObjectFixfee.getAttribute2());
        sqlArgs.add(etsObjectFixfee.getRemark());
        sqlArgs.add(etsObjectFixfee.getRemark());
        sqlArgs.add(etsObjectFixfee.getCreationDate());
        sqlArgs.add(etsObjectFixfee.getCreationDate());
        sqlArgs.add(etsObjectFixfee.getCreatedBy());
        sqlArgs.add(etsObjectFixfee.getCreatedBy());
        sqlArgs.add(etsObjectFixfee.getLastUpdateDate());
        sqlArgs.add(etsObjectFixfee.getLastUpdateDate());
        sqlArgs.add(etsObjectFixfee.getLastUpdateBy());
        sqlArgs.add(etsObjectFixfee.getLastUpdateBy());
        sqlArgs.add(etsObjectFixfee.getObjectNo());
        sqlArgs.add(etsObjectFixfee.getObjectNo());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� objectNo �����ѯ����SQL��
     * ����Զ��������ݻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param objectNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByObjectNoModel(int objectNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " SYSTEM_ID,"
                + " FIX_DATE,"
                + " AMOUNT,"
                + " FIX_NO,"
                + " ATTRIBUTE1,"
                + " ATTRIBUTE2,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " ETS_OBJECT_FIXFEE"
                + " WHERE"
                + " OBJECT_NO = ?";
        sqlArgs.add(objectNo);

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
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        if (foreignKey.equals("objectNo")) {
            sqlModel = getDataByObjectNoModel(etsObjectFixfee.getObjectNo());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� objectNo ��������ɾ��SQL��
     * ����Զ��������ݻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEE ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param objectNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByObjectNoModel(int objectNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " ETS_OBJECT_FIXFEE"
                + " WHERE"
                + " OBJECT_NO = ?";
        sqlArgs.add(objectNo);

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
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        if (foreignKey.equals("objectNo")) {
            sqlModel = getDeleteByObjectNoModel(etsObjectFixfee.getObjectNo());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վά�޳ɱ�(EAM) ETS_OBJECT_FIXFEEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectFixfeeDTO etsObjectFixfee = (EtsObjectFixfeeDTO) dtoParameter;
        String sqlStr = "SELECT EO.WORKORDER_OBJECT_NAME,\n" +
                "       EOF.FIX_DATE,\n" +
                "       EOF.AMOUNT,\n" +
                "       EOF.REMARK,\n" +
                "       EOF.CREATION_DATE,\n" +
                "       SU.USERNAME," +
                "        EOF.SYSTEM_ID," +
                "       EO.WORKORDER_OBJECT_CODE\n" +
                "  FROM ETS_OBJECT_FIXFEE EOF, ETS_OBJECT EO, SF_USER SU\n" +
                " WHERE EOF.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND SU.USER_ID = EOF.CREATED_BY\n" +
                "   AND EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR EOF.CREATION_DATE >= TO_DATE(?, 'YYYY-MM-DD'))\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR EOF.CREATION_DATE < TO_DATE(?, 'YYYY-MM-DD')+1)" +
                "   AND EO.ORGANIZATION_ID=CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EO.ORGANIZATION_ID)))";

        sqlArgs.add(etsObjectFixfee.getWorkorderObjectName());
        sqlArgs.add(etsObjectFixfee.getFromDate());
        sqlArgs.add(etsObjectFixfee.getFromDate());
        sqlArgs.add(etsObjectFixfee.getToDate());
        sqlArgs.add(etsObjectFixfee.getToDate());
        sqlArgs.add(etsObjectFixfee.getCompany());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}