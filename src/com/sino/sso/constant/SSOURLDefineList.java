package com.sino.sso.constant;

import com.sino.base.constant.web.WebActionConstant;

/**
 * Created by IntelliJ IDEA.
 * User: zhoujs
 * Date: 2009-2-18
 * Time: 17:33:28
 * Functiion:
 */
public interface SSOURLDefineList {
    String SYNC_CHANGES_URL = "/servlet/com.sino.ams.synchronize.servlet.AssetsUpdateServlet?action=" + WebActionConstant.QUERY_ACTION;//�ʲ�ֱ�ӱ䶯ͬ��
    String SYNC_TRANS_IN_COMP_URL = "/servlet/com.sino.ams.synchronize.servlet.AssetsCommitServlet?transferType=BTW_COMP&action=" + WebActionConstant.QUERY_ACTION;// ��˾�����ͬ��
    String SYNC_TRANS_RESULT_URL = "/servlet/com.sino.ams.synchronize.servlet.AssetsCommitServlet?transferType=P&action=" + WebActionConstant.QUERY_ACTION;// �������ֱ��ͬ��
    String ASSETS_CONFIRM_INFO_URL = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsConfirmHeaderServlet?action=" + WebActionConstant.QUERY_ACTION;// �ʲ�ȷ����Ϣ
}
