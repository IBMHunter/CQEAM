package com.sino.ams.expand.dto;


/**
* <p>Title: �ʲ���չ��Ϣ��ETS_ITEM_INFO_EX EtsItemInfoEx</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsItemInfoExSearchDTO extends EtsItemInfoExDTO{


	private String responsibilityDept="";	//���β���
	
	private String deptName="";	//���β���
	
	private String financeProp="";	//��������
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getFinanceProp() {
		return financeProp;
	}
	public void setFinanceProp(String financeProp) {
		this.financeProp = financeProp;
	}
	public String getResponsibilityDept() {
		return responsibilityDept;
	}
	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}
}