package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ��ת���̣��ڰ���ת��Ϣ SfActInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfActInfoDTO extends CheckBoxDTO{

	private String sfactActId = "";
	private int sfactAppdefId = 0;
	private String sfactUrl = "";
	private String sfactApplId = "";
	private int sfactDocType = 0;
	private String sfactCaseId = "";
	private int sfactPrjFileId = 0;
    private String sfactProjName = "";
    private int sfactProcId = 0;
	private String sfactProcName = "";
	private String sfactProcDesc = "";
	private int sfactTaskId = 0;
	private String sfactTaskName = "";
	private String sfactTaskDesc = "";
	private String sfactTaskGroup = "";
	private String sfactTaskRole = "";
	private int sfactTaskType = 0;
	private float sfactTaskDuration = 0;
	private int sfactTaskWorkType = 0;
	private String sfactTaskApiName = "";
	private String sfactTaskUrl = "";
	private String sfactTaskAttribute1 = "";
	private String sfactTaskAttribute2 = "";
	private String sfactTaskAttribute3 = "";
	private String sfactTaskAttribute4 = "";
	private String sfactTaskAttribute5 = "";
	private String sfactTaskDivRight = "";
	private String sfactTaskHidden = "";
	private int sfactTaskCtl = 0;
	private int sfactTaskCycleType = 0;
	private String sfactTaskCycleUrl = "";
	private String sfactTaskCommentGroup = "";
	private String sfactTaskCommentRole = "";
	private String sfactTaskCommentInfo = "";
	private String sfactTaskCommentUrl = "";
	private String sfactHandlerGroup = "";
	private String sfactTaskCommentDiv = "";
	private String sfactTaskCommentHide = "";
	private int sfactActStatus = 0;
	private String sfactApplMsg = "";
	private String sfactCommentApplMsg = "";
	private int sfactCommentQty = 0;
	private String sfactCommentUsers = "";
	private int sfactCommentType = 0;
	private String sfactCompleteDate = "";
	private double sfactCompleteRealDuration = 0;
	private int sfactCompleteStatus = 0;
	private String sfactCompleteUser = "";
	private double sfactCompleteWorkDuration = 0;
	private String sfactComposeUser = "";
	private String sfactCreateDt = "";
	private int sfactCycleType = 0;
	private int sfactCycleQty = 0;
	private String sfactCycleUsers = "";
	private int sfactDeliveryPriority = 0;
    private String sfactTaskUsers = "";
    private String sfactUserMsg = "";
	private String sfactFromActId = "";
	private String sfactFromDate = "";
	private String sfactFromProcName = "";
	private String sfactFromProjName = "";
	private int sfactFromTaskId = 0;
	private String sfactFromTaskUser = "";
	private String sfactHandler = "";
	private double sfactLagReal = 0;
	private double sfactLagWork = 0;
	private int sfactLeadDayMode = 0;
	private String sfactNextProcName = "";
	private int sfactNextTaskId = 0;
	private String sfactNextTaskName = "";
	private String sfactPickDate = "";
	private int sfactPickStatus = 0;
	private String sfactPickUser = "";
	private String sfactSignDate = "";
	private String sfactSignDueDate = "";
	private int sfactSignStatus = 0;
	private String sfactSignUser = "";
	private String sfactSortColumn1 = "";
	private String sfactSortColumn2 = "";
	private String sfactSortColumn3 = "";
	private String sfactApplColumn1 = "";
	private String sfactApplColumn2 = "";
	private String sfactApplColumn3 = "";
	private String sfactApplColumn4 = "";
	private String sfactApplColumn5 = "";
	private String sfactApplColumn6 = "";
	private String sfactApplColumn7 = "";
	private String sfactApplColumn8 = "";
	private String sfactApplColumn9 = "";
	private String sfactSuspendDesc = "";
	private int sfactSuspendFlag = 0;
	private String sfactSplitTaskId = "";
	private int sfactPauseId = 0;
	private String sfactCaseLockDate = "";
	private int sfactCaseLockStatus = 0;
	private String sfactCaseLockUser = "";
	private int sfactAssistFlowNum = 0;
	private String sfactAssistFlowAct = "";
	private String sfactParallelFlowAct = "";
	private String sfactFromCaseId = "";
	private String sfactReturnTime = "";
	private String sfactReturnActId = "";
    private String sfactPlusGroup = "";
    private int sfactTaskTid = 0;

    private int copyFlag = 0;

    public SfActInfoDTO() {
		super();
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ACT_ID��ÿ����ת��ɵ���һ�ڵ㶼������ACTID��
	 * @param sfactActId String
	 */
	public void setSfactActId(String sfactActId){
		this.sfactActId = sfactActId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� Ӧ�ö���ID
	 * @param sfactAppdefId String
	 */
	public void setSfactAppdefId(int sfactAppdefId){
		this.sfactAppdefId = sfactAppdefId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��ǰҳ��ʹ�õ�URL
	 * @param sfactUrl String
	 */
	public void setSfactUrl(String sfactUrl){
		this.sfactUrl = sfactUrl;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� Ӧ�� APP_ID
	 * @param sfactApplId String
	 */
	public void setSfactApplId(String sfactApplId){
		this.sfactApplId = sfactApplId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ACT�ĵ����� 0:��׼, 1:��ʾ, 2:��ǩ, 3:����
	 * @param sfactDocType String
	 */
	public void setSfactDocType(int sfactDocType){
		this.sfactDocType = sfactDocType;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� CASEID��������ȫ���ڲ��仯��Ϊ��ʱ������CASEID��ͬʱд��case��Ϣ��
	 * @param sfactCaseId String
	 */
	public void setSfactCaseId(String sfactCaseId){
		this.sfactCaseId = sfactCaseId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PRJ_FILE_ID
	 * @param sfactPrjFileId String
	 */
	public void setSfactPrjFileId(int sfactPrjFileId){
		this.sfactPrjFileId = sfactPrjFileId;
	}

    /**
     * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PRJ_FILE_ID
     * @param sfactProjName String
     */
    public void setSfactProjName(String sfactProjName){
        this.sfactProjName = sfactProjName;
    }

    /**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PROC_ID
	 * @param sfactProcId String
	 */
	public void setSfactProcId(int sfactProcId){
		this.sfactProcId = sfactProcId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PROC_NAME
	 * @param sfactProcName String
	 */
	public void setSfactProcName(String sfactProcName){
		this.sfactProcName = sfactProcName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PROC_DESC
	 * @param sfactProcDesc String
	 */
	public void setSfactProcDesc(String sfactProcDesc){
		this.sfactProcDesc = sfactProcDesc;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ID
	 * @param sfactTaskId String
	 */
	public void setSfactTaskId(int sfactTaskId){
		this.sfactTaskId = sfactTaskId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_NAME
	 * @param sfactTaskName String
	 */
	public void setSfactTaskName(String sfactTaskName){
		this.sfactTaskName = sfactTaskName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_DESC
	 * @param sfactTaskDesc String
	 */
	public void setSfactTaskDesc(String sfactTaskDesc){
		this.sfactTaskDesc = sfactTaskDesc;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_GROUP
	 * @param sfactTaskGroup String
	 */
	public void setSfactTaskGroup(String sfactTaskGroup){
		this.sfactTaskGroup = sfactTaskGroup;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ROLE
	 * @param sfactTaskRole String
	 */
	public void setSfactTaskRole(String sfactTaskRole){
		this.sfactTaskRole = sfactTaskRole;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_TYPE
	 * @param sfactTaskType String
	 */
	public void setSfactTaskType(int sfactTaskType){
		this.sfactTaskType = sfactTaskType;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_DURATION
	 * @param sfactTaskDuration String
	 */
	public void setSfactTaskDuration(float sfactTaskDuration){
		this.sfactTaskDuration = sfactTaskDuration;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_WORK_TYPE
	 * @param sfactTaskWorkType String
	 */
	public void setSfactTaskWorkType(int sfactTaskWorkType){
		this.sfactTaskWorkType = sfactTaskWorkType;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_API_NAME
	 * @param sfactTaskApiName String
	 */
	public void setSfactTaskApiName(String sfactTaskApiName){
		this.sfactTaskApiName = sfactTaskApiName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_URL
	 * @param sfactTaskUrl String
	 */
	public void setSfactTaskUrl(String sfactTaskUrl){
		this.sfactTaskUrl = sfactTaskUrl;
	}

    /**
     * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_1
     * @param sfactTaskTid String
     */
    public void setSfactTaskTid(int sfactTaskTid){
        this.sfactTaskTid = sfactTaskTid;
    }

    /**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_1
	 * @param sfactTaskAttribute1 String
	 */
	public void setSfactTaskAttribute1(String sfactTaskAttribute1){
		this.sfactTaskAttribute1 = sfactTaskAttribute1;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_2
	 * @param sfactTaskAttribute2 String
	 */
	public void setSfactTaskAttribute2(String sfactTaskAttribute2){
		this.sfactTaskAttribute2 = sfactTaskAttribute2;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_3
	 * @param sfactTaskAttribute3 String
	 */
	public void setSfactTaskAttribute3(String sfactTaskAttribute3){
		this.sfactTaskAttribute3 = sfactTaskAttribute3;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_4
	 * @param sfactTaskAttribute4 String
	 */
	public void setSfactTaskAttribute4(String sfactTaskAttribute4){
		this.sfactTaskAttribute4 = sfactTaskAttribute4;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_5
	 * @param sfactTaskAttribute5 String
	 */
	public void setSfactTaskAttribute5(String sfactTaskAttribute5){
		this.sfactTaskAttribute5 = sfactTaskAttribute5;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_READ_ONLY
	 * @param sfactTaskDivRight String
	 */
	public void setSfactTaskDivRigth(String sfactTaskDivRight){
		this.sfactTaskDivRight = sfactTaskDivRight;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_HIDDEN
	 * @param sfactTaskHidden String
	 */
	public void setSfactTaskHidden(String sfactTaskHidden){
		this.sfactTaskHidden = sfactTaskHidden;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_CTL
	 * @param sfactTaskCtl String
	 */
	public void setSfactTaskCtl(int sfactTaskCtl){
		this.sfactTaskCtl = sfactTaskCtl;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_CYCLE_TYPE
	 * @param sfactTaskCycleType String
	 */
	public void setSfactTaskCycleType(int sfactTaskCycleType){
		this.sfactTaskCycleType = sfactTaskCycleType;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_CYCLE_URL
	 * @param sfactTaskCycleUrl String
	 */
	public void setSfactTaskCycleUrl(String sfactTaskCycleUrl){
		this.sfactTaskCycleUrl = sfactTaskCycleUrl;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_GROUP
	 * @param sfactTaskCommentGroup String
	 */
	public void setSfactTaskCommentGroup(String sfactTaskCommentGroup){
		this.sfactTaskCommentGroup = sfactTaskCommentGroup;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_ROLE
	 * @param sfactTaskCommentRole String
	 */
	public void setSfactTaskCommentRole(String sfactTaskCommentRole){
		this.sfactTaskCommentRole = sfactTaskCommentRole;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_INFO
	 * @param sfactTaskCommentInfo String
	 */
	public void setSfactTaskCommentInfo(String sfactTaskCommentInfo){
		this.sfactTaskCommentInfo = sfactTaskCommentInfo;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_URL
	 * @param sfactTaskCommentUrl String
	 */
	public void setSfactTaskCommentUrl(String sfactTaskCommentUrl){
		this.sfactTaskCommentUrl = sfactTaskCommentUrl;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_HANDLER_PROP
	 * @param sfactHandlerGroup String
	 */
	public void setSfactHandlerGroup(String sfactHandlerGroup){
		this.sfactHandlerGroup = sfactHandlerGroup;
	}

    /**
     * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_HANDLER_PROP
     * @param sfactPlusGroup String
     */
    public void setSfactPlusGroup(String sfactPlusGroup){
        this.sfactPlusGroup = sfactPlusGroup;
    }

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_READ
	 * @param sfactTaskCommentDiv String
	 */
	public void setSfactTaskCommentDiv(String sfactTaskCommentDiv){
		this.sfactTaskCommentDiv = sfactTaskCommentDiv;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_HIDE
	 * @param sfactTaskCommentHide String
	 */
	public void setSfactTaskCommentHide(String sfactTaskCommentHide){
		this.sfactTaskCommentHide = sfactTaskCommentHide;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_ACT_STATUS
	 * @param sfactActStatus String
	 */
	public void setSfactActStatus(int sfactActStatus){
		this.sfactActStatus = sfactActStatus;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_APPL_MSG
	 * @param sfactApplMsg String
	 */
	public void setSfactApplMsg(String sfactApplMsg){
		this.sfactApplMsg = sfactApplMsg;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_APPL_MSG
	 * @param sfactCommentApplMsg String
	 */
	public void setSfactCommentApplMsg(String sfactCommentApplMsg){
		this.sfactCommentApplMsg = sfactCommentApplMsg;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_QTY
	 * @param sfactCommentQty String
	 */
	public void setSfactCommentQty(int sfactCommentQty){
		this.sfactCommentQty = sfactCommentQty;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_USERS
	 * @param sfactCommentUsers String
	 */
	public void setSfactCommentUsers(String sfactCommentUsers){
		this.sfactCommentUsers = sfactCommentUsers;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_TYPE
	 * @param sfactCommentType String
	 */
	public void setSfactCommentType(int sfactCommentType){
		this.sfactCommentType = sfactCommentType;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_DATE
	 * @param sfactCompleteDate String
	 */
	public void setSfactCompleteDate(String sfactCompleteDate) {
		this.sfactCompleteDate = sfactCompleteDate;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_REAL_DURATION
	 * @param sfactCompleteRealDuration String
	 */
	public void setSfactCompleteRealDuration(double sfactCompleteRealDuration){
		this.sfactCompleteRealDuration = sfactCompleteRealDuration;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_STATUS
	 * @param sfactCompleteStatus String
	 */
	public void setSfactCompleteStatus(int sfactCompleteStatus){
		this.sfactCompleteStatus = sfactCompleteStatus;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_USER
	 * @param sfactCompleteUser String
	 */
	public void setSfactCompleteUser(String sfactCompleteUser){
		this.sfactCompleteUser = sfactCompleteUser;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_WORK_DURATION
	 * @param sfactCompleteWorkDuration String
	 */
	public void setSfactCompleteWorkDuration(double sfactCompleteWorkDuration){
		this.sfactCompleteWorkDuration = sfactCompleteWorkDuration;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_COMPOSE_USER
	 * @param sfactComposeUser String
	 */
	public void setSfactComposeUser(String sfactComposeUser){
		this.sfactComposeUser = sfactComposeUser;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_CREATE_DT
	 * @param sfactCreateDt String
	 */
	public void setSfactCreateDt(String sfactCreateDt) {
		this.sfactCreateDt = sfactCreateDt;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� 0:δָ��  1:˳����
	 * @param sfactCycleType String
	 */
	public void setSfactCycleType(int sfactCycleType){
		this.sfactCycleType = sfactCycleType;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_CYCLE_QTY
	 * @param sfactCycleQty String
	 */
	public void setSfactCycleQty(int sfactCycleQty){
		this.sfactCycleQty = sfactCycleQty;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_CYCLE_USERS
	 * @param sfactCycleUsers String
	 */
	public void setSfactCycleUsers(String sfactCycleUsers){
		this.sfactCycleUsers = sfactCycleUsers;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� 0:����  1:����  2:�ؼ�
	 * @param sfactDeliveryPriority String
	 */
	public void setSfactDeliveryPriority(int sfactDeliveryPriority){
		this.sfactDeliveryPriority = sfactDeliveryPriority;
	}

    /**
     * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� 0:����  1:����  2:�ؼ�
     * @param sfactTaskUsers String
     */
    public void setSfactTaskUsers(String sfactTaskUsers){
        this.sfactTaskUsers = sfactTaskUsers;
    }

    /**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_USER_MSG
	 * @param sfactUserMsg String
	 */
	public void setSfactUserMsg(String sfactUserMsg){
		this.sfactUserMsg = sfactUserMsg;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_ACT_ID
	 * @param sfactFromActId String
	 */
	public void setSfactFromActId(String sfactFromActId){
		this.sfactFromActId = sfactFromActId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_DATE
	 * @param sfactFromDate String
	 */
	public void setSfactFromDate(String sfactFromDate) {
		this.sfactFromDate = sfactFromDate;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_PROC_NAME
	 * @param sfactFromProcName String
	 */
	public void setSfactFromProcName(String sfactFromProcName){
		this.sfactFromProcName = sfactFromProcName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_PROJ_NAME
	 * @param sfactFromProjName String
	 */
	public void setSfactFromProjName(String sfactFromProjName){
		this.sfactFromProjName = sfactFromProjName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_TASK_ID
	 * @param sfactFromTaskId String
	 */
	public void setSfactFromTaskId(int sfactFromTaskId){
		this.sfactFromTaskId = sfactFromTaskId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ת���û�ID
	 * @param sfactFromTaskUser String
	 */
	public void setSfactFromTaskUser(String sfactFromTaskUser){
		this.sfactFromTaskUser = sfactFromTaskUser;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��������Ϣ
	 * @param sfactHandler String
	 */
	public void setSfactHandler(String sfactHandler){
		this.sfactHandler = sfactHandler;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���ĵ���������ǩ��֮��Ĺ���ʱ���ֵ
	 * @param sfactLagReal String
	 */
	public void setSfactLagReal(double sfactLagReal){
		this.sfactLagReal = sfactLagReal;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���ĵ����͹�����ǩ��֮�����ʵʱ���ֵ
	 * @param sfactLagWork String
	 */
	public void setSfactLagWork(double sfactLagWork){
		this.sfactLagWork = sfactLagWork;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� �Թ�ʱ��������Ϊ��ʱ����
	 * @param sfactLeadDayMode String
	 */
	public void setSfactLeadDayMode(int sfactLeadDayMode){
		this.sfactLeadDayMode = sfactLeadDayMode;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��һ�ڵ�������ƣ�LOGר�ã�
	 * @param sfactNextProcName String
	 */
	public void setSfactNextProcName(String sfactNextProcName){
		this.sfactNextProcName = sfactNextProcName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��һ�ڵ�����LOGר�ã�
	 * @param sfactNextTaskId String
	 */
	public void setSfactNextTaskId(int sfactNextTaskId){
		this.sfactNextTaskId = sfactNextTaskId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��һ�ڵ�������LOGר�ã�
	 * @param sfactNextTaskName String
	 */
	public void setSfactNextTaskName(String sfactNextTaskName){
		this.sfactNextTaskName = sfactNextTaskName;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PICK_DATE
	 * @param sfactPickDate String
	 */
	public void setSfactPickDate(String sfactPickDate) {
		this.sfactPickDate = sfactPickDate;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PICK_STATUS
	 * @param sfactPickStatus String
	 */
	public void setSfactPickStatus(int sfactPickStatus){
		this.sfactPickStatus = sfactPickStatus;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_PICK_USER
	 * @param sfactPickUser String
	 */
	public void setSfactPickUser(String sfactPickUser){
		this.sfactPickUser = sfactPickUser;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ǩ������
	 * @param sfactSignDate String
	 */
	public void setSfactSignDate(String sfactSignDate) {
		this.sfactSignDate = sfactSignDate;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� Ӧת��ʱ��
	 * @param sfactSignDueDate String
	 */
	public void setSfactSignDueDate(String sfactSignDueDate){
		this.sfactSignDueDate = sfactSignDueDate;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_SIGN_STATUS
	 * @param sfactSignStatus String
	 */
	public void setSfactSignStatus(int sfactSignStatus){
		this.sfactSignStatus = sfactSignStatus;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_SIGN_USER
	 * @param sfactSignUser String
	 */
	public void setSfactSignUser(String sfactSignUser){
		this.sfactSignUser = sfactSignUser;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ������������
	 * @param sfactSortColumn1 String
	 */
	public void setSfactSortColumn1(String sfactSortColumn1){
		this.sfactSortColumn1 = sfactSortColumn1;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� �������η���
	 * @param sfactSortColumn2 String
	 */
	public void setSfactSortColumn2(String sfactSortColumn2){
		this.sfactSortColumn2 = sfactSortColumn2;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��������������
	 * @param sfactSortColumn3 String
	 */
	public void setSfactSortColumn3(String sfactSortColumn3){
		this.sfactSortColumn3 = sfactSortColumn3;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� �ؼ���
	 * @param sfactApplColumn1 String
	 */
	public void setSfactApplColumn1(String sfactApplColumn1){
		this.sfactApplColumn1 = sfactApplColumn1;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ����
	 * @param sfactApplColumn2 String
	 */
	public void setSfactApplColumn2(String sfactApplColumn2){
		this.sfactApplColumn2 = sfactApplColumn2;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ����
	 * @param sfactApplColumn3 String
	 */
	public void setSfactApplColumn3(String sfactApplColumn3){
		this.sfactApplColumn3 = sfactApplColumn3;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�1
	 * @param sfactApplColumn4 String
	 */
	public void setSfactApplColumn4(String sfactApplColumn4){
		this.sfactApplColumn4 = sfactApplColumn4;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�2
	 * @param sfactApplColumn5 String
	 */
	public void setSfactApplColumn5(String sfactApplColumn5){
		this.sfactApplColumn5 = sfactApplColumn5;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�3
	 * @param sfactApplColumn6 String
	 */
	public void setSfactApplColumn6(String sfactApplColumn6){
		this.sfactApplColumn6 = sfactApplColumn6;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�4
	 * @param sfactApplColumn7 String
	 */
	public void setSfactApplColumn7(String sfactApplColumn7){
		this.sfactApplColumn7 = sfactApplColumn7;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�5
	 * @param sfactApplColumn8 String
	 */
	public void setSfactApplColumn8(String sfactApplColumn8){
		this.sfactApplColumn8 = sfactApplColumn8;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�6
	 * @param sfactApplColumn9 String
	 */
	public void setSfactApplColumn9(String sfactApplColumn9){
		this.sfactApplColumn9 = sfactApplColumn9;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_SUSPEND_DESC
	 * @param sfactSuspendDesc String
	 */
	public void setSfactSuspendDesc(String sfactSuspendDesc){
		this.sfactSuspendDesc = sfactSuspendDesc;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_SUSPEND_FLAG
	 * @param sfactSuspendFlag String
	 */
	public void setSfactSuspendFlag(int sfactSuspendFlag){
		this.sfactSuspendFlag = sfactSuspendFlag;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� SFACT_SPLIT_TASK_ID
	 * @param sfactSplitTaskId String
	 */
	public void setSfactSplitTaskId(String sfactSplitTaskId){
		this.sfactSplitTaskId = sfactSplitTaskId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��ͣID
	 * @param sfactPauseId String
	 */
	public void setSfactPauseId(int sfactPauseId){
		this.sfactPauseId = sfactPauseId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��������
	 * @param sfactCaseLockDate String
	 */
	public void setSfactCaseLockDate(String sfactCaseLockDate){
		this.sfactCaseLockDate = sfactCaseLockDate;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ����״̬
	 * @param sfactCaseLockStatus String
	 */
	public void setSfactCaseLockStatus(int sfactCaseLockStatus){
		this.sfactCaseLockStatus = sfactCaseLockStatus;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ������
	 * @param sfactCaseLockUser String
	 */
	public void setSfactCaseLockUser(String sfactCaseLockUser){
		this.sfactCaseLockUser = sfactCaseLockUser;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��������
	 * @param sfactAssistFlowNum String
	 */
	public void setSfactAssistFlowNum(int sfactAssistFlowNum){
		this.sfactAssistFlowNum = sfactAssistFlowNum;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ����ACTID�ַ��������ָ���
	 * @param sfactAssistFlowAct String
	 */
	public void setSfactAssistFlowAct(String sfactAssistFlowAct){
		this.sfactAssistFlowAct = sfactAssistFlowAct;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ����ACTID�ַ��������ָ���
	 * @param sfactParallelFlowAct String
	 */
	public void setSfactParallelFlowAct(String sfactParallelFlowAct){
		this.sfactParallelFlowAct = sfactParallelFlowAct;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� ��һCASEID(����������ר��)
	 * @param sfactFromCaseId String
	 */
	public void setSfactFromCaseId(String sfactFromCaseId){
		this.sfactFromCaseId = sfactFromCaseId;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� �˻ؿ�ʼʱ���
	 * @param sfactReturnTime String
	 */
	public void setSfactReturnTime(String sfactReturnTime) {
		this.sfactReturnTime = sfactReturnTime;
	}

	/**
	 * ���ܣ�������ת���̣��ڰ���ת��Ϣ���� �˻ؿ�ʼACTID
	 * @param sfactReturnActId String
	 */
	public void setSfactReturnActId(String sfactReturnActId){
		this.sfactReturnActId = sfactReturnActId;
	}


	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ACT_ID��ÿ����ת��ɵ���һ�ڵ㶼������ACTID��
	 * @return String
	 */
	public String getSfactActId() {
		return this.sfactActId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� Ӧ�ö���ID
	 * @return String
	 */
	public int getSfactAppdefId() {
		return this.sfactAppdefId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��ǰҳ��ʹ�õ�URL
	 * @return String
	 */
	public String getSfactUrl() {
		return this.sfactUrl;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� Ӧ�� APP_ID
	 * @return String
	 */
	public String getSfactApplId() {
		return this.sfactApplId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ACT�ĵ����� 0:��׼, 1:��ʾ, 2:��ǩ, 3:����
	 * @return String
	 */
	public int getSfactDocType() {
		return this.sfactDocType;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� CASEID��������ȫ���ڲ��仯��Ϊ��ʱ������CASEID��ͬʱд��case��Ϣ��
	 * @return String
	 */
	public String getSfactCaseId() {
		return this.sfactCaseId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PRJ_FILE_ID
	 * @return String
	 */
	public int getSfactPrjFileId() {
		return this.sfactPrjFileId;
	}

    /**
     * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PRJ_FILE_ID
     * @return String
     */
    public String getSfactProjName() {
        return this.sfactProjName;
    }

    /**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PROC_ID
	 * @return String
	 */
	public int getSfactProcId() {
		return this.sfactProcId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PROC_NAME
	 * @return String
	 */
	public String getSfactProcName() {
		return this.sfactProcName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PROC_DESC
	 * @return String
	 */
	public String getSfactProcDesc() {
		return this.sfactProcDesc;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ID
	 * @return String
	 */
	public int getSfactTaskId() {
		return this.sfactTaskId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_NAME
	 * @return String
	 */
	public String getSfactTaskName() {
		return this.sfactTaskName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_DESC
	 * @return String
	 */
	public String getSfactTaskDesc() {
		return this.sfactTaskDesc;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_GROUP
	 * @return String
	 */
	public String getSfactTaskGroup() {
		return this.sfactTaskGroup;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ROLE
	 * @return String
	 */
	public String getSfactTaskRole() {
		return this.sfactTaskRole;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_TYPE
	 * @return String
	 */
	public int getSfactTaskType() {
		return this.sfactTaskType;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_DURATION
	 * @return String
	 */
	public float getSfactTaskDuration() {
		return this.sfactTaskDuration;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_WORK_TYPE
	 * @return String
	 */
	public int getSfactTaskWorkType() {
		return this.sfactTaskWorkType;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_API_NAME
	 * @return String
	 */
	public String getSfactTaskApiName() {
		return this.sfactTaskApiName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_URL
	 * @return String
	 */
	public String getSfactTaskUrl() {
		return this.sfactTaskUrl;
	}

    /**
     * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_1
     * @return String
     */
    public int getSfactTaskTid() {
        return this.sfactTaskTid;
    }

    /**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_1
	 * @return String
	 */
	public String getSfactTaskAttribute1() {
		return this.sfactTaskAttribute1;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_2
	 * @return String
	 */
	public String getSfactTaskAttribute2() {
		return this.sfactTaskAttribute2;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_3
	 * @return String
	 */
	public String getSfactTaskAttribute3() {
		return this.sfactTaskAttribute3;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_4
	 * @return String
	 */
	public String getSfactTaskAttribute4() {
		return this.sfactTaskAttribute4;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_ATTRIBUTE_5
	 * @return String
	 */
	public String getSfactTaskAttribute5() {
		return this.sfactTaskAttribute5;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_READ_ONLY
	 * @return String
	 */
	public String getSfactTaskDivRight() {
		return this.sfactTaskDivRight;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_HIDDEN
	 * @return String
	 */
	public String getSfactTaskHidden() {
		return this.sfactTaskHidden;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_CTL
	 * @return String
	 */
	public int getSfactTaskCtl() {
		return this.sfactTaskCtl;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_CYCLE_TYPE
	 * @return String
	 */
	public int getSfactTaskCycleType() {
		return this.sfactTaskCycleType;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_CYCLE_URL
	 * @return String
	 */
	public String getSfactTaskCycleUrl() {
		return this.sfactTaskCycleUrl;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_GROUP
	 * @return String
	 */
	public String getSfactTaskCommentGroup() {
		return this.sfactTaskCommentGroup;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_ROLE
	 * @return String
	 */
	public String getSfactTaskCommentRole() {
		return this.sfactTaskCommentRole;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_INFO
	 * @return String
	 */
	public String getSfactTaskCommentInfo() {
		return this.sfactTaskCommentInfo;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_URL
	 * @return String
	 */
	public String getSfactTaskCommentUrl() {
		return this.sfactTaskCommentUrl;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_HANDLER_PROP
	 * @return String
	 */
	public String getSfactHandlerGroup() {
		return this.sfactHandlerGroup;
	}

    /**
     * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_HANDLER_PROP
     * @return String
     */
    public String getSfactPlusGroup() {
        return this.sfactPlusGroup;
    }

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_READ
	 * @return String
	 */
	public String getSfactTaskCommentDiv() {
		return this.sfactTaskCommentDiv;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_TASK_COMMENT_HIDE
	 * @return String
	 */
	public String getSfactTaskCommentHide() {
		return this.sfactTaskCommentHide;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_ACT_STATUS
	 * @return String
	 */
	public int getSfactActStatus() {
		return this.sfactActStatus;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_APPL_MSG
	 * @return String
	 */
	public String getSfactApplMsg() {
		return this.sfactApplMsg;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_APPL_MSG
	 * @return String
	 */
	public String getSfactCommentApplMsg() {
		return this.sfactCommentApplMsg;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_QTY
	 * @return String
	 */
	public int getSfactCommentQty() {
		return this.sfactCommentQty;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_USERS
	 * @return String
	 */
	public String getSfactCommentUsers() {
		return this.sfactCommentUsers;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMMENT_TYPE
	 * @return String
	 */
	public int getSfactCommentType() {
		return this.sfactCommentType;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_DATE
	 * @return String
	 */
	public String getSfactCompleteDate() {
		return this.sfactCompleteDate;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_REAL_DURATION
	 * @return String
	 */
	public double getSfactCompleteRealDuration() {
		return this.sfactCompleteRealDuration;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_STATUS
	 * @return String
	 */
	public int getSfactCompleteStatus() {
		return this.sfactCompleteStatus;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_USER
	 * @return String
	 */
	public String getSfactCompleteUser() {
		return this.sfactCompleteUser;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMPLETE_WORK_DURATION
	 * @return String
	 */
	public double getSfactCompleteWorkDuration() {
		return this.sfactCompleteWorkDuration;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_COMPOSE_USER
	 * @return String
	 */
	public String getSfactComposeUser() {
		return this.sfactComposeUser;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_CREATE_DT
	 * @return String
	 */
	public String getSfactCreateDt() {
		return this.sfactCreateDt;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� 0:δָ��  1:˳����
	 * @return String
	 */
	public int getSfactCycleType() {
		return this.sfactCycleType;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_CYCLE_QTY
	 * @return String
	 */
	public int getSfactCycleQty() {
		return this.sfactCycleQty;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_CYCLE_USERS
	 * @return String
	 */
	public String getSfactCycleUsers() {
		return this.sfactCycleUsers;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� 0:����  1:����  2:�ؼ�
	 * @return String
	 */
	public int getSfactDeliveryPriority() {
		return this.sfactDeliveryPriority;
	}

    /**
     * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� 0:����  1:����  2:�ؼ�
     * @return String
     */
    public String getSfactTaskUsers() {
        return this.sfactTaskUsers;
    }

    /**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_USER_MSG
	 * @return String
	 */
	public String getSfactUserMsg() {
		return this.sfactUserMsg;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_ACT_ID
	 * @return String
	 */
	public String getSfactFromActId() {
		return this.sfactFromActId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_DATE
	 * @return String
	 */
	public String getSfactFromDate()  {
		return this.sfactFromDate;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_PROC_NAME
	 * @return String
	 */
	public String getSfactFromProcName() {
		return this.sfactFromProcName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_PROJ_NAME
	 * @return String
	 */
	public String getSfactFromProjName() {
		return this.sfactFromProjName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_FROM_TASK_ID
	 * @return String
	 */
	public int getSfactFromTaskId() {
		return this.sfactFromTaskId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ת���û�ID
	 * @return String
	 */
	public String getSfactFromTaskUser() {
		return this.sfactFromTaskUser;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��������Ϣ
	 * @return String
	 */
	public String getSfactHandler() {
		return this.sfactHandler;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���ĵ���������ǩ��֮��Ĺ���ʱ���ֵ
	 * @return String
	 */
	public double getSfactLagReal() {
		return this.sfactLagReal;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���ĵ����͹�����ǩ��֮�����ʵʱ���ֵ
	 * @return String
	 */
	public double getSfactLagWork() {
		return this.sfactLagWork;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� �Թ�ʱ��������Ϊ��ʱ����
	 * @return String
	 */
	public int getSfactLeadDayMode() {
		return this.sfactLeadDayMode;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��һ�ڵ�������ƣ�LOGר�ã�
	 * @return String
	 */
	public String getSfactNextProcName() {
		return this.sfactNextProcName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��һ�ڵ�����LOGר�ã�
	 * @return String
	 */
	public int getSfactNextTaskId() {
		return this.sfactNextTaskId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��һ�ڵ�������LOGר�ã�
	 * @return String
	 */
	public String getSfactNextTaskName() {
		return this.sfactNextTaskName;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PICK_DATE
	 * @return String
	 */
	public String getSfactPickDate() {
		return this.sfactPickDate;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PICK_STATUS
	 * @return String
	 */
	public int getSfactPickStatus() {
		return this.sfactPickStatus;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_PICK_USER
	 * @return String
	 */
	public String getSfactPickUser() {
		return this.sfactPickUser;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ǩ������
	 * @return String
	 */
	public String getSfactSignDate() {
		return this.sfactSignDate;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� Ӧת��ʱ��
	 * @return String
	 */
	public String getSfactSignDueDate() {
		return this.sfactSignDueDate;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_SIGN_STATUS
	 * @return String
	 */
	public int getSfactSignStatus() {
		return this.sfactSignStatus;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_SIGN_USER
	 * @return String
	 */
	public String getSfactSignUser() {
		return this.sfactSignUser;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ������������
	 * @return String
	 */
	public String getSfactSortColumn1() {
		return this.sfactSortColumn1;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� �������η���
	 * @return String
	 */
	public String getSfactSortColumn2() {
		return this.sfactSortColumn2;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��������������
	 * @return String
	 */
	public String getSfactSortColumn3() {
		return this.sfactSortColumn3;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� �ؼ���
	 * @return String
	 */
	public String getSfactApplColumn1() {
		return this.sfactApplColumn1;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ����
	 * @return String
	 */
	public String getSfactApplColumn2() {
		return this.sfactApplColumn2;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ����
	 * @return String
	 */
	public String getSfactApplColumn3() {
		return this.sfactApplColumn3;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�1
	 * @return String
	 */
	public String getSfactApplColumn4() {
		return this.sfactApplColumn4;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�2
	 * @return String
	 */
	public String getSfactApplColumn5() {
		return this.sfactApplColumn5;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�3
	 * @return String
	 */
	public String getSfactApplColumn6() {
		return this.sfactApplColumn6;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�4
	 * @return String
	 */
	public String getSfactApplColumn7() {
		return this.sfactApplColumn7;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�5
	 * @return String
	 */
	public String getSfactApplColumn8() {
		return this.sfactApplColumn8;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ���̣�Ӧ�ý����ֶ�6
	 * @return String
	 */
	public String getSfactApplColumn9() {
		return this.sfactApplColumn9;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_SUSPEND_DESC
	 * @return String
	 */
	public String getSfactSuspendDesc() {
		return this.sfactSuspendDesc;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_SUSPEND_FLAG
	 * @return String
	 */
	public int getSfactSuspendFlag() {
		return this.sfactSuspendFlag;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� SFACT_SPLIT_TASK_ID
	 * @return String
	 */
	public String getSfactSplitTaskId() {
		return this.sfactSplitTaskId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��ͣID
	 * @return String
	 */
	public int getSfactPauseId() {
		return this.sfactPauseId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��������
	 * @return String
	 */
	public String getSfactCaseLockDate() {
		return this.sfactCaseLockDate;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ����״̬
	 * @return String
	 */
	public int getSfactCaseLockStatus() {
		return this.sfactCaseLockStatus;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ������
	 * @return String
	 */
	public String getSfactCaseLockUser() {
		return this.sfactCaseLockUser;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��������
	 * @return String
	 */
	public int getSfactAssistFlowNum() {
		return this.sfactAssistFlowNum;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ����ACTID�ַ��������ָ���
	 * @return String
	 */
	public String getSfactAssistFlowAct() {
		return this.sfactAssistFlowAct;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ����ACTID�ַ��������ָ���
	 * @return String
	 */
	public String getSfactParallelFlowAct() {
		return this.sfactParallelFlowAct;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� ��һCASEID(����������ר��)
	 * @return String
	 */
	public String getSfactFromCaseId() {
		return this.sfactFromCaseId;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� �˻ؿ�ʼʱ���
	 * @return String
	 */
	public String getSfactReturnTime() {
		return this.sfactReturnTime;
	}

	/**
	 * ���ܣ���ȡ��ת���̣��ڰ���ת��Ϣ���� �˻ؿ�ʼACTID
	 * @return String
	 */
	public String getSfactReturnActId() {
		return this.sfactReturnActId;
	}

    public int getCopyFlag() {
        return this.copyFlag;
    }

    public void setCopyFlag(int copyFlag) {
        this.copyFlag = copyFlag;
    }   
}