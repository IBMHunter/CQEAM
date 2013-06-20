package com.sino.ams.synchronize.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.AssetsBarCodeUpdateModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-3-14
 * Time: 6:29:04
 * To change this template use File | Settings | File Templates.
 */
public class AssetsBarCodeUpdateDAO extends AMSBaseDAO {
      public SfUserDTO sfUser = null;

    /**
     * ���ܣ�eAM�����ص�ͬ�� ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AssetsBarCodeUpdateDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
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
        super.sqlProducer = new AssetsBarCodeUpdateModel((SfUserDTO) userAccount, dtoPara);
    }

   public void syschronizeLocus(String systemId) {
        CallableStatement cs = null;
        String callStr = "{CALL ams_syn_pkg.syn_asset_entry_change(?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(callStr);
            cs.setInt(1, sfUser.getOrganizationId());
            cs.setString(2, systemId);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
        }
    }
}
