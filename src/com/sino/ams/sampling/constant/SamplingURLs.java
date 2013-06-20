package com.sino.ams.sampling.constant;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface SamplingURLs {

	String TASK_LIST_PAGE = "/sampling/taskList.jsp";
	String TASK_DETAIL_PAGE = "/sampling/taskData.jsp";
	String TASK_SEARCH_PAGE = "/sampling/taskSearch.jsp";



	String BATCH_LIST_PAGE = "/sampling/batchList.jsp";
	String BATCH_DETAIL_PAGE = "/sampling/batchData.jsp";
	String ORDER_LIST_PAGE = "/sampling/orderList.jsp";
	String ORDER_SEACRH_PAGE = "/sampling/orderSearch.jsp";

	String ORDER_FRM_PAGE = "/sampling/orderFrm.jsp";//�������ҳ��
	String ORDER_FRM_TOP = "/sampling/orderCreateTop.jsp";//��������ϱ�ҳ��
	String ORDER_FRM_LEFT = "/sampling/orderTaskTree.jsp";//����������ҳ��
	String ORDER_FRM_RIGHT = "/sampling/batchOrder.jsp";//��������ұ�ҳ��


	String ORDER_CREATE_PAGE = "/sampling/orderCreate.jsp";
	String ORDER_DETAIL_PAGE = "/sampling/orderData.jsp";

	String TASK_SERVLET = "/servlet/com.sino.ams.sampling.servlet.AmsAssetsSamplingTaskServlet";
	String BATCH_SERVLET = "/servlet/com.sino.ams.sampling.servlet.AmsAssetsSamplingBatchServlet";
	String ORDER_SERVLET = "/servlet/com.sino.ams.sampling.servlet.AmsAssetsSamplingHeaderServlet";
	String TASK_SEARCH_SERVLET = "/servlet/com.sino.ams.sampling.servlet.TaskSearchServlet";
	String ORDER_FRM_SERVLET = "/servlet/com.sino.ams.sampling.servlet.OrderFrmServlet";
	String TASK_BATCH_TREE = "/servlet/com.sino.ams.sampling.servlet.TaskBatchTreeServlet";
	String BATCH_ORDER_SERVLET = "/servlet/com.sino.ams.sampling.servlet.BatchOrderServlet";
	String ORDER_SEARCH_SERVLET = "/servlet/com.sino.ams.sampling.servlet.OrderSearchServlet";
}
