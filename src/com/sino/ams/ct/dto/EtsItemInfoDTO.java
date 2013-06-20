package com.sino.ams.ct.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: ��ͨ�豸���� EtsItemInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ��ʿ��
 * @version 1.0
 */
public class EtsItemInfoDTO extends CommonRecordDTO {

	private String systemid = "";  //ϵͳID(���к�)
	
	private String faBarcode = ""; //��ӦMIS�̶��ʲ�����;��ֹʹ���ֶ�
	
	private String vendorBarcode = ""; //��Ӧ��������
	
	private int itemQty ; //�豸����
	
	private SimpleCalendar disableDate = null; //ʧЧ����

	private String remark = ""; //��ע
	
	private SimpleCalendar startDate = null; //��������

	private String itemCode = ""; //�������
	
	private int projectid ; //����ID
	 
	private String itemStatus = ""; //�豸״̬
	
	private String attribute1 = ""; //��չ����1;��������
	
	private String attribute2 = ""; //��չ����2
	
	private String sendtomisFlag = ""; //ͬ����MIS��־
	
	private String misItemname = ""; //MIS�豸����
	
	private String misItemspec = ""; //MIS����ͺ�
	
	private String assetId = ""; //�����ʲ�ID
	
	private String addressId = ""; //�ʲ��ص�ID
	
	private int financeProp = 0; //��������
	
	private String attribute3 = ""; //��չ����3(�����Ǳ���;)
	
	private String parentBarcode = ""; //����ǩ��
	
	private SimpleCalendar lastLocChgDate = null; //�豸�ص����䶯ʱ��
	
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE; //��֯ID
	
	private String barcode = ""; //��ǩ��
	
	private String isParent = ""; //�Ƿ��Ǹ��豸
	
	private int responsibilityUser ; //������
	
	private String responsibilityDept = ""; //���β���
	
	private String maintainUser = ""; //ά����Ա
	
	private String maintainDept = ""; //ʹ�ò���
	
	private String price = ""; //����
	
	private String isTmp = ""; //1:��ʱ�豸
	
	private String qryType = ""; //�����������жϰ������������в�ѯ�����磺�������ѯ���߰��豸���Ʋ�ѯ
	
	private SimpleCalendar minTime = null; //��С��������
	
    private SimpleCalendar maxTime = null; //�����������
	
	private String daiwei = ""; //��ά��˾
	
	//=====================================������ETS_SYSTEM_ITEM���е�����======================================//
	private String itemCategory2 = ""; //Ŀ¼���
	
	//=====================================�����Ǹ����DTOҪ�õ������������=======================================//
	
	private int objectCategory = 0; //�ص�����
	
	private int itemCategory; //�豸����
	
	private String itemName = ""; //�豸����
	
	private String name = ""; //��������
	private String projectName = ""; //��������
	
	private String itemSpec = ""; //�豸�ͺ�
	
	private String workorderObjectName = ""; //�ص�����
	
	private int invType = 0; //ETS_FLEX_VALUE_SET����CODE�ֶ�˵��Ӧ��ֵ�������ǲֿ����ͣ������ֵ伯��
	
	private String countyCode = ""; //ETS_COUNTY���е������ֵ䣬Ϊ��ѯ��������
	
	private String workorderObjectNo = ""; //ETS_OBJECT�������
	
	private String key = ""; //��ͨ�ʲ�ƥ��ҳ���õ��Ĳ�ѯ������

	private String assetNumber = "";
	private String isRetirements = "";
	private String itemCategoryName = "";
	private String workorderObjectCode = "";
	private String employeeNumber = "";
	private String responsibilityUserName = "";
	private String responsibilityDeptName = "";
	private String maintainUserName = "";
	private String vendorName = "";
	private String itemStatusName = "";
	private String tagNumber = "";
	private String faCategory1 = "";
	private String faCategory2 = "";
	private String assetsDescription = "";
	private String modelNumber = "";
	private String currentUnits = "";
	private String unitOfMeasure = "";
	private String assetsLocationCode = "";
	private String assetsLocation = "";
	private String assignedToNumber = "";
	private String assignedToName = "";
	private String bookTypeCode = "";
	private String bookTypeName = "";
	private String depreciationAccount = "";
	private String depreciationAccountName = "";
	private SimpleCalendar assetsCreateDate = null;
	private SimpleCalendar datePlacedInService = null;
	private String cost = "";
	private String originalCost = "";
	private String cost0 = "";
	private String depreciation = "";
	private String deprnCost = "";
	private String lifeInYears = "";
	private String deptName = "";
	private String company = "";
	

	public EtsItemInfoDTO() {
		super();
		this.disableDate = new SimpleCalendar();
		this.startDate = new SimpleCalendar();
		this.lastLocChgDate = new SimpleCalendar();
		this.minTime = new SimpleCalendar();
		this.maxTime = new SimpleCalendar();
		this.assetsCreateDate = new SimpleCalendar();
		this.datePlacedInService = new SimpleCalendar();
	}
	
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWorkorderObjectNo() {
		return workorderObjectNo;
	}

	public void setWorkorderObjectNo(String workorderObjectNo) {
		this.workorderObjectNo = workorderObjectNo;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public int getInvType() {
		return invType;
	}

	public void setInvType(int invType) {
		this.invType = invType;
	}

	public int getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(int itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getObjectCategory() {
		return objectCategory;
	}

	public void setObjectCategory(int objectCategory) {
		this.objectCategory = objectCategory;
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
	public void setItemQty(int itemQty){
		this.itemQty = itemQty;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ʧЧ����
	 * @param disableDate SimpleCalendar
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
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������

	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException{
		this.startDate.setCalendarValue(startDate);
	}
	
	public void setAssetsCreateDate(String assetsCreateDate) throws CalendarException{
		this.assetsCreateDate.setCalendarValue(assetsCreateDate);
	}

	public void setDatePlacedInService(String datePlacedInService) throws CalendarException{
		this.datePlacedInService.setCalendarValue(datePlacedInService);
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
	public void setAssetId(String assetId){
		this.assetId = assetId;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �ʲ��ص�ID
	 * @param addressId String
	 */
	public void setAddressId(String addressId){
		this.addressId = addressId;
	}

	/**
	 * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
	 * @param financeProp String
	 */
	public void setFinanceProp(int financeProp){
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
	 * @param lastLocChgDate SimpleCalendar
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
	public int getItemQty() {
		return this.itemQty;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ʧЧ����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getDisableDate() {
		try {
			disableDate.setCalPattern(getCalPattern());
		} catch (CalendarException e) {
			e.printLog();
		}
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
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������

	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}
	
	public SimpleCalendar getAssetsCreateDate() throws CalendarException {
		assetsCreateDate.setCalPattern(getCalPattern());
		return this.assetsCreateDate;
	}
	
	public SimpleCalendar getDatePlacedInService() throws CalendarException {
		datePlacedInService.setCalPattern(getCalPattern());
		return this.datePlacedInService;
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
	public String getAssetId() {
		return this.assetId;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �ʲ��ص�ID
	 * @return String
	 */
	public String getAddressId() {
		return this.addressId;
	}

	/**
	 * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
	 * @return String
	 */
	public int getFinanceProp() {
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
	
	public void setQryType(String qryType) {
		this.qryType = qryType;
	}
	
	public String getQryType() {
		return this.qryType;
	}

	public String getDaiwei() {
		return daiwei;
	}

	public void setDaiwei(String daiwei) {
		this.daiwei = daiwei;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getWorkorderObjectName() {
		return workorderObjectName;
	}

	public void setWorkorderObjectName(String workorderObjectName) {
		this.workorderObjectName = workorderObjectName;
	}
	
	public SimpleCalendar getMaxTime() {
		return maxTime;
	}
	
	public void setMaxTime(String maxTime) {
		this.maxTime = new SimpleCalendar(maxTime);
	}

	/*
	public SimpleCalendar getMaxTime() throws CalendarException {
		maxTime.setCalPattern(getCalPattern());
		return this.maxTime;
	}

	public void setMaxTime(String maxTime) throws CalendarException {
		this.maxTime.setCalendarValue(maxTime);
	}
	*/

	public SimpleCalendar getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = new SimpleCalendar(minTime);
    }
    /*
	public SimpleCalendar getMinTime() throws CalendarException {
		minTime.setCalPattern(getCalPattern());
		return this.minTime;
	}

	public void setMinTime(String minTime) throws CalendarException {
		this.minTime.setCalendarValue(minTime);
	}
	*/

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getItemCategory2() {
		return itemCategory2;
	}

	public void setItemCategory2(String itemCategory2) {
		this.itemCategory2 = itemCategory2;
	}



	public String getAssetNumber() {
		return assetNumber;
	}



	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}



	public String getIsRetirements() {
		return isRetirements;
	}



	public void setIsRetirements(String isRetirements) {
		this.isRetirements = isRetirements;
	}



	public String getItemCategoryName() {
		return itemCategoryName;
	}



	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}



	public String getWorkorderObjectCode() {
		return workorderObjectCode;
	}

	public void setWorkorderObjectCode(String workorderObjectCode) {
		this.workorderObjectCode = workorderObjectCode;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getResponsibilityUserName() {
		return responsibilityUserName;
	}

	public void setResponsibilityUserName(String responsibilityUserName) {
		this.responsibilityUserName = responsibilityUserName;
	}

	public String getResponsibilityDeptName() {
		return responsibilityDeptName;
	}

	public void setResponsibilityDeptName(String responsibilityDeptName) {
		this.responsibilityDeptName = responsibilityDeptName;
	}

	public String getMaintainUserName() {
		return maintainUserName;
	}

	public void setMaintainUserName(String maintainUserName) {
		this.maintainUserName = maintainUserName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getItemStatusName() {
		return itemStatusName;
	}

	public void setItemStatusName(String itemStatusName) {
		this.itemStatusName = itemStatusName;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public String getFaCategory1() {
		return faCategory1;
	}

	public void setFaCategory1(String faCategory1) {
		this.faCategory1 = faCategory1;
	}

	public String getFaCategory2() {
		return faCategory2;
	}

	public void setFaCategory2(String faCategory2) {
		this.faCategory2 = faCategory2;
	}

	public String getAssetsDescription() {
		return assetsDescription;
	}

	public void setAssetsDescription(String assetsDescription) {
		this.assetsDescription = assetsDescription;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getCurrentUnits() {
		return currentUnits;
	}

	public void setCurrentUnits(String currentUnits) {
		this.currentUnits = currentUnits;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getAssetsLocationCode() {
		return assetsLocationCode;
	}

	public void setAssetsLocationCode(String assetsLocationCode) {
		this.assetsLocationCode = assetsLocationCode;
	}

	public String getAssetsLocation() {
		return assetsLocation;
	}

	public void setAssetsLocation(String assetsLocation) {
		this.assetsLocation = assetsLocation;
	}

	public String getAssignedToNumber() {
		return assignedToNumber;
	}

	public void setAssignedToNumber(String assignedToNumber) {
		this.assignedToNumber = assignedToNumber;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	public String getBookTypeCode() {
		return bookTypeCode;
	}

	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	public String getDepreciationAccount() {
		return depreciationAccount;
	}

	public void setDepreciationAccount(String depreciationAccount) {
		this.depreciationAccount = depreciationAccount;
	}

	public String getDepreciationAccountName() {
		return depreciationAccountName;
	}

	public void setDepreciationAccountName(String depreciationAccountName) {
		this.depreciationAccountName = depreciationAccountName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(String originalCost) {
		this.originalCost = originalCost;
	}

	public String getCost0() {
		return cost0;
	}

	public void setCost0(String cost0) {
		this.cost0 = cost0;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getDeprnCost() {
		return deprnCost;
	}

	public void setDeprnCost(String deprnCost) {
		this.deprnCost = deprnCost;
	}

	public String getLifeInYears() {
		return lifeInYears;
	}

	public void setLifeInYears(String lifeInYears) {
		this.lifeInYears = lifeInYears;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}

}
