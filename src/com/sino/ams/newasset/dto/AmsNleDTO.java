package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * <p>Title: CMCCEAM</p>
 * <p>Description: �й��ƶ������ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AmsNleDTO extends CommonRecordDTO {

	private String amsLneId = "";
	private String lneCode = "";
	private String lneName = "";
	public String getAmsLneId() {
		return amsLneId;
	}

	public String getLneCode() {
		return lneCode;
	}

	public String getLneName() {
		return lneName;
	}

	public void setAmsLneId(String amsLneId) {
		this.amsLneId = amsLneId;
	}

	public void setLneCode(String lneCode) {
		this.lneCode = lneCode;
	}

	public void setLneName(String lneName) {
		this.lneName = lneName;
	}
}
