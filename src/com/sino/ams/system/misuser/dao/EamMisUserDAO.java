package com.sino.ams.system.misuser.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.misuser.dto.EamMisUserDTO;
import com.sino.ams.system.misuser.model.EamMisUserModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2009-1-6
 * Time: 11:08:38
 * To change this template use File | Settings | File Templates.
 */
public class EamMisUserDAO extends AMSBaseDAO {
    private SfUserDTO sfUser = null;
      public EamMisUserDAO(SfUserDTO userAccount, EamMisUserDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EamMisUserDTO dtoPara = (EamMisUserDTO) dtoParameter;
        super.sqlProducer = new EamMisUserModel((SfUserDTO) userAccount, dtoPara);
    }
}
