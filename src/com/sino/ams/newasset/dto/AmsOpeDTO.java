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
public class AmsOpeDTO extends CommonRecordDTO {

	private String amsOpeId = "";
	private String opeCode = "";
	private String opeName = "";

	public String getAmsOpeId() {
		return amsOpeId;
	}

	public String getOpeCode() {
		return opeCode;
	}

	public String getOpeName() {
		return opeName;
	}

	public void setAmsOpeId(String amsOpeId) {
		this.amsOpeId = amsOpeId;
	}

	public void setOpeCode(String opeCode) {
		this.opeCode = opeCode;
	}

	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}
}
