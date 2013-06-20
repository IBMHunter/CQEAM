package com.sino.ams.system.trust.dto;

import java.sql.Timestamp;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ��ά��Ա��(EAM) AmsMaintainPeople</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsMaintainPeopleDTO extends CheckBoxDTO{

	private String userId = "";
	private String userName = "";
	private String userTelephone = "";
	private String userMobilePhone = "";
	private String email = "";
	private String bpNumber = "";
	private String companyId =  SyBaseSQLUtil.NULL_INT_VALUE+"";
	private Timestamp creationDate = null;
	private int createdBy =  SyBaseSQLUtil.NULL_INT_VALUE;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy =  SyBaseSQLUtil.NULL_INT_VALUE;


	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ���к�
	 * @param userId String
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ��ά��Ա����
	 * @param userName String
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ��ά��Ա�̻�
	 * @param userTelephone String
	 */
	public void setUserTelephone(String userTelephone){
		this.userTelephone = userTelephone;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ��ά��Ա�ƶ��绰
	 * @param userMobilePhone String
	 */
	public void setUserMobilePhone(String userMobilePhone){
		this.userMobilePhone = userMobilePhone;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ��ά��Ա��������
	 * @param email String
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ��ά��ԱBP������
	 * @param bpNumber String
	 */
	public void setBpNumber(String bpNumber){
		this.bpNumber = bpNumber;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ������ά��˾
	 * @param companyId String
	 */
	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� ��������
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
	 * ���ܣ����ô�ά��Ա��(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����ô�ά��Ա��(EAM)���� �ϴ��޸�����
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
	 * ���ܣ����ô�ά��Ա��(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ���к�
	 * @return String
	 */
	public String getUserId(){
		return this.userId;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ��ά��Ա����
	 * @return String
	 */
	public String getUserName(){
		return this.userName;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ��ά��Ա�̻�
	 * @return String
	 */
	public String getUserTelephone(){
		return this.userTelephone;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ��ά��Ա�ƶ��绰
	 * @return String
	 */
	public String getUserMobilePhone(){
		return this.userMobilePhone;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ��ά��Ա��������
	 * @return String
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ��ά��ԱBP������
	 * @return String
	 */
	public String getBpNumber(){
		return this.bpNumber;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ������ά��˾
	 * @return String
	 */
	public String getCompanyId(){
		return this.companyId;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� �ϴ��޸�����
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ��ά��Ա��(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}