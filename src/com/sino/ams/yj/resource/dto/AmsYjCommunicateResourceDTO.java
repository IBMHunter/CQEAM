package com.sino.ams.yj.resource.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: ս��Ӧ��ͨ����Դ AmsYjCommunicateResource</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsYjCommunicateResourceDTO extends CheckBoxDTO{

	private String resourceId = "";
	private String deptName = "";
	private String equipmentName = "";
	private String resourceQty = "";
	private String location = "";
	private String model = "";
	private String normalStatus = "";
	private String chargeDept = "";
	private String charger = "";
	private String chargerMobile = "";
	private String keeper = "";
	private String keeperMobile = "";
	private String remark = "";
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar creationDate = null;
	private String createUser = "";
	private SimpleCalendar lastUpdateDate = null;
	private String lastUpdateUser = "";
    private String orgOpt="";
    private String equipmentOpt ="";

    private String comvan="";//�Ƿ���Ӧ��ͨ�ų�
    private String isatphone="";//�Ƿ������ǵ绰

    public AmsYjCommunicateResourceDTO() {
		super();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� ���
	 * @param resourceId String
	 */
	public void setResourceId(String resourceId){
		this.resourceId = resourceId;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	��λ����
	 * @param deptName String
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	װ������
	 * @param equipmentName String
	 */
	public void setEquipmentName(String equipmentName){
		this.equipmentName = equipmentName;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� ����
	 * @param resourceQty String
	 */
	public void setResourceQty(String resourceQty){
		this.resourceQty = resourceQty;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	�÷�λ��
	 * @param location String
	 */
	public void setLocation(String location){
		this.location = location;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	���
	 * @param model String
	 */
	public void setModel(String model){
		this.model = model;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� �Ƿ�������ʹ��
	 * @param normalStatus String
	 */
	public void setNormalStatus(String normalStatus){
		this.normalStatus = normalStatus;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	���ܲ���
	 * @param chargeDept String
	 */
	public void setChargeDept(String chargeDept){
		this.chargeDept = chargeDept;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	������
	 * @param charger String
	 */
	public void setCharger(String charger){
		this.charger = charger;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	�ֻ�
	 * @param chargerMobile String
	 */
	public void setChargerMobile(String chargerMobile){
		this.chargerMobile = chargerMobile;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	������
	 * @param keeper String
	 */
	public void setKeeper(String keeper){
		this.keeper = keeper;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� 	������-�ֻ�
	 * @param keeperMobile String
	 */
	public void setKeeperMobile(String keeperMobile){
		this.keeperMobile = keeperMobile;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� ��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� ������
	 * @param createUser String
	 */
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����ս��Ӧ��ͨ����Դ���� �ϴ��޸���
	 * @param lastUpdateUser String
	 */
	public void setLastUpdateUser(String lastUpdateUser){
		this.lastUpdateUser = lastUpdateUser;
	}


	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� ���
	 * @return String
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	��λ����
	 * @return String
	 */
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	װ������
	 * @return String
	 */
	public String getEquipmentName() {
		return this.equipmentName;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� ����
	 * @return String
	 */
	public String getResourceQty() {
		return this.resourceQty;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	�÷�λ��
	 * @return String
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	���
	 * @return String
	 */
	public String getModel() {
		return this.model;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� �Ƿ�������ʹ��
	 * @return String
	 */
	public String getNormalStatus() {
		return this.normalStatus;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	���ܲ���
	 * @return String
	 */
	public String getChargeDept() {
		return this.chargeDept;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	������
	 * @return String
	 */
	public String getCharger() {
		return this.charger;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	�ֻ�
	 * @return String
	 */
	public String getChargerMobile() {
		return this.chargerMobile;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	������
	 * @return String
	 */
	public String getKeeper() {
		return this.keeper;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� 	������-�ֻ�
	 * @return String
	 */
	public String getKeeperMobile() {
		return this.keeperMobile;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� ��֯ID
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� ������
	 * @return String
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡս��Ӧ��ͨ����Դ���� �ϴ��޸���
	 * @return String
	 */
	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

    public String getOrgOpt() {
        return orgOpt;
    }

    public void setOrgOpt(String orgOpt) {
        this.orgOpt = orgOpt;
    }

    public String getComvan() {
        return comvan;
    }

    public void setComvan(String comvan) {
        this.comvan = comvan;
    }

    public String getEquipmentOpt() {
        return equipmentOpt;
    }

    public void setEquipmentOpt(String equipmentOpt) {
        this.equipmentOpt = equipmentOpt;
    }

    public String getIsatphone() {
        return isatphone;
    }

    public void setIsatphone(String isatphone) {
        this.isatphone = isatphone;
    }
}