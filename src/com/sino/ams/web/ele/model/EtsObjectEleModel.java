package com.sino.ams.web.ele.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.web.ele.dto.EtsObjectEleDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsObjectEleModel</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class EtsObjectEleModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ���վ���ά����(EAM) ETS_OBJECT_ELE ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectEleDTO ���β���������
     */
    public EtsObjectEleModel(SfUserDTO userAccount, EtsObjectEleDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ����ɻ�վ���ά����(EAM) ETS_OBJECT_ELE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_OBJECT_ELE\n" +
                "  (SYSTEMID,\n" +
                "   WORKORDER_OBJECT_NO,\n" +
                "   PERIOD,\n" +
                "   UNIT_PRICE,\n" +
                "   QUANTITY,\n" +
                /*"   START_DATE,\n" +
                "   END_DATE,\n" +*/
                "   REMARK,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY," +
                "   AMMETER_READING)\n" +
                "VALUES\n" +
                "  ( NEWID() ,  ?, ?, ?, ?, ?, GETDATE(), ?, ?)";

        sqlArgs.add(etsObjectEle.getWorkorderObjectNo());
        sqlArgs.add(etsObjectEle.getPeriod());
        sqlArgs.add(etsObjectEle.getUnitPrice());
        sqlArgs.add(etsObjectEle.getQuantity());
      /*  sqlArgs.add(etsObjectEle.getStartDate());
        sqlArgs.add(etsObjectEle.getEndDate());*/
        sqlArgs.add(etsObjectEle.getRemark());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsObjectEle.getAmmeterReading());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վ���ά����(EAM) ETS_OBJECT_ELE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_OBJECT_ELE"
                + " SET"
                + " WORKORDER_OBJECT_NO = ?,"
                + " PERIOD = ?,"
                + " UNIT_PRICE = ?,"
                + " QUANTITY = ?,"
                + " AMMETER_READING = ?,"
             /*   + " END_DATE = ?,"*/
                + " REMARK = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " SYSTEMID = ?";

        sqlArgs.add(etsObjectEle.getWorkorderObjectNo());
        sqlArgs.add(etsObjectEle.getPeriod());
        sqlArgs.add(etsObjectEle.getUnitPrice());
        sqlArgs.add(etsObjectEle.getQuantity());
        sqlArgs.add(etsObjectEle.getAmmeterReading());
        /*sqlArgs.add(etsObjectEle.getEndDate());*/
        sqlArgs.add(etsObjectEle.getRemark());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsObjectEle.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վ���ά����(EAM) ETS_OBJECT_ELE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " ETS_OBJECT_ELE"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(etsObjectEle.getWorkorderObjectNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վ���ά����(EAM) ETS_OBJECT_ELE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        String sqlStr = "SELECT EOE.SYSTEMID,\n" +
                "       EOE.WORKORDER_OBJECT_NO,\n" +
                "       EOE.PERIOD,\n" +
                "       EOE.UNIT_PRICE,\n" +
                "       EOE.QUANTITY,\n" +
                "       EOE.REMARK,\n" +
                "       EOE.AMMETER_READING,\n" +
                "       EO.WORKORDER_OBJECT_NAME,\n" +
                "       EOE.LAST_UPDATE_DATE,\n" +
                "       SU.USERNAME LAST_UPDATE_BY\n" +
                "  FROM ETS_OBJECT_ELE EOE, ETS_OBJECT EO, SF_USER SU\n" +
                " WHERE EO.WORKORDER_OBJECT_NO = EOE.WORKORDER_OBJECT_NO\n" +
                "   AND EOE.LAST_UPDATE_BY *= SU.USER_ID\n" +
                "   AND SYSTEMID = ?";
        sqlArgs.add(etsObjectEle.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վ���ά����(EAM) ETS_OBJECT_ELE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " WORKORDER_OBJECT_NO,"
                + " PERIOD,"
                + " UNIT_PRICE,"
                + " QUANTITY,"
                + " START_DATE,"
                + " END_DATE,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " AMMETER_READING"
                + " FROM"
                + " ETS_OBJECT_ELE"
                + " WHERE"
                + "( " + SyBaseSQLUtil.isNull() + "  OR SYSTEMID LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR WORKORDER_OBJECT_NO LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR PERIOD LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR UNIT_PRICE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR QUANTITY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR START_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR END_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR REMARK LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
        sqlArgs.add(etsObjectEle.getSystemid());
        sqlArgs.add(etsObjectEle.getSystemid());
        sqlArgs.add(etsObjectEle.getWorkorderObjectNo());
        sqlArgs.add(etsObjectEle.getWorkorderObjectNo());
        sqlArgs.add(etsObjectEle.getPeriod());
        sqlArgs.add(etsObjectEle.getPeriod());
        sqlArgs.add(etsObjectEle.getUnitPrice());
        sqlArgs.add(etsObjectEle.getUnitPrice());
        sqlArgs.add(etsObjectEle.getQuantity());
        sqlArgs.add(etsObjectEle.getQuantity());
        sqlArgs.add(etsObjectEle.getStartDate());
        sqlArgs.add(etsObjectEle.getStartDate());
        sqlArgs.add(etsObjectEle.getEndDate());
        sqlArgs.add(etsObjectEle.getEndDate());
        sqlArgs.add(etsObjectEle.getRemark());
        sqlArgs.add(etsObjectEle.getRemark());
        sqlArgs.add(etsObjectEle.getCreationDate());
        sqlArgs.add(etsObjectEle.getCreationDate());
        sqlArgs.add(etsObjectEle.getCreatedBy());
        sqlArgs.add(etsObjectEle.getCreatedBy());
        sqlArgs.add(etsObjectEle.getLastUpdateDate());
        sqlArgs.add(etsObjectEle.getLastUpdateDate());
        sqlArgs.add(etsObjectEle.getLastUpdateBy());
        sqlArgs.add(etsObjectEle.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� workorderObjectNo �����ѯ����SQL��
     * ����Զ��������ݻ�վ���ά����(EAM) ETS_OBJECT_ELE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param workorderObjectNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByWorkorderObjectNoModel(String workorderObjectNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " PERIOD,"
                + " UNIT_PRICE,"
                + " QUANTITY,"
                + " START_DATE,"
                + " END_DATE,"
                + " REMARK,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " AMMETER_READING"
                + " FROM"
                + " ETS_OBJECT_ELE"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(workorderObjectNo);

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
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        if (foreignKey.equals("workorderObjectNo")) {
            sqlModel = getDataByWorkorderObjectNoModel(etsObjectEle.getWorkorderObjectNo());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� workorderObjectNo ��������ɾ��SQL��
     * ����Զ��������ݻ�վ���ά����(EAM) ETS_OBJECT_ELE ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param workorderObjectNo String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteByWorkorderObjectNoModel(String workorderObjectNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " ETS_OBJECT_ELE"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(workorderObjectNo);

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
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        if (foreignKey.equals("workorderObjectNo")) {
            sqlModel = getDeleteByWorkorderObjectNoModel(etsObjectEle.getWorkorderObjectNo());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɻ�վ���ά����(EAM) ETS_OBJECT_ELEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectEleDTO etsObjectEle = (EtsObjectEleDTO) dtoParameter;
        String sqlStr = "SELECT EOE.SYSTEMID,\n" +
                "       EO.WORKORDER_OBJECT_NAME,\n" +
                "       EOE.PERIOD,\n" +
                "       EOE.UNIT_PRICE,\n" +
                "       EOE.QUANTITY,\n" +
                "       EOE.CREATION_DATE,\n" +
                "       SU.USERNAME\n" +
                "  FROM ETS_OBJECT_ELE EOE, ETS_OBJECT EO, SF_USER SU\n" +
                " WHERE EOE.WORKORDER_OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND SU.USER_ID = EOE.CREATED_BY\n" +
                "   AND EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR EOE.CREATION_DATE >= TO_DATE(?, 'YYYY-MM-DD'))\n" +
                "   AND ( " + SyBaseSQLUtil.isNull() + "  OR EOE.CREATION_DATE < TO_DATE(?, 'YYYY-MM-DD')+1)\n" +
                "   AND EOE.PERIOD LIKE dbo.NVL(?, EOE.PERIOD)" +
                "   AND EO.ORGANIZATION_ID = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, EO.ORGANIZATION_ID)))";

        sqlArgs.add(etsObjectEle.getWorkorderObjectName());
        sqlArgs.add(etsObjectEle.getFromDate());
        sqlArgs.add(etsObjectEle.getFromDate());
        sqlArgs.add(etsObjectEle.getToDate());
        sqlArgs.add(etsObjectEle.getToDate());
        sqlArgs.add(etsObjectEle.getPeriod());
        sqlArgs.add(etsObjectEle.getCompany());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}