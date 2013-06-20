package com.sino.ams.workorder.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderTmpDTO;
import com.sino.ams.workorder.model.EtsWorkorderTmpModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsWorkorderTmpDAO</p>
 * <p>Description:�����Զ����ɷ������EtsWorkorderTmpDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class EtsWorkorderTmpDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�������ʱ��(EAM) ETS_WORKORDER_TMP ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsWorkorderTmpDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsWorkorderTmpDAO(SfUserDTO userAccount, EtsWorkorderTmpDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsWorkorderTmpDTO dtoPara = (EtsWorkorderTmpDTO) dtoParameter;
        super.sqlProducer = new EtsWorkorderTmpModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ����빤����ʱ��(EAM)��ETS_WORKORDER_TMP�����ݡ�
     * @return boolean
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("������ʱ��(EAM)");
    }

    /**
     * ���ܣ����¹�����ʱ��(EAM)��ETS_WORKORDER_TMP�����ݡ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("������ʱ��(EAM)");
    }

    /**
     * ���ܣ�ɾ��������ʱ��(EAM)��ETS_WORKORDER_TMP�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("������ʱ��(EAM)");
    }

}