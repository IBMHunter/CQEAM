package com.sino.soa.mis.srv.transassetdistribution.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: SRV_VENDOR_INFO SrvVendorInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SrvAssetDistributionDTO extends CheckBoxDTO{
	private String bookTypeCode="";
	private String assetNumber ="";
	private String stratLastUpdateDate="";
	private String endLastUpdateDate="";
	private String orgOption="";
	private String assetsType ="";
	/**
	 * @return the orgOption
	 */
	public String getOrgOption() {
		return orgOption;
	}
	/**
	 * @param orgOption the orgOption to set
	 */
	public void setOrgOption(String orgOption) {
		this.orgOption = orgOption;
	}
	/**
	 * @return the bookTypeCode
	 */
	public String getBookTypeCode() {
		return bookTypeCode;
	}
	/**
	 * @param bookTypeCode the bookTypeCode to set
	 */
	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}
	/**
	 * @return the assetNumber
	 */
	public String getAssetNumber() {
		return assetNumber;
	}
	/**
	 * @param assetNumber the assetNumber to set
	 */
	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}
	/**
	 * @return the stratLastUpdateDate
	 */
	public String getStratLastUpdateDate() {
		return stratLastUpdateDate;
	}
	/**
	 * @param stratLastUpdateDate the stratLastUpdateDate to set
	 */
	public void setStratLastUpdateDate(String stratLastUpdateDate) {
		this.stratLastUpdateDate = stratLastUpdateDate;
	}
	/**
	 * @return the endLastUpdateDate
	 */
	public String getEndLastUpdateDate() {
		return endLastUpdateDate;
	}
	/**
	 * @param endLastUpdateDate the endLastUpdateDate to set
	 */
	public void setEndLastUpdateDate(String endLastUpdateDate) {
		this.endLastUpdateDate = endLastUpdateDate;
	}
	public String getAssetsType() {
		return assetsType;
	}
	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}
	
}