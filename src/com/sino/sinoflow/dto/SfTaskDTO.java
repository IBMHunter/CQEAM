package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �������� SfTask</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfTaskDTO extends CheckBoxDTO{

	private int procedureId = 0;
	private int taskId = 0;
	private String taskName = "";
	private String taskDesc = "";
	private String groupName = "";
	private String roleName = "";
	private int flowType = 0;
	private String api = "";
	private int duration = 0;
	private int durationUnit = 0;
    private String url = "";
    private String divRight = "";
	private String divHidden = "";
	private int controlType = 0;
	private int cycleType = 0;
    private String cycleUrl = "";
    private String reviewGroup = "";
	private String reviewRole = "";
	private String reviewDesc = "";
    private String reviewUrl = "";
    private String reviewDivRight = "";
	private String reviewDivHidden = "";
    private int copyFlag = 0;
    private String copyReason = "";
    private String copyHiddenDiv = "";
    private String copyUrl = "";
    private String attribute1 = "";
    private String attribute2 = "";
    private String attribute3 = "";
    private String attribute4 = "";
    private String attribute5 = "";
    private int tid = 0;

    public SfTaskDTO() {
		super();

	}

	/**
	 * ���ܣ����������������� PROCEDURE_ID
	 * @param procedureId String
	 */
	public void setProcedureId(int procedureId){
		this.procedureId = procedureId;
	}

	/**
	 * ���ܣ����������������� ���� ID
	 * @param taskId String
	 */
	public void setTaskId(int taskId){
		this.taskId = taskId;
	}

	/**
	 * ���ܣ����������������� ��������
	 * @param taskName String
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	/**
	 * ���ܣ����������������� ��������
	 * @param taskDesc String
	 */
	public void setTaskDesc(String taskDesc){
		this.taskDesc = taskDesc;
	}

	/**
	 * ���ܣ����������������� ���
	 * @param groupName String
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	/**
	 * ���ܣ����������������� ��ɫ
	 * @param roleName String
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	/**
	 * ���ܣ����������������� ��������, 0:ֱ�� 1:���� 2:���� 3:���� 4:����  ����� JOIN 0:�ȴ� 1:���ȴ�
	 * @param flowType String
	 */
	public void setFlowType(int flowType){
		this.flowType = flowType;
	}

	/**
	 * ���ܣ����������������� �ӿڳ���
	 * @param api String
	 */
	public void setApi(String api){
		this.api = api;
	}

	/**
	 * ���ܣ����������������� Ԥ��ʱ��
	 * @param duration String
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}

	/**
	 * ���ܣ����������������� ���㵥Ԫ, 0:����ʱ 1:ʵ������
	 * @param durationUnit String
	 */
	public void setDurationUnit(int durationUnit){
		this.durationUnit = durationUnit;
	}

    /**
     * ���ܣ����������������� ����
     * @param url String
     */
    public void setUrl(String url){
        this.url = url;
    }


    /**
	 * ���ܣ����������������� ����
	 * @param divRight String
	 */
	public void setDivRight(String divRight){
		this.divRight = divRight;
	}

	/**
	 * ���ܣ����������������� �L��
	 * @param divHidden String
	 */
	public void setDivHidden(String divHidden){
		this.divHidden = divHidden;
	}

	/**
	 * ���ܣ����������������� �������, 0:�� 1:������ 2:��ǩ 3:��ʾ
	 * @param controlType String
	 */
	public void setControlType(int controlType){
		this.controlType = controlType;
	}

	/**
	 * ���ܣ����������������� ��ǩ����, 0:��Ա��ǩ 1:����ǩ
	 * @param cycleType String
	 */
	public void setCycleType(int cycleType){
		this.cycleType = cycleType;
	}

    /**
     * ���ܣ����������������� ��ǩ����, 0:��Ա��ǩ 1:����ǩ
     * @param cycleUrl String
     */
    public void setCycleUrl(String cycleUrl){
        this.cycleUrl = cycleUrl;
    }

	/**
	 * ���ܣ����������������� ��ʾ���
	 * @param reviewGroup String
	 */
	public void setReviewGroup(String reviewGroup){
		this.reviewGroup = reviewGroup;
	}

	/**
	 * ���ܣ����������������� ��ʾ��ɫ
	 * @param reviewRole String
	 */
	public void setReviewRole(String reviewRole){
		this.reviewRole = reviewRole;
	}

	/**
	 * ���ܣ����������������� ��ʾ��ʾ
	 * @param reviewDesc String
	 */
	public void setReviewDesc(String reviewDesc){
		this.reviewDesc = reviewDesc;
	}

    /**
     * ���ܣ����������������� ��ʾ����
     * @param reviewUrl String
     */
    public void setReviewUrl(String reviewUrl){
        this.reviewUrl = reviewUrl;
    }

    /**
	 * ���ܣ����������������� ��ʾ����
	 * @param reviewDivRight String
	 */
	public void setReviewDivRight(String reviewDivRight){
		this.reviewDivRight = reviewDivRight;
	}

	/**
	 * ���ܣ����������������� ��ʾ�L��
	 * @param reviewDivHidden String
	 */
	public void setReviewDivHidden(String reviewDivHidden){
		this.reviewDivHidden = reviewDivHidden;
	}

    public void setCopyFlag(int copyFlag) {
        this.copyFlag = copyFlag;
    }

    public void setCopyReason(String copyReason) {
        this.copyReason = copyReason;
    }

    public void setCopyHiddenDiv(String copyHiddenDiv) {
        this.copyHiddenDiv = copyHiddenDiv;
    }

    public void setCopyUrl(String copyUrl) {
        this.copyUrl = copyUrl;
    }

    /**
     * ���ܣ����������������� ��ʾ�L��
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ����������������� ��ʾ�L��
     * @param attribute2 String
     */
    public void setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }

    /**
     * ���ܣ����������������� ��ʾ�L��
     * @param attribute3 String
     */
    public void setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }

    /**
     * ���ܣ����������������� ��ʾ�L��
     * @param attribute4 String
     */
    public void setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }

    /**
     * ���ܣ����������������� ��ʾ�L��
     * @param attribute5 String
     */
    public void setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }

    /**
     * ���ܣ����������������� ��ʾ�L��
     * @param tid String
     */
    public void setTid(int tid){
        this.tid = tid;
    }

    /**
	 * ���ܣ���ȡ������������ PROCEDURE_ID
	 * @return String
	 */
	public int getProcedureId() {
		return this.procedureId;
	}

	/**
	 * ���ܣ���ȡ������������ ���� ID
	 * @return String
	 */
	public int getTaskId() {
		return this.taskId;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * ���ܣ���ȡ������������ ��������
	 * @return String
	 */
	public String getTaskDesc() {
		return this.taskDesc;
	}

	/**
	 * ���ܣ���ȡ������������ ���
	 * @return String
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * ���ܣ���ȡ������������ ��ɫ
	 * @return String
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * ���ܣ���ȡ������������ ��������, 0:ֱ�� 1:���� 2:���� 3:���� 4:����  ����� JOIN 0:�ȴ� 1:���ȴ�
	 * @return String
	 */
	public int getFlowType() {
		return this.flowType;
	}

	/**
	 * ���ܣ���ȡ������������ �ӿڳ���
	 * @return String
	 */
	public String getApi() {
		return this.api;
	}

	/**
	 * ���ܣ���ȡ������������ Ԥ��ʱ��
	 * @return String
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * ���ܣ���ȡ������������ ���㵥Ԫ, 0:����ʱ 1:ʵ������
	 * @return String
	 */
	public int getDurationUnit() {
		return this.durationUnit;
	}

    /**
     * ���ܣ���ȡ������������ ����
     * @return String
     */
    public String getUrl() {
        return this.url;
    }

    /**
	 * ���ܣ���ȡ������������ ����
	 * @return String
	 */
	public String getDivRight() {
		return this.divRight;
	}

	/**
	 * ���ܣ���ȡ������������ �L��
	 * @return String
	 */
	public String getDivHidden() {
		return this.divHidden;
	}

	/**
	 * ���ܣ���ȡ������������ �������, 0:�� 1:������ 2:��ǩ 3:��ʾ
	 * @return String
	 */
	public int getControlType() {
		return this.controlType;
	}

	/**
	 * ���ܣ���ȡ������������ ��ǩ����, 0:��Ա��ǩ 1:����ǩ
	 * @return String
	 */
	public int getCycleType() {
		return this.cycleType;
	}

    /**
     * ���ܣ���ȡ������������ ��ǩ����, 0:��Ա��ǩ 1:����ǩ
     * @return String
     */
    public String getCycleUrl() {
        return this.cycleUrl;
    }

    /**
	 * ���ܣ���ȡ������������ ��ʾ���
	 * @return String
	 */
	public String getReviewGroup() {
		return this.reviewGroup;
	}

	/**
	 * ���ܣ���ȡ������������ ��ʾ��ɫ
	 * @return String
	 */
	public String getReviewRole() {
		return this.reviewRole;
	}

	/**
	 * ���ܣ���ȡ������������ ��ʾ��ʾ
	 * @return String
	 */
	public String getReviewDesc() {
		return this.reviewDesc;
	}

    /**
     * ���ܣ���ȡ������������ ��ʾ��ʾ
     * @return String
     */
    public String getReviewUrl() {
        return this.reviewUrl;
    }

    /**
	 * ���ܣ���ȡ������������ ��ʾ����
	 * @return String
	 */
	public String getReviewDivRight() {
		return this.reviewDivRight;
	}

	/**
	 * ���ܣ���ȡ������������ ��ʾ�L��
	 * @return String
	 */
	public String getReviewDivHidden() {
		return this.reviewDivHidden;
	}

    public int getCopyFlag() {
        return this.copyFlag;
    }

    public String getCopyReason() {
        return this.copyReason;
    }

    public String getCopyHiddenDiv() {
        return this.copyHiddenDiv;
    }

    public String getCopyUrl() {
        return this.copyUrl;
    }

    /**
     * ���ܣ���ȡ������������ ��ʾ�L��
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡ������������ ��ʾ�L��
     * @return String
     */
    public String getAttribute2() {
        return this.attribute2;
    }

    /**
     * ���ܣ���ȡ������������ ��ʾ�L��
     * @return String
     */
    public String getAttribute3() {
        return this.attribute3;
    }

    /**
     * ���ܣ���ȡ������������ ��ʾ�L��
     * @return String
     */
    public String getAttribute4() {
        return this.attribute4;
    }

    /**
     * ���ܣ���ȡ������������ ��ʾ�L��
     * @return String
     */
    public String getAttribute5() {
        return this.attribute5;
    }

    /**
     * ���ܣ���ȡ������������ ��ʾ�L��
     * @return String
     */
    public int getTid() {
        return this.tid;
    }
}