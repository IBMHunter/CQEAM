package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.base.dto.DTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsFaDictDTO;
import com.sino.ams.newasset.model.AmsFaDictModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsFaDictDAO</p>
 * <p>Description:�����Զ����ɷ������AmsFaDictDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsFaDictDAO extends AMSBaseDAO {


    public AmsFaDictDAO(SfUserDTO userAccount, AmsFaDictDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsFaDictDTO dtoPara = (AmsFaDictDTO) dtoParameter;
        super.sqlProducer = new AmsFaDictModel((SfUserDTO) userAccount, dtoPara);
    }
}
