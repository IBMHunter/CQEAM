package com.sino.ams.ct.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class EtsFaAssetsDTO extends EtsItemInfoDTO {
	
	private String assetsDescription = ""; //�ʲ�����
	
	private String modelNumber = ""; //�ʲ��ͺ�
	
	private String assetNumber = ""; //�ʲ����
	
	private String tagNumber = ""; //�ʲ���ǩ��
	
	private String assetsStatus = ""; //�ʲ�״̬
	
	private String originalCost = ""; //�ʲ�ԭֵ
	
	private SimpleCalendar datePlacedInService = null; //��������
	
	private String deprnCost = ""; //��ֵ
	
	private String assignedToName = ""; //����������
		
	private String assignedToNumber = ""; //�����˹���
	
	private int responsibilityUser; //������
	
	private String responsibilityDept = ""; //���β���
	
	private SimpleCalendar retireDate = null; //��������
	
	//============================�����Ǳ����ʲ���ϸ��Ϣ��ѯ��Ҫ��ӵ�����==============================================//
	
	private String isRetirements = ""; //�Ƿ񱨷�
	
	private String faCategoryCode = "";
	
	private String unitOfMeasure = "";
	
	private String lifeInYears = "";
	
	private String currentUnits = "";
	
	private String assetsLocation = "";
	
	private String cost = "";
	
	private String bookTypeCode = "";
	
	private String transToMis = "";
	
	private String transToMisDesc = "";
	
	private String transToMisTag = "";
	
	private String transToMisLoc = "";
	
	private String codeCombinationId = "";
	
	private String companyCode = "";
	
	private String scrapValue = "";
	
	private String depreciationAccount = "";
	
	private String assetsLocationCode = "";
	
	private String impairReserve = "";
	
	private String deprnReserve = "";
	
	//=======================================�����Ǵ�ͨ�ʲ�ƥ��ģ����Ҫ������=======================================//
	private String countyCodeMis = ""; //ETS_COUNTY���еĶ�ӦMIS�����ֶ�
	
	public String getCountyCodeMis() {
		return countyCodeMis;
	}

	public void setCountyCodeMis(String countyCodeMis) {
		this.countyCodeMis = countyCodeMis;
	}

	public EtsFaAssetsDTO() {
		super();
		this.datePlacedInService = new SimpleCalendar();
		this.retireDate = new SimpleCalendar();
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	public String getAssetsDescription() {
		return assetsDescription;
	}

	public void setAssetsDescription(String assetsDescription) {
		this.assetsDescription = assetsDescription;
	}

	public String getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(String assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	public String getAssignedToNumber() {
		return assignedToNumber;
	}

	public void setAssignedToNumber(String assignedToNumber) {
		this.assignedToNumber = assignedToNumber;
	}

	public SimpleCalendar getDatePlacedInService() throws CalendarException {
		datePlacedInService.setCalPattern(getCalPattern());
		return this.datePlacedInService;
	}

	public void setDatePlacedInService(String datePlacedInService) throws CalendarException {
		this.datePlacedInService.setCalendarValue(datePlacedInService);
	}

	public String getDeprnCost() {
		return deprnCost;
	}

	public void setDeprnCost(String deprnCost) {
		this.deprnCost = deprnCost;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(String originalCost) {
		this.originalCost = originalCost;
	}

	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}

	public SimpleCalendar getRetireDate() throws CalendarException {
		retireDate.setCalPattern(getCalPattern());
		return retireDate;
	}

	public void setRetireDate(String retireDate) throws CalendarException {
		this.retireDate.setCalendarValue(retireDate);
	}
	
	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public int getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(int responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getAssetsLocation() {
		return assetsLocation;
	}

	public void setAssetsLocation(String assetsLocation) {
		this.assetsLocation = assetsLocation;
	}

	public String getAssetsLocationCode() {
		return assetsLocationCode;
	}

	public void setAssetsLocationCode(String assetsLocationCode) {
		this.assetsLocationCode = assetsLocationCode;
	}

	public String getBookTypeCode() {
		return bookTypeCode;
	}

	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}

	public String getCodeCombinationId() {
		return codeCombinationId;
	}

	public void setCodeCombinationId(String codeCombinationId) {
		this.codeCombinationId = codeCombinationId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCurrentUnits() {
		return currentUnits;
	}

	public void setCurrentUnits(String currentUnits) {
		this.currentUnits = currentUnits;
	}

	public String getDepreciationAccount() {
		return depreciationAccount;
	}

	public void setDepreciationAccount(String depreciationAccount) {
		this.depreciationAccount = depreciationAccount;
	}

	public String getDeprnReserve() {
		return deprnReserve;
	}

	public void setDeprnReserve(String deprnReserve) {
		this.deprnReserve = deprnReserve;
	}

	public String getFaCategoryCode() {
		return faCategoryCode;
	}

	public void setFaCategoryCode(String faCategoryCode) {
		this.faCategoryCode = faCategoryCode;
	}

	public String getImpairReserve() {
		return impairReserve;
	}

	public void setImpairReserve(String impairReserve) {
		this.impairReserve = impairReserve;
	}

	public String getIsRetirements() {
		return isRetirements;
	}

	public void setIsRetirements(String isRetirements) {
		this.isRetirements = isRetirements;
	}

	public String getLifeInYears() {
		return lifeInYears;
	}

	public void setLifeInYears(String lifeInYears) {
		this.lifeInYears = lifeInYears;
	}

	public String getScrapValue() {
		return scrapValue;
	}

	public void setScrapValue(String scrapValue) {
		this.scrapValue = scrapValue;
	}

	public String getTransToMis() {
		return transToMis;
	}

	public void setTransToMis(String transToMis) {
		this.transToMis = transToMis;
	}

	public String getTransToMisDesc() {
		return transToMisDesc;
	}

	public void setTransToMisDesc(String transToMisDesc) {
		this.transToMisDesc = transToMisDesc;
	}

	public String getTransToMisLoc() {
		return transToMisLoc;
	}

	public void setTransToMisLoc(String transToMisLoc) {
		this.transToMisLoc = transToMisLoc;
	}

	public String getTransToMisTag() {
		return transToMisTag;
	}

	public void setTransToMisTag(String transToMisTag) {
		this.transToMisTag = transToMisTag;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
}
