package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �ӿڳ�����Ϣ SfApi</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfApiDTO extends CheckBoxDTO{

	private int apiId = 0;
	private int projectId = 0;
	private int procedureId = 0;
	private int taskId = 0;
	private String projectName = "";
	private String procedureName = "";
	private String taskName = "";
	private int apiControl = 0;
	private String sfqueryopen = "";
	private String sfpostopen = "";
	private String sfquerysign = "";
	private String sfpostsign = "";
	private String sfquerycomplete = "";
	private String sfgroupreview = "";
	private String sfquerycyclereview = "";
	private String sfqueryconditionalflow = "";
	private String sfquerygroup = "";
	private String sfparallelflow = "";
	private String sfqueryassistflow = "";
	private String sfquerydistribute = "";
	private String sfquerygoback = "";
	private String sfquerysave = "";
	private String sfpostsave = "";
	private String apiName = "";
	private String apiControlStr = "";
    private int taskTid = 0;
    private String enabled = "";

    public String getApiControlStr() {
		return apiControlStr;
	}

	public void setApiControlStr(String apiControlStr) {
		this.apiControlStr = apiControlStr;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public SfApiDTO() {
		super();
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� �ӿڳ��� ID  
	 * @param apiId String
	 */
	public void setApiId(int apiId){
		this.apiId = apiId;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� ��������
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� ��������
	 * @param procedureName String
	 */
	public void setProcedureName(String procedureName){
		this.procedureName = procedureName;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� ��������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

    public void setTaskTid(int taskTid) {
        this.taskTid = taskTid;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
	 * ���ܣ����ýӿڳ�����Ϣ���� API ����, bit 0 - SFQueryOpen, bit 1 - SFPostOpen, bit 2 - SFQuerySign, bit 3 - SFPostSign, bit 4 - SFQueryComplete, bit 5 - SFGroupReview, bit 6 - SFQueryCycleReview, bit 7 - SFQueryConditionalFlow, bit 8 - SFQueryGroup, bit 9 - SFParallelFlow, bit 10 - SFQueryAssistFlow, bit 11 - SFQueryDistribute, bit 12 - SFQueryGoBack, bit 13 - SFQuerySave, bit 14 - SFPostSave
	 * @param apiControl String
	 */
	public void setApiControl(int apiControl){
		this.apiControl = apiControl;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryOpen
	 * @param sfqueryopen String
	 */
	public void setSfqueryopen(String sfqueryopen){
		this.sfqueryopen = sfqueryopen;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFPostOpen
	 * @param sfpostopen String
	 */
	public void setSfpostopen(String sfpostopen){
		this.sfpostopen = sfpostopen;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQuerySign
	 * @param sfquerysign String
	 */
	public void setSfquerysign(String sfquerysign){
		this.sfquerysign = sfquerysign;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFPostSign
	 * @param sfpostsign String
	 */
	public void setSfpostsign(String sfpostsign){
		this.sfpostsign = sfpostsign;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryComplete
	 * @param sfquerycomplete String
	 */
	public void setSfquerycomplete(String sfquerycomplete){
		this.sfquerycomplete = sfquerycomplete;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFGroupReview
	 * @param sfgroupreview String
	 */
	public void setSfgroupreview(String sfgroupreview){
		this.sfgroupreview = sfgroupreview;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryCycleReview
	 * @param sfquerycyclereview String
	 */
	public void setSfquerycyclereview(String sfquerycyclereview){
		this.sfquerycyclereview = sfquerycyclereview;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryConditionalFlow
	 * @param sfqueryconditionalflow String
	 */
	public void setSfqueryconditionalflow(String sfqueryconditionalflow){
		this.sfqueryconditionalflow = sfqueryconditionalflow;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryGroup
	 * @param sfquerygroup String
	 */
	public void setSfquerygroup(String sfquerygroup){
		this.sfquerygroup = sfquerygroup;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFParallelFlow
	 * @param sfparallelflow String
	 */
	public void setSfparallelflow(String sfparallelflow){
		this.sfparallelflow = sfparallelflow;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryAssistFlow
	 * @param sfqueryassistflow String
	 */
	public void setSfqueryassistflow(String sfqueryassistflow){
		this.sfqueryassistflow = sfqueryassistflow;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryDistribute
	 * @param sfquerydistribute String
	 */
	public void setSfquerydistribute(String sfquerydistribute){
		this.sfquerydistribute = sfquerydistribute;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQueryGoBack
	 * @param sfquerygoback String
	 */
	public void setSfquerygoback(String sfquerygoback){
		this.sfquerygoback = sfquerygoback;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFQuerySave
	 * @param sfquerysave String
	 */
	public void setSfquerysave(String sfquerysave){
		this.sfquerysave = sfquerysave;
	}

	/**
	 * ���ܣ����ýӿڳ�����Ϣ���� SFPostSave
	 * @param sfpostsave String
	 */
	public void setSfpostsave(String sfpostsave){
		this.sfpostsave = sfpostsave;
	}


	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� �ӿڳ��� ID  
	 * @return String
	 */
	public int getApiId() {
		return this.apiId;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� ��������
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� ��������
	 * @return String
	 */
	public String getProcedureName() {
		return this.procedureName;
	}

    public String getEnabled() {
        return this.enabled;
    }

    /**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� ��������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

    public int getTaskTid() {
        return this.taskTid;
    }

    /**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� API ����, bit 0 - SFQueryOpen, bit 1 - SFPostOpen, bit 2 - SFQuerySign, bit 3 - SFPostSign, bit 4 - SFQueryComplete, bit 5 - SFGroupReview, bit 6 - SFQueryCycleReview, bit 7 - SFQueryConditionalFlow, bit 8 - SFQueryGroup, bit 9 - SFParallelFlow, bit 10 - SFQueryAssistFlow, bit 11 - SFQueryDistribute, bit 12 - SFQueryGoBack, bit 13 - SFQuerySave, bit 14 - SFPostSave
	 * @return String
	 */
	public int getApiControl() {
		return this.apiControl;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryOpen
	 * @return String
	 */
	public String getSfqueryopen() {
		return this.sfqueryopen;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFPostOpen
	 * @return String
	 */
	public String getSfpostopen() {
		return this.sfpostopen;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQuerySign
	 * @return String
	 */
	public String getSfquerysign() {
		return this.sfquerysign;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFPostSign
	 * @return String
	 */
	public String getSfpostsign() {
		return this.sfpostsign;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryComplete
	 * @return String
	 */
	public String getSfquerycomplete() {
		return this.sfquerycomplete;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFGroupReview
	 * @return String
	 */
	public String getSfgroupreview() {
		return this.sfgroupreview;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryCycleReview
	 * @return String
	 */
	public String getSfquerycyclereview() {
		return this.sfquerycyclereview;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryConditionalFlow
	 * @return String
	 */
	public String getSfqueryconditionalflow() {
		return this.sfqueryconditionalflow;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryGroup
	 * @return String
	 */
	public String getSfquerygroup() {
		return this.sfquerygroup;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFParallelFlow
	 * @return String
	 */
	public String getSfparallelflow() {
		return this.sfparallelflow;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryAssistFlow
	 * @return String
	 */
	public String getSfqueryassistflow() {
		return this.sfqueryassistflow;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryDistribute
	 * @return String
	 */
	public String getSfquerydistribute() {
		return this.sfquerydistribute;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQueryGoBack
	 * @return String
	 */
	public String getSfquerygoback() {
		return this.sfquerygoback;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFQuerySave
	 * @return String
	 */
	public String getSfquerysave() {
		return this.sfquerysave;
	}

	/**
	 * ���ܣ���ȡ�ӿڳ�����Ϣ���� SFPostSave
	 * @return String
	 */
	public String getSfpostsave() {
		return this.sfpostsave;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(int procedureId) {
		this.procedureId = procedureId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

}