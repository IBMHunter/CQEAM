package com.sino.ams.task.service.odi.td;

import com.sino.ams.task.service.odi.AbstractTaskODIService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.common.SrvType;
import com.sino.soa.td.srv.assetretire.srv.TDTransRetiredAssetDetailSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��ODI�����TDϵͳ��ȡ�����ʲ��嵥��EAMϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDRetireODIRequestService extends AbstractTaskODIService {

    @Override
    protected void initODIServiceName() {
        odiServiceName = "TDTransRetiredAssetDetailSrv";
    }

    /**
     * <p>����˵������TDϵͳ��ȡ�����ʲ��嵥��EAMϵͳ</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��ȡ�����ʲ��嵥��EAMϵͳ����ʱ�����ݴ����쳣
     */
    public void requestODI2ProcessTDRetire() throws DataHandleException {
        Connection conn = null;
        SynLogUtil logUtil = new SynLogUtil();
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            initODIServiceName();
            logSynStart(conn, logUtil);
           truncateData("ZTE_TD_RETIREMENT_BASIC_INFO",conn);
            RowSet rows = getTDBookTypeCodes(conn);
            boolean hasError = false;
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();
                TDTransRetiredAssetDetailSrv srv = new TDTransRetiredAssetDetailSrv();
                String envCode = getODIEnvCode(conn);
                srv.setEnvCode(envCode);
                String lastUpdateDate = getLastUpdateDate(SrvType.SRV_TD_FA_RETIRE, conn);
                srv.setStartRetireDate(lastUpdateDate);
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    String bookTypeCode = row.getStrValue("BOOK_TYPE_CODE");
                    srv.setBookTypeCode(bookTypeCode);
                    try {
                        srv.excute();
                        logSynEnd(conn, logUtil, bookTypeCode);
                    } catch (Throwable ex) {
                        hasError = true;
                        logSynError(conn, logUtil, bookTypeCode);
                    }
                }
            }
            if (!hasError) {//��¼��־��������������ݶ�ȡ��©
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_RETIRE, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_RETIRE, conn);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }

    /**
     * <p>����˵������¼ͬ����ʼ��־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynStart(Connection conn, SynLogUtil logUtil) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_TD_FA_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("ͬ������TD�ʲ���ʼ��");
        logUtil.synLog(logDTO, conn);
    }

    /**
     * <p>����˵������¼ͬ��������־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @param bookTypeCode �ʲ��˲�����
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynEnd(Connection conn, SynLogUtil logUtil, String bookTypeCode) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_TD_FA_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("ͬ������TD�ʲ��ɹ����ʲ��˲���" + bookTypeCode);
        logUtil.synLog(logDTO, conn);
    }

    /**
     * <p>����˵������¼ͬ��������־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @param bookTypeCode �ʲ��˲�����
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynError(Connection conn, SynLogUtil logUtil, String bookTypeCode) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_TD_FA_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("ͬ������TD�ʲ�ʧ�ܣ��ʲ��˲���" + bookTypeCode);
        logUtil.synLog(logDTO, conn);
    }
    public void truncateData(String tableName,Connection conn) {
//                Connection conn = null;
               try {
//                    conn = DBManager.getDBConnection();
          SQLModel sqlModel = new SQLModel();
            String      sqlStr="TRUNCATE TABLE  "+tableName;
            sqlModel.setSqlStr(sqlStr);
		DBOperator.updateRecord(sqlModel, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }/*finally {
            DBManager.closeDBConnection(conn);
        }*/
           }
}
