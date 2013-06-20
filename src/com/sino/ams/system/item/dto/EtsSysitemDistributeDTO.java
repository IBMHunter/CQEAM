package com.sino.ams.system.item.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ������֯����� EtsSysitemDistribute</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsSysitemDistributeDTO extends CheckBoxDTO{

	private int systemId  ;
	private String itemCode = "";
	private int organizationId  ;
	private Timestamp creationDate = null;
	private int createdBy ;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy  ;

	/**
	 * ���ܣ�����������֯��������� ���к�
	 * @param systemId String
	 */
	public void setSystemId(int systemId){
		this.systemId = systemId;
	}

	/**
	 * ���ܣ�����������֯��������� �豸����
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ�����������֯��������� ������֯
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����������֯��������� ��������
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
	 * ���ܣ�����������֯��������� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����������֯��������� �ϴ��޸�����
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
	 * ���ܣ�����������֯��������� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ������֯��������� ���к�
	 * @return String
	 */
	public int getSystemId(){
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡ������֯��������� �豸����
	 * @return String
	 */
	public String getItemCode(){
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ������֯��������� ������֯
	 * @return String
	 */
	public int getOrganizationId(){
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ������֯��������� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ������֯��������� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ������֯��������� �ϴ��޸�����
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ������֯��������� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}
