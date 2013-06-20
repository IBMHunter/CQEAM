package com.sino.ams.newasset.constant;

import com.sino.base.constant.web.WebActionConstant;

/**
 *
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface AssetsURLList {
    //===============================================�ʲ���������=============================
    String ASSETS_TRANS_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsTransHeaderServlet";
    String ASSETS_FREE_SERVLET = "/servlet/com.sino.ams.newasset.free.servlet.FreeAssetsHeaderServlet";
    String ASSETS_QUERY_PAGE = "/newasset/assetsTrans.jsp";
    String ORDER_QUERY_PAGE = "/newasset/orderQuery.jsp";
    String ORDER_PRINT_QUERY = "/newasset/printQuery.jsp";
    String PRINT_DETAIL_PAGE = "/newasset/orderPrintDetail.jsp"; //�ʲ���������ϸ��Ϣ
    String ORDER_QUERY_SERVLET = "/servlet/com.sino.ams.newasset.servlet.OrderQueryServlet";
    String ORDER_PRINT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.OrderPrintServlet";
    String TRANS_EDIT_PAGE = "/newasset/assetsTransEdit.jsp";
    String ASSETS_DISCARD_PAGE = "/newasset/assetsDiscardData.jsp";
    String TRANS_EDIT_PAGE_NM = "/newasset/assetsTransEdit_42.jsp";
    String FREE_EDIT_PAGE = "/newasset/free/freeAssetsEdit.jsp"; //��ֵ����ϸ��Ϣ
    String FREE_DETAIL_PAGE = "/newasset/free/freeAssetsDetail.jsp"; //��ֵ����ϸ��Ϣ
    String TRANS_DETAIL_PAGE = "/newasset/assetsTransDetail.jsp"; //�ʲ���������ϸ��Ϣ
    String TRANS_DETAIL_PAGE_NM = "/newasset/assetsTransDetail_42.jsp"; //�ʲ���������ϸ��Ϣ
    String ORDER_APPROVE_SERVLET = "/servlet/com.sino.ams.newasset.servlet.OrderApproveServlet";
    String APPROVE_EDIT_PAGE = "/newasset/assetsTransApprove.jsp"; //�ʲ�����������ҳ��
    String APPROVE_EDIT_NM = "/newasset/assetsTransApprove_42.jsp"; //�ʲ�����������ҳ��(���ɹ�)
    String APPROVE_DETL_PAGE = "/newasset/transApproveDetail.jsp"; //�ʲ���������������Ϣ
    String APPROVE_DETL_NM = "/newasset/transApproveDetail_42.jsp"; //�ʲ�������������Ϣ(���ɹ�)
    String APPROVE_DISCARD_PAGE = "/newasset/discardApprove.jsp"; //�ʲ�����������ϢclearApprove
    String APPROVE_DISCARD_DTL_PAGE = "/newasset/discardApproveDetail.jsp"; //�ʲ�����������ϢclearApprove
    String RCV_DETAIL_PAGE = "/newasset/assetsRcvDetail.jsp"; //�ʲ�������������ϸ��Ϣ
    String ASSETS_DEPT_SELECT = "/newasset/deptSelect.jsp";
    String GROUP_CHOOSE_SERVLET = "/servlet/com.sino.ams.newasset.servlet.GroupChooseServlet";
    String ASSETS_GROUP_SELECT = "/newasset/chooseGroup.jsp";
    String ASSETS_SUBMIT_SUCCESS = "/newasset/success.jsp";
    String ASSETS_TRANS_EDIT = "/newasset/assetsTransEdit.jsp";
    String ASSETS_FRM_PAGE = "/newasset/assetsFrm.jsp";
    String OD_ASSETS_SERVLET = "/servlet/com.sino.ams.newasset.servlet.OdEtsFaAssetsServlet";//�������������ʲ�
    String OD_ASSETS_FRM_PAGE = "/newasset/odAssetsFrm.jsp";//�������������ʲ�
    String ASSETS_FRM_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssetsFrmServlet";
    String ASSETS_TOP_PAGE = "/newasset/assetsTop.jsp";
    String ASSETS_TOP_PAGE2 = "/newasset/assetsTop2.jsp";
    String ASSETS_TREE_PAGE = "/newasset/assetsTree.jsp";
    String ASSETS_QRY_PAGE = "/newasset/assetsQuery.jsp";
    String OD_ASSETS_QRY_PAGE = "/newasset/odAssetsQuery.jsp";
    String ADMIN_CONFIRM_PAGE = "/newasset/adminConfirmQuery.jsp";
    String ASSETS_QRY_PAGE2 = "/newasset/assetsQuery2.jsp";
    String ASSETS_QRY_SERVLET = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=" + AssetsActionConstant.QUERY_ACTION;
    String ASSETS_DTL_PAGE = "/newasset/assetsDetail.jsp"; //�ʲ���ϸ��Ϣ
    String ASSETS_DTL_CT_PAGE = "/ct/equip/assetsDetail.jsp"; //��ͨ�ʲ���ϸ��Ϣ
//	String ITEM_DTL_PAGE = "/newasset/itemDetail.jsp"; //�豸��ϸ��Ϣ
    String ASSETS_PRIVI_QRY = "/newasset/priviQuery.jsp";
    String ASSETS_PRIVI_DTL = "/newasset/priviDetail.jsp";
    String ASSETS_PRIVI_TOP = "/newasset/priviTop.jsp";
    String ASSETS_PRIVI_LEFT = "/newasset/priviLeft.jsp";
    String ASSETS_PRIVI_RIGHT = "/newasset/priviRight.jsp";
    String ASSETS_PRIVI_FRM = "/newasset/priviFrm.jsp";
    String ASSETS_PRIVI_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsPriviServlet";
    String PRIVI_RIGHT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.PriviRightServlet";
    String ASSETS_STS_PROVINCE = "/newasset/assetsProvinceStatis.jsp";
    String ASSETS_STS_COMPANY = "/newasset/assetsCompanyStatis.jsp";
    String ASSETS_STS_DEPT = "/newasset/assetsDeptStatis.jsp";
    String ASSETS_STS_PERSONAL = "/newasset/assetsPersonalStatis.jsp";
    String ASSETS_RCV_QRY = "/newasset/assetsReceiveQuery.jsp";
    String ASSETS_ASSIGN_QRY = "/newasset/assetsAssignQuery.jsp";
    String ASSETS_RCV_SERVLRT = "/servlet/com.sino.ams.newasset.servlet.AssetsReceiveServlet";
    String ASSETS_ASSIGN_FRM = "/newasset/assignFrm.jsp";
    String ASSETS_ASSIGN_TOP = "/newasset/assignTop.jsp";
    String ASSETS_ASSIGN_LEFT = "/newasset/assignLeft.jsp";
    String ASSIGN_LEFT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssignLeftServlet";
    String ASSIGN_DEPT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssignDeptServlet";
    String ASSIGN_PERSON_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssignPersonServlet";
    String ASSIGN_USER_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssignUserServlet";
    String ASSETS_ASSIGN_PERSON = "/newasset/assignPerson.jsp";
    String ASSETS_ASSIGN_USER = "/newasset/assignUser.jsp";
    String ASSETS_ASSIGN_USER2 = "/newasset/assignUser2.jsp";
    String ASSETS_ASSIGN_LOCATION = "/newasset/assignLocation.jsp";
    String ASSETS_ASSIGN_DEPT = "/newasset/assignDept.jsp";
    String ASSETS_ASSIGN_RIGHT = "/newasset/assignRight.jsp";
    String ASSETS_ASSIGN_FRM_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssignFrmServlet";
    String ASSIGN_RIGHT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssignRightServlet";
    String ASSETS_DISCARD_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssetsDiscardServlet";
    String DISCARD_QRY_PAGE = "/newasset/discardOrderQuery.jsp";
    String DISCARD_EDIT_PAGE = "/newasset/discardOrderEdit.jsp";
    String ASSETS_CLEAR_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssetsClearServlet";
    String CLEAR_QRY_PAGE = "/newasset/clearOrderQuery.jsp";
    String CLEAR_EDIT_PAGE = "/newasset/clearOrderEdit.jsp";
    String CHECK_BATC_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsCheckBatchServlet";
    String CHECK_APPR_SERVLET = "/servlet/com.sino.ams.newasset.servlet.CheckApproveServlet";
    String BATCH_QRY_PAGE = "/newasset/checkBatchQuery.jsp";
    String BATCH_EDIT_PAGE = "/newasset/checkBatchEdit.jsp";
    String BATCH_DETAIL_PAGE = "/newasset/checkBatchDetail.jsp";
    String BATCH_APPROVE_EDIT = "/newasset/checkApproveEdit.jsp";
    String CHECK_HEADER_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsCheckHeaderServlet";
    String ORDER_ANALYZE_SERVLET = "/servlet/com.sino.ams.newasset.servlet.CheckOrderAnalyzeServlet";
    String HEADER_QRY_PAGE = "/newasset/checkOrderQuery.jsp";
    String ANALYZE_ORDER_QRY = "/newasset/chkOrderAnalyzeQuery.jsp";
    String ANALYZE_ORDER_DTL = "/newasset/chkOrderAnalyzeDetail.jsp";
    String HEADER_EDIT_PAGE = "/newasset/checkOrderDetail.jsp";
    String ARCHIVE_ORDER_SERVLET = "/servlet/com.sino.ams.newasset.servlet.ChkOrderArchiveServlet";
    String ARCHIVE_ORDER_QRY = "/newasset/archiveOrderQuery.jsp";
    String ORDER_ARCHIVE_QRY = "/newasset/orderArchiveQuery.jsp";
    String ORDER_ARCHIVE_SERVLET = "/servlet/com.sino.ams.newasset.servlet.OrderArchiveServlet";
    String ARCHIVE_ORDER_DTL = "/newasset/archiveOrderDetail.jsp";
    String CUST_QRY_SET_PAGE = "/newasset/customQuerySet.jsp";
    String CUST_QRY_PAGE = "/newasset/customQuery.jsp";
    String CUST_QRY_SET_SERVLET = "/servlet/com.sino.ams.newasset.servlet.CustomQuerySetServlet";
    String CUST_QRY_SERVLET = "/servlet/com.sino.ams.newasset.servlet.CustomQueryServlet";
    String BARCODE_HISTORY_PAGE = "/newasset/barcodeHistory.jsp";
    String LOCATION_QUERY_SERVLET = "/servlet/com.sino.ams.newasset.servlet.LoctionQueryServlet";
    String LOCATION_QUERY_PAGE = "/newasset/locationQuery.jsp";
    String LOCATION_DETAIL_PAGE = "/newasset/locationDetail.jsp";
    String DEPT_QRY_PAGE = "/newasset/deptQuery.jsp"; //���Ų�ѯ����
    String DEPT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsMisDeptServlet"; //���Ų�ѯ����
    String COMM_QRY_PARA = "/newasset/commQueryPara.jsp";
    String COMM_QRY_RESULT = "/newasset/commonQueryResult.jsp";
    String FA_CAT_QRY_PAGE = "/newasset/faCatQuery.jsp";
    String FA_CAT_QRY_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsFaCategoryVServlet";
    String FA_CONTENT_PAGE = "/newasset/faDictContent.jsp";
    String FA_CONTENT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.ContentPriviServlet"; //
    String RCV_HEADER_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsRcvHeaderServlet";
    String RCV_ORDER_DETAIL = "/newasset/rcvOrderDetail.jsp";
    String RCV_ORDER_APPROVE = "/newasset/rcvOrderApprove.jsp";
    String RCV_ORDER_QUERY = "/newasset/rcvOrderQuery.jsp";
    String ADMIN_CONFIRM_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AdminConfirmServlet";
    String ITEM_DATA_PAGE = "/newasset/itemData.jsp";
    String ITEM_FRM_PAGE = "/newasset/itemFrm.jsp";
    String ITEM_BOTTOM_PAGE = "/newasset/itemBottom.jsp";
    String ITEM_DETAIL_PAGE = "/newasset/itemDetail.jsp";
    String ITEM_MAINTAIN_SERVLET = "/servlet/com.sino.ams.newasset.servlet.ItemMaintainServlet";

    String MANY_DIMENSION_PAGE = "/system/manydimensions/manyDimensions.jsp";
    String MANY_DIMENSION_DATA_PAGE = "/system/manydimensions/ManyDimensionsData.jsp";
    String MANY_DIMENSION_BOTTOM_PAGE = "/system/manydimensions/ManyDimensionsBottom.jsp";
    String MANY_DIMENSION_FRM_PAGE = "/system/manydimensions/ManyDimensionsFrm.jsp";
    String MANY_DIMENSION_SERVLET = "/servlet/com.sino.ams.system.manydimensions.servlet.ManyDimensionsServlet";
    String MANY_DIMENSION_BOTTOM_SERVLET = "/servlet/com.sino.ams.system.manydimensions.servlet.ManyDimensionsBottomServlet";

    String ITEM_BOTTOM_SERVLET = "/servlet/com.sino.ams.newasset.servlet.ItemMainBottomServlet";
    String ITEM_LOG_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsItemCorrectLogServlet";
    String FREE_ASSETS_QRY = "/newasset/freeAssetsQuery.jsp";//�����ʲ���ѯҳ��
    String MIS_ASSETS_QRY = "/newasset/misAssetsQuery.jsp";//MIS�ʲ���ѯҳ��
    String MIS_DEPRN_ASSETS_QRY = "/newasset/misDeprnAssetsQuery.jsp";//MIS�ʲ��۾ɲ�ѯҳ��
    String MIS_DISCARDED_ASSETS_QRY = "/newasset/misDiscardedAssetsQuery.jsp";//MIS�ʲ����ϲ�ѯҳ��
    String RCV_PRINT_QRY = "/newasset/rcvPrintQuery.jsp";//���յ��ݴ�ӡ��ѯҳ��
    String RCV_ORDER_PRINT = "/newasset/rcvOrderPrint.jsp";//���յ��ݴ�ӡ��ϸҳ��
    String ASSETS_INVI_PAGE = "/newasset/assetsInvi.jsp";//���˹�����ѯ
    String ASSETS_INVI_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AssetsInviServlet";//���˹�����ѯ

    String SPECIAL_ASSETS_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsSpecialAssetsServlet";
    String SPECIAL_ASSETS_QUERY_PAGE = "/newasset/specialAssetsPage.jsp";//�����ʲ�����ҳ��
    String SPECIAL_ASSETS_EDIT_PAGE = "/newasset/specialAssetsEdit.jsp";
    String SPECIAL_ASSETS_DETAIL_PAGE = "/newasset/specialAssetsDetail.jsp"; //�����ʲ���������ϸ��Ϣ
    String SPECIAL_APPROVE_EDIT_PAGE = "/newasset/specialassetsTransApprove.jsp"; //�����ʲ�����������ҳ��
    String SPECIAL_ORDER_APPROVE_SERVLET = "/servlet/com.sino.ams.newasset.servlet.SpecialOrderApproveServlet";
    String SPECIAL_APPROVE_DETL_PAGE = "/newasset/specialApproveDetail.jsp"; //�����ʲ���������������Ϣ


//=====================================�����Ǳ�����======================================
    String COMP_LOC_RPT = "/newasset/report/compLocationReport.jsp";
    String LOC_DTL_RPT = "/newasset/report/locDetailReport.jsp";
    String OWN_LOC_RPT = "/newasset/report/ownLocations.jsp";
    String SCANED_LOC_RPT = "/newasset/report/scanedLocations.jsp";
    String NOTSCANED_LOC_RPT = "/newasset/report/notScanedLocations.jsp";

    String COMP_ITEM_RPT = "/newasset/report/compItemReport.jsp";
    String LOC_ITEM_RPT = "/newasset/report/locItemReport.jsp";
    String ITEM_CAT_RPT = "/newasset/report/itemCatDetailReport.jsp";

    String OWN_ITEM_RPT = "/newasset/report/ownItems.jsp";
    String SCANED_ITEM_RPT = "/newasset/report/scanedItems.jsp";
    String DIFF_ITEM_RPT = "/newasset/report/diffItems.jsp";

    String SCAN_MONITOR_RPT = "/newasset/report/checkMonitorRpt.jsp";
    String SCAN_LOC_DTL = "/newasset/report/resLocDetailReport.jsp";

    String MAIN_RES_LOC = "/newasset/report/responsibleLocs.jsp";//�ض���ά��˾�����εص�
    String SCAN_LOC_Y = "/newasset/report/scanedLocs.jsp";//�ض���ά��˾�����εص�
    String SCAN_LOC_N = "/newasset/report/notScanedLocs.jsp";//�ض���ά��˾�����εص�

    String SCAN_ITEMS_RPT = "/newasset/report/itemResponsibleScan.jsp";//�ض���ά��˾�����ض��ص��ɨ�����

    String GROUP_CHK_RPT = "/newasset/report/groupCheckResult.jsp";
    String GROUP_FRAME_RPT = "/newasset/report/groupCheckFrm.jsp";//���ҳ��(������ͳ��)
    String SCAN_GROUP_PAGE = "/newasset/report/checkGroup.jsp";
    String GROUP_LOCATION_PAGE = "/newasset/report/groupLocations.jsp";//Ѳ��ص���ϸ��Ϣ(������ͳ��)
    String GROUP_ITEM_PAGE = "/newasset/report/groupItems.jsp";//�̵��豸��ϸ��Ϣ(������ͳ��)
    String DEPT_ITEM_RPT = "/newasset/report/deptItemReport.jsp";

    String COST_DIFF_RPT = "/newasset/report/costDiffReport.jsp";//�ɱ������̵���챨��
    String COST_DTL_RPT = "/newasset/report/costDtlReport.jsp";//�ɱ������̵���챨����ϸҳ��(���ҳ��)
    String COST_OWN_ASSETS = "/newasset/report/costOwnAssets.jsp";//�ɱ������̵���챨��(�����ʲ�)
    String COST_SCANED_ITEMS = "/newasset/report/costScanedItems.jsp";//�ɱ������̵���챨��(��ɨ���豸)
    String COST_NOT_SCANED_ASSETS = "/newasset/report/costNotScanedAssets.jsp";//�ɱ������̵���챨��(δɨ���ʲ�)

    String DEPT_DIFF_RPT = "/newasset/report/deptDiffReport.jsp";//�����̵���챨��
    String DEPT_DIFF_DETAIL = "/newasset/report/deptDiffReportDetail.jsp";//�����̵�������ϸ���
    String DEPT_OWN_ASSETS ="/newasset/report/deptOwnAssets.jsp"; //�����̵���챨��(�����ʲ�)
    String DEPT_SCANED_ITEMS ="/newasset/report/deptScanedItems.jsp"; //�����̵���챨��(��ɨ���豸)
    String DEPT_NOT_SCANED_ASSETS ="/newasset/report/deptNotScanedAssets.jsp"; //�����̵���챨��(δɨ���豸)

    String FA_ANALYSIS_RPT = "/newasset/report/faAnalyseReport.jsp";//�ʲ��䶯����(��һ�㱨��)
    String OU_ASSETS_RPT = "/newasset/report/orgAssetsReport.jsp";//�ʲ��䶯����(�ڶ����ض�OU����)
    String OU_ASSETS_RPT_2 = "/newasset/report/orgAssetsReport_2.jsp";//�ʲ��䶯����(�ڶ����ض�OU����)
    String OU_ASSETS_RPT_3 = "/newasset/report/orgAssetsReport_3.jsp";//�ʲ��䶯����(�ڶ����ض�OU����)
    String CHECK_REPORT = "/newasset/report/checkReport.jsp";//�̵�ͳ�Ʊ���";
    String BTS_MONITOR_RPT = "/newasset/report/btsMonitorReport.jsp";//��վ�̵���ʱ��ر���1";
    String BTS_DETAIL_RRP = "/newasset/report/btsDetailReport.jsp";//��վ�̵���ʱ��ر���2";
    String COST_CHECK_REPORT = "/newasset/report/costCheckReport.jsp";//�̵�ͳ�Ʊ���";
    String ASSETS_CHG_REPORT = "/newasset/report/assetsChangeReport.jsp";//�̵�ͳ�Ʊ���";

    String CHECK_DETAIL_REPORT = "/newasset/report/checkDetailReport.jsp";//�̵���ϸͳ�Ʊ���";
    String PERSON_CHECK_REPORT = "/newasset/report/personCheckReport.jsp";//�����̵㱨��

    String CHECK_RESULT_RPT = "/newasset/report/checkResult.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
    String COST_CHECK_RESULT = "/newasset/report/costCheckResult.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(������)";
    String CHECK_RESULT_FRM = "/newasset/report/checkResultFrm.jsp";//�̵���ͳ�Ʊ���--���ҳ";
    String CHECK_RESULT_RPT_1 = "/newasset/report/checkResult1.jsp";//�̵���ͳ�Ʊ���--��ʵһ��";
    String CHECK_RESULT_RPT_2 = "/newasset/report/checkResult2.jsp";//�̵���ͳ�Ʊ���--�ʲ����Բ�һ��";
    String CHECK_RESULT_RPT_3 = "/newasset/report/checkResult3.jsp";//�̵���ͳ�Ʊ���--�п�����(1)";
    String CHECK_RESULT_RPT_4 = "/newasset/report/checkResult4.jsp";//�̵���ͳ�Ʊ���--�����޿�";
    String CHECK_RESULT_RPT_5 = "/newasset/report/checkResult5.jsp";//�̵���ͳ�Ʊ���--�п�����(2)";
    String CHECK_RESULT_RPT_6 = "/newasset/report/checkResult6.jsp";//����PDAɨ���嵥

    String NEW_FA_CHK_REPORT = "/newasset/report/newCheckResult.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
	String NEW_FA_FRAME = "/newasset/report/newAssetsFrm.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
	String NEW_FA_REPORT = "/newasset/report/newAssetsReport.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
	String NEW_FA_SCANED = "/newasset/report/newAssetsScaned.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
	String NEW_FA_NOT_SCANED = "/newasset/report/newAssetsNotScaned.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
	String NEW_FA_IDENTICAL = "/newasset/report/newAssetsIdentical.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
    String NEW_FA_DIFFERENT = "/newasset/report/newAssetsDifferent.jsp";//�̵���ͳ�Ʊ���--���ͳ��ҳ(��OU)";
    String NEW_CHECK_RESULT_DEPT = "/newasset/report/newCheckResultDept.jsp";//�����ʲ��̵��أ������ţ�;
    String NEW_FA_FRAME_DEPT = "/newasset/report/newAssetsDeptFrm.jsp";//�����ʲ��̵��أ������ţ�"��ϸFREAM";
    String NEW_FA_REPORT_DEPT = "/newasset/report/newAssetsDeptReport.jsp";//�����ʲ��̵��أ������ţ������ʲ���
    String NEW_FA_SCANED_DEPT = "/newasset/report/newAssetsScanedDeptReport.jsp";//�����ʲ��̵��أ�������,ɨ���ʲ���
    String NEW_FA_NOT_SCANED_DEPT = "/newasset/report/newAssetsNotScanedDeptReport.jsp";//�����ʲ��̵��أ�������,ɨ���ʲ���
    String NEW_FA_IDENTICAL_DEPT = "/newasset/report/newAssetsIdenticalDeptReport.jsp";//�����ʲ��̵��أ�������,��ʵһ�£�
    String NEW_FA_DIFFERENT_DEPT = "/newasset/report/newAssetsDifferentDeptReport.jsp";
    String NEW_FA_ASSETS_MONITOR_REPORT = "/newasset/report/newAssetsMonitorReport.jsp";    //�����ʲ���ر���
    String NEW_FA_ASSETS_MONITOR_FRAME = "/newasset/report/newAssetsMonitorFrm.jsp";  //�����ʲ���ر���--�����ϸ
    String NEW_FA_ASSETS_BY_MIS = "/newasset/report/newAssetsMonitorByMis.jsp"; //�����ʲ���ر���--MIS�����ʲ�
    String NEW_FA_ASSETS_MONITOR_TOTAL_SCANED = "/newasset/report/newAssetsMonitorTotalScaned.jsp";//�����ʲ���ر���--�����ϸ--�ڼ�ʵ��ɨ���ʲ���ϸ(��OU)";
    String NEW_FA_ASSETS_MONITOR_SCANED = "/newasset/report/newAssetsMonitorScaned.jsp";//�����ʲ���ر���--�����ϸ--��ɨ���ʲ���ϸ(��OU)";
    String NEW_FA_ASSETS_MONITOR_NEED_SCANED = "/newasset/report/newAssetsMonitorNeedScaned.jsp";//�����ʲ���ر���--�����ϸ--�������ת���ʲ�������PDAɨ��ȷ����ϸ(��OU)";
	String NEW_FA_ASSETS_MONITOR_NOT_SCANED = "/newasset/report/newAssetsMonitorNotScaned.jsp";//�����ʲ���ر���--�����ϸ--δɨ���ʲ�ͳ��ҳ(��OU)";
	String NEW_FA_ASSETS_MONITOR_IDENTICAL = "/newasset/report/newAssetsMonitorIdentical.jsp";//�����ʲ���ر���--�����ϸ--��ʵһ���ʲ�ͳ��ҳ(��OU)";
    String NEW_FA_ASSETS_MONITOR_DIFFERENT = "/newasset/report/newAssetsMonitorDifferent.jsp";//�����ʲ���ر���--�����ϸ--��ʵ��һ���ʲ�ͳ��ҳ(��OU)";


//--------------------------------�̶��ʲ����幹����ֲ����-------------------------------------------------
    String SPECIAL_ASSETS_REPORT = "/newasset/report/assetsSpecialReport.jsp";//רҵ�ʲ�����ͳ�Ʊ���
    String DEPT_ASSETS_REPORT = "/newasset/report/assetsDeptReport.jsp";//�����ʲ�����ͳ�Ʊ���
    String AREA_ASSETS_REPORT = "/newasset/report/assetsAreaReport.jsp";//�����ʲ�����ͳ�Ʊ���
    String INVEST_ASSETS_REPORT = "/newasset/report/assetsInvestReport.jsp";//Ͷ�ʷ������ͳ�Ʊ���
    String INVEST_FREE_ASSETS_REPORT = "/newasset/report/freeAssetsInvestReport.jsp";//�����ʲ�Ͷ�ʷ������ͳ�Ʊ���
    String LOSE_ASSETS_REPORT = "/newasset/report/loseAssetsReport.jsp";//�ʲ��̿�ͳ�Ʊ���
    String CT_AREA_ASSETS_REPORT = "/newasset/report/ctAssetsAreaReport.jsp";  //��ͨ�ʲ�����ͳ��
    String CT_SPECIAL_ASSETS_REPORT = "/newasset/report/ctAssetsSpecialReport.jsp";//��ͨ�ʲ�רҵͳ�Ʊ���
    String CT_DEPT_ASSETS_REPORT = "/newasset/report/assetsCTDeptReport.jsp";//��ͨ�ʲ�����ͳ�Ʊ���
    String UNUSE_DEPT_ASSETS_REPORT = "/newasset/report/assetsUnuseDeptReport.jsp";	//�����ʲ�����ͳ��
    String DISCARDED_DEPT_ASSETS_REPORT = "/newasset/report/assetsDiscardedDeptReport.jsp";//�����ʲ�����ͳ��
    String TODISCARDED_DEPT_ASSETS_REPORT = "/newasset/report/assetsToDiscardedDeptReport.jsp";//�������ʲ�����ͳ��
    String DH_DEPT_ASSETS_REPORT = "/newasset/report/assetsDHDeptReport.jsp";//��ֵ�׺��ʲ�����ͳ��
    String DH_AREA_ASSETS_REPORT = "/newasset/report/assetsDHAreaReport.jsp";//��ֵ�׺��ʲ�����ͳ�Ʊ���
    String DH_SPECIAL_ASSETS_REPORT = "/newasset/report/assetsDHSpecialReport.jsp";//��ֵ�׺��ʲ�רҵͳ�Ʊ���
    String DH_CATEGORY_ASSETS_REPORT = "/newasset/report/assetsDHCategoryReport.jsp";//��ֵ�׺��ʲ�Ŀ¼���ɷֲ�ͳ�Ʊ���
    String FREE_SPECIAL_ASSETS_REPORT = "/newasset/report/assetsFreeSpecialReport.jsp";//�����ʲ�רҵͳ�Ʊ���
    String DISCARDED_SPECIAL_ASSETS_REPORT = "/newasset/report/assetsDiscardedSpecialReport.jsp";//�����ʲ�רҵͳ�Ʊ���
    String TODISCARDED_SPECIAL_ASSETS_REPORT = "/newasset/report/assetsToDiscardedSpecialReport.jsp";//�������ʲ�רҵͳ�Ʊ���
    String TD_SPECIAL_ASSETS_REPORT = "/newasset/report/assetsTDSpecialReport.jsp";//TD�ʲ�רҵͳ�Ʊ���
    String TD_AREA_ASSETS_REPORT = "/newasset/report/assetsTDAreaReport.jsp";//TD�ʲ�����ͳ�Ʊ���
    String TD_DEPT_ASSETS_REPORT = "/newasset/report/assetsTDDeptReport.jsp";//TD�ʲ�����ͳ��
    String RENT_DEPT_ASSETS_REPORT = "/newasset/report/assetsRentDeptReport.jsp";//��Ӫ�����ʲ�רҵͳ�Ʊ���
    String SPECIAL_IMPAIRMENT_ASSETS_REPORT = "/newasset/report/impairmentAssetsSpecialReport.jsp";//��ֵ�ʲ���רҵ�ʲ�����ͳ�Ʊ���
    String AREA_IMPAIRMENT_ASSETS_REPORT = "/newasset/report/impairmentAssetsAreaReport.jsp";//��ֵ�ʲ������ʲ�����ͳ�Ʊ���
    String IMPAIRMENT_ASSETS_REPORT = "/newasset/report/impairmentAssetsReport.jsp";//�ʲ���ֵ���

//--------------------------------�������ʲ�״̬��ִ�����-�ʲ�����-------------------------------------------
    String SPECIAL_NEW_ASSETS_REPORT = "/newasset/report/assetsNewSpecialReport.jsp";//Ͷ�ʷ������ͳ�Ʊ���(����)
    String SPECIAL_FREE_ASSETS_REPORT = "/newasset/report/freeAssetsSpecialReport.jsp";//�����ʲ�Ӧ������ͳ�Ʊ���

//------------------------------------------����ָ���౨��-------------------------------------------------
    String LOSE_RATE_REPORT = "/newasset/report/assetsLoseRateReport.jsp";//�ʲ��̿��ʣ���˾���棩
    String MATCH_RATE_REPORT = "/newasset/report/assetsMatchRateReport.jsp";//����̵��ʲ���ʵ����ʣ���˾���棩
    String DISCARD_RATE_REPORT = "/newasset/report/assetsDiscardRateReport.jsp";//���ɸ�������δ���õı����ʲ�����


    String ASSETS_SUB_EDIT_PAGE = "/newasset/sub/assetsSubEdit.jsp";
    String ASSETS_QUERY_PAGE_SHAN = "/newasset/sub/assetsTransShan.jsp";
    String ASSETS_SUB_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsJianzhirServlet";
    String EFA_REPORT = "/newasset/report/efaReport.jsp";

    String ASSETS_GENERAL_VER_PAGE = "/newasset/report/assetsGenVer.jsp";//����ָ���౨�����������б���
    String ASSETS_GENERAL_VER_DETAIL = "/newasset/report/assetsGenVerDetail.jsp";

    String ASSETS_IN_DATA_QUERY = "/newasset/report/assetsInDataQuery.jsp";//��������ͳһ¼���ѯ����
    String ASSETS_IN_DATA_INFO = "/newasset/report/assetsInDataDetial.jsp";//��������ͳһ¼�����
    String ASSETS_IN_DATA_SERVLET = "/servlet/com.sino.ams.newasset.report.servlet.ReportInDataServlet?act=" + WebActionConstant.QUERY_ACTION;
    String ASSETS_IN_DATA_REPROT = "/newasset/report/assetsInDataReport.jsp";//����ָ���౨������¼���౨��

//=====================================����======================================
    String CHECK_DATA_PAGE = "/newasset/checkDataQuery.jsp";//��ѯʵ��̨�����Ҳ����������̵���ʲ�
    String DZYH_COM_RPT = "/newasset/report/dzyhComReport.jsp";//��ֵ�׺�ͳ��(��˾)
    String DZYH_DEPT_RPT = "/newasset/report/dzyhDeptReport.jsp";//��ֵ�׺�ͳ��(����)

    String LASTING_COM_RPT = "/newasset/report/lastingComReport.jsp";//�����ʲ�ͳ��(��˾)
    String LASTING_DEPT_RPT = "/newasset/report/lastingDeptReport.jsp";//�����ʲ�ͳ��(����)

    String VILLAGE_COM_PRT = "/newasset/report/villageComReport.jsp";//��ͨ�����ʲ�ͳ��(��˾)
    String VILLAGE_DEPT_PRT = "/newasset/report/villageDeptReport.jsp";//��ͨ�����ʲ�ͳ��(����)

    String COST_NO_SAME_QRY = "/newasset/costNoSameQuery.jsp";//ͳ��EFA�����Ӧ�ɱ�����EFA�����˶�Ӧ�ɱ����Ĳ���Ӧ

    String TABLESPACE_MONITOR_QRY = "/newasset/amsTablespaceMoniQry.jsp";//�鿴��ռ��С

    String JOB_CONTROL_QRY = "/newasset/amsJobControlQry.jsp";//�Զ�JOB�������

    String SYN_JOB_QRY = "/newasset/amsSynJobQry.jsp";//ͬ��JOB�������

    String INVALID_OBJECT_QRY = "/newasset/amsInvalidObjectQry.jsp";//�鿴�Ƿ���δ����İ�

    String ITEM_STATUS_ERROR = "/newasset/itemStatusError.jsp";//����EFA�ѱ��ϵ�EII������������

    String COST_DEPT_REC_MATCH = "/newasset/costDeptRecMatch.jsp";

    String ASSETS_TURN_PKG = "/synchronize/assetsTurnQry.jsp";

    //=====================================��鹤��ͳ��======================================
    String SAMPLING_CHK_REPORT = "/sampling/report/samplingChkResult.jsp";
    String SAMPLING_FRM_REPORT = "/sampling/report/samplingChkFrm.jsp";
    String SAMPLING_ONE_REPORT = "/sampling/report/samplingOneReport.jsp";
    String SAMPLING_TWO_REPORT = "/sampling/report/samplingTwoReport.jsp";
    String SAMPLING_THREE_REPORT = "/sampling/report/samplingThreeReport.jsp";
    String SAMPLING_FOUR_REPORT = "/sampling/report/samplingFourReport.jsp";

    //=====================================ʵ��̨�˲�ѯ======================================
    String ITEM_FRM_QRY = "/newasset/itemFrmQuery.jsp";
    String ITEM_FRM_TREE = "/servlet/com.sino.ams.newasset.servlet.itemFrmTreeServlet";
    String ITEM_FRM_TREE_QRY = "/newasset/itemFrmTree.jsp";
    String ITEM_MAINTAIN_SERVLET3 = "/servlet/com.sino.ams.newasset.servlet.ItemMaintainServlet3";
    
    //=====================================�ʲ�����======================================
    String ASSETS_ALLOCATION_QUERY = "/newasset/allocation/assetsAllocationQuery.jsp";
    String ASSETS_ALLOCATION_SERVLET = "/servlet/com.sino.ams.newasset.allocation.servlet.AmsAssetsAllocationHeaderServlet";
    String ASSETS_ALLOCATION_EDIT = "/newasset/allocation/assetsAllocationEdit.jsp";
    String ASSETS_ALLOCATION_DETAIL = "/newasset/allocation/assetsAllocationDetail.jsp";
    String ASSETS_ALLOCATION_APPROVE = "/newasset/allocation/assetsAllocationApprove.jsp";
    String ASSETS_ALLOCATION_APPROVE_SERVLET = "/servlet/com.sino.ams.newasset.allocation.servlet.AmsAssetsAllocationApproveServlet";

    String ASSETS_RENT_PAGE = "/newasset/rent/assetsEdit.jsp";
    String ASSETS_DETAIL_PAGE = "/newasset/rent/assetsDetail.jsp";
    
    String ITEM_HISTORY_PAGE = "/newasset/itemHistory.jsp";
    
    
    String OBJECT_ASSETS_PAGE = "/newasset/objectAssets.jsp";
    
    String AAT_CONFIRM_YN_SERVLET = "/servlet/com.sino.ams.newasset.servlet.AATConfirmYNServlet";
    String AAT_CONFIRM_YN_PAGE = "/newasset/aatConfirmYnQuery.jsp";
    
    String ASSETS_DEVALUE_SERVLET = "/servlet/com.sino.ams.newasset.devalue.servlet.DevalueAssetsHeaderServlet";
    String DEVALUE_EDIT_PAGE = "/newasset/devalue/devalueAssetsEdit.jsp"; //��ֵ���༭��Ϣ
    String DEVALUE_DETAIL_PAGE = "/newasset/devalue/devalueAssetsDetail.jsp"; //��ֵ����ϸ��Ϣ
    String DEVALUE_QUERY_PAGE = "/newasset/devalue/devalueQuery.jsp"; //��ֵ����ѯ
    String DEVALUE_DETAIL = "/newasset/devalue/devalueDetail.jsp"; //��ֵ����ѯ��ϸ
    
    //��Ϣ�����ʹ���
    String INFORMATION_MATERIAL_SERVLET = "/servlet/com.sino.nm.ams.newasset.servlet.InformationMaterialManageServlet";
    String INFORMATION_MATERIAL_QUERY = "/nm/newasset/informationMaterialManage.jsp";
    String INFORMATION_MATERIAL_ADD = "/nm/newasset/addInformationMaterial.jsp";

    //=====================================��Ʒ��������======================================
    String BJ_VENDOR_QUERY = "/spare/bjVendorQuery.jsp";//����ά�޹�˾��ѯ
    String BJ_VENDOR_INFO = "/spare/bjVendorDetail.jsp";//����ά�޹�˾ά��
    String BJ_VENDOR_SERVLET = "/servlet/com.sino.ams.spare.servlet.BjVendorServlet?act=" + WebActionConstant.QUERY_ACTION;
    String SPARE_VENDOR_QUERY = "/spare/spareVendorQuery.jsp";//�������̲�ѯ
    String SPARE_VENDOR_INFO = "/spare/spareVendorDetail.jsp";//��������ά��
    String SPARE_VENDOR_SERVLET = "/servlet/com.sino.ams.spare.servlet.SpareVendorServlet?act=" + WebActionConstant.QUERY_ACTION;
    //=====================================��Ʒ��������======================================
    String SPARE_APPLY_SERVLET = "/servlet/com.sino.ams.spare.servlet.BjslServlet";
    
    
}
