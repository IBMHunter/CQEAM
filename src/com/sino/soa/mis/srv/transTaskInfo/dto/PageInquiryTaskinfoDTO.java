package com.sino.soa.mis.srv.transTaskInfo.dto;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: SRV_TASKINFO SrvTaskinfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class PageInquiryTaskinfoDTO extends CheckBoxDTO{

	private String orgId = "";
	private String orgName = "";
	private String projectId ="";
	private String segment1 = "";
	private String taskId = "";
	private String taskNumber = "";
	private String taskName = "";
	private String description = "";
	private String wbsLevel = "";
	private String taskManager = "";
	private String parentTaskId = "";
	private String parentTaskNum = "";
	private String startDate = "";
	private String completionDate = "";
	private String serviceTypeCode = "";
	private String chargeableFlag = "";
	private String billableFlag = "";
	private String costFlag = "";
	private String attribute1 = "";
	private String attribute2 = "";
	private String attribute3 = "";
	private String attribute4 = "";
	private String attribute5 = "";
	private String attribute6 = "";
	private String pmProductCode = "";
	private String pmTaskReference = "";
	private String creationDate = "";
	private String lastUpdateDate = "";
	
	private String startLastUpdatDate = "";
	private String endLastUpdatDate = "";
	private String ouOption ="";
	private String assetsType ="";

	public PageInquiryTaskinfoDTO() {
		super();

	}

	
	/**
	 * ���ܣ�����SRV_TASKINFO���� ����������֯ ����
	 * @param orgName String
	 */
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ��Ŀ���
	 * @param segment1 String
	 */
	public void setSegment1(String segment1){
		this.segment1 = segment1;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ������
	 * @param taskNumber String
	 */
	public void setTaskNumber(String taskNumber){
		this.taskNumber = taskNumber;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ��������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ����
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ������
	 * @param taskManager String
	 */
	public void setTaskManager(String taskManager){
		this.taskManager = taskManager;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ����������
	 * @param parentTaskNum String
	 */
	public void setParentTaskNum(String parentTaskNum){
		this.parentTaskNum = parentTaskNum;
	}



	/**
	 * ���ܣ�����SRV_TASKINFO���� ��������
	 * @param serviceTypeCode String
	 */
	public void setServiceTypeCode(String serviceTypeCode){
		this.serviceTypeCode = serviceTypeCode;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� �ɼƷ�
	 * @param chargeableFlag String
	 */
	public void setChargeableFlag(String chargeableFlag){
		this.chargeableFlag = chargeableFlag;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ���ʱ���
	 * @param billableFlag String
	 */
	public void setBillableFlag(String billableFlag){
		this.billableFlag = billableFlag;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� �Ƿ�Ϊ��ͬ����
	 * @param costFlag String
	 */
	public void setCostFlag(String costFlag){
		this.costFlag = costFlag;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ����
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� �ص�
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ��ע
	 * @param attribute3 String
	 */
	public void setAttribute3(String attribute3){
		this.attribute3 = attribute3;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ������Ϣ
	 * @param attribute4 String
	 */
	public void setAttribute4(String attribute4){
		this.attribute4 = attribute4;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ������Ϣ
	 * @param attribute5 String
	 */
	public void setAttribute5(String attribute5){
		this.attribute5 = attribute5;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� ������Ϣ
	 * @param attribute6 String
	 */
	public void setAttribute6(String attribute6){
		this.attribute6 = attribute6;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� PM��Ʒ��Դ
	 * @param pmProductCode String
	 */
	public void setPmProductCode(String pmProductCode){
		this.pmProductCode = pmProductCode;
	}

	/**
	 * ���ܣ�����SRV_TASKINFO���� PM��Դ�ο�
	 * @param pmTaskReference String
	 */
	public void setPmTaskReference(String pmTaskReference){
		this.pmTaskReference = pmTaskReference;
	}


	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ����������֯ ����
	 * @return String
	 */
	public String getOrgName() {
		return this.orgName;
	}


	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ��Ŀ���
	 * @return String
	 */
	public String getSegment1() {
		return this.segment1;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ������
	 * @return String
	 */
	public String getTaskNumber() {
		return this.taskNumber;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ��������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ����
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ������
	 * @return String
	 */
	public String getTaskManager() {
		return this.taskManager;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ����������
	 * @return String
	 */
	public String getParentTaskNum() {
		return this.parentTaskNum;
	}



	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ��������
	 * @return String
	 */
	public String getServiceTypeCode() {
		return this.serviceTypeCode;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� �ɼƷ�
	 * @return String
	 */
	public String getChargeableFlag() {
		return this.chargeableFlag;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ���ʱ���
	 * @return String
	 */
	public String getBillableFlag() {
		return this.billableFlag;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� �Ƿ�Ϊ��ͬ����
	 * @return String
	 */
	public String getCostFlag() {
		return this.costFlag;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ����
	 * @return String
	 */
	public String getAttribute1() {
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� �ص�
	 * @return String
	 */
	public String getAttribute2() {
		return this.attribute2;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ��ע
	 * @return String
	 */
	public String getAttribute3() {
		return this.attribute3;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ������Ϣ
	 * @return String
	 */
	public String getAttribute4() {
		return this.attribute4;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ������Ϣ
	 * @return String
	 */
	public String getAttribute5() {
		return this.attribute5;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� ������Ϣ
	 * @return String
	 */
	public String getAttribute6() {
		return this.attribute6;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� PM��Ʒ��Դ
	 * @return String
	 */
	public String getPmProductCode() {
		return this.pmProductCode;
	}

	/**
	 * ���ܣ���ȡSRV_TASKINFO���� PM��Դ�ο�
	 * @return String
	 */
	public String getPmTaskReference() {
		return this.pmTaskReference;
	}



	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the parentTaskId
	 */
	public String getParentTaskId() {
		return parentTaskId;
	}

	/**
	 * @param parentTaskId the parentTaskId to set
	 */
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the wbsLevel
	 */
	public String getWbsLevel() {
		return wbsLevel;
	}

	/**
	 * @param wbsLevel the wbsLevel to set
	 */
	public void setWbsLevel(String wbsLevel) {
		this.wbsLevel = wbsLevel;
	}

	/**
	 * @return the ouOption
	 */
	public String getOuOption() {
		return ouOption;
	}

	/**
	 * @param ouOption the ouOption to set
	 */
	public void setOuOption(String ouOption) {
		this.ouOption = ouOption;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getCompletionDate() {
		return completionDate;
	}


	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public String getLastUpdateDate() {
		return lastUpdateDate;
	}


	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	public String getStartLastUpdatDate() {
		return startLastUpdatDate;
	}


	public void setStartLastUpdatDate(String startLastUpdatDate) {
		this.startLastUpdatDate = startLastUpdatDate;
	}


	public String getEndLastUpdatDate() {
		return endLastUpdatDate;
	}


	public void setEndLastUpdatDate(String endLastUpdatDate) {
		this.endLastUpdatDate = endLastUpdatDate;
	}

}