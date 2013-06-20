package com.sino.ams.match.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.match.dto.EtsItemMatchRecDTO;
import com.sino.ams.match.model.AmsRentAssetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2008-6-10
 * Time: 16:01:30
 * To change this template use File | Settings | File Templates.
 */
public class AmsRentAssetDAO extends BaseDAO {

    private SfUserDTO sfUser = null;


    public AmsRentAssetDAO(SfUserDTO userAccount, EtsItemMatchRecDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsItemMatchRecDTO dtoPara = (EtsItemMatchRecDTO) dtoParameter;
        super.sqlProducer = new AmsRentAssetModel((SfUserDTO) userAccount, dtoPara);
    }

    public void saveDTOs(DTOSet dtos) throws DataHandleException {
        if (dtos != null && dtos.getSize() > 0) {
            int dtoCount = dtos.getSize();
            for (int i = 0; i < dtoCount; i++) {
                EtsItemMatchRecDTO dto = (EtsItemMatchRecDTO) dtos.getDTO(i);
                saveDTO(dto);
            }
        }
    }

    public void saveDTO(EtsItemMatchRecDTO dto) throws DataHandleException {
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
            //���� ETS_ITEM_INFO.FINANCE_PROP
            SQLModel sqlModel = modelProducer.updateFinanceProp(dto);
            DBOperator.updateRecord(sqlModel, conn);
            //��ѯETS_ITEM_MATCH_REC���Ƿ���ڸ��豸�ļ�¼
            sqlModel = modelProducer.getHasBeenModel(dto);
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            //���û�в���,����и���.
            if (!sq.hasResult()) {
                insertIntoEIMR(dto);
            } else {
                updateEIMR(dto);
            }
            //����ETS_ITEM_MATCH_REC_LOG
            conn.commit();
            hasError = false;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (QueryException e) {
            Logger.logError(e);
        } finally {
            try {
                if (hasError) {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            }
            catch (SQLException e) {
                Logger.logError(e);
            }
        }

    }

    //����ETS_ITEM_MATCH_REC
    public void insertIntoEIMR(EtsItemMatchRecDTO dto) throws DataHandleException {
        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRModel(dto);
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_REC
    public void updateEIMR(EtsItemMatchRecDTO dto) throws DataHandleException {
        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateEIMRModel(dto);
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_REC_LOG

    //--------------------------------------------------------------------------------------------------
    //ɾ��������¼

    public void delDTOs(DTOSet dtos) throws DataHandleException {
        if (dtos != null && dtos.getSize() > 0) {
            int dtoCount = dtos.getSize();
            for (int i = 0; i < dtoCount; i++) {
                EtsItemMatchRecDTO dto = (EtsItemMatchRecDTO) dtos.getDTO(i);
                delDTO(dto);
            }
        }
    }

    // ɾ���������
    public void delDTO(EtsItemMatchRecDTO dto) throws DataHandleException {
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
            //�ĵ�˵��:
//               1��	��ѡ�е��豸���Ա��Ϊԭ�豸���
//               2��	ɾ��ets_item_match�����Ӧ��¼
//               3��	��¼ƥ���¼����ETS_ITEM_MATCH_REC��id��ƥ��ʱ�䡢ƥ���ˡ�system_id��asset_id,ƥ�䷽ʽ��ԭ�豸������豸���
//               4��	��¼ƥ���¼����ETS_ITEM_MATCH_REC_LOG��id��ƥ��ʱ�䡢ƥ���ˡ�system_id��asset_id,ƥ�䷽ʽ��ԭ�豸������豸���
//               5��	ETS_ITEM_MATCH_COMPLET.CURRENT_UNITS-1

            //���� ETS_ITEM_INFO.FINANCE_PROP
            SQLModel sqlModel = modelProducer.updateFinanceProp(dto);
            DBOperator.updateRecord(sqlModel, conn);
            //ɾ��ets_item_match�����Ӧ��¼
            //��ѯETS_ITEM_MATCH_REC���Ƿ���ڸ��豸�ļ�¼
            sqlModel = modelProducer.getHasBeenModel(dto);
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            //���û�в���,����и���.
            if (!sq.hasResult()) {
                insertIntoEIMR(dto);
            } else {
                updateEIMR(dto);
            }
            //����ETS_ITEM_MATCH_REC_LOG
            conn.commit();
            hasError = false;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (QueryException e) {
            Logger.logError(e);
        } finally {
            try {
                if (hasError) {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            }
            catch (SQLException e) {
                Logger.logError(e);
            }
        }

    }

    /**
     * ȷ�������ʲ���
     * ����ets_tem_info FINANCE_PROP="OTHERS" ,ATTRIBUTE1="RENT"
     *
     * @param systemIds
     */
    public void confirmRentAssets(String[] systemIds, String matchType, String[] startDate,String []barcodes) throws DataHandleException , SQLModelException, CalendarException {
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            if ((systemIds != null) && (systemIds.length > 0)) {

                String inSqlStr = ArrUtil.arrToSqlStr(systemIds);
                if (matchType.equals("11")) {
                    for (int i = 0; i < systemIds.length; i++) {
                           String systemId[]=systemIds[i].split(";");
                        int j=Integer.parseInt(systemId[1]);
                        
                        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
                        SQLModel sqlModel = modelProducer.updateRentInfoModel(systemId[0],startDate[j]);
                        DBOperator.updateRecord(sqlModel, conn);

                        SQLModel sm= modelProducer.insertRentInfoModel(barcodes[j]);
                        DBOperator.updateRecord(sm, conn);
                    }

                    insertIntolog(systemIds);
                    insertIntoReclog(systemIds);
                } else {


                      for (int i = 0; i < systemIds.length; i++) {
                            String systemId[]=systemIds[i].split(";");
                        int j=Integer.parseInt(systemId[1]);

                           AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
                    SQLModel sqlModel = modelProducer.cancleRentInfoModel(systemId[0]);
                    DBOperator.updateRecord(sqlModel, conn);
                           SQLModel sm= modelProducer.deleteRentInfoModel(barcodes[j]);
                        DBOperator.updateRecord(sm, conn);
                     }
                    insertIntoReclogs(systemIds);
                    deletelog(systemIds);
                }


            }
            conn.commit();
            hasError = false;
        } catch (SQLException e) {
            Logger.logError(e);
        } finally {
            try {
                if (hasError) {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            }
            catch (SQLException e) {
                Logger.logError(e);
            }
        }
    }

    public void insertIntolog(String[] systemIds) throws DataHandleException {
        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
//        String[] systemIds = inSqlStr.split(",");
        for (int i = 0; i < systemIds.length; i++) {
             String systemId[]=systemIds[i].split(";");
            SQLModel sqlModel = modelProducer.insertIntolog(systemId[0], "10");
            DBOperator.updateRecord(sqlModel, conn);

        }

    }

    //����ETS_ITEM_MATCH_REC_LOG
    public void insertIntoReclog(String[] systemIds) throws DataHandleException {
        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
//        String[] systemIds = systemId.split(",");
        for (int i = 0; i < systemIds.length; i++) {
             String systemId[]=systemIds[i].split(";");
            SQLModel sqlModel = modelProducer.insertIntoRecLog(systemId[1]);
            DBOperator.updateRecord(sqlModel, conn);
        }

    }

    public void insertIntoReclogs(String[] systemIds) throws DataHandleException {
        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
//        String[] systemIds = systemId.split(",");
        for (int i = 0; i < systemIds.length; i++) {
             String systemId[]=systemIds[i].split(";");
            SQLModel sqlModel = modelProducer.insertIntoRecLogs(systemId[0]);
            DBOperator.updateRecord(sqlModel, conn);
        }

    }

    public void deletelog(String[] systemIds) throws DataHandleException {
        AmsRentAssetModel modelProducer = (AmsRentAssetModel) sqlProducer;
//        String[] systemIds = systemId.split(",");
        for (int i = 0; i < systemIds.length; i++) {
             String systemId[]=systemIds[i].split(";");
            SQLModel sqlModel = modelProducer.deletelog(systemId[0]);
            DBOperator.updateRecord(sqlModel, conn);
        }

    }

}
