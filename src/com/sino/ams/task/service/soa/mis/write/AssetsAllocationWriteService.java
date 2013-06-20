package com.sino.ams.task.service.soa.mis.write;

import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.SrvDAO;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA����EAMϵͳ�����Ĺ�˾���ʲ�����ͬ����MISϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsAllocationWriteService extends AbstractTaskSOAService {

    /**
     * <p>����˵������EAMϵͳ�����Ĺ�˾���ʲ�����ͬ����MISϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��EAMϵͳ�����Ĺ�˾���ʲ�����ͬ����MISϵͳ����ʱ�����ݴ����쳣
     */
    public void writeAssetsAllocations() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            RowSet rows = getCompanyList(conn);
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();
                SrvDAO srvDAO = new SrvDAO();
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    String orgId = row.getStrValue("ORGANIZATION_ID");
                    int organizationId = Integer.parseInt(orgId);
                    taskExecutor = getOUTaskExecutor(conn, organizationId);
                    if (taskExecutor == null) {
                        continue;
                    }
                    srvDAO.synTransInCompany(conn, taskExecutor);
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
