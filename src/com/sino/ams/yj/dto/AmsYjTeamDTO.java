package com.sino.ams.yj.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: Ӧ��ͨ�ű��϶���� AmsYjTeam</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0 
 */

public class AmsYjTeamDTO extends CheckBoxDTO {

    private String teamId = "";
    private String teamName = "";
    private String responsibilityUser = "";
    private String tel = "";
    private String quantity = "";
    private String situation = "";
    private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar creationDate = null;
    private String createUser = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateUser = "";
    private String organizationOption = "";

    public AmsYjTeamDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ���
     *
     * @param teamId AdvancedNumber
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��������
     *
     * @param teamName String
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��ҵ������
     *
     * @param responsibilityUser String
     */
    public void setResponsibilityUser(String responsibilityUser) {
        this.responsibilityUser = responsibilityUser;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� �ֻ���
     *
     * @param tel String
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��������
     *
     * @param quantity AdvancedNumber
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� �������������ص�
     *
     * @param situation String
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��֯ID
     *
     * @param organizationId AdvancedNumber
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� ������
     *
     * @param createUser AdvancedNumber
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ�����Ӧ��ͨ�ű��϶�������� �ϴ��޸���
     *
     * @param lastUpdateUser AdvancedNumber
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }


    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ���
     *
     * @return AdvancedNumber
     */
    public String getTeamId() {
        return this.teamId;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��������
     *
     * @return String
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��ҵ������
     *
     * @return String
     */
    public String getResponsibilityUser() {
        return this.responsibilityUser;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� �ֻ���
     *
     * @return String
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��������
     *
     * @return AdvancedNumber
     */
    public String getQuantity() {
        return this.quantity;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� �������������ص�
     *
     * @return String
     */
    public String getSituation() {
        return this.situation;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��֯ID
     *
     * @return AdvancedNumber
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� ������
     *
     * @return AdvancedNumber
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� �ϴ��޸�����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡӦ��ͨ�ű��϶�������� �ϴ��޸���
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

}