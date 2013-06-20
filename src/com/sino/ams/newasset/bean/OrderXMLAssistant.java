package com.sino.ams.newasset.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.newasset.constant.AssetsDictConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderXMLAssistant { //����Ĵ�������ΪPDA�ϵĳ���û�а���˼ŵ����˾�����淶�����������������˸�ӳ���ϵ��

	private static String rootName = "workorders";
	private static String orderName = "workorder";
	private static String itemName = "item";
	private static String orderPrimaryKey = "id";
	private static String orderType = "type";
	private static Map chkOrderMap = new HashMap();
	private static Map chkItemMap = new HashMap();
	private static Map createOrderMap = new HashMap();
	private static List orderTypes = new ArrayList();
	private static Map orderMaps = new HashMap();

	static {
		/**
		 * 1.�������̵㹤��ͷ��Ϣ
		 */
		chkOrderMap.put("id", "transNo");
		chkOrderMap.put("type", "orderType");
		chkOrderMap.put("object_no", "checkLocation");
		chkOrderMap.put("location", "objectLocation");
		chkOrderMap.put("location_code", "objectCode");
		chkOrderMap.put("creator", "createdLoginUser");
		chkOrderMap.put("creation_date", "creationDate");
		chkOrderMap.put("start_date", "startTime");
		chkOrderMap.put("period", "implementDays");
		chkOrderMap.put("deadline_date", "deadlineDate");
//        chkOrderMap.put("locationname", "workorderObjectName");
		chkOrderMap.put("AssignDate", "distributeDate");
		chkOrderMap.put("scanover_date", "scanoverDate");
		chkOrderMap.put("scan_category", "checkCategory");
		chkOrderMap.put("costCode", "costCenterCode");
		chkOrderMap.put("new_location", "newLocation");

		/**
		 * 2.�������̵㹤������Ϣ
		 */
		chkItemMap.put("code", "barcode");
		chkItemMap.put("item_code", "itemCode");
		chkItemMap.put("name", "itemName");
		chkItemMap.put("type", "itemSpec");
		chkItemMap.put("category", "itemCategory");
		chkItemMap.put("vendor_code", "vendorBarcode");
		chkItemMap.put("start_date", "startDate");
		chkItemMap.put("assign_userid", "responsibilityUser");
		chkItemMap.put("assign_groupid", "responsibilityDept");
		chkItemMap.put("username", "maintainUser");
		chkItemMap.put("scandate", "scanDate");
		chkItemMap.put("box_no", "boxNo");
		chkItemMap.put("net_unit", "netUnit");
		chkItemMap.put("status", "scanStatus"); //�豸ɨ��״̬��5��ʾɨ�赽��6��ʾδɨ�赽.
		chkItemMap.put("attribute1", "attribute1"); //�豸  �Ƿ������ʲ�
		chkItemMap.put("manufacturerId", "manufacturerId");
		chkItemMap.put("isShare", "isShare");
		chkItemMap.put("contentCode", "contentCode");
		chkItemMap.put("contentName", "contentName");
		chkItemMap.put("power", "power");
		chkItemMap.put("replace_flag", "replaceFlag");
		chkItemMap.put("new_tag", "newTag");
		chkItemMap.put("construct_status", "constructStatus");
		chkItemMap.put("lne_id", "lneId");
		chkItemMap.put("lne_name", "lneName");
		chkItemMap.put("cex_id", "cexId");
		chkItemMap.put("cex_name", "cexName");
		chkItemMap.put("ope_id", "opeId");
		chkItemMap.put("ope_name", "opeName");
		chkItemMap.put("nle_id", "nleId");
		chkItemMap.put("nle_name", "nleName");


		/**
		 * 3.PDA����������Ϣ
		 */
		createOrderMap.put("type", "orderType");
		createOrderMap.put("GroupID", "groupId");
		createOrderMap.put("category", "category");
		createOrderMap.put("name", "loginName");
		createOrderMap.put("object_no", "checkLocation");
		createOrderMap.put("user_id", "archivedBy");
		createOrderMap.put("scan_category","checkCategory");
		createOrderMap.put("costCode", "costCenterCode");

		/**
		 * 4.֧�ֵ��̵㹤������
		 */
		orderTypes.add(AssetsDictConstant.ASS_CHK_PAD); //�ʲ��̵�
		orderTypes.add(AssetsDictConstant.INS_CHK_PAD); //�Ǳ��̵�
		orderTypes.add(AssetsDictConstant.RNT_CHK_PAD); //�����̵�

		/**
		 * 5.�̵㹤��PDA�������������ӳ��
		 */
		orderMaps.put(AssetsDictConstant.ASS_CHK_PAD, AssetsDictConstant.ASS_CHK);
		orderMaps.put(AssetsDictConstant.INS_CHK_PAD, AssetsDictConstant.INS_CHK);
		orderMaps.put(AssetsDictConstant.RNT_CHK_PAD, AssetsDictConstant.RNT_CHK);
	}

	/**
	 * ���ܣ����캯��˽�л�����ֹ�ⲿ����ʵ��������
	 */
	private OrderXMLAssistant() {
		super();
	}

	/**
	 * ���ܣ���ȡ�̵㹤��������PDA����������ֶ�ӳ���ϵ
	 * @return Map
	 */
	public static Map getChkItemMap() {
		return chkItemMap;
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
	public static Map getChkOrderMap() {
		return chkOrderMap;
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
}
