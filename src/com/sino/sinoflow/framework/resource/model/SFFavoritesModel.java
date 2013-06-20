package com.sino.sinoflow.framework.resource.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.framework.resource.dto.SFFavoritesDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: EtsFavoritesModel</p>
 * <p>Description:�����Զ�����SQL��������EtsFavoritesModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class SFFavoritesModel extends BaseSQLProducer {

    private SfUserBaseDTO sfUser = null;

    /**
     * ���ܣ��û������ղؼ�(AMS) SF_FAVORITES ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserBaseDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsFavoritesDTO ���β���������
     */
    public SFFavoritesModel(SfUserBaseDTO userAccount, SFFavoritesDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ������û������ղؼ�(AMS) SF_FAVORITES���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " SF_FAVORITES("
                + " SORT_NO,"
                + " USER_ID,"
                + " SF_RES_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?, ?, ?)";

        sqlArgs.add(etsFavorites.getSortNo());
        sqlArgs.add(etsFavorites.getUserId());
        sqlArgs.add(etsFavorites.getSfResId());
        sqlArgs.add(etsFavorites.getCreationDate());
        sqlArgs.add(etsFavorites.getCreatedBy());
        sqlArgs.add(etsFavorites.getLastUpdateDate());
        sqlArgs.add(etsFavorites.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������û������ղؼ�(AMS) SF_FAVORITES���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        String sqlStr = "UPDATE SF_FAVORITES"
                + " SET"
                + " SORT_NO = ?,"
                + " USER_ID = ?,"
                + " SF_RES_ID = ?,"
                + " CREATION_DATE = ?,"
                + " CREATED_BY = ?,"
                + " LAST_UPDATE_DATE = ?,"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " SYSTEMID = ?";

        sqlArgs.add(etsFavorites.getSortNo());
        sqlArgs.add(etsFavorites.getUserId());
        sqlArgs.add(etsFavorites.getSfResId());
        sqlArgs.add(etsFavorites.getCreationDate());
        sqlArgs.add(etsFavorites.getCreatedBy());
        sqlArgs.add(etsFavorites.getLastUpdateDate());
        sqlArgs.add(etsFavorites.getLastUpdateBy());
        sqlArgs.add(etsFavorites.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������û������ղؼ�(AMS) SF_FAVORITES����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + " SYSTEMID = ?";
        sqlArgs.add(etsFavorites.getSystemid());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������û������ղؼ�(AMS) SF_FAVORITES������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " SORT_NO,"
                + " USER_ID,"
                + " SF_RES_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + " SYSTEMID = ?";
        sqlArgs.add(etsFavorites.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������û������ղؼ�(AMS) SF_FAVORITES����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " SORT_NO,"
                + " USER_ID,"
                + " SF_RES_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + "(? <= 0 OR SYSTEMID = ?)"
                + "(? <= 0 OR SORT_NO = ?)"
                + "(? <= 0 OR USER_ID = ?)"
                + "(? <= 0 OR SF_RES_ID = ?)"
                + "(? = '' OR CREATION_DATE LIKE ?)"
                + "(? <= 0 OR CREATED_BY = ?)"
                + "(? = '' OR LAST_UPDATE_DATE LIKE ?)"
                + "(? <= 0 OR LAST_UPDATE_BY = ?)";
        sqlArgs.add(etsFavorites.getSystemid());
        sqlArgs.add(etsFavorites.getSystemid());
        sqlArgs.add(etsFavorites.getSortNo());
        sqlArgs.add(etsFavorites.getSortNo());
        sqlArgs.add(etsFavorites.getUserId());
        sqlArgs.add(etsFavorites.getUserId());
        sqlArgs.add(etsFavorites.getSfResId());
        sqlArgs.add(etsFavorites.getSfResId());
        sqlArgs.add(etsFavorites.getCreationDate());
        sqlArgs.add(etsFavorites.getCreationDate());
        sqlArgs.add(etsFavorites.getCreatedBy());
        sqlArgs.add(etsFavorites.getCreatedBy());
        sqlArgs.add(etsFavorites.getLastUpdateDate());
        sqlArgs.add(etsFavorites.getLastUpdateDate());
        sqlArgs.add(etsFavorites.getLastUpdateBy());
        sqlArgs.add(etsFavorites.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� sfResId �����ѯ����SQL��
     * ����Զ����������û������ղؼ�(AMS) SF_FAVORITES��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param sfResId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataBySfResIdModel(int sfResId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " SORT_NO,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + " SF_RES_ID = ?";
        sqlArgs.add(sfResId);

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
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        if (foreignKey.equals("sfResId")) {
            sqlModel = getDataBySfResIdModel(etsFavorites.getSfResId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� sfResId ��������ɾ��SQL��
     * ����Զ����������û������ղؼ�(AMS) SF_FAVORITES ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @param sfResId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDeleteBySfResIdModel(int sfResId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + " SF_RES_ID = ?";
        sqlArgs.add(sfResId);

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
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        if (foreignKey.equals("sfResId")) {
            sqlModel = getDeleteBySfResIdModel(etsFavorites.getSfResId());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������û������ղؼ�(AMS) SF_FAVORITESҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SFFavoritesDTO etsFavorites = (SFFavoritesDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " SYSTEMID,"
                + " SORT_NO,"
                + " USER_ID,"
                + " SF_RES_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + " (? <= 0 OR SYSTEMID = ?)"
                + "AND (? <= 0 OR SORT_NO = ?)"
                + "AND (? <= 0 OR USER_ID = ?)"
                + "AND (? <= 0 OR SF_RES_ID = ?)"
                + "AND (? = '' OR CREATION_DATE LIKE ?)"
                + "AND (? <= 0 OR CREATED_BY = ?)"
                + "AND (? = '' OR LAST_UPDATE_DATE LIKE ?)"
                + "AND (? <= 0 OR LAST_UPDATE_BY = ?)";
        sqlArgs.add(etsFavorites.getSystemid());
        sqlArgs.add(etsFavorites.getSystemid());
        sqlArgs.add(etsFavorites.getSortNo());
        sqlArgs.add(etsFavorites.getSortNo());
        sqlArgs.add(etsFavorites.getUserId());
        sqlArgs.add(etsFavorites.getUserId());
        sqlArgs.add(etsFavorites.getSfResId());
        sqlArgs.add(etsFavorites.getSfResId());
        sqlArgs.add(etsFavorites.getCreationDate());
        sqlArgs.add(etsFavorites.getCreationDate());
        sqlArgs.add(etsFavorites.getCreatedBy());
        sqlArgs.add(etsFavorites.getCreatedBy());
        sqlArgs.add(etsFavorites.getLastUpdateDate());
        sqlArgs.add(etsFavorites.getLastUpdateDate());
        sqlArgs.add(etsFavorites.getLastUpdateBy());
        sqlArgs.add(etsFavorites.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //������ͨ��ѡ����Ŀ  Ȼ�󴫸�����  ѡ������Ŀ
    public SQLModel getMenu() {
        SQLModel sqlModel = new SQLModel();
        ArrayList list = new ArrayList();
        String sqlStr = "SELECT SRD.RES_ID, SRD.RES_NAME" +
                "  FROM SF_RES_DEFINE SRD" +
                " WHERE SRD.RES_PAR_ID = 0" +
                "   AND EXISTS (SELECT NULL" +
                "          FROM SF_RES_PRIVS SRP, SF_USER_AUTHORITY SUA" +
                "         WHERE SRP.ROLE_NAME = SUA.ROLE_NAME" +
                "           AND SRP.SYSTEM_ID = SRD.SYSTEM_ID" +
                "           AND SUA.USER_ID = ?)" +
                " ORDER BY SRD.RES_ID, SRD.RES_PAR_ID";
        list.add(sfUser.getUserId());
        sqlModel.setArgs(list);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    public SQLModel getSmallMenu(String resParId) {
        SQLModel sqlModel = new SQLModel();
        ArrayList list = new ArrayList();
        String sqlStr = "SELECT SRD.RES_NAME,SRD.RES_ID,SRD.SYSTEM_ID" +
                "  FROM SF_RES_DEFINE SRD" +
                " WHERE  " + SyBaseSQLUtil.isNotNull("SRD.RES_PAR_ID") + " " +
                "   AND  " + SyBaseSQLUtil.isNotNull("SRD.RES_URL") + " " +
                "   AND EXISTS (SELECT NULL" +
                "          FROM SF_RES_PRIVS SRP, SF_USER_AUTHORITY SUA" +
                "         WHERE SRP.ROLE_NAME = SUA.ROLE_NAME" +
                "           AND SRP.SYSTEM_ID = SRD.SYSTEM_ID" +
                "           AND SUA.USER_ID = ?)" +
                "   AND SRD.RES_PAR_ID LIKE ? + '.%'" +
                " ORDER BY SRD.RES_ID, SRD.RES_PAR_ID";
        list.add(sfUser.getUserId());
        list.add(resParId);
        sqlModel.setArgs(list);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    public SQLModel insertData(String menus,String i) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO SF_FAVORITES(" +
                "   SORT_NO, " +
                "   USER_ID," +
                "   SF_RES_ID," +
                "   CREATION_DATE," +
                "   CREATED_BY )" +
                " VALUES" +
                "  (?, ?, ?, GETDATE(), ?)";
        sqlArgs.add(i);
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(menus);
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    public SQLModel updateData() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE SF_FAVORITES"
                + " SET"
                + " SORT_NO = ?,"
                + " USER_ID = ?,"
                + " SF_RES_ID = ?,"
                + " CREATION_DATE = ?,"
                + " CREATED_BY = ?,"
                + " LAST_UPDATE_DATE = ?,"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " SYSTEMID = ?";

        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    public SQLModel getData() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SRD.SYSTEM_ID,SRD.RES_NAME,SRD.IS_POPUP,SRD.POPSCRIPT,SRD.RES_URL" +
                "  FROM SF_FAVORITES SF, SF_RES_DEFINE SRD" +
                " WHERE SRD.SYSTEM_ID = SF.SF_RES_ID" +
                "       AND SF.USER_ID=?" +
                "       ORDER BY SF.SORT_NO";
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    public SQLModel deleteData() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " SF_FAVORITES"
                + " WHERE"
                + " USER_ID = ?";

        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}