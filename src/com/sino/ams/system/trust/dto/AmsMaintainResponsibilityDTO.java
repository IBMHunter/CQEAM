package com.sino.ams.system.trust.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;

/**
* <p>Title: ��ά���� AmsMaintainResponsibility</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class AmsMaintainResponsibilityDTO extends EtsObjectDTO{

	private String systemId = "";
	private String companyId = "";
    private String companyId2 = "";
	private int objectNo = SyBaseSQLUtil.NULL_INT_VALUE;
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String remark = "";

    private String  checkPoint = "";

	private String toConfirmLocOpt = "";
	private String confirmedLocOpt = "";
	private String countyOpt = "";





	/**
	 * ���ܣ����ô�ά�������� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}



	public String getSystemId() {
		return systemId;
	}



	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}



	public String getCompanyId() {
		return companyId;
	}



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}



	public String getCompanyId2() {
		return companyId2;
	}



	public void setCompanyId2(String companyId2) {
		this.companyId2 = companyId2;
	}



	public int getObjectNo() {
		return objectNo;
	}



	public void setObjectNo(int objectNo) {
		this.objectNo = objectNo;
	}



	public int getOrganizationId() {
		return organizationId;
	}



	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}



	/**
	 * ���ܣ���ȡ��ά�������� ��ע
	 * @return String
	 */
	public String getRemark(){
		return this.remark;
	}

    /**
	 * ���ܣ���ȡ���㣨EO.WORKORDER_OBJECT_NO||'@@@'||AC.COMPANY_ID CHECK_POINT��  �ϴθ�����
	 * @return String
	 */
    public String getCheckPoint() {
        return checkPoint;
    }

	public String getConfirmedLocOpt() {
		return confirmedLocOpt;
	}

	public String getToConfirmLocOpt() {
		return toConfirmLocOpt;
	}

	public String getCountyOpt() {
		return countyOpt;
	}

	public void setCheckPoint(String checkPoint) {
        this.checkPoint=checkPoint;
    }

	public void setConfirmedLocOpt(String confirmedLocOpt) {
		this.confirmedLocOpt = confirmedLocOpt;
	}

	public void setToConfirmLocOpt(String toConfirmLocOpt) {
		this.toConfirmLocOpt = toConfirmLocOpt;
	}

	public void setCountyOpt(String countyOpt) {
		this.countyOpt = countyOpt;
	}
}
