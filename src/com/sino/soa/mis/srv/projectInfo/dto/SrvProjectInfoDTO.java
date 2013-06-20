package com.sino.soa.mis.srv.projectInfo.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.math.AdvancedNumber;

/**
 * <p>Title: ��Ŀ��Ϣ���� SrvProjectInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class SrvProjectInfoDTO extends CheckBoxDTO {

	private String projectId = null;
	private String name = "";
	private String segment1 = "";
	private String projectType = "";
	private String projectStatusCode = "";
	private SimpleCalendar startDate = null;
	private SimpleCalendar completionDate = null;
	private String enabledFlag = "";
	private String source = "";
	private SimpleCalendar creationDate = null;
	private AdvancedNumber createdBy = null;
	private SimpleCalendar lastUpdateDate = null;
	private AdvancedNumber lastUpdateBy = null;
	private String  misProjectId = null;
	private AdvancedNumber organizationId = null;
	private String projectClass = "";
	private String description = "";
	private String projectManager = "";
	private String pmProjectReference = "";
	private String pmProductCode = "";
	private String OuOption ="";
	private String assetsType ="";
	private String organizationName = "";
    private String orgCode="";
	public SrvProjectInfoDTO() {
		super();
		this.startDate = new SimpleCalendar();
		this.completionDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
		this.createdBy = new AdvancedNumber();
		this.lastUpdateBy = new AdvancedNumber();
		this.organizationId = new AdvancedNumber();
	}

	/**
	 * @return the ouOption
	 */
	public String getOuOption() {
		return OuOption;
	}

	/**
	 * @param ouOption the ouOption to set
	 */
	public void setOuOption(String ouOption) {
		OuOption = ouOption;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ���к�
	 * @param projectId AdvancedNumber
	 */
	public void setProjectId(String projectId){
		this.projectId = projectId;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ����
	 * @param name String
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ���
	 * @param segment1 String
	 */
	public void setSegment1(String segment1){
		this.segment1 = segment1;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ����
	 * @param projectType String
	 */
	public void setProjectType(String projectType){
		this.projectType = projectType;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ״̬����
	 * @param projectStatusCode String
	 */
	public void setProjectStatusCode(String projectStatusCode){
		this.projectStatusCode = projectStatusCode;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ��ʼ����
	 * @param startDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setStartDate(String startDate) throws CalendarException{
		this.startDate.setCalendarValue(startDate);
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ�������
	 * @param completionDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCompletionDate(String completionDate) throws CalendarException{
		this.completionDate.setCalendarValue(completionDate);
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� 'Y'
	 * @param enabledFlag String
	 */
	public void setEnabledFlag(String enabledFlag){
		this.enabledFlag = enabledFlag;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Դ 'MIS'  or 'EAM'
	 * @param source String
	 */
	public void setSource(String source){
		this.source = source;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ������
	 * @param createdBy AdvancedNumber
	 */
	public void setCreatedBy(AdvancedNumber createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy AdvancedNumber
	 */
	public void setLastUpdateBy(AdvancedNumber lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� MIS��ĿID
	 * @param misProjectId AdvancedNumber
	 */
	public void setMisProjectId(String  misProjectId){
		this.misProjectId = misProjectId;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��֯ID
	 * @param organizationId AdvancedNumber
	 */
	public void setOrganizationId(AdvancedNumber organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ����
	 * @param projectClass String
	 */
	public void setProjectClass(String projectClass){
		this.projectClass = projectClass;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��ĿժҪ
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ŀ����
	 * @param projectManager String
	 */
	public void setProjectManager(String projectManager){
		this.projectManager = projectManager;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ʒ��Դ�ο�
	 * @param pmProjectReference String
	 */
	public void setPmProjectReference(String pmProjectReference){
		this.pmProjectReference = pmProjectReference;
	}

	/**
	 * ���ܣ�������Ŀά����(EAM)���� ��Ʒ��Դ
	 * @param pmProductCode String
	 */
	public void setPmProductCode(String pmProductCode){
		this.pmProductCode = pmProductCode;
	}


	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ���к�
	 * @return AdvancedNumber
	 */
	public String getProjectId() {
		return this.projectId;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ����
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ���
	 * @return String
	 */
	public String getSegment1() {
		return this.segment1;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ����
	 * @return String
	 */
	public String getProjectType() {
		return this.projectType;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ״̬����
	 * @return String
	 */
	public String getProjectStatusCode() {
		return this.projectStatusCode;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ��ʼ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getStartDate() throws CalendarException {
		startDate.setCalPattern(getCalPattern());
		return this.startDate;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ�������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCompletionDate() throws CalendarException {
		completionDate.setCalPattern(getCalPattern());
		return this.completionDate;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� 'Y'
	 * @return String
	 */
	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Դ 'MIS'  or 'EAM'
	 * @return String
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ������
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� �ϴ��޸���
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� MIS��ĿID
	 * @return AdvancedNumber
	 */
	public String  getMisProjectId() {
		return this.misProjectId;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��֯ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ����
	 * @return String
	 */
	public String getProjectClass() {
		return this.projectClass;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��ĿժҪ
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ����
	 * @return String
	 */
	public String getProjectManager() {
		return this.projectManager;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ʒ��Դ�ο�
	 * @return String
	 */
	public String getPmProjectReference() {
		return this.pmProjectReference;
	}

	/**
	 * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ʒ��Դ
	 * @return String
	 */
	public String getPmProductCode() {
		return this.pmProductCode;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}