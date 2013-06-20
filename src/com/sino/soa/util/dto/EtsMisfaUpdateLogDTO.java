package com.sino.soa.util.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ETS_MISFA_UPDATE_LOG EtsMisfaUpdateLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsMisfaUpdateLogDTO extends CheckBoxDTO{

	private String logid = "";
	private String batchId = "";
	private String barcode = "";
	private int assetId = SyBaseSQLUtil.NULL_INT_VALUE;
	private int ifUpdateTagnumber = SyBaseSQLUtil.NULL_INT_VALUE;
	private String tagNumberFrom = "";
	private String tagNumberTo = "";
	private String locationFrom = "";
	private String locationTo = "";
	private String nameFrom = "";
	private String nameTo = "";
	private String modelFrom = "";
	private String modelTo = "";
	private String ownerFrom = "";
	private String ownerTo = "";
	private String updateType = "";
	private int organizationId = 0 ;
	private int transStatus = SyBaseSQLUtil.NULL_INT_VALUE;
	private String transErrormsg = "";
	private SimpleCalendar transDate = null;
	private String remark = "";
	private String costCenterFrom = "";
	private String costCenterTo = "";
	private int codeCombinationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar creationDate = null;
	private int createdBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy = SyBaseSQLUtil.NULL_INT_VALUE;
	private int orgTo = SyBaseSQLUtil.NULL_INT_VALUE;
	private int codeCombinationIdTo = SyBaseSQLUtil.NULL_INT_VALUE;
	private String assetCategoryTo = "";
	private String transactionNo = "";
	private String manufacturerFrom ="";
	private String manufacturerTo ="";
	

	public EtsMisfaUpdateLogDTO() {
		super();
		this.transDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ETS_MISFA_UPDATE_LOG_s.nextval
	 * @param logid String
	 */
	public void setLogid(String logid){
		this.logid = logid;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� BATCH_ID
	 * @param batchId String
	 */
	public void setBatchId(String batchId){
		this.batchId = batchId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ETS_ITEM_INFO.barcode
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ASSET_ID
	 * @param assetId String
	 */
	public void setAssetId(int assetId){
		this.assetId = assetId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� IF_UPDATE_TAGNUMBER
	 * @param ifUpdateTagnumber String
	 */
	public void setIfUpdateTagnumber(int ifUpdateTagnumber){
		this.ifUpdateTagnumber = ifUpdateTagnumber;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� TAG_NUMBER_FROM
	 * @param tagNumberFrom String
	 */
	public void setTagNumberFrom(String tagNumberFrom){
		this.tagNumberFrom = tagNumberFrom;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� TAG_NUMBER_TO
	 * @param tagNumberTo String
	 */
	public void setTagNumberTo(String tagNumberTo){
		this.tagNumberTo = tagNumberTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� LOCATION_FROM
	 * @param locationFrom String
	 */
	public void setLocationFrom(String locationFrom){
		this.locationFrom = locationFrom;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� LOCATION_TO
	 * @param locationTo String
	 */
	public void setLocationTo(String locationTo){
		this.locationTo = locationTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� NAME_FROM
	 * @param nameFrom String
	 */
	public void setNameFrom(String nameFrom){
		this.nameFrom = nameFrom;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� NAME_TO
	 * @param nameTo String
	 */
	public void setNameTo(String nameTo){
		this.nameTo = nameTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� MODEL_FROM
	 * @param modelFrom String
	 */
	public void setModelFrom(String modelFrom){
		this.modelFrom = modelFrom;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� MODEL_TO
	 * @param modelTo String
	 */
	public void setModelTo(String modelTo){
		this.modelTo = modelTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� OWNER_FROM
	 * @param ownerFrom String
	 */
	public void setOwnerFrom(String ownerFrom){
		this.ownerFrom = ownerFrom;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� OWNER_TO
	 * @param ownerTo String
	 */
	public void setOwnerTo(String ownerTo){
		this.ownerTo = ownerTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� LOCATION���ص���DESC���ʲ����ƹ���ͺű��TAG_NUMBER:�����OWNER�������˱��ALL:�����+�ʲ���+�ͺ�+�ص�
	 * @param updateType String
	 */
	public void setUpdateType(String updateType){
		this.updateType = updateType;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ORGANIZATION_ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� 0:δ����  1�����  2������
	 * @param transStatus String
	 */
	public void setTransStatus(int transStatus){
		this.transStatus = transStatus;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� TRANS_ERRORMSG
	 * @param transErrormsg String
	 */
	public void setTransErrormsg(String transErrormsg){
		this.transErrormsg = transErrormsg;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� TRANS_DATE
	 * @param transDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setTransDate(String transDate) throws CalendarException{
		this.transDate.setCalendarValue(transDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� REMARK
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� COST_CENTER_FROM
	 * @param costCenterFrom String
	 */
	public void setCostCenterFrom(String costCenterFrom){
		this.costCenterFrom = costCenterFrom;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� COST_CENTER_TO
	 * @param costCenterTo String
	 */
	public void setCostCenterTo(String costCenterTo){
		this.costCenterTo = costCenterTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� CODE_COMBINATION_ID
	 * @param codeCombinationId String
	 */
	public void setCodeCombinationId(int codeCombinationId){
		this.codeCombinationId = codeCombinationId;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ORG_TO
	 * @param orgTo String
	 */
	public void setOrgTo(int orgTo){
		this.orgTo = orgTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� CODE_COMBINATION_ID_TO
	 * @param codeCombinationIdTo String
	 */
	public void setCodeCombinationIdTo(int codeCombinationIdTo){
		this.codeCombinationIdTo = codeCombinationIdTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ASSET_CATEGORY_TO
	 * @param assetCategoryTo String
	 */
	public void setAssetCategoryTo(String assetCategoryTo){
		this.assetCategoryTo = assetCategoryTo;
	}

	/**
	 * ���ܣ�����ETS_MISFA_UPDATE_LOG���� ��ŵ������Ż򱨷ϵ����
	 * @param transactionNo String
	 */
	public void setTransactionNo(String transactionNo){
		this.transactionNo = transactionNo;
	}


	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ETS_MISFA_UPDATE_LOG_s.nextval
	 * @return String
	 */
	public String getLogid() {
		return this.logid;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� BATCH_ID
	 * @return String
	 */
	public String getBatchId() {
		return this.batchId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ETS_ITEM_INFO.barcode
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ASSET_ID
	 * @return String
	 */
	public int getAssetId() {
		return this.assetId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� IF_UPDATE_TAGNUMBER
	 * @return String
	 */
	public int getIfUpdateTagnumber() {
		return this.ifUpdateTagnumber;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� TAG_NUMBER_FROM
	 * @return String
	 */
	public String getTagNumberFrom() {
		return this.tagNumberFrom;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� TAG_NUMBER_TO
	 * @return String
	 */
	public String getTagNumberTo() {
		return this.tagNumberTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� LOCATION_FROM
	 * @return String
	 */
	public String getLocationFrom() {
		return this.locationFrom;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� LOCATION_TO
	 * @return String
	 */
	public String getLocationTo() {
		return this.locationTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� NAME_FROM
	 * @return String
	 */
	public String getNameFrom() {
		return this.nameFrom;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� NAME_TO
	 * @return String
	 */
	public String getNameTo() {
		return this.nameTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� MODEL_FROM
	 * @return String
	 */
	public String getModelFrom() {
		return this.modelFrom;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� MODEL_TO
	 * @return String
	 */
	public String getModelTo() {
		return this.modelTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� OWNER_FROM
	 * @return String
	 */
	public String getOwnerFrom() {
		return this.ownerFrom;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� OWNER_TO
	 * @return String
	 */
	public String getOwnerTo() {
		return this.ownerTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� LOCATION���ص���DESC���ʲ����ƹ���ͺű��TAG_NUMBER:�����OWNER�������˱��ALL:�����+�ʲ���+�ͺ�+�ص�
	 * @return String
	 */
	public String getUpdateType() {
		return this.updateType;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ORGANIZATION_ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� 0:δ����  1�����  2������
	 * @return String
	 */
	public int getTransStatus() {
		return this.transStatus;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� TRANS_ERRORMSG
	 * @return String
	 */
	public String getTransErrormsg() {
		return this.transErrormsg;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� TRANS_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getTransDate() throws CalendarException {
		transDate.setCalPattern(getCalPattern());
		return this.transDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� REMARK
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� COST_CENTER_FROM
	 * @return String
	 */
	public String getCostCenterFrom() {
		return this.costCenterFrom;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� COST_CENTER_TO
	 * @return String
	 */
	public String getCostCenterTo() {
		return this.costCenterTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� CODE_COMBINATION_ID
	 * @return String
	 */
	public int getCodeCombinationId() {
		return this.codeCombinationId;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ������
	 * @return String
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ORG_TO
	 * @return String
	 */
	public int getOrgTo() {
		return this.orgTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� CODE_COMBINATION_ID_TO
	 * @return String
	 */
	public int getCodeCombinationIdTo() {
		return this.codeCombinationIdTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ASSET_CATEGORY_TO
	 * @return String
	 */
	public String getAssetCategoryTo() {
		return this.assetCategoryTo;
	}

	/**
	 * ���ܣ���ȡETS_MISFA_UPDATE_LOG���� ��ŵ������Ż򱨷ϵ����
	 * @return String
	 */
	public String getTransactionNo() {
		return this.transactionNo;
	}

	/**
	 * @return the manufacturerFrom
	 */
	public String getManufacturerFrom() {
		return manufacturerFrom;
	}

	/**
	 * @param manufacturerFrom the manufacturerFrom to set
	 */
	public void setManufacturerFrom(String manufacturerFrom) {
		this.manufacturerFrom = manufacturerFrom;
	}

	/**
	 * @return the manufacturerTo
	 */
	public String getManufacturerTo() {
		return manufacturerTo;
	}

	/**
	 * @param manufacturerTo the manufacturerTo to set
	 */
	public void setManufacturerTo(String manufacturerTo) {
		this.manufacturerTo = manufacturerTo;
	}

}