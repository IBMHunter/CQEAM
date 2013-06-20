package com.sino.ams.system.user.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: EtsOuCityMap</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsOuCityMapDTO extends CheckBoxDTO{
	private int organizationId;
	private String company = "";
	private String companyCode = "";
	private String enabled = "";
	private Timestamp creationDate = null;
	private int createdBy;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy;
	private String bookTypeCode = "";
	private String bookTypeName = "";
	/**
	 * ���ܣ�����DTO���� organizationId
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����DTO���� company
	 * @param company String
	 */
	public void setCompany(String company){
		this.company = company;
	}

	/**
	 * ���ܣ�����DTO���� companyCode
	 * @param companyCode String
	 */
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}

	/**
	 * ���ܣ�����DTO���� enabled
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

	/**
	 * ���ܣ�����DTO���� creationDate
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
	 * ���ܣ�����DTO���� createdBy
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����DTO���� lastUpdateDate
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
	 * ���ܣ�����DTO���� lastUpdateBy
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	/**
	 * ���ܣ���ȡDTO���� organizationId
	 * @return String
	 */
	public int getOrganizationId(){
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡDTO���� company
	 * @return String
	 */
	public String getCompany(){
		return this.company;
	}

	/**
	 * ���ܣ���ȡDTO���� companyCode
	 * @return String
	 */
	public String getCompanyCode(){
		return this.companyCode;
	}

	/**
	 * ���ܣ���ȡDTO���� enabled
	 * @return String
	 */
	public String getEnabled(){
		return this.enabled;
	}

	/**
	 * ���ܣ���ȡDTO���� creationDate
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡDTO���� createdBy
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateDate
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateBy
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getBookTypeCode() {
		return bookTypeCode;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

}
