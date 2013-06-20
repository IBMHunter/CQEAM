package com.sino.ams.workorder.dto;

import com.sino.base.dto.DTO;

/**
 * 
 * @ϵͳ����: Ԥת�������ӡ�����ֶ�
 * @��������:
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Sep 14, 2011
 */
public class EtsItemDTO implements DTO {
	private String companyName = "";
	private String barcode2 = ""; // ��ǩ��ͼƬ
	private String barcodeImg = ""; // ��ǩ��ͼƬ
	private String itemName = "";
	private String itemSpec = "";
	private String startDate2 = "";

	public String getBarcode2() {
		return barcode2;
	}

	public void setBarcode2(String barcode2) {
		this.barcode2 = barcode2;
	}

	public String getBarcodeImg() {
		return barcodeImg;
	}

	public void setBarcodeImg(String barcodeImg) {
		this.barcodeImg = barcodeImg;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStartDate2() {
		if( startDate2.length() > 10 ){
			startDate2 = startDate2.substring( 0 , 10 );
		}
		return startDate2;
	}

	public void setStartDate2(String startDate2) {
		this.startDate2 = startDate2;
	}
}
