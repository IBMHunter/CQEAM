package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: (��ֵ�׺�Ʒ)��ǩ����Ϣ(EAM) EtsItemInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EtsItemInfoDTO extends CommonRecordDTO{

	private String systemid = "";	//ϵͳID(���к�)
	private String faBarcode = "";	//��ӦMIS�̶��ʲ�����;��ֹʹ���ֶ�
	private String vendorBarcode = "";	//��Ӧ��������
	private String itemQty = "";	//�豸����
	private SimpleCalendar disableDate = null;	//ʧЧ����
	private String remark = "";		//��ע
	private String itemCode = "";	//�������
	private int projectid = SyBaseSQLUtil.NULL_INT_VALUE;	//����ID
	private String itemStatus = "";	//�豸״̬
	private String attribute1 = "";	//��չ����1;��������
	private String attribute2 = "";	//��չ����2
	private String sendtomisFlag = "";	//ͬ����MIS��־
	private String misItemname = "";	//MIS�豸����
	private String misItemspec = "";	//MIS����ͺ�
	private int assetId = SyBaseSQLUtil.NULL_INT_VALUE;	//�����ʲ�ID
	private int addressId = SyBaseSQLUtil.NULL_INT_VALUE;	//�ʲ��ص�ID
	private String financeProp = "";	//��������
	private String attribute3 = "";	//��չ����3�����ң�
	private String parentBarcode = "";	//����ǩ��
	private SimpleCalendar lastLocChgDate = null;	//�豸�ص����䶯ʱ�䣨�������ڣ�
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;	//��֯ID
	private String barcode = "";	//��ǩ��
	private String isParent = "";	//�Ƿ��Ǹ��豸
	private int responsibilityUser = SyBaseSQLUtil.NULL_INT_VALUE;	//������
	private String responsibilityDept = "";	//���β���
	private String maintainUser = "";	//ά����Ա
	private String maintainDept = "";	//ʹ�ò���
	private String isTmp = "";	//1:��ʱ�豸
	private String price = "";	//����

	private String eiiDeptName = "";	//ʹ�ò���
	private String eiiUserName = "";	//������
	
	//==============================================ETS_SYSTEM_ITEM===========================
	private String eiiItemCategory2 = "";	//Ŀ¼���
	private String eiiItemName = "";		//Ʒ��
	private String eiiItemSpec = "";		//����ͺ�
	
	//================================================ETS_0BJECT==============================
	private String eiiWorkorderObjectName = "";	//�ص�

	public EtsItemInfoDTO(){
		super();
		this.disableDate = new SimpleCalendar();
		this.lastLocChgDate = new SimpleCalendar();
	}
	
	public String getEiiItemCategory2() {
		return eiiItemCategory2;
	}

	public void setEiiItemCategory2(String eiiItemCategory2) {
		this.eiiItemCategory2 = eiiItemCategory2;
	}

	public String getEiiItemName() {
		return eiiItemName;
	}

	public void setEiiItemName(String eiiItemName) {
		this.eiiItemName = eiiItemName;
	}

	public String getEiiItemSpec() {
		return eiiItemSpec;
	}

	public void setEiiItemSpec(String eiiItemSpec) {
		this.eiiItemSpec = eiiItemSpec;
	}

	public String getEiiWorkorderObjectName() {
		return eiiWorkorderObjectName;
	}

	public void setEiiWorkorderObjectName(String eiiWorkorderObjectName) {
		this.eiiWorkorderObjectName = eiiWorkorderObjectName;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ϵͳID(���к�)
	 * @param systemid String
	 */
	public void setSystemid(String systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��ӦMIS�̶��ʲ�����;��ֹʹ���ֶ�
	 * @param faBarcode String
	 */
	public void setFaBarcode(String faBarcode){
		this.faBarcode = faBarcode;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��Ӧ��������
	 * @param vendorBarcode String
	 */
	public void setVendorBarcode(String vendorBarcode){
		this.vendorBarcode = vendorBarcode;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �豸����
	 * @param itemQty String
	 */
	public void setItemQty(String itemQty){
		this.itemQty = itemQty;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ʧЧ����
	 * @param disableDate String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDisableDate(String disableDate) throws CalendarException{
		this.disableDate.setCalendarValue(disableDate);
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �������
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ����ID
	 * @param projectid String
	 */
	public void setProjectid(int projectid){
		this.projectid = projectid;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �豸״̬
	 * @param itemStatus String
	 */
	public void setItemStatus(String itemStatus){
		this.itemStatus = itemStatus;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��չ����1;��������
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��չ����2
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ͬ����MIS��־
	 * @param sendtomisFlag String
	 */
	public void setSendtomisFlag(String sendtomisFlag){
		this.sendtomisFlag = sendtomisFlag;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� MIS�豸����
	 * @param misItemname String
	 */
	public void setMisItemname(String misItemname){
		this.misItemname = misItemname;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� MIS����ͺ�
	 * @param misItemspec String
	 */
	public void setMisItemspec(String misItemspec){
		this.misItemspec = misItemspec;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �����ʲ�ID
	 * @param assetId String
	 */
	public void setAssetId(int assetId){
		this.assetId = assetId;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �ʲ��ص�ID
	 * @param addressId String
	 */
	public void setAddressId(int addressId){
		this.addressId = addressId;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param financeProp String
	 */
	public void setFinanceProp(String financeProp){
		this.financeProp = financeProp;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��չ����3(�����Ǳ���;)
	 * @param attribute3 String
	 */
	public void setAttribute3(String attribute3){
		this.attribute3 = attribute3;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ����ǩ��
	 * @param parentBarcode String
	 */
	public void setParentBarcode(String parentBarcode){
		this.parentBarcode = parentBarcode;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �豸�ص����䶯ʱ��
	 * @param lastLocChgDate String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastLocChgDate(String lastLocChgDate) throws CalendarException{
		this.lastLocChgDate.setCalendarValue(lastLocChgDate);
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��ǩ��
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �Ƿ��Ǹ��豸
	 * @param isParent String
	 */
	public void setIsParent(String isParent){
		this.isParent = isParent;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ������
	 * @param responsibilityUser String
	 */
	public void setResponsibilityUser(int responsibilityUser){
		this.responsibilityUser = responsibilityUser;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ���β���
	 * @param responsibilityDept String
	 */
	public void setResponsibilityDept(String responsibilityDept){
		this.responsibilityDept = responsibilityDept;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ά����Ա
	 * @param maintainUser String
	 */
	public void setMaintainUser(String maintainUser){
		this.maintainUser = maintainUser;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ʹ�ò���
	 * @param maintainDept String
	 */
	public void setMaintainDept(String maintainDept){
		this.maintainDept = maintainDept;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� 1:��ʱ�豸
	 * @param isTmp String
	 */
	public void setIsTmp(String isTmp){
		this.isTmp = isTmp;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ����
	 * @param price String
	 */
	public void setPrice(String price){
		this.price = price;
	}


	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ϵͳID(���к�)
	 * @return String
	 */
	public String getSystemid() {
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��ӦMIS�̶��ʲ�����;��ֹʹ���ֶ�
	 * @return String
	 */
	public String getFaBarcode() {
		return this.faBarcode;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��Ӧ��������
	 * @return String
	 */
	public String getVendorBarcode() {
		return this.vendorBarcode;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �豸����
	 * @return String
	 */
	public String getItemQty() {
		return this.itemQty;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ʧЧ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDisableDate() throws CalendarException {
		disableDate.setCalPattern(getCalPattern());
		return this.disableDate;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �������
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ����ID
	 * @return String
	 */
	public int getProjectid() {
		return this.projectid;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �豸״̬
	 * @return String
	 */
	public String getItemStatus() {
		return this.itemStatus;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��չ����1;��������
	 * @return String
	 */
	public String getAttribute1() {
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��չ����2
	 * @return String
	 */
	public String getAttribute2() {
		return this.attribute2;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ͬ����MIS��־
	 * @return String
	 */
	public String getSendtomisFlag() {
		return this.sendtomisFlag;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� MIS�豸����
	 * @return String
	 */
	public String getMisItemname() {
		return this.misItemname;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� MIS����ͺ�
	 * @return String
	 */
	public String getMisItemspec() {
		return this.misItemspec;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �����ʲ�ID
	 * @return String
	 */
	public int getAssetId() {
		return this.assetId;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �ʲ��ص�ID
	 * @return String
	 */
	public int getAddressId() {
		return this.addressId;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return String
	 */
	public String getFinanceProp() {
		return this.financeProp;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��չ����3(�����Ǳ���;)
	 * @return String
	 */
	public String getAttribute3() {
		return this.attribute3;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ����ǩ��
	 * @return String
	 */
	public String getParentBarcode() {
		return this.parentBarcode;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �豸�ص����䶯ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastLocChgDate() throws CalendarException {
		lastLocChgDate.setCalPattern(getCalPattern());
		return this.lastLocChgDate;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��ǩ��
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �Ƿ��Ǹ��豸
	 * @return String
	 */
	public String getIsParent() {
		return this.isParent;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ������
	 * @return String
	 */
	public int getResponsibilityUser() {
		return this.responsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ���β���
	 * @return String
	 */
	public String getResponsibilityDept() {
		return this.responsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ά����Ա
	 * @return String
	 */
	public String getMaintainUser() {
		return this.maintainUser;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ʹ�ò���
	 * @return String
	 */
	public String getMaintainDept() {
		return this.maintainDept;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� 1:��ʱ�豸
	 * @return String
	 */
	public String getIsTmp() {
		return this.isTmp;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ����
	 * @return String
	 */
	public String getPrice() {
		return this.price;
	}

	public String getEiiDeptName() {
		return eiiDeptName;
	}

	public void setEiiDeptName(String eiiDeptName) {
		this.eiiDeptName = eiiDeptName;
	}

	public String getEiiUserName() {
		return eiiUserName;
	}

	public void setEiiUserName(String eiiUserName) {
		this.eiiUserName = eiiUserName;
	}
}