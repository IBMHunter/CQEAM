package com.sino.ams.match.amselementmatch.dto;

import com.sino.base.dto.CheckBoxDTO;

public class AmsElementMatchDTO extends CheckBoxDTO{
	
	public AmsElementMatchDTO() {
		super();
	}
	
//�ʲ�Ŀ¼�е�����
	//�ʲ�Ŀ¼ID
	private String contentId = "";
	
	//�ʲ�Ŀ¼����
	private String contentCode = "";
	
	//�ʲ�Ŀ¼����
	private String contentName = "";
	

//�߼�����Ԫ������
	//�߼�����Ԫ��ID
	private String amsLneId = "";
	//private int amsLneId;
	
	//����רҵ1
	private String netCategory1= "";
	
	//����רҵ2
	private String netCategory2= "";
	
	//רҵ1���a
	private String netCategory1Code= "";
	
	//רҵ2���a
	private String netCategory2Code= "";
	
	//��Ԫ����
	private String netUnitCode = "";
	
	//�߼�����Ԫ��
	private String logNetEle = "";
	
	//Ӣ�ļ��
	private String engAb = "";
	
//Ͷ�ʷ�������
	//Ͷ�ʷ�������ID
	private String amsCexId = "";
	
	//Ͷ�ʴ���
	private String investCategory1 = "";
	
	//Ͷ������
	private String investCategory2 = "";
	
	//Ͷ�ʷ������
	private String investCatCode = "";
	
	//Ͷ�ʷ�������
	private String investCatName = "";
	

//ҵ��ƽ̨����
	//ҵ��ƽ̨����ID
	private String amsOpeId = "";
	
	//ҵ��ƽ̨����
	private String opeCode = "";
	
	//ҵ��ƽ̨����
	private String opeName = "";
	

//����������
	//����������ID
//	private String amsLneId = "";
	
	//�����α���
	private String lneCode = "";
	
	//����������
	private String lneName = "";
	
	//��������
	private String accessType = "";
	
	//�Ƿ���Ч
	private String enabled = "";
	
	//�ɱ�����
	private String costType = "";
	
	
	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	private String[] contentCodes = new String[]{};

	public String getAmsCexId() {
		return amsCexId;
	}

	public void setAmsCexId(String amsCexId) {
		this.amsCexId = amsCexId;
	}

	public String getAmsLneId() {
		return amsLneId;
	}

	public void setAmsLneId(String amsLneId) {
		this.amsLneId = amsLneId;
	}

	public String getAmsOpeId() {
		return amsOpeId;
	}

	public void setAmsOpeId(String amsOpeId) {
		this.amsOpeId = amsOpeId;
	}

	public String getContentCode() {
		return contentCode;
	}

	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getInvestCatCode() {
		return investCatCode;
	}

	public void setInvestCatCode(String investCatCode) {
		this.investCatCode = investCatCode;
	}

	public String getInvestCategory1() {
		return investCategory1;
	}

	public void setInvestCategory1(String investCategory1) {
		this.investCategory1 = investCategory1;
	}

	public String getInvestCategory2() {
		return investCategory2;
	}

	public void setInvestCategory2(String investCategory2) {
		this.investCategory2 = investCategory2;
	}

	public String getInvestCatName() {
		return investCatName;
	}

	public void setInvestCatName(String investCatName) {
		this.investCatName = investCatName;
	}

	public String getLneCode() {
		return lneCode;
	}

	public void setLneCode(String lneCode) {
		this.lneCode = lneCode;
	}

	public String getLneName() {
		return lneName;
	}

	public void setLneName(String lneName) {
		this.lneName = lneName;
	}

	public String getLogNetEle() {
		return logNetEle;
	}

	public void setLogNetEle(String logNetEle) {
		this.logNetEle = logNetEle;
	}

	public String getNetCategory1() {
		return netCategory1;
	}

	public void setNetCategory1(String netCategory1) {
		this.netCategory1 = netCategory1;
	}

	public String getNetCategory2() {
		return netCategory2;
	}

	public void setNetCategory2(String netCategory2) {
		this.netCategory2 = netCategory2;
	}

	public String getNetUnitCode() {
		return netUnitCode;
	}

	public void setNetUnitCode(String netUnitCode) {
		this.netUnitCode = netUnitCode;
	}

	public String getOpeCode() {
		return opeCode;
	}

	public void setOpeCode(String opeCode) {
		this.opeCode = opeCode;
	}

	public String getOpeName() {
		return opeName;
	}

	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String[] getContentCodes() {
		return contentCodes;
	}

	public void setContentCodes(String[] contentCodes) {
		this.contentCodes = contentCodes;
	}

	public String getEngAb() {
		return engAb;
	}

	public void setEngAb(String engAb) {
		this.engAb = engAb;
	}
	public String getNetCategory1Code() {
		return netCategory1Code;
	}

	public void setNetCategory1Code(String netCategory1Code) {
		this.netCategory1Code = netCategory1Code;
	}

	public String getNetCategory2Code() {
		return netCategory2Code;
	}

	public void setNetCategory2Code(String netCategory2Code) {
		this.netCategory2Code = netCategory2Code;
	}
	
	
}
