package com.sino.ams.match.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.ams.match.dto.EtsItemMatchRecDTO;
import com.sino.ams.match.model.EtsItemMatchRecModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsItemMatchRecDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemMatchRecDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class EtsItemMatchRecDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�ETS_ITEM_MATCH_REC ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchRecDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsItemMatchRecDAO(SfUserDTO userAccount, EtsItemMatchRecDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsItemMatchRecDTO dtoPara = (EtsItemMatchRecDTO) dtoParameter;
        super.sqlProducer = new EtsItemMatchRecModel((SfUserDTO) userAccount, dtoPara);
    }

    //--------------------------------------------------------------------------------------------------
    //���������¼
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
            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
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
            insertIntoEIMRL(dto);
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
        EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRModel(dto);
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_REC
    public void updateEIMR(EtsItemMatchRecDTO dto) throws DataHandleException {
        EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateEIMRModel(dto);
        DBOperator.updateRecord(sqlModel, conn);
    }

    //����ETS_ITEM_MATCH_REC_LOG
    public void insertIntoEIMRL(EtsItemMatchRecDTO dto) throws DataHandleException {
        EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRLModel(dto);
        DBOperator.updateRecord(sqlModel, conn);
    }

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
            //�ĵ�˵��:
//               1��	��ѡ�е��豸���Ա��Ϊԭ�豸���
//               2��	ɾ��ets_item_match�����Ӧ��¼
//               3��	��¼ƥ���¼����ETS_ITEM_MATCH_REC��id��ƥ��ʱ�䡢ƥ���ˡ�system_id��asset_id,ƥ�䷽ʽ��ԭ�豸������豸���
//               4��	��¼ƥ���¼����ETS_ITEM_MATCH_REC_LOG��id��ƥ��ʱ�䡢ƥ���ˡ�system_id��asset_id,ƥ�䷽ʽ��ԭ�豸������豸���
//               5��	ETS_ITEM_MATCH_COMPLET.CURRENT_UNITS-1
//               6��	����ETS_FA_ASSETS��Ϣ
//               7��	����FA_ADDITIONS_B@MIS_FA�����Ϣ
    public void delDTO(EtsItemMatchRecDTO dto) throws DataHandleException {
        boolean hasError = true;
        boolean autoCommit = false;
        String tagNumber = "";
        String misTagNumber = "";
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;

            //���� ETS_ITEM_INFO.FINANCE_PROP
            SQLModel sqlModel = modelProducer.updateFinanceProp(dto);
            DBOperator.updateRecord(sqlModel, conn);

           //����ETS_ITEM_MATCH_COMPLET
            sqlModel = modelProducer.updateeimcModel(dto);
            DBOperator.updateRecord(sqlModel, conn);

            //ɾ��ets_item_match�����Ӧ��¼
            sqlModel = modelProducer.delFromMatchTbl(dto);
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
            insertIntoEIMRL(dto);
            //����ETS_FA_ASSETS��Ϣ
            sqlModel = modelProducer.getTagNumber(dto.getAssetId());
            sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            tagNumber = sq.getFirstRow().getStrValue("TAG_NUMBER");
            misTagNumber = sq.getFirstRow().getStrValue("MIS_TAG_NUMBER");

            sqlModel = modelProducer.updateFAInfo(dto.getAssetId(), tagNumber, misTagNumber);
            DBOperator.updateRecord(sqlModel, conn);

            //����MIS��Ϣ
//            sqlModel = modelProducer.updateFA_MIS(tagNumber);
//            DBOperator.updateRecord(sqlModel,conn);
            conn.commit();
            hasError = false;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (QueryException e) {
            Logger.logError(e);
        } catch (ContainerException e) {
            Logger.logError(e);
        }
        finally {
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
     * @param systemIds
     */
    public void confirmRentAssets(String[] systemIds) throws DataHandleException {
        if ((systemIds != null) && (systemIds.length > 0)) {
            String inSqlStr = ArrUtil.arrToSqlStr(systemIds);

            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
            SQLModel sqlModel = modelProducer.updateRentInfoModel(inSqlStr);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    /**
     * ȷ�ϴ����ʲ���
     * ����ets_tem_info FINANCE_PROP="OTHERS" ,ATTRIBUTE1="DG"
     * @param systemIds
     * @throws DataHandleException
     */
    public void confirmDGAssets(String[] systemIds) throws DataHandleException {
        if ((systemIds != null) && (systemIds.length > 0)) {
            String inSqlStr = ArrUtil.arrToSqlStr(systemIds);

            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
            SQLModel sqlModel = modelProducer.updateDGModel(inSqlStr);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    /**
     * ȷ�ϵ�ֵ�׺��ʲ���
     * ����ets_tem_info FINANCE_PROP="OTHERS" ,ATTRIBUTE1="DG"
     * @param systemIds
     * @throws DataHandleException
     */
    public void confirmLCAssets(String[] systemIds) throws DataHandleException {
        if ((systemIds != null) && (systemIds.length > 0)) {
            String inSqlStr = ArrUtil.arrToSqlStr(systemIds);

            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
            SQLModel sqlModel = modelProducer.updateLCModel(inSqlStr);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    /**
     * ȷ�ϵ�ֵ�׺��ʲ���
     * ����ets_tem_info FINANCE_PROP="OTHERS" ,ATTRIBUTE1="DG"
     * @param systemIds
     * @throws DataHandleException
     */
    public void confirmCTAssets(String[] systemIds) throws DataHandleException {
        if ((systemIds != null) && (systemIds.length > 0)) {
            String inSqlStr = ArrUtil.arrToSqlStr(systemIds);

            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
            SQLModel sqlModel = modelProducer.updateCTModel(inSqlStr);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }
    
    
    public boolean getDistributePrj(String prjId, DTOSet dtos) throws DataHandleException, SQLException {
        boolean operateResult = false;
        if (dtos != null && dtos.getSize() > 0) {
            operateResult = true;
            int dtoCount = dtos.getSize();
            EtsItemMatchRecModel modelProducer = (EtsItemMatchRecModel) sqlProducer;
            try{
            	String params = "(";
            	for (int i = 0; i < dtoCount; i++) {
            		EtsItemMatchRecDTO dto = (EtsItemMatchRecDTO) dtos.getDTO(i);
            		params += "'" + dto.getSystemId() + "',";
            	}
            	params = params.substring(0, params.length()-1) + ")";
            	SQLModel sqlModel = modelProducer.getUpdateDistributePrj(prjId, params);
            	DBOperator.updateRecord(sqlModel, conn);
            	conn.commit();
            }catch(Exception e){
            	e.printStackTrace();
            	conn.rollback();
            }
            if(conn != null){
            	conn.close();
            }
        }
        return operateResult;
    }
}