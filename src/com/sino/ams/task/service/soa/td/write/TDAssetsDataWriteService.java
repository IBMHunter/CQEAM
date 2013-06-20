package com.sino.ams.task.service.soa.td.write;

import com.sino.ams.task.dao.soa.td.TDAssetsChangeSearchDAO;
import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.soa.td.srv.assetsinfoupdate.dao.TDAssetsUpdateDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA����EAMϵͳ�������ʲ��䶯ͬ����TDϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDAssetsDataWriteService extends AbstractTaskSOAService {

    /**
     * <p>����˵������EAMϵͳ�������ʲ��䶯ͬ����TDϵͳ  </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��EAMϵͳ�������ʲ��䶯ͬ����TDϵͳ����ʱ�����ݴ����쳣
     */
    public void writeTDAssetsChange() throws DataHandleException {
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
                    String[] assetIds = getOU2SynchronizeAssets(conn, organizationId);
                    if (assetIds == null || assetIds.length == 0) {
                        continue;
                    }
                    TDAssetsUpdateDAO assetsUpdateDAO = new TDAssetsUpdateDAO(taskExecutor, null, conn);
                    assetsUpdateDAO.syschronizeAssets(organizationId, assetIds);
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
     * <p>����˵������ȡָ��OU��ͬ�����ʲ�ID����
     *
     * @param conn           ���ݿ�����
     * @param organizationId OU��֯ID
     * @return ��ͬ�����ʲ�ID����
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ���ݳ���ʱ�׳����쳣
     */
    private String[] getOU2SynchronizeAssets(Connection conn, int organizationId) throws QueryException {
        String[] assetsIds = null;
        try {
            TDAssetsChangeSearchDAO assetsChangeDAO = new TDAssetsChangeSearchDAO(taskExecutor, null, conn);
            RowSet rows = assetsChangeDAO.getTDOU2SynchronizeAssets(organizationId);
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();
                assetsIds = new String[dataCount];
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    assetsIds[i] = row.getStrValue("ASSET_ID");
                }
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return assetsIds;
    }
}
