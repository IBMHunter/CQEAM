package com.sino.ams.match.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.dto.HideMisAssetDTO;
import com.sino.ams.match.model.HideMisAssetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-12-5
 * Time: 10:55:09
 * To change this template use File | Settings | File Templates.
 */
public class HideMisAssetDAO extends AMSBaseDAO {
    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public HideMisAssetDAO(SfUserDTO userAccount, HideMisAssetDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        HideMisAssetDTO dtoPara = (HideMisAssetDTO) dtoParameter;
        super.sqlProducer = new HideMisAssetModel((SfUserDTO) userAccount, dtoPara);
    }

    public void deleteData() throws DataHandleException {
        super.deleteData();
    }


    public String doHide(String[] assetIds, String reMark) {
        String ret = "Y";
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            HideMisAssetModel modelProducer = (HideMisAssetModel) sqlProducer;
            SQLModel sqlModel;
            sqlModel = modelProducer.getHasBeenModel(assetIds);
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (!sq.hasResult()) { // �ж� �� ets_item_match_assist_mis
                for (int i = 0; i < assetIds.length; i++) {     //�������β���
                    HideMisAssetDTO hudedto = (HideMisAssetDTO) dtoParameter;
                    hudedto.setAssetId(StrUtil.strToInt(assetIds[i]));
                    hudedto.setReMark(reMark);
                    super.createData();
                }
            } else {               //��ѡ�豸�д����Ѿ��������û����ε��豸
                ret = "N";
            }
            conn.commit();
            hasError = false;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            e.printStackTrace();
        } catch (QueryException e) {
            e.printStackTrace();
        } finally {
            try {
                if (hasError) {
                    ret = "N";
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            }
            catch (SQLException e) {
                Logger.logError(e);
            }
        }
//        }
        return ret;
    }

    //����ETS_ITEM_MATCH_REC
    public void insertIntoEIMR() throws DataHandleException {
        HideMisAssetModel modelProducer = (HideMisAssetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_REC
    public void updateEIMR() throws DataHandleException {
        HideMisAssetModel modelProducer = (HideMisAssetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateEIMRModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_REC_LOG
    public void insertIntoEIMRL() throws DataHandleException {
        HideMisAssetModel modelProducer = (HideMisAssetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRLModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_COMPLET
    public void updateEIMC() throws DataHandleException {
        HideMisAssetModel modelProducer = (HideMisAssetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateEIMCModel();
        DBOperator.updateRecord(sqlModel, conn);
    }
}
