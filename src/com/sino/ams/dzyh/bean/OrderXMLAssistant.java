package com.sino.ams.dzyh.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.dzyh.constant.LvecDicts;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class OrderXMLAssistant { //����Ĵ�������ΪPDA�ϵĳ���û�а���˼ŵ����˾�����淶�����������������˸�ӳ���ϵ��

	private static String rootName = "workorders";
	private static String orderName = "workorder";
	private static String itemName = "item";
	private static String orderPrimaryKey = "id";
	private static String orderType = "type";
	private static Map orderMap = new HashMap();
	private static Map downloadItemMap = new HashMap();
	private static Map uploadItemMap = new HashMap();

	private static Map createOrderMap = new HashMap();
	private static List orderTypes = new ArrayList();
	private static Map orderMaps = new HashMap();

	static {
		/**
		 * 1.�������̵㹤��ͷ��Ϣ
		 */
		orderMap.put("id", "orderNo");
		orderMap.put("taskNo", "checkTaskId");
		orderMap.put("taskName", "taskName");
		orderMap.put("type", "orderType");
		orderMap.put("object_no", "checkLocation");
		orderMap.put("location", "locationName");
		orderMap.put("creator", "createdUser");
		orderMap.put("creation_date", "creationDate");
		orderMap.put("start_date", "startTime");
		orderMap.put("period", "implementDays");
		orderMap.put("deadline_date", "deadlineDate");
		orderMap.put("AssignDate", "distributeDate");
		orderMap.put("scanover_date", "scanoverDate");

		/**
		 * 2.�����̵㹤������Ϣ
		 */
		downloadItemMap.put("code", "barcode");
		downloadItemMap.put("item_code", "itemCode");
		downloadItemMap.put("name", "itemName");
		downloadItemMap.put("type", "itemSpec");
		downloadItemMap.put("category", "itemCategory");
		downloadItemMap.put("start_date", "startDate");
		downloadItemMap.put("assign_userid", "responsibilityUser");
		downloadItemMap.put("assign_groupid", "responsibilityDept");
		downloadItemMap.put("username", "maintainUser");
		downloadItemMap.put("scandate", "scanDate");
		downloadItemMap.put("box_no", "boxNo");
		downloadItemMap.put("net_unit", "netUnit");
		downloadItemMap.put("status", "systemStatus"); //�豸ɨ��״̬��5��ʾɨ�赽��6��ʾδɨ�赽.
		downloadItemMap.put("attribute1", "attribute1"); //�豸  �Ƿ������ʲ�

		downloadItemMap.put("price", "price");
		downloadItemMap.put("item_category2", "itemCategory2");
		downloadItemMap.put("attribute3", "vendorName");

		/**
		 * 3.�����̵㹤������Ϣ
		 */
		uploadItemMap.put("code", "barcode");
		uploadItemMap.put("item_code", "itemCode");
		uploadItemMap.put("name", "itemName");
		uploadItemMap.put("type", "itemSpec");
		uploadItemMap.put("category", "itemCategory");
		uploadItemMap.put("start_date", "startDate");
		uploadItemMap.put("assign_userid", "responsibilityUser");
		uploadItemMap.put("assign_groupid", "responsibilityDept");
		uploadItemMap.put("username", "maintainUser");
		uploadItemMap.put("scandate", "scanDate");
		uploadItemMap.put("box_no", "boxNo");
		uploadItemMap.put("net_unit", "netUnit");
		uploadItemMap.put("status", "scanStatus"); //�豸ɨ��״̬��5��ʾɨ�赽��6��ʾδɨ�赽.
		uploadItemMap.put("attribute1", "attribute1"); //�豸  �Ƿ������ʲ�

		uploadItemMap.put("price", "price");
		uploadItemMap.put("item_category2", "itemCategory2");
		uploadItemMap.put("attribute3", "vendorName");

		/**
		 * 4.PDA����������Ϣ
		 */
		createOrderMap.put("type", "orderType");
		createOrderMap.put("GroupID", "groupId");
		createOrderMap.put("category", "category");
		createOrderMap.put("name", "loginName");
		createOrderMap.put("object_no", "samplingLocation");
		createOrderMap.put("user_id", "archivedBy");
		createOrderMap.put("scan_category","checkCategory");
		createOrderMap.put("costCode", "costCenterCode");
		createOrderMap.put("taskNo", "taskNo");

		/**
		 * 5.֧�ֵĳ�鹤������(Ϊ���ڹ�������ʱͳһ���������̵㲿�ֵĹ���)
		 */
		orderTypes.add(LvecDicts.ORD_TYPE1_DZYH); //�ͺ��̵�
		orderTypes.add(LvecDicts.ORD_TYPE1_YQYB); //�Ǳ��̵�

		/**
		 * 6.�̵㹤��PDA�������������ӳ��
		 */
		orderMaps.put(LvecDicts.ORD_TYPE1_DZYH, LvecDicts.ORD_TYPE2_DZYH);
		orderMaps.put(LvecDicts.ORD_TYPE1_YQYB, LvecDicts.ORD_TYPE2_YQYB);
	}

	/**
	 * ���ܣ���ȡ�̵㹤��������PDA����������ֶ�ӳ���ϵ
	 * @return Map
	 */
	public static Map getDownloadItemMap() {
		return downloadItemMap;
	}

	/**
	 * ���ܣ���ȡ�̵㹤�������ݽڵ�����
	 * @return String
	 */
	public static String getItemName() {
		return itemName;
	}

	/**
	 * ���ܣ���ȡ�̵㹤��PDA����������ֶ�ӳ���ϵ
	 * @return Map
	 */
	public static Map getOrderMap() {
		return orderMap;
	}


	/**
	 * ���ܣ���ȡPDA�����̵㹤������������ֶ�ӳ���ϵ
	 * @return Map
	 */
	public static Map getOrderCreateMap() {
		return createOrderMap;
	}

	/**
	 * ���ܣ���ȡ�̵㹤���ڵ�����
	 * @return String
	 */
	public static String getOrderName() {
		return orderName;
	}

	/**
	 * ����:��ȡ�̵㹤��XML�ļ����ڵ�����
	 * @return String
	 */
	public static String getRootName() {
		return rootName;
	}

	/**
	 * ���ܣ���ȡ���������ֶΡ�
	 * @return String
	 */
	public static String getOrderPrimaryKey() {
		return orderPrimaryKey;
	}

	/**
	 * ���ܣ���ȡ���������ֶΡ�
	 * @return String
	 */
	public static String getOrderType() {
		return orderType;
	}

	public static List getOrderTypes() {
		return orderTypes;
	}

	public static Map getOrderMaps() {
		return orderMaps;
	}

	public static Map getUploadItemMap(){
		return uploadItemMap;
	}
}
