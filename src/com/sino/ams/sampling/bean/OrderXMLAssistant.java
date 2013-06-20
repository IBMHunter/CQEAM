package com.sino.ams.sampling.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.newasset.urgenttrans.constant.UrgentAppConstant;
import com.sino.ams.sampling.constant.SamplingDicts;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
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
	private static Map samOrderMap = new HashMap();
	private static Map downloadItemMap = new HashMap();
	private static Map uploadItemMap = new HashMap();

	private static Map createOrderMap = new HashMap();
	private static List orderTypes = new ArrayList();
	private static Map orderMaps = new HashMap();

	static {
		/**
		 * 1.�������̵㹤��ͷ��Ϣ
		 */
		samOrderMap.put("id", "orderNo");
		samOrderMap.put("taskNo", "taskNo");
		samOrderMap.put("taskName", "taskName");
		samOrderMap.put("type", "orderType");
		samOrderMap.put("object_no", "samplingLocation");
		samOrderMap.put("location", "samplingLocationName");
		samOrderMap.put("creator", "createdUser");
		samOrderMap.put("creation_date", "creationDate");
		samOrderMap.put("start_date", "startTime");
		samOrderMap.put("period", "implementDays");
		samOrderMap.put("deadline_date", "deadlineDate");
		samOrderMap.put("AssignDate", "distributeDate");
		samOrderMap.put("scanover_date", "scanoverDate");

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
		orderTypes.add(SamplingDicts.ASS_CHK_PAD); //�ʲ��̵�
		orderTypes.add(SamplingDicts.INS_CHK_PAD); //�Ǳ��̵�
		orderTypes.add(SamplingDicts.RNT_CHK_PAD); //�����̵�
		orderTypes.add(SamplingDicts.ASS_SAM_PDA);//�ʲ����
		
		orderTypes.add( UrgentAppConstant.PDA_CREATE_TYPE_NAME );//�ʲ���������
		
		/**
		 * 6.�̵㹤��PDA�������������ӳ��
		 */
		orderMaps.put(SamplingDicts.ASS_CHK_PAD, SamplingDicts.ASS_CHK);
		orderMaps.put(SamplingDicts.INS_CHK_PAD, SamplingDicts.INS_CHK);
		orderMaps.put(SamplingDicts.RNT_CHK_PAD, SamplingDicts.RNT_CHK);
		orderMaps.put(SamplingDicts.ASS_SAM_PDA, SamplingDicts.ORDR_NO_MARK);
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
	public static Map getSamOrderMap() {
		return samOrderMap;
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
