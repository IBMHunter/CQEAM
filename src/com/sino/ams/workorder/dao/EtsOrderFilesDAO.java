                            package com.sino.ams.workorder.dao;

import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsOrderFilesDTO;
import com.sino.ams.workorder.model.EtsOrderFilesModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsOrderFilesDAO</p>
 * <p>Description:�����Զ����ɷ������EtsOrderFilesDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */
public class EtsOrderFilesDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ������ļ�����(EAM) ETS_ORDER_FILES ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsOrderFilesDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsOrderFilesDAO(SfUserDTO userAccount, EtsOrderFilesDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsOrderFilesDTO dtoPara = (EtsOrderFilesDTO) dtoParameter;
        super.sqlProducer = new EtsOrderFilesModel((SfUserDTO) userAccount, dtoPara);
    }
}