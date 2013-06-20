package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.SyBaseSQLUtil;



/**
* <p>Title: ��ֵ�׺�Ʒ�䶯��ʷ��(EAM) EamDhChgLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EamDhChgLogDTO extends EamDhCatalogValuesDTO{


	private String dhChgLogId = "";
	private String barcode = "";
	private String chgType = "";
	private String fromDept = "";
	private String toDept = "";
	private int fromAddressId = SyBaseSQLUtil.NULL_INT_VALUE;
	private int toAddressId = SyBaseSQLUtil.NULL_INT_VALUE;
	private int fromResponsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private String toResponsibilityUser = "";
	private int fromMaintainUser = SyBaseSQLUtil.NULL_INT_VALUE;
	private int toMaintainUser = SyBaseSQLUtil.NULL_INT_VALUE ;
	private String fromStatus = "";
	private String toStatus = "";
	private String fromAtttibute1 = "";
	private String fromAttribute2 = "";
	private String toAttribute1 = "";
	private String toAttribute2 = "";
	private String refNo = "";

//=========================�����ֶ���������ʾ��=============================//
	private String chgTypeValue = "";
	private String fromDeptName = "";
	private String toDeptName = "";
	private String fromLocationCode = "";
	private String fromLocationName = "";
	private String toLocationCode = "";
	private String toLocationName = "";
	private String fromResponsibilityUserName = "";
	private String toResponsibilityUserName = "";
	private String fromStatusValue = "";
	private String toStatusValue = "";
	private String fromAtttibute1Value = "";
	private String fromAttribute2Value = "";
	private String toAttribute1Value = "";
	private String toAttribute2Value = "";
	private String fromResponsibilityUse;

	private String itemCategoryValue = "";
	private String itemName = "";
	private String itemSpec = "";

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� EAM_DH_CHG_LOG_S.NEXTVAL
	 * @param dhChgLogId String
	 */
	public void setDhChgLogId(String dhChgLogId){
		this.dhChgLogId = dhChgLogId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ǩ
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �䶯����(�μ��ֵ䣺��ֵ�׺�Ʒ�䶯����)
	 * @param chgType String
	 */
	public void setChgType(String chgType){
		this.chgType = chgType;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭʹ�ò���
	 * @param fromDept String
	 */
	public void setFromDept(String fromDept){
		this.fromDept = fromDept;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ��ʹ�ò���
	 * @param toDept String
	 */
	public void setToDept(String toDept){
		this.toDept = toDept;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭ�ص�
	 * @param fromAddressId String
	 */
	public void setFromAddressId(int fromAddressId){
		this.fromAddressId = fromAddressId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �µص�
	 * @param toAddressId String
	 */
	public void setToAddressId(int toAddressId){
		this.toAddressId = toAddressId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭ������
	 * @param fromResponsibilityUser String
	 */
	public void setFromResponsibilityUser(int fromResponsibilityUser){
		this.fromResponsibilityUser = fromResponsibilityUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ��������
	 * @param toResponsibilityUser String
	 */
	public void setToResponsibilityUser(String toResponsibilityUser){
		this.toResponsibilityUser = toResponsibilityUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭ������
	 * @param fromMaintainUser String
	 */
	public void setFromMaintainUser(int fromMaintainUser){
		this.fromMaintainUser = fromMaintainUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �±�����
	 * @param toMaintainUser String
	 */
	public void setToMaintainUser(int toMaintainUser){
		this.toMaintainUser = toMaintainUser;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭʹ��״̬
	 * @param fromStatus String
	 */
	public void setFromStatus(String fromStatus){
		this.fromStatus = fromStatus;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ��ʹ��״̬
	 * @param toStatus String
	 */
	public void setToStatus(String toStatus){
		this.toStatus = toStatus;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�1
	 * @param fromAtttibute1 String
	 */
	public void setFromAtttibute1(String fromAtttibute1){
		this.fromAtttibute1 = fromAtttibute1;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�2
	 * @param fromAttribute2 String
	 */
	public void setFromAttribute2(String fromAttribute2){
		this.fromAttribute2 = fromAttribute2;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�1
	 * @param toAttribute1 String
	 */
	public void setToAttribute1(String toAttribute1){
		this.toAttribute1 = toAttribute1;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�2
	 * @param toAttribute2 String
	 */
	public void setToAttribute2(String toAttribute2){
		this.toAttribute2 = toAttribute2;
	}

	public void setChgTypeValue(String chgTypeValue) {
		this.chgTypeValue = chgTypeValue;
	}

	public void setToStatusValue(String toStatusValue) {
		this.toStatusValue = toStatusValue;
	}

	public void setToResponsibilityUserName(String toResponsibilityUserName) {
		this.toResponsibilityUserName = toResponsibilityUserName;
	}

	public void setToLocationName(String toLocationName) {
		this.toLocationName = toLocationName;
	}

	public void setToLocationCode(String toLocationCode) {
		this.toLocationCode = toLocationCode;
	}

	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
	}

	public void setToAttribute2Value(String toAttribute2Value) {
		this.toAttribute2Value = toAttribute2Value;
	}

	public void setToAttribute1Value(String toAttribute1Value) {
		this.toAttribute1Value = toAttribute1Value;
	}

	public void setFromStatusValue(String fromStatusValue) {
		this.fromStatusValue = fromStatusValue;
	}

	public void setFromResponsibilityUserName(String fromResponsibilityUserName) {
		this.fromResponsibilityUserName = fromResponsibilityUserName;
	}

	public void setFromLocationName(String fromLocationName) {
		this.fromLocationName = fromLocationName;
	}

	public void setFromLocationCode(String fromLocationCode) {
		this.fromLocationCode = fromLocationCode;
	}

	public void setFromDeptName(String fromDeptName) {
		this.fromDeptName = fromDeptName;
	}

	public void setFromAtttibute1Value(String fromAtttibute1Value) {
		this.fromAtttibute1Value = fromAtttibute1Value;
	}

	public void setFromAttribute2Value(String fromAttribute2Value) {
		this.fromAttribute2Value = fromAttribute2Value;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public void setItemCategoryValue(String itemCategoryValue) {
		this.itemCategoryValue = itemCategoryValue;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� EAM_DH_CHG_LOG_S.NEXTVAL
	 * @return String
	 */
	public String getDhChgLogId() {
		return this.dhChgLogId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ǩ
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �䶯����(�μ��ֵ䣺��ֵ�׺�Ʒ�䶯����)
	 * @return String
	 */
	public String getChgType() {
		return this.chgType;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭʹ�ò���
	 * @return String
	 */
	public String getFromDept() {
		return this.fromDept;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ��ʹ�ò���
	 * @return String
	 */
	public String getToDept() {
		return this.toDept;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭ�ص�
	 * @return String
	 */
	public int getFromAddressId() {
		return this.fromAddressId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �µص�
	 * @return String
	 */
	public int getToAddressId() {
		return this.toAddressId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭ������
	 * @return String
	 */
	public int getFromResponsibilityUser() {
		return this.fromResponsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ��������
	 * @return String
	 */
	public String getToResponsibilityUser() {
		return this.toResponsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭ������
	 * @return String
	 */
	public int getFromMaintainUser() {
		return this.fromMaintainUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �±�����
	 * @return String
	 */
	public int getToMaintainUser() {
		return this.toMaintainUser;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ԭʹ��״̬
	 * @return String
	 */
	public String getFromStatus() {
		return this.fromStatus;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� ��ʹ��״̬
	 * @return String
	 */
	public String getToStatus() {
		return this.toStatus;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�1
	 * @return String
	 */
	public String getFromAtttibute1() {
		return this.fromAtttibute1;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�2
	 * @return String
	 */
	public String getFromAttribute2() {
		return this.fromAttribute2;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�1
	 * @return String
	 */
	public String getToAttribute1() {
		return this.toAttribute1;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ�䶯��ʷ��(EAM)���� �����ֶ�2
	 * @return String
	 */
	public String getToAttribute2() {
		return this.toAttribute2;
	}

	public String getChgTypeValue() {
		return chgTypeValue;
	}

	public String getFromAttribute2Value() {
		return fromAttribute2Value;
	}

	public String getFromAtttibute1Value() {
		return fromAtttibute1Value;
	}

	public String getFromDeptName() {
		return fromDeptName;
	}

	public String getFromLocationCode() {
		return fromLocationCode;
	}

	public String getFromLocationName() {
		return fromLocationName;
	}

	public String getFromResponsibilityUse() {
		return fromResponsibilityUse;
	}

	public String getFromResponsibilityUserName() {
		return fromResponsibilityUserName;
	}

	public String getFromStatusValue() {
		return fromStatusValue;
	}

	public String getToAttribute1Value() {
		return toAttribute1Value;
	}

	public String getToAttribute2Value() {
		return toAttribute2Value;
	}

	public String getToDeptName() {
		return toDeptName;
	}

	public String getToLocationCode() {
		return toLocationCode;
	}

	public String getToLocationName() {
		return toLocationName;
	}

	public String getToResponsibilityUserName() {
		return toResponsibilityUserName;
	}

	public String getToStatusValue() {
		return toStatusValue;
	}

	public String getRefNo() {
		return refNo;
	}

	public String getItemCategoryValue() {
		return itemCategoryValue;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemSpec() {
		return itemSpec;
	}
}
