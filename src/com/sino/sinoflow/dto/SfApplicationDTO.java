package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: Ӧ����Ϣ SfApplication</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfApplicationDTO extends CheckBoxDTO{

	private int appId = 0;
    private String isFlowProcess = "";
    private int projectId = 0;
    private String projectName = "";
	private String procedureName = "";
    private String groupName = "";
    private String roleName = "";
    private String categoryName = "";
	private String appName = "";
    private String url="";
    private String appDataClass = "";
	private String appDataSqlmodel = "";
	private int windowType = 0;
	private int finishMessage = 0;
	private int confirmFinish = 0;
	private int allowOperation = 0;
	private String sortColumn1 = "";
	private String sortColumn2 = "";
	private String sortColumn3 = "";
	private String appColumn1 = "";
	private String appColumn2 = "";
	private String appColumn3 = "";
	private String appColumn4 = "";
	private String appColumn5 = "";
	private String appColumn6 = "";
	private String appColumn7 = "";
	private String appColumn8 = "";
	private String appColumn9 = "";
	private String linkProjectNameField = "";
	private String linkProjectDescField = "";
	private String linkProcedureNameField = "";
	private String linkProcedureDescField = "";
	private String linkTaskNameField = "";
	private String linkTaskDescField = "";
	private String linkTaksDurationField = "";
	private String linkGroupField = "";
	private String linkRoleFiled = "";
	private String linkUsersField = "";
	private String linkFromProjectField = "";
	private String linkFormProcedureField = "";
	private String linkFromUserField = "";
	private String linkFormDateField = "";
	private String linkFormTaskIdField = "";
	private String linkHandleGroupField = "";
	private String linkHandlerField = "";
	private String linkSignStatusField = "";
	private String linkSignDateField = "";
	private String linkTaskDueField = "";
	private String linkReviewGroupField = "";
	private String linkReviewRoleField = "";
	private String linkReviewUsersField = "";
	private String linkReviewStatusField = "";
	private String linkDeliveryPriorityField = "";
	private String linkUserMessageField = "";
	private String linkNextCyclePropField = "";
	private String viewInList = "";
    private String trayType = "";
    private String procUrl = "";

    public String getViewInList() {
		return viewInList;
	}

	public void setViewInList(String viewInList) {
		this.viewInList = viewInList;
	}

	public SfApplicationDTO() {
		super();
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� Ӧ�� ID
	 * @param appId String
	 */
	public void setAppId(int appId){
		this.appId = appId;
	}

    /**
     * ���ܣ�����Ӧ����Ϣ���� ����Ӧ��, Y:�� N:��
     * @param isFlowProcess String
     */
    public void setIsFlowProcess(String isFlowProcess){
        this.isFlowProcess = isFlowProcess;
    }


    /**
	 * ���ܣ�����Ӧ����Ϣ���� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��������
	 * @param procedureName String
	 */
	public void setProcedureName(String procedureName){
		this.procedureName = procedureName;
	}

    /**
     * ���ܣ�����Ӧ����Ϣ���� �������
     * @param groupName String
     */
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    /**
	 * ���ܣ�����Ӧ����Ϣ���� ��ʾӦ�÷�������
	 * @param categoryName String
	 */
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ʾӦ������
	 * @param appName String
	 */
	public void setAppName(String appName){
		this.appName = appName;
	}

    /**
     * ���ܣ�����Ӧ����Ϣ���� Ӧ�� URL
     * @param url String
     */
    public void setUrl(String url){
        this.url = url;
    }

    /**
	 * ���ܣ�����Ӧ����Ϣ���� Ӧ�����ݽӿ�����
	 * @param appDataClass String
	 */
	public void setAppDataClass(String appDataClass){
		this.appDataClass = appDataClass;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� Ӧ������ SQLModel, �� SQLModel Ϊ�����ʾӦ�����Լ���ȡӦ�õ�����
	 * @param appDataSqlmodel String
	 */
	public void setAppDataSqlmodel(String appDataSqlmodel){
		this.appDataSqlmodel = appDataSqlmodel;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� Ӧ����ʾ��������, 0:�ҿ���� 1:�´���
	 * @param windowType String
	 */
	public void setWindowType(int windowType){
		this.windowType = windowType;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���ʱ������Ϣ, 0:��ֹ 1:����
	 * @param finishMessage String
	 */
	public void setFinishMessage(int finishMessage){
		this.finishMessage = finishMessage;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ȷ����Ϣ��, 0:��ֹ 1:����
	 * @param confirmFinish String
	 */
	public void setConfirmFinish(int confirmFinish){
		this.confirmFinish = confirmFinish;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���������, bit 0:ȡ��, bit 1:����, bit 2:�˻�, bit 3:�鿴����
	 * @param allowOperation String
	 */
	public void setAllowOperation(int allowOperation){
		this.allowOperation = allowOperation;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ������
	 * @param sortColumn1 String
	 */
	public void setSortColumn1(String sortColumn1){
		this.sortColumn1 = sortColumn1;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �η���
	 * @param sortColumn2 String
	 */
	public void setSortColumn2(String sortColumn2){
		this.sortColumn2 = sortColumn2;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��������
	 * @param sortColumn3 String
	 */
	public void setSortColumn3(String sortColumn3){
		this.sortColumn3 = sortColumn3;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �ؼ�������, Ӧ�ñ���ʹ���ڹؼ��ֵ�����
	 * @param appColumn1 String
	 */
	public void setAppColumn1(String appColumn1){
		this.appColumn1 = appColumn1;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��������, Ӧ�ñ���ʹ�������������
	 * @param appColumn2 String
	 */
	public void setAppColumn2(String appColumn2){
		this.appColumn2 = appColumn2;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��������, Ӧ�ñ���ʹ��������������
	 * @param appColumn3 String
	 */
	public void setAppColumn3(String appColumn3){
		this.appColumn3 = appColumn3;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���� 1, �����Ӧ�ñ�����
	 * @param appColumn4 String
	 */
	public void setAppColumn4(String appColumn4){
		this.appColumn4 = appColumn4;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���� 2, �����Ӧ�ñ�����
	 * @param appColumn5 String
	 */
	public void setAppColumn5(String appColumn5){
		this.appColumn5 = appColumn5;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���� 3, �����Ӧ�ñ�����
	 * @param appColumn6 String
	 */
	public void setAppColumn6(String appColumn6){
		this.appColumn6 = appColumn6;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���� 4, �����Ӧ�ñ�����
	 * @param appColumn7 String
	 */
	public void setAppColumn7(String appColumn7){
		this.appColumn7 = appColumn7;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���� 5, �����Ӧ�ñ�����
	 * @param appColumn8 String
	 */
	public void setAppColumn8(String appColumn8){
		this.appColumn8 = appColumn8;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���� 6, �����Ӧ�ñ�����
	 * @param appColumn9 String
	 */
	public void setAppColumn9(String appColumn9){
		this.appColumn9 = appColumn9;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �������Ʒ�����
	 * @param linkProjectNameField String
	 */
	public void setLinkProjectNameField(String linkProjectNameField){
		this.linkProjectNameField = linkProjectNameField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��������������
	 * @param linkProjectDescField String
	 */
	public void setLinkProjectDescField(String linkProjectDescField){
		this.linkProjectDescField = linkProjectDescField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �������Ʒ�����
	 * @param linkProcedureNameField String
	 */
	public void setLinkProcedureNameField(String linkProcedureNameField){
		this.linkProcedureNameField = linkProcedureNameField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��������������
	 * @param linkProcedureDescField String
	 */
	public void setLinkProcedureDescField(String linkProcedureDescField){
		this.linkProcedureDescField = linkProcedureDescField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰ�������Ʒ�����
	 * @param linkTaskNameField String
	 */
	public void setLinkTaskNameField(String linkTaskNameField){
		this.linkTaskNameField = linkTaskNameField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰ��������������
	 * @param linkTaskDescField String
	 */
	public void setLinkTaskDescField(String linkTaskDescField){
		this.linkTaskDescField = linkTaskDescField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰ����Ԥ��ʱ�������
	 * @param linkTaksDurationField String
	 */
	public void setLinkTaksDurationField(String linkTaksDurationField){
		this.linkTaksDurationField = linkTaksDurationField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰ����ָ����������
	 * @param linkGroupField String
	 */
	public void setLinkGroupField(String linkGroupField){
		this.linkGroupField = linkGroupField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰ����ָ����ɫ������
	 * @param linkRoleFiled String
	 */
	public void setLinkRoleFiled(String linkRoleFiled){
		this.linkRoleFiled = linkRoleFiled;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰ��ɫ�����û�������, ���ж���û�, ���� ��;�� �ָ�
	 * @param linkUsersField String
	 */
	public void setLinkUsersField(String linkUsersField){
		this.linkUsersField = linkUsersField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ת���������Ʒ�����
	 * @param linkFromProjectField String
	 */
	public void setLinkFromProjectField(String linkFromProjectField){
		this.linkFromProjectField = linkFromProjectField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ת���������Ʒ�����
	 * @param linkFormProcedureField String
	 */
	public void setLinkFormProcedureField(String linkFormProcedureField){
		this.linkFormProcedureField = linkFormProcedureField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ת���˷�����
	 * @param linkFromUserField String
	 */
	public void setLinkFromUserField(String linkFromUserField){
		this.linkFromUserField = linkFromUserField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ת��ʱ�������
	 * @param linkFormDateField String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLinkFormDateField(String linkFormDateField) throws CalendarException{
		this.linkFormDateField = linkFormDateField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ת������ ID ������
	 * @param linkFormTaskIdField String
	 */
	public void setLinkFormTaskIdField(String linkFormTaskIdField){
		this.linkFormTaskIdField = linkFormTaskIdField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �̳���������
	 * @param linkHandleGroupField String
	 */
	public void setLinkHandleGroupField(String linkHandleGroupField){
		this.linkHandleGroupField = linkHandleGroupField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �����˷�����, ���ж��������, ���� ��;���ָ�
	 * @param linkHandlerField String
	 */
	public void setLinkHandlerField(String linkHandlerField){
		this.linkHandlerField = linkHandlerField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� �Ƿ�ǩ�շ�����, Y, N
	 * @param linkSignStatusField String
	 */
	public void setLinkSignStatusField(String linkSignStatusField){
		this.linkSignStatusField = linkSignStatusField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ǩ��ʱ�������
	 * @param linkSignDateField String
	 */
	public void setLinkSignDateField(String linkSignDateField){
		this.linkSignDateField = linkSignDateField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� Ӧת��ʱ�������
	 * @param linkTaskDueField String
	 */
	public void setLinkTaskDueField(String linkTaskDueField){
		this.linkTaskDueField = linkTaskDueField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ʾ��������, �� ��;�� �ָ�
	 * @param linkReviewGroupField String
	 */
	public void setLinkReviewGroupField(String linkReviewGroupField){
		this.linkReviewGroupField = linkReviewGroupField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ʾ��ɫ������
	 * @param linkReviewRoleField String
	 */
	public void setLinkReviewRoleField(String linkReviewRoleField){
		this.linkReviewRoleField = linkReviewRoleField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ���п���ʾ�û�������, �� ��; ���ָ�
	 * @param linkReviewUsersField String
	 */
	public void setLinkReviewUsersField(String linkReviewUsersField){
		this.linkReviewUsersField = linkReviewUsersField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��ǰΪ��ʾ״̬������, Y, N
	 * @param linkReviewStatusField String
	 */
	public void setLinkReviewStatusField(String linkReviewStatusField){
		this.linkReviewStatusField = linkReviewStatusField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ת�������̶ȷ�����, 0-����, 1-����, 2-�ؼ�
	 * @param linkDeliveryPriorityField String
	 */
	public void setLinkDeliveryPriorityField(String linkDeliveryPriorityField){
		this.linkDeliveryPriorityField = linkDeliveryPriorityField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ������Ϣ������
	 * @param linkUserMessageField String
	 */
	public void setLinkUserMessageField(String linkUserMessageField){
		this.linkUserMessageField = linkUserMessageField;
	}

	/**
	 * ���ܣ�����Ӧ����Ϣ���� ��һ�����ǩ������Ա������, ��ʽΪ (RC1: USER1;USER2;��;|RC_2:USER1;USER2;��;|��). RC1 �����һ�������ķ���������, USER1, USER2 �ȴ���÷���ָ��Ļ�ǩ�������п��ܵĲ�����; RC2, RC3, �� �ȴ���ڶ������һ�������ķ���������, ��������Ϊֱ��, �� RC1 Ϊ��, ������ (:USER1,USER2,��;)
	 * @param linkNextCyclePropField String
	 */
	public void setLinkNextCyclePropField(String linkNextCyclePropField){
		this.linkNextCyclePropField = linkNextCyclePropField;
	}

    public void setTrayType(String trayType) {
        this.trayType = trayType;
    }

    /**
	 * ���ܣ���ȡӦ����Ϣ���� Ӧ�� ID  
	 * @return String
	 */
	public int getAppId() {
		return this.appId;
	}

    /**
     * ���ܣ���ȡӦ����Ϣ���� ����Ӧ��, Y:�� N:��
     * @return String
     */
    public String getIsFlowProcess() {
        return this.isFlowProcess;
    }

    /**
	 * ���ܣ���ȡӦ����Ϣ���� ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��������
	 * @return String
	 */
	public String getProcedureName() {
		return this.procedureName;
	}

    /**
     * ���ܣ���ȡӦ����Ϣ���� �������
     * @return String
     */
    public String getGroupName() {
        return this.groupName;
    }

    /**
	 * ���ܣ���ȡӦ����Ϣ���� ��ʾӦ�÷�������
	 * @return String
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ʾӦ������
	 * @return String
	 */
	public String getAppName() {
		return this.appName;
	}

    /**
     * ���ܣ���ȡӦ����Ϣ���� Ӧ�� URL  
     * @return String
     */
    public String getUrl() {
        return this.url;
    }

    /**
	 * ���ܣ���ȡӦ����Ϣ���� Ӧ�����ݽӿ�����
	 * @return String
	 */
	public String getAppDataClass() {
		return this.appDataClass;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� Ӧ������ SQLModel, �� SQLModel Ϊ�����ʾӦ�����Լ���ȡӦ�õ�����
	 * @return String
	 */
	public String getAppDataSqlmodel() {
		return this.appDataSqlmodel;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� Ӧ����ʾ��������, 0:�ҿ���� 1:�´���
	 * @return String
	 */
	public int getWindowType() {
		return this.windowType;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���ʱ������Ϣ, 0:��ֹ 1:����
	 * @return String
	 */
	public int getFinishMessage() {
		return this.finishMessage;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ȷ����Ϣ��, 0:��ֹ 1:����
	 * @return String
	 */
	public int getConfirmFinish() {
		return this.confirmFinish;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���������, bit 0:ȡ��, bit 1:����, bit 2:�˻�, bit 3:�鿴����
	 * @return String
	 */
	public int getAllowOperation() {
		return this.allowOperation;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ������
	 * @return String
	 */
	public String getSortColumn1() {
		return this.sortColumn1;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �η���
	 * @return String
	 */
	public String getSortColumn2() {
		return this.sortColumn2;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��������
	 * @return String
	 */
	public String getSortColumn3() {
		return this.sortColumn3;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �ؼ�������, Ӧ�ñ���ʹ���ڹؼ��ֵ�����
	 * @return String
	 */
	public String getAppColumn1() {
		return this.appColumn1;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��������, Ӧ�ñ���ʹ�������������
	 * @return String
	 */
	public String getAppColumn2() {
		return this.appColumn2;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��������, Ӧ�ñ���ʹ��������������
	 * @return String
	 */
	public String getAppColumn3() {
		return this.appColumn3;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���� 1, �����Ӧ�ñ�����
	 * @return String
	 */
	public String getAppColumn4() {
		return this.appColumn4;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���� 2, �����Ӧ�ñ�����
	 * @return String
	 */
	public String getAppColumn5() {
		return this.appColumn5;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���� 3, �����Ӧ�ñ�����
	 * @return String
	 */
	public String getAppColumn6() {
		return this.appColumn6;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���� 4, �����Ӧ�ñ�����
	 * @return String
	 */
	public String getAppColumn7() {
		return this.appColumn7;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���� 5, �����Ӧ�ñ�����
	 * @return String
	 */
	public String getAppColumn8() {
		return this.appColumn8;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���� 6, �����Ӧ�ñ�����
	 * @return String
	 */
	public String getAppColumn9() {
		return this.appColumn9;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �������Ʒ�����
	 * @return String
	 */
	public String getLinkProjectNameField() {
		return this.linkProjectNameField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��������������
	 * @return String
	 */
	public String getLinkProjectDescField() {
		return this.linkProjectDescField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �������Ʒ�����
	 * @return String
	 */
	public String getLinkProcedureNameField() {
		return this.linkProcedureNameField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��������������
	 * @return String
	 */
	public String getLinkProcedureDescField() {
		return this.linkProcedureDescField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰ�������Ʒ�����
	 * @return String
	 */
	public String getLinkTaskNameField() {
		return this.linkTaskNameField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰ��������������
	 * @return String
	 */
	public String getLinkTaskDescField() {
		return this.linkTaskDescField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰ����Ԥ��ʱ�������
	 * @return String
	 */
	public String getLinkTaksDurationField() {
		return this.linkTaksDurationField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰ����ָ����������
	 * @return String
	 */
	public String getLinkGroupField() {
		return this.linkGroupField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰ����ָ����ɫ������
	 * @return String
	 */
	public String getLinkRoleFiled() {
		return this.linkRoleFiled;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰ��ɫ�����û�������, ���ж���û�, ���� ��;�� �ָ�
	 * @return String
	 */
	public String getLinkUsersField() {
		return this.linkUsersField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ת���������Ʒ�����
	 * @return String
	 */
	public String getLinkFromProjectField() {
		return this.linkFromProjectField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ת���������Ʒ�����
	 * @return String
	 */
	public String getLinkFormProcedureField() {
		return this.linkFormProcedureField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ת���˷�����
	 * @return String
	 */
	public String getLinkFromUserField() {
		return this.linkFromUserField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ת��ʱ�������
	 * @return String
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public String getLinkFormDateField() throws CalendarException {
		return this.linkFormDateField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ת������ ID ������
	 * @return String
	 */
	public String getLinkFormTaskIdField() {
		return this.linkFormTaskIdField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �̳���������
	 * @return String
	 */
	public String getLinkHandleGroupField() {
		return this.linkHandleGroupField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �����˷�����, ���ж��������, ���� ��;���ָ�
	 * @return String
	 */
	public String getLinkHandlerField() {
		return this.linkHandlerField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� �Ƿ�ǩ�շ�����, Y, N
	 * @return String
	 */
	public String getLinkSignStatusField() {
		return this.linkSignStatusField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ǩ��ʱ�������
	 * @return String
	 */
	public String getLinkSignDateField() {
		return this.linkSignDateField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� Ӧת��ʱ�������
	 * @return String
	 */
	public String getLinkTaskDueField() {
		return this.linkTaskDueField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ʾ��������, �� ��;�� �ָ�
	 * @return String
	 */
	public String getLinkReviewGroupField() {
		return this.linkReviewGroupField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ʾ��ɫ������
	 * @return String
	 */
	public String getLinkReviewRoleField() {
		return this.linkReviewRoleField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ���п���ʾ�û�������, �� ��; ���ָ�
	 * @return String
	 */
	public String getLinkReviewUsersField() {
		return this.linkReviewUsersField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��ǰΪ��ʾ״̬������, Y, N
	 * @return String
	 */
	public String getLinkReviewStatusField() {
		return this.linkReviewStatusField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ת�������̶ȷ�����, 0-����, 1-����, 2-�ؼ�
	 * @return String
	 */
	public String getLinkDeliveryPriorityField() {
		return this.linkDeliveryPriorityField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ������Ϣ������
	 * @return String
	 */
	public String getLinkUserMessageField() {
		return this.linkUserMessageField;
	}

	/**
	 * ���ܣ���ȡӦ����Ϣ���� ��һ�����ǩ������Ա������, ��ʽΪ (RC1: USER1;USER2;��;|RC_2:USER1;USER2;��;|��). RC1 �����һ�������ķ���������, USER1, USER2 �ȴ���÷���ָ��Ļ�ǩ�������п��ܵĲ�����; RC2, RC3, �� �ȴ���ڶ������һ�������ķ���������, ��������Ϊֱ��, �� RC1 Ϊ��, ������ (:USER1,USER2,��;)
	 * @return String
	 */
	public String getLinkNextCyclePropField() {
		return this.linkNextCyclePropField;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

    public String getTrayType() {
        return this.trayType;
    }

    public void setProcUrl(String procUrl) {
        this.procUrl = procUrl;
    }

    public String getProcUrl() {
        return this.procUrl;
    }
}