package com.sino.ams.system.trust.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ��ά��˾��(EAM) AmsMaintainCompany</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsMaintainCompanyDTO extends CheckBoxDTO{
    private String companyId="";
	private String name = "";
	private String address = "";
	private String legalRepresentative = "";
	private String contactPeople = "";
	private String officeTelephone = "";
	private String contactTelephone = "";
	private String faxNumber = "";
	private int organizationId ;
	private Timestamp creationDate = null;
	private int createdBy;
    private Timestamp lastUpdateDate = null;
	private int lastUpdateBy;
	private String  countyCode="";
	private String remark = "";

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String  getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String  countyCode) {
        this.countyCode = countyCode;
    }

    public int getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String  companyId) {
        this.companyId = companyId;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ��˾����
	 * @param name String
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ��˾��ַ
	 * @param address String
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ���˴���
	 * @param legalRepresentative String
	 */
	public void setLegalRepresentative(String legalRepresentative){
		this.legalRepresentative = legalRepresentative;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ��ϵ��
	 * @param contactPeople String
	 */
	public void setContactPeople(String contactPeople){
		this.contactPeople = contactPeople;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� �칫�绰
	 * @param officeTelephone String
	 */
	public void setOfficeTelephone(String officeTelephone){
		this.officeTelephone = officeTelephone;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ��ϵ�˵绰
	 * @param contactTelephone String
	 */
	public void setContactTelephone(String contactTelephone){
		this.contactTelephone = contactTelephone;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� �������
	 * @param faxNumber String
	 */
	public void setFaxNumber(String faxNumber){
		this.faxNumber = faxNumber;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ����OU��֯ID
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� ��������
	 * @param creationDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			SimpleCalendar cal = new SimpleCalendar(creationDate);
			this.creationDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ����ô�ά��˾��(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
			this.lastUpdateDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ��˾����
	 * @return String
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ��˾��ַ
	 * @return String
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ���˴���
	 * @return String
	 */
	public String getLegalRepresentative(){
		return this.legalRepresentative;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ��ϵ��
	 * @return String
	 */
	public String getContactPeople(){
		return this.contactPeople;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� �칫�绰
	 * @return String
	 */
	public String getOfficeTelephone(){
		return this.officeTelephone;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ��ϵ�˵绰
	 * @return String
	 */
	public String getContactTelephone(){
		return this.contactTelephone;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� �������
	 * @return String
	 */
	public String getFaxNumber(){
		return this.faxNumber;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ����OU��֯ID
	 * @return String
	 */
	public int getOrganizationId(){
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ��ά��˾��(EAM)���� �ϴ��޸�����
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

}