package com.sino.ams.synchronize.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.AmsSynTmpDTO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.AssetsChangeSynModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ:�ʲ��䶯ֱ��ͬ�������ڵ��й�˾ͬ�����β��š��ص�ı��</p>
 * <p>Ŀǰɽ��ʹ�ñ�����</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2007~2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsChangeSynDAO extends AMSBaseDAO {

    /**
     * ���ܣ�eAM�����ص�ͬ�� ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AssetsChangeSynDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EamSyschronizeDTO dtoPara = (EamSyschronizeDTO) dtoParameter;
        super.sqlProducer = new AssetsChangeSynModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��ʲ��䶯ֱ��ͬ��
     * <B>�÷�������������ƣ�������Ҫ������Ƶķ������ܵ��ñ�����</B>
     * @param orgnizationId String
     * @param assetIds String[]
     */
    public synchronized void syschronizeAssets(int orgnizationId, String[] assetIds) {
        boolean operateResult = false;
        boolean autoCommit = true;
        CallableStatement cs = null;
        try {
            int assetsCount = assetIds.length;
            String sourceStr = ArrUtil.arrToSqlStr(assetIds);
            AmsSynTmpDTO assetsDTO = new AmsSynTmpDTO();
            assetsDTO.setSourceStr(sourceStr);
            String targetStr = "";
            AmsSynTmpDAO synTmpDAO = new AmsSynTmpDAO(userAccount, null, conn);
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            for (int i = 0; i < assetsCount; i++) {
                targetStr = assetIds[i];
                assetsDTO.setTargetStr(targetStr);
                synTmpDAO.setDTOParameter(assetsDTO);
                synTmpDAO.createData();
            }
            String callStr = "{CALL AMS_SYN_PKG.SYN_ASSET_CHANGE_IMMEDIATE(?, ?)}";
            cs = conn.prepareCall(callStr);
            cs.setInt(1, orgnizationId);
            cs.setInt(2, userAccount.getUserId());
            cs.execute();
            synTmpDAO.deleteData();
            operateResult = true;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (DataHandleException ex) {
            ex.printLog();
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage("SYSC_SUCCESS");
                } else {
                    conn.rollback();
                    prodMessage("SYSC_FAILURE");
                    message.setIsError(true);
                }
                conn.setAutoCommit(autoCommit);
                DBManager.closeDBStatement(cs);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
    }

    public File getExportFile() throws DataTransException {
        AssetsChangeSynModel modelProducer = (AssetsChangeSynModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPageQueryModel();
        String reportTitle = "EAM�ʲ��䶯����";
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        rule.setFieldMap(getFieldMap());
        CustomTransData custData = new CustomTransData();
        custData.setReportTitle(reportTitle);
        custData.setReportPerson(userAccount.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("ASSET_NUMBER", "�ʲ����");
        fieldMap.put("BARCODE", "EAM����");
        fieldMap.put("TAG_NUMBER", "MIS����");
        fieldMap.put("COMPANY_NAME", "��˾����");
        fieldMap.put("ASSETS_DESCRIPTION", "MIS����");
        fieldMap.put("ITEM_NAME", "EAM����");

        fieldMap.put("MODEL_NUMBER", "MIS�ͺ�");
        fieldMap.put("ITEM_SPEC", "EAM�ͺ�");

        fieldMap.put("ASSETS_LOCATION", "MIS�ص�");
        fieldMap.put("LOCATION_CODE", "EAM�ص�");
        fieldMap.put("OLD_ASSIGNED_TO_NAME", "MIS������");
        fieldMap.put("NEW_USER_NAME", "EAM������");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "MIS��������");
        fieldMap.put("LIFE_IN_YEARS", "MIS�۾�����");

		return fieldMap;
	}
}
