package com.sino.ams.newSite.dto;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.ams.bean.CommonRecordDTO;

/**
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 12, 2011 10:20:36 AM
 *          ��˵���������ص����̱�  ͷ��Ϣ
 */
public class EamAddressAddHDTO extends AMSFlowDTO {

    private String transId = "";             //�������   8
    private String transNo = "";             //���ݱ��   8
    private String transStatus = "";         //����״̬
    private int organizationId;         //��˾ID    8
    private String dept = "";                //����        #
    private String createdReason = "";         //���ݴ���ԭ��

    private String organizationName = "";       //��˾����       8
    private String deptName = "";               //��������
    private String createdByName = "";         //��������

    private String company = "";                //��˾����111
    private String username = "";
    private String deptCode = "";
    private String excel="";
    
    private String emergentLevel = ""; //�����̶�
    private String emergentLevelOption = ""; //�����ɶ�������  
    
    private String addrMaintainTypeOption = ""; //ά������������

    public String getAddrMaintainTypeOption() {
		return addrMaintainTypeOption;
	}

	public void setAddrMaintainTypeOption(String addrMaintainTypeOption) {
		this.addrMaintainTypeOption = addrMaintainTypeOption;
	}

	public EamAddressAddHDTO() {
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCreatedReason() {
        return createdReason;
    }

    public void setCreatedReason(String createdReason) {
        this.createdReason = createdReason;
    }


    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getExcel() {
        return excel;
    }

    public void setExcel(String excel) {
        this.excel = excel;
    }

	public String getEmergentLevel() {
		return emergentLevel;
	}

	public void setEmergentLevel(String emergentLevel) {
		this.emergentLevel = emergentLevel;
	}

	public String getEmergentLevelOption() {
		return emergentLevelOption;
	}

	public void setEmergentLevelOption(String emergentLevelOption) {
		this.emergentLevelOption = emergentLevelOption;
	}
}
