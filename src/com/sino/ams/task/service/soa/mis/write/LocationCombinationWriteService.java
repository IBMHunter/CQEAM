package com.sino.ams.task.service.soa.mis.write;

import com.sino.ams.task.dao.soa.LocationCombinationSearchDAO;
import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.soa.mis.srv.assetLocComb.dao.SrvAssetLocCombDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA���񽫽�EAMϵͳ�����ĵص�䶯ͬ����MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class LocationCombinationWriteService extends AbstractTaskSOAService {

    /**
     * <p>����˵������EAMϵͳ�����ĵص�䶯ͬ����MISϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��EAMϵͳ�����ĵص�䶯ͬ����MISϵͳ����ʱ�����ݴ����쳣
     */
    public void writeLocationChanges() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            RowSet rows = getCompanyList(conn);
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
                    String[] objectNos = getOU2SynchronizeLocations(conn, organizationId);
                    if (objectNos == null || objectNos.length == 0) {
                        continue;
                    }
                    SrvAssetLocCombDAO assetsUpdateDAO = new SrvAssetLocCombDAO(taskExecutor, null, conn);
                    assetsUpdateDAO.synLocComb(ArrUtil.arrToSqlStr(objectNos));
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
     * <p>����˵������ȡָ��OU��ͬ���ĵص���ϵ���������</p>
     *
     * @param conn           ���ݿ�����
     * @param organizationId OU��֯ID
     * @return ��ͬ�����ʲ�ID����
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ���ݳ���ʱ�׳����쳣
     */
    private String[] getOU2SynchronizeLocations(Connection conn, int organizationId) throws QueryException {
        String[] objectNos = null;
        try {
            LocationCombinationSearchDAO searchDAO = new LocationCombinationSearchDAO(taskExecutor, null, conn);
            RowSet rows = searchDAO.getOU2SynchronizeLocations(organizationId);
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();
                objectNos = new String[dataCount];
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    objectNos[i] = row.getStrValue("WORKORDER_OBJECT_NO");
                }
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return objectNos;
    }
}
