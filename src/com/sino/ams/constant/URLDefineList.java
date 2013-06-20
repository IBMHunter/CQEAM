package com.sino.ams.constant;

import com.sino.base.constant.web.WebActionConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public interface URLDefineList {

	//==================================�����ҳ��===========================================//
	String LOGIN_PAGE = "/login.jsp"; //��½ҳ��
	String COMMON_MSG_PAGE = "/publicMessage.jsp";//��Ϣ��ʾҳ�棬����ҳ�����include����ʽ������ҳ�档
	String FIRST_LOGIN_PAGE = "/firstLogin.jsp"; //��MIS�û��״ε�¼�޸ĳ�ʼ����
	String PORTAL_SERVLET = "/servlet/com.sino.ams.log.servlet.PortalLoginServlet"; //portal��֤Servlet
	String HN_PORTAL_SERVLET = "/servlet/com.sino.hn.portal.servlet.PortalLoginServlet"; //portal��֤Servlet
	
	String LOGIN_FRM_SERVLET = "/servlet/com.sino.ams.log.servlet.LoginFrmServlet";
	String HOME_PAGE = "/mainPage.jsp"; //�����ҳ��
	String TIME_OUT_PAGE = "/timeOutPage.jsp"; //�Ự����ҳ��
	String NO_PRIVI_PAGE = "/noPriviPage.jsp"; //��Ȩ��ҳ��
	String ERROR_PAGE = "/flow/errorPage.jsp"; //����ҳ��
	String TOP_PAGE = "/topPage.jsp"; //�����˵�ҳ��
	String MENU_PAGE = "/resourceMenu.jsp"; //���˵���ҳ��
	String SUCCESS_PAGE = "/public/successPage.jsp"; //�����ɹ�����ҳ��
	String TEMP_PAGE = "/temPage.jsp"; //��ʱҳ�棬���ڶ��忪����Ա�Ĺ�������
	String WINDOW_CLOSE_PAGE = "/public/windowClose.jsp"; //ת��ر�ҳ�棬�ɴ�����ֵ retValue
	String INDEX_PAGE = "/home.jsp";//��ҳ��Ϣ
	String INDEX_PAGE_NM = "/home_NM.jsp";//��ҳ��Ϣ
	String GET_MENU_PAGE = "/getMenuPage.jsp";//������Ŀ
	//==================================�����ҳ��===========================================//

	//==================================��Ŀά����ҳ��===========================================//
	String RES_FRM_PAGE = "/system/resource/resourceFrm.jsp";
	String RES_QRY_PAGE = "/system/resource/resourceQuery.jsp";
	String RES_TREE_PAGE = "/system/resource/resourceTree.jsp";
	String RES_DTL_PAGE = "/system/resource/resourceDetail.jsp";
	String RES_QRY_SERVLET = "/servlet/com.sino.ams.system.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.QUERY_ACTION;
	String RES_NEW_SERVLET = "/servlet/com.sino.ams.system.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.NEW_ACTION;
	String RES_DTL_SERVLET = "/servlet/com.sino.ams.system.resource.servlet.SfResDefineServlet?act=" + WebActionConstant.DETAIL_ACTION;
	String RES_PRIVI_FRM = "/system/resource/resPriviFrm.jsp";
	String RES_PRIVI_TREE = "/system/resource/resPriviTree.jsp";
	String RES_PRIVI_QUERY = "/system/resource/resPriviQuery.jsp";
	String PRIVI_QUERY_SERVLET = "/servlet/com.sino.ams.system.resource.servlet.SfResPrivsServlet?act=" + WebActionConstant.QUERY_ACTION;
	String WORK_PLAN_QUERY = "/system/plan/workPlanQuery.jsp";   //�����ƻ�ά������ѯ��ҳ��
	String WORK_PLAN_DETAIL = "/system/plan/workPlanDetail.jsp"; //�����ƻ���ϸҳ��
	String WORK_PLAN_SEARCH = "/system/plan/workPlanSearch.jsp";//�����ƻ���ѯҳ��
	String WORK_PLAN_PIGEONHOLE = "/system/plan/workPlanPigeonhole.jsp";//�����ƻ��鵵ҳ��
	String WORK_PLAN_NEW = "/system/plan/workPlanNew.jsp";//���������ƻ�ҳ��
	String WORK_PLAN_S_DETAI = "/system/plan/workPlanSDetail.jsp";//�����ƻ���ϸҳ�棨��ѯ��
	String WORK_PLAN_QUERY_SERVLET = "/servlet/com.sino.ams.plan.servlet.AmsWorkPlanServlet?act=" + WebActionConstant.DETAIL_ACTION;

	//==================================��Ŀά����ҳ��===========================================//

	//==============================================�û���Ϣά����ҳ��============================
	String GROUP_QUERY_PAGE = "/system/group/groupQuery.jsp";
	String GROUP_SERVLET_PAGE = "/servlet/com.sino.ams.system.user.servlet.SfGroupServlet?act=" + WebActionConstant.QUERY_ACTION;
	String GROUP_DETAIL_PAGE = "/system/group/groupDetail.jsp";
	String ROLE_QUERY_PAGE = "/system/role/roleQuery.jsp";
	String ROLE_DETAIL_PAGE = "/system/role/roleDetail.jsp";
	String ROLE_QUERY_SERVLET = "/servlet/com.sino.ams.system.user.servlet.SfRoleServlet?act=" + WebActionConstant.QUERY_ACTION;
	String USER_LIST_PAGE = "/system/user/userList.jsp";
	String USER_LIST_SERVLET = "/servlet/com.sino.ams.system.user.servlet.SfUserServlet?act=" + WebActionConstant.QUERY_ACTION;
	String USER_DETAIL_PAGE = "/system/user/userDetail.jsp";
	String CHOOSE_USER_PAGE = "/workorder/util/chooseUser.jsp";
	String CHANGE_USER_PAGE ="/system/user/changeUserPassword.jsp";  //�޸��û�����ҳ��
	String BARCODE_RECEIVE_PAGE = "/print/barcodeReceiveInfo.jsp";		 //��ǩ����ά��ҳ��
    String BARCODE_PRINT_PAGE = "/print/barcodePrint.jsp";  //��ǩ���ô�ӡȷ��ά��ҳ��
	String BARCODE_RECEIVE_DETAIL_PAGE = "/print/barcodeReceiveDetail.jsp";	//��ǩ������ϸ��Ϣ
	String BARCODE_MAX_MAINTAIN_PAGE = "/print/barcodeMaxMaintain.jsp";	//����ǩ��ά��ҳ��
	String BARCODE_MAX_MAINTAIN_DETAIL = "/print/barcodeMaxMaintainDetail.jsp";	//����ǩ����ϸ��Ϣҳ��
	//==============================================�û���Ϣά����ҳ��============================

	//=================================������Ϣά��ҳ��===================================
	String ORDER_DIFF_PAGE = "/workorder/order/orderDiffInfo.jsp";
	//������ѯ
	String QUERY_INTEGRATION = "/workorder/queryIntegration.jsp"; //�����ۺϲ�ѯ
	String TRUN_LIST_DIFFERENT = "/workorder/trunListDifferent.jsp"; //ת���嵥�����ѯ
	String TRUN_LIST_DIFFERENT_QUERY = "/workorder/trunListDifferentQuery.jsp"; //ת���嵥�����ѯ
	String TRUN_LIST_DITAIL_QUERY = "/workorder/trunListDetailQuery.jsp"; //���ӹ�����ϸ��ѯ
	String TRUN_LIST_QUERY = "/workorder/trunListQuery.jsp"; //ת���嵥��ѯ
	String TRUN_LIST_PRINT_QUERY = "/workorder/trunListPrintQuery.jsp"; //ת�������ӡ
	String TRUN_LIST_PRINT_DETAIL = "/workorder/trunListPrintDetail.jsp"; //ת�������ӡ_���ǩ60*38mm
	String TRUN_LIST_PRINT_SIMPLE_DETAIL = "/workorder/trunListPrintSimpleDetail.jsp"; //ת�������ӡ_С��ǩ60*10mm
    String TRUN_LIST_PRINT_HISTORY_DETAIL="/workorder/trunListPrintHistoryDetail.jsp"; //��ӡ��ǩ�ŵ���ϸ��Ϣ
	String ORDER_PROCESS = "/workorder/orderProcessView.jsp";   //�������
	String QUERY_BATCH = "/workorder/queryBatch.jsp";       //��������������ѯ
	String ORDER_DETAIL = "/workorder/orderDetail.jsp";    //������Ϣ��ѯ
	String ORDER_DETAIL_SERVLET = "/servlet/com.sino.ams.workorder.servlet.OrderDetailServlet";//������ϸ��Ϣ
	String QUERY_INTEGRATION_SERVLET = "/servlet/com.sino.ams.workorder.servlet.QueryIntegrationServlet"; //����������ϸ��Ϣ
	String QUERY_BATCH_DTL = "/workorder/queryBatchDtl.jsp";   //����������ϸ��Ϣ
	//bts
	String WORKORDER_NEW = "/workorder/bts/batchForm_bts.jsp";   //��վרҵ
	String WORKORDER_TMP_PAGE = "/workorder/bts/workorderTmp.jsp"; //��վ��
	String WORKORDER_CHOOSE_PAGE = "/workorder/bts/chooseWorkorders.jsp";  //��������
	String TMP_WORKORDER_INFO = "/workorder/bts/workorderTmpDetail.jsp";//��վ����ϸ

	String WORKORDER_TMP_SERVLET = "/servlet/com.sino.ams.workorder.servlet.WorkOrderTmpServlet";
	String WORKORDER_CHOOSE_SERVLET = "/servlet/com.sino.ams.workorder.servlet.WorkorderChooseSevrlet";
	//data
	String WORKORDER_NEW_DATA = "/workorder/data/data_batchForm.jsp";   //����רҵ
	String DATA_NEW = "/workorder/data/data.jsp";//����רҵ
	String DATE_LINE = "/workorder/data/dateLine.jsp";//������רҵ
	String DATE_DETAIL = "/workorder/data/dateDetail.jsp";//��������ϸ
    String HANDOVER_NEW = "/workorder/handover.jsp"; //�������ӹ���
    String HANDOVER_DETAIL = "/workorder/handoverDetail.jsp"; //���ӹ�������ϸ

	String WORKORDER_TMP_PAGE_DATA = "/workorder/data/data_orderTmp.jsp";
	//    String WORKORDER_CHOOSE_PAGE_DATA = "/workorder/data/chooseWorkorders.jsp";
	String NET_BETTER = "/workorder/net/netBetter.jsp";   //����רҵ
	String NET_BETTER_LINE = "/workorder/net/netBetterLine.jsp";//��������Ϣ
	String NET_BETTET_DETAIL = "/workorder/net/netBetterDetail.jsp";//��������ϸ

	String CHANG_NEW = "/workorder/chang/chang.jsp";//����רҵ
	String CHANG_LINE = "/workorder/chang/changLine.jsp"; //��������Ϣ
	String CHANG_DETAIL = "/workorder/chang/changDetail.jsp"; //��������ϸ

	String ELECTRI_NEW = "/workorder/electric/electri.jsp";  //����רҵ
	String ELECTRI_LINE = "/workorder/electric/electriLine.jsp";//��������Ϣ
	String ELECTRI_DETAIL = "/workorder/electric/electriDetail.jsp"; //��������ϸ

	String MONITOR_NEW = "/workorder/monitor/monitor.jsp";  //���רҵ
	String MONITOR_LINE = "/workorder/monitor/monitorLine.jsp"; //�������Ϣ
	String MOINTOR_DETAIL = "/workorder/monitor/mointorDetail.jsp"; //�������ϸ

	String TRANSFER_NEW = "/workorder/transfer/transfer.jsp";  //����רҵ
	String TRANSFER_LINE = "/workorder/transfer/transferLine.jsp";  //��������Ϣ
	String TRANSFER_DETAIL = "/workorder/transfer/transferDetail.jsp"; //��������ϸ

	String TMP_WORKORDER_INFO_DATA = "/workorder/data/data_orderTmpDetail.jsp";
	String WOERK_PERSON_QUERY="/workorder/workPersonQuery.jsp"; //����ִ�����ۺϲ�ѯ
	String DISTRI_AGAIN ="/workorder/distriAgain.jsp";//�������·����ѯҳ��
	String CONFIRM_IMPLEMENT = "/workorder/confirmImplement.jsp";//����ѡ������ִ����

	String SCAN_MONITOR_RPT = "/workorder/report/scanMonitorRpt.jsp";//Ѳ���ر���
	String SCAN_RESPONS_RPT = "/workorder/report/locResponsibleScan.jsp";//�ض���ά��˾
	String MAIN_RES_LOC = "/workorder/report/responsibleLocs.jsp";//�ض���ά��˾�����εص�
	String SCAN_LOC_Y = "/workorder/report/scanedLocs.jsp";//�ض���ά��˾�����εص�
	String SCAN_LOC_N = "/workorder/report/notScanedLocs.jsp";//�ض���ά��˾�����εص�

	String SCAN_ITEMS_RPT = "/workorder/report/itemResponsibleScan.jsp";//�ض���ά��˾�����ض��ص��ɨ�����
	String LOC_ITEM = "/workorder/report/locationItems.jsp";//��ǰ�ص��豸
	String LOC_SCANED_ITEM = "/workorder/report/locationScanedItems.jsp";//��ǰ�ص���Ѳ���豸
	String LOC_DIFF_ITEM = "/workorder/report/locationDiffItems.jsp";//��ǰ�ص�δѲ���豸

	String GROUP_RESULT_RPT = "/workorder/report/groupResultRpt.jsp";//Ѳ��������(������ͳ��)
	String GROUP_FRAME_RPT = "/workorder/report/groupFrm.jsp";//���ҳ��(������ͳ��)
	String SCAN_GROUP_PAGE = "/workorder/report/scanGroup.jsp";//Ѳ�������Ϣ(������ͳ��)
	String GROUP_LOCATION_PAGE = "/workorder/report/groupLocations.jsp";//Ѳ��ص���ϸ��Ϣ(������ͳ��)
	String GROUP_ITEM_PAGE = "/workorder/report/groupItems.jsp";//Ѳ���豸��ϸ��Ϣ(������ͳ��)
	String COMP_ITEM_REPORT = "/workorder/report/compItemReport.jsp";//����˾ͳ��Ѳ���豸
	String COMP_ITEM_DETAIL = "/workorder/report/compItemDetail.jsp";//����˾Ѳ���豸�б�
	String COMP_OWN_ITEM = "/workorder/report/compOwnItems.jsp";
	String COMP_SCANED_ITEM = "/workorder/report/compScanedItems.jsp";
	String COMP_NOT_SCANED_ITEM = "/workorder/report/compNotScanedItems.jsp";

	String DEPT_ITEM_REPORT = "/workorder/report/deptItemReport.jsp";//�����β���ͳ��Ѳ���豸
	String DEPT_ITEM_DETAIL = "/workorder/report/deptItemDetail.jsp";//�����β���Ѳ���豸�б�
	String DEPT_OWN_ITEM = "/workorder/report/deptOwnItems.jsp";
	String DEPT_SCANED_ITEM = "/workorder/report/deptScanedItems.jsp";
	String DEPT_NOT_SCANED_ITEM = "/workorder/report/deptNotScanedItems.jsp";
	//=================================������Ϣά��ҳ�� end===================================

	//======================================begin=�豸����ά����ҳ��============================
	String EQUIP_DETAIL_PAGE = "/system/item/equipMainInfo.jsp";
	String EQUIP_QUERY_PAGE = "/system/item/equipMainSearch.jsp";
	String EQUIP_QUERY_SERVLET = "/servlet/com.sino.ams.system.item.servlet.EtsSystemItemServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DISTRI_EQUIP_PAGE = "/system/item/equipDistriSearch.jsp";
	String DISTRI_DETAIL_PAGE = "/system/item/equipDistriInfo.jsp";
	String DISTRI_EQUIP_QUERY_SERVLET = "/servlet/com.sino.ams.system.item.servlet.EquipDistriServlet?act=" + WebActionConstant.QUERY_ACTION;
	String SPARE_CONFIRM= "/system/part/sparePartConfirm.jsp";
	String CONFIRM_QUERY_SERVLET = "/servlet/com.sino.ams.system.part.servlet.PartConfirmServlet?act=" + WebActionConstant.QUERY_ACTION;
	//String SPARE_REJIGGER = "/system/part/spareRejigger.jsp"; //�������ģ��ϲ���
	String SPARE_REJIGGER = "/system/part/spareRejigger.jsp"; //�������ģ��ϲ���<******·�����ĺ�****> 
	String SPARE_DISTRI = "/system/part/spareDistri.jsp";//��������ȷ��(��ϸҳ��)
	String SPARE_QUERY = "/system/part/spareDistriQuery.jsp";// ���������ѯҳ��
	String SPARE_STATISTIC = "/system/part/spareStatistics.jsp"; //����ͳ��ҳ��
	String REASON_STATISTIC= "/system/part/reasonStatistics.jsp";//ԭ��ͳ��ҳ��
	//==============================================�豸����ά����ҳ��=end============================

	//==============================================��άά����ҳ��============================
	String TRUSTCOR_QUERY_SERVLET = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainCompanyServlet?act=" + WebActionConstant.QUERY_ACTION;
	String TRUSTCOR_QUERY_PAGE = "/system/trust/trustCompanyQuery.jsp";
	String TRUSTCOR_DETAIL_PAGE = "/system/trust/trustCompanyDetail.jsp";
	String TRUSTCOR_DETAIL_SERVLET = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainCompanyServlet?act=" + WebActionConstant.DETAIL_ACTION;

	String TRUSTCORUSR_QUERY_SERVLET = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainPeopleServlet?act=" + WebActionConstant.QUERY_ACTION;
	String TRUSTCORUSR_QUERY_PAGE = "/system/trust/trustCorpUsrQuery.jsp";
	String TRUSTCORUSR_DETAIL_PAGE = "/system/trust/trustCorpUsrDetail.jsp";

	String TRUSTCORRSP_SERVLET = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainResponsibilityServlet";
	String TRUSTCORRSP_QUERY_PAGE = "/system/trust/trustCorpRSPQuery.jsp";
	String CONFIRM_LOCATION_PAGE = "/system/trust/confirmLocation.jsp";
	String TRUSTCORRSP_CHAIN_PAGE = "/system/trust/trustCorpRSPChain.jsp";
	String TRUSTCORRSP_UNCHAIN_PAGE = "/system/trust/trustCorpRSPUnchain.jsp";
	String TRUST_ATTACH_FILE_PAGE = "/system/trust/uploadFile.jsp";
	String TRUST_ATTACH_FILE_SERVLET = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainFilesServlet?act=" + WebActionConstant.NEW_ACTION;
	//==============================================��άά����ҳ��============================

	//==============================================�ֵ�ά����ҳ��============================
	String DICT_FRM_PAGE = "/system/dict/dictionaryFrm.jsp";
	String DICT_TREE_PAGE = "/system/dict/dictTree.jsp";
	String DICT_DETAIL_PAGE = "/system/dict/dictDetail.jsp";
	String DICT_TYPE_QRY_SERVLET = "/servlet/com.sino.ams.system.dict.servlet.EtsFlexValueSetServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DICT_TYPE_QRY_PAGE = "/system/dict/dictTypeQuery.jsp";
	String DICT_TYPE_DETAIL_PAGE = "/system/dict/dictTypeDetail.jsp";
	String DICT_QUERYRY_SERVLET = "/servlet/com.sino.ams.system.dict.servlet.EtsFlexValuesServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DICT_QUERY_PAGE = "/system/dict/dictQuery.jsp";
	
	String DICT_ANALYSE_FRM_PAGE = "/system/dict/dictionaryAnalyseFrm.jsp";
	String DICT_ANALYSE_TREE_PAGE = "/system/dict/dictAnalyseTree.jsp";
	String DICT_ANALYSE_DETAIL_PAGE = "/system/dict/dictAnalyseDetail.jsp";
	String DICT_ANALYSE_TYPE_QRY_SERVLET = "/servlet/com.sino.ams.system.dict.servlet.EtsFlexAnalyseValueSetServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DICT_ANALYSE_TYPE_QRY_PAGE = "/system/dict/dictAnalyseTypeQuery.jsp";
	String DICT_ANALYSE_TYPE_DETAIL_PAGE = "/system/dict/dictAnalyseTypeDetail.jsp";
	String DICT_ANALYSE_QUERYRY_SERVLET = "/servlet/com.sino.ams.system.dict.servlet.EtsFlexAnalyseValuesServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DICT_ANALYSE_QUERY_PAGE = "/system/dict/dictAnalyseQuery.jsp";
	//==============================================�ֵ�ά����ҳ��============================

	//==============================================��վά�޷�ҳ��============================
	String OBJECT_BTS = "/others/bts/objectBTS.jsp";
	String OBJECT_BTS_DETAIL = "/others/bts/objectBTSDetail.jsp";
	String OBJECT_ELE = "/others/ele/objectELE.jsp";
	String OBJECT_ELE_DETAIL = "/others/ele/objectELEDetail.jsp";
	String OBJECT_SPARE = "/others/spare/objectSpare.jsp";
	String OBJECT_SPARE_DETAIL = "/others/spare/objectSpareDetail.jsp";
	String OBJECT_BTS_QUERY = "/servlet/com.sino.ams.web.bts.servlet.EtsObjectFixfeeServlet?act=" + WebActionConstant.QUERY_ACTION;
	String OBJECT_ELE_QUERY = "/servlet/com.sino.ams.web.ele.servlet.EtsObjectEleServlet?act=" + WebActionConstant.QUERY_ACTION;
	String OBJECT_ITEM_QUERY = "/servlet/com.sino.ams.web.item.servlet.EtsItemFixfeeServlet?act=" + WebActionConstant.QUERY_ACTION;
	String ITEM_FIXING_QUERY = "/system/fixing/itemInfoQuery.jsp";
	String ITEM_FIXING_DETAIL = "/system/fixing/itemInfoDetail.jsp";
	String ITEM_FIXING_SEARCH = "/servlet/com.sino.ams.system.fixing.servlet.EtsItemInfoServlet?act=" + WebActionConstant.QUERY_ACTION;

	//==============================================��վά�޷�ҳ��============================

	//============================= bigin��רҵ�ص����======================================================
	String COMPAR_SEARCH = "/system/comparison/groupComparSearch.jsp";
	String COMPAR_INFO = "/system/comparison/groupComparInfo.jsp";
	String GROUP_COMPAR_QUERY ="/system/comparison/groupLocQuery.jsp";
	String GROUP_COMPAR_DETAIL ="/system/comparison/groupLocInfo.jsp";
	//====================================end ���רҵ����===================================================

	//==============================================begin=��Ŀ��Ϣά��ҳ��============================
	String PROJECT_QUERY_PAGE = "/system/project/maintenance/projectMainSearch.jsp";
	String PROJECT_DETAIL_PAGE = "/system/project/maintenance/projectInfo.jsp";
	String PROJECT_QUERY_SERVLET = "/servlet/com.sino.ams.system.project.servlet.EtsPaProjectsAllServlet?act=" + WebActionConstant.QUERY_ACTION;
	//==============================================��Ŀ��Ϣά��ҳ��=end============================   //


	// ==============================================begin=������Ϣά��ҳ��============================
	String COUNTY_QUERY = "/system/county/countyQuery.jsp";
	String COUNTY_INFO = "/system/county/countyInfo.jsp";
	String COUNTY_QUERY_SERVLET= "/servlet/com.sino.ams.system.county.servlet.EtsCountyServlet?act=" + WebActionConstant.QUERY_ACTION;
	//==============================================������Ϣά��ҳ��=end============================

	//===============================================�ʲ���������=============================
	String ASSETS_SERVLET = "/servlet/com.sino.ams.assets.servlet.AmsAssetsTransHServlet";
	String ASSETS_ODDTL_SERVLET = "/servlet/com.sino.ams.assets.servlet.AmsAssetsTransHServlet?act=" + WebActionConstant.DETAIL_ACTION;
	String ASSETS_TRANS_EDIT = "/assets/assetsTransEdit.jsp";
	String ASSETS_FRM_PAGE = "/assets/assetsFrm.jsp";
	String ASSETS_TREE_PAGE = "/assets/assetsTree.jsp";
	String ASSETS_QRY_PAGE = "/assets/assetsQuery.jsp";
	String ASSETS_QRY_SERVLET = "/servlet/com.sino.ams.assets.servlet.AmsFaAssetsServlet?act=" + WebActionConstant.QUERY_ACTION;
	String ASSETS_DTL_PAGE = "/assets/assetsDetail.jsp";//�ʲ���ϸ��Ϣ
	String ASSETS_SHARE_FLOW_PAGE = "/newassets/assetsShareFlow.jsp";	//�ʲ���������ҳ��
	String ASSETS_SHARE_FLOW_DEATIL_PAGE = "/assets/assetsShareFlowDeail.jsp"; //�ʲ�����������ϸ��Ϣҳ��
	String ASSETS_SHARE_FLOW_EDIT_PAGE = "/newassets/assetsShareFlowEdit.jsp"; //�޸��ʲ�����������ϸ��Ϣҳ��

	//==============================================begin=�ص�ά��ҳ��============================
	//-------------------------------- ��վ�ص�ά��ҳ�� -----------------------
	String BASEPOINT_QUERY_PAGE = "/system/basepoint/basePointSearch.jsp";
	String BASEPOINT_DETAIL_PAGE = "/system/basepoint/basePointInfo.jsp";
	String BASEPOINT_QUERY_SERVLET = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet?act=" + WebActionConstant.QUERY_ACTION;
	//--------------------------------- ���������ص�ά��ҳ��--------------------
	String SWITCHES_QUERY_PAGE = "/system/switches/switchesSearch.jsp";
	String SWITCHES_DETAIL_PAGE = "/system/switches/switchesInfo.jsp";
	String SWITCHES_QUERY_SERVLET = "com.sino.ams.system.switches.servlet.EtsObjectServlet?act=" + WebActionConstant.QUERY_ACTION;
	String SWITCHES_SERVLET = "com.sino.ams.system.switches.servlet.EtsObjectServlet";
	//--------------------------------- �����豸ά��ҳ��------------------------
	String OTHER_LOCATIONS_QUERY = "/system/specialty/otLocationQuery.jsp";   //�����ص�
	String OTHER_LOCATIONS_INFO = "/system/specialty/otLocationInfo.jsp";
	String OTHER_EQUIPMENTS_QUERY = "/system/specialty/otEquipmentQuery.jsp";   //�����豸
	String OTHER_EQUIPMENTS_INFO = "/system/specialty/otEquipmentInfo.jsp";
	String OTHER_QUERY_SERVLET = "/servlet/com.sino.ams.system.specialty.servlet.OtLocsVindicateServlet?act=" + WebActionConstant.QUERY_ACTION;

	//==============================================�ص�ά��ҳ��=end============================

	//==============================================����������ҳ��============================
	String SPARE_IN_QRY_PAGE = "/nm/spare/sparePoInQuery.jsp";
	String SPARE_IN_DTL_PAGE = "/spare/sparePoInDetail.jsp";
	String SPARE_DB_QRY_PAGE = "/spare/bjdbQuery.jsp";     //����ҳ��
	String SPARE_DB_DTL_PAGE = "/spare/bjdbDetail.jsp";
	//==============================================����������ҳ��============================

	//==============================================���̹���ҳ��============================
	String PROCEDURE_QUERY_PAGE = "/system/procedure/procedureQuery.jsp";
	String PROCEDURE_DETAIL_PAGE = "/system/procedure/procedureDetail.jsp";
	//==============================================��������ҳ��============================

	//========================================begin=====�����ʲ�ά��ҳ��===========================
	//---------------------------------����ά��ҳ��===========================
	String HOUSE_QUERY_PAGE = "/system/house/houseSearch.jsp";
	String HOUSE_DETAIL_PAGE = "/system/house/houseInfo.jsp";
	String HOUSE_SERVLET = "/servlet/com.sino.ams.system.house.servlet.AmsHouseInfoServlet";
	String HOUSE_DETAIL_SERVLET = "/servlet/com.sino.ams.system.house.servlet.AmsHouseInfoServlet?act=" + WebActionConstant.DETAIL_ACTION;
	String HOUSE_ATTACH_FILE_PAGE = "/system/house/uploadItemFile.jsp";
	//---------------------------------����ά��ҳ��===========================
	String LAND_QUERY_PAGE = "/system/house/landSearch.jsp";
	String LAND_DETAIL_PAGE = "/system/house/landInfo.jsp";
	String LAND_DETAIL_SERVLET = "/servlet/com.sino.ams.system.house.servlet.AmsLandInfoServlet?act=" + WebActionConstant.DETAIL_ACTION;
	//---------------------------------��������ҳ��===========================
	String NOTE_QUERY_PAGE = "/system/note/noteSearch.jsp";
	String NOTE_DETAIL_PAGE = "/system/note/noteInfo.jsp";
	String NOTE_SERVLET = "/servlet/com.sino.ams.system.note.servlet.AmsRentDeadlineServlet";
	//==============================================����ά��ҳ��==end==========================

	//=============================================�ֿ�Ȩ��ά��ҳ��===========================
	String INV_PRIVI_QUERY = "/nm/spare/invPrivi/invPriviQuery.jsp";
	String INV_PRIVI_SERVLET = "/servlet/com.sino.nm.ams.spare.invprivi.servlet.AmsInvPriviServlet?act=" + WebActionConstant.QUERY_ACTION;
	//==============================================�ֿ�Ȩ��ά��ҳ��============================

	//=============================================�����汾����ҳ��===========================
	String ITEM_MAINTENANCE = "/spare/version/maintenance.jsp";
	String ITEM_MAINTENANCE_QUERY = "spare/version/maintenanceQuery.jsp";//
	//==============================================�����汾����ҳ��============================
	//=============================================�Ǿ�ά��ҳ��===========================
	String INSTRUMENT_QUERY = "/instrument/instrumentQuery.jsp";
	String INSTRUMENT_DETAIL = "/instrument/instrumentDeatli.jsp";
	//==============================================�Ǿ�ά��ҳ��============================

	//==============================================����ά��ҳ��============================
	String CABEL_INFO_QUERY = "/others/cabel/cableInfoQuery.jsp";
	String CABEL_INFO_DETAIL = "/others/cabel/cableInfoDetail.jsp";
	String CABEL_INFO_SERVLET = "/servlet/com.sino.ams.others.cabel.servlet.AmsCabelInfoServlet";

	//==============================================����ά��ҳ��============================

	//==============================================��������ҳ��============================
	String ALLOT_PAGE = "/spare/allot/allot.jsp";
	String ALLOT_SUB_PAGE = "/spare/allot/OuList.jsp";
	String ALLOT_SERVLET = "/srvlet/com.sino.ams.spare.allot.servlet.AmsItemTransDServlet";
	//==============================================��������ҳ��============================

	//==============================================������ʾҳ��============================
	String MESSAGE_PROCESS = "/servlet/com.sino.framework.servlet.MessageProcessServlet";
	//==============================================������ʾҳ��============================

	//==============================================�ص��ѯҳ��============================
	String LOCUS_QUERY_PAGE = "/net/locus/locusQuery.jsp";

	String LOCUS_DETAIL_BTS_PAGE = "/net/locus/locusDetail.jsp";        //��վ
	String LOCUS_DETAIL_COMM_PAGE = "/net/locus/locusCommDetail.jsp";  //����
	String LOCUS_UNCHECK_QUERY_PAGE = "/net/locus/locusUncheckQuery.jsp";
	String LOCUS_SERVLET = "/servlet/com.sino.ams.net.locus.servlet.LocusServlet";
	//==============================================�ص��ѯҳ��============================

	//==============================================�豸��ѯҳ��============================
	String QRY_BY_PROJ_PAGE = "/net/equip/projQuery.jsp";
	String QRY_BY_BARCODE_PAGE = "/net/equip/barcodeQuery.jsp";
	String QRY_BY_SPEC_PAGE = "/net/equip/specQuery.jsp";
	String QRY_BY_CATE_PAGE = "/net/equip/cateQuery.jsp";
	String QRY_BY_LOCUS_PAGE = "/net/equip/locusQuery.jsp";
	String QRY_BY_ALLOT_PAGE = "/net/equip/allotQuery.jsp";
	String QRY_BY_DAIWEI_PAGE = "/net/equip/daiweiQuery.jsp";//����ά��˾��ѯҳ��
	String QRY_ITEM_SERVLET = "/servlet/com.sino.ams.net.equip.servlet.ItemInfoServlet";
	String QRY_BY_INTEGRATEDSERVLET="/servlet/com.sino.ams.net.equip.servlet.IntegratedServlet";
	String QRY_BY_INTAGRATEDQUERY_SERVLET="/servlet/com.sino.ams.net.equip.servlet.IntagratedQueryServlet";
	String QRY_BY_INTEGRATED= "/net/equip/Integrated.jsp";
	String QRY_BY_INTAGRATEDQUERY="/net/equip/IntagratedQuery.jsp";
	String PLANTMESSAGE="/net/equip/system/plantMessage.jsp";
	String MIS_SPARE_PAGE = "/net/equip/misSpare.jsp";//MIS�����豸��ѯҳ��
	String QRY_SPARE_SERVLET = "/servlet/com.sino.ams.net.equip.servlet.MisSpareQueryServlet";//��ѯMIS�����豸SERVLET
	//==============================================�豸��ѯҳ��============================

	//==============================================ͳ��ҳ��============================
	String SITUS_STATISTIC_QUERY = "/net/reportforms/situsStatForm.jsp";  //����ͳ��--���ص�
	String ORDER_STATISTIC_QUERY = "/net/reportforms/orderStatLocation.jsp";  //����ͳ��--���ص�(������)
	String CITY_COMPLETE_STATISTIC = "/net/reportforms/cityComplete.jsp"; //���й�����ɼ�ʱ��
	String INDIVIDUAL_RATE_STATISTIC = "/net/reportforms/individualComRate.jsp"; //��Ա������ɼ�ʱ��
	String FALSE_RATE_STATISTIC = "/net/reportforms/falsityRate.jsp";   //����������
	String PATROL_ORDER_DIFFERENT_QUERY = "/workorder/patrolOrderDifferentQuery.jsp"; //Ѳ���嵥�����ѯ
	//--�豸������ͳ��--
	String STAT_EQP_BY_LOCUS_PAGE = "/net/statistic/equip/locusEStat.jsp";     //���ص�
	String STAT_EQP_BY_CATE_PAGE = "/net/statistic/equip/cateEStat.jsp";       //������
	String STAT_EQP_BY_CATE_PAGE2 = "/net/statistic/equip/cateIStat.jsp";       //������
	String STAT_EQP_BY_NAME_PAGE = "/net/statistic/equip/proviEStat.jsp";       //������
	String STAT_EQP_BY_VENDOR_PAGE = "/net/statistic/equip/vendorEStat.jsp";       //����Ӧ��
	String STAT_EQP_BY_VENDOR_PAGE2 = "/net/statistic/equip/vendorIStat.jsp";       //����Ӧ��

	String STAT_EQP_SERVLET = "/servlet/com.sino.ams.net.statistic.servlet.EquipStatServlet";    //����servlet
	//--����ͳ��
	String STAT_WO_CHECK_PAGE = "/net/statistic/aviso/checkStat.jsp";
	String STAT_WO_MONTH_PAGE = "/net/statistic/aviso/monthStat.jsp";
	String STAT_WO_TIME_PAGE = "/net/statistic/aviso/timeStat.jsp";
	String STAT_WO_YEAR_PAGE = "/net/statistic/aviso/yearStat.jsp";
	String STAT_WO_SERVLET = "/servlet/com.sino.ams.net.statistic.servlet.AvisoStatServlet";
    String WORKORDER_STATISTICS_PAGE = "/newasset/report/workorderStatistics.jsp";
    String WORKORDER_STATISTICS_SERVLET = "/servlet/com.sino.ams.newasset.report.servlet.WorkorderStatisticsServlet";
    String LOGIN_STATISTICS_PAGE = "/newasset/report/loginStatistics.jsp";
    String LOGIN_STATISTICS_SERVLET = "/servlet/com.sino.ams.newasset.report.servlet.LoginStatisticsServlet";
	//--�豸���޴���ͳ��
	// String QRY_ITEM_REPAIR ="/net/statistic/aviso/yearStat.jsp";
	String QRY_ITEM_REPAIR = "/others/spare/itemrepair/itemRepairQuery.jsp";
	String QRY_VENDOR_REPAIR = "/others/spare/itemrepair/vendorRepairQuery.jsp";
	String REPAIR_STATISTIC = "/others/spare/itemrepair/repairStatistic.jsp";
	String REPAIR_STATISTIC_VENDOR = "/others/spare/itemrepair/repairStatisticVendor.jsp";
	//==============================================ͳ��ҳ��============================

	//=======================================�����ʲ�ҳ��==================
	String INTANGIBLE_QUERY = "/system/intangible/intangible.jsp"; //�����ʲ�
	String RENT_SEARCH = "/system/rent/rentSearch.jsp";  //�����ʲ�
	String RENT_INFO = "/system/rent/rentInfo.jsp";//�����ʲ���ϸ
	String RENT_DETAIL = "/system/rent/rentDetail.jsp";  //�����ʲ���Ϣ
	String RENT_HISTORY_SEARCH = "/system/rent/rentHistory.jsp";  //�����ʲ��䶯��ʷ
	
	//=======================================�����ʲ�ҳ��==================

	//===============================�����ӡ����ҳ��================================
	String BARCODE_PRINT_INFO="/print/barcodePrintInfo.jsp";//�����ӡ���쵥����д
	String BARCODE_PRINT_STATISTIC="/print/barcodePrintStatic.jsp" ;//�����ӡͳ��
	String CHOOSE_PRINT_GROUP = "/print/printGroup.jsp";//��ǩ������ѡ�����
	//================================�����ӡ����ҳ��===============================

	//============================== ƥ��ҳ��===========================================
	//==============================================�ʲ�ƥ��==
	// ==========================
	String FINANCE_PROP_SET_PAGE = "/match/financePropSet.jsp";
	String FINANCE_PROP_SET_SERVLET = "com.sino.ams.match.servlet.EtsItemMatchRecServlet";
	String UNUSED_ASSETS = "/match/unusedAssets.jsp";
	String CHANGED_ASSETS = "/match/changedAssets.jsp";
	String CHANGED_MATCHL = "/match/changedMatchL.jsp";   //ת��ƥ��:����������Ϣ
	String CHANGED_MATCHR = "/match/changedMatchR.jsp";   //ת��ƥ��:MIS�ʲ���Ϣ
	String CHANGED_MATCHR_NOMACTINGASSET = "/match/nomatchingPage.jsp";   //δƥ���ʲ��嵥
	String CHANGED_MATCHR_MACTPROPERTY = "/match/mactProperty.jsp";//
	String BARCODE_MATCH = "/match/barcodeMatch.jsp";    //����ƥ��
	String BARCODE_MATCH_SERVLET = "/servlet/com.sino.ams.match.servlet.BarcodeMatchServlet";
	String EQUIPMENT_INFO = "/match/overplusEquipment.jsp";  //�ʲ�ƥ�䱨��ʣ���豸�嵥
	String ETS_ITEM_MATCHINFO = "/match/itemMatchAssit.jsp";
	String UNYOKE_PAGE = "/match/unyoke.jsp";       //           �����ʲ�ƥ���ϵ���ݶ���
//   String RENT_CONFIRMED = "/servlet/com.sino.ams.system.rent.servlet.RentConfirmedServlet";       //  �����ʲ�ȷ��=11�������ʲ�ȷ��=13
//   String RENT_CONFIRMED_PAGE = "/system/rent/rentConfirmed.jsp";       //  �����ʲ�ȷ��=11�������ʲ�ȷ��=13
	String MIS_ASSETS = "/match/misEquScreen.jsp"; //mis�ʲ�����ҳ��
	String PRACT_PAGE = "/match/amsPractInfo.jsp"; //ʵ���ʲ���Ϣ
	String ACCOUNT_PAGE = "/match/amsAccountInfo.jsp"; //�����ʲ���Ϣ
	String UNSYNCHRONIZED_ASSETS = "/match/unsynchronizedAssets.jsp"; //δͬ���ʲ��嵥

	//==============================================�ʲ�ƥ��============================
	
	//========================================TD�ʲ�ƥ��===========================================
	String FINANCE_PROP_SET_TD_SERVLET = "com.sino.td.match.servlet.EtsItemMatchTdRecServlet";
	String BARCODE_MATCH_TD_SERVLET = "/servlet/com.sino.td.match.servlet.BarcodeMatchTdServlet";
	String BARCODE_MATCH_TD = "/td/match/barcodeMatchTd.jsp";    //td����ƥ��
	String MANUAL_MATCH_TD_MIS="/td/match/manualMatchTdMIS.jsp";
	String MANUAL_MATCH_TD_AMS="/td/match/manualMatchTdAMS.jsp";
	String UNYOKE_TD_PAGE = "/td/match/unyokeTd.jsp";       //           �����ʲ�ƥ���ϵTD
	String CHANGED_MATCHR_MACTPROPERTY_TD = "/td/match/matchedTdList.jsp";//
	String CHANGED_MATCHR_NOMACTINGASSET_TD = "/td/match/noMatchedTdList.jsp";   //δƥ���ʲ��嵥
	//========================================TD�ʲ�ƥ��
	
	//===============�ص�ƥ��===================
	String AMS_MIS_LOC_MATCH = "/match/amsMisLocMatch/amsMisLocMatch.jsp";
	String AMS_MIS_LOC_INFO = "/match/amsMisLocMatch/amsMisLocInfo.jsp";
	String HIDE_MIS_PAGE = "/match/hideMisAsset.jsp";
	String AMS_LOGIC_NETWORK_MATCH_PAGE = "/match/amselementmatch/amsLogicNetworkMatch.jsp";	//�߼�����Ԫ�����Զ�Ӧ��ϵҳ��
	String AMS_LOGIC_NETWORK_MATCH_DETAIL = "/match/amselementmatch/amsLogicNetworkMatchDetail.jsp";	//�߼�����Ԫ�����Զ�Ӧ��ϵҳ��
	String AMS_CEX_MATCH_PAGE = "/match/amselementmatch/amsCexMatch.jsp";		//Ͷ�ʷ������ʲ�Ŀ¼��Ӧ��ϵҳ��
	String AMS_CEX_MATCH_DETAIL = "/match/amselementmatch/amsCexMatchDetail.jsp";	//Ͷ�ʷ������ʲ�Ŀ¼��Ӧ��ϵƥ��ҳ��
	String AMS_OPE_MATCH_PAGE = "/match/amselementmatch/amsOpeMatch.jsp";
	String AMS_OPE_MATCH_DETAIL = "/match/amselementmatch/amsOpeMatchDetail.jsp";
	String AMS_NLE_MATCH_PAGE = "/match/amselementmatch/amsNleMatch.jsp";
	String AMS_NLE_MATCH_DETAIL = "/match/amselementmatch/amsNleMatchDetail.jsp";
	
	String NLE_PAGE = "/system/manydimensions/nleQuery.jsp";
	String NLE_DETAIL_PAGE = "/system/manydimensions/nleDetail.jsp";
	String OPE_DETAIL_PAGE = "/system/manydimensions/opeDetail.jsp";
	String OPE_PAGE = "/system/manydimensions/opeQuery.jsp";
	String CEX_PAGE = "/system/manydimensions/cexQuery.jsp";
	String CEX_DETAIL_PAGE = "/system/manydimensions/cexDetail.jsp";
	String LNE_PAGE = "/system/manydimensions/lneQuery.jsp";
	String LNE_DETAIL_PAGE = "/system/manydimensions/lneDetail.jsp";
	//===============�ص�ƥ��===================
   
	//=================================ƥ��ҳ��==============================================

	//=================================ͬ��ҳ��==============================================
	String EAM_NEW_LOCUS = "/synchronize/eamNewLocus.jsp";  //EAM�����ص�ͬ��
	      
	String EAM_NEW_SERVLET = "/servlet/com.sino.ams.synchronize.servlet.EamNewLocusServlet";
	String NET_ASSETS_UPDATE = "/synchronize/netAssetsUpdate.jsp";//��·�ʲ�����
	String ASSETS_UPDATE = "/synchronize/assetsUpdate.jsp";   //�ʲ����ͬ��
    String ASSETS_CHANGE_SYN = "/synchronize/assetsChangeSyn.jsp";
	String ASSETS_BARCODE ="/synchronize/assetsBarCodeUpdate.jsp";//����ͬ��
	String ASSETS_COMMITS = "/synchronize/assetsCommit.jsp";//�ʲ��䶯ֱ��ͬ��
	String ASSETS_DISCARDED_SYN = "/synchronize/assetsDiscardeds.jsp";//�ʲ�����ͬ��
	String TRANS_DETIL = "/synchronize/transStatusDetail.jsp";
	String TRANS_QUERY = "/synchronize/transStatusQuery.jsp";     //��ѯҳ��
	String MESSAGE_QRY_PAGE = "/system/message/messageQuery.jsp";
	String MESSAGE_DTL_PAGE = "/system/message/messageDetail.jsp";
	String MESSAGE_SERVLET = "/servlet/com.sino.ams.system.message.servlet.SfMsgCategoryServlet";

	String USER_LOG_PAGE = "/system/log/userActionLog.jsp";//�û�������־
	String SYS_LOG_PAGE = "/system/log/sysRunLog.jsp";//ϵͳ���м�¼��־

	String PA_ASSETS_QRY = "/match/prematch/paAssetsQuery.jsp";//ת��׼���嵥��ѯ����
	String PA_MATCH_AMS = "/match/prematch/paMatchAmsQuery.jsp";//AMS��ת��ƥ�����
	String AUTO_MATCH_PAGE = "/match/prematch/autoMatchQuery.jsp";//ת��ƥ�����(�Զ�ƥ��)
	String PA_MATCH_FRM = "/match/prematch/paMatchFrm.jsp";//ƥ����ҳ��
	String MAN_MATCH_FRM = "/match/prematch/manualMatchFrm.jsp";//ģ��ƥ����ҳ��
	String PA_MATCH_MIS = "/match/prematch/paMatchMisQuery.jsp";//MIS��ת��ƥ�����
	String AUTO_MATCH_SERVLET = "/servlet/com.sino.ams.prematch.servlet.AutoMatchServlet";//ת��ƥ�����(�Զ�ƥ��)
	String MANUAL_FRM_SERVLET = "/servlet/com.sino.ams.prematch.servlet.ManualMatchFrmServlet";//�ֹ�ƥ����servlet
	String MATCH_SERVLET = "/servlet/com.sino.ams.prematch.servlet.AmsPaMatchServlet";//ƥ��servlet
	String MATCHED_LIST_PAGE = "/match/prematch/matchedList.jsp";//��ƥ�����
	String MATCH_RESULT = "/match/matchResult.jsp";//ƥ���ر���ҳ��
	String MATCH_RESULT_SERVLET = "/servlet/com.sino.ams.match.servlet.MatchResultServlet";//ƥ���ر���servlet

//	String HDV_ORDER_SERVLET = "/workorder/handover/hdvOrderQuery.jsp";//���ӹ�����ѯ
	String HDV_ORDER_SERVLET = "/servlet/com.sino.ams.workorder.servlet.HandOverBatchServlet";
	String HDV_ORDER_QUERY = "/workorder/handover/hdvOrderQuery.jsp";//���ӹ�����ѯ
	String HDV_ORDER_EDIT = "/workorder/handover/hdvOrderEdit.jsp";//���ӹ����༭
	String HDV_ORDER_DETL = "/workorder/handover/hdvOrderDetl.jsp";//���ӹ�����ϸ��Ϣ

	String ADDRESS_TAG_PAGE = "/system/object/addressTagNumQuery.jsp";
	String OBJECT_QUERY_PAGE = "/system/object/objectQuery.jsp";
	String OBJECT_PRINT_DETAIL_PAGE = "/system/object/objectPrintDetail.jsp";
	String OBJECT_DETAIL_PAGE = "/system/object/objectDetail.jsp";
	String ASSETS_NOMATCH_RESPONSIBLE = "/system/object/assetsNoMatchResponsibleSearch.jsp"; //�������벿�Ų����ѯ
	String OBJECT_LIST_PAGE	= "/system/object/objectList.jsp";//չ�ֵص����Ƶ��м��ֶ�
	String COMM_OBJECT_SERVLET = "/servlet/com.sino.ams.system.object.servlet.CommonObjectServlet";
	String OBJECT_MAX_PAGE = "/system/object/objectMax.jsp";
	String OBJECT_MAX_SERVLET = "/servlet/com.sino.ams.system.object.servlet.ObjectMaxServlet";

	String ADDRESS_CHANGED ="/synchronize/addressChanged.jsp"; //�ص���Ϣ���ͬ��
	String ADDRESS_CHANGED_SERVLET ="/servlet/com.sino.ams.synchronize.servlet.AddressChangedServlet"; //�ص���Ϣ���ͬ��Servlet

	//	=================================��ֵ�׺���ά��ҳ��==============================================
	String DZYHCATALOG_FRM_PAGE = "/dzyh/catalog/dzyhCatalogFrm.jsp";
	String DZYHCATALOG_TREE_PAGE = "/dzyh/catalog/dzyhCatalogTree.jsp";
	String DZYHCATALOG_DETAIL_PAGE = "/dzyh/catalog/dzyhCatalogDetail.jsp";
	String DZYHCATALOG_TYPE_QRY_SERVLET = "/servlet/com.sino.ams.dzyh.servlet.EamDhCatalogSetServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DZYHCATALOG_TYPE_QRY_PAGE = "/dzyh/catalog/dzyhCatalogTypeQuery.jsp";
	String DZYHCATALOG_TYPE_DETAIL_PAGE = "/dzyh/catalog/dzyhCatalogTypeDetail.jsp";
	String DZYHCATALOG_QUERYRY_SERVLET = "/servlet/com.sino.ams.dzyh.servlet.EamDhCatalogValuesServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DZYHCATALOG_QUERY_PAGE = "/dzyh/catalog/dzyhCatalogQuery.jsp";
	//	=================================��ֵ�׺���ά��ҳ��==============================================

	//	=================================��ֵ�׺�Ȩ�޶���ά��ҳ��==============================================
	String DZYH_PRIVI_QRY = "/dzyh/privi/priviQuery.jsp";
	String DZYH_PRIVI_DTL = "/dzyh/privi/priviDetail.jsp";
	String DZYH_PRIVI_TOP = "/dzyh/privi/priviTop.jsp";
	String DZYH_PRIVI_LEFT = "/dzyh/privi/priviLeft.jsp";
	String DZYH_PRIVI_RIGHT = "/dzyh/privi/priviRight.jsp";
	String DZYH_PRIVI_FRM = "/dzyh/privi/priviFrm.jsp";
	String DZYH_PRIVI_SERVLET = "/servlet/com.sino.ams.dzyh.servlet.EamDhPriviServlet";
	//String PRIVI_RIGHT_SERVLET = "/servlet/com.sino.ams.newasset.servlet.PriviRightServlet";
	//	=================================��ֵ�׺�Ȩ�޶���ά��ҳ��==============================================
	
	//	================================= ��ֵ�׺ĵص�ά��ҳ�� =================================
	String DZYH_QUERY_PAGE = "/dzyh/address/addressSearch.jsp";
	String DZYH_DETAIL_PAGE = "/dzyh/address/addressInfo.jsp";
	String DZYH_QUERY_SERVLET = "/servlet/com.sino.ams.dzyh.servlet.EtsObjectServlet?act=" + WebActionConstant.QUERY_ACTION;
	// ================================= ��ֵ�׺ĵص�ά��ҳ�� =================================
	
	// ================================= �����Ǳ�ص�ά��ҳ�� =================================
	String INSTRUMENT_QUERY_PAGE = "/instrument/instrumentAddressSearch.jsp";
	String INSTRUMENT_DETAIL_PAGE = "/instrument/instrumentAddressInfo.jsp";
	String INSTRUMENT_QUERY_SERVLET = "/servlet/com.sino.ams.instrument.servlet.AmsInstrumentEamYbObjectNoServlet?act=" + WebActionConstant.QUERY_ACTION;
	// ================================= �����Ǳ�ص�ά��ҳ�� =================================
	
	//	================================= ��ֵ�׺�̨�˲�ѯҳ�� =================================
	String DZYH_TZHZ_QUERY_PAGE = "/dzyh/query/summaryQuery.jsp";
	String DZYH_TZ_QUERY_PAGE = "/dzyh/query/dzyhQuery.jsp";
	String DZYH_TZ_DETAIL_PAGE = "/dzyh/query/dzyhDetail.jsp";
	String DZYH_ENTER_QUERY_PAGE = "/dzyh/query/enterDzyhQuery.jsp";
	String DZYH_ENTER_DETAIL_PAGE = "/dzyh/query/enterDzyhDetail.jsp";
	String DZYH_ENTER_QRY_SERVLET = "/servlet/com.sino.ams.dzyh.servlet.EamDhBillHServlet?act=" + WebActionConstant.QUERY_ACTION;
	String DZYH_ADD_QUERY_PAGE = "/dzyh/query/addDzyhQuery.jsp";
	String DZYH_ADD_PAGE = "/dzyh/query/addDzyhAdd.jsp";
	String DZYH_ADD_DETAIL_PAGE = "/dzyh/query/addDzyhDetail.jsp";
	String DZYH_ADD_QRY_SERVLET = "/servlet/com.sino.ams.dzyh.servlet.EamDhBillLServlet?act=" + WebActionConstant.QUERY_ACTION;

	String DZYH_FBTJ_QUERY_PAGE = "/dzyh/query/dzyhFBTJQuery.jsp";
	String DZYH_FBTJ_DETAIL_PAGE = "/dzyh/query/dzyhFBTJDetail.jsp";
	
	// ================================= ��ֵ�׺�̨�˲�ѯҳ�� =================================
	
	// ================================= ��ֵ�׺�Ʒ�̵� =================================

	String DZYH_LOSS_QUERY_PAGE = "/dzyh/query/lossEquipmentQuery.jsp";
	String DZYH_LOSS_DETAIL_PAGE = "/dzyh/query/lossEquipmentDetail.jsp";
	String DZYH_CHECKIMP_QUERY_PAGE = "/dzyh/query/checkImplementQuery.jsp";
	String DZYH_CHECKIMP_DETAIL_PAGE = "/dzyh/query/checkImplementDetail.jsp";
	
	String DZYH_ITEMDISPOSE_QUERY_PAGE = "/dzyh/dispose/itemDisposeQuery.jsp";
	String DZYH_ITEMDISPOSE_DETAIL_PAGE = "/dzyh/dispose/itemDisposeDetail.jsp";
	String DZYH_DISPOSE_QUERY_PAGE = "/dzyh/dispose/dzyhDisposeQuery.jsp";
	String DZYH_DISPOSE_DETAIL_PAGE = "/dzyh/dispose/dzyhDisposeDetail.jsp";
	
	String DZYH_HISTORY_PAGE = "/dzyh/DzyhHistoryDetail.jsp";
	
	// ================================= ��ֵ�׺�Ʒ�̵� =================================
	
	//==============================================��ͨ�豸��ѯҳ��============================
	String QRY_ETS_ITEM_INFO_SERVLET = "/servlet/com.sino.ams.ct.servlet.EtsItemInfoServlet";
	String QRY_BY_ETS_PROJ_PAGE = "/ct/equip/projQuery.jsp";
	String QRY_BY_ETS_BARCODE_PAGE = "/ct/equip/barcodeQuery.jsp";
	String QRY_BY_ETS_SPEC_PAGE = "/ct/equip/specQuery.jsp";
	String QRY_BY_ETS_CATE_PAGE = "/ct/equip/cateQuery.jsp";
	String QRY_BY_ETS_LOCUS_PAGE = "/ct/equip/locusQuery.jsp";
	String QRY_BY_ETS_ALLOT_PAGE = "/ct/equip/allotQuery.jsp";
	String QRY_BY_ETS_DAIWEI_PAGE = "/ct/equip/daiweiQuery.jsp";//����ά��˾��ѯҳ��
	//==============================================��ͨ�豸��ѯҳ��============================
	
	//==============================================��ͨ�ʲ����ϲ�ѯҳ��==========================================
	String QRY_BY_ETS_FA_ASSETS_PAGE = "/ct/scrap/scrapQuery.jsp";
	String QRY_BY_ETS_FA_ASSETS_DETAIL_PAGE = "/ct/scrap/scrapDetail.jsp";
	String QRY_ETS_FA_ASSETS_SERVLET = "/servlet/com.sino.ams.ct.servlet.EtsFaAssetsServlet";
    String OD_ASSETS_QRY_PAGE = "/newasset/odAssetsQuery.jsp";//���������ʲ�����
	//==============================================��ͨ�ʲ����ϲ�ѯҳ��==========================================	
	
	//==============================================�ǾߵǼǿ�ҳ��===============================================
	String INSTRUMENT_REGISTRATION = "/instrument/instrumentRegistration.jsp"; 
	String INSTRUMENT_REGISTRATION_DETAIL = "/instrument/instrumentRegistrationDetail.jsp";
	
	String INSTRUMENT_REGISTRATION_PAGE = "/equipment/instrumentTJQuery.jsp";
	
	String INSTRUMENT_USE_ITEMCATEGORY2_PAGE = "/equipment/YQYBuseItemCategory2Query.jsp"; 
	String INSTRUMENT_USE_BARCODE_PAGE = "/equipment/YQYBuseBarcodeQuery.jsp"; 
	String INSTRUMENT_REPAIR_ITEMCATEGORY2_PAGE = "/equipment/YQYBrepairItemCategory2Query.jsp"; 
	String INSTRUMENT_REPAIR_BARCODE_PAGE = "/equipment/YQYBrepairBarcodeQuery.jsp";

    //=====================================����ά��ҳ��=================================================
    String MANUFACTURER_QUERY_PAGE ="/system/manufacturer/manufacturerQuery.jsp";
    String MANUFACTURER_DETAIL_PAGE ="/system/manufacturer/manufacturerDetail.jsp";
    String MANUFACTURER_QUERY_SERVLET ="/servlet/com.sino.ams.system.manufacturer.servlet.ManufacturerServlet";
    //====================================�����ʲ�����ҳ��================================================
    String FREE_ITEM_QUERY ="/freeItem/freeItemQuery.jsp";
    String SPECIALTY_FREE_ITEM="/freeItem/specialtyItemQuery.jsp";
    String DEPT_FREE_ITEM="/freeItem/deptFreeItem.jsp";
    //====================================�ʲ����ϱ���ҳ��=====================================================
    String ITEM_WASTREL_QUERY="/freeItem/itemWastrelQuery.jsp";
    String SPECIALTY_WASTREL_QUERY="/freeItem/specialtyItemWastrel.jsp";
    String DEPT_WASTREL_QUERY="/freeItem/deptWastrelItem.jsp";
    String DISCARDED_ASSETS_SEARCH = "/newasset/report/discardedAssetsReport.jsp";
//  ====================================�ʲ������ϱ���ҳ��=====================================================
    String TODISCARDED_ASSETS_SEARCH = "/newasset/report/todiscardedAssetsReport.jsp";
    String ASSETS_DISCARD_SEARCH="/newasset/AmsAssetsDiscardSearch.jsp";
    //==================================�����ʲ�ͳ��========================
    String SELL_ASSETS_QUERY="/freeItem/sellAssetsQuery.jsp";
    String SELL_ASSETS_REPORT = "/newasset/report/sellAssetsReport.jsp";
    //==============================�����ʲ�ͳ��======
    String RENT_ASSETS_QUERY="/freeItem/rentAssetsQuery.jsp";
    String RENT_ASSETS_REPORT = "/newasset/report/rentAssetsReport.jsp";
    String APP_AREA_RENT_ASSETS_REPORT = "/newasset/report/appAreaRentAssetsReport.jsp";//�����ʲ�Ӧ������ͳ�Ʊ���
    //===========================�����ʲ�ͳ��====
    String DONATE_ASSETS_QUERY="/freeItem/donateAssetsQuery.jsp";
    String DONATE_ASSETS_REPORT = "/newasset/report/donateAssetsReport.jsp";
    //===============================TD�ʲ�רҵͳ��==================
    String SPECIALTY_TD_ASSETS="/freeItem/tdAssetsSpecialty.jsp" ;
    //============================��ͨ�ʲ�רҵͳ��=========
    String SPECIALTY_CT_ASSETS="/freeItem/ctAssetsSpecialty.jsp";
    String AREA_CT_ASSETS="/freeItem/ctAssetsArea.jsp";
    //=============================��ֵ�׺�ͳ��=================
    String SPECIALTY_DH_ASSETS="/freeItem/dhAssetsSpecialty.jsp";
    String DEPT_DH_ASSETS="/freeItem/dhAssetsDept.jsp";
    String AREA_DH_ASSETS="/freeItem/dhAssetsArea.jsp";

    //===============================��������ά��======
    String RPT_DEFINE_QUERY="/rptdefine/rptDefine.jsp";
    String RPT_DEFINE_DETAIL="/rptdefine/rptDefineDetail.jsp";
    String RPT_DEFINE_SERVLET="com.sino.ams.reportdefine.RPTDefineServlet?act="+ WebActionConstant.QUERY_ACTION;
    String RPT_PARAMETER_DETAIL="/rptdefine/rptParameterDetail.jsp";
    String RPT_PARAMETER_QUERY="/rptdefine/rptParameter.jsp";
    String RPT_PARAMETER_SERVLET="com.sino.ams.reportdefine.RPTParameterServlet?act="+ WebActionConstant.QUERY_ACTION;
    String RPT_VIEW_QUERY="/rptdefine/rptView.jsp";
    String RPT_VIEW_DETAIL="/rptdefine/rptViewDetail.jsp";
    String RPT_VIEW_SERVLET="com.sino.ams.reportdefine.RPTViewServlet?act="+ WebActionConstant.QUERY_ACTION;
    
    String BIRT_RPT_VIEW_LNE_PAGE ="/analyse/birtReport/rptLneParameter.jsp";
    String BIRT_RPT_VIEW_PE_PAGE ="/analyse/birtReport/rptPeParameter.jsp";
    String BIRT_RPT_VIEW_PR_PAGE ="/analyse/birtReport/rptPrParameter.jsp";
    //================================ͼ������ά��============================
    String GRAPH_GR_QUERY="/graphdefine/graphdefine.jsp";
    String GRAPH_GR_DETAIL="/graphdefine/graphdefinedetail.jsp";
    String GRAPH_GR_SERVLET="com.sino.ams.graphdefine.GraphDefineServlet?act="+ WebActionConstant.QUERY_ACTION;
    String GRAPH_PARAMETER_QUERY="/graphdefine/graphparameter.jsp";
    String GRAPH_PARAMETER_DETAIL="/graphdefine/graphparameterdetail.jsp";
    String GRAPH_PARAMETER_SERVLET="com.sino.ams.graphdefine.GraphParameterServlet?act="+ WebActionConstant.QUERY_ACTION;
    String GRAPH_VIEW_DEF_QUERY="/graphdefine/graphview.jsp";
    String GRAPH_VIEW_DEF_DETAIL="/graphdefine/graphviewdefdetail.jsp";
    String GRAPH_VIEW_DEF_ADD="/graphdefine/graphviewdefadd.jsp";
    String GRAPH_VIEW_DEF_SERVLET="com.sino.ams.graphdefine.GraphViewDefServlet?act="+ WebActionConstant.QUERY_ACTION;

    //=======================��������ҳ��====================
    String FREE_FLOW_QUERY="/freeflow/freeflow.jsp";
    String FREE_FLOW_EDIT="/freeflow/assetsFreeEdit.jsp";
    String FREE_FLOW_SERVLET="/servlet/com.sino.ams.freeflow.FreeFlowServlet";
    String TRANS_FREE_EDIT="/freeflow/assetsFreeEdit_42.jsp";
    String TRANS_FREE_DETAIL="/freeflow/assetsTransDetail.jsp";
    String TRANS_FREE_DETAIL_NOTHER="/freeflow/assetsTransDetail_42.jsp";
    String ORDER_APPROVE_SERVLET="/servlet/com.sino.ams.freeflow.OrderApproveServlet";
    String APPROVE_EDIT_PAGE="/freeflow/approve/assetsTransApprove.jsp";
    String APPROVE_DETAIL_PAGE="/freeflow/approve/transApproveDetail.jsp";
    String ASSETS_FREE_SEARCH="/freeflow/AmsAssetsFreeSearch.jsp";
    String ASSETS_FREE_REPORT_SEARCH="/newasset/report/FreeAssetsReport.jsp";
    String ASSETS_SHARE_SEARCH="/newassets/AmsAssetsShareSearch.jsp";
    String ASSETS_SELL_SEARCH="/newassets/AmsAssetsSellSearch.jsp";
    String ASSETS_RENT_SEARCH="/newassets/AmsAssetsRentSearch.jsp";
    String ASSETS_DONATE_SEARCH="/newassets/AmsAssetsDonateSearch.jsp";
    String HOUSE_RENT_CALL="/system/house/rentSearch.jsp";
    String HOUSE_DETAI_CALL="/system/house/rentInfoCall.jsp";
    String LAND_SEARCH_PAGE="/system/house/rent/dispositionLandSearch.jsp";
    String LAND_HOUSE_PAGE="/system/house/dispositionLandHouse.jsp";
    String ASSETS_SUB_SEARCH="/freeflow/AmsAssetsFreeSearch.jsp";
    

    String ASSETS_PASTE = "/system/paste/assetsPaste.jsp";   //����ճ��������

    //================================����ά��ҳ�� ============================  
    String DRIVER_INFO_PAGE ="/analyse/driverInfo/driverMainInfo.jsp";
    //================================�����Ӧ��ϵά��ҳ�� ============================  
    String DRIVER_MAPPING_QUERY_PAGE ="/analyse/driverMapping/driverMappingSearch.jsp";
    String DRIVER_MAPPING_DETAIL_PAGE ="/analyse/driverMapping/driverMappingDetail.jsp";
    
    //================================kpiָ��ά�� ============================ 
	String KPI_DEFINE_QUERY_PAGE = "/system/kpi/kpiDefineSearch.jsp";
	String KPI_DEFINE_EDIT_PAGE = "/system/kpi/kpiDefineEdit.jsp";

	//================================HELP���ҳ�� ============================ 
    String HLP_RES_FRM_PAGE = "/system/help/helpFrm.jsp";
    String HLP_RES_SEARCH_PAGE = "/system/help/helpSearch.jsp";
    String HLP_RES_QUERY_PAGE = "/system/help/helpQuery.jsp";
    String HLP_RES_DETAIL_PAGE = "";
    String HLP_RES_TREE_PAGE = "/system/help/helpTree.jsp";


    //==============================================����������Ϣҳ��===============================================
	String ETS_ITEM_INFO_EX_QUERY_PAGE = "/expand/etsItemInfoExQuery.jsp";
	String ETS_ITEM_INFO_EX_DETAIL_PAGE = "/expand/etsItemInfoExDetail.jsp";
	String ETS_ITEM_INFO_EX_QUEREN_QUERY_PAGE = "/expand/etsItemInfoExQueRenQuery.jsp";
	String ETS_ITEM_INFO_EX_QUEREN_DETAIL_PAGE = "/expand/etsItemInfoExQueRenDetail.jsp";
	String ETS_ITEM_INFO_EX_SEARCH_QUERY_PAGE = "/expand/etsItemInfoExSearchQuery.jsp";

	//================================================���ع���ҳ��================================================
	String ETS_EX_LAND_QUERY_PAGE = "/expand/etsItemLandInfoSearch.jsp";
	String ETS_EX_LAND_DETAIL_PAGE = "/expand/etsItemLandInfoDetail.jsp";
	String ETS_EX_LAND_FILE_PAGE = "/expand/uploadItemFile.jsp";
	String ETS_EX_LAND_QUERY_SERVLET = "/servlet/com.sino.ams.expand.servlet.EtsItemLandInfoServlet?act=" + WebActionConstant.QUERY_ACTION;
    
	//================================================�����ʲ���������================================================
	String FA_MSG_QRY_PAGE = "/newasset/free/message/freeAssetsMsgQuery.jsp";
	String FA_MSG_DTL_PAGE = "/newasset/free/message/freeAssetsMsgDetail.jsp";
	String FA_MSG_SERVLET = "/servlet/com.sino.ams.newasset.message.servlet.FreeAssetsMsgSetAndQueryServlet";
    
	//========================================ͨ���ʲ�ƥ��==========================================
	String TF_MATCH_RESULT = "/match/tf/tfMatchResult.jsp";//ͨ��ƥ���ر���ҳ��
	String TF_UNYOKE_PAGE = "/match/tf/tfUnyoke.jsp";      //����ͨ���ʲ�ƥ���ϵ���ݶ���
	String TF_FINANCE_PROP_SET_PAGE = "/match/tf/tfFinancePropSet.jsp";
	String TF_FINANCE_PROP_SET_SERVLET = "com.sino.ams.match.servlet.TfEtsItemMatchRecServlet";
	String TF_CHANGED_MATCHR_MACTPROPERTY = "/match/tf/tfMactProperty.jsp";	//ƥ��ͨ���ʲ��嵥
	String TF_CHANGED_MATCHR_NOMACTINGASSET = "/match/tf/tfNomatchingPage.jsp";	//δƥ��ͨ���ʲ��嵥
	
	String RENT_ASSETS_QRY_PAGE = "/newasset/rent/odAssetsQuery.jsp";//���������ʲ�����
	
	// ================================= �����Ǳ���� ====================================================
	String INSTRUMENT_YB_CHK_HISTORY_SERVLET = "/servlet/com.sino.nm.ams.instrument.servlet.AmsInstrumentEamYbChkHistoryServlet";
	// ================================= �����Ǳ���� ====================================================

    // ================================= ��Ʒ�������� ====================================================
	String WAREHOUSE_QUERY_PAGE = "/spare/warehouseDefine/warehouseQuery.jsp";//�ֿ�ص��ѯ
	String WAREHOUSE_DETAIL_PAGE = "/spare/warehouseDefine/warehouseDetail.jsp";//�ֿ�ص�ά��
    String ON_NET_SEARCH = "/spare/onnet/itemOnNetSearch.jsp"; //�豸��������ѯ
	String ON_NET_DETAIL = "/spare/onnet/itemOnNetDetail.jsp"; //�豸������ά��
    String SPARE_CATEORY_CONFIRM= "/spare/spareConfirmQuery.jsp";//��������ȷ�ϲ�ѯ
    String SPARE_CATEORY_INFO = "/spare/spareConfirmInfo.jsp";//��������ȷ��ά��
	// ================================= ��Ʒ�������� ====================================================
	
}

