package com.sino.ams.newasset.report.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-14
 * Time: 16:50:02
 * To change this template use File | Settings | File Templates.
 * Changed by ���� at 2009-09-21�� because add ����ճ��������
 */
public class ReportInDataDTO extends CommonRecordDTO {
    private String reportId = "";//ID
    private String period = "" ;//����ڼ�
    private String managerGuideType = "";//����ָ���౨������
    private int organizationId;//OU
    private String orgName="";//OU
    private int projectTrunAssets = 0;//�������ڹ���ת�ʶ�
    private int projectSumAssets = 0;//�����ۼ�Ͷ���ܶ�
    private int noTimelyReportNum = 0 ;//δ��ʱ�ϱ�����
    private int assetsmentReportNum = 0;//������Ӧ�ϱ�����
    private int assetsmentFalseNum = 0;//�������ڷ�����ת���ʲ���׼ȷ������
    private int assetsmentAssetsSum = 0;//��������ת���ʲ�����
    private int completeCheckNum = 0;//ʵ����ɵ��ʲ�ʵ��������̵����񹤵�����
    
    private int planCheckNum = 0;//�ƻ��涨���ʲ�����̵����񹤵�����
    private int accountMatchCase = 0;//�������ʵ������ʲ�����
    private int checkAssetsSum = 0;//����ʲ�������
    private int assetsCopNum = 0;//������������ɵ��ճ�Ѳ���̵�Ĺ�����
    private int assetsCopSum = 0;//�����ڼ��ڼƻ����ճ�Ѳ���̵㹤������
    private int assetsMatchCase = 0;//�̵�����ʵ������ʲ�����
    private int assetsCheckSum = 0;//�̵��ʲ�������
    private int accurateErrorNumber = 0;//������صĲ�����

    //Add by ���� at 2009-09-21
    private int curValue;   //ճ������ĳ���豸����
    private int totalValue;   //����豸����
    private String companyCode = "";
    private String kpiCode = "";    //ָ�����
    private String kpiName = "";    //ָ������
    private boolean isKpi = false;      //�Ƿ�KPIָ��
    private String value = ""; //ֵ

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }
 

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

     

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getManagerGuideType() {
        return managerGuideType;
    }

    public void setManagerGuideType(String managerGuideType) {
        this.managerGuideType = managerGuideType;
    }

 

    public int getCurValue() {
        return curValue;
    }

    public void setCurValue(int curValue) {
        this.curValue = curValue;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * Function:       ��ȡ�Ƿ�KPIָ��
     * @return         String���ͣ���Y����ʾ����KPIָ�꣬��N����ʾ������KPIָ��
     * Add by liyi at 2009-09-25, because this used in KPI 
     */
    public boolean getKpi() {
        return isKpi;
    }

    /**
     * Function:        �����Ƿ�����KPIָ��
     * @param kpi       String���ͣ���Y����ʾ����KPIָ�꣬��N����ʾ������KPIָ��
     */
    public void setKpi(boolean kpi) {
        isKpi = kpi;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getProjectTrunAssets() {
		return projectTrunAssets;
	}

	public void setProjectTrunAssets(int projectTrunAssets) {
		this.projectTrunAssets = projectTrunAssets;
	}

	public int getProjectSumAssets() {
		return projectSumAssets;
	}

	public void setProjectSumAssets(int projectSumAssets) {
		this.projectSumAssets = projectSumAssets;
	}

	public int getNoTimelyReportNum() {
		return noTimelyReportNum;
	}

	public void setNoTimelyReportNum(int noTimelyReportNum) {
		this.noTimelyReportNum = noTimelyReportNum;
	}

	public int getAssetsmentReportNum() {
		return assetsmentReportNum;
	}

	public void setAssetsmentReportNum(int assetsmentReportNum) {
		this.assetsmentReportNum = assetsmentReportNum;
	}

	public int getAssetsmentFalseNum() {
		return assetsmentFalseNum;
	}

	public void setAssetsmentFalseNum(int assetsmentFalseNum) {
		this.assetsmentFalseNum = assetsmentFalseNum;
	}

	public int getAssetsmentAssetsSum() {
		return assetsmentAssetsSum;
	}

	public void setAssetsmentAssetsSum(int assetsmentAssetsSum) {
		this.assetsmentAssetsSum = assetsmentAssetsSum;
	}

	public int getCompleteCheckNum() {
		return completeCheckNum;
	}

	public void setCompleteCheckNum(int completeCheckNum) {
		this.completeCheckNum = completeCheckNum;
	}

	public int getPlanCheckNum() {
		return planCheckNum;
	}

	public void setPlanCheckNum(int planCheckNum) {
		this.planCheckNum = planCheckNum;
	}

	public int getAccountMatchCase() {
		return accountMatchCase;
	}

	public void setAccountMatchCase(int accountMatchCase) {
		this.accountMatchCase = accountMatchCase;
	}

	public int getCheckAssetsSum() {
		return checkAssetsSum;
	}

	public void setCheckAssetsSum(int checkAssetsSum) {
		this.checkAssetsSum = checkAssetsSum;
	}

	public int getAssetsCopNum() {
		return assetsCopNum;
	}

	public void setAssetsCopNum(int assetsCopNum) {
		this.assetsCopNum = assetsCopNum;
	}

	public int getAssetsCopSum() {
		return assetsCopSum;
	}

	public void setAssetsCopSum(int assetsCopSum) {
		this.assetsCopSum = assetsCopSum;
	}

	public int getAssetsMatchCase() {
		return assetsMatchCase;
	}

	public void setAssetsMatchCase(int assetsMatchCase) {
		this.assetsMatchCase = assetsMatchCase;
	}

	public int getAssetsCheckSum() {
		return assetsCheckSum;
	}

	public void setAssetsCheckSum(int assetsCheckSum) {
		this.assetsCheckSum = assetsCheckSum;
	}

	public int getAccurateErrorNumber() {
		return accurateErrorNumber;
	}

	public void setAccurateErrorNumber(int accurateErrorNumber) {
		this.accurateErrorNumber = accurateErrorNumber;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
