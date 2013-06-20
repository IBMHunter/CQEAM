package com.sino.ams.yj.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: ���ǵ绰��Ϣ�� AmsYjIsatphone</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author jialongchuan
 * @version 1.0
 */

public class AmsYjIsatphoneDTO extends CheckBoxDTO {

    private String isatphoneId = "";
    private String isatphoneName = "";
    private String isatphoneType = "";
    private String isatphoneModel = "";
    private String isatphoneAddress = "";
    private String tel = "";
    private String buyingTime = "";
    private String cost = "";
    private String usedNumber = "";
    private int  organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar creationDate = null;
    private String createUser = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateUser = "";
    private String organizationOption = "";
    private String resourceId = "";
    private String equipmentName = "";

    public AmsYjIsatphoneDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();

    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ���
     *
     * @param isatphoneId AdvancedNumber
     */
    public void setIsatphoneId(String isatphoneId) {
        this.isatphoneId = isatphoneId;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ������������
     *
     * @param isatphoneName String
     */
    public void setIsatphoneName(String isatphoneName) {
        this.isatphoneName = isatphoneName;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ���
     *
     * @param isatphoneType String
     */
    public void setIsatphoneType(String isatphoneType) {
        this.isatphoneType = isatphoneType;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ �ͺ�
     *
     * @param isatphoneModel String
     */
    public void setIsatphoneModel(String isatphoneModel) {
        this.isatphoneModel = isatphoneModel;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ �洢�ص��λ
     *
     * @param isatphoneAddress String
     */
    public void setIsatphoneAddress(String isatphoneAddress) {
        this.isatphoneAddress = isatphoneAddress;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ �绰����
     *
     * @param tel String
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ����ʱ��
     *
     * @param buyingTime String
     */
    public void setBuyingTime(String buyingTime) {
        this.buyingTime = buyingTime;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ �ʲ�ԭֵ(��Ԫ)
     *
     * @param cost String
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ʹ�ô������꣩
     *
     * @param usedNumber String
     */
    public void setUsedNumber(String usedNumber) {
        this.usedNumber = usedNumber;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ��֯ID
     *
     * @param organizationId AdvancedNumber
     */
    public void setOrganizationId(int  organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ��������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ ������
     *
     * @param createUser AdvancedNumber
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ �ϴ��޸�����
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ��������ǵ绰��Ϣ������ �ϴ��޸���
     *
     * @param lastUpdateUser AdvancedNumber
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }


    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ���
     *
     * @return AdvancedNumber
     */
    public String getIsatphoneId() {
        return this.isatphoneId;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ������������
     *
     * @return String
     */
    public String getIsatphoneName() {
        return this.isatphoneName;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ���
     *
     * @return String
     */
    public String getIsatphoneType() {
        return this.isatphoneType;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ �ͺ�
     *
     * @return String
     */
    public String getIsatphoneModel() {
        return this.isatphoneModel;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ �洢�ص��λ
     *
     * @return String
     */
    public String getIsatphoneAddress() {
        return this.isatphoneAddress;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ �绰����
     *
     * @return String
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ����ʱ��
     *
     * @return String
     */
    public String getBuyingTime() {
        return this.buyingTime;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ �ʲ�ԭֵ(��Ԫ)
     *
     * @return String
     */
    public String getCost() {
        return this.cost;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ʹ�ô������꣩
     *
     * @return String
     */
    public String getUsedNumber() {
        return this.usedNumber;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ��֯ID
     *
     * @return AdvancedNumber
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ ������
     *
     * @return AdvancedNumber
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ �ϴ��޸�����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ���ǵ绰��Ϣ������ �ϴ��޸���
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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}