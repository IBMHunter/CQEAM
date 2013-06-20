package com.sino.soa.td.srv.assettransincompanysrv.dto;

import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-14
 * Time: 9:54:36
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFATdAssetsTransInCompanyDTO extends AmsAssetsTransLineDTO {

	private String batchId = "";
	private String systemIds = "";
	private String transStatus = ""; //0: �ȴ�ִ��  1:��������  2:�������
	private String errMsg = "";
	private String creationBy = null;
	private String logId = "";
	private String barCode = ""; //ETS_ITEM_INFO.barcode
	private String location = ""; //�ص�����
	private String ifUpdateTagNumber = ""; //�Ƿ���Ҫ�����ʲ�����
	private String tagNumberFrom = ""; //ԭ�ʲ�����
	private String tagNumberTo = ""; //���ʲ�����
	private String locationFrom = ""; //ԭ�ص�
	private String locationTo = ""; //�µص�
	private String nameFrom = ""; //ԭ�ʲ�����
	private String nameTo = ""; //���ʲ�����
	private String modelFrom = ""; //ԭ�ʲ��ͺ�
	private String modelTo = ""; //���ʲ��ͺ�
	private String ownerFrom = ""; //ԭ������
	private String ownerTo = ""; //��������
	private String updateType = ""; //��������
	private String transErrorMsg = ""; //���´�����־�洢
	private SimpleCalendar transDate = null; //����ʱ��
	private String costCenterFrom = ""; //ԭ�ɱ�����
	private String costCenterTo = ""; //�³ɱ�����
	private String faContentCodeOption = ""; //�ʲ�����������
	private String oldbarDoe = "";
	private String oldAssetsDedcription = "";
	private String oldModelNumber = "";
	private String oldAssetsLocation = "";
	private String newAssetsLocation = "";
	private String oldDeptName = "";
	private String oldUserName = "";
	private String newDeptName = "";
	private String newUserName = "";
	private String deptNameOption = ""; //��������������
	private String newDeptNameOption = "";
	private String logid = "";     //��������
	private String netManger = "";     //�жϹ���������
	private String netMgrOpt = "";
	private String msg = "";     //������Ϣ
	private String matchUserId = "";//ƥ����
	private String matchUserName = "";//ƥ��������
	private String lastUpdateByName = "";//EAM�ص��ϴ��޸�������
	private String organizationOpt = "";

	public SBFIFATdAssetsTransInCompanyDTO() {
		super();
		transDate = new SimpleCalendar();
	}


	public String getNewDeptNameOption() {
		return newDeptNameOption;
	}

	public void setNewDeptNameOption(String newDeptNameOption) {
		this.newDeptNameOption = newDeptNameOption;
	}

	public String getDeptNameOption() {
		return deptNameOption;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public void setDeptNameOption(String deptNameOption) {
		this.deptNameOption = deptNameOption;
	}

	public String getNewDeptName() {
		return newDeptName;
	}

	public void setNewDeptName(String newDeptName) {
		this.newDeptName = newDeptName;
	}

	public String getOldbarDoe() {
		return oldbarDoe;
	}

	public void setOldbarDoe(String oldbarDoe) {
		this.oldbarDoe = oldbarDoe;
	}

	public String getOldAssetsDedcription() {
		return oldAssetsDedcription;
	}

	public void setOldAssetsDedcription(String oldAssetsDedcription) {
		this.oldAssetsDedcription = oldAssetsDedcription;
	}

	public String getOldModelNumber() {
		return oldModelNumber;
	}

	public void setOldModelNumber(String oldModelNumber) {
		this.oldModelNumber = oldModelNumber;
	}

	public String getOldAssetsLocation() {
		return oldAssetsLocation;
	}

	public void setOldAssetsLocation(String oldAssetsLocation) {
		this.oldAssetsLocation = oldAssetsLocation;
	}

	public String getNewAssetsLocation() {
		return newAssetsLocation;
	}

	public void setNewAssetsLocation(String newAssetsLocation) {
		this.newAssetsLocation = newAssetsLocation;
	}

	public String getOldDeptName() {
		return oldDeptName;
	}

	public void setOldDeptName(String oldDeptName) {
		this.oldDeptName = oldDeptName;
	}

	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
	}

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getFaContentCodeOption() {
		return faContentCodeOption;
	}

	public void setFaContentCodeOption(String faContentCodeOption) {
		this.faContentCodeOption = faContentCodeOption;
	}

	public String getIfUpdateTagNumber() {
		return ifUpdateTagNumber;
	}

	public void setIfUpdateTagNumber(String ifUpdateTagNumber) {
		this.ifUpdateTagNumber = ifUpdateTagNumber;
	}

	public String getTagNumberFrom() {
		return tagNumberFrom;
	}

	public void setTagNumberFrom(String tagNumberFrom) {
		this.tagNumberFrom = tagNumberFrom;
	}

	public String getTagNumberTo() {
		return tagNumberTo;
	}

	public void setTagNumberTo(String tagNumberTo) {
		this.tagNumberTo = tagNumberTo;
	}

	public String getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(String locationFrom) {
		this.locationFrom = locationFrom;
	}

	public String getLocationTo() {
		return locationTo;
	}

	public void setLocationTo(String locationTo) {
		this.locationTo = locationTo;
	}

	public String getNameFrom() {
		return nameFrom;
	}

	public void setNameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}

	public String getNameTo() {
		return nameTo;
	}

	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	public String getModelFrom() {
		return modelFrom;
	}

	public void setModelFrom(String modelFrom) {
		this.modelFrom = modelFrom;
	}

	public String getModelTo() {
		return modelTo;
	}

	public void setModelTo(String modelTo) {
		this.modelTo = modelTo;
	}

	public String getOwnerFrom() {
		return ownerFrom;
	}

	public void setOwnerFrom(String ownerFrom) {
		this.ownerFrom = ownerFrom;
	}

	public String getOwnerTo() {
		return ownerTo;
	}

	public void setOwnerTo(String ownerTo) {
		this.ownerTo = ownerTo;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getTransErrorMsg() {
		return transErrorMsg;
	}

	public void setTransErrorMsg(String transErrorMsg) {
		this.transErrorMsg = transErrorMsg;
	}

	public SimpleCalendar getTransDate() throws CalendarException {
		transDate.setCalPattern(getCalPattern());
		return transDate;
	}

	public void setTransDate(SimpleCalendar transDate) throws CalendarException {
		this.transDate.setCalendarValue(transDate);
	}

	public String getCostCenterFrom() {
		return costCenterFrom;
	}

	public void setCostCenterFrom(String costCenterFrom) {
		this.costCenterFrom = costCenterFrom;
	}

	public String getCostCenterTo() {
		return costCenterTo;
	}

	public void setCostCenterTo(String costCenterTo) {
		this.costCenterTo = costCenterTo;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSystemIds() {
		return systemIds;
	}

	public void setSystemIds(String systemIds) {
		this.systemIds = systemIds;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}


	public String getNetManger() {
		return netManger;
	}

	public void setNetManger(String netManger) {
		this.netManger = netManger;
	}

	public String getMatchUserId() {
		return matchUserId;
	}

	public void setMatchUserId(String matchUserId) {
		this.matchUserId = matchUserId;
	}

	public String getMatchUserName() {
		return matchUserName;
	}

	public void setMatchUserName(String matchUserName) {
		this.matchUserName = matchUserName;
	}


	public String getLastUpdateByName() {
		return lastUpdateByName;
	}

	public void setLastUpdateByName(String lastUpdateByName) {
		this.lastUpdateByName = lastUpdateByName;
	}

	public String getOrganizationOpt() {
		return organizationOpt;
	}

	public String getNetMgrOpt() {
		return netMgrOpt;
	}

	public void setOrganizationOpt(String organizationOpt) {
		this.organizationOpt = organizationOpt;
	}

	public void setNetMgrOpt(String netMgrOpt) {
		this.netMgrOpt = netMgrOpt;
	}
}