package com.sino.ams.newasset.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class AmsDeprnAssetsDTO extends EtsFaAssetsDTO {
	
	

	public AmsDeprnAssetsDTO() {
		super();
		this.lastUpdateDate = new SimpleCalendar();
	}

	//�ʲ��˲�
	private String bookTypeCode = "";

//	�ʲ�Ψһ��ʶ
	private int assetId= -1;
	
//	�ʲ���ǩ��
	private String tagNumber = "";
	
//	�̶��ʲ����	    
	private String assetNumber = "";
	   
//	�̶��ʲ�����  
	private String description = "";
	  
//	������˾  
	private String segment1 = "";
	  
//	����һ������  
	private String segment2 = "";
	  
//	�̶��ʲ�ԭֵ  
	private float cost = 0;
	  
//	��ֵ   
	private String netBookValue = "";
	   
//	���¼�ֵ׼��  
	private String ptdImpairment = "";
	  
//	�����ֵ׼��   
	private String ytdImpairment = "";
	  
//	�ۼƼ�ֵ׼��   
	private String impairmentReserve = "";
	  
//	�����۾�  
	private String ptdDeprn = "";
	  
//	�����۾�  
	private String ytdDeprn = "";
	  
//	�ۼ��۾�   
	private String deprnReserve = "";
	  
//	�۾��ڼ�  
	private String periodName = "";
	  
//	�۾�ʣ������   
	private String deprnLeftMonth = "";
	   
//	����������  
	private SimpleCalendar lastUpdateDate = null;
	  
//	��˾Selectѡ��
	private String orgOption = "";

//	��֯OU
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	
	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	public String getBookTypeCode() {
		return bookTypeCode;
	}

	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDeprnLeftMonth() {
		return deprnLeftMonth;
	}

	public void setDeprnLeftMonth(String deprnLeftMonth) {
		this.deprnLeftMonth = deprnLeftMonth;
	}

	public String getDeprnReserve() {
		return deprnReserve;
	}

	public void setDeprnReserve(String deprnReserve) {
		this.deprnReserve = deprnReserve;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImpairmentReserve() {
		return impairmentReserve;
	}

	public void setImpairmentReserve(String impairmentReserve) {
		this.impairmentReserve = impairmentReserve;
	}

	public String getPtdImpairment() {
		return ptdImpairment;
	}

	public void setPtdImpairment(String ptdImpairment) {
		this.ptdImpairment = ptdImpairment;
	}


	public SimpleCalendar getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	public String getNetBookValue() {
		return netBookValue;
	}

	public void setNetBookValue(String netBookValue) {
		this.netBookValue = netBookValue;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getPtdDeprn() {
		return ptdDeprn;
	}

	public void setPtdDeprn(String ptdDeprn) {
		this.ptdDeprn = ptdDeprn;
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public String getSegment2() {
		return segment2;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public String getYtdDeprn() {
		return ytdDeprn;
	}

	public void setYtdDeprn(String ytdDeprn) {
		this.ytdDeprn = ytdDeprn;
	}

	public String getYtdImpairment() {
		return ytdImpairment;
	}

	public void setYtdImpairment(String ytdImpairment) {
		this.ytdImpairment = ytdImpairment;
	}

	public String getOrgOption() {
		return orgOption;
	}

	public void setOrgOption(String orgOption) {
		this.orgOption = orgOption;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

}
