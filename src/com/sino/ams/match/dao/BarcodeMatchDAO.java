package com.sino.ams.match.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.dto.BarcodeMatchDTO;
import com.sino.ams.match.model.BarcodeMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-11-29
 * Time: 9:36:46
 * To change this template use File | Settings | File Templates.
 */
public class BarcodeMatchDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ�ƥ��-ƥ�����ݴ洢(EAM) ETS_ITEM_MATCH ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public BarcodeMatchDAO(SfUserDTO userAccount, BarcodeMatchDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        BarcodeMatchDTO dtoPara = (BarcodeMatchDTO) dtoParameter;
        super.sqlProducer = new BarcodeMatchModel((SfUserDTO) userAccount, dtoPara);
    }

    public void deleteData() throws DataHandleException {
        super.deleteData();
    }

    /**
     * ���ܣ���ѡ�е����ݽ���ƥ��
     * @param checkedAssets DTOSet
     * @return String
     */
    public String matchCheckedAssets(DTOSet checkedAssets) {
        String ret = "Y";
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
            SQLModel sqlModel = null;
            SimpleQuery sq = null;
            int checkedCount = checkedAssets.getSize();
            for (int i = 0; i < checkedCount; i++) {
                BarcodeMatchDTO bardto = (BarcodeMatchDTO) checkedAssets.getDTO(i);
                setDTOParameter(bardto);
                super.createData(); //дETS_ITEM_MATCH��
                super.updateData(); //���±�ETS_ITEM_INFO    FINAMCE_PROP='ASSETS'  LAST_UPDATE_DATE='GETDATE()'  LAST_UPDATE_BY=��ǰ�û�
                sqlModel = modelProducer.getHasBeenModel();
                if (sq == null) {
                    sq = new SimpleQuery(sqlModel, conn);
                } else {
                    sq.setSql(sqlModel);
                }
                sq.executeQuery();
                if (!sq.hasResult()) { // �ж� �� ETS_ITEM_MATCH_REC 1���о��޸Ĳ��� 2��û�оͲ������
                    insertIntoEIMR();
                } else {
                    updateEIMR();
                }
                insertIntoEIMRL(); //����� ETS_ITEM_MATCH_REC_LOG
                updateEIMC(); //���±�ETS_ITEM_MATCH_COMPLET.CURRENT_UNITS+1
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
            } catch (SQLException e) {
                Logger.logError(e);
            }
        }
        return ret;
    }

    public String doMatch(String[] systemids) {
        String ret = "Y";
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
            SQLModel sqlModel = null;
            SimpleQuery sq = null;
            for (int i = 0; i < systemids.length; i++) {
                BarcodeMatchDTO bardto = (BarcodeMatchDTO) dtoParameter;
                String syssa[] = systemids[i].split("@@@");
                bardto.setSystemid(syssa[0]);
                bardto.setAssetId(StrUtil.strToInt(syssa[1]));
                setDTOParameter(bardto);
                super.createData(); //дETS_ITEM_MATCH��
                super.updateData(); //���±�ETS_ITEM_INFO    FINAMCE_PROP='ASSETS'  LAST_UPDATE_DATE='GETDATE()'  LAST_UPDATE_BY=��ǰ�û�
                boolean hasMatchRecord=hasMatchRec();
                if (hasMatchRecord) { // �ж� �� ETS_ITEM_MATCH_REC 1����:ɾ������������ 2��û�оͲ������
                    deleteEIMR();
                    insertIntoEIMR();
                } else {
                    insertIntoEIMR();
                }
                insertIntoEIMRL(); //����� ETS_ITEM_MATCH_REC_LOG
                updateEIMC(); //���±�ETS_ITEM_MATCH_COMPLET.CURRENT_UNITS+1
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
            } catch (SQLException e) {
                Logger.logError(e);
            }
        }
        return ret;
    }

    /**
     * ���ܣ�����ETS_ITEM_MATCH_REC
     * @throws DataHandleException
     */
    public void insertIntoEIMR() throws DataHandleException {
        BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ����ETS_ITEM_MATCH_REC
     * @throws DataHandleException
     */
    public void updateEIMR() throws DataHandleException {
        BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateEIMRModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ɾ��ETS_ITEM_MATCH_REC
     * @throws DataHandleException
     */
    public void deleteEIMR() throws DataHandleException {
        BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.deleteEIMRModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ����Ƿ���ƥ���¼
     * @return
     * @throws QueryException
     */
    public boolean hasMatchRec() throws QueryException {
        BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getHasBeenModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }


    /**
     * ����ETS_ITEM_MATCH_REC_LOG
     * @throws DataHandleException
     */
    public void insertIntoEIMRL() throws DataHandleException {
        BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.insertIntoEIMRLModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ����ETS_ITEM_MATCH_COMPLET
     * @throws DataHandleException
     */
    public void updateEIMC() throws DataHandleException {
        BarcodeMatchModel modelProducer = (BarcodeMatchModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateEIMCModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�����Excel�ļ���
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "����ƥ����Ϣ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            rule.setCalPattern(LINE_PATTERN);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE", "EAM����");
            fieldMap.put("FA_BARCODE", "MIS�ʲ�����");
            fieldMap.put("ITEM_DESCRIPTION", "EAM�豸����");
            fieldMap.put("ASSETS_DESCRIPTION", "MIS�豸����");
            fieldMap.put("SPEC", "EAM����ͺ�");
            fieldMap.put("MIS_SPEC", "MIS����ͺ�");
            fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
            fieldMap.put("ETS_LOCATION", "EAM�ص�");
            fieldMap.put("ASSETS_LOCATION_CODE", "MIS�ص����");
            fieldMap.put("MIS_LOCATION", "MIS�ص�");
            fieldMap.put("USER_NAME", "EAM������");
            fieldMap.put("ASSIGNED_TO_NAME", "MIS������");
            fieldMap.put("CURRENT_UNITS", "MIS����");
            fieldMap.put("FA_CODE", "MISĿ¼����");
            fieldMap.put("FA_CATEGORY2", "MISĿ¼����");
            fieldMap.put("CONTENT_CODE", "EAMĿ¼����");
            fieldMap.put("CONTENT_NAME", "EAMĿ¼����");
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("����ƥ����Ϣ��");
            custData.setReportPerson(userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            TransferFactory factory = new TransferFactory();
            DataTransfer transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }
}
