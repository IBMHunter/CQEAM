package com.sino.ams.task.service.odi;


import com.sino.ams.task.service.AbstractTaskService;
import com.sino.ams.task.util.BackGroundTaskUtil;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.soa.util.SynUpdateDateUtils;

import java.sql.Connection;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ODI��̨����ĳ���</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class AbstractTaskODIService extends AbstractTaskService {

    protected String odiServiceName = "";

    public AbstractTaskODIService() {
        super();
        initODIServiceName();
    }

    /**
     * <p>����˵������ʼ��ODI������ </p>
     */
    protected abstract void initODIServiceName();

    /**
     * <p>����˵������ȡODI����Ĵ���  </p>
     *
     * @param conn ���ݿ�����
     * @return �ɹ��򷵻�ODI����Ĵ��룬���򷵻ؿ��ַ���
     * @throws QueryException ��ѯODI����������ʱ�׳��ò�ѯ�쳣
     */
    protected String getODIEnvCode(Connection conn) throws QueryException {
        String envCode = "";
        try {
            if (!StrUtil.isEmpty(odiServiceName)) {
                envCode = SynUpdateDateUtils.getEnvCode(odiServiceName, conn);
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return envCode;
    }
}
