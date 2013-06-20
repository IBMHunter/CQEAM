package com.sino.ams.task.service.soa.mis.write;

import com.sino.ams.task.dao.soa.mis.AssetsChangeSearchDAO;
import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.soa.mis.srv.assetsinfoupdate.dao.AssetsUpdateDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA����EAMϵͳ�������ʲ��䶯ͬ����MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsDataWriteService extends AbstractTaskSOAService {

    /**
     * <p>����˵������EAMϵͳ�������ʲ��䶯ͬ����MISϵͳ   </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��EAMϵͳ�������ʲ��䶯ͬ����MISϵͳ����ʱ�����ݴ����쳣
     */
    public void writeAssetsChange() throws DataHandleException {
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
                    String[] assetIds = getOU2SynchronizeAssets(conn, organizationId);
                    if (assetIds == null || assetIds.length == 0) {
                        continue;
                    }
                    AssetsUpdateDAO assetsUpdateDAO = new AssetsUpdateDAO(taskExecutor, null, conn);
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
     * @throws QueryException ��ѯ���ݳ���ʱ�׳����쳣
     */
    private String[] getOU2SynchronizeAssets(Connection conn, int organizationId) throws QueryException {
        String[] assetsIds = null;
        try {
            AssetsChangeSearchDAO assetsChangeDAO = new AssetsChangeSearchDAO(taskExecutor, null, conn);
            RowSet rows = assetsChangeDAO.getOU2SynchronizeAssets(organizationId);
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
