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
public class AmsCexDTO extends CommonRecordDTO {
	String amsCexId = "";
	String investCategory1 = "";
	String investCategory2 = "";
	String investCatCode = "";
	String investCatName = "";

	public String getAmsCexId() {
		return amsCexId;
	}

	public String getInvestCatCode() {
		return investCatCode;
	}

	public String getInvestCategory1() {
		return investCategory1;
	}

	public String getInvestCategory2() {
		return investCategory2;
	}

	public String getInvestCatName() {
		return investCatName;
	}

	public void setAmsCexId(String amsCexId) {
		this.amsCexId = amsCexId;
	}

	public void setInvestCatCode(String investCatCode) {
		this.investCatCode = investCatCode;
	}

	public void setInvestCategory1(String investCategory1) {
		this.investCategory1 = investCategory1;
	}

	public void setInvestCategory2(String investCategory2) {
		this.investCategory2 = investCategory2;
	}

	public void setInvestCatName(String investCatName) {
		this.investCatName = investCatName;
	}

}
