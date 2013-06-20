package com.sino.ams.task.service.soa.td.write;

import com.sino.ams.task.dao.soa.td.TDLocationSegment2SearchDAO;
import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.soa.mis.srv.assetLoc2Comb.dao.SrvAssetLoc2CombDAO;
import com.sino.soa.mis.srv.assetLocComb.dao.SrvAssetLocCombDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA����EAMϵͳ�����ĵص�ڶ��α䶯ͬ����TDϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDLocationSegment2WriteService extends AbstractTaskSOAService {

    /**
     * <p>����˵������EAMϵͳ�����ĵص�ڶ��α䶯ͬ����TDϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��EAMϵͳ�����ĵص�ڶ��α䶯ͬ����TDϵͳ����ʱ�����ݴ����쳣
     */
    public void writeTDLocationSegment2() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            RowSet rows = getTDCompanyList(conn);
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();

                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    String orgId = row.getStrValue("ORGANIZATION_ID");
                    int organizationId = Integer.parseInt(orgId);
                    taskExecutor = getOUTaskExecutor(conn, organizationId);
                    if (taskExecutor == null) {
                        continue;
                    }
                    String[] objectNos = getOU2SynchronizeLocationSegment2(conn, organizationId);
                    if (objectNos == null || objectNos.length == 0) {
                        continue;
                    }
                    SrvAssetLoc2CombDAO assetsUpdateDAO = new SrvAssetLoc2CombDAO(taskExecutor, null, conn);
                    assetsUpdateDAO.synLocComb(objectNos, "Y");
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
     * <p>����˵������ȡָ��OU��ͬ��������ص�����</p>
     *
     * @param conn           ���ݿ�����
     * @param organizationId OU��֯ID
     * @return ��ͬ�����ʲ�ID����
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ���ݳ���ʱ�׳����쳣
     */
    private String[] getOU2SynchronizeLocationSegment2(Connection conn, int organizationId) throws QueryException {
        String[] objects = null;
        try {
            TDLocationSegment2SearchDAO searchDAO = new TDLocationSegment2SearchDAO(taskExecutor, null, conn);
            RowSet rows = searchDAO.getOU2SynchronizeLocationSegment2(organizationId);
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();
                objects = new String[dataCount];
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    objects[i] = row.getStrValue("LOC2_CODE");
                    objects[i] += ",";
                    objects[i] += row.getStrValue("LOC2_DESC");
                }
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return objects;
    }
}

