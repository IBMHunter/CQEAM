package com.sino.ams.task.service.odi.mis;

import com.sino.ams.task.service.odi.AbstractTaskODIService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.common.SrvType;
import com.sino.soa.mis.srv.assetretire.srv.TransRetiredAssetDetailSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��ODI�����MISϵͳ��ȡ�����ʲ��嵥��EAMϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class RetireODIRequestService extends AbstractTaskODIService {


    @Override
    protected void initODIServiceName() {
        odiServiceName = "TransRetiredAssetDetailSrv";
    }

    /**
     * <p>����˵������MISϵͳ��ȡ�����ʲ��嵥��EAMϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��ȡ�����ʲ��嵥��EAMϵͳ����ʱ�����ݴ����쳣
     */
    public void requestODI2ProcessRetire() throws DataHandleException {
        Connection conn = null;
        SynLogUtil logUtil = new SynLogUtil();
        try {

            conn = getDBConnection();
            initTaskExecutor(conn);
            initODIServiceName();
            logSynStart(conn, logUtil);
            truncateData("ZTE_FA_RETIREMENT_BASIC_INFO",conn);
            RowSet rows = getMISBookTypeCodes(conn);
            boolean hasError = false;
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();
                TransRetiredAssetDetailSrv srv = new TransRetiredAssetDetailSrv();
                String envCode = getODIEnvCode(conn);
                srv.setEnvCode(envCode);
                String lastUpdateDate = getLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                srv.setStartRetireDate(lastUpdateDate);
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    String bookTypeCode = row.getStrValue("BOOK_TYPE_CODE");
                    srv.setBookTypeCode(bookTypeCode);
                    try {
                        logSynEnd(conn, logUtil, bookTypeCode);
                        srv.excute();
                    } catch (Throwable ex) {
                        hasError = true;
                        logSynError(conn, logUtil, bookTypeCode);
                    }
                }
                if(!hasError){//��¼��־��������������ݶ�ȡ��©
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_RETIRE, conn);
                }
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
     * @throws DataHandleException ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynStart(Connection conn, SynLogUtil logUtil) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_FA_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("�����ʲ�ͬ����ʼ��");
        logUtil.synLog(logDTO, conn);
    }

    /**
     * <p>����˵������¼ͬ��������־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @param bookTypeCode �ʲ��˲�����
     * @throws DataHandleException ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynEnd(Connection conn, SynLogUtil logUtil, String bookTypeCode) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_FA_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("ͬ��MIS�����ʲ��ɹ����ʲ��˲���" + bookTypeCode);
        logUtil.synLog(logDTO, conn);
    }

    /**
     * <p>����˵������¼ͬ��������־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @param bookTypeCode �ʲ��˲�����
     * @throws DataHandleException ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynError(Connection conn, SynLogUtil logUtil, String bookTypeCode) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_FA_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("ͬ�������ʲ�ʧ�ܣ��ʲ��˲���" + bookTypeCode);
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
