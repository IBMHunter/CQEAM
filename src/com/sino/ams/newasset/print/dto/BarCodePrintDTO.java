package com.sino.ams.newasset.print.dto;

import com.sino.base.dto.DTO;

/**
 * 
 * @ϵͳ����: ��ǩ��ӡDTO
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 22, 2011
 */
public class BarCodePrintDTO implements DTO {
	private String companyNamePrint = "";
	private String barcodePrint = ""; // ��ǩ��ͼƬ
	private String barcodeImg = ""; // ��ǩ��ͼƬ
	private String itemNamePrint = "";
	private String itemSpecPrint = "";
	private String startDatePrint = "";

	public String getCompanyNamePrint() {
		return companyNamePrint;
	}

	public void setCompanyNamePrint(String companyNamePrint) {
		this.companyNamePrint = companyNamePrint;
	}

	public String getBarcodePrint() {
		return barcodePrint;
	}

	public void setBarcodePrint(String barcodePrint) {
		this.barcodePrint = barcodePrint;
	}

	public String getBarcodeImg() {
		return barcodeImg;
	}

	public void setBarcodeImg(String barcodeImg) {
		this.barcodeImg = barcodeImg;
	}

	public String getItemNamePrint() {
		return itemNamePrint;
	}

	public void setItemNamePrint(String itemNamePrint) {
		this.itemNamePrint = itemNamePrint;
	}

	public String getItemSpecPrint() {
		return itemSpecPrint;
	}

	public void setItemSpecPrint(String itemSpecPrint) {
		this.itemSpecPrint = itemSpecPrint;
	}

	public String getStartDatePrint() {
		return startDatePrint;
	}

	public void setStartDatePrint(String startDatePrint) {
		this.startDatePrint = startDatePrint;
	}

}
