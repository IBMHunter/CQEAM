package com.sino.soa.service;

import java.sql.Connection;

import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.PoolException;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.soa.common.MIS_CONSTANT;
import com.sino.sso.dao.SSOUserLoginDAO;

/**
 * User: zhoujs
 * Date: 2009-5-31 17:16:47
 * Function:SOA������
 */
public class SOAProcess {
    private String startTime = "";
    private String endTime = "";

    private SrvProcessModel processModel = null;

    private SfUserDTO userAccount = new SfUserDTO();

    public SOAProcess() {
        ServletConfigDTO configDTO=new ServletConfigDTO();
        SSOUserLoginDAO ssoUserLoginDAO = new SSOUserLoginDAO(configDTO);
        userAccount = ssoUserLoginDAO.validateUser("SINOADMIN");
        processModel = new SrvProcessModel();
    }

    /**
     * ͬ��MIS��Ϣ
     */
    public void synMisInfo() {
        System.out.print("��ʼ�����Զ�SOAͬ��");
        Connection conn = null;
        SrvDAO srvDAO = new SrvDAO();
        try {
            conn = DBManager.getDBConnection();
            //��ѯ�ʲ����
            srvDAO.synAssetCategory(conn, userAccount);
            //��ѯԱ����Ϣ
            srvDAO.synEmployeeInfo(conn, userAccount);
            //��ѯֵ����Ϣ
            srvDAO.synSetValue(conn, userAccount, MIS_CONSTANT.SOURCE_MIS);
            //��ѯ��֯�ṹ
            srvDAO.synOrgstructure(conn, userAccount);
            //��ѯ��Ŀ��Ϣ
            srvDAO.synProjectInfo(conn, userAccount);
            //��ѯ��Ӧ����Ϣ
            srvDAO.synVendorInfo(conn, userAccount);
            //��ѯ����ڼ�
            srvDAO.synPeriodStatus(conn, userAccount);
            //��ѯ�ʲ��ص�
            srvDAO.synFaLocation(conn, userAccount);
            //��ѯ�ʲ�ͷ������Ϣ(ODI)
            srvDAO.synAssetHeaderInfo(conn, userAccount);
            //��ѯ�ʲ���������Ϣ(ODI)
            srvDAO.synAssetDistribute(conn, userAccount);
            //��ѯ�ʲ�������Ϣ����(ODI)
//            srvDAO.synAssetInfo(conn, userAccount);
            //��ѯ��Ŀ���(ODI)
//            srvDAO.synAccountBlance(conn, userAccount);
            //��ѯ��Ŀ������Ϣ(ODI)
            srvDAO.synTransTaskInfo(conn, userAccount);
            //ͬ����˾�ڵ���
            srvDAO.synTransInCompany(conn, userAccount);
            //ͬ����˾�����
            srvDAO.synTransBtwCompany(conn, userAccount);

        } catch (PoolException e) {
            e.printLog();
        } finally {
            DBManager.closeDBConnection(conn);
        }

        System.out.print("�����Զ�SOAͬ��");

    }

    public void synTDInfo() {
        System.out.print("��ʼ����TD�Զ�SOAͬ��");
        TDSrvDAO srvDAO = new TDSrvDAO();
        Connection conn = null;
        try {
            conn = DBManager.getDBConnection();
            //��ѯ�ʲ����
            srvDAO.synAssetCategory(conn, userAccount);
            //��ѯԱ����Ϣ
            srvDAO.synEmployeeInfo(conn, userAccount);
            //��ѯֵ����Ϣ
            srvDAO.synSetValue(conn, userAccount, MIS_CONSTANT.SOURCE_TD);
            //��ѯ��֯�ṹ
            srvDAO.synOrgstructure(conn, userAccount);
            //��ѯ��Ŀ��Ϣ
            srvDAO.synProjectInfo(conn, userAccount);
            //��ѯ��Ӧ����Ϣ
            srvDAO.synVendorInfo(conn, userAccount);
            //��ѯ����ڼ�
            srvDAO.synPeriodStatus(conn, userAccount);
            //��ѯ�ʲ��ص�
            srvDAO.synFaLocation(conn, userAccount);
            //��ѯ�ʲ�ͷ������Ϣ(ODI)
            srvDAO.synAssetHeaderInfo(conn, userAccount);
            //��ѯ�ʲ���������Ϣ(ODI)
            srvDAO.synAssetDistribute(conn, userAccount);
            //��ѯ�ʲ�������Ϣ����(ODI)
//            srvDAO.synAssetInfo(conn, userAccount);
            //��ѯ��Ŀ���(ODI)
//            srvDAO.synAccountBlance(conn, userAccount);
            //ͬ����˾�ڵ���
            srvDAO.synTransInCompany(conn, userAccount);
            //ͬ����˾�����
            srvDAO.synTransBtwCompany(conn, userAccount);

        } catch (PoolException e) {
            e.printLog();
        } finally {
            DBManager.closeDBConnection(conn);

        }

        System.out.print("����TD�Զ�SOAͬ��");

    }

}