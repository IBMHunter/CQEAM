package com.sino.ams.yj.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.EtsFaAssetsDTO;

/**
 * <p>Title: Ӧ����������� AmsYjDrill</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author jialongchuan
 * @version 1.0
 */

public class AmsYjDrillDTO extends EtsFaAssetsDTO {

    private String drillId = "";              //
    private String drillType = "";
    private String drillNature = "";
    private String drillName = "";
    private SimpleCalendar drillDate = null;
    private String drillAddress = "";
    private String peopleQuality = "";        //
    private String equipmentQuantity = "";
    private String plan1= "";
    private String question = "";
    private String isPerfect = "";
    private SimpleCalendar planDate = null;
    private String remark = "";
    private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar creationDate = null;
    private String createUser = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateUser = "";
    private String organizationOption = "";

    public AmsYjDrillDTO() {
        super();
        this.drillDate = new SimpleCalendar();
        this.planDate = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();

    }

    /**
     * ���ܣ�����Ӧ��������������� ���
     *
     * @param drillId AdvancedNumber
     */
    public void setDrillId(String drillId) {
        this.drillId = drillId;
    }

    /**
     * ���ܣ�����Ӧ��������������� ��������
     *
     * @param drillType String
     */
    public void setDrillType(String drillType) {
        this.drillType = drillType;
    }

    /**
     * ���ܣ�����Ӧ��������������� ��������
     *
     * @param drillNature String
     */
    public void setDrillNature(String drillNature) {
        this.drillNature = drillNature;
    }

    /**
     * ���ܣ�����Ӧ��������������� ��������
     *
     * @param drillName String
     */
    public void setDrillName(String drillName) {
        this.drillName = drillName;
    }

    /**
     * ���ܣ�����Ӧ��������������� ����ʱ��
     *
     * @param drillDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDrillDate(String drillDate) throws CalendarException {
        this.drillDate.setCalendarValue(drillDate);
    }

    /**
     * ���ܣ�����Ӧ��������������� �����ص�
     *
     * @param drillAddress String
     */
    public void setDrillAddress(String drillAddress) {
        this.drillAddress = drillAddress;
    }

    /**
     * ���ܣ�����Ӧ��������������� ��������
     *
     * @param peopleQuality AdvancedNumber
     */
    public void setPeopleQuality(String peopleQuality) {
        this.peopleQuality = peopleQuality;
    }

    /**
     * ���ܣ�����Ӧ��������������� ����װ������
     *
     * @param equipmentQuantity AdvancedNumber
     */
    public void setEquipmentQuantity(String equipmentQuantity) {
        this.equipmentQuantity = equipmentQuantity;
    }

    /**
     * ���ܣ�����Ӧ��������������� ������������
     *
     * @param question String
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * ���ܣ�����Ӧ��������������� �Ƿ���Ҫ����Ԥ��
     *
     * @param isPerfect String
     */
    public void setIsPerfect(String isPerfect) {
        this.isPerfect = isPerfect;
    }

    /**
     * ���ܣ�����Ӧ��������������� ����Ԥ���ƻ�ʱ��
     *
     * @param planDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setPlanDate(String planDate) throws CalendarException {
        this.planDate.setCalendarValue(planDate);
    }

    /**
     * ���ܣ�����Ӧ��������������� ��ע
     *
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ�����Ӧ��������������� ��֯ID
     *
     * @param organizationId AdvancedNumber
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ�����Ӧ��������������� ��������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ�����Ӧ��������������� ������
     *
     * @param createUser AdvancedNumber
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * ���ܣ�����Ӧ��������������� �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ�����Ӧ��������������� �ϴ��޸���
     *
     * @param lastUpdateUser AdvancedNumber
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }


    /**
     * ���ܣ���ȡӦ��������������� ���
     *
     * @return AdvancedNumber
     */
    public String getDrillId() {
        return this.drillId;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��������
     *
     * @return String
     */
    public String getDrillType() {
        return this.drillType;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��������
     *
     * @return String
     */
    public String getDrillNature() {
        return this.drillNature;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��������
     *
     * @return String
     */
    public String getDrillName() {
        return this.drillName;
    }

    /**
     * ���ܣ���ȡӦ��������������� ����ʱ��
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getDrillDate() throws CalendarException {
        drillDate.setCalPattern(getCalPattern());
        return this.drillDate;
    }

    /**
     * ���ܣ���ȡӦ��������������� �����ص�
     *
     * @return String
     */
    public String getDrillAddress() {
        return this.drillAddress;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��������
     *
     * @return AdvancedNumber
     */
    public String getPeopleQuality() {
        return this.peopleQuality;
    }

    /**
     * ���ܣ���ȡӦ��������������� ����װ������
     *
     * @return AdvancedNumber
     */
    public String getEquipmentQuantity() {
        return this.equipmentQuantity;
    }

    /**
     * ���ܣ���ȡӦ��������������� ������������
     *
     * @return String
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * ���ܣ���ȡӦ��������������� �Ƿ���Ҫ����Ԥ��
     *
     * @return String
     */
    public String getIsPerfect() {
        return this.isPerfect;
    }

    /**
     * ���ܣ���ȡӦ��������������� ����Ԥ���ƻ�ʱ��
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getPlanDate() throws CalendarException {
        planDate.setCalPattern(getCalPattern());
        return this.planDate;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��ע
     *
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��֯ID
     *
     * @return AdvancedNumber
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡӦ��������������� ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡӦ��������������� ������
     *
     * @return AdvancedNumber
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * ���ܣ���ȡӦ��������������� �ϴ��޸�����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
     * ���ܣ���ȡӦ��������������� �ϴ��޸���
     *
     * @return AdvancedNumber
     */
	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

       public String getOrganizationOption() {
        return organizationOption;
    }

    public void setOrganizationOption(String organizationOption) {
        this.organizationOption = organizationOption;
    }

	public String getPlan1() {
		return plan1;
	}

	public void setPlan1(String plan1) {
		this.plan1 = plan1;
	}
}