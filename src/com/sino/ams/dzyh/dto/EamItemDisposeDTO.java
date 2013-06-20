package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ���ʴ��õ���(EAM) EamItemDispose</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamItemDisposeDTO extends CommonRecordDTO{

	private String disposeId = "";	//�̵�����ID
	private String barcode = "";	//��ǩ��(�����ǩ)
	private int systemid = SyBaseSQLUtil.NULL_INT_VALUE;	//Ets_item_info. SYSTEMID
	private String disposeReason = "";	//��ֵ�׺Ĵ���ԭ��
	private String disposeType = "";	//��ֵ�׺Ĵ��÷�ʽ
	private String remark = "";	//��ע
	
	private String deptName="";	//ʹ�ò���
	private String workorderObjectName="";	//�ص�
	//==================================ETS_ITEM_INFO===========================================
	private String maintainUser="";	//������
	private String itemQty = "";	//�豸����
	private String price = "";	//����
	private String responsibilityUser = "";	//������
	private String userName = "";	//������
	private SimpleCalendar lastLocChgDate = null;	//�豸�ص����䶯ʱ�䣨�������ڣ�
	private String attribute3 = "";	//��չ����3�����ң�
	private String eiiRemark="";	//��ע
	
	//==================================ETS_SYSTEM_ITEM=========================================
	private String itemCategory2 = "";	//Ŀ¼���
	private String itemName = "";	//Ʒ��
	private String itemSpec = "";	//����ͺ�
	
	public EamItemDisposeDTO(){
		super();
		this.lastLocChgDate=new SimpleCalendar();
	}
	/**
	 * ���ܣ��������ʴ��õ���(EAM)���� �̵�����ID��EAM_ITEM_DISPOSE.DISPOSE_ID��
	 * @param disposeId String
	 */
	public void setDisposeId(String disposeId){
		this.disposeId = disposeId;
	}

	/**
	 * ���ܣ��������ʴ��õ���(EAM)���� ��ǩ��
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ��������ʴ��õ���(EAM)���� Ets_item_info. SYSTEMID
	 * @param systemid String
	 */
	public void setSystemid(int systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ��������ʴ��õ���(EAM)���� ����ԭ�򣬲μ��ֵ䡰��ֵ�׺Ĵ���ԭ��
	 * @param disposeReason String
	 */
	public void setDisposeReason(String disposeReason){
		this.disposeReason = disposeReason;
	}

	/**
	 * ���ܣ��������ʴ��õ���(EAM)���� �μ��ֵ䡰��ֵ�׺Ĵ��÷�ʽ��
	 * @param disposeType String
	 */
	public void setDisposeType(String disposeType){
		this.disposeType = disposeType;
	}

	/**
	 * ���ܣ��������ʴ��õ���(EAM)���� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ���ȡ���ʴ��õ���(EAM)���� �̵�����ID��EAM_ITEM_DISPOSE.DISPOSE_ID��
	 * @return String
	 */
	public String getDisposeId() {
		return this.disposeId;
	}

	/**
	 * ���ܣ���ȡ���ʴ��õ���(EAM)���� ��ǩ��
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ���ʴ��õ���(EAM)���� Ets_item_info. SYSTEMID
	 * @return String
	 */
	public int getSystemid() {
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ���ʴ��õ���(EAM)���� ����ԭ�򣬲μ��ֵ䡰��ֵ�׺Ĵ���ԭ��
	 * @return String
	 */
	public String getDisposeReason() {
		return this.disposeReason;
	}

	/**
	 * ���ܣ���ȡ���ʴ��õ���(EAM)���� �μ��ֵ䡰��ֵ�׺Ĵ��÷�ʽ��
	 * @return String
	 */
	public String getDisposeType() {
		return this.disposeType;
	}

	/**
	 * ���ܣ���ȡ���ʴ��õ���(EAM)���� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getItemCategory2() {
		return itemCategory2;
	}

	public void setItemCategory2(String itemCategory2) {
		this.itemCategory2 = itemCategory2;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getMaintainUser() {
		return maintainUser;
	}

	public void setMaintainUser(String maintainUser) {
		this.maintainUser = maintainUser;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public SimpleCalendar getLastLocChgDate() throws CalendarException {
		lastLocChgDate.setCalPattern(getCalPattern());
		return lastLocChgDate;
	}

	public void setLastLocChgDate(String lastLocChgDate) throws CalendarException {
		this.lastLocChgDate.setCalendarValue(lastLocChgDate);
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWorkorderObjectName() {
		return workorderObjectName;
	}
	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}
	public String getEiiRemark() {
		return eiiRemark;
	}
	public void setEiiRemark(String eiiRemark) {
		this.eiiRemark = eiiRemark;
	}

}