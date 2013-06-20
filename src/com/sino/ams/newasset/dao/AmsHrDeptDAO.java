package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.newasset.dto.AmsHrDeptDTO;
import com.sino.ams.newasset.model.AmsHrDeptModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsHrDeptDAO</p>
 * <p>Description:�����Զ����ɷ������AmsHrDeptDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsHrDeptDAO extends BaseDAO {


    /**
     * ���ܣ�MIS����(HR) AMS_MIS_DEPT ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHrDeptDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsHrDeptDAO(SfUserDTO userAccount, AmsHrDeptDTO dtoParameter,
                        Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsHrDeptDTO dtoPara = (AmsHrDeptDTO) dtoParameter;
        super.sqlProducer = new AmsHrDeptModel((SfUserDTO) userAccount, dtoPara);
    }
}
