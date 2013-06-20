package com.sino.td.commom;

/**
 * <p>Title: TdURLDefineList</p>
 * <p>Description:�����Զ����ɷ������TdURLDefineList��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author
 * @version 1.0
 */

public interface TdURLDefineList {

    public final String TD_NEW_LOCATIONS = "/td/tdSynchronizePage/TdNewLocationsSyn.jsp";  //Td�ص���ͬ�� 
    public final String TD_ASSETS_DISPOSE_SYN_PAGE = "/td/tdSynchronizePage/transStatusQuery.jsp";   //Td������״̬��ѯ
    public final String TD_ASSETS_TRANSFER_SYN_PAGE = "/td/tdSynchronizePage/assetsTrans.jsp";   //TD�ʲ��������ͬ��
    public final String TD_ASSETS_DISCARDED_SYN_PAGE = "/td/tdSynchronizePage/assetsDiscardeds.jsp";   //TD�ʲ�����ͬ��

    public final String TD_LOCATION_CHANGE_SYN_PAGE = "/td/tdSynchronizePage/synTdLocation.jsp";
    public final String IMPORT_TD_LOCATION_PAGE = "/srv/tdSyn/importTdLocation.jsp";//SOA�ص㵼��
    public final String UPDATE_TD_ASSETS_PAGE = "/srv/tdSyn/updateAssetInfo.jsp";
    public final String TD_ASSETS_CHANGE_SYN_PAGE = "/td/tdSynchronizePage/assetsChangeSyn.jsp";
    public final String TD_ASSETS_RETIRMENT_SYN_PAGE = "/td/tdSynchronizePage/assetsRetirment.jsp";
    public final String TD_ASSETS_TRANSINCOMPANY_SYN_PAGE = "/td/tdSynchronizePage/assetsInCompany.jsp";

    public final String ASSETS_QUERY_PAGE_TD = "/td/newasset/assetsTransTd.jsp";
    public final String TRANS_EDIT_PAGE_TD = "/td/newasset/assetsTransEditTd.jsp";
    public final String TRANS_DETAIL_PAGE_TD = "/td/newasset/assetsTransDetailTd.jsp"; //�ʲ���������ϸ��Ϣ
    public final String APPROVE_EDIT_PAGE_TD = "/td/newasset/assetsTransApproveTd.jsp"; //�ʲ�����������ҳ��
    public final String APPROVE_DETL_PAGE_TD = "/td/newasset/transApproveDetailTd.jsp"; //�ʲ���������������Ϣ
    public final String ASSETS_RCV_QRY_TD = "/td/newasset/assetsReceiveQueryTd.jsp";
    public final String RCV_DETAIL_PAGE_TD = "/td/newasset/assetsRcvDetailTd.jsp"; //�ʲ�������������ϸ��Ϣ
    public final String ASSETS_ASSIGN_QRY_TD = "/td/newasset/assetsAssignQueryTd.jsp";
    public final String ORDER_QUERY_PAGE_TD = "/td/newasset/orderQueryTd.jsp";
    public final String ORDER_PRINT_QUERY_TD = "/td/newasset/printQueryTd.jsp";
    public final String ADMIN_CONFIRM_PAGE_TD = "/td/newasset/adminConfirmQueryTd.jsp";
    public final String BARCODE_HISTORY_PAGE_TD = "/td/newasset/barcodeHistoryTd.jsp";
    public final String ASSETS_DTL_PAGE_TD = "/td/newasset/assetsDetailTd.jsp"; //�ʲ���ϸ��Ϣ
    public final String PRINT_DETAIL_PAGE_TD = "/td/newasset/orderPrintDetailTd.jsp"; //�ʲ���������ϸ��Ϣ

    public final String ASSETS_TRANS_SERVLET_TD = "/servlet/com.sino.td.newasset.servlet.TdAssetsTransHeaderServlet";
    public final String ORDER_APPROVE_SERVLET_TD = "/servlet/com.sino.td.newasset.servlet.TdOrderApproveServlet";
    public final String RCV_HEADER_SERVLET_TD = "/servlet/com.sino.td.newasset.servlet.TdAssetsRcvHeaderServlet";
    public final String ASSETS_RCV_SERVLRT_TD = "/servlet/com.sino.td.newasset.servlet.TdReceiveServlet";
    public final String ORDER_QUERY_SERVLET_TD = "/servlet/com.sino.td.newasset.servlet.TdOrderQueryServlet";
    public final String ORDER_PRINT_SERVLET_TD = "/servlet/com.sino.td.newasset.servlet.TdOrderPrintServlet";
    public final String ADMIN_CONFIRM_SERVLET_TD = "/servlet/com.sino.td.newasset.servlet.TdAdminConfirmServlet";

    //String TRANS_EDIT_PAGE = "/newasset/assetsTransEdit.jsp";

    public final String TD_ASSETS_SEARCH_PAGE = "/td/tdSearchPage/tdAssetsQuery.jsp";
    public final String TD_ASSETS_PRC_SERVLET = "/servlet/com.sino.td.assetsSearch.servlet.TdAssetsQueryServlet";
}