package com.sino.ams.synchronize.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.NetAssetsUpdateModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-3-14
 * Time: 7:32:01
 * To change this template use File | Settings | File Templates.
 */
public class NetAssetsUpdateDAO extends AMSBaseDAO {
      public SfUserDTO sfUser = null;

    /**
     * ���ܣ�eAM�����ص�ͬ�� ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public NetAssetsUpdateDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
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
        EamSyschronizeDTO dtoPara = (EamSyschronizeDTO) dtoParameter;
        super.sqlProducer = new NetAssetsUpdateModel((SfUserDTO) userAccount, dtoPara);
    }
}
