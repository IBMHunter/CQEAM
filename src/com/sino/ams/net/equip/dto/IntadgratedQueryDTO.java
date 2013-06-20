package com.sino.ams.net.equip.dto;

import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: AMS_ASSETS_ADDRESS_V AmsAssetsAddressV</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class IntadgratedQueryDTO extends EtsItemInfoDTO{

	private String countyName = "";
	private String deptCode = "";
	private String deptName = "";
	private String workorderObjectCode = "";
	private String workorderObjectLocation = "";
	private String itemCategoryName = "";

	private String treeCategory = "";
	private String assetsCategory = "";
	private String companyName = "";
	private String exportType = "";

	private String faCategory1 = "";
	private String faCategory2 = "";
	private String assetsDescription = "";
	private String faCategoryCode = "";
	private String faCategoryName = "";

	private String unitOfMeasure = "";
	private String lifeInYears = "";
	private String modelNumber = "";
	private String tagNumber = "";
	private String currentUnits = "";
	private String assetsLocation = "";
	private SimpleCalendar datePlacedInService = null;
	private String isRetirements = "";
	private String assetNumber = "";
	private String assetsStatus = "";
	private String bookTypeCode = "";
	private String assignedToName = "";
	private String assignedToNumber = "";
	private String segment1 = "";
	private String segment2 = "";
	private String codeCombinationId = "";
	private String itemStatusName = "";
	private String transferType = "";//����������һ�����ͣ������ڣ����ż䣬���м�

	private String cost = "";//ԭֵ--�ʲ�����ʱ�ļ�ֵ
	private String deprnCost = "";//��ֵ--�ʲ�ԭֵ��ȥ�ۼ��۾ɺ��ֵ
	private String scrapValue = "";//��ֵ--������ӦΪ��ֵ
	private String depreciation = "";//�ۼ��۾�--�ӿ�ʼ�۾�������,����һ���ķ���,����ƽ���۾ɷ�,�۾ɵ�������ʱ���ۼ��۾ɶ�
	private String depreciationAccount = "";//�۾��˻�
	private String depreAccountOption = "";//�۾��˻������б�
	private String faCategoryOption = "";//�̶��ʲ��б�������

	public IntadgratedQueryDTO() {
		super();
		this.datePlacedInService = new SimpleCalendar();
	}


	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� COUNTY_NAME
	 * @param countyName String
	 */
	public void setCountyName(String countyName){
		this.countyName = countyName;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� DEPT_CODE
	 * @param deptCode String
	 */
	public void setDeptCode(String deptCode){
		this.deptCode = deptCode;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� DEPT_NAME
	 * @param deptName String
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� WORKORDER_OBJECT_CODE
	 * @param workorderObjectCode String
	 */
	public void setWorkorderObjectCode(String workorderObjectCode){
		this.workorderObjectCode = workorderObjectCode;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� WORKORDER_OBJECT_LOCATION
	 * @param workorderObjectLocation String
	 */
	public void setWorkorderObjectLocation(String workorderObjectLocation){
		this.workorderObjectLocation = workorderObjectLocation;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public void setAssetsCategory(String assetsCategory) {
		this.assetsCategory = assetsCategory;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public void setTreeCategory(String treeCategory) {
		this.treeCategory = treeCategory;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� COUNTY_NAME
	 * @return String
	 */
	public String getCountyName() {
		return this.countyName;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� DEPT_CODE
	 * @return String
	 */
	public String getDeptCode() {
		return this.deptCode;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� DEPT_NAME
	 * @return String
	 */
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� WORKORDER_OBJECT_CODE
	 * @return String
	 */
	public String getWorkorderObjectCode() {
		return this.workorderObjectCode;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� WORKORDER_OBJECT_LOCATION
	 * @return String
	 */
	public String getWorkorderObjectLocation() {
		return this.workorderObjectLocation;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public String getAssetsCategory() {
		return assetsCategory;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getExportType() {
		return exportType;
	}

	public String getTreeCategory() {
		return treeCategory;
	}


	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� FA_CATEGORY1
	 * @param faCategory1 String
	 */
	public void setFaCategory1(String faCategory1){
		this.faCategory1 = faCategory1;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� FA_CATEGORY2
	 * @param faCategory2 String
	 */
	public void setFaCategory2(String faCategory2){
		this.faCategory2 = faCategory2;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� ASSETS_DESCRIPTION
	 * @param assetsDescription String
	 */
	public void setAssetsDescription(String assetsDescription){
		this.assetsDescription = assetsDescription;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� FA_CATEGORY_CODE
	 * @param faCategoryCode String
	 */
	public void setFaCategoryCode(String faCategoryCode){
		this.faCategoryCode = faCategoryCode;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� UNIT_OF_MEASURE
	 * @param unitOfMeasure String
	 */
	public void setUnitOfMeasure(String unitOfMeasure){
		this.unitOfMeasure = unitOfMeasure;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� LIFE_IN_YEARS
	 * @param lifeInYears String
	 */
	public void setLifeInYears(String lifeInYears){
		this.lifeInYears = lifeInYears;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� MODEL_NUMBER
	 * @param modelNumber String
	 */
	public void setModelNumber(String modelNumber){
		this.modelNumber = modelNumber;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� TAG_NUMBER
	 * @param tagNumber String
	 */
	public void setTagNumber(String tagNumber){
		this.tagNumber = tagNumber;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� CURRENT_UNITS
	 * @param currentUnits String
	 */
	public void setCurrentUnits(String currentUnits){
		this.currentUnits = currentUnits;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� ASSETS_LOCATION
	 * @param assetsLocation String
	 */
	public void setAssetsLocation(String assetsLocation){
		this.assetsLocation = assetsLocation;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� DATE_PLACED_IN_SERVICE
	 * @param datePlacedInService SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDatePlacedInService(String datePlacedInService) throws CalendarException{
		this.datePlacedInService.setCalendarValue(datePlacedInService);
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� IS_RETIREMENTS
	 * @param isRetirements String
	 */
	public void setIsRetirements(String isRetirements){
		this.isRetirements = isRetirements;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� ASSET_NUMBER
	 * @param assetNumber String
	 */
	public void setAssetNumber(String assetNumber){
		this.assetNumber = assetNumber;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� COST
	 * @param cost String
	 */
	public void setCost(String cost){
		this.cost = cost;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� DEPRN_COST
	 * @param deprnCost String
	 */
	public void setDeprnCost(String deprnCost){
		this.deprnCost = deprnCost;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� ASSETS_STATUS
	 * @param assetsStatus String
	 */
	public void setAssetsStatus(String assetsStatus){
		this.assetsStatus = assetsStatus;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� BOOK_TYPE_CODE
	 * @param bookTypeCode String
	 */
	public void setBookTypeCode(String bookTypeCode){
		this.bookTypeCode = bookTypeCode;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� ASSIGNED_TO_NAME
	 * @param assignedToName String
	 */
	public void setAssignedToName(String assignedToName){
		this.assignedToName = assignedToName;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� ASSIGNED_TO_NUMBER
	 * @param assignedToNumber String
	 */
	public void setAssignedToNumber(String assignedToNumber){
		this.assignedToNumber = assignedToNumber;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� SEGMENT1
	 * @param segment1 String
	 */
	public void setSegment1(String segment1){
		this.segment1 = segment1;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� SEGMENT2
	 * @param segment2 String
	 */
	public void setSegment2(String segment2){
		this.segment2 = segment2;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_ADDRESS_V���� CODE_COMBINATION_ID
	 * @param codeCombinationId String
	 */
	public void setCodeCombinationId(String codeCombinationId){
		this.codeCombinationId = codeCombinationId;
	}

	public void setScrapValue(String scrapValue) {
		this.scrapValue = scrapValue;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public void setItemStatusName(String itemStatusName) {
		this.itemStatusName = itemStatusName;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public void setDepreciationAccount(String depreciationAccount) {
		this.depreciationAccount = depreciationAccount;
	}

	public void setDepreAccountOption(String depreAccountOption) {
		this.depreAccountOption = depreAccountOption;
	}

	public void setFaCategoryOption(String faCategoryOption) {
		this.faCategoryOption = faCategoryOption;
	}

	public void setFaCategoryName(String faCategoryName) {
		this.faCategoryName = faCategoryName;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� FA_CATEGORY1
	 * @return String
	 */
	public String getFaCategory1() {
		return this.faCategory1;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� FA_CATEGORY2
	 * @return String
	 */
	public String getFaCategory2() {
		return this.faCategory2;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� ASSETS_DESCRIPTION
	 * @return String
	 */
	public String getAssetsDescription() {
		return this.assetsDescription;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� FA_CATEGORY_CODE
	 * @return String
	 */
	public String getFaCategoryCode() {
		return this.faCategoryCode;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� UNIT_OF_MEASURE
	 * @return String
	 */
	public String getUnitOfMeasure() {
		return this.unitOfMeasure;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� LIFE_IN_YEARS
	 * @return String
	 */
	public String getLifeInYears() {
		return this.lifeInYears;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� MODEL_NUMBER
	 * @return String
	 */
	public String getModelNumber() {
		return this.modelNumber;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� TAG_NUMBER
	 * @return String
	 */
	public String getTagNumber() {
		return this.tagNumber;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� CURRENT_UNITS
	 * @return String
	 */
	public String getCurrentUnits() {
		return this.currentUnits;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� ASSETS_LOCATION
	 * @return String
	 */
	public String getAssetsLocation() {
		return this.assetsLocation;
	}


	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� DATE_PLACED_IN_SERVICE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDatePlacedInService() throws CalendarException {
		datePlacedInService.setCalPattern(getCalPattern());
		return this.datePlacedInService;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� IS_RETIREMENTS
	 * @return String
	 */
	public String getIsRetirements() {
		return this.isRetirements;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� ASSET_NUMBER
	 * @return String
	 */
	public String getAssetNumber() {
		return this.assetNumber;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� COST
	 * @return String
	 */
	public String getCost() {
		return this.cost;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� DEPRN_COST
	 * @return String
	 */
	public String getDeprnCost() {
		return this.deprnCost;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� ASSETS_STATUS
	 * @return String
	 */
	public String getAssetsStatus() {
		return this.assetsStatus;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� BOOK_TYPE_CODE
	 * @return String
	 */
	public String getBookTypeCode() {
		return this.bookTypeCode;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� ASSIGNED_TO_NAME
	 * @return String
	 */
	public String getAssignedToName() {
		return this.assignedToName;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� ASSIGNED_TO_NUMBER
	 * @return String
	 */
	public String getAssignedToNumber() {
		return this.assignedToNumber;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� SEGMENT1
	 * @return String
	 */
	public String getSegment1() {
		return this.segment1;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� SEGMENT2
	 * @return String
	 */
	public String getSegment2() {
		return this.segment2;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_ADDRESS_V���� CODE_COMBINATION_ID
	 * @return String
	 */
	public String getCodeCombinationId() {
		return this.codeCombinationId;
	}

	public String getScrapValue() {
		return scrapValue;
	}

	public String getTransferType() {
		return transferType;
	}

	public String getItemStatusName() {
		return itemStatusName;
	}

	/**
	 * ���ܣ���ȡ�ۼ��۾�
	 * @return String
	 */
	public String getDepreciation() {
		return depreciation;
	}

	public String getDepreciationAccount() {
		return depreciationAccount;
	}

	public String getDepreAccountOption() {
		return depreAccountOption;
	}

	public String getFaCategoryOption() {
		return faCategoryOption;
	}

	public String getFaCategoryName() {
		return faCategoryName;
	}
}
