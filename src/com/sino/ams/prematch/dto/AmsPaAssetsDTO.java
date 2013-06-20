package com.sino.ams.prematch.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: MISת��׼���嵥 AmsPaAssets</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsPaAssetsDTO extends CommonRecordDTO{

	private String tagNumber = "";
	private String assetsDescription = "";
	private String modelNumber = "";
	private int projectId;
	private String projectNumber = "";
	private String projectName = "";
	private String assetsLocationCode = "";
	private String assetsLocation = "";
	private int assignedToPersonId;
	private String assignedToNumber = "";
	private String assignedToName = "";
	private SimpleCalendar datePlacedInService = null;
	private String bookTypeCode = "";
	private int assetUnits;
	private int organizationId;
	private int faAssetId ;
	private int taskId;
	private String taskName = "";
	private String taskNumber = "";
	private int paAssetId;
	private String transferFlag = "";
	private String reverseFlag = "";
	private String faCategoryCode = "";
	private String segment2 = "";

	public AmsPaAssetsDTO() {
		super();
		this.datePlacedInService = new SimpleCalendar();
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ��ǩ��
	 * @param tagNumber String
	 */
	public void setTagNumber(String tagNumber){
		this.tagNumber = tagNumber;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ�����
	 * @param assetsDescription String
	 */
	public void setAssetsDescription(String assetsDescription){
		this.assetsDescription = assetsDescription;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ͺ�
	 * @param modelNumber String
	 */
	public void setModelNumber(String modelNumber){
		this.modelNumber = modelNumber;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ��Ŀ���
	 * @param projectId String
	 */
	public void setProjectId(int projectId){
		this.projectId = projectId;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� MIS������Ŀ�α��
	 * @param projectNumber String
	 */
	public void setProjectNumber(String projectNumber){
		this.projectNumber = projectNumber;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ��ĿID
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ��ص�������
	 * @param assetsLocationCode String
	 */
	public void setAssetsLocationCode(String assetsLocationCode){
		this.assetsLocationCode = assetsLocationCode;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ��ص�
	 * @param assetsLocation String
	 */
	public void setAssetsLocation(String assetsLocation){
		this.assetsLocation = assetsLocation;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ������ID
	 * @param assignedToPersonId String
	 */
	public void setAssignedToPersonId(int assignedToPersonId){
		this.assignedToPersonId = assignedToPersonId;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ������Ա����
	 * @param assignedToNumber String
	 */
	public void setAssignedToNumber(String assignedToNumber){
		this.assignedToNumber = assignedToNumber;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ����������
	 * @param assignedToName String
	 */
	public void setAssignedToName(String assignedToName){
		this.assignedToName = assignedToName;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ��������
	 * @param datePlacedInService SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDatePlacedInService(String datePlacedInService) throws CalendarException{
		this.datePlacedInService.setCalendarValue(datePlacedInService);
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ��˲�����
	 * @param bookTypeCode String
	 */
	public void setBookTypeCode(String bookTypeCode){
		this.bookTypeCode = bookTypeCode;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ�����
	 * @param assetUnits String
	 */
	public void setAssetUnits(int assetUnits){
		this.assetUnits = assetUnits;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� OU��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �̶��ʲ�ID(ת�ʺ��д)
	 * @param faAssetId String
	 */
	public void setFaAssetId(int faAssetId){
		this.faAssetId = faAssetId;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ����ID
	 * @param taskId String
	 */
	public void setTaskId(int taskId){
		this.taskId = taskId;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ��������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� PA�ʲ�ID
	 * @param paAssetId String
	 */
	public void setPaAssetId(int paAssetId){
		this.paAssetId = paAssetId;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ת�ʱ�־
	 * @param transferFlag String
	 */
	public void setTransferFlag(String transferFlag){
		this.transferFlag = transferFlag;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� ������־
	 * @param reverseFlag String
	 */
	public void setReverseFlag(String reverseFlag){
		this.reverseFlag = reverseFlag;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ����
	 * @param faCategoryCode String
	 */
	public void setFaCategoryCode(String faCategoryCode){
		this.faCategoryCode = faCategoryCode;
	}

	/**
	 * ���ܣ�����MISת��׼���嵥���� �ʲ����2����
	 * @param segment2 String
	 */
	public void setSegment2(String segment2){
		this.segment2 = segment2;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}


	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ��ǩ��
	 * @return String
	 */
	public String getTagNumber() {
		return this.tagNumber;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ�����
	 * @return String
	 */
	public String getAssetsDescription() {
		return this.assetsDescription;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ͺ�
	 * @return String
	 */
	public String getModelNumber() {
		return this.modelNumber;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ��Ŀ���
	 * @return String
	 */
	public int getProjectId() {
		return this.projectId;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� MIS������Ŀ�α��
	 * @return String
	 */
	public String getProjectNumber() {
		return this.projectNumber;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ��ĿID
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ��ص�������
	 * @return String
	 */
	public String getAssetsLocationCode() {
		return this.assetsLocationCode;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ��ص�
	 * @return String
	 */
	public String getAssetsLocation() {
		return this.assetsLocation;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ������ID
	 * @return String
	 */
	public int getAssignedToPersonId() {
		return this.assignedToPersonId;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ������Ա����
	 * @return String
	 */
	public String getAssignedToNumber() {
		return this.assignedToNumber;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ����������
	 * @return String
	 */
	public String getAssignedToName() {
		return this.assignedToName;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDatePlacedInService() throws CalendarException {
		datePlacedInService.setCalPattern(getCalPattern());
		return this.datePlacedInService;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ��˲�����
	 * @return String
	 */
	public String getBookTypeCode() {
		return this.bookTypeCode;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ�����
	 * @return String
	 */
	public int getAssetUnits() {
		return this.assetUnits;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� OU��֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �̶��ʲ�ID(ת�ʺ��д)
	 * @return String
	 */
	public int getFaAssetId() {
		return this.faAssetId;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ����ID
	 * @return String
	 */
	public int getTaskId() {
		return this.taskId;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ��������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� PA�ʲ�ID
	 * @return String
	 */
	public int getPaAssetId() {
		return this.paAssetId;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ת�ʱ�־
	 * @return String
	 */
	public String getTransferFlag() {
		return this.transferFlag;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� ������־
	 * @return String
	 */
	public String getReverseFlag() {
		return this.reverseFlag;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ����
	 * @return String
	 */
	public String getFaCategoryCode() {
		return this.faCategoryCode;
	}

	/**
	 * ���ܣ���ȡMISת��׼���嵥���� �ʲ����2����
	 * @return String
	 */
	public String getSegment2() {
		return this.segment2;
	}

	public String getTaskNumber() {
		return taskNumber;
	}
}
