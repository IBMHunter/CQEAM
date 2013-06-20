package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ��ṹ����-L(EAM) EamDhBillL</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamDhBillLDTO extends CommonRecordDTO{

	private String billLineId = "";
	private String billHeaderId = "";
	private int catalogValueId = 0;
	private String itemCode = "";
	private int quantity = 0;
	private float price = 0;
	private String responsibilityDept = "";
	private int responsibilityUser = 0;
	private int workorderObjectNo = 0;
	private String maintainUser = "";
	private SimpleCalendar lastLocChgDate = null;
	private String manufactory = "";
	private String remark = "";

//	=============================================================================

	private String financeProp = "DZYH";
	private String billNo = "";	//���ݱ��
	private String billStatus = "";	//����״̬
    private String createUser = "";	//������
    private int orgId = SyBaseSQLUtil.NULL_INT_VALUE ;	
	private String catalogCode = "";	//Ŀ¼���
	private String itemName = "";	//Ʒ��
	private String itemSpec = "";	//����ͺ�
	private String barcode = "";	//�����
	private String deptName = "";	//ʹ�ò���
	private String userName = "";	//������
	private String workorderObjectName = "";	//�ص�

	public EamDhBillLDTO() {
		super();
		this.lastLocChgDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� EAM_DH_BILL_L_S.NEXTVAL
	 * @param billLineId String
	 */
	public void setBillLineId(String billLineId){
		this.billLineId = billLineId;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� EAM_DH_BILL_H. BILL_HEADER_ID
	 * @param billHeaderId String
	 */
	public void setBillHeaderId(String billHeaderId){
		this.billHeaderId = billHeaderId;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� Ŀ¼���EAM_DH_CATALOG_VALUES. Catalog_VALUE_ID
	 * @param catalogValueId String
	 */
	public void setCatalogValueId(int catalogValueId){
		this.catalogValueId = catalogValueId;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� �豸���루ETS_SYSTEM_ITEM.ITEM_CODE��
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� ����
	 * @param quantity String
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� ����
	 * @param price String
	 */
	public void setPrice(float price){
		this.price = price;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� ʹ�ò���(ETS_ITEM_INFO. RESPONSIBILITY_DEPT),�����ֵ䣺ams_mis_dept.dept_code
	 * @param responsibilityDept String
	 */
	public void setResponsibilityDept(String responsibilityDept){
		this.responsibilityDept = responsibilityDept;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� �����ˣ�ETS_ITEM_INFO. RESPONSIBILITY_USER��,
�����ֵ䣺ams_mis_employ.employee_id

	 * @param responsibilityUser String
	 */
	public void setResponsibilityUser(int responsibilityUser){
		this.responsibilityUser = responsibilityUser;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� �ص㣨ETS_OBJECT. WORKORDER_OBJECT_NAME��
	 * @param workorderObjectNo String
	 */
	public void setWorkorderObjectNo(int workorderObjectNo){
		this.workorderObjectNo = workorderObjectNo;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� �����ˣ�ETS_ITEM_INFO. MAINTAIN_USER��,�ֹ���д
	 * @param maintainUser String
	 */
	public void setMaintainUser(String maintainUser){
		this.maintainUser = maintainUser;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� �������ڣ�ETS_ITEM_INFO. LAST_LOC_CHG_DATE��
	 * @param lastLocChgDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastLocChgDate(String lastLocChgDate) throws CalendarException{
		this.lastLocChgDate.setCalendarValue(lastLocChgDate);
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� ���ң�ETS_ITEM_INFO.ATTRIBUTE3��
	 * @param manufactory String
	 */
	public void setManufactory(String manufactory){
		this.manufactory = manufactory;
	}

	/**
	 * ���ܣ����ñ�ṹ����-L(EAM)���� ��עETS_ITEM_INFO.REMARK
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� EAM_DH_BILL_L_S.NEXTVAL
	 * @return String
	 */
	public String getBillLineId() {
		return this.billLineId;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� EAM_DH_BILL_H. BILL_HEADER_ID
	 * @return String
	 */
	public String getBillHeaderId() {
		return this.billHeaderId;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� Ŀ¼���EAM_DH_CATALOG_VALUES. Catalog_VALUE_ID
	 * @return String
	 */
	public int getCatalogValueId() {
		return this.catalogValueId;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� �豸���루ETS_SYSTEM_ITEM.ITEM_CODE��
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� ����
	 * @return String
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� ����
	 * @return String
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� ʹ�ò���(ETS_ITEM_INFO. RESPONSIBILITY_DEPT),�����ֵ䣺ams_mis_dept.dept_code
	 * @return String
	 */
	public String getResponsibilityDept() {
		return this.responsibilityDept;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� �����ˣ�ETS_ITEM_INFO. RESPONSIBILITY_USER��,
�����ֵ䣺ams_mis_employ.employee_id

	 * @return String
	 */
	public int getResponsibilityUser() {
		return this.responsibilityUser;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� �ص㣨ETS_OBJECT. WORKORDER_OBJECT_NAME��
	 * @return String
	 */
	public int getWorkorderObjectNo() {
		return this.workorderObjectNo;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� �����ˣ�ETS_ITEM_INFO. MAINTAIN_USER��,�ֹ���д
	 * @return String
	 */
	public String getMaintainUser() {
		return this.maintainUser;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� �������ڣ�ETS_ITEM_INFO. LAST_LOC_CHG_DATE��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastLocChgDate() throws CalendarException {
		lastLocChgDate.setCalPattern(getCalPattern());
		return this.lastLocChgDate;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� ���ң�ETS_ITEM_INFO.ATTRIBUTE3��
	 * @return String
	 */
	public String getManufactory() {
		return this.manufactory;
	}

	/**
	 * ���ܣ���ȡ��ṹ����-L(EAM)���� ��עETS_ITEM_INFO.REMARK
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getFinanceProp() {
		return financeProp;
	}

	public void setFinanceProp(String financeProp) {
		this.financeProp = financeProp;
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

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
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
}