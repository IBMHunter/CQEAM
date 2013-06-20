package com.sino.ams.yj.ensure.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: Ӧ��ͨ�ű����¼���Ϣ�� AmsYjCommunicateEnsure</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ��ͨ�ű������
 */

public class AmsYjCommunicateEnsureDTO extends CheckBoxDTO {

    private String communicateId = "";
    private String deptName = "";
    private String ensureName = "";
    private String eventType = "";
    private SimpleCalendar ensureDateFrom = null;
    private SimpleCalendar ensureDateTo = null;
    private String manpowerQty = "";        //INT
    private String manpowerTimes = "";      //INT
    private String comvanQty = "";          //INT
    private String comvanTimes = "";        //INT
    private String equipmentQty = "";       //INT
    private String equipmentUnit = "";      //INT
    private String blockDegree = "";
    private String lossCondition = "";
    private String ensureMeasure = "";
    private String recoverSituation = "";
    private String governmentEvaluate = "";
    private String reasonAffect = "";
    private String question = "";
    private String guardMeasure = "";

    private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar creationDate = null;
    private int createUser = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateUser = SyBaseSQLUtil.NULL_INT_VALUE;
    private String orgOpt = "";
    private String ensureLocation = "";

    public AmsYjCommunicateEnsureDTO() {
        super();
        this.ensureDateFrom = new SimpleCalendar();
        this.ensureDateTo = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();

    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ���
     *
     * @param communicateId String
     */
    public void setCommunicateId(String communicateId) {
        this.communicateId = communicateId;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ 	��λ
     *
     * @param deptName String
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ͨ�ű�������
     *
     * @param ensureName String
     */
    public void setEnsureName(String ensureName) {
        this.ensureName = ensureName;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ �¼�����
     *
     * @param eventType String
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ����ʱ����
     * @param ensureDateFrom SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setEnsureDateFrom(String ensureDateFrom) throws CalendarException {
        this.ensureDateFrom.setCalendarValue(ensureDateFrom);
    }
    
    public String getManpowerQty() {
		return manpowerQty;
	}

	public void setManpowerQty(String manpowerQty) {
		this.manpowerQty = manpowerQty;
	}

	public String getManpowerTimes() {
		return manpowerTimes;
	}

	public void setManpowerTimes(String manpowerTimes) {
		this.manpowerTimes = manpowerTimes;
	}

	public String getComvanQty() {
		return comvanQty;
	}

	public void setComvanQty(String comvanQty) {
		this.comvanQty = comvanQty;
	}

	public String getComvanTimes() {
		return comvanTimes;
	}

	public void setComvanTimes(String comvanTimes) {
		this.comvanTimes = comvanTimes;
	}

	public String getEquipmentQty() {
		return equipmentQty;
	}

	public void setEquipmentQty(String equipmentQty) {
		this.equipmentQty = equipmentQty;
	}

	public String getEquipmentUnit() {
		return equipmentUnit;
	}

	public void setEquipmentUnit(String equipmentUnit) {
		this.equipmentUnit = equipmentUnit;
	}

	public String getCommunicateId() {
		return communicateId;
	}

	/**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ����ʱ����
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getEnsureDateFrom() throws CalendarException {
        ensureDateFrom.setCalPattern(getCalPattern());
        return this.ensureDateFrom;
    }  
    
    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ����ʱ��ֹ
     *
     * @param ensureDateTo SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setEnsureDateTo(String ensureDateTo) throws CalendarException {
        this.ensureDateTo.setCalendarValue(ensureDateTo);
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ͨ����ϳ̶�
     *
     * @param blockDegree String
     */
    public void setBlockDegree(String blockDegree) {
        this.blockDegree = blockDegree;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ��ʧ���
     *
     * @param lossCondition String
     */
    public void setLossCondition(String lossCondition) {
        this.lossCondition = lossCondition;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ Ӧ�����ϴ�ʩ
     *
     * @param ensureMeasure String
     */
    public void setEnsureMeasure(String ensureMeasure) {
        this.ensureMeasure = ensureMeasure;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ͨ�Żָ������ʱ��
     *
     * @param recoverSituation String
     */
    public void setRecoverSituation(String recoverSituation) {
        this.recoverSituation = recoverSituation;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ �ط�������������
     *
     * @param governmentEvaluate String
     */
    public void setGovernmentEvaluate(String governmentEvaluate) {
        this.governmentEvaluate = governmentEvaluate;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ �¼�ԭ��Ӱ�췶Χ
     *
     * @param reasonAffect String
     */
    public void setReasonAffect(String reasonAffect) {
        this.reasonAffect = reasonAffect;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ ���ڵ�����
     *
     * @param question String
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű����¼���Ϣ������ δ��������ʩ
     *
     * @param guardMeasure String
     */
    public void setGuardMeasure(String guardMeasure) {
        this.guardMeasure = guardMeasure;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ 	��λ
     *
     * @return String
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ͨ�ű�������
     *
     * @return String
     */
    public String getEnsureName() {
        return this.ensureName;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ �¼�����
     *
     * @return String
     */
    public String getEventType() {
        return this.eventType;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ����ʱ��ֹ
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getEnsureDateTo() throws CalendarException {
        ensureDateTo.setCalPattern(getCalPattern());
        return this.ensureDateTo;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ͨ����ϳ̶�
     *
     * @return String
     */
    public String getBlockDegree() {
        return this.blockDegree;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ��ʧ���
     *
     * @return String
     */
    public String getLossCondition() {
        return this.lossCondition;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ Ӧ�����ϴ�ʩ
     *
     * @return String
     */
    public String getEnsureMeasure() {
        return this.ensureMeasure;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ͨ�Żָ������ʱ��
     *
     * @return String
     */
    public String getRecoverSituation() {
        return this.recoverSituation;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ �ط�������������
     *
     * @return String
     */
    public String getGovernmentEvaluate() {
        return this.governmentEvaluate;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ �¼�ԭ��Ӱ�췶Χ
     *
     * @return String
     */
    public String getReasonAffect() {
        return this.reasonAffect;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ ���ڵ�����
     *
     * @return String
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű����¼���Ϣ������ δ��������ʩ
     *
     * @return String
     */
    public String getGuardMeasure() {
        return this.guardMeasure;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public SimpleCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = new SimpleCalendar(creationDate);
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public SimpleCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
    }

    public int getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(int lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getOrgOpt() {
        return orgOpt;
    }

    public void setOrgOpt(String orgOpt) {
        this.orgOpt = orgOpt;
    }

    public String getEnsureLocation() {
        return ensureLocation;
    }

    public void setEnsureLocation(String ensureLocation) {
        this.ensureLocation = ensureLocation;
    }
}