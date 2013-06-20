package com.sino.ams.system.item.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ETS_MIS_PO_VENDORS EtsMisPoVendors</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsMisPoVendorsDTO extends CheckBoxDTO{

	private String vendorId = "";
	private String vendorName = "";
	private String vendorNameAlt = "";
	private String segment1 = "";
	private String summaryFlag = "";
	private Timestamp creationDate = null;
	private int createdBy;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy;


	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� null
	 * @param vendorId String
	 */
	public void setVendorId(String vendorId){
		this.vendorId = vendorId;
	}

	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� null
	 * @param vendorName String
	 */
	public void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}

	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� null
	 * @param vendorNameAlt String
	 */
	public void setVendorNameAlt(String vendorNameAlt){
		this.vendorNameAlt = vendorNameAlt;
	}

	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� null
	 * @param segment1 String
	 */
	public void setSegment1(String segment1){
		this.segment1 = segment1;
	}

	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� null
	 * @param summaryFlag String
	 */
	public void setSummaryFlag(String summaryFlag){
		this.summaryFlag = summaryFlag;
	}

	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� ��������
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
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� �ϴ��޸�����
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
	 * ���ܣ�����ETS_MIS_PO_VENDORS���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� null
	 * @return String
	 */
	public String getVendorId(){
		return this.vendorId;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� null
	 * @return String
	 */
	public String getVendorName(){
		return this.vendorName;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� null
	 * @return String
	 */
	public String getVendorNameAlt(){
		return this.vendorNameAlt;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� null
	 * @return String
	 */
	public String getSegment1(){
		return this.segment1;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� null
	 * @return String
	 */
	public String getSummaryFlag(){
		return this.summaryFlag;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� �ϴ��޸�����
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡETS_MIS_PO_VENDORS���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}